package es.uma.sportjump.sjs.service.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uma.sportjump.sjs.dao.daos.CalendarEventDao;
import es.uma.sportjump.sjs.model.entities.CalendarEvent;
import es.uma.sportjump.sjs.model.entities.Team;
import es.uma.sportjump.sjs.model.entities.Training;
import es.uma.sportjump.sjs.service.services.CalendarService;

@Service("calendarService")
public class CalendarServiceImpl implements CalendarService {
	
	@Autowired
	CalendarEventDao calendarEventDao;

	@Override
	@Transactional
	public CalendarEvent setNewEvent(Date eventDate, Training training, Team team,	boolean periodicEvent) {
	
		CalendarEvent event = new CalendarEvent();
		event.setTeam(team);
		event.setTraining(training);
		event.setEventDate(eventDate);
		
		calendarEventDao.persistEvent(event);
		
		return event;		
	}

	@Override
	@Transactional
	public void updateEvent(CalendarEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public CalendarEvent findEventLight(Long idEvent) {
		return calendarEventDao.getEventgById(idEvent);
	}
	
	@Override
	public CalendarEvent findEvent(Long idEvent) {
		return calendarEventDao.getCompleteEventgById(idEvent);
	}
	


	@Override
	@Transactional
	public List<CalendarEvent> findAllEventByTeam(Team team) {
		return calendarEventDao.getEventsByGroup(team.getIdTeam());
	}

	@Override
	@Transactional
	public void removeEvent(CalendarEvent event) {
		calendarEventDao.deleteEvent(event);
	}

	@Override
	@Transactional
	public boolean existEvent(Date dateEvent, Team team) {
		CalendarEvent calendarEvent = calendarEventDao.getEventByDateAndTeam(dateEvent, team);
		
		return (calendarEvent != null)? true:false;
	}

	@Override
	@Transactional
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
