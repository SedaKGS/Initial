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
public class Customer implements Serializable {

	private String id;
	private String name;
	private String code;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String customerName) {
		this.name = customerName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String customerCode) {
		this.code = customerCode;
	}
	public Customer() {
		super();
	}
	public Customer(String id, String customerName, String customerCode) {
		super();
		this.id = id;
		this.name = customerName;
		this.code = customerCode;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name
				+ ", code=" + code + "]";
	}
	
}
