/**
 * 
 */
package it.seda.template.container.menu;

import javax.servlet.ServletContext;

/**
 * @author f.ricci
 *
 */
public interface MenuConfigurer {

	MenuHandler configure(ServletContext servletContext);
	
}
