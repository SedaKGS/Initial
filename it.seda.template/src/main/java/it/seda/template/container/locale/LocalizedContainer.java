package it.seda.template.container.locale;

import it.seda.template.container.Screen;
import it.seda.template.container.Template;
import it.seda.template.container.TemplateContainer;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author f.ricci
 *
 */
public class LocalizedContainer {

	private Logger logger = LoggerFactory.getLogger(LocalizedContainer.class);	
	
	private Locale locale;
	private TemplateContainer templateContainer;
	private Template defaultTemplate;
	private Map<String, Template> templates;
	private Map<String, Screen> screens;
	
	public Template getTemplate(String template) {
		return templates.get(template);
	}

	public Template getTemplateOrDefault(String template) {
		if (template==null) {
			return defaultTemplate;
		}
		return getTemplate(template);
	}		
	
	public Screen getScreen(String name) {
		return screens.get(name);
	}	
	
	public LocalizedContainer(Locale locale, TemplateContainer templateContainer) {
		this.locale=locale;
		this.templateContainer=templateContainer;
		this.templates=new HashMap<String, Template>();
		this.screens=new HashMap<String, Screen>();
	}
	
	public void setDefaultTemplate(Template defaultTemplate) {
		if (this.defaultTemplate!=null) {
			if (logger.isInfoEnabled()) {
				logger.info(getMessage("template " + this.defaultTemplate + " will be replaced by " + defaultTemplate));
				this.defaultTemplate.setDefault(false);
				return;
			}			
		}
		this.defaultTemplate=defaultTemplate;
	}	
	
	public void addTemplate(Template template) {
		if (templates.containsKey(template.getName())) {
			if (logger.isWarnEnabled()) {
				logger.warn(getMessage("duplicate template " + template.getName() + " discarded"));
				return;
			}						
		}
		template.setPriority(templates.size());
		templates.put(template.getName(), template);
		if (template.isDefault()) {
			setDefaultTemplate(template);
		}
	}	
	
	public void addScreen(String template, Screen screen) {
		Template t = getTemplateOrDefault(template);
		if (t==null) {
			LocalizedContainer rootLocalizedContainer = templateContainer.getLocalizedContainer(Locale.ROOT);
			if (rootLocalizedContainer==null) {
				logger.warn(getMessage("*******************************screen "+ screen.getName() + " without template " + template + ", discarded"));			
				return;				
			} else {
				t=rootLocalizedContainer.getTemplateOrDefault(template);
				if (t==null) {
					logger.warn(getMessage("screen "+ screen.getName() + " without template " + template + ", discarded"));			
					return;									
				}
			}

		}
		screen.setTemplate(t);
		screens.put(screen.getName(), screen);
	}	
	
	private String getMessage(String message) {
		return locale+" "+message;
	}
	
	public Template resolveDefaultTemplate() {
		if (defaultTemplate==null) {
			for (String tname : templates.keySet()) {
				if (templates.get(tname).getPriority()==0) {
					defaultTemplate=templates.get(tname);
					logger.warn("template " + tname + " set to default");
					break;
				}
			}
		}		
		return defaultTemplate;
	}
	
}
