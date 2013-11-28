package it.seda.generator.domain;

import it.seda.generator.util.GeneratorUtils;

public class JSP {
	
	private String clazz;
	private String name;
	private String namespace;
	private Form form;
	private Controller controller;
	private Model model;

	public JSP(String clazz) {
		this.clazz=clazz;
		this.name=GeneratorUtils.resolveClassName(clazz);
		this.namespace=GeneratorUtils.resolveClassNamespace(clazz);
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
	
	
	

}
