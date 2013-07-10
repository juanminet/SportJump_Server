<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
	
	<!-- dwr -->
	<tiles:importAttribute name="dwrEngine" toName="dwr" ignore="true"/>
	<c:choose>
		<c:when test="${empty pageScope.dwr || pageScope.dwr == 'false'}"><!-- NO DWR --></c:when>
		<c:when test="${pageScope.dwr == 'true'}"><!-- DEFAULT DWR --><jsp:include page="/WEB-INF/includes/dwrEngine.jsp" /></c:when>
		<c:otherwise><!-- CUSTOM DWR --><tiles:insertAttribute name="dwrEngine" ignore="true"/></c:otherwise>
	</c:choose>
	
	
	<!-- titulo -->	
	<tiles:useAttribute id="title" name="title"/>
	
	<title>
		<fmt:message key="${title}"/>
	</title>
	
	<link href="${pageContext.request.contextPath}/resources/css/custom.css"	type="text/css" rel="stylesheet" />	
	<link href="${pageContext.request.contextPath}/resources/css/template/standardTemplate.css"	type="text/css" rel="stylesheet" />	
	

</head>
<body>
	
	<div class="container">	
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		<div id="center-content" class="content">
			<div class="content_container">
				<tiles:insertAttribute name="body" />				
			</div>
		</div>		
		<div id="footer">
			<tiles:insertAttribute name="footer" />	
		</div>
	</div>	

</body>
</html>