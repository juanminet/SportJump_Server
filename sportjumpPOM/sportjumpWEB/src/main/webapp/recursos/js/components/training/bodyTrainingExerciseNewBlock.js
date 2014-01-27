var currentExerciseTr;
var jsNewExercise;

var listExerciseTam;
	
	$(document).ready( function() {
		
		listExerciseTam = "${fn:length(blockCommand.exerciseList)}";
	
	jsNewExercise = function newExercise(){		
		$("#create_exercise_name").val(MSG_TABLE_BUTTON_NEW);
		$('#create_exercise_lbox').dialog('open');	
	};
	
	jsSortingStop = function(event, ui) {			
		ui.item.css("background", "");
		refreshTable("table_exercises_block");
	}
	
	jsSortingStart = function(event, ui) {
		ui.placeholder.css("border", "1px solid #FF7400");
		ui.item.css("background", "#F2E3E3");
	}
	
		
	initSimpleSortableDataTableButton(
			"table_exercises_block",				
			MSG_TABLE_BUTTON_NEW,				
			10,
			jsNewExercise,
			jsSortingStart,
			jsSortingStop);			


 
    $( document ).on( "click", "#table_exercises_block tbody tr", function() {
    	currentExerciseTr = $(this);	    	
    	$("#exercise_name").val(String.trim(currentExerciseTr.text()));
    	$('#exercise_lbox').dialog('open');			
	});
	
	
	$("#exercise_lbox").dialog({
		autoOpen : false,
		resizable : false,
		draggable : false,
		width : 400,
		modal : true,
		buttons : [ 
			{					
				text : MSG_LBOX_BUTTON_CANCEL,
				class:"lbox-button",
				click : function() {
					$(this).dialog('close');
				}
			},
			{					
				text : MSG_LBOX_BUTTON_REMOVE,
				class:"lbox-button",
				click : function() {						
					removeExerciseTr();
					$(this).dialog('close');
				}
			}, 
			{					
				text :  MSG_LBOX_BUTTON_SAVE,
				class:"lbox-button",
				click : function() {						
					modifyExerciseTr();						
					$(this).dialog('close');
				}
			} 
		],
		open : function() {
			$('.ui-dialog-buttonset button').blur();
		}
	}); 
	
	
	$("#create_exercise_lbox").dialog({
		autoOpen : false,
		resizable : false,
		draggable : false,
		width : 400,
		modal : true,
		buttons : [ 
			{					
				text : MSG_LBOX_BUTTON_CANCEL,
				class:"lbox-button",
				click : function() {
					$(this).dialog('close');
				}
			}, 
			{					
				text :  MSG_LBOX_BUTTON_SAVE,
				class:"lbox-button",
				click : function() {						
					createNewExerciseTr();											
					$(this).dialog('close');
				}
			} 
		],
		open : function() {
			$('.ui-dialog-buttonset button').blur();
		}
	});		
	

}); 

function createNewExerciseTr(){
	
	var newTr= $('<tr>')
		.addClass('gradeU')	
		.append(			
			$('<td>')	
		);
	
	$('#table_exercises_block tbody').append(newTr);
	refreshTable('table_exercises_block');
	
	currentExerciseTr = newTr;		
	var value = $('#create_exercise_name').val();
	currentExerciseTr.find('td').text(value);
	
 	var newImput = $('<input>')
		.attr('name', 'exerciseList['+ listExerciseTam +']')
		.css("display", "none")
		.val(value);
	
	currentExerciseTr.append(newImput); 	

	listExerciseTam++;	
}	


function modifyExerciseTr(){
	var value = $('#exercise_name').val();
	currentExerciseTr.find('td').text(value);
	currentExerciseTr.find('input').val(value);		
}

function removeExerciseTr(){
	currentExerciseTr.remove();
	refreshTable('table_exercises_block');
	listExerciseTam--;
}

function refreshTable(idTabla){
	$('#' + idTabla + " tbody tr").each(function (index) {
		
		var text = $(this).text();		
		
		if(text == "No data available in table"){
			$(this).remove();
		}else{		
			$(this).removeClass( "even odd gradeU" );
			if (index % 2 == 0){
				$(this).addClass("gradeU odd");
			}else{
				$(this).addClass("gradeU even");
			}
			
			$(this).find('input').attr('name', 'exerciseList['+ index +']');
		}
	});	
}
