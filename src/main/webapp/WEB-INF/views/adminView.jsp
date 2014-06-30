<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<notes:authPage>
	<jsp:attribute name="title">Notes - Admin</jsp:attribute>
	<jsp:body>
		<div class="container-fluid">
			<div class="admin-user-list">
                <c:if test="${!empty errorMessage}">
                    <div class="alert alert-error">${errorMessage}</div>
                </c:if>
                
                <c:if test="${!empty successMessage}">
                    <div class="alert alert-success">${successMessage}</div>
                </c:if>
                
                <h1 class="note-header">Manage Users</h1>
                
				<table class="table table-striped table-condensed table-bordered">
					<thead>
						<tr>
							<th>Username</th>
							<th>Enabled</th>
							<th>Admin</th>
                            <th>Options</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user">
							<tr>
								<td>${user.username}</td>
								<td>${user.enabled? 'Yes' : 'No'}</td>
								<td>${user.authority == 'ROLE_ADMIN'? 'Yes' : 'No'}</td>
                                <td>
                                    <c:url var="deleteUrl" value="/admin/delete-user">
                                        <c:param name="userIds" value="${user.id}"/>
                                    </c:url>
                                    <a href="${deleteUrl}" title="Delete user" onclick="return confirm('Are you sure?');">Delete</a>
                                </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</jsp:body>
</notes:authPage>