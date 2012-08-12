<%@tag body-content="empty" pageEncoding="UTF-8" description="Obtiene el valor de una propiedad de configuracion"
%><%@attribute name="name" required="true" type="java.lang.String"  rtexprvalue="true" description="El nombre de la propiedad de configuracion"
%><%@attribute name="escapeXml" required="false" type="java.lang.Boolean" description="Si hay que escapar el valor de Xml"
%><%@tag import="java.util.*,org.springframework.web.context.*,org.springframework.web.context.support.*"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><c:set scope="page" var="ignore">
<%
	// NO TOCAR NI FORMATEAR ESTE TAG.
	// Los cierres de etiquetas a principio de linea son para evitar la aparicion de saltos de linea en el contenido 
	// que pinta el tag
	
	// El siguiente codigo obtiene la configuracion de la aplicacion
	WebApplicationContext ctx = WebApplicationContextUtils
			.getWebApplicationContext(application);
	Properties cfg = (Properties)ctx.getBean("applicationConfig", Properties.class);

	String val = cfg.getProperty(name, "");
	jspContext.setAttribute("__val",val) ;
%>
<c:if test="${! empty escapeXml}"><c:set scope="page" var="__val"><c:out value="${__val}" escapeXml="${escapeXml}"/></c:set></c:if>
</c:set>${__val}