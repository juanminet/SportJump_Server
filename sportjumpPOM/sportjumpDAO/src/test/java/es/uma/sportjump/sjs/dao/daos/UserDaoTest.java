package es.uma.sportjump.sjs.dao.daos;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

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

public class UserDaoTest {
	private static ApplicationContext applicationContext = null;
	private static ApplicationContext applicationContextJpa = null;
	private static ApplicationContext applicationContextMock = null;
	
	private String nameCoachAux = "Vicente";
	private String userNameCoachAux = "Del Bosque";	
	private String dniAux="44444444Y";
	
	private String nameTeamAux = "Auxiliar Team";



	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		applicationContextJpa = new ClassPathXmlApplicationContext("test-jpa-application-dao.xml");
		applicationContextMock = new ClassPathXmlApplicationContext("test-mock-application-dao.xml");
		


	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		applicationContext = null;
		applicationContextJpa = null;
		applicationContextMock = null;
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}
	
	
	/**********************************************************************************************************************/
	/********************************************        COACH        *****************************************************/
	/**********************************************************************************************************************/
	
	
	@Test
	public void testCoachJpaCRUD() {
		
		//select application context for JPA
		applicationContext = applicationContextJpa;
		
		//call testCoachCRUD
		testCoachCRUD();
		
		//remove application context
		applicationContext = null;
	
	}
	
	@Test
	public void testCoachMockCRUD() {
		
		//select application context for JPA
		applicationContext = applicationContextMock;
		
		//call testCoachCRUD
		testCoachCRUD();
		
		//remove application context
		applicationContext = null;
	
	}
	
	

	private void testCoachCRUD() {
		
		
		//Initialize variables
		String name = "Jose";
		String userName ="Mourinho";
		String dni = "98789878P";
	
		
		//Create coach
		Long idCoach = createCoach(name,userName,dni);
		
		//Make assert
		assertNotNull(idCoach);
		
		//Read coach
		Coach coach = readCoach(idCoach);
		
		//Make assert
		makeAssertCoach(name,userName,coach);
		
		
		//Delete coach
		deleteCoach(coach);
		
		//Read coach
		coach = readCoach(idCoach);
		
		//Make assert
		assertNull(coach);
		
	}
	
	
	
	@Test
	public void testGetCoachByUserNameJpa() {
		
		//select application context for JPA
		applicationContext = applicationContextJpa;
		
		//call testCoachCRUD
		testGetCoachByUserName();
		
		//remove application context
		applicationContext = null;
	
	}
	
	@Test
	public void testGetCoachByUserNameMock() {
		
		//select application context for JPA
		applicationContext = applicationContextMock;
		
		//call testCoachCRUD
		testGetCoachByUserName();
		
		//remove application context
		applicationContext = null;
	
	}

	private void testGetCoachByUserName() {
		
		//Initialize variables
		String name1 = "Sebastian";
		String userName1 ="Coe";
		String dni1="11111111P";
		String name2 = "Fermin";
		String userName2 ="Cacho";
		String dni2="22222222P";
		String name3 = "Carl";
		String userName3 ="Lewis";
		String dni3="33333333P";
		
		//Create coaches
		Long idCoach1 = createCoach(name1,userName1,dni1);
		Long idCoach2 = createCoach(name2,userName2,dni2);
		Long idCoach3 = createCoach(name3,userName3,dni3);
		
		//Make assert
		assertNotNull(idCoach1);
		assertNotNull(idCoach2);
		assertNotNull(idCoach3);
		
		//Read coach by name and surname
		Coach coach = readCoachByUserName(userName1);
		
		//Make assert
		makeAssertCoach(name1,userName1,coach);
		
		//Read coach by name and surname
		coach = readCoachByUserName(userName3);
		
		//Make assert
		makeAssertCoach(name3,userName3,coach);
				
				
		//Delete coaches
		deleteCoach(readCoach(idCoach1));
		deleteCoach(readCoach(idCoach2));
		deleteCoach(readCoach(idCoach3));
		
		//Read coach by name and surname
		coach = readCoachByUserName(userName1);	
		
		//Make assert
		assertNull(coach);
		
		
		
	}
	
	
	
	
	@Test
	public void testGetAllCoachesJpa() {
		
		//select application context for JPA
		applicationContext = applicationContextJpa;
		
		//call testCoach
		testGetAllCoaches();
		
		//remove application context
		applicationContext = null;
	
	}
	
	@Test
	public void testGetAllCoachesMock() {
		
		//select application context for JPA
		applicationContext = applicationContextMock;
		
		//call testCoach
		testGetAllCoaches();
		
		//remove application context
		applicationContext = null;
	
	}

	private void testGetAllCoaches() {
		
		//Initialize variables
		String name1 = "Sebastian";
		String userName1 ="Coe";
		String dni1="11111111P";
		String name2 = "Fermin";
		String userName2 ="Cacho";
		String dni2="22222222P";
		String name3 = "Carl";
		String userName3 ="Lewis";
		String dni3="33333333P";
		
		//Create coaches
		Long idCoach1 = createCoach(name1,userName1,dni1);
		Long idCoach2 = createCoach(name2,userName2,dni2);
		Long idCoach3 = createCoach(name3,userName3,dni3);
		
		//Make assert
		assertNotNull(idCoach1);
		assertNotNull(idCoach2);
		assertNotNull(idCoach3);
		
		//Read coach by name and surname
		List<Coach> listCoaches = readAllCoaches();
		
		//Make assert
		assertEquals(3,listCoaches.size());
		
		//Delete coaches
		deleteCoach(readCoach(idCoach2));
		
		//Read coach by name and surname
		listCoaches = readAllCoaches();
		
		//Make assert
		assertEquals(2,listCoaches.size());
				
		
		//Delete coaches
		deleteCoach(readCoach(idCoach3));
		
		//Read coach by name and surname
		listCoaches = readAllCoaches();
		
		//Make assert
		assertEquals(1,listCoaches.size());
		
		
		//Make assert
		makeAssertCoach(name1,userName1,listCoaches.get(0));
				
				
		//Delete coaches
		deleteCoach(readCoach(idCoach1));
		
		//Read coach by name and surname
		listCoaches = readAllCoaches();
		
				
		//Read coach by name and surname
		listCoaches = readAllCoaches();
		
		//Make assert
		assertEquals(0, listCoaches.size());
		
		
		
	}
	
	

	
	/**********************************************************************************************************************/
	/********************************************        Team        *****************************************************/
	/**********************************************************************************************************************/
	
	

	
	
	@Test
	public void testTeamJpaCRUD() {
		
		//select application context for JPA
		applicationContext = applicationContextJpa;
		
		//call testCoachCRUD
		testTeamCRUD();
		
		//remove application context
		applicationContext = null;
	
	}
	
	@Test
	public void testTeamMockCRUD() {
		
		//select application context for JPA
		applicationContext = applicationContextMock;
		
		//call testTeamCRUD
		testCoachCRUD();
		
		//remove application context
		applicationContext = null;
	
	}
	
	

	private void testTeamCRUD() {
		
		
		//Initialize variables
		String name = "Team Athletics";
				
		//Create Coach
		Long idCoach = createCoach(nameCoachAux,userNameCoachAux,dniAux);
		
		
		//Create team
		Long idTeam = createTeam(name,idCoach);
		
		
		//Make assert
		assertNotNull(idTeam);
		
		//Read team
		Team team = readTeam(idTeam);
		
		//Make assert
		makeAssertTeam(name,idCoach,team);
		
		
		//Delete team
		deleteTeam(team);
		
		//Read team
		team = readTeam(idTeam);
		
		//Make assert
		assertNull(team);
		
		
		//Delete coach
		deleteCoach(readCoach(idCoach));


		
	}
	
	

	@Test
	public void testGetAllTeamsJpa() {
		
		//select application context for JPA
		applicationContext = applicationContextJpa;
		
		//call testTeam
		testGetAllTeams();
		
		//remove application context
		applicationContext = null;
	
	}
	
	@Test
	public void testGetAllTeamsMock() {
		
		//select application context for JPA
		applicationContext = applicationContextMock;
		
		//call testTeam
		testGetAllTeams();
		
		//remove application context
		applicationContext = null;
	
	}

	private void testGetAllTeams() {
		
		//Create Coach
		Long idCoach = createCoach(nameCoachAux,userNameCoachAux,dniAux);
		
		//Initialize variables
		String name1 = "England";		
		String name2 = "France";		
		String name3 = "Spain";
		
		
		//Create coaches
		Long idTeam1 = createTeam(name1,idCoach);
		Long idTeam2 = createTeam(name2,idCoach);
		Long idTeam3 = createTeam(name3,idCoach);
		
		//Make assert
		assertNotNull(idTeam1);
		assertNotNull(idTeam2);
		assertNotNull(idTeam3);
		
		//Read coach by name and surname
		List<Team> listTeams = readAllTeams();
		
		//Make assert
		assertEquals(3,listTeams.size());
		
		//Delete coaches
		deleteTeam(readTeam(idTeam2));
		
		//Read coach by name and surname
		listTeams = readAllTeams();
		
		//Make assert
		assertEquals(2,listTeams.size());
				
		
		//Delete coaches
		deleteTeam(readTeam(idTeam3));
		
		//Read coach by name and surname
		listTeams = readAllTeams();
		
		//Make assert
		assertEquals(1,listTeams.size());
		
		
		//Make assert
		makeAssertTeam(name1,idCoach, listTeams.get(0));
				
				
		//Delete coaches
		deleteTeam(readTeam(idTeam1));
		
		//Read coach by name and surname
		listTeams = readAllTeams();
		
				
		//Read coach by name and surname
		listTeams = readAllTeams();
		
		//Make assert
		assertEquals(0, listTeams.size());
		
		//Delete coach
		deleteCoach(readCoach(idCoach));
		
	}
	
	
	
	

	/**********************************************************************************************************************/
	/********************************************       ATHLETE       *****************************************************/
	/**********************************************************************************************************************/
	

	@Test
	public void testAthleteJpaCRUD() {
		
		//select application context for JPA
		applicationContext = applicationContextJpa;
		
		//call testAthleteCRUD
		testAthleteCRUD();
		
		//remove application context
		applicationContext = null;
	
	}
	
	@Test
	public void testAthleteMockCRUD() {
		
		//select application context for JPA
		applicationContext = applicationContextMock;
		
		//call testAthleteCRUD
		testAthleteCRUD();
		
		//remove application context
		applicationContext = null;
	
	}
	
	

	private void testAthleteCRUD() {
		
		
		//Initialize variables
		String name = "Sebastian";
		String userName ="Coe";
		String dni = "66666666O";

		//Create Coach
		Long idCoach = createCoach(nameCoachAux,userNameCoachAux,dniAux);
		
		//Create team
		Long idTeam = createTeam(nameTeamAux,idCoach);
		
		//Create athlete
		Long idAthlete = createAthlete(name,userName, idTeam,dni);
		
		//Make assert
		assertNotNull(idAthlete);
		
		//Read athlete
		Athlete athlete = readAthlete(idAthlete);
		
		//Make assert
		makeAssertAthlete(name,userName,athlete);
		
		
		//Delete athlete
		deleteAthlete(athlete);
		
		//Read athlete
		athlete = readAthlete(idAthlete);
		
		//Make assert
		assertNull(athlete);
		
		//Delete team
		deleteTeam(readTeam(idTeam));
	
		
		//Delete coach
		deleteCoach(readCoach(idCoach));

		

		
	}
	
	
	
	@Test
	public void testGetAthleteByUserNameJpa() {
		
		//select application context for JPA
		applicationContext = applicationContextJpa;
		
		//call testAthleteCRUD
		testGetAthleteByUserName();
		
		//remove application context
		applicationContext = null;
	
	}
	
	@Test
	public void testGetAthleteByUserNameMock() {
		
		//select application context for JPA
		applicationContext = applicationContextMock;
		
		//call testAthleteCRUD
		testGetAthleteByUserName();
		
		//remove application context
		applicationContext = null;
	
	}

	private void testGetAthleteByUserName() {
		
		//Initialize variables
		String name1 = "Sebastian";
		String userName1 ="Coe";
		String dni1 = "55555555O";
		String name2 = "Usain";
		String userName2 ="Bolt";
		String dni2 = "66666666O";
		String name3 = "Carl";
		String userName3 ="Lewis";
		String dni3 = "77777777O";
		
		
		//Create Coach
		Long idCoach = createCoach(nameCoachAux,userNameCoachAux,dniAux);
		
		//Create team
		Long idTeam = createTeam(nameTeamAux,idCoach);
		
				
		//Create athletes
		Long idAthlete1 = createAthlete(name1,userName1,idTeam,dni1);
		Long idAthlete2 = createAthlete(name2,userName2,idTeam,dni2);
		Long idAthlete3 = createAthlete(name3,userName3,idTeam,dni3);
		
		//Make assert
		assertNotNull(idAthlete1);
		assertNotNull(idAthlete2);
		assertNotNull(idAthlete3);
		
		//Read athlete by name and surname
		Athlete athlete = readAthleteByUserName(userName1);
		
		//Make assert
		makeAssertAthlete(name1,userName1,athlete);
		
		//Read athlete by name and surname
		athlete = readAthleteByUserName(userName2);
		
		//Make assert
		makeAssertAthlete(name2,userName2,athlete);
				
				
		//Delete athlete
		deleteAthlete(readAthlete(idAthlete1));
		deleteAthlete(readAthlete(idAthlete2));
		deleteAthlete(readAthlete(idAthlete3));
		
		//Read athlete by name and surname
		athlete = readAthleteByUserName(userName2);			
		
		//Make assert
		assertNull(athlete);
		
		
		//Delete team
		deleteTeam(readTeam(idTeam));
	
		
		//Delete coach
		deleteCoach(readCoach(idCoach));

		
	}

	
	@Test
	public void testGetAllAthletesJpa() {
		
		//select application context for JPA
		applicationContext = applicationContextJpa;
		
		//call testAthlete
		testGetAllAthletes();
		
		//remove application context
		applicationContext = null;
	
	}
	
	@Test
	public void testGetAllAthletesMock() {
		
		//select application context for JPA
		applicationContext = applicationContextMock;
		
		//call testAthletes
		testGetAllAthletes();
		
		//remove application context
		applicationContext = null;
	
	}

	private void testGetAllAthletes() {
		
		//Create Coach
		Long idCoach = createCoach(nameCoachAux,userNameCoachAux,dniAux);
		
		//Create team
		Long idTeam = createTeam(nameTeamAux,idCoach);
		
		//Initialize variables
		String name1 = "Sebastian";
		String userName1 ="Coe";
		String dni1 = "55555555O";
		String name2 = "Usain";
		String userName2 ="Bolt";
		String dni2 = "66666666O";
		String name3 = "Carl";
		String userName3 ="Lewis";
		String dni3 = "77777777O";
		
		
		//Create athletes
		Long idAthlete1 = createAthlete(name1,userName1,idTeam,dni1);
		Long idAthlete2 = createAthlete(name2,userName2,idTeam,dni2);
		Long idAthlete3 = createAthlete(name3,userName3,idTeam,dni3);
		
		//Make assert
		assertNotNull(idAthlete1);
		assertNotNull(idAthlete2);
		assertNotNull(idAthlete3);
		
	
		
		//Read all athletes 
		List<Athlete> listAthlete = readAllAthletes();
		
		//Make assert
		assertEquals(3,listAthlete.size());
		
		
		
		//Delete athlete
		deleteAthlete(readAthlete(idAthlete2));
		
		//Read all athletes
		listAthlete = readAllAthletes();
		
		//Make assert
		assertEquals(2,listAthlete.size());
		
		
				
		
		//Delete athlete
		deleteAthlete(readAthlete(idAthlete3));
		
		//Read all athletes
		listAthlete = readAllAthletes();
		
		//Make assert
		assertEquals(1,listAthlete.size());
		
		
		//Make assert
		makeAssertAthlete(name1, userName1, listAthlete.get(0));
				
		//Delete athlete
		deleteAthlete(readAthlete(idAthlete1));
		
		//Read all athletes
		listAthlete = readAllAthletes();
				
		//Make assert
		assertEquals(0, listAthlete.size());
		
		//Delete team
		deleteTeam(readTeam(idTeam));
		
		//Delete coach
		deleteCoach(readCoach(idCoach));
		
	}
	
	
	//--------------------------------------------------------------------------------------------------------------------
	//                                                    PRIVATE METHODS
	//--------------------------------------------------------------------------------------------------------------------
		
		
	//------------------------------------------------   CREATE  --------------------------------------------------------
	
	
	private Long createCoach(String name, String userName, String dni) {
		//Get bean 
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Create coach
		Coach coach = new Coach();
		coach.setName(name);
		coach.setUserName(userName);
		coach.setDni(dni);
		
		//Persist coach		
		userDao.persistCoach(coach);
		

		//return idCoach
		return Long.valueOf(coach.getIdUser());	
	}
	
	
	private Long createTeam(String name, Long idCoach) {
		//Get bean 
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Create coach
		Coach coach = new Coach();
		coach.setIdUser(idCoach);
		
		//Create team
		String type = "Sprinters";
		String description = "Group for sprinters";
		Date dateCreate = new Date();
		
		Team team = new Team();
		team.setName(name);
		team.setType(type);
		team.setDescription(description);
		team.setDateCreate(dateCreate);
		team.setCoach(coach);
		
		System.out.println("SDDFASDFASDF");
		//Persist team		
		userDao.persistTeam(team);
		

		//return idTeam
		return Long.valueOf(team.getIdTeam());	
	}
	
	
	private Long createAthlete(String name, String userName, Long idTeam, String dni) {
		//Get bean 
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Get team
		Team team = userDao.getTeamById(idTeam);
		
		//Create athlete
		Athlete athlete = new Athlete();
		athlete.setName(name);
		athlete.setUserName(userName);
		athlete.setDni(dni);
		athlete.setTeam(team);
		
		//Persist athlete		
		userDao.persistAthlete(athlete);
		

		//return idAthlete
		return Long.valueOf(athlete.getIdUser());	
	}
	
		
	
	
	//------------------------------------------------   READ  --------------------------------------------------------
	


	 
	private Coach readCoach(Long idCoach){
		//Get bean
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Read Coach
		Coach coach = userDao.getCoachById(idCoach);
		
		return coach;
	}
	

	private Coach readCoachByUserName(String userName) {
		//Get bean
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Read Coach
		Coach coach = userDao.getCoachByUserName(userName);
		
		return coach;
	}
	
	
	private List<Coach> readAllCoaches() {
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Read Coach
		List<Coach> listCoaches = userDao.getAllCoaches();
		
		return listCoaches;
		
	}
	
		
	private Team readTeam(Long idTeam){
		//Get bean
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Read team
		Team team = userDao.getTeamById(idTeam);
		
		return team;
	}
	
	
	private List<Team> readAllTeams() {
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Read Coach		
		List<Team> listTeams = userDao.getAllTeams();
		
		return listTeams;		
	}
	
	
	private Athlete readAthlete(Long idAthlete) {
		//Get bean
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Read athlete
		Athlete athlete = userDao.getAthleteById(idAthlete);		
		
		return athlete;
	}
	
	private Athlete readAthleteByUserName(String userName) {
		//Get bean
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Read athlete
		Athlete  athlete = userDao.getAthleteByUserName(userName);
		
		return athlete;
	}
	
	private List<Athlete> readAllAthletes() {
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Read Coach		
		List<Athlete> listAthlete = userDao.getAllAthletes();
		
		return listAthlete;		
	}
	
	
	
	//------------------------------------------------   DELETE  --------------------------------------------------------
	


	private void deleteCoach(Coach coach){
		//Get bean
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Remove coach
		userDao.deleteCoach(Long.valueOf(coach.getIdUser()));
	}	
	

	private void deleteTeam(Team team){
		//Get bean
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Remove team
		userDao.deleteTeam(Long.valueOf(team.getIdTeam()));
	}	
	
	
	private void deleteAthlete(Athlete athlete) {
		//Get bean
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Remove athlete
		userDao.deleteAthlete(Long.valueOf(athlete.getIdUser()));		
	}
	
	
	
	
	
	//------------------------------------------------   ASSERT  --------------------------------------------------------


	private void makeAssertCoach(String name, String userName, Coach coach){
		assertEquals(coach.getName(), name);
		assertEquals(coach.getUserName(), userName);
	}
	
	

	private void makeAssertTeam(String name, Long idCoach, Team team){
		assertEquals(team.getName(), name);

	}	
	
	
	private void makeAssertAthlete(String name, String userName, Athlete athlete) {
		assertEquals(athlete.getName(),name);
		assertEquals(athlete.getUserName(),userName);
	}
	

	
}
