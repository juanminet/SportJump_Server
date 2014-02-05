package es.uma.sportjump.sjs.dao.daos;

import java.util.List;

import es.uma.sportjump.sjs.model.entities.Athlete;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Team;




public interface UserDao {
	
	/**********************************************************************************************************************/
	/********************************************        COACH        *****************************************************/
	/**********************************************************************************************************************/
	
	
	/**
	 * Persist coach
	 * @param coach
	 */
	public void persistCoach(Coach coach);	
	
	/**
	 * Get coach by id given by parameter
	 * @param id
	 * @return
	 */
	public Coach getCoachById(Long id);
	
	/**
	 * Remove coach
	 * @param id
	 */
	public void deleteCoach(Long id);

	/**
	 * Return Coach by username given by parameter, username is unique
	 * @param name
	 * @param surname
	 * @return
	 */
	public Coach getCoachByUserName(String userName);
	
	
	/**
	 * Return Coach with dni given by parameter, dni is unique
	 * @param dni
	 * @return
	 */
	public Coach getCoachByDni(String dni);
	
	
	/**
	 * Return a list with all Coaches 
	 * @return
	 */
	public List<Coach> getAllCoaches();
	
	
	
	
	
	
	
	
	
	/**********************************************************************************************************************/
	/********************************************        Team        *****************************************************/
	/**********************************************************************************************************************/
	
	
	/**
	 * Persist team
	 * @param team
	 */
	public void persistTeam(Team team);	
	
	/**
	 * Get  a complete team by id given by parameter
	 * @param id
	 * @return
	 */
	public Team getCompleteTeamById(Long id);
	
	/**
	 * Get team by id given by paramenter
	 * @param id
	 * @return
	 */
	public Team getTeamById(Long id);
	
	/**
	 * Remove team
	 * @param id
	 */
	public void deleteTeam(Long id);
	
	
	/**
	 * Return a list with all Teams 
	 * @return
	 */
	public List<Team> getAllTeams();
	
	/**
	 * Return a list with teams belong to coach given by paramenter
	 * @return
	 */
	public List<Team> getTeamsByCoach(Coach coach);
	
	
	
	
		
	/**********************************************************************************************************************/
	/********************************************       ATHLETE       *****************************************************/
	/**********************************************************************************************************************/
	
	
	/**
	 * Persist Athlete
	 * @param athlete
	 */
	public void persistAthlete(Athlete athlete);	
	
	/**
	 * Get athlete by id given by parameter
	 * @param id
	 * @return
	 */
	public Athlete getAthleteById(Long id);
	
	/**
	 * Remove athlete
	 * @param id
	 */
	public void deleteAthlete(Long id);

	/**
	 * Return Athlete with username given by parameter. username is unique.
	 * @param name
	 * @param surname
	 * @return
	 */
	public Athlete getAthleteByUserName(String UserName);	
	
	/**
	 * Return Athlete with dni given by parameter, dni is unique
	 * @param dni
	 * @return
	 */
	public Athlete getAthleteByDni(String dni);
	
	/**
	 * Return a list with all Athletes 
	 * @return
	 */
	public List<Athlete> getAllAthletes();
	
	/**
	 * Return a list with all athletes trained by coach given by parameter
	 * @return
	 */
	public List<Athlete> getAthletesByCoach(Coach coach);
	
	/**
	 * Return a list with all athletes from the team given by parameter
	 * @return
	 */
	public List<Athlete> getAthletesByTeam(Team team);
	
	

}
