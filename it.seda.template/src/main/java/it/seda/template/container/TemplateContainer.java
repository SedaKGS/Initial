/**
 * 
 */
package it.seda.template.container;

import it.seda.template.container.locale.LocalizedContainer;
import it.seda.template.context.TemplateContext;
import it.seda.template.request.ParameterContext;
import it.seda.template.utils.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author f.ricci
 *
 */
public class TemplateContainer {

	private Logger logger = LoggerFactory.getLogger(TemplateContainer.class);
	
	private TemplateContext context;
	
	private Map<Locale, LocalizedContainer> localizedContainers;
	
	public Map<Locale, LocalizedContainer> getLocalizedContainers() {
		if (localizedContainers==null) {
			localizedContainers=new HashMap<Locale, LocalizedContainer>();
		}
		return localizedContainers;
	}
	
	public LocalizedContainer getLocalizedContainer(Locale locale) {
		return getContainer(locale, false);
	}
	
	public LocalizedContainer getContainer(Locale locale, boolean create) {
		LocalizedContainer containerLocaleResolver=null;
		if (getLocalizedContainers().containsKey(locale)) {
			containerLocaleResolver=getLocalizedContainers().get(locale);
		} else if (create) { 
			containerLocaleResolver=new LocalizedContainer(locale,this);
			getLocalizedContainers().put(locale, containerLocaleResolver);
		}
		return containerLocaleResolver;
	}	
	
	public void addTemplate(Template template) {
		for (Locale locale : template.getLocales()) {
			getContainer(locale, true).addTemplate(template);
		}
	}
	
	public void resolveDefaultTemplates() {
		for (Locale locale : localizedContainers.keySet()) {
			LocalizedContainer containerLocaleResolver = localizedContainers.get(locale);
			containerLocaleResolver.resolveDefaultTemplate();
		}
	}		
	
	public void addScreen(String template, Screen screen) {
		for (Locale locale : screen.getLocales()) {
			LocalizedContainer container = getLocalizedContainer(locale);
			if (container==null) {
				logger.warn("localized screen " + locale + "." + screen.getName() + " wihout container, discarded");				
			} else {
				container.addScreen(template, screen);
			}
		}		
	}
	
	public TemplateContext getTemplateContext() {
		return context;
	}
	
	public TemplateContainer(TemplateContext context) {
		this.context=context;
	}

	public void render(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Locale currentLocale = context.getCurrentLocale();
		LocalizedContainer localizedContainer = getLocalizedContainer(currentLocale);
		if (localizedContainer==null) {
			logger.debug(url + " rendering locale " + currentLocale + " not found");			
			localizedContainer=getLocalizedContainer(Locale.ROOT);
			if (localizedContainer==null) {
				throw new ServletException("default localized contaner not found? Did you miss some configuration (template.xml)?");
			}
		}
		
		Screen screen = localizedContainer.getScreen(url);
		if (screen==null) {
			throw new ServletException("screen definition "+url+" not found");
		}
		
		Template template = screen.getTemplate();
		if (template==null) {
			throw new ServletException("screen definition "+url+" template is missing");
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("rendering screen " + screen.getName() + " to " + template.getUrl());
		}
		
		// prefase, caricamento degli atributi ereditati
		ParameterContext parameterContext = new ParameterContext(context);
		parameterContext.addAll(screen.getParameters());
		ParameterContext.register(request, parameterContext);
		
		// fase di render vero e proprio
		forward(template.getUrl(), request, response);
	}
	
	
	/**
     * Forwards to a path.
     *
     * @param path The path to forward to.
     * @throws IOException If something goes wrong during the operation.
     */
    private void forward(String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestDispatcher rd = request.getRequestDispatcher(path);

        if (rd == null) {
            throw new IOException("No request dispatcher returned for path '"
                    + path + "'");
        }

        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            throw Utils.wrapServletException(ex, "ServletException including path '"
                    + path + "'.");
        }
    }

    
	@Override
	public String toString() {
		return "TemplateContainer [context=" + context.getWacName() + ", containers=#"
				+ localizedContainers.size() + "]";
	}    
}
