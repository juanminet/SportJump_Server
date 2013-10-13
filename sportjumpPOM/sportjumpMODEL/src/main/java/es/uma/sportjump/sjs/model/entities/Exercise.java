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
import javax.persistence.UniqueConstraint;

/**
 * The persistent class for the ATHLETE database table.
 * 
 */
@Entity
@Table(	name="TB_EXERCISE",
		uniqueConstraints= @UniqueConstraint(columnNames={"POSITION", "ID_BLOCK"})
)
public class Exercise {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_EXERCISE")
	private Long idExercise;
	
	@Column(name="NAME", nullable=false)
	private String name;
	
	@Column(name="POSITION", nullable=false)
	private int pos;
	
	@ManyToOne(fetch=FetchType.LAZY , targetEntity = es.uma.sportjump.sjs.model.entities.ExerciseBlock.class) 
	@JoinColumn(name = "ID_BLOCK", nullable = false)
	private ExerciseBlock exerciseBlock;

	public Long getIdExercise() {
		return idExercise;
	}

	public void setIdExercise(Long idExercise) {
		this.idExercise = idExercise;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public ExerciseBlock getExerciseBlock() {
		return exerciseBlock;
	}

	public void setExerciseBlock(ExerciseBlock exerciseBlock) {
		this.exerciseBlock = exerciseBlock;
	}


}
