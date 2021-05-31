<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="kr">
<head>
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title> 수기 송장 입력 </title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<link
	href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/libs/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
<script
	src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script type="text/javascript">
	jQuery.ajaxSettings.traditional = true;

	$(function(){
			
		$("#delviNumForm").submit(function(){
			var orDeliveryInvoiceNumber = $("#orDeliveryInvoiceNumber").val();
			
			if(orDeliveryInvoiceNumber == ''){
				alert("송장번호를 제대로 입력해주세요");
				return false;
			}
		});

	});
    </script>
<style>
html, body {
	
}

body {
	display: -ms-flexbox;
	display: flex;
	-ms-flex-align: center;
	align-items: center;
	padding-top: 40px;
	padding-bottom: 40px;
}
</style>
</head>
<body>
	<div class="container-fluid  dashboard-content">
		<div class="row">
			<!-- ============================================================== -->
			<!-- basic table -->
			<!-- ============================================================== -->
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="card">
					<div class="card-header">
						<h4>
							수기 송장번호 입력
						</h4> 
					</div>
					<div class="card-body">
						<form id="delviNumForm" class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" action="<c:url value='/orders/config/edit_deliv_num.do'/>" method="post">
							<input name="orSerialSpecialNumber" value="${orVO.orSerialSpecialNumber }" type="hidden">
							<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="form-group row">								
									<input class="form-control" type="text" placeholder="송장번호를 적어주세요" id="orDeliveryInvoiceNumber" name="orDeliveryInvoiceNumber" value="">
								</div>
							</div>
							<div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-4 offset-4">
								<button type="submit" class="btn btn-primary btn-block"> 저장 </button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
