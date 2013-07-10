<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/ico" />
<title>Insert title here</title>
</head>
<body>

	<h1>HOME</h1>
	
	<h2>Bienvenido  ${loggedUser.userName} </h2>
	
	<label>Nombre: ${loggedUser.name}</label> <br/>
	<label>Apellidos: ${loggedUser.surname}</label> <br/>
	<label>Email: ${loggedUser.email}</label> <br/>
	
	
	
	<a class="logout" href="${pageContext.request.contextPath}/logout">Cerrar sesión</a>

</body>
</html>