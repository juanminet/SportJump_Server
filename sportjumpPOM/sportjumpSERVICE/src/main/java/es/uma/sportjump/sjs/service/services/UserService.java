package es.uma.sportjump.sjs.service.services;

import java.util.List;

import es.uma.sportjump.sjs.model.entities.Athlete;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Team;

public interface UserService {
	
	
	/**********************************************************************************************************************/
	/********************************************        COACH        *****************************************************/
	/**********************************************************************************************************************/
	
	/**
	 * 
	 * @param name
	 * @param surname
	 * @return
	 */
	public Long setNewCoach(String name, String surname);
	
	
	/**
	 * 
	 * @param idCoach
	 * @return
	 */	
	public Coach findCoach(Long idCoach);
	

	/**
	 * 
	 * @return
	 */
	public List<Coach> findAllCoaches();
	
	
	/**
	 * 
	 * @param name
	 * @param surname
	 * @return
	 */
	public List<Coach> findCoachesByNameSurname(String name, String surname);
	
	
	/**
	 * 
	 * @param idCoach
	 */
	public void removeCoach(Coach coach);

	
	/**********************************************************************************************************************/
	/********************************************        TEAM        *****************************************************/
	/**********************************************************************************************************************/
	
	
	/**
	 * 
	 * @param name
	 * @param surname
	 * @return
	 */
	public Long setNewTeam(String name, Coach coach);
	
	
	/**
	 * 
	 * @param idTeam
	 * @return
	 */	
	public Team findTeam(Long idTeam);
	

	/**
	 * 
	 * @return
	 */
	public List<Team> findAllTeams();	
	
	
	
	
	/**
	 * 
	 * @param idTeam
	 */
	public void removeTeam(Team team);

	
	
	/**********************************************************************************************************************/
	/******************************************        ATHLETES        ****************************************************/
	/**********************************************************************************************************************/
	
	/**
	 * 
	 * @param name
	 * @param surname
	 * @return
	 */
	public Long setNewAthlete(String name, String surname, Team team);
	
	
	/**
	 * 
	 * @param idAthlete
	 * @return
	 */	
	public Athlete findAthlete(Long idAthlete);
	

	/**
	 * 
	 * @return
	 */
	public List<Athlete> findAllAthletes();
	
	
	/**
	 * 
	 * @param name
	 * @param surname
	 * @return
	 */
	public List<Athlete> findAthletesByNameSurname(String name, String surname);
	
	
	/**
	 * 
	 * @param idAthlete
	 */
	public void removeAthlete(Athlete athlete);

	

}
