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
	    	$("#exercise_block_name").val(String.trim(currentExerciseTr.text()));
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
		

	}); 
	
	function createNewExerciseTr(oTable){
		var anSelected = fnGetSelected(oTable);
		
		// Hay elemento seleccionado		
		if (anSelected != ""){ 	
			
			var newTr= $('<tr>')
				.addClass('gradeU')	
				.append(			
					$('<td>')	
				);
			
			$('#table_exercises_block tbody').append(newTr);
			
			
			refreshTable('table_exercises_block');
			
			currentExerciseTr = newTr;		
			
			//valor seleccionado
			var value = $(anSelected).find('td').html();
			
			currentExerciseTr.find('td').text(value);
			
		 	var newImput = $('<input>')
				.attr('name', 'trainingDayList['+ listExerciseTam +']')
				.css("display", "none")
				.val(value);
			
			currentExerciseTr.append(newImput); 	
		
			listExerciseTam++;	
		}
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
				
				$(this).find('input').attr('name', 'trainingDayList['+ index +']');
			}
		});	
	}

  </script>
  
  <style>

  
  
  </style>


<div id="body_home">
	<div id = "body_home_container" >			
	    <div class="caja">
	   		<form:form commandName="trainingDayCommand" cssClass="caja"  action="${pageContext.request.contextPath}/action/training/day/save" method="POST" >
		   		<div class="subcaja">
			 		<h1><fmt:message key="training.day.tittle" /></h1>
			 		<a class="button" href="${pageContext.request.contextPath}/action/training/day/list"><fmt:message key="training.day.button.back" /></a>
			 		<input type="submit" value="<fmt:message key='training.day.button.save'/>" class="button_submit">
			    </div> 
				    
				<div class="subcaja doble_row">
					<fieldset>		
					<form:errors path="*"  element="div" />		
						<form:hidden path="id" />		
				        <div class="form-row">
				            <label for="name"><fmt:message key="training.day.name"/>:</label>
				           <span class="input"><form:input path="name" />   <form:errors path="name" cssClass="error" /></span> 
				        </div>   
				        <div class="form-row">
				            <label for="type"><fmt:message key="training.day.type"/>:</label>
				            <span class="input"><form:input path="type"/>   <form:errors path="type" cssClass="error" /></span> 
				        </div>  				       
				        <div class="form-row">
				            <label for="description"><fmt:message key="training.day.description"/>:</label>
				            <span class="input"><form:textarea path="description" cols="10"  disabled="disabled"/></span>		     
				        </div> 
				     </fieldset>
			     </div>
			     
			     <div class="doble_row" >    
					 <table class="display simple_button" id="table_exercises_block" >
				    	<thead>
				        	<tr>
				            	<th><fmt:message key="training.day.trainings" /> </th>	            	
				          	</tr>
				        </thead>
				        <tbody>	
				        	<c:forEach items="${trainingDayCommand.trainingDayList}" var="training" varStatus="status">
				        		<tr class="${rowStyle}  gradeU"> 
				        			<td>${training}</td> 
					             	<form:hidden path="trainingDayList[${status.index}]" />
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
<div id="new_exercise_block_lbox" class="lBox" title="Confirmación de alta de póliza">
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
		            	<th><fmt:message key="training.exercise.block.name" /> </th>
		            	<th><fmt:message key="training.exercise.block.type" /></th>		            	
		          	</tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${exerciseBlockList}" var="block">
			        	<tr class="${rowStyle}  gradeU"> 
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
<div id="modify_exercise_block_lbox" class="lBox" title="Confirmación de alta de póliza">
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


<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ fin LIGHTTBOXES /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->

</div>
