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
import es.uma.sportjump.sjs.model.entities.test.util.ExerciseBlockTestUtil;

public class ExerciseBlockModelEntityTest {


	private static EntityManagerFactory entityManagerFactory = null;
	
	//Variables
	private Coach coach;
	private static CoachTestUtil coachUtil;
	private static ExerciseBlockTestUtil exerciseBlockUtil;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("sportjumpJpaPU");
		
		coachUtil = CoachTestUtil.getInstance(entityManagerFactory);
		exerciseBlockUtil = ExerciseBlockTestUtil.getInstance(entityManagerFactory);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		entityManagerFactory = null;
		
		coachUtil = null;
		exerciseBlockUtil = null;
	}

	@Before
	public void setUp() throws Exception {
		coach = coachUtil.createCompleteCoach();
	}

	@After
	public void tearDown() throws Exception {
		// Delete Coach
		coachUtil.deleteCoach(coach.getIdUser());
	}

	@Test
	public void testCRUD() {
		// Definition ExerciseBlock
		String name = "1Bloque rapidez";
		String type = "1Velocidad";
		String description = "Bloque donde se trabaja la velocidad inmediata y la fuerza explosiva";

		// Create Exercise Block
		Long idBlock = exerciseBlockUtil.createExerciseBlock(name,type, description, coach);

		// Make assert
		assertNotNull(idBlock);

		// Read Exercise Block
		ExerciseBlock block = exerciseBlockUtil.readExericseBock(idBlock);
		
		// Make assert
		exerciseBlockUtil.makeAssertExerciseBlock(name,type,description, block);

		// Update exercise block
		String newName = "Rapidez";

		exerciseBlockUtil.updateExerciseBlock(idBlock, newName);

		// Read exercise block
		block = exerciseBlockUtil.readExericseBock(idBlock);

		// Make assert
		exerciseBlockUtil.makeAssertExerciseBlock(newName,type,description, block);

		// Delete exercise block
		exerciseBlockUtil.deleteExerciseBlock(idBlock);

		// Read exercise block
		block = exerciseBlockUtil.readExericseBock(idBlock);

		// Make assert
		assertNull(block);
	}
	
	
	@Test
	public void testNullables() {
		//Create block
		ExerciseBlock block = exerciseBlockUtil.createCompleteExerciseBlock(coach);
		assertNotNull(block);
		
		//check name
		block= exerciseBlockUtil.createCompleteExerciseBlock(coach);
		block.setName(null);
		exerciseBlockUtil.checkNullableExerciseBlock(block);
		
		//check type
		block= exerciseBlockUtil.createCompleteExerciseBlock(coach);
		block.setType(null);
		exerciseBlockUtil.checkNullableExerciseBlock(block);
			
	}
	
}
