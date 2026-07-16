<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="main">
	<script type="text/javascript">
		$(document).ready(function() {
			$('#submitForm').submit(function(e) {
				e.preventDefault();
				$.ajax({
					type : "post",
					data : $('#submitForm').serialize(),
					url : '${url}',
					success : function(e) {
						if (e) {
							$("#main").replaceWith(e);
						}
					},
					error : function(e) {
						console.log("Error = " + e);
					}
				});
			});
		});
	</script>
	<section class="content">
		<form:form id="submitForm" modelAttribute="bankUser"
			class="form-horizontal">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="col-lg-12">
				<div class="box box-info custom">
					<div class="box-header with-border">
						<h3 id="box-title" class="box-title">${title }</h3>
					</div>
					<!-- /.box-header -->
					<!-- form start -->
					<div class="box-body">
						<div class="row form-group ">
							<div class="col-xs-6 col-md-6 col-lg-6">
								<spring:message code="bankUser.userId" />
							</div>
							<div class="col-xs-6 col-md-6 col-lg-6">
								<form:input path="userId"
									disabled="${(not empty bankUser.userId)? true : false }" />
							</div>
						</div>
						<div class="row form-group ">
							<div class="col-xs-6 col-md-6 col-lg-6">
								<spring:message code="bankUser.username" />
							</div>
							<div class="col-xs-6 col-md-6 col-lg-6">
								<form:input path="username" />
							</div>
						</div>
						<div class="row form-group ">
							<div class="col-xs-6 col-md-6 col-lg-6">
								<spring:message code="bankUser.password" />
							</div>
							<div class="col-xs-6 col-md-6 col-lg-6">
								<form:input path="password" />
							</div>
						</div>
						<div class="row form-group ">
							<div class="col-xs-6 col-md-6 col-lg-6">
								<spring:message code="bankUser.retypePassword" />
							</div>
							<div class="col-xs-6 col-md-6 col-lg-6">
								<form:input path="retypePassword" />
							</div>
						</div>
						<div class="row form-group ">
							<div class="col-xs-6 col-md-6 col-lg-6">
								<spring:message code="bankUser.roleId" />
							</div>
							<div class="col-xs-6 col-md-6 col-lg-6">
								<form:input path="roleId" />
							</div>
						</div>
						<div class="row form-group ">
							<div class="col-xs-6 col-md-6 col-lg-6">
								<spring:message code="bankUser.email" />
							</div>
							<div class="col-xs-6 col-md-6 col-lg-6">
								<form:input path="email" />
							</div>
						</div>
						<div class="row form-group ">
							<div class="col-md-6 col-md-offset-5">
								<button type="submit" class="btn btn-primary">Ok</button>
							</div>
						</div>
					</div>
					<!-- /.box-footer -->
				</div>
				<!-- /.box -->
			</div>
		</form:form>
	</section>
</div>