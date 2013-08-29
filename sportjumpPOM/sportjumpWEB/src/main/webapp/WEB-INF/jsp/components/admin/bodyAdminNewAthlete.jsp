<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- QUITAR -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script>

function show_modify(){
	$("#caja_form").show();
	$("#caja_data").hide();
}
</script>


<div id ="body_home">
	<div id = "body_home_container">		
		<div id="caja_form" <c:if test="${empty newAthlete}">style="display: none;"</c:if>>	
		 	<form:form commandName="athleteCommand" cssClass="caja"  action="${pageContext.request.contextPath}/action/admin/athletes/save" method="POST" >
				<div class="subcaja">
			 		<h1><fmt:message key="admin.athletes.athlete.data" /></h1>
			 		<a class="button" href="${pageContext.request.contextPath}/action/admin/athletes"><fmt:message key="admin.button.return"/></a>
			    </div> 
			    <form:errors path="*" cssClass="errorblock" element="div" />
			    
			    <div class="subcaja">
					<fieldset>						
				        <div class="form-row">
				            <label for="userName"><fmt:message key="admin.athletes.user.name"/>:</label>
				            <span class="input"><form:input path="userName" size="20"/>   <form:errors path="userName" cssClass="error" /></span>
				        </div>   
				        <div class="form-row">
				            <label for="password"><fmt:message key="admin.athletes.password"/>:</label>
				            <span class="input"><form:password path="password" size="20" />   <form:errors path="password" cssClass="error" /></span>
				        </div>  
				        <div class="form-row">
				            <label for="repeatPassword"><fmt:message key="admin.athletes.repeat.password"/>:</label>
				            <span class="input"><form:password path="repeatPassword" size="20" />   <form:errors path="repeatPassword" cssClass="error" /></span>
				        </div> 				        
				     </fieldset>
			     </div>	
			     
			    <div class="subcaja">
					<fieldset>						
				        <div class="form-row">
				            <label for="name"><fmt:message key="admin.athletes.name"/>:</label>
				            <span class="input"><form:input path="name" size="40"/>   <form:errors path="name" cssClass="error" /></span>
				        </div>   
				        <div class="form-row">
				            <label for="surname"><fmt:message key="admin.athletes.surname"/>:</label>
				            <span class="input"><form:input path="surname" size="40"/>   <form:errors path="surname" cssClass="error" /></span>
				        </div>  
				        <div class="form-row">
				            <label for="dni"><fmt:message key="admin.athletes.dni"/>:</label>
				            <span class="input"><form:input path="dni" size="8" /> <form:errors path="dni" cssClass="error" /></span>
				        </div>  
				        <div class="form-row">
				            <label for="type"><fmt:message key="admin.athletes.type"/>:</label>
				            <span class="input"><form:input path="type" size="20" /> <form:errors path="type" cssClass="error" /></span><br>
				        </div> 
				        <div class="form-row">
				            <label for="team"><fmt:message key="admin.athletes.team"/>:</label>
				            <span class="input">
				            	<form:select path="idTeam">
								    <form:option value="0" label="Select One" />
								    <form:options items="${listTeams}" itemValue="idTeam" itemLabel="name" />
								</form:select>
								<form:errors path="idTeam" cssClass="error" />
							</span><br>
				        </div>
				        <div class="form-row">
				            <label for="dateBirth"><fmt:message key="admin.athletes.birth.date"/>:</label>
				            <span class="input"><form:input path="dateBirthDay" size="2" maxlength="2" placeholder="dd"/> /
				            					<form:input path="dateBirthMonth" size="2" maxlength="2" placeholder="mm" /> /
				            					<form:input path="dateBirthYear" size="4" maxlength="4" placeholder="yyyy" />
				            					<form:errors path="dateBirth*" cssClass="error" delimiter=" | " />
				        </div>
				        <div class="form-row">
				            <label for="address"><fmt:message key="admin.athletes.address"/>:</label>
				            <span class="input"><form:input path="address" size="70" /> <form:errors path="address" cssClass="error" /></span>
				        </div>
				        <div class="form-row">
				            <label for="telephone"><fmt:message key="admin.athletes.telephone"/>:</label>
				            <span class="input"><form:input path="telephone" size="9" /> <form:errors path="telephone" cssClass="error" /></span><br>
				        </div>
				        <div class="form-row">
				            <label for="email"><fmt:message key="admin.athletes.email"/>:</label>
				            <span class="input"><form:input path="email" size="40" placeholder="example@domain.com"/> <form:errors path="email" cssClass="error" /></span><br>
				        </div>
				        <div class="form-row">
				            <label for="comments"><fmt:message key="admin.athletes.comments"/>:</label>
				            <span class="input"><form:textarea path="comments" cols="73"  disabled="disabled"/></span>		            
				        </div> 
				        
				        <input type="submit" value="<fmt:message key='admin.button.save'/>" class="button_submit">
				     </fieldset>
			     </div>		
			</form:form>
		</div>
		 
		<div>
			<div id="caja_data" class="caja" <c:if test="${not empty newAthlete}">style="display: none;"</c:if>>
		
			 	<div class="subcaja">
			 		<h1><fmt:message key="admin.groups.group.data" /></h1>
			        <a class="button" href="${pageContext.request.contextPath}/action/admin/athletes/remove/${athleteCommand.idUser}"><fmt:message key="admin.button.remove"/></a>
			 		<div class="button" onclick="javascript:show_modify();"><a><fmt:message key="admin.button.modify"/></a></div>
			 		<a class="button" href="${pageContext.request.contextPath}/action/admin/athletes"><fmt:message key="admin.button.return"/></a>
			    </div> 	
		 
		 	  	<div class="subcaja">
					<fieldset>						
				        <div class="form-row">
				            <label for="userName"><fmt:message key="admin.athletes.user.name"/>:</label>
				            <span class="input"><label>${athleteCommand.userName}</span>
				        </div>   
				        <div class="form-row">
				            <label for="password"><fmt:message key="admin.athletes.password"/>:</label>
				            <span class="input"><label>**********</label></span>
				        </div> 				       		        
				     </fieldset>
			    </div>	
	  			<div class="subcaja">		 	
					<fieldset>	
					
						<div class="form-row">
				            <label for="name"><fmt:message key="admin.athletes.name"/>:</label>
				            <span class="input"><label>${athleteCommand.name}</label></span>
				        </div>   
				        <div class="form-row">
				            <label for="surname"><fmt:message key="admin.athletes.surname"/>:</label>
				            <span class="input"><label>${athleteCommand.surname}</label></span>
				        </div>  
				        <div class="form-row">
				            <label for="dni"><fmt:message key="admin.athletes.dni"/>:</label>
				            <span class="input"><label>${athleteCommand.dni}</label></span>
				        </div>  
				        <div class="form-row">
				            <label for="type"><fmt:message key="admin.athletes.type"/>:</label>
				            <span class="input"><label>${athleteCommand.type}</label></span>
				        </div> 		
				        <div class="form-row">
				            <label for="address"><fmt:message key="admin.athletes.address"/>:</label>
				            <span class="input"><label><fmt:formatDate value="${athleteCommand.dateBirth}" pattern="dd/MM/yyyy"/></label></span>
				        </div>
				        <div class="form-row">
				            <label for="teamName"><fmt:message key="admin.athletes.team"/>:</label>
				            <span class="input"><label>${athleteCommand.nameTeam}</label></span>
				        </div> 
		        	 	<div class="form-row">
				            <label for="address"><fmt:message key="admin.athletes.birth.date"/>:</label>
				            <span class="input"><label>${athleteCommand.address}</label></span>
				        </div>
				        <div class="form-row">
				            <label for="telephone"><fmt:message key="admin.athletes.telephone"/>:</label>
				            <span class="input"><label>${athleteCommand.telephone}</label></span><br>
				        </div>
				        <div class="form-row">
				            <label for="email"><fmt:message key="admin.athletes.email"/>:</label>
				            <span class="input"><label>${athleteCommand.email}</label></span><br>
				        </div>
				        <div class="form-row">
				            <label for="comments"><fmt:message key="admin.athletes.comments"/>:</label>
				            <span class="input"><textarea path="description" cols="73"  disabled="disabled"/>${athleteCommand.comments}</textarea></span>		            
				        </div> 			
					</fieldset>				
		     	</div>		
			</div> 
		</div> 		
	</div>
</div>