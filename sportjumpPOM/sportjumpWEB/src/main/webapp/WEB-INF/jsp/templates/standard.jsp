<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><fmt:message key="mensaje.title"/></title>
<link rel="shortcut icon" href="${cfg['web.resources.url.http']}/img/mutuaMad.ico" type="image/x-icon" />
<common:common_stylesheets/>
<link rel="stylesheet" href="<c:url value="/recursos/css/custom.css" />" type="text/css"/>

<common:common_javascript/>

<tiles:importAttribute name="dwrEngine" toName="dwr" ignore="true"/>
<c:choose>
	<c:when test="${empty pageScope.dwr || pageScope.dwr == 'false'}"><!-- NO DWR --></c:when>
	<c:when test="${pageScope.dwr == 'true'}"><!-- DEFAULT DWR --><jsp:include page="/WEB-INF/includes/dwrEngine.jsp" /></c:when>
	<c:otherwise><!-- CUSTOM DWR --><tiles:insertAttribute name="dwrEngine" ignore="true"/></c:otherwise>
</c:choose>

<script type="text/javascript">
	var contextPath="${pageContext.request.contextPath}";
	var resourcesPath="${cfg['web.resources.url.http']}";
	var maxFileSize = "${cfg['dwr.upload.fileSize']}";
</script>			

</head>
<body>
	<div id="contCabecera">
		<p id="logo"><strong>
		<a href="http://${cfg['domain.web.mutua']}">
		<img alt="<fmt:message key="mensaje.logo.mutua"/>" src="${cfg['web.resources.url.http']}/img/logo-mutuamad.png"></img></a>		
		</strong></p>
		<h1>
			<strong>extranet</strong>
			<img alt="" src="${cfg['web.resources.url.http']}/img/separador.gif" class="separador"/>
			<em><fmt:message key="mensaje.title"/></em>
		</h1>
		<c:if test="${not empty pageContext.request.userPrincipal.name}">
			<p class="usuario">
				<strong>Usuario:</strong>
				${pageContext.request.userPrincipal.name}
			</p>
		</c:if>
		<p class="version">
			<strong>Versión:</strong>
			${cfg['environment']} - ${cfg['version']}
		</p>
	</div>

	<div id="contenidoCentral">
		<div id="contenidoIzq">
			<div class="menu">
				<tiles:insertAttribute name="menu"/>
			</div>
		</div>

		<div id="contenidoDer">
			<tiles:insertAttribute name="body"/>
		</div><!--contenidoDer-->
	</div>

</body>
</html>
	   
   		