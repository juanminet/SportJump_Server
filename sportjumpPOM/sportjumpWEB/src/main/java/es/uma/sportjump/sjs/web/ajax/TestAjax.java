package es.uma.sportjump.sjs.web.ajax;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.uma.sportjump.sjs.model.entities.Athlete;


//AJAX implemented by Spring Rest and JQuery Ajax


@Controller
@RequestMapping("/test/ajax")
public class TestAjax{


	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void addUser(@RequestBody String athlete){			
		System.out.println("Test for POST Method with JSON request body. [athlete: " + athlete + "]" );		
	}
	
	@RequestMapping(value ="/{id}" ,  method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Athlete getValue(@PathVariable("id") Integer id){					
		System.out.println("Test for GET Method with JSON request body. [id " + id + "]");	
		Athlete athlete = new Athlete();		
		
		return athlete;
	}
	
	@RequestMapping(value ="/find/{name}" ,  method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody boolean existsUser(@PathVariable("name") String name){				
		System.out.println("Test for GET Method with JSON request body. [name " + name + "]");	
		return true;
	}
}
