package es.uma.sportjump.sjs.service.services;

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
public class ExerciseServiceTest {
	
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
		Training training =  createTraining();
 
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

	private Training createTraining() {
		
		String name = "Dia de rapidez";
		String type = "Velocidad";
		String description = "Es un dia de entrenamiento de ejercicios de fuerza explosiva para ganar velocida";
		
		List<ExerciseBlock> exerciseBlockList = new ArrayList<ExerciseBlock>();
		exerciseBlockList.add(exerciseBlock1);
		exerciseBlockList.add(exerciseBlock2);
		exerciseBlockList.add(exerciseBlock3);		
		
		return trainingService.setNewTraining(name, type, description, exerciseBlockList, coach);
		
	}
	
		
}
