package es.uma.sportjump.sjs.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.service.services.UserService;



@Controller
@SessionAttributes("loggedUser")
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;	

	
	@RequestMapping(method=RequestMethod.GET)
	public String login() {
		return "login";
	}	
	

	@RequestMapping(value="/success",method=RequestMethod.GET)
	public String loginSuccess(Model model) {
		
		String userName = loggedInUserName();
		
		Coach coach = userService.findCoachesByUserName(userName);
		
		model.addAttribute("loggedUser", coach);
		
		return "redirect:/action/home";
	}
	
		
	
	private String loggedInUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	

}
