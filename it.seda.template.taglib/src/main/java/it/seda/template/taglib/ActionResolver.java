/**
 * 
 */
package it.seda.template.taglib;

import java.beans.PropertyEditor;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.JspAwareRequestContext;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestDataValueProcessor;
import org.springframework.web.util.ExpressionEvaluationUtils;
import org.springframework.web.util.HtmlUtils;

/**
 * @author f.ricci
 *
 */
public class ActionResolver {

	private Logger logger = LoggerFactory.getLogger(ActionResolver.class);
	
	private static final String ACTION_ATTRIBUTE = "action";
	
	/**
	 * {@link javax.servlet.jsp.PageContext} attribute for the
	 * page-level {@link RequestContext} instance.
	 */
	public static final String REQUEST_CONTEXT_PAGE_ATTRIBUTE =
			"org.springframework.web.servlet.tags.REQUEST_CONTEXT";	
	
	private PageContext pageContext;
	private RequestContext requestContext;
	/**
	 * Return the current RequestContext.
	 */
	protected final RequestContext getRequestContext() {
		return this.requestContext;
	}	
	
	public ActionResolver(PageContext pageContext) throws JspTagException {
		this.pageContext=pageContext;

		try {
			this.requestContext = (RequestContext) this.pageContext.getAttribute(REQUEST_CONTEXT_PAGE_ATTRIBUTE);
			if (this.requestContext == null) {
				this.requestContext = new JspAwareRequestContext(this.pageContext);
				this.pageContext.setAttribute(REQUEST_CONTEXT_PAGE_ATTRIBUTE, this.requestContext);
			}
		}
		catch (RuntimeException ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
		catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new JspTagException(ex.getMessage());
		}		
	}
	
	/**
	 * Resolve the value of the '{@code action}' attribute.
	 * <p>If the user configured an '{@code action}' value then the result of
	 * evaluating this value is used. If the user configured an
	 * '{@code servletRelativeAction}' value then the value is prepended
	 * with the context and servlet paths, and the result is used. Otherwise, the
	 * {@link org.springframework.web.servlet.support.RequestContext#getRequestUri()
	 * originating URI} is used.
	 *
	 * @return the value that is to be used for the '{@code action}' attribute
	 */
	protected String resolveAction(String action, String servletRelativeAction) throws JspException {
		if (StringUtils.hasText(action)) {
			action = getDisplayString(evaluate(ACTION_ATTRIBUTE, action));
			return processAction(action);
		}
		else if (StringUtils.hasText(servletRelativeAction)) {
			String pathToServlet = getRequestContext().getPathToServlet();
			if (servletRelativeAction.startsWith("/") && !servletRelativeAction.startsWith(getRequestContext().getContextPath())) {
				servletRelativeAction = pathToServlet + servletRelativeAction;
			}
			servletRelativeAction = getDisplayString(evaluate(ACTION_ATTRIBUTE, servletRelativeAction));
			return processAction(servletRelativeAction);
		}
		else {
			String requestUri = getRequestContext().getRequestUri();
			ServletResponse response = this.pageContext.getResponse();
			if (response instanceof HttpServletResponse) {
				requestUri = ((HttpServletResponse) response).encodeURL(requestUri);
				String queryString = getRequestContext().getQueryString();
				if (StringUtils.hasText(queryString)) {
					requestUri += "?" + HtmlUtils.htmlEscape(queryString);
				}
			}
			if (StringUtils.hasText(requestUri)) {
				return processAction(requestUri);
			}
			else {
				throw new IllegalArgumentException("Attribute 'action' is required. " +
						"Attempted to resolve against current request URI but request URI was null.");
			}
		}
	}

	/**
	 * Process the action through a {@link RequestDataValueProcessor} instance
	 * if one is configured or otherwise returns the action unmodified.
	 */
	private String processAction(String action) {
		RequestDataValueProcessor processor = getRequestContext().getRequestDataValueProcessor();
		ServletRequest request = this.pageContext.getRequest();
		if ((processor != null) && (request instanceof HttpServletRequest)) {
			action = processor.processAction((HttpServletRequest) request, action);
		}
		return action;
	}	
	

	/**
	 * Build the display value of the supplied {@code Object}, HTML escaped
	 * as required. This version is <strong>not</strong> {@link PropertyEditor}-aware.
	 * @see #getDisplayString(Object, java.beans.PropertyEditor, boolean)
	 */
	public static String getDisplayString(Object value) {
		String displayValue = ObjectUtils.getDisplayString(value);
		return (true ? HtmlUtils.htmlEscape(displayValue) : displayValue);
	}	
	
	/**
	 * Evaluate the supplied value for the supplied attribute name. If the supplied value
	 * is {@code null} then {@code null} is returned, otherwise evaluation is
	 * handled using {@link ExpressionEvaluationUtils#evaluate(String, String, javax.servlet.jsp.PageContext)}.
	 */
	protected Object evaluate(String attributeName, Object value) throws JspException {
		if (value instanceof String) {
			return ExpressionEvaluationUtils.evaluate(attributeName, (String) value, this.pageContext);
		}
		else {
			return value;
		}
	}
}
