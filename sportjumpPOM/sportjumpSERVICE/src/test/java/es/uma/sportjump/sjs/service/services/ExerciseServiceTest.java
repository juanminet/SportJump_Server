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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service-application-service.xml", "classpath:test-service-application-dao.xml"})  
public class ExerciseServiceTest {
	
	@Autowired
	private ExerciseService exerciseService;
	
	@Autowired
	private UserService userService;
	
	private Coach coach;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		String coachName1 = "Jose";
		String coachUserName1 =  "Mourinho";
		String coachSurname1 =  "Fernandez";
		String coachEmail1 ="asdf@asdf.com";
		String coachDni1 = "11111111P";
		
		Long idCoach = userService.setNewCoach(coachName1, coachUserName1, coachSurname1, coachDni1, coachEmail1, null, null, null, null, null);
		
		coach = userService.findCoach(idCoach);
	}

	@After
	public void tearDown() throws Exception {
		userService.removeCoach(coach);
	}
	
	@Test
	public void testExerciseCRUD(){
				
		//Create exercise block
		ExerciseBlock exerciseBlock =  createExerciseBlock();
 
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

	private ExerciseBlock createExerciseBlock() {
		//Variables
		String name = "bloque fuerza";
		String type = "Fuerza";
		String description = "Haremos hincapié en la fuerza de hombros";
		
		String exerciseName1 = "15 X 50kg hombros";
		String exerciseName2 = "10 X 40kg dorsales";
		
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

}
