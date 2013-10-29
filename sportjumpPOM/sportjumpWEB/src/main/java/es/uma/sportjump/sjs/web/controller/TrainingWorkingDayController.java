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
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;
import es.uma.sportjump.sjs.model.entities.Training;
import es.uma.sportjump.sjs.service.services.ExerciseService;
import es.uma.sportjump.sjs.service.services.TrainingService;
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
		Training training = trainingService.findExerciseTraining(idDay);
		
		TrainingDayCommand trainingCommand = fillTrainingCommand(training);
		model.addAttribute("trainingDayCommand",trainingCommand);
		return NEW_TRAINING_DAY;		
	}	
	
	
	
	private TrainingDayCommand fillTrainingCommand(Training training) {
		TrainingDayCommand trainingCommand = new TrainingDayCommand();
		
		trainingCommand.setId(training.getIdTraining());
		trainingCommand.setName(training.getName());
		trainingCommand.setType(training.getType());
		trainingCommand.setDescription(training.getDescription());
		
		
		List<ExerciseBlock> exerciseBlockList = training.getListExerciseBlock();
		
		if (exerciseBlockList != null){
			List<String> exerciseBlockNameList  = new ArrayList<String>();
			for (ExerciseBlock exerciseBlock : exerciseBlockList){
				exerciseBlockNameList.add(exerciseBlock.getName());
			}		
			
			trainingCommand.setTrainingDayList(exerciseBlockNameList);
		}
		
		return trainingCommand;
	}

	@RequestMapping( value="/day/save" , method = RequestMethod.POST)
	public String saveBlockExercise(@Valid TrainingDayCommand trainingCommand, BindingResult errors, Model model, HttpSession session) {		

		if (errors.hasErrors()) {	
			return NEW_TRAINING_DAY;
		}	
		
		List<String> trainingNameList = trainingCommand.getTrainingDayList();
		List<ExerciseBlock> exerciseBlockList = new ArrayList<ExerciseBlock>();
		
		Coach  coach = (Coach) session.getAttribute("loggedUser");	
		
		for (String name: trainingNameList){
			ExerciseBlock exerciseBlock = exerciseService.findExerciseBlockByNameAndCoach(name, coach);
			
			if (exerciseBlock != null){
				exerciseBlockList.add(exerciseBlock);
			}			
		}
		
		
		if (trainingCommand.getId() == null){
			trainingService.setNewTraining(trainingCommand.getName(), trainingCommand.getType(), trainingCommand.getDescription(), exerciseBlockList, coach);
		}else{
			Training training = trainingService.findExerciseTraining(trainingCommand.getId());
			
			training.setName(trainingCommand.getName());
			training.setType(trainingCommand.getType());
			training.setDescription(trainingCommand.getDescription());
			training.setListExerciseBlock(exerciseBlockList);
			
			trainingService.updateTraining(training);
		}
		
		return LIST_TRAINING_DAY_REDIRECT;		
	}
	
	
	

	private void initListTrainingDay(Model model, HttpSession session) {		
		Coach  coach = (Coach) session.getAttribute("loggedUser");	
		
		model.addAttribute("trainingDayList", trainingService.findAllTraining(coach));
	}

	
}
