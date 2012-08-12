<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type='text/javascript' src='<c:url value="/dwr/engine.js" />'></script>


<script type='text/javascript'>

	alert("dwr cargado");
	
	//Handler que controla el fin de sesión
	/* dwr.engine.setTextHtmlHandler(
		function(resp) {
			if (resp.responseText.indexOf("j_security_check") != -1) {
				dwr.engine.setNotifyServerOnPageUnload(false);
				alert("Su sesión ha cadudado, se le va a redirigir a la pantalla de login");
				document.location = '${pageContext.request.contextPath}/';
			}
	}); */
</script>