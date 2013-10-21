package es.uma.sportjump.sjs.model.entities;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uma.sportjump.sjs.model.entities.test.util.CoachTestUtil;
import es.uma.sportjump.sjs.model.entities.test.util.TeamTestUtil;

public class TeamModelEntityTest {

	private static EntityManagerFactory entityManagerFactory = null;
	private static Coach coach;
	private static CoachTestUtil coachUtil;
	private static TeamTestUtil teamUtil;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("sportjumpJpaPU");
		coachUtil = CoachTestUtil.getInstance(entityManagerFactory);
		teamUtil = TeamTestUtil.getInstance(entityManagerFactory);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {			
	
		teamUtil = null;
		coachUtil = null;
		entityManagerFactory = null;
	}

	@Before
	public void setUp() throws Exception {
		coach = coachUtil.createCompleteCoach();
	}

	@After
	public void tearDown() throws Exception {
		//delete static coach
		coachUtil.deleteCoach(coach.getIdUser());
	}

	@Test
	public void testCRUD() {

		// Definition Team
		String name = "Equipo";
		String type = "Velocidad";
		String description = "Grupo de la velocidad";
		Date createDate = new Date();

		// Create Team
		Long idTeam = teamUtil.createTeam(name,type, description,createDate,coach);

		// Make assert
		assertNotNull(idTeam);

		// Read Team
		Team team = teamUtil.readTeam(idTeam);		
	

		// Make assert
		teamUtil.makeAssertTeam(name, team);

		// Update Team
		String newName = "Equipazo";

		teamUtil.updateTeam(idTeam, newName);

		// Read team
		team = teamUtil.readTeam(idTeam);

		// Make assert
		teamUtil.makeAssertTeam(newName,team);

		// Delete Team
		teamUtil.deleteTeam(idTeam);

		// Read Team
		team = teamUtil.readTeam(idTeam);

		// Make assert
		assertNull(team);

	}
	
	@Test
	public void testNullables() {
		//Create team
		Team team = new Team();
		teamUtil.checkNullableTeam(team);
		
		//check name
		team= teamUtil.createCompleteTeamNoPersist(coach);
		team.setName(null);
		teamUtil.checkNullableTeam(team);
		
		//check name
		team= teamUtil.createCompleteTeamNoPersist(coach);
		team.setType(null);
		teamUtil.checkNullableTeam(team);
		
		
		//check name
		team= teamUtil.createCompleteTeamNoPersist(coach);
		team.setDescription(null);
		teamUtil.checkNullableTeam(team);
		
		
		//check name
		team= teamUtil.createCompleteTeamNoPersist(coach);
		team.setDateCreate(null);
		teamUtil.checkNullableTeam(team);
		
		
		//check name
		team= teamUtil.createCompleteTeamNoPersist(coach);
		team.setCoach(null);
		teamUtil.checkNullableTeam(team);
	}
}
