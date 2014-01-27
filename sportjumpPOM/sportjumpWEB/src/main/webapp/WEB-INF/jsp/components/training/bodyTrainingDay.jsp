<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="body_home">
	<div id = "body_home_container" >			
	    <div class="caja">
	    
	   		<div class="subcaja">
		 		<h1><fmt:message key="training.day.tittle" /></h1>
		 		<a class="button" href="${pageContext.request.contextPath}/action/training/day/new"><span><fmt:message key="training.day.button.new" /></span></a>
		    </div> 
		    
		  			    
			<table class="display" id="table_exercises_block">
		    	<thead>
		        	<tr>
		            	<th><fmt:message key="training.day.name" /> </th>
		            	<th><fmt:message key="training.day.type" /></th>		            	
		          	</tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${trainingDayList}" var="day">
			        	<tr class="${rowStyle}  gradeU" onclick="location.href='${pageContext.request.contextPath}/action/training/day/${day.idTraining}';"> 
				            <td>${day.name}</td>            
				            <td>${day.type}</td>	            				         
			        	</tr>
		        	</c:forEach>	
		        </tbody>
		      </table>   
	      </div>      
      </div>
</div>
