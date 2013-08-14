<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- QUITAR -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>




</style>

<script>


	
$(document).ready( function() {

	initDataTableButton(
			"table_groups",
			"${pageContext.request.contextPath}/action/admin/groups/new", 
			'<fmt:message key="admin.groups.table.new.group" />', 
			10);
	  	
}); 
	
	
	
</script>
    
<div id ="body_home">



	<div id = "body_home_container" >
	
		<h1><fmt:message key="admin.groups.tittle" /></h1>
		<br/>
	    <div class="caja">
		<table class="display" id="table_groups">
	        <thead>
	          <tr>
	            <th><fmt:message key="admin.groups.table.name" /> </th>
	            <th><fmt:message key="admin.groups.table.type" /></th>
	            <th><fmt:message key="admin.groups.table.athletes.tam" /></th>
	          </tr>
	        </thead>
	        <tbody>
	          <tr class="odd gradeU">
	            <td>Trident</td>            
	            <td>Win 95+</td>
	            <td class="center"> 4</td>
	          </tr>
	          <tr class="even gradeU">
	            <td>Kelia</td>
	            <td>Win 95+</td>
	            <td class="center">5</td>
	          </tr>
	          <tr class="odd gradeU">
	            <td>Kelia</td>
	            <td>Win 95+</td>
	            <td class="center">5.5</td>
	          </tr>
	          <tr class="even gradeU">
	            <td>Trident</td>
	            <td>Win 98+</td>
	            <td class="center">6</td>
	          </tr>
	          <tr class="odd gradeU">
	            <td>Trident</td>
	            <td>Win XP SP2+</td>
	            <td class="center">7</td>
	          </tr>
	          <tr class="even gradeU">
	            <td>Trident</td>
	            <td>Win XP</td>
	            <td class="center">6</td>
	          </tr>
	        
	        </tbody>
	      </table>
      </div>
      
      </div>
</div>
