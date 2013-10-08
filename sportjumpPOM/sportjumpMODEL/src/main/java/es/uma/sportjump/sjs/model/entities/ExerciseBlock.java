package es.uma.sportjump.sjs.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TB_EXERCISE_BLOCK")
public class ExerciseBlock {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_BLOCK")
	private Long idExerciseBlock;
	
	@Column(name="NAME" , nullable = false, unique= true)
	private String name;
	
	@Column(name="TYPE", nullable = false)
	private String type;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=es.uma.sportjump.sjs.model.entities.Coach.class)
	@JoinColumn(name="ID_USER", nullable=false)
	private Coach coach;	
	



	public Long getIdExerciseBlock() {
		return idExerciseBlock;
	}

	public void setIdExerciseBlock(Long idExerciseBlock) {
		this.idExerciseBlock = idExerciseBlock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}
}
