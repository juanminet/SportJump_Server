
	
$(document).ready( function() {
	
	var revertEvent;
	var calendar;
	var eventSelected;
	var eventToRemove;
	var dataElem;

	$('#external-events div.external-event').each(function() {
		
		var eventObject = {
			title: $.trim($(this).text()) 
		};
		
		
		$(this).data('eventObject', eventObject);
		
		
		$(this).draggable({
			zIndex: 999,
			revert: true,      
			revertDuration: 0 
		});
		
	});
	
	$('.external-event').click(function() {
		
		var calEvent = {
			title: $(this).find('.name').text(),
			id: $(this).find('.id').text()
		};
		
		showTraining(calEvent);	
	});
	
	
    function isElemOverDiv(event, div) {
        var divElem = $('#' + div);

        var divOffset = divElem.offset();

        var x1 = divOffset.left;
        var x2 = divOffset.left + divElem.outerWidth(true);
        var y1 = divOffset.top;
        var y2 = divOffset.top + divElem.outerHeight(true);

        if (event.pageX >= x1 && event.pageX <= x2 &&
        		event.pageY >= y1 && event.pageY <= y2) {
            return true;
        }
        return false;
    }


	calendar = $('#calendar').fullCalendar({
		header: {
			left: 'today',
			center: 'title',
			right: 'prevYear,prev,next,nextYear'
		},
		editable: true,
		events:  CONTEXT_PATH + "/ajax/planning/group/" + ID_GROUP,
		droppable: true, 
		dragRevertDuration : 0,
		drop: function(date, allDay) { // this function is called when an out of calendar element is dropped
			createEvent($(this),date, allDay);			
		},
		eventDrop: function(event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view) {	// this function is called when a calendar element is dropped
			modifyEvent(event,revertFunc);			
		},
		eventClick: function(calEvent, jsEvent, view) {				
			showEvent(calEvent);			
		},
		eventDragStop: function (event, jsEvent, ui, view) {			
		    if (isElemOverDiv(jsEvent,'calendarEventTrash')) {
		    	removeEvent(event);		        
		    }
		}
	});
	
	$('.fc-header-left').prepend('<span class="fc-button fc-button-today"><div id="calendarEventTrash" class="calendar-trash"><img class="calendar-trash-img" src="' + CONTEXT_PATH + '/resources/img/trash.png"></img></div></span>');
	
	
	function createEvent(element,date, allDay){
		// retrieve event object selected
		var originalEventObject = element.data('eventObject');
		var id = element.find(".id").text();
		var name = element.find(".name").text();
		
		// we need to copy it for web visualization
		var dataEvent = $.extend({}, originalEventObject);
		dataEvent.id = id;
		dataEvent.title = name;
		dataEvent.start = date;
		dataEvent.allDay = allDay;		
		
		//create data for json to backend
		var data = new Object();
		data.idTraining = id;
		data.idGroup = ID_GROUP;
		data.name = name;
		data.date = date;	
		
		if (isDateEventEmpty(date)){
			createEventAjax(data,dataEvent, null);
		}else{
			eventToRemove = getEventDuplicated(dataEvent);
			eventSelected = dataEvent;
			dataElem = data;
			$('#create_event_lbox').dialog('open');				
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
	
	function showEvent(calEvent){
		eventSelected = calEvent;
		
		var trainingDiv = $('<div/>', {class : 'training_lbox_show'});
			 
		trainingDiv.append($('<label>').addClass("lbox_text_principal").append(MSG_LBOX_NAME));	
		trainingDiv.append( calEvent.title);	
		trainingDiv.append($('<br/>'));
	
		trainingDiv.append($('<label>').addClass("lbox_text_principal").append(MSG_LBOX_DATE));	
		trainingDiv.append(calEvent.start.toLocaleDateString());	
		
		trainingDiv.append($('<br/>'));
		trainingDiv.append($('<br/>'));
		trainingDiv.append($('<label>').addClass("lbox_text_principal").append(MSG_LBOX_TRAINING));		
			
		showEventAjax(calEvent.id, trainingDiv);	
				
	}
	
	function showTraining(calEvent){
		eventSelected = calEvent;
		
		var trainingDiv = $('<div/>', {class : 'training_lbox_show'});
			 
		trainingDiv.append($('<label>').addClass("lbox_text_principal").append(MSG_LBOX_NAME));	
		trainingDiv.append( calEvent.title);
	
		trainingDiv.append($('<br/>'));
		trainingDiv.append($('<br/>'));
		trainingDiv.append($('<label>').addClass("lbox_text_principal").append(MSG_LBOX_TRAINING));		
			
		showTrainingAjax(calEvent.id, trainingDiv);	
				
	}
	
	function removeEvent(calEvent){
		eventSelected = calEvent;
		$('#remove_event_lbox').dialog('open');
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
	
	$("#remove_event_lbox").dialog({
		autoOpen : false,
		resizable : false,
		draggable : false,
		width : 400,		
		modal : true,
		buttons : [ 
			{					
				text : MSG_LBOX_BUTTON_NO,
				class:"lbox-button",
				click : function() {						
					$(this).dialog('close');
					$('#lbox_show_container').empty();
				}
			},
			{					
				text : MSG_LBOX_BUTTON_YES,
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
	
	$("#show_training_lbox").dialog({
		autoOpen : false,
		resizable : false,
		draggable : false,
		width : 400,
		height: 400,
		modal : true,
		buttons : [ 
			{					
				text : MSG_LBOX_BUTTON_CLOSE,
				class:"lbox-button",
				click : function() {						
					$(this).dialog('close');
					$('#lbox_show_container').empty();
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
				text : MSG_LBOX_BUTTON_NO,
				class:"lbox-button",
				click : function() {		
					revertEvent();
					$(this).dialog('close');
				}
			},
			{					
				text : MSG_LBOX_BUTTON_YES,
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
	
	$("#create_event_lbox").dialog({
		autoOpen : false,
		resizable : false,
		draggable : false,
		width : 400,
		modal : true,
		buttons : [ 
			{					
				text : MSG_LBOX_BUTTON_NO,
				class:"lbox-button",
				click : function() {					
					$(this).dialog('close');
				}
			},
			{					
				text : MSG_LBOX_BUTTON_YES,
				class:"lbox-button",
				click : function() {					
					createEventAjax(dataElem,eventSelected,eventToRemove);	
					$(this).dialog('close');
				}
			}
		],
		open : function() {
			$('.ui-dialog-buttonset button').blur();
		}
	});
	
	$("#alert_event_lbox").dialog({
		autoOpen : false,
		resizable : false,
		draggable : false,
		width : 400,
		modal : true,
		buttons : [ 
			{					
				text : MSG_LBOX_BUTTON_CLOSE,
				class:"lbox-button",
				click : function() {						
					$(this).dialog('close');					
				}
			}
		],
		open : function() {
			$('.ui-dialog-buttonset button').blur();
		}
	});
	
	
	/* AJAX */
	
	function createEventAjax(data, eventCalendar, eventToRemove){
		var request = $.ajax({
			type: "POST",
			cache:false,
			url: CONTEXT_PATH + "/ajax/planning/save",
			data:data				  
		}); 
	 	
	 	request.done(function( idEvent ) {	
	 		if (eventToRemove != null){
	  			calendar.fullCalendar( 'removeEvents' , eventToRemove.id);	 
	  		 }
	 		eventCalendar.id = idEvent;
	 		$('#calendar').fullCalendar('renderEvent', eventCalendar, true); 	 		
		});
		 
		request.fail(function( jqXHR, textStatus ) {
			$('#alert_event_lbox_message').text("Request failed: " + textStatus );
			$('#alert_event_lbox').dialog('open');
		});
	}
	
	function showEventAjax(id, trainingDiv){
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
		  	error: function(jqXHR, textStatus ){
		  		$('#alert_event_lbox_message').text("Request failed: " + textStatus );
		  		$('#alert_event_lbox').dialog('open');
		  	}
	 	}); 
	}
	
	function showTrainingAjax(id, trainingDiv){
		$.ajax({
			url: CONTEXT_PATH + "/ajax/training/day/" + id,
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
		  	error: function(jqXHR, textStatus ){
		  		$('#alert_event_lbox_message').text("Request failed: " + textStatus );
		  		$('#alert_event_lbox').dialog('open');
		  	}
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
		  	error: function(jqXHR, textStatus ){
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
		  	error: function(jqXHR, textStatus ){
		  		$('#alert_event_lbox_message').text("Request failed: " + textStatus );
				$('#alert_event_lbox').dialog('open');
			}
	 	}); 
	}
	
}); 	






	