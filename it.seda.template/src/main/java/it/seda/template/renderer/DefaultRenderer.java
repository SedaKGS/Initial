package it.seda.template.renderer;

import it.seda.template.container.Screen;
import it.seda.template.container.Template;
import it.seda.template.container.TemplateContainer;
import it.seda.template.request.ParameterContext;
import it.seda.template.utils.Utils;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultRenderer implements Renderer {

	private Logger logger = LoggerFactory.getLogger(DefaultRenderer.class);	
	
	private TemplateContainer container;
	
	public void setContainer(TemplateContainer container) {
		this.container=container;
	}
	
	public DefaultRenderer() {}
	
	public DefaultRenderer(TemplateContainer container) {
		this.container=container;
		this.container.setRenderer(this);
	}
	

	public void render(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Locale currentLocale = container.getTemplateContext().getCurrentLocale();
		
		if (!container.containsScreen(url)) {
			logger.debug("screen " + url + " rendering locale " + currentLocale + " not found");			
			throw new ServletException("screen definition "+url+" not found");
		}		
		Screen screen = container.getScreen(url);
		
		Template template = container.getLocalizedTemplate(currentLocale, screen.getTemplate());
		if (template==null) {
			logger.debug(screen.getTemplate() +  " rendering locale " + currentLocale + " not found");			
			throw new ServletException("template " + (screen.getTemplate()==null?"*default*":screen.getTemplate()) + " definition not found for "+screen+ ". Did you miss some configuration (template.xml)?");
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("locale '" +currentLocale+ "' rendering screen " + screen.getName() + " to " + template.getUrl());
		}
		
		// prefase, caricamento degli atributi ereditati
		ParameterContext parameterContext = new ParameterContext(container.getTemplateContext());
		/*
		 parameterContext.addAll(screen.getParameters());
		 */
		parameterContext.addAll(screen.getParameters(currentLocale));
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

}
