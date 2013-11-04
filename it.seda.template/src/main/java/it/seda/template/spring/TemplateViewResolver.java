/**
 * 
 */
package it.seda.template.spring;

import it.seda.template.context.TemplateContext;
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
	 * @see TilesView#setRenderer(Renderer)
	 */
	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}	
	
	@Override
	protected TemplateView buildView(String viewName) throws Exception {
		TemplateView view = (TemplateView) super.buildView(viewName);
		view.setRenderer(this.renderer);
		return view;
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
				logger.info("TemplateConfigurer: adding definitions [" + defs + "]");
			}
		}
		
		
	}
	/*1*/

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		initializer.destroy();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		TemplateContext applicationContext = new TemplateContext((WebApplicationContext) getApplicationContext());
		applicationContext.loadDefinitions(definitions);
		if (initializer==null) {
			initializer=new TemplateInitializer();
		}
		initializer.initialize(applicationContext);
	}
	
}
