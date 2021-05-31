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
    <title> 3Bgogi Renewal Home Page - 로그인</title>
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <style>
    html,
    body {
        height: 100%;
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
    <!-- ============================================================== -->
    <!-- login page  -->
    <!-- ============================================================== -->
    <div class="splash-container">
    
        <div class="card ">
            <div class="card-header text-center"><img class="logo-img" src="${pageContext.request.contextPath}/resources/images/icon/3bgogi_logo.png" alt="logo"></div>
            <div class="card-body">
                <form action="<c:url value='/j_spring_security_check.do'/>" method="post">
                    <div class="form-group">
                        <input class="form-control form-control-lg" id="j_username" type="text" placeholder=" ID " name="j_username" autofocus>
                    </div>
                    <div class="form-group">
                        <input class="form-control form-control-lg" id="j_password" type="password" name="j_password" placeholder=" PASSWORD">
                    </div>
                    <button type="submit" class="btn btn-primary btn-lg btn-block"> 로그인 </button>
                </form>
            </div>
            <div class="card-footer bg-white p-0 " style="text-align: center;">
            	<c:if test="${fail==true }">
	            	<div class="card-footer-item">
	                    <span style="color:red;"> 아이디 혹은 비밀번호가 다르거나 권한이 없습니다.</span>
	                </div>
            	</c:if>
                <div class="card-footer-item card-footer-item-bordered">
                    <a href="<c:url value='/sign_up.do'/>" class="footer-link"> 회원 가입 </a>
                </div>
            </div>
        </div>
    </div>
  
  
    <!-- ============================================================== -->
    <!-- end login page  -->
    <!-- ============================================================== -->
    <!-- Optional JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
</body>
 
</html>
