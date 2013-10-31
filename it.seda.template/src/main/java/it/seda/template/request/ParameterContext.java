package it.seda.template.request;

import it.seda.template.container.Parameter;
import it.seda.template.context.TemplateContext;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;

/**
 * 
 * @author f.ricci
 *
 */
public class ParameterContext {

	public static final String ATTR_REQUEST_PARAMETER = ParameterContext.class.getName()+".CONTEXT";
	
	private TemplateContext templateContext;
	
	private Map<String, Parameter> parameters;
	
	public Map<String, Parameter> getParameters() {
		return ensureParameters();
	}
	
	public void addAll(Map<String, Parameter> parameters) {
		ensureParameters().putAll(parameters);
	}
	
	private Map<String, Parameter> ensureParameters() {
		if (parameters==null) {
			parameters=new HashMap<String, Parameter>();
		}
		return parameters;
	}

	public String getMessage(String code) {
		return templateContext.getMessage(code);
	}
	
	public String getMessage(String code, Object[] args) {
		return templateContext.getMessage(code, args);
	}	
	
	public ParameterContext(TemplateContext context) {
		this.templateContext=context;
	}
	
	public static void register(HttpServletRequest request, ParameterContext context) {
		request.setAttribute(ATTR_REQUEST_PARAMETER, context);
	}
	
	public static ParameterContext retrieve(HttpServletRequest request) {
		return (ParameterContext) request.getAttribute(ATTR_REQUEST_PARAMETER);
	}	
	
}
