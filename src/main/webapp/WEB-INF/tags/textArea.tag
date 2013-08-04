<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<%@ attribute name="name" required="true" %>
<%@ attribute name="value" required="false" description="if not set, then bindingResult must be non-null" %>
<%@ attribute name="label" required="false" %>
<%@ attribute name="bindingResult" required="false" type="org.springframework.validation.BindingResult" %>
<%@ attribute name="cssClass" required="false" %>

<c:if test="${value == null}">
	<c:set var="value" value="${bindingResult.getRawFieldValue(name)}"/>
</c:if>

<notes:formField>
	<jsp:attribute name="label">${label}</jsp:attribute>
	<jsp:attribute name="status">
		${bindingResult.hasFieldErrors(name)? 'error' : '' }
	</jsp:attribute>
	
	<jsp:body>
		<textarea class="${cssClass}" rows="8" name="${name}">${value}</textarea>
		<c:if test="${bindingResult.hasFieldErrors(name)}">
			<span class="help-block">${bindingResult.getFieldError(name).defaultMessage}</span>
		</c:if>
	</jsp:body>
</notes:formField>