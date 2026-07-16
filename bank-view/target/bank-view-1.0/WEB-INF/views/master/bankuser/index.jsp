<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<sec:authentication var="user" property="principal" />
<link
	href="<%=request.getContextPath()%>/resources/css/jquery.dataTables.min.css"
	rel="stylesheet" />
<script
	src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script>
<div id="main">
	<script type="text/javascript">
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var headers = {};
		headers[header] = token;
		$(document).ready(function() {
			initTaskTable('${pagingUrl}', 0);
			$('#search').on('keypress', function(e) {
				var code = e.keyCode || e.which;
				if (code == 13) {
					searchPaging('${pagingUrl}', 0, $('#search').val());
				}
			});
		});

		function searchPaging(url, data, search) {
			$('#bankUser').DataTable(
					{
						"ajax" : {
							"url" : url,
							"data" : function(d, settings) {
								var api = new $.fn.dataTable.Api(settings);
								d.pageNumber = Math.min(Math.max(0, Math
										.round(d.start / api.page.len())),
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
						columns : [ {
							"data" : "userId"
						}, {
							"data" : "username"
						}, {
							"data" : "email"
						}, {
							"data" : "roleId"
						}, {
							"data" : null,
							"bSortable" : false,
							"mRender" : function(o) {
								var button = '<button onclick="editBankUser(';
								button += "'";
								button += o.userId;
								button += "'";
								button += ')" class="btn btn-primary"';
								button += '>Ubah</button>';
								button += '<button onclick="deleteBankUser(';
								button += "'";
								button += o.userId;
								button += "'";
								button += ')" class="btn btn-primary"';
								button += '>Hapus</button>';
								return button;
							}
						} ]
					});
		}

		function addBankUser() {
			$.ajax({
				url : "bankUser/add",
				type : "GET",
				success : function(msg) {
					$("#main").replaceWith(msg);
				}
			});
		}

		function editBankUser(data) {
			$.ajax({
				url : "bankUser/update/" + data,
				type : "GET",
				success : function(msg) {
					$("#main").replaceWith(msg);
				}
			});
		}

		function deleteBankUser(data) {
			$('#lovList').modal('show');
			$('#ok').click(function() {
				$.ajax({
					url : "bankUser/delete/" + data,
					type : "POST",
					headers : headers,
					success : function(msg) {
						console.log(msg);
						$('#bankUser').DataTable().ajax.reload();
						$('#lovList').modal('hide');
					}
				});
			});
		}

		function pagination(data) {
			searchPaging('${pagingUrl}', data, $('#search').val());
		}

		function initTaskTable(url, data) {
			searchPaging(url, 0, '');
		}
	// -->
	</script>

	<section class="content">
		<div class="box">
			<div class="box-header">
				<h1 id="box-title" class="box-title">
					<p><spring:message code="bankUser.title" /></p>
				</h1>
				<button type="submit" class="btn btn-primary"
					onclick="addBankUser();">Tambah</button>
			</div>
			<div class="box-body">
				<table id="bankUser"
					class="display table table-bordered table-striped">
					<thead>
						<tr>
							<th><spring:message code="bankUser.userId" /></th>
							<th><spring:message code="bankUser.username" /></th>
							<th><spring:message code="bankUser.email" /></th>
							<th><spring:message code="bankUser.roleId" /></th>
							<th><spring:message code="action" /></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</section>
	<div class="modal" id=lovList aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					Konfirmasi Hapus Data
					<%-- 					<spring:message code="tolak" /> --%>
				</div>
				<div class="modal-body">
					Apakah Anda yakin ingin menghapus data ini?
					<%-- 					<spring:message code="msg.informasi" /> --%>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						Batal
						<%-- 						<spring:message code="button.batal" /> --%>
					</button>
					<button id="ok" type="submit" class="btn btn-primary">
						<i class="fa fa-envelope-o"></i> Lanjut
						<%-- 						<spring:message code="button.iya" /> --%>
					</button>
				</div>
			</div>
		</div>
	</div>

	<script
		src="<%=request.getContextPath()%>/resources/js/dataTables.bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/js/jquery.slimscroll.min.js"></script>
</div>
