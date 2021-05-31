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
    <title> 업무 알람 </title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/datepicker/jquery.datetimepicker.css" />
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
    
    <script type="text/javascript">
    
    	$(function(){
    		
    		$('#aaWorkStart').datetimepicker({
    			format:'Y-m-d H:i:s',
    			lang:'kr'
    		});
    		
    		$('#aaWorkEnd').datetimepicker({
    			format:'Y-m-d H:i:s',
    			lang:'kr'
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
<body>
        <div class="splash-container">
        	<div class="card">        	
	            <div class="card-header" style="text-align: center;">
	                <h4 class="mb-1" style="color:red;"> 해당 날짜는 신중하게 변경해야 합니다. </h4>
	                
	            </div>
	            <form class="card-body" id="attendanceChangeForm" method="POST">
	            	<input type="hidden" name="aaPk" value="${aaVO.aaPk }">
	            	<div class="form-group">
		                <label for="aaWorkStart"> 업무 시작 시간</label>
		                <input class="form-control" id="aaWorkStart" name="aaWorkStart" type="text" value="<fmt:formatDate value="${aaVO.aaWorkStart }" pattern="yyyy-MM-dd HH:mm:ss"/>">
	                </div>
	                
	            	<div class="form-group">            	
		            	<label for="aaWorkEnd"> 업무 종료 시간 </label>
		                <input class="form-control" id="aaWorkEnd" name="aaWorkEnd" type="text" value="<fmt:formatDate value="${aaVO.aaWorkEnd }" pattern="yyyy-MM-dd HH:mm:ss"/>">
	            	</div>
	            	<div class="form-group">            	
		            	<label for="aaBreakTime"> 휴게시간 ( 분 단위 ) </label>
		                <input class="form-control" id="aaBreakTime" name="aaBreakTime" type="number" min="0" max="60" step="30" value="${aaVO.aaBreakTime }">
	            	</div>
	            	<div class="form-group">
	            		<button type="submit" class="btn btn-primary btn-block"> 수정하기 </button>
	            	</div>
	            </form>
        	</div>
        </div>
</body>

<script src="${pageContext.request.contextPath}/resources/vendor/datepicker/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript">
		$(function() {

			$.datetimepicker.setLocale('kr');
			
			$("#attendanceChangeForm").submit(function(){
				if(confirm("해당 정보로 출퇴근 기록을 수정하시겠습니까?")){					
					var attendanceFormData = jQuery("#attendanceChangeForm").serialize();
					
					$.ajax({
						type : "POST",
						url:"<c:url value='/admin/team/attendance_change.do'/>",
						data: attendanceFormData,
						success:function(data){
							if(data == true){
								alert("수정 완료");
								opener.location.reload();
								self.close();
							}
						}
					});
				}
				
				event.preventDefault();
				return false;
			});
		});	
	</script>
</html>
