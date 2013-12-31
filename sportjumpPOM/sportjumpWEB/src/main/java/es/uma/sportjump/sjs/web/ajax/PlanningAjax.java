package es.uma.sportjump.sjs.web.ajax;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import es.uma.sportjump.sjs.web.controller.beans.EventBean;
import es.uma.sportjump.sjs.web.controller.beans.EventCalendarJSON;


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
	public @ResponseBody List<EventCalendarJSON> getTrainingDay(@PathVariable("idGroup") Long idGroup){	
		
		Team team = userService.findTeam(idGroup);	
		
		
		List<CalendarEvent> calendarEventList = calendarService.findAllEventByTeam(team);
		
		List<EventCalendarJSON> eventList = fillEvents(calendarEventList);

		return eventList;
	}	
	
	
	private List<EventCalendarJSON> fillEvents(	List<CalendarEvent> calendarEventList) {

		List<EventCalendarJSON> result = new ArrayList<EventCalendarJSON>();
		for(CalendarEvent event : calendarEventList){
			EventCalendarJSON eventCalendarJSON = new EventCalendarJSON();
			eventCalendarJSON.setId(event.getIdEvent());
			
			
			SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
			
			
			eventCalendarJSON.setStart(sdf.format(event.getEventDate()));
			eventCalendarJSON.setTitle(event.getTraining().getName());
			
			result.add(eventCalendarJSON);
		}
		return result;
	}


	@RequestMapping(value ="/save" ,  method = RequestMethod.POST)	
	@ResponseStatus(HttpStatus.OK)
	public  void saveEvent(@ModelAttribute("eventBean") EventBean eventBean){					
	
		Date eventDate = eventBean.getDate();
		Training training = trainingService.findTraining(Long.valueOf(eventBean.getIdTraining()));
		Team team = userService.findTeam(Long.valueOf(eventBean.getIdGroup()));	
		
		
		calendarService.setNewEvent(eventDate, training, team, false);
	}
}
