package it.seda.sem.mvc.manager;
import it.seda.jdbc.commons.DataPage;
import it.seda.sem.domain.ObjectCopier;
import it.seda.sem.domain.Server;
import it.seda.sem.manager.service.ServerService;
import it.seda.sem.mvc.manager.models.FormServer;

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
@RequestMapping(value="/server")
public class ServerController {

	@Inject ServerService serverService;

	private Logger logger = LoggerFactory.getLogger(ServerController.class);

	/*
	 * Method used to delete a server by id
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) 
	public String deleteServer(@PathVariable BigInteger id, 
			@RequestParam(value="pageNumber", defaultValue="1") int pageNumber, 
			@RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			ModelMap model) {


		serverService.deleteServer(id);
		FormServer server=new FormServer();
		server.setEsito("formAccount.esito.cancel");
		refreshDatagrid(model, pageNumber, rowsPerPage);

		model.addAttribute("serverData", server);
		return "server";
	}


	/*
	 * Method used to update a server
	 */

	@RequestMapping(method=RequestMethod.PUT) 
	public String updateServer(
			@Valid @ModelAttribute("serverData") FormServer formServer,
			BindingResult result,
			@RequestParam(value="pageNumber", defaultValue="1") int pageNumber, 
			@RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			ModelMap model) {

		if (!result.hasErrors()) {
			logger.debug("Server Manager: dati inseriti correttamente"); //TODO i18n		
			try{	
				Server server=ObjectCopier.createObject(formServer, Server.class);
				serverService.updateServer(server);
				formServer.setEsito("formServer.esito.ok");
			}catch(Exception e){
				formServer.setEsito("formServer.esito.notOk");
				logger.error("Err",e); //TODO i18n errore inserimento
			}finally{
				model.addAttribute("serverData",formServer);
			}

		}
		refreshDatagrid(model, pageNumber, rowsPerPage);	
		return "server";


	}


	/*
	 * Method used to edit a server given an id
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET) 
	public String editServer(@PathVariable BigInteger id, 
			@RequestParam(value="pageNumber", defaultValue="1") int pageNumber, 
			@RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			@RequestParam(value="action",required=false) String action,
			ModelMap model) {


		Server server=serverService.getServer(id);

		FormServer formServer=new FormServer();
		formServer.setId(server.getId());
		formServer.setNome(server.getNome());
		formServer.setDescrizione(server.getDescrizione());
		formServer.setIp(server.getIp());


		model.addAttribute("serverData",formServer);
		model.addAttribute("action",action);
		refreshDatagrid(model, pageNumber, rowsPerPage);
		return "server";
	}



	/*
	 * Give the requested page if the id is not specified
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(@RequestParam(value="pageNumber", defaultValue="1") int pageNumber,
			@RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			ModelMap model){

		FormServer formServer=new FormServer();
		refreshDatagrid(model, pageNumber, rowsPerPage);
		model.addAttribute("serverData", formServer);
		//return form view
		return "server";
	}



	/*
	 * Inserts a new server
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String addServer(@Valid @ModelAttribute("serverData") FormServer formServer, 
			BindingResult result, 
			ModelMap model,
			@RequestParam(value="pageNumber", defaultValue="1") int pageNumber,
			@RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage) {

		if (!result.hasErrors()) {
			logger.debug("Server Manager: dati inseriti correttamente"); //TODO i18n		
			try{	
				Server server=ObjectCopier.createObject(formServer, Server.class);
				serverService.insertServer(server);
				formServer.setEsito("formServer.esito.ok");
			}
			catch(Exception e){
				formServer.setEsito("formServer.esito.notOk");
				logger.error("Err",e); //TODO i18n errore inserimento
			}finally{
				model.addAttribute("serverData",formServer);
			}

		}
		refreshDatagrid(model, pageNumber, rowsPerPage);
		return "server";
	}


	protected void refreshDatagrid(ModelMap model, int pageNumber, int rowsPerPage) {
		DataPage<Server> serverPage = serverService.listServer(pageNumber, rowsPerPage);

		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("rowsPerPage", rowsPerPage);
		model.addAttribute("serversPage", serverPage);
	}



}
