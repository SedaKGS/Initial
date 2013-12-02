package it.seda.template.taglib.time.renderer;

/**
 * 
 * @author f.ricci
 *
 */
public enum DateTimePart {
	YEAR("year"), CENTURY("century"),YEAR_OF_CENTURY("yearOfCentury"), MONTH_OF_YEAR("monthOfYear"), 
	DAY_OF_MONTH("dayOfMonth"), HOUR("hour"), HOUR_OF_DAY("hourOfDay"), MINUTE_OF_HOUR("minuteOfHour"),
	SECOND_OF_MINUTE("secondOfminute"), MILLIS_OF_SECOND("millisOfSecond"), AMPM("amPm"), NO_PART("");
	
	private String nestedPath;
	
	DateTimePart(String nestedPath) {
		this.nestedPath=nestedPath;
	}
	
	public String getNestedPath() {
		return nestedPath;
	}
	
	public String getFullPath(String parent) {
		return parent+"."+nestedPath;
	}		
	
}	