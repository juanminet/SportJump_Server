package es.uma.sportjump.sjs.dao.test.util;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uma.sportjump.sjs.dao.daos.CalendarEventDao;
import es.uma.sportjump.sjs.model.entities.CalendarEvent;
import es.uma.sportjump.sjs.model.entities.Team;
import es.uma.sportjump.sjs.model.entities.Training;


@Component
public class CalendarEventDaoTestUtil {
	
	@Autowired
	CalendarEventDao calendarEventDao;

	public Long createCalendarEvent(Date eventDate, Team team, 	Training training) {
		
		CalendarEvent calendarEvent = new CalendarEvent();
		calendarEvent.setEventDate(eventDate);
		calendarEvent.setTeam(team);
		calendarEvent.setTraining(training);
		
		calendarEventDao.persistEvent(calendarEvent);
		
		return Long.valueOf(calendarEvent.getIdEvent());
	}

	public CalendarEvent readCalendarEvent(Long idEvent) {
		return calendarEventDao.getCompleteEventgById(idEvent);
	}

	public void makeAssertTraining(Date eventDate, Team team,	Training training, CalendarEvent calendarEvent) {
		assertEquals(eventDate, calendarEvent.getEventDate());
		assertEquals(team.getIdTeam(), calendarEvent.getTeam().getIdTeam());
		assertEquals(training.getIdTraining(), calendarEvent.getTraining().getIdTraining());	
	}

	public void updateTraining(Long idEvent, Date newEventDate) {		
		//read calendarEvent
		CalendarEvent calendarEvent = calendarEventDao.getCompleteEventgById(idEvent);
		
		//update calendarEvent
		calendarEvent.setEventDate(newEventDate);
		
		//persist calendarEvent
		calendarEventDao.persistEvent(calendarEvent);
	}

	public void deleteCaledarEvent(Long idEvent) {
		//read calendarEvent
		CalendarEvent calendarEvent = calendarEventDao.getCompleteEventgById(idEvent);
		
		//remove calendarEvent
		calendarEventDao.deleteEvent(calendarEvent);
	}

	public List<CalendarEvent> getAllTrainingByTeam(Team team1) {
		return calendarEventDao.getEventsByGroup(team1.getIdTeam());
	}
	

}
