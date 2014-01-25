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
public class Authority implements Serializable{

	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Authority [name=" + name + "]";
	}
	
}
