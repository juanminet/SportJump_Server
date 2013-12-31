package es.uma.sportjump.sjs.service.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public CalendarEvent setNewEvent(Date eventDate, Training training, Team team,	boolean periodicEvent) {
	
		CalendarEvent event = new CalendarEvent();
		event.setTeam(team);
		event.setTraining(training);
		event.setEventDate(eventDate);
		
		calendarEventDao.persistEvent(event);
		
		return event;		
	}

	@Override
	public void updateEvent(CalendarEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CalendarEvent findEvent(Long idEvent) {
		return calendarEventDao.getEventgById(idEvent);
	}

	@Override
	public List<CalendarEvent> findAllEventByTeam(Team team) {
		return calendarEventDao.getEventsByGroup(team.getIdTeam());
	}

	@Override
	public void removeEvent(CalendarEvent event) {
		calendarEventDao.deleteEvent(event);
	}

}
