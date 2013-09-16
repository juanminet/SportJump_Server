package es.uma.sportjump.sjs.service.services.impl;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import es.uma.sportjump.sjs.dao.daos.UserDao;
import es.uma.sportjump.sjs.model.entities.Athlete;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Team;
import es.uma.sportjump.sjs.service.services.UserService;



@Service("userService")
public class UserServiceImpl  implements UserService{
	
	@Autowired
	UserDao userDao;


	/**********************************************************************************************************************/
	/*******************************************        COACH        ******************************************************/
	/**********************************************************************************************************************/

	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Long setNewCoach(String name, String userName,
			String surname, String dni, String email, String type, String address,
			String comments, String telephone, Date dateBirth) {	
		
		//Create coach
		Coach coach = new Coach();
		coach.setName(name);
		coach.setUserName(userName);
		coach.setSurname(surname);
		coach.setDni(dni);
		coach.setEmail(email);
		coach.setType(type);
		coach.setAddress(address);
		coach.setComments(comments);
		coach.setTelephone(telephone);
		coach.setDateBirth(dateBirth);
		
		//persist coach
		userDao.persistCoach(coach);
		
		return coach.getIdUser();
	}




	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateCoach(Coach coach) {
		userDao.persistCoach(coach);	
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Coach findCoach(Long idCoach) {
		return userDao.getCoachById(idCoach);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Coach> findAllCoaches() {
		return userDao.getAllCoaches();
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Coach findCoachesByUserName(String userName) {
		return userDao.getCoachByUserName(userName);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Coach findCoachByDni(String dni) {
		return userDao.getCoachByDni(dni);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void removeCoach(Coach coach) {
		List<Team> teams = findTeamsByCoach(coach);
		
		for(Team team : teams){
			removeTeam(team);
		}
		userDao.deleteCoach(coach.getIdUser());		
	}
	
	

	
	
	
	
	
	
	
	/**********************************************************************************************************************/
	/********************************************        TEAMS        *****************************************************/
	/**********************************************************************************************************************/
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Long setNewTeam(String name, String type, String description, Date dateCreate,  Coach coach) {
		
		//Create team
		Team team = new Team();
		team.setName(name);
		team.setType(type);
		team.setDescription(description);
		team.setDateCreate(dateCreate);
		team.setCoach(coach);
		
		//persist team
		userDao.persistTeam(team);
		
		return team.getIdTeam();
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateTeam(Team team) {
		userDao.persistTeam(team);
		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Team findTeam(Long idTeam) {
		return userDao.getTeamById(idTeam);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Team> findAllTeams() {
		return userDao.getAllTeams();
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void removeTeam(Team team) {
		List<Athlete> athletes = findAthletesFromTeam(team);
		
		for(Athlete athlete : athletes){
			removeAthlete(athlete);
		}
		
		userDao.deleteTeam(team.getIdTeam());
		
	}
	

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Team> findTeamsByCoach(Coach coach) {
		return userDao.getTeamsByCoach(coach);		
	}
	
	
	
	/**********************************************************************************************************************/
	/******************************************        ATHLETES        ****************************************************/
	/**********************************************************************************************************************/	

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Long setNewAthlete(String name, String userName,
			String surname, String dni, String email, String type, String address,
			String comments, String telephone, Date dateBirth, Team team) {
				
		//Crate object athlete
		Athlete athlete = new Athlete();
		athlete.setName(name);
		athlete.setUserName(userName);
		athlete.setSurname(surname);
		athlete.setDni(dni);
		athlete.setEmail(email);
		athlete.setType(type);
		athlete.setAddress(address);
		athlete.setComments(comments);
		athlete.setTelephone(telephone);
		athlete.setDateBirth(dateBirth);
		athlete.setTeam(team);
		
		//Persist athlete
		userDao.persistAthlete(athlete);
		
		
		return athlete.getIdUser();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateAthlete(Athlete athlete) {
		userDao.persistAthlete(athlete);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Athlete findAthlete(Long idAthlete) {	
		
		return userDao.getAthleteById(idAthlete);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Athlete> findAllAthletes() {
		return userDao.getAllAthletes();
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Athlete> findAthletesFromTeam(Team team) {
		return userDao.getAthletesByTeam(team);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Athlete> findAthletesFromCoach(Coach coach) {
		return userDao.getAthletesByCoach(coach);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Athlete findAthleteByDni(String dni) {
		return userDao.getAthleteByDni(dni);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Athlete findAthletesByUserName(String userName) {
		return userDao.getAthleteByUserName(userName);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void removeAthlete(Athlete athlete) {		
		userDao.deleteAthlete(athlete.getIdUser());			
	}


	
	/**********************************************************************************************************************/
	/******************************************        USERS        ****************************************************/
	/**********************************************************************************************************************/
	

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean existUserByDni(String dni) {
		boolean res = true;
		
		Coach coach = findCoachByDni(dni);
		  
		if (coach == null){
			Athlete athlete = findAthleteByDni(dni);
			
			if(athlete == null){
				res = false;
			}
		}
		
		return res;
	}




	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean existUserByUSerName(String userName) {
		boolean res = true;
		
		Coach coach = findCoachesByUserName(userName);
		  
		if (coach == null){
			Athlete athlete = findAthletesByUserName(userName);
			
			if(athlete == null){
				res = false;
			}
		}
		
		return res;
	}

	
	

}
