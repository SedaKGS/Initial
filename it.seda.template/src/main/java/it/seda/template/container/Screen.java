package it.seda.template.container;

import it.seda.template.context.TemplateResource;

import java.util.HashMap;
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
	private String template;
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

		logger.debug(name + " inherited #" + p + " parameters from " + parent.getName());		
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
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public Map<String, Parameter> getParameters() {
		return parameters;
	}
	
	public Screen() {
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
				+ (template==null?"*default*":template)  
				+ ", parameters=" + parameters 
				+ ", resource=" + resource+"]";
	}
	
}
