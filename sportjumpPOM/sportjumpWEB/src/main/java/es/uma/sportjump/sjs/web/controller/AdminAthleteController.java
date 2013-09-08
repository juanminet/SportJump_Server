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
import es.uma.sportjump.sjs.service.services.AuthService;
import es.uma.sportjump.sjs.service.services.UserService;
import es.uma.sportjump.sjs.web.constants.AuthConstants;
import es.uma.sportjump.sjs.web.controller.commands.AthleteCommand;
import es.uma.sportjump.sjs.web.controller.validation.AthleteValidator;


@Controller
@SessionAttributes({"athleteCommand", "listTeams"})
@RequestMapping("/action/admin")
public class AdminAthleteController {
	
	

	@Autowired
	UserService userService;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	AthleteValidator athleteValidator;

	
	protected static final String LIST_ATHLETES = "admin_athletes";
	protected static final String NEW_ATHLETE_FORM = "admin_athletes_new";
	protected static final String ATHLETES_REDIRECT = "redirect:/action/admin/athletes";
		
	
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


}
