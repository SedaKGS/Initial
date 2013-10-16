package it.seda.sem.mvc.welcome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
	
	private Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	
	
	public ModelAndView def() {
		//TODO qui loggare tutte le informazioni sullo user bean dal security context
		
		logger.info("tutte le informazioni sullo user bean");

		return new ModelAndView("welcome");
	}
	
}
