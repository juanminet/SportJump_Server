package es.uma.sportjump.sjs.dao.daos.impl.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.uma.sportjump.sjs.dao.daos.TrainingDao;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Training;

@Repository("trainingDao")
public class TrainingDaoJpaImpl implements TrainingDao{

	 
	@PersistenceContext
 	protected EntityManager em;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void persistTraining(Training training) {
		if (training.getIdTraining() != null){
			em.merge(training);
		}else{
			em.persist(training);
		}
		
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Training getTrainingById(Long id) {			
		return em.find(Training.class, id);
	}
	

	@Transactional(propagation=Propagation.REQUIRED)
	public Training getCompleteTrainingById(Long id) {
		Training res = null;
		Query query = em.createNamedQuery("findFetchTrainingById")
				.setParameter("idTraining",id);
		try{
			res =  (Training) query.getSingleResult();
		}catch (NoResultException noResultException) {
			res = null;
		}
		return res;
	}
	

	@Transactional(propagation=Propagation.REQUIRED)
	public Training getTrainingByNameAndCoach(String name, Coach coach) {
		Training training = null;
		Query query = em.createNamedQuery("findTrainingByNameAndCoach")
				.setParameter("idUser", coach.getIdUser())
				.setParameter("name", name);
		
		try{
			training = (Training) query.getSingleResult();
		}catch (NoResultException noResultException) {
			//No result, return training = null;
		}
		return training;
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteTraining(Training training) {
		em.remove(getTrainingById(training.getIdTraining()));
		em.flush();
	}

	
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Training> getAllTrainingByCoach(Coach coach) {


		Query query = em.createNamedQuery("findAllTrainingsByCoach")
				.setParameter("idUser", coach.getIdUser());
		
		List<Training> listTraining = query.getResultList();		
		
		return listTraining;		
	}

	
}
