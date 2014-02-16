var currentExerciseTr;
var jsNewExercise;

var listExerciseTam;
	
$(document).ready( function() {
	
	listExerciseTam = LIST_EXERCISE_TAM;
	
	jsNewExerciseBlock = function newExerciseBlock(){					
		$('#new_exercise_block_lbox').dialog('open');	
	};
	
	jsSortingStop = function(event, ui) {			
		ui.item.css("background", "");
		refreshTable("table_exercises_block");
	};
	
	jsSortingStart = function(event, ui) {
		// Set border colour of cell to red (this works)
		ui.placeholder.css("border", "1px solid #FF7400");
		ui.item.css("background", "#F2E3E3");
	};
	
		
	initSimpleSortableDataTableButton(
			"table_exercises_block",				
			MSG_TABLE_BUTTON_NEW,				
			10,
			jsNewExerciseBlock,
			jsSortingStart,
			jsSortingStop
	);
	
	var oTableBlocks = initSelectableDataTable(
			"table_blocks",
			5
	);
	
 
    $( document ).on( "click", "#table_exercises_block tbody tr", function() {
    	currentExerciseTr = $(this);
    	var name= String.trim($(currentExerciseTr).find("td:nth-child(2)").text())
    	$("#exercise_block_name").val(name);
    	$('#modify_exercise_block_lbox').dialog('open');			
	});
	
	
	$("#modify_exercise_block_lbox").dialog({
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
			}
		],
		open : function() {
			$('.ui-dialog-buttonset button').blur();
		}
	}); 
	
	
	$("#new_exercise_block_lbox").dialog({
		autoOpen : false,
		resizable : false,
		draggable : false,
		width : 640,
		height: 480,
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
				text : MSG_LBOX_BUTTON_SAVE,
				class:"lbox-button",
				click : function() {						
					 createNewExerciseTr(oTableBlocks);											
					 $(this).dialog('close'); 
				}
			} 
		],
		open : function() {
			$('.ui-dialog-buttonset button').blur();
		}
	});	
	
	
	$("#show_exercise_block_lbox").dialog({
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
	

	
	$("#show_training_button").click(function(){		
	   var trainingDiv = $('<div/>', {class : 'training_lbox_show'});		

		trainingDiv.append($('<label>').append($('#training_name').val()));
		trainingDiv.append($('<br/>'));
		trainingDiv.append($('<label>').append($('#training_description').val()));	
		
		var names = "";
		$('#table_exercises_block tbody tr').each(function(index, element){
			names = names + $(element).find("td:nth-child(2)").text() + "-";					
		});
		
		$.ajax({
			url: CONTEXT_PATH + "/ajax/training/exercise/names/" + names,
		  	type: 'GET',
		  	cache: false,				 
		  	success: function(data){
		  		$.each(data.listBlock, function(iBlock,block){				
					var divExerciseBlock = createExerciseBlockDiv(block);				
					trainingDiv.append(divExerciseBlock);
				});					  		
		  	},
		  	error: function(){alert("ajax error");}
	 	}); 
		
		$("#lbox_show_container").append(trainingDiv);					
		$('#show_exercise_block_lbox').dialog('open');					
	});
	
	
	
	//ToolTips
	setToolTip();	 
	
});

function setToolTip(){
	 $("#new_exercise_block_lbox tbody tr").mouseenter( function(){
			var element = $(this);
			var exerciseId = $(this).find("td:first").text();	

			if (!isNaN(exerciseId)){
				showTipExerciseIdAjax(element, exerciseId);		
			}
		} );
		 $("#new_exercise_block_lbox tbody tr").mouseout( function(){
			$(this).hideBalloon();	
		} );
		
		
		$("#table_exercises_block tbody tr").mouseenter( function(){
			var element = $(this);
			var exerciseId = $(this).find("td:first").text();	
			
			if (!isNaN(exerciseId)){
				showTipExerciseIdAjax(element, exerciseId);		
			}	
		} );
		$("#table_exercises_block tbody tr").mouseout( function(){
			$(this).hideBalloon();	
		} );  
}
	
function showTipExerciseIdAjax(element, exerciseId){	
	if (!existExerciseBlock(exerciseId)){			
		$.ajax({
			url: CONTEXT_PATH + "/ajax/training/exercise/" + exerciseId,
		  	type: 'GET',
		  	cache: false,				 
		  	success: function(data){
		  		setExerciseBlock(data, exerciseId);
		  		showTip(element,data);
		  	},
		  	error: function(){alert("ajax error");	}
	 	}); 
	}else{		
		showTip(element, getExerciseBlock(exerciseId));
	}
}	


function showTip(element, exerciseBlock){
	var exerciseBlockDiv = createExerciseBlockDiv(exerciseBlock);				  
	   
	$(element).showBalloon({						
		position: 'right',
  		contents: exerciseBlockDiv,
  		minLifetime: 0, showDuration: 0, hideDuration: 0 
	});				
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

function createNewExerciseTr(oTable){
	var anSelected = fnGetSelected(oTable);
	
	// Hay elemento seleccionado		
	if (anSelected != ""){ 	
		
		var newTr= $('<tr>')
			.addClass('gradeU')	
			.append(			
				$('<td>').hide()	
			).append(			
				$('<td>')	
			);;
		
		$('#table_exercises_block tbody').append(newTr);
		
		
		refreshTable('table_exercises_block');
		
		currentExerciseTr = newTr;		
		
		//valor seleccionado
		var id = $(anSelected).find('td:nth-child(1)').html();
		var name = $(anSelected).find('td:nth-child(2)').html();			
		
		currentExerciseTr.find('td:nth-child(1)').text(id);
		currentExerciseTr.find('td:nth-child(2)').text(name);
		
		
        
       
		
		var newImputId = $('<input>')
			.attr('id', 'id')
			.attr('name', 'trainingDayList['+ listExerciseTam +'].id')	
			.attr('type', 'hidden')	
			.attr('value', id);
		
		currentExerciseTr.append(newImputId);
		
		var newImputName = $('<input>')
		.attr('id', 'name')
		.attr('name', 'trainingDayList['+ listExerciseTam +'].name')
		.attr('type', 'hidden')	
		.attr('value', name);
	 	
		currentExerciseTr.append(newImputName); 	
	
		listExerciseTam++;	
	}
	setToolTip();
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
			
			$(this).find('#name').attr('name', 'trainingDayList['+ index +'].name');
			$(this).find('#id').attr('name', 'trainingDayList['+ index +'].id');
		}
	});	
}

var exerciseBlockList = new Array();

function setExerciseBlock(exerciseBlock, id){
	exerciseBlockList[id] = exerciseBlock;
}

function getExerciseBlock(id){
	return exerciseBlockList[id];
}

function existExerciseBlock(id){		
	return(getExerciseBlock(id) != null)? true :  false;
}
	
