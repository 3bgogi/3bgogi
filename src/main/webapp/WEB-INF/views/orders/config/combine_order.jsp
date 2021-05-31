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
<title>주문서 정보 변경</title>
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
		
		$("#searchAddressBtn").click(function(){
			window.open("<c:url value='/orders/search/tab.do'/>", "우편 조회" , "width=700, height=800, top=100, left=300, scrollbars=no");
			
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
		<c:set var="combOdSize" value="${fn:length(combineOrderList)}"/>
		<div class="row">
			<!-- ============================================================== -->
			<!-- basic table -->
			<!-- ============================================================== -->
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="card">
					<c:if test="${combOdSize == 1 }">
						<h5 class="card-header"> 변경 할 주문 목록 </h5>
					</c:if>
					<c:if test="${combOdSize > 1 }">
						<h5 class="card-header"> 하나로 합칠 할 주문 목록 </h5>
					</c:if>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-hover" style="font-size: 12px; word-break: keep-all; white-space: nowrap;">
								<thead>
									<tr>
										<th scope="col"></th>
										<th scope="col">발송기한</th>
										<th scope="col">구매자</th>
										<th scope="col">주문표기명</th>
										<th scope="col">수령자</th>
										<th scope="col">주소</th>
										<th scope="col">구매자핸드폰</th>
										<th scope="col">수령자핸드폰</th>
										<th scope="col">배송메세지</th>
									</tr>
									
								</thead>
								<tbody class="devideTable">
									<c:forEach items="${combineOrderList }" var="orders">										
										<tr>
											<td>
												<label class="custom-control custom-radio custom-control-inline">
													<input type="radio" name="changeOrderInfo" class="custom-control-input"
													data-serial-special-number="${orders.orSerialSpecialNumber }"
													data-buyer-name="${orders.orBuyerName }"
													data-buyer-another-name="${orders.orBuyerAnotherName }"
													data-buyer-contract-number1="${orders.orBuyerContractNumber1 }"
													data-buyer-contract-number2="${orders.orBuyerContractNumber2 }"
													data-receiver-name="${orders.orReceiverName }"
													data-receiver-contract-number1="${orders.orReceiverContractNumber1 }"
													data-receiver-contract-number2="${orders.orReceiverContractNumber2 }"
													data-delivery-message="${orders.orDeliveryMessage }"
													data-shipping-address="${orders.orShippingAddress }"
													data-shipping-address-detail="${orders.orShippingAddressDetail }"
													data-shipping-address-number="${orders.orShippingAddressNumber }"
													data-sending-deadline="${orders.orSendingDeadline }"
													data-regdate="${orders.orRegdate }">
													<span class="custom-control-label"></span>
												</label>
											</td>
											<td>${orders.orSendingDeadline }</td>
											<td>${orders.orBuyerName }</td>
											<td>${orders.orBuyerAnotherName }</td>
											<td>${orders.orReceiverName }</td>
											<td>${orders.orShippingAddress } ${orders.orShippingAddressDetail }</td>
											<td>${orders.orBuyerContractNumber1 }</td>
											<td>${orders.orReceiverContractNumber1 }</td>
											<td>${orders.orDeliveryMessage }</td>
										</tr>
										
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="offset-xl-3 col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="card">
					<h5 class="card-header">정보 변경</h5>
					<div class="card-body">
						<form id="combineOrderForm">
							<c:forEach items="${combineOrderList }" var="orders">
								<input type="hidden" name="orSerialList" value="${orders.orSerialSpecialNumber }">
							</c:forEach>
							<input type="hidden" name="orRegdate" id="orRegdate">
							<input type="hidden" name="orSerialSpecialNumber" id="orSerialSpecialNumber">
							<div class="form-group">
								<label for="orSettlementDay">발송일</label> 
								<input class="form-control" id="orSendingDeadline" type="text" name="orSendingDeadline" >
							</div>
							<div class="form-group">
								<label for="orBuyerName">구매자</label> 
								<input class="form-control" id="orBuyerName" type="text" name="orBuyerName">
							</div>
							<div class="form-group">
								<label for="orBuyerName"> 주문표기명 </label> 
								<input class="form-control" id="orBuyerAnotherName" type="text" name="orBuyerAnotherName">
							</div>
							<div class="form-group">
								<label for="orReceiverName">수령자</label> 
								<input class="form-control" id="orReceiverName" type="text" name="orReceiverName">
							</div>
							<div class="form-group">
								<label for="orShippingAddressNumber"> 우편번호 </label>
								<div class="input-group mb-1" >
		                        	<input type="text" class="form-control form-control-sm" id="orShippingAddressNumber" name="orShippingAddressNumber" readonly="readonly" required>
		                        	<div class="input-group-append">
		                            	<button type="button" class="btn btn-primary btn-sm" id="searchAddressBtn"> 검색 </button>
		                            </div>
	                            </div>
							</div>
							<div class="form-group">
								<label for="orShippingAddress">주소</label> 
								<input class="form-control" id="orShippingAddress" type="text" name="orShippingAddress">
							</div>
							<div class="form-group">
								<label for="orShippingAddressDetail">상세주소</label> 
								<input class="form-control" id="orShippingAddressDetail" type="text" name="orShippingAddressDetail">
							</div>
							<div class="form-group">
								<label for="orReceiverContractNumber1">구매자핸드폰</label> 
								<input class="form-control" id="orBuyerContractNumber1" type="text" name="orBuyerContractNumber1">
							</div>
							<div class="form-group">
								<label for="orReceiverContractNumber1">구매자연락처</label> 
								<input class="form-control" id="orBuyerContractNumber2" type="text" name="orBuyerContractNumber2">
							</div>
							<div class="form-group">
								<label for="orReceiverContractNumber1">수령자핸드폰</label> 
								<input class="form-control" id="orReceiverContractNumber1" type="text" name="orReceiverContractNumber1">
							</div>
							<div class="form-group">
								<label for="orReceiverContractNumber1">수령자연락처</label> 
								<input class="form-control" id="orReceiverContractNumber2" type="text" name="orReceiverContractNumber2">
							</div>
							<div class="form-group">
								<label for="orDeliveryMessage">배송메세지</label> 
								<input class="form-control" id="orDeliveryMessage" type="text" name="orDeliveryMessage">
							</div>
							<div class="form-group text-right">
								<button class="btn btn-primary" type="button" id="combineOrderBtn"> 변경 </button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script
	src="${pageContext.request.contextPath}/resources/libs/js/renewal_cs_manage.js"></script>
</html>
