package it.seda.template.taglib;

import it.seda.template.request.ParameterContext;
import it.seda.template.utils.LocaleOrientation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



public class I18N {

	static Logger logger = LoggerFactory.getLogger(I18N.class);

	protected static ParameterContext getContext(){
		RequestAttributes attributes=RequestContextHolder.getRequestAttributes();
		ParameterContext parameterContext=ParameterContext.retrieve(((ServletRequestAttributes)attributes).getRequest());
		return parameterContext;
	}

	protected static String getMessage(String key,Object [] args){
		if(key==null) return null;
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
	
	public static String direction() {
		return LocaleOrientation.getOrientation(getContext().getLocale())==LocaleOrientation.RIGHT_TO_LEFT?"rtl":"ltr";
	}
}
