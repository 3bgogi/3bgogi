<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		$('#exDateStart').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d'
    			
    		});
    		$('#exDateEnd').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d'
    		});
    		
			
    		$("#searchProdQtyLogBtn").click(function(){
    			$("#prodQtyLogForm").submit();
    			
    		});
    		
    		
    		$("#prodQtyLogForm").submit(function(){
    			dateStart = $("#exDateStart").val();
    			dateEnd = $("#exDateEnd").val();
    			
    			$("#dateStart").val(dateStart);
    			$("#dateEnd").val(dateEnd);
    			
    		});
    	});
    	
    	function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#prodQtyLogForm").submit();
			
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
                            <h2 class="pageheader-title"> 상품 생산 기록 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 설정 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 상품 생산 기록 </li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ecommerce-widget">
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                        <div class="card">
	                        	<h5 class="card-header"> 상품 생산 기록 </h5>
                                <div class="card-body">
                                	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mb-2">
                                		<div class="form-inline">
	                                		<input type="text" class="form-control form-control-sm mb-2" id="exDateStart" style="text-align: center;" value="${osVO.dateStart }"/>
	                                		<input type="text" class="form-control form-control-sm mb-2" id="exDateEnd" style="text-align: center;" value="${osVO.dateEnd }"/>
		                                	<button class="btn btn-primary btn-xs mb-2" type="button" id="searchProdQtyLogBtn"> 검색 </button>
		                                	
                                		</div>
	                                </div>
                                	<div class="table-responsive">
	                                    <table id="example2" class="table table-bordered" style="text-align: center;">
	                                        <thead class="bg-light">
	                                            <tr>
	                                                <th width="60%">상품명</th>
	                                                <th width="10%">생산 수량</th>
	                                                <th width="10%">관리자모드</th>
	                                                <th width="20%">생산 시간</th>
	                                                
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:if test="${empty pqlList }">
	                                        		<tr>
	                                        			<td colspan="4" style="text-align: center;"> 생산 기록이 없습니다 </td>
	                                        		</tr>
	                                        	</c:if>
	                                        	<c:if test="${!empty pqlList }">
		                                        	<c:forEach var="pqllist" items="${pqlList }">                                        	
			                                            <tr class="table-3bgogi-hover">
			                                                <td>${pqllist.productName } [ ${pqllist.optionName } ]</td>
			                                                <td>${pqllist.pqlQty } 개</td>
			                                                <td>
			                                                	<c:if test="${pqllist.pqlAdminFlag == false}">
			                                                		N
			                                                	</c:if>
			                                                	<c:if test="${pqllist.pqlAdminFlag == true}">
			                                                		Y
			                                                	</c:if>
			                                                </td>
			                                                <td><fmt:formatDate value="${pqllist.pqlRegdate }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
			                                            </tr>
		                                        	</c:forEach>
	                                        	</c:if>
	                                        </tbody>
	                                    </table>
                                	</div>
                                </div>
                            </div>
                        </div>
						<form class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="prodQtyLogForm" action="<c:url value='/log/prod_qty_log.do'/>" method="get">
							<input type="hidden" name="dateStart" id="dateStart" value="${osVO.dateStart }">
							<input type="hidden" name="dateEnd" id="dateEnd" value="${osVO.dateEnd }">
							<input type="hidden" name="searchCondition" >
                        	<input type="hidden" name="currentPage" value="${osVO.currentPage}">
							<nav aria-label="Page navigation" style="text-align: center;">
								<ul class="pagination" style="display: -webkit-inline-box;">
									<c:if test='${osVO.firstPage>1 }'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${osVO.firstPage-1})">«</a></li>
									</c:if>
									<c:forEach var="i" step="1" end="${osVO.lastPage}" begin="${osVO.firstPage }">
										<c:if test="${osVO.currentPage != 0 }">
																				
											<li class="page-item 
												<c:if test='${osVO.currentPage == i }'>
													active
												</c:if>
											"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${i})">${i }</a></li>
										</c:if>
										
									</c:forEach>
									<c:if test='${osVO.lastPage < osVO.totalPage}'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${osVO.lastPage+1})">»</a></li>
									</c:if>
								</ul>
							</nav>
						</form>

						<!-- ============================================================== -->
                        <!-- end valifation types -->
                        <!-- ============================================================== -->
                    </div>
                 </div>
            </div>
        <!-- /page content -->
        <%@ include file="../inc/bottom.jsp" %>