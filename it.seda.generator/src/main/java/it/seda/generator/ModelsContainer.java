package it.seda.generator;

import it.seda.generator.domain.Controller;
import it.seda.generator.domain.Form;
import it.seda.generator.domain.I18NMessages;
import it.seda.generator.domain.JSP;
import it.seda.generator.domain.Model;
import it.seda.generator.domain.ModelMapper;
import it.seda.generator.domain.MyBatisMapper;
import it.seda.generator.domain.Service;

import java.util.ArrayList;
import java.util.List;

public class ModelsContainer {
	
	private Model model;
	private ModelMapper modelMapper;
	private Service service;
	private Controller controller;
	private Form form;
	private I18NMessages I18NMessages;
	private JSP jsp;
	private MyBatisMapper myBatisMapper;
    
	
	
	public MyBatisMapper getMyBatisMapper() {
		return myBatisMapper;
	}

	public void setMyBatisMapper(MyBatisMapper myBatisMapper) {
		this.myBatisMapper = myBatisMapper;
	}

	public JSP getJsp() {
		return jsp;
	}

	public void setJsp(JSP jsp) {
		this.jsp = jsp;
	}

	public I18NMessages getI18NMessages() {
		return I18NMessages;
	}

	public void setI18NMessages(I18NMessages i18nMessages) {
		I18NMessages = i18nMessages;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public ModelsContainer() {
		
		
	}

	
	public void setModel(Model model){
		this.model=model;
	}
	
	public Model getModel(){
		return this.model;
	}

	public ModelMapper getModelMapper() {
		return modelMapper;
	}

	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	
	
	
	

}
