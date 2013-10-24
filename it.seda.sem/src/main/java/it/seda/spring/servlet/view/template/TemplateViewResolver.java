/**
 * 
 */
package it.seda.spring.servlet.view.template;

import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * @author f.ricci
 *
 */
public class TemplateViewResolver extends UrlBasedViewResolver {
	
	public TemplateViewResolver() {
		setViewClass(requiredViewClass());
	}

	/**
	 * Requires {@link TilesView}.
	 */
	@Override
	protected Class requiredViewClass() {
		return TemplateView.class;
	}
	
}
