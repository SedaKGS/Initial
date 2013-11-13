package it.seda.template.container;

import it.seda.template.container.command.CommandTheme;
import it.seda.template.context.TemplateResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class contains all the attributes present in the tag screen that is
 * used in the seda composition tool's configuration file (es. template.xml).
 * In addition to these parameters this class contains a parameter list in wich every 
 * element represents the tag attribute used in the same configuration file.
 * 
 * <br>
 * 
 * 
 */
public class Screen {

	private Logger logger = LoggerFactory.getLogger(Screen.class);

	private String name;
	private String inherit;
	private boolean inherited;
	private String template;
	private List<Parameter> parametersList;

	private TemplateResource resource;
    
	/**
	 * Returns the screen's name.
	 */
	public String getName() {
		return name;
	}
    /**
     * Sets the screen's name.
     */
	public void setName(String name) {
		this.name = name;
	}
    /**
     * Checks is a screen has already inherited a parameters's list.      
     */
	public boolean hasInerited() {
		return inherited;
	}
    /**
     * This method allow to inherit parameters from a parent screen.
     */
	public void inherit(Screen parent) {
		List<Parameter> parameterListTemp=new ArrayList<Parameter>();
		inherited = true;
		for (Parameter parameter : parent.getParametersList()) {
			if (!parametersList.contains(parameter)) {
				parameterListTemp.add(parameter);
			}
		}
		parametersList.addAll(parameterListTemp);
		logger.debug(name + " inherited #" + parameterListTemp.size() + " parameters from "
				+ parent.getName());
	}
    /**
     * Returns the screen's parameter list.
     */
	public List<Parameter> getParametersList() {
		return this.parametersList;
	}
    /**
     * Returns the template resource.
     */
	public TemplateResource getResource() {
		return resource;
	}
	 /**
     * Sets the template resource.
     */
	public void setResource(TemplateResource resource) {
		this.resource = resource;
	}
    
	/**
	 * Returns the screen parent's name.
	 */
	public String getInherit() {
		return inherit;
	}
	
    /**
     * Sets the screen parent's name.
     */
	public void setInherit(String inherit) {
		this.inherit = inherit;
	}
    
	/**
	 *Checks if the screen has a parent. 
	 */
	public boolean hasInheritance() {
		return inherit != null;
	}
    /**
     * Returns the screen'template name.
     */
	public String getTemplate() {
		return template;
	}
    /**
     * Sets ther screen template's name.
     */
	public void setTemplate(String template) {
		this.template = template;
	}
    /**
     * Returns all the parameters of a given locale and theme.
     */
	public Map<String, Parameter> getParameters(Locale locale,String thm) {
		Map<String, Parameter> parameters = new HashMap<String, Parameter>();
		for (Parameter parameter : parametersList) {
			if ((parameter.contains((Locale.ROOT))|| parameter.contains(locale))) {
				   for (CommandTheme ct: parameter.getCommandTheme()) {
					   if(ct.evaluateTheme(thm)
							   || ct.evaluateTheme(ct.getDefaultTheme())) {
						   parameters.put(parameter.getKey(), parameter);
					   }
				}
				   
			}
		}
		return parameters;
	}
    /**
     * This is the screen's class constructor.
     */
	public Screen() {
		parametersList = new ArrayList<Parameter>(4);

	}
    /*
     * Adds a parameter to the parameter list.
     */
	public void addParameter(Parameter parameter) {
		parametersList.add(parameter);

	}
	@Override
	public String toString() {
		return "Screen [name=" + name + ", inherit=" + inherit + ", template="
				+ (template == null ? "*default*" : template) + ", parameters="
				+ parametersList + ", resource=" + resource + "]";
	}

}
