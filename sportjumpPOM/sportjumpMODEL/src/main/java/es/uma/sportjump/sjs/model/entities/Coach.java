package es.uma.sportjump.sjs.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_COACH")
@NamedQueries({
    @NamedQuery(name="findAllCoaches",
                query="SELECT c from Coach c"),
    @NamedQuery(name="findCoachByUserName",
                query="SELECT c from Coach c where c.userName = :userName"),
    @NamedQuery(name="findCoachByDni",
                query="SELECT c from Coach c where c.dni = :dni"),
}) 
public class Coach extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="coach", cascade = CascadeType.ALL)
	private List<Team> listTeams;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="coach", cascade = CascadeType.ALL)
	private List<ExerciseBlock> listExerciseBlocks;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="coach", cascade = CascadeType.ALL)
	private List<Training> listTrainings;
	
    public Coach() {
	    super();
    }

	public List<Team> getListTeams() {
		return listTeams;
	}

	public void setListTeams(List<Team> listTeams) {
		this.listTeams = listTeams;
	}

	public List<ExerciseBlock> getListExerciseBlocks() {
		return listExerciseBlocks;
	}

	public void setListExerciseBlocks(List<ExerciseBlock> listExerciseBlocks) {
		this.listExerciseBlocks = listExerciseBlocks;
	}

	public List<Training> getListTrainings() {
		return listTrainings;
	}

	public void setListTrainings(List<Training> listTrainings) {
		this.listTrainings = listTrainings;
	}
}

