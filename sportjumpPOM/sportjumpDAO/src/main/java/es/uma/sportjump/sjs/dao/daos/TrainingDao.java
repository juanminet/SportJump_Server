package es.uma.sportjump.sjs.dao.daos;

import java.util.List;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Training;




public interface TrainingDao {
	
	
	/**
	 * Persist training
	 * @param training
	 */
	public void persistTraining(Training training);	
	
	/**
	 * Get LAZY training by id given by parameter 
	 * @param id
	 * @return
	 */
	public Training getTrainingById(Long id);
	
	/**
	 * Get EAGER training by id given by parameter 
	 * @param id
	 * @return
	 */
	public Training getCompleteTrainingById(Long id);
	
	/**
	 * Remove coach
	 * @param id
	 */
	public void deleteTraining(Training training);

	
	/**
	 * Return a list with all trainings 
	 * @return
	 */
	public List<Training> getAllTrainingByCoach(Coach coach);
	
	

}
