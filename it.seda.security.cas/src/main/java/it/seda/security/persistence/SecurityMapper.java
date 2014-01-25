package it.seda.security.persistence;

import it.seda.security.domain.Account;
import it.seda.security.domain.Group;
import it.seda.security.domain.GroupMember;
import it.seda.security.domain.MutableAccount;
import it.seda.security.domain.Signon;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;


@Repository
public interface SecurityMapper {

	Account getAccountByUsername(String username);
	MutableAccount getMutableAccountByUsername(String username);
	String findPasswordByUsername(String username);

	void insertAccount(MutableAccount account);
	void updateAccount(MutableAccount account);
	void deleteAccount(String username);
	void loginFailure(String username);	
	void resetAttempts(String username);
	
	void insertDefaultGroupMember(String username);
	void insertAdminGroupMember(String username);
	
	void deleteGroupMember(GroupMember groupMember);	
	void insertGroupMember(GroupMember groupMember);	
	void updateGroupMember(GroupMember groupMember);
	
	void updateCredentialsExpiration(String username);
	
	void insertSignon(Signon signon);
	void updateSignon(Signon signon);
	
	List<Group> groupsList();
	
	List<MutableAccount> listAccount(String customerId, RowBounds rowBounds);
	int listAccountCount(String customerId);

}
