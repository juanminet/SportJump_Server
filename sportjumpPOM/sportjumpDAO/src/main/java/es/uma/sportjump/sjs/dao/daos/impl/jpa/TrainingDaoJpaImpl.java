package es.uma.sportjump.sjs.dao.daos.impl.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.uma.sportjump.sjs.dao.daos.TrainingDao;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Training;

@Repository("trainingDao")
public class TrainingDaoJpaImpl implements TrainingDao{

	 
	@PersistenceContext( type = PersistenceContextType.EXTENDED)
 	protected EntityManager em;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void persistTraining(Training training) {
		em.persist(training);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Training getTrainingById(Long id) {
		return em.find(Training.class, id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Training getCompleteTrainingById(Long id) {
		return em.find(Training.class, id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteTraining(Training training) {
		em.remove(getTrainingById(training.getIdTraining()));
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
