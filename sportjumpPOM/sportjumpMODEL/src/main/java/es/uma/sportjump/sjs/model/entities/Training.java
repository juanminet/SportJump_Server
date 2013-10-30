package es.uma.sportjump.sjs.model.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(	name="TB_TRAINING", 
	uniqueConstraints= @UniqueConstraint(columnNames={"NAME", "ID_USER"})
)
public class Training {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_TRAINING")
	private Long idTraining;
	
	@Column(name="NAME", nullable=false)
	private String name;
	
	@Column(name="type", nullable=false)
	private String type;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=es.uma.sportjump.sjs.model.entities.Coach.class)
	@JoinColumn(name="ID_USER", nullable=false)
	private Coach coach;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_TRAINING_FK", nullable=false)
	private List<ExerciseBlock> listExerciseBlock;


	public Long getIdTraining() {
		return idTraining;
	}


	public void setIdTraining(Long idTraining) {
		this.idTraining = idTraining;
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


	public List<ExerciseBlock> getListExerciseBlock() {
		return listExerciseBlock;
	}


	public void setListExerciseBlock(List<ExerciseBlock> listExerciseBlock) {
		this.listExerciseBlock = listExerciseBlock;
	}	


}
