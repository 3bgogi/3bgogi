<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %>
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
			
			$(".specialReqBtn").click(function(){
				var orderOrPk = $(this).val();
				
				window.open("/orders/special_request.do?orPk="+orderOrPk, "특별 요청 사항 처리 " , "width=800px, height=620px, top=50px, left=50px, scrollbars=no");
			});
			
			$("#reqFilterKeywordList").click(function(){
				window.open("/order/config/req_filter_keyword_list.do", "필터링 단어 목록 " , "width=600px, height=800px, top=50px, left=50px, scrollbars=no");
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
                            <h2 class="pageheader-title"> 요청 사항 체크  </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문서 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 요구사항 체크 </a></li>
                                        
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
									<a href="<c:url value='/orders/delivery_msg_check.do'/>" class="btn btn-success blinking"> 요구사항 체크 </a>
									<a href="<c:url value='/stock/stk_check.do'/>" class="btn"> 재고 할당 </a> 
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
	                        		<form name="deliveryMsgCheckForm" id="deliveryMsgCheckForm" action="<c:url value="/orders/delivery_msg_check.do"/>" method="get">
	                        			<input type="hidden" name="searchCondition" >
			                        	<input type="hidden" name="currentPage" value="${osVO.currentPage}">	                        		
			                            <div class="email-filters-left">
				                            <div class="btn-group">
				                                <label class="custom-control custom-radio custom-control-inline">
		                                        	<input type="radio" id="todays" name="datePeriod" value="0"
		                                        		<c:if test="${osVO.datePeriod == 0 }">
															 checked="checked"
														</c:if>
		                                        	class="custom-control-input"><span class="custom-control-label"> 오늘 </span>
		                                        </label>
		                                        <label class="custom-control custom-radio custom-control-inline">
		                                        	<input type="radio" id="weeksAgo" name="datePeriod" value="1" 
		                                        		<c:if test="${osVO.datePeriod == 1 }">
															 checked="checked"
														</c:if>
		                                        	class="custom-control-input"><span class="custom-control-label"> 일주일 </span>
		                                        </label>
		                                        <label class="custom-control custom-radio custom-control-inline">
		                                        	<input type="radio" id="userSelect" name="datePeriod" value="2" 
		                                        		<c:if test="${osVO.datePeriod == 2 }">
															 checked="checked"
														</c:if>
		                                        	class="custom-control-input"><span class="custom-control-label"> 임의 </span>
		                                        </label>
				                            </div>
				                            <div class="btn-group">
				                            	<input type="text" class="btn btn-light btn-xs" id="dateStart" name="dateStart" style="width: 8em;" value="${osVO.dateStart }"/> &nbsp;
				                                <input type="text" class="btn btn-light btn-xs" id="dateEnd" name="dateEnd" style="width: 8em;" value="${osVO.dateEnd }"/>
				                            </div>
				                            <div class="btn-group">
				                                <select class="form-control" name="recordCountPerPage">
													<option value="10"
														<c:if test="${osVO.recordCountPerPage == 10 }">
															selected="selected"
														</c:if>
													>10 개씩</option>
													<option value="20"
														<c:if test="${osVO.recordCountPerPage == 20 }">
															selected="selected"
														</c:if>
													>20 개씩</option>
													<option value="30"
														<c:if test="${osVO.recordCountPerPage == 30 }">
															selected="selected"
														</c:if>
													>30 개씩</option>
													<option value="40"
														<c:if test="${osVO.recordCountPerPage == 40 }">
															selected="selected"
														</c:if>
													>40 개씩</option>
													<option value="100"
														<c:if test="${osVO.recordCountPerPage == 100 }">
															selected="selected"
														</c:if>
													>100 개씩</option>
												</select>
				                            </div>
				                            <div class="btn-group">
				                                <select class="form-control" name="delivMsgFlag">
													<option value="0"
														<c:if test="${osVO.delivMsgFlag == 0 }">
															selected="selected"
														</c:if>
													> 배송메세지 있는 건</option>
													<option value="1"
														<c:if test="${osVO.delivMsgFlag == 1 }">
															selected="selected"
														</c:if>
													> 배송메세지 전부 보기</option>
												</select>
				                            </div>
				                            <div class="btn-group">
				                                <select class="form-control" name="specialReq">
													<option value="0"
														<c:if test="${osVO.specialReq == 0 }">
															selected="selected"
														</c:if>
													> 특별 요청 사항 </option>
													<option value="1"
														<c:if test="${osVO.specialReq == 1 }">
															selected="selected"
														</c:if>
													> 특별 요청 없는 건 </option>
													<option value="2"
														<c:if test="${osVO.specialReq == 2 }">
															selected="selected"
														</c:if>
													> 특별 요청 사항 있는 건</option>
												</select>
				                            </div>
				                            <div class="btn-group">
				                                <select class="form-control" name="reservationType">
													<option value="0"
														<c:if test="${osVO.reservationType == 0 }">
															selected="selected"
														</c:if>
													> 예약 없는 건 </option>
													<option value="1"
														<c:if test="${osVO.reservationType == 1 }">
															selected="selected"
														</c:if>
													> 예약 포함 전부 </option>
												</select>
				                            </div>
				                            <div class="btn-group">
				                            	<select class="form-control" name="searchType">
								                    <option value="buyerName" 
								                    	<c:if test="${osVO.searchType == 'buyerName' }">
															 selected="selected"
														</c:if>
								                    >구매자</option>
								                    <option value="receiverName"
								                    	<c:if test="${osVO.searchType == 'receiverName' }">
															 selected="selected"
														</c:if>
								                    >수령인</option>
								                    <option value="buyerNum"
								                    	<c:if test="${osVO.searchType == 'buyerNum' }">
															 selected="selected"
														</c:if>
								                    >구매자 번호</option>
								                    <option value="receiverNum"
								                    	<c:if test="${osVO.searchType == 'receiverNum' }">
															 selected="selected"
														</c:if>
								                    >수령인 번호</option>
								                    <option value="orderNum"
								                    	<c:if test="${osVO.searchType == 'orderNum' }">
															 selected="selected"
														</c:if>
								                    >주문번호</option>
								                    <option value="orderProductNum"
								                    	<c:if test="${osVO.searchType == 'orderProductNum' }">
															 selected="selected"
														</c:if>
								                    >상품주문번호</option>
								                    <option value="matchingProduct"
								                    	<c:if test="${osVO.searchType == 'matchingProduct' }">
															 selected="selected"
														</c:if>
								                    >매칭 상품명</option>
								                    <option value="storeProduct"
								                    	<c:if test="${osVO.searchType == 'storeProduct' }">
															 selected="selected"
														</c:if>
								                    >판매처 상품명</option>
							                    </select>
				                            </div>
				                            <div class="btn-group">
				                            	<input class="form-control" id="searchKeyword" name="searchKeyword" type="text"  placeholder="검색 내용을 입력해주세요" value="${osVO.searchKeyword }">
				                                <button class="btn btn-primary" type="submit"> 검색 </button>
				                            </div>
				                        </div>
	                        		</form>
		                        </div>
	                        </div>
                        </div>
                    </div>
                    <div class="row">
                    	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 
                                	<button class="btn btn-primary btn-xs mb-2" type="button" id="reqFilterKeywordList"> 배송메세지 필터링 단어 목록 </button>
                                </h5>
                                <div class="card-body">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                            	<th scope="col">
			                                        <label class="custom-control custom-checkbox be-select-all">
									                	<input class="custom-control-input chk_all" type="checkbox" name="chk_all" id="or_pk"><span class="custom-control-label"></span>
									                </label>
		                                        </th>
                                            	<th scope="col"> 판매처 </th>
                                                <th scope="col"> 주문자/수령인 </th>
                                                <th scope="col"> 매칭 상품명 / 매칭 상품명 </th>
                                                <th scope="col"> 배송메세지 </th>
                                                <th scope="col"> 특별 요청 사항 </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach var="orderslist" items="${orList }">
                                        		<tr>
                                        			<td scope="row" class="checkTd">
				                                    <label class="custom-control custom-checkbox be-select-all">
										            <input class="custom-control-input chk_all" type="checkbox" name="orPk" value="${orderslist.orPk }">
										            <span class="custom-control-label"></span>
										             </label>
				                                    </td>
                                        			<td>${orderslist.ssName }</td>
	                                                <td>${orderslist.orBuyerName }/${orderslist.orReceiverName }</td>
	                                                <td>
	                                                	<c:forEach var="pmList" items="${orderslist.productMatchingVOList }">
	                                                		<c:forEach var="omList" items="${pmList.optionMatchingVOList }">
	                                                			<c:forEach var="proList" items="${omList.productOptionList }">
	                                                				<p>${proList.productName } [ ${proList.optionName } ]  <fmt:formatNumber value="${orderslist.orAmount * omList.omOptionAmount }" pattern="#,###"/>  개</p>
	                                                			</c:forEach>
	                                                		</c:forEach>
	                                                	</c:forEach>
	                                                </td>
	                                                <td>
	                                                	<c:if test="${!empty rfkList }">
	                                                		<c:set var="loopBreak" value="false"/>
	                                                		<c:set var="listSize" value="${fn:length(rfkList) }"/>
	                                                		<c:set var="listSizeCounting" value="0"/>
	                                                		
	                                                		<c:forEach var="rfklist" items="${rfkList }">
	                                                			<c:set var="listSizeCounting" value="${listSizeCounting+1 }"/>
	                                                			<c:if test="${loopBreak == false }">
	                                                				<c:if test="${fn:contains(orderslist.orDeliveryMessage, rfklist.rfkWord)}">
	                                                					
	                                                					<c:set var="targetWordSize" value="${fn:length(orderslist.orDeliveryMessage) }"/>
	                                                					<c:set var="refWordSize" value="${fn:length(rfklist.rfkWord) }"/>
	                                                					<c:set var="firstSubWordNum" value="${fn:indexOf(orderslist.orDeliveryMessage, rfklist.rfkWord) }"/>
	                                                					<c:set var="middleSubWordNum" value="${firstSubWordNum + refWordSize}"/>
	                                                					<c:set var="lastSubWordNum" value="${targetWordSize - firstSubWordNum - middleSubWordNum }"/>
	                                                					
	                                                					<span>${fn:substring(orderslist.orDeliveryMessage, 0, firstSubWordNum) }</span><span class="text-danger">${fn:substring(orderslist.orDeliveryMessage, firstSubWordNum, middleSubWordNum) }</span><span>${fn:substring(orderslist.orDeliveryMessage, middleSubWordNum, targetWordSize) }</span>
	                                                					
	                                                					<c:set var="loopBreak" value="true"/>
	                                                				</c:if>
	                                                			</c:if>
	                                                			<c:if test="${listSize == listSizeCounting and loopBreak == false}">
	                                                				${orderslist.orDeliveryMessage }
	                                                			</c:if>
	                                                		</c:forEach>
	                                                	</c:if>
	                                                	<c:if test="${empty rfkList }">
	                                                		${orderslist.orDeliveryMessage }
	                                                	</c:if>
	                                                	<c:if test="${orderslist.orRequest != '' }">
	                                                	<br> <span class="text-primary"> 저장된 요청 사항 => ${orderslist.orRequest }</span>
	                                                	
	                                                	</c:if>
	                                                </td>
	                                                <td>
	                                                	<c:if test="${orderslist.orRequest != '' }">
	                                                		<button class="btn btn-brand btn-xs specialReqBtn" value="${orderslist.orPk }">요청사항수정</button>
	                                                	</c:if>
	                                                	<c:if test="${orderslist.orRequest == '' }">
	                                                		<button class="btn btn-success btn-xs specialReqBtn" value="${orderslist.orPk }">요청사항입력</button>
	                                                	</c:if>
	                                                </td>
	                                            </tr>
                                        	</c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
        <!-- /page content -->
        <%@ include file="../inc/bottom.jsp" %>