<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
	$(document).ready( function() {
		
		initDataTable(
				"table_exercises_block",
				10);
		  	
	}); 
  </script>


<div id="body_home">
	<div id = "body_home_container" >			
	    <div class="caja">
	    
	   		<div class="subcaja">
		 		<h1><fmt:message key="training.exercise.block.tittle" /></h1>
		 		<a class="button" href="${pageContext.request.contextPath}/action/training/exercise/block/new"><fmt:message key="training.exercise.block.button.new" /></a>
		    </div> 
		    
		  			    
			<table class="display" id="table_exercises_block">
		    	<thead>
		        	<tr>
		            	<th><fmt:message key="training.exercise.block.name" /> </th>
		            	<th><fmt:message key="training.exercise.block.type" /></th>		            	
		          	</tr>
		        </thead>
		        <tbody>
		        	<tr class="${rowStyle}  gradeU" onclick="location.href='${pageContext.request.contextPath}/action/training/exercise/block/1';"> 
			             <td>fuerza primavera</td>            
			            <td>Fuerza</td>	            				         
		        	</tr>
		        		<tr class="${rowStyle}  gradeU" onclick="location.href='${pageContext.request.contextPath}/action/training/exercise/block/1';"> 
			             <td>Carreras puesta punto</td>            
			            <td>Carreras</td>	            				         
		        	</tr>
		        		<tr class="${rowStyle}  gradeU" onclick="location.href='${pageContext.request.contextPath}/action/training/exercise/block/1';"> 
			             <td>Velocidad mantenida</td>            
			            <td>Carreras</td>	            				         
		        	</tr>
		        		         
		        </tbody>
		      </table>   
	      </div>      
      </div>
</div>
