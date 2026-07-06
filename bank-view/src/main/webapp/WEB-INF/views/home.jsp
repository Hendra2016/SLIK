<link
	href="<%=request.getContextPath()%>/resources/lib/plugins/datatables/jquery.dataTables.min.css"
	rel="stylesheet" />
<script
	src="<%=request.getContextPath()%>/resources/lib/plugins/datatables/jquery.dataTables.min.js"></script>
<div id="main">
	<script type="text/javascript">
		$(document).ready(function() {
			initTaskTable("hpm", 0);
			$('#search').on('keypress', function(e) {
				var code = e.keyCode || e.which;
				if (code == 13) {
					searchPaging('hpm/render', 0, $('#search').val());
				}
			});
		});

		function searchPaging(url, data, search) {
			var task = eval('(' + '${taskMenu}' + ')');
			$('#task')
					.DataTable(
							{
								"ajax" : {
									"url" : url + "?task=" + task,
									"data" : function(d, settings) {
										var api = new $.fn.dataTable.Api(
												settings);
										d.pageNumber = Math.min(Math.max(0,
												Math.round(d.start
														/ api.page.len())),
												api.page.info().pages);
									}
								},
								"dom" : '<"top"ft><"bottom"p><"clear">',
								search : true,
								destroy : true,
								processing : true,
								serverSide : true,
								retrieve : true,
								ordering : false,
								info : false,
								"fnDrawCallback" : function(oSettings) {
									/* Need to redo the counters if filtered or sorted */
									if (oSettings.bSorted
											|| oSettings.bFiltered) {
										for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
											$(
													'td:eq(0)',
													oSettings.aoData[oSettings.aiDisplay[i]].nTr)
													.html(i + 1);
										}
									}
								},
								columns : [ {
									"data" : "data"
								}, {
									"data" : "test"
								} ]
							});
		}

		function pagination(data) {
			searchPaging('hpm/render', data, $('#search').val());
		}

		function initTaskTable(url, data) {
			searchPaging(url, 0, '');
		}

		function request($data) {
			$.ajax({
				url : '${globalUrl}' + "/" + $data,
				type : "GET",
				contentType : "application/json",
				success : function(data) {
					$("#main").replaceWith(data);
				}
			});
		}
	// -->
	</script>

	<section class="content">
		<div class="box">
			<div class="box-header">
				<h1 class="box-title">${title }</h1>
			</div>
			<div class="box-body">
				<table id="task" class="display table table-bordered table-striped">
					<thead>
						<tr>
							<th>Data</th>
							<th>Coba</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</section>

	<script
		src="<%=request.getContextPath()%>/resources/lib/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/lib/plugins/slimScroll/jquery.slimscroll.min.js"></script>
</div>
