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
	private static UserService userService;
	
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
	
	String athleteName4 = "Usain";
	String athleteUserName4 = "Usain";
	String athleteSurname4 =  "Bolt";
	String athleteEmail4 ="asdf3@asdf.com";
	String athleteDni4 = "77777777P";
	
	
	String teamName1 = "Equipo";
	String teamName2 = "Team";
	String teamName3 = "Grupo";

	private Long idCoach1;
	private Long idCoach2;
	private Long idCoach3;
	private Long idTeam1;
	private Long idTeam2;
	private Long idTeam3;
	private Long idAthlete1;
	private Long idAthlete2;
	private Long idAthlete3;
	private Long idAthlete4;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] SPRING_CONFIGURATION_FILES = {"test-service-application-service.xml", "test-service-application-dao.xml"};
		applicationContext = new ClassPathXmlApplicationContext(SPRING_CONFIGURATION_FILES);	
		userService = applicationContext.getBean(UserService.class);
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
		initUserServiceCoaches();
		
		//Get coaches
		Coach coach1 = userService.findCoach(idCoach1);
		Coach coach2 = userService.findCoach(idCoach2);
		Coach coach3 = userService.findCoach(idCoach3);		
		
		//Get all coaches
		List<Coach> listCoaches = userService.findAllCoaches();
		
		assertTrue(listCoaches.contains(coach1));
		assertTrue(listCoaches.contains(coach2));
		assertTrue(listCoaches.contains(coach3));
		assertEquals(3, listCoaches.size());
		
		finishUserServiceCoaches();	
	
	}
	
	@Test
	public void testGetCoachByUserName(){
		initUserServiceCoaches();
		
		//Get coaches
		Coach coach1 = userService.findCoach(idCoach1);
		Coach coach3 = userService.findCoach(idCoach3);
		
		
		//Get coach by name userName
		Coach coachByUserName = userService.findCoachesByUserName(coachUserName1);		
		assertEquals(coach1,coachByUserName);
		
		//Get coach by name userName
		coachByUserName = userService.findCoachesByUserName(coachUserName3);		
		assertEquals(coach3,coachByUserName);
		
		//Get coach by name userName
		coachByUserName = userService.findCoachesByUserName("null");		
		assertNull(coachByUserName);
		
		finishUserServiceCoaches();
	}
	
	@Test
	public void testGetCoachByDni(){
		initUserServiceCoaches();
		
		//Get coaches
		Coach coach1 = userService.findCoach(idCoach1);
		Coach coach3 = userService.findCoach(idCoach3);
		
		
		//Get coach by name userName
		Coach coachByUserName = userService.findCoachByDni(coachDni1);		
		assertEquals(coach1,coachByUserName);
		
		//Get coach by name userName
		coachByUserName = userService.findCoachByDni(coachDni3);		
		assertEquals(coach3,coachByUserName);
		
		//Get coach by name userName
		coachByUserName = userService.findCoachByDni(athleteDni4);		
		assertNull(coachByUserName);
		
		finishUserServiceCoaches();
	}
	
	private void initUserServiceCoaches(){
		initCoaches();
	}
	
	private void finishUserServiceCoaches(){
		finishCoaches();
	}
	
	/**********************************************************************************************************************/
	/********************************************        TEAM         *****************************************************/
	/**********************************************************************************************************************/
	
	
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
		initUserServiceTeams();
		
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
		
		finishUSerServiceTeams();	
	}
	
	@Test
	public void testGetTeamsByCoach(){
		initUserServiceTeams();
	
		//Get coach 
		Coach coach1 = userService.findCoach(idCoach1);
		Coach coach2 = userService.findCoach(idCoach2);
 
		
		//Get teams 
		Team team1 = userService.findTeam(idTeam1);
		Team team2 = userService.findTeam(idTeam2);
		Team team3 = userService.findTeam(idTeam3);
				
		
		
		//Get teams
		List<Team> listTeams1 = userService.findTeamsByCoach(coach1);
		List<Team> listTeams2 = userService.findTeamsByCoach(coach2);
		
		assertTrue(listTeams1.contains(team1));
		assertTrue(listTeams2.contains(team2));
		assertTrue(listTeams2.contains(team3));
		assertEquals(1, listTeams1.size());
		assertEquals(2, listTeams2.size());
		
		finishUSerServiceTeams();
	
	}
	
	private void initUserServiceTeams(){
		initCoaches();
		initTeams();
	}
	
	private void finishUSerServiceTeams(){
		//finishTeams();
		finishCoaches();
	}
	
	/**********************************************************************************************************************/
	/********************************************       ATHLETE       *****************************************************/
	/**********************************************************************************************************************/
	
	
	
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
		initUserServiceAthletes();
		
		//Get athletes 
		Athlete athlete = userService.findAthlete(idAthlete1);
		Athlete athlete2 = userService.findAthlete(idAthlete2);
		Athlete athlete3 = userService.findAthlete(idAthlete3);		
		
		
		//Get all athletes
		List<Athlete> listAthletes = userService.findAllAthletes();
		
		assertTrue(listAthletes.contains(athlete));
		assertTrue(listAthletes.contains(athlete2));
		assertTrue(listAthletes.contains(athlete3));
		assertEquals(4, listAthletes.size());
		
		initUserServiceAthletes();	
	}
	
	@Test
	public void testGetAthletesFromTeam(){
		initUserServiceAthletes();
		
		//Get athletes 
		Athlete athlete = userService.findAthlete(idAthlete1);
		Athlete athlete2 = userService.findAthlete(idAthlete2);
		Athlete athlete3 = userService.findAthlete(idAthlete3);
		Athlete athlete4 = userService.findAthlete(idAthlete4);
		
		//Get teams 
		Team team1 = userService.findTeam(idTeam1);
		Team team2 = userService.findTeam(idTeam2);
		Team team3 = userService.findTeam(idTeam3);
						
		
		//Get athletes from Team
		List<Athlete> listAthletesTeam1 = userService.findAthletesFromTeam(team1);
		List<Athlete> listAthletesTeam2 = userService.findAthletesFromTeam(team2);
		List<Athlete> listAthletesTeam3 = userService.findAthletesFromTeam(team3);
		
		assertTrue(listAthletesTeam1.contains(athlete));
		assertTrue(listAthletesTeam2.contains(athlete2));
		assertTrue(listAthletesTeam2.contains(athlete3));
		assertTrue(listAthletesTeam3.contains(athlete4));
		assertEquals(1, listAthletesTeam1.size());
		assertEquals(2, listAthletesTeam2.size());
		assertEquals(1, listAthletesTeam3.size());
		
		initUserServiceAthletes();	
	}
	
	
	@Test
	public void testGetAthletesFromCoach(){
		initUserServiceAthletes();
		
		//Get athletes 
		Athlete athlete = userService.findAthlete(idAthlete1);
		Athlete athlete2 = userService.findAthlete(idAthlete2);
		Athlete athlete3 = userService.findAthlete(idAthlete3);
		Athlete athlete4 = userService.findAthlete(idAthlete4);
		
		//Get coaches
		Coach coach1 = userService.findCoach(idCoach1);
		Coach coach2 = userService.findCoach(idCoach2);
		Coach coach3 = userService.findCoach(idCoach3);
						
		
		//Get athletes from Team
		List<Athlete> listAthletesCoach1 = userService.findAthletesFromCoach(coach1);
		List<Athlete> listAthletesCoach2 = userService.findAthletesFromCoach(coach2);
		List<Athlete> listAthletesCoach3 = userService.findAthletesFromCoach(coach3);
		
		assertTrue(listAthletesCoach1.contains(athlete));
		assertTrue(listAthletesCoach2.contains(athlete2));
		assertTrue(listAthletesCoach2.contains(athlete3));
		assertTrue(listAthletesCoach2.contains(athlete4));
		assertEquals(1, listAthletesCoach1.size());
		assertEquals(3, listAthletesCoach2.size());
		assertEquals(0, listAthletesCoach3.size());
		
		initUserServiceAthletes();	
	}
	
	
	@Test
	public void testGetAthletesByNameSurname(){
		initUserServiceAthletes();
		
		//Get athletes 
		Athlete athlete = userService.findAthlete(idAthlete1);
		Athlete athlete3 = userService.findAthlete(idAthlete3);				
		
		
		//Get athlete by userName
		Athlete athleteByUserName = userService.findAthletesByUserName(athleteUserName1);		
		assertEquals(athlete, athleteByUserName);
		
		//Get athlete by userName
		athleteByUserName = userService.findAthletesByUserName(athleteUserName3);		
		assertEquals(athlete3, athleteByUserName);		
		
		
		//Get athlete by userName
		athleteByUserName = userService.findAthletesByUserName("NULL");
	
		assertNull(athleteByUserName);
		
		finishUserServiceAthletes();
	}
	
	
	@Test
	public void testGetAthletesByDni(){
		initUserServiceAthletes();
		
		//Get athletes 
		Athlete athlete = userService.findAthlete(idAthlete1);
		Athlete athlete3 = userService.findAthlete(idAthlete3);				
		
		
		//Get athlete by userName
		Athlete athleteByUserName = userService.findAthleteByDni(athleteDni1);		
		assertEquals(athlete, athleteByUserName);
		
		//Get athlete by userName
		athleteByUserName = userService.findAthleteByDni(athleteDni3);		
		assertEquals(athlete3, athleteByUserName);		
		
		
		//Get athlete by userName
		athleteByUserName = userService.findAthleteByDni(coachDni1);	
	
		assertNull(athleteByUserName);
		
		finishUserServiceAthletes();
	}
	
	private void initUserServiceAthletes(){		
		initCoaches();
		initTeams();
		initAthletes();
	}
	

	private void finishUserServiceAthletes(){		
		//finishAthletes();
		//finishTeams();
		finishCoaches();
	}
	
	private void initCoaches(){
		//Create coaches
		idCoach1 = userService.setNewCoach(coachName1, coachUserName1,coachSurname1, coachDni1,coachEmail1,null,null,null,null,null);	
		idCoach2 = userService.setNewCoach(coachName2, coachUserName2,coachSurname2, coachDni2, coachEmail2,null,null,null,null,null);
		idCoach3 = userService.setNewCoach(coachName3, coachUserName3,coachSurname3, coachDni3, coachEmail3,null,null,null,null,null);
	}
	
	private void initTeams(){
		//Get coaches
		Coach coach1 = userService.findCoach(idCoach1);
		Coach coach2 = userService.findCoach(idCoach2);		
		
		//Create teams
		idTeam1 = createTeam(teamName1, coach1); 	
		idTeam2 = createTeam(teamName2, coach2); 
		idTeam3 = createTeam(teamName3, coach2); 
				
	}
	
	private void initAthletes(){
		//Get teams 
		Team team1 = userService.findTeam(idTeam1);
		Team team2 = userService.findTeam(idTeam2);
		Team team3 = userService.findTeam(idTeam3);
		
		//Create athletes
		idAthlete1 = userService.setNewAthlete(athleteName1, athleteUserName1,athleteSurname1, athleteDni1, athleteEmail1,null,null,null,null,null, team1);	
		idAthlete2 = userService.setNewAthlete(athleteName2, athleteUserName2,athleteSurname2, athleteDni2, athleteEmail2,null,null,null,null,null, team2);
		idAthlete3 = userService.setNewAthlete(athleteName3, athleteUserName3,athleteSurname3, athleteDni3, athleteEmail3,null,null,null,null,null, team2);
		idAthlete4 = userService.setNewAthlete(athleteName4, athleteUserName4,athleteSurname4, athleteDni4, athleteEmail4,null,null,null,null,null, team3);
	}	
	
	
//	private void finishAthletes(){
//		//Get athletes 
//		Athlete athlete1 = userService.findAthlete(idAthlete1);
//		Athlete athlete2 = userService.findAthlete(idAthlete2);
//		Athlete athlete3 = userService.findAthlete(idAthlete3);
//		Athlete athlete4 = userService.findAthlete(idAthlete4);
//		
//		
//		//Remove athletes
//		userService.removeAthlete(athlete1);	
//		userService.removeAthlete(athlete2);	
//		userService.removeAthlete(athlete3);
//		userService.removeAthlete(athlete4);
//	}
//	
//	
//	private void finishTeams(){
//		//Get teams 
//		Team team1 = userService.findTeam(idTeam1);
//		Team team2 = userService.findTeam(idTeam2);
//		Team team3 = userService.findTeam(idTeam3);
//		
//		//Remove team
//		userService.removeTeam(team1);			
//		userService.removeTeam(team2);		
//		userService.removeTeam(team3);		
//	}
	
	private void finishCoaches(){
		//Get coaches
		Coach coach1 = userService.findCoach(idCoach1);
		Coach coach2 = userService.findCoach(idCoach2);
		Coach coach3 = userService.findCoach(idCoach3);
		
		
		//Remove coach
		userService.removeCoach(coach1);
		userService.removeCoach(coach2);
		userService.removeCoach(coach3);
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
		
	