<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<script>
	var CONTEXT_PATH = "${pageContext.request.contextPath}";
	var MSG_LBOX_BUTTON_YES = "<fmt:message key='planning.calendar.lbox.button.yes'/>";
	var MSG_LBOX_BUTTON_NO = "<fmt:message key='planning.calendar.lbox.button.no'/>";
</script>
<div id="body_home">
	<div id = "body_home_container" >			
	    <div class="caja">
	    
	   		<div class="subcaja">
		 		<h1><fmt:message key="training.exercise.block.tittle" /></h1>
		 		<a class="button" href="${pageContext.request.contextPath}/action/training/exercise/block/new"><span><fmt:message key="training.exercise.block.button.new" /></span></a>
		    </div> 
		    
		  			    
			<table class="display" id="table_exercises_block">
		    	<thead>
		        	<tr>
		            	<th><fmt:message key="training.table.name" /> </th>
		            	<th><fmt:message key="training.table.type" /></th>	
		            	<th class="table_field_center"><fmt:message key="training.table.remove" /></th>	            	
		          	</tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${exerciseBlockList}" var="block">
			        	<tr class="${rowStyle}  gradeU"> 
				            <td onclick="location.href='${pageContext.request.contextPath}/action/training/exercise/block/${block.idExerciseBlock}';">${block.name}</td>            
				            <td onclick="location.href='${pageContext.request.contextPath}/action/training/exercise/block/${block.idExerciseBlock}';">${block.type}</td>	  
				            <td onclick="javascript:removeBlock(${block.idExerciseBlock});" >
				           		<div class="table_cross_remove">
				            		<img src="${pageContext.request.contextPath}/resources/img/cross.png" />
				            	</div>
				            </td>	          				         
			        	</tr>
		        	</c:forEach>	
		        </tbody>
		      </table>   
	      </div>      
      </div>
      
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ LIGHTTBOXES /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
      <!-- lightbox for remove dialog -->
	<div id="remove_training_block_lbox" class="lBox" title="<fmt:message key='training.exercise.block.lbox.remove.tittle'/>">
		<!-- Mensaje -->
		<div class="cajaMensajeTop">
			<div class="cajaMensajeTopLeft"></div>
			<div class="cajaMensajeTopRight"></div>
		</div>
		<div class="mensaje dobleLinea fuenteFormLB">
			<fmt:message key="training.exercise.block.lbox.remove.text" />
		</div>
		<!--mensaje-->
		<div class="cajaMensajePie">
			<div class="cajaMensajePieLeft"></div>
			<div class="cajaMensajePieRight"></div>
		</div>
		<!-- Mensaje -->
	</div>


<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ fin LIGHTTBOXES /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
</div>
