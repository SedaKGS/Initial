package it.seda.generator.domain;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import it.seda.generator.ModelsContainer;
import it.seda.generator.Render;
import it.seda.generator.util.GeneratorUtils;



public class ModelMapper implements Render {
	
	private Model  model;
	private String clazz;
	private String mapperName;
	private String mapperNamespace;
	private String annotationName;
	private String annotationNamesapce;
	private String annotationClazz;
	static private String RESOURCE="/it/seda/generator/templates/ModelMapper.vm";
	private ModelsContainer container;

	public ModelMapper() {
		
	}
    
	public ModelMapper (String clazz,ModelsContainer container) {
		this.clazz=clazz+"Mapper";
		this.mapperName=GeneratorUtils.resolveClassName(clazz)+"Mapper";
		this.mapperNamespace=GeneratorUtils.resolveClassNamespace(clazz);
		this.container=container;
	}
	
	

	public static String getRESOURCE() {
		return RESOURCE;
	}

	

	public Model getModel() {
		return model;
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

	public String getMapperName() {
		return mapperName;
	}
	
	public String getMapperUpperName() {
		return GeneratorUtils.capitalize(mapperName);
	}
	
	public String getMapperLowerName() {
		return GeneratorUtils.lower(mapperName);
	}

	public void setMapperName(String mapperName) {
		this.mapperName = mapperName;
	}

	public String getMapperNamespace() {
		return mapperNamespace;
	}

	public void setMapperNamespace(String mapperNamespace) {
		this.mapperNamespace = mapperNamespace;
	}

	public String getAnnotationName() {
		return annotationName;
	}

	public void setAnnotationName(String annotationName) {
		this.annotationName = annotationName;
	}

	public String getAnnotationNamesapce() {
		return annotationNamesapce;
	}

	public void setAnnotationNamesapce(String annotationNamesapce) {
		this.annotationNamesapce = annotationNamesapce;
	}
	
	

	public String getAnnotationClazz() {
		return annotationClazz;
	}

	public void setAnnotationClazz(String annotationClazz) {
		this.annotationClazz = annotationClazz;
	}

	public void setAnnotation(String clazz){
		this.annotationName=GeneratorUtils.resolveClassName(clazz);
		this.annotationNamesapce=GeneratorUtils.resolveClassNamespace(clazz);
		this.annotationClazz=clazz;
	}

	@Override
	public String toString() {
		return "ModelMapper [model=" + model + ", clazz=" + clazz
				+ ", mapperName=" + mapperName + ", mapperNamespace="
				+ mapperNamespace + ", annotationName=" + annotationName
				+ ", annotationNamesapce=" + annotationNamesapce + "]";
	}

	@Override
	public void render() {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(VelocityEngine.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init();
		VelocityContext context = new VelocityContext();
		context.put( "mapper", this);
		Template template = ve.getTemplate(this.RESOURCE);
		StringWriter sw = new StringWriter();
		template.merge( context, sw );
		GeneratorUtils.printToFile(this.mapperNamespace,this.mapperName,"java",sw.toString());	
	}
	
	
}
