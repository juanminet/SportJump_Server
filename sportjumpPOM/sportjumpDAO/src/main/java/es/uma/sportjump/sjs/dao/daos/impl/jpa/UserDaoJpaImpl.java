package es.uma.sportjump.sjs.dao.daos.impl.jpa;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.uma.sportjump.sjs.dao.daos.UserDao;
import es.uma.sportjump.sjs.model.entities.Athlete;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Team;

@Repository("userDao")
public class UserDaoJpaImpl  implements UserDao{

 
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
 	protected EntityManager em;
	
	
	
	/**********************************************************************************************************************/
	/********************************************        COACH        *****************************************************/
	/**********************************************************************************************************************/
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void persistCoach(Coach coach) {
		em.persist(coach);		
	}


	@Transactional(propagation=Propagation.REQUIRED)
	public Coach getCoachById(Long id) {
		return em.find(Coach.class, Long.valueOf(id));
	}


	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteCoach(Long id) {
		em.remove(getCoachById(id));		
		em.flush();
	}


	@Transactional(propagation=Propagation.REQUIRED)
	public Coach getCoachByUserName(String userName) {
		
		Query query = em.createNamedQuery("findCoachByUserName")
				.setParameter("userName", userName);
		Coach resListCoach = null;
		
		try{
			resListCoach = (Coach) query.getSingleResult();
		}catch(NoResultException noResultException){
			resListCoach = null;
		}
		
		return resListCoach;
	}
	



	@Transactional(propagation=Propagation.REQUIRED)
	public Coach getCoachByDni(String dni) {
		Query query = em.createNamedQuery("findCoachByDni")
				.setParameter("dni", dni);
		Coach resCoach = null;
		
		try{
			resCoach = (Coach) query.getSingleResult();
		}catch(NoResultException noResultException){
			resCoach = null;
		}
		
		return resCoach;
	}


	
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Coach> getAllCoaches() {
		
		Query query = em.createNamedQuery("findAllCoaches");
		
		List<Coach> resListCoach = query.getResultList();
		
		return resListCoach;
	}
	
	
	
	
	
	
	
	
	
	/**********************************************************************************************************************/
	/********************************************        Team        *****************************************************/
	/**********************************************************************************************************************/
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void persistTeam(Team team) {
		em.persist(team);		
	}


	@Transactional(propagation=Propagation.REQUIRED)
	public Team getCompleteTeamById(Long id) {		
		Team res = null;
		Query query = em.createNamedQuery("findfetchTeamById")
				.setParameter("idTeam", id);
		try{
			res = (Team) query.getSingleResult();
		}catch (NoResultException noResultException) {
			res = null;
		}
		return res;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public Team getTeamById(Long id){
		Team team = em.find(Team.class, Long.valueOf(id));		
		return team;
	}


	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteTeam(Long id) {
		em.remove(getCompleteTeamById(id));	
		em.flush();
	}
	
	
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Team> getAllTeams() {
		
		Query query = em.createNamedQuery("findAllTeams");
		
		List<Team> resListTeams = query.getResultList();
		
		return resListTeams;
	}
	
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Team> getTeamsByCoach(Coach coach) {
		
		Query query = em.createNamedQuery("findAllTeamsByCoach")
				.setParameter("idCoach", coach.getIdUser());
		
		List<Team> resListTeams = query.getResultList();
		
		return resListTeams;
	}
	

	/**********************************************************************************************************************/
	/********************************************       ATHLETE       *****************************************************/
	/**********************************************************************************************************************/
	

	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void persistAthlete(Athlete athlete) {		
		em.persist(athlete);		
	}

	
	@Transactional(propagation=Propagation.REQUIRED)
	public Athlete getAthleteById(Long id) {
		return em.find(Athlete.class, Long.valueOf(id));
	}

	
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteAthlete(Long id) {
		em.remove(getAthleteById(id));
		em.flush();
	}


	@Transactional(propagation=Propagation.REQUIRED)
	public Athlete getAthleteByUserName(String userName) {		
								
		Query query = em.createNamedQuery("findAthleteByUserName")
				.setParameter("userName", userName);
		
		Athlete resAthlete =  null;
		
		try{
			resAthlete = (Athlete) query.getSingleResult();	
		}catch(NoResultException noResultException){
			resAthlete = null;
		}
		
		return resAthlete;
	}
	

	
	@Transactional(propagation=Propagation.REQUIRED)
	public Athlete getAthleteByDni(String dni) {
		Query query = em.createNamedQuery("findAthleteByDni")
				.setParameter("dni", dni);
		
		Athlete resAthlete =  null;
		
		try{
			resAthlete = (Athlete) query.getSingleResult();	
		}catch(NoResultException noResultException){
			resAthlete = null;
		}
		
		return resAthlete;
	}

	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Athlete> getAllAthletes() {
		
		Query query = em.createNamedQuery("findAllAthletes");
		
		List<Athlete> resListAthletes = query.getResultList();
		
		return resListAthletes;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Athlete> getAthletesByCoach(Coach coach) {
		Query query  = em.createNamedQuery("findAthleteFromCoach")
				.setParameter("idCoach", coach.getIdUser());
		
		List<Athlete> resListAthletes = query.getResultList();
		
		return resListAthletes;
	}


	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Athlete> getAthletesByTeam(Team team) {
		Query query  = em.createNamedQuery("findAthleteFromTeam")
				.setParameter("idTeam", team.getIdTeam());
		
		List<Athlete> resListAthletes = query.getResultList();
		
		return resListAthletes;
	}

}
