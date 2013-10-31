/**
 * 
 */
package it.seda.template.context;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Locale;

/**
 * @author f.ricci
 *
 */
public interface TemplateResource {

	/**
	 * Get the path name for this resource.
	 * You can access this ressource by passing the path to 
	 * {@link TemplateContext#getResource(String) getResource}.
	 * 
	 * @return the path including localization.
	 */
	String getLocalePath();

	/**
	 * Get the path name for this resource. Multiple versions of 
	 * a resource can share the same path if the locale part is different.
	 * 
	 * @return the path excluding localization.
	 */
	String getPath();

	/**
	 * Get the Locale for this resource.
	 * 
	 * @return the Locale.
	 */
	Locale getLocale();

	/**
	 * Get the path name of another version of the resource.
	 * 
	 * @param locale the Locale for the new version.
	 * @return the path including localization.
	 */
	String getLocalePath(Locale locale);

	/**
	 * Get a java.io.InputStream to read the contents of this resource.
	 * 
	 * @return the InputStream.
	 * @throws IOException if the contents cannot be read.
	 */
	InputStream getInputStream() throws IOException;
	
	/**
	 * Get a java.io.Reader to read the contents of this resource.
	 * 
	 * @return the InputStream.
	 * @throws IOException if the contents cannot be read.
	 */
	Reader getReader() throws IOException;	

	/**
	 * Get the last modification date for this resource.
	 * 
	 * @return the difference, measured in milliseconds, between the current 
	 * time and midnight, January 1, 1970 UTC.
	 * @throws IOException if the last modification date cannot be found.
	 */
	long getLastModified() throws IOException;		

}
