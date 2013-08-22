package es.uma.sportjump.sjs.web.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.uma.sportjump.sjs.service.services.UserService;



@Controller
public class AuthController {

	private static final String ROLE_USER = "ROLE_USER";

	@Autowired
	private UserDetailsManager userDetailsManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SaltSource saltSource;
	
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
		
		
		if (!userDetailsManager.userExists(username)) {
			
			addUserToAuthSystem(username,password);
			
			addUserToSportJump(username,name,surname,dni,email);
			
		}
		return "login";
	}


	private void addUserToAuthSystem(String username, String password) {
		List<GrantedAuthority> authorites = new ArrayList<GrantedAuthority>();
		authorites.add(new SimpleGrantedAuthority(ROLE_USER));
		
		
		User user = new User(username, password, true, false, false, false, authorites);
		User saltedUser = new User(username, passwordEncoder.encodePassword(password, saltSource.getSalt(user)), 
				true, false, false, false, authorites);
		
		userDetailsManager.createUser(saltedUser);		
	}
	
	
	private void addUserToSportJump(String userName, String name, String surname, String dni, String email) {
		userService.setNewCoach(name, userName, surname, dni, email, null, null, null, null, null);		
	}

}
