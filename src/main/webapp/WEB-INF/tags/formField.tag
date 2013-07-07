<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<%@ attribute name="label" required="false" %>

<div class="control-group">
	<label class="control-label">${label}</label>
	<div class="controls">
		<jsp:doBody/>
	</div>
</div>