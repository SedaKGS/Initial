package it.seda.generator;

import it.seda.generator.domain.Controller;
import it.seda.generator.domain.Form;
import it.seda.generator.domain.I18NMessages;
import it.seda.generator.domain.JSP;
import it.seda.generator.domain.Model;
import it.seda.generator.domain.ModelMapper;
import it.seda.generator.domain.MyBatisMapper;
import it.seda.generator.domain.Service;
import it.seda.generator.util.GeneratorUtils;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class Renderer {

	public Renderer() {

	}
   
	

	public void render(Object object) {
		
		String modelName=GeneratorUtils.resolveClassName(object.getClass().toString());
		String name=GeneratorUtils.resolveClassName(object.getClass().toString()).toLowerCase();
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(VelocityEngine.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init();
		VelocityContext context = new VelocityContext();
		context.put( name, object );

		/*
		 * read the first template
		 */
		Template template = ve.getTemplate("/it/seda/generator/templates/"+modelName+".vm");

		StringWriter sw = new StringWriter();

		template.merge( context, sw );

		System.out.println(sw.toString());
	}
	



}
