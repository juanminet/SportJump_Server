package es.uma.sportjump.sjs.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;

import es.uma.sportjump.sjs.dao.test.util.CalendarEventDaoTestUtil;
import es.uma.sportjump.sjs.dao.test.util.CoachDaoTestUtil;
import es.uma.sportjump.sjs.dao.test.util.TeamDaoTestUtil;
import es.uma.sportjump.sjs.dao.test.util.TrainingDaoTestUtil;
import es.uma.sportjump.sjs.model.entities.CalendarEvent;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Team;
import es.uma.sportjump.sjs.model.entities.Training;

public class CalendarEventDaoTest{
	
	
	@Autowired
	private CoachDaoTestUtil coachUtil;
	
	@Autowired
	private TeamDaoTestUtil teamUtil;
	
	@Autowired
	private TrainingDaoTestUtil trainingUtil;
	
	@Autowired
	private CalendarEventDaoTestUtil calendarEventDaoTestUtil;
	
	
	protected Coach coach;
	private Team team1;
	private Training training1;
	private Training training2;
	private Training training3;
		
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {	
		coach = coachUtil.createNewCoach();
		
		Long id1 = trainingUtil.createTraining("name1", "type1", "descripcion1", null, coach);
		Long id2 = trainingUtil.createTraining("name2", "type2", "descripcion2", null, coach);
		Long id3 = trainingUtil.createTraining("name3", "type3", "descripcion3", null, coach);
		
		training1 = trainingUtil.readTraining(id1);
		training2 = trainingUtil.readTraining(id2);
		training3 = trainingUtil.readTraining(id3); 
	
		Long idTeam = teamUtil.createTeam("team1", coach); 
		team1 = teamUtil.readTeam(idTeam);		
	}

	@After
	public void tearDown() throws Exception {
		teamUtil.deleteTeam(team1);
		
		trainingUtil.deleteTraining(training1);
		trainingUtil.deleteTraining(training2);
		trainingUtil.deleteTraining(training3);
		
		coachUtil.deleteCoach(coach);
	}
	


	public void testCalendarEventCRUD() {		
		// Definition calendarEvent
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(45000000);
		Date eventDate = calendar.getTime();

		// Create CalendarEvent
		Long idEvent = calendarEventDaoTestUtil.createCalendarEvent(eventDate, team1, training1);

		// Make assert
		assertNotNull(idEvent);

		// Read calendarEvent
		CalendarEvent calendarEvent = calendarEventDaoTestUtil.readCalendarEvent(idEvent);
		
		// Make assert
		calendarEventDaoTestUtil.makeAssertTraining(eventDate, team1, training1, calendarEvent);

		// Update training
		calendar.setTimeInMillis(8000000);
		Date newEventDate = calendar.getTime();
		calendarEventDaoTestUtil.updateTraining(idEvent, newEventDate);

		// Read calendarEvent
		calendarEvent = calendarEventDaoTestUtil.readCalendarEvent(idEvent);
				
		// Make assert
		calendarEventDaoTestUtil.makeAssertTraining(newEventDate, team1, training1, calendarEvent);

		// Delete calendarEvent
		calendarEventDaoTestUtil.deleteCaledarEvent(idEvent);

		// Read calendarEvent
		calendarEvent = calendarEventDaoTestUtil.readCalendarEvent(idEvent);
				
		// Make assert
		assertNull(calendarEvent);
	}
	
	
	public void testGetAllEventByTeam(){
		// Definition calendarEvent
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(45000000);
		Date eventDate = calendar.getTime();
		
		//Read List of calendarEvents
		List<CalendarEvent> calendarEventList =  calendarEventDaoTestUtil.getAllTrainingByTeam(team1);
		int initSize = calendarEventList.size();
				
		// Create CalendarEvent
		Long idEvent1 = calendarEventDaoTestUtil.createCalendarEvent(eventDate, team1, training1);
		calendar.setTimeInMillis(55000000);	
		Long idEvent2 = calendarEventDaoTestUtil.createCalendarEvent(calendar.getTime(), team1, training2);
		calendar.setTimeInMillis(65000000);
		Long idEvent3 = calendarEventDaoTestUtil.createCalendarEvent(calendar.getTime(), team1, training3);
		
			
		//Read List of calendarEvents
		calendarEventList =  calendarEventDaoTestUtil.getAllTrainingByTeam(team1);		
		
		//make asserts
		assertNotNull(idEvent1);
		assertNotNull(idEvent2);
		assertNotNull(idEvent3);		
					
		//asserts
		assertNotNull (calendarEventList);
		assertEquals(initSize + 3, calendarEventList.size());
		
		// Delete calendarEvent
		calendarEventDaoTestUtil.deleteCaledarEvent(idEvent1);
		
		//Read List exercises
		calendarEventList = calendarEventDaoTestUtil.getAllTrainingByTeam(team1);
		
		//asserts
		assertNotNull (calendarEventList);
		assertEquals(initSize + 2, calendarEventList.size());
		
		//Delete exerciseBlock
		calendarEventDaoTestUtil.deleteCaledarEvent(idEvent2);
		calendarEventDaoTestUtil.deleteCaledarEvent(idEvent3);
	}
	
	
}
