<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<%@ attribute name="title" required="true" %>

<notes:page>
	<jsp:attribute name="title">${title}</jsp:attribute>
	<jsp:attribute name="navLinks">
		<ul class="nav">
			<li><a href="${contextPath}/home/view">Home</a></li>
			<li><a href="${contextPath}/search/view">Search</a></li>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="${contextPath}/admin/view">Admin</a></li>
			</sec:authorize>
		</ul>
		<ul class="nav pull-right">
			<li><a href="${contextPath}/logout">Logout ${user.name}</a></li>
		</ul>
	</jsp:attribute>
	<jsp:body>
		<jsp:doBody/>
	</jsp:body>
</notes:page>