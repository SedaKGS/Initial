package it.seda.template.taglib.time;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

public class Listformat {

	public static void main(String[] args) {
		/* Array of available locales */
	    Locale[] locales = DateFormat.getAvailableLocales();

	    String result = "";

	    for (Locale locale : locales) {

	        /* Add language info, if necessary */
	    	result += locale.getDisplayCountry() + "\t";
            result += locale.getLanguage() + '\t';

	        /* Retrieving pattern */ 
	        try {
	        	SimpleDateFormat simpleDateFormat = (SimpleDateFormat) DateFormat.getDateTimeInstance(DateFormat.SHORT,
                        DateFormat.SHORT, locale);
	            result += simpleDateFormat.toPattern() + '\t';
	            DateFormatSymbols dateFormatSymbols = simpleDateFormat.getDateFormatSymbols();
	            result += Arrays.asList(dateFormatSymbols.getAmPmStrings()).toString() + '\t';
	            result += Arrays.asList(dateFormatSymbols.getEras()).toString() + '\t';
	            result += simpleDateFormat.format(new java.util.Date());
	            result +="\n";
	        } catch (ClassCastException e) {
	            // ******************************** //
	            // What's up? Is there another way? //
	            // ******************************** //
	        }

	    }
	    System.out.println(result);
	}
	
}
