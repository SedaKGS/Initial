package it.seda.generator.domain;

import it.seda.generator.util.GeneratorUtils;



public class ModelMapper {
	
	private Model  model;
	private String clazz;
	private String mapperName;
	private String mapperNamespace;
	private String annotationName;
	private String annotationNamesapce;
	private String annotationClazz;
	

	public ModelMapper() {
		
	}
    
	public ModelMapper (String clazz) {
		this.clazz=clazz;
		this.mapperName=GeneratorUtils.resolveClassName(clazz);
		this.mapperNamespace=GeneratorUtils.resolveClassNamespace(clazz);
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
	
	
}
