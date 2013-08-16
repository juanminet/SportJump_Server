package es.uma.sportjump.sjs.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.model.entities.Team;
import es.uma.sportjump.sjs.service.services.UserService;
import es.uma.sportjump.sjs.web.controller.commands.GroupCommand;


@Controller
@SessionAttributes("groupCommand")
@RequestMapping("/action/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	
	protected static String NEW_GROUP_FORM = "admin_groups_new";	
	
	protected static String GROUPS_REDIRECT = "redirect:/action/admin/groups";	
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String home(Model model) {
		return adminGroups(model);
		
	}	
	
	@RequestMapping(value={"/groups"})
	public String adminGroups(Model model) {
		
		List<Team> listTeams = userService.findAllTeams();
		model.addAttribute("listTeams", listTeams);
	
		return "admin_groups";		
	}	
	
	@RequestMapping(value={"/groups/new"})
	public String newGroup(Model model, HttpSession session) {		
		
		initGroupModel(model,session);	
		
		return NEW_GROUP_FORM;		
	}
	
	@RequestMapping(value={"/groups/{idTeam}"}, method=RequestMethod.GET)
	public String Group(@PathVariable("idTeam") Long idTeam, Model model, HttpSession session) {		
			
		Team team = userService.findTeam(idTeam);
				
		GroupCommand groupCommand = createGroupComand(team);
		
		model.addAttribute("groupCommand", groupCommand);		
		
		return NEW_GROUP_FORM;		
	}
		

	@RequestMapping(value={"/groups/save"}, method=RequestMethod.POST)
	public String saveGroup(@Valid GroupCommand groupCommand, BindingResult errors, HttpSession session) {		
		
		if (errors.hasErrors()) {
			return NEW_GROUP_FORM;
		} 
		
		Coach  coach = (Coach) session.getAttribute("loggedUser");
		
		userService.setNewTeam(groupCommand.getName(), groupCommand.getType(), groupCommand.getDescription(), groupCommand.getCreateDate(), coach);
		
		return GROUPS_REDIRECT;		
	}
	
	
	
		

	@RequestMapping(value={"/athletes"})
	public String AdminAthletes(Model model) {			
		return "admin_athletes";		
	}	
	
	@RequestMapping(value={"/profile"})
	public String AdminProfile(Model model) {			
		return "admin_profile";		
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
		
		groupCommand.setName(team.getName());
		groupCommand.setType(team.getType());
		groupCommand.setDescription(team.getDescription());
		groupCommand.setCreateDate(team.getDateCreate());
		groupCommand.setCoachName(team.getCoach().getCompleteName());
		
		return groupCommand;
	}
}
