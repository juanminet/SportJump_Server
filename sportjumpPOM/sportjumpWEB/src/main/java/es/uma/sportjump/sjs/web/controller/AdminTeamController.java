package es.uma.sportjump.sjs.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.uma.sportjump.sjs.model.entities.Athlete;
import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Team;
import es.uma.sportjump.sjs.service.services.AuthService;
import es.uma.sportjump.sjs.service.services.UserService;
import es.uma.sportjump.sjs.web.controller.beans.RowAdminTeamWebBean;
import es.uma.sportjump.sjs.web.controller.commands.GroupCommand;


@Controller
@SessionAttributes({"groupCommand", "listTeams"})
@RequestMapping("/action/admin")
public class AdminTeamController {
	
	

	@Autowired
	UserService userService;
	
	@Autowired
	AuthService authService;
	
	
	protected static final String LIST_GROUPS = "admin_groups";	
	protected static final String NEW_GROUP_FORM = "admin_groups_new";
	protected static final String GROUPS_REDIRECT = "redirect:/action/admin/groups";	
	
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String home(Model model,HttpSession session) {
		return adminGroups(model,session);
		
	}	
	
	@RequestMapping(value={"/groups"})
	public String adminGroups(Model model, HttpSession session) {
		
		Coach  coach = (Coach) session.getAttribute("loggedUser");	
		
		List<RowAdminTeamWebBean> listTeams = fillAdminTeams(coach);		
		
		model.addAttribute("listTeams", listTeams);
	
		return "admin_groups";		
	}		
	

	@RequestMapping(value={"/groups/new"})
	public String newGroup(Model model, HttpSession session) {		
		
		initGroupModel(model,session);	
		
		return NEW_GROUP_FORM;		
	}
	
	@RequestMapping(value={"/groups/{idTeam}"}, method=RequestMethod.GET)
	public String getGroup(@PathVariable("idTeam") Long idTeam, Model model, HttpSession session) {		
			
		Team team = userService.findTeam(idTeam);
				
		GroupCommand groupCommand = createGroupComand(team);
		
		model.addAttribute("groupCommand", groupCommand);	
		
		List<Athlete> listAthletes = userService.findAthletesFromTeam(team);		
		
		model.addAttribute("listAthletes", listAthletes);
		
		return NEW_GROUP_FORM;		
	}
		

	@RequestMapping(value={"/groups/save"}, method=RequestMethod.POST)
	public String saveGroup(@Valid GroupCommand groupCommand, BindingResult errors, Model model, HttpSession session) {		
	
		
		if (errors.hasErrors()) {
			model.addAttribute("newTeam", true);
			return NEW_GROUP_FORM;
		} 
		
		Coach  coach = (Coach) session.getAttribute("loggedUser");
		
		
		Long idTeam = groupCommand.getIdTeam();
		if (idTeam == null){	//Create
			userService.setNewTeam(groupCommand.getName(), groupCommand.getType(), groupCommand.getDescription(), groupCommand.getCreateDate(), coach);
		}else{ //update		
			Team team = userService.findTeam(groupCommand.getIdTeam());
			userService.updateTeam(fillTeam(team,groupCommand));
		}
		
		return GROUPS_REDIRECT;		
	}
	
	@RequestMapping(value={"/groups/remove/{idTeam}"}, method=RequestMethod.GET)
	public String removeGroup(@PathVariable("idTeam") Long idTeam, Model model, HttpSession session) {		
			
		Team team = userService.findTeam(idTeam);
		
		if (userService != null){
			userService.removeTeam(team);
		}
		
		return GROUPS_REDIRECT;		
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

	private Team fillTeam(Team team, GroupCommand groupCommand) {
		team.setName(groupCommand.getName());
		team.setType(groupCommand.getType());
		team.setDescription(groupCommand.getDescription());		
		
		return team;
	}

		
	private void initGroupModel(Model model, HttpSession session) {
		GroupCommand groupCommand = new GroupCommand();
		groupCommand.setCreateDate(new Date(System.currentTimeMillis()));		
		Coach  coach = (Coach) session.getAttribute("loggedUser");		
		groupCommand.setCoachName(coach.getCompleteName());
		
		model.addAttribute("groupCommand", groupCommand);
		model.addAttribute("newTeam", true);
	}
	
	private GroupCommand createGroupComand(Team team) {
		GroupCommand groupCommand = new GroupCommand();
		
		groupCommand.setIdTeam(team.getIdTeam());
		groupCommand.setName(team.getName());
		groupCommand.setType(team.getType());
		groupCommand.setDescription(team.getDescription());
		groupCommand.setCreateDate(team.getDateCreate());
		groupCommand.setCoachName(team.getCoach().getCompleteName());
		
		return groupCommand;
	}

}
