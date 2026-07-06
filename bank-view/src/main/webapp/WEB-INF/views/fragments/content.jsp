<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bank Unknown</title>
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
<script
	src="<%=request.getContextPath()%>/resources/js/jquery-1.8.2.js "></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.js "></script>
<link href="<%=request.getContextPath()%>/resources/css/bootstrap.css"
	rel="stylesheet" />
<script
	src="<%=request.getContextPath()%>/resources/js/jquery.slimscroll.min.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/AdminLTE.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/resources/css/skins/skin-green-light.css"
	rel="stylesheet" />
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lte 9]>
  <script src="<%=request.getContextPath()%>/resources/html5shiv-master/src/html5shiv.js"></script>
  <script
	src="<%=request.getContextPath()%>/resources/Respond-master/src/respond.js "></script>
<![endif]-->


<script src="<%=request.getContextPath()%>/resources/js/app.min.js"></script>
<link
	href="<%=request.getContextPath()%>/resources/css/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<style type="text/css">
.wait {
	display: none;
	width: 100%;
	height: 100%;
	top: 0;
	left: 0;
	bottom: 0;
	position: fixed;
	overflow-y: scroll;
	background-color: rgba(255, 255, 255, 0.5);
	z-index: 999999;
}

.image-centered {
	position: fixed;
	top: 50%;
	left: 50%;
	margin-top: -50px;
	margin-left: -100px;
}

/* COBA */
#loader-wrapper {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 1000;
}

#loader {
	display: block;
	position: relative;
	left: 50%;
	top: 50%;
	width: 150px;
	height: 150px;
	margin: -75px 0 0 -75px;
	border-radius: 50%;
	border: 3px solid transparent;
	border-top-color: #3498db;
	-webkit-animation: spin 2s linear infinite;
	/* Chrome, Opera 15+, Safari 5+ */
	animation: spin 2s linear infinite;
	/* Chrome, Firefox 16+, IE 10+, Opera */
}

#loader:before {
	content: "";
	position: absolute;
	top: 5px;
	left: 5px;
	right: 5px;
	bottom: 5px;
	border-radius: 50%;
	border: 3px solid transparent;
	border-top-color: #e74c3c;
	-webkit-animation: spin 3s linear infinite;
	/* Chrome, Opera 15+, Safari 5+ */
	animation: spin 3s linear infinite;
	/* Chrome, Firefox 16+, IE 10+, Opera */
}

#loader:after {
	content: "";
	position: absolute;
	top: 15px;
	left: 15px;
	right: 15px;
	bottom: 15px;
	border-radius: 50%;
	border: 3px solid transparent;
	border-top-color: #f9c922;
	-webkit-animation: spin 1.5s linear infinite;
	/* Chrome, Opera 15+, Safari 5+ */
	animation: spin 1.5s linear infinite;
	/* Chrome, Firefox 16+, IE 10+, Opera */
}

@-webkit-keyframes spin { 
	0% {
		-webkit-transform: rotate(0deg); /* Chrome, Opera 15+, Safari 3.1+ */
		-ms-transform: rotate(0deg); /* IE 9 */
		transform: rotate(0deg); /* Firefox 16+, IE 10+, Opera */
	}
	
	100%{
	-webkit-transform=:rotate(360deg); /* Chrome, Opera 15+, Safari 3.1+ */
	-ms-transform:rotate(360deg); /* IE 9 */
	transform:rotate(360deg); /* Firefox 16+, IE 10+, Opera */
	}
}
@keyframes spin { 
	0% {
		-webkit-transform: rotate(0deg); /* Chrome, Opera 15+, Safari 3.1+ */
		-ms-transform: rotate(0deg); /* IE 9 */
		transform: rotate(0deg); /* Firefox 16+, IE 10+, Opera */
	}
	100% {
		-webkit-transform:rotate(360deg); /* Chrome, Opera 15+, Safari 3.1+ */
		-ms-transform:rotate(360deg); /* IE 9 */
		transform:rotate(360deg); /* Firefox 16+, IE 10+, Opera */
	}
}
</style>
<script type="text/javascript">
	var he;
	$(document).ready(function() {
		$('.dropdown-menu').find('form').click(function(e) {
			e.stopPropagation();
		});
		// 		buildingMenu(1);
		$.ajax({
			url : "sidebar",
			type : "GET",
			success : function(msg) {
				$("#sidebar").replaceWith(msg);
				// 						if ($data == 1) {
				// 							changeContent($("ul.treeview-menu li a:first")
				// 									.attr('href'));
				// 						}
			}
		});
	});

	$(window).resize(function() {
		he = $(window).height() - 48;
	});

	$(window).trigger('resize');

	$(document).ajaxStart(function(e) {
		$("#wait").css("display", "block");
	});

	$(document).ajaxComplete(function(e) {
		$("#wait").css("display", "none");
	});

	function buildingMenu($data) {
		$.ajax({
			url : "sidebar",
			type : "GET",
			success : function(msg) {
				$("#sidebar").replaceWith(msg);
				// 						if ($data == 1) {
				// 							changeContent($("ul.treeview-menu li a:first")
				// 									.attr('href'));
				// 						}
			}
		});
	}
	function changeContent($data) {
		$.ajax({
			url : $data,
			type : "GET",
			success : function(msg) {
				$("#main").replaceWith(msg);
			}
		});
		return false;
	}
</script>
</head>

<body class="hold-transition skin-green-light fixed sidebar-mini">
	<div id="wait" class="wait">
		<div id="loader-wrapper">
			<div id="loader">
				<p class="data-loader">${title }</p>
			</div>
		</div>

	</div>
	<div id="wrapper">
		<jsp:include page="header.jsp" />
		<jsp:include page="sidebar.jsp" />

		<div class="content-wrapper">
			<div id="main"></div>
		</div>
	</div>
</body>
</html>