/**
 * 
 */
package it.seda.template.container.locale;

import it.seda.template.container.Template;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author f.ricci
 *
 */
public class LocalizedTemplate {

	private Logger logger = LoggerFactory.getLogger(LocalizedTemplate.class);	
	
	private Locale locale;
	private Map<String, Map<String, Template>> templates;
	private Map<String, Template> defaultTemplates;
	private LocalizedTemplateContainer container;
	public Locale getLocale() {
		return locale;
	}
	
	public boolean isLocaleRoot() {
		return Locale.ROOT.equals(locale);
	}
	
	public boolean contains(String theme, String tname) {
		if (tname==null) {
			return defaultTemplates.get(theme)!=null;
		}
		return getThemedTemplates(theme).containsKey(tname);
	}
	
	public Template getTemplate(String theme, String tname) {
		if (tname==null) {
			return defaultTemplates.get(theme);
		}
		return getThemedTemplates(theme).get(tname);		
	}		
	public LocalizedTemplate(Locale locale, LocalizedTemplateContainer container) {
		this.locale=locale;
		this.container=container;
		this.templates=new HashMap<String, Map<String, Template>>();
		this.defaultTemplates=new HashMap<String, Template>();
	}
	
	public void setDefaultTemplate(Template defaultTemplate) {
		for (String theme : defaultTemplate.getThemes()) {
			setDefaultTemplate(theme, defaultTemplate);
		}
	}
	
	public void setDefaultTemplate(String theme, Template defaultTemplate) {
		if (defaultTemplates.get(theme)!=null) {
			if (logger.isInfoEnabled()) {
				logger.info(getMessage("theme '" + theme + "' template " + defaultTemplates.get(theme) + " will be replaced by " + defaultTemplate));
				defaultTemplates.get(theme).setDefault(false);
			}							
		}
		defaultTemplates.put(theme,defaultTemplate);
	}		
	
	public void addTemplate(Template template) {
		for (String theme : template.getThemes()) {
			Map<String, Template> themedTemplates=getThemedTemplates(theme);
			if (themedTemplates.containsKey(template.getName())) {
				if (logger.isWarnEnabled()) {
					logger.warn(getMessage("theme '" + theme + "' duplicate template " + template.getName() + " discarded"));
					return;
				}
			}
			template.setPriority(themedTemplates.size());
			themedTemplates.put(template.getName(), template);
			if (template.isDefault()) {
				setDefaultTemplate(theme, template);
			}
		}
	}		

	protected Map<String, Template> getThemedTemplates(String theme) {
		Map<String, Template> themedTemplates = templates.get(theme);
		if (themedTemplates==null) {
			themedTemplates = new TreeMap<String, Template>();
			templates.put(theme, themedTemplates);
		}
		return themedTemplates;
	}
	
	private String getMessage(String message) {
		return locale+" "+message;
	}
	
	public void resolveDefaultTemplate() {
		for(String theme: templates.keySet()) {
			if (!defaultTemplates.containsKey(theme)) {
				Map<String, Template> themedTemplates=getThemedTemplates(theme);				
				for (String tname : themedTemplates.keySet()) {
					if (themedTemplates.get(tname).getPriority()==0) {
						defaultTemplates.put(theme, themedTemplates.get(tname));
						logger.warn("theme '" + theme + "'template " + tname + " set to default");
						break;
					}
				}
			}
		}
	}
	
}
