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
	<link rel="SHORTCUT ICON" href="favicon.ico" type="image/x-icon" />
	
	<!-- comunes -->
<%-- <common:common_stylesheets/>	
	<common:common_javascript/> --%>	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-1.10.3/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-1.10.3/js/jquery-ui-1.10.3.custom.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-1.10.3/js/jquery.balloon.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/data-table-1.9.4/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/customDataTable.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/components/fullcalendar/fullcalendar.js"></script>




	<link href="${pageContext.request.contextPath}/resources/data-table-1.9.4/css/demo_table_jui.css"	type="text/css" rel="stylesheet" />	
	<link href="${pageContext.request.contextPath}/resources/jquery-1.10.3/css/smoothness/jquery-ui-1.10.3.custom.css"	type="text/css" rel="stylesheet" />	

	<link href="${pageContext.request.contextPath}/resources/css/reset.css"	type="text/css" rel="stylesheet" />	
	<link href="${pageContext.request.contextPath}/resources/css/custom.css"	type="text/css" rel="stylesheet" />	
	<link href="${pageContext.request.contextPath}/resources/css/template/standardTemplate.css"	type="text/css" rel="stylesheet" />	
	
	<link href="${pageContext.request.contextPath}/resources/components/fullcalendar/fullcalendar.css"	type="text/css" rel="stylesheet" />	

	
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