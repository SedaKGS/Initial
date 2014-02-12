package it.seda.security.authentication;

import it.seda.security.domain.Account;
import it.seda.security.service.SecurityService;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * This listener will be called when authentication failures appear.
 * Each authentication failure will inform the user. 
 * The user increments an authentication failure counter and deactivates it self when a certain threshold is reached
 * 
 * @author f.ricci
 *
 */
@Component("authenticationFailureListener")
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

	public static final long BFD_THRESHOLD=5000;
	
	private Logger logger = LoggerFactory.getLogger(AuthenticationFailureListener.class);
	
	@Autowired SecurityService securityService;
	
	@Override
	public void onApplicationEvent(
			AuthenticationFailureBadCredentialsEvent event) {

		String username = event.getAuthentication().getName();
		
		Account user = securityService.getAccountByUserName(username);
		if (user!=null) {
			Date lastAttempt = user.getLastAttempt();
			if (lastAttempt==null) {
				lastAttempt=new Date();
			}
			Date current = new Date();
			if (current.getTime()-lastAttempt.getTime()<=BFD_THRESHOLD) {
				if (logger.isWarnEnabled()) {
					HttpServletRequest request = resolveRequest();
					logger.warn("Brute force detected for '" + username + "' from '" + request.getRemoteAddr() + "'");					
				}
			}
			securityService.reportLoginFailure(username);
		}
		
	}

	protected HttpServletRequest resolveRequest() {
		RequestAttributes attributes=RequestContextHolder.getRequestAttributes();
		return ((ServletRequestAttributes)attributes).getRequest();
	}
}
