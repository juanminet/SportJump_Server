<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

		
		<div id ="body_registration">
			<div id = "body_home_container">		
				<div id="caja_form">	
				 	<form:form commandName="profileCommand" cssClass="caja"  action="registration" method="POST" >
						<div class="subcaja">
					 		<h1>
					 			<fmt:message key="admin.profile.registration" />						 							 		
					 		</h1>	
					 		<h1>
					 			<span style="color:#FF9300;"><fmt:message key="admin.profile.registration.message" /></span>				 		
					 		</h1>		
					 		 	
					 		<a class="button" href="login"><span></span><fmt:message key="admin.button.return"/></span></a>	
					    </div> 
					    <form:errors path="*" cssClass="errorblock" element="div" />
					    
					    <div class="subcaja">
							<fieldset>						
						        <div class="form-row">
						            <label for="userName"><fmt:message key="admin.profile.user.name"/>:</label>
						            <span class="input"><form:input path="userName" size="20"/>   <form:errors path="userName" cssClass="error" /></span>
						        </div>   
						        <div class="form-row">
						            <label for="password"><fmt:message key="admin.profile.password"/>:</label>
						            <span class="input"><form:password path="password" size="20" />   <form:errors path="password" cssClass="error" /></span>
						        </div>  
						        <div class="form-row">
						            <label for="repeatPassword"><fmt:message key="admin.profile.repeat.password"/>:</label>
						            <span class="input"><form:password path="repeatPassword" size="20" />   <form:errors path="repeatPassword" cssClass="error" /></span>
						        </div> 				        
						     </fieldset>
					     </div>	
					     
					    <div class="subcaja">
							<fieldset>						
						        <div class="form-row">
						            <label for="name"><fmt:message key="admin.profile.name"/>:</label>
						            <span class="input"><form:input path="name" size="40"/>   <form:errors path="name" cssClass="error" /></span>
						        </div>   
						        <div class="form-row">
						            <label for="surname"><fmt:message key="admin.profile.surname"/>:</label>
						            <span class="input"><form:input path="surname" size="40"/>   <form:errors path="surname" cssClass="error" /></span>
						        </div>  
						        <div class="form-row">
						            <label for="dni"><fmt:message key="admin.profile.dni"/>:</label>
						            <span class="input"><form:input path="dni" size="8" /> <form:errors path="dni" cssClass="error" /></span>
						        </div>  
						        <div class="form-row">
						            <label for="type"><fmt:message key="admin.profile.type"/>:</label>
						            <span class="input"><form:input path="type" size="20" /> <form:errors path="type" cssClass="error" /></span><br>
						        </div> 
						       
						        <div class="form-row">
						            <label for="dateBirth"><fmt:message key="admin.profile.birth.date"/>:</label>
						            <span class="input"><form:input path="dateBirthDay" size="2" maxlength="2" placeholder="dd"/> /
						            					<form:input path="dateBirthMonth" size="2" maxlength="2" placeholder="mm" /> /
						            					<form:input path="dateBirthYear" size="4" maxlength="4" placeholder="yyyy" />
						            					<form:errors path="dateBirth*" cssClass="error" delimiter=" | " /></span>
						        </div>
						        <div class="form-row">
						            <label for="address"><fmt:message key="admin.profile.address"/>:</label>
						            <span class="input"><form:input path="address" size="70" /> <form:errors path="address" cssClass="error" /></span>
						        </div>
						        <div class="form-row">
						            <label for="telephone"><fmt:message key="admin.profile.telephone"/>:</label>
						            <span class="input"><form:input path="telephone" size="9" /> <form:errors path="telephone" cssClass="error" /></span><br>
						        </div>
						        <div class="form-row">
						            <label for="email"><fmt:message key="admin.profile.email"/>:</label>
						            <span class="input"><form:input path="email" size="40" placeholder="example@domain.com"/> <form:errors path="email" cssClass="error" /></span><br>
						        </div>
						        <div class="form-row">
						            <label for="comments"><fmt:message key="admin.profile.comments"/>:</label>
						            <span class="input"><form:textarea path="comments" cols="73"  disabled="disabled"/></span>		            
						        </div> 
						        
						        <input type="submit" value="<fmt:message key='admin.button.save'/>" class="button_submit">
						     </fieldset>
					     </div>		
					</form:form>
				</div>				
			</div>
		</div>		

