/**
 * 
 */
package it.seda.security.domain;

/**
 * @author f.ricci
 *
 */
@SuppressWarnings("serial")
public class MutableAccount extends AbstractAccount {

	protected String customerId;
	protected String groupName;	
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Override
	public String toString() {
		return "MutableAccount [customerId="+ customerId + ", username="
				+ username + ", firstName=" + firstName + ", lastName=" + lastName 
				+ ", email=" + email + ", groupName=" + groupName + ", enabled=" + enabled
				+ ", attempts=" + attempts + ", lastAttempt=" + lastAttempt 
				+ ", isLocked()=" + isLocked() + "]";
	}	
	
}
