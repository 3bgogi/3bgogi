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
<title>  수령방식변경  </title>
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
		$("#pickUpStatChange").submit(function(){
			var orRecMemo = $("#orRecMemo").val();
			var orRecStoragePlace = $("#orRecStoragePlace").val();
			var orRecType =  $("#orRecType").val();
			
			if(orRecType != 0){				
				if(orRecMemo == ''){
					$("#orRecMemo").val(" ");
					
				}if(orRecStoragePlace == ''){
					$("#orRecStoragePlace").val(" ");
					
				}
				
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
							 수령방식변경 
						</h4> 
					</div>
					<div class="card-body">
						<form id="pickUpStatChange" class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" action="<c:url value='/orders/pick_up_service.do'/>" method="post">
							<input name="orSerialSpecialNumber" value="${orVO.orSerialSpecialNumber }" type="hidden">
							
							<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="form-group row">								
									<select class="form-control" id="orRecType" name="orRecType" >
			                        	<option value="0">택배수령</option>
			                            <option value="1">퀵서비스</option>
			                            <option value="2">방문수령</option>
			                            <option value="3">제주익일특급</option>
			                        </select>
								</div>
							</div>
							
							<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="form-group row">								
									<input class="form-control" type="text" placeholder="수령 시간 혹은 메모를 적어주세요" id="orRecMemo" name="orRecMemo" value="">
								</div>
							</div>
							
							<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="form-group row">								
									<input class="form-control" type="text" placeholder="보관장소를 적어주세요" id="orRecStoragePlace" name="orRecStoragePlace" value="">
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
