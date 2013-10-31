/**
 * 
 */
package it.seda.template.container;

import it.seda.template.context.TemplateContext;
import it.seda.template.utils.Utils;

import org.springframework.web.context.WebApplicationContext;

/**
 * @author f.ricci
 *
 */
public class ContainerAccess {

	public static final String ATTR_CONTEXT_CONTAINER = TemplateContainer.class.getName()+".CONTAINER";
	
	private ContainerAccess(){}
	
	public static void register(TemplateContext templateContext, TemplateContainer container) {
		templateContext.getServletContext().setAttribute(getContextId(templateContext.getWacName()), container);
	}
	
	public static void destroy(TemplateContext templateContext) {
		templateContext.getServletContext().setAttribute(getContextId(templateContext.getWacName()), null);
	}	
	
	public static TemplateContainer retrieve(WebApplicationContext wac) {
		return (TemplateContainer)wac.getServletContext().getAttribute(getContextId(Utils.resolveWacName(wac)));
	}
	
	public static String getContextId(String id) {
		return ATTR_CONTEXT_CONTAINER+"."+id;
	}
	
}
