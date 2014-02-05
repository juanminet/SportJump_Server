package es.uma.sportjump.sjs.service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

	@Transactional
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

	@Transactional
	public void updateTraining(Training training) {
		trainingDao.persistTraining(training);
	}

	@Transactional
	public Training findTrainingLight(Long idTraining) {
		return trainingDao.getTrainingById(idTraining);
	}
	
	@Transactional
	public Training findTraining(Long idTraining) {
		return trainingDao.getCompleteTrainingById(idTraining);
	}


	@Transactional
	public void removeTraining(Training training) {
		trainingDao.deleteTraining(training);
	}

	@Transactional
	public List<Training> findAllTraining(Coach coach) {
		return trainingDao.getAllTrainingByCoach(coach);
	}

	@Override
	public Training findTrainingByNameAndCoach(String name, Coach coach) {
		return trainingDao.getTrainingByNameAndCoach(name, coach);
	}


}
