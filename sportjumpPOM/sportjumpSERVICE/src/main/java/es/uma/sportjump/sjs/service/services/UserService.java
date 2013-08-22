package es.uma.sportjump.sjs.service.services;

import java.util.Date;
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
	public Long setNewCoach(String name, String userName,String surname, String dni, String email,
			String type, String address, String comments, String telephone,	Date dateBirth);
	
	
	/**
	 * 
	 * @param coach
	 */
	public void updateCoach(Coach coach);
	
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
	 * @param username
	 * @return
	 */
	public Coach findCoachesByUserName(String userName);
	
	
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
	public Long setNewTeam(String name, String type, String description, Date dateCreate, Coach coach);
	
	/**
	 * 
	 * @param team
	 */
	public void updateTeam(Team team);
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
	 * @param team
	 */
	public void removeTeam(Team team);
	
	/**
	 * 
	 * @param coach
	 */
	public List<Team>  findTeamsByCoach(Coach coach);

	
	
	/**********************************************************************************************************************/
	/******************************************        ATHLETES        ****************************************************/
	/**********************************************************************************************************************/
	
	/**
	 * 
	 * @param name
	 * @param surname
	 * @return
	 */
	public Long setNewAthlete(String name, String userName,String surname,String dni,
			String email,String type, String address, String comments, String telephone,
			Date dateBirth, Team team);
	
	
	/**
	 * 
	 * @param athlete
	 */
	public void updateAthlete(Athlete athlete);
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
	public Athlete findAthletesByUserName(String userName);
	
	
	/**
	 * 
	 * @param idAthlete
	 */
	public void removeAthlete(Athlete athlete);


}
