package es.uma.sportjump.sjs.model.entities.test.util;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import es.uma.sportjump.sjs.model.entities.Athlete;
import es.uma.sportjump.sjs.model.entities.Team;

public class AthleteTestUtil {
	
	private static EntityManagerFactory entityManagerFactory;
	private static AthleteTestUtil athleteUtil;
	
	private AthleteTestUtil(EntityManagerFactory entityManagerFactoryNew){
		entityManagerFactory = entityManagerFactoryNew;
	}
	
	private static synchronized AthleteTestUtil getSynchronizedInstance( EntityManagerFactory entityManagerFactory){
		if (athleteUtil == null){
			athleteUtil = new AthleteTestUtil(entityManagerFactory);
		}
		
		return athleteUtil;
	}
	
	public static AthleteTestUtil getInstance(EntityManagerFactory entityManagerFactory){
		return getSynchronizedInstance(entityManagerFactory);
	}
	
	
	
	public Long createCompleteAthlete(String name, String surname,
			String userName, String email, String dni, String type,
			String comments, String telephone, String address, Date dateBirth, Team team) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//Create athlete
		Athlete athlete = new Athlete();
		athlete.setName(name);
		athlete.setSurname(surname);
		athlete.setUserName(userName);
		athlete.setEmail(email);
		athlete.setDni(dni);
		athlete.setType(type);
		athlete.setComments(comments);
		athlete.setTelephone(telephone);
		athlete.setAddress(address);
		athlete.setDateBirth(dateBirth);
		athlete.setTeam(team);
		
		//Persist entity
		entityManager.getTransaction().begin();
		entityManager.persist(athlete);
		entityManager.getTransaction().commit();
		
		//return idAthlete
		return Long.valueOf(athlete.getIdUser());
	}

	public Long createAthlete(String name, String surname, String userName, String email, String dni, Team team) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//Create athlete
		Athlete athlete = new Athlete();
		athlete.setName(name);
		athlete.setSurname(surname);
		athlete.setUserName(userName);
		athlete.setEmail(email);
		athlete.setDni(dni);
		athlete.setTeam(team);
		
		//Persist entity
		entityManager.getTransaction().begin();
		entityManager.persist(athlete);
		entityManager.getTransaction().commit();
		
		//return idAthlete
		return Long.valueOf(athlete.getIdUser());
	}

	public Athlete readAthlete(Long idAthlete) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//read Athlete
		Athlete athlete = entityManager.find(Athlete.class, idAthlete);
		return athlete;
	}

	public void updateAthlete(Long idAthlete, String newName) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
	
		//read Athlete
		Athlete athlete = entityManager.find(Athlete.class, idAthlete);
		
		//updete athlete
		athlete.setName(newName);
		
		entityManager.getTransaction().begin();
		entityManager.persist(athlete);
		entityManager.getTransaction().commit();		
	}

	public void deleteAthlete(Long idAthlete) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//remove athlete
		Athlete athlete = entityManager.find(Athlete.class, idAthlete);
		
		entityManager.getTransaction().begin();
		entityManager.remove(athlete);
		entityManager.getTransaction().commit();
		
	}

	public void makeAssertAthlete(String name, String surname, Athlete athlete) {
		assertEquals(name, athlete.getName());
		assertEquals(surname, athlete.getSurname());
		
	}
	

}
