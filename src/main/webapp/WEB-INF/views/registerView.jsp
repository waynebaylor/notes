<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<notes:page>
	<jsp:attribute name="title">Notes - Register User</jsp:attribute>
	<jsp:body>
		<div class="container">
			<form method="post" action="${contextPath}/register/submit" class="form-horizontal">
				<fieldset>
					<legend>Register</legend>
					<div class="control-group">
						<label class="control-label">Username</label>
						<div class="controls">
							<input type="text" name="username" value="">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Password</label>
						<div class="controls">
							<input type="password" name="password" value="">
						</div>
					</div>
					<div class="form-actions">
						<button type="submit" class="btn">Submit</button>
					</div>					
				</fieldset>
			</form>
		</div>
	</jsp:body>
</notes:page>