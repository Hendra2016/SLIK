<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="<%=request.getContextPath()%>/resources/lib/bootstrap/css/bootstrap.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/resources/lib/dist/css/AdminLTE.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/resources/lib/plugins/iCheck/square/blue.css"
	rel="stylesheet" />
<style type="text/css">
.error {
	padding: 15px;
	margin-bottom: 20px;
	text-align: center;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.header {
	padding-top: 150px;
}

.statement {
	padding: 20px;
	margin-top: 20px;
	border-radius: 20px;
	color: whitesmoke;
	background-color: crimson;
	border-color: #ebccd1;
	position: relative;
	left: 0;
	right: 0;
	margin-bottom: 20px;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}
</style>
</head>

<body class="hold-transition login-page ">

	<div class="row header">
		<div class="col-xs-12">
			<div class="col-xs-3 col-md-3"></div>
			<div class="col-xs-12 col-md-6">
				<div class="login-box-body">
					<div class="login-logo">
						<b>Maybank</b>STP
					</div>
					<div class="statement">
<!-- 						<h3> -->
<%-- 							<spring:message code="login.bii" /> --%>
<!-- 						</h3> -->
<%-- 						<spring:message code="login.statement" /> --%>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>