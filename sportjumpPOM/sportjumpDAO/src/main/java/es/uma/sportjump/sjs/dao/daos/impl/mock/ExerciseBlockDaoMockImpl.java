package es.uma.sportjump.sjs.dao.daos.impl.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import es.uma.sportjump.sjs.dao.daos.ExerciseBlockDao;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.ExerciseBlock;

@Repository("ExerciseBlockDao")
public class ExerciseBlockDaoMockImpl implements ExerciseBlockDao{
	List<ExerciseBlock> listExerciseBlocks = new ArrayList<ExerciseBlock>();

	@Override
	public void persistExerciseBlock(ExerciseBlock exerciseBlock) {
		
		if (exerciseBlock != null){
			ExerciseBlock block = getExerciseBlockById(exerciseBlock.getIdExerciseBlock());
			if (block == null){
				listExerciseBlocks.add(exerciseBlock);
				exerciseBlock.setIdExerciseBlock(Long.valueOf(listExerciseBlocks.indexOf(exerciseBlock)));				
			}else{
				Long id =  block.getIdExerciseBlock();
				listExerciseBlocks.set(id.intValue(), exerciseBlock);
				exerciseBlock.setIdExerciseBlock(id);				
			}
		}		
	}

	@Override
	public ExerciseBlock getExerciseBlockById(Long idBlock) {
		ExerciseBlock exerciseBlock = null;
		
		for (ExerciseBlock block : listExerciseBlocks){
			if (block.getIdExerciseBlock() == idBlock){
				exerciseBlock = block;
				break;
			}
		}
		
		return exerciseBlock;
	}

	@Override
	public void deleteExerciseBlock(Long idBlock) {
		ExerciseBlock exerciseBlock = getExerciseBlockById(idBlock);
		
		listExerciseBlocks.remove(exerciseBlock);		
	}

	@Override
	public List<ExerciseBlock> getAllExerciseBlockByCoach(Coach coach) {
		List<ExerciseBlock> res = new ArrayList<ExerciseBlock>();
		
		for (ExerciseBlock block : listExerciseBlocks){
			if (block.getCoach().getIdUser() == coach.getIdUser()){
				res.add(block);
			}
		}
		
		return res;
	}

	@Override
	public ExerciseBlock getCompleteExerciseBlock(ExerciseBlock exerciseBlock) {
		return getExerciseBlockById(exerciseBlock.getIdExerciseBlock());
	}

	@Override
	public ExerciseBlock getExerciseBlockByNameAndCoach(String name, Coach coach) {
		ExerciseBlock res = null;
		
		for (ExerciseBlock block : listExerciseBlocks){
			if (name.equals(block.getName()) && block.getCoach().getIdUser() == coach.getIdUser()){
				res = block;
				break;
			}
		}
		
		return res;
	}

}
