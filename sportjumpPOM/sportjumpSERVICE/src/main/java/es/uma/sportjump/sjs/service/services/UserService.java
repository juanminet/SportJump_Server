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
	 * @param dni
	 * @return
	 */
	public Coach findCoachByDni(String dni);
	
	
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
	 * Find a team without any references
	 * @param idTeam
	 * @return
	 */	
	public Team findTeamLight(Long idTeam);
	
	/**
	 * Find a complete team with all references
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
	 * @return
	 */
	public List<Athlete> findAthletesFromTeam(Team team);
	
	
	/**
	 * 
	 * @return
	 */
	public List<Athlete> findAthletesFromCoach(Coach coach);
	
	/**
	 * 
	 * @param name
	 * @param surname
	 * @return
	 */
	public Athlete findAthletesByUserName(String userName);
	
	/**
	 * 
	 * @param dni
	 * @return
	 */
	public Athlete findAthleteByDni(String dni);
	
	
	/**
	 * 
	 * @param idAthlete
	 */
	public void removeAthlete(Athlete athlete);
	
	
	
	/**********************************************************************************************************************/
	/******************************************          USERS         ****************************************************/
	/**********************************************************************************************************************/
	
	/**
	 * Method to know if exist one user with dni given by parameter
	 * @param dni
	 * @return true if exist user, false otherwise
	 */
	public boolean existUserByDni(String dni);
	
	/**
	 * Method to kno if exist one user with username given by paramenter
	 * @param userName
	 * @return true if exist user, false otherwise
	 */
	public  boolean existUserByUSerName(String userName);
	

}
