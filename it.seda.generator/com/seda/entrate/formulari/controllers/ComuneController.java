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



@Controller
@RequestMapping(value="/ANCOMUTB")
public class ComuneController {

    @Inject ComuneService comuneService;
    private Logger logger = LoggerFactory.getLogger(ComuneController.class);
	
    /*
     * Method used to delete  Comune by primary key
    */
    @RequestMapping(value="/{societa}/{codiceBelfiore}", 
                    method=RequestMethod.DELETE) 
    public String deleteName(
                             @PathVariable java.lang.String societa,
                             @PathVariable java.lang.String codiceBelfiore,
                             @RequestParam(value="pageNumber", defaultValue="1")  int pageNumber, 
                             @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
                             ModelMap model) {
			                   
    Comune comune=new Comune();
    comune.setSocieta(societa);
    comune.setCodiceBelfiore(codiceBelfiore);
    comuneService.deleteComune(comune);
    ComuneForm comuneForm=new ComuneForm();
    comuneForm.setEsito("comuneForm.esito.cancel");
    refreshDatagrid(model, pageNumber, rowsPerPage);
    model.addAttribute("comuneData", comune);
    return "comune";
    }
	

    /*
     * Method used to update  Comune
    */
    @RequestMapping(method=RequestMethod.PUT) 
    public String updateComune(
                                    @Valid @ModelAttribute("comuneData") ComuneForm comuneForm,
                                    BindingResult result,
                                    @RequestParam(value="pageNumber", defaultValue="1") int pageNumber, 
                                    @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
                                    ModelMap model) {
    
    if (!result.hasErrors()) {
    logger.debug("ComuneController: dati inseriti correttamente"); //TODO i18n		
	try{	
        Comune comune =ObjectCopier.createObject(comuneForm, Comune.class);
        comuneService.updateComune(comune);
        comuneForm.setEsito("comuneForm.esito.ok");
       }catch(Exception e){
        comuneForm.setEsito("comuneForm.esito.notOk");
        logger.error("Err",e); //TODO i18n errore inserimento
       }finally{
        model.addAttribute("comuneForm",comuneForm);
       }
     }
     refreshDatagrid(model, pageNumber, rowsPerPage);				
     return "comune";
     }
	

    /*
     * Method used to edit Comune given the primary key
    */
    @RequestMapping(value="/{societa}/{codiceBelfiore}", 
                    method=RequestMethod.GET) 
    public String editComune(
                            @PathVariable java.lang.String societa,
                            @PathVariable java.lang.String codiceBelfiore,
                            @RequestParam(value="pageNumber", defaultValue="1") int pageNumber, 
                            @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
                            @RequestParam(value="action",required=false) String action,
                            ModelMap model) {
     Comune comune=new Comune();
     comune.setSocieta(societa);
     comune.setCodiceBelfiore(codiceBelfiore);
     comune=comuneService.getComuneById(comune);
     ComuneForm comuneForm=new ComuneForm();
     model.addAttribute("comuneData",comuneForm);
     model.addAttribute("action",action);
     refreshDatagrid(model, pageNumber, rowsPerPage);
     return "comune";
    }
	
			
    /*
     * Give a void page if the primary key is not specified
    */
    @RequestMapping(method = RequestMethod.GET)
    public String initForm(
                           @RequestParam(value="pageNumber", defaultValue="1") int pageNumber,
                           @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
                           ModelMap model){
 
     ComuneForm comuneForm=new ComuneForm();
     refreshDatagrid(model, pageNumber, rowsPerPage);
     model.addAttribute("comuneData", comuneForm);
     return "comune";
    }

	/*
	 * Inserts a new Comune
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String addComune(@Valid @ModelAttribute("comuneData") ComuneForm comuneForm, 
                                 BindingResult result, 
                                 ModelMap model,
                                 @RequestParam(value="pageNumber", defaultValue="1") int pageNumber,
                                 @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage) {
            
		if (!result.hasErrors()) {
			logger.debug("ComuneController: dati inseriti correttamente"); //TODO i18n		
			try{
			    Comune comune=ObjectCopier.createObject(comuneForm, Comune.class);
				comuneService.insertComune(comune);
				comuneForm.setEsito("comuneForm.esito.ok");
			}catch(Exception e){
				comuneForm.setEsito("comuneForm.esito.notOk");
				logger.error("Err",e); //TODO i18n errore inserimento
			}finally{
				model.addAttribute("comuneForm",comuneForm);
			}
		}
		refreshDatagrid(model, pageNumber, rowsPerPage);
		return "comune";
	}
	
    /*
     * Refresh datagrid
    */
     protected void refreshDatagrid(ModelMap model, int pageNumber, int rowsPerPage) {	
        int totalRows=comuneService.listComuneCount();
        RowBoundsHelper rbh = new RowBoundsHelper(rowsPerPage, pageNumber);
        List<Comune> ar=comuneService.listComune(rbh.buildRowBounds());	
        Page<Comune> page = new Page<Comune>(ar);
        rbh.decorate(page, totalRows);   
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("rowsPerPage", rowsPerPage);
        model.addAttribute("ComunePage", page);
     }	
	
	
		
		
		
}





