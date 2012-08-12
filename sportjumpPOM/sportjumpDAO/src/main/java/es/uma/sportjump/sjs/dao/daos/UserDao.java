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
	 * Return a list with all Coaches with name and surname given by parameter
	 * @param name
	 * @param surname
	 * @return
	 */
	public List<Coach> getCoachByNameSurname(String name, String surname);
	
	
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
	 * Get team by id given by parameter
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
	 * Return a list with all Coaches with name and surname given by parameter
	 * @param name
	 * @param surname
	 * @return
	 */
	public List<Athlete> getAthleteByNameSurname(String name, String surname);
	

	/**
	 * Return a list with all Athletes 
	 * @return
	 */
	public List<Athlete> getAllAthletes();
	
	

}
