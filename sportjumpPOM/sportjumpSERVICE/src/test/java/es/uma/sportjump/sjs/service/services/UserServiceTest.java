package es.uma.sportjump.sjs.service.services;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.uma.sportjump.sjs.model.entities.Athlete;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Team;

public class UserServiceTest {
	private static ApplicationContext applicationContext = null;	
	
	String coachName1 = "Jose";
	String coachUserName1 =  "Mourinho";
	String coachSurname1 =  "Fernandez";
	String coachEmail1 ="asdf@asdf.com";
	String coachDni1 = "11111111P";
	
	String coachName2 = "Pep";
	String coachUserName2 = "Guardiola";
	String coachSurname2 =  "Fernandez2";
	String coachEmail2 ="asdf2@asdf.com";
	String coachDni2 = "22222222P";
	
	String coachName3 = "Vicente";
	String coachUserName3 = "Del Bosque";
	String coachSurname3 =  "Fernandez3";
	String coachEmail3 ="asdf3@asdf.com";
	String coachDni3 = "33333333P";
	
	String athleteName1 = "Sebastian";
	String athleteUserName1 = "Coe";
	String athleteSurname1 =  "Fernandez";
	String athleteEmail1 ="asdf@asdf.com";
	String athleteDni1 = "44444444P";
	
	String athleteName2 = "Fermin";
	String athleteUserName2 = "Cacho";
	String athleteSurname2 =  "Fernandez2";
	String athleteEmail2 ="asdf2@asdf.com";
	String athleteDni2 = "55555555P";
	
	String athleteName3 = "El";
	String athleteUserName3 = "Guerrouj";
	String athleteSurname3 =  "Fernandez3";
	String athleteEmail3 ="asdf3@asdf.com";
	String athleteDni3 = "66666666P";
	
	
	
	
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
		UserService userService = (UserService) applicationContext.getBean("userService");
		
		//Create coach 1
		Long idCoach1 = userService.setNewCoach(coachName1, coachUserName1,coachSurname1, coachDni1,coachEmail1,null,null,null,null,null);
		
		assertEquals(new Long(0), idCoach1);
		
		//Get coach 1
		Coach coach = userService.findCoach(idCoach1);
		
		assertEquals(coachName1,coach.getName());
		assertEquals(coachUserName1, coach.getUserName());
		assertEquals(coachSurname1, coach.getSurname());
		assertEquals(coachEmail1, coach.getEmail());
		
		//update coach
		coach.setName(coachName2);
		userService.updateCoach(coach);
		
		//Get coach 1
		coach = userService.findCoach(idCoach1);
		
		assertEquals(coachName2,coach.getName());
		
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
		Long idCoach1 = userService.setNewCoach(coachName1, coachUserName1,coachSurname1, coachDni1,coachEmail1,null,null,null,null,null);	
		Long idCoach2 = userService.setNewCoach(coachName2, coachUserName2,coachSurname2, coachDni2, coachEmail2,null,null,null,null,null);
		Long idCoach3 = userService.setNewCoach(coachName3, coachUserName3,coachSurname3, coachDni3, coachEmail3,null,null,null,null,null);
		
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
	public void testGetAllByUserName(){
		//Get UserService
		UserService userService = applicationContext.getBean(UserService.class);
		
		//Create coaches
		Long idCoach1 = userService.setNewCoach(coachName1, coachUserName1,coachSurname1, coachDni1,coachEmail1,null,null,null,null,null);	
		Long idCoach2 = userService.setNewCoach(coachName2, coachUserName2,coachSurname2, coachDni2, coachEmail2,null,null,null,null,null);
		Long idCoach3 = userService.setNewCoach(coachName3, coachUserName3,coachSurname3, coachDni3, coachEmail3,null,null,null,null,null);
		
		//Get coaches
		Coach coach = userService.findCoach(idCoach1);
		Coach coach2 = userService.findCoach(idCoach2);
		Coach coach3 = userService.findCoach(idCoach3);
				
		
		
		//Get coach by name userName
		Coach coachByUserName = userService.findCoachesByUserName(coachUserName1);
		
		assertEquals(coach,coachByUserName);
		
		//Get coach by name userName
		coachByUserName = userService.findCoachesByUserName(coachName2);
		
		assertNull(coachByUserName);
		
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
		Long idCoach = userService.setNewCoach(coachName1, coachUserName1,coachSurname1, coachDni1,coachEmail1,null,null,null,null,null);	
		
		//Get coach 
		Coach coach = userService.findCoach(idCoach);
				
				
		
		//Create team 
		Long idTeam = createTeam(teamName1, coach); 
				
		assertEquals(new Long(0), idTeam);
		
		//Get team 
		Team team = userService.findTeam(idTeam);
		
		assertEquals(teamName1,team.getName());
		
		//update team
		team.setName(teamName2);
		userService.updateTeam(team);
		
		//Get team 
		team = userService.findTeam(idTeam);
		
		assertEquals(teamName2,team.getName());
			
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
		Long idCoach = userService.setNewCoach(coachName1, coachUserName1,coachSurname1, coachDni1,coachEmail1,null,null,null,null,null);	
	
		//Get coach 
		Coach coach = userService.findCoach(idCoach);
		
		//Create teams
		Long idTeam1 = createTeam(teamName1, coach); 	
		Long idTeam2 = createTeam(teamName2, coach); 
		Long idTeam3 = createTeam(teamName3, coach); 
		
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
	public void testGetTeamsByCoach(){
		//Get UserService
		UserService userService = applicationContext.getBean(UserService.class);
		
		//Create coach
		Long idCoach = userService.setNewCoach(coachName1, coachUserName1,coachSurname1, coachDni1,coachEmail1,null,null,null,null,null);	
		Long idCoach2 = userService.setNewCoach(coachName2, coachUserName2,coachSurname2, coachDni2,coachEmail2,null,null,null,null,null);	
	
		//Get coach 
		Coach coach1 = userService.findCoach(idCoach);
		Coach coach2 = userService.findCoach(idCoach2);
		
		//Create teams
		Long idTeam1 = createTeam(teamName1, coach1); 	
		Long idTeam2 = createTeam(teamName2, coach2); 
		Long idTeam3 = createTeam(teamName3, coach1); 
		
		//Get teams 
		Team team1 = userService.findTeam(idTeam1);
		Team team2 = userService.findTeam(idTeam2);
		Team team3 = userService.findTeam(idTeam3);
				
		
		
		//Get teams
		List<Team> listTeams1 = userService.findTeamsByCoach(coach1);
		List<Team> listTeams2 = userService.findTeamsByCoach(coach2);
		
		assertTrue(listTeams1.contains(team1));
		assertTrue(listTeams2.contains(team2));
		assertTrue(listTeams1.contains(team3));
		assertEquals(2, listTeams1.size());
		assertEquals(1, listTeams2.size());
		
		//Remove teams
		userService.removeTeam(team1);	
		userService.removeTeam(team2);	
		userService.removeTeam(team3);
		
		//Remove coach
		userService.removeCoach(coach1);
		userService.removeCoach(coach2);
	
	}
	
	
	@Test
	public void testAthleteCRUD(){
		//Get UserService
		UserService userService = applicationContext.getBean(UserService.class);
		
		//Create coach 
		Long idCoach1 = userService.setNewCoach(coachName1, coachUserName1,coachSurname1, coachDni1,coachEmail1,null,null,null,null,null);	
		
		
		//Get coach 
		Coach coach = userService.findCoach(idCoach1);
		
		//Create team 
		Long idTeam = createTeam(teamName1, coach);
		
		//Get team 
		Team team = userService.findTeam(idTeam);
		
		//Create athlete 
		Long idAthlete1 = userService.setNewAthlete(athleteName1, athleteUserName1,athleteSurname1,athleteDni1, athleteEmail1, null,null,null,null,null,team);
		
		assertEquals(new Long(0), idAthlete1);
		
		//Get athlete 
		Athlete athlete = userService.findAthlete(idAthlete1);
		
		assertEquals(athleteName1,athlete.getName());
		assertEquals(athleteUserName1, athlete.getUserName());
		
		//Update athlete
		athlete.setName(athleteName2);
		athlete.setUserName(athleteUserName2);
		userService.updateAthlete(athlete);
		
		//Get athlete 
		athlete = userService.findAthlete(idAthlete1);
		
		assertEquals(athleteName2,athlete.getName());
		assertEquals(athleteUserName2, athlete.getUserName());
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
		Long idCoach = userService.setNewCoach(coachName1, coachUserName1,coachSurname1, coachDni1,coachEmail1,null,null,null,null,null);	
		//Get coach 
		Coach coach = userService.findCoach(idCoach);
		//Create team 
		Long idTeam = createTeam(teamName1, coach);		
		//Get team 
		Team team = userService.findTeam(idTeam);
		
		//Create athletes
		Long idAthlete1 = userService.setNewAthlete(athleteName1, athleteUserName1,athleteSurname1, athleteDni1, athleteEmail1,null,null,null,null,null, team);	
		Long idAthlete2 = userService.setNewAthlete(athleteName2, athleteUserName2,athleteSurname2, athleteDni2, athleteEmail2,null,null,null,null,null, team);
		Long idAthlete3 = userService.setNewAthlete(athleteName3, athleteUserName3,athleteSurname3, athleteDni3, athleteEmail3,null,null,null,null,null, team);
		

		
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
		Long idCoach = userService.setNewCoach(coachName1, coachUserName1,coachSurname1, coachDni1,coachEmail1,null,null,null,null,null);	
		//Get coach 
		Coach coach = userService.findCoach(idCoach);
		//Create team 
		Long idTeam = createTeam(teamName1, coach);		
		//Get team 
		Team team = userService.findTeam(idTeam);
		
		//Create athletes
		Long idAthlete1 = userService.setNewAthlete(athleteName1, athleteUserName1,athleteSurname1, athleteDni1, athleteEmail1,null,null,null,null,null, team);	
		Long idAthlete2 = userService.setNewAthlete(athleteName2, athleteUserName2,athleteSurname2, athleteDni2, athleteEmail2,null,null,null,null,null, team);
		Long idAthlete3 = userService.setNewAthlete(athleteName3, athleteUserName3,athleteSurname3, athleteDni3, athleteEmail3,null,null,null,null,null, team);
		
		//Get athletes 
		Athlete athlete = userService.findAthlete(idAthlete1);
		Athlete athlete2 = userService.findAthlete(idAthlete2);
		Athlete athlete3 = userService.findAthlete(idAthlete3);
				
		
		
		//Get all athletes
		Athlete athleteByUserName = userService.findAthletesByUserName(athleteUserName1);
		
		assertEquals(athlete, athleteByUserName);
		
		//Get all athletes
		athleteByUserName = userService.findAthletesByUserName(athleteName2);
	
		assertNull(athleteByUserName);
		
		//Remove athletes
		userService.removeAthlete(athlete);	
		userService.removeAthlete(athlete2);	
		userService.removeAthlete(athlete3);
		
		//Remove team
		userService.removeTeam(team);				
		
		//Remove coach
		userService.removeCoach(coach);

	
	}
	


	
	private Long createTeam(String teanName, Coach coach) {
		//Get UserService
		UserService userService = applicationContext.getBean(UserService.class);
		
		//Nullable attributes
		String type = "sprinters";
		String description = "Group for sprinter";
		Date dateCreate = new Date();
		
		Long idTeam = userService.setNewTeam(teamName1,type, description, dateCreate, coach);		
		return idTeam;
	}
	
	
	
}
		
	