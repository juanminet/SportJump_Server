package es.uma.sportjump.sjs.web.ajax;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.uma.sportjump.sjs.model.entities.CalendarEvent;
import es.uma.sportjump.sjs.model.entities.Team;
import es.uma.sportjump.sjs.model.entities.Training;
import es.uma.sportjump.sjs.service.services.CalendarService;
import es.uma.sportjump.sjs.service.services.ExerciseService;
import es.uma.sportjump.sjs.service.services.TrainingService;
import es.uma.sportjump.sjs.service.services.UserService;
import es.uma.sportjump.sjs.web.ajax.exceptions.AjaxException;
import es.uma.sportjump.sjs.web.beans.EventBean;
import es.uma.sportjump.sjs.web.beans.EventCalendarBean;
import es.uma.sportjump.sjs.web.beans.TrainingBean;
import es.uma.sportjump.sjs.web.beans.mappings.CalendarEventBeanMapping;
import es.uma.sportjump.sjs.web.beans.mappings.TrainingBeanMapping;


@Controller
@RequestMapping("/ajax/planning")
public class PlanningAjax<E> {
	
	@Autowired
	TrainingService trainingService;
	
	@Autowired
	ExerciseService exerciseService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CalendarService calendarService;

	@RequestMapping(value ="/group/{idGroup}" ,  method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<EventCalendarBean> getTrainingDay(@PathVariable("idGroup") Long idGroup){	
		
		Team team = userService.findTeam(idGroup);	
		
		
		List<CalendarEvent> calendarEventList = calendarService.findAllEventByTeam(team);
		
		List<EventCalendarBean> eventList = CalendarEventBeanMapping.fillEvents(calendarEventList);

		return eventList;
	}	
	
	



	@RequestMapping(value ="/save" ,  method = RequestMethod.POST)	
	@ResponseStatus(HttpStatus.OK)
	public  @ResponseBody String saveEvent(@ModelAttribute("eventBean") EventBean eventBean) throws AjaxException{	
		String result = null;
		
		Date eventDate = eventBean.getDate();
		Training training = trainingService.findTrainingLight(Long.valueOf(eventBean.getIdTraining()));
		Team team = userService.findTeam(Long.valueOf(eventBean.getIdGroup()));	
		
		try{
			CalendarEvent calendarEvent = calendarService.setNewEvent(eventDate, training, team, false);
			result = String.valueOf(calendarEvent.getIdEvent());
		}catch (Exception e) {
			throw new AjaxException();
		}	
		
		return result;
	}
	
	@RequestMapping(value ="/{idEvent}" ,  method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody TrainingBean getTraining(@PathVariable("idEvent") Long idEvent){					
	
		CalendarEvent calendarEvent = calendarService.findEvent(idEvent);
		if(calendarEvent == null){
			throw new EmptyResultDataAccessException("Event not found", 1);
		}
		Training training = trainingService.findTraining(calendarEvent.getTraining().getIdTraining());	
		 
		if (training == null){
			throw new EmptyResultDataAccessException("Training element not found", 1);			
		}
		TrainingBean result = TrainingBeanMapping.fillTrainingBean(training);
		return result;
	}	
	
	@RequestMapping(value="/{idEvent}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void removeEvent(@PathVariable("idEvent") Long idEvent) throws AjaxException{		
		CalendarEvent calendarEvent = calendarService.findEventLight(idEvent);
		if (calendarEvent != null){
			calendarService.removeEvent(calendarEvent);			
		}else{
			throw new AjaxException();
		}	
	}
	
	@RequestMapping(value ="/{idEvent}" ,  method = RequestMethod.PUT)	
	@ResponseStatus(HttpStatus.OK)
	public void modifyEvent(@PathVariable("idEvent") Long idEvent, @RequestBody EventBean eventBean) throws AjaxException{	
		CalendarEvent newCalendarEvent = new CalendarEvent();
		newCalendarEvent.setIdEvent(idEvent);
		newCalendarEvent.setEventDate(eventBean.getDate());
		
		calendarService.modifyEventDate(idEvent, eventBean.getDate(), userService.findTeam(Long.valueOf(eventBean.getIdGroup())));			
	}
	
	@ExceptionHandler(AjaxException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Error peticion")
	public void notFound() {
	}
	
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Error peticion")
	public void error() {
	}
}
