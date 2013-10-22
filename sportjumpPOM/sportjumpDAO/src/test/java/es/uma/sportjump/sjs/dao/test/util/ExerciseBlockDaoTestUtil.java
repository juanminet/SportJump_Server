package es.uma.sportjump.sjs.dao.test.util;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uma.sportjump.sjs.dao.daos.ExerciseBlockDao;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Exercise;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;


@Component
public class ExerciseBlockDaoTestUtil {
	
	@Autowired
	ExerciseBlockDao exerciseBlockDao;
	
	
	public ExerciseBlock createCompleteExerciseBlockNum(Coach coach, int num){
		// Definition ExerciseBlock
		String name = "Bloque rapidez" + num;
		String type = "Velocidad"+ num;
		String description = "Bloque donde se trabaja la velocidad inmediata y la fuerza explosiva"+ num;

		
		//Create ExerciseBlock
		ExerciseBlock block = new ExerciseBlock();
		block.setName(name);
		block.setDescription(description);
		block.setType(type);
		block.setCoach(coach);
		
		//Persist entity
		exerciseBlockDao.persistExerciseBlock(block);
		
		return block;
	}
	
	
	public Long createExerciseBlock(String name, String type,	String description, List<Exercise> exerciseList, Coach coach) {				
		//create object
		ExerciseBlock exerciseBlock = new ExerciseBlock();
		exerciseBlock.setName(name);
		exerciseBlock.setDescription(description);
		exerciseBlock.setType(type);
		exerciseBlock.setListExercises(exerciseList);
		exerciseBlock.setCoach(coach);
		
		//Persist
		exerciseBlockDao.persistExerciseBlock(exerciseBlock);
		
		return Long.valueOf(exerciseBlock.getIdExerciseBlock());
	}
	
	public ExerciseBlock readExerciseBlock(Long idExerciseBlock) {				
		return exerciseBlockDao.getExerciseBlockById(idExerciseBlock);
	}

	
	public void deleteExerciseBlock(ExerciseBlock exerciseBlock) {		
		//remove
		exerciseBlockDao.deleteExerciseBlock(exerciseBlock.getIdExerciseBlock());
	}

	public void updateExerciseBlock(String name2, String type2,ExerciseBlock exerciseBlock) {
		//set attributes
		exerciseBlock.setName(name2);
		exerciseBlock.setType(type2);
		
		exerciseBlockDao.persistExerciseBlock(exerciseBlock);		
	}

	public void makeAssertExerciseBlock(String name, String type, ExerciseBlock exerciseBlock) {
		assertEquals(name, exerciseBlock.getName());
		assertEquals(type, exerciseBlock.getType());
	}



}
