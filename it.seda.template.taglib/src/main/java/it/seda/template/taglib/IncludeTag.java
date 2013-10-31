/**
 * 
 */
package it.seda.template.taglib;

import it.seda.template.container.Parameter;
import it.seda.template.request.ParameterContext;

import java.io.IOException;

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

	private String parameterName;
	private Object[] args;	

	public void setParameter(String parameter) {
		this.parameterName = parameter;
	}
	
	public void setArgs(Object[] args) {
		this.args = args;
	}	

	public int doEndTag() throws JspTagException {

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

}
