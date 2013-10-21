package es.uma.sportjump.sjs.model.entities;

import static org.junit.Assert.assertEquals;
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

import es.uma.sportjump.sjs.model.entities.test.util.AthleteTestUtil;
import es.uma.sportjump.sjs.model.entities.test.util.CoachTestUtil;
import es.uma.sportjump.sjs.model.entities.test.util.TeamTestUtil;

public class AthleteModelEntityTest {

	private static EntityManagerFactory entityManagerFactory = null;
	
	private static CoachTestUtil coachTestUtil;
	private static TeamTestUtil teamTestUtil;
	private static AthleteTestUtil athleteTestUtil;
	
	private static Coach coach;
	private static Team team;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("sportjumpJpaPU");
		
		coachTestUtil = CoachTestUtil.getInstance(entityManagerFactory);
		teamTestUtil = TeamTestUtil.getInstance(entityManagerFactory);
		athleteTestUtil = AthleteTestUtil.getInstance(entityManagerFactory);

		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		athleteTestUtil = null;
		teamTestUtil = null;
		coachTestUtil= null;		
		entityManagerFactory = null;
	}

	@Before
	public void setUp() throws Exception {
		coach = coachTestUtil.createCompleteCoach();
		team = teamTestUtil.createCompleteTeam(coach);
	}

	@After
	public void tearDown() throws Exception {
		//delete static team
		teamTestUtil.deleteTeam(team.getIdTeam());
		
		//delete static coach
		coachTestUtil.deleteCoach(coach.getIdUser());		
	}

	@Test
	public void testCRUD() {

		// Definition Athlete
		String name = "Sebastian";
		String surname = "Coe";
		String userName = "sebas";
		String email = "coe@coe.es";
		String dni = "31722334y";

		// Create athlete
		Long idAthlete = athleteTestUtil.createAthlete(name, surname,userName, email,dni, team);

		// Make assert
		assertNotNull(idAthlete);

		// Read athlete
		Athlete athlete = athleteTestUtil.readAthlete(idAthlete);

		// Make assert
		athleteTestUtil.makeAssertAthlete(name, surname, athlete);

		// Update Athlete
		String newName = "Fermin";

		athleteTestUtil.updateAthlete(idAthlete, newName);

		// Read Athlete
		athlete = athleteTestUtil.readAthlete(idAthlete);

		// Make assert
		athleteTestUtil.makeAssertAthlete(newName, surname, athlete);

		// Delete athlete
		athleteTestUtil.deleteAthlete(idAthlete);

		// Read Client
		athlete = athleteTestUtil.readAthlete(idAthlete);

		// Make assert
		assertNull(athlete);

	}
	
	@Test
	public void testAttributes(){
		// Definition Athlete
		String name = "Sebastian";
		String surname = "Coe";
		String userName = "sebas";
		String email = "coe@coe.es";
		String dni="11223344";
		String type="type";
		String comments = "Coments";
		String telephone = "6666666666";
		String address = "Calle de la casa de al lado";
		Date dateBirth = new Date();
		
				
		
		// Create athlete
		Long idAthlete = athleteTestUtil.createCompleteAthlete(name, surname,userName, email,dni,type,comments,telephone,address,dateBirth, team);
		
		Athlete athlete = athleteTestUtil.readAthlete(idAthlete);
		assertEquals(type, athlete.getType());
		assertEquals(comments, athlete.getComments());
		assertEquals(telephone, athlete.getTelephone());
		assertEquals(address, athlete.getAddress());
		
		Calendar calExpected = Calendar.getInstance();
		calExpected.setTime(dateBirth);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(athlete.getDateBirth());
		assertEquals(calExpected.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.DAY_OF_MONTH));
		assertEquals(calExpected.get(Calendar.MONTH), cal.get(Calendar.MONTH));
		assertEquals(calExpected.get(Calendar.YEAR), cal.get(Calendar.YEAR));
		
		athleteTestUtil.deleteAthlete(idAthlete);
				
	}
}
