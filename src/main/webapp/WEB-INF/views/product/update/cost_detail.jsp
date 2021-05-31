<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		$('input[name=cdLossFlag]').change(function(){
    			if($(this).val()=='1'){
    				$('.cdLossRateDiv').show();
    			}else{
    				$('.cdLossRateDiv').hide();
    			}
    		});
    		
    		$('input[name=cdCompanyDiagnosis]').change(function(){
    			if($(this).val()=='1'){
    				$('.cdManufacturerDiv').show();
    				$('.cdStoreCompanyDiv').hide();
    				
    			}else{
    				$('.cdManufacturerDiv').hide();
    				$('.cdStoreCompanyDiv').show();
    			}
    		});
    		
    		//원가에 숫자가 아닌 문자가 들어갈 경우 초기화 시킴
    		$("#cdCost").keyup(function(){
    			var regex=  /^\d+$/;
    			
    			var cdCost = $(this).val();
    			
    			if(!regex.test(cdCost)){
    				alert("숫자만 입력해야 합니다.");
    				$(this).val("0");
    			}
    		});
    		
    		//손실률에 숫자가 아닌 문자가 들어갈 경우 초기화 시킴
    		$("#cdLossRate").keyup(function(){
    			var regex=  /^\d+$/;
    			
    			var cdLossRate = $(this).val();
    			
    			if(!regex.test(cdLossRate)){
    				alert("숫자만 입력해야 합니다.");
    				$(this).val("0");
    			}
    		});
    		
    		$("#insertCostInputDataForm").submit(function(){
    			var ciPrice = $("#ciPrice").val();
    			var ciWeight = $("#ciWeight").val();
    			var ciAnimalProdTraceNum = $("#ciAnimalProdTraceNum").val();
    			var ciAbattoir = $("#ciAbattoir").val();
    			var ciCountryOfOrigin = $("#ciCountryOfOrigin").val();
    			var ciLevel = $("#ciLevel").val();
    			var ciItemsManufNum = $("#ciItemsManufNum").val();
    		});
    		
    		//저장할 때에 중요사항 null 값 체크
    		$("#insertCostDetailData").submit(function(){
    			var cdName = $("#cdName").val();
    			var cdCost = $("#cdCost").val();
    			var cdLossFlag = $("input[name=cdLossFlag]").val();
    			var cdLossRate = $("#cdLossRate").val();
    			
    			if(cdName == null || cdName == ""){
    				alert("원가명을 입력해주세요.");
    				$("#cdName").focus();
    				event.preventDefault();
    				return false;
    				
    			}else if(cdCost == null){
    				alert("원가를 입력해주세요.");
    				$("#cdCost").val("0");
    				$("#cdCost").focus();
    				event.preventDefault();
    				return false;
    				
    			}else if(cdLossFlag == 1){
    				
    				if(cdLossRate == null){
    					alert("손실률을 입력해주세요.");
    					$("#cdLossRate").focus();
        				event.preventDefault();
        				return false;
        				
    				}
    				
    			}
    			
    			 if(confirm("이 정보로 원가를 입력하시겠습니까?")){
    				 
    			 }else{
    				event.preventDefault();
     				return false;
     				
    			 }
    			 
    		});
    		
    		 $(".numberCheck").keyup(function(){
				 var checkVal = onlyNumberInsertFunc($(this).val());
				 $(this).val(checkVal);
			 });
    		 
    		 $("input[name=ciOutputFlag]").click(function(){
    			var cdFk = $(this).data("cd-fk");
    			var ciPk = $(this).data("ci-pk");
    			
    			location.href="<c:url value='/products/choose_cost_io.do?ciPk="+ciPk+"&cdFk="+cdFk+"'/>";
    			
    			
    		 });
    		
    	});
    	
    	function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#costDetailListForm").submit();
		}
    	
    	function openCostIoDetail(ciPk){
    		
    		window.open("<c:url value='/products/update/cost_io.do?ciPk="+ciPk+"'/>", "입고 상품 자세히 보기" , "width=600, height=760, top=200, left=1200, scrollbars=no");
    		
    	}
    	
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
    <style type="text/css">
    	.table-3bgogi-hover:hover{
    		background: #FFC6C6;
    	}
    </style>
    
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
                            <h2 class="pageheader-title"> 상품 및 원재료 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 상품 및 원재료 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 상품 및 원재료 입력 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 원재료 상세사항 수정</li>
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
                                <h5 class="card-header"> 
                                	<c:forEach var="cclist" items="${ccList }">
                                		<c:if test="${cdVO.ccFk == cclist.ccPk}">
                                			${cclist.ccCodeType } ${cdVO.cdName } 수정
                                		</c:if>                                                	
                                    </c:forEach>
                                </h5>
                                <div class="card-body">
                                    <form name="insertCostDetailForm" id="insertCostDetailData" action="<c:url value='/products/insert/cost_detail.do'/>" method="post">
                                    	<input type="hidden" name="cdPk" value="${cdVO.cdPk }">
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 원재료명 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="원가명을 입력해주세요." class="form-control" id="cdName" name="cdName" value="${cdVO.cdName }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 원재료 분류 코드 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <select class="form-control" id="ccFk" name="ccFk">
                                                	<c:forEach var="cclist" items="${ccList }">
	                                                    <option value="${cclist.ccPk }"
	                                                    <c:if test="${cdVO.ccFk == cclist.ccPk}">
				                                			selected="selected"
				                                		</c:if>
	                                                    > ${cclist.ccCodeType } </option>
	                                                                                                    	
                                                	</c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 원가 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="원가를 입력해주세요. (손실률 없는 원가)" id="cdCost" class="form-control" name="cdCost" value="${cdVO.cdCost }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 원가 손실 여부 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <label class="custom-control custom-radio custom-control-inline">
                                                <input type="radio" value="1" name="cdLossFlag"
                                                	<c:if test="${cdVO.cdLossFlag == true }">
	                                                	checked="checked" 
                                                	</c:if> 
                                                class="custom-control-input"><span class="custom-control-label"> 손실 Y </span>
                                            </label>
                                            <label class="custom-control custom-radio custom-control-inline">
                                                <input type="radio" value="0" name="cdLossFlag" 
                                                	<c:if test="${cdVO.cdLossFlag == false }">
	                                                	checked="checked" 
                                                	</c:if>
                                                class="custom-control-input"><span class="custom-control-label"> 손실 N</span>
                                            </label>
                                            </div>
                                        </div>
                                        <div class="form-group row cdLossRateDiv">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 원가 손실률 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="원가 손실률을 입력해주세요." class="form-control" id="cdLossRate" name="cdLossRate" value="${cdVO.cdLossRate }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 구입사 구분 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <label class="custom-control custom-radio custom-control-inline">
                                                <input type="radio" value="1" name="cdCompanyDiagnosis" 
                                                	<c:if test="${cdVO.cdCompanyDiagnosis == true }">
	                                                	checked="checked" 
                                                	</c:if> 	
                                                class="custom-control-input"><span class="custom-control-label"> 제조사 </span>
                                            </label>
                                            <label class="custom-control custom-radio custom-control-inline">
                                                <input type="radio" value="0" name="cdCompanyDiagnosis" 
                                                	<c:if test="${cdVO.cdCompanyDiagnosis == false }">
	                                                	checked="checked" 
                                                	</c:if> 
                                                class="custom-control-input"><span class="custom-control-label"> 판매처 </span>
                                            </label>
                                            </div>
                                        </div>
                                        
	                                     	 <div class="form-group row cdManufacturerDiv"
	                                     	 	<c:if test="${cdVO.cdCompanyDiagnosis == false }">
	                                     	 		style="display:none;"
	                                     	 	 </c:if>
	                                     	 >
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right" style="color:red;"> 제조사</label>
	                                            <div class="col-12 col-sm-8 col-lg-6">
	                                                <input type="text" placeholder="제조사를 입력해주세요. (쓰지 않아도 무관)" class="form-control" id="cdManufacturer" name="cdManufacturer" value="${cdVO.cdManufacturer }">
	                                            </div>
	                                        </div>
	                                     	<div class="form-group row cdStoreCompanyDiv"
	                                     		<c:if test="${cdVO.cdCompanyDiagnosis == true }">
	                                     	 		style="display:none;"
	                                     	 	 </c:if>
	                                     	>
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right" style="color:skyblue;"> 판매처 </label>
	                                            <div class="col-12 col-sm-8 col-lg-6">
	                                                <input type="text" placeholder="판매처를 입력해주세요. (쓰지 않아도 무관)" class="form-control" id="cdStoreCompany" name="cdStoreCompany" value="${cdVO.cdStoreCompany }">
	                                            </div>
	                                        </div> 
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 원가 단위 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <select class="form-control" id="cdMeasure" name="cdMeasure">
                                                    <option value="kg"
                                                    	<c:if test="${cdVO.cdMeasure == 'kg' }">
                                                    		selected="selected"
                                                    	</c:if>
                                                    > Kg(키로)</option>
                                                    <option value="liter"
                                                    	<c:if test="${cdVO.cdMeasure == 'liter' }">
                                                    		selected="selected"
                                                    	</c:if>
                                                    > Liter(리터)</option>
                                                    <option value="ea"
                                                    	<c:if test="${cdVO.cdMeasure == 'ea' }">
                                                    		selected="selected"
                                                    	</c:if>
                                                    > Ea(개수)</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 원재료 설명란 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <textarea class="form-control" id="cdRemark" name="cdRemark" rows="3" style="resize: none;" placeholder="적지 않아도 무관.">${cdVO.cdRemark }</textarea>
                                            </div>
                                        </div>
                                        <div class="form-group row text-right">
                                            <div class="col col-sm-10 col-lg-9 offset-sm-1 offset-lg-0">
                                                <button type="button" class="btn btn-space btn-primary"> 수정하기 </button>
                                                <button class="btn btn-space btn-secondary">취소하기</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        
                        <!-- 원가 변동사항 로그란 시작-->
                        
                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-12 col-12">
	                        <div class="card">
                                <h5 class="card-header"> 상품 입고 </h5>
                                <div class="card-body">
                                	<form class="needs-validation" id="insertCostInputDataForm" method="POST" action="<c:url value='/products/insert/cost_io.do'/>">
                                		<input type="hidden" name="cdFk" value="${cdVO.cdPk }">
                                    	<div class="mb-3">
	                                    	<label for="ciPrice">원가</label>
	                                    	<input type="number" class="form-control numberCheck" id="ciPrice" name="ciPrice" placeholder="원가를 입력해주세요" value="0">
	                                    </div>
                                        <div class="row">
                                        	<div class="col-md-9 mb-3">
                                            	<label for="ciWeight"> 입고 무게( g단위로 입력 ) </label>
                                                <input type="text" class="form-control numberCheck" id="ciWeight" name="ciWeight" placeholder="" value="0" required="">
                                            </div>
                                            <div class="col-md-3 mb-3">
                                            	<label for="ciLevel">등급</label>
                                                <input type="text" class="form-control" id="ciLevel" name="ciLevel" placeholder="" required="">
                                            </div>
                                        </div>
                                        <div class="mb-3">
                                        	<label for="ciAnimalProdTraceNum">이력번호</label>
                                            <input type="text" class="form-control" id="ciAnimalProdTraceNum" name="ciAnimalProdTraceNum" placeholder="이력번호를 입력해주세요" required="">
                                        </div>
                                        <div class="mb-3">
                                        	<label for="ciItemsManufNum">품목제조번호</label>
                                            <input type="text" class="form-control" id="ciItemsManufNum" name="ciItemsManufNum" placeholder="품목제조번호를 입력해주세요" required="">
                                        </div>
                                        <div class="row">
                                        	<div class="col-md-6 mb-3">
                                            	<label for="ciAbattoir"> 도축장 </label>
                                                <input type="text" class="form-control" id="ciAbattoir" name="ciAbattoir" placeholder="" value="" required="">
                                            </div>
	                                        <div class="col-md-6 mb-3">
	                                        	<label for="ciCountryOfOrigin"> 원산지 </label>
	                                            <input type="text" class="form-control" id="ciCountryOfOrigin" name="ciCountryOfOrigin" placeholder="" value="" required="">
	                                        </div>
                                        </div>
                                        <hr class="mb-4">
                                        <button class="btn btn-primary btn-lg btn-block" type="submit"> 입고하기 </button>
                                   </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-8 col-lg-8 col-md-8 col-sm-12 col-12">
	                        <div class="card">
                                <h5 class="card-header">원재료 변동 사항</h5>
                                <div class="card-body">
	                                <div class="table-responsive">
	                                    <table id="example2" class="table table-bordered" style="text-align:center; font-size: 12px; word-break: keep-all; white-space: nowrap;">
	                                        <thead>
	                                            <tr>
	                                                <th scope="col" width="10%">입고무게</th>
	                                                <th scope="col" width="20%">이력번호</th>
	                                                <th scope="col" width="5%">등급</th>
	                                                <th scope="col" width="10%">도축장</th>
	                                                <th scope="col" width="10%">원산지</th>
	                                                <th scope="col" width="15%">품목제조번호</th>
	                                                <th scope="col" width="10%">등록일</th>
	                                                <th scope="col" width="15%">출력라벨선택</th>
	                                            </tr>
	                                        </thead>
	                                        
	                                        <tbody>
	                                        	<c:if test="${!empty cdVO.costIoVOList}">                                        	
		                                        	<c:forEach var="costIoVOlist" items="${cdVO.costIoVOList }">                                    	
			                                            <tr class="table-3bgogi-hover">
			                                                <td onclick="openCostIoDetail(${costIoVOlist.ciPk })"><fmt:formatNumber value="${costIoVOlist.ciWeight }" pattern="#,###"/>g</td>
			                                                <td onclick="openCostIoDetail(${costIoVOlist.ciPk })">${costIoVOlist.ciAnimalProdTraceNum }</td>
			                                                <td onclick="openCostIoDetail(${costIoVOlist.ciPk })">${costIoVOlist.ciLevel }</td>
			                                                <td onclick="openCostIoDetail(${costIoVOlist.ciPk })">${costIoVOlist.ciAbattoir }</td>
			                                                <td onclick="openCostIoDetail(${costIoVOlist.ciPk })">${costIoVOlist.ciCountryOfOrigin }</td>
			                                                <td onclick="openCostIoDetail(${costIoVOlist.ciPk })">${costIoVOlist.ciItemsManufNum }</td>
			                                                <td onclick="openCostIoDetail(${costIoVOlist.ciPk })"><fmt:formatDate value="${costIoVOlist.ciRegdate }" pattern="yyyy-MM-dd"/> </td>
			                                                <td>
			                                                	<label class="custom-control custom-radio custom-control-inline">
						                                        	<input type="radio"  name="ciOutputFlag" value="0" data-cd-fk="${cdVO.cdPk }" data-ci-pk="${costIoVOlist.ciPk }"
						                                        		<c:if test="${costIoVOlist.ciOutputFlag == true }">
																			 checked="checked"
																		</c:if>
						                                        	class="custom-control-input"><span class="custom-control-label"></span>
						                                        </label>
			                                                </td>
			                                            </tr>
		                                        	</c:forEach>
	                                        	</c:if>
	                                        	<c:if test="${empty cdVO.costIoVOList}">
	                                        		<td colspan="8"> 원재료 변동 내역이 없습니다</td>
	                                        	</c:if>
	                                        </tbody>
	                                    </table>
	                                </div>
                                </div>
                            </div>
                        </div>
                        <!-- 원가 변동사항 로그란 끝-->
						<!-- ============================================================== -->
                        <!-- end valifation types -->
                        <!-- ============================================================== -->
                    </div>
            </div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>