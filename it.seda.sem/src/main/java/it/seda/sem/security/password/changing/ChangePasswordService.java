package it.seda.sem.security.password.changing;

import it.seda.sem.security.domain.Account;
import it.seda.sem.security.domain.Signon;
import it.seda.sem.security.service.AccountService;

import javax.inject.Inject;


import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

@Component("passwordHandler")
public class ChangePasswordService implements PasswordHandler {
	
	@Inject AccountService accountService;
	@Inject private ShaPasswordEncoder passwordEncoder;
	
	@Override
	public boolean changePassword(FormChangePassword formChangePassword) {
		Signon newSignon=new Signon();;
		//dati passati
		String username=formChangePassword.getUsername();
		String oldPassword=passwordEncoder.encodePassword(formChangePassword.getOldPassword(),username);
		String confirm=passwordEncoder.encodePassword(formChangePassword.getConfirm(),username);
		//dati registrati
		Account account = accountService.getAccountByUserName(username);
		String encryptedPassword=accountService.findPasswordByUsername(username);
		if(oldPassword.equals(confirm)&&oldPassword.equals(encryptedPassword)&&username.equals(account.getUsername())){
			newSignon.setPassword(formChangePassword.getNewPassword());
			newSignon.setUsername(formChangePassword.getUsername());
			accountService.updateSignon(newSignon);
			return true;
		}
			
		
		return false;
	}

}
