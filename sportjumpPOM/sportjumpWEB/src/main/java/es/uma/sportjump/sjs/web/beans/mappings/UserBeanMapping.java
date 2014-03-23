package es.uma.sportjump.sjs.web.beans.mappings;

import es.uma.sportjump.sjs.model.entities.Athlete;
import es.uma.sportjump.sjs.web.beans.AthleteRestBean;

public class UserBeanMapping {
	
	public static AthleteRestBean athleteRestBeanFromAthleteEntity(Athlete athlete){
		AthleteRestBean athleteRestBean = new AthleteRestBean();
		
		athleteRestBean.setIdUser(athlete.getIdUser());	
		athleteRestBean.setUserName(athlete.getUserName());
		athleteRestBean.setName(athlete.getName());
		athleteRestBean.setSurname(athlete.getSurname());
		athleteRestBean.setDni(athlete.getDni());
		
		athleteRestBean.setAddress(athlete.getAddress());		
		athleteRestBean.setDateBirth(athlete.getDateBirth());
		athleteRestBean.setEmail(athlete.getEmail());			
		athleteRestBean.setTelephone(athlete.getTelephone());
		athleteRestBean.setType(athlete.getType());
		athleteRestBean.setComments(athlete.getComments());
		
		athleteRestBean.setTeam(athlete.getTeam().getName());
		athleteRestBean.setCoach(athlete.getTeam().getCoach().getName());
		
		return athleteRestBean;
	}
}
