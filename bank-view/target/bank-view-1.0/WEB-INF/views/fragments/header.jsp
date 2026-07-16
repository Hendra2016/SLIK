<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%-- <sec:authentication var="user" property="principal" /> --%>
<script type="text/javascript">
	$(document).ready(function() {
		setInterval(function() {
			loadTime()
		}, 1);

	});

	function loadTime() {
		DaysofWeek = new Array()
		DaysofWeek[0] = "Sunday"
		DaysofWeek[1] = "Monday"
		DaysofWeek[2] = "Tuesday"
		DaysofWeek[3] = "Wednesday"
		DaysofWeek[4] = "Thursday"
		DaysofWeek[5] = "Friday"
		DaysofWeek[6] = "Saturday"

		Months = new Array()
		Months[0] = "January"
		Months[1] = "February"
		Months[2] = "March"
		Months[3] = "April"
		Months[4] = "May"
		Months[5] = "June"
		Months[6] = "July"
		Months[7] = "August"
		Months[8] = "September"
		Months[9] = "October"
		Months[10] = "November"
		Months[11] = "December"
		var dte = new Date();
		var hrs = dte.getHours();
		var min = dte.getMinutes();
		var sec = dte.getSeconds();
		var day = DaysofWeek[dte.getDay()]
		var date = dte.getDate()
		var month = Months[dte.getMonth()]
		var year = dte.getFullYear()
		document.getElementById("spanDate").innerHTML = day + " " + month + " "
				+ year + " " + hrs + ":" + min + ":" + sec;
	}
</script>
<header class="main-header">
	<!-- Logo -->
	<a class="logo"> <!-- mini logo for sidebar mini 50x50 pixels --> <span
		class="logo-mini"><b>AUR</b></span> <!-- logo for regular state and mobile devices -->

		<span class="logo-lg"><img width="230px" height="50px"
			src='<%=request.getContextPath()%>/resources/coollogo_com-10323704.gif' /></span>
	</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span> <span
			class="icon-bar"></span> <span class="icon-bar"></span> <span
			class="icon-bar"></span>
		</a>

		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<li class="dropdown user user-menu"><a class="dropdown-toggle">
						<span id="spanDate"></span>
				</a></li>

				<li class="dropdown user user-menu"><a class="dropdown-toggle"
					href="" data-toggle="dropdown">${user.username}<strong
						class="caret"></strong></a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
						<li class="user-body">
							<div>
								<a role="menuitem" tabindex="-1" href="logout">Log Out</a>
							</div>
						</li>
					</ul></li>
			</ul>
		</div>
	</nav>
</header>