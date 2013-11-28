package it.seda.generator;

import it.seda.generator.domain.Controller;
import it.seda.generator.domain.Form;
import it.seda.generator.domain.I18NMessages;
import it.seda.generator.domain.JSP;
import it.seda.generator.domain.Model;
import it.seda.generator.domain.ModelMapper;
import it.seda.generator.domain.MyBatisMapper;
import it.seda.generator.domain.Service;

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
	
	
	
	public void render(ModelMapper modelMapper) {
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
		context.put( "mapper", modelMapper );

		/*
		 * read the first template
		 */
		Template template = ve.getTemplate("/it/seda/generator/templates/ModelMapper.vm");

		StringWriter sw = new StringWriter();

		template.merge( context, sw );

		System.out.println(sw.toString());
	}
	
	
	
	public void render(Service service) {
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
		context.put( "service", service );

		/*
		 * read the first template
		 */
		Template template = ve.getTemplate("/it/seda/generator/templates/Service.vm");

		StringWriter sw = new StringWriter();

		template.merge( context, sw );

		System.out.println(sw.toString());
	}
	
	
	public void render(Form form) {
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
		context.put( "form", form );

		/*
		 * read the first template
		 */
		Template template = ve.getTemplate("/it/seda/generator/templates/Form.vm");

		StringWriter sw = new StringWriter();

		template.merge( context, sw );

		System.out.println(sw.toString());
	}
	
	
	public void render(Controller controller) {
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
		context.put( "controller", controller );

		/*
		 * read the first template
		 */
		Template template = ve.getTemplate("/it/seda/generator/templates/Controller.vm");

		StringWriter sw = new StringWriter();

		template.merge( context, sw );

		System.out.println(sw.toString());
	}
	
	
	public void render(I18NMessages i18NMessages) {
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
		context.put( "messages", i18NMessages );

		/*
		 * read the first template
		 */
		Template template = ve.getTemplate("/it/seda/generator/templates/Validator.vm");
		StringWriter sw = new StringWriter();
		template.merge( context, sw );
		System.out.println(sw.toString());
		
		template = ve.getTemplate("/it/seda/generator/templates/Messages.vm");
		sw = new StringWriter();
		template.merge( context, sw );
		System.out.println(sw.toString());
	}
	
	public void render(JSP jsp) {
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
		context.put( "jsp", jsp );

		/*
		 * read the first template
		 */
		Template template = ve.getTemplate("/it/seda/generator/templates/JSP.vm");
		StringWriter sw = new StringWriter();
		template.merge( context, sw );
		System.out.println(sw.toString());
		
		
	}
	
	
	public void render(MyBatisMapper myBatisMapper) {
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
		context.put( "myBatisMapper", myBatisMapper );

		/*
		 * read the first template
		 */
		Template template = ve.getTemplate("/it/seda/generator/templates/MyBatisMapper.vm");
		StringWriter sw = new StringWriter();
		template.merge( context, sw );
		System.out.println(sw.toString());
		
		
	}





}
