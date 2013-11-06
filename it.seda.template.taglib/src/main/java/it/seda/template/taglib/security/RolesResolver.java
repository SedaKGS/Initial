package it.seda.template.taglib.security;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author f.ricci
 *
 */
public interface RolesResolver {

	boolean hasRoles(Set<String> roles, HttpServletRequest request);
	
}
