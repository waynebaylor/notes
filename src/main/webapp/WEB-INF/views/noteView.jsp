<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<notes:authPage>
	<jsp:attribute name="title">Notes - View Note</jsp:attribute>
	<jsp:body>
		<div class="container">
			<div class="row">
				<div class="span2">
					<ul class="note-tag-list">
						<c:forEach items="${tags}" var="tag">
							<li>
								<a href="${contextPath}/search?q=%23${tag.name}">#${util:escapeHtml(tag.name)}</a>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="span10">
					<pre>${util:escapeHtml(note.content)}</pre>
				</div>			
			</div>
		</div>
	</jsp:body>
</notes:authPage>