package es.uma.sportjump.sjs.web.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.service.services.AuthService;
import es.uma.sportjump.sjs.service.services.UserService;
import es.uma.sportjump.sjs.web.controller.commands.AthleteCommand;
import es.uma.sportjump.sjs.web.controller.commands.ProfileCommand;
import es.uma.sportjump.sjs.web.controller.validation.ProfileValidator;


@Controller
@RequestMapping("/action/admin/profile")
public class ProfileController {	
	

	@Autowired
	UserService userService;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	private ProfileValidator profileValidator;
	

	protected static final String ADMIN_PROFILE = "admin_profile";	
	protected static final String ADMIN_PROFILE_REDIRECT = "redirect:/action/admin/profile";
	protected static final String LOGOUT = "redirect:/logout";
	
		
	@RequestMapping( method=RequestMethod.GET)
	public String AdminProfile(Model model, HttpSession session) {	
		
		initProfileModel(model,session);
		
		return ADMIN_PROFILE;		
	}
	
	@RequestMapping(value={"/save"}, method=RequestMethod.POST)
	public String saveProfile(@Valid ProfileCommand profileCommand, BindingResult errors, Model model, HttpSession session) {	
					
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
			int month = cal.get(Calendar.MONTH) + 1;
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
		Date dateBirth = getDate(profileCommand);
		if (dateBirth != null){
			coach.setDateBirth(dateBirth);
		}
		return coach;
	}
	
	private Date getDate(ProfileCommand profileCommand){
		Date resDate = null;
		if (!StringUtils.isEmpty(profileCommand.getDateBirthDay())  &&
		!StringUtils.isEmpty(profileCommand.getDateBirthMonth()) &&
		!StringUtils.isEmpty(profileCommand.getDateBirthDay())){
		
			int day = Integer.valueOf(profileCommand.getDateBirthDay());
			int month = Integer.valueOf(profileCommand.getDateBirthMonth()) - 1;
			int year = Integer.valueOf(profileCommand.getDateBirthYear());
			Calendar calendar = new GregorianCalendar(year, month, day);
			resDate = calendar.getTime();
		}
		
		return resDate;
	}
	
	@RequestMapping(value={"/remove/{idCoach}"}, method=RequestMethod.GET)
	public String removeGroup(@PathVariable("idCoach") Long idCoach, Model model, HttpSession session) {		
			
		Coach coach = userService.findCoach(idCoach);
			
		if (userService != null){
			userService.removeCoach(coach);
		}
		
		//TODO from authentication
		
		return LOGOUT;		
	}
}
