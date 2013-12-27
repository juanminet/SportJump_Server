<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- QUITAR -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>




</style>

<script>


	
$(document).ready( function() {

	
	 // page is now ready, initialize the calendar...

	 var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
 	 
    $('#calendar').fullCalendar({
    	theme:true,
    	editable:true,

        dayClick: function() {
            alert('a day has been clicked!');
        },
    	events: [
	 				{
	 					title: 'All Day Event',
	 					start: new Date(y, m, 1)
	 				},
	 				{
	 					title: 'Long Event',
	 					start: new Date(y, m, d-5),
	 					end: new Date(y, m, d-2)
	 				}
				]
    	
    });

	  	
}); 	

	
</script>
    
<div id ="body_home">
	<div id = "body_home_container" >			
	    <div class="caja">
	    
	   		<div class="subcaja">
		 		<h1><fmt:message key="admin.groups.list.groups" /></h1>
		 		<a class="button" href="${pageContext.request.contextPath}/action/admin/groups/new"><span><fmt:message key="admin.button.new.group" /></span></a>
		    </div> 
		    
		    <div id='calendar'></div>
		    
			
	      </div>      
      </div>
</div>
