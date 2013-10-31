/**
 * 
 */
package it.seda.template.request;

import it.seda.template.container.TemplateContainer;
import it.seda.template.context.TemplateContext;
import it.seda.template.utils.Utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author f.ricci
 *
 */
public class ServletRequest implements Request{

    /**
     * The native available scopes: request, session and application.
     */
    private static final List<String> SCOPES
            = Collections.unmodifiableList(Arrays.asList("request", "session", "application"));

    
    private TemplateContext templateContext;
    
    /**
     * The request object to use.
     */
    private HttpServletRequest request;

    /**
     * The response object to use.
     */
    private HttpServletResponse response;

    /**
     * The response output stream, lazily initialized.
     */
    private OutputStream outputStream;

    /**
     * The response writer, lazily initialized.
     */
    private PrintWriter writer;

    /**
     * <p>The lazily instantiated <code>Map</code> of header name-value
     * combinations (immutable).</p>
     */
    private Map<String, String> header = null;

    /**
     * <p>The lazily instantiated <code>Map</code> of header name-value
     * combinations (write-only).</p>
     */
    private List<String> responseHeaders = null;


    /**
     * <p>The lazily instantitated <code>Map</code> of header name-values
     * combinations (immutable).</p>
     */
    private Map<String, String[]> headerValues = null;


    /**
     * <p>The lazily instantiated <code>Map</code> of request
     * parameter name-value.</p>
     */
    private Map<String, String> param = null;


    /**
     * <p>The lazily instantiated <code>Map</code> of request scope
     * attributes.</p>
     */
    private Map<String, Object> requestScope = null;

    /**
     * <p>The lazily instantiated <code>Map</code> of session scope
     * attributes.</p>
     */
    private Map<String, Object> sessionScope = null;


    /**
     * Creates a new instance of ServletTilesRequestContext.
     *
     * @param applicationContext The application context.
     * @param request The request object.
     * @param response The response object.
     */
    public ServletRequest(
            TemplateContext templateContext,
            HttpServletRequest request, HttpServletResponse response) {
    	this.templateContext = templateContext;
        this.request = request;
        this.response = response;
    }

	@Override
	public TemplateContext getTemplateContext() {
		return templateContext;
	}
    
    
    /** {@inheritDoc} */
    public Map<String, String> getHeader() {

        if ((header == null) && (request != null)) {
//            header = new ReadOnlyEnumerationMap<String>(new HeaderExtractor(request, null));
        }
        return (header);

    }

    /** {@inheritDoc} */
    public List<String> getResponseHeaders() {

        if ((responseHeaders == null) && (response != null)) {
//            responseHeaders = new HeaderExtractor(null, response);
        }
        return (responseHeaders);

    }

    /** {@inheritDoc} */
    public Map<String, String[]> getHeaderValues() {

        if ((headerValues == null) && (request != null)) {
//            headerValues = new HeaderValuesMap(new HeaderExtractor(request, response));
        }
        return (headerValues);

    }


    /** {@inheritDoc} */
    public Map<String, String> getParam() {

        if ((param == null) && (request != null)) {
//            param = new ReadOnlyEnumerationMap<String>(new ParameterExtractor(request));
        }
        return (param);

    }


    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    public Map<String, String[]> getParamValues() {
        return request.getParameterMap();
    }

    @Override
    public Map<String, Object> getContext(String scope) {
        if("request".equals(scope)){
            return getRequestScope();
        }else if("session".equals(scope)){
            return getSessionScope();
        }else if("application".equals(scope)){
//            return getApplicationScope();
        	return null;
        }
        throw new IllegalArgumentException(scope + " does not exist. Call getAvailableScopes() first to check.");
    }

    /** {@inheritDoc} */
    public Map<String, Object> getRequestScope() {

        if ((requestScope == null) && (request != null)) {
//            requestScope = new ScopeMap(new RequestScopeExtractor(request));
        }
        return (requestScope);

    }


    /** {@inheritDoc} */
    public Map<String, Object> getSessionScope() {

        if ((sessionScope == null) && (request != null)) {
//            sessionScope = new ScopeMap(new SessionScopeExtractor(request));
        }
        return (sessionScope);

    }

    @Override
    public List<String> getAvailableScopes() {
        return SCOPES;
    }

    /** {@inheritDoc} */
    public void doForward(String path) throws IOException {
        if (response.isCommitted()) {
            doInclude(path);
        } else {
            forward(path);
        }
    }


    /** {@inheritDoc} */
    public void doInclude(String path) throws IOException {
        RequestDispatcher rd = request.getRequestDispatcher(path);

        if (rd == null) {
            throw new IOException("No request dispatcher returned for path '"
                    + path + "'");
        }

        try {
            rd.include(request, response);
        } catch (ServletException ex) {
            throw Utils.wrapServletException(ex, "ServletException including path '"
                    + path + "'.");
        }
    }

    /**
     * Forwards to a path.
     *
     * @param path The path to forward to.
     * @throws IOException If something goes wrong during the operation.
     */
    private void forward(String path) throws IOException {
        RequestDispatcher rd = request.getRequestDispatcher(path);

        if (rd == null) {
            throw new IOException("No request dispatcher returned for path '"
                    + path + "'");
        }

        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            throw Utils.wrapServletException(ex, "ServletException including path '"
                    + path + "'.");
        }
    }

    /** {@inheritDoc} */
    public OutputStream getOutputStream() throws IOException {
        if (outputStream == null) {
            outputStream = response.getOutputStream();
        }
        return outputStream;
    }

    /** {@inheritDoc} */
    public Writer getWriter() throws IOException {
        return getPrintWriter();
    }

    /** {@inheritDoc} */
    public PrintWriter getPrintWriter() throws IOException {
        if (writer == null) {
            writer = response.getWriter();
        }
        return writer;
    }

    /** {@inheritDoc} */
    public boolean isResponseCommitted() {
        return response.isCommitted();
    }

    /** {@inheritDoc} */
    public void setContentType(String contentType) {
        response.setContentType(contentType);
    }

    /** {@inheritDoc} */
    public Locale getRequestLocale() {
        return request.getLocale();
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    /** {@inheritDoc} */
    public boolean isUserInRole(String role) {
        return request.isUserInRole(role);
    }

}
