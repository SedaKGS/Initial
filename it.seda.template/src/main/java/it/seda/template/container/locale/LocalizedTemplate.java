/**
 * 
 */
package it.seda.template.container.locale;

import it.seda.template.container.Template;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author f.ricci
 *
 */
public class LocalizedTemplate {

	private Logger logger = LoggerFactory.getLogger(LocalizedTemplate.class);	
	
	private Locale locale;
	private Map<String,Template> templates;
	private Template defaultTemplate;
	private LocalizedTemplateContainer container;
	public Locale getLocale() {
		return locale;
	}
	
	public boolean isLocaleRoot() {
		return Locale.ROOT.equals(locale);
	}
	
	public boolean contains(String tname) {
		if (tname==null) {
			return defaultTemplate!=null;
		}
		return templates.containsKey(tname);
	}
	
	public Template getTemplate(String tname) {
		if (tname==null) {
			return defaultTemplate;
		}
		return templates.get(tname);
	}		
	public LocalizedTemplate(Locale locale, LocalizedTemplateContainer container) {
		this.locale=locale;
		this.container=container;
		this.templates=new HashMap<String, Template>();
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
