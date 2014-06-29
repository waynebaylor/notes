<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<%@ attribute name="title" required="true" %>
<%@ attribute name="navLinks" required="false" %>

<!DOCTYPE html>

<html>
<head>
	<title>${title}</title>
	
	<link rel="stylesheet" type="text/css" href="${contextPath}/assets/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/public/css/notes.css">
	
	<script type="text/javascript" src="${contextPath}/assets/jquery-2.0.2.min.js"></script>
    <script type="text/javascript" src="${contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${contextPath}/assets/autosize/jquery.autosize.min.js"></script>
</head>
<body>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="brand" href="${contextPath}/home/view">Notes</a>
				${navLinks}
			</div>
		</div>
	</div>
	
	<jsp:doBody/>
</body>
</html>