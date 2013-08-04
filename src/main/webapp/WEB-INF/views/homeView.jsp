<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<notes:authPage>
	<jsp:attribute name="title">Notes - Home</jsp:attribute>
	<jsp:body>
		<div class="container">
			<c:if test="${!empty errorMessage}">
				<div class="alert alert-error">${errorMessage}</div>
			</c:if>
			
			<c:if test="${!empty successMessage}">
				<div class="alert alert-success">${successMessage}</div>
			</c:if>
			
			<div class="home-search-results">
				<fieldset><legend>Recent Notes</legend></fieldset>
				<table class="table table-striped table-condensed table-bordered">
					<thead>
						<tr>
							<th>Note</th>
							<th>Options</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${notes}" var="note">
							<tr>
								<td>
									${fn:substring(note.content, 0, 100)}
									<c:if test="${fn:length(note.content) > 100}">...</c:if>
								</td>
								<td>
									<a href="">View</a>
									<a href="">Edit</a>
									<a href="">Delete</a>
								</td>
							</tr>
						</c:forEach>
						<c:if test="${empty notes}">
							<tr>
								<td colspan="2">No notes</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<div class="home-create-note">
				<form class="form-vertical" method="post" action="${contextPath}/note/create">
					<fieldset>
						<legend>Create Note</legend>
						
						<notes:textArea cssClass="span12" label="Note" name="content" bindingResult="${result}"/>
						
						<div class="form-actions">
							<button type="submit" class="btn">Create</button>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</jsp:body>
</notes:authPage>