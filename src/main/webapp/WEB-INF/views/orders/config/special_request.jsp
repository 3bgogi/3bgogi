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
<title> 특별 요청 </title>
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
	
	var devideOrderCounting = 0;

	$(function(){
			
		//합포 체크 여부
		$("#orRequestCombFlag").change(function(){
			
			if($(this).is(":checked")){
				$(this).val("1");
				
			}else{
				$(this).val("0");
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
							 특별 요청 사항 처리
						</h4> 
						<p> 해당 사항을 저장할 경우 엑셀 주문서에서 따로 출력되어 처리됩니다 </p>
					</div>
					<div class="card-body">
						<form id="specialReqForm" class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" action="<c:url value='/orders/special_request.do'/>" method="post">
							<input name="orPk" value="${orVO.orPk }" type="hidden">
							<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="form-group row">								
									<input class="form-control" type="text" placeholder="특별 요청 사항을 적어주세요" id="orRequest" name="orRequest" value="${orVO.orRequest }">
								</div>
								<c:if test="${orderCombineFlag == true }">								
									<div class="form-group row">								
										<label class="col-12 col-sm-6 col-form-label text-sm-right"> 용기 합포장 여부 </label>
	                                    <div class="col-12 col-sm-6 col-lg-6 pt-1">
		                                    <div class="switch-button switch-button-xs">
			                                    <input type="checkbox" name="orRequestCombFlag" id="orRequestCombFlag" value="0">
			                                    <span>
			                                    	<label for="orRequestCombFlag"></label>
			                                    </span>
		                                    </div>
	                                   </div>
									</div>
								</c:if>
							</div>
							<div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-4 offset-4">
								<button class="btn btn-primary btn-block" id="specialReqSaveBtn"> 저장 </button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
