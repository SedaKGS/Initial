package it.seda.template.renderer;

import it.seda.template.container.Screen;
import it.seda.template.container.Template;
import it.seda.template.container.TemplateContainer;
import it.seda.template.container.menu.MenuHandler;
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
import org.springframework.ui.context.Theme;
import org.springframework.web.servlet.support.RequestContextUtils;

public class DefaultRenderer implements Renderer {

	private Logger logger = LoggerFactory.getLogger(DefaultRenderer.class);	
	
	private TemplateContainer container;
	private MenuHandler menuHandler;
	
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
		Theme currentTheme=RequestContextUtils.getTheme(request);
		String themeName=currentTheme.getName();
		
		if (!container.containsScreen(url)) {
			logger.debug("screen " + url + " rendering locale " + currentLocale + " not found");			
			throw new ServletException("screen definition "+url+" not found");
		}		
		Screen screen = container.getScreen(url);
		
		Template template = container.getLocalizedTemplate(currentLocale, currentTheme.getName(), screen.getTemplate());
		if (template==null) {
			logger.debug(screen.getTemplate() +  " rendering locale " + currentLocale + " and theme '" + currentTheme + "' not found");			
			throw new ServletException("locale '" +currentLocale+ "' theme '" +currentTheme+ "' template " + (screen.getTemplate()==null?"*default*":screen.getTemplate()) + " definition not found for "+screen+". Did you miss some configuration (template.xml)?");
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("locale '" +currentLocale+ "', theme '" +currentTheme+ "' rendering screen '" + screen.getName() + "' to " + template.getUrl());
		}
		
		// prefase, caricamento degli atributi ereditati
		ParameterContext parameterContext = new ParameterContext(container.getTemplateContext());
		/*
		 parameterContext.addAll(screen.getParameters());
		 */
		parameterContext.addAll(screen.getParameters(currentLocale,themeName));
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
