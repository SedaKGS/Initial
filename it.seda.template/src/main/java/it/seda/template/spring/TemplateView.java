package it.seda.template.spring;

import it.seda.template.container.ContainerAccess;
import it.seda.template.container.TemplateContainer;
import it.seda.template.container.menu.MenuItem;
import it.seda.template.context.ContextAccess;
import it.seda.template.context.TemplateContext;
import it.seda.template.renderer.DefaultRenderer;
import it.seda.template.renderer.Renderer;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.support.JstlUtils;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.util.WebUtils;

/**
 * 
 * @author f.ricci
 *
 */
public class TemplateView extends AbstractUrlBasedView {

	private Renderer renderer;

	protected boolean exposeForwardAttributes = false;

	protected boolean exposeJstlAttributes = true;

	protected TemplateContext applicationContext;

	/**
	 * Set the {@link Renderer} to use.
	 * If not set, by default {@link DefinitionRenderer} is used.
	 */
	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}

	/**
	 * Whether to expose JSTL attributes. By default set to {@code true}.
	 * @see JstlUtils#exposeLocalizationContext(RequestContext)
	 */
	protected void setExposeJstlAttributes(boolean exposeJstlAttributes) {
		this.exposeJstlAttributes = exposeJstlAttributes;
	}

	@Override
	protected void initServletContext(ServletContext servletContext) {
		super.initServletContext(servletContext);
		if (servletContext.getMajorVersion() == 2 && servletContext.getMinorVersion() < 5) {
			this.exposeForwardAttributes = true;
		}
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();

		this.applicationContext = ContextAccess.retrieve(getWebApplicationContext());

		if (this.renderer == null) {
			TemplateContainer container = ContainerAccess.retrieve(getWebApplicationContext());
			this.renderer = new DefaultRenderer(container);
		}
	}	

	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ServletContext servletContext = getServletContext();
		TemplateContainer container = ContainerAccess.retrieve(getWebApplicationContext());
		if (container == null) {
			throw new ServletException("Template container is not initialized. " +
					"Have you added a TemplateViewResolver to your web application context?");
		}

		List<MenuItem> menu = container.getMenuHandler().resolve(request);
		model.put("menu_items", menu);
		
		exposeModelAsRequestAttributes(model, request);
		JstlUtils.exposeLocalizationContext(new RequestContext(request, servletContext));

		if (!response.isCommitted()) {
			// Template is going to use a forward, but some web containers (e.g. OC4J 10.1.3)
			// do not properly expose the Servlet 2.4 forward request attributes... However,
			// must not do this on Servlet 2.5 or above, mainly for GlassFish compatibility.
			if (this.exposeForwardAttributes) {
				try {
					WebUtils.exposeForwardRequestAttributes(request);
				}
				catch (Exception ex) {
					// Servlet container rejected to set internal attributes, e.g. on TriFork.
					this.exposeForwardAttributes = false;
				}
			}
		}

		container.render(getUrl(), request, response);
	}

}
