package es.uma.sportjump.sjs.web.ajax.util;

import java.util.ArrayList;
import java.util.List;

import es.uma.sportjump.sjs.model.entities.Exercise;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;
import es.uma.sportjump.sjs.model.entities.Training;
import es.uma.sportjump.sjs.web.controller.beans.ExerciseWebBean;
import es.uma.sportjump.sjs.web.controller.beans.TrainingWebBean;

public class ComonAjaxUtils {
	
	public static TrainingWebBean fillTrainingWebBean(Training training) {
		TrainingWebBean result = new TrainingWebBean();
		
		result.setName(training.getName());
		result.setType(training.getType());
		result.setDescription(training.getDescription());
	
		List<ExerciseBlock> listBlock = training.getListExerciseBlock();
		if (listBlock != null){
			List<ExerciseWebBean> listblock  = new ArrayList<ExerciseWebBean>();
			for(ExerciseBlock block : training.getListExerciseBlock()){
				listblock.add(fillExerciseBlock(block));
			}
			result.setListBlock(listblock);
		}
		
		return result;
	}

	public static ExerciseWebBean fillExerciseBlock(ExerciseBlock block) {
		ExerciseWebBean result = new ExerciseWebBean();
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
