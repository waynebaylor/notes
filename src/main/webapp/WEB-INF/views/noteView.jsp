<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<notes:authPage>
	<jsp:attribute name="title">Notes - View Note</jsp:attribute>
	<jsp:body>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span2 offset1">
					<div class="tags-header">Tags</div>
					<ul class="note-tag-list">
						<c:forEach items="${tags}" var="tag">
							<li>
								<a href="${contextPath}/search?q=%23${tag.name}">#${util:escapeHtml(tag.name)}</a>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="span8">
					<div class="note-header">Note</div>
					<%-- Stephanie changing stuff. :) Not sure how to do this next part with the escape..  --%>
					<div class="note-content">${note.content}</div>
				</div>		
			</div>
		</div>
	</jsp:body>
</notes:authPage>