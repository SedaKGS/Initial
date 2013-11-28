package it.seda.generator.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.seda.generator.util.GeneratorUtils;

public class Form {
	
	private String clazz;
	private String name;
	private String namespace;
	private Model model;
	List<Attribute> formAttributes;
	List<Attribute> listAttributes;
	Map<String,String> formListAttributes;
	
	Comparator<Attribute> comparator=new Comparator<Attribute>(){

		@Override
		public int compare(Attribute atl, Attribute atr) {
			
			if(atl.getForm()<atr.getForm()){
			return -1;
			}
			if(atl.getForm()>atr.getForm()){
				return 1;
			}
			return 0;
		}

		
		
	};

	public Form(String clazz) {
		this.clazz=clazz;
		this.name=GeneratorUtils.resolveClassName(clazz);
		this.namespace=GeneratorUtils.resolveClassNamespace(clazz);
		formAttributes=new ArrayList<Attribute>();
		listAttributes=new ArrayList<Attribute>();
		formListAttributes=new HashMap<String,String>();
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getName() {
		return name;
	}
	
	public String getLowerName(){
		return GeneratorUtils.lower(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
		List<Attribute> modelAttributes=model.getAttributes();
		Collections.sort(modelAttributes, getComparator());
		for (Attribute attribute : modelAttributes) {
			if(attribute.getForm()>0) {
				formAttributes.add(attribute);
			}
			if(attribute.getGeneric()!=null&&attribute.getType().equals(List.class)) {
				Attribute attr=new Attribute(attribute.getName()+"List",attribute.getGeneric(),null);
				listAttributes.add(attr);
				formListAttributes.put(attribute.getName(), attribute.getName()+"List");
			}
		}
		
	}
	
	public String getListAddedName(String listName){
		String name="";
		if(listName!=null){
			name=formListAttributes.get(listName);
		}
		return name;
	}
	
	public String getModelName(){
		return model.getName();
	}
	
	
	public List<Attribute> getFormAttributes() {
		return formAttributes;
	}

	public void setFormAttributes(List<Attribute> formAttributes) {
		this.formAttributes = formAttributes;
	}

	private Comparator<Attribute> getComparator(){
		return this.comparator;
		
	}

	public List<Attribute> getListAttributes() {
		return listAttributes;
	}

	public void setListAttributes(List<Attribute> listAttributes) {
		this.listAttributes = listAttributes;
	}
    
	
	

}
