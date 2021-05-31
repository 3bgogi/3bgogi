<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		var optionCostsMatchingCount = 1;
    		
    		$("#anotherOptionPk").selectpicker('refresh');
    		
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
					                        +'<label class="col-12 col-sm-3 col-form-label text-sm-right"> 원가 연결 선택 </label>'
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
				                             +'<button type="button" class="btn btn-space btn-warning" name="deleteConnenctButton"> 원가삭제 </button>'
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
			
			
			$(document).on("click", "button[name=deleteCostMatchingButton]", function(){
				if(confirm("해당 원가 매칭을 정말로 삭제하시겠습니까?")){
					var costUniqueNum = $(this).data("cost-unique-num");
                    var costName = $(this).data("cost-name");
                    var optionPk = $(this).data("option-pk");
                    
                    var params = "?optionFk="+optionPk+"&costUniqueNum="+costUniqueNum+"&costName="+costName;
                    
                    location.href="<c:url value='/options/delete/option_cost_matching.do"+params+"'/>";
				}
			});
    	});
    </script>
    
        <!-- page content -->
        <!-- ============================================================== -->
        <!-- wrapper  -->
        <!-- ============================================================== -->
        <div class="dashboard-wrapper">
            <div class="container-fluid  dashboard-content">
                <!-- ============================================================== -->
                <!-- pageheader -->
                <!-- ============================================================== -->
                <div class="row">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <div class="page-header">
                            <h2 class="pageheader-title"> 옵션 확인 및 수정 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">데이터 관리</a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">데이터 목록</a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">상품 리스트</a></li>
                                        <li class="breadcrumb-item"><a href="<c:url value='/products/read/product.do?productPk=${optionVO.productFk }'/>" class="breadcrumb-link">상품</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">옵션 확인</li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- end pageheader -->
                <!-- ============================================================== -->
                    <div class="row">
                        <!-- ============================================================== -->
                        <!-- valifation types -->
                        <!-- ============================================================== -->
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> ${productVO.productName }의 옵션 확인 </h5>
                                <div class="card-body">
                                    <form id="insertOptionForm" method="POST" action="<c:url value='/options/update/option.do'/>">
                                    	<input type="hidden" value="${optionVO.productFk }" name="productFk">
                                    	<input type="hidden" value="${optionVO.optionPk }" name="optionPk">
                                        <div class="form-group row">
                                        	
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 명</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionName" placeholder="상품 명을 입력해주세요" class="form-control" value="${optionVO.optionName }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 판매가 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionPrice" placeholder="판매가를 입력해주세요." class="form-control" value="${optionVO.optionPrice }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 자체 원가 사용 여부 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionCostFlag"
	                                                <c:if test="${optionVO.optionCostFlag == true }">
	                                                	checked="checked"
	                                                </c:if>
	                                                 value="1" class="custom-control-input"><span class="custom-control-label">사용</span>
	                                            </label>
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionCostFlag"  
	                                                <c:if test="${optionVO.optionCostFlag == false }">
	                                                	checked="checked"
	                                                </c:if>
	                                                value="0" class="custom-control-input"><span class="custom-control-label">사용하지 않음</span>
	                                            </label>
                                            </div>
                                        </div>
                                        <div class="form-group row optionCostDiv"
	                                        <c:if test="${optionVO.optionCostFlag == false }">
		                                    	style="display: none;"
		                                    </c:if>
	                                        
	                                        >
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 자체 원가 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionCost" placeholder="원가를 입력해주세요." class="form-control" value="${optionVO.optionCost }">
                                            </div>
                                        </div>
                                        <div class="form-group row optionCostDiv"
	                                        <c:if test="${optionVO.optionCostFlag == false }">
		                                    	style="display: none;"
		                                    </c:if>
	                                        
	                                        >
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 공급 원가 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionSupplyCost" placeholder="공급 원가(입력 하지 않아도 됨)" class="form-control" value="${optionVO.optionSupplyCost }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                        	<label class="col-12 col-sm-3 col-form-label text-sm-right"> 다른 상품 재고 차감 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <select class="form-control" id="anotherOptionPk" name="anotherOptionPk" data-live-search="true" data-size="8">
                                                	<option value="0"> 상품 선택 </option>
	                                                <c:if test="${!empty productList }">
	                                                	<c:forEach var="productlist" items="${productList }">
	                                                		<option value="${productlist.optionPk }"
	                                                			<c:if test="${optionVO. anotherOptionPk == productlist.optionPk}">
	                                                				selected="selected"
	                                                			</c:if>
	                                                		>${productlist.productName } ${productlist.optionName }</option>
	                                                	</c:forEach>
	                                                </c:if>                                            	
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row" id="anotherOptionQtyDiv"                                        
                                        	<c:if test="${optionVO.anotherOptionPk == 0 }">
		                                    	style="display: none;"
		                                    </c:if>
                                        	>
                                        	<label class="col-12 col-sm-3 col-form-label text-sm-right"> 재고 차감 개수 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="anotherOptionQty" placeholder="다른 상품 재고 차감 개수 입력" class="form-control" value="${optionVO.anotherOptionQty }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 사용 여부 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionFlag" 
	                                                <c:if test="${optionVO.optionFlag == true }">
				                                    	checked="checked"
				                                    </c:if>
	                                                value="1" class="custom-control-input"><span class="custom-control-label">사용</span>
	                                            </label>
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionFlag" 
	                                                <c:if test="${optionVO.optionFlag == false }">
				                                    	checked="checked"
				                                    </c:if>
	                                                value="0" class="custom-control-input"><span class="custom-control-label">사용하지 않음</span>
	                                            </label>
                                            </div>
                                        </div>
                                         <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 주문서 위치 선택</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <select class="form-control" id="eoFk" name="eoFk">
	                                                <c:forEach var="exceloderseqVolist" items="${exceloderseqVoList }">
	                                                	<option value="${exceloderseqVolist.eosPk }"
	                                                		<c:if test="${optionVO.eoFk == exceloderseqVolist.eosPk }">
						                                    	selected="selected"
						                                    </c:if>
	                                                	>${exceloderseqVolist.eosLocation }</option>
	                                                </c:forEach>                                               	
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 합포 선택</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <select class="form-control" id="pccFk" name="pccFk">
	                                                <c:forEach var="packingcombinecodeVolist" items="${packingcombinecodeVoList }">
	                                                	<option value="${packingcombinecodeVolist.pccPk }"
	                                                		<c:if test="${optionVO.pccFk == packingcombinecodeVolist.pccPk  }">
						                                    	selected="selected"
						                                    </c:if>
	                                                	>${packingcombinecodeVolist.pccType }</option>
	                                                </c:forEach>                                               	
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 순서 선택</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <select class="form-control" id="optionSeq" name="optionSeq">
                                                	<c:forEach var="numbering" begin="1" end="12" step="1">
                                                		<option value="${numbering }"
                                                			<c:if test="${optionVO.optionSeq == numbering  }">
						                                    	selected="selected"
						                                    </c:if>
                                                		>${numbering } 순위 </option>
                                                	</c:forEach>                                            	
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 바코드 1</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionBarcodeNumber1" class="form-control" value="${optionVO.optionBarcodeNumber1 }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 바코드 2</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionBarcodeNumber2" class="form-control" value="${optionVO.optionBarcodeNumber2 }">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 면세 여부 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionTaxFlag"
	                                                	<c:if test="${optionVO.optionTaxFlag == true  }">
						                                    checked="checked"
						                                </c:if>
	                                                 value="1" class="custom-control-input"><span class="custom-control-label">면세</span>
	                                            </label>
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionTaxFlag" 
	                                                	<c:if test="${optionVO.optionTaxFlag == false  }">
						                                    checked="checked"
						                                </c:if>
	                                                value="0" class="custom-control-input"><span class="custom-control-label">과세</span>
	                                            </label>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 재고 체크 여부 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionStockFlag" 
	                                                	<c:if test="${optionVO.optionStockFlag == true }">
	                                                		checked="checked"
	                                                	</c:if>
	                                                 value="1" class="custom-control-input"><span class="custom-control-label">사용</span>
	                                            </label>
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionStockFlag"
	                                                	<c:if test="${optionVO.optionStockFlag == false }">
	                                                		checked="checked"
	                                                	</c:if>
	                                                 value="0" class="custom-control-input"><span class="custom-control-label">사용하지 않음</span>
	                                            </label>
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row optionStockMaxAlarmDiv">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 재고 알람 개수 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionStockMaxAlarm" value="${optionVO.optionStockMaxAlarm }" class="form-control">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row optionStockDiv">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 재고 개수 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionStock" value="${optionVO.optionStock }" class="form-control">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 엑셀 컬러 여부 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionExcelColorCheck"
	                                                	<c:if test="${optionVO.optionExcelColorCheck == true  }">
						                                    checked="checked" 
						                                </c:if>
	                                                 value="1" class="custom-control-input"><span class="custom-control-label">사용</span>
	                                            </label>
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="optionExcelColorCheck"
	                                                	<c:if test="${optionVO.optionExcelColorCheck == false  }">
						                                    checked="checked" 
						                                </c:if>
	                                                 value="0" class="custom-control-input"><span class="custom-control-label">사용하지 않음</span>
	                                            </label>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 메모 1</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionMemo1" class="form-control" value="${optionVO.optionMemo1 }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 메모 2</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionMemo2" class="form-control" value="${optionVO.optionMemo2 }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 메모 3</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionMemo3" class="form-control" value="${optionVO.optionMemo3 }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 메모 4</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="optionMemo4" class="form-control" value="${optionVO.optionMemo4 }">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                        	<div class="col-xl-3 col-lg-6 col-md-6 col-sm-12 col-12"></div>
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
                                                <textarea name="optionRemark" style="resize: none;" rows="8" class="form-control">${optionVO.optionRemark }</textarea>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"></label>
                                            <div class="col-12 col-sm-8 col-lg-6" style="text-align: center;">
                                                <button type="button" class="btn btn-space btn-secondary" id="addCostsMatchingDivButton"> 원가 연결 창 추가하기 </button>
                                            </div>
                                        </div>
                                        <div class="costsConnectDiv">   
                                        	<c:forEach var="ocmList" items="${optionVO.optionCostsMatchingVOList }">
                                        		<div class="form-group row costsDiv">
		                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 원가 연결 선택 </label>
		                                            <div class="col-12 col-sm-8 col-lg-6 row">
			                                            <div class="col-md-5 mb-3">
			                                           	 	<label for="country">옵션 명</label>
			                                            	<select class="form-control" disabled="disabled">
			                                            		<c:set var="costNames" value=""/>
			                                                	<c:forEach var="costsVolist" items="${costsVoList }">                                                	
					                                                <option data-unique-num="${costsVolist.costUniqueNum}" value="${costsVolist.costName }"
					                                                	<c:if test="${costsVolist.costUniqueNum eq ocmList.costUniqueNum and costsVolist.costName eq ocmList.costName }">
						                                                	selected="selected"
						                                                	<c:set var="costNames" value="${costsVolist.costName }"/>
						                                                </c:if>
					                                                >${costsVolist.costName } - <fmt:formatNumber value="${costsVolist.totalPrice }" pattern="#,###"
					                                                ></fmt:formatNumber>
					                                                	 원</option>                                       	
			                                                	</c:forEach>
			                                                </select>
			                                            </div>
			                                            <div class="col-md-2 mb-2 devideDiv">
				                                             <label for="zip"> 타입 </label>
				                                             <select class="form-control ocmGramRecalFlag" disabled="disabled">
				                                             	<option 
					                                             	<c:if test="${ocmList.ocmGram == 0 }">
					                                             		selected="selected"
					                                             	</c:if>
				                                             	value="0"> 원본 그대로 </option>
				                                             	<option 
				                                             		<c:if test="${ocmList.ocmGram == 1 }">
				                                             			selected="selected"
				                                             		</c:if>
				                                             	value="1"> g으로 나누기 </option>
				                                             </select>
			                                             </div>
			                                             <div class="col-md-2 mb-1 devideNumDiv"
				                                             <c:if test="${ocmList.ocmGram == 0 }">
						                                     	style="display: none;"
						                                     </c:if>
						                                     <c:if test="${ocmList.ocmGram == 1 }">

						                                     </c:if>
			                                              >
				                                             <label for="zip">누눌 수</label>
				                                             <input type="text" class="form-control" value="${ocmList.ocmProductionDivide }" disabled="disabled">
			                                             </div>
			                                             <div class="col-md-2 mb-1 devideEachDiv">
				                                             <label for="zip">개수</label>
				                                             <input type="text" class="form-control" value="${ocmList.ocmEach }" disabled="disabled">
			                                             </div>
			                                             <div class="col-md-1 mb-1 deleteConnenctDiv">
				                                             <label for="zip"></label>
				                                             <button type="button" class="btn btn-space btn-warning" name="deleteCostMatchingButton"
				                                             data-cost-unique-num="${ocmList.costUniqueNum }"
				                                             data-cost-name="${costNames }"
				                                             data-option-pk="${optionVO.optionPk }"> 원가삭제 </button>
			                                             </div>
		                                            </div>
		                                        </div>
                                        	</c:forEach> 
                                        	                                    
                                        </div>
                                        <div class="form-group row text-right">
                                            <div class="col col-sm-10 col-lg-9 offset-sm-1 offset-lg-0">
                                                <button id="optionSubmit" type="submit" class="btn btn-space btn-primary"> 옵션 수정 하기 </button>
                                                <a class="btn btn-space btn-secondary" href="<c:url value='/products/read/product.do?productPk=${optionVO.productFk }'/>"> 돌아가기 </a>
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
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>