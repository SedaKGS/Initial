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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author f.ricci
 *
 */
@SuppressWarnings("serial")
public class IncludeTag extends TagSupport {

	private Logger logger = LoggerFactory.getLogger(IncludeTag.class);
	private SecurityHelper securityHelper;
	
	private String parameterName;
	private Object[] args;	
	private Set<String> hasRoles;
	
	public void setParameter(String parameter) {
		this.parameterName = parameter;
	}
	
	public void setArgs(Object[] args) {
		this.args = args;
	}	

	public void setHasRoles(String hasRoles) {
		if (hasRoles!=null) {
			this.hasRoles=new HashSet<String>();
			for(String role:hasRoles.split(",")) {
				this.hasRoles.add(role);
			}
		}
	}
	
	public int doEndTag() throws JspTagException {
		try{
			pageContext.getOut().flush();
		}catch(Exception ignore){}

		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
		
		if (hasRoles!=null && hasRoles.size()>0) {
			if (!getSecurityHelper().isInRole(hasRoles, request)) {
				return EVAL_PAGE; 
			}
		}
		
		ParameterContext parameterContext = ParameterContext.retrieve((HttpServletRequest)pageContext.getRequest());

		try {
			if (parameterContext==null) {
				logger.warn("template missing parameter context");
			} else {
				Parameter parameter = parameterContext.getParameters().get(parameterName);
				if (parameter==null) {
					logger.warn("template missing parameter "+parameterName);
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
		hasRoles=null;
		
	}	
		
}
