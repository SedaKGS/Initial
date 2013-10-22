package it.seda.sem.mvc.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/welcome")
public class WelcomeController {
	
	private Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView def() {
		//TODO qui loggare tutte le informazioni sullo user bean dal security context
		
		logger.info("tutte le informazioni sullo user bean");
		logger.info("Lingua = "+LocaleContextHolder.getLocale().getDisplayLanguage());
		

		return new ModelAndView("welcome");
	}
	
}
