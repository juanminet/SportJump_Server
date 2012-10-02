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
		<%-- AQUI VAN LOS RECURSOS REQUERIDOS POR LA APLICACION --%>
		<link href="${base}/css/reset.css" type="text/css" charset="UTF-8" rel="stylesheet"></link>
		<link href="${base}/css/appestilos.css" type="text/css" charset="UTF-8" rel="stylesheet"></link>
		<link href="${base}/css/jquery-timepicker/jquery.timepicker.css" rel="stylesheet" type="text/css" />
		<!--[if lte IE 7]>		
			<link rel="stylesheet" href="${base}/css/estilos-ie.css" type="text/css" media="screen, projection" />
		<![endif]-->
	</c:when>
	<c:otherwise>
		<link href="${base}/${path}" rel="stylesheet" type="text/css" />
	</c:otherwise>
</c:choose>
