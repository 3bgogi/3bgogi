<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../../inc/top.jsp" %>
    <%@ include file="../../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		$('#searchMinDate').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d'
    			
    		});
    		
    		$('#searchMaxDate').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d'
    		});
    		
    		$(".updateStock").click(function(){
    			var pilPk = $(this).data("pil-pk");
    			var pilQty = $(this).data("pil-qty");
    			var optionFk = $(this).data("option-fk");

    			
    			$("#pilPk").val(pilPk);
    			$("#pilQty").val(pilQty);
    			$("#optionFk").val(optionFk);
    			
    			$("#selectProductStockResForm").attr("action","<c:url value='/stock/stock_add_res.do'/>");
    			$("#selectProductStockResForm").attr("method","post");
    			
    			$("#selectProductStockResForm").submit();
    		});
    		
    		
    		$("button[name=stockFileDownload]").click(function(){
    			var pilFile = $(this).val();

    				if(confirm("입고 파일을 다운로드 하시겠습니까?")){
    	    			
    	    		var pilFileInput = document.createElement("input");
    	    		pilFileInput.name="pilFileName";
    	    		pilFileInput.type="hidden";
    	    		pilFileInput.value=pilFile;
    	    			
    	    		var excelDownloadForm =  document.createElement("form")
    	    		excelDownloadForm.action="/stock/option_stocks_file.do";
    	    		excelDownloadForm.method="GET";
    	    			
    	    		excelDownloadForm.append(pilFileInput);
    	    			
    	    		$("#excelDownloadIframe").append(excelDownloadForm);
    	    		excelDownloadForm.submit();
    	    		$("#excelDownloadIframe").html("");

    			}
    		});
    		
        	
    	});
    		
    	function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#selectProductStockResForm").submit();
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
                            <h2 class="pageheader-title"> 상품 입고 내역 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 재고관리 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 상품 입고 내역 </li>
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
                                <h5 class="card-header"> 입고 목록 </h5>
                                <div class="card-body">
                                	<div class="table-responsive">
	                                    <table id="example2" class="table table-bordered" style="text-align: center;">
	                                        <thead class="bg-light">
	                                            <tr>
	                                                <th width="17%">요청 상품(개수)</th>
	                                                <th width="17%">유통기한</th>
	                                                <th width="15%">신청자<span class="text-success">(승인자)</span></th>
	                                                <th width="15%">요청일<span class="text-success">(승인일)</span></th>
	                                                <th width="10%">상세파일</th>
	                                                <th width="10%">등록일</th>
	                                                <th width="10%">요청</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:if test="${empty pilList }">
	                                        		<tr>
	                                        			<td colspan="7"> 입고 내역이 없습니다 </td>
	                                        		</tr>
	                                        	</c:if>
	                                        	<c:if test="${!empty pilList }">
	                                        		<c:forEach var="pil" items="${pilList }">
	                                        			<tr>
		                                        			<td >  
		                                        				${pil.productList[0].productName }<br>
		                                        				[ ${pil.productList[0].optionName } ] ${pil.pilQty } 개
		                                        			</td>
		                                        			<td >
		                                        				<c:if test="${!empty pil.pilExpDate }">
		                                        					${pil.pilExpDate }
		                                        				</c:if>
		                                        				<c:if test="${empty pil.pilExpDate }">
		                                        					-
		                                        				</c:if>
		                                        			</td>
		                                        			<td >  
		                                        				${pil.pilAdmin }
		                                        				<c:if test="${!empty pil.pilPermAdmin }">
		                                        					<p class="text-success">${pil.pilPermAdmin }</p>
		                                        				</c:if>
		                                        			</td>
		                                        			<td >
		                                        				${pil.pilReqDate } <br>
		                                        				<c:if test="${!empty pil.pilResDate }">
		                                        					<p class="text-success">(${pil.pilResDate })</p>
		                                        				</c:if>
		                                        			</td>
		                                        			<td >
		                                        				<c:if test="${!empty pil.pilFilePath}">
		                                        					<button name="stockFileDownload" class="btn btn-primary btn-xs" value="${pil.pilFileName}">${pil.pilFileOriName}</button>
		                                        				</c:if>
		                                        				<c:if test="${empty pil.pilFilePath}">
		                                        					-
		                                        				</c:if>
		                                        			</td>
		                                        			<td >
		                                        				<fmt:formatDate value="${pil.pilRegdate }" pattern="yyyy-MM-dd"/>
		                                        			</td>
		                                        			<td>
		                                        				<c:if test="${pil.pilPermFlag == true }">
		                                        					<button class="btn btn-success btn-xs"> 승인완료 </button>
		                                        				</c:if>
		                                        				<c:if test="${pil.pilPermFlag == false }">
		                                        					<sec:authorize access="hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')">
			                                        					<button class="btn btn-primary btn-xs updateStock"
			                                        					data-pil-pk="${pil.pilPk }" 
			                                        					data-pil-qty="${pil.pilQty }"
			                                        					data-option-fk="${pil.productList[0].optionPk }"
			                                        					> 승인</button>
		                                        					</sec:authorize>
		                                        					
		                                        					<sec:authorize access="hasRole('ROLE_USER') and !hasRole('ROLE_ADMIN')">
			                                        					-승인대기-
		                                        					</sec:authorize>
		                                        				</c:if>
		                                        			</td>
		                                        		</tr>
	                                        		</c:forEach>
	                                        	</c:if>
	                                        </tbody>
	                                    </table>
                                	</div>
                                </div>
                            </div>
                        </div>
                        	<!-- 페이징 처리에 필요한 값 -->
						<div class="col-xl-3 col-lg-4 col-md-4 col-sm-12 col-12">
                        	<form id="selectProductStockResForm" action="<c:url value='/stock/stock_req_list.do'/>" method="get">
                        	<input type="hidden" name="searchCondition" >
                        	<input type="hidden" name="searchKeyword">
                        	<input type="hidden" name="pilPk" id="pilPk">
                        	<input type="hidden" name="pilQty" id="pilQty">
                        	<input type="hidden" name="optionFk" id="optionFk">
                        	<input type="hidden" name="currentPage" value="${PaginationInfo.currentPage}">
								<div class="card">
									<div class="card-body border-top">
										<h3 class="font-16"> 상품명 </h3>
										<input type="text" class="form-control" id="productName" name="searchOne" placeholder="입고 상품명 " value="${PaginationInfo.searchOne }">
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
										<h3 class="font-16"> 승인 여부 </h3>
										<div class="custom-control custom-checkbox">
											<input type="radio" value="" class="custom-control-input" name="permType" id="permAll" 
												<c:if test="${PaginationInfo.permType == '' or empty PaginationInfo.permType}">
													checked
												</c:if>
											>
											<label class="custom-control-label" for="permAll"> 전체</label>
										</div>
										<div class="custom-control custom-checkbox">
											<input type="radio" value="donPerm" class="custom-control-input" name="permType" id="donPerm"
												<c:if test="${PaginationInfo.permType == 'donPerm' }">
													checked
												</c:if>
											>
											<label class="custom-control-label" for="donPerm"> 미승인 </label>
										</div>
										<div class="custom-control custom-checkbox">
											<input type="radio" value="permision" class="custom-control-input" name="permType" id="permision"
												<c:if test="${PaginationInfo.permType == 'permision' }">
													checked
												</c:if>
											>
											<label class="custom-control-label" for="permision"> 승인 </label>
										</div>
									</div>
									<div class="card-body border-top">
										<h3 class="font-16"> 페이지 당 목록 개수 </h3>
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
										<h3 class="font-16"> 검색 날짜 타입 </h3>
										<div class="custom-control custom-checkbox">
											<input type="radio" value="pil_regdate" class="custom-control-input" name="dateType" id="cdAll" 
												<c:if test="${PaginationInfo.dateType == 'pil_regdate' or empty PaginationInfo.dateType}">
													checked
												</c:if>
											>
											<label class="custom-control-label" for="cdAll"> 등록일 </label>
										</div>
										<div class="custom-control custom-checkbox">
											<input type="radio" value="pil_req_date" class="custom-control-input" name="dateType" id="reqDate"
												<c:if test="${PaginationInfo.dateType == 'pil_req_date' }">
													checked
												</c:if>
											>
											<label class="custom-control-label" for="reqDate"> 요청일 </label>
										</div>
										<div class="custom-control custom-checkbox">
											<input type="radio" value="pil_res_date" class="custom-control-input" name="dateType" id="resDate"
												<c:if test="${PaginationInfo.dateType == 'pil_res_date' }">
													checked
												</c:if>
											>
											
											<label class="custom-control-label" for="resDate"> 승인일 </label>
										</div>
										<div class="custom-control custom-checkbox">
											<input type="radio" value="pil_exp_date" class="custom-control-input" name="dateType" id="expDate"
												<c:if test="${PaginationInfo.dateType == 'pil_exp_date' }">
													checked
												</c:if>
											>
											<label class="custom-control-label" for="expDate"> 유통기한 </label>
										</div>
									</div>
									<div class="card-body border-top">
										<h3 class="font-16"> 날짜 선택 </h3>
										<div class="form-row">
											<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
												<input type="text" class="form-control" name="searchMinDate" id="searchMinDate" placeholder="" required value="${PaginationInfo.searchMinDate }">
											</div>
											<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
												<input type="text" class="form-control" name="searchMaxDate" id="searchMaxDate" placeholder="" required value="${PaginationInfo.searchMaxDate }">
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
								<ul class="pagination" style="display: -webkit-inline-box;">
									<c:if test='${PaginationInfo.firstPage>1 }'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc('${PaginationInfo.firstPage-1}')">«</a></li>
									</c:if>
									<c:forEach var="i" step="1" end="${PaginationInfo.lastPage}" begin="${PaginationInfo.firstPage }">
										<li class="page-item 
											<c:if test='${PaginationInfo.currentPage == i }'>
												active
											</c:if>
										"><a class="page-link" href="javascript:void(0)" onclick="pageFunc('${i}')">${i }</a></li>
									</c:forEach>
									<c:if test='${PaginationInfo.lastPage < PaginationInfo.totalPage}'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc('${PaginationInfo.lastPage+1}')">»</a></li>
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
        <iframe id="excelDownloadIframe" width="0" height="0">
		</iframe> 
        <%@ include file="../../../inc/bottom.jsp" %>