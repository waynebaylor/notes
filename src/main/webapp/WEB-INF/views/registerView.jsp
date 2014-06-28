<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<notes:page>
	<jsp:attribute name="title">Notes - Register User</jsp:attribute>
	<jsp:body>
		<div class="container-fluid">
			<c:if test="${!empty errorMessage}">
				<div class="alert alert-error">${errorMessage}</div>
			</c:if>
			
            <h1 class="note-header">New User Registration</h1>
            
			<form method="post" action="${contextPath}/register/submit" class="form-horizontal">
				<notes:textField label="Desired Username" name="username" bindingResult="${result}"/>
				<notes:passwordField label="Password" name="password" value="" bindingResult="${result}"/>
				
				<div class="form-actions">
					<button type="submit" class="btn">Submit</button>
					&nbsp;
					<a href="${contextPath}/login/view">Cancel</a>
				</div>					
			</form>
		</div>
	</jsp:body>
</notes:page>