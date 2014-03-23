package es.uma.sportjump.sjs.web.beans.mappings;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import es.uma.sportjump.sjs.model.entities.CalendarEvent;
import es.uma.sportjump.sjs.web.beans.EventCalendarBean;
import es.uma.sportjump.sjs.web.beans.EventCalendarCompleteBean;

public class CalendarEventBeanMapping {

	public static List<EventCalendarBean> fillEvents(List<CalendarEvent> calendarEventList) {

		List<EventCalendarBean> result = new ArrayList<EventCalendarBean>();
		for(CalendarEvent event : calendarEventList){
			EventCalendarBean eventCalendarJSON = new EventCalendarBean();
			eventCalendarJSON.setId(event.getIdEvent());
			
			
			SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
			
			
			eventCalendarJSON.setStart(sdf.format(event.getEventDate()));
			eventCalendarJSON.setTitle(event.getTraining().getName());
			
			result.add(eventCalendarJSON);
		}
		return result;
	}
	
	public static List<EventCalendarCompleteBean> fillCompleteEvents(List<CalendarEvent> calendarEventList) {

		List<EventCalendarCompleteBean> result = new ArrayList<EventCalendarCompleteBean>();
		for(CalendarEvent event : calendarEventList){
			EventCalendarCompleteBean eventCalendarJSON = new EventCalendarCompleteBean();
			eventCalendarJSON.setId(event.getIdEvent());
			
			
			SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
			
			
			eventCalendarJSON.setStart(sdf.format(event.getEventDate()));
			eventCalendarJSON.setTitle(event.getTraining().getName());
			
			eventCalendarJSON.setTraining(TrainingBeanMapping.fillTrainingBean(event.getTraining()));
			
			result.add(eventCalendarJSON);
		}
		return result;
	}
}
