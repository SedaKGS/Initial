/**
 * 
 */
package it.seda.template.spring;

import it.seda.template.container.menu.MenuConfigurer;
import it.seda.template.container.menu.MenuHandler;
import it.seda.template.container.menu.DefaultMenuConfigurer;
import it.seda.template.context.TemplateContext;
import it.seda.template.renderer.DefaultRenderer;
import it.seda.template.renderer.Renderer;
import it.seda.template.startup.TemplateInitializer;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * @author f.ricci
 *
 */
public class TemplateViewResolver extends UrlBasedViewResolver implements InitializingBean, DisposableBean {
	
	private Renderer renderer;
	private MenuHandler menuHandler;
	private MenuConfigurer menuConfigurer;
	
	public TemplateViewResolver() {}

	/**
	 * Requires {@link TemplateView}.
	 */
	@Override
	@SuppressWarnings("rawtypes")	
	protected Class getViewClass() {
		return TemplateView.class;
	}

	/**
	 * Set the {@link Renderer} to use. If not set, by default
	 * {@link it.seda.template.renderer.DefaultRenderer} is used.
	 * @see TemplateView#setRenderer(Renderer)
	 */
	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}	
	
	/**
	 * Set the {@link MenuConfigurer} to use. If not set, by default
	 * {@link it.seda.template.container.menu.DefaultMenuConfigurer} is used.
	 * @see TemplateView#setMenuHandler(MenuHandler)
	 */	
	public void setMenuConfigurer(MenuConfigurer menuConfigurer) {
		this.menuConfigurer = menuConfigurer;
	}
	
	/*1*/
	/**
	 * Set the Template definitions, i.e. the list of files containing the definitions.
	 * Default is "/WEB-INF/template.xml".
	 */	
	private String[] definitions;
	private TemplateInitializer initializer;
	public void setDefinitions(String[] definitions) {
		this.definitions = definitions;
		if (definitions != null) {
			String defs = StringUtils.arrayToCommaDelimitedString(definitions);
			if (logger.isInfoEnabled()) {
				logger.info("TemplateViewResolver: adding definitions [" + defs + "]");
			}
		}
	}
	/*1*/

	@Override
	public void destroy() throws Exception {
		initializer.destroy();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		TemplateContext templateContext = new TemplateContext((WebApplicationContext) getApplicationContext());
		templateContext.loadDefinitions(definitions);
		if (initializer==null) {
			initializer=new TemplateInitializer();
		}
		initializer.initialize(templateContext);
		if (renderer==null) {
			renderer=new DefaultRenderer(templateContext.getContainer());
		}
		else{
			renderer.setContainer(templateContext.getContainer());
		}
		
		if (menuConfigurer==null) {
			menuConfigurer=new DefaultMenuConfigurer();
		}
		
		if (menuHandler==null) {
			menuHandler = menuConfigurer.configure(templateContext.getServletContext());	
		}
		
		templateContext.getContainer().setMenuHandler(menuHandler);
	}

	/* view builder */
	@Override
	protected TemplateView buildView(String viewName) throws Exception {
		TemplateView view = (TemplateView) super.buildView(viewName);
		view.setRenderer(this.renderer);
		return view;
	}	
	
}
