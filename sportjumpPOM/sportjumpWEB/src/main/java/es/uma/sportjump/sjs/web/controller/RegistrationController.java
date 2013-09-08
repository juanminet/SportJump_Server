package es.uma.sportjump.sjs.web.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uma.sportjump.sjs.service.services.AuthService;
import es.uma.sportjump.sjs.service.services.UserService;
import es.uma.sportjump.sjs.web.constants.AuthConstants;
import es.uma.sportjump.sjs.web.controller.commands.ProfileCommand;
import es.uma.sportjump.sjs.web.controller.validation.ProfileValidator;



@Controller
public class RegistrationController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private ProfileValidator profileValidator;
	
	protected static final String REGISTRATION = "registration";
	protected static final String LOGIN =  "login";

	
	
	
	
	
	@RequestMapping(value="/registration",method=RequestMethod.GET)
	public String login(Model model) {
		
		initRegistration(model);
		return REGISTRATION;
	}

	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String createUser(@Valid ProfileCommand profileCommand,BindingResult errors, Model model, HttpSession session) {
		
		profileValidator.checkNewUser(profileCommand, errors);
		if (errors.hasErrors()) {
			model.addAttribute("errorProfile", true);
			return REGISTRATION;
		}

		createProfile(profileCommand);

		return LOGIN;
	}
	
	private void initRegistration(Model model){
		ProfileCommand profileCommand = new ProfileCommand();
		model.addAttribute("profileCommand", profileCommand);
	}
	
	private void createProfile(ProfileCommand profileCommand) {
		
		//crate coach authorization		
		authService.addUser(profileCommand.getUserName(), profileCommand.getPassword(), AuthConstants.ROLE_COACH);
		
		//create athlete data		
		userService.setNewCoach(profileCommand.getName(),
				profileCommand.getUserName(),
				profileCommand.getSurname(),
				profileCommand.getDni(),
				profileCommand.getEmail(),
				profileCommand.getType(),
				profileCommand.getAddress(),
				profileCommand.getComments(),
				profileCommand.getTelephone(),
				getDate(profileCommand));
	}
	
	private Date getDate(ProfileCommand profileCommand){
		Date resDate = null;
		if (!StringUtils.isEmpty(profileCommand.getDateBirthDay())  &&
		!StringUtils.isEmpty(profileCommand.getDateBirthMonth()) &&
		!StringUtils.isEmpty(profileCommand.getDateBirthDay())){
		
			int day = Integer.valueOf(profileCommand.getDateBirthYear());
			int month = Integer.valueOf(profileCommand.getDateBirthMonth());
			int year = Integer.valueOf(profileCommand.getDateBirthYear());
			Calendar calendar = new GregorianCalendar(year, month, day);
			resDate = calendar.getTime();
		}
		
		return resDate;
	}
}
