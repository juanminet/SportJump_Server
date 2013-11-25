package es.uma.sportjump.sjs.web.controller.beans;

import java.util.List;

public class TrainingWebBean {
	
	private String name;
	private String type;
	private String description;
	
	private List<ExerciseWebBean> listBlock;
	
	
	

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

	public List<ExerciseWebBean> getListBlock() {
		return listBlock;
	}

	public void setListBlock(List<ExerciseWebBean> listBlock) {
		this.listBlock = listBlock;
	}
}
