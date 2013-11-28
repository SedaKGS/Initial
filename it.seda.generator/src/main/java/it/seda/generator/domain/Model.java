/**
 * 
 */
package it.seda.generator.domain;

import it.seda.generator.util.GeneratorUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	private Comparator<Attribute> comparator=new Comparator<Attribute>(){

		@Override
		public int compare(Attribute atl, Attribute atr) {
			
			if(atl.getPk()<atr.getPk()){
			return -1;
			}
			if(atl.getPk()>atr.getPk()){
				return 1;
			}
			return 0;
		}

		
		
	};
	
	public String getName() {
		return name;
	}
	
	
	public String getNamespace() {
		return namespace;
	}

	public String getFullName() {
		return clazz;
	}
	
	public String getClazz() {
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
		Collections.sort(attributes,getComparator());
		return attributes;
	}
	
	public Model (String clazz) {
		attributes=new ArrayList<Attribute>();
		this.clazz=clazz;
		this.name=GeneratorUtils.resolveClassName(clazz);
		this.namespace=GeneratorUtils.resolveClassNamespace(clazz);
		
	}
	
	public String getUpperName() {
		return GeneratorUtils.capitalize(name);
	}
	
	public String getLowerName() {
		return GeneratorUtils.lower(name);
	}
	
	private Comparator<Attribute> getComparator(){
		return this.comparator;
	}
	
	
	
}
