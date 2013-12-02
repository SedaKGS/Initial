package it.seda.generator.domain;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import it.seda.generator.ModelsContainer;
import it.seda.generator.Render;
import it.seda.generator.util.GeneratorUtils;

public class I18NMessages implements Render{
	
	private String clazzMes;
	private String nameMes;
	private String namespaceMes;
	private String clazzVal;
	private String nameVal;
	private String namespaceVal;
	private Form form;
	private Model model;
	static private String RESOURCEMESSAGES="/it/seda/generator/templates/Messages.vm";
	static private String RESOURCEVALIDATOR="/it/seda/generator/templates/Validator.vm";
    private ModelsContainer container; 
	
	public I18NMessages(String clazzMes,String clazzVal,ModelsContainer container) {
		this.clazzMes=clazzMes;
		this.nameMes=GeneratorUtils.resolveClassName(clazzMes);
		this.namespaceMes=GeneratorUtils.resolveClassNamespace(clazzMes);
		this.clazzVal=clazzVal;
		this.nameVal=GeneratorUtils.resolveClassName(clazzVal);
		this.namespaceVal=GeneratorUtils.resolveClassNamespace(clazzVal);
		this.container=container;
	}
    
	

	public static String getRESOURCEMESSAGES() {
		return RESOURCEMESSAGES;
	}
	
	public static String getRESOURCEVALIDATOR() {
		return RESOURCEVALIDATOR;
	}


	public String getClazzMes() {
		return clazzMes;
	}



	public void setClazzMes(String clazzMes) {
		this.clazzMes = clazzMes;
	}



	public String getNameMes() {
		return nameMes;
	}



	public void setNameMes(String nameMes) {
		this.nameMes = nameMes;
	}



	public String getNamespaceMes() {
		return namespaceMes;
	}



	public void setNamespaceMes(String namespaceMes) {
		this.namespaceMes = namespaceMes;
	}



	public String getClazzVal() {
		return clazzVal;
	}



	public void setClazzVal(String clazzVal) {
		this.clazzVal = clazzVal;
	}



	public String getNameVal() {
		return nameVal;
	}



	public void setNameVal(String nameVal) {
		this.nameVal = nameVal;
	}



	public String getNamespaceVal() {
		return namespaceVal;
	}



	public void setNamespaceVal(String namespaceVal) {
		this.namespaceVal = namespaceVal;
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



	@Override
	public void render() {
		this.model=this.container.getModel();
		this.form=this.container.getForm();
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(VelocityEngine.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init();
		VelocityContext context = new VelocityContext();
		context.put( "messages", this);
		Template template = ve.getTemplate(this.RESOURCEMESSAGES);
		StringWriter sw = new StringWriter();
		template.merge( context, sw );
		GeneratorUtils.printToFile(this.namespaceMes,this.nameMes+"Messages","properties",sw.toString());
		template = ve.getTemplate(this.RESOURCEVALIDATOR);
		sw = new StringWriter();
		template.merge( context, sw );
		GeneratorUtils.printToFile(this.namespaceVal,this.nameVal+"Validator","properties",sw.toString());
		
	}

}
