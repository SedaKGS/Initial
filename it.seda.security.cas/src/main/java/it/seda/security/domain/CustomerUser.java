/**
 * 
 */
package it.seda.security.domain;

import java.io.Serializable;

/**
 * @author f.ricci
 *
 */
@SuppressWarnings("serial")
public class CustomerUser implements Serializable{
	
	private String customerId;
	private String username;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public CustomerUser() {
		super();
	}
	public CustomerUser(String customerId, String username) {
		super();
		this.customerId = customerId;
		this.username = username;
	}
	@Override
	public String toString() {
		return "CustomerUser [customerId=" + customerId + ", username="
				+ username + "]";
	}
	
}
