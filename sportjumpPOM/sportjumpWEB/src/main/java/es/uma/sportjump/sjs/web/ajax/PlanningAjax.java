package es.uma.sportjump.sjs.web.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.uma.sportjump.sjs.service.services.ExerciseService;
import es.uma.sportjump.sjs.service.services.TrainingService;
import es.uma.sportjump.sjs.web.controller.beans.EventBean;


@Controller
@RequestMapping("/ajax/planning")
public class PlanningAjax<E> {
	
	@Autowired
	TrainingService trainingService;
	
	@Autowired
	ExerciseService exerciseService;

	@RequestMapping(value ="/group/{idGroup}" ,  method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String getTrainingDay(@PathVariable("idGroup") Long idTrainingDay){					
	
		// '[{"id":111,"title":"Event1","start":"2013-12-10","url":"http:\/\/yahoo.com\/"},{"id":222,"title":"Event2","start":"2013-12-20","end":"2013-12-22","url":"http:\/\/yahoo.com\/"}]'
		
		String json= "[{\"id\":111,\"title\":\"Event1\",\"start\":\"2013-12-10\"}]";
		
		return json;
	}	
	
	
	@RequestMapping(value ="/save" ,  method = RequestMethod.POST)	
	@ResponseStatus(HttpStatus.OK)
	public  void saveEvent(@ModelAttribute("eventBean") EventBean eventBean){					
	
		
		System.out.println("guardando en base de datos");
		
	
	}
}
