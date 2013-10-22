package es.uma.sportjump.sjs.dao.daos.impl.mock;

import java.util.List;

import es.uma.sportjump.sjs.dao.daos.TrainingDao;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Training;

public class TrainingDaoMockImpl implements TrainingDao{

	@Override
	public void persistTraining(Training training) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Training getTrainingById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Training getCompleteTrainingById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTraining(Training training) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Training> getAllTrainingByCoach(Coach coach) {
		// TODO Auto-generated method stub
		return null;
	}

}
