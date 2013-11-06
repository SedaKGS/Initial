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
public class LocalizedTemplateContainer {
	private Logger logger = LoggerFactory.getLogger(LocalizedTemplateContainer.class);	
	
	private Map<Locale,LocalizedTemplate> localizedTemplates;
	
	public Map<Locale, LocalizedTemplate> getLocalizedTemplates() {
		if (localizedTemplates==null) {
			localizedTemplates=new HashMap<Locale, LocalizedTemplate>();
		}
		return localizedTemplates;
	}	
	
	public void addTemplate(Template template) {
		for (Locale locale : template.getLocales()) {
			getContainer(locale, true).addTemplate(template);
		}
	}
	
	public void resolveDefaultTemplates() {
		for (Locale locale : localizedTemplates.keySet()) {
			LocalizedTemplate localizedTemplate = localizedTemplates.get(locale);
			localizedTemplate.resolveDefaultTemplate();
		}
	}		
	
	public LocalizedTemplate getContainer(Locale locale) {
		return getContainer(locale, false);
	}
	
	public LocalizedTemplate getContainer(Locale locale, boolean create) {
		LocalizedTemplate localizedTemplate=null;
		if (getLocalizedTemplates().containsKey(locale)) {
			localizedTemplate=getLocalizedTemplates().get(locale);
		} else if (create) { 
			localizedTemplate=new LocalizedTemplate(locale,this);
			getLocalizedTemplates().put(locale, localizedTemplate);
		}
		return localizedTemplate;
	}		
	
	public LocalizedTemplateContainer() {
		localizedTemplates=new HashMap<Locale, LocalizedTemplate>();
	}

	
	public LocalizedTemplate getRootLocalizedTemplate() {
		return getContainer(Locale.ROOT,true);
	}
	
	public Template resolve(Locale locale, String tname) {
		LocalizedTemplate locTemplate=getContainer(locale);
		if (locTemplate==null) {
			locTemplate=getRootLocalizedTemplate();
		}
		return resolve(null, locTemplate, tname);
	}
	
	private Template resolve(LocalizedTemplate parent, LocalizedTemplate locTemplate, String tname) {
		Template template=null;
		if (locTemplate.contains(tname)) {
			template=locTemplate.getTemplate(tname);
			logger.debug(resolveParentLocale(parent)+locTemplate.getLocale() + "." + tname + " resolved with " + template);
		} else if (!locTemplate.isLocaleRoot()) {
			logger.trace(locTemplate.getLocale() + "." + tname + " trying to resolve in root localized container");
			return resolve(locTemplate, getRootLocalizedTemplate(), tname);
		} else {
			logger.warn(resolveParentLocale(parent)+locTemplate.getLocale() + "." + tname + " not resolved");
		}
		
		return template;
	}
	
	private String resolveParentLocale(LocalizedTemplate parent) {
		return (parent==null?"":"[ " + parent.getLocale()+"] ");
	}
}
