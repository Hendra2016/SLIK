<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en" dir="ltr"
	class="js supports no-touchevents preserve3d cssanimations cssfilters flexbox csstransforms3d">
<!--<![endif]-->
<!-- Website designed and developed by Bright Interactive, http://www.bright-interactive.com -->
<head>
<title>Asset Bank | Login</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link
	href="<%=request.getContextPath()%>/resources/css/bootstrap.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/login.css"
	rel="stylesheet" />

<script
	src="<%=request.getContextPath()%>/resources/js/jquery-1.8.2.js "></script>
<script
	src="<%=request.getContextPath()%>/resources/js/bootstrap.js "></script>

<style type="text/css">
#loginPage, #registrationPage {
	background:
		url('<%=request.getContextPath()%>/resources/images/background.jpg')
		no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	position: relative;
	margin: 0;
}

.box, #loginPage .leftShadow, #registrationPage .leftShadow {
	margin-top: 0;
	position: relative;
	top: 10%;
}

div.rightShadow, div.leftShadow {
	padding-bottom: 0;
}

.ie7 #loginPage, .ie7 #registrationPage, .ie8 #loginPage, .ie8 #registrationPage
	{
	background: none;
}

/* fix dissapearing form in IE 7 */
.ie7 form.floated {
	height: auto;
}

.bg-scale-fallback {
	position: fixed;
	width: 100%;
	height: 100%;
}

.bg-scale-fallback img {
	position: fixed;
	top: 0;
	left: 0;
}

.bgwidth {
	width: 100%;
}

.bgheight {
	height: 100%;
}

/* Overide positionning of login panel on small devices (as can appear off screen) */
@media all and (max-width: 767px) {
	.box, #loginPage .leftShadow {
		top: 10%;
		margin-left: auto;
	}
}

@media all and (max-width: 430px) {
	.box, #loginPage .leftShadow {
		top: 0;
	}
}
</style>
</head>
<body id="loginPage">
	<div class="box" id="loginPanel">
		<div class="box__header logo box--centered">
			<a href="#"><img
				src="<%=request.getContextPath()%>/resources/images/logo-bank-indonesia.jpg"
				height="100px" width="350px" alt="Bank BI" class="logo"
				id="logoHomeLink"></a>
		</div>
		<div class="box__inner">
			<div class="printHide"></div>
			<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
				<div class="error">
					<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
				</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="error">
					<c:out value="${msg}" />
				</div>
			</c:if>
			<c:if test="${not empty error}">
				<div class="error">
					<c:out value="${error}" />
				</div>
			</c:if>

			<form id="loginForm" action="<c:url value='/login' />" method='POST'
				class="form-full-field form--login">
				<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
				<div class="form-group form-group-stacked noTopMargin">
					<div class="form-controls">
						<i aria-hidden="true" class="glyphicon glyphicon-envelope field-icon"></i> <input
							type="text" name="username" maxlength="50" value="" id="username" autocomplete="off"
							class="text" placeholder="<spring:message code="login.user"/>">
					</div>
				</div>
				<div class="form-group form-group-stacked">
					<div class="form-controls">
						<i aria-hidden="true" class="glyphicon glyphicon-lock field-icon"></i> <input
							type="password" name="password" maxlength="50" value=""
							id="password" class="js-caps-check" placeholder="<spring:message code="login.password"/>">
						<p class="help-block js-caps-warn" style="display: none;">
							<i class="icon-warning-sign"></i> Caps lock is on
						</p>
					</div>
				</div>
				<div class="form-group form-group-stacked">
					<div class="form-controls">
						<input type="submit" name="submit" value="Log in"
							id="login-button" class="button button--block">
					</div>
				</div>
			</form>

		</div>

	</div>
	<div class="hoverPreviewImageOuter" title="Hide preview">
		<div class="hoverPreviewImage"></div>
	</div>
</body>
</html>