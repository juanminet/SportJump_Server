package es.uma.sportjump.sjs.model.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExerciseModelEntityTest {

	private static EntityManagerFactory entityManagerFactory = null;
	
	//Variables
	private Coach coach;
	private ExerciseBlock exerciseBlock;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("sportjumpJpaPU");
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		entityManagerFactory = null;
	}

	@Before
	public void setUp() throws Exception {
		// Definition Coach
		String name = "Mourinho";
		String surname = "Garcia";
		String userName = "Jose";
		String email = "asdf@asdf.es";
		String dni = "88779988R";

		// Create Coach
		Long idCoach = createCoach(name,surname, userName, email,dni);
		coach = readCoach(idCoach);
		
		// Definition ExerciseBlock
		String nameExerciseBlock = "Bloque rapidez";
		String type = "Velocidad";
		String description = "Bloque donde se trabaja la velocidad inmediata y la fuerza explosiva";

		// Create Exercise Block
		Long idBlock = createExerciseBlock(nameExerciseBlock,type, description);

		// Read Exercise Block
		exerciseBlock = readExericseBock(idBlock);
	}

	@After
	public void tearDown() throws Exception {
		// Delete ExerciseBlock
		deleteExerciseBlock(exerciseBlock.getIdExerciseBlock());
		
		// Delete Coach
		deleteCoach(coach.getIdUser());		
	}

	@Test
	public void testCRUD() {
		// Definition exercise
		String name = "2x30m recuperando 5min";
	
		// Create exercise
		Long idExercise = createExercise(name);

		// Make assert
		assertNotNull(idExercise);

		// Read Exercise 
		Exercise exercise = readExericse(idExercise);
		
		// Make assert
		makeAssertExercise(name,exercise);

		// Update exercise 
		String newName = "Rapidez";

		updateExercise(idExercise, newName);

		// Read exercise 
		exercise = readExericse(idExercise);

		// Make assert
		makeAssertExercise(newName,exercise);

		// Delete exercise 
		deleteExercise(idExercise);

		// Read exercise 
		exercise = readExericse(idExercise);

		// Make assert
		assertNull(exercise);
	}
	
	
	
	

	private Long createExercise(String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//Create Exercise
		Exercise exercise = new Exercise();
		exercise.setName(name);
		//exercise.setExerciseBlock(exerciseBlock);
		
		//Persist entity
		entityManager.getTransaction().begin();
		entityManager.persist(exercise);
		entityManager.getTransaction().commit();
		
		return Long.valueOf(exercise.getIdExercise());
	}
	
	
	
	private Exercise readExericse(Long idExercise) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
			
		//read exercise 
		Exercise exercise = entityManager.find(Exercise.class, idExercise) ;
		return exercise;
	}
	
	private void makeAssertExercise(String name, Exercise exercise) {
		assertEquals(name, exercise.getName());
	}
	
	
	private void updateExercise(Long idExercise, String newName) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
			
		//read exercise 
		Exercise exercise = entityManager.find(Exercise.class, idExercise);
		
		//update exercise 
		exercise.setName(newName);
		
		entityManager.getTransaction().begin();
		entityManager.persist(exercise);
		entityManager.getTransaction().commit();
	}


	private void deleteExercise(Long idExercise) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//read exercise block
		Exercise exercise = entityManager.find(Exercise.class, idExercise) ;
	
		entityManager.getTransaction().begin();
		entityManager.remove(exercise);
		entityManager.getTransaction().commit();
	}

	
	//Exercise

	private Long createExerciseBlock(String name, String type,	String description) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//Create ExerciseBlock
		ExerciseBlock block = new ExerciseBlock();
		block.setName(name);
		block.setDescription(description);
		block.setType(type);
		block.setCoach(coach);
		
		//Persist entity
		entityManager.getTransaction().begin();
		entityManager.persist(block);
		entityManager.getTransaction().commit();
		
		return Long.valueOf(block.getIdExerciseBlock());
	}
	
	private ExerciseBlock readExericseBock(Long idBlock) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//read exercise block
		ExerciseBlock block = entityManager.find(ExerciseBlock.class, idBlock) ;
		return block;
	}


	private void deleteExerciseBlock(Long idBlock) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//remove coach
		ExerciseBlock block = entityManager.find(ExerciseBlock.class, idBlock);
		
		entityManager.getTransaction().begin();
		entityManager.remove(block);
		entityManager.getTransaction().commit();
	}
	
	//Coach
	private Long createCoach(String name, String surname, String userName, String email, String dni) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//Create coach
		Coach coach = new Coach();
		coach.setName(name);
		coach.setUserName(userName);
		coach.setSurname(surname);
		coach.setEmail(email);
		coach.setDni(dni);
	
		
		//Persist entity
		entityManager.getTransaction().begin();
		entityManager.persist(coach);
		entityManager.getTransaction().commit();
		
		//return idCoach
		return Long.valueOf(coach.getIdUser());
	}
	
	private Coach readCoach(Long idCoach) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//read Coach
		Coach coach = entityManager.find(Coach.class, idCoach);
		return coach;
	}
	
	private void deleteCoach(Long idCoach) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//remove coach
		Coach coach = entityManager.find(Coach.class, idCoach);
		
		entityManager.getTransaction().begin();
		entityManager.remove(coach);
		entityManager.getTransaction().commit();
		
	}
}
