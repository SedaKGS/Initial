package  com.seda.entrate.formulari.controllers;

import it.seda.sem.domain.ObjectCopier;
import it.seda.sem.jdbc.RowBoundsHelper;
import it.seda.template.taglib.DatagridTag.Page;
import com.seda.entrate.bind.ComuneForm;
import com.seda.entrate.formulari.domain.Comune;
import com.seda.entrate.formulari.service.ComuneService;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.apache.ibatis.session.RowBounds;
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
import it.seda.ws.restfull.data.WebServiceOutput;



@Controller
@RequestMapping(value="/ANCOMUTB")
public class ComuneControllerWS {

    @Inject ComuneService comuneService;
    private Logger logger = LoggerFactory.getLogger(ComuneControllerWS.class);
	
    /*
     * Method used to delete  Comune by primary key
    */
    @RequestMapping(value="/{societa}/{codiceBelfiore}", 
                    method=RequestMethod.DELETE) 
    public WebServiceOutput deleteName(
                             @PathVariable java.lang.String societa,
                             @PathVariable java.lang.String codiceBelfiore,
                             @RequestParam(value="pageNumber", defaultValue="1")  int pageNumber, 
                             @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
                             ModelMap model) {
			                   
    Comune comune=new Comune();
    comune.setSocieta(societa);
    comune.setCodiceBelfiore(codiceBelfiore);
    comuneService.deleteComune(comune);
    
    WebServiceOutput  deleteOut = new WebServiceOutput ();
    deleteOut.setStatus("OK");
    deleteOut.setTotalPages(0);
	deleteOut.setTotalRows(0);
	deleteOut.setPageSize(15);
	deleteOut.setPageNumber(1);
    return deleteOut;
    }
	

    /*
     * Method used to update  Comune
    */
    @RequestMapping(method=RequestMethod.PUT) 
    public WebServiceOutput updateComune(
                                    @Valid @ModelAttribute("comuneData") ComuneForm comuneForm,
                                    BindingResult result,
                                    @RequestParam(value="pageNumber", defaultValue="1") int pageNumber, 
                                    @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
                                    ModelMap model) {
                                    
      WebServiceOutput  updateOut = new WebServiceOutput ();
  
    updateOut.setTotalPages(0);
	updateOut.setTotalRows(0);
	updateOut.setPageSize(15);
	updateOut.setPageNumber(1);
    updateOut.setStatus("OK");                            
    
    if (!result.hasErrors()) {
    logger.debug("ComuneControllerWS: dati inseriti correttamente"); //TODO i18n		
	try{	
        Comune comune =ObjectCopier.createObject(comuneForm, Comune.class);
        comuneService.updateComune(comune);
        comuneForm.setEsito("comuneForm.esito.ok");
       
       }catch(Exception e){
        comuneForm.setEsito("comuneForm.esito.notOk");
        logger.error("Err",e); //TODO i18n errore inserimento
        updateOut.setStatus("KO");
       }finally{
        model.addAttribute("comuneForm",comuneForm);
       }
     }		
    return updateOut;  
     }
	

    /*
     * Method used to edit Comune given the primary key
    */
    @RequestMapping(value="/{societa}/{codiceBelfiore}", 
                    method=RequestMethod.GET) 
    public WebServiceOutput editComune(
                            @PathVariable java.lang.String societa,
                            @PathVariable java.lang.String codiceBelfiore,
                            @RequestParam(value="pageNumber", defaultValue="1") int pageNumber, 
                            @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
                            @RequestParam(value="action",required=false) String action,
                            ModelMap model) {
     Comune comune=new Comune();
     comune.setSocieta(societa);
     comune.setCodiceBelfiore(codiceBelfiore);
  
     WebServiceOutput editOutput= comuneService.getComuneByIdWS(comune);
     return editOutput;
    }
	
			

	/*
	 * Inserts a new Comune
	 */
	@RequestMapping(method = RequestMethod.POST)
	public WebServiceOutput addComune(@Valid @ModelAttribute("comuneData") ComuneForm comuneForm, 
                                 BindingResult result, 
                                 ModelMap model,
                                 @RequestParam(value="pageNumber", defaultValue="1") int pageNumber,
                                 @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage) {
                                 
                                 
         WebServiceOutput  insertOut = new WebServiceOutput ();
  
    insertOut.setTotalPages(0);
	insertOut.setTotalRows(0);
	insertOut.setPageSize(15);
	insertOut.setPageNumber(1);
    insertOut.setStatus("OK");                                 
            
		if (!result.hasErrors()) {
			logger.debug("ComuneControllerWS: dati inseriti correttamente"); //TODO i18n		
			try{
			    Comune comune=ObjectCopier.createObject(comuneForm, Comune.class);
				comuneService.insertComune(comune);
				comuneForm.setEsito("comuneForm.esito.ok");
			}catch(Exception e){
				comuneForm.setEsito("comuneForm.esito.notOk");
				logger.error("Err",e); //TODO i18n errore inserimento
				 insertOut.setStatus("KO");     
			}finally{
				model.addAttribute("comuneForm",comuneForm);
			}
		}
	
		return insertOut;
	}
	
  
	
	
		
		
		
}





