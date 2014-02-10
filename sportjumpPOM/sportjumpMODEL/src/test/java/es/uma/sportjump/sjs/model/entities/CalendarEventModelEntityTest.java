package es.uma.sportjump.sjs.model.entities;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uma.sportjump.sjs.model.entities.test.util.CalendarEventTestUtil;
import es.uma.sportjump.sjs.model.entities.test.util.CoachTestUtil;
import es.uma.sportjump.sjs.model.entities.test.util.TeamTestUtil;
import es.uma.sportjump.sjs.model.entities.test.util.TrainingTestUtil;

public class CalendarEventModelEntityTest {


	private static EntityManagerFactory entityManagerFactory = null;
	
	//Variables
	private Coach coach;
	private static CoachTestUtil coachUtil;
	private static TrainingTestUtil trainingTestUtil;
	private static TeamTestUtil teamTestUtil;
	private static CalendarEventTestUtil calendarEventTestUtil;
	
	private static Team team1;
	private static Training training1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("sportjumpJpaPU");
		
		coachUtil = CoachTestUtil.getInstance(entityManagerFactory);		
		trainingTestUtil = TrainingTestUtil.getInstance(entityManagerFactory);
		teamTestUtil = TeamTestUtil.getInstance(entityManagerFactory);
		calendarEventTestUtil = CalendarEventTestUtil.getInstance(entityManagerFactory);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		entityManagerFactory = null;
		
		trainingTestUtil = null;	
		coachUtil = null;
		teamTestUtil = null;
		
	}

	@Before
	public void setUp() throws Exception {
		coach = coachUtil.createCompleteCoach();
		
		training1 = trainingTestUtil.createCompleteTraining(coach, 1);
		team1 = teamTestUtil.createCompleteTeam(coach);
	}

	@After
	public void tearDown() throws Exception {
	
		//Delete training
		trainingTestUtil.deleteTraining(training1.getIdTraining());
		
		// Delete Coach
		coachUtil.deleteCoach(coach.getIdUser());
	}

	@Test
	public void testCRUD() {
		// Definition calendarEvent
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(45000000);
		Date eventDate = calendar.getTime();

		// Create CalendarEvent
		Long idEvent = calendarEventTestUtil.createCalendarEvent(eventDate,team1, training1);

		// Make assert
		assertNotNull(idEvent);

		// Read calendarEvent
		CalendarEvent calendarEvent = calendarEventTestUtil.readCalendarEvent(idEvent);
		
		// Make assert
		calendarEventTestUtil.makeAssertCalendarEvent(eventDate, team1, training1, calendarEvent);

		// Update training
		calendar.setTimeInMillis(8000000);
		Date newEventDate = calendar.getTime();
		calendarEventTestUtil.updateTraining(idEvent, newEventDate);

		// Read calendarEvent
		calendarEvent = calendarEventTestUtil.readCalendarEvent(idEvent);
				
		// Make assert
		calendarEventTestUtil.makeAssertCalendarEvent(newEventDate, team1, training1, calendarEvent);

		// Delete calendarEvent
		calendarEventTestUtil.deleteCaledarEvent(idEvent);

		// Read calendarEvent
		calendarEvent = calendarEventTestUtil.readCalendarEvent(idEvent);
				
		// Make assert
		assertNull(calendarEvent);
	}
}
