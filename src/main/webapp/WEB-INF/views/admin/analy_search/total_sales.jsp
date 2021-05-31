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
                                <h2 class="pageheader-title"> 매출 </h2>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                        	<li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 분석 </a></li>
                                            <li class="breadcrumb-item active" aria-current="page"> 선택 조회 </li>
                                        </ol>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ============================================================== -->
                    <!-- end pageheader  -->
                    <!-- ============================================================== -->
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="checkboxradio">
                                <form id="analyForms" class="card" action="<c:url value='/analytics/total_sales.do'/>" method="GET">
                                	<div class="card-body">
                                		<h4> 검색 날짜 설정 </h4>
                                		<div class="btn-group">
				                        	<input type="text" class="btn btn-light btn-xs" id="dateStart" name="dateStart" style="width: 8em;" value="${osVO.dateStart }"/> &nbsp;
				                            <input type="text" class="btn btn-light btn-xs" id="dateEnd" name="dateEnd" style="width: 8em;" value="${osVO.dateEnd }"/>                               
				                        </div>
				                        <button class="btn btn-primary btn-xs" type="submit">검색하기</button>
                                	</div>
                                </form>
                            </div>
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="checkboxradio">
                                <div class="card">
                                <h5 class="card-header"> 판매처 별 매출 조회 ( 환불 건은 자동으로 차감되어 계산됨) </h5>
                                <div class="card-body">
                                    <table class="table table-hover">
                                    	
                                    	<thead>
											<tr>
												<th>판매처명</th>
												<th>송장 수</th>
												<th>고객 부담 배송비</th>
												<th>고객 부담 배송할인액</th>
												<th>매출</th>
											</tr>
                                    	</thead>
                                        <tbody>
                                        	<c:if test="${!empty salesList }">
                                        		<c:set var="sendingCount" value="0"/>
                                        		<c:set var="delivPriceCount" value="0"/>
                                        		<c:set var="delivDiscPriceCount" value="0"/>
                                        		<c:set var="totalPriceCount" value="0"/>
                                        		<c:forEach var="slist" items="${salesList }">                                        		
		                                            <tr>
		                                            	<td>${slist.ssName }</td>
		                                            	<td>
		                                            		<fmt:formatNumber value="${slist.pmFk }" pattern="#,###"/> 장
		                                            		<c:set var="sendingCount" value="${sendingCount + slist.pmFk }"/>
		                                            	</td>
		                                            	<td>
		                                            		<fmt:formatNumber value="${slist.orDeliveryPrice }" pattern="#,###"/> 원
		                                            		<c:set var="delivPriceCount" value="${delivPriceCount + slist.orDeliveryPrice}"/>
		                                            	</td>
		                                            	<td>
		                                            		<fmt:formatNumber value="${slist.orDeliveryDiscountPrice }" pattern="#,###"/> 원
		                                            		<c:set var="delivDiscPriceCount" value="${delivDiscPriceCount + slist.orDeliveryDiscountPrice}"/>
		                                            	</td>
		                                            	<td>
		                                            		<fmt:formatNumber value="${slist.orTotalPrice }" pattern="#,###"/> 원
		                                            		<c:set var="totalPriceCount" value="${totalPriceCount + slist.orTotalPrice}"/>
		                                            	</td>
		                                            </tr>
		                                            
                                        		</c:forEach>
                                        		<tr>
                                        			<td> 총 합 </td>
                                        			<td>
                                        				<fmt:formatNumber value="${sendingCount }" pattern="#,###"/> 장
                                        			</td>
                                        			<td>
                                        				<fmt:formatNumber value="${delivPriceCount }" pattern="#,###"/> 원
                                        			</td>
                                        			<td>
                                        				<fmt:formatNumber value="${delivDiscPriceCount }" pattern="#,###"/> 원
                                        			</td>
                                        			
                                        			<c:set var="totalSales" value="${(totalPriceCount + delivPriceCount - delivDiscPriceCount)}"/>
                                        			<td>
                                        				<fmt:formatNumber value="${totalSales}" pattern="#,###"/> 원
                                        			</td>
                                        			
                                        		</tr>             	
                                        	</c:if>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            </div>
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="checkboxradio">
                                <div class="card">
                                <h5 class="card-header"> 판매처 별 취소내역 ( 해당 데이터는 매출에 기록되지 않음 - 참고용 ) </h5>
                                <div class="card-body">
                                    <table class="table table-hover">
                                    	<thead>
											<tr>
												<th>판매처명</th>
												<th>송장 수</th>
												<th>고객 부담 배송비</th>
												<th>고객 부담 배송할인액</th>
												<th>매출</th>
											</tr>
                                    	</thead>
                                        <tbody>
                                        	<c:if test="${!empty canlcedList }">
                                        		<c:set var="sendingCount" value="0"/>
                                        		<c:set var="delivPriceCount" value="0"/>
                                        		<c:set var="delivDiscPriceCount" value="0"/>
                                        		<c:set var="totalPriceCount" value="0"/>
                                        		<c:forEach var="slist" items="${canlcedList }">                                        		
		                                            <tr>
		                                            	<td>${slist.ssName }</td>
		                                            	<td>
		                                            		<fmt:formatNumber value="${slist.pmFk }" pattern="#,###"/> 장
		                                            		<c:set var="sendingCount" value="${sendingCount + slist.pmFk }"/>
		                                            	</td>
		                                            	<td>
		                                            		<fmt:formatNumber value="${slist.orDeliveryPrice }" pattern="#,###"/> 원
		                                            		<c:set var="delivPriceCount" value="${delivPriceCount + slist.orDeliveryPrice}"/>
		                                            	</td>
		                                            	<td>
		                                            		<fmt:formatNumber value="${slist.orDeliveryDiscountPrice }" pattern="#,###"/> 원
		                                            		<c:set var="delivDiscPriceCount" value="${delivDiscPriceCount + slist.orDeliveryDiscountPrice}"/>
		                                            	</td>
		                                            	<td>
		                                            		<fmt:formatNumber value="${slist.orTotalPrice }" pattern="#,###"/> 원
		                                            		<c:set var="totalPriceCount" value="${totalPriceCount + slist.orTotalPrice}"/>
		                                            	</td>
		                                            </tr>
                                        		</c:forEach>
                                        		
                                        		<tr>
                                        			<td> 총 합 </td>
                                        			<td>
                                        				<fmt:formatNumber value="${sendingCount }" pattern="#,###"/> 장
                                        			</td>
                                        			<td>
                                        				<fmt:formatNumber value="${delivPriceCount }" pattern="#,###"/> 원
                                        			</td>
                                        			<td>
                                        				<fmt:formatNumber value="${delivDiscPriceCount }" pattern="#,###"/> 원
                                        			</td>
                                        			
                                        			<c:set var="totalSales" value="${(totalPriceCount + delivPriceCount - delivDiscPriceCount)}"/>
                                        			
                                        			<td>
                                        				<fmt:formatNumber value="${totalSales}" pattern="#,###"/> 원
                                        			</td>
                                        			
                                        		</tr>             	
                                        	</c:if>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            </div>
                    </div>
                </div>
        <!-- /page content -->
        <iframe id="excelDownloadIframe" width="0" height="0">
		</iframe>  
        <%@ include file="../../inc/bottom.jsp" %>