package es.uma.sportjump.sjs.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="TB_TEAM")
public class Team {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_Team")
	private Long idTeam;
	
	@Column(name="NAME")
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY , targetEntity = es.uma.sportjump.sjs.model.entities.Coach.class) 
	@JoinColumn(name = "ID_COACH", nullable = false)
	private Coach coach;
	
	


	public Team() {
		super();		
	}

	public Long getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(Long idTeam) {
		this.idTeam = idTeam;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	
	
}
