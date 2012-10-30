<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/ico" />
<title>Sport Jump</title>
<style type="text/css">
body {
	font-family:Verdana, Arial, Helvetica, sans-serif;
	font-size:11px;
	color:#333333;
}
/* BOF formulario 1 */
.form {
	border:1px solid #990000;
	width:450px;
	margin:auto;
}
.form legend{
	font-weight:bold;
	font-size:12px;
}
.form ol{
	list-style:none;
}
.form ol li{
	padding-bottom:5px;
}
.form ol li label{
	width:120px;
	float:left;
	text-align:left;
}
.form input[type=text] {
	border:1px solid #CCCCCC;
}
.form input.boton {
	padding:3px;
	color:#FFFFFF;
	background-color:#990000;
	border:1px solid #000000;
	cursor: pointer;
}

.form  .form_error_txt{
margin: 40px;
}

</style>
</head>
<body>



	<div>
		<div <c:if test="${not empty param.login_error}">style="display:none;"</c:if>>
			<form action="j_spring_security_check" method="POST">
				<div id="capaLogin" class="cajaCen">
					<fieldset class="form">
						<legend><fmt:message key="login.form.label.login"/></legend>						
						<ol>
							<li><label><fmt:message key="login.form.user"/></label><input class="user" id="usuario" name="j_username"  type="text" maxlength="23" size="25"></li>
						   	<li><label><fmt:message key="login.form.password"/></label><input class="password" id="password" name="j_password" type="password" maxlength="23" size="25"></li>
						</ol>						
					
						<p align="center">
							<input class="boton" name="aceptar" id="loginButton" value="<fmt:message key="login.form.aceptar"/>" type="submit">
							<input class="boton" name="Registro" id="loginButton" value="<fmt:message key="login.form.registro"/>" type="button" onclick="window.location='registration;'">
						</p>
					
					</fieldset>
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