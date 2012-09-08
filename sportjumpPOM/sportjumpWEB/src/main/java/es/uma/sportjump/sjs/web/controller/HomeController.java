package es.uma.sportjump.sjs.web.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("loggedInUserName")
@RequestMapping("/action/home")
public class HomeController {

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String index(Model model) {		
		model.addAttribute("loggedInUserName", loggedInUserName());	
		return "home";
		
	}
	
	private String loggedInUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
