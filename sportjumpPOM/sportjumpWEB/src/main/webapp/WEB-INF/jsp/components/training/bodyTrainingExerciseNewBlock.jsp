<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
	
	
<!-- QUITAR -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
var currentExerciseTr;
var jsNewExercise;

var listExerciseTam;
	
	$(document).ready( function() {
		
		listExerciseTam = "${fn:length(blockCommand.exerciseList)}";
		
		alert(listExerciseTam);
		jsNewExercise = function newExercise(){
			var newTr= $('<tr>')
				.addClass('gradeU')			
				.append(						
				$('<td>').text("<fmt:message key='training.execise.block.table.button.new'/>")
				
				
				
				
			);
			$('#table_exercises_block tbody').append(newTr);
			refreshTable('table_exercises_block');
			
			currentExerciseTr = newTr;			
		  	$("#exercise_name").val(String.trim(currentExerciseTr.text()));
			$('#exercise_lbox').dialog('open');	
		};
		
		initSimpleSortableDataTableButton(
				"table_exercises_block",				
				"<fmt:message key='training.execise.block.table.button.new'/>",				
				10,
				jsNewExercise);			


	 
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
						currentExerciseTr.remove();
						refreshTable('table_exercises_block');
						listExerciseTam--;
						$(this).dialog('close');
					}
				}, 
				{					
					text : "<fmt:message key='training.exercise.block.lbox.button.save'/>",
					class:"lbox-button",
					click : function() {						
					//	currentExerciseTr.find('td').text($('#exercise_name').val());
						var value = $('#exercise_name').val();
						currentExerciseTr.find('td').html("<input name='exerciseList["+ listExerciseTam +"]' value='"+ value + "'/>");
						listExerciseTam++;
						
						$(this).dialog('close');
					}
				} 
			],
			open : function() {
				$('.ui-dialog-buttonset button').blur();
			}
		});

	}); 
	

  </script>
  
  <style>

  
  
  </style>


<div id="body_home">
	<div id = "body_home_container" >			
	    <div class="caja">
	   		<form:form commandName="blockCommand" cssClass="caja"  action="${pageContext.request.contextPath}/action/training/exercise/block/save" method="POST" >
		   		<div class="subcaja">
			 		<h1><fmt:message key="training.exercise.block.tittle" /></h1>
			 		<a class="button" href="${pageContext.request.contextPath}/action/training/exercise/block/new"><fmt:message key="training.exercise.block.button.back" /></a>
			 		<input type="submit" value="<fmt:message key='training.exercise.block.button.save'/>" class="button_submit">
			    </div> 
				    
				<div class="subcaja doble_row">
					<fieldset>						
				        <div class="form-row">
				            <label for="name"><fmt:message key="training.exercise.block.name"/>:</label>
				           <span class="input"><form:input path="name" />   <form:errors path="name" cssClass="error" /></span> 
				        </div>   
				        <div class="form-row">
				            <label for="type"><fmt:message key="training.exercise.block.type"/>:</label>
				            <span class="input"><form:input path="type"/>   <form:errors path="type" cssClass="error" /></span> 
				        </div>  				       
				        <div class="form-row">
				            <label for="description"><fmt:message key="training.exercise.block.description"/>:</label>
				            <span class="input"><form:textarea path="description" cols="10"  disabled="disabled"/></span>		     
				        </div> 
				     </fieldset>
			     </div>
			     
			     <div class="doble_row" >
			     
			    
			     
				 <table class="display simple_button" id="table_exercises_block" >
			    	<thead>
			        	<tr>
			            	<th><fmt:message key="training.exercise.block.exercises" /> </th>	            	
			          	</tr>
			        </thead>
			        <tbody>	
			        	<c:forEach items="${blockCommand.exerciseList}" var="exercise" varStatus="status">
			        		<tr class="${rowStyle}  gradeU"> 
				             	<td><input name="exerciseList[${status.index}]" value="${exercise.name}"/></td>   
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



<!-- lightbox for modify exercises -->
<div id="exercise_lbox" class="lBox" title="Confirmación de alta de póliza">
	<!-- Mensaje -->
	<div class="cajaMensajeTop">
		<div class="cajaMensajeTopLeft"></div>
		<div class="cajaMensajeTopRight"></div>
	</div>
	<div class="mensaje dobleLinea fuenteFormLB">
		<div class="single_row">
			<label for="exercise_name"><fmt:message	key="training.exercise.block.name" />:</label> 
			<span class="input"><input id="exercise_name" /> </span>
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
