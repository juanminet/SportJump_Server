package es.uma.sportjump.sjs.dao.test;


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

import es.uma.sportjump.sjs.dao.daos.UserDao;
import es.uma.sportjump.sjs.model.entities.Athlete;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Team;

public class UserDaoTest {
	private static ApplicationContext applicationContext = null;
	private static ApplicationContext applicationContextJpa = null;
	private static ApplicationContext applicationContextMock = null;
	
	private String nameCoachAux1 = "Vicente";
	private String userNameCoachAux1 = "Del Bosque";	
	private String dniAux1="44444444Y";
	
	private String nameCoachAux2 = "Jose";
	private String userNameCoachAux2 = "Mourinho";	
	private String dniAux2="55555555U";
	
	private String nameTeamAux1 = "Auxiliar Team 1";
	private String nameTeamAux2 = "Auxiliar Team 2";
	private String nameTeamAux3 = "Auxiliar Team 3";
	
	private String userName1;
	private String userName2;
	private String userName3;
	private String userName4;
	
	private String name1;
	private String name2;
	private String name3;
	private String name4;
	
	private String dni1;
	private String dni2;
	private String dni3;
	private String dni4;
	
	private Long idAuxCoach1;
	private Long idAuxCoach2;
	private Long idAuxCoach3;
	
	private Long idAuxTeam1;
	private Long idAuxTeam2;
	private Long idAuxTeam3;
	
	private Long idAuxAthlete1;
	private Long idAuxAthlete2;
	private Long idAuxAthlete3;
	private Long idAuxAthlete4;
	



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
		
		//Update
		String name2 = "Josep";
		String userName2 = "Guardiola";
		updateCoach(name2,userName2,coach);
		
		//Read coach
		coach = readCoach(idCoach);
		
		
		//Make assert
		makeAssertCoach(name2,userName2,coach);
		
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
		
		initCoaches();
		
		//Read coach by name and surname
		Coach coach = readCoachByUserName(userName1);
		
		//Make assert
		makeAssertCoach(name1,userName1,coach);
		
		//Read coach by name and surname
		coach = readCoachByUserName(userName3);
		
		//Make assert
		makeAssertCoach(name3,userName3,coach);	

		finishCoaches();		
	}
	
	
	@Test
	public void testGetCoachByDniJpa() {
		
		//select application context for JPA
		applicationContext = applicationContextJpa;
		
		//call testCoachCRUD
		testGetCoachByDni();
		
		//remove application context
		applicationContext = null;
	
	}
	
	@Test
	public void testGetCoachByDniMock() {
		
		//select application context for JPA
		applicationContext = applicationContextMock;
		
		//call testCoachCRUD
		testGetCoachByDni();
		
		//remove application context
		applicationContext = null;
	
	}

	private void testGetCoachByDni() {
		
		initCoaches();
		
		//Read coach by name and surname
		Coach coach = readCoachByDni(dni1);
		
		//Make assert
		makeAssertCoach(name1,userName1,coach);
		
		//Read coach by name and surname
		coach = readCoachByDni(dni3);
		
		//Make assert
		makeAssertCoach(name3,userName3,coach);	

		finishCoaches();		
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
		
		//Read coach by name and surname
		List<Coach> listCoaches = readAllCoaches();		
		int sizeOriginal = listCoaches.size();
		
		initCoaches();
		
		//Read coach by name and surname
		 listCoaches = readAllCoaches();
		
		//Make assert
		assertEquals(sizeOriginal + 3,listCoaches.size());
		
		finishCoaches();		
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
		Long idCoach = createCoach(nameCoachAux1,userNameCoachAux1,dniAux1);
		
		
		//Create team
		Long idTeam = createTeam(name,idCoach);
		
		
		//Make assert
		assertNotNull(idTeam);
		
		//Read team
		Team team = readTeam(idTeam);
		
		//Make assert
		makeAssertTeam(name,idCoach,team);
		
		//Update team
		String name2="Team jumpers";
		updateTeam(name2, team);
		
		
		//Read team
		team = readTeam(idTeam);
		
		//Make assert
		makeAssertTeam(name2,idCoach,team);
				
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
		//Read coach by name and surname
		List<Team> listTeams = readAllTeams();
		int sizeOriginal = listTeams.size();
		//Create Coach
		Long idCoach = createCoach(nameCoachAux1,userNameCoachAux1,dniAux1);
		
		//Initialize variables
		String name1 = "England";		
		String name2 = "France";		
		String name3 = "Spain";
		
		
		//Create teams
		Long idTeam1 = createTeam(name1,idCoach);
		Long idTeam2 = createTeam(name2,idCoach);
		Long idTeam3 = createTeam(name3,idCoach);
		
		//Make assert
		assertNotNull(idTeam1);
		assertNotNull(idTeam2);
		assertNotNull(idTeam3);
		
		//Read coach by name and surname
		listTeams = readAllTeams();
		
		//Make assert
		assertEquals(sizeOriginal +3,listTeams.size());
		
		//Delete coaches
		deleteTeam(readTeam(idTeam2));
		
		//Read coach by name and surname
		listTeams = readAllTeams();
		
		//Make assert
		assertEquals(sizeOriginal + 2,listTeams.size());
				
		
		//Delete coaches
		deleteTeam(readTeam(idTeam3));
		
		//Read coach by name and surname
		listTeams = readAllTeams();
		
		//Make assert
		assertEquals(sizeOriginal +1,listTeams.size());		
		
				
				
		//Delete coaches
		deleteTeam(readTeam(idTeam1));
		
		//Read coach by name and surname
		listTeams = readAllTeams();
		
				
		//Read coach by name and surname
		listTeams = readAllTeams();
		
		//Make assert
		assertEquals(sizeOriginal + 0, listTeams.size());
		
		//Delete coach
		deleteCoach(readCoach(idCoach));
		
	}
	

	@Test
	public void testGetTeamsCoachJpa() {
		
		//select application context for JPA
		applicationContext = applicationContextJpa;
		
		//call testTeam
		testGetTeamsCoach();
		
		//remove application context
		applicationContext = null;
	
	}
	
	@Test
	public void testGetTeamsCoachMock() {
		
		//select application context for JPA
		applicationContext = applicationContextMock;
		
		//call testTeam
		testGetTeamsCoach();
		
		//remove application context
		applicationContext = null;
	
	}
	
	
	

	private void testGetTeamsCoach() {
		//Variables
		String nameCoach="Ancelloti";
		String userNameCoach = "ancelo";
		String dniCoach= "99998877L";
		
		//Create Coaches
		Long idCoach = createCoach(nameCoachAux1,userNameCoachAux1,dniAux1);		
		Long idCoach2 = createCoach(nameCoach,userNameCoach,dniCoach);
		
		//Read coaces
		Coach coach1 = readCoach(idCoach);
		Coach coach2 = readCoach(idCoach2);
		
		//Initialize variables
		String name1 = "England";		
		String name2 = "France";		
		String name3 = "Spain";
		
		
		//Create teams
		Long idTeam1 = createTeam(name1,idCoach);
		Long idTeam2 = createTeam(name2,idCoach2);
		Long idTeam3 = createTeam(name3,idCoach);
		
		//List teams by coaches
		List<Team> listTeamCoach1 = readTeamsCoach(coach1);
		List<Team> listTeamCoach2 = readTeamsCoach(coach2);
		
		//Asserts		
		assertEquals(listTeamCoach1.size(), 2);
		assertEquals(listTeamCoach2.size(), 1);
		
		
		//delete Teams
		deleteTeam(readTeam(idTeam1));
		deleteTeam(readTeam(idTeam2));
		deleteTeam(readTeam(idTeam3));
		
		//delete Coaches
		deleteCoach(coach1);
		deleteCoach(coach2);
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
		Long idCoach = createCoach(nameCoachAux1,userNameCoachAux1,dniAux1);
		
		//Create team
		Long idTeam = createTeam(nameTeamAux1,idCoach);
		
		//Create athlete
		Long idAthlete = createAthlete(name,userName, idTeam,dni);
		
		//Make assert
		assertNotNull(idAthlete);
		
		//Read athlete
		Athlete athlete = readAthlete(idAthlete);
		
		//Make assert
		makeAssertAthlete(name,userName,athlete);
		
		//Update athlete
		String name2= "Usain Bolt";
		String username2 = "rayo";
		String dni2 = "98989898";
		
		updateAthlete(name2,username2,dni2,athlete);
		
		
		//Read athlete
		athlete = readAthlete(idAthlete);
		
		//Make assert
		makeAssertAthlete(name2,username2,athlete);
		
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
		
		initAthletes();
		
		//Read athlete by name and surname
		Athlete athlete = readAthleteByUserName(userName1);
		
		//Make assert
		makeAssertAthlete(name1,userName1,athlete);
		
		//Read athlete by name and surname
		athlete = readAthleteByUserName(userName2);
		
		//Make assert
		makeAssertAthlete(name2,userName2,athlete);
				
		finishAthletes();		
	}
	
	
	@Test
	public void testGetAthleteByDniJpa() {
		
		//select application context for JPA
		applicationContext = applicationContextJpa;
		
		//call testAthleteCRUD
		testGetAthleteByDni();
		
		//remove application context
		applicationContext = null;
	
	}
	
	@Test
	public void testGetAthleteByDniMock() {
		
		//select application context for JPA
		applicationContext = applicationContextMock;
		
		//call testAthleteCRUD
		testGetAthleteByDni();
		
		//remove application context
		applicationContext = null;
	
	}

	private void testGetAthleteByDni() {
		
		initAthletes();
		
		//Read athlete by name and surname
		Athlete athlete = readAthleteByDni(dni1);
		
		//Make assert
		makeAssertAthlete(name1,userName1,athlete);
		
		//Read athlete by name and surname
		athlete = readAthleteByDni(dni2);
		
		//Make assert
		makeAssertAthlete(name2,userName2,athlete);
				
		finishAthletes();		
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
		//Read all athletes 
		List<Athlete> listAthlete = readAllAthletes();
		int sizeOriginal = listAthlete.size();
		
		initAthletes();	
		
		//Read all athletes 
		listAthlete = readAllAthletes();
		
		//Make assert
		assertEquals(sizeOriginal + 4,listAthlete.size());
		
		
		finishAthletes();
	}
	

	@Test
	public void testGetAthletesByCoachJpa() {
		
		//select application context for JPA
		applicationContext = applicationContextJpa;
		
		//call testAthlete
		testGetAthletesByCoach();
		
		//remove application context
		applicationContext = null;
	
	}
	
	@Test
	public void testGetAthletesByCoachMock() {
		
		//select application context for JPA
		applicationContext = applicationContextMock;
		
		//call testAthletes
		testGetAthletesByCoach();
		
		//remove application context
		applicationContext = null;
	
	}
	private void testGetAthletesByCoach() {
		initAthletes();	
		
		
		//Read all athletes 
		List<Athlete> listAthletes = readAthletesByCoach(idAuxCoach1);
		
		//Make assert
		assertEquals(1,listAthletes.size());
		
		//Read all athletes 
		listAthletes = readAthletesByCoach(idAuxCoach2);
		
		//Make assert
		assertEquals(3,listAthletes.size());		
		
		finishAthletes();		
	}
	
	@Test
	public void testGetAthletesByTeamJpa() {
		
		//select application context for JPA
		applicationContext = applicationContextJpa;
		
		//call testAthlete
		testGetAthletesByTeam();
		
		//remove application context
		applicationContext = null;
	
	}
	
	@Test
	public void testGetAthletesByTeamMock() {
		
		//select application context for JPA
		applicationContext = applicationContextMock;
		
		//call testAthletes
		testGetAthletesByTeam();
		
		//remove application context
		applicationContext = null;
	
	}
	private void testGetAthletesByTeam() {
		initAthletes();	
		
		
		//Read all athletes team 1
		List<Athlete> listAthletes = readAthletesByTeam(idAuxTeam1);
		
		//Make assert
		assertEquals(1,listAthletes.size());
		
		//Read athletes team 2
		listAthletes = readAthletesByTeam(idAuxTeam2);
		
		//Make assert
		assertEquals(2,listAthletes.size());	
		
		//Read athletes team 3
		listAthletes = readAthletesByTeam(idAuxTeam3);
		
		//Make assert
		assertEquals(1,listAthletes.size());
		
		finishAthletes();		
	}

	
	//--------------------------------------------------------------------------------------------------------------------
	//                                                    PRIVATE METHODS
	//--------------------------------------------------------------------------------------------------------------------
		
	//------------------------------------------------  INITS  ----------------------------------------------------------
	
	private void initCoaches() {
		//Initialize variables
		name1 = "Sebastian";
		userName1 ="Coe";
		dni1="11111111P";
		name2 = "Fermin";
		userName2 ="Cacho";
		dni2="22222222P";
		name3 = "Carl";
		userName3 ="Lewis";
		dni3="33333333P";
		
		//Create coaches
		idAuxCoach1 = createCoach(name1,userName1,dni1);
		idAuxCoach2 = createCoach(name2,userName2,dni2);
		idAuxCoach3 = createCoach(name3,userName3,dni3);
		
		//Make assert
		assertNotNull(idAuxCoach1);
		assertNotNull(idAuxCoach2);
		assertNotNull(idAuxCoach3);
		
	}
	
	private void finishCoaches() {
		//Delete coaches
		deleteCoach(readCoach(idAuxCoach1));
		deleteCoach(readCoach(idAuxCoach2));
		deleteCoach(readCoach(idAuxCoach3));		
			
	}

	private void initAthletes() {
		//Create Coaches
		idAuxCoach1 = createCoach(nameCoachAux1,userNameCoachAux1,dniAux1);
		idAuxCoach2 = createCoach(nameCoachAux2,userNameCoachAux2,dniAux2);
		
		//Create team
		idAuxTeam1 = createTeam(nameTeamAux1,idAuxCoach1);
		idAuxTeam2 = createTeam(nameTeamAux2,idAuxCoach2);
		idAuxTeam3 = createTeam(nameTeamAux3,idAuxCoach2);
		
		//Initialize variables
		name1 = "Sebastian";
		userName1 ="Coe";
		dni1 = "55555555O";
		name2 = "Usain";
		userName2 ="Bolt";
		dni2 = "66666666O";
		name3 = "Carl";
		userName3 ="Lewis";
		dni3 = "77777777O";
		name4 = "Fermin";
		userName4 ="Cacho";
		dni4 = "11111111O";
		
		
		//Create athletes
		idAuxAthlete1 = createAthlete(name1,userName1,idAuxTeam1,dni1);
		idAuxAthlete2 = createAthlete(name2,userName2,idAuxTeam2,dni2);
		idAuxAthlete3 = createAthlete(name3,userName3,idAuxTeam2,dni3);
		idAuxAthlete4 = createAthlete(name4,userName4,idAuxTeam3,dni4);
		
		//Make assert
		assertNotNull(idAuxAthlete1);
		assertNotNull(idAuxAthlete2);
		assertNotNull(idAuxAthlete3);
		assertNotNull(idAuxAthlete4);
	}
	
	private void finishAthletes() {
		//Delete athlete
		deleteAthlete(readAthlete(idAuxAthlete4));
		deleteAthlete(readAthlete(idAuxAthlete2));
		deleteAthlete(readAthlete(idAuxAthlete3));
		deleteAthlete(readAthlete(idAuxAthlete1));
		
		//Delete team
		deleteTeam(readTeam(idAuxTeam1));
		deleteTeam(readTeam(idAuxTeam2));
		deleteTeam(readTeam(idAuxTeam3));
		
		//Delete coach
		deleteCoach(readCoach(idAuxCoach1));	
		deleteCoach(readCoach(idAuxCoach2));
	}
	
	
	
	
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
	


	private Coach readCoachByDni(String dni) {
		//Get bean
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Read Coach
		Coach coach = userDao.getCoachByDni(dni);
		
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
		
		//Read teams		
		List<Team> listTeams = userDao.getAllTeams();
		
		return listTeams;		
	}
	
	private List<Team> readTeamsCoach(Coach coach){
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Read teams
		List<Team> listTeams = userDao.getTeamsByCoach(coach);
		
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
	

	private Athlete readAthleteByDni(String dni) {
		//Get bean
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Read athlete
		Athlete  athlete = userDao.getAthleteByDni(dni);
		
		return athlete;
	}
	
	private List<Athlete> readAllAthletes() {
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Read Coach		
		List<Athlete> listAthlete = userDao.getAllAthletes();
		
		return listAthlete;		
	}
	
	private List<Athlete> readAthletesByCoach(Long idCoach) {
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Read Coach		
		Coach coach = readCoach(idCoach);
		//Read athletes
		List<Athlete> listAthlete = userDao.getAthletesByCoach(coach);
		
		return listAthlete;		
	}
	

	

	private List<Athlete> readAthletesByTeam(Long idAuxTeam) {
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Read Team		
		Team team = readTeam(idAuxTeam);
		
		//Read athletes
		List<Athlete> listAthlete = userDao.getAthletesByTeam(team);
		
		return listAthlete;		
	}
	//------------------------------------------------ UPDATE ----------------------------------------------------------
	
	
	private void updateCoach(String name2, String userName2, Coach coach) {
		//Get bean
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Set new values
		coach.setName(name2);
		coach.setUserName(userName2);
		
		//Remove coach
		userDao.persistCoach(coach);
		
	}
	


	private void updateTeam(String name2, Team team) {
		//Get bean
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Set new values
		team.setName(name2);
		
		//Remove coach
		userDao.persistTeam(team);
		
	}
	
	private void updateAthlete(String name2, String username2, String dni2,Athlete athlete) {
		UserDao userDao = applicationContext.getBean(UserDao.class);
		
		//Set new values
		athlete.setName(name2);
		athlete.setUserName(username2);
		athlete.setDni(dni2);
		
		//Remove coach
		userDao.persistAthlete(athlete);
		
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
