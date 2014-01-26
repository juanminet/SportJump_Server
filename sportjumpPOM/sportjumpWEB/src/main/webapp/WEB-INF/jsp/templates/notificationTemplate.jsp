<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- QUITAR con prefix -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->
	
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<!-- icono de página -->	
	<link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/ico" />
	
	<link href="${pageContext.request.contextPath}/resources/css/reset.css"	type="text/css" rel="stylesheet" />	
	<link href="${pageContext.request.contextPath}/resources/css/custom.css"	type="text/css" rel="stylesheet" />	
	<link href="${pageContext.request.contextPath}/resources/css/template/standardTemplate.css"	type="text/css" rel="stylesheet" />	
	<link href="${pageContext.request.contextPath}/resources/css/template/notificationTemplate.css"	type="text/css" rel="stylesheet" />	
	
	
	<!-- titulo -->				
	<tiles:useAttribute id="title" name="title"/>
	
	<title>
		<fmt:message key="${title}"/>
	</title>


</head>
<body>
	
	<div class="container">	
		<tiles:useAttribute id="notification_title" name="notification_title" scope="session"/>
		<tiles:useAttribute id="notification_description" name="notification_description" scope="session"/>
		<tiles:useAttribute id="notification_info" name="notification_info" scope="session"/>
					
		
		<div id="header">			
			<tiles:insertAttribute name="header" />
		</div>
		<div id="holder_container" class="holder_container">
			<div id="body_container_single">							
				<div class="cajaNotification">			
							
					 <div class="cajaCenNotification">	
						<fieldset class="form">				
							<div style="  height: 150px; ">							
								<img alt="" src="${pageContext.request.contextPath}/resources/img/bullet_error.png"  style="float:left;">
								
								<div class="form_error_txt">
									<h2><fmt:message key="${notification_title}"/></h2>
									</br>					
									<p><fmt:message key="${notification_description}"/></p>	
									
								</div>			
							</div>
							<div>
								<p align="center"><fmt:message key="${notification_info}"/></p></br>
								
								<p align="center">	
									<a href="<c:url value="/logout" />" class="button">
										<span><fmt:message key="mensaje.volver"/></span>
									</a>										
								</p>	
							</div>			
						</fieldset>
					</div>			
				</div>
			</div>
		</div>
			
		<div id="footer">
			<tiles:insertAttribute name="footer" />	
		</div>
	</div>	

</body>
</html>