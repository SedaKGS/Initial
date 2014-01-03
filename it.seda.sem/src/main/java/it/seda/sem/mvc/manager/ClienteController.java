package it.seda.sem.mvc.manager;

import it.seda.jdbc.commons.DataPage;
import it.seda.sem.domain.Cliente;
import it.seda.sem.domain.ObjectCopier;
import it.seda.sem.manager.service.ClienteService;
import it.seda.sem.mvc.manager.models.FormClient;

import java.math.BigInteger;

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
@RequestMapping(value="/cliente")
public class ClienteController {
	
    @Inject ClienteService clientService;
	
	private Logger logger = LoggerFactory.getLogger(ClienteController.class);
	
	/*
	 * Method used to delete a client by id
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) 
	public String deleteClient(@PathVariable BigInteger id, 
			               @RequestParam(value="pageNumber", defaultValue="1") int pageNumber, 
			               @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			               ModelMap model) {
    
		
	clientService.deleteCliente(id);
	FormClient client=new FormClient();
	client.setEsito("formClient.esito.cancel");
	refreshDatagrid(model, pageNumber, rowsPerPage);
	
	model.addAttribute("clienteData", client);
	return "cliente";
	}
	
	
	/*
	 * Method used to update a client
	 */
	
	@RequestMapping(method=RequestMethod.PUT) 
	public String updateClient(
			               @Valid @ModelAttribute("clienteData") FormClient formClient,
			               BindingResult result,
			               @RequestParam(value="pageNumber", defaultValue="1") int pageNumber, 
			               @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			               ModelMap model) {
    
		if (!result.hasErrors()) {
			logger.debug("Client Manager: dati inseriti correttamente"); //TODO i18n		
			try{	
				Cliente client=ObjectCopier.createObject(formClient, Cliente.class);
				clientService.updateCliente(client);
				formClient.setEsito("formClient.esito.ok");
			}catch(Exception e){
				formClient.setEsito("formClient.esito.notOk");
				logger.error("Err",e); //TODO i18n errore inserimento
			}finally{
				model.addAttribute("clienteData",formClient);
			}

		}
		refreshDatagrid(model, pageNumber, rowsPerPage);	
		return "cliente";
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
	
		
    Cliente client=clientService.getCliente(id); 

    FormClient formClient=ObjectCopier.createObject(client, FormClient.class);
    
    model.addAttribute("clienteData",formClient);
    model.addAttribute("action",action);
	refreshDatagrid(model, pageNumber, rowsPerPage);
	return "cliente";
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
		model.addAttribute("clienteData", formClient);
		//return form view
		return "cliente";
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
			logger.debug("Customer Manager: dati inseriti correttamente"); //TODO i18n		
			try{	
				Cliente client=ObjectCopier.createObject(formClient, Cliente.class);
				clientService.insertCliente(client);
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
				
		
		return "cliente";
	}
	
	
	protected void refreshDatagrid(ModelMap model, int pageNumber, int rowsPerPage) {
		DataPage<Cliente> clientiPage=clientService.listClienti(pageNumber, rowsPerPage);		
	
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("rowsPerPage", rowsPerPage);
		model.addAttribute("clientiPage", clientiPage);
	}
	
	
}
