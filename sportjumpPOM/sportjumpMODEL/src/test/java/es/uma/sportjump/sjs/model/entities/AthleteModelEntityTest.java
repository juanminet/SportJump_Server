package es.uma.sportjump.sjs.model.entities;

import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AthleteModelEntityTest {

	private static EntityManagerFactory entityManagerFactory = null;
	private static Coach coach;
	private static Team team;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("sportjumpJpaPU");
		
		//Init static objects
		
		//Create coach
		String name = "Pepe";
		String userName = "Garcia";
		coach = createCoach(name, userName);
		
		//Create team		
		String nameTeam = "Equipo";
		team = createTeam(nameTeam);
		

		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		//delete static team
		deleteTeam(team);
		
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

		// Definition Athlete
		String name = "Sebastian";
		String surname = "Coe";
		String userName = "sebas";
		String email = "coe@coe.es";

		// Create athlete
		Long idAthlete = createAthlete(name, surname,userName, email);

		// Make assert
		assertNotNull(idAthlete);

		// Read athlete
		Athlete athlete = readAthlete(idAthlete);

		// Make assert
		makeAssertAthlete(name, surname, athlete);

		// Update Athlete
		String newName = "Fermin";

		updateAthlete(idAthlete, newName);

		// Read Athlete
		athlete = readAthlete(idAthlete);

		// Make assert
		makeAssertAthlete(newName, surname, athlete);

		// Delete athlete
		deleteAthlete(idAthlete);

		// Read Client
		athlete = readAthlete(idAthlete);

		// Make assert
		assertNull(athlete);

	}

	private Long createAthlete(String name, String surname, String userName, String email) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//Create athlete
		Athlete athlete = new Athlete();
		athlete.setName(name);
		athlete.setSurname(surname);
		athlete.setUserName(userName);
		athlete.setEmail(email);
		athlete.setTeam(team);
		
		//Persist entity
		entityManager.getTransaction().begin();
		entityManager.persist(athlete);
		entityManager.getTransaction().commit();
		
		//return idAthlete
		return Long.valueOf(athlete.getIdUser());
	}

	private Athlete readAthlete(Long idAthlete) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//read Athlete
		Athlete athlete = entityManager.find(Athlete.class, idAthlete);
		return athlete;
	}

	private void updateAthlete(Long idAthlete, String newName) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
	
		//read Athlete
		Athlete athlete = entityManager.find(Athlete.class, idAthlete);
		
		//updete athlete
		athlete.setName(newName);
		
		entityManager.getTransaction().begin();
		entityManager.persist(athlete);
		entityManager.getTransaction().commit();		
	}

	private void deleteAthlete(Long idAthlete) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//remove athlete
		Athlete athlete = entityManager.find(Athlete.class, idAthlete);
		
		entityManager.getTransaction().begin();
		entityManager.remove(athlete);
		entityManager.getTransaction().commit();
		
	}

	private void makeAssertAthlete(String name, String surname, Athlete athlete) {
		assertEquals(name, athlete.getName());
		assertEquals(surname, athlete.getSurname());
		
	}
	
	
	//BEFORE & AFTER CLASS data
	
	private static  Coach createCoach(String name, String userName) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//Create coach
		Coach coach = new Coach();
		coach.setName(name);
		coach.setUserName(userName);
		
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
	
	
	private static  Team createTeam(String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//data not nullable
		String type = "Velocidad";
		String description = "Grupo dedicado a la velocidad (pruebas 100, 200, 400 metros).";
		Date date = new Date(System.currentTimeMillis());
		
		//Create team
		Team team = new Team();
		team.setName(name);
		team.setType(type);
		team.setDescription(description);
		team.setDateCreate(date);
		
		team.setCoach(coach);
		
		//Persist entity
		entityManager.getTransaction().begin();
		entityManager.persist(team);
		entityManager.getTransaction().commit();
		
		//return team
		return team;
	}
	
	private static void deleteTeam(Team teamToRemove) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//remove team
		Team team = entityManager.find(Team.class, teamToRemove.getIdTeam());
		
		//delete team
		entityManager.getTransaction().begin();
		entityManager.remove(team);
		entityManager.getTransaction().commit();
		
	}

}
