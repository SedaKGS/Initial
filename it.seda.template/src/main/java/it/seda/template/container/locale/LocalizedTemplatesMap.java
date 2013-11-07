package it.seda.template.container.locale;



import it.seda.template.utils.LocaleComparator;

import java.util.Locale;
import java.util.Set;
import java.util.TreeMap;



@SuppressWarnings("serial")
public class LocalizedTemplatesMap<K, V> extends TreeMap<Locale, LocalizedTemplate>  {

	@Override
	public boolean containsKey(Object localeToFind) {
		Locale l;
		if(!(localeToFind instanceof Locale)){
			return false;
		}
		l=(Locale)localeToFind;
		int weight = LocaleComparator.localeWeight(l);
		boolean found=false;
		Set<Locale> locales=this.keySet();
		for (Locale locale : locales) {
			if (l.getLanguage().equals(locale.getLanguage())) {
				int lw = LocaleComparator.localeWeight(locale);
				found=LocaleComparator.localeEqual(locale,l,Math.min(weight,lw));
				if (found) break;
			}
		}		
		return found;
		
	}

	
	
	
	@Override
	public LocalizedTemplate get(Object key) {
		LocalizedTemplate localizedtemplate=null;
		Locale localeKey=null;
		boolean found;
		if(!(key instanceof Locale)){
			return localizedtemplate;
		}
		localeKey=(Locale)key;
		int weight = LocaleComparator.localeWeight(localeKey);
		Set<Locale> locales=this.keySet();
		for (Locale locale : locales) {
			if (localeKey.getLanguage().equals(locale.getLanguage())) {
				int lw = LocaleComparator.localeWeight(locale);
				found=LocaleComparator.localeEqual(locale,localeKey,Math.min(weight,lw));
				if (found) {
					localizedtemplate=super.get(locale);
				    break;
				}
			}
		}		
		return localizedtemplate;
	}




	public LocalizedTemplatesMap() {
		super(new LocaleComparator<Locale>());
	}

	
	

	

	

}
