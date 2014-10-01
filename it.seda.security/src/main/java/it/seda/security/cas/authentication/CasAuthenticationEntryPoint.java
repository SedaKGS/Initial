
package it.seda.security.cas.authentication;

import it.seda.security.authentication.UserDetailsAdapter;
import it.seda.security.cas.CommonUtils;
import it.seda.security.cas.ServiceProperties;
import it.seda.security.domain.Application;
import it.seda.security.service.ManagerService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 * Used by the <code>ExceptionTranslationFilter</code> to commence authentication via the Seda Central
 * Authentication Service (CAS).
 * <p>
 * The user's browser will be redirected to the SEDA CAS enterprise-wide login page.
 * This page is specified by the <code>loginUrl</code> property. Once login is complete, the CAS login page will
 * redirect to the page indicated by the <code>service</code> property. The <code>service</code> is a HTTP URL
 * belonging to the current application. The <code>service</code> URL is monitored by the {@link CasAuthenticationFilter},
 * which will validate the CAS login was successful.
 *
 * @author f.ricci
 */
public class CasAuthenticationEntryPoint implements AuthenticationEntryPoint, InitializingBean {

	@Autowired protected ManagerService managerService;
	@Autowired protected UserDetailsService userDetailsService;
//	@Autowired protected UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken ;
	
	private ServiceProperties serviceProperties;
	
	private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    private String loginUrl;
    private String redirectUrl ="";
    private String urlEncodedService = "";
    private String applicationId = "";

    public void afterPropertiesSet() throws Exception {
        Assert.hasLength(this.loginUrl, "loginUrl must be specified");
        Assert.notNull(this.serviceProperties, "serviceProperties must be specified");
        Assert.notNull(this.serviceProperties.getService(),"serviceProperties.getService() cannot be null.");
    }

    public final void commence(final HttpServletRequest servletRequest, final HttpServletResponse response,
            final AuthenticationException authenticationException) throws IOException, ServletException {

    	urlEncodedService = createServiceUrl(servletRequest, response);
        redirectUrl = createRedirectUrl(urlEncodedService);
        applicationId = getApplicationId(serviceProperties);
        
        preCommence(servletRequest, response);
        
        //TODO Verificare se c'un ticket nella request ed in tal caso chiamare il web services per il salvataggio dell userbean
        submitTicket(servletRequest,response);
//        saveApplicationId(servletRequest);
        response.sendRedirect(concatApplicationIdToUrl(applicationId));
    }


	/**
     * Constructs a new Service Url.  The default implementation relies on the CAS client to do the bulk of the work.
     * @param request the HttpServletRequest
     * @param response the HttpServlet Response
     * @return the constructed service url.  CANNOT be NULL.
     */
    protected String createServiceUrl(final HttpServletRequest request, final HttpServletResponse response) {
        return CommonUtils.constructServiceUrl(null, response, this.serviceProperties.getService(), null, this.serviceProperties.getArtifactParameter(), true);
    }

    /**
     * Constructs the Url for Redirection to the CAS server.  Default implementation relies on the CAS client to do the bulk of the work.
     *
     * @param serviceUrl the service url that should be included.
     * @return the redirect url.  CANNOT be NULL.
     */
    protected String createRedirectUrl(final String serviceUrl) {
        return CommonUtils.constructRedirectUrl(this.loginUrl, this.serviceProperties.getServiceParameter(), serviceUrl, this.serviceProperties.isSendRenew(), false);
    }

    /**
     * Template method for you to do your own pre-processing before the redirect occurs.
     *
     * @param request the HttpServletRequest
     * @param response the HttpServletResponse
     */
    protected void preCommence(final HttpServletRequest request, final HttpServletResponse response) {

    }

    /**
     * The enterprise-wide CAS login URL. Usually something like
     * <code>https://www.mycompany.com/cas/login</code>.
     *
     * @return the enterprise-wide CAS login URL
     */
    public final String getLoginUrl() {
        return this.loginUrl;
    }

    public final ServiceProperties getServiceProperties() {
        return this.serviceProperties;
    }

    public final void setLoginUrl(final String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public final void setServiceProperties(final ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    /* Metodo per ottenere l'Id della applicazione */
    protected String getApplicationId (ServiceProperties serviceProperties) {
    	String applicationName  = serviceProperties.getApplicationName();
    	Application application = null;
    	try {
    		application = managerService.selectApplicationIdByName(applicationName);	
		} catch (Exception e) {
			logger.error("Applicazione: " +applicationName +" non censita all'interno della tabella Applications");
			logger.error(e.getMessage());
		}
    	if (application != null) {
    		return application.getId();	
    	} 
    	return "";
    }
    
    /* Metodo per concatenare l'ÏId della applicazione alla Url di redirect */ 
    protected String concatApplicationIdToUrl (String applicationId) {
    	logger.debug("Url di redirect: " +redirectUrl + "&applicationId=" +applicationId);
		return redirectUrl = redirectUrl + "&applicationId=" +applicationId;
	}


    private void saveApplicationId(HttpServletRequest servletRequest) {
	
    	RestTemplate restTemplate = new RestTemplate();

    	restTemplate.put(urlEncodedService+"/casws/"+applicationId, applicationId);
		
	}
    
    protected void submitTicket(HttpServletRequest servletRequest,HttpServletResponse response) throws IOException {
		
    	String ticket = servletRequest.getParameter("ticket");
    	if (ticket== null)  {
    		return;
    	}
    	
    	UserDetailsAdapter userDetailsAdapter = getUserBean(ticket);  	
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            // in here, get your principal, and populate the auth object with 
            // the right authorities
        	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken("luca","Seda123%");
        	usernamePasswordAuthenticationToken.setDetails(userDetailsAdapter);
        	usernamePasswordAuthenticationToken.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        response.sendRedirect("it.seda.example.springProject/");
        
	}

	private UserDetailsAdapter getUserBean(String ticket) {
//		UserDetailsAdapter userDetailsAdapter = new UserDetailsAdapter();
//		userDetailsAdapter.setPassword("Seda123%");
		
		RestTemplate restTemplate = new RestTemplate();
		UserDetailsAdapter userDetailsAdapter = restTemplate.getForObject(redirectUrl, UserDetailsAdapter.class);
		return null;
	}
}
