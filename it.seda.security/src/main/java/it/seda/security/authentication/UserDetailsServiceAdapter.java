/**
 * 
 */
package it.seda.security.authentication;

import it.seda.security.domain.Account;
import it.seda.security.service.SecurityService;

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
	
	@Autowired SecurityService securityService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Account account = securityService.getAccountByUserName(username);
		
		if (account==null) {
			//TODO NO I18N
			throw new UsernameNotFoundException("No such user: " + username);
		} else if (account.getAuthorities().isEmpty()) {
			//TODO NO I18N			
			throw new UsernameNotFoundException("User " + username + " has no authorities");
		}

		UserDetailsAdapter user = new UserDetailsAdapter(account);	
		user.setPassword(securityService.findPasswordByUsername(username));
		
		return user;
	}

}
