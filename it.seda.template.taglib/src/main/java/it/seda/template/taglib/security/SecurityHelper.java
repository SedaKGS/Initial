/**
 * 
 */
package it.seda.template.taglib.security;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author f.ricci
 *
 */
public class SecurityHelper {

	public static final String SPRING_SECURITY_CONTEXT="org.springframework.security.core.context.SecurityContextHolder";
	
	private RolesResolver resolver;
	
	public boolean isInRole(Set<String> roles, HttpServletRequest request) {
		return resolver.hasRoles(roles, request);
	}
	
	public SecurityHelper() {
		if (hasSpringSecurity()) {
			resolver=new SpringRolesResolver();
		} else {
			resolver=new DefaultRolesResolver();
		}
	}

	private boolean hasSpringSecurity() {
		boolean found=false;
		try {
			Class.forName(SPRING_SECURITY_CONTEXT, false, SecurityHelper.class.getClassLoader());
			found=true;
		} catch (Exception ignore){}
		
		return found;
	}
}
