<%@page isErrorPage="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<% response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); %>

Hubo un error al arrancar la aplicacion

<%--
Atributos standar que se pueden usar (estan en la request)
javax.servlet.error.message, 
javax.servlet.error.status_code, 
javax.servlet.error.servlet_name, 
javax.servlet.error.exception, 
javax.servlet.error.request_uri

--%>