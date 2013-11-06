package it.seda.template.utils;

import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;

import org.springframework.web.context.WebApplicationContext;

public class Utils {

	static Pattern IN_QUOTE_PATTERN = Pattern.compile("'([^']*)'");
	
	public final static String resolveWacName(String displayName) {
		Matcher matcher = IN_QUOTE_PATTERN.matcher(displayName);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}

	public final static String resolveWacName(WebApplicationContext wac) {
		return resolveWacName(wac.getDisplayName());
	}
	
	public static Locale localeFromString(String locale) {
		if(locale==null){
			return Locale.ROOT;
		}
		if (locale.equalsIgnoreCase("default")) {
			return Locale.getDefault();
		} else if (locale.equalsIgnoreCase("root")) {
			return Locale.ROOT;
		} 
	    String parts[] = locale.split("_", -1);
	    if (parts.length == 1) return new Locale(parts[0]);
	    else if (parts.length == 2) return new Locale(parts[0], parts[1]);
	    else if (parts.length > 3) return new Locale(parts[0], parts[1], parts[2]);
	    else {
	    	return Locale.ROOT;
	    }
	}	
	
	/**
     * Wraps a ServletException to create an IOException with the root cause if present.
     *
     * @param ex The exception to wrap.
     * @param message The message of the exception.
     * @return The wrapped exception.
     */
    public static IOException wrapServletException(ServletException ex,
            String message) {
        IOException retValue;
        Throwable rootCause = ex.getRootCause();
        if (rootCause != null) {
            // Replace the ServletException with an IOException, with the root
            // cause of the first as the cause of the latter.
            retValue = new IOException(message, rootCause);
        } else {
            retValue = new IOException(message, ex);
        }

        return retValue;
    }
	
}
