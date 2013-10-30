package es.uma.sportjump.sjs.dao.test.util;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uma.sportjump.sjs.dao.daos.TrainingDao;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;
import es.uma.sportjump.sjs.model.entities.Training;


@Component
public class TrainingDaoTestUtil {
	
	@Autowired
	TrainingDao trainingDao;
	
	public Long createTraining(String name, String type,	String description, List<ExerciseBlock> exerciseBlockList, Coach coach) {				
		//create object
		Training training = new Training();
		training.setName(name);
		training.setDescription(description);
		training.setType(type);
		training.setListExerciseBlock(exerciseBlockList);
		training.setCoach(coach);
		
		//Persist
		trainingDao.persistTraining(training);
		
		return Long.valueOf(training.getIdTraining());
	}
	
	public Training readTraining(Long idTraining) {				
		return trainingDao.getTrainingById(idTraining);
	}

	
	public void deleteTraining(Training training) {		
		//remove
		trainingDao.deleteTraining(training);
	}

	public void updateTraining(String name2, String type2, List<ExerciseBlock> exerciseBlockList, Training training) {
		//set attributes
		training.setName(name2);
		training.setType(type2);
		training.setListExerciseBlock(exerciseBlockList);
		
		trainingDao.persistTraining(training);		
	}

	public void makeAssertTraining(String name, String type, Training training) {
		assertEquals(name, training.getName());
		assertEquals(type, training.getType());
	}

	public Training getTrainingByNameAndCoach(String name, Coach coach) {
		return trainingDao.getTrainingByNameAndCoach(name, coach);
	}
}
