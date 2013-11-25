package it.seda.generator;

import it.seda.generator.domain.Model;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class Renderer {

	public Renderer() {

	}

	public void render(Model model) {
		/*
		 *  create a new instance of the engine
		 */
		VelocityEngine ve = new VelocityEngine();

		/*
		 *  configure the engine.  In this case, we are using
		 *  ourselves as a logger (see logging examples..)
		 */

//		ve.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM, this);

		/*
		 *  initialize the engine
		 */
		ve.setProperty(VelocityEngine.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		ve.init();

		/*
		 * create the velocity context 
		 */
		VelocityContext context = new VelocityContext();
		context.put( "model", model );
		
		/*
		 * read the first template
		 */
		Template template = ve.getTemplate("/it/seda/generator/templates/Model.vm");
		
		StringWriter sw = new StringWriter();

		template.merge( context, sw );
		
		System.out.println(sw.toString());
	}



}
