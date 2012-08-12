package es.uma.sportjump.sjs.model.entities;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CoachModelEntityTest {

	private static EntityManagerFactory entityManagerFactory = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("sportjumpJpaPU");
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
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

		// Definition Coach
		String name = "Equipo";		

		// Create Coach
		Long idCoach = createCoach(name);

		// Make assert
		assertNotNull(idCoach);

		// Read Coach
		Coach coach = readCoach(idCoach);

		// Make assert
		makeAssertCoach(name, coach);

		// Update Coach
		String newName = "Equipazo";

		updateCoach(idCoach, newName);

		// Read coach
		coach = readCoach(idCoach);

		// Make assert
		makeAssertCoach(newName,coach);

		// Delete Coach
		deleteCoach(idCoach);

		// Read Coach
		coach = readCoach(idCoach);

		// Make assert
		assertNull(coach);

	}

	private Long createCoach(String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//Create coach
		Coach coach = new Coach();
		coach.setName(name);
	
		
		//Persist entity
		entityManager.getTransaction().begin();
		entityManager.persist(coach);
		entityManager.getTransaction().commit();
		
		//return idCoach
		return Long.valueOf(coach.getIdUser());
	}

	private Coach readCoach(Long idCoach) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//read Coach
		Coach coach = entityManager.find(Coach.class, idCoach);
		return coach;
	}

	private void updateCoach(Long idCoach, String newName) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
	
		//read coach
		Coach coach = entityManager.find(Coach.class, idCoach);
		
		//update coach
		coach.setName(newName);
		
		entityManager.getTransaction().begin();
		entityManager.persist(coach);
		entityManager.getTransaction().commit();		
	}

	private void deleteCoach(Long idCoach) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//remove coach
		Coach coach = entityManager.find(Coach.class, idCoach);
		
		entityManager.getTransaction().begin();
		entityManager.remove(coach);
		entityManager.getTransaction().commit();
		
	}

	private void makeAssertCoach(String name, Coach coach) {
		assertEquals(name, coach.getName());		
	}	
	
	
	

}
