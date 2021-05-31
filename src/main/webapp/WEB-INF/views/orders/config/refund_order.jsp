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
<title>주문서 나누기</title>
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
	var regexp = /^[0-9]*$/

	$(function(){
		
		$("#orRefunds").keyup(function(){
			
			if( !regexp.test($(this).val()) ) {

				alert("숫자만 입력하세요");

				$(this).val("");
				$(this).focus();
			}
			
		});
		
		$("#orderRefundsForm").submit(function(){
			
			if(!confirm("해당 사항으로 환불 처리를 하시겠습니까?")){
				
				return false;
			}
			
			var orRefunds = $("#orRefunds").val();
			var orAmount = $("#orAmount").val();

			if(orRefunds <= 0){
				alert("환불 개수는 0이나 0보다 작을 수 없습니다");
				$(this).focus();
				return false;
			}else if(orRefunds > orAmount){
				alert("환불 개수가 기존의 수량보다 클 수 없습니다");
				$(this).focus();
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
			<!-- ============================================================== -->
			<!-- basic tabs  -->
			<!-- ============================================================== -->
			
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                	<div class="card">
                        	<div class="card-body">
                            	<form id="orderRefundsForm" action="<c:url value='/orders/refund_order.do'/>" method="post">
									<input type="hidden" name="orPk" value="${orVO.orPk}">
									<input type="hidden" id="orAmount" value="${orVO.orAmount }">
									<div class="tab-content" id="myTabContent">
										<div class="tab-pane fade show active" id="ratioDevide" role="tabpanel" aria-labelledby="home-tab">
											<h4 class="text-primary mb-1"> 구매자 : ${orVO.orBuyerName } / 수령자 : ${orVO.orReceiverName }</h4>
											<p class="text-primary mb-3"> 상품 </p>
											<span class="text-primary mb-3"> ${orVO.orProduct } ${orVO.orProductOption } ${orVO.orAmount } 개 </span>
											<c:if test="${!empty orVO.productMatchingVOList }">	                                        			
			                                	<c:forEach var="pmlist" items="${orVO.productMatchingVOList }">  

														<c:if test="${!empty pmlist.optionMatchingVOList }">																		
								                    	<c:forEach var="omlist" items="${pmlist.optionMatchingVOList }">
										                	<c:forEach var="proOplist" items="${omlist.productOptionList }">
											               		<c:if test="${!empty proOplist.optionName }">								                                        			
												            		<p style="font-size: 10px;">${proOplist.productName } [ ${proOplist.optionName } ] ${orVO.orAmount * omlist.omOptionAmount } 개</p>
											                	</c:if>
										                	</c:forEach>
	
								                    	</c:forEach>
													</c:if>
			                                    </c:forEach>
	                                        </c:if>
											
											
											<div class="input-group mb-3">
                                                <input type="text" class="form-control" value="기존 수량 ${orVO.orAmount } 개 => 환불 처리할 개수" readonly="readonly">
                                                <input type="number" class="form-control" id="orRefunds" name="orRefunds" value="${orVO.orRefunds }">
                                                <div class="input-group-append">
                                                    <button type="submit" class="btn btn-primary"> 환불처리 </button>
                                                </div>
                                            </div>
											
										</div>
									</div>
								</form>
                            </div>
                       </div>
                    </div>
			</div>
		</div>
	</div>
</body>
</html>
