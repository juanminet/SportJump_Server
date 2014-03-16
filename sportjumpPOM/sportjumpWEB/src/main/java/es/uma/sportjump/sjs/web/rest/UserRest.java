package es.uma.sportjump.sjs.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.uma.sportjump.sjs.model.entities.Athlete;
import es.uma.sportjump.sjs.service.services.UserService;
import es.uma.sportjump.sjs.web.rest.beans.AthleteRestBean;
import es.uma.sportjump.sjs.web.rest.beans.mappings.UserRestBeanMapping;


@Controller
@RequestMapping("/rest/user")
public class UserRest {
	
	@Autowired
	UserService userService;	

	
	@RequestMapping(value="/athlete/{userName}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public  @ResponseBody AthleteRestBean getAthlete(@PathVariable("userName") String userName){			
		
		Athlete athlete = userService.findAthletesByUserName(userName);
		
		System.out.println("Test for GET Method with JSON request body. [userName " + userName + "]");	
		

		return UserRestBeanMapping.athleteRestBeanFromAthleteEntity(athlete);
	}
}