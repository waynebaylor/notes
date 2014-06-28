<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<notes:authPage>
	<jsp:attribute name="title">Notes - Admin</jsp:attribute>
	<jsp:body>
		<div class="container-fluid">
			<div class="admin-user-list">
                <h1 class="note-header">Manage Users</h1>
                
				<form method="post" action="${contextPath}/admin/delete-user">
					<table class="table table-striped table-condensed table-bordered">
						<thead>
							<tr>
								<th></th>
								<th>Username</th>
								<th>Enabled</th>
								<th>Admin</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${users}" var="user">
								<tr>
									<td style="text-align:center;">
										<input type="checkbox" name="userIds" value="${user.id}">
									</td>
									<td>${user.username}</td>
									<td>${user.enabled? 'Yes' : 'No'}</td>
									<td>${user.authority == 'ROLE_ADMIN'? 'Yes' : 'No'}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<button type="submit" class="btn">Delete Users</button>
				</form>
			</div>
		</div>
	</jsp:body>
</notes:authPage>