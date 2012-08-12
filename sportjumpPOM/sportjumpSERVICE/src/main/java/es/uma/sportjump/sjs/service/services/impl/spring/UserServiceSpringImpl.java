package es.uma.sportjump.sjs.service.services.impl.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uma.sportjump.sjs.dao.daos.UserDao; 
import es.uma.sportjump.sjs.model.entities.Athlete;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Team;
import es.uma.sportjump.sjs.service.services.UserService;


@Service("userService")
public class UserServiceSpringImpl  implements UserService{
	
	@Autowired
	UserDao userDao;
	
	
	/**********************************************************************************************************************/
	/*******************************************        COACH        ******************************************************/
	/**********************************************************************************************************************/
	public Long setNewCoach(String name, String surname) {
		
		//Create coach
		Coach coach = new Coach();
		coach.setName(name);
		coach.setSurname(surname);
		
		//persist coach
		userDao.persistCoach(coach);
		
		return coach.getIdUser();
	}



	public Coach findCoach(Long idCoach) {
		return userDao.getCoachById(idCoach);
	}
	
	public List<Coach> findAllCoaches() {
		return userDao.getAllCoaches();
	}
	
	public List<Coach> findCoachesByNameSurname(String name, String surname) {
		return userDao.getCoachByNameSurname(name, surname);
	}



	public void removeCoach(Coach coach) {
		userDao.deleteCoach(coach.getIdUser());
		
	}
	
	

	
	
	
	
	
	
	
	/**********************************************************************************************************************/
	/********************************************        TEAMS        *****************************************************/
	/**********************************************************************************************************************/
	
	public Long setNewTeam(String name, Coach coach) {
		
		//Create team
		Team team = new Team();
		team.setName(name);
		team.setCoach(coach);
		
		//persist team
		userDao.persistTeam(team);
		
		return team.getIdTeam();
	}
	

	public Team findTeam(Long idTeam) {
		return userDao.getTeamById(idTeam);
	}
	
	
	public List<Team> findAllTeams() {
		return userDao.getAllTeams();
	}
	
	
	public void removeTeam(Team team) {
		userDao.deleteTeam(team.getIdTeam());
		
	}
	
	
	
	
	
	/**********************************************************************************************************************/
	/******************************************        ATHLETES        ****************************************************/
	/**********************************************************************************************************************/	

	
	public Long setNewAthlete(String name, String surname, Team team) {
		
		//Crate object athlete
		Athlete athlete = new Athlete();
		athlete.setName(name);
		athlete.setSurname(surname);
		athlete.setTeam(team);
		
		//Persist athlete
		userDao.persistAthlete(athlete);
		
		
		return athlete.getIdUser();
	}


	public Athlete findAthlete(Long idAthlete) {	
		
		return userDao.getAthleteById(idAthlete);
	}
	
	
	public List<Athlete> findAllAthletes() {
		return userDao.getAllAthletes();
	}
	
	public List<Athlete> findAthletesByNameSurname(String name, String surname) {
		return userDao.getAthleteByNameSurname(name, surname);
	}


	
	public void removeAthlete(Athlete athlete) {		
		userDao.deleteAthlete(athlete.getIdUser());			
	}




}
