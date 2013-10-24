package it.seda.spring.servlet.view.template;

import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
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

	private volatile boolean exposeForwardAttributes = false;


	/**
	 * Checks whether we need to explicitly expose the Servlet 2.4 request attributes
	 * by default.
	 * <p>This will be done by default on Servlet containers up until 2.4, and skipped
	 * for Servlet 2.5 and above. Note that Servlet containers at 2.4 level and above
	 * should expose those attributes automatically! This feature exists for
	 * Servlet 2.3 containers and misbehaving 2.4 containers only.
	 */
	@Override
	protected void initServletContext(ServletContext sc) {
		if (sc.getMajorVersion() == 2 && sc.getMinorVersion() < 5) {
			this.exposeForwardAttributes = true;
		}
	}


	@Override
	public boolean checkResource(final Locale locale) throws Exception {
//		TilesContainer container = ServletUtil.getContainer(getServletContext());
//		if (!(container instanceof BasicTilesContainer)) {
//			// Cannot check properly - let's assume it's there.
//			return true;
//		}
//		BasicTilesContainer basicContainer = (BasicTilesContainer) container;
//		TilesApplicationContext appContext = new ServletTilesApplicationContext(getServletContext());
//		TilesRequestContext requestContext = new ServletTilesRequestContext(appContext, null, null) {
//			@Override
//			public Locale getRequestLocale() {
//				return locale;
//			}
//		};
//		return (basicContainer.getDefinitionsFactory().getDefinition(getUrl(), requestContext) != null);
		return true;
	}

	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ServletContext servletContext = getServletContext();
//		TilesContainer container = ServletUtil.getContainer(servletContext);
//		if (container == null) {
//			throw new ServletException("Tiles container is not initialized. " +
//					"Have you added a TilesConfigurer to your web application context?");
//		}

		exposeModelAsRequestAttributes(model, request);
		JstlUtils.exposeLocalizationContext(new RequestContext(request, servletContext));

		if (!response.isCommitted()) {
			// Tiles is going to use a forward, but some web containers (e.g. OC4J 10.1.3)
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

//		container.render(getUrl(), request, response);
	}

	
}
