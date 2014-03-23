package es.uma.sportjump.sjs.web.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.uma.sportjump.sjs.model.entities.Athlete;
import es.uma.sportjump.sjs.model.entities.CalendarEvent;
import es.uma.sportjump.sjs.model.entities.Team;
import es.uma.sportjump.sjs.model.entities.Training;
import es.uma.sportjump.sjs.service.services.CalendarService;
import es.uma.sportjump.sjs.service.services.TrainingService;
import es.uma.sportjump.sjs.service.services.UserService;
import es.uma.sportjump.sjs.web.beans.EventCalendarCompleteBean;
import es.uma.sportjump.sjs.web.beans.EventCalendarBean;
import es.uma.sportjump.sjs.web.beans.TrainingBean;
import es.uma.sportjump.sjs.web.beans.mappings.CalendarEventBeanMapping;
import es.uma.sportjump.sjs.web.beans.mappings.TrainingBeanMapping;


@Controller
@RequestMapping("/rest/planning")
public class PlanningRest {
	
	@Autowired
	UserService userService;	
	
	@Autowired
	CalendarService calendarService;
	
	@Autowired
	TrainingService trainingService;

	Logger log = Logger.getLogger(PlanningRest.class);
	
	
	
	@RequestMapping(value="/athlete/{idAthlete}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public  @ResponseBody List<EventCalendarBean> getCalendarEventsFromAthleteId(@PathVariable("idAthlete") Long idAthlete){			
		
		Athlete athlete = userService.findAthlete(idAthlete);
		Team team = userService.findTeamLight(athlete.getTeam().getIdTeam());		
		
		List<CalendarEvent> calendarEventList = calendarService.findAllEventByTeam(team);
				
		log.info("GET in method [getTrainingFromAthleteByDate] with PathVariable [idAthlete :" + idAthlete + "]");		

		return CalendarEventBeanMapping.fillEvents(calendarEventList);
	}
	
	
	
	@RequestMapping(value="/athlete/username/{userName}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public  @ResponseBody List<EventCalendarCompleteBean> getCalendarEventsFromAthlete(@PathVariable("userName") String userName ){			
		
		Athlete athlete = userService.findAthletesByUserName(userName);
		Team team = userService.findTeamLight(athlete.getTeam().getIdTeam());		
		
		List<CalendarEvent> calendarEventList = calendarService.findAllEventCompleteByTeam(team);
				
		log.info("GET in method [getTrainingFromAthleteByDate] with PathVariable [userName :" + userName + "]");		

		return CalendarEventBeanMapping.fillCompleteEvents(calendarEventList);
	}
	
	
	@RequestMapping(value="/event/{idEvent}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public  @ResponseBody TrainingBean getTrainingFromAthleteByDate(@PathVariable("idEvent") Long idEvent ){			
		
		CalendarEvent calendarEvent = calendarService.findEvent(idEvent);
		
		if(calendarEvent == null){
			throw new EmptyResultDataAccessException("Event not found", 1);
		}
		
		Training training = trainingService.findTraining(calendarEvent.getTraining().getIdTraining());	
		 
		if (training == null){
			throw new EmptyResultDataAccessException("Training element not found", 1);			
		}	
	
				
		log.info("GET in method [getTrainingFromAthleteByDate] with PathVariable [idEvent :" + idEvent + "]");		

		return TrainingBeanMapping.fillTrainingBean(training);
	}	
}