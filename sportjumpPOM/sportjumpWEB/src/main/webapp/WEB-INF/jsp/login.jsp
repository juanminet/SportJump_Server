<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link href="${pageContext.request.contextPath}/resources/css/reset.css"	type="text/css" rel="stylesheet" />	
	<link href="${pageContext.request.contextPath}/resources/css/template/standardTemplate.css"	type="text/css" rel="stylesheet" />	
	
	
<link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/ico" />
<title>Sport Jump</title>
<style type="text/css">



form ol{
	list-style:none;
}
form ol li{
	padding-bottom:5px;
}
form ol li label{
	width:120px;
	float:left;
	text-align:left;
}
fieldset{
	padding:12px;
	margin-top:2ex;
}
.cajaCen{
	background-color: #EEEEEE;
    padding-left: 4ex;
    padding-right: 4ex;
    padding-top: 4ex;
}

.form_error_txt{
	float:left;  
	margin-left: 5ex;     
	margin-top: 5ex; 
	width: 200px;
}
    
#capaLogin{

    margin: 100px auto auto;
    width: 325px;
}

#capaLoginError{
    border: 1px solid #D19290;;
    margin: 100px auto auto;
    width: 400px;
}

</style>
</head>
<body>



	<div>
		<div <c:if test="${not empty param.login_error}">style="display:none;"</c:if>>
			<form action="j_spring_security_check" method="POST">
				<div id="capaLogin" class="cajaCen">
					
					<div id="logo">
						<img src="${pageContext.request.contextPath}/resources/img/header/logo.png">
					</div>
				
					
					<fieldset>										
						<ol>
							<li><label><fmt:message key="login.form.user"/></label><input class="user" id="usuario" name="j_username"  type="text" maxlength="23" size="25"></li>
						   	<li><label><fmt:message key="login.form.password"/></label><input class="password" id="password" name="j_password" type="password" maxlength="23" size="25"></li>
						</ol>						
					
						<p align="center" style="margin-top: 2ex;">
							<input class="button_submit" name="aceptar" id="loginButton" value="<fmt:message key="login.form.aceptar"/>" type="submit">
							<a class="button" href="${pageContext.request.contextPath}/registration"><span><fmt:message key="login.form.registro" /></span></a>							
						</p>
					
					</fieldset>
					
				</div>			
	
				
			</form>			
		
			
		</div>
		
		<div class="login-error" <c:if test="${empty param.login_error}">style="display:none;"</c:if>>
		<div id="capaLoginError" class="cajaCen">
			<fieldset class="form">					
				
				<div style="  height: 150px; ">							
					<img alt="" src="${pageContext.request.contextPath}/resources/img/bullet_error.png"  style="float:left;">
					
					<div class="form_error_txt">
						<h2><fmt:message key="login.error.denied"/></h2>
						</br>
						<c:choose>
							<c:when test="${param.login_error == '1'}">
								<p><fmt:message key="login.error.session"/></p>
							</c:when>
							<c:otherwise>
								<p><fmt:message key="login.error.authorization"/></p>
							</c:otherwise>
						</c:choose>
						
					</div>			
				</div>
				<div>
					<p align="center"><fmt:message key="login.error.message"/></p></br>
					
					<p align="center">							
						<input class="button_submit" name="Registro" id="loginButton" value="<fmt:message key="login.form.aceptar"/>" type="button" onclick="window.location='login;'">
					</p>	
				</div>			
			</fieldset>
			</div>
			
		</div>
		
	
	</div>
	
	
</body>
</html>