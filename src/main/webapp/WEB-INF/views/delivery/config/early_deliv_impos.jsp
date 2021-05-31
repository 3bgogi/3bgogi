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
<title>배송불가지역키워드</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script type="text/javascript">
    	$(function(){
    		
    		//키워드 추가
    		$("#insertDelivImposForm").submit(function(){
    			diAddress = $("#diAddress").val();
    			
    			if(diAddress == ''){
    				alert("키워드를 입력해주세요");
    				$("#diAddress").focus();
    				
    				event.preventDefault();
    				return false;
    				
    			}
    			
    		});
    		
    		
    		//키워드 삭제
    		$("#keywordDelBtn").click(function(){
    			delivSize = $("input[name=diPk]:checked").length;
    			
    			if(delivSize > 1){
    				alert("한 번에 하나씩만 삭제 가능합니다");
    				
    			}else{    				
    				diPk = $("input[name=diPk]:checked").val();
    				edaFk = $("input[name=edaFk]").val();
	    			location.href='/delivery/config/early_deliv/impos_del.do?diPk='+diPk+'&edaFk='+edaFk;
	    			
	    			
    			}
    			
    			
    			
    		});
    		
    	});
    	
</script>
<style>
html, body {
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
	<div class="container-fluid  dashboard-content">
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="card">
					<h5 class="card-header">배송불가지역 키워드 추가하기</h5>
					<div class="card-body">
						<form id="insertDelivImposForm" class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" action="<c:url value='/delivery/config/early_deliv/impos.do'/>" method="POST">
							<input name="edaFk" value="${eda.edaPk }" type="hidden">
							<div class="form-group">
								<label class="col-form-label"> 배송불가지역 키워드 </label>
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" id="diAddress" name="diAddress" placeholder="키워드를 입력해주세요">
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-primary"> 추가 </button>
                                    </div>
                                </div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<hr>
			
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="card">
					<h5 class="card-header">입력된 배송불가지역 키워드</h5>
					<div class="card-body">
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mb-2">
							<button class="btn btn-danger btn-xs mb-2" type="button" id="keywordDelBtn"> 키워드 삭제 </button>
						</div>
						<table class="table table-hover">
							<thead>
								<tr>
									<th scope="col"></th>
									<th scope="col">키워드</th>
									<th scope="col">비고</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty edaList }">
									<c:forEach var="edalist" items="${edaList }">
										<c:forEach var="delivlist" items="${edalist.diList }">
											<tr>
												<th scope="row">
													<label class="custom-control custom-checkbox be-select-all">
														<input class="custom-control-input chk_all" type="checkbox" name="diPk" value="${delivlist.diPk }">
														<span class="custom-control-label"></span>
													</label>
												</th>
												<td> ${delivlist.diAddress } </td>
												<td> - </td>
											</tr>											
										</c:forEach>
									</c:forEach>
								</c:if>
								<c:if test="${empty edaList }">
									<tr style="text-align: center;">
										<td colspan="3"> 등록된 키워드가 존재하지 않습니다</td>
									</tr>
								</c:if>
								
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
