package es.uma.sportjump.sjs.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uma.sportjump.sjs.model.entities.ExerciseBlock;


@Controller
@RequestMapping("/action/training")
public class TrainingWorkingDayController {
	
	private final String LIST_TRAINING_DAY = "training_day";
	
	@RequestMapping( value="/day/list" ,method = RequestMethod.GET)
	public String listTrainingDay(Model model) {	
		
		initListTrainingDay(model);
		
		return LIST_TRAINING_DAY;		
	}	
	
	@RequestMapping( value="/day/new" ,method = RequestMethod.GET)
	public String newTrainingDay(Model model) {		
		initListTrainingDay(model);
		return LIST_TRAINING_DAY;		
	}	
	
	
	@RequestMapping( value="/day/{idDay}" ,method = RequestMethod.GET)
	public String getTrainingDay(@PathVariable("idDay") Long idDay, Model model) {		
		initListTrainingDay(model);
		return LIST_TRAINING_DAY;		
	}	
	
	
	

	private void initListTrainingDay(Model model) {
		List<ExerciseBlock> trainingDayList = new ArrayList<ExerciseBlock>();
		
		ExerciseBlock exerciseBlock = new ExerciseBlock();
		exerciseBlock.setIdExerciseBlock(new Long(1));
		exerciseBlock.setName("Entrenamiento de Fuerza");
		exerciseBlock.setType("Fuerza");
		
	
		
		
		ExerciseBlock exerciseBlock2 = new ExerciseBlock();
		exerciseBlock2.setIdExerciseBlock(new Long(2));
		exerciseBlock2.setName("Entrenamiento de Altura");
		exerciseBlock2.setType("Altura");
		
		
		
		ExerciseBlock exerciseBlock3 = new ExerciseBlock();
		exerciseBlock3.setIdExerciseBlock(new Long(3));
		exerciseBlock3.setName("Entrenamiento de Velocidad");
		exerciseBlock3.setType("Velocidad");
		
		trainingDayList.add(exerciseBlock);
		trainingDayList.add(exerciseBlock2);
		trainingDayList.add(exerciseBlock3);
		
		
		model.addAttribute("trainingDayList", trainingDayList);
	}

	
}
