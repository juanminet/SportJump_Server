package es.uma.sportjump.sjs.service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.uma.sportjump.sjs.dao.daos.ExerciseBlockDao;
import es.uma.sportjump.sjs.dao.daos.TrainingDao;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;
import es.uma.sportjump.sjs.model.entities.Training;
import es.uma.sportjump.sjs.service.services.TrainingService;

@Service("trainingService")
public class TrainingServiceImpl implements TrainingService{
	
	@Autowired
	TrainingDao trainingDao;
	
	
	@Autowired
	ExerciseBlockDao exerciseBlockDao;


	@Transactional(propagation=Propagation.REQUIRED)
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


	@Transactional(propagation=Propagation.REQUIRED)
	public void updateTraining(Training training) {
		trainingDao.persistTraining(training);
	}


	@Transactional(propagation=Propagation.REQUIRED)
	public Training findTrainingLight(Long idTraining) {
		return trainingDao.getTrainingById(idTraining);
	}
	

	@Transactional(propagation=Propagation.REQUIRED)
	public Training findTraining(Long idTraining) {
		return trainingDao.getCompleteTrainingById(idTraining);
	}


	@Transactional(propagation=Propagation.REQUIRED)
	public void removeTraining(Training training) {
		trainingDao.deleteTraining(training);
	}


	@Transactional(propagation=Propagation.REQUIRED)
	public List<Training> findAllTraining(Coach coach) {
		return trainingDao.getAllTrainingByCoach(coach);
	}


	@Transactional(propagation=Propagation.REQUIRED)
	public Training findTrainingByNameAndCoach(String name, Coach coach) {
		return trainingDao.getTrainingByNameAndCoach(name, coach);
	}


}
