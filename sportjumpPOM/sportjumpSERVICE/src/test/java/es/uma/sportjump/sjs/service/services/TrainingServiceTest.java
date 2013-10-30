package es.uma.sportjump.sjs.service.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;
import es.uma.sportjump.sjs.model.entities.Training;
import es.uma.sportjump.sjs.service.services.test.util.CoachServiceTestUtil;
import es.uma.sportjump.sjs.service.services.test.util.ExerciseServiceTestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service-application-service.xml", "classpath:test-service-application-dao.xml"})  
public class TrainingServiceTest {
	
	@Autowired
	private TrainingService trainingService;
	
	@Autowired
	private CoachServiceTestUtil coachTestUtil;
	
	@Autowired
	private ExerciseServiceTestUtil exerciseTestUtil;
	
	private Coach coach;
	
	private  ExerciseBlock exerciseBlock1;
	private  ExerciseBlock exerciseBlock2;
	private  ExerciseBlock exerciseBlock3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		coach = coachTestUtil.createNewCoach();
		
		exerciseBlock1 = exerciseTestUtil.createExerciseBlock(1, coach);
		exerciseBlock2 = exerciseTestUtil.createExerciseBlock(2, coach);
		exerciseBlock3 = exerciseTestUtil.createExerciseBlock(3, coach);
		
		
	}

	@After
	public void tearDown() throws Exception {
		
		exerciseTestUtil.removeExerciseBlock(exerciseBlock1);
		exerciseTestUtil.removeExerciseBlock(exerciseBlock2);
		exerciseTestUtil.removeExerciseBlock(exerciseBlock3);
		coachTestUtil.removeCoach(coach);
	}
	
	@Test
	public void testTrainingCRUD(){
				
		//Create training
		Training training =  createTraining(1);
 
		assertNotNull(training);
		assertNotNull(training.getIdTraining());
		
		//Get Training
		Training trainingAux = trainingService.findExerciseTraining(training.getIdTraining());
		
		
		assertEquals(training.getName(),trainingAux.getName());
		assertEquals(training.getType(), trainingAux.getType());
		assertEquals(training.getDescription(),trainingAux.getDescription());
		List<ExerciseBlock> exerciseBlockList = training.getListExerciseBlock();
		List<ExerciseBlock> exerciseBlockListAux = trainingAux.getListExerciseBlock();
		if(exerciseBlockList == null){
			assertNull(exerciseBlockListAux);
		}else{
			assertEquals(exerciseBlockList.size(), exerciseBlockListAux.size());
			for(ExerciseBlock exercise : exerciseBlockList){
				assertTrue(exerciseBlockListAux.contains(exercise));
			}
		}
	
		
		//update training
		String trainingName2 = "Fuerza progresiva";
		training.setName(trainingName2);
		trainingService.updateTraining(training);
		
		//Get training
		trainingAux = trainingService.findExerciseTraining(training.getIdTraining());
		
		assertEquals(trainingName2,trainingAux.getName());
		
		//Remove training
		trainingService.removeTraining(trainingAux);
		
		//Get training
		trainingAux = trainingService.findExerciseTraining(training.getIdTraining());
		
		assertNull(trainingAux);
	}
	
	@Test
	public void testAllTrainings(){
		
		
		//Get Training
		List<Training> trainingList = trainingService.findAllTraining(coach);
		int initSize = trainingList.size();		
		//Create training
		Training training1 =  createTraining(1);
		Training training2 =  createTraining(2);
		Training training3 =  createTraining(3);
		
		
 
		assertNotNull(training1);
		assertNotNull(training1.getIdTraining());
		assertNotNull(training2);
		assertNotNull(training2.getIdTraining());
		assertNotNull(training3);
		assertNotNull(training3.getIdTraining());
		
		//Get Training
		trainingList = trainingService.findAllTraining(coach);
		
		assertEquals(initSize + 3, trainingList.size());
		
		assertTrue(trainingList.contains(training1));
		assertTrue(trainingList.contains(training2));
		assertTrue(trainingList.contains(training3));
		
		
		
		//Remove training
		trainingService.removeTraining(training2);
		
		//Get Training
		trainingList = trainingService.findAllTraining(coach);
		
		assertEquals(initSize + 2, trainingList.size());
		
		assertTrue(trainingList.contains(training1));
		assertFalse(trainingList.contains(training2));
		assertTrue(trainingList.contains(training3));
	
		//Remove training
		trainingService.removeTraining(training1);
		trainingService.removeTraining(training3);
		
		
		//Get Training
		trainingList = trainingService.findAllTraining(coach);
		
		assertEquals(initSize, trainingList.size());
	}
	
	
	@Test
	public void testFindTrainingByNameAndCoach(){
		Training trainingExpected1 = createTraining(1);
		Training trainingExpected2 = createTraining(2);
		Training trainingExpected3 = createTraining(3);
		
		
		//Get training
		Training training1 = trainingService.findTrainingByNameAndCoach(trainingExpected1.getName(), coach);
		Training training2 = trainingService.findTrainingByNameAndCoach(trainingExpected2.getName(), coach);
		Training training3 = trainingService.findTrainingByNameAndCoach(trainingExpected3.getName(), coach);
		
		
		//Make asserts
		assertEquals(trainingExpected1.getIdTraining(),training1.getIdTraining());
		assertEquals(trainingExpected2.getIdTraining(),training2.getIdTraining());
		assertEquals(trainingExpected3.getIdTraining(),training3.getIdTraining());
		
		//Remove trainings
		trainingService.removeTraining(training1);
		trainingService.removeTraining(training2);
		trainingService.removeTraining(training3);		
	}
		


	private Training createTraining(int num) {
		
		String name = "Dia de rapidez" +  num;
		String type = "Velocidad" +  num;
		String description = "Es un dia de entrenamiento de ejercicios de fuerza explosiva para ganar velocida" +  num;
		
		List<ExerciseBlock> exerciseBlockList = new ArrayList<ExerciseBlock>();
		exerciseBlockList.add(exerciseBlock1);
		exerciseBlockList.add(exerciseBlock2);
		exerciseBlockList.add(exerciseBlock3);		
		
		return trainingService.setNewTraining(name, type, description, exerciseBlockList, coach);
		
	}
	
		
}
