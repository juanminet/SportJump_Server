var idToRemove;
$(document).ready( function() {
	initDataTable(
			"table_athletes",
			10);
	
	$("#remove_athlete_lbox").dialog({
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
					location.href= CONTEXT_PATH + '/action/admin/athletes/remove/' + idToRemove;			
					$(this).dialog('close');
				}
			}
		],
		open : function() {
			$('.ui-dialog-buttonset button').blur();
		}
	});
}); 

function removeAthlete(idAthlete){
	idToRemove = idAthlete;
	$("#remove_athlete_lbox").dialog("open");
}