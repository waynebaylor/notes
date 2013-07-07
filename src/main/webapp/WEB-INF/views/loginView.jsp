<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<notes:page>
	<jsp:attribute name="title">Notes</jsp:attribute>
	<jsp:body>
		<div class="container">
			<form method="post" action="${contextPath}/j_spring_security_check" class="form-horizontal">
				<div class="control-group">
					<label class="control-label">Username</label>
					<div class="controls">
						<input type="text" name="j_username" value="">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Password</label>
					<div class="controls">
						<input type="password" name="j_password" value="">
					</div>
				</div>
				<div class="form-actions">
					<button type="submit" class="btn">Login</button>
					&nbsp;
					<a href="${contextPath}/register/view">Register</a>
				</div>
			</form>
		</div>
	</jsp:body>
</notes:page>