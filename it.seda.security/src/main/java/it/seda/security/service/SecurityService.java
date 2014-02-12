/**
 * 
 */
package it.seda.security.service;

import it.seda.jdbc.commons.DataPage;
import it.seda.jdbc.commons.DefaultDataPage;
import it.seda.jdbc.ibatis.RowBoundsHelper;
import it.seda.security.authentication.UserDetailsAdapter;
import it.seda.security.domain.Account;
import it.seda.security.domain.CustomerUser;
import it.seda.security.domain.MutableAccount;
import it.seda.security.domain.Group;
import it.seda.security.domain.GroupMember;
import it.seda.security.domain.Signon;
import it.seda.security.service.exceptions.DuplicateAccountException;
import it.seda.security.persistence.ManagerMapper;
import it.seda.security.persistence.SecurityMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author f.ricci
 *
 */
@Service
@Transactional
public class SecurityService {

	@Autowired private SecurityMapper securityMapper;
	@Autowired private ManagerMapper managerMapper;
	@Autowired private ShaPasswordEncoder passwordEncoder;
	@Autowired private SaltSource saltSource;

	@Transactional(readOnly=true)
	public Account getAccountByUserName(String username) {
		return securityMapper.getAccountByUsername(username);
	}

	@Transactional(readOnly=true)
	public MutableAccount getMutableAccountByUserName(String username) {
		return securityMapper.getMutableAccountByUsername(username);
	}

	@Transactional(readOnly=true)
	public String findPasswordByUsername(String username) {
		return securityMapper.findPasswordByUsername(username);
	}

	public void reportLoginFailure(String username) {
		securityMapper.loginFailure(username);
	}
	
	public void restoreAttempts(String username) {
		securityMapper.resetAttempts(username);
	}	

	public void insertAccount(MutableAccount mutable) {
		if (securityMapper.getAccountByUsername(mutable.getUsername())!=null) {
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

		CustomerUser cu = new CustomerUser(mutable.getCustomerId(),mutable.getUsername());
		
		securityMapper.insertAccount(mutable);
		managerMapper.insertCustomerUser(cu);
		securityMapper.insertGroupMember(gm);
		securityMapper.insertSignon(signon);
		securityMapper.updateCredentialsExpiration(mutable.getUsername());
	}

	public void updateSignon(Signon signon) {

		Account tempAccount =new Account();
		tempAccount.setUsername(signon.getUsername());
		Object salt = saltSource.getSalt(new UserDetailsAdapter(tempAccount));	
		signon.setPassword(passwordEncoder.encodePassword(signon.getPassword(),salt));

		securityMapper.updateSignon(signon);
		securityMapper.updateCredentialsExpiration(signon.getUsername());
	}	

	@Transactional(readOnly=true)
	public DataPage<MutableAccount> listaAccount(String customerId, int pageNumber, int rowsPerPage){

		RowBoundsHelper rbh = new RowBoundsHelper(rowsPerPage, pageNumber);
		int totalrows=listAccountCount(customerId); //per testare la propagazione della transazione su altri metodi

		List<MutableAccount> accountList=securityMapper.listAccount(customerId, rbh.buildRowBounds());
		DataPage<MutableAccount> accountPage= new DefaultDataPage<MutableAccount>(accountList);
		rbh.decorate(accountPage, totalrows);

		return accountPage;
	}

	@Transactional(readOnly=true)
	public int listAccountCount(String customerId){
		int rowsNumber=securityMapper.listAccountCount(customerId);
		return rowsNumber;
	}


	@Transactional(readOnly=true)
	public List<Group> groupsList(){
		List<Group> lg=securityMapper.groupsList();
		return lg;
	}

	public void deleteAccount(String username){
		securityMapper.deleteAccount(username);
	}

	public void updateAccount(MutableAccount ato){
		GroupMember gm = new GroupMember();
		gm.setGroupName(ato.getGroupName());
		gm.setUsername(ato.getUsername());
		securityMapper.updateAccount(ato);
		securityMapper.updateGroupMember(gm);
	}

}
