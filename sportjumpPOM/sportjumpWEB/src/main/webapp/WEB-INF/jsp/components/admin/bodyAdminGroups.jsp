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
			"table_groups",
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
		 		<h1><fmt:message key="admin.groups.list.groups" /></h1>
		 		<a class="button" href="${pageContext.request.contextPath}/action/admin/groups/new"><fmt:message key="admin.button.new.group" /></a>
		    </div> 
		    
		    
			<table class="display" id="table_groups">
		    	<thead>
		        	<tr>
		            	<th><fmt:message key="admin.groups.table.name" /> </th>
		            	<th><fmt:message key="admin.groups.table.type" /></th>
		            	<th><fmt:message key="admin.groups.table.athletes.tam" /></th>
		          	</tr>
		        </thead>
		        <tbody>
		        	<c:forEach var="team" items="${listTeams}" >
				    	<c:choose>
			          		<c:when test="${rowCounter.count % 2 == 0}">
			            		<c:set var="rowStyle" scope="page" value="odd"/>
			          		</c:when>
			          		<c:otherwise>
			           			<c:set var="rowStyle" scope="page" value="even"/>
			          		</c:otherwise>
			        	</c:choose>
			        	<tr class="${rowStyle}  gradeU" onclick="location.href='${pageContext.request.contextPath}/action/admin/groups/${team.idTeam}';">
				            <td>${team.name}</td>            
				            <td>${team.type}</td>
				            <td class="center">${team.size}</td>
			        	</tr>
		        	</c:forEach>	         
		        </tbody>
		      </table>
	      </div>      
      </div>
</div>
