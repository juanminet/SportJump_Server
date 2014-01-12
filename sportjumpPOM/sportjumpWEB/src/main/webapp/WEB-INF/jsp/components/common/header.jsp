<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- QUITAR con prefix -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link href="${pageContext.request.contextPath}/resources/css/menu_bar.css"	type="text/css" rel="stylesheet" />	
<link href="${pageContext.request.contextPath}/resources/css/header.css"	type="text/css" rel="stylesheet" />
	
<div class="header">

	<div id="header_top">
		<div id="logo">
			<img src="${pageContext.request.contextPath}/resources/img/header/logo.png">
		</div>
		<div id = profile>	
			<div class="header_flags">
				 <a href="?lang=es_ES"><img src="${pageContext.request.contextPath}/resources/img/header/flag_es.gif"></a>
				<a href="?lang=en"><img src="${pageContext.request.contextPath}/resources/img/header/flag_en.gif"></a>
			</div>
			
			<h3 style="margin-top: 10px; margin-bottom: 10px"><fmt:message key="header.welcome"/> ${loggedUser.userName}</h3>	
							
		<%-- 	<div class="button" style="margin-right: 0em;">	
				<a href="${pageContext.request.contextPath}/logout"><fmt:message key="header.boton.close_session"/></a>
			</div>
			<div class="button">	
				<a href="${pageContext.request.contextPath}/logout"><fmt:message key="header.boton.profile"/></a>
			</div>				
			 --%>
		</div>
	</div>

	<div id="header_bottom">
		<div id="nav_bar" class="menu_bar">
			<ul>		
					
				<li><a <c:if test="${layer == 'home'}">class="active"</c:if> href="${pageContext.request.contextPath}/action/home"><fmt:message key="header.bar.home"/></a></li>
				<li><a <c:if test="${layer == 'planning'}">class="active"</c:if>href="${pageContext.request.contextPath}/action/planning/group/list"><fmt:message key="header.bar.planning"/></a></li>
				<li><a <c:if test="${layer == 'training'}">class="active"</c:if>href="${pageContext.request.contextPath}/action/training/day/list"><fmt:message key="header.bar.trainings"/></a></li>
				<li><a <c:if test="${layer == 'admin'}">class="active"</c:if> href="${pageContext.request.contextPath}/action/admin"><fmt:message key="header.bar.admin"/></a></li>	
				<li class="logout"><a href="${pageContext.request.contextPath}/logout"><fmt:message key="header.boton.close_session"/></a></li>
				<li class="logout"><a <c:if test="${layer == 'profile'}">class="active"</c:if> href="${pageContext.request.contextPath}/action/admin/profile"><fmt:message key="header.boton.profile"/></a></li>		
								
			</ul>
		</div>
	</div>
	
	

</div>