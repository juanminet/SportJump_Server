package es.uma.sportjump.sjs.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="TB_TEAM")
@NamedQueries({
	@NamedQuery(name="findAllTeams",
				query="Select t from Team t"),
	@NamedQuery(name="findAllTeamsByCoach",
				query="Select t from Team t where t.coach.idUser = :idCoach"),
	@NamedQuery(name="findfetchTeamById",
				query="Select t from Team t LEFT JOIN FETCH t.coach where t.idTeam = :idTeam")
})
public class Team {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_Team")
	private Long idTeam;
	
	@Column(name="NAME", nullable = false)
	private String name;
	
	@Column(name="TEAM_TYPE", nullable = false)
	private String type;
	
	@Column(name="DESCRIPTION", nullable = false)
	private String description;
	
	@Column(name="DATE_CREATE", nullable = false)
	private Date dateCreate;
	
	@ManyToOne(fetch=FetchType.LAZY , targetEntity = es.uma.sportjump.sjs.model.entities.Coach.class) 
	@JoinColumn(name = "ID_COACH", nullable = false)
	private Coach coach;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "team", cascade = CascadeType.ALL)
	private List<CalendarEvent> listCalendarEvents;
	

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

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	public List<CalendarEvent> getListCalendarEvents() {
		return listCalendarEvents;
	}

	public void setListCalendarEvents(List<CalendarEvent> listCalendarEvents) {
		this.listCalendarEvents = listCalendarEvents;
	}
	
}
