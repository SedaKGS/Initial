/**
 * 
 */
package it.seda.security.cas.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lmontesi
 *
 */
@Controller
@RequestMapping("/login/casws")
public class CasWebService {
	
	@RequestMapping(value="{ticket}", method = RequestMethod.GET)
	public String getUserDetailsAdapter(@PathVariable String ticket, ModelMap model) {
		
		UserDetails userDetails =  (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		model.addAttribute("model", userDetails);
		return null;
	}

	@RequestMapping(value="{applicationId}", method = RequestMethod.PUT)
	public @ResponseBody String saveApplicationId(@PathVariable String applicationId, HttpServletRequest servletRequest) {
		HttpSession session = servletRequest.getSession();
		session.setAttribute("applicationId", applicationId);
		return "";
	}

	
}
