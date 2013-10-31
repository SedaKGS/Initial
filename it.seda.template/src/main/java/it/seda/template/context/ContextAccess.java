/**
 * 
 */
package it.seda.template.context;

import it.seda.template.utils.Utils;

import org.springframework.web.context.WebApplicationContext;

/**
 * @author f.ricci
 *
 */
public class ContextAccess {

	public static final String ATTR_CONTEXT_TEMPLATE = TemplateContext.class.getName()+".CONTEXT";
	
	private ContextAccess(){}
	
	public static void register(TemplateContext templateContext) {
		templateContext.getServletContext().setAttribute(getContextId(templateContext.getWacName()), templateContext);
	}
	
	public static void destroy(TemplateContext templateContext) {
		templateContext.getServletContext().setAttribute(getContextId(templateContext.getWacName()), null);
	}	
	
	public static TemplateContext retrieve(WebApplicationContext wac) {
		return (TemplateContext)wac.getServletContext().getAttribute(getContextId(Utils.resolveWacName(wac)));
	}
	
	public static String getContextId(String id) {
		return ATTR_CONTEXT_TEMPLATE+"."+id;
	}
	
}
