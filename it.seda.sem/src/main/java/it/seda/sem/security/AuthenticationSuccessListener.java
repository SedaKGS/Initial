package it.seda.sem.security;

import it.seda.sem.security.domain.Account;
import it.seda.sem.security.service.AccountService;

import javax.inject.Inject;

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

	  @Inject private AccountService accountService;

	  public void onApplicationEvent(AuthenticationSuccessEvent event) {

	    String username = event.getAuthentication().getName();
	    
	    Account account = accountService.getAccountByUserName(username);
	    if (account!=null) {
	    	accountService.restoreAttempts(username);
	    }

	  }
}
