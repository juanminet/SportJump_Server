package es.uma.sportjump.sjs.web.ajax;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;
import es.uma.sportjump.sjs.model.entities.Training;
import es.uma.sportjump.sjs.service.services.ExerciseService;
import es.uma.sportjump.sjs.service.services.TrainingService;
import es.uma.sportjump.sjs.web.beans.ExerciseBean;
import es.uma.sportjump.sjs.web.beans.TrainingBean;
import es.uma.sportjump.sjs.web.beans.mappings.TrainingBeanMapping;


@Controller
@RequestMapping("/ajax/training")
public class TrainigAjax<E> {
	
	@Autowired
	TrainingService trainingService;
	
	@Autowired
	ExerciseService exerciseService;

	@RequestMapping(value ="/day/{idTrainingDay}" ,  method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody TrainingBean getTrainingDay(@PathVariable("idTrainingDay") Long idTrainingDay){					
	
		Training training = trainingService.findTraining(idTrainingDay);	
		 
		if (training == null){
			throw new EmptyResultDataAccessException("Training element not found", 1);			
		}
		TrainingBean result = TrainingBeanMapping.fillTrainingBean(training);
		return result;
	}	
	


	@RequestMapping(value ="/exercise/{idExerciseBlock}" ,  method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ExerciseBean getExerciseBlock(@PathVariable("idExerciseBlock") Long idExerciseBlock){					
	
		ExerciseBlock exererciseBlock = exerciseService.findExerciseBlock(idExerciseBlock);
		if (exererciseBlock == null){
			throw new EmptyResultDataAccessException("Training element not found", 1);			
		}
		
		
		ExerciseBean result = TrainingBeanMapping.fillExerciseBlock(exererciseBlock);
		return result;
	}
	
	
	@RequestMapping(value ="/exercise/name/{nameExerciseBlock}" ,  method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ExerciseBean getExerciseBlockByName(@PathVariable("nameExerciseBlock") String nameExerciseBlock,  HttpSession session){	
		
		Coach  coach = (Coach) session.getAttribute("loggedUser");	
	
		ExerciseBlock exererciseBlock = exerciseService.findExerciseBlockByNameAndCoach(nameExerciseBlock, coach);
		if (exererciseBlock == null){
			throw new EmptyResultDataAccessException("Training element not found", 1);			
		}
		
		
		ExerciseBean result = TrainingBeanMapping.fillExerciseBlock(exererciseBlock);
		return result;
	}
	
	
	@RequestMapping(value ="/exercise/names/{namesExerciseBlock}" ,  method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody TrainingBean getTrainingDay(@PathVariable("namesExerciseBlock") String  names,  HttpSession session){					
	
		
		
		TrainingBean result = new TrainingBean();
		
		String[] nameArray = names.split("-");
		Coach  coach = (Coach) session.getAttribute("loggedUser");		
		List<ExerciseBean> listExerciseWebBean = new ArrayList<ExerciseBean>();
		
		for(String name : nameArray){
			ExerciseBlock block = exerciseService.findExerciseBlockByNameAndCoach(name, coach);
			
			listExerciseWebBean.add(TrainingBeanMapping.fillExerciseBlock(block));
		}
		
		result.setListBlock(listExerciseWebBean);				
	
		return result;
	}		

	

//	 
//	@ExceptionHandler(NullPointerException.class)
//	@ResponseBody
//	public String handleException1(NullPointerException ex)
//	{
//	    return ex.getMessage();
//	}
	
//	
//	@ExceptionHandler(TrainingNotFoundException.class)
//	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Element not Found")
//	public void notFound() {
//	}
	
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Element not Found")
	public void notFound() {
	}
	

	
}
