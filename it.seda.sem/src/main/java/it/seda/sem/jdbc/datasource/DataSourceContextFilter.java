package it.seda.sem.jdbc.datasource;

import it.seda.sem.security.ApplicationContextProvider.ApplicationContextHolder;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

/**
 * 
 * @author f.ricci
 */
public class DataSourceContextFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(DataSourceContextFilter.class);	
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		
		initContextHolders(request);

		try {
			filterChain.doFilter(request, servletResponse);
		}
		finally {
			resetContextHolders();
			if (logger.isDebugEnabled()) {
				logger.debug("Cleared thread-bound datasource context: " + request);
			}
		}
		
	}
	
	private void initContextHolders(HttpServletRequest request) {

		// qui la logica di gestione di ricerca di quale datasource
		// il nome deve matchare conquelo definito nella configurazione di spring sul root-context
		// in futuro vedremo con l'applicativo come implementarlo, se caso mai da un 
		// bean a livello di root-context che estende una classe particolare e con la gestione di una 
		// priorità gerarchica in modo da cercarlo su più...
		DataSourceContextHolder.setDataSourceRef("dataSourceRef",true);

		if (logger.isDebugEnabled()) {
			logger.debug("Bound datasource context to thread: " + request);
		}
	}

	private void resetContextHolders() {
		DataSourceContextHolder.resetDataSourceContext();
	}

}
