package es.uma.sportjump.sjs.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Team;
import es.uma.sportjump.sjs.model.entities.Training;
import es.uma.sportjump.sjs.service.services.TrainingService;
import es.uma.sportjump.sjs.service.services.UserService;
import es.uma.sportjump.sjs.web.controller.beans.RowAdminTeamWebBean;

@Controller
@RequestMapping("/action/planning")
public class PlanningController {
	
	private static final String PLANNING_GROUP_LIST = "planning_group_list";
	private static final String PLANNING_CALENDAR = "planning_calendar";

	@Autowired
	UserService userService;
	
	@Autowired
	TrainingService trainingService;
	
	@RequestMapping(value="/group/list" , method = { RequestMethod.GET, RequestMethod.POST })
	public String groupList(Model model, HttpSession session) {		
		
		Coach  coach = (Coach) session.getAttribute("loggedUser");
		List<RowAdminTeamWebBean> listTeams = 	fillAdminTeams(coach);		
		model.addAttribute("listTeams", listTeams);
		
		return PLANNING_GROUP_LIST;		
	}	
	
	
	@RequestMapping(value="/calendar/group/{idGroup}" , method = { RequestMethod.GET, RequestMethod.POST })
	public String showCalendar(@PathVariable("idGroup") Long idGroup, Model model, HttpSession session) {	
		initPlanning(idGroup, model, session);
		
		
		return PLANNING_CALENDAR;		
	}	

	
	
	private List<RowAdminTeamWebBean> fillAdminTeams(Coach coach) {
		List<RowAdminTeamWebBean> listAdminTeams = new ArrayList<RowAdminTeamWebBean>();
		List<Team> listTeams = userService.findTeamsByCoach(coach);
		
		for(Team team : listTeams){
			RowAdminTeamWebBean row = new RowAdminTeamWebBean();
			row.setIdTeam(team.getIdTeam());
			row.setName(team.getName());
			row.setType(team.getType());
			row.setSize(userService.findAthletesFromTeam(team).size());
			listAdminTeams.add(row);
		}
		return listAdminTeams;
	}
	
	
	private void initPlanning(Long idGroup, Model model, HttpSession session) {		
		Coach  coach = (Coach) session.getAttribute("loggedUser");	
		
		List<Training> trainingDayList = trainingService.findAllTraining(coach);
		
		model.addAttribute("trainingDayList", trainingDayList);
		model.addAttribute("idGroup", idGroup);
	}
}
