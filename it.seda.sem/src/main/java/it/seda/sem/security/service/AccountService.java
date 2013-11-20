/**
 * 
 */
package it.seda.sem.security.service;

import it.seda.sem.security.UserDetailsAdapter;
import it.seda.sem.security.domain.Account;
import it.seda.sem.security.domain.AccountTO;
import it.seda.sem.security.domain.Group;
import it.seda.sem.security.domain.GroupMember;
import it.seda.sem.security.domain.Signon;
import it.seda.sem.security.exceptions.DuplicateAccountException;
import it.seda.sem.security.persistence.AccountMapper;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
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
	
	public AccountTO getAccountTOByUserName(String username) {
		return accountMapper.getAccountTOByUsername(username);
	}

	public String findPasswordByUsername(String username) {
		return accountMapper.findPasswordByUsername(username);
	}

	/**
	 * 
	 * @param account
	 * @throws DuplicateAccountException in case of duplicate account username
	 */
	@Transactional("transactionSecurityManager")
	public void insertAccount(AccountTO account) {
		if (accountMapper.getAccountByUsername(account.getUsername())!=null) {
			throw new DuplicateAccountException(account.getUsername());
		}
		
		Signon signon = new Signon();
		signon.setUsername(account.getUsername());
		
		
		Account unAccount = AccountTO.createAccount(account);
		Object salt = saltSource.getSalt(new UserDetailsAdapter(unAccount));
		signon.setPassword(passwordEncoder.encodePassword(account.getUsername(),salt));
		
		GroupMember gm = new GroupMember();
		gm.setGroupName(account.getGroupName());
		gm.setUsername(account.getUsername());
		
		accountMapper.insertAccount(account);
		accountMapper.insertGroupMember(gm);
		accountMapper.insertSignon(signon);
		accountMapper.updateCredentialsExpiration(account.getUsername());
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
	
	@Transactional("transactionSecurityManager")
	public List<Group> groupsList(){
		List<Group> lg=accountMapper.groupsList();
		return lg;
	}
	
	@Transactional("transactionSecurityManager")
	public List<AccountTO> listaAccount(RowBounds rowBounds){
		List<AccountTO> ar=accountMapper.listAccount(rowBounds);
		return ar;
	}
	
	@Transactional("transactionSecurityManager")
	public int listAccountCount(){
		int rowsNumber=accountMapper.listAccountCount();
		return rowsNumber;
	}
	
	@Transactional("transactionSecurityManager")
	public void deleteAccount(String username){
		accountMapper.deleteAccount(username);
	}
	
	@Transactional("transactionSecurityManager")
	public void updateAccountTO(AccountTO ato){
		GroupMember gm = new GroupMember();
		gm.setGroupName(ato.getGroupName());
		gm.setUsername(ato.getUsername());
		accountMapper.updateAccountTO(ato);
		accountMapper.updateGroupMember(gm);
	}

}
