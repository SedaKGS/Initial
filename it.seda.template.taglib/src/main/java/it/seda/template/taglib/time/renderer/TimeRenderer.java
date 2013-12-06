package it.seda.template.taglib.time.renderer;

import it.seda.template.taglib.time.DateTime;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author f.ricci
 *
 */
public class TimeRenderer extends AbstractRenderer {
	
	private DateTime dateTime;
	protected SimpleDateFormat timeFormat;
	private String pattern;
	private List<PartRenderer> renderers;
	private String[] amPmStrings;
	private String path;
	
	private int ax;
	private int hx;
	private int mx;
	
	public TimeRenderer(String path, DateTime dateTime, SimpleDateFormat timeFormat) {
		this.path=path;
		this.dateTime=dateTime;
		this.timeFormat=timeFormat;
		this.pattern=timeFormat.toPattern();
		this.amPmStrings=timeFormat.getDateFormatSymbols().getAmPmStrings();
		resolvePartRenderers();
	}
	
	private void resolvePartRenderers() {
		
		renderers=new ArrayList<PartRenderer>();
		
		ax = pattern.indexOf('a');
		hx = pattern.indexOf('h')>-1?pattern.indexOf('h'):pattern.indexOf('H');
		mx = pattern.indexOf('m')>-1?pattern.indexOf('m'):pattern.indexOf('M');
		
		BoundedPartRenderer hours=null;
		if (pattern.contains("HH")) {
			hours = new BoundedPartRenderer(path, DateTimePart.HOUR_OF_DAY, hx, 0, 24, 2, '0', nullOrInteger(dateTime.getHourOfDay()));
		} else if (pattern.contains("H")) {
			hours = new BoundedPartRenderer(path, DateTimePart.HOUR_OF_DAY, hx, 0, 24, 2, ' ', nullOrInteger(dateTime.getHourOfDay()));
		} else {
			if (pattern.contains("hh")) {
				hours = new BoundedPartRenderer(path, DateTimePart.HOUR, hx, 1, 13, 2, '0', nullOrInteger(dateTime.getHour()));
			} else {
				hours = new BoundedPartRenderer(path, DateTimePart.HOUR, hx, 1, 13, 2, ' ', nullOrInteger(dateTime.getHour()));
			}
			if (ax==-1) {
				ax=mx+1;
			}
			if (ax>-1) {
				renderers.add(new AmPmPartRenderer(path, ax, amPmStrings, dateTime.getAmPm()));
			}
			if (ax == pattern.length()) {
				renderers.add(new FixedPartRenderer(ax-1, "&nbsp;", cssClassSep));
			}
		}
		applyToPart(hours);

		renderers.add(hours);
		renderers.add(new FixedPartRenderer(mx-1, ":", cssClassSep));
		
		BoundedPartRenderer minutes=null;
		if (pattern.contains("mm")) {
			minutes = new BoundedPartRenderer(path, DateTimePart.MINUTE_OF_HOUR, mx, 1, 60, 2, '0', Integer.valueOf(dateTime.getMinuteOfHour()));
		} else {
			minutes = new BoundedPartRenderer(path, DateTimePart.MINUTE_OF_HOUR, mx, 1, 60, 2, ' ', Integer.valueOf(dateTime.getMinuteOfHour()));
		}
		
		applyToPart(minutes);
		
		renderers.add(minutes);
		
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
		return "TimeRenderer [path=" + path + ", pattern=" + pattern + "]";
	}

	
}
