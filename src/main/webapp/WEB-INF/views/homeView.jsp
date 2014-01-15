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
				
				<div class="home-create-note">
					<a href="#">Create Note</a>
					<form class="form-vertical" style="display:none;" method="post" action="${contextPath}/note/create">
						<fieldset>
							<legend>Create Note</legend>
							
							<notes:textArea cssClass="span12" label="" name="content" bindingResult="${result}"/>
							
							<div class="form-actions">
								<button type="submit" class="btn">Create</button>
							</div>
						</fieldset>
					</form>
				</div>
				
				<div class="home-search-results">
					<form method="post" action="${contextPath}/note/delete">
						<fieldset>
							<legend>Recent Notes</legend>
							<table class="table table-striped table-condensed table-bordered">
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
													${noteContent}
													<c:if test="${fn:length(note.content) > 100}">...</c:if>
												</label>
											</td>
											<td>
												<a href="${contextPath}/note/view?id=${note.id}">View</a>
												<a href="${contextPath}/note/edit/view?id=${note.id}">Edit</a>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty notes}">
										<tr>
											<td colspan="3">No notes</td>
										</tr>
									</c:if>
								</tbody>
							</table>
						</fieldset>
						
						<div class="form-actions">
							<button type="submit" class="btn">Delete</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$(document).ready(function() {
				var $homeCreateNote = $(".home-create-note");
				$homeCreateNote.find("a").click(function(event) {
					event.preventDefault();
					$(this).hide();
					$(this).next("form").slideDown("fast");
					$homeCreateNote.find("textarea").focus();
				});
			});
		</script>
	</jsp:body>
</notes:authPage>