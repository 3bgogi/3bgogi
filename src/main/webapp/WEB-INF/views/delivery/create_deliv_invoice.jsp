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
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
    <script type = "text/javascript" src = "http://code.jquery.com/jquery-latest.min.js"></script>
	<script type = "text/javascript" src = "https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
	<script type = "text/javascript" src = "https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/libs/js/jquery-barcode.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/deliv_style.css">
    <script type="text/javascript">
    
		$(function() {

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
	<c:set var="today" value="<%=new java.util.Date()%>"/>
	<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" var="toDay"/>
	
	<div class="container-fluid  dashboard-content" style="padding:0" id="testId">
		<c:if test="${!empty errorOr }">
			<button id="errorBtn" style="display: none;" type="button" data-toggle="modal" data-target="#delivErrorModal">errors</button>
			<input name="errorQty" type="hidden" value="${fn:length(errorOr) }">
		</c:if>
		<c:set var="topValue" value="0"/>
		<c:set var="totalDeliv" value="${fn:length(orList) }"/>
		<c:set var="delivCounting" value="1"/>
		<c:if test="${empty orList }">
			<button id="closeBtn" class="btn btn-success btn-block" style="font-size:30px;">송장이 없습니다 누르면 창이 닫힙니다</button>
		</c:if>
		
		<c:if test="${!empty orList }">
			<c:forEach var="orlist" items="${orList }">		
				<div id="html_deliv_test" style="position: absolute; width:1090px; height:429px; top:${topValue}px;" >
				
					<c:if test="${orlist.orRecType > 0 and orlist.orRecType <= 3 }">
						<h3 class='deliv-styles deliv-dont-deliv-msg-top' style="top:17px; left:700px; font-size:12px;"> 수령인 : ${orlist.orReceiverName }, </h3>
						<h3 class='deliv-styles deliv-dont-deliv-msg-top' style="top:35px; left:700px; font-size:12px;"> 수령시간 : ${orlist.orRecMemo }, 보관장소 :  ${orlist.orRecStoragePlace }</h3>
						<h1 class='deliv-styles deliv-dont-deliv-msg-top' style="top:170px; left:670px; font-size:90px;transform: rotate(-45deg); opacity: 0.4;"> 보 관 증 </h1>
						
						<c:if test="${orlist.orRecType == 1 }">
							<h3 class='deliv-styles deliv-dont-deliv-msg-top' style="top:20px; font-size:20px;">**** 퀵서비스 **** 퀵서비스 **** 퀵서비스 **** 퀵서비스 ****</h3>
							<h3 class='deliv-styles deliv-order-number' style="top: 130px; font-size:130px; opacity: 0.4;"> 퀵서비스 </h3>
							
						</c:if>
						<c:if test="${orlist.orRecType == 2 }">
							<h3 class='deliv-styles deliv-dont-deliv-msg-top' style="top:20px; font-size:20px;">**** 방문수령 **** 방문수령 **** 방문수령 **** 방문수령 ****</h3>
							<h3 class='deliv-styles deliv-order-number' style="top: 130px; font-size:130px; opacity: 0.4;"> 방문수령 </h3>
							
						</c:if>
						
						<c:if test="${orlist.orRecType == 3 }">
							<h3 class='deliv-styles deliv-dont-deliv-msg-top' style="top:20px; font-size:20px;">**** 제주익일 **** 제주익일 **** 제주익일 **** 제주익일 ****</h3>
							<h3 class='deliv-styles deliv-order-number' style="top: 130px; font-size:130px; opacity: 0.4;"> 제주익일 </h3>
							
						</c:if>

						<h3 class='deliv-styles deliv-reserv-date'>${toDay }</h3>
						
						<h3 class='deliv-styles deliv-buyer-name'>주문인 : ${orlist.orBuyerName }</h3>
						
						<h3 class='deliv-styles deliv-order-number' style="top: 90px; left:80px; font-size:20px;"> 수령 시간 </h3>
						
						<h3 class='deliv-styles deliv-box-weight'> ${orlist.orRecMemo } </h3>
						
						<div class='deliv-styles deliv-pickup-place' style="top:140px; left:80px; font-size:20px;"> 보관 장소</div>
						
						<div class='deliv-styles deliv-pickup-place' style="top:175px;">${orlist.orRecStoragePlace } </div>


						<h3 class='deliv-styles deliv-message-title'> 배송메세지 :</h3>

						<h3 class='deliv-styles deliv-message'> ${orlist.orDeliveryMessage } </h3>
						
					</c:if>
					
					
					<c:if test="${orlist.orRecType == 0 or orlist.orRecType == 3 }">
						<c:if test="${orlist.orRecType == 0 }">						
							<c:if test="${orlist.productOptionList[0].prodSorting == -2 or orlist.productOptionList[0].prodSorting == -3 or orlist.productOptionList[0].prodSorting == -4  }">
								<h1 class='deliv-styles deliv-dont-deliv-msg-top' style="top:170px; left:670px; font-size:80px;transform: rotate(-45deg); opacity: 0.4;">선물세트</h1>
							</c:if>	
						</c:if>
						
						<c:if test="${orlist.orRecType == 3 }">						
							<c:if test="${orlist.productOptionList[0].prodSorting == -2 or orlist.productOptionList[0].prodSorting == -3 or orlist.productOptionList[0].prodSorting == -4  }">
								<h1 class='deliv-styles deliv-dont-deliv-msg-top' style="top:80px; left:670px; font-size:80px; opacity: 0.4;">선물세트</h1>
								<h1 class='deliv-styles deliv-dont-deliv-msg-top' style="top:200px; left:670px; font-size:80px; opacity: 0.4;">제주익일</h1>
								
								
							</c:if>	
							<c:if test="${orlist.productOptionList[0].prodSorting == -2 and orlist.productOptionList[0].prodSorting == -3 and orlist.productOptionList[0].prodSorting == -4  }">
								<h1 class='deliv-styles deliv-dont-deliv-msg-top' style="top:200px; left:670px; font-size:80px; opacity: 0.4;">제주익일</h1>
								
								
							</c:if>
						</c:if>		
												
						<h3 class='deliv-styles deliv-po-nm-point'>접수국 : ${fn:replace(orlist.regiPoNm, '우체국', '') }</h3>
						<h3 class='deliv-styles deliv-reserv-date'>${toDay } </h3>
						
						<h3 class='deliv-styles deliv-buyer-name'>주문인 : ${orlist.orBuyerName }</h3>
						<h3 class='deliv-styles deliv-ss-name'>주문처 : ${orlist.ssName } </h3>
						<h3 class='deliv-styles deliv-order-number'>고유번호  : ${orlist.orSerialSpecialNumber } </h3>
		
						<h3 class='deliv-styles deliv-box-weight'>중량 : 초소</h3>
						<h3 class='deliv-style deliv-sending-price'>요금 : 계약요금</h3>
							
						<div class='deliv-styles deliv-zip-barcode barcodes' data-barcodes="${orlist.orShippingAddressNumber }"></div>
						
						<h3 class='deliv-styles deliv-message-title'>(${delivCounting }/${totalDeliv }) 배송메세지 :</h3>
						
						
						<h3 class='deliv-styles deliv-message'> ${orlist.orDeliveryMessage } </h3>
						
						
						<h3 class='deliv-styles arr-cnpo-before'>${fn:substring(orlist.delivAreaCd, 0, 2) }</h3>
						<h3 class='deliv-styles arr-cnpo-nm'>${orlist.arrCnpoNm }</h3>
						<h3 class='deliv-styles arr-cnpo-nm-after'>${fn:substring(orlist.delivAreaCd, 2, 5) }</h3>
						<h3 class='deliv-styles deliv-po-nm'>${orlist.delivPoNm }</h3>
						<h3 class='deliv-styles deliv-po-nm-before'>${fn:substring(orlist.delivAreaCd, 5, 7) }</h3>
						<h3 class='deliv-styles deliv-po-nm-after'>${fn:substring(orlist.delivAreaCd, 7, 9) }</h3>
					</c:if>
					
					<h3 class='deliv-styles deliv-sender-addr'>인천광역시 계양구 효서로 388 (작전동) 3층 삼형제고기</h3>
					<h3 class='deliv-styles deliv-sender-zip-num'> 21126 </h3>
					
					<h3 class='deliv-styles deliv-sender'
						<c:if test="${!empty orlist.orBuyerAnotherName and fn:length(orlist.orBuyerAnotherName) > 10 }">
							style="font-size : 13px;"
							
						</c:if>
						
					>  
						<c:if test="${empty orlist.orBuyerAnotherName }">
							삼형제고기
						</c:if>
						<c:if test="${!empty orlist.orBuyerAnotherName  }">
							${orlist.orBuyerAnotherName }
						</c:if>
					</h3>
					
					<h3 class='deliv-styles deliv-sender-tel-num'> T : 0507-1312-1620 </h3>
					
					
					<h3 class='deliv-styles rec-addr'> ${orlist.orShippingAddress } ${orlist.orShippingAddressDetail } </h3>
					
					<h3 class='deliv-styles receiver'> ${orlist.orReceiverName } </h3>
					
					<h3 class='deliv-styles rec-tel-num'> T : ${orlist.orReceiverContractNumber1 } </h3>
					<c:if test="${!empty orlist.orReceiverContractNumber2 }">
						<h3 class='deliv-styles rec-mob-num'> T : ${orlist.orReceiverContractNumber2 } </h3>				
					</c:if>
					
					
					<div class='deliv-styles deliv-invoice-num delivNum' data-barcodes="${orlist.regiNo }"></div>
					 
					<fmt:parseNumber var="divQty" integerOnly="true" value="${ ( fn:length(orlist.productOptionList) + ( fn:length(orlist.productOptionList)%1 ) %1) /8 }"/>
					<c:set var="divMod" value="${( fn:length(orlist.productOptionList) + ( fn:length(orlist.productOptionList)%1 ) %1) % 8 }"/>
					
					<c:set var="qty" value="0"/>
					<c:set var="prodTop" value="80"/>
					<c:if test="${divQty == 0 && divMod > 0}">
						<c:forEach var="i" begin="0" step="1" end="${fn:length(orlist.productOptionList) - 1 }">				
							<h3 class="deliv-styles product-qty" style='top:${prodTop}px;'>${orlist.productOptionList[i].anotherOptionQty }</h3>
							<h3 class="deliv-styles product-name" style='top:${prodTop-12}px; '>
								${orlist.productOptionList[i].productName } [ ${orlist.productOptionList[i].optionName } ]  <c:if test="${!empty orlist.productOptionList[i].optionMemo1 }">* 특별 요청 : ${orlist.productOptionList[i].optionMemo1 }</c:if>
							</h3>
							
							<c:set var="qty" value="${qty + products.anotherOptionQty  }"/>
							<c:set var="prodTop" value="${prodTop + 40 }"/>	
						</c:forEach>
					</c:if>
					<c:if test="${divQty == 1 && divMod == 0}">
						<c:forEach var="i" begin="0" step="1" end="${8 - 1  }">				
							<h3 class="deliv-styles product-qty" style='top:${prodTop}px;'>${orlist.productOptionList[i].anotherOptionQty }</h3>
							<h3 class="deliv-styles product-name" style='top:${prodTop-12}px;'>
								${orlist.productOptionList[i].productName } [ ${orlist.productOptionList[i].optionName } ] <c:if test="${!empty orlist.productOptionList[i].optionMemo1 }">* 특별 요청 : ${orlist.productOptionList[i].optionMemo1 }</c:if>
							</h3>
							
							<c:set var="qty" value="${qty + products.anotherOptionQty  }"/>
							<c:set var="prodTop" value="${prodTop + 40 }"/>	
						</c:forEach>
					</c:if>
					<c:if test="${(divQty > 1) || (divQty == 1 && divMod > 0)}">
						<c:forEach var="i" begin="0" step="1" end="${8 - 1 }">				
							<h3 class="deliv-styles product-qty" style='top:${prodTop}px;'>${orlist.productOptionList[i].anotherOptionQty }</h3>
							<h3 class="deliv-styles product-name" style='top:${prodTop-12}px;'>
								${orlist.productOptionList[i].productName } [ ${orlist.productOptionList[i].optionName } ]  <c:if test="${!empty orlist.productOptionList[i].optionMemo1 }">* 특별 요청 : ${orlist.productOptionList[i].optionMemo1 }</c:if>
							</h3>
							
							<c:set var="qty" value="${qty + products.anotherOptionQty  }"/>
							<c:set var="prodTop" value="${prodTop + 40 }"/>	
						</c:forEach>
					</c:if>
					
					
					
					<%-- <h3 class='deliv-styles total-qty'>총 상품수량 : ${fn:length(orlist.productOptionList) }</h3> --%>
					<h3 class='deliv-styles qty-title'>수량</h3>
				</div>
				
				<c:if test="${(divQty > 1) || (divQty == 1 && divMod > 0)}">
				
					<c:set var="topValue" value="${topValue + 432 }"/>
					<c:set var="divs" value="${fn:length(orlist.productOptionList) % 8 }"/>
					<c:forEach var="i" begin="0" step="1" end="${divQty - 1 }">
						 <div id="html_deliv_test" style="position: absolute; width:1090px; height:429px; top:${topValue}px;" >
						 	<h3 class='deliv-styles deliv-dont-deliv-msg-top'>**** 발송 금지**** 발송 금지**** 발송 금지**** 발송 금지**** 발송 금지**** 발송 금지**** 발송 금지**** 발송 금지****</h3>
						 	<h3 class='deliv-styles deliv-dont-deliv-msg-bottom'>**** 발송 금지**** 발송 금지**** 발송 금지**** 발송 금지**** 발송 금지**** 발송 금지**** 발송 금지**** 발송 금지****</h3>
						 	<h3 class='deliv-styles receiver'> ${orlist.orReceiverName } 의 나머지 상품 </h3>
						 	<c:if test="${orlist.productOptionList[0].prodSorting == -2 or orlist.productOptionList[0].prodSorting == -3 or orlist.productOptionList[0].prodSorting == -4  }">
								<h1 class='deliv-styles deliv-dont-deliv-msg-top' style="top:170px; left:670px; font-size:80px;transform: rotate(-45deg); opacity: 0.4;">선물세트</h1>
							</c:if>	
							
						 		<c:set var="prodTop" value="80"/>
						 		
						 		<c:if test="${(i + 1) == (divQty)}">
						 			<c:forEach var="j" begin="${(i + 1) *8 }" step="1" end="${(((i + 1) * 8) + divMod - 1) }">
										<h3 class="deliv-styles product-qty" style='top:${prodTop}px;'>${orlist.productOptionList[j].anotherOptionQty }</h3>
										<h3 class="deliv-styles product-name" style='top:${prodTop-12}px;'>
											${orlist.productOptionList[j].productName } [ ${orlist.productOptionList[j].optionName } ] <c:if test="${!empty orlist.productOptionList[i].optionMemo1 }">* 특별 요청 : ${orlist.productOptionList[i].optionMemo1 }</c:if>
											
										</h3>
										
										<c:set var="prodTop" value="${prodTop + 40 }"/>	
									</c:forEach>
								
						 		</c:if>
						 		<c:if test="${(i + 1) < (divQty)}">
						 			<c:forEach var="j" begin="${(i + 1)*8 }" step="1" end="${(((i + 1) * 8) + 8 ) - 1 }">
										<h3 class="deliv-styles product-qty" style='top:${prodTop}px;'>${orlist.productOptionList[j].anotherOptionQty }</h3>
										<h3 class="deliv-styles product-name" style='top:${prodTop-12}px;'>
											${orlist.productOptionList[j].productName } [ ${orlist.productOptionList[j].optionName } ] <c:if test="${!empty orlist.productOptionList[i].optionMemo1 }">* 특별 요청 : ${orlist.productOptionList[i].optionMemo1 }</c:if>
										</h3>
										<c:set var="prodTop" value="${prodTop + 40 }"/>	
									</c:forEach>
						 		</c:if>
								
								
							<h3 class='deliv-styles qty-title'>수량</h3>
							
						 </div>
						 <c:set var="topValue" value="${topValue + 432 }"/>
						 
					</c:forEach>
					
				</c:if>
				
				<c:set var="topValue" value="${topValue + 432 }"/>
				<c:set var="delivCounting" value="${delivCounting + 1}"/>
			</c:forEach>
		
		</c:if>
		
	</div>
	
	<div class="modal fade" id="delivErrorModal" tabindex="-1" role="dialog" aria-labelledby="delivErrorModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document" style="max-width: 80%; width: 80%; ">
			<div class="modal-content">
				<div class="modal-header">
					<a href="#" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</a>
				</div>
				<div class="modal-body">
					 <div class="row">
                    	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="card">
                                <h5 class="card-header"> 송장 생성 에러 목록</h5>
                                <div class="card-body">
                                    <table class="table table-hover">
                                    	<thead>
                                    		<tr>
												<th>주문자</th>
												<th>에러이유</th>
                                    		</tr>
                                    	</thead>
                                        <tbody>
                                        	<c:forEach var="errors" items="${errorOr }">
                                        		<c:if test="${empty errors.message }">
                                        			<tr>
	                                        			<td>서버오류</td>
	                                        			<td>우체국 통신 에러 주문정보가 존재합니다</td>
	                                        		</tr>
                                        		</c:if>
                                        		<c:if test="${!empty errors.message }">                                        		
	                                        		<tr>
	                                        			<td>${errors.orBuyerName }</td>
	                                        			<td>${errors.message }</td>
	                                        		</tr>			
                                        		</c:if>
											</c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		
		$(".deliv-zip-barcode").each(function(idx, items){
			$(this).barcode(""+ $(this).data("barcodes") , 'code128', {barWidth:2, barHeight:60});
			
		});
		
		
		$(".deliv-invoice-num").each(function(idx, items){
			$(this).barcode(""+ $(this).data("barcodes") , 'code128', {barWidth:2, barHeight:75});
			
		});
		
		if($("input[name=errorQty]").val() > 0){
			$("#errorBtn").click();
			
		}
		
		$("#closeBtn").click(function(){
			
			self.close();
		});
		function epostFontStyle(elements){
			elements.style.position="absolute";
			elements.style.color="black";
			return elements;
			
			
		}
	});
</script>
 
</html>
