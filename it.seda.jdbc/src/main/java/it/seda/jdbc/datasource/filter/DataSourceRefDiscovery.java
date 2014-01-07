/**
 * 
 */
package it.seda.jdbc.datasource.filter;

import javax.servlet.http.HttpServletRequest;

/**
 * Provides the ability to find the current data source ref key lookup.
 *  
 * @author f.ricci
 *
 */
public interface DataSourceRefDiscovery {

	/**
	 * 
	 * @param request current HttpServletRequest
	 * @return the data source ref key lookup
	 */
	String find(HttpServletRequest request);
	
}
