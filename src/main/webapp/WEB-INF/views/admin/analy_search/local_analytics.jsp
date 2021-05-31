<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		$('#dateStart').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d',
    			"setDate" : new Date(1985,10,01)
    			
    		});
    		$('#dateEnd').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d'
    		});
    		
    		$("#dateStart").click(function(){
    			$("#userSelect").prop("checked", true);
    		});
    		
    		$("#dateEnd").click(function(){
    			$("#userSelect").prop("checked", true);
    		});

    	});
    	
    	function localAreaDetail(dateStart, dateEnd, address){
    		
    		window.open("<c:url value='/analytics/local_deteail.do?dateStart="+dateStart+"&dateEnd="+dateEnd+"&searchKeyword="+address+"'/>", "상세 통계" , "width=1200, height=800, top=100, left=300, scrollbars=no");
    	}
    </script>
    
        <!-- page content -->
        <!-- ============================================================== -->
        <!-- wrapper  -->
        <!-- ============================================================== -->
        <div class="dashboard-wrapper">
            <div class="container-fluid dashboard-content ">
                    <!-- ============================================================== -->
                    <!-- pageheader  -->
                    <!-- ============================================================== -->
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="page-header">
                                <h2 class="pageheader-title"> 지역 별 통계 </h2>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                        	<li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 분석 </a></li>
                                            <li class="breadcrumb-item active" aria-current="page"> 지역 통계 </li>
                                        </ol>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                    	
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="">
                        	<div class="card">
                        		<div class="card-body">
                        			<div class="table-responsive">
										<table class="table table-hover">
			                            	<thead>
												<tr>
													<th> 지역 </th>
													<th> 총 발급 송장 </th>
													<th> 평균 상품 구매 개수 </th>
													<th> 평균 총 구매가 </th>
													<th> 상세보기 </th>
												</tr>
			                              	</thead>
			                              	
			                                <tbody>
			                                	<c:if test="${!empty localList}">
			                                		<c:set var="totalQty" value="0"/>
			                                		<c:set var="totalPrice" value="0"/>
			                                		<c:set var="totalOrder" value="0"/>
			                                		
				                                	<c:forEach var="locallist" items="${localList }">
				                                		<c:set var="totalQty" value="${totalQty + locallist.orAmount}"/>
				                                		<c:set var="totalPrice" value="${totalPrice + locallist.orTotalPrice}"/>
				                                		<c:set var="totalOrder" value="${totalOrder + locallist.totalOrderCount}"/>
				                                		<tr>
				                                			<td>${locallist.orShippingAddress }</td>
				                                			<td>
				                                				<fmt:formatNumber value="${locallist.totalOrderCount }" pattern="#,###" /> 건
				                                			</td>
				                                			<td>
				                                				<fmt:formatNumber value="${locallist.orAmount }" pattern="#,###" /> 개
				                                			</td>
				                                			<td>
				                                				<fmt:formatNumber value="${locallist.orTotalPrice }" pattern="#,###" /> 원
				                                			</td>
				                                			<td>
				                                				<button class="btn btn-success btn-xs" onclick="localAreaDetail('${osVO.dateStart}', '${osVO.dateEnd }', '${locallist.orShippingAddress }')"> 보기 </button>
				                                			</td>
				                                			
				                                		</tr>
				                                	</c:forEach>
				                                		<tr>
				                                			<td> 종 합 </td>
				                                			<td>
				                                				<fmt:formatNumber value="${totalOrder }" pattern="#,###" /> 건
				                                			</td>
				                                			<td>
				                                				<fmt:formatNumber value="${totalQty / 17 }" pattern="#,###" /> 개
				                                			</td>
				                                			<td>
				                                				<fmt:formatNumber value="${totalPrice / 17 }" pattern="#,###" /> 원
				                                			</td>
				                                			<td></td>
				                                		</tr>
			                                	</c:if>
			                                	<c:if test="${empty localList}">
			                                		<tr>
			                                			<td colspan="5"> 조회된 건이 존재하지 않습니다 </td>
			                                		</tr>
			                                		
			                                	</c:if>
			                                </tbody>
			                           </table>
		                        	</div>
                        		</div>
                        	</div>
                        	
                 		</div>
            	 	</div>
        		</div>
        <!-- /page content -->
        <iframe id="excelDownloadIframe" width="0" height="0">
		</iframe> 
        <%@ include file="../../inc/bottom.jsp" %>