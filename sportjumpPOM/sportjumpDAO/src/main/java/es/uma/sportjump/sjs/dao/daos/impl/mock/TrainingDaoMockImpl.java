package es.uma.sportjump.sjs.dao.daos.impl.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.uma.sportjump.sjs.dao.daos.TrainingDao;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Training;

@Component("trainingDao")
public class TrainingDaoMockImpl implements TrainingDao{
	
	private List<Training> listTrainings = new ArrayList<Training>();

	@Override
	public void persistTraining(Training training) {
		if (training != null){
			Training currentTraining = getTrainingById(training.getIdTraining());			
			if (currentTraining == null){
				listTrainings.add(training);
				training.setIdTraining(Long.valueOf(listTrainings.indexOf(training)));				
			}else{
				Long id =  currentTraining.getIdTraining();
				listTrainings.set(id.intValue(), training);	
			}
		}
	}

	@Override
	public Training getTrainingById(Long id) {

		Training res = null;
		
		for(Training training : listTrainings){
			if (training.getIdTraining() == id){
				res = training;
				break;
			}
		}
		return res;
	}

	@Override
	public Training getCompleteTrainingById(Long id) {		
		return getTrainingById(id) ;
	}

	@Override
	public void deleteTraining(Training training) {
		listTrainings.remove(training);
	}

	@Override
	public List<Training> getAllTrainingByCoach(Coach coach) {
		List<Training> resList = new ArrayList<Training>();
		
		
		for(Training training : listTrainings){
			if (training.getCoach().getIdUser() == coach.getIdUser()){					
				resList.add(training);
			}
		}
		
		return resList;
	}

	@Override
	public Training getTrainingByNameAndCoach(String name, Coach coach) {
		Training res = null;
		
		for(Training training : listTrainings){
			if (name.equals(training.getName()) && training.getCoach().getIdUser() == coach.getIdUser()){					
				res = training;
				break;
			}
		}
		
		return res;
	}

}
