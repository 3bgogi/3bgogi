<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="kr">
<head>
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title> 송장 발송 결과 </title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
    <script type="text/javascript">
    
    	$(function(){
    		
    		window.onbeforeunload = function(e){
    	    	opener.location.reload();
        	}
    		
    	});
    </script>
    <style>
    html,
    body {

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
		        <div class="card">
		            <div class="card-header" style="text-align: center;">
		                <h4 class="mb-1" style="color:red;"> ${osVO.dateStart } 의 발송 결과 </h4>

		                <c:set var="counting" value="1" />
		                <c:forEach var="od" items="${deliveryInfoList }">
		                	<h4>${counting}. 주문인 [${od.orBuyerName}] ${od.orDeliveryInvoiceNumber } - ${od.message }</h4>
		                	<c:set var="counting" value="${counting + 1 }" />
		                </c:forEach>
		                
		                
		                
		            </div>
		        </div>
			</div>
		</div>
	</div>
</body>
</html>
