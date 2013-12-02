package  it.seda.sem.mvc.manager;

import it.seda.sem.domain.ObjectCopier;
import it.seda.sem.jdbc.RowBoundsHelper;
import it.seda.template.taglib.DatagridTag.Page;
import it.seda.sem.mvc.manager.models.FormForm;
import it.seda.sem.domain.Test;
import it.seda.sem.manager.service.TestService;
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
@RequestMapping(value="/test")
public class TestController {

    @Inject TestService testService;
    private Logger logger = LoggerFactory.getLogger(TestController.class);
	
    /*
     * Method used to delete  Test by primary key
    */
    @RequestMapping(value="/{id}", 
                    method=RequestMethod.DELETE) 
    public String deleteName(
                             @PathVariable java.lang.Integer id,
                             @RequestParam(value="pageNumber", defaultValue="3")  int pageNumber, 
                             @RequestParam(value="rowsPerPage",defaultValue="18") int rowsPerPage,
                             ModelMap model) {
			                   
    Test test=new Test();
    test.setId(id);
    testService.deleteTest(test);
    FormForm formForm=new FormForm();
    formForm.setEsito("formForm.esito.cancel");
    refreshDatagrid(model, pageNumber, rowsPerPage);
    model.addAttribute("testData", test);
    return "test";
    }
	

    /*
     * Method used to update  Test
    */
    @RequestMapping(method=RequestMethod.PUT) 
    public String updateTest(
                                    @Valid @ModelAttribute("testData") FormForm formForm,
                                    BindingResult result,
                                    @RequestParam(value="pageNumber", defaultValue="3") int pageNumber, 
                                    @RequestParam(value="rowsPerPage",defaultValue="18") int rowsPerPage,
                                    ModelMap model) {
    
    if (!result.hasErrors()) {
    logger.debug("TestController: dati inseriti correttamente"); //TODO i18n		
	try{	
        Test test =ObjectCopier.createObject(formForm, Test.class);
        testService.updateTest(test);
        formForm.setEsito("formForm.esito.ok");
       }catch(Exception e){
        formForm.setEsito("formForm.esito.notOk");
        logger.error("Err",e); //TODO i18n errore inserimento
       }finally{
        model.addAttribute("formForm",formForm);
       }
     }
     refreshDatagrid(model, pageNumber, rowsPerPage);				
     return "test";
     }
	

    /*
     * Method used to edit Test given the primary key
    */
    @RequestMapping(value="/{id}", 
                    method=RequestMethod.GET) 
    public String editTest(
                            @PathVariable java.lang.Integer id,
                            @RequestParam(value="pageNumber", defaultValue="3") int pageNumber, 
                            @RequestParam(value="rowsPerPage",defaultValue="18") int rowsPerPage,
                            @RequestParam(value="action",required=false) String action,
                            ModelMap model) {
     Test test=new Test();
     test.setId(id);
     test=testService.getTestById(test);
     FormForm formForm=new FormForm();
     model.addAttribute("testData",formForm);
     model.addAttribute("action",action);
     refreshDatagrid(model, pageNumber, rowsPerPage);
     return "test";
    }
	
			
    /*
     * Give a void page if the primary key is not specified
    */
    @RequestMapping(method = RequestMethod.GET)
    public String initForm(
                           @RequestParam(value="pageNumber", defaultValue="3") int pageNumber,
                           @RequestParam(value="rowsPerPage",defaultValue="18") int rowsPerPage,
                           ModelMap model){
 
     FormForm formForm=new FormForm();
     refreshDatagrid(model, pageNumber, rowsPerPage);
     model.addAttribute("testData", formForm);
     return "test";
    }

	/*
	 * Inserts a new Test
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String addTest(@Valid @ModelAttribute("testData") FormForm formForm, 
                                 BindingResult result, 
                                 ModelMap model,
                                 @RequestParam(value="pageNumber", defaultValue="3") int pageNumber,
                                 @RequestParam(value="rowsPerPage",defaultValue="18") int rowsPerPage) {
            
		if (!result.hasErrors()) {
			logger.debug("TestController: dati inseriti correttamente"); //TODO i18n		
			try{
			    Test test=ObjectCopier.createObject(formForm, Test.class);
				testService.insertTest(test);
				formForm.setEsito("formForm.esito.ok");
			}catch(Exception e){
				formForm.setEsito("formForm.esito.notOk");
				logger.error("Err",e); //TODO i18n errore inserimento
			}finally{
				model.addAttribute("formForm",formForm);
			}
		}
		refreshDatagrid(model, pageNumber, rowsPerPage);
		return "test";
	}
	
    /*
     * Refresh datagrid
    */
     protected void refreshDatagrid(ModelMap model, int pageNumber, int rowsPerPage) {	
        int totalRows=testService.listTestCount();
        RowBoundsHelper rbh = new RowBoundsHelper(rowsPerPage, pageNumber);
        List<Test> ar=testService.listTest(rbh.buildRowBounds());	
        Page<Test> page = new Page<Test>(ar);
        rbh.decorate(page, totalRows);   
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("rowsPerPage", rowsPerPage);
        model.addAttribute("TestPage", page);
     }	
	
	
		
		
		
}





