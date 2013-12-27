<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
	
	
<!-- QUITAR -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style>


.row_selected{
	background: red;
}
</style>
<script>
var currentExerciseTr;
var jsNewExercise;

var listExerciseTam;
	
	$(document).ready( function() {
		
		listExerciseTam = "${fn:length(trainingDayCommand.trainingDayList)}";
		
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
				"<fmt:message key='training.execise.block.table.button.new'/>",				
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
					text : "<fmt:message key='training.exercise.block.lbox.button.cancel'/>",
					class:"lbox-button",
					click : function() {
						$(this).dialog('close');
					}
				},
				{					
					text :"<fmt:message key='training.exercise.block.lbox.button.remove'/>",
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
					text : "<fmt:message key='training.exercise.block.lbox.button.cancel'/>",
					class:"lbox-button",
					click : function() {
						$(this).dialog('close');
					}
				}, 
				{					
					text : "<fmt:message key='training.exercise.block.lbox.button.save'/>",
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
			width : 640,
			height: 480,
			modal : true,
			buttons : [ 
				{					
					text : "<fmt:message key='training.exercise.block.lbox.button.close'/>",
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
				showTipExerciseIdAjax(element, exerciseId);		
			} );
			 $("#new_exercise_block_lbox tbody tr").mouseout( function(){
				$(this).hideBalloon();	
			} );
			
			
			$("#table_exercises_block tbody tr").mouseenter( function(){
				var element = $(this);
				var exerciseId = $(this).find("td:first").text();	                
				showTipExerciseIdAjax(element, exerciseId);		
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
	

  </script>
  
  
  <script>
 	 var CONTEXT_PATH = "${pageContext.request.contextPath}";
  </script>



<div id="body_home">
	<div id = "body_home_container" >			
	    <div class="caja">
	   		<form:form commandName="trainingDayCommand" cssClass="caja"  action="${pageContext.request.contextPath}/action/training/day/save" method="POST" >
		   		<div class="subcaja">
			 		<h1><fmt:message key="training.day.tittle" /></h1>
			 		<input type="submit" value="<fmt:message key='training.day.button.save'/>" class="button_submit">			 		
			 		<c:if test="${not empty trainingDayCommand.id}">
			 			<a class="button" href="${pageContext.request.contextPath}/action/training/day/remove/${trainingDayCommand.id}"><span><fmt:message key="training.day.button.remove" /></span></a>
			 			<a id="show_training_button" class="button"><span><fmt:message key="training.day.button.show" /></span></a>
			 		</c:if>
			 		<a class="button" href="${pageContext.request.contextPath}/action/training/day/list"><span><fmt:message key="training.day.button.back" /></span></a>
			    </div> 
				    
				<div class="subcaja doble_row">
					<fieldset>		
					<!-- <form:errors path="*"  element="div" /> -->		
						<form:hidden path="id" />		
						<form:errors path="name" cssClass="error" />
				        <div class="form-row">
				        	
				            <label for="name"><fmt:message key="training.day.name"/>:</label>
				           	<span class="input"><form:input path="name" id = "training_name"/>   </span> 
				        </div> 
				        <form:errors path="type" cssClass="error" />  
				        <div class="form-row">
				            <label for="type"><fmt:message key="training.day.type"/>:</label>
				            <span class="input"><form:input path="type"/>   </span> 
				        </div>  				       
				        <div class="form-row">
				            <label for="description"><fmt:message key="training.day.description"/>:</label>
				            <span class="input"><form:textarea path="description" cols="10"  disabled="disabled" id = "training_description"/></span>		     
				        </div> 
				     </fieldset>
			     </div>
			     
			     <div class="doble_row" >    
					 <table class="display simple_button" id="table_exercises_block" >
				    	<thead>
				        	<tr>
				        		<th hidden="true">id</th>
				            	<th><fmt:message key="training.day.trainings" /> </th>	            	
				          	</tr>
				        </thead>
				        <tbody>	
				        	<c:forEach items="${trainingDayCommand.trainingDayList}" var="exerciseBlock" varStatus="status">
				        		<tr class="${rowStyle}  gradeU"> 
				        			<td hidden="true">${exerciseBlock.id}</td> 
				        			<td>${exerciseBlock.name}</td> 
				        			<form:input path="trainingDayList[${status.index}].id" id="id" type="hidden"/>
					             	<form:input path="trainingDayList[${status.index}].name" id="name" type="hidden" />
				        		</tr>
				        	</c:forEach>        		         
				    	</tbody>
					</table>
				</div>		
			</form:form>   
		</div>   
		
		
	</div>
	
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ LIGHTTBOXES /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->

<!-- lightbox for create exercises -->
<div id="new_exercise_block_lbox" class="lBox" title="<fmt:message key='training.day.lbox.create.tittle'/>">
	<!-- Mensaje -->
	<div class="cajaMensajeTop">
		<div class="cajaMensajeTopLeft"></div>
		<div class="cajaMensajeTopRight"></div>
	</div>
	<div class="mensaje dobleLinea fuenteFormLB">
		<div class="single_row">
			 <table class="display" id="table_blocks">
		    	<thead>
		        	<tr>
		        		<th hidden="true">id</th>
		            	<th><fmt:message key="training.exercise.block.name" /> </th>
		            	<th><fmt:message key="training.exercise.block.type" /></th>		            	
		          	</tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${exerciseBlockList}" var="block">
			        	<tr class="${rowStyle}  gradeU">
			        		<td hidden="true">${block.idExerciseBlock}</td> 
				            <td>${block.name}</td>            
				            <td>${block.type}</td>	            				         
			        	</tr>
		        	</c:forEach>	
		        </tbody>
		      </table>      
		</div>
	</div>
	<!--mensaje-->
	<div class="cajaMensajePie">
		<div class="cajaMensajePieLeft"></div>
		<div class="cajaMensajePieRight"></div>
	</div>
	<!-- Mensaje -->	
	
</div>


<!-- lightbox for modify exercises -->
<div id="modify_exercise_block_lbox" class="lBox" title="<fmt:message key='training.day.lbox.modify.tittle'/>">
	<!-- Mensaje -->
	<div class="cajaMensajeTop">
		<div class="cajaMensajeTopLeft"></div>
		<div class="cajaMensajeTopRight"></div>
	</div>
	<div class="mensaje dobleLinea fuenteFormLB">
		<div class="single_row">
			<label for="exercise_block_name"><fmt:message	key="training.exercise.block.name" />:</label> 
			<span class="input"><input id="exercise_block_name" disabled="disabled"/> </span>
		</div>
	</div>
	<!--mensaje-->
	<div class="cajaMensajePie">
		<div class="cajaMensajePieLeft"></div>
		<div class="cajaMensajePieRight"></div>
	</div>
	<!-- Mensaje -->	
	
</div>



<!-- lightbox for show exercises -->
<div id="show_exercise_block_lbox" class="lBox" title="<fmt:message key='training.day.lbox.show.tittle'/>">
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
