/**
 * 
 */
package it.seda.template.container.menu;

import java.util.List;

import javax.servlet.ServletContext;

import it.seda.template.context.TemplateResource;
import it.seda.template.startup.ResourceUtils;
import it.seda.template.startup.XMLMenuHandlerParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

/**
 * @author f.ricci
 *
 */
public class DefaultMenuConfigurer implements MenuConfigurer, InitializingBean, DisposableBean {

	private Logger logger = LoggerFactory.getLogger(DefaultMenuConfigurer.class);	
	
	private String[] definitions;
	private XMLMenuHandlerParser initializer;
	public void setDefinitions(String[] definitions) {
		this.definitions = definitions;
		if (definitions != null) {
			String defs = StringUtils.arrayToCommaDelimitedString(definitions);
			if (logger.isInfoEnabled()) {
				logger.info("XmlMenuConfigurer: adding definitions [" + defs + "]");
			}
		}
	}		
	
	@Override
	public void afterPropertiesSet() throws Exception {
		initializer=new XMLMenuHandlerParser();
	}	
	
	@Override
	public MenuHandler configure(ServletContext servletContext) {
		List<TemplateResource> resources = ResourceUtils.stringArrayToResources(definitions, servletContext);
		return initializer.parse(resources);
	}
	
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}


}
