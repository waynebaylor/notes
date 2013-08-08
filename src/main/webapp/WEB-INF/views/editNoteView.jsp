<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<notes:authPage>
	<jsp:attribute name="title">Notes - Edit Note</jsp:attribute>
	<jsp:body>
		<div class="container">
			<c:if test="${!empty errorMessage}">
				<div class="alert alert-error">${errorMessage}</div>
			</c:if>
			
			<c:if test="${!empty successMessage}">
				<div class="alert alert-success">${successMessage}</div>
			</c:if>
			
			<form method="post" action="${contextPath}/note/edit/submit">
				<fieldset>	
					<legend>Edit Note</legend>	
								
					<input type="hidden" name="id" value="${note.id}">
					<notes:textArea cssClass="span12" label="Note" name="content" value="${note.content}"/>
					
					<div class="form-actions">
						<button type="submit" class="btn">Save</button>
					</div>
				</fieldset>
			</form>
		</div>
	</jsp:body>
</notes:authPage>