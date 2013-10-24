/**
 * 
 */
package it.seda.spring.servlet.view.template;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author f.ricci
 *
 */
public class TemplateConfigurer implements ServletContextAware, InitializingBean, DisposableBean {

	private Logger logger = LoggerFactory.getLogger(TemplateConfigurer.class);	

	private String[] definitions;
	private String context;

	private ServletContext servletContext;
	/**
	 * Set the Tiles definitions, i.e. the list of files containing the definitions.
	 * Default is "/WEB-INF/tiles.xml".
	 */
	public void setDefinitions(String[] definitions) {
		this.definitions = definitions;
		if (definitions != null) {
			String defs = StringUtils.arrayToCommaDelimitedString(definitions);
			if (logger.isInfoEnabled()) {
				logger.info("TemplateConfigurer: adding definitions [" + defs + "]");
			}
		}
	}
	
	public void setContext(String context) {
		this.context = context;
		if (context != null) {
			if (logger.isInfoEnabled()) {
				logger.info("TemplateConfigurer: adding context [" + context + "]");
			}
		}
	}		

	@Override
	public void afterPropertiesSet() throws Exception {
		if (context==null) {
			throw new IllegalArgumentException("TemplateConfigurer: context null");
		}
		logger.info("TemplateConfigurer: afterPropertiesSet");
		WebApplicationContext a = WebApplicationContextUtils.getWebApplicationContext(servletContext,"events");
		logger.info(a==null?"non trovato":a.getId());
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext=servletContext;
	}


	@Override
	public void destroy() throws Exception {
		logger.info("TemplateConfigurer: afterPropertiesSet");		
	}

}
