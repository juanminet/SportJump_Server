package es.uma.sportjump.sjs.web.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import es.uma.sportjump.sjs.service.services.AuthService;
import es.uma.sportjump.sjs.service.services.UserService;
import es.uma.sportjump.sjs.web.controller.commands.ProfileCommand;

@Component
public class AdminValidator extends BaseValidator {
	
	@Autowired
	UserService userService;	
	
	@Autowired
	AuthService authService;

	
	
	
	protected void checkUserPassword(ProfileCommand userCommand, String nameCommand,BindingResult errors) {
		
		checkMandatory("userName", userCommand.getUserName(), nameCommand, errors);
		checkMandatory("password", userCommand.getPassword(),nameCommand,errors);	
		
		if(errors.hasErrors()){
			return;
		}		
		
		checkPassword(userCommand.getPassword(), userCommand.getRepeatPassword(), errors);
		if(errors.hasErrors()){
			return;
		}
		
		
	}
	protected void checkExistDni(String dni, BindingResult errors){
		if ( userService.existUserByDni(dni)){
			errors.rejectValue("dni", "Exist.athleteCommand.dni");					
		}	
	}
	
	protected void checkUser(String userName, BindingResult errors) {
		if ( userService.existUserByUSerName(userName)){
			errors.rejectValue("userName", "Exist.athleteCommand.userName");					
		}			
	}
	
	

}
