package es.uma.sportjump.sjs.dao.test.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uma.sportjump.sjs.dao.daos.UserDao;
import es.uma.sportjump.sjs.model.entities.Coach;


@Component
public class CoachDaoTestUtil {
	
	@Autowired
	UserDao userDao;
	
	public Long createCoach(String name, String userName, String dni) {	
		
		//Create coach
		Coach coach = new Coach();
		coach.setName(name);
		coach.setUserName(userName);
		coach.setDni(dni);
		
		//Persist coach		
		userDao.persistCoach(coach);
		

		//return idCoach
		return Long.valueOf(coach.getIdUser());	
	}
	
	public Coach readCoach(Long idCoach){		
		
		//Read Coach
		Coach coach = userDao.getCoachById(idCoach);
		
		return coach;
	}
	
	public void deleteCoach(Coach coach){
		
		//Remove coach
		userDao.deleteCoach(Long.valueOf(coach.getIdUser()));
	}

	public Coach createNewCoach() {
		//Initialize variables
		String name = "Jose";
		String userName ="Mourinho";
		String dni = "98789878P";
		
		Long idCoach = createCoach(name, userName, dni);
		return readCoach(idCoach);
	}

}
