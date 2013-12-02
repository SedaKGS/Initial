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
		container.getController().render();
		container.getForm().render();
		container.getI18NMessages().render();
		container.getJsp().render();
		container.getModel().render();
		container.getModelMapper().render();
		container.getMyBatisMapper().render();
		container.getService().render();
	}

}
