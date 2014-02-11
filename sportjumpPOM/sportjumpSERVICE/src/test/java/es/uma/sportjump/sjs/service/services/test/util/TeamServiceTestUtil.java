package es.uma.sportjump.sjs.service.services.test.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Team;
import es.uma.sportjump.sjs.service.services.UserService;

@Component
public class TeamServiceTestUtil {
	
	
	@Autowired
	UserService userService;
	
	
	public Team createTeam(int num, Coach coach) {
		//Variables
		String name = "Fondistas" + num;
		String type = "fondo" + num;
		String description = "hacemos mucho fondo" + num;
		
		Long idTeam = userService.setNewTeam(name, type, description, new Date(), coach);
		
		return userService.findTeam(idTeam);
	}
	
	
	public void removeTeam(Team team){
		userService.removeTeam(team);
	}
	
}
