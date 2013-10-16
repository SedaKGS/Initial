/**
 * 
 */
package it.seda.sem.security.domain;

/**
 * @author f.ricci
 *
 */
public class Authority {

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
