package it.seda.sem.security.persistence;

import it.seda.sem.security.annotations.SecurityRepository;
import it.seda.sem.security.domain.Account;
import it.seda.sem.security.domain.AccountTO;
import it.seda.sem.security.domain.Group;
import it.seda.sem.security.domain.GroupMember;
import it.seda.sem.security.domain.Signon;

import java.util.List;

import org.apache.ibatis.session.RowBounds;


@SecurityRepository
public interface AccountMapper {

	Account getAccountByUsername(String username);
	AccountTO getAccountTOByUsername(String username);
	String findPasswordByUsername(String username);

	void insertAccount(AccountTO account);
	void updateAccountTO(AccountTO account);
	void deleteAccount(String username);
	
	void insertDefaultGroupMember(String username);
	void insertAdminGroupMember(String username);
	
	void deleteGroupMember(GroupMember groupMember);	
	void insertGroupMember(GroupMember groupMember);	
	void updateGroupMember(GroupMember groupMember);
	
	void updateAccount(Account account);	
	void updateCredentialsExpiration(String username);
	
	void insertSignon(Signon signon);
	void updateSignon(Signon signon);
	
	List<Group> groupsList();
	
	List<AccountTO> listAccount(RowBounds rowBounds);
	int listAccountCount();

}
