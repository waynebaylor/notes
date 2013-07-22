<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<notes:page>
	<jsp:attribute name="title">Notes - Register User</jsp:attribute>
	<jsp:body>
		<div class="container">
			<c:if test="${!empty errorMessage}">
				<div class="alert alert-error">${errorMessage}</div>
			</c:if>
			
			<form method="post" action="${contextPath}/register/submit" class="form-horizontal">
				<fieldset>
					<legend>Register</legend>
					
					<notes:textField label="Username" name="username" bindingResult="${result}"/>
					<notes:passwordField label="Password" name="password" value="" bindingResult="${result}"/>
					
					<div class="form-actions">
						<button type="submit" class="btn">Submit</button>
					</div>					
				</fieldset>
			</form>
		</div>
	</jsp:body>
</notes:page>