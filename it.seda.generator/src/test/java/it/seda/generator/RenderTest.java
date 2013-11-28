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
		
		
		Model model = container.getModelList().get(0);
		System.out.println(model.toString());
		Renderer renderer = new Renderer();
		renderer.render(model);
		
		
		ModelMapper modelMapper=container.getModelMapper().get(0);
		System.out.println(modelMapper.toString());
		renderer.render(modelMapper);
		org.junit.Assert.assertEquals(1, 1);
		
		
		Service service=container.getService();
		service.setModel(model);
		service.setModelMapper(modelMapper);
		System.out.println(service.toString());
		renderer.render(service);
		org.junit.Assert.assertEquals(1, 1);
		
		
		
		Form form=container.getForm();
		form.setModel(model);
		System.out.println(form.toString());
		renderer.render(form);
		org.junit.Assert.assertEquals(1, 1);
		
		
		Controller controller=container.getController();
		controller.setService(service);
		controller.setForm(form);
		controller.setModel(model);
		System.out.println(controller.toString());
		renderer.render(controller);
		org.junit.Assert.assertEquals(1, 1);
		
		
		I18NMessages messages=container.getI18NMessages();
		messages.setForm(form);
		System.out.println(messages.toString());
		renderer.render(messages);
		org.junit.Assert.assertEquals(1, 1);
		
		JSP jsp=container.getJsp();
		jsp.setForm(form);
		jsp.setController(controller);
		jsp.setModel(model);
		System.out.println(jsp.toString());
		renderer.render(jsp);
		org.junit.Assert.assertEquals(1, 1);
		
		
		MyBatisMapper bmapper=container.getMyBatisMapper();
		bmapper.setModel(model);
		System.out.println(bmapper.toString());
		renderer.render(bmapper);
		org.junit.Assert.assertEquals(1, 1);
	}

}
