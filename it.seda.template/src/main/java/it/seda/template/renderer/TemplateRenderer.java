package it.seda.template.renderer;

import it.seda.template.container.Screen;
import it.seda.template.container.Template;
import it.seda.template.container.TemplateContainer;
import it.seda.template.container.locale.LocalizedContainer;
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

public class TemplateRenderer implements Renderer {

	private Logger logger = LoggerFactory.getLogger(TemplateRenderer.class);	
	
	private TemplateContainer container;
	
	public void setContainer(TemplateContainer container) {
		this.container=container;
	}
	
	public TemplateRenderer() {}
	
	public TemplateRenderer(TemplateContainer container) {
		this.container=container;
	}

	public void render(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Locale currentLocale = container.getTemplateContext().getCurrentLocale();
		LocalizedContainer localizedContainer = container.getLocalizedContainer(currentLocale);
		if (localizedContainer==null) {
			logger.debug(url + " rendering locale " + currentLocale + " not found");			
			localizedContainer=container.getLocalizedContainer(Locale.ROOT);
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
			throw new ServletException("screen definition "+url+" missing of template");
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("rendering screen " + screen.getName() + " to " + template.getUrl());
		}
		
		// prefase, caricamento degli atributi ereditati
		ParameterContext parameterContext = new ParameterContext(container.getTemplateContext());
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

}
