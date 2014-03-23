package es.uma.sportjump.sjs.dao.daos.impl.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import es.uma.sportjump.sjs.dao.daos.CalendarEventDao;
import es.uma.sportjump.sjs.model.entities.CalendarEvent;
import es.uma.sportjump.sjs.model.entities.Team;

@Repository("calendarEventDao")
public class CalendarEventDaoMockImpl implements CalendarEventDao {
	
	List<CalendarEvent> listCalendarEvent = new ArrayList<CalendarEvent>();

	@Override
	public void persistEvent(CalendarEvent calendarEvent) {
		if (calendarEvent != null){
			
			if (calendarEvent.getIdEvent() == null){
				listCalendarEvent.add(calendarEvent);
				calendarEvent.setIdEvent(Long.valueOf(listCalendarEvent.indexOf(calendarEvent)));
			}else{
				Long id = calendarEvent.getIdEvent();
				listCalendarEvent.set(id.intValue(), calendarEvent);
				calendarEvent.setIdEvent(id);
			}
		}
	}

	@Override
	public CalendarEvent getEventgById(Long id) {
		CalendarEvent res = null;
		for (CalendarEvent event : listCalendarEvent){
			if (id.equals(event.getIdEvent())){
				res = event;
				break;
			}
		}
		
		return res;
	}

	@Override
	public CalendarEvent getEventByDateAndTeam(Date date, Team team) {
		CalendarEvent res = null;
		for (CalendarEvent event : listCalendarEvent){
			if (date.equals(event.getEventDate()) && team.getIdTeam().equals(event.getTeam().getIdTeam())){
				res = event;
				break;
			}
		}
		
		return res;
	}

	@Override
	public List<CalendarEvent> getEventsByGroup(Long idTeam) {
		List<CalendarEvent> res = new ArrayList<CalendarEvent>();
		for (CalendarEvent event : listCalendarEvent){
			if (idTeam.equals(event.getTeam().getIdTeam())){
				res.add(event);
			}
		}
		return res;
	}

	@Override
	public void deleteEvent(CalendarEvent calendarEvent) {
		CalendarEvent eventToremove = null;
		for (CalendarEvent event : listCalendarEvent){
			if (calendarEvent.getIdEvent().equals(event.getIdEvent())){
				eventToremove = event;
				break;
			}
		}		
		
		if(eventToremove != null){
			listCalendarEvent.remove(eventToremove);
		}
	}
	
	@Override
	public void updateEvent(CalendarEvent calendarEventToUpdate) {
		CalendarEvent currentEvent = getCompleteEventgById(calendarEventToUpdate.getIdEvent());
		currentEvent.setEventDate(calendarEventToUpdate.getEventDate());		
	}

	@Override
	public CalendarEvent getCompleteEventgById(Long id) {
		return getEventgById(id);
	}
	
}
