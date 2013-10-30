package es.uma.sportjump.sjs.dao.test.impl.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.uma.sportjump.sjs.dao.test.TrainingDaoTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-jpa-application-dao.xml")  
public class TrainingDaoJpaTest  extends TrainingDaoTest{
	
	
	@Test
	public void testTrainingCRUD(){
		super.testTrainingCRUD();
	}
	
	@Test
	public void testTrainingWithExerciseBlock(){
		super.testTrainingWithExerciseBlock();
	}
	
	@Test
	public void testAllTrainings(){
		super.testAllTrainings();
	}
	
	@Test
	public void testGetTrainingByNameAndCoach(){
		super.testGetTrainingByNameAndCoach();
	}

}
