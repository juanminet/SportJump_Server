package es.uma.sportjump.sjs.dao.daos.impl.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.uma.sportjump.sjs.dao.daos.UserDao;
import es.uma.sportjump.sjs.model.entities.Athlete;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Team;

@Component("athleteDao")
public class UserDaoMockImpl implements UserDao{
	
	List<Coach> listCoaches = new ArrayList<Coach>();
	
	List<Team> listTeams = new ArrayList<Team>();

	List<Athlete> listAthletes = new ArrayList<Athlete>();
	
	/**********************************************************************************************************************/
	/********************************************        COACH        *****************************************************/
	/**********************************************************************************************************************/
	
	@Override
	public void persistCoach(Coach coach) {
		
		Coach coachAux = getCoachById(coach.getIdUser());
		if(coachAux == null){
			//add team to list
			listCoaches.add(coach);
					
			//set id 
			coach.setIdUser((long)listCoaches.indexOf(coach));
		}else{
			Long idUser = coach.getIdUser();
			
			listCoaches.set(idUser.intValue(), coach);
		}
		
	}



	@Override
	public Coach getCoachById(Long id) {
		Coach resCoach = null;
		
		//find coach
		int index = -1;
		for(Coach coach : listCoaches){
			if(coach.getIdUser() == id){
				index = listCoaches.indexOf(coach);
				break;
			}
		}
		
		//get coach
		if (index != -1){
			resCoach = listCoaches.get(index);
		}
		
		return resCoach;
	}



	@Override
	public void deleteCoach(Long id) {
		//find coach
		int index = -1;
		for(Coach coach : listCoaches){
			if(coach.getIdUser() == id){
				index = listCoaches.indexOf(coach);
				break;
			}
		}
		
		//remove coach
		if (index != -1){
			listCoaches.remove(index);
		}		
	}



	@Override
	public Coach getCoachByUserName(String userName) {
		Coach resListCoaches = null;
		
		//find coach		
		for(Coach coach : listCoaches){
			if(coach.getUserName().equals(userName)){
				resListCoaches = coach;
				break;
			}
		}
		
		return resListCoaches;		
	}
	


	@Override
	public Coach getCoachByDni(String dni) {
		Coach resCoach = null;
		
		//find coach		
		for(Coach coach : listCoaches){
			if(coach.getDni().equals(dni)){
				resCoach = coach;
				break;
			}
		}
		
		return resCoach;	
	}

	
	
	@Override
	public List<Coach> getAllCoaches() {
		return listCoaches;		
	}
	



	
	/**********************************************************************************************************************/
	/********************************************        TEAM        *****************************************************/
	/**********************************************************************************************************************/
	
	
	@Override
	public void persistTeam(Team team) {
		
		
		
		if (getCoachById(team.getCoach().getIdUser()) != null){	
			
			if (getTeamById(team.getIdTeam()) == null){			
				//add athlete to list
				listTeams.add(team);
						
				//set id 
				team.setIdTeam((long)listTeams.indexOf(team));	
			}else{
				listTeams.set(team.getIdTeam().intValue(),team);
			}
			
			
		
		}
	}



	@Override
	public Team getTeamById(Long id) {
		Team resTeam = null;
		//find coach
		int index = -1;
		for(Team team : listTeams){
			if(team.getIdTeam() == id){
				index = listTeams.indexOf(team);
				break;
			}
		}
		
		//remove team
		if (index != -1){
			resTeam = listTeams.get(index);
		}	
		
		return resTeam;
	}



	@Override
	public void deleteTeam(Long id) {
		//find coach
		int index = -1;
		for(Team team : listTeams){
			if(team.getIdTeam() == id){
				index = listTeams.indexOf(team);
				break;
			}
		}
		
		//remove team
		if (index != -1){
			listTeams.remove(index);
		}		
	}
	
	
	@Override
	public List<Team> getAllTeams() {
		return listTeams;		
	}
	

	@Override
	public List<Team> getTeamsByCoach(Coach coach) {
		
		List<Team> resListTeam = new ArrayList<Team>();
		for(Team team : listTeams){
			if(team.getCoach().getIdUser() == coach.getIdUser()){
				resListTeam.add(team);
			}
		}
		
		return resListTeam;
	}






	
	
	
	/**********************************************************************************************************************/
	/********************************************       ATHLETE       *****************************************************/
	/**********************************************************************************************************************/
	
	
	public void persistAthlete(Athlete athlete) {
		// check team
		if (listTeams.contains(athlete.getTeam())) {
			
			if(getAthleteById(athlete.getIdUser()) == null){
				// add athlete to list
				listAthletes.add(athlete);
	
				// set id
				athlete.setIdUser((long) listAthletes.indexOf(athlete));
			}else{
				listAthletes.set(athlete.getIdUser().intValue(), athlete);
			}
		}
	}
	

	
	public Athlete getAthleteById(Long id) {
		Athlete resAthlete = null;
		
		//find athlete
		int index = -1;
		for(Athlete athlete : listAthletes){
			if(athlete.getIdUser() == id){
				index = listAthletes.indexOf(athlete);
				break;
			}
		}
		
		//get athlete
		if (index != -1){
			resAthlete = listAthletes.get(index);
		}
		
		return resAthlete;
	}
	

	
	public void deleteAthlete(Long id) {
		
		//find athlete
		int index = -1;
		for(Athlete athlete : listAthletes){
			if(athlete.getIdUser() == id){
				index = listAthletes.indexOf(athlete);
				break;
			}
		}
		
		//remove athlete
		if (index != -1){
			listAthletes.remove(index);
		}		
	}



	@Override
	public Athlete getAthleteByUserName(String userName) {
		 Athlete resListAthletes = null;
		
		//find athlete
		for(Athlete athlete : listAthletes){
			if(athlete.getUserName().equals(userName)){
				resListAthletes = athlete;
			}
		}
				
		return resListAthletes;		
	}
	



	@Override
	public Athlete getAthleteByDni(String dni) {
		 Athlete resAthlete = null;
			
			//find athlete
			for(Athlete athlete : listAthletes){
				if(athlete.getDni().equals(dni)){
					resAthlete = athlete;
				}
			}
					
			return resAthlete;	
	}




	@Override
	public List<Athlete> getAllAthletes() {
		return listAthletes;		
	}



	@Override
	public List<Athlete> getAthletesByCoach(Coach coach) {
		List<Athlete> resListAthletes = new ArrayList<Athlete>();
			
		//find athlete
		for(Athlete athlete : listAthletes){
			if(athlete.getTeam().getCoach().getIdUser().equals(coach.getIdUser())){
				resListAthletes.add(athlete);
			}
		}
				
		return resListAthletes;		
	}



	@Override
	public List<Athlete> getAthletesByTeam(Team team) {
		List<Athlete> resListAthletes = new ArrayList<Athlete>();
		
		//find athlete
		for(Athlete athlete : listAthletes){
			if(athlete.getTeam().getIdTeam().equals(team.getIdTeam())){
				resListAthletes.add(athlete);
			}
		}
				
		return resListAthletes;		
	}





}
