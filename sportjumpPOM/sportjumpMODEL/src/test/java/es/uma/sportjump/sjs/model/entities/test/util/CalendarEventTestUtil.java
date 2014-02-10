package es.uma.sportjump.sjs.model.entities.test.util;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import es.uma.sportjump.sjs.model.entities.CalendarEvent;
import es.uma.sportjump.sjs.model.entities.Team;
import es.uma.sportjump.sjs.model.entities.Training;

public class CalendarEventTestUtil {
	
	private static EntityManagerFactory entityManagerFactory;
	private static CalendarEventTestUtil calendarEventTestUtil;
	
	private CalendarEventTestUtil(EntityManagerFactory entityManagerFactoryNew){
		entityManagerFactory = entityManagerFactoryNew;
	}
	
	private static synchronized CalendarEventTestUtil getSynchronizedInstance( EntityManagerFactory entityManagerFactory){
		if (calendarEventTestUtil == null){
			calendarEventTestUtil = new CalendarEventTestUtil(entityManagerFactory);
		}
		
		return calendarEventTestUtil;
	}
	
	public static CalendarEventTestUtil getInstance(EntityManagerFactory entityManagerFactory){
		return getSynchronizedInstance(entityManagerFactory);
	}
	


	public Long createCalendarEvent(Date eventDate, Team team, Training training) {
		//Create calendarEvent
		CalendarEvent calendarEvent = new CalendarEvent();
		calendarEvent.setEventDate(eventDate);
		calendarEvent.setTeam(team);
		calendarEvent.setTraining(training);
		
		//Persist entity
		Long idEvent = persistEvent(calendarEvent);
		
		return idEvent;
	}	

	
	public Long persistEvent(CalendarEvent calendarEvent) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(calendarEvent);
		entityManager.getTransaction().commit();
		
		return Long.valueOf(calendarEvent.getIdEvent());		
	}

	public CalendarEvent readCalendarEvent(Long idEvent) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//read calendarEvent
		CalendarEvent blocalendarEventck = entityManager.find(CalendarEvent.class, idEvent) ;
		return blocalendarEventck;
	}

	public void makeAssertCalendarEvent(Date eventDate, Team team, Training training, CalendarEvent calendarEvent) {
		assertEquals(eventDate, calendarEvent.getEventDate());
		assertEquals(team.getIdTeam(), calendarEvent.getTeam().getIdTeam());
		assertEquals(training.getIdTraining(), calendarEvent.getTraining().getIdTraining());			
	}

	public void updateTraining(Long idEvent, Date newEventDate) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//read calendarEvent
		CalendarEvent calendarEvent = entityManager.find(CalendarEvent.class, idEvent);
		
		//update training
		calendarEvent.setEventDate(newEventDate);
		
		//persist
		entityManager.getTransaction().begin();
		entityManager.persist(calendarEvent);
		entityManager.getTransaction().commit();
	}
	
	

	public void deleteCaledarEvent(Long idEvent) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//remove training
		CalendarEvent calendarEvent = entityManager.find(CalendarEvent.class, idEvent);
		
		entityManager.getTransaction().begin();
		entityManager.remove(calendarEvent);
		entityManager.getTransaction().commit();		
	}


	

}
