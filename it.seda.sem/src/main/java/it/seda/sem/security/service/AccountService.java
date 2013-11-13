/**
 * 
 */
package it.seda.sem.security.service;

import it.seda.sem.security.UserDetailsAdapter;
import it.seda.sem.security.domain.Account;
import it.seda.sem.security.domain.Signon;
import it.seda.sem.security.persistence.AccountMapper;

import javax.inject.Inject;

import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author f.ricci
 *
 */
@Service
public class AccountService {

	@Inject private AccountMapper accountMapper;
	@Inject private ShaPasswordEncoder passwordEncoder;
	@Inject private SaltSource saltSource;

	public Account getAccountByUserName(String username) {
		return accountMapper.getAccountByUsername(username);
	}

	public String findPasswordByUsername(String username) {
		return accountMapper.findPasswordByUsername(username);
	}

	@Transactional("transactionSecurityManager")
	public void insertAccount(Account account) {
		Signon signon = new Signon();
		signon.setUsername(account.getUsername());
		
		
		Object salt = saltSource.getSalt(new UserDetailsAdapter(account));
		signon.setPassword(passwordEncoder.encodePassword(account.getUsername(),salt));
		
		accountMapper.insertAccount(account);
		accountMapper.insertDefaultGroupMember(account.getUsername());
		accountMapper.insertSignon(signon);
	}
	
	@Transactional("transactionSecurityManager")
	public void insertAdministrator(Account account) {
		Signon signon = new Signon();
		signon.setUsername(account.getUsername());
			
		Object salt = saltSource.getSalt(new UserDetailsAdapter(account));
		signon.setPassword(passwordEncoder.encodePassword(account.getUsername(),salt));	
		
		accountMapper.insertAccount(account);
		accountMapper.insertAdminGroupMember(account.getUsername());
		accountMapper.insertSignon(signon);
		accountMapper.updateAccount(account);
	}	
	
	@Transactional("transactionSecurityManager")
	public void updateAccount(Account account) {
		accountMapper.updateAccount(account);
	}
	
	@Transactional("transactionSecurityManager")
	public void updateSignon(Signon signon) {
		
		Account tempAccount =new Account();
		tempAccount.setUsername(signon.getUsername());
		Object salt = saltSource.getSalt(new UserDetailsAdapter(tempAccount));	
		signon.setPassword(passwordEncoder.encodePassword(signon.getPassword(),salt));
		
		accountMapper.updateSignon(signon);
	}	

}
