package es.uma.sportjump.sjs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.uma.sportjump.sjs.service.services.AuthService;
import es.uma.sportjump.sjs.service.services.UserService;
import es.uma.sportjump.sjs.web.constants.AuthConstants;



@Controller
public class AuthController {

	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/registration",method=RequestMethod.GET)
	public String login() {
		return "registration";
	}
	
	@RequestMapping(value="/registration",method=RequestMethod.POST)
	public String createUser(	@RequestParam("user")String username, 
								@RequestParam("password") String password,
								@RequestParam("name")String name, 
								@RequestParam("surname") String surname,
								@RequestParam("dni") String dni,
								@RequestParam("email") String email ) {
		
		
		if (!authService.existUser(username)) {
			
			authService.addUser(username, password, AuthConstants.ROLE_COACH);
			
			addUserToSportJump(username,name,surname,dni,email);
			
		}
		return "login";
	}	
	
	private void addUserToSportJump(String userName, String name, String surname, String dni, String email) {
		userService.setNewCoach(name, userName, surname, dni, email, null, null, null, null, null);		
	}

}
