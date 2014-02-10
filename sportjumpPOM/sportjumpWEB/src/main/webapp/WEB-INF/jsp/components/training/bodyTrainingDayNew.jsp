<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
	
<script>
	var CONTEXT_PATH = "${pageContext.request.contextPath}";
	 
	var MSG_TABLE_BUTTON_NEW = "<fmt:message key='training.execise.block.table.button.new'/>";
	var MSG_LBOX_BUTTON_CANCEL = "<fmt:message key='training.exercise.block.lbox.button.cancel'/>";
	var MSG_LBOX_BUTTON_REMOVE = "<fmt:message key='training.exercise.block.lbox.button.remove'/>";
	var MSG_LBOX_BUTTON_SAVE = "<fmt:message key='training.exercise.block.lbox.button.save'/>";	
	var MSG_LBOX_BUTTON_CLOSE = "<fmt:message key='training.exercise.block.lbox.button.close'/>";
	
	var LIST_EXERCISE_TAM = "${fn:length(trainingDayCommand.trainingDayList)}";	
 </script>



<div id="body_home">
	<div id = "body_home_container" >			
	    <div class="caja">
	   		<form:form commandName="trainingDayCommand" cssClass="caja"  action="${pageContext.request.contextPath}/action/training/day/save" method="POST" >
		   		<div class="subcaja">
			 		<h1><fmt:message key="training.day.tittle" /></h1>
			 		<input type="submit" value="<fmt:message key='training.day.button.save'/>" class="button_submit">			 		
			 		<c:if test="${not empty trainingDayCommand.id}">
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
