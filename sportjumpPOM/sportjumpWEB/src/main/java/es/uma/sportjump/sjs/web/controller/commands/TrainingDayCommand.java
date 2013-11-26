package es.uma.sportjump.sjs.web.controller.commands;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import es.uma.sportjump.sjs.web.controller.beans.ExerciseWebBean;

public class TrainingDayCommand {
	
	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String type;
	private String description;
	
	List<ExerciseWebBean> trainingDayList;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public List<ExerciseWebBean> getTrainingDayList() {
		return trainingDayList;
	}
	public void setTrainingDayList(List<ExerciseWebBean> trainingDayList) {
		this.trainingDayList = trainingDayList;
	}
	
	
}
