<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		$("button[name=stockDetail]").click(function(){
    			var dateStart = $("#dateStart").val();
    			var params = "?dateStart="+dateStart+"&searchType="+$(this).val();
    			
    			location.href="<c:url value='/stock/stock_search_result.do"+params+"'/>";
    		});
    		
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
    		
    	});
    </script>
    
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
                            <h2 class="pageheader-title"> 재고 할당 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item  active" aria-current="page"><a href="javascript:void(0);" class="breadcrumb-link"> 재고 체크 </a></li>
                                        
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
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="card" style="background-color: #efeff6;">
								<div class="card-body">
									<h4> 주문서 진행 단계 </h4>
									<a href="<c:url value='/orders/order_page.do'/>" class="btn btn-success"> 주문서 입력</a>
									<a href="<c:url value='/order/config/search_except_addr_order.do'/>" class="btn btn-success"> 특수 지역 체크  </a>
									<a href="<c:url value="/order/matching/products_matching.do"/>" class="btn btn-success"> 상품 매칭 </a> 
									<a href="<c:url value="/order/matching/option_matching.do"/>" class="btn btn-success"> 옵션 매칭 </a>
									<a href="<c:url value='/config/freebie/apply.do'/>" class="btn btn-success"> 사은품 부여  </a>    
									<a href="<c:url value='/orders/delivery_msg_check.do'/>" class="btn btn-success"> 요구사항 체크 </a>
									<a href="<c:url value='/stock/stk_check.do'/>" class="btn btn-success blinking"> 재고 할당 </a> 
									<a href="<c:url value='/orders/cancled_order_check.do'/>" class="btn"> 취소 주문  </a>
									<a href="<c:url value='/security/epost.do'/>" class="btn"> 송장 부여  </a> 
								</div>
							</div>
						</div>
					</div>
                	<div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        	<div class="card">
	                        	<div class="card-body">
	                        		<form id="stkCheckForm" action="<c:url value='/stock/stk_check.do'/>" method="get">                        		
			                            <div class="email-filters-right">
				                            <div class="btn-group">
				                            	<input type="text" class="btn btn-light" id="dateStart" name="dateStart" style="width: 8em;" value="${osVO.dateStart }"/> &nbsp; 
				                                <input type="text" class="btn btn-light" id="dateEnd" name="dateEnd" style="width: 8em;" value="${osVO.dateEnd }"/>
				                            </div>
				                            <div class="btn-group" style="text-align: right;">
				                                <button class="btn btn-primary" type="submit"> 해당 기간 재고 할당 </button>
				                            </div>
				                        </div>
	                        		</form>
		                        </div>
	                        </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 상품 출고 가능 및 재고 미할당 목록</h5>
                                <div class="card-body">
                                    <table class="table">
                                    	<c:set var="outputPosiv" value="${searchResult[0].outputPosiv == null ? 0 : searchResult[0].outputPosiv }" />
                                    	<c:set var="outputReserve" value="${searchResult[0].outputReserve == null ? 0 : searchResult[0].outputReserve }" />
                                    	<c:set var="notInv" value="${searchResult[0].notInv == null ? 0 : searchResult[0].notInv }" />
                                    	<c:set var="notOutputToday" value="${searchResult[0].notInv == null ? 0 : searchResult[0].notOutputToday }" />
                                    	<c:set var="specialRegion" value="${searchResult[0].specialRegion == null ? 0 : searchResult[0].specialRegion }" />
                                    	<c:set var="depositFlag" value="${searchResult[0].depositFlag == null ? 0 : searchResult[0].depositFlag }" />
                                        <tbody>
                                            <tr>
                                                <td colspan="2">출고가능 ${outputPosiv } 건 (미출고 :  ${notOutputToday } 건)</td>
                                                <td><button class="btn btn-outline-primary btn-block" name="stockDetail" value="outputPosiv">출고가능 목록</button></td>
                                                <td><button class="btn btn-outline-primary btn-block" name="stockDetail" value="outputPosivNotRelease">미출고 목록</button></td>
                                            </tr>
                                            <tr>
                                                <td colspan="2"> 예약 발송  ${outputReserve } 건 </td>
                                                <td colspan="2"><button class="btn btn-outline-brand btn-block" name="stockDetail" value="outputReserve"> 예약 목록 </button></td>
                                            </tr>
                                            <tr>
                                                <td colspan="2"> 재고 미할당  ${notInv } 건 </td>
                                                <td colspan="2"><button class="btn btn-outline-danger btn-block" name="stockDetail" value="notInv"> 재고 미할당 목록 </button></td>
                                            </tr>
                                            <tr>
                                                <td colspan="2"> 특수지역  ${specialRegion } 건 </td>
                                                <td colspan="2"><button class="btn btn-outline-danger btn-block" name="stockDetail" value="specialRegion"> 특수지역 목록 </button></td>
                                            </tr>
                                            <tr>
                                                <td colspan="2"> 입금대기  ${depositFlag } 건 </td>
                                                <td colspan="2"><button class="btn btn-outline-danger btn-block" name="stockDetail" value="depositOrder"> 입금대기 목록 </button></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
					</div>
	            </div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>