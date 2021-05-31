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
<title>${ordersVO.orProduct } 상품명 매칭</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/pace-master/pace.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/js/common_util.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/renewal_page.css">
<script src="${pageContext.request.contextPath}/resources/libs/js/renewal_matching_manage.js"></script>
<script type="text/javascript">
$(function(){
	
	$("#productName").focus();
});

$(document).ajaxStart(function() {
	Pace.start();
}).ajaxStop(function() {
	Pace.stop();
});
$.ajaxSetup({
	
    beforeSend: function(xhr) {
        xhr.setRequestHeader("AJAX", true);
     },
     error: function(xhr, status, err) {
        if (xhr.status == 401) {
            alert("인증에 실패 했습니다. 로그인 페이지로 이동합니다.");
            location.href = "/loginFail.do";
         } else if (xhr.status == 403) {
            alert("세션이 만료가 되었습니다. 로그인 페이지로 이동합니다.");
              location.href = "/login.do";
         } else if (xhr.status == 500) {
             alert(" 500에러 ");
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
		<form class="row" name="productListSearchForm" action="<c:url value='/order/matching/product_list_result.do'/>" method="get">
			<input type="hidden" value="${ordersVO.orPk }" name="orFk">
			<input type="hidden" value="${ordersVO.orProduct }" name="orProductName">
			<input type="hidden" value="${editFlag }" name="editFlag">
			<input type="hidden" value="${pmVO.pmPk }" name="pmPk">
			<div class="col-lg-12">
				<h3>판매처 상품명 : ${ordersVO.orProduct }</h3>
			</div>
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
						<input class="form-control" type="text" placeholder="상품 검색" name="productName" id="productName">
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
				<div class="row">
					<c:if test="${!empty  pmVO}">
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mb-4">
							<span class="alert alert-primary"> 현재 매칭된 상품 => ${pmVO.productsVOList[0].productName }</span>
						</div>
					</c:if>
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="productSearchList">
					</div>
					<!-- <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" style="display: none;">
						<nav aria-label="Page navigation" style="text-align: center;">
							<ul class="pagination" style="display: -webkit-inline-box;">
								<li class="page-item"><a class="page-link" href="#">Previous</a></li>
								<li class="page-item"><a class="page-link" href="#">1</a></li>
								<li class="page-item active"><a class="page-link " href="#">2</a></li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item"><a class="page-link" href="#">Next</a></li>
							</ul>
						</nav>
					</div> -->
				</div>
			</div>
		</div>
</body>

<script type="text/javascript">
window.paceOptions = {
		target : "#progress-bar",
		startOnPageLoad : false,
		ajax : false
};
	
</script>

</html>
