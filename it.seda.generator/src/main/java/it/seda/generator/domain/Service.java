package it.seda.generator.domain;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import it.seda.generator.ModelsContainer;
import it.seda.generator.Render;
import it.seda.generator.util.GeneratorUtils;

public class Service implements Render {
    
	private String clazz;
	private String name;
	private String namespace;
	private String transactionManager;
	private Model model;
	private ModelMapper modelMapper;
	static private String RESOURCE="/it/seda/generator/templates/Service.vm";
	private ModelsContainer container;
	
	public Service(String clazz,String transactionManager,ModelsContainer container) {
		this.clazz=clazz+"Service";
		this.transactionManager=transactionManager;
		this.name=GeneratorUtils.resolveClassName(clazz)+"Service";
		this.namespace=GeneratorUtils.resolveClassNamespace(clazz);
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



	public void setName(String name) {
		this.name = name;
	}



	public String getNamespace() {
		return namespace;
	}



	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}



	public String getTransactionManager() {
		return transactionManager;
	}



	public void setTransactionManager(String transactionManager) {
		this.transactionManager = transactionManager;
	}



	public String getLowerName(){
		return GeneratorUtils.lower(name);
	}
	
	
	public Service(Model model, ModelMapper modelMapper) {
		this.model = model;
		this.modelMapper = modelMapper;
	}



	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public ModelMapper getModelMapper() {
		return modelMapper;
	}

	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

    
	public String getMapperName(){
		return modelMapper.getMapperName();
	}
	
	public String getMapperLower(){
		return GeneratorUtils.lower(modelMapper.getMapperName());
	}
	
	public String getMapperUpper(){
		return modelMapper.getMapperUpperName();
	}
	
	public String getModelLower(){
		return GeneratorUtils.lower(model.getName());
	}
	
	public String getModelUpper(){
		return model.getUpperName();
	}
	

	@Override
	public String toString() {
		return "Service [model=" + model + ", modelMapper=" + modelMapper + "]";
	}


	@Override
	public void render() {
		this.model=this.container.getModel();
		this.modelMapper=this.container.getModelMapper();
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(VelocityEngine.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init();
		VelocityContext context = new VelocityContext();
		context.put( "service", this);
		Template template = ve.getTemplate(this.RESOURCE);
		StringWriter sw = new StringWriter();
		template.merge( context, sw );
		GeneratorUtils.printToFile(this.namespace,this.name,"java",sw.toString());
	}



	
	
	

}
