<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- QUITAR -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>




</style>

<script>


	
$(document).ready( function() {

	/* initialize the external events
	-----------------------------------------------------------------*/

	$('#external-events div.external-event').each(function() {
	
		// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
		// it doesn't need to have a start or end
		var eventObject = {
			title: $.trim($(this).text()) // use the element's text as the event title
		};
		
		// store the Event Object in the DOM element so we can get to it later
		$(this).data('eventObject', eventObject);
		
		// make the event draggable using jQuery UI
		$(this).draggable({
			zIndex: 999,
			revert: true,      // will cause the event to go back to its
			revertDuration: 0  //  original position after the drag
		});
		
	});


	/* initialize the calendar
	-----------------------------------------------------------------*/
	
	$('#calendar').fullCalendar({
		header: {
			left: 'today',
			center: 'title',
			right: 'prevYear,prev,next,nextYear'
		},
		editable: true,
		events:  CONTEXT_PATH + "/ajax/planning/group/" + ID_GROUP,
		droppable: true, // this allows things to be dropped onto the calendar !!!
		drop: function(date, allDay) { // this function is called when something is dropped
		
			// retrieve the dropped element's stored Event Object
			var originalEventObject = $(this).data('eventObject');
			var id = $(this).find(".id").text();
			var name = $(this).find(".name").text();
			
			// we need to copy it, so that multiple events don't have a reference to the same object
			var copiedEventObject = $.extend({}, originalEventObject);
			
			// assign it the date that was reported
			copiedEventObject.id = id;
			copiedEventObject.title = name;
			copiedEventObject.start = date;
			copiedEventObject.allDay = allDay;
			
			//var data = {"id" : id , "name" : name , "date" : $.date(date) };
			
			var data = new Object();
			data.idTraining = id;
			data.idGroup = ID_GROUP;
			data.name = name;
			data.date = date;
			
			// render the event on the calendar
			// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
			$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
			
			// is the "remove after drop" checkbox checked?
			if ($('#drop-remove').is(':checked')) {
				// if so, remove the element from the "Draggable Events" list
				$(this).remove();
			}
			
			alert(data);
			
			$.ajax({
				  type: "POST",
				  url: CONTEXT_PATH + "/ajax/planning/save",
				  data:data
			});
			
		},
		eventDrop: function(event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view) {
			alert($(this).text());
			alert(event.id);
		},
	});

	$.date = function (dateObject) {
	       // alert(dateObject);
	        var d = new Date(dateObject);
	        var day = d.getDate();
	        var month = d.getMonth();
	        var year = d.getFullYear();
	        var hours = d.getHours();
	        var minutes = d.getMinutes();
	        var seconds = d.getSeconds()
	        var date = day + "/" + month + "/" + year + " " + hours + ((minutes < 10) ? ":0" : ":") + minutes + ((seconds < 10) ? ":0" : ":") + seconds;

	        return date;
	    }
	  	
}); 	

	
</script>

  <script>
 	 var CONTEXT_PATH = "${pageContext.request.contextPath}";
 	 var ID_GROUP = "${idGroup}";
  </script>

<style>
		
	#wrap {
		width: 100%;
		margin: 0 auto;
		}
		
	#external-events {
		float: left;
		width: 15%;
		padding: 0 10px;
		border: 1px solid #ccc;
		background: #eee;
		text-align: left;
		}
		
	#external-events h4 {
		font-size: 16px;
		margin-top: 0;
		padding-top: 1em;
		}
		
	.external-event { /* try to mimick the look of a real event */
		margin: 10px 0;
		padding: 2px 4px;
		background: #646464;
		color: #fff;
		font-size: .85em;
		cursor: pointer;
		}
		
	#external-events p {
		margin: 1.5em 0;
		font-size: 11px;
		color: #666;
		}
		
	#external-events p input {
		margin: 0;
		vertical-align: middle;
		}

	#calendar {
		float: right;
		width: 80%;
		}
		
		.fc-event{
			background: #EF7900;
			border: 1px solid #EF7900;
		}

</style>
    
<div id ="body_home">
	<div id = "body_home_container" >			
	    <div class="caja">
	    
	   		<div class="subcaja">
		 		<h1><fmt:message key="planning.calendar.tittle" /></h1>
		 		<a class="button" href="${pageContext.request.contextPath}/action/planning/group/list"><span><fmt:message key="planning.calendar.button.back" /></span></a>
		    </div>

			<div id='wrap'>

				<div id='external-events'>
					<h4><fmt:message key="planning.calendar.entrenamientos" /></h4>
					<c:forEach items="${trainingDayList}" var="training">
						<div class='external-event'>
							<div class="name">${training.name}</div>
							<div class="id" hidden="true">${training.idTraining}</div>
						</div>
					</c:forEach>
					<p>
						<input type='checkbox' id='drop-remove' /> <label
							for='drop-remove'><fmt:message key="planning.calendar.external.check.periodic" /></label>
					</p>
				</div>

				<div id='calendar'></div>

				<div style='clear: both'></div>
			</div>
		</div>      
      </div>
</div>
