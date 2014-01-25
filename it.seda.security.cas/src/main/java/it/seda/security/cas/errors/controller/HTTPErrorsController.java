/**
 * 
 */
package it.seda.security.cas.errors.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author f.ricci
 *
 */
@Controller
@RequestMapping(value="/errors")
public class HTTPErrorsController {
	
	@RequestMapping(value = "/http404", method=RequestMethod.GET)
	public String handle404(String code, HttpServletRequest request, ModelMap model) {

	     String originalUri = (String)       
	        request.getAttribute("javax.servlet.forward.request_uri");
	    
	     model.addAttribute("origin", originalUri);
	    
	    return "error404";
	}	
	
	@RequestMapping(value = "/http500", method=RequestMethod.GET)
	public String handle500(String code, HttpServletRequest request, ModelMap model) {

	     String originalUri = (String)       
	        request.getAttribute("javax.servlet.forward.request_uri");
	    
	     model.addAttribute("origin", originalUri);
	    
	    return "error500";
	}	
	
//	@RequestMapping(value = "/{code}", method=RequestMethod.GET)
//	public String handle(@PathVariable("code") String code, HttpServletRequest request, ModelMap model) {
//
//	     String originalUri = (String)       
//	        request.getAttribute("javax.servlet.forward.request_uri");
//	     
//	     if (originalUri==null); //TODO remove me
//	     
//	    return "error"+code;
//	}	
	
}
