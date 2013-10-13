package es.uma.sportjump.sjs.dao.daos;

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

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Exercise;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;

//@ContextConfiguration(locations = "classpath:test-jpa-application-dao.xml")  
public class ExerciseBlockDaoTest{
	
	@Autowired
	protected ExerciseBlockDao exerciseBlockDao;
	
	@Autowired
	protected UserDao userDao;
	
	
	protected Coach coach;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		//Initialize variables
		String name = "Jose";
		String userName ="Mourinho";
		String dni = "98789878P";
		
		//Create coach
		Long idCoach = createCoach(name,userName,dni);
		
		//Read coach
		coach = readCoach(idCoach);
	}

	@After
	public void tearDown() throws Exception {
		deleteCoach(coach);
	}
	

	public void testExerciseBlockCRUD() {		
		
		//Initialize variables
		String name = "Bloque brutal";
		String type ="Brutality";
		String description = "Es un bloque donde se busca la brutalidad en el entrenamiento";
	
		
		//Create exerciseBlock
		Long idExerciseBlock = createExerciseBlock(name,type,description, null);
		
		//Make assert
		assertNotNull(idExerciseBlock);
		
		//Read exerciseBlock
		ExerciseBlock exerciseBlock = readExerciseBlock(idExerciseBlock);
		
		//Make assert
		makeAssertExerciseBlock(name,type,exerciseBlock);
	
		//Update
		String name2 = "Calentamiento2";
		String type2 = "calentamientos";
		updateExerciseBlock(name2,type2,exerciseBlock);
		
		//Read exerciseBlock
		exerciseBlock = readExerciseBlock(idExerciseBlock);
		
		
		//Make assert
		makeAssertExerciseBlock(name2,type2	,exerciseBlock);
		
		//Delete exerciseBlock
		deleteExerciseBlock(exerciseBlock);
		
		//Read exerciseBlock
		exerciseBlock = readExerciseBlock(idExerciseBlock);
		
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
		exercise1.setPos(1);
		
		Exercise exercise2 = new Exercise();
		exercise2.setName(exerciseName2);
		exercise2.setPos(2);
		
		List<Exercise> exerciseList = new ArrayList<Exercise>();
		exerciseList.add(exercise1);
		exerciseList.add(exercise2);
		
		//Create exerciseBlock
		Long idExerciseBlock = createExerciseBlock(name,type,description, exerciseList);
		
		//Make assert
		assertNotNull(idExerciseBlock);
		
		//Read exerciseBlock
		ExerciseBlock exerciseBlock = readExerciseBlock(idExerciseBlock);
		
		List<Exercise> exerciseListAux = exerciseBlock.getListExercises();
		
		//Make asserts
		assertNotNull(exerciseListAux);
		assertEquals(2, exerciseList.size());
		
		//Delete exerciseBlock
		deleteExerciseBlock(exerciseBlock);
		
		//Read exerciseBlock
		exerciseBlock = readExerciseBlock(idExerciseBlock);
		
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
		Long idExerciseBlock1 = createExerciseBlock(name1,type1,description1, null);
		Long idExerciseBlock2 = createExerciseBlock(name2,type2,description2, null);
		Long idExerciseBlock3 = createExerciseBlock(name3,type3,description3, null);
		
		//make asserts
		assertNotNull(idExerciseBlock1);
		assertNotNull(idExerciseBlock2);
		assertNotNull(idExerciseBlock3);
		
		//Read exerciseBlock
		ExerciseBlock exerciseBlock1 = readExerciseBlock(idExerciseBlock1);
		ExerciseBlock exerciseBlock2 = readExerciseBlock(idExerciseBlock2);
		ExerciseBlock exerciseBlock3 = readExerciseBlock(idExerciseBlock3);
		
		//Read List exercises
		exerciseBlockList = exerciseBlockDao.getAllExerciseBlockByCoach(coach);
		
		//asserts
		assertNotNull (exerciseBlockList);
		assertEquals(initSize + 3, exerciseBlockList.size());
		assertTrue(exerciseBlockList.contains(exerciseBlock1));
		assertTrue(exerciseBlockList.contains(exerciseBlock2));
		assertTrue(exerciseBlockList.contains(exerciseBlock3));
		
		//Delete exerciseBlock
		deleteExerciseBlock(exerciseBlock1);
		
		//Read List exercises
		exerciseBlockList = exerciseBlockDao.getAllExerciseBlockByCoach(coach);
		
		//asserts
		assertNotNull (exerciseBlockList);
		assertEquals(initSize + 2, exerciseBlockList.size());
		
		//Delete exerciseBlock
		deleteExerciseBlock(exerciseBlock2);
		deleteExerciseBlock(exerciseBlock3);
	}
		
		
	
	//---------------------------------------------------------------------   PRIVATE METHODS    --------------------------------------------------------------------------//

	protected Long createExerciseBlock(String name, String type,	String description, List<Exercise> exerciseList) {				
		//create object
		ExerciseBlock exerciseBlock = new ExerciseBlock();
		exerciseBlock.setName(name);
		exerciseBlock.setDescription(description);
		exerciseBlock.setType(type);
		exerciseBlock.setListExercises(exerciseList);
		exerciseBlock.setCoach(coach);
		
		//Persist
		exerciseBlockDao.persistExerciseBlock(exerciseBlock);
		
		return Long.valueOf(exerciseBlock.getIdExerciseBlock());
	}
	
	protected ExerciseBlock readExerciseBlock(Long idExerciseBlock) {				
		return exerciseBlockDao.getExerciseBlockById(idExerciseBlock);
	}

	
	protected void deleteExerciseBlock(ExerciseBlock exerciseBlock) {		
		//remove
		exerciseBlockDao.deleteExerciseBlock(exerciseBlock.getIdExerciseBlock());
	}

	protected void updateExerciseBlock(String name2, String type2,ExerciseBlock exerciseBlock) {
		//set attributes
		exerciseBlock.setName(name2);
		exerciseBlock.setType(type2);
		
		exerciseBlockDao.persistExerciseBlock(exerciseBlock);		
	}

	protected void makeAssertExerciseBlock(String name, String type, ExerciseBlock exerciseBlock) {
		assertEquals(name, exerciseBlock.getName());
		assertEquals(type, exerciseBlock.getType());
	}


	

	/*-------------------------------------------------------------      COACH ----------------------------------------------------------------*/
	 
	protected Long createCoach(String name, String userName, String dni) {	
		
		//Create coach
		Coach coach = new Coach();
		coach.setName(name);
		coach.setUserName(userName);
		coach.setDni(dni);
		
		//Persist coach		
		userDao.persistCoach(coach);
		

		//return idCoach
		return Long.valueOf(coach.getIdUser());	
	}
	
	protected Coach readCoach(Long idCoach){		
		
		//Read Coach
		Coach coach = userDao.getCoachById(idCoach);
		
		return coach;
	}
	
	protected void deleteCoach(Coach coach){
		
		//Remove coach
		userDao.deleteCoach(Long.valueOf(coach.getIdUser()));
	}
}
