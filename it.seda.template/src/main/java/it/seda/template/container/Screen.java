package it.seda.template.container;

import it.seda.template.container.command.CommandTheme;
import it.seda.template.container.command.CommandThemeDefinitions;
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
	private String template;
	private List<Parameter> parametersList;

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
		inherited = true;
		int p = 0;
		for (Parameter parameter : parent.getParametersList()) {
			if (!parametersList.contains(parameter)) {
				p++;
				parametersList.add(parameter);
			} else {
				Parameter param = parametersList.get(parametersList
						.indexOf(parameter));
				int l = 0;
				for (Locale locale : parameter.getLocales()) {
					if (!param.getLocales().contains(locale)) {
						param.addLocale(locale);
						l++;
					}
				}
				if (l > 0)
					logger.debug(name + " inherited #" + l + " locales from "
							+ parent.getName());
				
				//Theme
				int t=0;
				for(CommandTheme ct:parameter.getCommandTheme()){
					if(!param.getCommandTheme().contains(ct)){
						param.addCommandTheme(ct);
						t++;
					}
				if(t>0)	
					logger.debug(name + " inherited #" + t + " commandThemes from "
							+ parent.getName());
				}
				//Theme
			}
		}
		logger.debug(name + " inherited #" + p + " parameters from "
				+ parent.getName());
	}

	public List<Parameter> getParametersList() {
		return this.parametersList;
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
		return inherit != null;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Map<String, Parameter> getParameters(Locale locale,String thm) {
		Map<String, Parameter> parameters = new HashMap<String, Parameter>();
		for (Parameter parameter : parametersList) {
			if ((parameter.contains((Locale.ROOT))|| parameter.contains(locale))) {
				   for (CommandTheme ct: parameter.getCommandTheme()) {
					   if(ct.evaluateTheme(thm)||ct.evaluateTheme(CommandThemeDefinitions.DEFAULT_THEME)){
					   parameters.put(parameter.getKey(), parameter);
					   }
				}
				   
			}
		}
		return parameters;
	}

	public Screen() {
		parametersList = new ArrayList<Parameter>(4);

	}

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
