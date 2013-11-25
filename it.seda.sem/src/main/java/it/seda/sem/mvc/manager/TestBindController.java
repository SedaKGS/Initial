package it.seda.sem.mvc.manager;
import it.seda.sem.domain.ObjectCopier;
import it.seda.sem.domain.Server;
import it.seda.sem.jdbc.RowBoundsHelper;
import it.seda.sem.manager.service.ServerService;
import it.seda.sem.mvc.manager.models.FormServer;
import it.seda.template.taglib.DatagridTag.Page;

import java.math.BigInteger;
import java.util.List;

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
public class TestBindController {
	
    @Inject ServerService serverService;
	
	private Logger logger = LoggerFactory.getLogger(TestBindController.class);

	/*
	 * Give the requested page if the id is not specified
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(@RequestParam(value="pageNumber", defaultValue="1") int pageNumber,
			               @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			               ModelMap model){
 
		FormServer formServer=new FormServer();
		model.addAttribute("testData", formServer);
		return "testBind";
	}
	
	/*
	 * Inserts a new server
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String addServer(@Valid @ModelAttribute("clientData") FormServer formServer, 
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

		return "testBind";
	}
	
}
