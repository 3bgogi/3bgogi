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
    <title> 주문서 작업 기록 </title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/deliv_style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
    <script type = "text/javascript" src = "http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/libs/js/jquery-barcode.js"></script>
    <script type="text/javascript">
    
    	$(function(){
    	});
    </script>
</head>
<body>
	<div class="container-fluid  dashboard-content">
		<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
        	<div class="card">
            	<h5 class="card-header"> 주문서 작업 내역 </h5>
            <div class="card-body">
        		<table class="table table-hover">
                	<thead>
                		
						<tr>
							<th>IP</th>
							<th>관리자</th>
							<th>분류</th>
							<th>내역</th>
							<th>기록시간</th>
						</tr>
                    </thead>
                    <tbody>
                    	<c:if test="${!empty ohList }">
                    		<c:forEach var="ohlist" items="${ohList }">                    		
	                    		<tr>
	                    			<td>${ohlist.ohIp }</td>
	                    			<td>${ohlist.ohAdmin }</td>
	                    			<td>${ohlist.ohEndPoint }</td>
	                    			<td>${ohlist.ohDetail }</td>
	                    			<td>${ohlist.ohRegdate }</td>
	                    		</tr>
                    		</c:forEach>
                        </c:if>
                        <c:if test="${empty ohList }">
                        	<tr>
                        		<td colspan="5">작업기록이 없습니다</td>
                        	</tr>
                        </c:if>
                   	</tbody>
                </table>
             </div>
          </div>
        </div>
	</div>
</body>

</html>
