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

<h1>REGISTRATION</h1>
	
		
		<div class="registration" style="color:#FF9300;margin-left:-45px; margin-bottom:15px">	¿Todavía no tienes usuario en Sport Jump? Regístrate.</div>
		<form action="registration" method="POST">
			<table>
				<tr>
					<td>
						<label for="user">Nombre usuario:</label>
					</td>
					<td>
						<input style="width:250px" type="text" name="user" id="user" >
					</td>
				</tr>
				<tr>
					<td>
						<label for="password">Contraseña:</label>
					</td>
					<td>
						<input style="width:250px" type="password" name="password" id="password" >
					</td>
				</tr>
				
				<tr>
					<td>
						<label for="repeatpassword">Repetir contraseña:</label>
					</td>
					<td>
						<input style="width:250px" type="password" name="repeatpassword" id="repeatpassword" >
					</td>
				</tr>
				
				<tr>
					<td>
						<label for="user">Nombre:</label>
					</td>
					<td>
						<input style="width:250px" type="text" name="name" id="name" >
					</td>
				</tr>
				
				<tr>
					<td>
						<label for="user">Apellidos:</label>
					</td>
					<td>
						<input style="width:250px" type="text" name="surname" id="surname" >
					</td>
				</tr>
				<tr>
					<td>
						<label for="dni">Dni:</label>
					</td>
					<td>
						<input style="width:250px" type="text" name="dni" id="dni" >
					</td>
				</tr>
				<tr>
					<td>
						<label for="email">Email:</label>
					</td>
					<td>
						<input style="width:250px" type="text" name="email" id="email" >
					</td>
				</tr>
				
				<tr>
					<td>
						
					</td>
					<td>
						<input style="width:150px" type="submit" value="Registrarse"> 	
					</td>
				</tr>
			</table>
		 </form>
	</div>
</body>
</html>