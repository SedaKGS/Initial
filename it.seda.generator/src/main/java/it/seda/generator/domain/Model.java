/**
 * 
 */
package it.seda.generator.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author f.ricci
 *
 */
public class Model {

	
	private String clazz;
	private String name;
	private String namespace;
	
	private List<Attribute> attributes;
	
	public String getName() {
		return name;
	}
	public String getNamespace() {
		return namespace;
	}

	public String getFullName() {
		return clazz;
	}
	
	public void addAttribute(Attribute attribute) {
		attributes.add(attribute);
	}
	
	public void addAllAttribute(List<Attribute> attributes) {
		attributes.addAll(attributes);
	}

	public void addAllAttributeWithReplace(List<Attribute> attributes) {
		attributes.clear();
		addAllAttribute(attributes);
	}		
	
	public List<Attribute> getAttributes() {
		return attributes;
	}
	
	public Model (String clazz) {
		attributes=new ArrayList<Attribute>();
		this.clazz=clazz;
		resolveClass(clazz);
	}
	
	
	protected void resolveClass(String clazz) {
		int lastdot = clazz.lastIndexOf('.');
		if (lastdot>0) {
			name=clazz.substring(lastdot+1);
			namespace=clazz.substring(0,lastdot);
		} else {
			name=clazz;
			namespace="";
		}
	}
	
}
