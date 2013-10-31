package it.seda.template.request;

import it.seda.template.context.TemplateContext;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author f.ricci
 *
 */
public interface Request {

	
	TemplateContext getTemplateContext();

	Map<String, Object> getContext(String scope);

	List<String> getAvailableScopes();
	
}
