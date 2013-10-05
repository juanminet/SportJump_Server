package es.uma.sportjump.sjs.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uma.sportjump.sjs.web.controller.commands.AthleteCommand;
import es.uma.sportjump.sjs.web.controller.commands.ExerciseBlockCommand;


@Controller
@RequestMapping("/action/training")
public class TrainingExerciseController {
	
	protected static final String LIST_TRAINING_BLOCKS = "training_exercise";
	protected static final String NEW_TRAINING_BLOCK = "training_new_block";
	
	
	@RequestMapping( value="/exercise" , method = RequestMethod.GET)
	public String exercise(Model model) {			
		return LIST_TRAINING_BLOCKS;
		
	}	
	
	
	@RequestMapping( value="/exercise/block/new" , method = RequestMethod.GET)
	public String newBlockExercise(Model model) {
		
		ExerciseBlockCommand blockCommand = new ExerciseBlockCommand();
		
		model.addAttribute("blockCommand", blockCommand);
		return NEW_TRAINING_BLOCK;		
	}	
	
	
	@RequestMapping( value="/exercise/block/{idBlock}" , method = RequestMethod.GET)
	public String getBlockExercise(@PathVariable("idBlock") Long idBlock, Model model) {		
	ExerciseBlockCommand blockCommand = new ExerciseBlockCommand();
		
		model.addAttribute("blockCommand", blockCommand);
		return NEW_TRAINING_BLOCK;		
	}	
	
	@RequestMapping( value="/exercise/block/save" , method = RequestMethod.POST)
	public String saveBlockExercise(@Valid ExerciseBlockCommand exerciseBlockCommand, BindingResult errors, Model model, HttpSession session) {		

		return LIST_TRAINING_BLOCKS;		
	}
	
	
}
