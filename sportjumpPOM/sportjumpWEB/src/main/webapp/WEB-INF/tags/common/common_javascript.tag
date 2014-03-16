<%@tag body-content="empty" pageEncoding="UTF-8"	description="Pone los js compartidos"%>
<%@attribute name="path" required="false" type="java.lang.Boolean" description="Ruta relativa del recurso comun"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="baseName" scope="page" value="web.resources.url.${pageContext.request.scheme}"/>
<c:set var="base" scope="page" value="${cfg[baseName]}"/>
<c:if test="${fn:startsWith(base,'@')}"><c:set var="base" value="${pageContext.request.contextPath}/${fn:substring(base,1,-1)}"/></c:if>
<c:choose>
	<c:when test="${empty path}">
	<%-- APPLICATION JAVASCRIPT RESOURCES --%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-1.10.3/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-1.10.3/js/jquery-ui-1.10.3.custom.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-1.10.3/js/jquery.balloon.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/data-table-1.9.4/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/customDataTable.js"></script>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/components/fullcalendar/fullcalendar.js"></script>
	
	</c:when>
	<c:otherwise>
		<script src="${base}/${path}" type="text/javascript" charset="UTF-8"></script>
	</c:otherwise>	
</c:choose>