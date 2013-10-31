package it.seda.template.container;

import it.seda.template.context.TemplateResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author f.ricci
 *
 */
public class Screen {

	private Logger logger = LoggerFactory.getLogger(Screen.class);	
	
	private String name;
	private String inherit;
	private boolean inherited;
	private Template template;
	private List<Locale> locales;
	private Map<String, Parameter> parameters;
	private TemplateResource resource;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean hasInerited() {
		return inherited;
	}
	public void inherit(Screen parent) {
		inherited=true;
		int p=0;
		for (String key : parent.getParameters().keySet()) {
			if (!parameters.containsKey(key)) {
				p++;
				parameters.put(key, parent.getParameters().get(key));
			}
		}

		int l=0;
		for (Locale locale : parent.getLocales()) {
			if (!locales.contains(locale)) {
				l++;
				locales.add(locale);
			}
		}
		logger.debug(name + " inherited #" + p + " parameters and #" + l + " locales from " + parent.getName());		
	}
	public TemplateResource getResource() {
		return resource;
	}
	public void setResource(TemplateResource resource) {
		this.resource = resource;
	}
	public String getInherit() {
		return inherit;
	}
	public void setInherit(String inherit) {
		this.inherit = inherit;
	}
	public boolean hasInheritance() {
		return inherit!=null;
	}
	public Template getTemplate() {
		return template;
	}
	public void setTemplate(Template template) {
		this.template = template;
	}
	public void addLocales(List<Locale> locales) {
		this.locales.addAll(locales);
	}	
	public List<Locale> getLocales() {
		return locales;
	}	
	public Map<String, Parameter> getParameters() {
		return parameters;
	}
	
	public Screen() {
		locales=new ArrayList<Locale>();
		parameters=new HashMap<String, Parameter>(4);
	}
	
	
	public void addParameter(Parameter parameter) {
		if (parameters.containsKey(parameter.getKey())) {
			if (logger.isInfoEnabled()) {
				logger.info("duplicate parameter key: " + parameter.toString());
				return;				
			}
		}
		
		parameters.put(parameter.getKey(), parameter);
	}
	
	@Override
	public String toString() {
		return "Screen [name=" + name + ", inherit="
				+ inherit + ", template="
				+ template + ",locales=" + locales 
				+ ", parameters=" + parameters 
				+ ", resource=" + resource+"]";
	}
	
}
