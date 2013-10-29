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
import es.uma.sportjump.sjs.model.entities.Exercise;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;
import es.uma.sportjump.sjs.service.services.test.util.CoachServiceTestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service-application-service.xml", "classpath:test-service-application-dao.xml"})  
public class ExerciseServiceTest {
	
	@Autowired
	private ExerciseService exerciseService;
	
	@Autowired
	private CoachServiceTestUtil coachTestUtil;
	
	private Coach coach;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		coach = coachTestUtil.createNewCoach();
	}

	@After
	public void tearDown() throws Exception {
		coachTestUtil.removeCoach(coach);
	}
	
	@Test
	public void testExerciseCRUD(){
				
		//Create exercise block
		ExerciseBlock exerciseBlock =  createExerciseBlock(1);
 
		assertNotNull(exerciseBlock);
		assertNotNull(exerciseBlock.getIdExerciseBlock());
		
		//Get exerciseBock
		ExerciseBlock exerciseBlockAux = exerciseService.findExerciseBlock(exerciseBlock.getIdExerciseBlock());
		
		assertEquals(exerciseBlock.getName(),exerciseBlockAux.getName());
		assertEquals(exerciseBlock.getType(), exerciseBlockAux.getType());
		assertEquals(exerciseBlock.getDescription(),exerciseBlockAux.getDescription());
		List<Exercise> exerciseList = exerciseBlock.getListExercises();
		List<Exercise> exerciseListAux = exerciseBlockAux.getListExercises();
		if(exerciseList == null){
			assertNull(exerciseListAux);
		}else{
			assertEquals(exerciseList.size(), exerciseListAux.size());
			for(Exercise exercise : exerciseList){
				assertTrue(exerciseListAux.contains(exercise));
			}
		}
	
		
		//update exercise block
		String exerciseBlockName2 = "Fuera progresiva";
		exerciseBlock.setName(exerciseBlockName2);
		exerciseService.updateExerciseBlock(exerciseBlock);
		
		//Get exercise block
		exerciseBlockAux = exerciseService.findExerciseBlock(exerciseBlock.getIdExerciseBlock());
		
		assertEquals(exerciseBlockName2,exerciseBlockAux.getName());
		
		//Remove exercise block
		exerciseService.removeExerciseBlock(exerciseBlock);
		
		//Get coach 1
		exerciseBlockAux = exerciseService.findExerciseBlock(exerciseBlock.getIdExerciseBlock());
		
		assertNull(exerciseBlockAux);
	}

	private ExerciseBlock createExerciseBlock(int num) {
		//Variables
		String name = "bloque fuerza" + num;
		String type = "Fuerza" + num;
		String description = "Haremos hincapie en la fuerza de hombros" + num;
		
		String exerciseName1 = "15 X 50kg hombros" + num;
		String exerciseName2 = "10 X 40kg dorsales" + num;
		
		Exercise exercise1 = new Exercise();
		exercise1.setName(exerciseName1);
		exercise1.setPos(1);
		
		Exercise exercise2 = new Exercise();
		exercise2.setName(exerciseName2);
		exercise2.setPos(2);
		
		List<Exercise> exerciseList = new ArrayList<Exercise>();
		exerciseList.add(exercise1);
		exerciseList.add(exercise2);
		
		
		
		return exerciseService.setNewExerciseBlock(name, type, description, exerciseList, coach);
	}
	
	
	@Test
	public void testExerciseByNameCoach(){
		//Create exercise block
		ExerciseBlock exerciseBlock1 =  createExerciseBlock(1);				
		ExerciseBlock exerciseBlock2 =  createExerciseBlock(2);			
		ExerciseBlock exerciseBlock3 =  createExerciseBlock(3);
		
		
		assertEquals(exerciseBlock1.getIdExerciseBlock(), exerciseService.findExerciseBlockByNameAndCoach(exerciseBlock1.getName(), coach).getIdExerciseBlock());
		assertEquals(exerciseBlock2.getIdExerciseBlock(), exerciseService.findExerciseBlockByNameAndCoach(exerciseBlock2.getName(), coach).getIdExerciseBlock());
		assertEquals(exerciseBlock3.getIdExerciseBlock(), exerciseService.findExerciseBlockByNameAndCoach(exerciseBlock3.getName(), coach).getIdExerciseBlock());
		
		//Remove exercise block
		exerciseService.removeExerciseBlock(exerciseBlock1);
		exerciseService.removeExerciseBlock(exerciseBlock2);
		exerciseService.removeExerciseBlock(exerciseBlock3);
	}

}
