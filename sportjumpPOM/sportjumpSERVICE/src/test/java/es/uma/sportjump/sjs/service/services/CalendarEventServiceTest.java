package es.uma.sportjump.sjs.service.services;

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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.uma.sportjump.sjs.model.entities.CalendarEvent;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Team;
import es.uma.sportjump.sjs.model.entities.Training;
import es.uma.sportjump.sjs.service.services.test.util.CoachServiceTestUtil;
import es.uma.sportjump.sjs.service.services.test.util.TeamServiceTestUtil;
import es.uma.sportjump.sjs.service.services.test.util.TrainingServiceTestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service-application-service.xml", "classpath:test-service-application-dao.xml"})  
public class CalendarEventServiceTest {
	
	@Autowired
	private CalendarService calendarService;
	
	@Autowired
	private CoachServiceTestUtil coachTestUtil;
	
	@Autowired
	private TeamServiceTestUtil teamTestUtil;
	
	@Autowired
	private TrainingServiceTestUtil  trainingTestUtil;
	
	private Coach coach;
	
	private Team team;
	
	private  Training training1;
	private  Training training2;
	private  Training training3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		coach = coachTestUtil.createNewCoach();
		
		team = teamTestUtil.createTeam(1, coach);
		
		training1 = trainingTestUtil.createTraining(1, coach);
		training2 = trainingTestUtil.createTraining(2, coach);
		training3 = trainingTestUtil.createTraining(3, coach);
		
		
	}

	@After
	public void tearDown() throws Exception {
		
		trainingTestUtil.removeTraining(training1);
		trainingTestUtil.removeTraining(training2);
		trainingTestUtil.removeTraining(training3);
		
		teamTestUtil.removeTeam(team);
		
		coachTestUtil.removeCoach(coach);
	}
	
	@Test
	public void testCalendarEventCRUD(){
				
		//Create calendarEvent
		CalendarEvent calendarEvent =  createCalendarEvent(1,training1, team);
 
		assertNotNull(calendarEvent);
		assertNotNull(calendarEvent.getIdEvent());
		
		//Get calendarEvent
		CalendarEvent calendarEventAux = calendarService.findEvent(calendarEvent.getIdEvent());
		
		
		assertEquals(calendarEvent.getIdEvent(),calendarEventAux.getIdEvent());
		assertEquals(calendarEvent.getTeam().getIdTeam(), calendarEventAux.getTeam().getIdTeam());
		assertEquals(calendarEvent.getTraining().getIdTraining(),calendarEventAux.getTraining().getIdTraining());
		assertEquals(calendarEvent.getEventDate(),calendarEventAux.getEventDate());	
		
		//update calendarEvent
		Date  newDate = new Date();
		calendarEvent.setEventDate(newDate);
		calendarService.updateEvent(calendarEvent);
		
		
		//Get calendarEvent
		calendarEventAux = calendarService.findEvent(calendarEvent.getIdEvent());
		
		assertEquals(newDate,calendarEventAux.getEventDate());
		
		//Remove calendarEvent
		calendarService.removeEvent(calendarEventAux);
		
		//Get calendarEvent
		calendarEventAux = calendarService.findEvent(calendarEvent.getIdEvent());
		
		assertNull(calendarEventAux);
	}
	
	@Test
	public void testAllEventsByTeam(){
		
		
		//Get calendarEvent
		List<CalendarEvent> eventList = calendarService.findAllEventByTeam(team);
		int initSize = eventList.size();		
		
		
		//Create events
		CalendarEvent calendarEvent1 =  createCalendarEvent(1,training1, team);
		CalendarEvent calendarEvent2 =  createCalendarEvent(2,training2, team);
		CalendarEvent calendarEvent3 =  createCalendarEvent(3,training3, team);
		
		
 
		assertNotNull(calendarEvent1);
		assertNotNull(calendarEvent1.getIdEvent());
		assertNotNull(calendarEvent2);
		assertNotNull(calendarEvent2.getIdEvent());
		assertNotNull(calendarEvent3);
		assertNotNull(calendarEvent3.getIdEvent());
		
		//Get events
		eventList = calendarService.findAllEventByTeam(team);
		
		assertEquals(initSize + 3, eventList.size());
		
		//Remove calendarEvent
		calendarService.removeEvent(calendarEvent1);
		
		//Get events
		eventList = calendarService.findAllEventByTeam(team);
		
		assertEquals(initSize + 2, eventList.size());
		
	
		//Remove calendarEvent
		calendarService.removeEvent(calendarEvent2);
		calendarService.removeEvent(calendarEvent3);				
		
		
		//Get Training
		eventList = calendarService.findAllEventByTeam(team);
		
		assertEquals(initSize, eventList.size());
	}
	
	
	private CalendarEvent createCalendarEvent(int num,Training training, Team team) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(5000000*num);
		
		Date eventDate = calendar.getTime(); 
		
		return calendarService.setNewEvent(eventDate, training, team, false);
		
	}
	
		
}
