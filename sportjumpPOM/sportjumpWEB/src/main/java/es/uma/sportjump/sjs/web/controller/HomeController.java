package es.uma.sportjump.sjs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/action/home")
public class HomeController {
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String home(Model model) {		
		return "home";
		
	}	
	
}
