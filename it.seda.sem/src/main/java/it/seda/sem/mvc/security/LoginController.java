/**
 * 
 */
package it.seda.sem.mvc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author f.ricci
 *
 */
@Controller()
@RequestMapping(value="/login")
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);
			
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView def() {
		logger.debug("SEM::-hello login");
		return new ModelAndView("login");
	}
	
}
