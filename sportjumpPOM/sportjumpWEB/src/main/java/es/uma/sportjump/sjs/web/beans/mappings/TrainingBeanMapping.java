package es.uma.sportjump.sjs.web.beans.mappings;

import java.util.ArrayList;
import java.util.List;

import es.uma.sportjump.sjs.model.entities.Exercise;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;
import es.uma.sportjump.sjs.model.entities.Training;
import es.uma.sportjump.sjs.web.beans.ExerciseBean;
import es.uma.sportjump.sjs.web.beans.TrainingBean;

public class TrainingBeanMapping {
	public static TrainingBean fillTrainingBean(Training training) {
		TrainingBean result = new TrainingBean();
		
		result.setName(training.getName());
		result.setType(training.getType());
		result.setDescription(training.getDescription());
	
		List<ExerciseBlock> listBlock = training.getListExerciseBlock();
		if (listBlock != null){
			List<ExerciseBean> listblock  = new ArrayList<ExerciseBean>();
			for(ExerciseBlock block : training.getListExerciseBlock()){
				listblock.add(fillExerciseBlock(block));
			}
			result.setListBlock(listblock);
		}
		
		return result;
	}

	public static ExerciseBean fillExerciseBlock(ExerciseBlock block) {
		ExerciseBean result = new ExerciseBean();
		result.setName(block.getName());
		result.setType(block.getType());
		result.setDescription(block.getDescription());
		
		List<Exercise> listExercises = block.getListExercises();
		if (listExercises != null){
			List<String> listExerciseName = new ArrayList<String>();
			for(Exercise exercise : block.getListExercises()){
				listExerciseName.add(exercise.getName());
			}
			result.setListExercise(listExerciseName);
		}		
		return result;
	}
}
