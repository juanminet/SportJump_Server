package es.uma.sportjump.sjs.model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(	name="TB_TRAINING", 
	uniqueConstraints= @UniqueConstraint(columnNames={"NAME", "ID_USER"})
)
@NamedQueries({
	@NamedQuery(name="findAllTrainings",
				query="Select t from Training t"),
	@NamedQuery(name="findAllTrainingsByCoach",
				query="Select t from Training t where t.coach.idUser = :idUser"),
	@NamedQuery(name="findTrainingByNameAndCoach",
				query="Select t from Training t where t.coach.idUser = :idUser and t.name = :name"),
	@NamedQuery(name="findFetchTrainingById",
				query="Select t from Training t LEFT JOIN FETCH t.listExerciseBlock where t.idTraining = :idTraining")
})
public class Training {
	
	@PreRemove
	protected void preRemove() {
		setListExerciseBlock(null);
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_training")
	private Long idTraining;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="type", nullable=false)
	private String type;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne(	fetch=FetchType.EAGER, 
				targetEntity=es.uma.sportjump.sjs.model.entities.Coach.class)
	@JoinColumn(name="id_user", nullable=false)
	private Coach coach;
	
	@ManyToMany
    @JoinTable(	joinColumns = @JoinColumn(name = "id_training"),
            	inverseJoinColumns = @JoinColumn(name = "id_block"))
	private List<ExerciseBlock> listExerciseBlock;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="training")
	private List<CalendarEvent>listCalendarEvents;


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

	
	public List<CalendarEvent> getListCalendarEvents() {
		return listCalendarEvents;
	}


	public void setListCalendarEvents(List<CalendarEvent> listCalendarEvents) {
		this.listCalendarEvents = listCalendarEvents;
	}


	@Override
	public int hashCode() {		
		return this.idTraining.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		 
		if (this.idTraining == null){
			return false;
		}
		
		boolean res = false;		
		
		if (obj instanceof Training){
			if (this.idTraining.equals(((Training) obj).getIdTraining())){
				res = true;
			}
		}
		return res;
	}

}
