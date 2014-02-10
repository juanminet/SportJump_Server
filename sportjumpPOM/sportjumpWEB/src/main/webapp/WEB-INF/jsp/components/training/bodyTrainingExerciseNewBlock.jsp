<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<script>
	var MSG_TABLE_BUTTON_NEW = "<fmt:message key='training.execise.block.table.button.new'/>";
	var MSG_LBOX_BUTTON_CANCEL = "<fmt:message key='training.exercise.block.lbox.button.cancel'/>";
	var MSG_LBOX_BUTTON_REMOVE = "<fmt:message key='training.exercise.block.lbox.button.remove'/>";
	var MSG_LBOX_BUTTON_SAVE = "<fmt:message key='training.exercise.block.lbox.button.save'/>";
	var LIST_EXERCISE_TAM = "${fn:length(blockCommand.exerciseList)}";
</script>

<div id="body_home">
	<div id = "body_home_container" >			
	    <div class="caja">
	   		<form:form commandName="blockCommand" cssClass="caja"  action="${pageContext.request.contextPath}/action/training/exercise/block/save" method="POST" >
		   		<div class="subcaja">
			 		<h1><fmt:message key="training.exercise.block.tittle" /></h1>
			 		
			 		<input type="submit" value="<fmt:message key='training.exercise.block.button.save'/>" class="button_submit">
			 		<a class="button" href="${pageContext.request.contextPath}/action/training/exercise"><span><fmt:message key="training.exercise.block.button.back" /></span></a>			 		
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
<div id="create_exercise_lbox" class="lBox" title="<fmt:message key='training.exercise.block.lbox.create.tittle'/>">
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
<div id="exercise_lbox" class="lBox" title="<fmt:message key='training.exercise.block.lbox.modify.tittle'/>">
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
