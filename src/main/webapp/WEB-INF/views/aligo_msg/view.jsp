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
<title> 알리고 문자 보내기 </title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap-select/css/bootstrap-select.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/multi-select/css/multi-select.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/datepicker/jquery.datetimepicker.css" />

<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/multi-select/js/jquery.multi-select.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/js/common_util.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-select/js/bootstrap-select.js"></script>
<script type="text/javascript">
	$(function(){
		
		$('#rDate').datetimepicker({
			timepicker:false,
			lang:'kr',
			format:'Ymd'
			
		});
		
		$('#rTime').datetimepicker({
			datepicker:false,
			timepicker:true,
			lang:'kr',
			format:'Hi'
		});
		
		$("button[name=insertSmsFormBtn]").click(function(){
			var asfHead = $(this).data("asf-head");
			var asfBody = $(this).data("asf-body");
			var asfFooter = $(this).data("asf-footer");
			
			var phoneTextArea = asfHead+"\n"+asfBody+"\n"+asfFooter;
			
			$("#phone_text_area").val(phoneTextArea);
			
			
		});
		var aligoConting = 0;
		
		$('#aligoSendingData').multiSelect({
			afterSelect: function(values){
				var exceptSize = $("#aligoSendingData option:selected").length;
				var targetSize = $("#aligoSendingData option").not(":selected").length;
				
				$("#targetSize").text(targetSize+" 명");
				$("#exceptSize").text(exceptSize+" 명");
				
			  },
			  afterDeselect: function(values, text){
				  var exceptSize = $("#aligoSendingData option:selected").length;
					var targetSize = $("#aligoSendingData option").not(":selected").length;
					
					$("#targetSize").text(targetSize+" 명");
					$("#exceptSize").text(exceptSize+" 명");
			  }
		});
		
		$("#userInsertPhone").submit(function(){
			var receiverName = $("#receiverName").val();
			var receiverPhone = $("#receiverPhone").val();
			
			if(receiverName == ''){
				alert("고객명을 입력해주세요.");
				$("#receiverName").focus();
				
				event.preventDefault();
				return false;
			}else if(receiverPhone == ''){
				$("#receiverPhone").focus();
				
				event.preventDefault();
				return false;
				
			}
			
			var optionForm = "<option data-receiver='"+receiverPhone+"' data-destination='"+receiverName+"' value='"+receiverPhone+" "+receiverName+"'>"+receiverName+" "+receiverPhone+"</option>";
			
			$('#aligoSendingData').append(optionForm);
			
			$("#aligoSendingData").multiSelect("refresh");
			
			var exceptSize = $("#aligoSendingData option:selected").length;
			var targetSize = $("#aligoSendingData option").not(":selected").length;
			
			$("#targetSize").text(targetSize+" 명");
			$("#exceptSize").text(exceptSize+" 명");
			
			var receiverName = $("#receiverName").val("");
			var receiverPhone = $("#receiverPhone").val("");
			
			$("#receiverName").focus();
			
			event.preventDefault();
			return false;
		});
		
		$("#aligoExcelFileReadForm").submit(function(){
			
			var form = $('#aligoExcelFileReadForm')[0];
            var formData = new FormData(form);
            
            formData.append("aligoExcelFile", $("#AligoExcelFile")[0].files[0]);
            
			// var aligoForm = jQuery("#aligoExcelFileReadForm").serialize();
			
			$.ajax({
				processData: false,
                contentType: false,
				type       : 'POST',
				data       : formData,
				url        : '<c:url value="/aligo_msg/excel.do"/>',
				success    : function(data){
					
					var optionForm = "";
					
					$.each(data, function(){
						optionForm = "<option data-receiver='"+this.receiver+"' data-destination='"+this.destination+"' value='"+this.receiver+" "+this.destination+"'>"+this.destination+" "+this.receiver+"</option>";
						$('#aligoSendingData').append(optionForm);
					});
					
					$("#aligoSendingData").multiSelect("refresh");
					
					alert("엑셀파일 입력완료");
					
					var exceptSize = $("#aligoSendingData option:selected").length;
					var targetSize = $("#aligoSendingData option").not(":selected").length;
					
					alert(targetSize+" 명");
					alert(exceptSize+" 명");
					$("#targetSize").text(targetSize+" 명");
					$("#exceptSize").text(exceptSize+" 명");
				}
				
			});
			
			event.preventDefault();
			return false;
			
		});
		
		
		// phone_text_area
		
		$("#sendingBtn").click(function(){
			
			if(!confirm("해당 내용으로 문자를 보내시겠습니까?")){
				
				event.preventDefault();
				return false;
			}
			
			 var receiver = "";
			 var destination = "";
			 var sendingSize = $("#aligoSendingData option").not(":selected").length;
			 var msg = $("#phone_text_area").val();
			 var rDate = $("#rDate").val();
			 var rTime = $("#rTime").val();
			 var testmodeYn = $("input[name=testmodeYn]").val();
			 
			 if(sendingSize == 0){
				 alert("문자 대상자가 존재하지 않습니다");
				 event.preventDefault();
				 return false;
			 }
			 if(msg == ''){
				 alert("문자 내용을 작성해주세요");
				 $("#phone_text_area").focus();
				 return false;
			 }
			 
			 $('#aligoSendingData :not(:selected)').each(function(i, selected) {
				 if(i == 0){
					  receiver+=$(selected).data("receiver");
					  destination+=$(selected).data("receiver")+"|"+$(selected).data("destination");
				 }else{
					 receiver+=","+$(selected).data("receiver");
					 destination+=","+$(selected).data("receiver")+"|"+$(selected).data("destination");
				 }
				 
		     });
			 
			 if(msg.match("%고객명%")){
				 $.ajax({
					type       : 'POST',
					async      : false,
					data       : {
					    "receiver":receiver,
					    "destination":destination,
					    "msg":msg,
					    "rDate":rDate,
					    "rTime":rTime,
					    "testmodeYn":testmodeYn
					},
					url        : '<c:url value="/aligo_msg/sending.do"/>',
					success    : function(data){
					 	alert(data);   	
					}
						    
				});
			 }else{
				 $.ajax({
					type       : 'POST',
					async      : false,
					data       : {
					    "receiver":receiver,
					    "msg":msg,
					    "rDate":rDate,
					    "rTime":rTime,
					    "testmodeYn":testmodeYn
					},
					url        : '<c:url value="/aligo_msg/sending.do"/>',
					success    : function(data){
						alert(data);  
					}
					    
				});
			 }
			 
		});
	});
</script>
<style>
#sendingBtn{
	position: absolute;
	top: 79%;
    left: 4.6%;
    width: 76.5%;
    margin-left: 7.2%;
    
}

#phone_backgounrd{
    height: 700px;
    background-size: cover;
    margin-bottom: 50px;
}
#phone_text_area{
	resize: none;
    position: absolute;
    left: 4.6%;
    top: 31%;
    width: 76.5%;
    height: 44.5%;
    margin: 7.2%;
    
}
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
                        <!-- profile -->
                        <!-- ============================================================== -->
                        <!-- ============================================================== -->
                        <!-- end profile -->
                        <!-- ============================================================== -->
                        <!-- ============================================================== -->
                        <!-- campaign data -->
                        <!-- ============================================================== -->
                        <div class="col-xl-9 col-lg-9 col-md-7 col-sm-12 col-12">
                            <!-- ============================================================== -->
                            <!-- campaign tab one -->
                            <!-- ============================================================== -->
                            <div class="influence-profile-content pills-regular">
                                <ul class="nav nav-pills mb-3 nav-justified" id="pills-tab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link active" id="aligo_info_tab" data-toggle="pill" href="#aligo_info" role="tab" aria-controls="aligo_info" aria-selected="true"> 알리고 문자 보내기 </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="aligo_config_tab" data-toggle="pill" href="#aligo_config" role="tab" aria-controls="aligo_config" aria-selected="false"> 알리고 문자 폼 </a>
                                    </li>
                                </ul>
                                <div class="tab-content" id="tab-contents">
                                    <div class="tab-pane fade show active" id="aligo_info" role="tabpanel" aria-labelledby="aligo_info_tab">
                                        <div class="row">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <div class="section-block">
                                                    <h3 class="section-title">Aligo 문자 발송 정보</h3>
                                                </div>
                                            </div>
                                            <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12">
                                                <div class="card">
                                                    <div class="card-body">
                                                        <h1 class="mb-1"> <fmt:formatNumber value="${aligoRemain.SMS_CNT }" pattern="#,###" /> 건 </h1>
                                                        <p> SMS 발송 가능 건</p>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12">
                                                <div class="card">
                                                    <div class="card-body">
                                                        <h1 class="mb-1"> <fmt:formatNumber value="${aligoRemain.LMS_CNT }" pattern="#,###" /> 건</h1>
                                                        <p> LMS 발송 가능 건</p>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12">
                                                <div class="card">
                                                    <div class="card-body">
                                                        <h1 class="mb-1"> <fmt:formatNumber value="${aligoRemain.MMS_CNT }" pattern="#,###" /> 건</h1>
                                                        <p> MMS 발송 가능 건</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="section-block">
                                            <h3 class="section-title"> 문자 대상자 </h3>
                                        </div>
                                        <div class="row">
	                                        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
	                                        	<div class="card">
		                                        	<div class="card-body">
		                                        		<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
			                                                <form id="aligoExcelFileReadForm" method="post"  enctype="multipart/form-data">
						                                        <div class="input-group mb-3">
						                                        	<input type="file" class="form-control" id="AligoExcelFile" name="AligoExcelFile">
							                                        <div class="input-group-append be-addon">
							                                        	<button type="submit" class="btn btn-success" > 엑셀파일 입력  </button>
							                                        </div>
						                                        </div>
						                                    </form>
			                                            </div>
		                                        	</div>
		                                            <div class="card-body">
		                                                <select multiple="multiple" id="aligoSendingData" name="aligoSendingData[]">
		                                                	<c:set var="aligoCounting" value="0"/>
												      		<c:if test="${!empty aligoList }">
												      			<c:forEach var="aligolist" items="${aligoList.aligoVOList }">												      			
													      			<option 
													      				data-receiver="${aligolist.receiver }" 
													      				data-destination="${aligolist.destination }" 
													      				value="${aligolist.receiver } ${aligolist.destination }"
													      				>${aligolist.destination } ${aligolist.receiver }</option>
													      				<c:set var="aligoCounting" value="${aligoCounting + 1 }"/>
												      			</c:forEach>
												      		</c:if>
											            </select>
		                                            </div>
		                                            <div class="card-body">
		                                        		<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
			                                                <form id="userInsertPhone" method="get">
						                                        <div class="input-group mb-3">
						                                        	<input type="text" class="form-control" id="receiverName" name="receiverName" placeholder="고객명">
						                                        	<input type="text" class="form-control phone-inputmask" id="receiverPhone" name="receiverPhone" placeholder="핸드폰 번호">
							                                        <div class="input-group-append be-addon">
							                                        	<button type="submit" class="btn btn-primary" > 직접 입력  </button>
							                                        </div>
						                                        </div>
						                                    </form>
			                                            </div>
		                                        	</div>
		                                            <div class="row border-top card-footer p-0" style="margin: 0px;">
		                                                <div class="campaign-metrics d-xl-inline-block col-xl-6 col-lg-6 col-md-6 col-sm-6 col-6">
		                                                    <h4 class="mb-0" id="targetSize"> 
		                                                    <c:if test="${!empty aligoCounting }">
		                                                    	<fmt:formatNumber value="${aligoCounting }" pattern="#,###"/> 명
		                                                    </c:if>
		                                                    <c:if test="${empty aligoCounting }">
		                                                    	0 명 
		                                                    </c:if>
		                                                    </h4>
		                                                    <p> 문자 발송 대상자(중복자포함) </p>
		                                                </div>
		                                                <div class="campaign-metrics d-xl-inline-block col-xl-6 col-lg-6 col-md-6 col-sm-6 col-6">
		                                                    <h4 class="mb-0" id="exceptSize"> 0 명 </h4>
		                                                    <p> 문자 제외 대상자 </p>
		                                                </div>
		                                            </div>
		                                        </div>
	                                        </div>
	                                        
	                                        <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
	                                        	<div class="card">
	                                        		<h5 class="card-header"> 환경 설정  </h5>
	                                        		
		                                        	<div class="card-body">
		                                        		<div class="col-md-12 mb-3">
		                                                	<label for="rDate"> 예약일 </label>
		                                                    <input type="text" class="form-control" id="rDate" name="rDate" placeholder="" value="" required="">
		                                                </div>
		                                                <div class="col-md-12 mb-3">
		                                                	<label for="rTime"> 예약 시간 </label>
		                                                    <input type="text" class="form-control" id="rTime" name="rTime" placeholder="" value="" required="">
		                                               	</div>
		                                               	<div class="col-md-12 mb-3">
		                                                	<label for=""> 테스트 여부 </label>
		                                                    <div class="col-12 col-sm-12 col-lg-12">
					                                            <label class="custom-control custom-radio custom-control-inline">
					                                                <input type="radio" name=testmodeYn checked="checked" value="Y" class="custom-control-input"><span class="custom-control-label">사용</span>
					                                            </label>
					                                            <label class="custom-control custom-radio custom-control-inline">
					                                                <input type="radio" name="testmodeYn" value="" class="custom-control-input"><span class="custom-control-label">사용하지 않음</span>
					                                            </label>
				                                            </div>
		                                               	</div>
		                                        	</div>
		                                        </div>
	                                        </div>
                                        
                                        </div>
                                        
                                    </div>
                                    <div class="tab-pane fade" id="aligo_config" role="tabpanel" aria-labelledby="#aligo_config_tab">
                                        <div class="row">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <div class="section-block">
                                                    <h2 class="section-title"> 문자 폼 삽입 </h2>
                                                </div>
                                            </div>
                                            <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12">
                                            	<form class="card" method="POST" action="<c:url value='/aligo_msg/form_insert.do'/>">
                                                    <div class="card-header bg-primary text-center p-3">
                                                        <h4 class="mb-0 text-white">새로운 문자 폼 입력</h4>
                                                    </div>
                                                    <div class="form-group row" style="padding: 7px 0;">
			                                            <label class="col-12 col-sm-3 col-form-label text-sm-right">상단</label>
			                                            <div class="col-12 col-sm-8 col-lg-6">
			                                                <input type="text" required="" name="asfHead" class="form-control form-control-sm">
			                                            </div>
			                                        </div>
			                                        <div class="form-group row" style="padding: 7px 0;">
			                                            <label class="col-12 col-sm-3 col-form-label text-sm-right">하단</label>
			                                            <div class="col-12 col-sm-8 col-lg-6">
			                                                <input type="text" required="" name="asfFooter" class="form-control form-control-sm">
			                                            </div>
			                                        </div>
			                                        <div class="card-body border-top">
                                                        <textarea class="form-control" name="asfBody" rows="12" cols="12"></textarea>
                                                        <button class="btn btn-outline-primary btn-block btn-lg"> 폼 입력하기 </button>
                                                    </div>
                                                </form>
                                            </div>
                                            <c:if test="${!empty asfList }">
                                            	<c:forEach var="asflist" items="${asfList }">
                                            		<div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12">
		                                                <div class="card">
		                                                    <div class="card-header bg-primary text-center p-3 ">
		                                                        <h4 class="mb-0 text-white"> 기본형 </h4>
		                                                    </div>
		                                                    <div class="card-body text-center">
		                                                        <p>${asflist.asfHead }</p>
		                                                        <p>${asflist.asfFooter } </p>
		                                                    </div>
		                                                    <div class="card-body border-top">
		                                                        <textarea class="form-control" rows="12" cols="12">${asflist.asfBody}</textarea>
		                                                        <button class="btn btn-outline-secondary btn-block btn-lg" name="insertSmsFormBtn"
		                                                        	data-asf-head="${asflist.asfHead }"
		                                                        	data-asf-body="${asflist.asfBody}"
		                                                        	data-asf-footer="${asflist.asfFooter}"
		                                                        > 폼 삽입 </button>
		                                                    </div>
		                                                </div>
		                                            </div>
                                            	</c:forEach>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- ============================================================== -->
                            <!-- end campaign tab one -->
                            <!-- ============================================================== -->
                        </div>
                        <div class="col-xl-3 col-lg-3 col-md-5 col-sm-12 col-12">
                            <!-- ============================================================== -->
                            <!-- card profile -->
                            <!-- ============================================================== -->
                            <div class="card">
                                <div class="card-body">
                                    <div class="user-avatar text-center d-block">
                                        
                                    </div>
                                    <div class="text-center">
                                        <h2 class="font-24 mb-0"> 알리고 문자 미리 보기 </h2>
                                        <p> 예시 이미지 </p>
                                    </div>
                                </div>
                                <div id="phone_backgounrd" class="card-body border-top" style="background-image: url('${pageContext.request.contextPath}/resources/images/aligo/phone_background.png');">
                                	<textarea id="phone_text_area" name="msg" class="form-control" rows="10" cols="" style="resize: none;"></textarea>
                                    <button id="sendingBtn" type="button" class="btn btn-primary btn-lg"> 전송 </button>
                                    
                                </div>
                                
                            </div>
                            <!-- ============================================================== -->
                            <!-- end card profile -->
                            <!-- ============================================================== -->
                        </div>
                        <!-- ============================================================== -->
                        <!-- end campaign data -->
                        <!-- ============================================================== -->
                    </div>
	</div>
</body>
<script src="${pageContext.request.contextPath}/resources/vendor/inputmask/js/jquery.inputmask.bundle.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/datepicker/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript">
	$(function() {
			
		$(".phone-inputmask").inputmask("999-9999-9999");
			
	});	
</script>
</html>
