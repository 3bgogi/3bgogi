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
    				
    	});
    	function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#costDetailListForm").submit();
		}
    	
    	function updateCostDetail(cdPk){
    		location.href="<c:url value='/products/update/cost_detail.do?cdPk="+cdPk+"'/>";
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
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 상품 및 원재료 목록 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 원재료 목록 </li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- end pageheader -->
                <!-- ============================================================== -->
                <div class="ecommerce-widget">
                    <div class="row">
                        <!-- ============================================================== -->
                        <!-- valifation types -->
                        <!-- ============================================================== -->
                        <div class="col-xl-9 col-lg-8 col-md-8 col-sm-12 col-12">
	                        <div class="card">
                                <h5 class="card-header">입력된 원재료 목록</h5>
                                <div class="card-body">
                                	<div class="table-responsive">
	                                    <table id="example2" class="table table-bordered" style="text-align:center; font-size: 12px; word-break: keep-all; white-space: nowrap;">
	                                        <thead class="bg-light">
	                                            <tr>
	                                                <th width="17%">분류</th>
	                                                <th width="15%">원재료명</th>
	                                                <th width="15%">원가</th>
	                                                <th width="10%">손실률</th>
	                                                <th width="10%">손실 계산 원가</th>
	                                                <th width="10%">단위</th>
	                                                <th width="15%">제조사 혹은 판매처</th>
	                                                <th width="8%">수정</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:forEach var="costDetaillist" items="${costDetailList }">                                        	
		                                            <tr class="table-3bgogi-hover">
		                                                <td>${costDetaillist.costCodeVOList[0].ccCodeType }</td>
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
		                                                <td><button class="btn btn-primary" onclick="updateCostDetail(${costDetaillist.cdPk})">수정</button></td>
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
                        	<form id="costDetailListForm" action="<c:url value='/products/list/cost_detail.do'/>" method="get">
                        	<input type="hidden" name="searchCondition" >
                        	<input type="hidden" name="searchKeyword">
                        	<input type="hidden" name="currentPage" value="${PaginationInfo.currentPage}">
								<div class="card">
									<div class="card-body border-top">
										<h3 class="font-16"> 원재료명 </h3>
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
									<div class="card-body">
										<h3 class="font-16"> 페이지 당 원재료 목록 개수 </h3>
										<select class="form-control" name=recordCountPerPage>
											<option value="10"
												<c:if test="${PaginationInfo.recordCountPerPage == 10 }">
													selected="selected"
												</c:if>
											>10 개씩</option>
											<option value="20"
												<c:if test="${PaginationInfo.recordCountPerPage == 20 }">
													selected="selected"
												</c:if>
											>20 개씩</option>
											<option value="30"
												<c:if test="${PaginationInfo.recordCountPerPage == 30 }">
													selected="selected"
												</c:if>
											>30 개씩</option>
											<option value="40"
												<c:if test="${PaginationInfo.recordCountPerPage == 40 }">
													selected="selected"
												</c:if>
											>40 개씩</option>
										</select>
									</div>
									<div class="card-body border-top">
										<h3 class="font-16">단위</h3>
										<div class="custom-control custom-checkbox">
											<input type="radio" value="all" class="custom-control-input" name="searchTwo" id="cdAll" 
												<c:if test="${PaginationInfo.searchTwo == 'all' or PaginationInfo.searchTwo == '' }">
													checked
												</c:if>
											>
											<label class="custom-control-label" for="cdAll"> 전체 </label>
										</div>
										<div class="custom-control custom-checkbox">
											<input type="radio" value="kg" class="custom-control-input" name="searchTwo" id="cdMeasureKG"
												<c:if test="${PaginationInfo.searchTwo == 'kg' }">
													checked
												</c:if>
											>
											<label class="custom-control-label" for="cdMeasureKG"> kg </label>
										</div>
										<div class="custom-control custom-checkbox">
											<input type="radio" value="liter" class="custom-control-input" name="searchTwo" id="cdMeasureLITER"
												<c:if test="${PaginationInfo.searchTwo == 'liter' }">
													checked
												</c:if>
											>
											<label class="custom-control-label" for="cdMeasureLITER"> liter </label>
										</div>
										<div class="custom-control custom-checkbox">
											<input type="radio" value="ea" class="custom-control-input" name="searchTwo" id="cdMeasureEA"
												<c:if test="${PaginationInfo.searchTwo == 'ea' }">
													checked
												</c:if>
											>
											<label class="custom-control-label" for="cdMeasureEA"> ea </label>
										</div>
									</div>
									<div class="card-body border-top">
										<h3 class="font-16"> 원재료 금액대 </h3>
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
							<nav aria-label="Page navigation" style="text-align: center;">
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
            </div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>