package it.seda.template.context;

import it.seda.template.container.TemplateContainer;
import it.seda.template.context.locale.LocaleResolver;
import it.seda.template.context.locale.URLResource;
import it.seda.template.startup.ResourceNotFoundException;
import it.seda.template.startup.ResourceUtils;
import it.seda.template.utils.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @author f.ricci
 *
 */
public class TemplateContext {

	private String name;	
	
	private WebApplicationContext wac;
	private ServletContext servletContext;
	private LocaleResolver localeResolver;
	private TemplateContainer container;
	
	private List<TemplateResource> resources;
	
	public String getWacName() {
		return name;
	}
	
	public WebApplicationContext getWebApplicationContext() {
		return wac;
	}	

	public void setContainer(TemplateContainer container) {
		this.container=container;
	}
	
	public TemplateContainer getContainer() {
		return container;
	}
	
	public MessageSource getMessageSource() {
		return wac;
	}	
	
	public String getInitParameter(String name) {
		return servletContext.getInitParameter(name);
	}
	
	public ServletContext getServletContext() {
		return servletContext;
	}
	
	public List<TemplateResource> getTemplateResources() {
		return resources;
	}

	public Locale getCurrentLocale() {
		return localeResolver.getLocale();
	}	
	
	public String getMessage(String code) {
		return getMessage(code, localeResolver.getLocale(), null);
	}
	
	public String getMessage(String code, Object[] args) {
		return getMessage(code, localeResolver.getLocale(), args);
//		return wac.getMessage(code, args, localeResolver.getLocale());
	}		
	
	public String getMessage(String code, Locale locale) {
		return getMessage(code, locale, null);		
	}		
	
	public String getMessage(String code, Locale locale, Object[] args) {
		return wac.getMessage(code, args, "*** missing localized message '"+ code +"' ***", locale==null?localeResolver.getLocale():locale);
	}	
	
	public TemplateContext (WebApplicationContext wac) {
		this.wac=wac;
		this.servletContext=this.wac.getServletContext();
		this.name=Utils.resolveWacName(this.wac.getDisplayName());
		this.localeResolver=new LocaleResolver();
	}
	
	public void loadDefinitions(String[] definitions) {
		if (definitions==null || definitions.length<1) {
			definitions=new String[]{"WEB-INF/template.xml"};
		}
		
		resources=ResourceUtils.stringArrayToResources(definitions, servletContext);
	}		
	
	@Override
	public String toString() {
		return "TemplateContext ["+wac.getDisplayName()+"]";
	}
}
