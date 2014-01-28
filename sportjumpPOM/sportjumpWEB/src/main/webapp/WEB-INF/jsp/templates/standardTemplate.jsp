<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- QUITAR con prefix -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->
	
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<!-- icono de página -->	
	<link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/ico" />
	
	<!-- comunes -->
 	<common:common_stylesheets/>	
	<common:common_javascript/> 

	<!-- jsAditional -->
	<tiles:importAttribute name="jsAditional" ignore="true"/>
	<c:if test="${not empty jsAditional}">
		<c:forEach var="jsFile" items="${jsAditional}">
	<script src="<c:url value="${jsFile}"/>" type="text/javascript" charset="UTF-8"></script>
		</c:forEach>
	</c:if>
	
	<!-- titulo -->				
	<tiles:useAttribute id="title" name="title"/>
	
	<title>
		<fmt:message key="${title}"/>
	</title>
	

</head>
<body>

	<div class="container">	
		<tiles:useAttribute id="layer" name="layer" scope="session"/>
		<tiles:useAttribute id="section" name="section" scope="session"/>					
		
		<div id="header">		
			<tiles:insertAttribute name="header" />
		</div>
		<div id="holder_container" class="holder_container">							
			<c:if test="${section != 'no_section'}">
				<div id="left_container">
					 <tiles:insertAttribute name="left-panel" />
				</div>
				<div id="body_container">				 
					<tiles:insertAttribute name="body" />				
				</div> 
			</c:if>	
			
			<c:if test="${section == 'no_section'}">				
				<div id="body_container_single">				 
					<tiles:insertAttribute name="body" />				
				</div> 
			</c:if>	
			
				
		</div>	
		<div id="footer">
			<tiles:insertAttribute name="footer" />	
		</div>
	</div>	

</body>
</html>