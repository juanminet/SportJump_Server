<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link href="${pageContext.request.contextPath}/resources/css/menu_bar.css"	type="text/css" rel="stylesheet" />	
<link href="${pageContext.request.contextPath}/resources/css/header.css"	type="text/css" rel="stylesheet" />
	
<div class="header">

	<div id="header_top">
		<div id="logo">
			<img src="${pageContext.request.contextPath}/resources/img/header/logo.gif">
		</div>
		<div id = profile>
		
			<div class="header_flags">
				<img src="${pageContext.request.contextPath}/resources/img/header/flag_es.gif">
				<img src="${pageContext.request.contextPath}/resources/img/header/flag_en.gif">
			</div>
			<h3 style="margin-top: 10px; margin-bottom: 10px"><fmt:message key="header.welcome"/> ${loggedUser.userName}</h3>	
							
			<div class="header_button" style="margin-right: 0em;">	
				<a href="${pageContext.request.contextPath}/logout"><fmt:message key="header.boton.close_session"/></a>
			</div>
			<div class="header_button">	
				<a href="${pageContext.request.contextPath}/logout"><fmt:message key="header.boton.profile"/></a>
			</div>				
			
		</div>
	</div>
	<div id="header_bottom">
		<div id="nav_bar" class="menu_bar">
			<ul>				
				<li><a href="home"><fmt:message key="header.bar.home"/></a></li>
				<li><a href="training"><fmt:message key="header.bar.trainings"/></a></li>
				<li><a href="admin"><fmt:message key="header.bar.admin"/></a></li>				
			</ul>
		</div>
	</div>
	
	

</div>