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

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Exercise;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;
import es.uma.sportjump.sjs.model.entities.Training;
import es.uma.sportjump.sjs.service.services.ExerciseService;
import es.uma.sportjump.sjs.service.services.TrainingService;
import es.uma.sportjump.sjs.web.controller.commands.ExerciseBlockCommand;
import es.uma.sportjump.sjs.web.controller.commands.TrainingDayCommand;


@Controller
@RequestMapping("/action/training")
public class TrainingWorkingDayController {
	@Autowired
	private TrainingService trainingService;

	@Autowired
	private ExerciseService exerciseService;
	
	
	private final String LIST_TRAINING_DAY = "training_day_list";
	private final String NEW_TRAINING_DAY = "training_day_new";
	
	protected static final String LIST_TRAINING_DAY_REDIRECT = "redirect:/action/training/day/list";	
	
	@RequestMapping( value="/day/list" ,method = RequestMethod.GET)
	public String listTrainingDay(Model model, HttpSession session) {	
		
		initListTrainingDay(model, session);
		return LIST_TRAINING_DAY;		
	}	
	
	@RequestMapping( value="/day/new" ,method = RequestMethod.GET)
	public String newTrainingDay(Model model, HttpSession session) {		
		//initListTrainingDay(model, session);
		
		initTrainingNewDay(model, session);
		return NEW_TRAINING_DAY;		
	}	
	
	
	private void initTrainingNewDay(Model model, HttpSession session) {
		Coach  coach = (Coach) session.getAttribute("loggedUser");			
		
		model.addAttribute("trainingDayCommand", new TrainingDayCommand());
		model.addAttribute("exerciseBlockList", exerciseService.findAllExerciseBlockByCoach(coach));
	}

	@RequestMapping( value="/day/{idDay}" ,method = RequestMethod.GET)
	public String getTrainingDay(@PathVariable("idDay") Long idDay, Model model, HttpSession session) {		
		initListTrainingDay(model, session);
		return LIST_TRAINING_DAY;		
	}	
	
	
	
	@RequestMapping( value="/day/save" , method = RequestMethod.POST)
	public String saveBlockExercise(@Valid TrainingDayCommand trainingCommand, BindingResult errors, Model model, HttpSession session) {		

		if (errors.hasErrors()) {	
			return NEW_TRAINING_DAY;
		}	
		
		List<String> trainingNameList = trainingCommand.getTrainingDayList();
		List<Training> trainingList = new ArrayList<Training>();
//		
//		if (trainingNameList != null){
//			int index = 0;
//			for (String exerciseName : trainingNameList){
//				Training exercise = new Training();
//				exercise.setName(exerciseName);
//				
//				trainingList.add(exercise);
//				index++;
//			}
//		}
//		
//		Coach  coach = (Coach) session.getAttribute("loggedUser");
//		
//		if (trainingCommand.getId() == null){
//			exerciseService.setNewExerciseBlock(trainingCommand.getName(), trainingCommand.getType(), trainingCommand.getDescription(), trainingList, coach);
//		}else{
//			ExerciseBlock exerciseBlock = exerciseService.findExerciseBlock(trainingCommand.getId());
//			
//			exerciseBlock.setName(trainingCommand.getName());
//			exerciseBlock.setType(trainingCommand.getType());
//			exerciseBlock.setDescription(trainingCommand.getDescription());
//			exerciseBlock.setListExercises(trainingList);
//			
//			exerciseService.updateExerciseBlock(exerciseBlock);
//		}
		
		return LIST_TRAINING_DAY_REDIRECT;		
	}
	
	
	

	private void initListTrainingDay(Model model, HttpSession session) {		
		Coach  coach = (Coach) session.getAttribute("loggedUser");	
		
		model.addAttribute("trainingDayList", trainingService.findAllTraining(coach));
	}

	
}
