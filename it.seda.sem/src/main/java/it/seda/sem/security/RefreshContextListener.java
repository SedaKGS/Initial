package it.seda.sem.security;

import it.seda.sem.mvc.security.LoginController;
import it.seda.sem.security.domain.Account;
import it.seda.sem.security.service.AccountService;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 
 * @author f.ricci
 *
 */
@Component("securityRefreshContextListener")
public class RefreshContextListener implements ApplicationListener<ContextRefreshedEvent> {
	
	private Logger logger = LoggerFactory.getLogger(RefreshContextListener.class);	
	
	public final static String ADMIN_USER="admin";
	
	@Inject AccountService accountService; 
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Account account = accountService.getAccountByUserName(ADMIN_USER);
		
		if (account==null) {
			logger.warn("adinitrator account not found.... trying to create it..."); //TODO no i18n
			final DateTime expiration = new DateTime(2099, 12, 31, 12, 0, 0, 0);
			final DateTime credentialsExpiration = new DateTime(2099, 12, 30, 12, 0, 0, 0);
			account=new Account();
			account.setUsername(ADMIN_USER);
			account.setFirstName("administrator");
			account.setLastName("administrator");
			account.setEnabled(true);
			account.setLocked(false);			
			
			account.setExpiration(expiration.toDate());
			account.setCredentialsExpiration(credentialsExpiration.toDate());

			accountService.insertAdministrator(account);
			logger.warn("adinitrator account created..."); //TODO no i18n			
		} else {
			logger.warn("adinitrator account found"); //TODO no i18n
		}
		
	}

}
