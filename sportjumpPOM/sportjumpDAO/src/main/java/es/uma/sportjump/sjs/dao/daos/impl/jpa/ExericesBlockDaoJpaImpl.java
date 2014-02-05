package es.uma.sportjump.sjs.dao.daos.impl.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.uma.sportjump.sjs.dao.daos.ExerciseBlockDao;
import es.uma.sportjump.sjs.dao.daos.TrainingDao;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Exercise;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;

@Repository("exerciseBlockDao")
public class ExericesBlockDaoJpaImpl implements ExerciseBlockDao {
	
	@Autowired
	TrainingDao trainingDao;
	

	@PersistenceContext
 	protected EntityManager em;
	
	

	public void persistExerciseBlock(ExerciseBlock exerciseBlock) {	
		if (exerciseBlock.getIdExerciseBlock() != null){
			updateExerciseBlock(exerciseBlock);
		}else{
			em.persist(exerciseBlock);
		}
	}
	
	
	private void updateExerciseBlock(ExerciseBlock exerciseBlock) {		
		
		ExerciseBlock persistentExerciseBlock = em.find(ExerciseBlock.class, exerciseBlock.getIdExerciseBlock());		

		// Remove old Exercise references
		if (persistentExerciseBlock.getListExercises() != null){
			persistentExerciseBlock.getListExercises().clear();			
			em.merge(persistentExerciseBlock);	
			em.flush();
		}
		
		// Update the block
		persistentExerciseBlock.setName(exerciseBlock.getName());
		persistentExerciseBlock.setType(exerciseBlock.getType());
		persistentExerciseBlock.setDescription(exerciseBlock.getDescription());
		
		List<Exercise> listExercises = exerciseBlock.getListExercises();
		
		if (listExercises != null){
			List<Exercise> persistentListExercises = persistentExerciseBlock.getListExercises();
			
			for (Exercise exercise : listExercises){
				persistentListExercises.add(exercise);
			}
		}
		
		em.merge(persistentExerciseBlock);	
	}


	
	public ExerciseBlock getExerciseBlockById(Long idBlock) {
		ExerciseBlock exerciseBlock =  em.find(ExerciseBlock.class, idBlock);		

		if (exerciseBlock != null){
			em.detach(exerciseBlock);
		}
		
		return exerciseBlock;
	}

	
	public void deleteExerciseBlock(Long idBlock) {		
		
		ExerciseBlock persistentExerciseBlock =  em.find(ExerciseBlock.class, idBlock);

		em.remove(persistentExerciseBlock);
		em.flush();
	}
	

	@SuppressWarnings("unchecked")	
	public List<ExerciseBlock> getAllExerciseBlockByCoach(Coach coach) {
		
		Query query = em.createNamedQuery("findAllExerciseBlockByCoach")
				.setParameter("idUser", coach.getIdUser());
		
		List<ExerciseBlock> listExerciseBlock = query.getResultList();		
		
		return listExerciseBlock;
	}


	public ExerciseBlock getCompleteExerciseBlock(ExerciseBlock exerciseBlock) {
		return em.find(ExerciseBlock.class, exerciseBlock.getIdExerciseBlock());
	}


	
	public ExerciseBlock getExerciseBlockByNameAndCoach(String name, Coach coach) {
		Query query = em.createNamedQuery("findExerciseBlockByNameAndCoach")
				.setParameter("idUser", coach.getIdUser())
				.setParameter("name", name);
		
		ExerciseBlock exerciseBlock = (ExerciseBlock) query.getSingleResult();
		return exerciseBlock;
	}
}
