/**
 * 
 */
package it.seda.template.startup;

import it.seda.template.container.ContainerAccess;
import it.seda.template.container.TemplateContainer;
import it.seda.template.context.ContextAccess;
import it.seda.template.context.TemplateContext;

/**
 * @author f.ricci
 *
 */
public class TemplateInitializer {

	private TemplateContext context;
	
	public void initialize(TemplateContext context) {
		this.context=context;
		ContextAccess.register(context);

		XMLContainerParser containerParser = new XMLContainerParser(context);
		TemplateContainer container = containerParser.parse();
		context.setContainer(container);
		ContainerAccess.register(context, container);
	}
	
	public void destroy() {
		ContextAccess.destroy(context);
		ContainerAccess.destroy(context);
	}
	
	
}
