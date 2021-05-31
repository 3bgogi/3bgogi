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

var doubleSubmitFlag = false;

function doubleSubmitCheck(){
    if(doubleSubmitFlag){
        return doubleSubmitFlag;
    }else{
        doubleSubmitFlag = true;
        return false;
    }
}

$(function(){
	$("#orderGiftSubmit").submit(function(){
		
		doubleSubmitCheck();
		
		if(doubleSubmitFlag == false){
			
			
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
                    	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 선물세트 입력 폼</h5>
                                <div class="card-body">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th scope="col">판매처</th>
                                                <th scope="col">주문 수</th>
                                                <th scope="col">입력일</th>
                                                <th scope="col">삭제여부</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<tr>
		                                     	<th scope="row"></th>
		                                        <th scope="row"></th>
		                                        <th scope="row"></th>
		                                        <th scope="row"></th>
		                                    </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 선물세트 엑셀로 나누기 </h5>
                                <div class="card-body">
                                    <form id="orderGiftSubmit" method="POST" action="<c:url value='/orders/devide/gift.do'/>" enctype="multipart/form-data">
                                    	<input type="hidden" name="orPk" value="${osVO.orPk }" id="orPk">
                                    	
                                        <div class="input-group mb-3">
                                        	<input type="file" name="excelfile" class="form-control">
	                                        <div class="input-group-append be-addon">
	                                        	<input type="submit" class="btn btn-success" value="해당 엑셀 파일로 나누기">
	                                        	<!-- <button type="button" type="submit" class="btn btn-success" > 해당 엑셀 파일로 나누기 </button> -->
	                                        </div>
                                        </div>
                                        
                                        
                                    </form>
                                </div>
                            </div>
                        </div>
                        
					</div>
	</div>
	
</body>
</html>
