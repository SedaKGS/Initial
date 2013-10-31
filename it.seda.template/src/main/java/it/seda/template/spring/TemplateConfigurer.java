/**
 * 
 */
package it.seda.template.spring;

import it.seda.template.context.TemplateContext;
import it.seda.template.startup.TemplateInitializer;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author f.ricci
 *
 */
public class TemplateConfigurer implements ApplicationContextAware, InitializingBean, DisposableBean {

	private Logger logger = LoggerFactory.getLogger(TemplateConfigurer.class);	

	private String[] definitions;

	private ServletContext servletContext;
	private WebApplicationContext webApplicationContext;
	
	private TemplateInitializer initializer;
	/**
	 * Set the Template definitions, i.e. the list of files containing the definitions.
	 * Default is "/WEB-INF/template.xml".
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
	
	@Override
	public void afterPropertiesSet() throws Exception {
		TemplateContext applicationContext = new TemplateContext(webApplicationContext);
		applicationContext.loadDefinitions(definitions);
		if (initializer==null) {
			initializer=new TemplateInitializer();
		}
		initializer.initialize(applicationContext);
	}

	@Override
	public void destroy() throws Exception {
		initializer.destroy();		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (applicationContext instanceof WebApplicationContext) {
			webApplicationContext=(WebApplicationContext) applicationContext;
			servletContext=webApplicationContext.getServletContext();
			return;
		}  
		throw new IllegalApplicationContextException(applicationContext.getClass().toString());
	}
	
	
	@SuppressWarnings("serial")
	public class IllegalApplicationContextException extends BeansException {
		public IllegalApplicationContextException(String msg) {
			super(msg);
		}
	}

}
