package it.seda.template.taglib.time.renderer;

import it.seda.template.taglib.time.DateTime;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author f.ricci
 *
 */
public class DateRenderer implements Renderer {
	
	private DateTime dateTime;
	protected SimpleDateFormat dateFormat;
	private String pattern;
	private List<PartRenderer> renderers;
	private String path;
	
	private char sep='/';
	
	private int xy;
	private int xM;
	private int xd;
	
	private Integer minYEar;
	private Integer maxYear;
	private int currentYear;
	
	private String cssClass;
	private String cssClassSep;
	
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public void setCssClassSep(String cssClassSep) {
		this.cssClassSep = cssClassSep;
	}

	public DateRenderer(String path, DateTime dateTime, SimpleDateFormat dateFormat, Integer minYEar, Integer maxYear) {
		this.path=path;
		this.dateTime=dateTime;
		this.dateFormat=dateFormat;
		this.pattern=dateFormat.toPattern();
		this.currentYear=Integer.parseInt(dateTime.getYear());
		
		if (minYEar!=null) {
			this.minYEar=minYEar;
		} else {
			this.minYEar=currentYear-5;
		}
		
		if (maxYear!=null) {
			this.maxYear=maxYear;
		} else {
			this.maxYear=currentYear+5;
		}		
		
		resolvePartRenderers();
	}
	
	private void resolvePartRenderers() {
		
		renderers=new ArrayList<PartRenderer>();
		
		xy = pattern.indexOf('y');
		xM = pattern.indexOf('M');
		xd = pattern.indexOf('d');

		BoundedPartRenderer years=new BoundedPartRenderer(path, DateTimePart.YEAR, xy, minYEar, maxYear+1, 4, ' ', currentYear);
		years.setCssClass(cssClass);
		BoundedPartRenderer months=new BoundedPartRenderer(path, DateTimePart.MONTH_OF_YEAR, xM, 1, 13, 2, count(pattern,'M')>1?'0':' ', Integer.parseInt(dateTime.getMonthOfYear()));
		months.setCssClass(cssClass);
		BoundedPartRenderer days=new BoundedPartRenderer(path, DateTimePart.DAY_OF_MONTH, xd, 1, 32, 2, count(pattern,'d')>1?'0':' ', Integer.parseInt(dateTime.getDayOfMonth()));
		days.setCssClass(cssClass);

		renderers.add(years);
		renderers.add(months);
		renderers.add(days);
		
		int[] indexes = new int[]{xy,xM,xd};
		Arrays.sort(indexes);
		
		renderers.add(new FixedPartRenderer(indexes[1]-1, String.valueOf(sep), cssClassSep));
		renderers.add(new FixedPartRenderer(indexes[2]-1, String.valueOf(sep), cssClassSep));
		
		Collections.sort(renderers, PART_COMPARATOR);
	}

	@Override
	public void render(Writer writer) throws IOException {

		for (PartRenderer pRenderer : renderers) {
			writer.write(pRenderer.render());
		}
	}

	@Override
	public String toString() {
		return "DateRenderer [path=" + path + ", pattern=" + pattern + "]";
	}

	protected void resolveSep() {
		if (pattern.indexOf('/')>-1) {
			sep='/';
		} else if (pattern.indexOf('.')>-1) {
			sep='.';
		} else if (pattern.indexOf('-')>-1) {
			sep='-';
		} 
	}
	
	protected int count(String haystack, char needle) {
	    int count = 0;
	    for (int i=0; i < haystack.length(); i++) {
	        if (haystack.charAt(i) == needle) {
	             count++;
	        }
	    }
	    return count;
	}
}
