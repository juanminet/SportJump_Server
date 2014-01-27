<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id ="body_home">
	<div id = "body_home_container">			
		<div id="caja_form" <c:if test="${empty newTeam}">style="display: none;"</c:if>>	
		 	<form:form commandName="groupCommand" cssClass="caja"  action="${pageContext.request.contextPath}/action/admin/groups/save" method="POST" >
				<div class="subcaja">
			 		<h1><fmt:message key="admin.groups.group.data" /></h1>
			 		<a class="button" href="${pageContext.request.contextPath}/action/admin/groups"><span><fmt:message key="admin.button.return"/></span></a>
			    </div> 
			    <div class="subcaja">
					<fieldset>
						<form:errors path="*" cssClass="errorblock" element="div" />
				        <div class="form-row">
				            <label for="name"><fmt:message key="admin.groups.group.name"/>:</label>
				            <span class="input"><form:input path="name" size="40"/>   <form:errors path="name" cssClass="error" /></span>
				        </div>   
				        <div class="form-row">
				            <label for="type"><fmt:message key="admin.groups.group.type"/>:</label>
				            <span class="input"><form:input path="type" size="20"/>   <form:errors path="type" cssClass="error" /></span>
				        </div>  
				        <div class="form-row">
				            <label for="coachName"><fmt:message key="admin.groups.group.coach"/>:</label>
				            <span class="input"><form:label path="coachName" cssClass="second-col">${groupCommand.coachName}</form:label></span><br>
				        </div>         
				        <div class="form-row">
				            <label for="createDate"><fmt:message key="admin.groups.group.date.create"/>:</label>
				           <span class="input"> <form:label path="createDate" size="40"><fmt:formatDate value="${groupCommand.createDate}" pattern="dd/MM/yyyy"/></form:label></span>
				        </div>           
				        <div class="form-row">
				            <label for="name"><fmt:message key="admin.groups.group.description"/>:</label>
				            <span class="input"><form:textarea path="description" cols="73" /></span>				            
				        </div> 
				        <input type="submit" value="<fmt:message key='admin.button.save'/>" class="button_submit">
				     </fieldset>
			     </div>		
			</form:form>
		</div>
		 
		 <div>
		 <div id="caja_data" class="caja" <c:if test="${not empty newTeam}">style="display: none;"</c:if>>
		
		 	<div class="subcaja">
		 		<h1><fmt:message key="admin.groups.group.data" /></h1>
		        <a class="button" href="${pageContext.request.contextPath}/action/admin/groups/remove/${groupCommand.idTeam}"><span><fmt:message key="admin.button.remove"/></span></a>
		 		<div onclick="javascript:show_modify();"><a class="button"><span><fmt:message key="admin.button.modify"/></span></a></div>
		 		<a class="button" href="${pageContext.request.contextPath}/action/admin/groups"><span><fmt:message key="admin.button.return"/></span></a>
		    </div> 	
		 
		 	
		  	<div class="subcaja">		 	
				<fieldset>				 			
			        <div class="form-row">
			            <label for="name"><fmt:message key="admin.groups.group.name"/>:</label>
			           <span class="input"><label>${groupCommand.name}</label></span>
			        </div>   
			        <div class="form-row">
			            <label for="type"><fmt:message key="admin.groups.group.type"/>:</label>
			            <span class="input"><label>${groupCommand.type}</label></span>
			        </div>  
			        <div class="form-row">
			            <label for="coachName"><fmt:message key="admin.groups.group.coach"/>:</label>
			            <span class="input"><label class="second-col">${groupCommand.coachName}</label></span>
			        </div>         
			        <div class="form-row">
			            <label for="createDate"><fmt:message key="admin.groups.group.date.create"/>:</label>
			           <span class="input"> <label><fmt:formatDate value="${groupCommand.createDate}" pattern="dd/MM/yyyy"/></label></span>
			        </div>           
			        <div class="form-row">
			            <label for="name"><fmt:message key="admin.groups.group.description"/>:</label>
			            <span class="input"><textarea path="description" cols="73"  disabled="disabled">${groupCommand.description}</textarea></span>		            
			        </div> 		        
			     </fieldset>
		     </div>		
		 </div> 
		</div> 
				
	
		<div class="caja" <c:if test="${not empty newTeam}">style="display: none;"</c:if>>
	   		<div class="subcaja">
		 		<h1><fmt:message key="admin.groups.athletes.list" /></h1>
		    </div> 	
		    
		    <table class="display" id="table_athletes">
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
			        	<tr class="${rowStyle}  gradeU" onclick="location.href='${pageContext.request.contextPath}/action/admin/athletes/${athlete.idUser}';"> 
				             <td>${athlete.completeName}</td>            
				            <td>${athlete.dni}</td>
				            <td>${athlete.team.name}</td>
				            <td class="center">${athlete.age}</td> 				         
			        	</tr>
		        	</c:forEach>	         
		    	</tbody>
			</table>
		</div>   
	</div>
</div>