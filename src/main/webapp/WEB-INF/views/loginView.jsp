<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<notes:page>
	<jsp:attribute name="title">Notes</jsp:attribute>
	<jsp:body>
		<div class="container-fluid">
			<div class="row-fluid">
				<c:if test="${!empty errorMessage}">
					<div class="alert alert-error">${errorMessage}</div>
				</c:if>
				
				<c:if test="${!empty successMessage}">
					<div class="alert alert-success">${successMessage}</div>
				</c:if>
				
                <h1 class="note-header">Login</h1>
                
				<form method="post" action="${contextPath}/j_spring_security_check" class="form-horizontal">
					<notes:textField label="Username" name="j_username" value="${j_username}"/>
					<notes:passwordField label="Password" name="j_password" value=""/>
					
					<div class="form-actions">
						<button type="submit" class="btn">Login</button>
						&nbsp;
						<a href="${contextPath}/register/view">Register</a>
					</div>
				</form>
			</div>
		</div>
		<script type="text/javascript">
			document.forms[0].j_username.focus();
		</script>
	</jsp:body>
</notes:page>