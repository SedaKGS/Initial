/**
 * 
 */
package it.seda.template.spring;

import it.seda.template.renderer.Renderer;

import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * @author f.ricci
 *
 */
public class TemplateViewResolver extends UrlBasedViewResolver {
	
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
}
