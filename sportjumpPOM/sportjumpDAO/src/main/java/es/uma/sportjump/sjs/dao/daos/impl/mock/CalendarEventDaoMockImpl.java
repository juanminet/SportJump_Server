package es.uma.sportjump.sjs.dao.daos.impl.mock;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import es.uma.sportjump.sjs.dao.daos.CalendarEventDao;
import es.uma.sportjump.sjs.model.entities.CalendarEvent;
import es.uma.sportjump.sjs.model.entities.Team;

@Repository("calendarEventDao")
public class CalendarEventDaoMockImpl implements CalendarEventDao {

	@Override
	public void persistEvent(CalendarEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CalendarEvent getEventgById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CalendarEvent getEventByDateAndTeam(Date date, Team team) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CalendarEvent> getEventsByGroup(Long idTeam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEvent(CalendarEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEvent(CalendarEvent calendarEventToUpdate) {
		// TODO Auto-generated method stub
		
	}

	
}
