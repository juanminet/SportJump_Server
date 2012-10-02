<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/ico" />
<title>Sport Jump</title>
</head>
<body>

<h1>LOGIN</h1>
	<div style="margin:20px;margin-left:400px" >
		<div <c:if test="${not empty param.login_error}">style="display:none;"</c:if>>
			<form action="j_spring_security_check" method="POST">
				<div id="capaLogin" class="cajaCen">
					<fieldset>
						<legend>LOGIN</legend>
						<label for="usuario">
							<span><fmt:message key="login.form.user"/></span>
							<span class="input_home">
								<input class="user" name="j_username" id="usuario" maxlength="23" type="text">
							</span>
						</label>
						
						
						<label for="password">
							<span><fmt:message key="login.form.password"/></span>
							<span class="input_home">
								<input type="password" maxlength="23" id="password" name="j_password" class="password" >
							</span>
						</label>
					</fieldset>
				</div>			
	
				<div class="boton">
					<input class="btn_aceptar" name="aceptar" id="loginButton" value="Aceptar" type="submit">
				</div>
			</form>	
		
			<a href="registration">Registration</a>		
			
		</div>
		
		<div class="login-error" <c:if test="${empty param.login_error}">style="display:none;"</c:if>>
			<div class="txtMensaje error">
				<h2><fmt:message key="login.error.denied"/></h2>
				<p><fmt:message key="login.error.session"/></p>
			</div>
			<p><fmt:message key="login.error.message"/></p>
			<div class="boton">
				<a  class="btn_aceptar"  href="login">Aceptar</a>						
			</div>
		</div>
		
	
	</div>
	
	
</body>
</html>