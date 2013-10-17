/**
 * 
 */
package it.seda.sem.security.service;

import javax.inject.Inject;

import it.seda.sem.security.domain.Account;
import it.seda.sem.security.domain.GroupMember;
import it.seda.sem.security.domain.Signon;
import it.seda.sem.security.persistence.AccountMapper;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	public Account getAccountByUserName(String username) {
		return accountMapper.getAccountByUsername(username);
	}

	public String findPasswordByUsername(String username) {
		return accountMapper.findPasswordByUsername(username);
	}

	@Transactional
	public void insertAccount(Account account) {
		Signon signon = new Signon();
		signon.setUsername(account.getUsername());
		signon.setPassword(((PasswordEncoder) passwordEncoder).encode(account.getUsername()));
		
		accountMapper.insertAccount(account);
		accountMapper.insertDefaultGroupMember(account.getUsername());
		accountMapper.insertSignon(signon);
	}
	
	@Transactional
	public void insertAdministrator(Account account) {
		Signon signon = new Signon();
		signon.setUsername(account.getUsername());
		signon.setPassword(((PasswordEncoder) passwordEncoder).encode(account.getUsername()));
		
		accountMapper.insertAccount(account);
		accountMapper.insertAdminGroupMember(account.getUsername());
		accountMapper.insertSignon(signon);
		accountMapper.updateAccount(account);
	}	
	
	@Transactional
	public void updateAccount(Account account) {
		accountMapper.updateAccount(account);
	}
	
	@Transactional
	public void updateSignon(Signon signon) {
		signon.setPassword(((PasswordEncoder) passwordEncoder).encode(signon.getUsername()));		
		accountMapper.updateSignon(signon);
	}	

}
