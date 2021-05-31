<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %>
    <script type="text/javascript">

    </script>
    <style type="text/css">
    	.sending-header{
    	background-color: #71748d; 
    	font-color: #fff;
    	}
    	
    	.sending-header > th{
    	color: #fff !important;
    	
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
                            <h2 class="pageheader-title"> 판매처 발송 결과 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 발송 및 출고</a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 판매처 송장부여 </a></li>
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
                            <div class="card">
                                <div class="card-body">
                                	<div class="table-responsive">
	                                    <table class="table table-bordered" style="word-break: keep-all;">
	                                        <thead>
	                                            <tr class="sending-header text-center">
	                                            	<th scope="col">송장번호</th>
	                                                <th scope="col">구매자 / 수령자</th>
	                                                <th scope="col">연락처</th>
	                                                <th scope="col">주소</th>
	                                                <th scope="col">상품</th>
	                                                <th scope="col">개수</th>
	                                                <th scope="col">발송기한</th>
	                                                <th scope="col"> 발송일 </th>
	                                                
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:if test="${empty sendingList }">
	                                        		<tr>
		                                                <th scope="row" colspan="8" style="text-align: center;">발송값이 없습니다</th>
		                                            </tr>
	                                        	</c:if>
	                                        	<c:if test="${!empty sendingList }">
	                                        		<c:forEach var="sendinglist" items="${sendingList }">
	                                        			<tr>
			                                                <td>
			                                                	${sendinglist.orDeliveryInvoiceNumber }
			                                                </td>
			                                                <td>
			                                                	${sendinglist.orBuyerName } / <br>
			                                                	${sendinglist.orReceiverName }
			                                                </td>
			                                                <td>
			                                                	${sendinglist.orBuyerContractNumber1 } /<br>
			                                                	${sendinglist.orReceiverContractNumber1 }
			                                                </td>
			                                                <td>
			                                                	${sendinglist.orShippingAddress }<br>
			                                                	${sendinglist.orShippingAddressDetail }
			                                                </td>
			                                                <td>
			                                                	${sendinglist.orProduct } <br>
			                                                	${sendinglist.orProductOption }
			                                                </td>
			                                                <td>
			                                                	${sendinglist.orAmount } 개
			                                                </td>
			                                                <td>
			                                                	${sendinglist.orSendingDeadline }
			                                                </td>
			                                                <td>
			                                                	${sendinglist.orSendingDay }
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
                    </div>
            </div>
        <!-- /page content -->
		<iframe id="excelDownloadIframe" width="0" height="0">
		</iframe>
			         
        <%@ include file="../inc/bottom.jsp" %>