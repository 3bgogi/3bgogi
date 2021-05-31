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
    <title> 3Bgogi Renewal Home Page - 회원가입</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
    <script type="text/javascript">
    
    	$(function(){
    		
    		$("#signUp").click(function(event){
    			
    			//정규화 공식
    			var regNumber = /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/;
    			
		    	var adminId	= $("input[name=adminId]").val();
	    		var adminPass = $("input[name=adminPass]").val();
	    		var adminPassCheck = $("input[name=adminPassCheck]").val();	
	    		var adminName = $("input[name=adminName]").val();
	    		var adminPhone = $("input[name=adminPhone]").val();
	    		var adminAddress = $("input[name=adminAddress]").val();
	    		
	    		if(adminId.trim() == "" || adminId.length < 4){
	    			alert("아이디 빈값이 아니거나 4자리 이상이어야 합니다.");
	    			$("input[name=adminId]").focus();
	    			return false;
	    			
	    		}else if(adminPass.length < 6){
	    			alert("비밀번호는 6자리 이상이어야 합니다.");
	    			$("input[name=adminPass]").focus();
	    			return false;
	    			
	    		}else if(adminPass != adminPassCheck){
	    			alert("비밀번호가 서로 다릅니다");
	    			$("input[name=adminPass]").val("");
	    			$("input[name=adminPass]").val("");
	    			$("input[name=adminPass]").focus();
	    			return false;
	    			
	    		}else if(adminName.trim() == ""){
	    			alert("이름을 입력해주세요.");
	    			$("input[name=adminName]").focus();
	    			return false;
	    			
	    		}else if(regNumber.test(adminPhone) == false){
	    			alert("연락처는 숫자만 입력해야 합니다.");
	    			$("input[name=adminPhone]").focus();
	    			return false;
	    			
	    		}
	    		
	    		$("form[name=signUpForm]").submit();
	    		
    		});
    		
    	});
    </script>
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
<!-- ============================================================== -->
<!-- signup form  -->
<!-- ============================================================== -->

<body>
    <!-- ============================================================== -->
    <!-- signup form  -->
    <!-- ============================================================== -->
    
    
    <form name="signUpForm" class="splash-container" action="<c:url value='/sign_up.do'/>" method="post">
        <div class="card">
            <div class="card-header" style="text-align: center;">
                <h3 class="mb-1"> 삼형제고기 관리자 가입</h3>
                <p> 가입 뒤 최종 관리자에게 연락주세요. </p>
            </div>
            <div class="card-body">
                <div class="form-group">
                    <input class="form-control form-control-lg" type="text" name="adminId" placeholder="아이디" autocomplete="off">
                </div>
                <div class="form-group">
                    <input class="form-control form-control-lg" type="password" name="adminPass"placeholder="비밀번호" autocomplete="off">
                </div>
                <div class="form-group">
                    <input class="form-control form-control-lg" type="password" name="adminPassCheck"placeholder="비밀번호 확인" autocomplete="off">
                </div>
                <div class="form-group">
                    <input class="form-control form-control-lg" type="text" name="adminName" placeholder="이름">
                </div>
                <div class="form-group">
                    <input class="form-control form-control-lg" id="adminPhone" type="text" name="adminPhone" placeholder="연락처" onkeyup="checkphone()">
                </div>
                <div class="form-group">
                    <input class="form-control form-control-lg" type="text" name="adminAddress" placeholder="주소">
                </div>
                <div class="form-group pt-2">
                    <button class="btn btn-block btn-primary" id="signUp" type="button"> 가입하기 </button>
                </div>
            </div>
            <div class="card-footer bg-white">
                <p> 관리자 아이디가 있다면  <a href="<c:url value='/login.do'/>" class="text-secondary">여길 눌러주세요</a></p>
            </div>
        </div>
    </form>
</body>

 
</html>
