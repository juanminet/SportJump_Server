package es.uma.sportjump.sjs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/error")
public class ErrorController {
	
	@RequestMapping(value = "/error", method = { RequestMethod.GET, RequestMethod.POST })
	public String error(Model model) {		
		return "error_page";
		
	}
	
	@RequestMapping(value = "/denied",method = { RequestMethod.GET, RequestMethod.POST })
	public String accesDenied(Model model) {		
		return "access_denied_authorization_page";		
	}	
	
	
	@RequestMapping(value = "/denied/authorization",method = { RequestMethod.GET, RequestMethod.POST })
	public String accesDeniedAuthorization(Model model) {		
		return "access_denied_authorization_page";		
	}	
	
	@RequestMapping(value = "/denied/authentication",method = { RequestMethod.GET, RequestMethod.POST })
	public String accesDeniedAuthentication(Model model) {		
		return "access_denied_authentication_page";		
	}	
	
}
