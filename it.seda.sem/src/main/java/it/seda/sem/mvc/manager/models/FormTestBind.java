package it.seda.sem.mvc.manager.models;

import it.seda.template.taglib.time.DateTime;
import it.seda.template.taglib.time.ValidDateTime;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;

public class FormTestBind {
    
	@ValidDateTime
	private DateTime date;
	@ValidDateTime
	private DateTime time;
	@ValidDateTime
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
		// ragionare sulla eventuale data di default
		LocaleContext localeContext = LocaleContextHolder.getLocaleContext();
		
		date = new DateTime();
		time = new DateTime();
		datetime = new DateTime();		
	}
	
	

}
