package es.uma.sportjump.sjs.model.entities.test.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Team;

public class TeamTestUtil {
	
	private static EntityManagerFactory entityManagerFactory;
	private static TeamTestUtil teamUtil;
	
	private TeamTestUtil(EntityManagerFactory entityManagerFactoryNew){
		entityManagerFactory = entityManagerFactoryNew;
	}
	
	private static synchronized TeamTestUtil getSynchronizedInstance( EntityManagerFactory entityManagerFactory){
		if (teamUtil == null){
			teamUtil = new TeamTestUtil(entityManagerFactory);
		}
		
		return teamUtil;
	}
	
	public static TeamTestUtil getInstance(EntityManagerFactory entityManagerFactory){
		return getSynchronizedInstance(entityManagerFactory);
	}
	
	

	public Team createCompleteTeamNoPersist(Coach coach){
		// Definition Team
		String name = "Equipo";
		String type = "Velocidad";
		String description = "Grupo de la velocidad";
		Date dateCreate = new Date();
				
		//Create team
		Team team = new Team();
				
		team.setName(name);
		team.setType(type);
		team.setDescription(description);
		team.setDateCreate(dateCreate);
		team.setCoach(coach);
		
		return team;
	}

	public Team createCompleteTeam(Coach coach){
		// Definition Team
		String name = "Equipo";
		String type = "Velocidad";
		String description = "Grupo de la velocidad";
		Date dateCreate = new Date();
				
		Long idTeam = createTeam(name, type, description, dateCreate, coach);
		
		return readTeam(idTeam);
	}
	
	public Long createTeam(String name, String type, String description, Date dateCreate, Coach coach) {		
		//Create team
		Team team = new Team();
		team.setName(name);
		team.setType(type);
		team.setDescription(description);
		team.setDateCreate(dateCreate);
		team.setCoach(coach);
		
		//Persist entity
		Long res = persistTeam(team);
		
		//return idTeam
		return res;
	}
	
	public Long persistTeam(Team team){
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//Persist entity
		entityManager.getTransaction().begin();
		entityManager.persist(team);
		entityManager.getTransaction().commit();
		
		//return idTeam
		return Long.valueOf(team.getIdTeam());
	}

	public Team readTeam(Long idTeam) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//read Team
		Team team = entityManager.find(Team.class, idTeam);	
		
		return team;
	}

	public void updateTeam(Long idTeam, String newName) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
	
		//read team
		Team team = entityManager.find(Team.class, idTeam);
		
		//update team
		team.setName(newName);
		
		entityManager.getTransaction().begin();
		entityManager.persist(team);
		entityManager.getTransaction().commit();		
	}

	public void deleteTeam(Long idTeam) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//remove team
		Team team = entityManager.find(Team.class, idTeam);
		
		entityManager.getTransaction().begin();
		entityManager.remove(team);
		entityManager.getTransaction().commit();
		
	}

	public void makeAssertTeam(String name, Team team) {
		assertEquals(name, team.getName());		
	}

	public void checkNullableTeam(Team team) {
		
		boolean check = false;
		
		try{
			persistTeam(team);
		}catch (PersistenceException persistenceException) {
			check=true;
		}
		
		assertTrue(check);
	}

}
