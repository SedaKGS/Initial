package it.seda.sem.mvc.manager;
import it.seda.sem.manager.service.ServerService;
import it.seda.sem.mvc.manager.models.FormTestBind;

import java.util.LinkedHashMap;
import java.util.Map;

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
	
	private Map<String,String> daysOfMonth = new LinkedHashMap<String,String>(31);
	private Map<String,String> monthsOfYear = new LinkedHashMap<String,String>(12);	
	private Map<String,String> years = new LinkedHashMap<String,String>(10);	
	
	private Map<String,String> hoursOfDay = new LinkedHashMap<String,String>(24);	
	private Map<String,String> minutesOfHour = new LinkedHashMap<String,String>(60);
	private Map<String,String> secondsOfMinute = new LinkedHashMap<String,String>(60);
	
	public TestBindController() {
		for (int i = 0; i < 31; i++) {
			daysOfMonth.put(String.valueOf(i+1), String.valueOf(i+1));
		}
		
		for (int i = 0; i < 12; i++) {
			monthsOfYear.put(String.valueOf(i+1), String.valueOf(i+1));
		}
		
		for (int i = 2008; i < 2008+10; i++) {
			years.put(String.valueOf(i+1), String.valueOf(i+1));
		}
		
		for (int i = 0; i < 24; i++) {
			hoursOfDay.put(String.valueOf(i+1), String.valueOf(i+1));
		}

		for (int i = 0; i < 60; i++) {
			minutesOfHour.put(String.valueOf(i+1), String.valueOf(i+1));
			secondsOfMinute.put(String.valueOf(i+1), String.valueOf(i+1));			
		}								
	}

	
	/*
	 * Give the requested page if the id is not specified
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(@RequestParam(value="pageNumber", defaultValue="1") int pageNumber,
			               @RequestParam(value="rowsPerPage",defaultValue="15") int rowsPerPage,
			               ModelMap model){
 
		FormTestBind formTestBind=new FormTestBind();
		model.addAttribute("testData", formTestBind);

		addChronos(model);
		
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

		addChronos(model);		
		
		return "testBind";
	}
	
	
	protected void addChronos(ModelMap model) {
		model.addAttribute("daysOfMonth", daysOfMonth);
		model.addAttribute("monthsOfYear", monthsOfYear);
		model.addAttribute("years", years);
		
		model.addAttribute("hoursOfDay", hoursOfDay);		
		model.addAttribute("minutesOfHour", minutesOfHour);
		model.addAttribute("secondsOfMinute", secondsOfMinute);		
	}
}
