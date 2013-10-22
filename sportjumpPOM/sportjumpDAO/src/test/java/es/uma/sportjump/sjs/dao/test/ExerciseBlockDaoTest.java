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

import es.uma.sportjump.sjs.dao.daos.ExerciseBlockDao;
import es.uma.sportjump.sjs.dao.daos.UserDao;
import es.uma.sportjump.sjs.dao.test.util.CoachDaoTestUtil;
import es.uma.sportjump.sjs.dao.test.util.ExerciseBlockDaoTestUtil;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Exercise;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;

//@ContextConfiguration(locations = "classpath:test-jpa-application-dao.xml")  
public class ExerciseBlockDaoTest{
	
	@Autowired
	protected ExerciseBlockDao exerciseBlockDao;
	
	@Autowired
	protected UserDao userDao;
	
	@Autowired
	private CoachDaoTestUtil coachUtil;
	
	@Autowired
	private ExerciseBlockDaoTestUtil exerciseBlockUtil;
	
	
	protected Coach coach;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {	
		coach = coachUtil.createNewCoach();
	}

	@After
	public void tearDown() throws Exception {
		coachUtil.deleteCoach(coach);
	}
	

	public void testExerciseBlockCRUD() {		
		
		//Initialize variables
		String name = "Bloque brutal";
		String type ="Brutality";
		String description = "Es un bloque donde se busca la brutalidad en el entrenamiento";
	
		
		//Create exerciseBlock
		Long idExerciseBlock = exerciseBlockUtil.createExerciseBlock(name,type,description, null, coach);
		
		//Make assert
		assertNotNull(idExerciseBlock);
		
		//Read exerciseBlock
		ExerciseBlock exerciseBlock = exerciseBlockUtil.readExerciseBlock(idExerciseBlock);
		
		//Make assert
		exerciseBlockUtil.makeAssertExerciseBlock(name,type,exerciseBlock);
	
		//Update
		String name2 = "Calentamiento2";
		String type2 = "calentamientos";
		exerciseBlockUtil.updateExerciseBlock(name2,type2,exerciseBlock);
		
		//Read exerciseBlock
		exerciseBlock = exerciseBlockUtil.readExerciseBlock(idExerciseBlock);
		
		
		//Make assert
		exerciseBlockUtil.makeAssertExerciseBlock(name2,type2	,exerciseBlock);
		
		//Delete exerciseBlock
		exerciseBlockUtil.deleteExerciseBlock(exerciseBlock);
		
		//Read exerciseBlock
		exerciseBlock = exerciseBlockUtil.readExerciseBlock(idExerciseBlock);
		
		//Make assert
		assertNull(exerciseBlock);

	}
	
	public void testExercises(){
		//Initialize variables
		String name = "Bloque brutal";
		String type ="Brutality";
		String description = "Es un bloque donde se busca la brutalidad en el entrenamiento";
	
		String exerciseName1 = "15 X 50kg hombros";
		String exerciseName2 = "10 X 40kg dorsales";
		
		Exercise exercise1 = new Exercise();
		exercise1.setName(exerciseName1);
		exercise1.setPos(0);
		
		Exercise exercise2 = new Exercise();
		exercise2.setName(exerciseName2);
		exercise2.setPos(1);
		
		List<Exercise> exerciseList = new ArrayList<Exercise>();
		exerciseList.add(exercise1);
		exerciseList.add(exercise2);
		
		//Create exerciseBlock
		Long idExerciseBlock = exerciseBlockUtil.createExerciseBlock(name,type,description, exerciseList, coach);
		
		//Make assert
		assertNotNull(idExerciseBlock);
		
		//Read exerciseBlock
		ExerciseBlock exerciseBlock = exerciseBlockUtil.readExerciseBlock(idExerciseBlock);
		
		List<Exercise> exerciseListAux = exerciseBlock.getListExercises();
		
		//Make asserts
		assertNotNull(exerciseListAux);
		assertEquals(2, exerciseListAux.size());
		assertEquals (exerciseName1,exerciseListAux.get(0).getName());
		assertEquals (exerciseName2,exerciseListAux.get(1).getName());
		
		//update
		String newExerciseName1 = "35 X 50kg hombros";
		
		exerciseBlock.getListExercises().get(0).setName(newExerciseName1);
		exerciseBlockDao.persistExerciseBlock(exerciseBlock);
		
		//Read exerciseBlock
		exerciseBlock = exerciseBlockUtil.readExerciseBlock(idExerciseBlock);
		
		exerciseListAux = exerciseBlock.getListExercises();
		
		//Make asserts
		assertNotNull(exerciseListAux);
		assertEquals(2, exerciseListAux.size());
		assertEquals (newExerciseName1,exerciseListAux.get(0).getName());
		assertEquals (exerciseName2,exerciseListAux.get(1).getName());
		
		//Delete exerciseBlock
		exerciseBlockUtil.deleteExerciseBlock(exerciseBlock);
		
		//Read exerciseBlock
		exerciseBlock = exerciseBlockUtil.readExerciseBlock(idExerciseBlock);
		
		//Make assert
		assertNull(exerciseBlock);
		
	}
	
	public void testAllExerciseBlocks(){
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
		
		//Read List exercises
		List<ExerciseBlock> exerciseBlockList = exerciseBlockDao.getAllExerciseBlockByCoach(coach);
		int initSize= 0;
		if(exerciseBlockList != null){
			initSize = exerciseBlockList.size();
		}
				

		//Create exerciseBlock
		Long idExerciseBlock1 = exerciseBlockUtil.createExerciseBlock(name1,type1,description1, null, coach);
		Long idExerciseBlock2 = exerciseBlockUtil.createExerciseBlock(name2,type2,description2, null, coach);
		Long idExerciseBlock3 = exerciseBlockUtil.createExerciseBlock(name3,type3,description3, null, coach);
		
		//make asserts
		assertNotNull(idExerciseBlock1);
		assertNotNull(idExerciseBlock2);
		assertNotNull(idExerciseBlock3);
		
		//Read exerciseBlock
		ExerciseBlock exerciseBlock1 = exerciseBlockUtil.readExerciseBlock(idExerciseBlock1);
		ExerciseBlock exerciseBlock2 = exerciseBlockUtil.readExerciseBlock(idExerciseBlock2);
		ExerciseBlock exerciseBlock3 = exerciseBlockUtil.readExerciseBlock(idExerciseBlock3);
		
		//Read List exercises
		exerciseBlockList = exerciseBlockDao.getAllExerciseBlockByCoach(coach);
		
		//asserts
		assertNotNull (exerciseBlockList);
		assertEquals(initSize + 3, exerciseBlockList.size());
		assertTrue(exerciseBlockList.contains(exerciseBlock1));
		assertTrue(exerciseBlockList.contains(exerciseBlock2));
		assertTrue(exerciseBlockList.contains(exerciseBlock3));
		
		//Delete exerciseBlock
		exerciseBlockUtil.deleteExerciseBlock(exerciseBlock1);
		
		//Read List exercises
		exerciseBlockList = exerciseBlockDao.getAllExerciseBlockByCoach(coach);
		
		//asserts
		assertNotNull (exerciseBlockList);
		assertEquals(initSize + 2, exerciseBlockList.size());
		
		//Delete exerciseBlock
		exerciseBlockUtil.deleteExerciseBlock(exerciseBlock2);
		exerciseBlockUtil.deleteExerciseBlock(exerciseBlock3);
	}
}
