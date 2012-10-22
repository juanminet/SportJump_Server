<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isErrorPage="true"%>
<%@page import="java.lang.Exception"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="org.slf4j.Logger;"%>
<%!
private static final Logger LOG = LoggerFactory.getLogger("application") ;
%>
<%
	Exception ex = (Exception)(request.getAttribute("exception")!=null?request.getAttribute("exception"):exception);
	if(ex!=null){
		ex.printStackTrace();
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		LOG.error(sw.toString());
		
	}
%>
<div class="boxMensaje">
	<div class="txtMensaje error">
		<h2><b><fmt:message key="error.title"/></b></h2>
		<p><fmt:message key="error.aplicacion"/></p>
		
	</div>				
</div>
<p class="alignRight botones">
	<a href="<c:url value="/action/home" />" class="btn btn-volver">
		<span><fmt:message key="mensaje.volver"/></span>
	</a>
</p>
