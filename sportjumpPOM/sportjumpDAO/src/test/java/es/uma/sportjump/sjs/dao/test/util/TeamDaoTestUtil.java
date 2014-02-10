package es.uma.sportjump.sjs.dao.test.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uma.sportjump.sjs.dao.daos.UserDao;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Team;


@Component
public class TeamDaoTestUtil {
	
	@Autowired
	UserDao userDao;
	
	public Long createTeam(String name, Coach coach) {
		
		//Create team
		String type = "Sprinters";
		String description = "Group for sprinters";
		Date dateCreate = new Date();
		
		Team team = new Team();
		team.setName(name);
		team.setType(type);
		team.setDescription(description);
		team.setDateCreate(dateCreate);
		team.setCoach(coach);		
	
		//Persist team		
		userDao.persistTeam(team);
		

		//return idTeam
		return Long.valueOf(team.getIdTeam());	
	}
	
	public Team readTeam(Long idTeam){		
		
		//Read Team
		Team team = userDao.getTeamById(idTeam);
		
		return team;
	}
	
	public void deleteTeam(Team team){
		
		//Remove team
		userDao.deleteTeam(Long.valueOf(team.getIdTeam()));
	}

}
