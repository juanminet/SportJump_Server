package es.uma.sportjump.sjs.model.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uma.sportjump.sjs.model.entities.test.util.CoachTestUtil;
import es.uma.sportjump.sjs.model.entities.test.util.ExerciseBlockTestUtil;
import es.uma.sportjump.sjs.model.entities.test.util.TrainingTestUtil;

public class TrainingModelEntityTest {


	private static EntityManagerFactory entityManagerFactory = null;
	
	//Variables
	private Coach coach;
	private static CoachTestUtil coachUtil;
	private static ExerciseBlock exerciseBlock1;
	private static ExerciseBlock exerciseBlock2;
	private static ExerciseBlock exerciseBlock3;
	private static ExerciseBlockTestUtil exerciseBlockUtil;
	private static TrainingTestUtil trainingTestUtil;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("sportjumpJpaPU");
		
		coachUtil = CoachTestUtil.getInstance(entityManagerFactory);
		exerciseBlockUtil = ExerciseBlockTestUtil.getInstance(entityManagerFactory);
		trainingTestUtil = TrainingTestUtil.getInstance(entityManagerFactory);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		entityManagerFactory = null;
		
		trainingTestUtil = null;
		exerciseBlockUtil = null;
		coachUtil = null;
		
	}

	@Before
	public void setUp() throws Exception {
		coach = coachUtil.createCompleteCoach();
		
		exerciseBlock1 = exerciseBlockUtil.createCompleteExerciseBlockNum(coach, 1);
		exerciseBlock2 = exerciseBlockUtil.createCompleteExerciseBlockNum(coach, 2);
		exerciseBlock3 = exerciseBlockUtil.createCompleteExerciseBlockNum(coach, 3);
	}

	@After
	public void tearDown() throws Exception {
	
		
		exerciseBlockUtil.deleteExerciseBlock(exerciseBlock1.getIdExerciseBlock());
		exerciseBlockUtil.deleteExerciseBlock(exerciseBlock2.getIdExerciseBlock());
		exerciseBlockUtil.deleteExerciseBlock(exerciseBlock3.getIdExerciseBlock());
		
		// Delete Coach
		coachUtil.deleteCoach(coach.getIdUser());
	}

	@Test
	public void testCRUD() {
		// Definition training
		String name = "Rapidez 1";
		String type = "Velocidad";
		String description = "Entrenamos fuerza, y rapidez para coger musculatura";

		// Create training
		Long idTraining = trainingTestUtil.createTraining(name,type, description, coach);

		// Make assert
		assertNotNull(idTraining);

		// Read training
		Training training = trainingTestUtil.readTraining(idTraining);
		
		// Make assert
		trainingTestUtil.makeAssertTraining(name,type,description, training);

		// Update training
		String newName = "Rapidez 2";

		trainingTestUtil.updateTraining(idTraining, newName);

		// Read training
		training = trainingTestUtil.readTraining(idTraining);

		// Make assert
		trainingTestUtil.makeAssertTraining(newName,type,description, training);

		// Delete training
		trainingTestUtil.deleteTraining(idTraining);

		// Read training
		training = trainingTestUtil.readTraining(idTraining);

		// Make assert
		assertNull(training);
	}
	
	@Test
	public void testWithBlocks(){
		// Definition training
		String name = "Rapidez 1";
		String type = "Velocidad";
		String description = "Entrenamos fuerza, y rapidez para coger musculatura";
		
		List<ExerciseBlock> exerciseBlockList = new ArrayList<ExerciseBlock>();
		exerciseBlockList.add(exerciseBlock1);
		exerciseBlockList.add(exerciseBlock2);
		
		
		// Create training
		Long idTraining = trainingTestUtil.createTrainingWithBlocks(name,type, description, coach, exerciseBlockList);
		
		// Read training
		Training training = trainingTestUtil.readTraining(idTraining);
		
		
		assertEquals(exerciseBlock1.getIdExerciseBlock(),training.getListExerciseBlock().get(0).getIdExerciseBlock());
		assertEquals(exerciseBlock2.getIdExerciseBlock(),training.getListExerciseBlock().get(1).getIdExerciseBlock());
		
		//Update
		exerciseBlockList.remove(exerciseBlock1);		
		trainingTestUtil.updateTrainingBlocks(idTraining, exerciseBlockList);
		
		// Read training
		training = trainingTestUtil.readTraining(idTraining);
		
		assertEquals(1,training.getListExerciseBlock().size());	
		
		
		exerciseBlockList.add(exerciseBlock3);
		trainingTestUtil.updateTrainingBlocks(idTraining, exerciseBlockList);
		
		// Read training
		training = trainingTestUtil.readTraining(idTraining);
		
		assertEquals(2,training.getListExerciseBlock().size());	
		
		
		// Delete training
		trainingTestUtil.deleteTraining(idTraining);

		// Read training
		training = trainingTestUtil.readTraining(idTraining);

		// Make assert
		assertNull(training);
		
	}
}
