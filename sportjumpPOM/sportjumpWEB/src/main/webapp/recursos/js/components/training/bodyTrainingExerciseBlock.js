var idToRemove;
$(document).ready(function() {
	initDataTable("table_exercises_block", 10);
	
	$("#remove_training_block_lbox").dialog({
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
					location.href= CONTEXT_PATH + '/action/training/exercise/remove/' + idToRemove;			
					$(this).dialog('close');
				}
			}
		],
		open : function() {
			$('.ui-dialog-buttonset button').blur();
		}
	});
	
});

function removeBlock(idBlock){
	idToRemove = idBlock;
	$("#remove_training_block_lbox").dialog("open");
}