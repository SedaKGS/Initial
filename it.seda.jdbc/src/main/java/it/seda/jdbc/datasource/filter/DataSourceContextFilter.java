package it.seda.jdbc.datasource.filter;

import it.seda.jdbc.datasource.DataSourceContextHolder;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * This filter is responsible to initialize the DataSourceContextHolder. It uses DataSourceRefDiscovery bean to find the data source ref. If no user defined 
 * DataSourceRefDiscovery bean was provided it use DefaultDataSourceRefDiscovery bean.
 *  
 * @author f.ricci
 * @see it.seda.jdbc.datasource.filter.DataSourceRefDiscovery
 */
public class DataSourceContextFilter extends OncePerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(DataSourceContextFilter.class);	
	
	private DataSourceRefDiscovery dataSourceRefDiscovery;
	
	private boolean threadContextInheritable = false;

	/**
	 * Sets discovery bean that will be used to find the current data source key lookup. Default it.seda.jdbc.datasource.filter.DataSourceRefDiscovery.
	 * 
	 */
	public void setDataSourceRefDiscovery(DataSourceRefDiscovery dataSourceRefDiscovery) {
		this.dataSourceRefDiscovery=dataSourceRefDiscovery;
	}
	
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
	protected void initFilterBean() throws ServletException {
		if (dataSourceRefDiscovery==null) {
			dataSourceRefDiscovery=new DefaultDataSourceRefDiscovery();
		}
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		initContextHolders(request);

		try {
			filterChain.doFilter(request, response);
		}
		finally {
			resetContextHolders();
			if (logger.isDebugEnabled()) {
				logger.debug("Cleared thread-bound datasource context: " + request);
			}
		}
		
	}
	
	private void initContextHolders(HttpServletRequest request) {
		String dataSourceRef = dataSourceRefDiscovery.find(request);

		DataSourceContextHolder.setDataSourceRef(dataSourceRef,threadContextInheritable);

		if (logger.isDebugEnabled()) {
			logger.debug("Bound datasource context to thread: " + request);
		}
	}

	private void resetContextHolders() {
		DataSourceContextHolder.resetDataSourceContext();
	}


}
