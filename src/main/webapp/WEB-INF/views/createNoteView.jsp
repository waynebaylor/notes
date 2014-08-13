<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<notes:authPage>
	<jsp:attribute name="title">Notes - Create Note</jsp:attribute>
	<jsp:body>
		<div class="container-fluid">
			<div class="row-fluid">
                <c:if test="${!empty errorMessage}">
                    <div class="alert alert-error">${errorMessage}</div>
                </c:if>
                
                <c:if test="${!empty successMessage}">
                    <div class="alert alert-success">${successMessage}</div>
                </c:if>
                
                <h1 class="note-header">Create Note</h1>
                
                <p>         
                    <a target="_blank" href="http://daringfireball.net/projects/markdown/syntax">Markdown Syntax</a>
                </p>
                
                <c:url var="formActionUrl" value="/note/create/submit" />
				<form method="post" action="${formActionUrl}">
					<notes:textArea cssClass="span12" label="" name="content" value="${note.content}" />
					
					<div class="form-actions">
						<button type="submit" class="btn">Save</button>
						&nbsp;
						
						<c:url var="formCancelUrl" value="/home/view" />
						<a href="${formCancelUrl}">Cancel</a>
					</div>
				</form>
			</div>
		</div>
        
        <script type="text/javascript">
            $("[name=content]").autosize();
        </script>
	</jsp:body>
</notes:authPage>