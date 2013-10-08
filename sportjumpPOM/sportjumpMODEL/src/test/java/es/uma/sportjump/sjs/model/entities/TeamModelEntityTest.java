package es.uma.sportjump.sjs.model.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TeamModelEntityTest {

	private static EntityManagerFactory entityManagerFactory = null;
	private static Coach coach;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("sportjumpJpaPU");
		
		//Init static objects
		
		//Create coach
		String name = "Pepe";	
		String userName = "Garcia";
		String dni= "34567898p";
		coach = createCoach(name, userName, dni);		
		

		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
			
		//delete static coach
		deleteCoach(coach);
		
		
		entityManagerFactory = null;
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testCRUD() {

		// Definition Team
		String name = "Equipo";
		String type = "Velocidad";
		String description = "Grupo de la velocidad";
		Date createDate = new Date();

		// Create Team
		Long idTeam = createTeam(name,type, description,createDate);

		// Make assert
		assertNotNull(idTeam);

		// Read Team
		Team team = readTeam(idTeam);		
	

		// Make assert
		makeAssertTeam(name, team);

		// Update Team
		String newName = "Equipazo";

		updateTeam(idTeam, newName);

		// Read team
		team = readTeam(idTeam);

		// Make assert
		makeAssertTeam(newName,team);

		// Delete Team
		deleteTeam(idTeam);

		// Read Team
		team = readTeam(idTeam);

		// Make assert
		assertNull(team);

	}
	
	@Test
	public void testNullables() {
		//Create team
		Team team = new Team();
		checkNullableTeam(team);
		
		//check name
		team= createCompleteTeam();
		team.setName(null);
		checkNullableTeam(team);
		
		//check name
		team= createCompleteTeam();
		team.setType(null);
		checkNullableTeam(team);
		
		
		//check name
		team= createCompleteTeam();
		team.setDescription(null);
		checkNullableTeam(team);
		
		
		//check name
		team= createCompleteTeam();
		team.setDateCreate(null);
		checkNullableTeam(team);
		
		
		//check name
		team= createCompleteTeam();
		team.setCoach(null);
		checkNullableTeam(team);
	}
	
	private Team createCompleteTeam(){
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

	private void checkNullableTeam(Team team) {
		
		boolean check = false;
		
		try{
			persistTeam(team);
		}catch (PersistenceException persistenceException) {
			check=true;
		}
		
		assertTrue(check);
	}

	private Long createTeam(String name, String type, String description, Date dateCreate) {		
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
	
	private Long persistTeam(Team team){
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//Persist entity
		entityManager.getTransaction().begin();
		entityManager.persist(team);
		entityManager.getTransaction().commit();
		
		//return idTeam
		return Long.valueOf(team.getIdTeam());
	}

	private Team readTeam(Long idTeam) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//read Team
		Team team = entityManager.find(Team.class, idTeam);	
		
		return team;
	}

	private void updateTeam(Long idTeam, String newName) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
	
		//read team
		Team team = entityManager.find(Team.class, idTeam);
		
		//update team
		team.setName(newName);
		
		entityManager.getTransaction().begin();
		entityManager.persist(team);
		entityManager.getTransaction().commit();		
	}

	private void deleteTeam(Long idTeam) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//remove team
		Team team = entityManager.find(Team.class, idTeam);
		
		entityManager.getTransaction().begin();
		entityManager.remove(team);
		entityManager.getTransaction().commit();
		
	}

	private void makeAssertTeam(String name, Team team) {
		assertEquals(name, team.getName());		
	}
	
	
	//BEFORE & AFTER CLASS data
	
	private static  Coach createCoach(String name, String userName, String dni) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//Create coach
		Coach coach = new Coach();
		coach.setName(name);
		coach.setUserName(userName);
		coach.setDni(dni);
		
		//Persist entity
		entityManager.getTransaction().begin();
		entityManager.persist(coach);
		entityManager.getTransaction().commit();
		
		//return coach
		return coach;
	}
	
	private static void deleteCoach(Coach coachToRemove) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//remove team
		Coach coach = entityManager.find(Coach.class, coachToRemove.getIdUser());
						
		entityManager.getTransaction().begin();
		entityManager.remove(coach);
		entityManager.getTransaction().commit();
		
	}
	
	

}
