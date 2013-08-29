package es.uma.sportjump.sjs.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
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
import es.uma.sportjump.sjs.web.constants.AuthConstants;
import es.uma.sportjump.sjs.web.controller.beans.RowAdminTeam;
import es.uma.sportjump.sjs.web.controller.commands.AthleteCommand;
import es.uma.sportjump.sjs.web.controller.commands.GroupCommand;
import es.uma.sportjump.sjs.web.controller.commands.ProfileCommand;
import es.uma.sportjump.sjs.web.controller.validation.AthleteValidator;
import es.uma.sportjump.sjs.web.controller.validation.ProfileValidator;


@Controller
@SessionAttributes({"groupCommand", "athleteCommand", "listTeams"})
@RequestMapping("/action/admin")
public class AdminController {
	
	

	@Autowired
	UserService userService;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	AthleteValidator athleteValidator;
	
	@Autowired
	ProfileValidator profileValidator;
	
	
	
	protected static final String LIST_GROUPS = "admin_groups";	
	protected static final String LIST_ATHLETES = "admin_athletes";
	protected static final String NEW_GROUP_FORM = "admin_groups_new";
	protected static final String NEW_ATHLETE_FORM = "admin_athletes_new";
	protected static final String ADMIN_PROFILE = "admin_profile";
	protected static final String GROUPS_REDIRECT = "redirect:/action/admin/groups";	
	protected static final String ATHLETES_REDIRECT = "redirect:/action/admin/athletes";	
	protected static final String ADMIN_PROFILE_REDIRECT = "redirect:/action/admin/profile";
	
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String home(Model model,HttpSession session) {
		return adminGroups(model,session);
		
	}	
	
	@RequestMapping(value={"/groups"})
	public String adminGroups(Model model, HttpSession session) {
		
		Coach  coach = (Coach) session.getAttribute("loggedUser");	
		
		List<RowAdminTeam> listTeams = fillAdminTeams(coach);		
		
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
	
	private List<RowAdminTeam> fillAdminTeams(Coach coach) {
		List<RowAdminTeam> listAdminTeams = new ArrayList<RowAdminTeam>();
		List<Team> listTeams = userService.findTeamsByCoach(coach);
		
		for(Team team : listTeams){
			RowAdminTeam row = new RowAdminTeam();
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
	
 /*----------------------------------------------------------------------------------------------------------------------------------------*/		
 /*----------------------------------------------------          ATHLETES          --------------------------------------------------------*/		
 /*----------------------------------------------------------------------------------------------------------------------------------------*/		

	@RequestMapping(value={"/athletes"})
	public String adminAthletes(Model model, HttpSession session) {	
		Coach  coach = (Coach) session.getAttribute("loggedUser");	
		
		List<Athlete> listAthletes = userService.findAthletesFromCoach(coach);		
	
		model.addAttribute("listAthletes", listAthletes);
		
		return LIST_ATHLETES;		
	}	
	
	
	@RequestMapping(value={"/athletes/new"})
	public String newAthlete(Model model, HttpSession session) {	
		
		initAthleteModel(model,session);
		
		
		return NEW_ATHLETE_FORM;		
	}	
	
	
	@RequestMapping(value={"/athletes/{idAthlete}"}, method=RequestMethod.GET)
	public String getAthlete(@PathVariable("idAthlete") Long idAthlete, Model model, HttpSession session) {		
			
		Athlete athlete = userService.findAthlete(idAthlete);
				
		AthleteCommand athleteCommand = createAthleteCommand(athlete);
		
		model.addAttribute("athleteCommand", athleteCommand);
		
		return NEW_ATHLETE_FORM;		
	}
	
	

	@RequestMapping(value={"/athletes/save"}, method=RequestMethod.POST)
	public String saveAthlete(@Valid AthleteCommand athleteCommand, BindingResult errors, Model model, HttpSession session) {	
					
		Long idUser = athleteCommand.getIdUser();
		boolean isNew = (idUser == null);
		
		
		if(isNew){
			athleteValidator.checkAthleteNew(athleteCommand, model, errors);
			if (errors.hasErrors()) {
				model.addAttribute("newAthlete", true);	
				return NEW_ATHLETE_FORM;
			}		
		}else{
			Athlete athlete = userService.findAthlete(idUser);
			athleteValidator.checkAthleteModified(athleteCommand,athlete, model, errors);
			if (errors.hasErrors()) {
				model.addAttribute("newAthlete", true);	
				return NEW_ATHLETE_FORM;
			} 
		}		
		
		
		
		if (isNew){	//Create			
			saveNewAtlete(athleteCommand);
		}else{ //update	
			Athlete athlete = userService.findAthlete(idUser);
			updateAthlete(athleteCommand,athlete);			
		}		 
		
		return ATHLETES_REDIRECT;		
	}	

	@RequestMapping(value={"/athletes/remove/{idUser}"}, method=RequestMethod.GET)
	public String removeAthlete(@PathVariable("idUser") Long idUser, Model model, HttpSession session) {		
			
		Athlete  athlete = userService.findAthlete(idUser);
		
		removeAthlete(athlete);
			
		return ATHLETES_REDIRECT;		
	}

	
	
	private void removeAthlete(Athlete athlete) {
		if(authService.existUser(athlete.getUserName())){
			authService.removeUser(athlete.getUserName());
		}
		
		if (athlete != null){
			userService.removeAthlete(athlete);
		}
	}

	private void initAthleteModel(Model model, HttpSession session) {
		AthleteCommand athleteCommand = new AthleteCommand();
		model.addAttribute("athleteCommand", athleteCommand);
		model.addAttribute("newAthlete", true);				
		
		Coach  coach = (Coach) session.getAttribute("loggedUser");	
		List<Team> listTeams = userService.findTeamsByCoach(coach);
		model.addAttribute("listTeams", listTeams);
	}
	
	private AthleteCommand createAthleteCommand(Athlete athlete) {
		AthleteCommand athleteCommand = new AthleteCommand();
		
		athleteCommand.setIdUser(athlete.getIdUser());
		athleteCommand.setUserName(athlete.getUserName());
		athleteCommand.setName(athlete.getName());
		athleteCommand.setSurname(athlete.getSurname());
		athleteCommand.setDni(athlete.getDni());
		athleteCommand.setEmail(athlete.getEmail());
		athleteCommand.setType(athlete.getType());
		athleteCommand.setAddress(athlete.getAddress());
		athleteCommand.setComments(athlete.getComments());
		athleteCommand.setTelephone(athlete.getTelephone());
		
		//Date
		Calendar cal = Calendar.getInstance();
		cal.setTime(athlete.getDateBirth());
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);	
		athleteCommand.setDateBirthDay(String.valueOf(day));
		athleteCommand.setDateBirthMonth(String.valueOf(month));
		athleteCommand.setDateBirthYear(String.valueOf(year));
		athleteCommand.setDateBirth(athlete.getDateBirth());
		
		//Team
		athleteCommand.setIdTeam(athlete.getTeam().getIdTeam());
		athleteCommand.setNameTeam(athlete.getTeam().getName());
		
		return athleteCommand;
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
		
		//Auth
		 authService.addUser(userName, athleteCommand.getPassword(), AuthConstants.ROLE_ATHLETE);
	}
	

	
	private void updateAthlete(AthleteCommand athleteCommand, Athlete athlete) {		
		//update athlete authorization		
		authService.updateUser(athlete.getUserName(), athleteCommand.getUserName(), athleteCommand.getPassword());
		
		//update athlete data		
		userService.updateAthlete(fillAthlete(athlete,athleteCommand));
	}

	
	
	private Athlete fillAthlete(Athlete athlete, AthleteCommand athleteCommand) {
		
		
		athlete.setUserName(athleteCommand.getUserName());
		athlete.setName(athleteCommand.getName());
		athlete.setSurname(athleteCommand.getSurname());
		athlete.setDni(athleteCommand.getDni());
		athlete.setEmail(athleteCommand.getEmail());
		athlete.setType(athleteCommand.getType());
		athlete.setAddress(athleteCommand.getAddress());
		athlete.setComments(athleteCommand.getComments());
		athlete.setTelephone(athleteCommand.getTelephone());
		
		//Date birth		
		int day = Integer.valueOf(athleteCommand.getDateBirthDay());
		int month = Integer.valueOf(athleteCommand.getDateBirthMonth());
		int year = Integer.valueOf(athleteCommand.getDateBirthYear());
		Calendar calendar = new GregorianCalendar(year, month, day);
		athlete.setDateBirth(calendar.getTime());
		
		//Team
		Team team = userService.findTeam(athleteCommand.getIdTeam());				
		athlete.setTeam(team);
		
		return athlete;
	}

/*----------------------------------------------------------------------------------------------------------------------------------------*/		
 /*----------------------------------------------------          PROFILE          --------------------------------------------------------*/		
 /*----------------------------------------------------------------------------------------------------------------------------------------*/	
	
	@RequestMapping(value={"/profile"})
	public String AdminProfile(Model model, HttpSession session) {	
		
		initProfileModel(model,session);
		
		return ADMIN_PROFILE;		
	}
	
	@RequestMapping(value={"/profile/save"}, method=RequestMethod.POST)
	public String saveProfile(@Valid ProfileCommand profileCommand, BindingResult errors, Model model, HttpSession session) {	
					
		Long idUser = profileCommand.getIdUser();
				
		Coach  coach = (Coach) session.getAttribute("loggedUser");
		profileValidator.checkUserProfile(profileCommand, coach, errors);
		if (errors.hasErrors()) {
			model.addAttribute("errorProfile", true);	
			return ADMIN_PROFILE;
		} 

		updateProfile(profileCommand,coach);			
				 
		
		return ADMIN_PROFILE_REDIRECT;		
	}	


	private void initProfileModel(Model model, HttpSession session) {
		Coach  coach = (Coach) session.getAttribute("loggedUser");
		
		ProfileCommand profileCommand = createProfileCommand(coach);
		
		model.addAttribute("profileCommand", profileCommand);		
	}	
	
	
	private ProfileCommand createProfileCommand(Coach coach) {
		
		
		ProfileCommand profileCommand = new AthleteCommand();
		
		profileCommand.setIdUser(coach.getIdUser());
		profileCommand.setUserName(coach.getUserName());
		profileCommand.setName(coach.getName());
		profileCommand.setSurname(coach.getSurname());
		profileCommand.setDni(coach.getDni());
		profileCommand.setEmail(coach.getEmail());
		profileCommand.setType(coach.getType());
		profileCommand.setAddress(coach.getAddress());
		profileCommand.setComments(coach.getComments());
		profileCommand.setTelephone(coach.getTelephone());
		profileCommand.setPassword("");
		
		//Date
		Date dateBirthday = coach.getDateBirth();
		
		if (dateBirthday != null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(coach.getDateBirth());
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);	
			profileCommand.setDateBirthDay(String.valueOf(day));
			profileCommand.setDateBirthMonth(String.valueOf(month));
			profileCommand.setDateBirthYear(String.valueOf(year));
			profileCommand.setDateBirth(coach.getDateBirth());
		}
		
		return profileCommand;
	}
	
	private void updateProfile(ProfileCommand profileCommand, Coach coach) {
		
		//update athlete authorization		
		authService.updateUser(coach.getUserName(), profileCommand.getUserName(), profileCommand.getPassword());
		
		//update athlete data		
		userService.updateCoach(fillCoach(coach, profileCommand));
	}

	private Coach fillCoach(Coach coach, ProfileCommand profileCommand) {

		
		coach.setUserName(profileCommand.getUserName());
		coach.setName(profileCommand.getName());
		coach.setSurname(profileCommand.getSurname());
		coach.setDni(profileCommand.getDni());
		coach.setEmail(profileCommand.getEmail());
		coach.setType(profileCommand.getType());
		coach.setAddress(profileCommand.getAddress());
		coach.setComments(profileCommand.getComments());
		coach.setTelephone(profileCommand.getTelephone());
		
		//Date birth	
		if (!StringUtils.isEmpty(profileCommand.getDateBirthDay())  &&
			!StringUtils.isEmpty(profileCommand.getDateBirthMonth()) &&
			!StringUtils.isEmpty(profileCommand.getDateBirthDay())){
			
			int day = Integer.valueOf(profileCommand.getDateBirthYear());
			int month = Integer.valueOf(profileCommand.getDateBirthMonth());
			int year = Integer.valueOf(profileCommand.getDateBirthYear());
			Calendar calendar = new GregorianCalendar(year, month, day);
			coach.setDateBirth(calendar.getTime());
		}
		return coach;
	}
	
}
