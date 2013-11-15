package it.seda.sem.security.controller;

import it.seda.sem.security.domain.Signon;
import it.seda.sem.security.password.FormChangePassword;
import it.seda.sem.security.service.AccountService;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/changePassword")
public class ChangePasswordController {
	
//	@Inject @Qualifier("passwordHandler")PasswordHandler changePassword;
	@Inject AccountService accountService;
	
	private Logger logger = LoggerFactory.getLogger(ChangePasswordController.class);
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model){
		FormChangePassword formChangePassword = new FormChangePassword();
		formChangePassword.setNewPassword("NEW_PASSWORD");
		formChangePassword.setConfirm("CONFIRM_NEW_PASSWORD");
		formChangePassword.setEsito(true);
		//command object
		model.addAttribute("changePassword", formChangePassword);
		//return form view
		return "changePassword";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String changePassword(@Valid  @ModelAttribute("changePassword") FormChangePassword formChangePassword, BindingResult result, ModelMap model) {
		List<ObjectError> errors=null;
		if (result.hasErrors()) {
			errors=result.getAllErrors();
			return "changePassword";
		} else {		
			logger.debug("ChangePassword: dati inseriti correttamente"); //TODO i18n
		}
		
//		boolean esito=changePassword.changePassword(formChangePassword);
//		formChangePassword.setEsito(esito);
//		model.addAttribute("changePasswordData", formChangePassword);
//		if(esito){
//			logger.info("ChangePassword: password cambiata");
//		}
//		else{
//			return "changePassword";
//		}
		
		Signon signon = new Signon();
		signon.setPassword(formChangePassword.getNewPassword());
		signon.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		accountService.updateSignon(signon);		
		
		return "changePassword";
	}
}






	
	
	
	
	
