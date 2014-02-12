/**
 * 
 */
package it.seda.security.authentication;

import it.seda.security.domain.Account;
import it.seda.security.domain.Authority;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author f.ricci
 *
 */
@SuppressWarnings("serial")
public class UserDetailsAdapter implements UserDetails {

	private Account account;
	private String password;

	public String getFirstName() {
		return account.getFirstName(); 
	}
	public String getLastName() {
		return account.getLastName();
	}
	public String getEmail() {
		return account.getEmail();
	}
	public Date getRegistration() {
		return account.getRegistration();
	}
	public Date getExpiration() {
		return account.getExpiration();
	}
	public Date getCredentialsExpiration() {
		return account.getCredentialsExpiration();
	}
	
	public short getAttempts() {
		return account.getAttempts();
	}
	
	public UserDetailsAdapter(Account account) {
		this.account=account;
	}
	
	public UserDetailsAdapter() {
		super();
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities =
				new HashSet<GrantedAuthority>();
		for (Authority role : account.getAuthorities()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return !account.isExpired(new Date());
	}

	@Override
	public boolean isAccountNonLocked() {
		return !account.isLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !account.isCredentialsExpired(new Date());
	}

	@Override
	public boolean isEnabled() {
		return account.isEnabled();
	}

}
