<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<notes:authPage>
	<jsp:attribute name="title">Notes - Create Note</jsp:attribute>
	<jsp:body>
		<div class="container-fluid">
			<div class="row-fluid">
				<c:if test="${result.hasFieldErrors('content')}">
					<div class="alert alert-error">${result.getFieldError('content').defaultMessage}</div>
				</c:if>
			
				<c:url var="formActionUrl" value="/note/create/submit" />
				
				<form method="post" action="${formActionUrl}">
					<fieldset>
						<legend>Create Note</legend>
					
						<notes:textArea cssClass="span12" label="" name="content" value="${note.content}" />
						
						<div class="form-actions">
							<button type="submit" class="btn">Save</button>
							&nbsp;
							
							<c:url var="formCancelUrl" value="/home/view" />
							<a href="${formCancelUrl}">Cancel</a>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</jsp:body>
</notes:authPage>