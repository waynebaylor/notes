<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<notes:authPage>
	<jsp:attribute name="title">Notes - Home</jsp:attribute>
	<jsp:body>
		<div class="container-fluid">
			<div class="row-fluid">
				<c:if test="${!empty errorMessage}">
					<div class="alert alert-error">${errorMessage}</div>
				</c:if>
				
				<c:if test="${!empty successMessage}">
					<div class="alert alert-success">${successMessage}</div>
				</c:if>
				
                <h1 class="note-header">Recent Notes</h1>
                
                <p>
    				<c:url var="createNoteUrl" value="/note/create/view" />
    				<a href="${createNoteUrl}">Create Note</a>
				</p>
                
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
								<td class="note-summary">
                                    <c:url var="viewUrl" value="/note/view">
                                        <c:param name="id" value="${note.id}"/>
                                    </c:url>
                                    <a href="${viewUrl}" title="View note">
                                        ${note.content}
										
                                    </a>
								</td>
								<td class="options">
                                    <c:url var="editUrl" value="/note/edit/view">
                                        <c:param name="id" value="${note.id}"/>
                                    </c:url>
									<a href="${editUrl}" title="Edit note">Edit</a>
                                    
                                    <c:url var="deleteUrl" value="/note/delete">
                                        <c:param name="noteIds" value="${note.id}"/>
                                    </c:url>
                                    <a href="${deleteUrl}" title="Delete note" onclick="return confirm('Are you sure?');">Delete</a>
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
		</div>
	</jsp:body>
</notes:authPage>