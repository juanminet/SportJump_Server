package es.uma.sportjump.sjs.web.controller.validation;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import es.uma.sportjump.sjs.model.entities.Athlete;
import es.uma.sportjump.sjs.web.controller.commands.AthleteCommand;

@Component
public class AthleteValidator extends AdminValidator {
	public void checkAthleteNew(AthleteCommand athleteCommand,Model model, BindingResult errors){
		checkUserPassword(athleteCommand,"athleteCommand", errors);
		if(errors.hasErrors()){
			return;
		}
		
		checkModelAthlete(athleteCommand, errors);
		if(errors.hasErrors()){
			return;
		}
		
		checkUser(athleteCommand.getUserName(), errors);
		if(errors.hasErrors()){
			return;
		}
		
		checkExistDni(athleteCommand.getDni(), errors);
		if(errors.hasErrors()){
			return;
		}
		
	}
	
	public void checkAthleteModified(AthleteCommand athleteCommand, Athlete athlete, Model model, BindingResult errors){
		checkUserPassword(athleteCommand,"athleteCommand",errors);
		if(errors.hasErrors()){
			return;
		}
		
		checkModelAthlete(athleteCommand, errors);
		if(errors.hasErrors()){
			return;
		}
		
		if (!athleteCommand.getUserName().equals(athlete.getUserName())){
			checkUser(athleteCommand.getUserName(), errors);
			if(errors.hasErrors()){
				return;
			}
		}	
		
		if(!athleteCommand.getDni().equals(athlete.getDni())){
			checkExistDni(athleteCommand.getDni(), errors);
			if(errors.hasErrors()){
				return;
			}
		}
		
		
	}
	
	
	
	
	
	private void checkModelAthlete(AthleteCommand athleteCommand,BindingResult errors) {
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
			
	}
}
