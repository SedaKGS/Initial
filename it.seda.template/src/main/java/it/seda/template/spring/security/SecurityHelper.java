/**
 * 
 */
package it.seda.template.spring.security;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author f.ricci
 *
 */
public class SecurityHelper {

	public static final String SPRING_SECURITY_CONTEXT="org.springframework.security.core.context.SecurityContextHolder";
	
	private final static RolesResolver resolver;
	
	static {
		Logger logger = LoggerFactory.getLogger(SecurityHelper.class);
		if (hasSpringSecurity()) {
			resolver=new SpringRolesResolver();
		} else {
			resolver=new DefaultRolesResolver();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("resolver '" + resolver + "'");
		}
	}
	
	public static boolean isInRole(Set<String> roles) {
		return resolver.hasRoles(roles, getHttpRequest());
	}	
	
	public static boolean isInRole(Set<String> roles, HttpServletRequest request) {
		return resolver.hasRoles(roles, request);
	}
	
	private SecurityHelper() {}

	protected static HttpServletRequest getHttpRequest(){
		RequestAttributes attributes=RequestContextHolder.getRequestAttributes();
		return ((ServletRequestAttributes)attributes).getRequest();
	}		
	
	private static boolean hasSpringSecurity() {
		boolean found=false;
		try {
			Class.forName(SPRING_SECURITY_CONTEXT, false, SecurityHelper.class.getClassLoader());
			found=true;
		} catch (Exception ignore){}
		
		return found;
	}
}
