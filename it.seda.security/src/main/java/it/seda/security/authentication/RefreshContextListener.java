package it.seda.security.authentication;

import it.seda.security.domain.Account;
import it.seda.security.domain.MutableAccount;
import it.seda.security.service.SecurityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired private SecurityService securityService; 
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Account account = securityService.getAccountByUserName(ADMIN_USER);
		
		if (account==null) {
			logger.warn("administrator account not found.... trying to create it..."); //TODO no i18n
			//final DateTime expiration = new DateTime(2099, 12, 31, 12, 0, 0, 0);
			//final DateTime credentialsExpiration = new DateTime(2099, 12, 30, 12, 0, 0, 0);
			MutableAccount accountto=new MutableAccount();
			accountto.setCustomerId("SEDA");
			accountto.setUsername(ADMIN_USER);
			accountto.setGroupName("administrators");
			accountto.setFirstName("administrator");
			accountto.setLastName("administrator");			
			accountto.setEmail("administrator@administrator.it");
			accountto.setEnabled(true);

			securityService.insertAccount(accountto);
			logger.warn("administrator account created..."); //TODO no i18n			
		} else {
			logger.warn("administrator account found"); //TODO no i18n
		}
		
	}

}
