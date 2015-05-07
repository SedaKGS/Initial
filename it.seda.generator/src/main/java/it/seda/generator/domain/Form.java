package it.seda.generator.domain;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import it.seda.generator.ModelsContainer;
import it.seda.generator.Render;
import it.seda.generator.util.GeneratorUtils;

public class Form implements Render {
	
	private String clazz;
	private String name;
	private String namespace;
	private Model model;
	List<Attribute> formAttributes;
	List<Attribute> listAttributes;
	Map<String,String> formListAttributes;
	static private String RESOURCE="/it/seda/generator/templates/Form.vm";
	private ModelsContainer container;
	
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

	public Form(String clazz,ModelsContainer container) {
		this.clazz=clazz+"Form";
		this.name=GeneratorUtils.resolveClassName(clazz)+"Form";
		this.namespace=GeneratorUtils.resolveClassNamespace(clazz);
		formAttributes=new ArrayList<Attribute>();
		listAttributes=new ArrayList<Attribute>();
		formListAttributes=new HashMap<String,String>();
		this.container=container;
	}

	
	public static String getRESOURCE() {
		return RESOURCE;
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
			else{
				this.listAttributes.add(attribute);
			}
			if(attribute.getGeneric()!=null&&attribute.getType().equals(List.class)) {
				Attribute attr=new Attribute(attribute.getName()+"List",attribute.getGeneric(),null);
				attr.setField(attribute.getField());
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
	
	public int getListCount(){
		if(formListAttributes!=null){
			return formListAttributes.size();
		}
		return 0;
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

	@Override
	public void render() {
		this.setModel(container.getModel());
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(VelocityEngine.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init();
		VelocityContext context = new VelocityContext();
		context.put( "form", this);
		Template template = ve.getTemplate(this.RESOURCE);
		StringWriter sw = new StringWriter();
		template.merge( context, sw );
		GeneratorUtils.printToFile(this.namespace,this.name,"java",sw.toString());
	}


	
    
	
	

}
