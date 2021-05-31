<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		//판매처 감추기
    		$('.cdStoreCompanyDiv').hide();
    		
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
    		
    		
    		
    	});
    	function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#costDetailListForm").submit();
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
                            <h2 class="pageheader-title"> 원재료 입력 </h2>
                            <p class="pageheader-text">Proin placerat ante duiullam scelerisque a velit ac porta, fusce sit amet vestibulum mi. Morbi lobortis pulvinar quam.</p>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 상품 및 원재료 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 원재료 상세사항 입력 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 원재료 입력 </li>
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
                                <h5 class="card-header"> 원재료 데이터 상세사항 입력</h5>
                                <div class="card-body">
                                    <form name="insertCostDetailForm" id="insertCostDetailData" action="<c:url value='/products/insert/cost_detail.do'/>" method="post">
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 원재료명 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="원가명을 입력해주세요." class="form-control" id="cdName" name="cdName">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 원재료 분류 코드 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <select class="form-control" id="ccFk" name="ccFk">
                                                	<c:forEach var="cclist" items="${ccList }">
	                                                    <option value="${cclist.ccPk }"> ${cclist.ccCodeType } </option>                                                	
                                                	</c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 원가 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="원가를 입력해주세요. (손실률 없는 원가)" id="cdCost" class="form-control" name="cdCost" value="0">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 원가 손실 여부 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <label class="custom-control custom-radio custom-control-inline">
                                                <input type="radio" value="1" name="cdLossFlag" checked="checked" class="custom-control-input"><span class="custom-control-label"> 손실 Y </span>
                                            </label>
                                            <label class="custom-control custom-radio custom-control-inline">
                                                <input type="radio" value="0" name="cdLossFlag" class="custom-control-input"><span class="custom-control-label"> 손실 N</span>
                                            </label>
                                            </div>
                                        </div>
                                        <div class="form-group row cdLossRateDiv">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 원가 손실률 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="원가 손실률을 입력해주세요." class="form-control" id="cdLossRate" name="cdLossRate" value="0">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 구입사 구분 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <label class="custom-control custom-radio custom-control-inline">
                                                <input type="radio" value="1" name="cdCompanyDiagnosis" checked="checked" class="custom-control-input"><span class="custom-control-label"> 제조사 </span>
                                            </label>
                                            <label class="custom-control custom-radio custom-control-inline">
                                                <input type="radio" value="0" name="cdCompanyDiagnosis" class="custom-control-input"><span class="custom-control-label"> 판매처 </span>
                                            </label>
                                            </div>
                                        </div>
                                        <div class="form-group row cdManufacturerDiv">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right" style="color:red;"> 제조사</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="제조사를 입력해주세요. (쓰지 않아도 무관)" class="form-control" id="cdManufacturer" name="cdManufacturer">
                                            </div>
                                        </div>
                                        <div class="form-group row cdStoreCompanyDiv">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right" style="color:skyblue;"> 판매처 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="판매처를 입력해주세요. (쓰지 않아도 무관)" class="form-control" id="cdStoreCompany" name="cdStoreCompany">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 원가 단위 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <select class="form-control" id="cdMeasure" name="cdMeasure">
                                                    <option value="kg"> Kg(키로)</option>
                                                    <option value="liter"> Liter(리터)</option>
                                                    <option value="ea"> Ea(개수)</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 원가 설명란 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <textarea class="form-control" id="cdRemark" name="cdRemark" rows="3" style="resize: none;" placeholder="적지 않아도 무관."></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group row text-right">
                                            <div class="col col-sm-10 col-lg-9 offset-sm-1 offset-lg-0">
                                                <button type="submit" class="btn btn-space btn-primary">저장하기</button>
                                                <button class="btn btn-space btn-secondary">취소하기</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-xl-9 col-lg-8 col-md-8 col-sm-12 col-12">
	                        <div class="card">
                                <h5 class="card-header">입력된 원가 목록</h5>
                                <div class="card-body">
                                	<div class="table-responsive">
	                                    <table id="example2" class="table table-bordered" style="width:100%; text-align: center;">
	                                        <thead>
	                                            <tr>
	                                                <th scope="col">분류</th>
	                                                <th scope="col">원가명</th>
	                                                <th scope="col">원가</th>
	                                                <th scope="col">손실률</th>
	                                                <th scope="col">손실 계산 원가</th>
	                                                <th scope="col">단위</th>
	                                                <th scope="col">제조사 혹은 판매처</th>
	                                                <th scope="col">등록일</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:forEach var="costDetaillist" items="${costDetailList }">                                        	
		                                            <tr class="table-3bgogi-hover">
		                                                <th scope="row">${costDetaillist.costCodeVOList[0].ccCodeType }</th>
		                                                <td>${costDetaillist.cdName }</td>
		                                                
		                                                <td><fmt:formatNumber value="${costDetaillist.cdCost }" pattern="#,###"/>원</td>
		                                                
		                                                <c:if test="${costDetaillist.cdLossFlag == true }">
		                                                	<td>${costDetaillist.cdLossRate }%</td>
		                                                </c:if>
		                                                <c:if test="${costDetaillist.cdLossFlag == false }">
		                                                	<td style="color:red;">없음</td>
		                                                </c:if>
		                                                <td>
		                                                	<c:if test="${costDetaillist.cdLossFlag == true }">
		                                                		<c:set var="lossCal" value=""/>
		                                                		<fmt:formatNumber value="${costDetaillist.cdCost + (costDetaillist.cdCost*costDetaillist.cdLossRate/100) }" pattern="#,###"/>원
		                                                	</c:if>
		                                                	<c:if test="${costDetaillist.cdLossFlag == false }">
		                                                		<fmt:formatNumber value="${costDetaillist.cdCost }" pattern="#,###"/>원
		                                                	</c:if>
		                                                </td>
		                                                <td>${costDetaillist.cdMeasure }</td>
		                                                <td>
		                                                	<c:if test="${costDetaillist.cdCompanyDiagnosis == true }">
		                                                		제조사 : ${costDetaillist.cdManufacturer }
		                                                	</c:if>
		                                                	<c:if test="${costDetaillist.cdCompanyDiagnosis == false }">
		                                                		판매처 : ${costDetaillist.cdStoreCompany }
		                                                	</c:if>
		                                                </td>
		                                                <td>${costDetaillist.cdRegdate }</td>
		                                            </tr>
	                                        	</c:forEach>
	                                        </tbody>
	                                    </table>
                                	</div>
                                </div>
                            </div>
                        </div>
                        
                        
                        	<!-- 페이징 처리에 필요한 값 -->
						<div class="col-xl-3 col-lg-4 col-md-4 col-sm-12 col-12">
                        	<form id="costDetailListForm" action="<c:url value='/products/insert/cost_detail.do'/>" method="get">
                        	<input type="hidden" name="searchCondition" >
                        	<input type="hidden" name="searchKeyword">
                        	<input type="hidden" name="currentPage" value="${PaginationInfo.currentPage}">
								<div class="card">
									<div class="card-body border-top">
										<h3 class="font-16"> 원가명 </h3>
										<input type="text" class="form-control" id="cdName" name="searchOne" placeholder="원가명 입력 란" value="${PaginationInfo.searchOne }">
									</div>
									<div class="card-body">
										<h3 class="font-16">분류 코드</h3>
										<select class="form-control" name="searchCode">
											<option value="0">전체</option>
											<c:forEach var="cclist" items="${ccList }">
												<option value="${cclist.ccPk }" 
													<c:if test="${PaginationInfo.searchCode == cclist.ccPk }">
														selected="selected"
													</c:if>
												>${cclist.ccCodeType }</option>
											</c:forEach>
										</select>
									</div>
									<div class="card-body border-top">
										<h3 class="font-16">단위</h3>
										<div class="custom-control custom-checkbox">
											<input type="radio" value="all" class="custom-control-input" name="searchTwo" id="cdMeasureAll" checked="checked">
											<label class="custom-control-label" for="cdMeasureKG"> 전체 </label>
										</div>
										<div class="custom-control custom-checkbox">
											<input type="radio" value="kg" class="custom-control-input" name="searchTwo" id="cdMeasureKG">
											<label class="custom-control-label" for="cdMeasureKG"> kg </label>
										</div>
										<div class="custom-control custom-checkbox">
											<input type="radio" value="liter" class="custom-control-input" name="searchTwo" id="cdMeasureLITER">
											<label class="custom-control-label" for="cdMeasureLITER"> liter </label>
										</div>
										<div class="custom-control custom-checkbox">
											<input type="radio" value="ea" class="custom-control-input" name="searchTwo" id="cdMeasureEA">
											<label class="custom-control-label" for="cdMeasureEA"> ea </label>
										</div>
									</div>
									<div class="card-body border-top">
										<h3 class="font-16"> 원가 금액대 </h3>
										<div class="form-row">
											<div class="col-xl-4 col-lg-4 col-md-4 col-sm-12 col-12">
												<input type="text" class="form-control" name="searchMinPrice" id="minCost" placeholder="최소금액" required value="${PaginationInfo.searchMinPrice }">
											</div>
											<div class="col-xl-2 col-lg-2 col-md-2 col-sm-12 col-12"> 
												원부터
											</div>
											<div class="col-xl-5 col-lg-5 col-md-5 col-sm-12 col-12">
												<input type="text" class="form-control" name="searchMaxPrice" id="maxCost" placeholder="최대금액" required value="${PaginationInfo.searchMaxPrice }">
											</div>
											<div class="col-xl-1 col-lg-1 col-md-1 col-sm-12 col-12">
												원
											</div>
										</div>
									</div>
									<div class="card-body border-top">
										<button class="btn btn-secondary btn-lg btn-block"> 검색하기 </button>
									</div>
								</div>
							</form>
						</div>
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<nav aria-label="Page navigation example" style="text-align: center;">
								<ul class="pagination" style="display: inline-flex;  flex-wrap: wrap;">
									<c:if test='${PaginationInfo.firstPage>1 }'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${PaginationInfo.firstPage-1})">«</a></li>
									</c:if>
									<c:forEach var="i" step="1" end="${PaginationInfo.lastPage}" begin="${PaginationInfo.firstPage }">
										<li class="page-item 
											<c:if test='${PaginationInfo.currentPage == i }'>
												active
											</c:if>
										"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${i})">${i }</a></li>
									</c:forEach>
									<c:if test='${PaginationInfo.lastPage < PaginationInfo.totalPage}'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${PaginationInfo.lastPage+1})">»</a></li>
									</c:if>
								</ul>
							</nav>
						</div>

			<!-- ============================================================== -->
                        <!-- end valifation types -->
                        <!-- ============================================================== -->
                    </div>
            </div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>