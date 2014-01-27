<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
	 var CONTEXT_PATH = "${pageContext.request.contextPath}";
	 var ID_GROUP = "${idGroup}";
	 
	 var MSG_EVENT_DUPLICATED = "<fmt:message key='planning.calendar.event.error.duplicated' />";
	 var MSG_LBOX_NAME = "<fmt:message key='planning.calendar.lbox.name'/>: ";
	 var MSG_LBOX_DATE = "<fmt:message key='planning.calendar.lbox.date'/>: ";
	 var MSG_LBOX_TRAINING = "<fmt:message key='planning.calendar.lbox.training'/>: ";
	 var MSG_LBOX_BUTTON_CLOSE = "<fmt:message key='planning.calendar.lbox.button.close'/>";
	 var MSG_LBOX_BUTTON_REMOVE = "<fmt:message key='planning.calendar.lbox.button.remove'/>";
	 var MSG_LBOX_BUTTON_CANCEL = "<fmt:message key='planning.calendar.lbox.button.cancel'/>";
	 var MSG_LBOX_BUTTON_CONTINUE = "<fmt:message key='planning.calendar.lbox.button.continue'/>";
 </script>

<style>
		

</style>
    
<div id ="body_home">
	<div id = "body_home_container" >			
	    <div class="caja">
	    
	   		<div class="subcaja">
		 		<h1><fmt:message key="planning.calendar.tittle" /></h1>
		 		<a class="button" href="${pageContext.request.contextPath}/action/planning/group/list"><span><fmt:message key="planning.calendar.button.back" /></span></a>
		    </div>

			<div id='wrap'>

				<div id='external-events'>
					<h4><fmt:message key="planning.calendar.entrenamientos" /></h4>
					<c:forEach items="${trainingDayList}" var="training">
						<div class='external-event'>
							<div class="name">${training.name}</div>
							<div class="id" hidden="true">${training.idTraining}</div>
						</div>
					</c:forEach>					
				</div>

				<div id='calendar'></div>

				<div style='clear: both'></div>
			</div>
		</div>      
      </div>
      
      
      <!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ LIGHTTBOXES /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->


<!-- lightbox for modify dialog -->
<div id="modify_event_lbox" class="lBox" title="<fmt:message key='planning.calendar.lbox.modify.tittle'/>">
	<!-- Mensaje -->
	<div class="cajaMensajeTop">
		<div class="cajaMensajeTopLeft"></div>
		<div class="cajaMensajeTopRight"></div>
	</div>
	<div class="mensaje dobleLinea fuenteFormLB">
		<fmt:message key="planning.calendar.lbox.modify.text" />
	</div>
	<!--mensaje-->
	<div class="cajaMensajePie">
		<div class="cajaMensajePieLeft"></div>
		<div class="cajaMensajePieRight"></div>
	</div>
	<!-- Mensaje -->	
	
</div>



<!-- lightbox for show exercises -->
<div id="show_training_lbox" class="lBox" title="<fmt:message key='planning.calendar.lbox.show.tittle'/>">
	<!-- Mensaje -->
	<div class="cajaMensajeTop">
		<div class="cajaMensajeTopLeft"></div>
		<div class="cajaMensajeTopRight"></div>
	</div>
	<div class="mensaje dobleLinea fuenteFormLB">
		<div class="single_row">
			<div id="lbox_show_container">
				
			</div>
		</div>
	</div>
	<!--mensaje-->
	<div class="cajaMensajePie">
		<div class="cajaMensajePieLeft"></div>
		<div class="cajaMensajePieRight"></div>
	</div>
	<!-- Mensaje -->	
	
</div>


<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ fin LIGHTTBOXES /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
<!-- /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ *********** /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ -->
</div>
