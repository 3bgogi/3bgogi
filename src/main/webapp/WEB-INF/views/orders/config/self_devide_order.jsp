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
<title>주문서 나누기</title>
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
	var regexp = /^[0-9]*$/

	$(function(){
		
		if($("#orQty").val() == 1){
			alert("1개인 상품은 나눌 수 없습니다 주문서 분리를 해주세요");
			self.close();
		}
		
		$("#selfDevideOriginalValue, #selfDevideValue, #radioDevideValue").keyup(function(){
			
			if( !regexp.test($(this).val()) ) {

				alert("숫자만 입력하세요");

				$(this).val("0");

			}
			
		});
		
		// 비율 나누기
		$("#radioDevideBtn").click(function(){
			$("#orderDevideType").val("0");
			
			selfDevideForm();
			
		});
		
		//임의 숫자 나누기
		$("#selfDevideBtn").click(function(){
			$("#orderDevideType").val("1");
			
			selfDevideForm();
		});
		
		
		function selfDevideForm(){
			
			var devideType = $("#orderDevideType").val();
			var orQty = Number($("#orQty").val());
			var radioDevideValue = Number($("#radioDevideValue").val());
			var selfDevideOriginalValue = Number($("#selfDevideOriginalValue").val());
			
			if(devideType == 0){
				
				if(orQty < radioDevideValue){
					
					alert("상품 수량보다 크게 나눌 수 없습니다");
					$(this).val("0");
					$("#radioDevideValue").focus();
					return false;
				}
				
			}else if(devideType == 1){
				if(orQty <= selfDevideOriginalValue){
					
					alert("상품 수량보다 크거나 같게 나눌 수 없습니다");
					$(this).val("0");
					$("#selfDevideOriginalValue").focus();
					return false;
					
				}else{
					$("#selfDevideValue").val(orQty - selfDevideOriginalValue);
					
				}
			}
			
			if(!confirm("해당 숫자로 나누시겠습니까?")){
				
				return false;
			}else{
				var devideParams = jQuery("#devideOrderForm").serialize();
				
				
				$.ajax({
					
				    type       : 'POST',
				    url        : '<c:url value="/orders/config/self_devide_order.do"/>',
				    async	   : false,
				    data       : devideParams,
				    success    : function(data){
				    	if(data == true){
				    		
				    		if($("#closing").val() == 'true'){
				    			alert("주문서 나누기 완료. 재고할당이 있었을 경우 재할당 해주세요.");
				    			opener.location.reload();
				    		}else{
				    			alert("나눈 후 상품 매칭, 재고할당을 해주세요");
				    		}
				    		
				    		self.close();
				    		
				    	}else{
				    		alert("주문서 나누기 실패");

				    	}
				    }
				});	
				
			}
			
			
		}
		
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
	        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
		        <div class="page-header">
		        	<h3 class="mb-2"> 주문서 나누기 </h3>
		        </div>
	        </div>
        </div>
		<div class="row">
			<!-- ============================================================== -->
			<!-- basic tabs  -->
			<!-- ============================================================== -->
			<div class="col-xl-6 col-lg-12 col-md-12 col-sm-12 col-12 mb-5">
				<div class="tab-regular">
					<ul class="nav nav-tabs " id="myTab" role="tablist">
						<li class="nav-item"><a class="nav-link active" id="home-tab" data-toggle="tab" href="#ratioDevide" role="tab" aria-controls="ratioDevide" aria-selected="true"> 박스 단위로 나누기</a></li>
						<li class="nav-item"><a class="nav-link" id="profile-tab" data-toggle="tab" href="#selfDevide" role="tab" aria-controls="selfDevide" aria-selected="false"> 지정 나누기 </a></li>
					</ul>
					<form id="devideOrderForm">
						<input type="hidden" name="orPk" value="${orVO.orPk}">
						<input type="hidden" id="closing" name="closing" value="${closing }">
						<input type="hidden" id="orQty" value="${orVO.orAmount}">
						
						<input type="hidden" id="orderDevideType" name="orderDevideType" value="0">
						
						<div class="tab-content" id="myTabContent">
							<div class="tab-pane fade show active" id="ratioDevide" role="tabpanel" aria-labelledby="home-tab">
								
								<p class="lead" style="font-size: 11px;">상품 개수가 5일 때 2로 나누면 3, 2 로 나눠짐</p>
								<p class="lead" style="font-size: 11px;">상품 개수가 7일 때 3으로 나누면 3, 2, 2 로 나눠짐</p>
								<div class="input-group mb-3">
									
									<input type="text" class="form-control" id="radioDevideValue" name="radioDevideValue" value="0">
									<div class="input-group-append">							
										<button class="btn btn-primary" type="button" id="radioDevideBtn"> 나누기 </button>
									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="selfDevide" role="tabpanel" aria-labelledby="profile-tab">
								<h3> 개수 입력으로 나누기 </h3>
								<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="form-group">
										<input class="form-control mb-3" type="text" id="selfDevideOriginalValue" name="selfDevideOriginalValue" value="0">
										
										<input class="form-control" type="text" id="selfDevideValue" name="selfDevideValue" value="0" readonly="readonly">
									</div>
									<button class="btn btn-primary" type="button" id="selfDevideBtn"> 나누기 </button>
								</div>
							</div>
						
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
