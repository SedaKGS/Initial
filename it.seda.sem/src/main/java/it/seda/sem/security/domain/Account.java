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
@SuppressWarnings("serial")
public class Account implements Serializable {

	// let us put max failed login attempts at 5
	// lo ptremmo registrare anche su di un bean che carica le informazioni
	// da qualche parametrizzazione da qualche parte dell'universo
	public static final short MAX_FAILED_LOGIN_ATTEMPTS = 5;
 	
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private List<Authority> authorities;
	private boolean enabled;
 
	private Date registration;
	private Date expiration;
	private Date credentialsExpiration;
	
	private short attempts;
	private Date lastAttempt;
	
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
		return attempts>=Account.MAX_FAILED_LOGIN_ATTEMPTS;
	}
	public short getAttempts() {
		return attempts;
	}
	public void setAttempts(short attempts) {
		this.attempts = attempts;
	}
	public Date getLastAttempt() {
		return lastAttempt;
	}
	public void setLastAttempt(Date lastAttempt) {
		this.lastAttempt = lastAttempt;
	}
	
	@Override
	public String toString() {
		return "Account [username=" + username + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email
				+ ", authorities=" + authorities + ", enabled=" + enabled
				+ ", registration=" + registration + ", expiration="
				+ expiration + ", credentialsExpiration="
				+ credentialsExpiration + ", attempts=" + attempts
				+ ", lastAttempt=" + lastAttempt + "]";
	}
	
}
