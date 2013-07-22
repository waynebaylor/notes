<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<%@ attribute name="name" required="true" %>
<%@ attribute name="value" required="false" description="if not set, then bindingResult must be non-null" %>
<%@ attribute name="label" required="false" %>
<%@ attribute name="bindingResult" required="false" type="org.springframework.validation.BindingResult" %>

<c:if test="${value == null}">
	<c:set var="value" value="${bindingResult.getRawFieldValue(name)}"/>
</c:if>

<notes:formField>
	<jsp:attribute name="label">${label}</jsp:attribute>
	<jsp:attribute name="status">
		${bindingResult.hasFieldErrors(name)? 'error' : '' }
	</jsp:attribute>
	
	<jsp:body>
		<input type="text" name="${name}" value="${value}">
		<c:if test="${bindingResult.hasFieldErrors(name)}">
			<span class="help-inline">${bindingResult.getFieldError(name).defaultMessage}</span>
		</c:if>
	</jsp:body>
</notes:formField>