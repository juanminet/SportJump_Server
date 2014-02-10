package es.uma.sportjump.sjs.dao.daos.impl.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.uma.sportjump.sjs.dao.daos.CalendarEventDao;
import es.uma.sportjump.sjs.model.entities.CalendarEvent;
import es.uma.sportjump.sjs.model.entities.Team;

@Repository("calendarEventDao")
public class CalendarEventDaoJpaImpl implements CalendarEventDao {

	
	@PersistenceContext
 	protected EntityManager em;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void persistEvent(CalendarEvent event) {
		if (event.getIdEvent() == null){
			em.persist(event);
		}else{
			em.merge(event);
		}
	}

	
	@Transactional(propagation=Propagation.REQUIRED)
	public CalendarEvent getEventgById(Long idEvent) {
		return em.find(CalendarEvent.class, idEvent);
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public CalendarEvent getCompleteEventgById(Long id) {
		CalendarEvent res = null;
		Query query = em.createNamedQuery("findfetchCalendarEventById")
				.setParameter("idEvent", id);
		
		try{
			res = (CalendarEvent) query.getSingleResult();
		}catch (NoResultException noResultException) {
			res = null;
		}
		
		return res;
	}


	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public List<CalendarEvent> getEventsByGroup(Long idTeam) {
		Query query = em.createNamedQuery("findAllCalendarEventsByTeam")
				.setParameter("idTeam", idTeam);
		
		List<CalendarEvent> listCalendarEvent = query.getResultList();		
		
		return listCalendarEvent;
	}


	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteEvent(CalendarEvent event) {
		CalendarEvent eventPersistent = em.find(CalendarEvent.class, event.getIdEvent());
		em.remove(eventPersistent);
		em.flush();
	}


	@Transactional(propagation=Propagation.REQUIRED)
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

	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateEvent(CalendarEvent calendarEvent) {
		em.persist(calendarEvent);		
	}

	
}
