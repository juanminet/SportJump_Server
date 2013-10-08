package es.uma.sportjump.sjs.model.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExerciseBlockModelEtnityTest {


	private static EntityManagerFactory entityManagerFactory = null;
	
	//Variables
	private Coach coach;
	
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
	}

	@After
	public void tearDown() throws Exception {
		// Delete Coach
		deleteCoach(coach.getIdUser());
	}

	@Test
	public void testCRUD() {
		// Definition ExerciseBlock
		String name = "1Bloque rapidez";
		String type = "1Velocidad";
		String description = "Bloque donde se trabaja la velocidad inmediata y la fuerza explosiva";

		// Create Exercise Block
		Long idBlock = createExerciseBlock(name,type, description);

		// Make assert
		assertNotNull(idBlock);

		// Read Exercise Block
		ExerciseBlock block = readExericseBock(idBlock);
		
		// Make assert
		makeAssertExerciseBlock(name,type,description, block);

		// Update exercise block
		String newName = "Rapidez";

		updateExerciseBlock(idBlock, newName);

		// Read exercise block
		block = readExericseBock(idBlock);

		// Make assert
		makeAssertExerciseBlock(newName,type,description, block);

		// Delete exercise block
		deleteExerciseBlock(idBlock);

		// Read exercise block
		block = readExericseBock(idBlock);

		// Make assert
		assertNull(block);
	}
	
	
	@Test
	public void testNullables() {
		//Create block
		ExerciseBlock block = createCompleteExerciseBlock();
		assertNotNull(block);
		
		//check name
		block= createCompleteExerciseBlock();
		block.setName(null);
		checkNullableExerciseBlock(block);
		
		//check type
		block= createCompleteExerciseBlock();
		block.setType(null);
		checkNullableExerciseBlock(block);
			
	}
	
	
	private ExerciseBlock createCompleteExerciseBlock(){
		// Definition ExerciseBlock
		String name = "Bloque rapidez";
		String type = "Velocidad";
		String description = "Bloque donde se trabaja la velocidad inmediata y la fuerza explosiva";

		
		//Create ExerciseBlock
		ExerciseBlock block = new ExerciseBlock();
		block.setName(name);
		block.setDescription(description);
		block.setType(type);
		block.setCoach(coach);
		
		return block;
	}
	
	private void checkNullableExerciseBlock(ExerciseBlock block) {
		
		boolean check = false;
		
		try{
			persistExerciseBlock(block);
		}catch (PersistenceException persistenceException) {
			check=true;
		}catch (Exception exception){
			check=true;
		}
		
		assertTrue(check);
	}

	
	private Long createExerciseBlock(String name, String type,	String description) {			
		//Create ExerciseBlock
		ExerciseBlock block = new ExerciseBlock();
		block.setName(name);
		block.setDescription(description);
		block.setType(type);
		block.setCoach(coach);
		
		//Persist entity
		Long idExerciseBlock = persistExerciseBlock(block);
		
		return idExerciseBlock;
	}
	
	private Long persistExerciseBlock(ExerciseBlock block) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
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

	private void makeAssertExerciseBlock(String name, String type,	String description, ExerciseBlock block) {
		assertEquals(name,block.getName());
		assertEquals(type, block.getType());
		assertEquals(description, block.getDescription());		
	}
	

	private void deleteExerciseBlock(Long idBlock) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//remove coach
		ExerciseBlock block = entityManager.find(ExerciseBlock.class, idBlock);
		
		entityManager.getTransaction().begin();
		entityManager.remove(block);
		entityManager.getTransaction().commit();
	}
	


	private void updateExerciseBlock(Long idBlock, String newName) {			
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//read exercise block
		ExerciseBlock block = entityManager.find(ExerciseBlock.class, idBlock);
		
		//update exercise block
		block.setName(newName);
		
		//persist
		entityManager.getTransaction().begin();
		entityManager.persist(block);
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
