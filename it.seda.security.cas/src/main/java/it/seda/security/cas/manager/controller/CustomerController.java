/**
 * 
 */
package it.seda.security.cas.manager.controller;

import javax.validation.Valid;

import it.seda.jdbc.commons.DataPage;
import it.seda.security.domain.Customer;
import it.seda.security.service.ManagerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author f.ricci
 *
 */
@Controller
@RequestMapping(value="/customer")
public class CustomerController {

	@Autowired protected ManagerService managerService;
	
	private Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(@RequestParam(value="pageNumber", defaultValue="1") int pageNumber,
			@RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			ModelMap model) {

		Customer customerForm=new Customer();
		refreshDatagrid(model, pageNumber, rowsPerPage);
		model.addAttribute("customerForm", customerForm);

		return "customer_manager";
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(@Valid @ModelAttribute("customerForm") Customer customerForm,
			BindingResult result,
			@RequestParam(value="pageNumber", defaultValue="1") int pageNumber,
			@RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			ModelMap model) {



		return "customer_manager";
	}	
	
	
	protected void refreshDatagrid(ModelMap model, int pageNumber, int rowsPerPage) {
		DataPage<Customer> customerPage=managerService.listCustomer(pageNumber, rowsPerPage);		
	
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("rowsPerPage", rowsPerPage);
		model.addAttribute("customerPage", customerPage);
	}	
	
}
