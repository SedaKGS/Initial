package it.seda.security.filter;

import it.seda.security.ApplicationContextProvider.ApplicationContextHolder;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.LocaleResolver;

/**
 * 
 * @author f.ricci
 */
public class SecurityRequestContextFilter extends OncePerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(SecurityRequestContextFilter.class);	
	
	private boolean threadContextInheritable = false;
	
	/**
	 * Set whether to expose the DataSourceContext as inheritable
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

	/**
	 * Returns "false" so that the filter may set up the request context in each
	 * asynchronously dispatched thread.
	 */
	@Override
	protected boolean shouldNotFilterAsyncDispatch() {
		return false;
	}

	/**
	 * Returns "false" so that the filter may set up the request context in an
	 * error dispatch.
	 */
	@Override
	protected boolean shouldNotFilterErrorDispatch() {
		return false;
	}		
	
	@Override
	public void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		
		ServletRequestAttributes attributes = new ServletRequestAttributes(request);
		initContextHolders(request, attributes);

		try {
			filterChain.doFilter(request, response);
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
			LocaleContextHolder.setLocale(request.getLocale(), threadContextInheritable);
		} else {
			LocaleContextHolder.setLocale(localeResolver.resolveLocale(request), threadContextInheritable);			
		}

		RequestContextHolder.setRequestAttributes(requestAttributes, threadContextInheritable);
		if (logger.isDebugEnabled()) {
			logger.debug("Bound request context to thread: " + request);
		}
	}

	private void resetContextHolders() {
		LocaleContextHolder.resetLocaleContext();
		RequestContextHolder.resetRequestAttributes();
	}

}
