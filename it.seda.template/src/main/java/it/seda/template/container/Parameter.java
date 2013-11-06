package it.seda.template.container;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import it.seda.template.renderer.Renderer;

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
	
	
	
	
	
	public Parameter() {
		locales=new HashSet<Locale>();
	}
	public boolean conatains(Locale locale){
		return locales.contains(locale);
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
