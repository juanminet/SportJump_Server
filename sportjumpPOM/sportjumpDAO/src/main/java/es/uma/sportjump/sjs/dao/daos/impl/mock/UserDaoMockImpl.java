package es.uma.sportjump.sjs.dao.daos.impl.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sun.org.apache.regexp.internal.recompile;

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
		//add team to list
		listCoaches.add(coach);
				
		//set id 
		coach.setIdUser((long)listCoaches.indexOf(coach));
		
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
	public List<Coach> getAllCoaches() {
		return listCoaches;		
	}
	



	
	/**********************************************************************************************************************/
	/********************************************        TEAM        *****************************************************/
	/**********************************************************************************************************************/
	
	
	@Override
	public void persistTeam(Team team) {
		
		
		
		if (getCoachById(team.getCoach().getIdUser()) != null){			
		
			//add athlete to list
			listTeams.add(team);
					
			//set id 
			team.setIdTeam((long)listTeams.indexOf(team));	
			
			
		
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
	
	
	/**********************************************************************************************************************/
	/********************************************       ATHLETE       *****************************************************/
	/**********************************************************************************************************************/
	
	
	public void persistAthlete(Athlete athlete) {
		// check team
		if (listTeams.contains(athlete.getTeam())) {
			// add athlete to list
			listAthletes.add(athlete);

			// set id
			athlete.setIdUser((long) listAthletes.indexOf(athlete));
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
	public List<Athlete> getAllAthletes() {
		return listAthletes;		
	}






	

}
