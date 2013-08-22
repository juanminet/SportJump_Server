package es.uma.sportjump.sjs.web.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import es.uma.sportjump.sjs.service.services.UserService;
import es.uma.sportjump.sjs.web.controller.commands.AthleteCommand;
import es.uma.sportjump.sjs.web.controller.commands.GroupCommand;


@Controller
@SessionAttributes({"groupCommand", "athleteCommand"})
@RequestMapping("/action/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	
	protected static String LIST_GROUPS = "admin_groups";	
	protected static String LIST_ATHLETES = "admin_athletes";
	protected static String NEW_GROUP_FORM = "admin_groups_new";
	protected static String NEW_ATHLETE_FORM = "admin_athletes_new";
	protected static String GROUPS_REDIRECT = "redirect:/action/admin/groups";	
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String home(Model model,HttpSession session) {
		return adminGroups(model,session);
		
	}	
	
	@RequestMapping(value={"/groups"})
	public String adminGroups(Model model, HttpSession session) {
		
		Coach  coach = (Coach) session.getAttribute("loggedUser");	
		
		List<Team> listTeams = userService.findTeamsByCoach(coach);
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
	
	private Team fillTeam(Team team, GroupCommand groupCommand) {
		team.setName(groupCommand.getName());
		team.setType(groupCommand.getType());
		team.setDescription(groupCommand.getDescription());		
		
		return team;
	}

	@RequestMapping(value={"/groups/remove/{idTeam}"}, method=RequestMethod.GET)
	public String removeGroup(@PathVariable("idTeam") Long idTeam, Model model, HttpSession session) {		
			
		Team team = userService.findTeam(idTeam);
		
		userService.removeTeam(team);
		
		return GROUPS_REDIRECT;		
	}
	
	
	
		

	@RequestMapping(value={"/athletes"})
	public String adminAthletes(Model model) {	
		
		List<Athlete> listAthletes = userService.findAllAthletes();
		model.addAttribute("listAthletes", listAthletes);		
		
		return LIST_ATHLETES;		
	}	
	
	
	@RequestMapping(value={"/athletes/new"})
	public String newAthlete(Model model, HttpSession session) {	
		
		initAthleteModel(model,session);
		
		
		return NEW_ATHLETE_FORM;		
	}	
	
	@RequestMapping(value={"/athletes/save"}, method=RequestMethod.POST)
	public String saveAthlete(@Valid AthleteCommand athleteCommand, BindingResult errors, Model model, HttpSession session) {	
		
		if (errors.hasErrors()) {
			model.addAttribute("newAthlete", true);	
			return NEW_ATHLETE_FORM;
		} 
		//TODO Validar a user y password
		//TODO Validar athlete
		//TODO save user password para athlete
		 saveNewAtlete(athleteCommand);
		//Guardar o modificar atleta
		
		return LIST_ATHLETES;		
	}	
	
	
	
	

	private void saveNewAtlete(AthleteCommand athleteCommand) {
		String name= athleteCommand.getName();
		String userName = athleteCommand.getUserName();
		String surname = athleteCommand.getSurname();
		String dni = athleteCommand.getDni();
		String email = athleteCommand.getEmail();
		String type = athleteCommand.getType();
		String address= athleteCommand.getAddress();
		String comments = athleteCommand.getComments();
		String telephone = athleteCommand.getTelephone();
				
		//Date birth		
		int day = Integer.valueOf(athleteCommand.getDateBirthDay());
		int month = Integer.valueOf(athleteCommand.getDateBirthMonth());
		int year = Integer.valueOf(athleteCommand.getDateBirthYear());
		Calendar calendar = new GregorianCalendar(year, month, day);
		Date dateBirth = calendar.getTime();
		
		//Team
		Team team = userService.findTeam(athleteCommand.getIdTeam());
		
		userService.setNewAthlete(name, userName, surname, dni, email, type, address, comments, telephone, dateBirth, team);
		
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
		
		groupCommand.setIdTeam(team.getIdTeam());
		groupCommand.setName(team.getName());
		groupCommand.setType(team.getType());
		groupCommand.setDescription(team.getDescription());
		groupCommand.setCreateDate(team.getDateCreate());
		groupCommand.setCoachName(team.getCoach().getCompleteName());
		
		return groupCommand;
	}
	
	private void initAthleteModel(Model model, HttpSession session) {
		AthleteCommand athleteCommand = new AthleteCommand();
		model.addAttribute("athleteCommand", athleteCommand);
		model.addAttribute("newAthlete", true);				
		
		Coach  coach = (Coach) session.getAttribute("loggedUser");	
		List<Team> listTeams = userService.findTeamsByCoach(coach);
		model.addAttribute("listTeams", listTeams);
	}
}
