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

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateExerciseBlock(ExerciseBlock exerciseBlock) {
		exerciseBlockDao.persistExerciseBlock(exerciseBlock);
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public ExerciseBlock findExerciseBlock(Long idExerciseBlock) {
		return exerciseBlockDao.getExerciseBlockById(idExerciseBlock);
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void removeExerciseBlock(ExerciseBlock exerciseBlock) {
		exerciseBlockDao.deleteExerciseBlock(exerciseBlock.getIdExerciseBlock());
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<ExerciseBlock> findAllExerciseBlockByCoach(Coach coach) {
		return exerciseBlockDao.getAllExerciseBlockByCoach(coach);				
	}

}
