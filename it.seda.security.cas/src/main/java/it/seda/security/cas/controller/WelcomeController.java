
package it.seda.security.cas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author f.ricci
 *
 */
@Controller
@RequestMapping(value="/home")
public class WelcomeController {
	
	private Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView def() {
		//TODO qui loggare tutte le informazioni sullo user bean dal security context
		
		logger.info("tutte le informazioni sullo user bean");
		logger.info("Lingua = "+LocaleContextHolder.getLocale().getDisplayLanguage());
		SecurityContext ctx=SecurityContextHolder.getContext();
		logger.info("Ruoli:");
		for (GrantedAuthority auothority : ctx.getAuthentication().getAuthorities()) {
			logger.info(auothority.getAuthority());
		}
		logger.info("Principal: "+ctx.getAuthentication().getPrincipal());
		logger.info("Credential: "+ctx.getAuthentication().getCredentials());
		logger.info("Details: "+ctx.getAuthentication().getDetails());
		logger.info("Name "+ctx.getAuthentication().getName());
		return new ModelAndView("home");
	}
	
}