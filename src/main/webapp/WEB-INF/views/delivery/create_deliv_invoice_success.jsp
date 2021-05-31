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
    <title> 송장 부여 결과 </title>
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
	<script type = "text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
	<script type = "text/javascript" src = "https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/libs/js/jquery-barcode.js"></script>
    
    
    <script type="text/javascript">
    
    	$(function(){
    		
    		window.onbeforeunload = function(e){
    	    	opener.location.reload();
        	}
    		
    	});
    </script>
    <style>
   
   	 @media print{@page {size: landscape}}
   	 
   	 h3{
   	 	-webkit-font-smoothing: antialiased;
   	 }
    </style>
</head>
<body>
	<div class="container-fluid  dashboard-content" style="padding:0" id="testId">
		<div class="row" style="display: none;">
			<canvas id="delivTest" width="1101" height="429"></canvas>
		</div>
		
		<div id="htmlTest" style="position: absolute; width:1090px; height:429px;" >
			
		</div>
		
		<div id="htmlTest2" style="position: absolute; width:1090px; height:429px; top:430px;" >
			
		</div>
	</div>
</body>
<script type="text/javascript">
	/* var canvas  = document.getElementById("delivTest");
	var context = canvas.getContext("2d");
	context.font = "bold 50px '맑은 고딕'";
	context.fillText("B8", 260, 50.4);
	
	var context = canvas.getContext("2d");
	context.font = "bold 20px '맑은 고딕'";
	context.fillText("부평물", 320, 47.4);
	
	
	var context = canvas.getContext("2d");
	context.font = "bold 28px '맑은 고딕'";
	context.fillText("40", 390, 38.4);
	
	var context = canvas.getContext("2d");
	context.font = "bold 22px '맑은 고딕'";
	context.fillText("인천계양", 427, 47.4);
	
	
	var context = canvas.getContext("2d");
	context.font = "bold 40px '맑은 고딕'";
	context.fillText("55", 530, 50.4);
	
	
	var context = canvas.getContext("2d");
	context.font = "bold 40px '맑은 고딕'";
	context.fillText("41", 600, 50.4);

	var arrCnpoNmBefore = document.createElement("h3");
	arrCnpoNmBefore.innerHTML="B8";
	arrCnpoNmBefore.style.fontSize=55+"px";	
	arrCnpoNmBefore.style.top=8.4+"px";
	arrCnpoNmBefore.style.left=260+"px";
	arrCnpoNmBefore = epostFontStyle(arrCnpoNmBefore);
	
	
	var arrCnpoNm = document.createElement("h3");
	arrCnpoNm.innerHTML="부평물";
	arrCnpoNm.style.fontSize=15+"px";	
	arrCnpoNm.style.top=40.4+"px";
	arrCnpoNm.style.left=330+"px";
	arrCnpoNm = epostFontStyle(arrCnpoNm);
	
	var arrCnpoNmAfter = document.createElement("h3");
	arrCnpoNmAfter.innerHTML="409";
	arrCnpoNmAfter.style.fontSize=40+"px";	
	arrCnpoNmAfter.style.top=16.4+"px";
	arrCnpoNmAfter.style.left=390+"px";
	arrCnpoNmAfter = epostFontStyle(arrCnpoNmAfter); */
	
	
	var arrCnpoNmBeforeHtml = ""; 
	//접수 우체국 및 인쇄일
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles deliv-po-nm-point'>접수국 : 인천계양</h3>"
	
	+"<h3 class='deliv-styles deliv-reserv-date'>2020-09-01 </h3>";
	
	
	//주문고객 정보
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles deliv-buyer-name'>주문인 : 갓기찬</h3>"
		+"<h3 class='deliv-styles deliv-ss-name'>주문처 : 스마트스토어 </h3>"
		+"<h3 class='deliv-styles deliv-order-number'>고유번호  : 스마-33608 </h3>";
		
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles deliv-box-weight'>중량 : 초소</h3>"
		+"<h3 class='deliv-style deliv-sending-price'>요금 : 계약요금</h3>";
		
	arrCnpoNmBeforeHtml+="<div class='deliv-styles deliv-zip-barcode barcodes'></div>";
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles deliv-message-title'>배송메세지 : </h3>";
	
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles deliv-message'> 3층 사무실에 놔주세요 </h3>";
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles arr-cnpo-before'>B8</h3>"
	+"<h3 class='deliv-styles arr-cnpo-nm'>부평물</h3>"
	+"<h3 class='deliv-styles arr-cnpo-nm-after'>409</h3>"
	+"<h3 class='deliv-styles deliv-po-nm'>인천계양</h3>"
	+"<h3 class='deliv-styles deliv-po-nm-before'>55</h3>"
	+"<h3 class='deliv-styles deliv-po-nm-after'>41</h3>";
	
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles deliv-sender-addr'> 인천광역시 계양구 효서로 388 (작전동) </h3>";
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles deliv-sender-zip-num'> 21125 </h3>";
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles deliv-sender'> 삼형제고기 </h3>";
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles deliv-sender-tel-num'> T : 0507-1312-1620 </h3>";
	
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles rec-addr'> 인천광역시 남동구 수현로136번길 25 (만수동, 한양빌라) 8동 102호 </h3>";
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles receiver'> 캡틴 갓기찬 </h3>";
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles rec-tel-num'> T : 010-9350-3632 </h3>";
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles rec-mob-num'> T : 010-1234-1234 </h3>";
	
	
	arrCnpoNmBeforeHtml+="<div class='deliv-styles deliv-invoice-num delivNum'></div>";
	
	
	
	
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles total-qty'>총 상품수량 : 3</h3>";
	arrCnpoNmBeforeHtml+="<h3 class='deliv-styles qty-title'>수량</h3>";
	
	
	arrCnpoNmBeforeHtml+="<h3 style='top:80px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>1</h3>";
	arrCnpoNmBeforeHtml+="<h3 style='top:80px; left:730px;  position:absolute; width: 300px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>LA양념갈비 1kg [단일상품]</h3>";
	
	arrCnpoNmBeforeHtml+="<h3 style='top:120px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>1</h3>";
	arrCnpoNmBeforeHtml+="<h3 style='top:120px; left:730px;  position:absolute; width: 300px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>LA양념갈비 1kg [단일상품]</h3>";
	
	arrCnpoNmBeforeHtml+="<h3 style='top:160px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>1</h3>";
	arrCnpoNmBeforeHtml+="<h3 style='top:160px; left:730px;  position:absolute; width: 310px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품]</h3>";
	
	arrCnpoNmBeforeHtml+="<h3 style='top:200px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>1</h3>";
	arrCnpoNmBeforeHtml+="<h3 style='top:200px; left:730px;  position:absolute; width: 310px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품]</h3>";
	
	arrCnpoNmBeforeHtml+="<h3 style='top:240px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>1</h3>";
	arrCnpoNmBeforeHtml+="<h3 style='top:240px; left:730px;  position:absolute; width: 310px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품]</h3>";
	
	arrCnpoNmBeforeHtml+="<h3 style='top:280px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>1</h3>";
	arrCnpoNmBeforeHtml+="<h3 style='top:280px; left:730px;  position:absolute; width: 310px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품]</h3>";
	
	arrCnpoNmBeforeHtml+="<h3 style='top:320px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>1</h3>";
	arrCnpoNmBeforeHtml+="<h3 style='top:320px; left:730px;  position:absolute; width: 310px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품]</h3>";
	
	arrCnpoNmBeforeHtml+="<h3 style='top:360px; left:700px; position:absolute; font-size:14px; color:black; font-weight: revert;'>1</h3>";
	arrCnpoNmBeforeHtml+="<h3 style='top:360px; left:730px;  position:absolute; width: 310px; font-size:14px; white-space: pre-line; color:black; font-weight: bold;'>LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품] LA양념갈비 1kg [단일상품]</h3>";
	
	
	/* document.getElementById("htmlTest").appendChild(arrCnpoNmBefore);
	document.getElementById("htmlTest").appendChild(arrCnpoNm);
	document.getElementById("htmlTest").appendChild(arrCnpoNmAfter);
	
	document.getElementById("htmlTest2").append(arrCnpoNmBefore);
	document.getElementById("htmlTest2").append(arrCnpoNm);
	document.getElementById("htmlTest2").append(arrCnpoNmAfter); */
	
	document.getElementById("htmlTest").innerHTML=arrCnpoNmBeforeHtml;
	document.getElementById("htmlTest2").innerHTML=arrCnpoNmBeforeHtml;
	
	
	$(".barcodes").barcode('21126', 'code128',{barWidth:2, barHeight:60});
	
	$(".delivNum").barcode('60963158186731', 'code128',{barWidth:2.7, barHeight:75});
	
	function epostFontStyle(elements){
		elements.style.position="absolute";
		elements.style.color="black";
		return elements;
		
		
	}
	
	 html2canvas($('#htmlTest')[0]).then(function(canvas) {
		    var doc = new jsPDF('p', 'mm'); //jspdf객체 생성
		    var imgData = canvas.toDataURL('image/png'); //캔버스를 이미지로 변환
		    doc.addImage(imgData, 'PNG', 0, 0); //이미지를 기반으로 pdf생성
		    doc.save('sample-file.pdf'); //pdf저장
		  });
</script>
 
</html>
