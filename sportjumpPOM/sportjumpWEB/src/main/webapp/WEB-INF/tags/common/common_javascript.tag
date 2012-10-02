<%@tag body-content="empty" pageEncoding="UTF-8"	description="Pone los js compartidos"%>
<%@attribute name="path" required="false" type="java.lang.Boolean" description="Ruta relativa del recurso comun"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="baseName" scope="page" value="web.resources.url.${pageContext.request.scheme}"/>
<c:set var="base" scope="page" value="${cfg[baseName]}"/>
<c:if test="${fn:startsWith(base,'@')}"><c:set var="base" value="${pageContext.request.contextPath}/${fn:substring(base,1,-1)}"/></c:if>
<c:choose>
<c:when test="${empty path}">
<%-- AQUI VAN LOS RECURSOS REQUERIDOS POR LA APLICACION --%>
<script src="${base}/js/jquery.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${base}/js/jquery-ui.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${base}/js/jquery.ui.datepicker-es.js" type="text/javascript" charset="UTF-8"></script>
<script src="${base}/js/jquery.dataTables.min.js" type="text/javascript"></script>


<!--[if lte IE 6]>
	<script src="${base}/js/DD_belatedPNG.js" type="text/javascript"></script>
	<script src="${base}/js/form.js" type="text/javascript"></script>
<![endif]-->
</c:when>
<c:otherwise>
<script src="${base}/${path}" type="text/javascript" charset="UTF-8"></script>
</c:otherwise>	
</c:choose>