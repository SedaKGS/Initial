package it.seda.sem.security.persistence;

import it.seda.sem.security.annotations.SecurityRepository;
import it.seda.sem.security.domain.Account;
import it.seda.sem.security.domain.GroupMember;
import it.seda.sem.security.domain.Signon;


@SecurityRepository
public interface AccountMapper {

	Account getAccountByUsername(String username);
	String findPasswordByUsername(String username);

	void insertAccount(Account account);
	
	void insertDefaultGroupMember(String username);
	void insertAdminGroupMember(String username);
	void deleteGroupMember(GroupMember groupMember);	
	void insertGroupMember(GroupMember groupMember);	
	
	void updateAccount(Account account);	
	
	void insertSignon(Signon signon);
	void updateSignon(Signon signon);

}
