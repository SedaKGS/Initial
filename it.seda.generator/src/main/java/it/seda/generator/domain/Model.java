/**
 * 
 */
package it.seda.generator.domain;

import it.seda.generator.ModelsContainer;
import it.seda.generator.Render;
import it.seda.generator.util.GeneratorUtils;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * @author f.ricci
 *
 */
public class Model implements Render {
    
	private String INSERT=null;
	private String VALUES=null;
	private String WHERE=null;
	private String SET=null;
	private String ALL=null;
	private String ORDER=null;
	
	private String clazz;
	private String name;
	private String namespace;
	static private String RESOURCE="/it/seda/generator/templates/Model.vm";
	private ModelsContainer container;
	
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
	
	
	public static String getRESOURCE() {
		return RESOURCE;
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
	
	public Model (String clazz,ModelsContainer container) {
		attributes=new ArrayList<Attribute>();
		this.clazz=clazz;
		this.name=GeneratorUtils.resolveClassName(clazz);
		this.namespace=GeneratorUtils.resolveClassNamespace(clazz);
		this.container=container;
		
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
	
    public String getWhere(){
    	if(WHERE==null){
    	int count=0;
    	for (Attribute attribute : attributes) {
			if(attribute.getPk()>0&&attribute.isNotList()){
				count++;
			}
		}
    	int temp=1;
    	StringBuilder sb=new StringBuilder("WHERE ");
    	for (Attribute attribute : attributes) {
			if(attribute.getPk()>0&&attribute.isNotList()){
				if(temp<count){
				sb.append(attribute.getName());
				sb.append("= #{"+attribute.getName()+"} ");
				sb.append("AND ");
				temp++;
				}else{
				sb.append(attribute.getName());	
				sb.append("= #{"+attribute.getName()+"} ");
				}
			}
		}
    	this.WHERE=sb.toString();
    	}
    	return WHERE;
    }
    
    public String getSet(){
    	if(SET==null){
    	int count=0;
    	for (Attribute attribute : attributes) {
			if(attribute.getPk()==0&&attribute.isNotList()){
				count++;
			}
		}
    	int temp=1;
    	StringBuilder sb=new StringBuilder("SET ");
    	for (Attribute attribute : attributes) {
			if(attribute.getPk()==0&&attribute.isNotList()){
				if(temp<count){
				sb.append(attribute.getName());
				sb.append("= #{"+attribute.getName()+"} ");
				sb.append(", ");
				temp++;
				}else{
				sb.append(attribute.getName());	
				sb.append("= #{"+attribute.getName()+"} ");
				}
			}
		}
    	this.SET=sb.toString();
    	}
    	return SET;
    }
    
    
    public String getInsert(){
    	if(INSERT==null){
    	int count=0;
    	for (Attribute attribute : attributes) {
			if(attribute.getAuto()==0&&attribute.isNotList()){
				count++;
			}
		}
    	int temp=1;
    	StringBuilder sb=new StringBuilder("");
    	for (Attribute attribute : attributes) {
			if(attribute.getAuto()==0&&attribute.isNotList()){
				if(temp<count){
				sb.append(attribute.getName());
				//sb.append("= #{"+attribute.getName()+"} ");
				sb.append(", ");
				temp++;
				}else{
				sb.append(attribute.getName());	
				//sb.append("= #{"+attribute.getName()+"} ");
				}
			}
		}
    	this.SET=sb.toString();
    	}
    	return SET;
    }
    
    public String getValues(){
    	if(INSERT==null){
    	int count=0;
    	for (Attribute attribute : attributes) {
			if(attribute.getAuto()==0&&attribute.isNotList()){
				count++;
			}
		}
    	int temp=1;
    	StringBuilder sb=new StringBuilder("");
    	for (Attribute attribute : attributes) {
			if(attribute.getAuto()==0&&attribute.isNotList()){
				if(temp<count){
				//sb.append(attribute.getName());
				sb.append("#{"+attribute.getName()+"} ");
				sb.append(", ");
				temp++;
				}else{
				//sb.append(attribute.getName());	
				sb.append("#{"+attribute.getName()+"} ");
				}
			}
		}
    	this.SET=sb.toString();
    	}
    	return SET;
    }
    
    public String getAll(){
    	if(ALL==null){
    	int count=attributes.size();
    	StringBuilder sb=new StringBuilder("");
    	int temp=1;
    	for (Attribute attribute : attributes) {		
				if(temp<count){
				sb.append(attribute.getName());
				sb.append(", ");
				temp++;
				}else{
				sb.append(attribute.getName());	
				}
			
		}
    	this.ALL=sb.toString();
    	}
    	return ALL;
    }
	
    public String getOrder(){
    	if(ORDER==null){
    	int count=0;
    	for (Attribute attribute : attributes) {
			if(attribute.getPk()>0&&attribute.isNotList()){
				count++;
			}
		}
    	int temp=1;
    	StringBuilder sb=new StringBuilder("ORDER BY ");
    	for (Attribute attribute : attributes) {
			if(attribute.getPk()>0&&attribute.isNotList()){
				if(temp<count){
				sb.append(attribute.getName());
				sb.append(",");
				temp++;
				}else{
				sb.append(attribute.getName());	
				}
			}
		}
    	this.ORDER=sb.toString();
    	}
    	return ORDER;
    }


	@Override
	public void render() {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(VelocityEngine.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init();
		VelocityContext context = new VelocityContext();
		context.put( "model", this);
		Template template = ve.getTemplate(this.RESOURCE);
		StringWriter sw = new StringWriter();
		template.merge( context, sw );
		GeneratorUtils.printToFile(this.namespace,this.name,"java",sw.toString());
	}
}
