package es.uma.sportjump.sjs.model.entities.test.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;
import es.uma.sportjump.sjs.model.entities.Training;

public class TrainingTestUtil {
	
	private static EntityManagerFactory entityManagerFactory;
	private static TrainingTestUtil trainingTestUtil;
	
	private TrainingTestUtil(EntityManagerFactory entityManagerFactoryNew){
		entityManagerFactory = entityManagerFactoryNew;
	}
	
	private static synchronized TrainingTestUtil getSynchronizedInstance( EntityManagerFactory entityManagerFactory){
		if (trainingTestUtil == null){
			trainingTestUtil = new TrainingTestUtil(entityManagerFactory);
		}
		
		return trainingTestUtil;
	}
	
	public static TrainingTestUtil getInstance(EntityManagerFactory entityManagerFactory){
		return getSynchronizedInstance(entityManagerFactory);
	}

	public Long createTraining(String name, String type, String description,Coach coach) {
		//Create training
		Training training = new Training();
		training.setName(name);
		training.setDescription(description);
		training.setType(type);
		training.setCoach(coach);
		
		//Persist entity
		Long idTraining = persistTraining(training);
		
		return idTraining;
	}
	
	
	public Long createTrainingWithBlocks(String name, String type,	String description, Coach coach,List<ExerciseBlock> exerciseBlockList) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//Create list
		
		List<ExerciseBlock> exerciseBlockListNew = new ArrayList<ExerciseBlock>();
		
		for(ExerciseBlock block : exerciseBlockList){
			exerciseBlockListNew.add(entityManager.find(ExerciseBlock.class, block.getIdExerciseBlock()));	
		}
		
		
		
		
		//Create training
		Training training = new Training();
		training.setName(name);
		training.setDescription(description);
		training.setType(type);
		training.setCoach(coach);
		training.setListExerciseBlock(exerciseBlockListNew);
		
		//Persist entity

		
		entityManager.getTransaction().begin();
		entityManager.persist(training);
		entityManager.getTransaction().commit();
		
		return Long.valueOf(training.getIdTraining());
	}

	
	public Long persistTraining(Training training) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(training);
		entityManager.getTransaction().commit();
		
		return Long.valueOf(training.getIdTraining());		
	}

	public Training readTraining(Long idTraining) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//read training
		Training block = entityManager.find(Training.class, idTraining) ;
		return block;
	}

	public void makeAssertTraining(String name, String type, String description, Training training) {
		assertEquals(name,training.getName());
		assertEquals(type, training.getType());
		assertEquals(description, training.getDescription());			
	}

	public void updateTraining(Long idTraining, String newName) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//read training
		Training training = entityManager.find(Training.class, idTraining);
		
		//update training
		training.setName(newName);
		
		//persist
		entityManager.getTransaction().begin();
		entityManager.persist(training);
		entityManager.getTransaction().commit();
	}
	
	public void updateTrainingBlocks(Long idTraining, List<ExerciseBlock> exerciseBlockList) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//read training
		Training training = entityManager.find(Training.class, idTraining);
		
		
		//Create list
		
		List<ExerciseBlock> exerciseBlockListNew = new ArrayList<ExerciseBlock>();
		
		for(ExerciseBlock block : exerciseBlockList){
			exerciseBlockListNew.add(entityManager.find(ExerciseBlock.class, block.getIdExerciseBlock()));	
		}
		
		
		//update training
		training.setListExerciseBlock(exerciseBlockListNew);
		
		//persist
		entityManager.getTransaction().begin();
		entityManager.persist(training);
		entityManager.getTransaction().commit();
	}

	public void deleteTraining(Long idTraining) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//remove training
		Training block = entityManager.find(Training.class, idTraining);
		
		entityManager.getTransaction().begin();
		entityManager.remove(block);
		entityManager.getTransaction().commit();		
	}


	

}
