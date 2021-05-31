<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		$(".deleteRc").click(function(){
    			
    			if(confirm("해당 거래처를 정말로 삭제하시겠습니까? (거래내역서도 삭제됨)")){
    				
    				rcPk = $(this).val();
    				
    				location.href="<c:url value='/tax/res_company/delete.do?rcPk="+rcPk+"'/>";
    				
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
                            <h2 class="pageheader-title"> 등록된 거래처 목록 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 세금계산서</a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 거래처 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 거래처 목록 </li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ecommerce-widget">
                    <div class="row">
                        <!-- ============================================================== -->
                        <!-- valifation types -->
                        <!-- ============================================================== -->
                        <div class="col-xl-9 col-lg-8 col-md-8 col-sm-12 col-12">
	                        <div class="card">
                                <h5 class="card-header">거래처 목록</h5>
                                <div class="card-body">
                                	<div class="table-responsive">
                                		<a class="btn btn-primary btn-xs mb-3" href="<c:url value='/tax/res_company/insert.do'/>"> 거래처 추가 </a>
	                                    <table id="example2" class="table table-bordered" style="text-align:center; font-size: 12px; word-break: keep-all; white-space: nowrap;">
	                                        <thead class="bg-light">
	                                            <tr>
	                                                <th width="30%">거래처 명</th>
	                                                <th width="20%">사업자 번호</th>
	                                                <th width="20%">거래처 연락처</th>
	                                                <th width="20%">등록일</th>
	                                                <th width="10%">삭제</th>
	                                                
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:forEach var="rclist" items="${rcList }">                                        	
		                                            <tr class="table-3bgogi-hover">
		                                                <td>
		                                                	<a href="<c:url value='/tax/res_company/read.do?rcPk=${rclist.rcPk }'/>">${rclist.rcName }</a>
		                                                </td>
		                                                <td>${rclist.rcNum }</td>
		                                                <td>${rclist.rcContractNum }</td>
		                                                <td>${rclist.rcRegdate }</td>
		                                                <td>
		                                                	<button class="btn btn-danger btn-xs deleteRc" value="${rclist.rcPk }">삭제</button>
		                                                </td>
		                                            </tr>
	                                        	</c:forEach>
	                                        </tbody>
	                                    </table>
                                	</div>
                                </div>
                            </div>
                        </div>
						<%-- <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
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
						</div> --%>

						<!-- ============================================================== -->
                        <!-- end valifation types -->
                        <!-- ============================================================== -->
                    </div>
                 </div>
            </div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>