package es.uma.sportjump.sjs.model.entities.test.util;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import es.uma.sportjump.sjs.model.entities.Coach;

public class CoachTestUtil {
	
	private static EntityManagerFactory entityManagerFactory;
	private static CoachTestUtil coachUtil;
	
	private CoachTestUtil(EntityManagerFactory entityManagerFactoryNew){
		entityManagerFactory = entityManagerFactoryNew;
	}
	
	private static synchronized CoachTestUtil getSynchronizedInstance( EntityManagerFactory entityManagerFactory){
		if (coachUtil == null){
			coachUtil = new CoachTestUtil(entityManagerFactory);
		}
		
		return coachUtil;
	}
	
	public static CoachTestUtil getInstance(EntityManagerFactory entityManagerFactory){
		return getSynchronizedInstance(entityManagerFactory);
	}
	
	public Coach createCompleteCoach(){
		// Definition Coach
		String name = "Mourinho";
		String surname = "Garcia";
		String userName = "Jose";
		String email = "asdf@asdf.es";
		String dni = "88779988R";

		Long idCoach = createCoach(name, surname, userName, email, dni);
		return readCoach(idCoach);
	}
	
	
	public Long createCoach(String name, String surname, String userName, String email, String dni) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//Create coach
		Coach coach = new Coach();
		coach.setName(name);
		coach.setUserName(userName);
		coach.setSurname(surname);
		coach.setEmail(email);
		coach.setDni(dni);
	
		
		//Persist entity
		entityManager.getTransaction().begin();
		entityManager.persist(coach);
		entityManager.getTransaction().commit();
		
		//return idCoach
		return Long.valueOf(coach.getIdUser());
	}
	
	public Coach readCoach(Long idCoach) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//read Coach
		Coach coach = entityManager.find(Coach.class, idCoach);
		return coach;
	}
	
	public void deleteCoach(Long idCoach) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//remove coach
		Coach coach = entityManager.find(Coach.class, idCoach);
		
		entityManager.getTransaction().begin();
		entityManager.remove(coach);
		entityManager.getTransaction().commit();
		
	}
	
	
	public void updateCoach(Long idCoach, String newName) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
	
		//read coach
		Coach coach = entityManager.find(Coach.class, idCoach);
		
		//update coach
		coach.setName(newName);
		
		entityManager.getTransaction().begin();
		entityManager.persist(coach);
		entityManager.getTransaction().commit();		
	}


	public void makeAssertCoach(String name, Coach coach) {
		assertEquals(name, coach.getName());		
	}	

}
