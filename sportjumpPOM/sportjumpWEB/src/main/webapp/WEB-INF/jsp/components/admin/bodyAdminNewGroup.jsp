<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- QUITAR -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script>
$(document).ready( function() {
	initDataTable(
			"table_athletes",
			5);
});
</script>


<div id ="body_home">

	<div id = "body_home_container">


		<h1><fmt:message key="admin.groups.group.data" /></h1>
		<br/>
		<form:form commandName="groupCommand" cssClass="caja"  action="${pageContext.request.contextPath}/action/admin/groups/save" method="POST">
			<fieldset>
				<form:errors path="*" cssClass="errorblock" element="div" />
		        <div class="form-row">
		            <label for="name">Nombre:</label>
		            <span class="input"><form:input path="name" size="40"/>   <form:errors path="name" cssClass="error" /></span>
		        </div>   
		        <div class="form-row">
		            <label for="type">tipo:</label>
		            <span class="input"><form:input path="type" size="20"/>   <form:errors path="type" cssClass="error" /></span>
		        </div>  
		        <div class="form-row">
		            <label for="coachName">Entrenador:</label>
		            <span class="input"><form:label path="coachName" cssClass="second-col">${groupCommand.coachName}</form:label></span><br>
		        </div>         
		        <div class="form-row">
		            <label for="createDate">Fecha creacion:</label>
		           <span class="input"> <form:label path="createDate" size="40"><fmt:formatDate value="${groupCommand.createDate}" pattern="dd/MM/yyyy"/></form:label></span>
		        </div>           
		        <div class="form-row">
		            <label for="name">Descripcion:</label>
		            <span class="input"><form:textarea path="description" cols="73" /></span>
		            <form:errors path="description" cssClass="error" />
		        </div> 
		        <input type="submit" value="Guardar" class="button_submit">
		     </fieldset>
		     
	
		     
		     
		
		</form:form>
				
	
		<h1><fmt:message key="admin.groups.athletes.list" /></h1>
		<br/>
	    <div class="caja">
			<table class="display" id="table_athletes">
		        <thead>
		          <tr>
		            <th><fmt:message key="admin.athletes.table.name" /> </th>
		            <th><fmt:message key="admin.athletes.table.type" /></th>	           
		          </tr>
		        </thead>
		        <tbody>
		          <tr class="odd gradeU">
		            <td>Juan Miguel Muñoz Rondán</td>            
		            <td>Velocista</td>
		          </tr>
		          <tr class="even gradeU">
		            <td>Antonio Lopez Canterano</td>
		            <td>Velocista</td>
		          </tr>
		          <tr class="odd gradeU">
		            <td>Bernabe Martinez Soriano</td>
		            <td>Saltador</td>
		          </tr>
		          <tr class="even gradeU">
		            <td>Mariano Gutierre Olimpo</td>
		            <td>Velocista</td>
		          </tr>
		          <tr class="odd gradeU">
		            <td>Sonia Moreno Antunez</td>
		            <td>Saltador</td>
		          </tr>
		          <tr class="even gradeU">
		            <td>Gonzalo Amar Serrano</td>
		            <td>Velocista</td>
		          </tr>
		        
		        </tbody>
		      </table>  
	      </div>   
      </div>
</div>