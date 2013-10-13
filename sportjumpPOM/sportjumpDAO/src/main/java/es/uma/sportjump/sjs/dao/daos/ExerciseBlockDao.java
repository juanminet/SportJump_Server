package es.uma.sportjump.sjs.dao.daos;

import java.util.List;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;

public interface ExerciseBlockDao {
	
	/**
	 * Persist an exerciseBlock given by parameter
	 * @param exerciseBlock
	 */
	public void  persistExerciseBlock(ExerciseBlock exerciseBlock);
	
	/**
	 * Return  a complete ExerciseBlock (filled by exercises) 
	 * @param idBlock 
	 * @return ExerciseBlok fill by exercises
	 */
	public ExerciseBlock getExerciseBlockById(Long idBlock);
	
	/**
	 * Delete an ExerciseBlock 
	 * @param idBlock
	 */
	public void deleteExerciseBlock(Long idBlock);
	
	
	/**
	 * Return a LAZY list of ExerciseBlock from a coach given by paramenter
	 * @param coach
	 * @return
	 */
	public List<ExerciseBlock> getAllExerciseBlockByCoach(Coach coach);
	
	/**
	 * Return an ExerciseBlock object filled by his Exercises;
	 * @param exerciseBlock
	 * @return
	 */
	public ExerciseBlock getCompleteExerciseBlock(ExerciseBlock exerciseBlock);

}
