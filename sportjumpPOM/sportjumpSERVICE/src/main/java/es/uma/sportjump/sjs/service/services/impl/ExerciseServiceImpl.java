package es.uma.sportjump.sjs.service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.uma.sportjump.sjs.dao.daos.ExerciseBlockDao;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Exercise;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;
import es.uma.sportjump.sjs.service.services.ExerciseService;

@Service("exerciseService")
public class ExerciseServiceImpl implements ExerciseService {
	@Autowired
	private ExerciseBlockDao exerciseBlockDao;
	
//	@Autowired
//	private TrainingDao trainingDao;

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public ExerciseBlock setNewExerciseBlock(String name, String type,
			String description, List<Exercise> exerciseList, Coach coach) {
		
		ExerciseBlock exerciseBlock = new ExerciseBlock();
		exerciseBlock.setName(name);
		exerciseBlock.setDescription(description);
		exerciseBlock.setType(type);
		exerciseBlock.setListExercises(exerciseList);
		exerciseBlock.setCoach(coach);
				
		//Persist
		exerciseBlockDao.persistExerciseBlock(exerciseBlock);				
				
		return exerciseBlock;
	}

	@Transactional
	public void updateExerciseBlock(ExerciseBlock exerciseBlock) {		
		exerciseBlockDao.persistExerciseBlock(exerciseBlock);
	}

	@Transactional
	public ExerciseBlock findExerciseBlock(Long idExerciseBlock) {
		return exerciseBlockDao.getExerciseBlockById(idExerciseBlock);
	}
	
	@Transactional
	public ExerciseBlock findExerciseBlockByNameAndCoach(String name,Coach coach) {
		return exerciseBlockDao.getExerciseBlockByNameAndCoach(name, coach);
	}

	@Transactional
	public void removeExerciseBlock(ExerciseBlock exerciseBlock) {
		//TODO	 REMOVE IS NOT WORKING WELL
//		exerciseBlock = exerciseBlockDao.getExerciseBlockById(exerciseBlock.getIdExerciseBlock());			
//		deleteReferencias(exerciseBlock);
//		
		
		
		exerciseBlockDao.deleteExerciseBlock(exerciseBlock.getIdExerciseBlock());
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

	@Transactional
	public List<ExerciseBlock> findAllExerciseBlockByCoach(Coach coach) {
		return exerciseBlockDao.getAllExerciseBlockByCoach(coach);				
	}
}
