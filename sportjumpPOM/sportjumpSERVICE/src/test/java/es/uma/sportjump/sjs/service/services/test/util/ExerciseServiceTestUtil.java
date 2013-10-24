package es.uma.sportjump.sjs.service.services.test.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Exercise;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;
import es.uma.sportjump.sjs.service.services.ExerciseService;

@Component
public class ExerciseServiceTestUtil {
	
	
	@Autowired
	ExerciseService exerciseService;
	
	
	public ExerciseBlock createExerciseBlock(int num, Coach coach) {
		//Variables
		String name = "bloque fuerza" + num;
		String type = "Fuerza" + num;
		String description = "Haremos hincapié en la fuerza de hombros" + num;
		
		String exerciseName1 = "15 X 50kg hombros" + num;
		String exerciseName2 = "10 X 40kg dorsales" + num;
		
		Exercise exercise1 = new Exercise();
		exercise1.setName(exerciseName1);
		exercise1.setPos(1);
		
		Exercise exercise2 = new Exercise();
		exercise2.setName(exerciseName2);
		exercise2.setPos(2);
		
		List<Exercise> exerciseList = new ArrayList<Exercise>();
		exerciseList.add(exercise1);
		exerciseList.add(exercise2);
		
		
		
		return exerciseService.setNewExerciseBlock(name, type, description, exerciseList, coach);
	}
	
	
	public void removeExerciseBlock (ExerciseBlock exerciseBlock){
		exerciseService.removeExerciseBlock(exerciseBlock);
	}
	
}
