package es.uma.sportjump.sjs.dao.impl.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.uma.sportjump.sjs.dao.daos.ExerciseBlockDaoTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-mock-application-dao.xml")  
public class ExerciseBlockDaoMockTest  extends ExerciseBlockDaoTest{
	
	@Test
	public void testExerciseBlockCRUD(){
		super.testExerciseBlockCRUD();
	}
	
	@Test 
	public void testExercises(){
		super.testExercises();
	}
	
	@Test
	public void testAllExerciseBlocks(){
		super.testAllExerciseBlocks();
	}
}
