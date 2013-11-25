package it.seda.sem.mvc.manager;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import it.seda.sem.domain.Cliente;
import it.seda.sem.domain.ObjectCopier;
import it.seda.sem.jdbc.RowBoundsHelper;
import it.seda.sem.manager.service.ClientService;
import it.seda.sem.mvc.manager.models.FormAccount;
import it.seda.sem.mvc.manager.models.FormClient;
import it.seda.sem.security.domain.AccountTO;
import it.seda.sem.security.exceptions.DuplicateAccountException;
import it.seda.sem.security.service.AccountService;
import it.seda.template.taglib.DatagridTag.Page;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value="/client")
public class ClientController {
	
    @Inject ClientService clientService;
	
	private Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	
	
	/*
	 * Method used to delete a client by id
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) 
	public String deleteClient(@PathVariable BigInteger id, 
			               @RequestParam(value="pageNumber", defaultValue="1") int pageNumber, 
			               @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			               ModelMap model) {
    
		
	clientService.deleteClient(id);
	FormClient client=new FormClient();
	client.setEsito("formAccount.esito.cancel");
	refreshDatagrid(model, pageNumber, rowsPerPage);
	
	model.addAttribute("clientData", client);
	return "client";
	}
	
	
	/*
	 * Method used to update a client
	 */
	
	@RequestMapping(method=RequestMethod.PUT) 
	public String updateClient(
			               @Valid @ModelAttribute("clientData") FormClient formClient,
			               BindingResult result,
			               @RequestParam(value="pageNumber", defaultValue="1") int pageNumber, 
			               @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			               ModelMap model) {
    
		if (!result.hasErrors()) {
			logger.debug("Client Manager: dati inseriti correttamente"); //TODO i18n		
			try{	
				Cliente client=ObjectCopier.createObject(formClient, Cliente.class);
				clientService.updateClient(client);
				formClient.setEsito("formClient.esito.ok");
			}catch(Exception e){
				formClient.setEsito("formClient.esito.notOk");
				logger.error("Err",e); //TODO i18n errore inserimento
			}finally{
				model.addAttribute("cliente",formClient);
			}

		}
		refreshDatagrid(model, pageNumber, rowsPerPage);	
		return "client";
	}
	
	
	/*
	 * Method used to edit an account given an id
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET) 
	public String editAccount(@PathVariable BigInteger id, 
			                  @RequestParam(value="pageNumber", defaultValue="1") int pageNumber, 
			                  @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			                  @RequestParam(value="action",required=false) String action,
			                  ModelMap model) {
	
		
    Cliente client=clientService.getClient(id); 
    FormClient formClient=new FormClient();
    formClient.setId(client.getId());
    formClient.setNome(client.getNome());
    formClient.setDescrizione(client.getDescrizione());
    
    
    
   
    
    model.addAttribute("clientData",formClient);
    model.addAttribute("action",action);
	refreshDatagrid(model, pageNumber, rowsPerPage);
	return "client";
	}
	
	/*
	 * Give the requested page if the id is not specified
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(@RequestParam(value="pageNumber", defaultValue="1") int pageNumber,
			               @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			               ModelMap model){
 
		FormClient formClient=new FormClient();
		refreshDatagrid(model, pageNumber, rowsPerPage);
		model.addAttribute("clientData", formClient);
		//return form view
		return "client";
	}
	
	/*
	 * Inserts a new client
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String addClient(@Valid @ModelAttribute("clientData") FormClient formClient, 
			BindingResult result, 
			ModelMap model,
			@RequestParam(value="pageNumber", defaultValue="1") int pageNumber,
            @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage) {

		if (!result.hasErrors()) {
			logger.debug("Client Manager: dati inseriti correttamente"); //TODO i18n		
			try{	
				Cliente client=ObjectCopier.createObject(formClient, Cliente.class);
				clientService.insertClient(client);
				formClient.setEsito("formClient.esito.ok");
			}
			catch(Exception e){
				formClient.setEsito("formClient.esito.notOk");
				logger.error("Err",e); //TODO i18n errore inserimento
			}finally{
				model.addAttribute("clientData",formClient);
			}

		}

		refreshDatagrid(model, pageNumber, rowsPerPage);
				
		
		return "client";
	}
	
	
	protected void refreshDatagrid(ModelMap model, int pageNumber, int rowsPerPage) {
		int totalRows=clientService.listClientCount();

		RowBoundsHelper rbh = new RowBoundsHelper(rowsPerPage, pageNumber);
		List<Cliente> ar=clientService.listClient(rbh.buildRowBounds());
		
		Page<Cliente> clientPage = new Page<Cliente>(ar);
		rbh.decorate(clientPage, totalRows);
		
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("rowsPerPage", rowsPerPage);
		model.addAttribute("clientsPage", clientPage);
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

