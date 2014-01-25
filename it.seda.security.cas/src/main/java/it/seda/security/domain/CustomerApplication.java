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
public class CustomerApplication implements Serializable {

	private String customerId;
	private String applicationId;
	private String returnURL;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getReturnURL() {
		return returnURL;
	}
	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}
	@Override
	public String toString() {
		return "CustomerApplication [customerId=" + customerId
				+ ", applicationId=" + applicationId + ", returnURL="
				+ returnURL + "]";
	}
	
}
