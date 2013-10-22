package es.uma.sportjump.sjs.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;

import es.uma.sportjump.sjs.dao.daos.TrainingDao;
import es.uma.sportjump.sjs.dao.test.util.CoachDaoTestUtil;
import es.uma.sportjump.sjs.dao.test.util.ExerciseBlockDaoTestUtil;
import es.uma.sportjump.sjs.dao.test.util.TrainingDaoTestUtil;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;
import es.uma.sportjump.sjs.model.entities.Training;

public class TrainingDaoTest{
	
	
	@Autowired
	private TrainingDao trainingDao;
	
	@Autowired
	private CoachDaoTestUtil coachUtil;
	
	@Autowired
	private ExerciseBlockDaoTestUtil exerciseBlockUtil;
	
	@Autowired
	private TrainingDaoTestUtil trainingUtil;
	
	
	protected Coach coach;
	
	private ExerciseBlock exerciseBlock1;
	private ExerciseBlock exerciseBlock2;
	private ExerciseBlock exerciseBlock3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {	
		coach = coachUtil.createNewCoach();
		
		exerciseBlock1 = exerciseBlockUtil.createCompleteExerciseBlockNum(coach, 1);
		exerciseBlock2 = exerciseBlockUtil.createCompleteExerciseBlockNum(coach, 2);
		exerciseBlock3 = exerciseBlockUtil.createCompleteExerciseBlockNum(coach, 3);
	}

	@After
	public void tearDown() throws Exception {
		exerciseBlockUtil.deleteExerciseBlock(exerciseBlock1);
		exerciseBlockUtil.deleteExerciseBlock(exerciseBlock2);
		exerciseBlockUtil.deleteExerciseBlock(exerciseBlock3);
		
		coachUtil.deleteCoach(coach);
	}
	

	public void testTrainingCRUD() {		
		
		//Initialize variables
		String name = "Entrenamiento velocidad";
		String type ="Velocidad";
		String description = "Es un dia que se entrenara velocidad y fuerza explosiva";
	
		
		//Create training
		Long idTraining = trainingUtil.createTraining(name,type,description, null, coach);
		
		//Make assert
		assertNotNull(idTraining);
		
		//Read training
		Training training = trainingUtil.readTraining(idTraining);
		
		//Make assert
		trainingUtil.makeAssertTraining(name,type,training);
	
		//Update
		String name2 = "Calentamiento";
		String type2 = "Calentamientos";
		trainingUtil.updateTraining(name2,type2,null, training);
		
		//Read training
		training = trainingUtil.readTraining(idTraining);		
		
		//Make assert
		trainingUtil.makeAssertTraining(name2,type2,training);
		
		//Delete training
		trainingUtil.deleteTraining(training);
		
		//Read training
		training = trainingUtil.readTraining(idTraining);
		
		//Make assert
		assertNull(training);

	}
	
	public void testTrainingWithExerciseBlock(){
		// Definition training
		String name = "Rapidez 1";
		String type = "Velocidad";
		String description = "Entrenamos fuerza, y rapidez para coger musculatura";
		
		List<ExerciseBlock> exerciseBlockList = new ArrayList<ExerciseBlock>();
		exerciseBlockList.add(exerciseBlock1);
		exerciseBlockList.add(exerciseBlock2);
		
		
		// Create training
		Long idTraining = trainingUtil.createTraining(name,type, description, exerciseBlockList, coach);
		
		// Read training
		Training training = trainingUtil.readTraining(idTraining);
		
		
		assertEquals(exerciseBlock1.getIdExerciseBlock(),training.getListExerciseBlock().get(0).getIdExerciseBlock());
		assertEquals(exerciseBlock2.getIdExerciseBlock(),training.getListExerciseBlock().get(1).getIdExerciseBlock());
		
		//Update
		exerciseBlockList.remove(exerciseBlock1);		
		trainingUtil.updateTraining(name,type, exerciseBlockList,training);
		
		// Read training
		training = trainingUtil.readTraining(idTraining);
		
		assertEquals(1,training.getListExerciseBlock().size());	
		
		
		exerciseBlockList.add(exerciseBlock3);
		trainingUtil.updateTraining(name,type, exerciseBlockList,training);
		
		// Read training
		training = trainingUtil.readTraining(idTraining);
		
		assertEquals(2,training.getListExerciseBlock().size());	
		
		
		// Delete training
		trainingUtil.deleteTraining(training);

		// Read training
		training = trainingUtil.readTraining(idTraining);

		// Make assert
		assertNull(training);
	}
	
	public void testAllTrainings(){
		//Initialize variables
		String name1 = "Bloque brutal1";
		String type1 ="Brutality1";
		String description1 = "Es un bloque donde se busca la brutalidad en el entrenamiento1";
		

		String name2 = "Bloque brutal2";
		String type2 ="Brutality2";
		String description2 = "Es un bloque donde se busca la brutalidad en el entrenamiento2";
		

		String name3 = "Bloque brutal3";
		String type3 ="Brutality3";
		String description3 = "Es un bloque donde se busca la brutalidad en el entrenamiento3";
		
		List<ExerciseBlock> exerciseBlockList = new ArrayList<ExerciseBlock>();
		exerciseBlockList.add(exerciseBlock1);
		exerciseBlockList.add(exerciseBlock2);
		
		//Read trainings
		List<Training> trainingList =  trainingDao.getAllTrainingByCoach(coach);
		int initSize= 0;
		if(trainingList != null){
			initSize = trainingList.size();
		}
				

		//Create training
		Long idTraining1 = trainingUtil.createTraining(name1,type1,description1, null, coach);
		Long idTraining2 = trainingUtil.createTraining(name2,type2,description2, exerciseBlockList, coach);
		Long idTraining3 = trainingUtil.createTraining(name3,type3,description3, null, coach);
		
		//make asserts
		assertNotNull(idTraining1);
		assertNotNull(idTraining2);
		assertNotNull(idTraining3);
		
		//Read training
		Training training1 = trainingUtil.readTraining(idTraining1);
		Training training2 = trainingUtil.readTraining(idTraining2);
		Training training3 = trainingUtil.readTraining(idTraining3);
		
		//Read List trainings
		trainingList = trainingDao.getAllTrainingByCoach(coach);
		
		//asserts
		assertNotNull (trainingList);
		assertEquals(initSize + 3, trainingList.size());
		assertTrue(trainingList.contains(training1));
		assertTrue(trainingList.contains(training2));
		assertTrue(trainingList.contains(training3));
		
		//Delete training
		trainingUtil.deleteTraining(training1);
		
		//Read List exercises
		trainingList = trainingDao.getAllTrainingByCoach(coach);
		
		//asserts
		assertNotNull (trainingList);
		assertEquals(initSize + 2, trainingList.size());
		
		//Delete exerciseBlock
		trainingUtil.deleteTraining(training2);
		trainingUtil.deleteTraining(training3);
	}
}
