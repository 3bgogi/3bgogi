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
<title>${ordersVO.orProduct } 의 옵션 ${ordersVO.orProductOption } 매칭</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script type="text/javascript">
    
    	$(function(){
    		$("#productName").focus();
    	});
    	
    	window.onbeforeunload = function(e){
    		if($("input[name=editFlag]").val() == 'true'){    			
	    		opener.location.reload();
    		}
    	}
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
		<form class="row" name="productOptionListSearchForm" action="<c:url value='/order/matching/product_list_result.do'/>" method="get">
			<input type="hidden" value="${ordersVO.orPk }" name="orFk">
			<input type="hidden" value="${ordersVO.orProduct }" name="orProductName">
			<input type="hidden" value="${ordersVO.orProductOption }" name="orProductOption">
			<input type="hidden" value="${ordersVO.pmFk }" name="pmFk">
			<input type="hidden" value="${editFlag }" name="editFlag">
			<div class="col-lg-3">
				<select class="form-control" name=cfFk>
					<option value="0">전체 선택</option>
					<c:forEach var="cclist" items="${ccList }">
						<option value="${cclist.cfPk }">${cclist.cfCodeType }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-lg-9">
				<div class="email-search">
					<div class="input-group input-search">
						<input class="form-control" type="text" placeholder="상품 검색" id="productName" name="productName">
						<span class="input-group-btn">
							<button class="btn btn-secondary" id="productSearchButton" type="submit">
								<i class="fas fa-search"></i>
							</button>
						</span>
					</div>
				</div>
			</div>
		</form>
		<hr>
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				
				<c:if test="${editFlag == true}">
					<c:if test="${!empty omList}">
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mb-4">
							<div class="card">
								<div class="card-header">
									<span class="text-primary"> 현재 매칭된 옵션의 상품</span>
								</div>
	                           	<div class="card-body">
	                           		<c:forEach var="omlist" items="${omList }">
	                           			<div class="input-group input-group-sm mb-1">
		                                    <input type="text" disabled="disabled" class="form-control" value="${omlist.productOptionList[0].productName } [ ${omlist.productOptionList[0].optionName } ] 주문개수 * ${omlist.omOptionAmount} 개">
		                                    <div class="input-group-append">
		                                    	<button type="button" class="btn btn-danger" name="optionMatchingDeleteBtn" value="${omlist.omPk }"> 매칭 삭제하기  </button>
		                                    </div>
	                                    </div>
	                               	</c:forEach>
	                          	</div>
	                        </div>
						</div>				
					</c:if>
				</c:if>
				<div class="row">
					<c:if test="${editFlag == true}">
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="productOptionSearchList">
					</c:if>
					<c:if test="${editFlag == false}">
						<div class="col-xl-8 col-lg-8 col-md-8 col-sm-12 col-12" id="productOptionSearchList">
					</c:if>
					
						
					</div>
					<c:if test="${editFlag == true}">
					</c:if>
					<c:if test="${editFlag == false}">
						<div class="col-xl-4 col-lg-4 col-md-4 col-sm-12 col-12">
							<div class="product-sidebar">
								<div class="product-sidebar-widget">
									<h4 class="mb-0">판매처 상품명 : ${ordersVO.orProduct } </h4> 
									<h4 class="mb-0">판매처 옵션명 : ${ordersVO.orProductOption } </h4>
									<hr>
									<h4 class="mb-0"> 선택된 옵션 </h4>
									
								</div>
								<form class="product-sidebar-widget" id="productOptionInsertForm">
								
								</form>
								<div class="product-sidebar-widget">
									<a href="#" class="btn btn-outline-light"> 전부 취소하기 </a>
								</div>
								<div class="product-sidebar-widget">
									<button class="btn btn-primary btn-block" id="matchingOptionButton"> 매칭하기 </button>
								</div>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
</body>
<script src="${pageContext.request.contextPath}/resources/libs/js/renewal_matching_manage.js"></script>
</html>
