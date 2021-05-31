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
<title> 원재료 상세 기록 </title>
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
    $(function(){
    	
    	$(".numberCheck").keyup(function(){
			 var checkVal = onlyNumberInsertFunc($(this).val());
			 $(this).val(checkVal);
			 
		 });
    	
    });
    	
    function onlyNumberInsertFunc(values){
		var regex=  /^\d+$/;
		
		if(!regex.test(values)){
			alert("숫자만 입력해야 합니다.");
			return 0;
			
		}else{
			return values;
			
		}
	}
    
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
<div class="dashboard-wrapper">
	<div class="container-fluid  dashboard-content">
	<div class="row">
		<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
			<div class="card">
				<h5 class="card-header">상품 입고</h5>
				<div class="card-body">
					<form class="needs-validation" id="updateCostInputDataForm" method="POST" action="">
						<input type="hidden" name="ciPk" value="${ciVO.ciPk }">
						<div class="mb-3">
							<label for="ciPrice">원가</label> 
							<input type="number" class="form-control numberCheck" id="ciPrice" name="ciPrice" placeholder="원가를 입력해주세요" value="${ciVO.ciPrice }">
						</div>
						<div class="row">
							<div class="col-md-5 mb-3">
								<label for="ciWeight"> 입고 무게( g단위로 입력 ) </label> 
								<input type="text" class="form-control numberCheck" id="ciWeight" name="ciWeight" placeholder="" value="${ciVO.ciWeight }" required="">
							</div>
							<div class="col-md-4 mb-3">
								<label for="ciOutputWeight"> 출고 무게 </label> 
								<input type="text" class="form-control numberCheck" id="ciOutputWeight" name="ciOutputWeight" placeholder="" value="${ciVO.ciOutputWeight }" required="" disabled="disabled">
							</div>
							<div class="col-md-3 mb-3">
								<label for="ciLevel">등급</label> 
								<input type="text" class="form-control" id="ciLevel" name="ciLevel" placeholder="" required="" value="${ciVO.ciLevel }">
							</div>
							<div class="col-md-3 mb-3">
								<label for="ciMarblingLevel">근내지방등급</label> 
								<input type="text" class="form-control" id="ciMarblingLevel" name="ciMarblingLevel" placeholder="" required="" value="${ciVO.ciMarblingLevel }">
							</div>
						</div>
						
						<div class="mb-3">
							<label for="ciAnimalProdTraceNum">이력번호</label> 
							<input type="text" class="form-control" id="ciAnimalProdTraceNum" name="ciAnimalProdTraceNum" placeholder="이력번호를 입력해주세요" required="" value="${ciVO.ciAnimalProdTraceNum }">
						</div>
						<div class="mb-3">
							<label for="ciItemsManufNum">품목제조번호</label> 
							<input type="text" class="form-control" id="ciItemsManufNum" name="ciItemsManufNum" placeholder="품목제조번호를 입력해주세요" required="" value="${ciVO.ciItemsManufNum }">
						</div>
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="ciAbattoir"> 도축장 </label> 
								<input type="text" class="form-control" id="ciAbattoir" name="ciAbattoir" placeholder="" value="${ciVO.ciAbattoir }" required="">
							</div>
							<div class="col-md-6 mb-3">
								<label for="ciCountryOfOrigin"> 원산지 </label> 
								<input type="text" class="form-control" id="ciCountryOfOrigin" name="ciCountryOfOrigin" placeholder="" value="${ciVO.ciCountryOfOrigin }" required="">
							</div>
						</div>
						<hr class="mb-4">
						<div class="form-group row text-right">
                        	<div class="col col-sm-10 col-lg-9 offset-sm-1 offset-lg-0">
                            	<button type="submit" class="btn btn-space btn-primary"> 수정하기 </button>
                                <button class="btn btn-space btn-danger"> 삭제하기 </button>
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

