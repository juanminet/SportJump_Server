package es.uma.sportjump.sjs.service.services;

import java.util.List;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;
import es.uma.sportjump.sjs.model.entities.Training;



public interface TrainingService {
	
	/**
	 *  Creates a new training with the data given by parameters;
	 * @param name Name of training.
	 * @param type type of training.
	 * @param description description and comments of exercise block.
	 * @param exerciseBlockList list with the exerciseblock belong to training.
	 * @param coach coach that owns the current exercise block.
	 * @return the new Exercise block created with his new id.
	 */
	public Training setNewTraining(String name, String type, String description, List<ExerciseBlock> exerciseBlockList, Coach coach);
	
	
	/**
	 * Update an training given by parameter
	 * @param training the exerciseblock to update in database
	 */
	public void updateTraining(Training training);
	
	
	/**
	 * find an training by its id given by parameter
	 * @param idTraining 
	 * @return the training with the id.
	 */
	public Training findExerciseTraining(Long idTraining);
	
	
	/**
	 * Deletes the training given by parameter from database
	 * @param training the exercise block to delete
	 */
	public void removeTraining(Training training);
	
	
	/**
	 *  Find all training from a coach given by parameter
	 * @param coach 
	 * @return list with all training
	 */
	public List<Training> findAllTraining(Coach coach);
}
