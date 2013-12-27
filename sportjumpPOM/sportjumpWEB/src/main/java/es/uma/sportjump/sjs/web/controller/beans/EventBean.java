package es.uma.sportjump.sjs.web.controller.beans;

import java.util.Date;

public class EventBean {
	String idTraining;
	String idGroup;
	String name;
	Date date;
//	String allDay;

	public String getName() {
		return name;
	}
	public String getIdTraining() {
		return idTraining;
	}
	public void setIdTraining(String idTraining) {
		this.idTraining = idTraining;
	}
	public String getIdGroup() {
		return idGroup;
	}
	public void setIdGroup(String idGroup) {
		this.idGroup = idGroup;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
