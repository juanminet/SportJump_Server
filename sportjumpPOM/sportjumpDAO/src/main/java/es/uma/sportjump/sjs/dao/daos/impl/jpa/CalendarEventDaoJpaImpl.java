package es.uma.sportjump.sjs.dao.daos.impl.jpa;

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
public class CalendarEventDaoJpaImpl implements CalendarEventDao {

	
	@PersistenceContext( type = PersistenceContextType.EXTENDED)
 	protected EntityManager em;
	
	public void persistEvent(CalendarEvent event) {
		em.persist(event);
	}

	public CalendarEvent getEventgById(Long idEvent) {
		return em.find(CalendarEvent.class, idEvent);
	}


	@SuppressWarnings("unchecked")
	public List<CalendarEvent> getEventsByGroup(Long idTeam) {
		Query query = em.createNamedQuery("findAllCalendarEventsByTeam")
				.setParameter("idTeam", idTeam);
		
		List<CalendarEvent> listCalendarEvent = query.getResultList();		
		
		return listCalendarEvent;
	}


	public void deleteEvent(CalendarEvent event) {
		em.remove(event);
		em.flush();
	}

	@Override
	
	public CalendarEvent getEventByDateAndTeam(Date date, Team team) {
		Query query = em.createNamedQuery("findCalendarEventByDateAndTeam")
				.setParameter("eventDate", date)
				.setParameter("idTeam", team.getIdTeam());
		
		CalendarEvent calendarEvent = null;
		
		try{
			calendarEvent = (CalendarEvent) query.getSingleResult();	
		}catch (NoResultException noResultException) {
			calendarEvent = null;
		}
		
		return calendarEvent;
	}

	@Override
	
	public void updateEvent(CalendarEvent calendarEvent) {
		em.persist(calendarEvent);		
	}

}
