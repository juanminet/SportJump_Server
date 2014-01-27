<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<div id ="body_home">
	<div id = "body_home_container" >			
	    <div class="caja">
	    
	   		<div class="subcaja">
		 		<h1><fmt:message key="planning.group.list.tittle" /></h1>		 		
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
			        	<tr class="${rowStyle}  gradeU" onclick="location.href='${pageContext.request.contextPath}/action/planning/calendar/group/${team.idTeam}';">
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
