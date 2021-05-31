<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style type="text/css">
.blinking{
	-webkit-animation:blink 1s ease-in-out infinite alternate;
    -moz-animation:blink 1s ease-in-out infinite alternate;
    animation:blink 1s ease-in-out infinite alternate;
}
@-webkit-keyframes blink{
    0% {opacity:0.4;}
    100% {opacity:1;}
}
@-moz-keyframes blink{
    0% {opacity:0.4;}
    100% {opacity:1;}
}
@keyframes blink{
    0% {opacity:0.4;}
    100% {opacity:1;}
}

</style>
<div class="row">
	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
		<div class="card" style="background-color: #efeff6;">
			<div class="card-body">
				<h4> 주문서 진행 단계 </h4>
				<a href="#" class="btn  
					<c:if test="${order_process < 1 }">
						
					</c:if>
					<c:if test="${order_process >= 1 }">
						btn-success
					</c:if>
					<c:if test="${order_process == 1 }">
						blinking
					</c:if>
				"> 주문서 입력</a> 
				<a href="#" class="btn
					<c:if test="${order_process < 2 }">
						
					</c:if>
					<c:if test="${order_process >= 2 }">
						
						btn-success
					</c:if>
					<c:if test="${order_process == 2 }">
						blinking
					</c:if>
				"> 상품매칭 </a> 
				<a href="#" class="btn
					<c:if test="${order_process < 3 }">
						
					</c:if>
					<c:if test="${order_process >= 3 }">
						
						btn-success
					</c:if>
					<c:if test="${order_process == 3 }">
						blinking
					</c:if>
				"> 옵션 매칭 </a>
				<a href="#" class="btn
					<c:if test="${order_process < 4 }">
						
					</c:if>
					<c:if test="${order_process >= 4 }">
						btn-success
					</c:if>
					<c:if test="${order_process == 4 }">
						blinking
					</c:if>
				"> 재고 할당 </a> 
				<a href="#" class="btn
					<c:if test="${order_process < 5 }">

					</c:if>
					<c:if test="${order_process >= 5 }">
						btn-success
					</c:if>
					<c:if test="${order_process == 5 }">
						blinking
					</c:if>
				"> 취소 주문  </a> 
			</div>
		</div>
	</div>
</div>