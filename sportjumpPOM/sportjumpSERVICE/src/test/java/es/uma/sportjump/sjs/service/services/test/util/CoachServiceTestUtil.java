package es.uma.sportjump.sjs.service.services.test.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.service.services.UserService;

@Component
public class CoachServiceTestUtil {
	
	@Autowired
	private UserService userService;
	
	
	public Coach createNewCoach(){
		
		String coachName1 = "Jose";
		String coachUserName1 =  "Mourinho";
		String coachSurname1 =  "Fernandez";
		String coachEmail1 ="asdf@asdf.com";
		String coachDni1 = "11111111P";
		
		Long idCoach = userService.setNewCoach(coachName1, coachUserName1, coachSurname1, coachDni1, coachEmail1, null, null, null, null, null);
		
		Coach coach = userService.findCoach(idCoach);
		
		return coach;
	}


	public void removeCoach(Coach coach) {
		userService.removeCoach(coach);
	}

}
