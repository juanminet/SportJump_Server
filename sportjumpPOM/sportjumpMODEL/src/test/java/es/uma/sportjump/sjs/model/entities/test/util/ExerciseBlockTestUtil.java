package es.uma.sportjump.sjs.model.entities.test.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;

public class ExerciseBlockTestUtil {
	
	private static EntityManagerFactory entityManagerFactory;
	private static ExerciseBlockTestUtil exerciseBlockUtil;
	
	private ExerciseBlockTestUtil(EntityManagerFactory entityManagerFactoryNew){
		entityManagerFactory = entityManagerFactoryNew;
	}
	
	private static synchronized ExerciseBlockTestUtil getSynchronizedInstance( EntityManagerFactory entityManagerFactory){
		if (exerciseBlockUtil == null){
			exerciseBlockUtil = new ExerciseBlockTestUtil(entityManagerFactory);
		}
		
		return exerciseBlockUtil;
	}
	
	public static ExerciseBlockTestUtil getInstance(EntityManagerFactory entityManagerFactory){
		return getSynchronizedInstance(entityManagerFactory);
	}
	
	public ExerciseBlock createCompleteExerciseBlockNum(Coach coach, int num){
		// Definition ExerciseBlock
		String name = "Bloque rapidez" + num;
		String type = "Velocidad"+ num;
		String description = "Bloque donde se trabaja la velocidad inmediata y la fuerza explosiva"+ num;

		
		//Create ExerciseBlock
		ExerciseBlock block = new ExerciseBlock();
		block.setName(name);
		block.setDescription(description);
		block.setType(type);
		block.setCoach(coach);
		
		//Persist entity
		Long idExerciseBlock = persistExerciseBlock(block);
		
		return readExericseBock(idExerciseBlock);
	}

	
	public ExerciseBlock createCompleteExerciseBlock(Coach coach){
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
	
	public void checkNullableExerciseBlock(ExerciseBlock block) {
		
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

	
	public Long createExerciseBlock(String name, String type, String description, Coach coach) {			
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
	
	public Long persistExerciseBlock(ExerciseBlock block) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(block);
		entityManager.getTransaction().commit();
		
		return Long.valueOf(block.getIdExerciseBlock());		
	}

	
	public ExerciseBlock readExericseBock(Long idBlock) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//read exercise block
		ExerciseBlock block = entityManager.find(ExerciseBlock.class, idBlock) ;
		return block;
	}

	public void makeAssertExerciseBlock(String name, String type,	String description, ExerciseBlock block) {
		assertEquals(name,block.getName());
		assertEquals(type, block.getType());
		assertEquals(description, block.getDescription());		
	}
	

	public void deleteExerciseBlock(Long idBlock) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//remove coach
		ExerciseBlock block = entityManager.find(ExerciseBlock.class, idBlock);
		
		entityManager.getTransaction().begin();
		entityManager.remove(block);
		entityManager.getTransaction().commit();
	}
	


	public void updateExerciseBlock(Long idBlock, String newName) {			
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
}
