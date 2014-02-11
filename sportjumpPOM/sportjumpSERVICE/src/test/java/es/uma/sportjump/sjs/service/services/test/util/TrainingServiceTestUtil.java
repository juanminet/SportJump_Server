package es.uma.sportjump.sjs.service.services.test.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Training;
import es.uma.sportjump.sjs.service.services.TrainingService;

@Component
public class TrainingServiceTestUtil {
	
	
	@Autowired
	TrainingService trainingService;
	
	
	public Training createTraining(int num, Coach coach) {
		//Variables
		String name = "Entrenamiento fuerza" + num;
		String type = "Fuerza" + num;
		String description = "Haremos hincapie en la fuerza de hombros" + num;
		
		
		return trainingService.setNewTraining(name, type, description, null, coach);
	}
	
	
	public void removeTraining(Training training){
		trainingService.removeTraining(training);
	}
	
}
