/**
 * 
 */
package it.seda.template.spring.security;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author f.ricci
 *
 */
public class DefaultRolesResolver implements RolesResolver {

	@Override
	public boolean hasRoles(Set<String> roles, HttpServletRequest request) {
		
		for (String role : roles) {
			if (request.isUserInRole(role)) {
				return true;
			}			
		}

		return false;
	}

}
