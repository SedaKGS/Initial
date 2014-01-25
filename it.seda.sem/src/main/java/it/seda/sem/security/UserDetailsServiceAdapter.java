/**
 * 
 */
package it.seda.sem.security;

import it.seda.sem.security.domain.Account;
import it.seda.sem.security.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author f.ricci
 *
 */
@Service(value="userDetailsService")
public class UserDetailsServiceAdapter implements UserDetailsService {
	
	@Autowired AccountService accountService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Account account = accountService.getAccountByUserName(username);
		
		if (account==null) {
			//TODO NO I18N
			throw new UsernameNotFoundException("No such user: " + username);
		} else if (account.getAuthorities().isEmpty()) {
			//TODO NO I18N			
			throw new UsernameNotFoundException("User " + username + " has no authorities");
		}

		UserDetailsAdapter user = new UserDetailsAdapter(account);	
		user.setPassword(accountService.findPasswordByUsername(username));
		
		return user;
	}

}
