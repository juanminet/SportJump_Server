package es.uma.sportjump.sjs.dao.daos.impl.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.uma.sportjump.sjs.dao.daos.ExerciseBlockDao;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Exercise;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;

@Repository("ExerciseBlockDao")
public class ExericesBlockDaoJpaImpl implements ExerciseBlockDao {

	@PersistenceContext( type = PersistenceContextType.EXTENDED)
 	protected EntityManager em;
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void persistExerciseBlock(ExerciseBlock exerciseBlock) {	
		
		Long idExerciseBlock = exerciseBlock.getIdExerciseBlock();
		
		if(idExerciseBlock == null){
			createExerciseBlock(exerciseBlock);
		}else{
			updateExerciseBlock(exerciseBlock);
		}	
	}

	
	private void updateExerciseBlock(ExerciseBlock exerciseBlock) {
		List<Exercise> listExercises = exerciseBlock.getListExercises();
		
		ExerciseBlock exerciseBlockNew = getExerciseBlockById(exerciseBlock.getIdExerciseBlock());
		
		exerciseBlockNew.setName(exerciseBlock.getName());
		exerciseBlockNew.setType(exerciseBlock.getType());
		exerciseBlockNew.setDescription(exerciseBlock.getDescription());			
		
		deleteAllExercises(exerciseBlockNew);		
		exerciseBlockNew.setListExercises(new ArrayList<Exercise>());
			
		
		if(listExercises != null){	
			
			for(Exercise exercise : listExercises){
				exercise.setExerciseBlock(exerciseBlockNew);
				exerciseBlockNew.getListExercises().add(exercise);
				em.persist(exercise);
			}			
			
		}
		
		em.persist(exerciseBlockNew);				
	}


	private void createExerciseBlock(ExerciseBlock exerciseBlock) {
		
		List<Exercise> listExercises = exerciseBlock.getListExercises();
	
		em.persist(exerciseBlock);		
		

		ExerciseBlock exerciseBlockNew = getExerciseBlockById(exerciseBlock.getIdExerciseBlock());
		
		if(listExercises != null){	
			
			for(Exercise exercise : listExercises){
				exercise.setExerciseBlock(exerciseBlockNew);
				exerciseBlockNew.getListExercises().add(exercise);
				em.persist(exercise);
			}			
			
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public ExerciseBlock getExerciseBlockById(Long idBlock) {
		ExerciseBlock exerciseBlock =  em.find(ExerciseBlock.class, idBlock);
		if (exerciseBlock != null){			
			Query query = em.createNamedQuery("findAllExerciseByBlock")
					.setParameter("idBlock", Long.valueOf(exerciseBlock.getIdExerciseBlock()));	
			List<Exercise> listExercises = query.getResultList();
			
			exerciseBlock.setListExercises(listExercises);
		}
		
		return exerciseBlock;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteExerciseBlock(Long idBlock) {
		ExerciseBlock exerciseBlock = getExerciseBlockById(idBlock);
		deleteAllExercises(exerciseBlock);
		
		em.remove(exerciseBlock);
	}
	

	private void deleteAllExercises(ExerciseBlock exerciseBlock){
		List<Exercise> listExercises = exerciseBlock.getListExercises();
		if (listExercises != null){
			for(Exercise exercise : listExercises){
				em.remove(exercise);
			}
		}
	}
	

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ExerciseBlock> getAllExerciseBlockByCoach(Coach coach) {
		
		Query query = em.createNamedQuery("findAllExerciseBlockByCoach")
				.setParameter("idUser", coach.getIdUser());
		
		List<ExerciseBlock> listExerciseBlock = query.getResultList();		
		
		return listExerciseBlock;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ExerciseBlock getCompleteExerciseBlock(ExerciseBlock exerciseBlock) {
		return getExerciseBlockById(exerciseBlock.getIdExerciseBlock());
	}

}
