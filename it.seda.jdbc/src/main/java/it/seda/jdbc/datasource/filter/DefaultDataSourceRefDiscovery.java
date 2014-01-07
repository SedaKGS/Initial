package it.seda.jdbc.datasource.filter;

import it.seda.jdbc.datasource.DataSourceContext;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Find the current data source ref depending on http servlet request paths. 
 * Compares a pre-defined ant-style pattern against the URL. 
 * Matching operation preserve the user provided definition order. The first match returns the ref, otherwise the defaultRef. 
 * @author f.ricci
 *
 */
public class DefaultDataSourceRefDiscovery implements DataSourceRefDiscovery, InitializingBean, DisposableBean {

	private Logger logger = LoggerFactory.getLogger(DefaultDataSourceRefDiscovery.class);
	
	private String defaultRef;
	private Map<String, AntRequestMatcher> definitions;
	
	public void setDefinitions(Map<String, String> definitions) {
		if (definitions!=null) {
			if (logger.isInfoEnabled()) {
				logger.info("adding definitions -> " + definitions);
			}			
			this.definitions = new LinkedHashMap<String, AntRequestMatcher>(definitions.size());
			for (String url : definitions.keySet()) {
				this.definitions.put(definitions.get(url), new AntRequestMatcher(url));
			}
		}
	}
	
	public void setDefaultRef(String defaultRef) {
		this.defaultRef=defaultRef;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if (defaultRef==null) {
			defaultRef=DataSourceContext.DEFAULT_REF;
		}
		if (logger.isDebugEnabled()) {
			logger.info("default data source ref set to '" + defaultRef+ "'");
		}		
		
		if (definitions==null) {
			definitions=new HashMap<String, AntRequestMatcher>(0);
			if (logger.isInfoEnabled()) {
				logger.info("no definitions found ref always set to default '" + defaultRef+"'");
			}			
		}		
	}

	
	@Override
	public String find(HttpServletRequest request) {
		final String currentURL = request.getRequestURL().toString();

		for (String dataSourceRef : definitions.keySet()) {
			AntRequestMatcher matcher = definitions.get(dataSourceRef);
			if (matcher.matches(request)) {
				return dataSourceRef;
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("default data source ref for '" + currentURL + "'");			
		}
		
		return defaultRef;
	}

	
	
	@Override
	public void destroy() throws Exception {
	}

	
}
