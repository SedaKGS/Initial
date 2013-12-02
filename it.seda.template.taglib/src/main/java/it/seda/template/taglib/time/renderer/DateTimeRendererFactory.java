/**
 * 
 */
package it.seda.template.taglib.time.renderer;

import it.seda.template.taglib.time.DateTime;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Locale;

/**
 * @author f.ricci
 *
 */
public class DateTimeRendererFactory {
	
	protected Locale locale;

	protected SimpleDateFormat dateFormat;
	protected SimpleDateFormat timeFormat;

	public final static Comparator<PartRenderer> PART_COMPARATOR = new Comparator<PartRenderer>() {
		@Override
		public int compare(PartRenderer o1, PartRenderer o2) {
			return o1.getPriority()-o2.getPriority();
		}
	};
	
	public static void main(String[] args) throws IOException {
		DateTimeRendererFactory factory;
		PrintWriter writer = new PrintWriter(System.out);
		Locale[] locales = DateFormat.getAvailableLocales();
		for(Locale locale: locales) {
			writer.write("************************************************");
			writer.write("\n");
			writer.write("* locale="+locale.getDisplayCountry()+"\t"+locale.getLanguage());
			writer.write("\n");			
			factory=new DateTimeRendererFactory(locale);
			factory.buildTimeRenderer("time", new DateTime()).render(writer);
			writer.write("\n");			
			factory.buildDateRenderer("date", new DateTime(), null, null).render(writer);
			writer.write("\n");
			writer.flush();
		}
		
	}
	
	public DateTimeRendererFactory(Locale locale) {
		this.locale=locale;
		this.dateFormat = (SimpleDateFormat)DateFormat.getDateInstance(DateFormat.SHORT, locale);
		this.timeFormat = (SimpleDateFormat)DateFormat.getTimeInstance(DateFormat.SHORT, locale);
	}

	public TimeRenderer buildTimeRenderer(String path, DateTime dateTime) {
		return new TimeRenderer(path, dateTime, timeFormat);
	}
	
	public DateRenderer buildDateRenderer(String path, DateTime dateTime, Integer minYear, Integer maxYear) {
		return new DateRenderer(path, dateTime, dateFormat, minYear, maxYear);
	}	

}
