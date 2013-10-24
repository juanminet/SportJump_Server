package es.uma.sportjump.sjs.service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.uma.sportjump.sjs.dao.daos.TrainingDao;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;
import es.uma.sportjump.sjs.model.entities.Training;
import es.uma.sportjump.sjs.service.services.TrainingService;

@Service("trainingService")
public class TrainingServiceImpl implements TrainingService{
	
	@Autowired
	TrainingDao trainingDao;

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Training setNewTraining(String name, String type,String description, List<ExerciseBlock> exerciseBlockList,	Coach coach) {
		Training training = new Training();
		training.setName(name);
		training.setType(type);
		training.setDescription(description);
		training.setListExerciseBlock(exerciseBlockList);
		training.setCoach(coach);
		
		trainingDao.persistTraining(training);
		
		return trainingDao.getTrainingById(training.getIdTraining());
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateTraining(Training training) {
		trainingDao.persistTraining(training);
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Training findExerciseTraining(Long idTraining) {
		return trainingDao.getTrainingById(idTraining);
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void removeTraining(Training training) {
		trainingDao.deleteTraining(training);
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<Training> findAllTraining(Coach coach) {
		return trainingDao.getAllTrainingByCoach(coach);
	}

}
