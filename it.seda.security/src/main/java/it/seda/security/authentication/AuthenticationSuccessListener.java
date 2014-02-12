package it.seda.security.authentication;

import it.seda.security.domain.Account;
import it.seda.security.service.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * 
 * When a user is correctly authenticated the below listener will inform the user 
 * who for reset it’s authentication failure counters.
 * 
 * @author f.ricci
 *
 */
@Component("authenticationSuccessListener")
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent>{

	@Autowired private SecurityService securityService;

	public void onApplicationEvent(AuthenticationSuccessEvent event) {

		String username = event.getAuthentication().getName();

		Account account = securityService.getAccountByUserName(username);
		if (account!=null) {
			securityService.restoreAttempts(username);
		}

	}
}
