package it.seda.generator.domain;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.seda.generator.ModelsContainer;
import it.seda.generator.Render;
import it.seda.generator.XMLGeneratorParser;
import it.seda.generator.util.GeneratorUtils;

public class MyBatisMapper implements Render {
	
	private Model model;
	private String clazz;
	private String name;
	private String namespace;
	static private String RESOURCE="/it/seda/generator/templates/MyBatisMapper.vm";
	private ModelsContainer container;
	private Logger logger = LoggerFactory.getLogger(MyBatisMapper.class);

	public MyBatisMapper(String clazz,ModelsContainer container) {
		this.clazz=clazz+"Mapper";
		this.name=GeneratorUtils.resolveClassName(clazz)+"Mapper";
		this.namespace=GeneratorUtils.resolveClassNamespace(clazz);
		this.container=container;
	}

	public Model getModel() {
		return model;
	}
	
	
	
    
	public static String getRESOURCE() {
		return RESOURCE;
	}

	public void setModel(Model model) {
		this.model = model;
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
	
	public String getTable() {
		return model.getTable().toUpperCase();
	}


	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	public String getModelMapperName(){
		String myBatisMapperName=GeneratorUtils.capitalize(this.name);
		String modelMapperName=this.container.getModelMapper().getMapperUpperName();
		if(!name.equals(modelMapperName)){
		logger.info("MyBatis mapper name "+myBatisMapperName+" non coincide con "+modelMapperName+"verr� utilizzato "+modelMapperName);
		return modelMapperName;
		}
		return myBatisMapperName;
	}


	@Override
	public void render() {
		this.model=this.container.getModel();
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(VelocityEngine.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init();
		VelocityContext context = new VelocityContext();
		context.put( "myBatisMapper", this);
		Template template = ve.getTemplate(this.RESOURCE);
		StringWriter sw = new StringWriter();
		template.merge( context, sw );
		GeneratorUtils.printToFile(this.namespace,this.name,"xml",sw.toString());
	}

	
	
	
	

}
