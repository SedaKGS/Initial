package it.seda.generator.domain;

import it.seda.generator.ModelsContainer;
import it.seda.generator.Render;
import it.seda.generator.util.GeneratorUtils;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class RestWS implements Render{
	
	private String clazz;
	private String name;
	private String namespace;
	private Service service;
	private Model model;
	private Form form;
	private String baseUrl;
	private int pageNumber;
	private int rowsPerPage;
	static private String RESOURCE="/it/seda/generator/templates/RestWS.vm";
	private ModelsContainer container;

	public RestWS(String clazz,ModelsContainer container) {
		this.clazz=clazz+"ControllerWS";
		this.name=GeneratorUtils.resolveClassName(clazz)+"ControllerWS";
		this.namespace=GeneratorUtils.resolveClassNamespace(clazz);
		this.container=container;
	}
	
	public static String getRESOURCE() {
		return RESOURCE;
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
		return "ControllerWS [clazz=" + clazz + ", name=" + name + ", namespace="
				+ namespace + ", service=" + service + ", model=" + model
				+ ", form=" + form + ", pageNumber=" + pageNumber
				+ ", rowsPerPage=" + rowsPerPage + "]";
	}




	@Override
	public void render() {
		this.service=this.container.getService();
		this.form=this.container.getForm();
		this.model=this.container.getModel();
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(VelocityEngine.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init();
		VelocityContext context = new VelocityContext();
		context.put( "restWS", this);
		Template template = ve.getTemplate(this.RESOURCE);
		StringWriter sw = new StringWriter();
		template.merge( context, sw );
		GeneratorUtils.printToFile(this.namespace,this.name,"java",sw.toString());
		
	}

	
	
	
}