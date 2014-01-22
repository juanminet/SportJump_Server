<div id="sidebar" class="sidebar">
	<ul>
    	<li><a <c:if test="${section == 'groups'}">class="active"</c:if> href="${pageContext.request.contextPath}/action/admin/groups"><fmt:message key="admin.sidebar.groups"/></a></li>
    	<li><a <c:if test="${section == 'athletes'}">class="active"</c:if> href="${pageContext.request.contextPath}/action/admin/athletes"><fmt:message key="admin.sidebar.athletes"/></a></li>
    </ul>
    <!-- // .sideNav -->
</div>    
<!-- // #sidebar -->