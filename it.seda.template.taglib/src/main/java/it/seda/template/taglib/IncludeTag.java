/**
 * 
 */
package it.seda.template.taglib;

import it.seda.template.container.Parameter;
import it.seda.template.request.ParameterContext;
import it.seda.template.taglib.security.SecurityHelper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author f.ricci
 *
 */
@SuppressWarnings("serial")
public class IncludeTag extends TagSupport {

	
	private SecurityHelper securityHelper;
	
	private String parameterName;
	private Object[] args;	
	private Set<String> roles;
	
	public void setParameter(String parameter) {
		this.parameterName = parameter;
	}
	
	public void setArgs(Object[] args) {
		this.args = args;
	}	

	public void setRoles(String roles) {
		if (roles!=null) {
			this.roles=new HashSet<String>();
			for(String role:roles.split(",")) {
				this.roles.add(role);
			}
		}
	}
	
	public int doEndTag() throws JspTagException {

		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
		
		if (roles!=null && roles.size()>0) {
			if (!getSecurityHelper().isInRole(roles, request)) {
				return EVAL_PAGE; 
			}
		}
		
		ParameterContext parameterContext = ParameterContext.retrieve((HttpServletRequest)pageContext.getRequest());

		try {
			if (parameterContext==null) {
				pageContext.getOut().print("<p style=\"color: red;\">template missing parameter context</p>");
			} else {
				Parameter parameter = parameterContext.getParameters().get(parameterName);
				if (parameter==null) {
					pageContext.getOut().print("<p style=\"color: red;\">template missing parameter <i>"+parameterName+"</i></p>");
				} else {
					render(parameterContext, parameter);
				}

			}
			
		} catch (Exception x) {
			x.printStackTrace();
		} finally {
			recycle();
		}

		return EVAL_PAGE;
	}

	private void render(ParameterContext parameterContext, Parameter parameter) throws ServletException, IOException {
		if (parameter.getValue().toLowerCase().endsWith(".jsp")) {
			pageContext.getRequest().getRequestDispatcher(parameter.getValue()).include(pageContext.getRequest(), pageContext.getResponse());			
		} else {
			pageContext.getOut().print(parameterContext.getMessage(parameter.getValue(), args));
		}
	}		

	private SecurityHelper getSecurityHelper() {
		if (securityHelper==null) {
			securityHelper=new SecurityHelper();
		}
		return securityHelper;
	}
	
	private void recycle() {
		parameterName=null;
		args=null;	
		roles=null;
		
	}	
		
}
