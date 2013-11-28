package it.seda.generator.domain;

import it.seda.generator.util.GeneratorUtils;

public class Controller {
	
	private String clazz;
	private String name;
	private String namespace;
	private Service service;
	private Model model;
	private Form form;
	private String baseUrl;
	private int pageNumber;
	private int rowsPerPage;
	

	public Controller(String clazz) {
		this.clazz=clazz;
		this.name=GeneratorUtils.resolveClassName(clazz);
		this.namespace=GeneratorUtils.resolveClassNamespace(clazz);
	}
	
	
	
    
	public String getBaseUrl() {
		return baseUrl;
	}




	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}




	public String serviceUpper(){
		return service.getName();
	}
	
	public String serviceLower(){
		return GeneratorUtils.lower(service.getName());
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


	public Service getService() {
		return service;
	}


	public void setService(Service service) {
		this.service = service;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	@Override
	public String toString() {
		return "Controller [clazz=" + clazz + ", name=" + name + ", namespace="
				+ namespace + ", service=" + service + ", model=" + model
				+ ", form=" + form + ", pageNumber=" + pageNumber
				+ ", rowsPerPage=" + rowsPerPage + "]";
	}

	
	
	
}
