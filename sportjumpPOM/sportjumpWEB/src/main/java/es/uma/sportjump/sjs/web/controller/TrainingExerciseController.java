package es.uma.sportjump.sjs.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Exercise;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;
import es.uma.sportjump.sjs.service.services.ExerciseService;
import es.uma.sportjump.sjs.web.controller.commands.ExerciseBlockCommand;


@Controller
@SessionAttributes("blockCommand")
@RequestMapping("/action/training")
public class TrainingExerciseController {
	
	
	protected static final String LIST_TRAINING_BLOCKS = "training_exercise";
	protected static final String NEW_TRAINING_BLOCK = "training_new_block";

	protected static final String LIST_TRAINING_BLOCKS_REDIRECT = "redirect:/action/training/exercise";	
	
	@Autowired
	private ExerciseService exerciseService;
	
	
	@RequestMapping( value="/exercise" , method = RequestMethod.GET)
	public String exercise(Model model,  HttpSession session) {		
		
		Coach coach = (Coach) session.getAttribute("loggedUser");			
			
		List<ExerciseBlock> exerciseBlockList  = exerciseService.findAllExerciseBlockByCoach(coach);
		model.addAttribute("exerciseBlockList", exerciseBlockList);
		
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
		
		ExerciseBlock exerciseBlock = exerciseService.findExerciseBlock(idBlock);
	
		ExerciseBlockCommand blockCommand = 	fillExerciseBlockCommand(exerciseBlock);
		
		model.addAttribute("blockCommand", blockCommand);
		return NEW_TRAINING_BLOCK;		
	}	
	
	private ExerciseBlockCommand fillExerciseBlockCommand(	ExerciseBlock exerciseBlock) {

		ExerciseBlockCommand blockCommand = new ExerciseBlockCommand();
		blockCommand.setId(exerciseBlock.getIdExerciseBlock());
		blockCommand.setName(exerciseBlock.getName());
		blockCommand.setType(exerciseBlock.getType());
		blockCommand.setDescription(exerciseBlock.getDescription());
		
	
		List<Exercise> exerciseList = exerciseBlock.getListExercises();
		
		if (exerciseList != null){
			List<String> exerciseNames = new ArrayList<String>();
			for(Exercise exercise :  exerciseList){
				exerciseNames.add(exercise.getName());
			}
			blockCommand.setExerciseList(exerciseNames);
		}
		
		return blockCommand;	
	}


	@RequestMapping( value="/exercise/block/save" , method = RequestMethod.POST)
	public String saveBlockExercise(@Valid ExerciseBlockCommand blockCommand, BindingResult errors, Model model, HttpSession session) {		

		if (errors.hasErrors()) {	
			return NEW_TRAINING_BLOCK;
		}	
		
		List<String> exerciseNameList = blockCommand.getExerciseList();
		List<Exercise> exerciseList = new ArrayList<Exercise>();
		
		if (exerciseNameList != null){
			int index = 0;
			for (String exerciseName : exerciseNameList){
				Exercise exercise = new Exercise();
				exercise.setName(exerciseName);
				exercise.setPos(index);
				
				exerciseList.add(exercise);
				index++;
			}
		}
		
		Coach  coach = (Coach) session.getAttribute("loggedUser");
		
		if (blockCommand.getId() == null){
			exerciseService.setNewExerciseBlock(blockCommand.getName(), blockCommand.getType(), blockCommand.getDescription(), exerciseList, coach);
		}else{
			ExerciseBlock exerciseBlock = exerciseService.findExerciseBlock(blockCommand.getId());
			
			exerciseBlock.setName(blockCommand.getName());
			exerciseBlock.setType(blockCommand.getType());
			exerciseBlock.setDescription(blockCommand.getDescription());
			exerciseBlock.setListExercises(exerciseList);
			
			exerciseService.updateExerciseBlock(exerciseBlock);
		}
		
		return LIST_TRAINING_BLOCKS_REDIRECT;		
	}
	
	
}
