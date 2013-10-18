/**
 * 
 */
package it.seda.sem.security.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author f.ricci
 *
 */
public class Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private List<Authority> authorities;
	private boolean enabled;
 
	private Date registration;
	private Date expiration;
	private Date credentialsExpiration;
	
	private boolean locked;
	private short attempts;
	
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
	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Authority> Authorities) {
		this.authorities = Authorities;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Date getRegistration() {
		return registration;
	}
	public void setRegistration(Date registration) {
		this.registration = registration;
	}
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	public boolean isExpired(Date date) {
		return expiration.before(date);
	}
	public Date getCredentialsExpiration() {
		return credentialsExpiration;
	}
	public void setCredentialsExpiration(Date credentialsExpiration) {
		this.credentialsExpiration = credentialsExpiration;
	}
	public boolean isCredentialsExpired(Date date) {
		return credentialsExpiration.before(date);
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public short getAttempts() {
		return attempts;
	}
	public void setAttempts(short attempts) {
		this.attempts = attempts;
	}
	
	@Override
	public String toString() {
		return "Account [username=" + username + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", roles="
				+ authorities + ", enabled=" + enabled + ", registration="
				+ registration + ", expiration=" + expiration + "]";
	}
	
}
