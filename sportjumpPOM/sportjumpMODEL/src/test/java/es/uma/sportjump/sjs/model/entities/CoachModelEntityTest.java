package es.uma.sportjump.sjs.model.entities;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uma.sportjump.sjs.model.entities.test.util.CoachTestUtil;

public class CoachModelEntityTest {

	private static EntityManagerFactory entityManagerFactory = null;
	
	private static CoachTestUtil coachUtil;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("sportjumpJpaPU");		
		coachUtil = CoachTestUtil.getInstance(entityManagerFactory);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		entityManagerFactory = null;
		coachUtil = null;
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
		String name = "Mourinho";
		String surname = "Garcia";
		String userName = "Jose";
		String email = "asdf@asdf.es";
		String dni = "88779988R";

		// Create Coach
		Long idCoach = coachUtil.createCoach(name,surname, userName, email,dni);

		// Make assert
		assertNotNull(idCoach);

		// Read Coach
		Coach coach = coachUtil.readCoach(idCoach);

		// Make assert
		coachUtil.makeAssertCoach(name, coach);

		// Update Coach
		String newName = "Guardiola";

		coachUtil.updateCoach(idCoach, newName);

		// Read coach
		coach = coachUtil.readCoach(idCoach);

		// Make assert
		coachUtil.makeAssertCoach(newName,coach);

		// Delete Coach
		coachUtil.deleteCoach(idCoach);

		// Read Coach
		coach = coachUtil.readCoach(idCoach);

		// Make assert
		assertNull(coach);
	}
}
