package es.uma.sportjump.sjs.web.controller.commands;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class GroupCommand {

	@NotEmpty
	private String name;
	@NotEmpty
	private String type;
	@NotEmpty
	private String description;
	private Date createDate;

	private String coachName;

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	
	
}
