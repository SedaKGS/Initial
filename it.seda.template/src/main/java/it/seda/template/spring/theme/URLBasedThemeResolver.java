package it.seda.template.spring.theme;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.theme.AbstractThemeResolver;

public class URLBasedThemeResolver extends AbstractThemeResolver implements InitializingBean {

	private Logger logger = LoggerFactory.getLogger(URLBasedThemeResolver.class);	
	
	private Map<String, String> definitions;
	final String MESSAGE = "verify '%s' against '%s'"; 

	public void setDefinitions(Map<String, String> definitions) {
		this.definitions=definitions;
		if (definitions != null) {
			if (logger.isInfoEnabled()) {
				logger.info("adding definitions -> " + definitions);
			}
		}		
	}

	@Override
	public String resolveThemeName(HttpServletRequest request) {
		final String currentURL = request.getRequestURL().toString();

		for (String match : definitions.keySet()) {
			if (currentURL.startsWith(match)) {
				final String themeName = definitions.get(match);
				if (logger.isDebugEnabled()) {
					logger.debug(String.format(MESSAGE, match, currentURL) + " matches '"+themeName+"'!");
				}
				return themeName;
			}
			if (logger.isDebugEnabled()) {
				logger.debug(String.format(MESSAGE, match, currentURL) + " doesn't match!");				
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("default theme for '" + currentURL + "'");			
		}
		return getDefaultThemeName();
	}

	@Override
	public void setThemeName(HttpServletRequest request,
			HttpServletResponse response, String themeName) {

		throw new UnsupportedOperationException("Cannot change theme - use a different theme resolution strategy");

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (definitions==null) {
			definitions=new HashMap<String, String>(0);
			if (logger.isInfoEnabled()) {
				logger.info("no definitions found always set to default '" + getDefaultThemeName()+"'");
			}			
		}
	}

}
