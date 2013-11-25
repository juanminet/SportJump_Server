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
		
		jsNewExercise = function newExercise(){		
			$("#create_exercise_name").val("<fmt:message key='training.execise.block.table.button.new'/>");
			$('#create_exercise_lbox').dialog('open');	
		};
		
		jsSortingStop = function(event, ui) {			
			ui.item.css("background", "");
			refreshTable("table_exercises_block");
		}
		
		jsSortingStart = function(event, ui) {
			// Set border colour of cell to red (this works)
			ui.placeholder.css("border", "1px solid #FF7400");
			ui.item.css("background", "#F2E3E3");
		}
		
			
		initSimpleSortableDataTableButton(
				"table_exercises_block",				
				"<fmt:message key='training.execise.block.table.button.new'/>",				
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
				}, 
				{					
					text : "<fmt:message key='training.exercise.block.lbox.button.save'/>",
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

  </script>
  
  <style>

  
  
  </style>


<div id="body_home">
	<div id = "body_home_container" >			
	    <div class="caja">
	   		<form:form commandName="blockCommand" cssClass="caja"  action="${pageContext.request.contextPath}/action/training/exercise/block/save" method="POST" >
		   		<div class="subcaja">
			 		<h1><fmt:message key="training.exercise.block.tittle" /></h1>
			 		
			 		<input type="submit" value="<fmt:message key='training.exercise.block.button.save'/>" class="button_submit">
			 		<c:if test="${not empty blockCommand.id}">
			 			<a class="button" href="${pageContext.request.contextPath}/action/training/exercise/remove/${blockCommand.id}"><fmt:message key="training.exercise.block.button.remove" /></a>
			 		</c:if>
			 		<a class="button" href="${pageContext.request.contextPath}/action/training/exercise"><fmt:message key="training.exercise.block.button.back" /></a>			 		
			    </div> 
				    
				<div class="subcaja doble_row">
					<fieldset>		
					<form:errors path="*"  element="div" />		
						<form:hidden path="id" />		
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
			        			<td>${exercise}</td> 
				             	<form:hidden path="exerciseList[${status.index}]" />
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
<div id="create_exercise_lbox" class="lBox" title="Confirmación de alta de póliza">
	<!-- Mensaje -->
	<div class="cajaMensajeTop">
		<div class="cajaMensajeTopLeft"></div>
		<div class="cajaMensajeTopRight"></div>
	</div>
	<div class="mensaje dobleLinea fuenteFormLB">
		<div class="single_row">
			<label for="create_exercise_name"><fmt:message	key="training.exercise.block.name" />:</label> 
			<span class="input"><input id="create_exercise_name" /> </span>
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
