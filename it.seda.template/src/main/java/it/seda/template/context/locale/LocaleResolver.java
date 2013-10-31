/**
 * 
 */
package it.seda.template.context.locale;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author f.ricci
 *
 */
public class LocaleResolver {

	public Locale getLocale() {
		Locale locale = LocaleContextHolder.getLocale();
		if (locale==null) {
			locale = localFromRequest();
			if (locale == null) {
				locale = Locale.getDefault();				
			}
		}
		
		return locale;
	}

	private Locale localFromRequest() {
		HttpServletRequest servletRequest = null;
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if(requestAttributes != null && requestAttributes instanceof ServletRequestAttributes) {
			servletRequest = ((ServletRequestAttributes)requestAttributes).getRequest();
			return servletRequest.getLocale();
		}
		return null;
	}

	
	
	
}
