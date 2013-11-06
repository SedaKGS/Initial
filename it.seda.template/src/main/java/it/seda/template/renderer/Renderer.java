package it.seda.template.renderer;

import it.seda.template.container.TemplateContainer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author f.ricci
 *
 */
public interface Renderer {

	
	void render(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	void setContainer(TemplateContainer container);
	
}
