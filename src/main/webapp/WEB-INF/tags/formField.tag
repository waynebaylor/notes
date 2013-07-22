<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<%@ attribute name="label" required="false" %>
<%@ attribute name="status" required="false" %>

<div class="control-group ${status}">
	<label class="control-label">${label}</label>
	<div class="controls">
		<jsp:doBody/>
	</div>
</div>