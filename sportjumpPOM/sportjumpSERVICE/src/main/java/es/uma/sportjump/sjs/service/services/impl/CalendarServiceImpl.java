package es.uma.sportjump.sjs.service.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.uma.sportjump.sjs.dao.daos.CalendarEventDao;
import es.uma.sportjump.sjs.dao.daos.TrainingDao;
import es.uma.sportjump.sjs.model.entities.CalendarEvent;
import es.uma.sportjump.sjs.model.entities.Team;
import es.uma.sportjump.sjs.model.entities.Training;
import es.uma.sportjump.sjs.service.services.CalendarService;

@Service("calendarService")
public class CalendarServiceImpl implements CalendarService {
	
	@Autowired
	CalendarEventDao calendarEventDao;
	
	@Autowired
	TrainingDao trainingDao;

	
	@Transactional
	public CalendarEvent setNewEvent(Date eventDate, Training training, Team team,	boolean periodicEvent) {
		CalendarEvent eventToRemove = calendarEventDao.getEventByDateAndTeam(eventDate, team);
		if (eventToRemove != null) {
			removeEvent(eventToRemove);
		}

	
		CalendarEvent event = new CalendarEvent();
		event.setTeam(team);
		event.setTraining(training);
		event.setEventDate(eventDate);
		
		calendarEventDao.persistEvent(event);
		
		return event;		
	}


	@Transactional(propagation=Propagation.REQUIRED)
	public void updateEvent(CalendarEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	@Transactional(propagation=Propagation.REQUIRED)
	public CalendarEvent findEventLight(Long idEvent) {
		return calendarEventDao.getEventgById(idEvent);
	}
	

	@Transactional(propagation=Propagation.REQUIRED)
	public CalendarEvent findEvent(Long idEvent) {
		return calendarEventDao.getCompleteEventgById(idEvent);
	}
	


	@Transactional(propagation=Propagation.REQUIRED)
	public List<CalendarEvent> findAllEventByTeam(Team team) {
		return calendarEventDao.getEventsByGroup(team.getIdTeam());
	}
	
	@Override
	public List<CalendarEvent> findAllEventCompleteByTeam(Team team) {
		
		List<CalendarEvent> res = calendarEventDao.getEventsByGroup(team.getIdTeam());
		
		for (CalendarEvent event : res){
			
			Long idTraining = event.getTraining().getIdTraining();
			event.setTraining(trainingDao.getCompleteTrainingById(idTraining));
			
		}
		return res;
	}


	
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeEvent(CalendarEvent event) {
		calendarEventDao.deleteEvent(event);
	}


	@Transactional(propagation=Propagation.REQUIRED)
	public boolean existEvent(Date dateEvent, Team team) {
		CalendarEvent calendarEvent = calendarEventDao.getEventByDateAndTeam(dateEvent, team);
		
		return (calendarEvent != null)? true:false;
	}


	@Transactional(propagation=Propagation.REQUIRED)
	public void modifyEventDate(Long eventId, Date date, Team team) {	
		
		CalendarEvent eventToRemove = calendarEventDao.getEventByDateAndTeam(date, team);
		if (eventToRemove != null) {
			removeEvent(eventToRemove);
			//calendarEventDao.deleteEvent(eventToRemove);
		}
		
		CalendarEvent calendarEventToModify = calendarEventDao.getEventgById(eventId);
		calendarEventToModify.setEventDate(date);
		//calendarEventDao.updateEvent(calendarEventToModify);
	}
}
