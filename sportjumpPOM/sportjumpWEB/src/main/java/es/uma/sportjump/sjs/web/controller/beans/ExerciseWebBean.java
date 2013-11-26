package es.uma.sportjump.sjs.web.controller.beans;

import java.util.List;

public class ExerciseWebBean {
	
	private Long id;
	private String name;
	private String type;
	private String description;
	
	private List<String> listExercise;

	
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

	public List<String> getListExercise() {
		return listExercise;
	}

	public void setListExercise(List<String> listExercise) {
		this.listExercise = listExercise;
	}
}
