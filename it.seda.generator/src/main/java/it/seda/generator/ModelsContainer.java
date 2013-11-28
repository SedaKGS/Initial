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
	
	private List<Model> modelList;
	private List<ModelMapper> modelMapper;
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
		modelList=new ArrayList<Model>(1);
		modelMapper=new ArrayList<ModelMapper>(1);
		
	}

	public List<Model> getModelList() {
		return modelList;
	}

	public void setModelList(List<Model> modelList) {
		this.modelList = modelList;
	}
	
	public void addModel(Model model){
		modelList.add(model);
	}

	public List<ModelMapper> getModelMapper() {
		return modelMapper;
	}

	public void setModelMapper(List<ModelMapper> modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public void addModelMapper(ModelMapper modelMapper){
		this.modelMapper.add(modelMapper);
	}
	
	
	

}
