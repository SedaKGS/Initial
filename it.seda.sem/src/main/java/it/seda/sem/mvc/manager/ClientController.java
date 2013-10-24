package it.seda.sem.mvc.manager;

import java.util.List;

import it.seda.sem.mvc.manager.models.FormClient;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value="/manager")
public class ClientController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model){
 
		FormClient client = new FormClient();
		
		client.setCliente("CLIENTE");
		client.setDescrizione("DESC");
		client.setRegistrazione("TIMESTAMP");

		//command object
		model.addAttribute("initialClientData", client);
 
		//return form view
		return "formClient";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addCustomer(@Valid  @ModelAttribute("initialClientData") FormClient client, BindingResult result) {
		
		List<ObjectError> errors=null;
 
		if (result.hasErrors()) {
			errors=result.getAllErrors();
			return new ModelAndView("formClient");
		} else {
			System.out.println("Dati inseriti correttamente");
		}
		
		return new ModelAndView("nextPage","cliente",client);
 
	}

}
