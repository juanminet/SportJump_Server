package es.uma.sportjump.sjs.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.uma.sportjump.sjs.web.controller.beans.ExerciseWebBean;


@Controller
@RequestMapping("/rest/testservice")
public class TestServiceController {
	

	
	
//	@RequestMapping(value ="/{id}" ,  method = RequestMethod.DELETE)
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void deleteValue(@PathVariable("id") String id){		
//		
//		System.out.println("Test for DELETE Method. [id: " + id + "]");	
//	}
//	
//	@RequestMapping(value ="/{id}" ,  method = RequestMethod.PUT)
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void updateValue(@PathVariable("id") String id, @RequestBody Athlete athlete){		
//		
//		System.out.println("Test for PUT Method with JSON request body. [id: " + id + "],[athlete: " + athlete + "]");	
//	}
	
	@RequestMapping( method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public  @ResponseBody ExerciseWebBean getValue(){			
		
		System.out.println("Test for GET Method with JSON request body. [id " + 3 + "]");	
		
		ExerciseWebBean res = new ExerciseWebBean();
		res.setName("OK");
		return res;
	}
	
//	@RequestMapping(method = RequestMethod.POST)
//	@ResponseStatus(HttpStatus.CREATED)
//	public void createValue(@RequestBody String athlete){		
//		
//		System.out.println("Test for POST Method with JSON request body. [athlete: " + athlete + "]" );
//		userService.setNewAthlete(athlete, athlete);
//	}
//	
//	@RequestMapping(value ="/{id}" ,  method = RequestMethod.GET)
//	@ResponseStatus(HttpStatus.OK)
//	public @ResponseBody Athlete getValue(@PathVariable("id") Integer id){			
//		
//		System.out.println("Test for GET Method with JSON request body. [id " + id + "]");	
//		
//		Athlete athlete = userService.findAthlete(id);
//		
//		return athlete;
//	}
//	
//	@RequestMapping(value ="/methods/get/find/{name}" ,  method = RequestMethod.GET)
//	@ResponseStatus(HttpStatus.OK)
//	public @ResponseBody Boolean getValue(@PathVariable("name") String name){			
//		
//		System.out.println("Test for GET Method with JSON request body. [name " + name + "]");			
//		
//		return userService.existsAthlete(name, name);
//	}
	
	

	
}
