/**
 * 
 */
package it.seda.template.container;

import it.seda.template.context.TemplateResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author f.ricci
 *
 */
public class Template {
	
	private boolean def;
	private List<Locale> locales;
	private Set<String> themes;	
	private String name;
	private String url;
	private int priority;
	private TemplateResource resource;
	
	
	public TemplateResource getResource() {
		return resource;
	}
	public void setResource(TemplateResource resource) {
		this.resource = resource;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public boolean isDefault() {
		return def;
	}
	public void setDefault(boolean def) {
		this.def = def;
	}
	public void addLocales(List<Locale> locales) {
		this.locales.addAll(locales);
	}
	public List<Locale> getLocales() {
		return locales;
	}	
	public void addThemes(Set<String> themes) {
		this.themes.addAll(themes);
	}
	public Set<String> getThemes() {
		return themes;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Template() {
		locales=new ArrayList<Locale>();
		themes=new TreeSet<String>();
	}
	
	@Override
	public String toString() {
		return "Template [default=" + def + ", name=" + name + ", url=" + url
				+ ", themes=" + themes 
				+ ", locales=" + locales + ", resource=" + resource + "]";
	}

	
}
