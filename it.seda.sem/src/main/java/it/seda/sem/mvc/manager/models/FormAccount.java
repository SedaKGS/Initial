package it.seda.sem.mvc.manager.models;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class FormAccount {
	
	@Pattern(regexp="^[A-Za-z0-9_-]{3,15}$",
			message="{formAccount.userName.pattern}")
	@NotEmpty(message="{formAccount.userName.notEmpty}")
	private String username;
	
	@NotEmpty(message="{formAccount.firstName.notEmpty}")
	private String firstName;
	
	@NotEmpty(message="{formAccount.lastName.notEmpty}")
	private String lastName;
	
	@Pattern(regexp="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
			message="{formAccount.email.pattern}")
	@NotEmpty(message="{formAccount.email.notEmpty}")
	private String email;
	
	@NotEmpty(message="{formAccount.groupName.notEmpty}")
	private String groupName;
	
	
	private boolean enabled;
	
	
	private String esito;
	
	
	
	public FormAccount() {
		super();
	}
	
	
	public String getEsito() {
		return esito;
	}


	public void setEsito(String esito) {
		this.esito = esito;
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
		return "FormAccount [username=" + username + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email
				+ ", groupName=" + groupName + ", enabled=" + enabled + "]";
	}
	
	
	

}
