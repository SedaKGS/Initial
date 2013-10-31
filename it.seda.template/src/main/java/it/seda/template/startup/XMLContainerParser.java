package it.seda.template.startup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.seda.template.container.Parameter;
import it.seda.template.container.Screen;
import it.seda.template.container.Template;
import it.seda.template.container.TemplateContainer;
import it.seda.template.context.TemplateContext;
import it.seda.template.context.TemplateResource;
import it.seda.template.utils.Utils;
import it.seda.template.xparser.ParserException;
import it.seda.template.xparser.XNode;
import it.seda.template.xparser.XPathParser;

/**
 * 
 * @author f.ricci
 *
 */
public class XMLContainerParser {

	private Logger logger = LoggerFactory.getLogger(XMLContainerParser.class);

	final static String NULL_TEMPLATE = "*null*";
	
	private boolean parsed;
	private XPathParser parser;
	private TemplateContainer templateContainer;
	private TemplateContext context;
	private Map<String, Screen> temporaryScreens;
	private Map<String, String> temporaryTemplate;
	private TemplateResource currentResource;

	public XMLContainerParser(TemplateContext context) {
		this.context=context;
		templateContainer=new TemplateContainer(context);
		temporaryScreens=new HashMap<String, Screen>();
		temporaryTemplate= new HashMap<String, String>();
	}

	public TemplateContainer parse() {
		if (parsed) {
			throw new ParserException("Each XMLContainerParser can only be used once.");
		}
		parsed = true;
		
		for (TemplateResource resource : context.getTemplateResources()) {
			currentResource=resource;
			parse(resource);
		}
		
		return templateContainer;
	}

	private void parse(TemplateResource resource) {

		try {
			parser=new XPathParser(resource.getReader());
		} catch (IOException e) {
			throw new ParserException(resource.getLocalePath(), e);
		}
		
		parseContainer(parser.evalNode("/templates"));
	
	}
	
	private void parseContainer(XNode root) {
		try {
			templatesElement(root.evalNodes("template"));
			templateContainer.resolveDefaultTemplates();
			screensElement(root.evalNodes("screen"));
			applyInheritance();
			buildScreenContainer();
		} catch (Exception e) {
			throw new ParserException("Error parsing Procedure System. Cause: " + e, e);
		}
	}

	private void templatesElement(List<XNode> templates) {
		if (templates!=null) {
			for (XNode xNode : templates) {
				String name = xNode.getStringAttribute("name");
				String url = xNode.getStringAttribute("url");
				boolean def= xNode.getBooleanAttribute("default",false);
				Template template = new Template();
				template.setResource(currentResource);
				template.setDefault(def);
				template.setName(name);
				template.setUrl(url);
				
				List<Locale> locales = localesElement(xNode);
				template.addLocales(locales);
				
				templateContainer.addTemplate(template);				
			}
		}
	}
	
	private void screensElement(List<XNode> screens) {
		if (screens!=null) {
			for (XNode xNode : screens) {
				String name = xNode.getStringAttribute("name");
				
				if (temporaryScreens.containsKey(name)) {
					throw new ParserException("duplicate screen definition " + name + 
							"; current=" + currentResource + " exists="+temporaryScreens.get(name).getResource());					
				}
				
				
				String template = xNode.getStringAttribute("template");
				temporaryTemplate.put(name, template==null?NULL_TEMPLATE:template);
				
				String inherit = xNode.getStringAttribute("extends");
				Screen screen = new Screen();
				screen.setName(name);
				screen.setInherit(inherit);
				screen.setResource(currentResource);
				
				List<Locale> locales = localesElement(xNode);				
				screen.addLocales(locales);
				
				Properties properties = parametersElement(xNode);
				for (Object okey : properties.keySet()) {
					String key = (String)okey;
					Parameter parameter = new Parameter();
					parameter.setKey(key);
					parameter.setValue(properties.getProperty(key));
					screen.addParameter(parameter);
				}

				temporaryScreens.put(name, screen);
			}

		}
	}	

	private void buildScreenContainer() {
		
		for (String name : temporaryScreens.keySet()) {
			Screen screen = temporaryScreens.get(name);
			String template = temporaryTemplate.get(name);
			templateContainer.addScreen(template.equals(NULL_TEMPLATE)?null:template, screen);			
		}		
		
	}

	private void applyInheritance() {
		for (String name : temporaryScreens.keySet()) {
			Screen screen = temporaryScreens.get(name);
			if (screen.hasInheritance()) {
				applyInheritance(screen);
			}
		}
	}	
	
	private void applyInheritance(Screen screen) {
		if (screen.hasInerited()) {
			return;
		}
		Screen parent = temporaryScreens.get(screen.getInherit());
		if (parent==null) {
			logger.error("screen " + screen.getName() + " inherits from a missing screen definition '" + screen.getInherit() + "', screen could be result in a broken view");
			return;
		}
		
		if (parent.hasInheritance()) {
			applyInheritance(parent);
		}
		
		screen.inherit(parent);
	}

	private Properties parametersElement(XNode screen) {
		XNode parameters = screen.evalNode("parameters");
		Properties properties = parameters.getChildrenAsProperties();		
		return properties;
	}

	private List<Locale> localesElement(XNode hasLocaleNode) {
		List<Locale> returnLocale=new ArrayList<Locale>();
		XNode locales = hasLocaleNode.evalNode("locales");		
		if (locales!=null) {
			List<XNode> localeList = locales.getChildren();
			for (XNode xNode : localeList) {
				String localeString = xNode.getStringBody();
				Locale locale = Utils.localeFromString(localeString);
				if (locale!=null) {
					returnLocale.add(locale);
				} else {
					throw new ParserException("string locale not parsed " + localeString);
				}
			}
		}
		if (returnLocale.size()==0) {
			returnLocale.add(currentResource.getLocale());
		}
		return returnLocale;
	}
}
