package es.uma.sportjump.sjs.web.beans;


public class EventCalendarCompleteBean {
	
	private Long id;
	private String title;
	private String start;
	
	private TrainingBean training;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public TrainingBean getTraining() {
		return training;
	}
	public void setTraining(TrainingBean training) {
		this.training = training;
	}
}
