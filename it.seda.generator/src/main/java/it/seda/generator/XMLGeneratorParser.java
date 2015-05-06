package it.seda.generator;

import it.seda.generator.domain.Attribute;
import it.seda.generator.domain.Controller;
import it.seda.generator.domain.Form;
import it.seda.generator.domain.I18NMessages;
import it.seda.generator.domain.JSP;
import it.seda.generator.domain.Model;
import it.seda.generator.domain.ModelMapper;
import it.seda.generator.domain.MyBatisMapper;
import it.seda.generator.domain.RestWS;
import it.seda.generator.domain.Service;
import it.seda.template.xparser.ParserException;
import it.seda.template.xparser.XNode;
import it.seda.template.xparser.XPathParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLGeneratorParser {
	
	private Logger logger = LoggerFactory.getLogger(XMLGeneratorParser.class);
	AliasRegistry aliasRegistry;
	private ModelsContainer container;
	private XPathParser parser;
	private InputStream inputStream;
	
	
	public XMLGeneratorParser() {
	}

	public XMLGeneratorParser(InputStream file) {
		 this.inputStream=file;
		 aliasRegistry= new AliasRegistry();
		 container=new ModelsContainer();
		 parse(inputStream);
	}
	
	
	public void parse(InputStream inputStream){
		InputStreamReader reader=new InputStreamReader(inputStream);
	    parser=new XPathParser(reader);
		parseRoot(parser.evalNode("/models"));
	}
	
	private void parseRoot(XNode root){
		try {
			aliasesElement(root.evalNodes("aliases"));
			modelElement(root.evalNode("model"));
			modelMapperElement(root.evalNode("mapper"));
			serviceElement(root.evalNode("service"));
			controllerElement(root.evalNode("controller"));
			controllerWSElement(root.evalNode("controllerWS"));
			formElement(root.evalNode("form"));
//			messagesElement(root.evalNode("messages"));
//			jspElement(root.evalNode("jsp"));
		    myBatisMapperElement(root.evalNode("myBatis"));
		} catch (Exception e) {
			throw new ParserException("Error parsing Procedure System. Cause: " + e, e);
		}
	}
	
	private void controllerWSElement(XNode controllerWS) {
		if(controllerWS!=null){
			String classpath=controllerWS.getStringAttribute("classpath");
			String baseUrl=controllerWS.getStringAttribute("baseurl");
			int pageNumber=controllerWS.getIntAttribute("pageNumber",1);
			int rowsPerPage=controllerWS.getIntAttribute("rowsPerPage",15);
			RestWS ctr=new RestWS(classpath,container);
			ctr.setBaseUrl(baseUrl);
			ctr.setPageNumber(pageNumber);
			ctr.setRowsPerPage(rowsPerPage);
			checkController(ctr);
			container.setController(ctr);
		}
	}

	

	private void  myBatisMapperElement(XNode myBatisMapper){
		if(myBatisMapper!=null){
			String clazz=myBatisMapper.getStringAttribute("classpath");
			MyBatisMapper mapper=new MyBatisMapper(clazz,container);
			container.setMyBatisMapper(mapper);
		}
		
	}
	
	private void jspElement(XNode jsp){
		if(jsp!=null){
			String clazz=jsp.getStringAttribute("classpath");
			JSP page=new JSP(clazz,container);
			container.setJsp(page);
		}
		
	}
	
	private void messagesElement(XNode messages){
		if(messages!=null){
			String message=messages.getStringAttribute("message");
			String validator=messages.getStringAttribute("validator");
			I18NMessages i18n=new I18NMessages(message,validator,container);
			container.setI18NMessages(i18n);
		}
		
	}
	
	private void formElement(XNode form){
		if(form!=null){
			String classpath=form.getStringAttribute("classpath");
			Form ctr=new Form(classpath,container);
			container.setForm(ctr);
		}
		
	}
	
	private void controllerElement(XNode controller){
		if(controller!=null){
			String classpath=controller.getStringAttribute("classpath");
			String baseUrl=controller.getStringAttribute("baseurl");
			int pageNumber=controller.getIntAttribute("pageNumber",1);
			int rowsPerPage=controller.getIntAttribute("rowsPerPage",15);
			Controller ctr=new Controller(classpath,container);
			ctr.setBaseUrl(baseUrl);
			ctr.setPageNumber(pageNumber);
			ctr.setRowsPerPage(rowsPerPage);
			checkController(ctr);
			container.setController(ctr);
		}
		
	}
	
	private void serviceElement(XNode service){
		if(service!=null){
			String classpath=service.getStringAttribute("classpath");
			String transaction=service.getStringAttribute("transaction");
			Service srv=new Service(classpath,transaction,container);
			container.setService(srv);
		}
		
	}
	
	private void modelMapperElement(XNode mapper){
		if(mapper!=null){
				ModelMapper mp=new ModelMapper(mapper.getStringAttribute("classpath"),container);
				String annotation=mapper.getStringAttribute("annotation");				
				mp.setAnnotation(annotation);
				mp.setModel(container.getModel());
				container.setModelMapper(mp);	
		}
	}
	
	private void aliasesElement(List<XNode> alisesGr){
		if(alisesGr!=null){
			for (XNode aliasesSg : alisesGr) {
				List<XNode> aliases=aliasesSg.evalNodes("alias");
				
				for (XNode alias : aliases) {
					String aliasName  =alias.getStringAttribute("name");
					String clazz =alias.getStringAttribute("class");
					aliasRegistry.registerAlias(aliasName, clazz);
				}
			
			}
			
		}
		
	}
	
	private void modelElement(XNode model){
		if(model!=null){
				Model mdl=new Model(model.getStringAttribute("classpath"),model.getStringAttribute("table"),container);
				List<XNode> attributes=model.evalNodes("attribute");
				for (XNode attribute : attributes) {	
					
					String column=attribute.getStringAttribute("column");
					String name=attribute.getStringAttribute("name");
					String type=attribute.getStringAttribute("type");
					String generic=attribute.getStringAttribute("generic");
					int pk=attribute.getIntAttribute("pk",0);
					int auto=attribute.getIntAttribute("auto",0);
					int form=0;
					int datagrid=0;
					int notEmpty=0;
					
					String inputType=null;
					String pattern=null;
					String field=null;
					XNode inputNode =attribute.evalNode("input");
					if(inputNode!=null){
					form=inputNode.getIntAttribute("form",0);
					inputType=inputNode.getStringAttribute("type");
					datagrid=inputNode.getIntAttribute("datagrid",0);
					field=inputNode.getStringAttribute("field");
					}
					
					XNode validator = attribute.evalNode("validator");
					if(validator!=null){
					pattern=validator.getStringAttribute("pattern");
					notEmpty=validator.getIntAttribute("notempty",0);
					}
					/*
					int form=attribute.getIntAttribute("form",0);*
					int datagrid=attribute.getIntAttribute("datagrid",0);*
					int notEmpty=attribute.getIntAttribute("notempty",0);*
					String pattern=attribute.getStringAttribute("pattern");*
					*/
				    Attribute attr=new Attribute();	
				    attr.setColumn(column);
				    attr.setName(name);
				    attr.setType(aliasRegistry.resolveAlias(type));
				    attr.setGeneric(aliasRegistry.resolveAlias(generic));
				    attr.setPk(pk);
				    attr.setForm(form);
				    attr.setAuto(auto);
				    attr.setDatagrid(datagrid);
				    attr.setNotEmpty(notEmpty);
				    attr.setPattern(pattern);
				    attr.setInputType(inputType);
				    attr.setField(field);
				    chekAttribute(attr);
				    mdl.addAttribute(attr);
				    
				}
				
				container.setModel(mdl);
			
		}
	}

     
	public ModelsContainer getContainer() {
		return container;
	}


	public void setContainer(ModelsContainer container) {
		this.container = container;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	public void chekAttribute(Attribute attribute){
		if(attribute.getGeneric()!=null){
			logger.warn(attribute.getName()+" non pu� essere primary key");
			return;
		}
		
		
	}
	
	public void checkController(Controller controller){
		if(controller.getName().equalsIgnoreCase("controller")){
			logger.warn(controller.getName()+" non � un nome consentito");
			return;
		}
	}
	
	private void checkController(RestWS controllerWS) {
		if(controllerWS.getName().equalsIgnoreCase("controllerWS")){
			logger.warn(controllerWS.getName()+" non � un nome consentito");
			return;
		}
	}

	
	
}
