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
		 		<h1><fmt:message key="admin.groups.list.groups" /></h1>
		 		<a class="button" href="${pageContext.request.contextPath}/action/admin/groups/new"><span><fmt:message key="admin.button.new.group" /></span></a>
		    </div> 
		    
		    
			<table class="display" id="table_groups">
		    	<thead>
		        	<tr>
		            	<th><fmt:message key="admin.groups.table.name" /> </th>
		            	<th><fmt:message key="admin.groups.table.type" /></th>
		            	<th class="table_field_center"><fmt:message key="admin.groups.table.athletes.tam" /></th>		            	
		            	<th class="table_field_center"><fmt:message key="admin.groups.table.remove" /></th>		            	
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
			        	<tr class="${rowStyle}  gradeU">
				            <td onclick="location.href='${pageContext.request.contextPath}/action/admin/groups/${team.idTeam}';">${team.name}</td>            
				            <td onclick="location.href='${pageContext.request.contextPath}/action/admin/groups/${team.idTeam}';">${team.type}</td>
				            <td class="table_field_center" onclick="location.href='${pageContext.request.contextPath}/action/admin/groups/${team.idTeam}';">${team.size}</td>
				            <td class="table_field_center" onclick="javascript:removeGroup(${team.idTeam});">
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
	<div id="remove_group_lbox" class="lBox" title="<fmt:message key='admin.group.lbox.remove.tittle'/>">
		<!-- Mensaje -->
		<div class="cajaMensajeTop">
			<div class="cajaMensajeTopLeft"></div>
			<div class="cajaMensajeTopRight"></div>
		</div>
		<div class="mensaje dobleLinea fuenteFormLB">
			<fmt:message key="admin.group.lbox.remove.text" />
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
