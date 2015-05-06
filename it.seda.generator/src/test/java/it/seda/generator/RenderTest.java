package it.seda.generator;

import it.seda.generator.domain.Attribute;
import it.seda.generator.domain.Controller;
import it.seda.generator.domain.Form;
import it.seda.generator.domain.I18NMessages;
import it.seda.generator.domain.JSP;
import it.seda.generator.domain.Model;
import it.seda.generator.domain.ModelMapper;
import it.seda.generator.domain.MyBatisMapper;
import it.seda.generator.domain.Service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

public class RenderTest {

	@Test
	public void test() {
		
		InputStream is=getClass().getClassLoader().getResourceAsStream("config.xml");
		XMLGeneratorParser parser=new XMLGeneratorParser(is);
		ModelsContainer container=parser.getContainer();
		if(container.getController()!=null) {container.getController().render();}
		if(container.getControllerWS()!=null) {container.getControllerWS().render();}
		if(container.getForm()!=null) {container.getForm().render();}
		if(container.getI18NMessages()!=null) {container.getI18NMessages().render();}
		if(container.getJsp()!=null) {container.getJsp().render();}
		if(container.getModel()!=null) {container.getModel().render();}
		if(container.getModelMapper()!=null) {container.getModelMapper().render();}
		if(container.getMyBatisMapper()!=null) {container.getMyBatisMapper().render();}
		if(container.getService()!=null) {container.getService().render();}
	}

}
