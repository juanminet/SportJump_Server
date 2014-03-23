package es.uma.sportjump.sjs.web.beans;


public class AthleteRestBean  extends UserRestBean{
	
	private String coach;
	private String team;
	public String getCoach() {
		return coach;
	}
	public void setCoach(String coach) {
		this.coach = coach;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}	
}
