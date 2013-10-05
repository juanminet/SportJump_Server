<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
	$(document).ready( function() {
		
		initSimpleDataTableButton(
				"table_exercises_block",
				"/asdf",
				"modificar",
				10);
		initSimpleDataTableButton(
				"table_exercises_modify_block",
				"/asdf",
				"anadir",
				10);
		$('#table_exercises_modify_block tbody').sortable();
		
		$('#altaPolizaBoton, #altaPolizaBoton2').bind("click", function(event) {			
			$('#altaPoliza').dialog('open');			
		});
		
		$("#altaPoliza").dialog({
			autoOpen : false,
			resizable : false,
			draggable : false,
			width : 400,
			modal : true,
			buttons : [ 
				{					
					text : "Cancelar",
					class:"lbox-button",
					click : function() {
						$(this).dialog('close');
					}
				},
				{					
					text : "Aceptar",
					class:"lbox-button",
					click : function() {
						location.href = 'confirmacionAltaPoliza';
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
			        	<tr class="${rowStyle}  gradeU"> 
				             <td>2X 300 metros o mas</td>   
			        	</tr>
			        	<tr class="${rowStyle}  gradeU"> 
				             <td>3X 50 metros</td>   
			        	</tr>
			        	<tr class="${rowStyle}  gradeU"> 
				             <td>1x prograsivo</td>          
			        	</tr>			        		         
			    	</tbody>
				</table>
				 </div>		
			</form:form>   
		</div>      
	</div>
	
	<input id="altaPolizaBoton" value="lightBox" class="button_submit">

<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ LIGHTTBOXES /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->



<!-- altaPoliza -->
<div id="altaPoliza" class="lBox" title="Confirmación de alta de póliza">
	<!-- Mensaje -->
	<div class="cajaMensajeTop">
		<div class="cajaMensajeTopLeft"></div>
		<div class="cajaMensajeTopRight"></div>
	</div>
	<div class="mensaje dobleLinea fuenteFormLB">
		
		  <div class="single_row">			    
			     
				 <table class="display simple_button" id="table_exercises_modify_block" >
			    	<thead>
			        	<tr>
			            	<th><fmt:message key="training.exercise.block.exercises" /> </th>	            	
			          	</tr>
			        </thead>
			        <tbody>
			        	<tr class="${rowStyle}  gradeU"> 
				             <td>2X 300 metros o mas</td>   
			        	</tr>
			        	<tr class="${rowStyle}  gradeU"> 
				             <td>3X 50 metros</td>   
			        	</tr>
			        	<tr class="${rowStyle}  gradeU"> 
				             <td>1x prograsivo</td>          
			        	</tr>			        		         
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


<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ fin LIGHTTBOXES /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->

</div>
