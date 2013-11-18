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
public class RequestContextFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(RequestContextFilter.class);	
	
	private boolean threadContextInheritable = false;
	/**
	 * Set whether to expose the LocaleContext and RequestAttributes as inheritable
	 * for child threads (using an {@link java.lang.InheritableThreadLocal}).
	 * <p>Default is "false", to avoid side effects on spawned background threads.
	 * Switch this to "true" to enable inheritance for custom child threads which
	 * are spawned during request processing and only used for this request
	 * (that is, ending after their initial task, without reuse of the thread).
	 * <p><b>WARNING:</b> Do not use inheritance for child threads if you are
	 * accessing a thread pool which is configured to potentially add new threads
	 * on demand (e.g. a JDK {@link java.util.concurrent.ThreadPoolExecutor}),
	 * since this will expose the inherited context to such a pooled thread.
	 */
	public void setThreadContextInheritable(boolean threadContextInheritable) {
		this.threadContextInheritable = threadContextInheritable;
	}

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
			LocaleContextHolder.setLocale(request.getLocale(), this.threadContextInheritable);
		} else {
			LocaleContextHolder.setLocale(localeResolver.resolveLocale(request), this.threadContextInheritable);			
		}

		RequestContextHolder.setRequestAttributes(requestAttributes, this.threadContextInheritable);
		if (logger.isDebugEnabled()) {
			logger.debug("Bound request context to thread: " + request);
		}
	}

	private void resetContextHolders() {
		LocaleContextHolder.resetLocaleContext();
		RequestContextHolder.resetRequestAttributes();
	}

}
