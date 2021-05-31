<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		/* $(".dataTable td").hover(function(){
    			$(this).parent().children().addClass("selectClass");
    		}, function(){
    			$(this).parent().children().removeClass("selectClass");
    		}); */
    	});
    </script>
    <style type="text/css">
    	.selectClass{
    		background-color: #FFA2A2;
    		color:white;
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
                            <h2 class="pageheader-title"> 재고 할당 부여 </h2>
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
                            <div class="card">
                                <h5 class="card-header">
                                	<c:if test="${osVO == 'outputPosiv' }">
                                		출고 가능 목록
                                	</c:if>
                                	<c:if test="${osVO == 'outputPosivNotRelease' }">
                                		미발송 출고 가능 목록
                                	</c:if>
                                	<c:if test="${osVO == 'outputReserve' }">
                                		예약 목록
                                	</c:if>
                                	<c:if test="${osVO == 'notInv' }">
                                		재고 미할당 목록
                                	</c:if>
                                </h5>
                                <div class="card-body">
                                	<div class="table-responsive">
	                                    <table class="table table-bordered">
	                                        <thead class="bg-light">
	                                            <tr style="text-align: center;">
	                                                <th scope="col"> 고유번호 </th>
	                                                <th scope="col"> 고객정보 </th>
	                                                <th scope="col"> 고유값 </th>
	                                                <th scope="col"> 재고 </th>
	                                                <th scope="col"> 주문번호 </th>
	                                                <th scope="col"> 상품주문번호 </th>
	                                                <th scope="col"> 상품번호 </th>
	                                                <th scope="col"> 상품목록 </th>
	                                            </tr>
	                                        </thead>
	                                        <tbody class="dataTable">
	                                        	<c:set var="rowCounting" value="1"/>
	                                        	<c:set var="backgroundBoolean" value="1"/>
	                                        	<c:forEach var="orlist" items="${stockList }">
	                                        		<c:set var="rowSpans" value="${fn:length(orlist.orVoList)}"/>
	                                        		
	                                        		
	                                        		<c:forEach var="stocklist" items="${orlist.orVoList }">
	                                        			<tr
	                                        			<c:if test="${backgroundBoolean % 2 == 0}">
			                                        		style="background-color:#edeef4;"
			                                        	</c:if>
			                                        	
	                                        			>
	                                        			<c:if test="${rowCounting == 1 }">
	                                        				<td rowspan="${rowSpans }" style="width:270px; text-align: center;">
								                            	${orlist.orSerialSpecialNumber }
								                            </td>
			                                        		<td rowspan="${rowSpans }" style="width:270px; text-align: center;">
								                            	${orlist.orBuyerName } / ${orlist.orBuyerContractNumber1 }<br>
								                                ${orlist.orReceiverName } / ${orlist.orReceiverContractNumber1 }<br>
								                                        			
								                                <c:if test="${empty orlist.orDeliveryInvoiceNumber }">
								                                	[ 송장 입력 X ]
								                                </c:if>
								                                <c:if test="${!empty orlist.orDeliveryInvoiceNumber }">
								                                	우체국택배 : ${orlist.orDeliveryInvoiceNumber }
								                                </c:if>
								                                <br>
								                            </td>
								                            <c:set var="rowCounting" value="${rowCounting+1 }"/>
			                                        	</c:if>
	                                        			<c:if test="${!empty stocklist.productMatchingVOList }">	                                        			
			                                        		<c:forEach var="pmlist" items="${stocklist.productMatchingVOList }">  
			                                        		    
					                                        		<td>${stocklist.orPk }</td>
					                                        		<td>
					                                        			<c:if test="${stocklist.orInvFlag == false }">
					                                        				<button class="btn btn-rounded btn-danger btn-xs"> 재고 할당 X </button>
					                                        			</c:if>
					                                        			<c:if test="${stocklist.orInvFlag == true }">
					                                        				<button class="btn btn-rounded btn-primary btn-xs"> 재고 할당 완료 </button>
					                                        			</c:if>
					                                        		</td>
					                                        		<td>${stocklist.orOrderNumber }</td>
					                                        		<td>${stocklist.orProductOrderNumber }</td>
					                                        		
					                                        		<td><a href="https://smartstore.naver.com/3bgogi/products/${stocklist.orProductNumber }" target="_blank">${stocklist.orProductNumber }</a></td>
					                                        		<td>${stocklist.orProduct } <br> ${stocklist.orProductOption } <hr>
																		<c:if test="${empty pmlist.optionMatchingVOList }">
																			매칭된 옵션이 없습니다.
																		</c:if>
																		<c:if test="${!empty pmlist.optionMatchingVOList }">																		
							                                        		<c:forEach var="opmlist" items="${pmlist.optionMatchingVOList }">
								                                        		<c:forEach var="proOplist" items="${opmlist.productOptionList }">
								                                        			<c:if test="${empty proOplist.optionName }">
								                                        				${proOplist.productName }  <span style="color:red;">[ 매칭된 옵션이 없습니다 ]</span><a href="<c:url value="/order/matching/option_matching.do"/>"> ▶ 옵션매칭  </a> <br>
								                                        			</c:if>
								                                        			<c:if test="${!empty proOplist.optionName }">								                                        			
									                                        			<a href="<c:url value='/products/read/product.do?productPk=${proOplist.productPk }'/>">${proOplist.productName } </a> 
									                                        			[ <a href="<c:url value='/options/read/option.do?optionPk=${proOplist.optionPk }'/>"> ${proOplist.optionName } </a> ]  ${proOplist.cfFk } 개<br>
								                                        			</c:if>
								                                        		</c:forEach>
							                                        		</c:forEach>
																		</c:if>
					                                        		</td>

			                                        		</c:forEach>
	                                        			</c:if>
	                                        			
	                                        			<c:if test="${empty stocklist.productMatchingVOList }">     
					                                        <td>${stocklist.orPk }</td>
	                                        				<td><button class="btn btn-rounded btn-danger btn-xs"> 재고 할당 X </button></td> 
					                                        <td>${stocklist.orOrderNumber }</td>
					                                        <td>${stocklist.orProductOrderNumber }</td>
					                                        <td><a href="https://smartstore.naver.com/3bgogi/products/${stocklist.orProductNumber }" target="_blank">${stocklist.orProductNumber }</a></td>
					                                        <td>  
					                                        	${stocklist.orProduct } <br> ${stocklist.orProductOption } <hr>
					                                        	<span style="color:red;"> 상품이 매칭되지 않았습니다. </span>
					                                        </td>

	                                        			</c:if>
	                                        		</tr>
	                                        		</c:forEach>
		                                        	<c:set var="backgroundBoolean" value="${backgroundBoolean + 1 }"/>
		                                        	<c:set var="rowCounting" value="1"/>
	                                        	</c:forEach>
	                                        </tbody>
	                                    </table>
                                	
                                	</div>
                                </div>
                            </div>
                        </div>
                    </div>
	            </div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>