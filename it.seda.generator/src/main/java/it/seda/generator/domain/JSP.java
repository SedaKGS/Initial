package it.seda.generator.domain;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import it.seda.generator.ModelsContainer;
import it.seda.generator.Render;
import it.seda.generator.util.GeneratorUtils;

public class JSP implements Render{
	
	private String clazz;
	private String name;
	private String namespace;
	private Form form;
	private Controller controller;
	private Model model;
	static private String RESOURCE="/it/seda/generator/templates/JSP.vm";
	private ModelsContainer container;

	public JSP(String clazz,ModelsContainer container) {
		this.clazz=clazz;
		this.name=GeneratorUtils.resolveClassName(clazz);
		this.namespace=GeneratorUtils.resolveClassNamespace(clazz);
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



	public Controller getController() {
		return controller;
	}



	public void setController(Controller controller) {
		this.controller = controller;
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

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}



	@Override
	public void render() {
		this.form=this.container.getForm();
		this.controller=this.container.getController();
		this.model=this.container.getModel();
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(VelocityEngine.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init();
		VelocityContext context = new VelocityContext();
		context.put( "jsp", this);
		Template template = ve.getTemplate(this.RESOURCE);
		StringWriter sw = new StringWriter();
		template.merge( context, sw );
		GeneratorUtils.printToFile(this.namespace,this.name,"jsp",sw.toString());
	}

}
