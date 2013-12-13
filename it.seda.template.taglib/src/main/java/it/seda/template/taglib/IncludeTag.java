/**
 * 
 */
package it.seda.template.taglib;

import it.seda.template.container.Parameter;
import it.seda.template.request.ParameterContext;
import it.seda.template.spring.security.SecurityHelper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author f.ricci
 *
 */
@SuppressWarnings("serial")
public class IncludeTag extends TagSupport implements TryCatchFinally {

	private Logger logger = LoggerFactory.getLogger(IncludeTag.class);
	
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
			this.hasRoles=new HashSet<String>(StringUtils.commaDelimitedListToSet(hasRoles));
//			this.hasRoles=new HashSet<String>();			
//			for(String role:hasRoles.split(",")) {
//				this.hasRoles.add(role);
//			}
		}
	}
	
	public int doEndTag() throws JspTagException {
		try{
			pageContext.getOut().flush();
		}catch(Exception ignore){}

		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
		
		if (hasRoles!=null && hasRoles.size()>0) {
			if (!SecurityHelper.isInRole(hasRoles, request)) {
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
			logger.error(x.getMessage(), x);
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

	@Override
	public void doCatch(Throwable throwable) throws Throwable {
		throw throwable;
	}

	@Override
	public void doFinally() {
		parameterName=null;
		args=null;	
		hasRoles=null;
	}	
		
}
