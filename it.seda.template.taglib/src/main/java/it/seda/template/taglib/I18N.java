package it.seda.template.taglib;

import javax.servlet.http.HttpServletRequest;

import it.seda.template.request.ParameterContext;
import it.seda.template.utils.LocaleOrientation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.context.Theme;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;



public class I18N {

	static Logger logger = LoggerFactory.getLogger(I18N.class);

	protected static ParameterContext getContext(){
		ParameterContext parameterContext=ParameterContext.retrieve(getHttpRequest());
		return parameterContext;
	}
	
	protected static HttpServletRequest getHttpRequest(){
		RequestAttributes attributes=RequestContextHolder.getRequestAttributes();
		return ((ServletRequestAttributes)attributes).getRequest();
	}	

	protected static Theme getTheme() {
		Theme theme = RequestContextUtils.getTheme(getHttpRequest());
		return theme;
	}
	
	protected static String getMessage(String key,Object [] args){
		if(key==null || key.isEmpty()) return null;
		String message=null;
		try{
			message=getContext().getMessage(key, args);		
		}
		catch(NoSuchMessageException e) {
			logger.warn(e.getMessage());
		}
		return message;
	}

	public static String printMessage(String key,Object [] args){
		return getMessage(key, args);
	}


	public static String printMessage(String key){
		return printMessage(key,null);
	}
	
	public static String printMessageTheme(String key){
		Theme theme = getTheme();
		if (theme!=null) {
			return theme.getMessageSource().getMessage(key, null, getContext().getLocale());
		} 

		logger.warn("ThemeResolver not found");
		
		return "";
	}	
	
	public static String locale() {
		return LocaleContextHolder.getLocale().toString();
	}	
	
	public static String direction() {
		return LocaleOrientation.getOrientation(getContext().getLocale())==LocaleOrientation.RIGHT_TO_LEFT?"rtl":"ltr";
	}
}
