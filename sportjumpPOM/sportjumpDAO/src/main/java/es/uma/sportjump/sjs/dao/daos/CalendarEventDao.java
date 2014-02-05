package es.uma.sportjump.sjs.dao.daos;

import java.util.Date;
import java.util.List;

import es.uma.sportjump.sjs.model.entities.CalendarEvent;
import es.uma.sportjump.sjs.model.entities.Team;




public interface CalendarEventDao {
	
	
	/**
	 * Persist calendar event
	 * @param event
	 */
	public void persistEvent(CalendarEvent event);	
	
	/**
	 * Get LAZY calendar event by id given by parameter 
	 * @param id
	 * @return
	 */
	public CalendarEvent getEventgById(Long id);
	
	/**
	 * Get EAGER calendar event by id given by parameter 
	 * @param id
	 * @return
	 */
	public CalendarEvent getCompleteEventgById(Long id);
	
	
	/**
	 * Find an event for date and team given by parameter
	 * @param date
	 * @param team
	 * @return
	 */
	public CalendarEvent getEventByDateAndTeam(Date date, Team team);
	
	
	/**
	 * Get list of all events belong to a group given by parameter 
	 * @param id
	 * @return the list with all events
	 */
	public List<CalendarEvent> getEventsByGroup(Long idTeam);
	
	/**
	 * Remove event
	 * @param id
	 */
	public void deleteEvent(CalendarEvent event);

	/**
	 * Update event given by parameter
	 * @param calendarEventToUpdate
	 */
	public void updateEvent(CalendarEvent calendarEventToUpdate);

	
}
