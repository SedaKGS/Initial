package it.seda.sem.mvc.manager;

import it.seda.sem.domain.ObjectCopier;
import it.seda.sem.mvc.manager.models.FormAccount;
import it.seda.sem.mvc.utils.OptionsUtil;
import it.seda.sem.security.domain.AccountTO;
import it.seda.sem.security.domain.Group;
import it.seda.sem.security.exceptions.DuplicateAccountException;
import it.seda.sem.security.service.AccountService;
import it.seda.template.taglib.DatagridTag.Page;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/account")
public class AccountController {

	@Inject AccountService accountService;

	private Logger logger = LoggerFactory.getLogger(AccountController.class);


	/*
	 * Method used to delete an account by username
	 */
	@RequestMapping(value="/{username}", method=RequestMethod.DELETE) 
	public String deleteAccount(@PathVariable String username, 
			@RequestParam(value="pageNumber", defaultValue="1") int pageNumber, 
			@RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			ModelMap model) {


		accountService.deleteAccount(username);
		FormAccount account=new FormAccount();
		account.setEsito("formAccount.esito.cancel");
		addListAccount(model, pageNumber, rowsPerPage);
		addListGroup(model);
		model.addAttribute("accountData", account);
		return "account";
	}

	/*
	 * Method used to update an account 
	 */

	@RequestMapping(method=RequestMethod.PUT) 
	public String updateAccount(
			@Valid @ModelAttribute("accountData") FormAccount account,
			BindingResult result,
			@RequestParam(value="pageNumber", defaultValue="1") int pageNumber, 
			@RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			ModelMap model) {

		if (!result.hasErrors()) {
			logger.debug("Account Controller: dati inseriti correttamente"); //TODO i18n		
			try{	
				AccountTO ato=ObjectCopier.createObject(account, AccountTO.class);
				accountService.updateAccountTO(ato);
				account.setEsito("formAccount.esito.ok");

			}catch(DuplicateAccountException e){
				account.setEsito("formAccount.esito.duplicate");
			}catch(Exception e){
				account.setEsito("formAccount.esito.notOk");
				logger.error("Err",e); //TODO i18n errore inserimento
			}finally{
				model.addAttribute("accountData",account);
			}

		}

		addListAccount(model, pageNumber, rowsPerPage);
		addListGroup(model);		

		return "account";
	}

	/*
	 * Method used to edit an account given a username
	 */
	@RequestMapping(value="/{username}/unlock", method=RequestMethod.POST) 
	public String unlockAccount(@PathVariable String username, 
			@RequestParam(value="pageNumber", defaultValue="1") int pageNumber, 
			@RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			ModelMap model) {

		accountService.restoreAttempts(username);
		
		FormAccount account=new FormAccount();
		account.setEsito("formAccount.esito.cancel"); 

		model.addAttribute("accountData", account);
		addListAccount(model, pageNumber, rowsPerPage);
		addListGroup(model);
		
		return "account";
	}	

	/*
	 * Method used to edit an account given a username
	 */
	@RequestMapping(value="/{username}", method=RequestMethod.GET) 
	public String editAccount(@PathVariable String username, 
			@RequestParam(value="pageNumber", defaultValue="1") int pageNumber, 
			@RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			@RequestParam(value="action",required=false) String action,
			ModelMap model) {


		AccountTO account=accountService.getAccountTOByUserName(username);
		FormAccount formAccount=ObjectCopier.createObject(account, FormAccount.class); 

		model.addAttribute("accountData",formAccount);
		model.addAttribute("action",action);
		addListAccount(model, pageNumber, rowsPerPage);
		addListGroup(model);
		return "account";
	}

	/*
	 * Give the requested page if the username is not specified
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(@RequestParam(value="pageNumber", defaultValue="1") int pageNumber,
			@RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			ModelMap model){

		FormAccount account=new FormAccount();

		addListAccount(model, pageNumber, rowsPerPage);
		addListGroup(model);
		model.addAttribute("accountData", account);
		//return form view
		return "account";
	}

	/*
	 * Inserts a new account
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String addAccount(@Valid @ModelAttribute("accountData") FormAccount account, 
			BindingResult result, 
			ModelMap model,
			@RequestParam(value="pageNumber", defaultValue="1") int pageNumber,
			@RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage) {

		if (!result.hasErrors()) {
			logger.debug("ChangePassword: dati inseriti correttamente"); //TODO i18n		
			try{	
				AccountTO ato=ObjectCopier.createObject(account, AccountTO.class);
				accountService.insertAccount(ato);
				account.setEsito("formAccount.esito.ok");

			}catch(DuplicateAccountException e){
				account.setEsito("formAccount.esito.duplicate");
			}catch(Exception e){
				account.setEsito("formAccount.esito.notOk");
				logger.error("Err",e); //TODO i18n errore inserimento
			}finally{
				model.addAttribute("accountData",account);
			}

		}

		addListAccount(model, pageNumber, rowsPerPage);
		addListGroup(model);		

		return "account";
	}

	protected void addListGroup(ModelMap model) {
		List<Group> groupsList=accountService.groupsList();
		Map<String,String> groupsMap=OptionsUtil.buildOptionList(groupsList,"groupName","groupName");
		model.addAttribute("groupsMap", groupsMap);
	}

	protected void addListAccount(ModelMap model, int pageNumber, int rowsPerPage) {
		Page<AccountTO> accountPage=accountService.listaAccount(pageNumber, rowsPerPage);

		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("rowsPerPage", rowsPerPage);
		model.addAttribute("accountsPage", accountPage);
	}
}
