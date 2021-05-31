<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="kr">
<head>
<!-- Required meta tags -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title> 상품 출고 </title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script type="text/javascript">
	$(function(){
		
		$("#orDeliveryInvoiceNumber").focus();
		
		$("#nonePickingCount").click(function(){
			location.href='/orders/search/customer_orders.do?dateType=or_sending_deadline&datePeriod=1&&outputPosiv=1&deliveryInvoiceFlag=1&cancledFlag=1&excelFlag=4&reservationType=2&refundFlag=2';
			
			
			
		});
		
	});
	
	$.ajaxSetup({
		
	    beforeSend: function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	     },
	     error: function(xhr, status, err) {
	        if (xhr.status == 401) {
	            alert("인증에 실패 했습니다. 로그인 페이지로 이동합니다.");
	            location.href = "/login.do";
	         } else if (xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 로그인 페이지로 이동합니다.");
	              location.href = "/login.do";
	         } else if (xhr.status == 500) {
	             alert(" 500에러 ");
	             location.href = "/login.do";
	        }
	     }
	});
	
</script>
</head>
<body>
	<div class="container-fluid  dashboard-content" style="padding: 0;">
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" style="padding: 0;">
				<div class="card" style="margin-bottom: 10px;">
					<form class="card-body" id="sendingForm">
						<table class="table table-bordered">
							<tbody>
								<tr>
									<td width="50%">
										<div class="input-group">
											<input type="text" class="form-control" id="orDeliveryInvoiceNumber" autocomplete="off">
											<div class="input-group-append">
												<button class="btn btn-primary btn-xs" type="submit">입력</button>
											</div>
										</div>
									</td>
									<td width="50%">
										<div class="input-group">
											<input type="text" class="form-control" disabled="disabled" id="invoiceValue">
											<div class="input-group-append">
												<button class="btn btn-secondary btn-xs" type="button" id="barcodeInit">초기화</button>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<button class="btn btn-secondary btn-xs btn-block" type="button" id="sendingReqBtn">강제출고 요청</button>
										
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" style="padding: 0;">
				<div class="card">
					<div class="card-body">
						<table class="table table-hover">
							<thead>
								<tr style="font-size: 10px;">
									<th width="70%">상품</th>
									<th width="15%">개수</th>
									<th width="15%">출고</th>
								</tr>
							</thead>
							<tbody id="sendingProductList">
							</tbody>
						</table>
						<form id="sendingTargetOrder">
						
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" style="padding: 0;">
				<div class="card">
					<div class="card-body">
						<table class="table table-hover">
							<thead>
								<tr style="font-size: 10px;">
									<th width="70%"> 분류 완료 상품</th>
									<th width="15%">개수</th>
									<th width="15%">출고</th>
								</tr>
							</thead>
							<tbody id="finishedOrder" style="background:gray; color:white;">
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
				
			<div class="row">
				<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" style="padding: 0;">
					<div class="card">
						<div class="card-body">
							<table class="table table-hover">
								<thead>
									<tr style="font-size: 10px;">
										<th width="70%"> 남은 송장 개수 </th>
										
										
										<th width="30%" id="nonePickingCount" style="cursor: pointer;">${nonePickingCount }</th>
										
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
	</div>
	
	<button type="button" class="btn btn-primary btn-xs" id="reasonBtn" data-toggle="modal" data-target="#reasonModal" style="display:none; "> 모달  </button>
	
	<div class="modal fade" id="reasonModal" tabindex="-1" role="dialog" aria-labelledby="#reasonModal" aria-hidden="true">
			<div class="modal-dialog" role="document" style="max-width: 700px;">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="stockModal"> 강제출고사유 </h5>
						<a href="#" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</a>
					</div>
					<div class="modal-body" >
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <label class="custom-control custom-radio">
                            	<input type="radio" name="srReason" checked class="custom-control-input" value="바코드 없음">
                            	<span class="custom-control-label">바코드 없음</span>
                            </label>
                            <label class="custom-control custom-radio">
                            	<input type="radio" name="srReason" class="custom-control-input" value="바코드 안읽힘">
                            	<span class="custom-control-label">바코드 안읽힘</span>
                            </label>
                            <label class="custom-control custom-radio">
                            	<input type="radio" name="srReason" class="custom-control-input" value="이미 찍은 상품">
                            	<span class="custom-control-label">이미 찍은 상품</span>
                            </label>
                            <label class="custom-control custom-radio">
                            	<input type="radio" name="srReason" class="custom-control-input" value="너무 많은 수량">
                            	<span class="custom-control-label">너무 많은 수량</span>
                            </label>
                            <label class="custom-control custom-radio">
                            	<input type="radio" name="srReason" class="custom-control-input" value="찍을 수 없는 선물세트">
                            	<span class="custom-control-label"> 찍을 수 없는 선물세트 </span>
                            </label>
                        </div>
					</div>
					 <div class="modal-footer">
					 	<a href="#" class="btn btn-primary" id="sendingReqReason"> 출고요청 </a>
						<a href="#" class="btn btn-secondary" data-dismiss="modal"> 닫기 </a>
					</div>
				</div>
			</div>
		</div>
		<button type="button" id="ccOpenSocket" onclick="ccOpenSocket();" style="display: none;">Open</button>
		
	<button type="button" class="btn btn-primary btn-xs" id="alertBtn" data-toggle="modal" data-target="#alertModal" style="display:none; "> 알람  </button>
	<div class="modal fade" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="#alertModal" aria-hidden="true">
			<div class="modal-dialog" role="document" style="max-width: 700px;">
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title"> 운영팀 알림 </h3>
						<a href="#" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</a>
					</div>
					<div class="modal-body" >
						<p id="alertTextDiv"></hp>
					</div>
					 <div class="modal-footer">
						<a href="#" class="btn btn-secondary" data-dismiss="modal"> 닫기 </a>
					</div>
				</div>
			</div>
		</div>
</body> 
<script type="text/javascript">
	
	$("#ccOpenSocket").click();
	
	var message = new Audio();
	message.src = "/resources/libs/sending_sound/message.wav";
	
	var ccWs;
	
	$.ajax({
		type       : 'GET',
		url        : '/delivery/non_picking_count.do',
		success    : function(data){		
			$("#nonePickingCount").text(data);
			
		}
		
	});
	
	function ccOpenSocket(){
		
	    if(ccWs!==undefined && ccWs.readyState!==WebSocket.CLOSED){
	        return;
	    }
	
	    //웹소켓 객체 만드는 코드
	    ccWs=new WebSocket("ws://192.168.0.66:8081/ccsocket.do");
	    
	    
	    
	    ccWs.onopen=function(event){
	        if(event.data===undefined) return;
	
	    };
	    
	    ccWs.onmessage=function(event){
	    	ccWriteResponse(event.data);
	    };
	    
	    ccWs.onclose=function(event){
	
	    }
	    
	}
	
	function ccWriteResponse(text){
		if($("#alertModal").hasClass("show")){
			$("#alertTextDiv").append("<h4>"+text+"</h4");
		
		}else{
			message.play();
			$("#alertBtn").click();
			$("#alertTextDiv").html("<h4>"+text+"</h4");
			
		}
		
	}

</script>
<script src="${pageContext.request.contextPath}/resources/libs/js/renewal_sending_manage.js?v=<%=System.currentTimeMillis() %>"></script>
</html>
