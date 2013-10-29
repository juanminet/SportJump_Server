package es.uma.sportjump.sjs.service.services;

import java.util.List;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Exercise;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;



public interface ExerciseService {
	
	/**
	 *  Creates a new Exercise block with the data given by parameters;
	 * @param name Name of exercise block.
	 * @param type type of exercise block.
	 * @param description description and comments of exercise block.
	 * @param exerciseList list with the exercise belong to Exercise block.
	 * @param coach coach that owns the current exercise block.
	 * @return the new Exercise block created with his new id.
	 */
	public ExerciseBlock setNewExerciseBlock(String name, String type, String description, List<Exercise> exerciseList, Coach coach);
	
	
	/**
	 * Update an exercise block given by parameter
	 * @param exerciseBlock the exerciseblock to update in database
	 */
	public void updateExerciseBlock(ExerciseBlock exerciseBlock);
	
	
	/**
	 * find an exerciseblock by its id given by parameter
	 * @param idExerciseBlock 
	 * @return the exercise with the id.
	 */
	public ExerciseBlock findExerciseBlock(Long idExerciseBlock);
	
	
	/**
	 * find an exerciseblock by name and coach given by parameter
	 * @param name
	 * @param coach
	 * @return the execiseBlock
	 */
	public ExerciseBlock findExerciseBlockByNameAndCoach(String name, Coach coach);
	
	/**
	 * Deletes the exercise block given by parameter from database
	 * @param exerciseBlock the exercise block to delete
	 */
	public void removeExerciseBlock(ExerciseBlock exerciseBlock);
	
	
	/**
	 *  Find all exercise block from a coach given by parameter
	 * @param coach 
	 * @return list with all exercise blocks
	 */
	public List<ExerciseBlock> findAllExerciseBlockByCoach(Coach coach);
}
