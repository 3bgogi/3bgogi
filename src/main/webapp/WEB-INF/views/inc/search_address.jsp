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
    <title> 주소 찾기 </title>
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/datepicker/jquery.datetimepicker.css" />
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script type="text/javascript">
    
    	$(function(){
    		
    		$("#searchAddressBtn").click(function(){
    			
	    		new daum.Postcode({
	    			
			        oncomplete: function(result) {
			        	
			        	$.ajax({
			        		type: 'GET',
			        		url:"<c:url value='/orders/search/search_address.do'/>",
			        		data:{
			        			"address":result.address
			        		},success:function(data){
						    	console.log(data);
						    	
						    	if(data.documents.length != 0) {
						    		
						    		
						    		var orAddressXmap = data.documents[0].address.x;
						        	var orAddressYmap = data.documents[0].address.y;
						        	var title = data.documents[0].address_name;
						        		
						        		$("#mapResultDiv").show();
						        		$("#mapResultHead").html("<p class='text-primary'>"+title+"</p>");
						        		
						        		createMap(orAddressXmap, orAddressYmap);
							        	
							        	$("#orShippingAddressNumber").val(result.zonecode);
							        	$("#orShippingAddress").val(result.address);
							        	
							        	var addrDetail = "";
							        	
							        	if(result.bname != "" && result.buildingName != ""){
							        		addrDetail="("+result.bname+", "+result.buildingName+")";
							        		
							        	}else if(result.bname != ""){
							        		addrDetail="("+result.bname+")";
							        		
							        	}else if(result.buildingName != ""){
							        		addrDetail="("+result.buildingName+")";
							        		
							        	}
							        	
							        	$("#orShippingAddressDetail").val(addrDetail);
							        	
						        
						    	}else{
						    		
						    		$("#mapResultHead").html("<p class='text-primary'>"+"지도 공개가 불가능한 지역"+"</p>");
						    		
									
									$("#mapResultDiv").show();
									
									
							       	$("#orShippingAddressNumber").val(result.zonecode);
							       	$("#orShippingAddress").val(result.address);
							       	
							       	var addrDetail = "";
							       	
							       	if(result.bname != "" && result.buildingName != ""){
							       		addrDetail="("+result.bname+", "+result.buildingName+")";
							       		
							       	}else if(result.bname != ""){
							       		addrDetail="("+result.bname+")";
							       		
							       	}else if(result.buildingName != ""){
							       		addrDetail="("+result.buildingName+")";
							       		
							       	}
							       	
							       	$("#orShippingAddressDetail").val(addrDetail);
						        	
						    	}
			        			
			        		}
			        		
			        	});
			        	
			        	
			        }
			    }).open();
	    		
    		});
    		
    		$("#selectedAddressBtn").click(function(){
    			
    			if(confirm("해당 주소로 입력하시겠습니까?")){
    				opener.document.getElementById("orShippingAddressNumber").value= $("#orShippingAddressNumber").val();
    				opener.document.getElementById("orShippingAddress").value= $("#orShippingAddress").val();
    				opener.document.getElementById("orShippingAddressDetail").value= $("#orShippingAddressDetail").val();
    				self.close();
    			}
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
	            <div class="card-body" id="">
	            	<div class="row">
		                <div class="col-md-12">		
		                	<button type="button" class="btn btn-primary btn-block" id="searchAddressBtn"> 주소 검색하기 </button>							
						</div>
	                </div>
	            </div>
        	</div>
        	<div class="card" id="mapResultDiv"style="display: none;">        	
	            <div class="card-header" style="text-align: center;" id="mapResultHead">
	                
	            </div>
	            <div class="card-body" id="">
	            	<div class="row">
		                <div class="col-md-12 mb-4" >					
							<div id="map" style="height: 400px;"></div>
							<input type="hidden" id="orShippingAddressNumber">
						</div>
						<div class="col-md-12 mb-4">		
							<label for="orShippingAddress"> 주소 </label> 
							<input type="text" class="form-control form-control-sm" id="orShippingAddress" placeholder="" disabled="disabled">				
						</div>
						<div class="col-md-12 mb-4">		
		                	<label for="orShippingAddress"> 상세주소 </label> 
							<input type="text" class="form-control form-control-sm" id="orShippingAddressDetail" placeholder="">					
						</div>
						<div class="col-md-12">		
		                	<button type="button" class="btn btn-primary btn-block" id="selectedAddressBtn"> 해당 주소로 입력 </button>							
						</div>
	                </div>
	            </div>
        	</div>
        </div>
</body>

<script src="${pageContext.request.contextPath}/resources/vendor/datepicker/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9c0487d898e60303f4b7ead687f377ab"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9c0487d898e60303f4b7ead687f377ab&libraries=drawing"></script>

<script type="text/javascript">
		function createMap(x, y){
			
			
			var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
        	var options = { //지도를 생성할 때 필요한 기본 옵션
        		center: new kakao.maps.LatLng(y, x), //지도의 중심좌표.
        		level: 3 //지도의 레벨(확대, 축소 정도)
        	};

        	var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
        	
        	var marker = new kakao.maps.Marker({
        	    map: map,
        	    position: new kakao.maps.LatLng(y, x)
        	});
		}
		
	</script>
</html>
