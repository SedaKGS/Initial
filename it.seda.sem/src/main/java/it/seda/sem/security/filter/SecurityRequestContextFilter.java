package it.seda.sem.security.filter;

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
public class SecurityRequestContextFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(SecurityRequestContextFilter.class);	
	
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
		
		ServletRequestAttributes attributes = new ServletRequestAttributes(request);
		initContextHolders(request, attributes);

		try {
			filterChain.doFilter(request, servletResponse);
		}
		finally {
			resetContextHolders();
			if (logger.isDebugEnabled()) {
				logger.debug("Cleared thread-bound request context: " + request);
			}
			attributes.requestCompleted();
		}
		
	}
	
	private void initContextHolders(HttpServletRequest request, ServletRequestAttributes requestAttributes) {
		
		LocaleResolver localeResolver = ApplicationContextHolder.getContext().getBean(LocaleResolver.class);
		if (localeResolver == null) {
			LocaleContextHolder.setLocale(request.getLocale(), true);
		} else {
			LocaleContextHolder.setLocale(localeResolver.resolveLocale(request), true);			
		}

		RequestContextHolder.setRequestAttributes(requestAttributes, true);
		if (logger.isDebugEnabled()) {
			logger.debug("Bound request context to thread: " + request);
		}
	}

	private void resetContextHolders() {
		LocaleContextHolder.resetLocaleContext();
		RequestContextHolder.resetRequestAttributes();
	}

}
