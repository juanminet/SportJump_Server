var idToRemove;  	
$(document).ready( function() {
	initDataTable(
			"table_groups",
			10);
	$("#remove_group_lbox").dialog({
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
					location.href= CONTEXT_PATH + '/action/admin/groups/remove/' + idToRemove;			
					$(this).dialog('close');
				}
			}
		],
		open : function() {
			$('.ui-dialog-buttonset button').blur();
		}
	});
}); 


function removeGroup(idGroup){
	idToRemove = idGroup;
	$("#remove_group_lbox").dialog("open");
}