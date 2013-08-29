package es.uma.sportjump.sjs.web.controller.commands;


public class AthleteCommand extends ProfileCommand {
	
	private Long idTeam;
	private String nameTeam;

	public Long getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(Long idTeam) {
		this.idTeam = idTeam;
	}


	public String getNameTeam() {
		return nameTeam;
	}

	public void setNameTeam(String nameTeam) {
		this.nameTeam = nameTeam;
	}
}
