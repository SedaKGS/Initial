package it.seda.sem.mvc.manager.models;

import org.joda.time.DateTime;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.support.RequestContextUtils;

public class FormTestBind {
    
	private DateTime date;
	private DateTime time;
	private DateTime datetime;
	
	public DateTime getDate() {
		return date;
	}
	public void setDate(DateTime date) {
		this.date = date;
	}
	public DateTime getTime() {
		return time;
	}
	public void setTime(DateTime time) {
		this.time = time;
	}
	public DateTime getDatetime() {
		return datetime;
	}
	public void setDatetime(DateTime datetime) {
		this.datetime = datetime;
	}
	

	
	public FormTestBind() {
		LocaleContext localeContext = LocaleContextHolder.getLocaleContext();
//		DateTime
//		date=new DateTime(zone);
//		time;
//		datetime;		
		
	}
	
	

}
