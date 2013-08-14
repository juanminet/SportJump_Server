package es.uma.sportjump.sjs.web.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.uma.sportjump.sjs.model.entities.Coach;
import es.uma.sportjump.sjs.web.controller.commands.GroupCommand;


@Controller
@SessionAttributes("groupCommand")
@RequestMapping("/action/admin")
public class AdminController {
	
	protected static String GROUPS_REDIRECT = "redirect:/action/admin/groups";	
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String home(Model model) {
		return "admin_groups";
		
	}	
	
	@RequestMapping(value={"/groups"})
	public String AdminGroups(Model model) {	
	
		return "admin_groups";		
	}	
	
	@RequestMapping(value={"/groups/new"})
	public String newGroup(Model model, HttpSession session) {		
		
		initGroupModel(model,session);	
		
		return "admin_groups_new";		
	}
	
	@RequestMapping(value={"/groups/save"}, method=RequestMethod.POST)
	public String saveGroup(@Valid GroupCommand groupCommand, BindingResult errors) {		
		
		if (errors.hasErrors()) {
			return "admin_groups_new";
		} 
		
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
	}
}
