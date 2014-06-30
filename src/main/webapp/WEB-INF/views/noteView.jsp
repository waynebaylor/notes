<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<notes:authPage>
	<jsp:attribute name="title">Notes - View Note</jsp:attribute>
	<jsp:body>
		<div class="container-fluid view-note">
			<div class="row-fluid">
                <c:if test="${!empty errorMessage}">
                    <div class="alert alert-error">${errorMessage}</div>
                </c:if>
                
                <c:if test="${!empty successMessage}">
                    <div class="alert alert-success">${successMessage}</div>
                </c:if>
                
				<div class="span2">
					<div class="tags-header">Tags</div>
					<ul class="note-tag-list">
						<c:forEach items="${tags}" var="tag">
							<li>
								<a href="${contextPath}/search?q=%23${tag.name}">#${util:escapeHtml(tag.name)}</a>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="span9 well">
					<div class="note-content">${note.content}</div>
					
					<c:url var="editurl" value="/note/edit/view">
						<c:param name="id" value="${note.id}" />
					</c:url>
					
                    <div class="form-actions">
					   <a href="${editurl}">Edit</a>
                    </div>
				</div>		
			</div>
		</div>
	</jsp:body>
</notes:authPage>