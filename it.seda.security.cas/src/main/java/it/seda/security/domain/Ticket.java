/**
 * 
 */
package it.seda.security.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author f.ricci
 *
 */
@SuppressWarnings("serial")
public class Ticket implements Serializable {

	private String id;
	private String username;
	private String applicationId;
	private Date creationDate;
	private Date expirationDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public boolean isExpired(Date when) {
		return expirationDate.before(when);
	}
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", username=" + username
				+ ", applicationId=" + applicationId + ", creationDate="
				+ creationDate + ", expirationDate=" + expirationDate 
				+ ", isExpired()=" + isExpired(new Date()) + "]";
	}
	
}
