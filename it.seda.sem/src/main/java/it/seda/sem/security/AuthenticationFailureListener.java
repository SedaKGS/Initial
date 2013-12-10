package it.seda.sem.security;

import it.seda.sem.security.domain.Account;
import it.seda.sem.security.service.AccountService;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	@Inject AccountService accountService;
	
	@Override
	public void onApplicationEvent(
			AuthenticationFailureBadCredentialsEvent event) {

		String username = event.getAuthentication().getName();
		
		Account user = accountService.getAccountByUserName(username);
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
			accountService.reportLoginFailure(username);
		}
		
	}

	protected HttpServletRequest resolveRequest() {
		RequestAttributes attributes=RequestContextHolder.getRequestAttributes();
		return ((ServletRequestAttributes)attributes).getRequest();
	}
}
