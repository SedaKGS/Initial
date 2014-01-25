/**
 * 
 */
package it.seda.security.domain;

import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * 
 * @author f.ricci
 *
 */
@SuppressWarnings("serial")
public class Account extends AbstractAccount {

	// let us put max failed login attempts at 5
	// lo potremmo registrare anche su di un bean che carica le informazioni
	// da qualche parametrizzazione da qualche parte dell'universo
	public static final short MAX_FAILED_LOGIN_ATTEMPTS = 5;
 	
	private List<Authority> authorities;
 
	private Date registration;
	private Date expiration;
	private Date credentialsExpiration;
	
	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Authority> Authorities) {
		this.authorities = Authorities;
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
	
	
	@Override
	public String toString() {
		return "Account [username=" + username + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email
				+ ", authorities=" + authorities + ", enabled=" + enabled
				+ ", registration=" + registration + ", expiration="
				+ expiration + ", credentialsExpiration="
				+ credentialsExpiration + ", attempts=" + attempts
				+ ", lastAttempt=" + lastAttempt + ", isLocked()=" + isLocked() + "]";
	}
	
}
