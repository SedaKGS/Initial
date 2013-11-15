package it.seda.sem.security.password.changing;

import it.seda.sem.mvc.manager.models.FormClient;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/changePassword")
public class ChangePasswordController {
	
	@Inject @Qualifier("passwordHandler")PasswordHandler changePassword;
	
	private Logger logger = LoggerFactory.getLogger(ChangePasswordController.class);
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model){
		FormChangePassword formChangePassword = new FormChangePassword();
		formChangePassword.setUsername("USERNAME");
		formChangePassword.setOldPassword("OLD_PASSWORD");
		formChangePassword.setNewPassword("NEW_PASSWORD");
		formChangePassword.setConfirm("CONFIRM_NEW_PASSWORD");
		formChangePassword.setEsito(true);
		//command object
		model.addAttribute("changePassword", formChangePassword);
		//return form view
		return "changePassword";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String addCustomer(@Valid  @ModelAttribute("changePassword") FormChangePassword formChangePassword, BindingResult result, ModelMap model) {
		List<ObjectError> errors=null;
		if (result.hasErrors()) {
			errors=result.getAllErrors();
			return "changePassword";
		} else {		
			logger.info("ChangePassword: dati inseriti correttamente");
		}
		
		boolean esito=changePassword.changePassword(formChangePassword);
		formChangePassword.setEsito(esito);
		model.addAttribute("changePasswordData", formChangePassword);
		if(esito){
			logger.info("ChangePassword: password cambiata");
		}
		else{
			return "changePassword";
		}
		return "aggiornamentoRiuscito";
	}
}






	
	
	
	
	
