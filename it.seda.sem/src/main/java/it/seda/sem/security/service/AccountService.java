/**
 * 
 */
package it.seda.sem.security.service;

import it.seda.jdbc.commons.DataPage;
import it.seda.jdbc.commons.DefaultDataPage;
import it.seda.jdbc.ibatis.RowBoundsHelper;
import it.seda.sem.security.UserDetailsAdapter;
import it.seda.sem.security.domain.Account;
import it.seda.sem.security.domain.MutableAccount;
import it.seda.sem.security.domain.Group;
import it.seda.sem.security.domain.GroupMember;
import it.seda.sem.security.domain.Signon;
import it.seda.sem.security.exceptions.DuplicateAccountException;
import it.seda.sem.security.persistence.AccountMapper;

import java.util.List;

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

	@Transactional(value="transactionSecurityManager", readOnly=true)
	public Account getAccountByUserName(String username) {
		return accountMapper.getAccountByUsername(username);
	}

	@Transactional(value="transactionSecurityManager", readOnly=true)
	public MutableAccount getMutableAccountByUserName(String username) {
		return accountMapper.getMutableAccountByUsername(username);
	}

	@Transactional(value="transactionSecurityManager", readOnly=true)
	public String findPasswordByUsername(String username) {
		return accountMapper.findPasswordByUsername(username);
	}

	@Transactional("transactionSecurityManager")
	public void reportLoginFailure(String username) {
		accountMapper.loginFailure(username);
	}
	
	@Transactional("transactionSecurityManager")
	public void restoreAttempts(String username) {
		accountMapper.resetAttempts(username);
	}	
	/**
	 * 
	 * @param mutable
	 * @throws DuplicateAccountException in case of duplicate account username
	 */
	@Transactional("transactionSecurityManager")
	public void insertAccount(MutableAccount mutable) {
		if (accountMapper.getAccountByUsername(mutable.getUsername())!=null) {
			throw new DuplicateAccountException(mutable.getUsername());
		}

		Signon signon = new Signon();
		signon.setUsername(mutable.getUsername());

		Account unAccount = new Account();
		unAccount.setUsername(mutable.getUsername());
		Object salt = saltSource.getSalt(new UserDetailsAdapter(unAccount));
		signon.setPassword(passwordEncoder.encodePassword(mutable.getUsername(),salt));

		GroupMember gm = new GroupMember();
		gm.setGroupName(mutable.getGroupName());
		gm.setUsername(mutable.getUsername());

		accountMapper.insertAccount(mutable);
		accountMapper.insertGroupMember(gm);
		accountMapper.insertSignon(signon);
		accountMapper.updateCredentialsExpiration(mutable.getUsername());
	}

	@Transactional("transactionSecurityManager")
	public void updateSignon(Signon signon) {

		Account tempAccount =new Account();
		tempAccount.setUsername(signon.getUsername());
		Object salt = saltSource.getSalt(new UserDetailsAdapter(tempAccount));	
		signon.setPassword(passwordEncoder.encodePassword(signon.getPassword(),salt));

		accountMapper.updateSignon(signon);
		accountMapper.updateCredentialsExpiration(signon.getUsername());
	}	

	@Transactional(value="transactionSecurityManager", readOnly=true)
	public DataPage<MutableAccount> listaAccount(int pageNumber, int rowsPerPage){

		RowBoundsHelper rbh = new RowBoundsHelper(rowsPerPage, pageNumber);
		int totalrows=listAccountCount(); //per testare la propagazione della transazione su altri metodi

		List<MutableAccount> accountList=accountMapper.listAccount(rbh.buildRowBounds());
		DataPage<MutableAccount> accountPage= new DefaultDataPage<MutableAccount>(accountList);
		rbh.decorate(accountPage, totalrows);

		return accountPage;
	}

	@Transactional(value="transactionSecurityManager", readOnly=true)
	public int listAccountCount(){
		int rowsNumber=accountMapper.listAccountCount();
		return rowsNumber;
	}


	@Transactional(value="transactionSecurityManager", readOnly=true)
	public List<Group> groupsList(){
		List<Group> lg=accountMapper.groupsList();
		return lg;
	}

	@Transactional("transactionSecurityManager")
	public void deleteAccount(String username){
		accountMapper.deleteAccount(username);
	}

	@Transactional("transactionSecurityManager")
	public void updateAccount(MutableAccount ato){
		GroupMember gm = new GroupMember();
		gm.setGroupName(ato.getGroupName());
		gm.setUsername(ato.getUsername());
		accountMapper.updateAccount(ato);
		accountMapper.updateGroupMember(gm);
	}

}
