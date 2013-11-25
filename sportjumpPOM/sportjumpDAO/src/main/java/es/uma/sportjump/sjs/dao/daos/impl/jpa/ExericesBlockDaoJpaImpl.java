package es.uma.sportjump.sjs.dao.daos.impl.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.uma.sportjump.sjs.dao.daos.ExerciseBlockDao;
import es.uma.sportjump.sjs.dao.daos.TrainingDao;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Exercise;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;

@Repository("exerciseBlockDao")
public class ExericesBlockDaoJpaImpl implements ExerciseBlockDao {
	
	@Autowired
	TrainingDao trainingDao;
	

	@PersistenceContext( type = PersistenceContextType.EXTENDED)
 	protected EntityManager em;
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void persistExerciseBlock(ExerciseBlock exerciseBlock) {	
		
		em.persist(exerciseBlock);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public ExerciseBlock getExerciseBlockById(Long idBlock) {
		ExerciseBlock exerciseBlock =  em.find(ExerciseBlock.class, idBlock);
//		if (exerciseBlock != null){			
//			Query query = em.createNamedQuery("findAllExerciseByBlock")
//					.setParameter("idBlock", Long.valueOf(exerciseBlock.getIdExerciseBlock()));	
//			List<Exercise> listExercises = query.getResultList();
//			
//			exerciseBlock.setListExercises(listExercises);
//		}
		
		return exerciseBlock;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteExerciseBlock(Long idBlock) {		
//		ExerciseBlock exerciseBlock = getExerciseBlockById(idBlock);			
//		deleteReferencias(exerciseBlock);
		
		
		ExerciseBlock exerciseBlockUpdated = getExerciseBlockById(idBlock);
		deleteAllExercises(exerciseBlockUpdated);
		em.remove(exerciseBlockUpdated);
	}
	

//	private void deleteReferencias(ExerciseBlock exerciseBlock) {
//		//Training references
//		List<Training> trainingList = exerciseBlock.getListTraining();
//		
//		for(Training training: trainingList){
//			Training trainingManaged = trainingDao.getCompleteTrainingById(training.getIdTraining());
//			List<ExerciseBlock> exerciseBlockList = trainingManaged.getListExerciseBlock();
//			List<ExerciseBlock> exerciseBlockListUpdated = new ArrayList<ExerciseBlock>();
//			for(ExerciseBlock block : exerciseBlockList){
//				if (!block.getIdExerciseBlock().equals(exerciseBlock.getIdExerciseBlock())){
//					exerciseBlockListUpdated.add(block);
//				}
//			}
//			trainingManaged.setListExerciseBlock(exerciseBlockListUpdated);
//			
//			trainingDao.persistTraining(trainingManaged);			
//		}		
//	}


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


	@Transactional(propagation = Propagation.REQUIRED)
	public ExerciseBlock getExerciseBlockByNameAndCoach(String name, Coach coach) {
		Query query = em.createNamedQuery("findExerciseBlockByNameAndCoach")
				.setParameter("idUser", coach.getIdUser())
				.setParameter("name", name);
		
		ExerciseBlock exerciseBlock = (ExerciseBlock) query.getSingleResult();
		return exerciseBlock;
	}

}
