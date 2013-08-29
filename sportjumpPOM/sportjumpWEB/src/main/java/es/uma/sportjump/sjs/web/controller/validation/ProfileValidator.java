package es.uma.sportjump.sjs.web.controller.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.web.controller.commands.ProfileCommand;

@Component
public class ProfileValidator extends AdminValidator {
	
	public void checkUserProfile(ProfileCommand profileCommand, Coach coach, BindingResult errors){
		checkUserPassword(profileCommand,"profileCommand",errors);
		if(errors.hasErrors()){
			return;
		}
		
		checkModelUserProfile(profileCommand, errors);
		if(errors.hasErrors()){
			return;
		}
		
		if (!profileCommand.getUserName().equals(coach.getUserName())){
			checkUser(profileCommand.getUserName(), errors);
			if(errors.hasErrors()){
				return;
			}
		}	
		
		if(!profileCommand.getDni().equals(coach.getDni())){
			checkExistDni(profileCommand.getDni(), errors);
			if(errors.hasErrors()){
				return;
			}
		}
		
		
	}
	
	
	
	
	
	private void checkModelUserProfile(ProfileCommand profileCommand,BindingResult errors) {
		checkMandatory("name", profileCommand.getName(), "profileCommand", errors);
		checkMandatory("dni", profileCommand.getDni(),"profileCommand",errors);		
		
	
		
		if(errors.hasErrors()){
			return;
		}
		
		checkDni(profileCommand.getDni(),"dni", "Pattern.profileCommand.dni",errors);
		
		String day = profileCommand.getDateBirthDay();
		String month = profileCommand.getDateBirthMonth();
		String year = profileCommand.getDateBirthYear();
		
		if (!(StringUtils.isEmpty(day) && StringUtils.isEmpty(month) && StringUtils.isEmpty(year))){
			String date = ValidationUtils.createDateString(day,month,year, "/");	
			
			checkDate(date,"dateBirthDay", "Pattern.profileCommand.dateBirthDay", errors);
			
			if(errors.hasErrors()){
				return;
			}
		}
			
	}
}
