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
    	
    		$("#analyForms").submit(function(){
    			
    			var ssList = $("#ssList").val();
    			var groupList = $("input[name=groupList]:checked").length;
    			
    			if(ssList == ''){
    				alert("조회할 판매처를 선택해주세요");
    				return false;
    			}
    			if(groupList == 0){
    				alert("조회항목을 선택해주세요");
    				return false;
    			}
    			
    		});
    		
    		$("#excelResultDown").click(function(){

    				if(confirm("검색 결과를 엑셀파일로 다운로드하시겠습니까?")){

    					/* var ssList = document.createElement("input");
    					ssList.name="ssList";
    					ssList.type="hidden";
    					ssList.value=$("#select").val();
    	    			
    	    			var groupList = document.createElement("input");
    	    			groupList.name="groupList";
    	    			groupList.type="hidden";
    	    			groupList.value=$("input[name=groupList]").val();
    	    			
    	    			var totalList = document.createElement("input");
    	    			totalList.name="totalList";
    	    			totalList.type="hidden";
    	    			totalList.value=$("input[name=totalList]").val();
    	    			
    	    			
    	    			
    	    			
    	    			excelDownloadForm.append(ssList);
    	    			excelDownloadForm.append(groupList);
    	    			excelDownloadForm.append(totalList); */
    	    			
    	    			var divs = document.createElement("div");
    	    			
    	    			var excelDownloadForm =  document.createElement("form");
    	    			excelDownloadForm.action="/analytics/analy_search_excel_down.do";
    	    			excelDownloadForm.method="POST";

    	    			var analyForms = $("#analyForms").children().clone(true).appendTo(excelDownloadForm);
    	    			
    	    			/* excelDownloadForm.append(analyForms); */
    	    			divs.append(excelDownloadForm);
    	    			
    	    			
    	    			$("#excelDownloadIframe").append(divs);
    	    		
    	    			excelDownloadForm.submit();
    	    			$("#excelDownloadIframe").html("");
    	    		}
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
                                <h2 class="pageheader-title"> 통계 선택 조회 </h2>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                        	<li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 데이터 관리 </a></li>
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 통계 </a></li>
                                            <li class="breadcrumb-item active" aria-current="page"> 통계 선택 조회 </li>
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
                                <form id="analyForms" class="card" action="<c:url value='/analytics/analy_search_list.do'/>" method="post">
                                	<div class="card-body">
                                		<h4> 조회 범위 설정 </h4>
                                		<div class="btn-group">
				                        	<select class="selectpicker" multiple data-actions-box="true" data-width="200px" id="ssList" name="ssList">
								            	<c:if test="${empty ssList }">
								                	<option value="0">등록된 판매처가 없습니다</option>
								                </c:if>
								                <c:if test="${!empty ssList }">
								                	<c:forEach var="sslist" items="${ssList }">
								                    	<option value="${sslist.ssPk }"
								                        	<c:if test="${fn:contains(osVO.ssList,sslist.ssPk ) }">
								                        		selected="selected"
								                        	</c:if>
								                        >${sslist.ssName }</option>
								                    </c:forEach>
								                </c:if>
							                </select>                                
				                        </div>
                                    	<div class="btn-group">
				                                <select class="form-control form-control-sm" name="dateType">
								                    <option value="or_regdate"
								                    	<c:if test="${osVO.dateType == 'or_regdate' }">
								                    		selected="selected"
								                    	</c:if>
								                    >주문 등록일</option>
								                    <option value="or_settlement_day"
								                    	<c:if test="${osVO.dateType == 'or_settlement_day' }">
								                    		selected="selected"
								                    	</c:if>
								                    >주문 결제일</option>
								                    <option value="or_sending_day"
								                    	<c:if test="${osVO.dateType == 'or_sending_day' }">
								                    		selected="selected"
								                    	</c:if>
								                    >주문 발송일</option>
								                    <option value="or_sending_deadline"
								                    	<c:if test="${osVO.dateType == 'or_sending_deadline' }">
								                    		selected="selected"
								                    	</c:if>
								                    >주문 발송기한</option>
							                    </select>
				                            </div>
				                            &nbsp;
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
				                            	<select class="form-control" name="searchType">
				                            		<option value="storeProduct"
								                    	<c:if test="${osVO.searchType == 'storeProduct' }">
															 selected="selected"
														</c:if>
								                    >판매처 상품/옵션 명</option>
								                    
								                    <option value="matchingProduct"
								                    	<c:if test="${osVO.searchType == 'matchingProduct' }">
															 selected="selected"
														</c:if>
								                    >매칭 상품명</option>
								                    <option value="matchingOption"
								                    	<c:if test="${osVO.searchType == 'matchingOption' }">
															 selected="selected"
														</c:if>
								                    >매칭 옵션명</option>
							                    </select>
				                            </div>
				                            <div class="btn-group">
				                            	<input class="form-control" id="searchKeyword" name="searchKeyword" type="text"  placeholder="검색 내용을 입력해주세요" value="${osVO.searchKeyword }">
				                                <button class="btn btn-primary" type="submit"> 검색 </button>
				                            </div>
                                	</div>
                                    <div class="card-body border-top">
                                            <h4>그룹화 목록</h4>
                                            <input value="${osVO.groupList }" name="results" type="hidden">
                                            <label class="custom-control custom-checkbox custom-control-inline">
                                            
                                                <input type="checkbox" name="groupList" value="inne.ss_name" class="custom-control-input"
                                                	<c:if test="${fn:contains(osVO.groupList, 'inne.ss_name') }">
                                                		checked="checked"
                                                	</c:if>
                                                ><span class="custom-control-label"> 판매처 </span>
                                            </label>
                                            <label class="custom-control custom-checkbox custom-control-inline">
                                                <input type="checkbox" name="groupList" value="inne.or_buyer_id" class="custom-control-input"
                                                	<c:if test="${fn:contains(osVO.groupList, 'inne.or_buyer_id') }">
                                                		checked="checked"
                                                	</c:if>
                                                ><span class="custom-control-label"> 구매자아이디 </span>
                                            </label>
                                            <label class="custom-control custom-checkbox custom-control-inline">
                                                <input type="checkbox" name="groupList" value="inne.or_buyer_name" class="custom-control-input"
                                                	<c:if test="${fn:contains(osVO.groupList, 'inne.or_buyer_name') }">
                                                		checked="checked"
                                                	</c:if>
                                                ><span class="custom-control-label"> 구매자명</span>
                                            </label>
                                            <label class="custom-control custom-checkbox custom-control-inline">
                                                <input type="checkbox" name="groupList" value="inne.or_buyer_contract_number1" class="custom-control-input"
                                                	<c:if test="${fn:contains(osVO.groupList, 'inne.or_buyer_contract_number1') }">
                                                		checked="checked"
                                                	</c:if>
                                                ><span class="custom-control-label"> 구매자 연락처</span>
                                            </label>
                                            <label class="custom-control custom-checkbox custom-control-inline">
                                                <input type="checkbox" name="groupList" value="inne.or_product" class="custom-control-input"
                                                	<c:if test="${fn:contains(osVO.groupList, 'inne.or_product') }">
                                                		checked="checked"
                                                	</c:if>
                                                ><span class="custom-control-label"> 판매처 상품명 </span>
                                            </label>
                                            <label class="custom-control custom-checkbox custom-control-inline">
                                                <input type="checkbox" name="groupList" value="inne.or_product_option" class="custom-control-input"
                                                	<c:if test="${fn:contains(osVO.groupList, 'inne.or_product_option') }">
                                                		checked="checked"
                                                	</c:if>
                                                ><span class="custom-control-label"> 판매처 옵션명 </span>
                                            </label>
                                            
                                            <label class="custom-control custom-checkbox custom-control-inline">
                                                <input type="checkbox" name="groupList" value="inne.or_order_number" class="custom-control-input"
                                                	<c:if test="${fn:contains(osVO.groupList, 'inne.or_order_number') }">
                                                		checked="checked"
                                                	</c:if>
                                                ><span class="custom-control-label" > 주문번호 </span>
                                            </label>
                                            <label class="custom-control custom-checkbox custom-control-inline">
                                                <input type="checkbox" name="groupList" value="inne.or_product_order_number" class="custom-control-input"
                                                	<c:if test="${fn:contains(osVO.groupList, 'inne.or_product_order_number') }">
                                                		checked="checked"
                                                	</c:if>
                                                ><span class="custom-control-label" > 상품주문번호 </span>
                                            </label>
                                            <label class="custom-control custom-checkbox custom-control-inline">
                                                <input type="checkbox" name="groupList" value="inne.or_payment_type" class="custom-control-input"
                                                	<c:if test="${fn:contains(osVO.groupList, 'inne.or_payment_type') }">
                                                		checked="checked"
                                                	</c:if>
                                                ><span class="custom-control-label"> 결제수단 </span>
                                            </label>
                                            <label class="custom-control custom-checkbox custom-control-inline">
                                                <input type="checkbox" name="groupList" value="inne.or_inflow_route" class="custom-control-input"
                                                	<c:if test="${fn:contains(osVO.groupList, 'inne.or_inflow_route') }">
                                                		checked="checked"
                                                	</c:if>
                                                ><span class="custom-control-label"> 유입경로 </span>
                                            </label>
                                            <label class="custom-control custom-checkbox custom-control-inline">
                                                <input type="checkbox" name="groupList" value="inne.or_payment_position_type" class="custom-control-input"
                                                	<c:if test="${fn:contains(osVO.groupList, 'inne.or_payment_position_type') }">
                                                		checked="checked"
                                                	</c:if>
                                                ><span class="custom-control-label"> 결제위치 </span>
                                            </label>
                                    </div>
                                    <div class="card-body border-top">
                                            <h4> 합산 목록 </h4>
                                            <label class="custom-control custom-checkbox custom-control-inline">
                                                <input type="checkbox" name="totalList" value="SUM(inne.or_amount) AS or_amount" class="custom-control-input"
                                                	<c:if test="${fn:contains(osVO.totalList, 'SUM(inne.or_amount) AS or_amount') }">
                                                		checked="checked"
                                                	</c:if>
                                                ><span class="custom-control-label"> 총 수량 </span>
                                            </label>
                                            <label class="custom-control custom-checkbox custom-control-inline">
                                                <input type="checkbox" name="totalList" value="SUM(inne.or_total_price) AS or_total_price" class="custom-control-input"
                                                	<c:if test="${fn:contains(osVO.totalList, 'SUM(inne.or_total_price) AS or_total_price') }">
                                                		checked="checked"
                                                	</c:if>
                                                ><span class="custom-control-label"> 총 판매가 </span>
                                            </label>
                                            <label class="custom-control custom-checkbox custom-control-inline">
                                                <input type="checkbox" name="totalList" value="SUM(inne.or_total_cost) AS or_total_cost" class="custom-control-input"
                                                	<c:if test="${fn:contains(osVO.totalList, 'SUM(inne.or_total_cost) AS or_total_cost') }">
                                                		checked="checked"
                                                	</c:if>
                                                ><span class="custom-control-label"> 총 원가 </span>
                                            </label>

                                    </div>
                                </form>
                            </div>
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="checkboxradio">
                                <div class="card">
                                <h5 class="card-header"> 조회 목록 </h5>
                                <div class="card-body">
                                	<c:if test="${!empty osVO.groupList }">                                	
	                                	<button class="btn btn-success btn-xs mb-3" id="excelResultDown">결과 엑셀 다운로드</button>
                                	</c:if>
                                    <table class="table table-hover">
                                    	
                                    	<thead>
											<c:if test="${!empty osVO.groupList }">
												<c:set var="headers" value="${fn:split(osVO.groupList, ',') }"/>
												<c:if test="${!empty osVO.totalList }">
													<c:set var="bodys" value="${fn:split(osVO.totalList, ',') }"/>
													
													<c:forEach var="headerList" items="${headers }">
														<c:if test="${headerList == 'inne.ss_name' }">
															<th>판매처명</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_buyer_id' }">
															<th>구매자 아이디</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_buyer_name' }">
															<th>구매자명</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_buyer_contract_number1' }">
															<th>구매자 연락처</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_product' }">
															<th>판매처 상품명</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_product_option' }">
															<th>판매처 옵션명</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_order_number' }">
															<th>주문번호</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_product_order_number' }">
															<th>상품주문번호</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_payment_type' }">
															<th>결제수단</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_inflow_route' }">
															<th>유입경로</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_payment_position_type' }">
															<th>결제위치</th>
														</c:if>
													</c:forEach>
													
													<c:forEach var="bodyList" items="${bodys }">
														<c:if test="${bodyList == 'SUM(inne.or_amount) AS or_amount' }">
															<th>총 수량</th>
														</c:if>
														
														<c:if test="${bodyList == 'SUM(inne.or_total_price) AS or_total_price' }">
															<th>총 판매액</th>
														</c:if>
														<c:if test="${bodyList == 'SUM(inne.or_total_cost) AS or_total_cost' }">
															<th>총 원가</th>
														</c:if>
													</c:forEach>
												</c:if>
												<c:if test="${empty osVO.totalList }">
													<c:forEach var="headerList" items="${headers }">
														<c:if test="${headerList == 'inne.ss_name' }">
															<th>판매처명</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_buyer_id' }">
															<th>구매자 아이디</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_buyer_name' }">
															<th>구매자명</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_buyer_contract_number1' }">
															<th>구매자 연락처</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_product' }">
															<th>판매처 상품명</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_product_option' }">
															<th>판매처 옵션명</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_order_number' }">
															<th>주문번호</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_product_order_number' }">
															<th>상품주문번호</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_payment_type' }">
															<th>결제수단</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_inflow_route' }">
															<th>유입경로</th>
														</c:if>
														<c:if test="${headerList == 'inne.or_payment_position_type' }">
															<th>결제위치</th>
														</c:if>
													</c:forEach>
												</c:if>
											
											</c:if>
                                    	</thead>
                                        <tbody>
                                        	<c:if test="${!empty analyList }">
                                        		<c:forEach var="map" items="${analyList }" varStatus="status">                                        		
		                                            <tr>
		                                            	<c:forEach var="item" items="${map }">
		                                            		<c:if test="${!empty item.value }">
		                                            			<c:if test="${item.key == 'or_amount' or item.key == 'or_total_price'}">
		                                            				<th>
		                                            					<fmt:formatNumber value="${item.value }" pattern="#,###"/>
		                                            				</th>
		                                            			</c:if>
		                                            			<c:if test="${item.key != 'or_amount' and item.key != 'or_total_price'}">
		                                            				<th>${item.value }</th>
		                                            			</c:if>
		                                            			
		                                            		</c:if>
		                                            		<c:if test="${empty item.value }">
		                                            			<th> - </th>
		                                            		</c:if>                                     	
			                                                
		                                            	</c:forEach>
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
        <!-- /page content -->
        <iframe id="excelDownloadIframe" width="0" height="0">
		</iframe>  
        <%@ include file="../../inc/bottom.jsp" %>