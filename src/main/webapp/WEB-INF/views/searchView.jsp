<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<notes:authPage>
	<jsp:attribute name="title">Notes - Search Results</jsp:attribute>
	<jsp:body>
		<div class="container-fluid">
			<div class="search-results">
				<form method="post" action="${contextPath}/note/delete">
					<fieldset>	
						<legend>Search Results</legend>
						
						<p>Showing results for: <strong>${q}</strong></p>
						
						<table class="table table-striped table-bordered table-condensed">
							<thead>
								<tr>
									<th></th>
									<th>Note</th>
									<th>Options</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${notes}" var="note">
									<tr>
										<td>
											<input type="checkbox" id="noteIds_${note.id}" name="noteIds" value="${note.id}">
										</td>
										<td>
											<label for="noteIds_${note.id}">
												<c:set var="noteContent" value="${fn:substring(note.content, 0, 100)}"/>
												${util:escapeHtml(noteContent)}
												<c:if test="${fn:length(note.content) > 100}">...</c:if>
											</label>
										</td>
										<td>
											<a href="${contextPath}/note/view?id=${note.id}">View</a>
											<a href="${contextPath}/note//edit/view?id=${note.id}">Edit</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</fieldset>
					
					<div class="form-actions">
						<button type="submit" class="btn">Delete</button>
					</div>
				</form>
			</div>
		</div>
	</jsp:body>
</notes:authPage>