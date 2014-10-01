/**
 * 
 */
package it.seda.security.cas.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author lmontesi
 *
 */
public class ApplicationIdFilter implements Filter{

    @Override
    public void destroy() {
        // Do nothing
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {

            HttpServletRequest request = (HttpServletRequest) req;
            HttpSession session=request.getSession();
            String applicationId = request.getParameter("applicationId");
            if (applicationId != null) {
            	session.setAttribute("applicationId", applicationId);
            }
            chain.doFilter(req, res);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // Do nothing
    }

}
