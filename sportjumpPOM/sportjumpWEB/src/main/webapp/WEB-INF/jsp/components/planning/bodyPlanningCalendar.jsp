<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- QUITAR -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>




</style>

<script>


	
$(document).ready( function() {
	
	var revertEvent;
	var calendar;
	var eventSelected;
	var eventToRemove;

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
	
	calendar = $('#calendar').fullCalendar({
		header: {
			left: 'today',
			center: 'title',
			right: 'prevYear,prev,next,nextYear'
		},
		editable: true,
		events:  CONTEXT_PATH + "/ajax/planning/group/" + ID_GROUP,
		droppable: true, // this allows things to be dropped onto the calendar !!!
		drop: function(date, allDay) { // this function is called when something is dropped
			createEvent($(this),date, allDay);			
		},
		eventDrop: function(event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view) {	
			modifyEvent(event,revertFunc);
		},
		eventClick: function(calEvent, jsEvent, view) {				
			showExercise(calEvent);
		},
	});
	
	function createEvent(element,date, allDay){
		// retrieve the dropped element's stored Event Object
		var originalEventObject = element.data('eventObject');
		var id = element.find(".id").text();
		var name = element.find(".name").text();
		
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
		
		
		if (isDateEventEmpty(date)){
			createEventAjax(data,copiedEventObject);
		}else{
			alert("<fmt:message key='planning.calendar.event.error.duplicated' />");
		}				 	
	}
	
	function modifyEvent(calEvent,revertFunc){
		eventSelected=calEvent;
		revertEvent = revertFunc;
		if (isEventDuplicated(calEvent)){
			eventToRemove = getEventDuplicated(calEvent);
			$('#modify_event_lbox').dialog('open');	
		}else{
			eventToRemove = null;
			modifyEventAjax(calEvent,revertFunc,false);	
		}	
	}	
	
	function showExercise(calEvent){
		eventSelected = calEvent;
		
		var trainingDiv = $('<div/>', {class : 'training_lbox_show'});
			 
		trainingDiv.append($('<label>').addClass("lbox_text_principal").append("<fmt:message key='planning.calendar.lbox.name'/>: "));	
		trainingDiv.append( calEvent.title);	
		trainingDiv.append($('<br/>'));
		trainingDiv.append($('<label>').addClass("lbox_text_principal").append("<fmt:message key='planning.calendar.lbox.date'/>: "));	
		trainingDiv.append(calEvent.start.toLocaleDateString());	
		trainingDiv.append($('<br/>'));
		trainingDiv.append($('<br/>'));
		trainingDiv.append($('<label>').addClass("lbox_text_principal").append("<fmt:message key='planning.calendar.lbox.training'/>: "));		
			
		showTrainingAjax(calEvent.id, trainingDiv);	
				
	}

	function createExerciseBlockDiv(block){
		var blockDiv = $('<div/>', {class : 'exercise_block_lbox_show'});
		
		blockDiv.append($('<label>').append(block.name))
				.append(" : ")
				.append($('<label>').append(block.description));
		
		var exerciseList = $('<ul/>' );
															
		
		$.each(block.listExercise, function(iExercise, exercise){			
			exerciseList.append($('<li>').append(exercise));
		});
		
		blockDiv.append(exerciseList);
		
		return blockDiv;
	}

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
	
	function getEventDuplicated(calEvent){		
		var result;
		
		var events = calendar.fullCalendar('clientEvents');	
		
		for (var i = 0; i < events.length; i++){
			var event = events[i];
			if ((calEvent.id  != event.id) && (calEvent.start.getTime() == event.start.getTime())){
				result = event;
				break;
			}
		}
		return result;
	}
	
	function isEventDuplicated(calEvent){		
		var result = false;
		
		var events = calendar.fullCalendar('clientEvents');	
		
		for (var i = 0; i < events.length; i++){
			var event = events[i];
			if ((calEvent.id  != event.id) && (calEvent.start.getTime() == event.start.getTime())){
				result = true;
				break;
			}
		}
		return result;
	}
	
	function isDateEventEmpty(date){		
		var result = true;
		
		var events = calendar.fullCalendar('clientEvents');	
		
		for (var i = 0; i < events.length; i++){
			var event = events[i];
			if ((date.getTime() == event.start.getTime())){
				result = false;
				break;
			}
		}
		return result;
	}
	
	/* Lightboxes */
	
	$("#show_training_lbox").dialog({
		autoOpen : false,
		resizable : false,
		draggable : false,
		width : 400,
		height: 400,
		modal : true,
		buttons : [ 
			{					
				text : "<fmt:message key='planning.calendar.lbox.button.close'/>",
				class:"lbox-button",
				click : function() {						
					$(this).dialog('close');
					$('#lbox_show_container').empty();
				}
			},
			{					
				text :"<fmt:message key='planning.calendar.lbox.button.remove'/>",
				class:"lbox-button",
				click : function() {
					removeEventAjax(eventSelected);					
					$(this).dialog('close');
				}
			}
		],
		open : function() {
			$('.ui-dialog-buttonset button').blur();
		}
	});
	
	
	$("#modify_event_lbox").dialog({
		autoOpen : false,
		resizable : false,
		draggable : false,
		width : 400,
		modal : true,
		buttons : [ 
			{					
				text : "<fmt:message key='planning.calendar.lbox.button.cancel'/>",
				class:"lbox-button",
				click : function() {		
					revertEvent();
					$(this).dialog('close');
				}
			},
			{					
				text :"<fmt:message key='planning.calendar.lbox.button.continue'/>",
				class:"lbox-button",
				click : function() {					
					modifyEventAjax(eventSelected,revertEvent,true);	
					$(this).dialog('close');
				}
			}
		],
		open : function() {
			$('.ui-dialog-buttonset button').blur();
		}
	}); 
	
	/* AJAX */
	
	function createEventAjax(data, copiedEventObject){
		var request = $.ajax({
			type: "POST",
			cache:false,
			url: CONTEXT_PATH + "/ajax/planning/save",
			data:data				  
		}); 
	 	
	 	request.done(function( idEvent ) {	
	 		copiedEventObject.id = idEvent;
	 		$('#calendar').fullCalendar('renderEvent', copiedEventObject, true); 	 		
		});
		 
		request.fail(function( jqXHR, textStatus ) {
			alert( "Request failed: " + textStatus );
		});
	}
	
	function showTrainingAjax(id, trainingDiv){
		$.ajax({
			url: CONTEXT_PATH + "/ajax/planning/" + id,
		  	type: 'GET',
		  	cache: false,				 
		  	success: function(data){
		  		$.each(data.listBlock, function(iBlock,block){				
					var divExerciseBlock = createExerciseBlockDiv(block);				
					trainingDiv.append(divExerciseBlock);
				});	
		  		
		  		$("#lbox_show_container").empty();				  		
		  		$("#lbox_show_container").append(trainingDiv);					
				$('#show_training_lbox').dialog('open');		
		  	},
		  	error: function(){alert("ajax error");}
	 	}); 
	}
	function modifyEventAjax(calEvent,revertFunc, removeEvent){		
		
		var data = new Object();
		data.idEvent = calEvent.id;
		data.idGroup = ID_GROUP;
		data.name = calEvent.title;
		data.date = calEvent.start;
		
		$.ajax({
			type: 'PUT',			
		  	cache: false,
		  	url: CONTEXT_PATH + "/ajax/planning/" + calEvent.id,
		  	contentType: "application/json",		  	
		  	data:JSON.stringify(data),
		  	 success: function(){
		  		 if (removeEvent){
		  			calendar.fullCalendar( 'removeEvents' , eventToRemove.id);	 
		  		 }
		  		  
		  		calendar.fullCalendar( 'updateEvent' , calEvent);  		
		  	},
		  	error: function(){
		  		revertFunc();
		  	} 
	 	});   
	}
	
	function removeEventAjax(calEvent){		
		$.ajax({
			url: CONTEXT_PATH + "/ajax/planning/" + calEvent.id,
		  	type: 'DELETE',
		  	cache: false,				 
		  	success: function(data){
		  		calendar.fullCalendar( 'removeEvents' , calEvent.id);  		
		  	},
		  	error: function(){alert("ajax error");}
	 	}); 
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
		background: none repeat scroll 0 0 #EF7900;
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
				</div>

				<div id='calendar'></div>

				<div style='clear: both'></div>
			</div>
		</div>      
      </div>
      
      
      <!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ LIGHTTBOXES /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->


<!-- lightbox for modify dialog -->
<div id="modify_event_lbox" class="lBox" title="<fmt:message key='planning.calendar.lbox.modify.tittle'/>">
	<!-- Mensaje -->
	<div class="cajaMensajeTop">
		<div class="cajaMensajeTopLeft"></div>
		<div class="cajaMensajeTopRight"></div>
	</div>
	<div class="mensaje dobleLinea fuenteFormLB">
		<fmt:message key="planning.calendar.lbox.modify.text" />
	</div>
	<!--mensaje-->
	<div class="cajaMensajePie">
		<div class="cajaMensajePieLeft"></div>
		<div class="cajaMensajePieRight"></div>
	</div>
	<!-- Mensaje -->	
	
</div>



<!-- lightbox for show exercises -->
<div id="show_training_lbox" class="lBox" title="<fmt:message key='planning.calendar.lbox.show.tittle'/>">
	<!-- Mensaje -->
	<div class="cajaMensajeTop">
		<div class="cajaMensajeTopLeft"></div>
		<div class="cajaMensajeTopRight"></div>
	</div>
	<div class="mensaje dobleLinea fuenteFormLB">
		<div class="single_row">
			<div id="lbox_show_container">
				
			</div>
		</div>
	</div>
	<!--mensaje-->
	<div class="cajaMensajePie">
		<div class="cajaMensajePieLeft"></div>
		<div class="cajaMensajePieRight"></div>
	</div>
	<!-- Mensaje -->	
	
</div>


<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ fin LIGHTTBOXES /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
</div>
