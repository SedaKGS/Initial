package it.seda.template.container;

import it.seda.template.renderer.Renderer;
import it.seda.template.utils.LocaleComparator;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author f.ricci
 *
 */

public class Parameter {

	private String key;
	private String value;
	private Renderer renderer;
	private Set<Locale> locales;
	
	
	public static void main(String[] args) {
		Parameter parameter = new Parameter();
		parameter.addLocale(Locale.ROOT);
//		parameter.addLocale(Locale.UK);
		parameter.addLocale(Locale.US);
		parameter.addLocale(Locale.CANADA_FRENCH);
		parameter.addLocale(Locale.CANADA);
		parameter.addLocale(Locale.ENGLISH);
		for (Locale locale : parameter.getLocales()) {
			System.out.println("'"+locale+"'");			
		}
		System.out.println(Locale.UK + " found '"+parameter.contains(Locale.UK)+"'");		
		System.out.println(Locale.US + " found '"+parameter.contains(Locale.US)+"'");
		System.out.println(Locale.ITALY + " found '"+parameter.contains(Locale.ITALY)+"'");
	}
	
	
	public Parameter() {
		locales=new TreeSet<Locale>(new LocaleComparator<Locale>());
	}

	
	public boolean contains(Locale l){
		int weight = LocaleComparator.localeWeight(l);
		boolean found=false;
		for (Locale locale : locales) {
			if (l.getLanguage().equals(locale.getLanguage())) {
				int lw = LocaleComparator.localeWeight(locale);
				found=LocaleComparator.localeEqual(locale,l,Math.min(weight,lw));
				if (found) break;
			}
		}		
		return found;
	}

	public void addLocale(Locale locale){
		locales.add(locale);
	}
	public void addLocales(List<Locale> locales){
		this.locales.addAll(locales);
	}
	public Set<Locale> getLocales(){
		return locales;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Renderer getRenderer() {
		return renderer;
	}
	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null) return false;
		if(obj instanceof Parameter){
			Parameter p=(Parameter) obj;
			if(p.getKey().equals(key)) return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Parameter [key=" + key + ", value=" + value + ", renderer="
				+ renderer + "]";
	}
	
}
