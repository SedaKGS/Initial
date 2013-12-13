/**
 * 
 */
package it.seda.template.spring.security;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author f.ricci
 *
 */
public class SpringRolesResolver implements RolesResolver {

	@Override
	public boolean hasRoles(Set<String> roles, HttpServletRequest request) {
		
		SecurityContext ctx=SecurityContextHolder.getContext(); 
		
		for(GrantedAuthority auth: ctx.getAuthentication().getAuthorities()) {
			if (roles.contains(auth.getAuthority())) {
				return true;
			}
		
		}
		return false;
	}

}
