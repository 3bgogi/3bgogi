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
<title>상품 검색</title>
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
<script
	src="${pageContext.request.contextPath}/resources/libs/js/common_util.js"></script>
<script type="text/javascript">
    
    	
    </script>
<style>
html, body {
	padding: 10px;
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
	<div class="card" style="width: 100%;">
		<div class="card-header">
			<h4 class="d-flex justify-content-between align-items-center mb-0">
				<span class="text-muted"> 상품 검색 </span>
			</h4>
		</div>
		<div class="card-body">
			<form id="productSearchForm">
				<div class="input-group mb-2">
					<select class="form-control" name="cfFk" style="height: 41px;">
						<c:if test="${empty classList }">
							<option>등록된 상품 분류값이 없습니다</option>
						</c:if>
						<c:if test="${!empty classList }">
							<option value="0">전체 선택</option>
							<c:forEach var="classlist" items="${classList }">
								<option value="${classlist.cfPk }">
									${classlist.cfCodeType }</option>
							</c:forEach>
						</c:if>
					</select> 
					<input type="text" class="form-control form-control-sm"
						name="productName" placeholder="상품명을 입력해주세요">
					<div class="input-group-append">
						<button type="submit" class="btn btn-success">검색</button>
					</div>
				</div>
			</form>
			<ul class="list-group mb-1" id="productList">

			</ul>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		
		$("#productSearchForm").submit(function(){

			var cfFk = $("select[name=cfFk]").val();
			var productName = $("input[name=productName]").val();
			
			$.ajax({
			    type       : 'GET',
			    url        : '<c:url value="/common/product_search.do"/>',
			    data       : {
			    	"cfFk":cfFk,
			    	"productName":productName
			    },
			    success    : function(data){
			    	writeProductList(data);
			    }
			});
			
			
			event.preventDefault();
			return false;
		});
		
		$(document).on("click",".selectProductBtn", function(){
			
			var productPk = $(this).data("product-pk");
			var productName = $(this).data("product-name");
			var optionPk = $(this).data("option-pk");
			var optionName = $(this).data("option-name");

			window.opener.document.getElementById("productPk").value=productPk;
			window.opener.document.getElementById("productName").value=productName;
			window.opener.document.getElementById("optionPk").value=optionPk;
			window.opener.document.getElementById("optionName").value=optionName;
			
			self.close();
		});
		
	});
	
	function showOption(className){
		var openStat = $(this).data("data-open-stat");
		
		if(openStat == 0){
			$("."+className).show();	
			$(this).data("data-open-stat", "1");
		}else{
			$("."+className).hide();
			$(this).data("data-open-stat", "0");
		}
		
		
	}
	
	
	function writeProductList(productList){
		
		var productListHTML = "";
		var words = "'";
		var optionWord = "optionList-";
		var counting = 1;
		
		$.each(productList, function(){
			var productPk = this.productPk;
			var productName = this.productName;
			
			productListHTML+='<ul class="list-group mb-1" onclick="showOption('+words+optionWord+counting+words+')" data-open-stat="0">'
	        	+'<li class="list-group-item d-flex justify-content-between">'
	            	+'<div>'
	                	+'<h6 class="my-0">'+this.productName+'</h6>'
	                   		+'<small class="text-muted">';
	                   	
	                   	if(this.productFlag == true){
	                   		productListHTML+='<button class="btn btn-rounded btn-outline-success btn-xs" type="button" style="margin-top: 6px;"> 사용중 </button>';
	                   	}else{
	                   		productListHTML+='<button class="btn btn-rounded btn-outline-danger btn-xs" type="button" style="margin-top: 6px;"> 미사용 </button>';
	                   	}
	                   	
	                   	productListHTML+='</small>'
	                 +'</div>'
	                 +'<span class="text-muted">'+this.productRegdate+'</span>'
	            +'</li>'
	        +'</ul>';
	        
	        $.each(this.optionVOList, function(){
	        	
	        	productListHTML+='<ul class="list-group mb-1 '+optionWord+counting+'" style="display: none;">'
		        	+'<li class="list-group-item d-flex justify-content-between" style="padding: 8px 10px;">'
		            	+'<div>'
		                	+'<h6 class="my-0">'+this.optionName+' ( 옵션 재고 ';
		                	
		                	if(this.optionCostFlag == true){
		                		productListHTML+='사용 ( 재고 : '+comma(this.optionStock)+' 개 )';
		                	}else{
		                		productListHTML+='사용치 않음';
		                	}
		                	productListHTML+=')</h6>'
		                 +'</div>'
		                 +'<span class="text-muted">'
		                 	+'<button class="btn btn-success btn-xs selectProductBtn" type="button" data-product-pk="'+productPk+'" data-product-name="'+productName+'" data-option-pk="'+this.optionPk+'" data-option-name="'+this.optionName+'"> 선택 </button>'
		                 +'</span>'
		            +'</li>'
		        +'</ul>';
	        });
	        
	        counting++;
		});
		
		$("#productList").html(productListHTML);
	}
</script>

</html>
