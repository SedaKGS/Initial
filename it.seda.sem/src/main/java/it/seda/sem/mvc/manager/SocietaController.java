package it.seda.sem.mvc.manager;

import it.seda.jdbc.commons.DataPage;
import it.seda.sem.domain.ObjectCopier;
import it.seda.sem.domain.Societa;
import it.seda.sem.manager.service.SocietaService;
import it.seda.sem.mvc.manager.models.FormSocieta;

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
@RequestMapping(value="/societa")
public class SocietaController {
	
    @Inject SocietaService societaService;
	
	private Logger logger = LoggerFactory.getLogger(SocietaController.class);
	
	
	
	/*
	 * Method used to delete a societa by id
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) 
	public String deleteSocieta(@PathVariable BigInteger id, 
			               @RequestParam(value="pageNumber", defaultValue="1") int pageNumber, 
			               @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			               ModelMap model) {
    
		
	societaService.deleteSocieta(id);
	FormSocieta societa=new FormSocieta();
	societa.setEsito("formSocieta.esito.cancel");
	refreshDatagrid(model, pageNumber, rowsPerPage);
	
	model.addAttribute("societaData", societa);
	return "societa";
	}
	
	
	/*
	 * Method used to update a society
	 */
	
	@RequestMapping(method=RequestMethod.PUT) 
	public String updateSocieta(
			               @Valid @ModelAttribute("societaData") FormSocieta formSocieta,
			               BindingResult result,
			               @RequestParam(value="pageNumber", defaultValue="1") int pageNumber, 
			               @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			               ModelMap model) {
    
		if (!result.hasErrors()) {
			logger.debug("Client Manager: dati inseriti correttamente"); //TODO i18n		
			try{	
				Societa societa=ObjectCopier.createObject(formSocieta, Societa.class);
				societaService.updateSocieta(societa);
				formSocieta.setEsito("formSocieta.esito.ok");
			}catch(Exception e){
				formSocieta.setEsito("formSocieta.esito.notOk");
				logger.error("Err",e); //TODO i18n errore inserimento
			}finally{
				model.addAttribute("societaData",formSocieta);
			}

		}
		refreshDatagrid(model, pageNumber, rowsPerPage);	
		return "societa";
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
	
		
	Societa societa=societaService.getSocieta(id); 
    FormSocieta formSocieta=new FormSocieta();
    formSocieta.setId(societa.getId());
    formSocieta.setNome(societa.getNome());
    formSocieta.setDescrizione(societa.getDescrizione());
    
    model.addAttribute("societaData",formSocieta);
    model.addAttribute("action",action);
	refreshDatagrid(model, pageNumber, rowsPerPage);
	return "societa";
	}
	
	/*
	 * Give the requested page if the id is not specified
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(@RequestParam(value="pageNumber", defaultValue="1") int pageNumber,
			               @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			               ModelMap model){
 
		FormSocieta formSocieta=new FormSocieta();
		refreshDatagrid(model, pageNumber, rowsPerPage);
		model.addAttribute("societaData", formSocieta);
		//return form view
		return "societa";
	}
	
	/*
	 * Inserts a new society
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String addClient(@Valid @ModelAttribute("societaData") FormSocieta formSocieta, 
			BindingResult result, 
			ModelMap model,
			@RequestParam(value="pageNumber", defaultValue="1") int pageNumber,
            @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage) {

		if (!result.hasErrors()) {
			logger.debug("Societa Manager: dati inseriti correttamente"); //TODO i18n		
			try{	
				Societa societa=ObjectCopier.createObject(formSocieta, Societa.class);
				societaService.insertSocieta(societa);
				formSocieta.setEsito("formSocieta.esito.ok");
			}
			catch(Exception e){
				formSocieta.setEsito("formSocieta.esito.notOk");
				logger.error("Err",e); //TODO i18n errore inserimento
			}finally{
				model.addAttribute("societaData",formSocieta);
			}

		}

		refreshDatagrid(model, pageNumber, rowsPerPage);
				
		
		return "societa";
	}
	
	
	protected void refreshDatagrid(ModelMap model, int pageNumber, int rowsPerPage) {
		DataPage<Societa> societaPage =societaService.listSocieta(pageNumber, rowsPerPage);
		
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("rowsPerPage", rowsPerPage);
		model.addAttribute("societaPage", societaPage);
	}
	
	
}
