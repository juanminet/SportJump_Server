package es.uma.sportjump.sjs.dao.daos.impl.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.uma.sportjump.sjs.dao.daos.CalendarEventDao;
import es.uma.sportjump.sjs.model.entities.CalendarEvent;

@Repository("calendarEventDao")
public class CalendarEventDaoJpaImpl implements CalendarEventDao {

	
	@PersistenceContext( type = PersistenceContextType.EXTENDED)
 	protected EntityManager em;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void persistEvent(CalendarEvent event) {
		em.persist(event);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CalendarEvent getEventgById(Long idEvent) {
		return em.find(CalendarEvent.class, idEvent);
	}


	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CalendarEvent> getEventsByGroup(Long idTeam) {
		Query query = em.createNamedQuery("findAllCalendarEventsByTeam")
				.setParameter("idTeam", idTeam);
		
		List<CalendarEvent> listCalendarEvent = query.getResultList();		
		
		return listCalendarEvent;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteEvent(CalendarEvent event) {
		em.remove(event);
	}

}
