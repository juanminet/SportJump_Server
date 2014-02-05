package es.uma.sportjump.sjs.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ATHLETE database table.
 * 
 */
@Entity
@Table(name="TB_ATHLETE")
@NamedQueries({
	@NamedQuery(name="findAllAthletes",
				query="Select a from Athlete a"),
	@NamedQuery(name="findAthleteByUserName",
				query="Select a from Athlete a where a.userName = :userName"),
	@NamedQuery(name="findAthleteByDni",
				query="Select a from Athlete a where a.dni = :dni"),
	@NamedQuery(name="findAthleteFromTeam",
				query="Select a from Athlete a where a.team.idTeam = :idTeam"),
	@NamedQuery(name="findAthleteFromCoach",
				query="Select a from Athlete a where a.team.coach.idUser = :idCoach")
})
public class Athlete extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	
	@ManyToOne(fetch=FetchType.LAZY , targetEntity = es.uma.sportjump.sjs.model.entities.Team.class) 
	@JoinColumn(name = "ID_TEAM", nullable = false)
	private Team team;

    public Athlete() {
	    super();
    }

    
	public Team getTeam() {
		return team;
	}



	public void setTeam(Team team) {
		this.team = team;
	}


}