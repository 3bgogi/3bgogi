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
    <title> 발송 변경 </title>
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
    		
    		$('#dateStart').datetimepicker({
    			format:'Y-m-d',
    			lang:'kr',
    			timepicker:false,
    			minDate : formatDate(new Date())
    		});
    		
    	});
    	
    	function formatDate(date) {
    	    var d = new Date(date),
    	        month = '' + (d.getMonth() + 1),
    	        day = '' + d.getDate(),
    	        year = d.getFullYear();
    	
    	    if (month.length < 2) month = '0' + month;
    	    if (day.length < 2) day = '0' + day;
    	
    	    return [year, month, day].join('-');
    	}
    	
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
	                <h4 class="mb-1" style="color:red;"> 발송일 변경 </h4>
	                
	            </div>
	            <form class="card-body" id="sendingDeadlineChangeForm" method="POST">
	            	<c:forEach var="serialNum" items="${osVO.orSerialSpecialNumberList }">	            	
		            	<input type="hidden" name="orSerialSpecialNumberList" value="${serialNum }">
	            	</c:forEach>
	            	<div class="form-group">
		                <label for="aaWorkStart"> 발송일 </label>
		                <input class="form-control" id="dateStart" name="dateStart" type="text" value="${osVO.dateStart }">
	                </div>
	            	<div class="form-group">
	            		<button type="submit" class="btn btn-primary btn-block"> 변경하기 </button>
	            	</div>
	            </form>
        	</div>
        </div>
</body>

<script src="${pageContext.request.contextPath}/resources/vendor/datepicker/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript">
		$(function() {

			$.datetimepicker.setLocale('kr');
			
			$("#sendingDeadlineChangeForm").submit(function(){
				var orSendingDeadline = $("#dateStart").val();
				
				if(confirm(orSendingDeadline+" 로 발송날짜를 변경하시겠습니까?")){					
					
					var sendingDeadlineChangeFormData = jQuery("#sendingDeadlineChangeForm").serialize();
					
					$.ajax({
						type : "POST",
						url:"<c:url value='/orders/change/deadline.do'/>",
						data: sendingDeadlineChangeFormData,
						success:function(data){
							if(data == true){
								alert("변경 완료");
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
