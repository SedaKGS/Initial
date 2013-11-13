/**
 * 
 */
package it.seda.template.container;

import it.seda.template.container.locale.LocalizedTemplateContainer;
import it.seda.template.context.TemplateContext;
import it.seda.template.renderer.Renderer;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author f.ricci
 *
 */
public class TemplateContainer {

	private TemplateContext context;
	private Renderer renderer;
	private LocalizedTemplateContainer localizedTemplateContainer;
	private ScreenContainer screenContainer;	

	public void addTemplate(Template template) {
		localizedTemplateContainer.addTemplate(template);
	}
	
	public void resolveDefaultTemplates() {
		localizedTemplateContainer.resolveDefaultTemplates();
	}		
	
	public void addScreen(Screen screen) {
		screenContainer.addScreen(screen);
	}
	
	public TemplateContext getTemplateContext() {
		return context;
	}
	
	public void setRenderer(Renderer renderer){
		this.renderer=renderer;
	}
	
	public TemplateContainer(TemplateContext context) {
		this.context=context;
		this.localizedTemplateContainer=new LocalizedTemplateContainer();
		this.screenContainer=new ScreenContainer();
		
	}

	public void render(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		renderer.render(url, request, response);
		
	}
    
	public boolean containsScreen(String url) {
		return screenContainer.contains(url);
	}

	public Screen getScreen(String url) {
		return screenContainer.getScreen(url);
	}

	public Template getLocalizedTemplate(Locale locale, String theme, String tname) {
		return localizedTemplateContainer.resolve(locale, theme, tname);
	}

	@Override
	public String toString() {
		return "TemplateContainer [context=" + context.getWacName() + ", renderer="
				+ renderer + ", localizedTemplateContainer="
				+ localizedTemplateContainer + ", screenContainer="
				+ screenContainer + "]";
	}
	

}
