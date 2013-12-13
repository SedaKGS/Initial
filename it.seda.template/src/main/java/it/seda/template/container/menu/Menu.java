/**
 * 
 */
package it.seda.template.container.menu;

import it.seda.template.context.TemplateResource;
import it.seda.template.spring.security.SecurityHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @author f.ricci
 *
 */
public class Menu {

	private Logger logger = LoggerFactory.getLogger(Menu.class);
	
	private TemplateResource resource;
	
	private int order;
	private String id;
	private String path;
	private List<AntRequestMatcher> matchers;
	private Set<String> roles;
	
	public int getOrder() {
		return order;
	}

	public String getId() {
		return id;
	}

	public String getPath() {
		return path;
	}

	public TemplateResource getResource() {
		return resource;
	}

	public void setResource(TemplateResource resource) {
		this.resource = resource;
	}

	public void addMatcher(AntRequestMatcher matcher) {
		getMatchers().add(matcher);
	}
	
	public void addAllMatchers(List<AntRequestMatcher> matchers) {
		getMatchers().clear();
		getMatchers().addAll(matchers);
	}	
	
	public void addRole(String role) {
		getRoles().add(role);
	}
	public void addAllRoles(Set<String> roles) {
		getRoles().clear();
		getRoles().addAll(roles);
	}	
	
	public boolean matches(HttpServletRequest request) {
		if (matchers==null || matchers.size()==0) {
			if (logger.isDebugEnabled()) {
				logger.debug("'" + id + "' without request path matchers");
			}
			return true;
		}
		for (AntRequestMatcher matcher : matchers) {
			if (matcher.matches(request)) {
				if (logger.isDebugEnabled()) {
					logger.debug("'" + id + "' request path matches!");
				}				
				return true;
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("'" + id + "' request path match not found ");
		}
		return false;
	}
	
	public boolean isInRole(HttpServletRequest request) {
		if (roles == null || roles.size()==0) {
			if (logger.isDebugEnabled()) {
				logger.debug("'" + id + "' without roles");
			}
			return true;
		}
		final boolean inRole = SecurityHelper.isInRole(roles, request);
		if (!inRole) {
			if (logger.isDebugEnabled()) {
				logger.debug("'" + id + "' roles match not found " + roles);
			}			
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("'" + id + "' role matches!");
			}			
		}
		return inRole;

	}	
	
	public Menu(String id, String path, int order) {
		Assert.notNull(id);
//		Assert.notNull(path);
		this.id=id;
		this.path=path;
		this.order=order;
	}
	
	protected List<AntRequestMatcher> getMatchers() {
		if (matchers==null) {
			matchers= new ArrayList<AntRequestMatcher>();
		}
		return matchers;
	}
	
	protected Set<String> getRoles() {
		if (roles==null) {
			roles= new HashSet<String>();
		}
		return roles;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Menu)) return false;
		
		Menu other = (Menu) obj;
		return this.id.equals(other.getId());
	}

	@Override
	public String toString() {
		return "Menu [order=" + order + ", id=" + id + ", path=" + path
				+ ", matchers=" + matchers + ", roles=" + roles + ", resource="
				+ resource + "]";
	}
	
}
