<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	var CONTEXT_PATH = "${pageContext.request.contextPath}";
	var MSG_LBOX_BUTTON_YES = "<fmt:message key='admin.lbox.button.yes'/>";
	var MSG_LBOX_BUTTON_NO = "<fmt:message key='admin.lbox.button.no'/>";
</script>

<div id ="body_home">
	<div id = "body_home_container" >			
	    <div class="caja">
	    
	   		<div class="subcaja">
		 		<h1><fmt:message key="admin.athletes.list.athletes" /></h1>
		 		<a class="button" href="${pageContext.request.contextPath}/action/admin/athletes/new"><span><fmt:message key="admin.button.new.athlete" /></span></a>
		    </div> 
			    
			 <table class="display" id="table_athletes">
		    	<thead>
		        	<tr>
		            	<th><fmt:message key="admin.athletes.table.name" /> </th>
		            	<th><fmt:message key="admin.athletes.table.dni" /></th>
		            	<th><fmt:message key="admin.athletes.table.group" /></th>
		            	<th class="table_field_center"><fmt:message key="admin.athletes.table.age" /></th>
		            	<th class="table_field_center"><fmt:message key="admin.athletes.table.remove" /></th>
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
			        	<tr class="${rowStyle}  gradeU"> 
				        	<td onclick="location.href='${pageContext.request.contextPath}/action/admin/athletes/${athlete.idUser}';">${athlete.completeName}</td>            
				            <td onclick="location.href='${pageContext.request.contextPath}/action/admin/athletes/${athlete.idUser}';">${athlete.dni}</td>
				            <td onclick="location.href='${pageContext.request.contextPath}/action/admin/athletes/${athlete.idUser}';">${athlete.team.name}</td>
				            <td class="table_field_center" onclick="location.href='${pageContext.request.contextPath}/action/admin/athletes/${athlete.idUser}';">${athlete.age}</td> 		
				            <td class="table_field_center" onclick="javascript:removeAthlete(${athlete.idUser});">
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
	<div id="remove_athlete_lbox" class="lBox" title="<fmt:message key='admin.athlete.lbox.remove.tittle'/>">
		<!-- Mensaje -->
		<div class="cajaMensajeTop">
			<div class="cajaMensajeTopLeft"></div>
			<div class="cajaMensajeTopRight"></div>
		</div>
		<div class="mensaje dobleLinea fuenteFormLB">
			<fmt:message key="admin.athlete.lbox.remove.text" />
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
