<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<notes:authPage>
	<jsp:attribute name="title">Notes - Search</jsp:attribute>
	<jsp:body>
		<div class="container">
			<form method="get" action="${contextPath}/search/submit">
				<fieldset>	
					<legend>Search</legend>	
								
					<notes:textField label="Query" name="q" value="${q}"/>
					
					<div class="form-actions">
						<button type="submit" class="btn">Search</button>
					</div>
				</fieldset>
			</form>
			
			<div class="search-results">
				<form method="post" action="${contextPath}/note/delete">
					<fieldset>	
						<legend>Search Results</legend>
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
					
					<button type="submit" class="btn">Delete</button>
				</form>
			</div>
		</div>
	</jsp:body>
</notes:authPage>