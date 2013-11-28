package it.seda.sem.mvc.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Wrapper class for date time used in the spring form
 * @author f.ricci
 *
 */
public class DateTimeForm {

	public final static String DATE_FORMAT="yyyy-MM-dd HH:mm:ss.SSS";
	public final static String FORMATTER="%04d-%02d-%02d %02d:%02d:%02d.%03d";	
	
	private final static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	
	static {
		sdf.setLenient(false);
	}
	
	private boolean valid=false;
	private String message;	
	private String formatted;
	private Date parsed;
	
	
	private String year;
	private String monthOfYear;
	private String dayOfMonth;
	
	private String hourOfDay;
	private String minuteOfHour;
	private String secondOfMinute;
	private String millisOfSecond;	
	
	
	public boolean isValid() {
		return valid;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getFormatted() {
		return formatted;
	}
	
	public Date getDate() {
		return parsed;
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
		rebuild();
	}

	public String getMonthOfYear() {
		return monthOfYear;
	}

	public void setMonthOfYear(String monthOfYear) {
		this.monthOfYear = monthOfYear;
		rebuild();
	}

	public String getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(String dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
		rebuild();
	}

	public String getHourOfDay() {
		return hourOfDay;
	}

	public void setHourOfDay(String hourOfDay) {
		this.hourOfDay = hourOfDay;
		rebuild();
	}

	public String getMinuteOfHour() {
		return minuteOfHour;
	}

	public void setMinuteOfHour(String minuteOfHour) {
		this.minuteOfHour = minuteOfHour;
		rebuild();
	}

	public String getSecondOfMinute() {
		return secondOfMinute;
	}

	public void setSecondOfMinute(String secondOfMinute) {
		this.secondOfMinute = secondOfMinute;
		rebuild();
	}

	public String getMillisOfSecond() {
		return millisOfSecond;
	}

	public void setMillisOfSecond(String millisOfSecond) {
		this.millisOfSecond = millisOfSecond;
		rebuild();
	}

	private void commonConstructor(int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            int millisOfSecond) {
		
		this.year=String.valueOf(year);
		this.monthOfYear=String.valueOf(monthOfYear);
		this.dayOfMonth=String.valueOf(dayOfMonth);
		
		this.hourOfDay=String.valueOf(hourOfDay);
		this.minuteOfHour=String.valueOf(minuteOfHour);
		this.secondOfMinute=String.valueOf(secondOfMinute);
		this.millisOfSecond=String.valueOf(millisOfSecond);		
		
		rebuild();
	}
	
	public DateTimeForm() {

		Calendar calendar = Calendar.getInstance();

		commonConstructor(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH), 
				calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND), 
				calendar.get(Calendar.MILLISECOND));

	}
	
	public DateTimeForm (
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            int millisOfSecond) {
    	
		commonConstructor(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);
    	
    }		
	
	public DateTimeForm (
            int year,
            int monthOfYear,
            int dayOfMonth) {
		
		commonConstructor(monthOfYear, monthOfYear, dayOfMonth, 0, 0, 0, 0);
    	
    }

	public DateTimeForm (
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            int millisOfSecond) {

		Calendar calendar = Calendar.getInstance();		
		
		commonConstructor(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH),
				hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);
    	
    }
	
	private void rebuild() {
		
		try {
			final int year=Integer.parseInt(this.year);
			final int monthOfYear=Integer.parseInt(this.monthOfYear);
			final int dayOfMonth=Integer.parseInt(this.dayOfMonth);
			final int hourOfDay=Integer.parseInt(this.hourOfDay);
			final int minuteOfHour=Integer.parseInt(this.minuteOfHour);
			final int secondOfMinute=Integer.parseInt(this.secondOfMinute);
			final int millisOfSecond=Integer.parseInt(this.millisOfSecond);
			
			formatted = String.format(FORMATTER, year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);

			parsed = sdf.parse(formatted);
			
		} catch (NumberFormatException x) {
			message = String.format("Invalid date part %s",year,monthOfYear,dayOfMonth,hourOfDay,minuteOfHour,secondOfMinute,millisOfSecond);
			valid = false;
		} catch (ParseException x) {
			message = x.getMessage();
			valid = false;
		}		
		
	}
	
	@SuppressWarnings("serial")
	public class DateTimeException extends RuntimeException {

		public DateTimeException(String message, Throwable cause) {
			super(message, cause);
		}

		public DateTimeException(String message) {
			super(message);
		}

		public DateTimeException(Throwable cause) {
			super(cause);
		}
		
	}
	
}
