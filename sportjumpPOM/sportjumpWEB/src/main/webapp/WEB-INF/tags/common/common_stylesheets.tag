<%@tag body-content="empty" pageEncoding="UTF-8" description="Pone los css compartidos"%>
<%@attribute name="path" required="false" type="java.lang.Boolean" description="Ruta relativa del recurso comun"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="baseName" scope="page" value="web.resources.url.${pageContext.request.scheme }"/>
<c:set var="base" scope="page" value="${cfg[baseName]}"/>
<c:if test="${fn:startsWith(base,'@')}">
	<c:set var="base" value="${pageContext.request.contextPath}/${fn:substring(base,1,-1)}" />
</c:if>
<c:choose>
	<c:when test="${empty path}">
		<%-- APPLICATION JAVASCRIPT RESOURCES --%>		
		<link href="${pageContext.request.contextPath}/resources/data-table-1.9.4/css/demo_table_jui.css"	type="text/css" rel="stylesheet" />	
		<link href="${pageContext.request.contextPath}/resources/jquery-1.10.3/css/smoothness/jquery-ui-1.10.3.custom.css"	type="text/css" rel="stylesheet" />	
	
		<link href="${pageContext.request.contextPath}/resources/css/reset.css"	type="text/css" rel="stylesheet" />	
		<link href="${pageContext.request.contextPath}/resources/css/custom.css"	type="text/css" rel="stylesheet" />	
		<link href="${pageContext.request.contextPath}/resources/css/template/standardTemplate.css"	type="text/css" rel="stylesheet" />	
		
		<link href="${pageContext.request.contextPath}/resources/components/fullcalendar/fullcalendar.css"	type="text/css" rel="stylesheet" />	
	</c:when>
	<c:otherwise>
		<link href="${base}/${path}" rel="stylesheet" type="text/css" />
	</c:otherwise>
</c:choose>
