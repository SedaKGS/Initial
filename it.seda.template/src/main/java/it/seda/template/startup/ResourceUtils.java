package it.seda.template.startup;

import it.seda.template.context.TemplateResource;
import it.seda.template.context.locale.URLResource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;

/**
 * 
 * @author f.ricci
 *
 */
public class ResourceUtils {

	private ResourceUtils(){}
	
	public final static List<TemplateResource> stringArrayToResources(String[] definitions, ServletContext servletContext) {
		List<TemplateResource> resources = new ArrayList<TemplateResource>(definitions.length);
		for (int i = 0; i < definitions.length; i++) {
			addResource(resources, definitions[i], servletContext);
		}
		return resources;
	}
	
	
	protected final static void addResource(List<TemplateResource> resources, String definition, ServletContext servletContext) {
		TemplateResource templateResource = getResource(definition, servletContext);
		if (templateResource!=null) {
			resources.add(templateResource);	
		} else {
			throw new ResourceNotFoundException(definition);
		}
	}
	
    public final static TemplateResource getResource(String localePath, ServletContext servletContext) {
        try {
            URL url = servletContext.getResource(localePath);
            if (url != null) {
                return new URLResource(localePath, url);
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            return null;
        }
    }	
    
    public final static TemplateResource getResource(TemplateResource base, Locale locale, ServletContext servletContext) {
        try {
            URL url = servletContext.getResource(base.getLocalePath(locale));
            if (url != null) {
                return new URLResource(base.getPath(), locale, url);
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            return null;
        }
    }    
}
