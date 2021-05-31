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
<title> 주문서 나누기 </title>
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
		$(document).on("click","img[name=movingCheck]", function(){
			
			if($(this).parent().parent().parent().hasClass("originalTable")===true){
				var cloneVar;
				cloneVar = $(this).parent().parent().clone();
				$(this).parent().parent().empty();
				$(".devideTable").append(cloneVar);
			}else{
				var cloneVar;
				cloneVar = $(this).parent().parent().clone();
				$(this).parent().parent().empty();
				$(".originalTable").append(cloneVar);
			}
		});
		
		$("#orderDevideBtn").click(function(){
			
			var orPkList = new Array();
			
			$(".devideTable input[name=ordata]").each(function(){
				orPkList.push($(this).data("or-pk"));
			});
			
			$.ajax({
				type       : 'POST',
				data       : {
					"orPkList":orPkList
				},
				url        : '/orders/config/devide.do',
				success    : function(data){
					alert("주문서 나누기 완료");
					opener.location.reload();
					self.close();
				}
				
			});
			
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
					<h5 class="card-header">원본 주문 목록</h5>
					<div class="card-body">
						<table class="table">
							<thead>
								<tr>
									<th scope="col"></th>
									<th scope="col">주문자/수령자</th>
									<th scope="col">상품</th>
									<th scope="col">주문가</th>
								</tr>
							</thead>
							<tbody class="originalTable">
								<c:forEach var="orderlist" items="${orderList }">
									<tr>
										<td>
											<img src="${pageContext.request.contextPath}/resources/images/switch.png" name="movingCheck" style="cursor:pointer;">
											<input type="hidden" name="ordata" data-or-pk="${orderlist.orPk }" data-ss-fk="${orderlist.ssFk }">
										</td>
										<td>${orderlist.orBuyerName } / ${orderlist.orReceiverName }</td>
										<td>
											<c:forEach var="pmVoList" items="${orderlist.productMatchingVOList }">
												<c:forEach var="omVoList" items="${pmVoList.optionMatchingVOList }">
													<c:forEach var="productOptions" items="${omVoList.productOptionList }">
														<p>${productOptions.productName } - ${productOptions.optionName } ${omVoList.omOptionAmount * orderlist.orAmount } 개</p>
													</c:forEach>
												</c:forEach>
											</c:forEach>
										</td>
										<td><fmt:formatNumber value="${orderlist.orTotalPrice }" pattern="#,###"/> 원</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="card">
					<h5 class="card-header">나눠질 주문 목록</h5>
					<div class="card-body">
						<table class="table">
							<thead>
								<tr>
									<th scope="col"></th>
									<th scope="col">주문자/수령자</th>
									<th scope="col">상품</th>
									<th scope="col">주문가</th>
								</tr>
							</thead>
							<tbody class="devideTable">

							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-4 offset-4">
					<button class="btn btn-primary btn-block" id="orderDevideBtn"> 나누기 </button>
				</div>
				<form method="POST" id="devideForms">
				
				</form>
			</div>
		</div>
	</div>
</body>
<script
	src="${pageContext.request.contextPath}/resources/libs/js/renewal_matching_manage.js"></script>
</html>
