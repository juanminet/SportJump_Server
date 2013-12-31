package es.uma.sportjump.sjs.dao.daos;

import java.util.List;

import es.uma.sportjump.sjs.model.entities.CalendarEvent;




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

	
}
