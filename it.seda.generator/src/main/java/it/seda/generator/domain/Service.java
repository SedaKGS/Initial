package it.seda.generator.domain;

import it.seda.generator.util.GeneratorUtils;

public class Service {
    
	private String clazz;
	private String name;
	private String namespace;
	private String transactionManager;
	private Model model;
	private ModelMapper modelMapper;
	
	public Service(String clazz,String transactionManager) {
		this.clazz=clazz;
		this.transactionManager=transactionManager;
		this.name=GeneratorUtils.resolveClassName(clazz);
		this.namespace=GeneratorUtils.resolveClassNamespace(clazz);
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
	
	
	

}
