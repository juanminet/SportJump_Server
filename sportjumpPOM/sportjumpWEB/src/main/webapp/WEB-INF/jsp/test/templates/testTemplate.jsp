<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->
	
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<!-- icono de página -->	
	<link rel="SHORTCUT ICON" href="favicon.ico" type="image/x-icon" />
	
	<!-- comunes -->
<%--  <common:common_stylesheets/>	
	<common:common_javascript/>  --%>
	

	
	<!-- titulo -->	
	<tiles:useAttribute id="title" name="title"/>
	
	<title>
		<fmt:message key="${title}"/>
	</title>
	

	
	
	


</head>
<body>
	
	<div class="container">	
		<tiles:insertAttribute name="header" />
		
		<div class="content" style="min-height: 435px;">
			<div class="content_container">
				<tiles:insertAttribute name="body" />	
				
			</div>
		</div>
		
		<tiles:insertAttribute name="footer" />	
	</div>	

</body>
</html>