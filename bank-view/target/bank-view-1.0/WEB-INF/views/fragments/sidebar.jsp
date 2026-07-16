<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(".sidebar").slimscroll({
		height : he,
		color : "#0073b7",
		alwaysVisible : true,
		size : "10px",
		start : 'bottom',
		railVisible : true,
		railColor : '#0073b7',
		railBorderRadius : 0

	}).css("width", "100%");
	// $(window).resize(function() {
	//     $('#fixed').height($(window).height() - 46);
	// });

	// $(window).trigger('resize');
	// console.log($(window).height());
</script>
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- sidebar menu: : style can be found in sidebar.less -->

		<ul class="sidebar-menu" id="sidebar">
			<c:forEach var="menu" items="${menus.data.children}" varStatus="i">
				<c:if test="${menu != null }">
					<c:choose>
						<c:when test="${menu.menuType == 'P' }">
							<li class="treeview"><a href="${menu.menuUrl }"
								onclick="return changeContent('${menu.menuUrl }');"> <i
									class="fa fa-book"></i> <span>${menu.menuName }</span>
							</a>
						</c:when>
						<c:otherwise>
							<li class="treeview"><a href="#"> <i class="fa fa-book"></i>
									<span>${menu.menuName }</span> <span
									class="pull-right-container"> <i
										class="fa fa-angle-left pull-right"></i>
								</span>
							</a>
								<ul class="treeview-menu view" id="ci">
									<c:forEach var="menu" items="${menu.children}" varStatus="i">
										<li><a href="${menu.menuUrl }"
											onclick="return changeContent('${menu.menuUrl }');"><i
												class="fa fa-circle-o text-red"></i> <span>${menu.menuName }</span>
										</a></li>
									</c:forEach>
								</ul></li>
						</c:otherwise>
					</c:choose>
				</c:if>
			</c:forEach>
		</ul>
	</section>
</aside>
