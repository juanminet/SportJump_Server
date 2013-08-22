<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- QUITAR -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>




</style>

<script>


	
$(document).ready( function() {

/* 	initDataTableButton(
			"table_groups",
			"${pageContext.request.contextPath}/action/admin/groups/new", 
			'<fmt:message key="admin.groups.table.new.group" />', 
			10); */
	
	initDataTable(
			"table_athletes",
			10);
	  	
}); 
	
	function pulsado(idTeam){
		alert(idTeam);
		
		 location.href='${pageContext.request.contextPath}/action/admin/groups/' + idTeam; 
	}
	
</script>
    
<div id ="body_home">
	<div id = "body_home_container" >			
	    <div class="caja">
	    
	   		<div class="subcaja">
		 		<h1><fmt:message key="admin.athletes.list.athletes" /></h1>
		 		<a class="button" href="${pageContext.request.contextPath}/action/admin/athletes/new"><fmt:message key="admin.button.new.athlete" /></a>
		    </div> 
		    
		    <table class="display" id="table_athletes">
			        <thead>
			          <tr>
			            <th><fmt:message key="admin.athletes.table.name" /> </th>
			            <th><fmt:message key="admin.athletes.table.type" /></th>	           
			          </tr>
			        </thead>
			        <tbody>
			          <tr class="odd gradeU">
			            <td>Juan Miguel Muñoz Rondán</td>            
			            <td>Velocista</td>
			          </tr>
			          <tr class="even gradeU">
			            <td>Antonio Lopez Canterano</td>
			            <td>Velocista</td>
			          </tr>
			          <tr class="odd gradeU">
			            <td>Bernabe Martinez Soriano</td>
			            <td>Saltador</td>
			          </tr>
			          <tr class="even gradeU">
			            <td>Mariano Gutierre Olimpo</td>
			            <td>Velocista</td>
			          </tr>
			          <tr class="odd gradeU">
			            <td>Sonia Moreno Antunez</td>
			            <td>Saltador</td>
			          </tr>
			          <tr class="even gradeU">
			            <td>Gonzalo Amar Serrano</td>
			            <td>Velocista</td>
			          </tr>
			        
			        </tbody>
			      </table> 
		    
			<%-- <table class="display" id="table_atletes">
		    	<thead>
		        	<tr>
		            	<th><fmt:message key="admin.athletes.table.name" /> </th>
		            	<th><fmt:message key="admin.athletes.table.dni" /></th>
		            	<th><fmt:message key="admin.athletes.table.group" /></th>
		            	<th><fmt:message key="admin.athletes.table.age" /></th>
		          	</tr>
		        </thead>
		        <tbody>
		        	<c:forEach var="athlete" items="${listAthletes}" >
				    	<c:choose>
			          		<c:when test="${rowCounter.count % 2 == 0}">
			            		<c:set var="rowStyle" scope="page" value="odd"/>
			          		</c:when>
			          		<c:otherwise>
			           			<c:set var="rowStyle" scope="page" value="even"/>
			          		</c:otherwise>
			        	</c:choose>
			        	<tr class="${rowStyle}  gradeU" onclick="location.href='${pageContext.request.contextPath}/action/admin/groups/${athlete.idUser}';">
				            <td>${athlete.completeName}</td>            
				            <td>${athlete.dni}</td>
				            <td>juanmi</td>
				            <td>${athlete.age}</td>
				            <td class="center">0</td>
			        	</tr>
		        	</c:forEach>	         
		        </tbody>
		      </table> --%>
	      </div>      
      </div>
</div>
