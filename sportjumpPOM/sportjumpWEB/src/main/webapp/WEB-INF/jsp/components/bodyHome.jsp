<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.body_home_header{
	text-align: center;
}
.body_home_container{
	text-align: center;
	line-height: 1.5em;
	 margin: 2em;
}
.body_home_img{
	height: 20em;
	margin: 2em;
}
</style>
<div id ="body_home">
	<div class="body_home_header">
		<img class="body_home_img" src="${pageContext.request.contextPath}/resources/img/carrera.jpg">
	</div>
	<div class="body_home_container">
		<fmt:message key= "home.mensaje.p1" />
	</div>

</div>
