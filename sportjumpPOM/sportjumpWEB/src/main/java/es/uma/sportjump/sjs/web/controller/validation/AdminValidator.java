package es.uma.sportjump.sjs.web.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import es.uma.sportjump.sjs.model.entities.Athlete;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.service.services.UserService;
import es.uma.sportjump.sjs.web.controller.commands.AthleteCommand;

@Component
public class AdminValidator extends BaseValidator {
	
	@Autowired
	UserService userService;	

	
	public void checkUserPassword(AthleteCommand athleteCommand,Model model, BindingResult errors) {
		
		checkMandatory("userName", athleteCommand.getUserName(), "athleteCommand", errors);
		checkMandatory("password", athleteCommand.getPassword(),"athleteCommand",errors);	
		
		if(errors.hasErrors()){
			return;
		}
		
		//Service
		checkUser( athleteCommand, errors);
		
		if(errors.hasErrors()){
			return;
		}
		
		checkPassword( athleteCommand, errors);
	}
	
	public void checkAthlete(AthleteCommand athleteCommand,BindingResult errors) {
		checkMandatory("name", athleteCommand.getName(), "athleteCommand", errors);
		checkMandatory("dni", athleteCommand.getDni(),"athleteCommand",errors);
		checkMandatory("idTeam", athleteCommand.getIdTeam(),"athleteCommand",errors);
		String date = ValidationUtils.createDateString(athleteCommand.getDateBirthDay(),athleteCommand.getDateBirthMonth(),athleteCommand.getDateBirthYear(), "/");	
		
		checkMandatory("dateBirthDay", date, "athleteCommand", errors);
		
		if(errors.hasErrors()){
			return;
		}
		
		checkDni(athleteCommand.getDni(),"dni", "Pattern.athleteCommand.dni",errors);
		checkDate(date,"dateBirthDay", "Pattern.athleteCommand.dateBirthDay", errors);
		
		if(errors.hasErrors()){
			return;
		}
		
		//Service
		checkExistDni(athleteCommand,errors);		
	}
	
	private void checkUser(AthleteCommand athleteCommand, BindingResult errors) {
		if ( userService.existUserByUSerName(athleteCommand.getUserName())){
			errors.rejectValue("userName", "Exist.athleteCommand.userName");					
		}			
	}
	
	
	private void checkPassword(AthleteCommand athleteCommand,BindingResult errors) {
		if(!athleteCommand.getPassword().equals(athleteCommand.getRepeatPassword())){
			errors.rejectValue("repeatPassword", "Different.athleteCommand.repeatPassword");
		}
	}
	
	private void checkExistDni(AthleteCommand athleteCommand, BindingResult errors){
		if ( userService.existUserByDni(athleteCommand.getDni())){
			errors.rejectValue("userName", "Exist.athleteCommand.dni");					
		}	
	}
	
	
	


}
