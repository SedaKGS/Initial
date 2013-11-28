package it.seda.sem.mvc.manager.models;

import it.seda.sem.mvc.manager.DateTimeForm;
import it.seda.sem.mvc.manager.ValidDateTime;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;

public class FormTestBind {
    
	@ValidDateTime
	private DateTimeForm date;
	@ValidDateTime
	private DateTimeForm time;
	@ValidDateTime
	private DateTimeForm datetime;
	
	public DateTimeForm getDate() {
		return date;
	}
	public void setDate(DateTimeForm date) {
		this.date = date;
	}
	public DateTimeForm getTime() {
		return time;
	}
	public void setTime(DateTimeForm time) {
		this.time = time;
	}
	public DateTimeForm getDatetime() {
		return datetime;
	}
	public void setDatetime(DateTimeForm datetime) {
		this.datetime = datetime;
	}
	

	
	public FormTestBind() {
		// ragionare sulla eventuale data di default
		LocaleContext localeContext = LocaleContextHolder.getLocaleContext();
		
		date = new DateTimeForm();
		time = new DateTimeForm();
		datetime = new DateTimeForm();		
	}
	
	

}
