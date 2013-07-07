<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<notes:authPage>
	<jsp:attribute name="title">Notes - Home</jsp:attribute>
	<jsp:body>
		<div class="container">
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
						<tr>
							<td colspan="2">No notes</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="home-create-note">
				<form class="form-vertical" method="post" action="${contextPath}/note/create">
					<fieldset>
						<legend>Create Note</legend>
						<notes:formField>
							<jsp:attribute name="label">Note</jsp:attribute>
							<jsp:body>
								<textarea class="span12" rows="8" name="content"></textarea>
							</jsp:body>
						</notes:formField>
						<div class="form-actions">
							<button type="submit" class="btn">Create</button>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</jsp:body>
</notes:authPage>