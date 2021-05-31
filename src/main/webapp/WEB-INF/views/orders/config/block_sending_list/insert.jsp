<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../../inc/top.jsp" %>
    <%@ include file="../../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){

    		
    	});
    	
    	function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#searchBlockSendingList").submit();
			
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
                            <h2 class="pageheader-title"> 문자발송금지명단 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> C / S </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 문자발송금지명단 </li>
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
                                <h5 class="card-header"> 문자발송금지명단 목록</h5>
                                <div class="card-body">
                                	<div class="table-responsive">
	                                    <table id="example2" class="table table-bordered" style="text-align:center; font-size: 12px; word-break: keep-all; white-space: nowrap;">
	                                        <thead class="bg-light">
	                                            <tr>
	                                                <th width="17%"> 연락처 </th>
	                                                <th width="17%"> 등록일 </th>
	                                                <th width="17%"> 삭제 </th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:if test="${empty bslList }">
	                                        		<tr>
	                                        			<td colspan="3"> 등록된 문자발송금지명단이 없습니다</td>
	                                        		</tr>
	                                        	</c:if>
	                                        	<c:if test="${!empty bslList }">
		                                        	<c:forEach var="bsllist" items="${bslList }">                                        	
			                                            <tr class="table-3bgogi-hover">
			                                                <td>
			                                                	${bsllist.bslNumber }
			                                                </td>
			                                                <td>${bsllist.bslRegdate }</td>
			                                                <td>
			                                                	<a class="btn btn-danger btn-xs" href="<c:url value='/config/block_sending_list/delete.do?bslPk=${bsllist.bslPk }'/>">삭제하기</a>
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
							<div class="col-xl-3 col-lg-4 col-md-4 col-sm-12 col-12">
								<div class="card">
									<h5 class="card-header"> 문자발송금지명단 추가하기 </h5>
									<div class="card-body">
						            	<form class="form-group" action="<c:url value='/config/block_sending_list/insert.do'/>" method="POST">
					                    	<div class="input-group">
					                    		<input type="text" class="form-control" name="bslNumber" placeholder="연락처">
						                        <div class="input-group-append">
						                        	<button type="submit" class="btn btn-primary"> 추가 </button>
						                        </div>
					                        </div>
						            	</form>
						            </div>
								</div>
							</div>
						<form class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="searchBlockSendingList" method="get" action="<c:url value='/config/block_sending_list/insert.do'/>">
							<input type="hidden" name="searchCondition" >
                        	<input type="hidden" name="searchKeyword">
                        	<input type="hidden" name="currentPage" value="${osVO.currentPage}">
							<nav aria-label="Page navigation" style="text-align: center;">
								<ul class="pagination" style="display: inline-flex;  flex-wrap: wrap;">
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
        <iframe id="fileDownloadIframe" width="0" height="0">
		</iframe> 
        <%@ include file="../../../inc/bottom.jsp" %>