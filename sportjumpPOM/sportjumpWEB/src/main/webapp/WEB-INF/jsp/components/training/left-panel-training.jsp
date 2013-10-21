<div id="sidebar" class="sidebar">
	<ul>    	
    	<li><a <c:if test="${section == 'workingday'}">class="active"</c:if> href="${pageContext.request.contextPath}/action/training/day/list"><fmt:message key="training.sidebar.workingday"/></a></li>    	
    	<li><a <c:if test="${section == 'exercise'}">class="active"</c:if> href="${pageContext.request.contextPath}/action/training/exercise"><fmt:message key="training.sidebar.exercise"/></a></li>
    </ul>
    <!-- // .sideNav -->
</div>    
<!-- // #sidebar -->