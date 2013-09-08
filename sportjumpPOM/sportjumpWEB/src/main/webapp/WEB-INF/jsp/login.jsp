<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link href="${pageContext.request.contextPath}/resources/css/reset.css"	type="text/css" rel="stylesheet" />	
	<link href="${pageContext.request.contextPath}/resources/css/template/standardTemplate.css"	type="text/css" rel="stylesheet" />	
	
<link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/ico" />
<title>Sport Jump</title>
<style type="text/css">

 form {
    margin: 100px auto auto;
    width: 375px;
}


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
	padding:12px
}
.cajaCen{
	background-color: #EEEEEE;
    padding-left: 4ex;
    padding-right: 4ex;
    padding-top: 4ex;
}
</style>
</head>
<body>



	<div>
		<div <c:if test="${not empty param.login_error}">style="display:none;"</c:if>>
			<form action="j_spring_security_check" method="POST">
				<div id="capaLogin" class="cajaCen">
				<div class="subcaja">
					<h1><fmt:message key="login.form.label.login"/></h1>
				</div>
				<div class="subcaja">		
					<fieldset>										
						<ol>
							<li><label><fmt:message key="login.form.user"/></label><input class="user" id="usuario" name="j_username"  type="text" maxlength="23" size="25"></li>
						   	<li><label><fmt:message key="login.form.password"/></label><input class="password" id="password" name="j_password" type="password" maxlength="23" size="25"></li>
						</ol>						
					
						<p align="center" style="margin-top: 2ex;">
							<input class="button_submit" name="aceptar" id="loginButton" value="<fmt:message key="login.form.aceptar"/>" type="submit">
							<input class="button_submit" name="Registro" id="loginButton" value="<fmt:message key="login.form.registro"/>" type="button" onclick="window.location='registration;'">
						</p>
					
					</fieldset>
					</div>
				</div>			
	
				
			</form>			
		
			
		</div>
		
		<div class="login-error" <c:if test="${empty param.login_error}">style="display:none;"</c:if>>
		
			<fieldset class="form">
				<legend><fmt:message key="login.form.label.error"/></legend>	
				
				<div style="  height: 150px; margin-left: 15px;">							
					<img alt="" src="${pageContext.request.contextPath}/resources/img/bullet_error.png"  style="float:left;">
					
					<div class="form_error_txt"  style="float:left;">
						<h2><fmt:message key="login.error.denied"/></h2>
						<p><fmt:message key="login.error.session"/></p>
					</div>			
				</div>
				<div>
					<p align="center"><fmt:message key="login.error.message"/></p>
					
					<p align="center">							
						<input class="boton" name="Registro" id="loginButton" value="<fmt:message key="login.form.aceptar"/>" type="button" onclick="window.location='login;'">
					</p>	
				</div>			
			</fieldset>
			
		</div>
		
	
	</div>
	
	
</body>
</html>