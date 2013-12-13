package it.seda.template.startup;

import it.seda.template.container.menu.AntRequestMatcher;
import it.seda.template.container.menu.Menu;
import it.seda.template.container.menu.MenuHandler;
import it.seda.template.context.TemplateResource;
import it.seda.template.xparser.ParserException;
import it.seda.template.xparser.XNode;
import it.seda.template.xparser.XPathParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLMenuHandlerParser {

	private Logger logger = LoggerFactory.getLogger(XMLMenuHandlerParser.class);
	
	private boolean parsed;
	private XPathParser parser;
	private TemplateResource currentResource;
	private Menu currentMenu;
	
	private MenuHandler menuHandler;

	public MenuHandler parse(List<TemplateResource> resources) {
		menuHandler=new MenuHandler();
		
		if (parsed) {
			throw new ParserException("Each XMLMenuHandlerParser can only be used once.");
		}
		parsed = true;
		
		for (TemplateResource resource : resources) {
			currentResource=resource;
			parse(resource);
		}		
		
		return menuHandler;
	}
	
	
	private void parse(TemplateResource resource) {

		try {
			parser=new XPathParser(resource.getReader());
		} catch (IOException e) {
			throw new ParserException(resource.getLocalePath(), e);
		}
		
		parseMenus(parser.evalNode("/menus"));
	}


	private void parseMenus(XNode root) {
		try {
			menuElement(root.evalNodes("menu"));
		} catch (Exception e) {
			throw new ParserException("Error parsing Procedure System. Cause: " + e, e);
		}
	}


	private void menuElement(List<XNode> menus) {
		if (menus!=null) {
			for (XNode xNode : menus) {
				String id = xNode.getStringAttribute("id");
				String path = xNode.getStringAttribute("path");
				int order = xNode.getIntAttribute("order",0);
				
				Menu menu = new Menu(id, path, order);
				menu.setResource(currentResource);
				currentMenu=menu;
				
				if (menuHandler.contains(menu)) {
					throw new ParserException("duplicate menu definition " + id + 
							"; current=" + currentResource + " exists="+menuHandler.get(id).getResource());							
				}
				
				Set<String> roles = rolesElement(xNode);
				menu.addAllRoles(roles);
				
				List<AntRequestMatcher> matchers = matchersElement(xNode);
				menu.addAllMatchers(matchers);
				
				menuHandler.addMenu(menu);				
			}
		}		
	}


	private List<AntRequestMatcher> matchersElement(XNode menuNode) {
		List<XNode> pathsNodes = menuNode.evalNodes("paths");
		
		List<AntRequestMatcher> matchers = new ArrayList<AntRequestMatcher>();
		
		if (pathsNodes.size()>0) {		
			if (pathsNodes.size()>1) {
				if (logger.isInfoEnabled()) {
					logger.info("Only one paths node can be specified for menu: '" + currentMenu.getId() + "' in '" + currentMenu.getResource() + "'");
				}
			}

			XNode pathsNode = pathsNodes.get(0);
			List<XNode> matches = pathsNode.evalNodes("match");
			for (XNode xNode : matches) {
				AntRequestMatcher antRequestMatcher = new AntRequestMatcher(xNode.getStringAttribute("pattern"));
				matchers.add(antRequestMatcher); 
			}
		}
		
		return matchers;
	}


	private Set<String> rolesElement(XNode menuNode) {
		List<XNode> allowedRolesNodes = menuNode.evalNodes("allowed-roles");
		
		Set<String> roles = new HashSet<String>();
		
		if (allowedRolesNodes.size()>0) {		
			if (allowedRolesNodes.size()>1) {
				if (logger.isInfoEnabled()) {
					logger.info("Only one allowed-roles node can be specified for menu: '" + currentMenu.getId() + "' in '" + currentMenu.getResource() + "'");
				}
			}
			XNode allowedRolesNode = allowedRolesNodes.get(0);
			List<XNode> allowedRoles = allowedRolesNode.evalNodes("role");
			for (XNode xNode : allowedRoles) {
				roles.add(xNode.getStringAttribute("name")); 
			}
		}

		return roles;
	}	

	
	
}
