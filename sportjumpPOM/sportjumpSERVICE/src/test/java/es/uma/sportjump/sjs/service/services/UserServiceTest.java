package es.uma.sportjump.sjs.service.services;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.uma.sportjump.sjs.dao.daos.UserDao;
import es.uma.sportjump.sjs.model.entities.Athlete;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Team;

public class UserServiceTest {
	private static ApplicationContext applicationContext = null;	
	
	String coachName1 = "Jose";
	String coachSurname1 =  "Mourinho";
	
	String coachName2 = "Pep";
	String coachSurname2 = "Guardiola";
	
	String coachName3 = "Vicente";
	String coachSurname3 = "Del Bosque";
	
	String athleteName1 = "Sebastian";
	String athleteSurname1 = "Coe";
	
	String athleteName2 = "Fermin";
	String athleteSurname2 = "Cacho";
	
	String athleteName3 = "El";
	String athleteSurname3 = "Guerrouj";
	
	
	String teamName1 = "Equipo";
	String teamName2 = "Team";
	String teamName3 = "Grupo";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] SPRING_CONFIGURATION_FILES = {"test-service-application-service.xml", "test-service-application-dao.xml"};
		applicationContext = new ClassPathXmlApplicationContext(SPRING_CONFIGURATION_FILES);		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		applicationContext = null;
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testCoachCRUD(){
		//Get UserService
		UserService userService = applicationContext.getBean(UserService.class);
		
		//Create coach 1
		Long idCoach1 = userService.setNewCoach(coachName1, coachSurname1);
		
		assertEquals(new Long(0), idCoach1);
		
		//Get coach 1
		Coach coach = userService.findCoach(idCoach1);
		
		assertEquals(coachName1,coach.getName());
		assertEquals(coachSurname1, coach.getSurname());
		
		//Remove coach1
		userService.removeCoach(coach);
		
		//Get coach 1
		coach = userService.findCoach(idCoach1);
		
		assertNull(coach);
		
	}
	
	
	@Test
	public void testGetAllCoaches(){
		//Get UserService
		UserService userService = applicationContext.getBean(UserService.class);
		
		//Create coaches
		Long idCoach1 = userService.setNewCoach(coachName1, coachSurname1);	
		Long idCoach2 = userService.setNewCoach(coachName2, coachSurname2);
		Long idCoach3 = userService.setNewCoach(coachName3, coachSurname3);
		
		//Get coaches
		Coach coach = userService.findCoach(idCoach1);
		Coach coach2 = userService.findCoach(idCoach2);
		Coach coach3 = userService.findCoach(idCoach3);
				
		
		
		//Get all coaches
		List<Coach> listCoaches = userService.findAllCoaches();
		
		assertTrue(listCoaches.contains(coach));
		assertTrue(listCoaches.contains(coach2));
		assertTrue(listCoaches.contains(coach3));
		assertEquals(3, listCoaches.size());
		
		//Remove coaches
		userService.removeCoach(coach);	
		userService.removeCoach(coach2);	
		userService.removeCoach(coach3);	
	
	}
	
	@Test
	public void testGetAlByNameSurname(){
		//Get UserService
		UserService userService = applicationContext.getBean(UserService.class);
		
		//Create coaches
		Long idCoach1 = userService.setNewCoach(coachName1, coachSurname1);	
		Long idCoach2 = userService.setNewCoach(coachName2, coachSurname2);
		Long idCoach3 = userService.setNewCoach(coachName3, coachSurname3);
		
		//Get coaches
		Coach coach = userService.findCoach(idCoach1);
		Coach coach2 = userService.findCoach(idCoach2);
		Coach coach3 = userService.findCoach(idCoach3);
				
		
		
		//Get coach by name surname
		List<Coach> listCoaches = userService.findCoachesByNameSurname(coachName1, coachSurname1);
		
		assertTrue(listCoaches.contains(coach));
		assertFalse(listCoaches.contains(coach2));
		assertFalse(listCoaches.contains(coach3));
		assertEquals(1, listCoaches.size());
		
		//Get coach by name surname
		listCoaches = userService.findCoachesByNameSurname(coachName1, coachSurname2);
		
		assertEquals(0, listCoaches.size());
		
		//Remove coaches
		userService.removeCoach(coach);	
		userService.removeCoach(coach2);	
		userService.removeCoach(coach3);	
	
	}
	
	
	@Test
	public void testTeamCRUD(){
		//Get UserService
		UserService userService = applicationContext.getBean(UserService.class);
		
		
		//Create coach 
		Long idCoach = userService.setNewCoach(coachName1, coachSurname1);
		
		//Get coach 
		Coach coach = userService.findCoach(idCoach);
				
				
		
		//Create team 
		Long idTeam = userService.setNewTeam(teamName1, coach);
				
		assertEquals(new Long(0), idTeam);
		
		//Get team 
		Team team = userService.findTeam(idTeam);
		
		assertEquals(teamName1,team.getName());
			
		//Remove team
		userService.removeTeam(team);
		
		//Get team 
		team = userService.findTeam(idTeam);
		
		assertNull(team);
		
		//Remove coach
		userService.removeCoach(coach);
		
	}
	
	
	@Test
	public void testGetAllTeams(){
		//Get UserService
		UserService userService = applicationContext.getBean(UserService.class);
		
		//Create coach
		Long idCoach = userService.setNewCoach(coachName1, coachSurname1);
		//Get coach 
		Coach coach = userService.findCoach(idCoach);
		
		//Create teams
		Long idTeam1 = userService.setNewTeam(teamName1, coach);	
		Long idTeam2 = userService.setNewTeam(teamName2, coach);
		Long idTeam3 = userService.setNewTeam(teamName3, coach);
		
		//Get teams 
		Team team = userService.findTeam(idTeam1);
		Team team2 = userService.findTeam(idTeam2);
		Team team3 = userService.findTeam(idTeam3);
				
		
		
		//Get all teams
		List<Team> listTeams = userService.findAllTeams();
		
		assertTrue(listTeams.contains(team));
		assertTrue(listTeams.contains(team2));
		assertTrue(listTeams.contains(team3));
		assertEquals(3, listTeams.size());
		
		//Remove teams
		userService.removeTeam(team);	
		userService.removeTeam(team2);	
		userService.removeTeam(team3);
		
		//Remove coach
		userService.removeCoach(coach);
	
	}
	
	
	@Test
	public void testAthleteCRUD(){
		//Get UserService
		UserService userService = applicationContext.getBean(UserService.class);
		
		//Create coach 
		Long idCoach1 = userService.setNewCoach(coachName1, coachSurname1);
		
		//Get coach 
		Coach coach = userService.findCoach(idCoach1);
		
		//Create team 
		Long idTeam = userService.setNewTeam(teamName1, coach);
		
		//Get team 
		Team team = userService.findTeam(idTeam);
		
		//Create athlete 
		Long idAthlete1 = userService.setNewAthlete(athleteName1, athleteSurname1, team);
		
		assertEquals(new Long(0), idAthlete1);
		
		//Get athlete 
		Athlete athlete = userService.findAthlete(idAthlete1);
		
		assertEquals(athleteName1,athlete.getName());
		assertEquals(athleteSurname1, athlete.getSurname());
		
		//Remove athlete
		userService.removeAthlete(athlete);
		
		//Get athlete 
		athlete = userService.findAthlete(idAthlete1);
		
		assertNull(athlete);
		
		
		//Remove team
		userService.removeTeam(team);				
		
		//Remove coach
		userService.removeCoach(coach);
		
	}
	
	
	
	@Test
	public void testGetAllAthletes(){
		//Get UserService
		UserService userService = applicationContext.getBean(UserService.class);
		
		//Create coach
		Long idCoach = userService.setNewCoach(coachName1, coachSurname1);
		//Get coach 
		Coach coach = userService.findCoach(idCoach);
		//Create team 
		Long idTeam = userService.setNewTeam(teamName1, coach);		
		//Get team 
		Team team = userService.findTeam(idTeam);
		
		//Create athletes
		Long idAthlete1 = userService.setNewAthlete(athleteName1, athleteSurname1, team);	
		Long idAthlete2 = userService.setNewAthlete(athleteName2, athleteSurname2, team);
		Long idAthlete3 = userService.setNewAthlete(athleteName3, athleteSurname3, team);
		
		//Get athletes 
		Athlete athlete = userService.findAthlete(idAthlete1);
		Athlete athlete2 = userService.findAthlete(idAthlete2);
		Athlete athlete3 = userService.findAthlete(idAthlete3);
				
		
		
		//Get all athletes
		List<Athlete> listAthletes = userService.findAllAthletes();
		
		assertTrue(listAthletes.contains(athlete));
		assertTrue(listAthletes.contains(athlete2));
		assertTrue(listAthletes.contains(athlete3));
		assertEquals(3, listAthletes.size());
		
		//Remove athletes
		userService.removeAthlete(athlete);	
		userService.removeAthlete(athlete2);	
		userService.removeAthlete(athlete3);
		
		//Remove team
		userService.removeTeam(team);				
		
		//Remove coach
		userService.removeCoach(coach);

	
	}
	
	
	@Test
	public void testGetAthletesByNameSurname(){
		//Get UserService
		UserService userService = applicationContext.getBean(UserService.class);
		
		//Create coach
		Long idCoach = userService.setNewCoach(coachName1, coachSurname1);
		//Get coach 
		Coach coach = userService.findCoach(idCoach);
		//Create team 
		Long idTeam = userService.setNewTeam(teamName1, coach);		
		//Get team 
		Team team = userService.findTeam(idTeam);
		
		//Create athletes
		Long idAthlete1 = userService.setNewAthlete(athleteName1, athleteSurname1, team);	
		Long idAthlete2 = userService.setNewAthlete(athleteName2, athleteSurname2, team);
		Long idAthlete3 = userService.setNewAthlete(athleteName3, athleteSurname3, team);
		
		//Get athletes 
		Athlete athlete = userService.findAthlete(idAthlete1);
		Athlete athlete2 = userService.findAthlete(idAthlete2);
		Athlete athlete3 = userService.findAthlete(idAthlete3);
				
		
		
		//Get all athletes
		List<Athlete> listAthletes = userService.findAthletesByNameSurname(athleteName1, athleteSurname1);
		
		assertTrue(listAthletes.contains(athlete));
		assertFalse(listAthletes.contains(athlete2));
		assertFalse(listAthletes.contains(athlete3));
		assertEquals(1, listAthletes.size());
		
		//Get all athletes
		listAthletes = userService.findAthletesByNameSurname(athleteName2, athleteSurname1);
	
		assertEquals(0, listAthletes.size());
		
		//Remove athletes
		userService.removeAthlete(athlete);	
		userService.removeAthlete(athlete2);	
		userService.removeAthlete(athlete3);
		
		//Remove team
		userService.removeTeam(team);				
		
		//Remove coach
		userService.removeCoach(coach);

	
	}
	


	
	
	
	
	
}
		
	