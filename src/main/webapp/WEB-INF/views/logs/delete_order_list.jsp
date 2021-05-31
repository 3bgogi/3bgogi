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
    		
			
    		$("#searchDeleteOrderBtn").click(function(){
    			$("#orderDeleteSearchForm").submit();
    			
    		});
    		
    		$("#orderDeleteSearchForm").submit(function(){
    			dateStart = $("#exDateStart").val();
    			dateEnd = $("#exDateEnd").val();
    			
    			$("#dateStart").val(dateStart);
    			$("#dateEnd").val(dateEnd);
    			
    		});
    		
    	});
    	
    	function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#orderDeleteSearchForm").submit();
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
                            <h2 class="pageheader-title"> 주문서 삭제 기록 내역 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 설정 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 주문서 삭제 기록 내역</li>
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
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				        	<div class="card">
				            	<h5 class="card-header"> 주문서 삭제 내역 </h5>
				           		<div class="card-body">
				            		<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mb-2">
                                		<div class="form-inline">
	                                		<input type="text" class="form-control form-control-sm mb-2" id="exDateStart" style="text-align: center;" value="${osVO.dateStart }"/>
	                                		<input type="text" class="form-control form-control-sm mb-2" id="exDateEnd" style="text-align: center;" value="${osVO.dateEnd }"/>
		                                	<button class="btn btn-primary btn-xs mb-2" type="button" id="searchDeleteOrderBtn"> 검색 </button>
		                                	
                                		</div>
	                                </div>
				        		<table class="table table-hover">
				                	<thead>
										<tr>
											<th>관리자</th>
											<th>ip</th>
											<th>주문번호</th>
											<th>상품주문번호</th>
											<th>상품명</th>
											<th>옵션명</th>
											<th>수량</th>
											<th>발송기한</th>
											<th>삭제일</th>
										</tr>
				                    </thead>
				                    <tbody>
				                    	<c:if test="${!empty orderDeleteList }">
				                    		<c:forEach var="list" items="${orderDeleteList }">                    		
					                    		<tr>
					                    			<td>${list.orderDelAdmin }</td>
					                    			<td>${list.orderDelIp }</td>
					                    			<td>${list.orderDelOrderNumber }</td>
					                    			<td>${list.orderDelProductOrderNumber }</td>
					                    			<td>${list.orderDelProduct }</td>
					                    			<td>${list.orderDelProductOption }</td>
					                    			<td>${list.orderDelProductQty }</td>
					                    			<td>${list.orderDelSendingDeadline }</td>
					                    			<td>${list.orderDelRegdate }</td>
					                    		</tr>
				                    		</c:forEach>
				                        </c:if>
				                        <c:if test="${empty orderDeleteList }">
				                        	<tr>
				                        		<td colspan="9">작업기록이 없습니다</td>
				                        	</tr>
				                        </c:if>
				                   	</tbody>
				                </table>
				             </div>
				          </div>
				        </div>
                        
						<form class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="orderDeleteSearchForm" action="<c:url value='/order/config/delete_order_list.do'/>" method="get">
							<input type="hidden" name="searchCondition" >
                        	<input type="hidden" name="searchKeyword">
                        	<input type="hidden" name="dateStart" id="dateStart" value="${osVO.dateStart }">
							<input type="hidden" name="dateEnd" id="dateEnd" value="${osVO.dateEnd }">
                        	<input type="hidden" name="currentPage" value="${osVO.currentPage}">
							<nav aria-label="Page navigation" style="text-align: center;">
								<ul class="pagination" style="display: -webkit-inline-box;">
									<c:if test='${osVO.firstPage>1 }'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${osVO.firstPage-1})">«</a></li>
									</c:if>
									<c:forEach var="i" step="1" end="${osVO.lastPage}" begin="${osVO.firstPage }">
										<li class="page-item 
											<c:if test='${osVO.currentPage == i }'>
												active
											</c:if>
										"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${i})">${i }</a></li>
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