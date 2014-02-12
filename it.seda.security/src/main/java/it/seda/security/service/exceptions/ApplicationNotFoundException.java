/**
 * 
 */
package it.seda.security.service.exceptions;

/**
 * @author f.ricci
 *
 */
@SuppressWarnings("serial")
public class ApplicationNotFoundException extends RuntimeException {

	private ApplicationNotFoundException(String message) {
		super(message);
	}

	
	public static ApplicationNotFoundException idNotFound(String applicationId) {
		return new ApplicationNotFoundException("application id="+applicationId);
	}

}
