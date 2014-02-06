package es.uma.sportjump.sjs.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(	name="TB_EXERCISE_BLOCK", 
		uniqueConstraints= @UniqueConstraint(columnNames={"NAME", "ID_USER"})
)
@NamedQueries({
	@NamedQuery(name="findAllExerciseBlock",
				query="Select e from ExerciseBlock e"),
	@NamedQuery(name="findAllExerciseBlockByCoach",
				query="Select e from ExerciseBlock e where e.coach.idUser = :idUser"),
	@NamedQuery(name="findExerciseBlockByNameAndCoach",
				query="Select e from ExerciseBlock e where e.coach.idUser = :idUser and e.name = :name"),
	@NamedQuery(name="findAllExercise",
				query="Select e from Exercise e")
})
public class ExerciseBlock {
	
	@SuppressWarnings("unused")
	@PreRemove
	private void removeTrainingReferences(){
		for (Training training : listTraining){
			training.getListExerciseBlock().remove(this);
		}
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_BLOCK")
	private Long idExerciseBlock;
	
	@Column(name="NAME" , nullable = false)
	private String name;
	
	@Column(name="TYPE", nullable = false)
	private String type;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=es.uma.sportjump.sjs.model.entities.Coach.class)
	@JoinColumn(name="ID_USER", nullable=false)
	private Coach coach;	
	
	@OneToMany(fetch = FetchType.EAGER, cascade= CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="ID_BLOCK_FK", nullable=false)
	private List<Exercise> listExercises;
	
	@ManyToMany(mappedBy="listExerciseBlock")
	private List<Training> listTraining;


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

	public List<Exercise> getListExercises() {
		return listExercises;
	}

	public void setListExercises(List<Exercise> listExercises) {
		this.listExercises = listExercises;
	}

	public List<Training> getListTraining() {
		return listTraining;
	}

	public void setListTraining(List<Training> listTraining) {
		this.listTraining = listTraining;
	}
	
	public ExerciseBlock clone(){
		ExerciseBlock res = new ExerciseBlock();
		res.setIdExerciseBlock(this.idExerciseBlock);
		res.setName(this.name);
		res.setType(this.type);
		res.setDescription(this.description);
		
		if ( this.listExercises != null){
			List<Exercise> listExercises = new ArrayList<Exercise>();
			for (Exercise exercise : this.listExercises){
				Exercise newExercise = new Exercise();
				newExercise.setName(exercise.getName());
				newExercise.setPos(exercise.getPos());
				
				listExercises.add(newExercise);
			}
			res.setListExercises(listExercises);
		}		
	
		return res;
	}

	@Override
	public int hashCode() {		
		return this.idExerciseBlock.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		 
		if (this.idExerciseBlock == null){
			return false;
		}
		
		boolean res = false;		
		
		if (obj instanceof ExerciseBlock){
			if (this.idExerciseBlock.equals(((ExerciseBlock) obj).getIdExerciseBlock())){
				res = true;
			}
		}
		return res;
	}
	
}
