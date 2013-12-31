package es.uma.sportjump.sjs.service.services;

import java.util.Date;
import java.util.List;

import es.uma.sportjump.sjs.model.entities.CalendarEvent;
import es.uma.sportjump.sjs.model.entities.Team;
import es.uma.sportjump.sjs.model.entities.Training;



public interface CalendarService {
	
	/**
	 *  Set new calendar event in Calendar
	 * @param eventDate
	 * @param training
	 * @param team
	 * @param periodicEvent
	 * @return the event created
	 */
	 
	public CalendarEvent setNewEvent(Date eventDate, Training training, Team team, boolean periodicEvent);
	
	
	/**
	 * Update a calendar event got previously
	 * @param event
	 */
	public void updateEvent(CalendarEvent event);
	
	
	/**
	 * find a calendar event with its id given by parameter
	 * @param idEvent
	 * @return
	 */
	public CalendarEvent findEvent(Long idEvent);
	
	/**
	 * Find all events belong to a team
	 * @param team
	 * @return
	 */
	public List<CalendarEvent> findAllEventByTeam(Team team);
	
	/**
	 * Remove an calendar event
	 * @param event
	 */
	public void removeEvent(CalendarEvent event);
	

}
