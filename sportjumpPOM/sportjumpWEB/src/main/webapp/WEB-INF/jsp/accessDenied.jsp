<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="boxMensaje">
	<div class="txtMensaje error">
		<h2><b><fmt:message key="mensaje.accesoDenegado.title"/></b></h2>
		<p><fmt:message key="mensaje.accesoDenegado.text"/></p>
		
	</div>				
</div>
<form class="alignRight" action="${pageContext.request.contextPath}/ibm_security_logout">
 	<div>
  		<input type="hidden" name="logoutExitPage" value="/action/home"/>
 		<span class="btn"><input type="submit" name="logout" value="<fmt:message key="link.logout"/>"/></span>
	</div>
</form>