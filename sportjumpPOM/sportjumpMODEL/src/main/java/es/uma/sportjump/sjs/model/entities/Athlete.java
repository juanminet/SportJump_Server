package es.uma.sportjump.sjs.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ATHLETE database table.
 * 
 */
@Entity
@Table(name="TB_ATHLETE")
public class Athlete extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="SURNAME")
	private String surname;
	
	@ManyToOne(fetch=FetchType.LAZY , targetEntity = es.uma.sportjump.sjs.model.entities.Team.class) 
	@JoinColumn(name = "ID_TEAM", nullable = false)
	private Team team;

    public Athlete() {
	    super();
    }


	



	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public Team getTeam() {
		return team;
	}



	public void setTeam(Team team) {
		this.team = team;
	}


}