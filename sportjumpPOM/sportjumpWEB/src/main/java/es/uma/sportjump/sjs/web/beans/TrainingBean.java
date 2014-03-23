package es.uma.sportjump.sjs.web.beans;

import java.util.List;

public class TrainingBean {
	
	private String name;
	private String type;
	private String description;
	
	private List<ExerciseBean> listBlock;
	
	
	

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

	public List<ExerciseBean> getListBlock() {
		return listBlock;
	}

	public void setListBlock(List<ExerciseBean> listBlock) {
		this.listBlock = listBlock;
	}
}
