package it.seda.sem.mvc.manager;
import it.seda.sem.manager.service.ServerService;
import it.seda.sem.mvc.manager.models.FormTestBind;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value="/testbind")
public class TestBindController {
	
    @Inject ServerService serverService;
	
	/*
	 * Give the requested page if the id is not specified
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(@RequestParam(value="pageNumber", defaultValue="1") int pageNumber,
			               @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			               ModelMap model){
 
		FormTestBind formTestBind=new FormTestBind();
		model.addAttribute("testData", formTestBind);

		return "testBind";
	}
	
	/*
	 * Inserts a new server
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String addServer(@Valid @ModelAttribute("testData") FormTestBind formTestBind, 
			BindingResult result, 
			ModelMap model) {

		model.addAttribute("testData",formTestBind);

		return "testBind";
	}
	

}
