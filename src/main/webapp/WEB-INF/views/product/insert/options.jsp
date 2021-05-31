<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="kr">
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>옵션</title>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/multi-select/css/multi-select.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap-select/css/bootstrap-select.css">

	
<script
	src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-select/js/bootstrap-select.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/slimscroll/jquery.slimscroll.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/multi-select/js/jquery.multi-select.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/js/common_util.js"></script>

<script type="text/javascript">

    	$(function(){
    		var optionCostsMatchingCount = 1;
    		
    		$("#anotherOptionPk").change(function(){
    			if($(this).val()==0){
    				$("#anotherOptionQtyDiv").hide();
    				
    			}else{
    				$("#anotherOptionQtyDiv").show();
    				
    			}
    		});
    		
    		$("input[name=optionStockFlag]").change(function(){
    			if($(this).val()==1){
    				$(".optionStockDiv").show();
    				$(".optionStockMaxAlarmDiv").show();
    			}else{
    				$(".optionStockDiv").hide();
    				$(".optionStockMaxAlarmDiv").hide();
    			}
    		});
    		
    		$("#optionSubmit").submit(function(){
    			var optionName = $("input[name=optionName]").val();
    			var optionPrice = $("input[name=optionPrice]").val();
    			var optionBarcodeNumber1 = $("input[name=optionBarcodeNumber1]").val();
    			
    			
    		});
    		
    		$("#insertOptionForm").submit(function(){
    			
    			$("input[name$=costUniqueNum]").each(function(){
    				$(this).val($(this).prev().children("option:selected").data("unique-num"));
    				alert($(this).prev().children("option:selected").data("unique-num"));
    			});
    		});
    		
    		//원가 연결 창 추가하기 누를 시 원가 연결 옵션 추가 이벤트
			$("#addCostsMatchingDivButton").click(function(){
				
				$.ajax({
	    			url:"<c:url value='/options/insert/costsVoListAjax.do'/>",
	    			type:"get",
	    			success:function(data){
	    				
	    				var costsConnectData = "";
	    				
	    				costsConnectData+='<div class="form-group row costsDiv">'
					                        +'<label class="col-12 col-sm-3 col-form-label text-sm-right"> 원재료 연결 선택 </label>'
					                        +'<div class="col-12 col-sm-8 col-lg-6 row">'
					                            +'<div class="col-md-5 mb-3">'
					                           	 	+'<label for="country">옵션 명</label>'
					                            	+'<select class="form-control" id="optionMatching" name="optionCostsMatchingVOList['+optionCostsMatchingCount+'].costName">';
	    				
	    				$.each(data, function(index, items){
	    					                                          	
	    					costsConnectData+='<option data-unique-num="'+items.costUniqueNum+'" value="'+items.costName+'">'+items.costName+' -'+comma(items.totalPrice)+' 원</option>';                                   	
                        
	    				});
	    				
	    				costsConnectData+='</select>'
	    								+'<input type="hidden" name="optionCostsMatchingVOList['+optionCostsMatchingCount+'].costUniqueNum">'
				                        +'</div>'
				                        +'<div class="col-md-2 mb-2 devideDiv">'
				                             +'<label for="zip"> 타입 </label>'
				                             +'<select class="form-control ocmGramRecalFlag" name="optionCostsMatchingVOList['+optionCostsMatchingCount+'].ocmGramRecalFlag">'
				                             	+'<option value="0"> 원본 그대로 </option>'
				                             	+'<option value="1"> g으로 나누기 </option>'
				                             	+'<option value="2"> 나누기 </option>'
				                             +'</select>'
				                         +'</div>'
				                         +'<div class="col-md-2 mb-1 devideNumDiv" style="display: none;">'
				                             +'<label for="zip">누눌 수</label>'
				                             +'<input type="text" value="1" class="form-control" name="optionCostsMatchingVOList['+optionCostsMatchingCount+'].ocmProductionDivide">'
				                         +'</div>'
				                         +'<div class="col-md-2 mb-1 devideEachDiv">'
				                             +'<label for="zip">개수</label>'
				                             +'<input type="text" value="1" class="form-control" name="optionCostsMatchingVOList['+optionCostsMatchingCount+'].ocmEach">'
				                         +'</div>'
				                         +'<div class="col-md-1 mb-1 deleteConnenctDiv">'
				                             +'<label for="zip"></label>'
				                             +'<button type="button" class="btn btn-space btn-warning" name="deleteConnenctButton"> 원재료삭제 </button>'
				                         +'</div>'
				                    +'</div>'
				                +'</div>';
				                
	    			$('.costsConnectDiv').append(costsConnectData);
	    			
	    			optionCostsMatchingCount++;
	    			
	    			}//success
	    			
	    		});//ajax
	    		
			});//addCostsMatchingDivButton click
			
			$(document).on("change", "select[name$=ocmGramRecalFlag]", function(){

				if( $(this).val()==1 || $(this).val()==2 ){
					
					$(this).parent().parent().find('.devideNumDiv').show();	
					
				}else{
					
					$(this).parent().parent().find('.devideNumDiv').hide();
				}
				
			}); //ocmGramRecalFlag change 이벤트
			
			$(document).on("click", "button[name=deleteConnenctButton]", function(){

				$(this).parent().parent().parent().remove();
				
			}); 
			
			$("input[name=optionCostFlag]").change(function(){
				if( $(this).val()==1){
					$(".optionCostDiv").show();
					
				}else{
					$(".optionCostDiv").hide();
				}
			});
			
    	});
    </script>
<style type="text/css">
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
                        <!-- valifation types -->
                        <!-- ============================================================== -->
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header">${productVO.productName } 의 옵션 상세 입력 </h5>
                                <div class="card-body">
                                    <form id="insertOptionForm" method="POST" action="<c:url value='/options/insert/option.do'/>">
                                    	<input type="hidden" name="productFk" value="${productVO.productPk }">
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 명</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionName" placeholder="상품 명을 입력해주세요" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 판매가 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionPrice" placeholder="판매가를 입력해주세요." class="form-control" value="0">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 자체 원가 사용 여부 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionCostFlag" value="1" class="custom-control-input"><span class="custom-control-label">사용</span>
	                                            </label>
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionCostFlag" checked="checked" value="0" class="custom-control-input"><span class="custom-control-label">사용하지 않음</span>
	                                            </label>
                                            </div>
                                        </div>
                                        <div class="form-group row optionCostDiv"style="display: none;">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 자체 원가 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionCost" placeholder="원가를 입력해주세요." class="form-control" value="0">
                                            </div>
                                        </div>
                                        <div class="form-group row optionCostDiv"style="display: none;">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 공급 원가  </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionSupplyCost" placeholder="원가를 공급 원가(입력 하지 않아도 됨)" class="form-control" value="0">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                        	<label class="col-12 col-sm-3 col-form-label text-sm-right"> 다른 상품 재고 차감 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <select class="form-control" id="anotherOptionPk" name="anotherOptionPk" data-live-search="true" data-size="8">
                                                	<option value="0"> 상품 선택 </option>
	                                                <c:if test="${!empty productList }">
	                                                	<c:forEach var="productlist" items="${productList }">
	                                                		<option value="${productlist.optionPk }">${productlist.productName } ${productlist.optionName }</option>
	                                                	</c:forEach>
	                                                </c:if>                                            	
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row" id="anotherOptionQtyDiv" style="display: none;">
                                        	<label class="col-12 col-sm-3 col-form-label text-sm-right"> 재고 차감 개수 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="anotherOptionQty" placeholder="다른 상품 재고 차감 개수 입력" class="form-control" value="0">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 사용 여부 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionFlag" checked="checked" value="1" class="custom-control-input"><span class="custom-control-label">사용</span>
	                                            </label>
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionFlag" value="0" class="custom-control-input"><span class="custom-control-label">사용하지 않음</span>
	                                            </label>
                                            </div>
                                        </div>
                                         <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 주문서 위치 선택</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <select class="form-control" id="eoFk" name="eoFk">
	                                                <c:forEach var="exceloderseqVolist" items="${exceloderseqVoList }">
	                                                	<option value="${exceloderseqVolist.eosPk }">${exceloderseqVolist.eosLocation }</option>
	                                                </c:forEach>                                               	
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 합포 선택</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <select class="form-control" id="pccFk" name="pccFk">
	                                                <c:forEach var="packingcombinecodeVolist" items="${packingcombinecodeVoList }">
	                                                	<option value="${packingcombinecodeVolist.pccPk }">${packingcombinecodeVolist.pccType }</option>
	                                                </c:forEach>                                               	
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 순서 선택</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <select class="form-control" id="optionSeq" name="optionSeq">
                                                	<c:forEach var="numbering" begin="1" end="12" step="1">
                                                		<option value="${numbering }">${numbering } 순위 </option>
                                                	</c:forEach>                                            	
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 바코드 1</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionBarcodeNumber1" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 바코드 2</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionBarcodeNumber2" class="form-control">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 면세 여부 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionTaxFlag" checked="checked" value="1" class="custom-control-input"><span class="custom-control-label">면세</span>
	                                            </label>
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionTaxFlag" value="0" class="custom-control-input"><span class="custom-control-label">과세</span>
	                                            </label>
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 재고 체크 여부 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionStockFlag" checked="checked" value="1" class="custom-control-input"><span class="custom-control-label">사용</span>
	                                            </label>
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionStockFlag" value="0" class="custom-control-input"><span class="custom-control-label">사용하지 않음</span>
	                                            </label>
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row optionStockMaxAlarmDiv">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 재고 알람 개수 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionStockMaxAlarm" value="0" class="form-control">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row optionStockDiv">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 재고 개수 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionStock" value="0" class="form-control">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 엑셀 컬러 여부 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionExcelColorCheck"  value="1" class="custom-control-input"><span class="custom-control-label">사용</span>
	                                            </label>
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionExcelColorCheck" checked="checked" value="0" class="custom-control-input"><span class="custom-control-label">사용하지 않음</span>
	                                            </label>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 메모 1</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionMemo1" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 메모 2</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionMemo2" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 메모 3</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionMemo3" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 메모 4</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionMemo4" class="form-control">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                        	<div class="col-xl-3 col-lg-6 col-md-6 col-sm-12 col-12">
				                                <!-- .card -->
				                                <div class="card card-figure">
				                                    <!-- .card-figure -->
				                                    <figure class="figure">
				                                        <!-- .figure-img -->
				                                        <div class="figure-img">
				                                            <img class="img-fluid" src="${pageContext.request.contextPath}/resources/images/card-img.jpg" alt="Card image cap">
				                                            <div class="figure-action">
				                                                <a href="#" class="btn btn-block btn-sm btn-primary"> 등록되지 않음</a>
				                                            </div>
				                                        </div>
				                                        <!-- /.figure-img -->
				                                        <!-- .figure-caption -->
				                                        <figcaption class="figure-caption">
				                                            <p class="text-muted mb-0"> 옵션 이미지 1 </p>
				                                        </figcaption>
				                                        <!-- /.figure-caption -->
				                                    </figure>
				                                    <!-- /.card-figure -->
				                                </div>
				                                <!-- /.card -->
				                            </div>
				                            <div class="col-xl-3 col-lg-6 col-md-6 col-sm-12 col-12">
				                                <!-- .card -->
				                                <div class="card card-figure">
				                                    <!-- .card-figure -->
				                                    <figure class="figure">
				                                        <!-- .figure-img -->
				                                        <div class="figure-img">
				                                            <img class="img-fluid" src="${pageContext.request.contextPath}/resources/images/card-img.jpg" alt="Card image cap">
				                                            <div class="figure-action">
				                                                <a href="#" class="btn btn-block btn-sm btn-primary"> 등록되지 않음</a>
				                                            </div>
				                                        </div>
				                                        <!-- /.figure-img -->
				                                        <!-- .figure-caption -->
				                                        <figcaption class="figure-caption">
				                                            <p class="text-muted mb-0"> 옵션 이미지 2</p>
				                                        </figcaption>
				                                        <!-- /.figure-caption -->
				                                    </figure>
				                                    <!-- /.card-figure -->
				                                </div>
				                                <!-- /.card -->
				                            </div>
				                        </div>   
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 비고 사항 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <textarea name="optionRemark" style="resize: none;" rows="8" class="form-control"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"></label>
                                            <div class="col-12 col-sm-8 col-lg-6" style="text-align: center;">
                                                <button type="button" class="btn btn-space btn-secondary" id="addCostsMatchingDivButton"> 원재료 연결 창 추가하기 </button>
                                            </div>
                                        </div>
                                        <div class="costsConnectDiv">                                        
	                                        <div class="form-group row costsDiv">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 원재료 연결 선택 </label>
	                                            <div class="col-12 col-sm-8 col-lg-6 row">
		                                            <div class="col-md-5 mb-3">
		                                           	 	<label for="country">옵션 명</label>
		                                            	<select class="form-control" id="optionMatching" name="optionCostsMatchingVOList[0].costName">
		                                                	<c:forEach var="costsVolist" items="${costsVoList }">                                                	
				                                                <option data-unique-num="${costsVolist.costUniqueNum }" value="${costsVolist.costName }">${costsVolist.costName } - <fmt:formatNumber value="${costsVolist.totalPrice }" pattern="#,###"></fmt:formatNumber> 원</option>                                       	
		                                                	</c:forEach>
		                                                </select>
		                                                <input type="hidden" name="optionCostsMatchingVOList[0].costUniqueNum">
		                                            </div>
		                                            <div class="col-md-2 mb-2 devideDiv">
			                                             <label for="zip"> 타입 </label>
			                                             <select class="form-control ocmGramRecalFlag" name="optionCostsMatchingVOList[0].ocmGramRecalFlag">
			                                             	<option value="0"> 원본 그대로 </option>
			                                             	<option value="1"> g으로 나누기 </option>
			                                             </select>
		                                             </div>
		                                             <div class="col-md-2 mb-1 devideNumDiv" style="display: none;">
			                                             <label for="zip">누눌 수</label>
			                                             <input type="text" value="1" class="form-control" name="optionCostsMatchingVOList[0].ocmProductionDivide">
		                                             </div>
		                                             <div class="col-md-2 mb-1 devideEachDiv">
			                                             <label for="zip">개수</label>
			                                             <input type="text" value="1" class="form-control" name="optionCostsMatchingVOList[0].ocmEach">
		                                             </div>
		                                             <div class="col-md-1 mb-1 deleteConnenctDiv">
			                                             <label for="zip"></label>
			                                             <button type="button" class="btn btn-space btn-warning" name="deleteConnenctButton"> 원재료삭제 </button>
		                                             </div>
	                                            </div>
	                                        </div>
                                        </div>
                                        <div class="form-group row text-right">
                                            <div class="col col-sm-10 col-lg-9 offset-sm-1 offset-lg-0">
                                                <button id="optionSubmit" type="submit" class="btn btn-space btn-primary"> 옵션 등록 하기 </button>
                                                <a class="btn btn-space btn-secondary" href="<c:url value='/products/read/product.do?productPk=${productVO.productPk }'/>"> 돌아가기 </a>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- ============================================================== -->
                        <!-- end valifation types -->
                        <!-- ============================================================== -->
                    </div>
            </div>
</body>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/slimscroll/jquery.slimscroll.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/multi-select/js/jquery.multi-select.js"></script>
<script type="text/javascript">
	$(function(){
		$("#anotherOptionPk").selectpicker('refresh');
	});
</script>
</html>