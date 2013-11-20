package it.seda.sem.security.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AccountTO implements Serializable {
	
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String groupName;
	private boolean enabled;
	
	
	public static Account createAccount(AccountTO accountTO){
		Account account=new Account();
		account.setUsername(accountTO.username);
		return account;
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return "AccountTO [username=" + username + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email
				+ ", groupName=" + groupName + ", enabled=" + enabled + "]";
	}
	

	
}
