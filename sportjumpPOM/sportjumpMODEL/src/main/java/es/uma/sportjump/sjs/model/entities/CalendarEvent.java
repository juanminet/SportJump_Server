package es.uma.sportjump.sjs.model.entities;

import java.util.Date;

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

@Entity
@Table(	name="TB_CALENDAR_EVENT",
		uniqueConstraints= @UniqueConstraint(columnNames={"ID_TEAM", "EVENT_DATE"})
)

public class CalendarEvent {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_EVENT")
	private Long idEvent;
	
	@Column(name="EVENT_DATE")
	private Date eventDate;
	
	@Column(name="PERIODIC_EVENT")
	private Boolean periodicEvent;
	
	
	@ManyToOne(fetch=FetchType.LAZY , targetEntity = es.uma.sportjump.sjs.model.entities.Team.class) 
	@JoinColumn(name = "ID_TEAM", nullable = false)
	private Team team;
	
	@ManyToOne(fetch=FetchType.LAZY , targetEntity = es.uma.sportjump.sjs.model.entities.Training.class) 
	@JoinColumn(name = "ID_TRAINING", nullable = false)
	private Training training;

	public Long getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(Long idEvent) {
		this.idEvent = idEvent;
	}


	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Boolean getPeriodicEvent() {
		return periodicEvent;
	}

	public void setPeriodicEvent(Boolean periodicEvent) {
		this.periodicEvent = periodicEvent;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}


}
