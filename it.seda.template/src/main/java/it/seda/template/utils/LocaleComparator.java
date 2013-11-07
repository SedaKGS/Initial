/**
 * 
 */
package it.seda.template.utils;

import java.util.Comparator;
import java.util.Locale;

/**
 * @author raffaello.ponzelli
 *
 */
public class LocaleComparator<T> implements Comparator<Locale> {

	@Override
	public int compare(Locale ll, Locale lr) {
		int llw=localeWeight(ll);
		int lrw=localeWeight(lr);
		
		if (llw>lrw) {
			return -1;
		}
		
		if (llw==lrw) {
			return -1*ll.toString().compareTo(lr.toString());
		}
		
		return +1;
	}

	
	public static int localeWeight(Locale ll) {
		int weight=0;
		if (!ll.getCountry().isEmpty()) {
			weight++;
		}
		if (!ll.getLanguage().isEmpty()) {
			weight++;
		}
		if (!ll.getVariant().isEmpty()) {
			weight++;
		}
		if (!ll.getScript().isEmpty()) {
			weight++;
		}
	
		return weight;
	}
	
	public static boolean localeEqual(Locale locale, Locale l, int weight) {
		boolean equal=false;
		switch (weight) {
		case 1:
			equal=locale.getLanguage().equals(l.getLanguage());
			break;
		case 2:
			equal=(locale.getLanguage().equals(l.getLanguage()) &&
					locale.getCountry().equals(l.getCountry()));
			break;
		case 3:
			equal=(locale.getLanguage().equals(l.getLanguage()) &&
					locale.getCountry().equals(l.getCountry()) &&
					locale.getVariant().equals(l.getVariant()));			
			break;			
		default:
			equal=locale.equals(l);
			break;
		}
		return equal;
	}	
}
