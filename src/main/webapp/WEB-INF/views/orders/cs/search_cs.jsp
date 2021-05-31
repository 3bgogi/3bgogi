<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
		$(function(){
			$("#excelResultDown").click(function(){

				if(confirm("검색 결과를 엑셀파일로 다운로드하시겠습니까?")){

	    			var divs = document.createElement("div");
	    			
	    			var excelDownloadForm =  document.createElement("form");
	    			excelDownloadForm.action="/orders/search/customer_orders_excel.do";
	    			excelDownloadForm.method="POST";

	    			var searchCustomerInfo = $("#searchCustomerInfo").children().clone(true).appendTo(excelDownloadForm);
	    			
	    			/* excelDownloadForm.append(analyForms); */
	    			divs.append(excelDownloadForm);
	    			
	    			$("#excelDownloadIframe").append(divs);
	    		
	    			excelDownloadForm.submit();
	    			$("#excelDownloadIframe").html("");
	    		}
			});
			
			$("input[name=datePeriod]").change(function(){
				datePeriod = $(this).val();
				
				if(datePeriod != 2){
					$("#dateStart").prop("readonly","readonly");
					$("#dateEnd").prop("readonly","readonly");
					
				}else{
					$("#dateStart").prop("readonly","");
					$("#dateEnd").prop("readonly","");
					
				}
				
				
			});
			
			$("#dateStart, #dateEnd").click(function(){
				$("#dateStart").prop("readonly","");
				$("#dateEnd").prop("readonly","");
				
			});
			
			$("#searchKeyword").focus();
			
			$(".table-3bgogi-hover  > tr").click(function(){
    			if($(this).next().hasClass('show')){
    				$(this).css("background-color","#fff");
    				
    			}else{
    				$(this).css("background-color","#FFC6C6");

    			}
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
    		
    		$("#dateStart").click(function(){
    			$("#userSelect").prop("checked", true);
    		});
    		
    		$("#dateEnd").click(function(){
    			$("#userSelect").prop("checked", true);
    		});
    		

			$("#delivResultBtn").click(function(){
    			window.open("<c:url value='/security/epost/deliv_date.do'/>", "송장 결과" , "width=700, height=800, top=100, left=300, scrollbars=no");

    		});
			
        	//발송처리양식
        	$("#delivOutputSaved").click(function(){
        		$("select[name=dateType]").val("or_sending_deadline");
        		$("#weeksAgo").prop("checked","checked");
        		$("select[name=outputPosiv]").val("1");
        		$("select[name=deliveryInvoiceFlag]").val("1");
        		$("select[name=reservationType]").val("2");
        		$("select[name=recordCountPerPage]").val("200");
        		
        		
        	});
        	
        	//주문서,라벨지 양식
			$("#productOutputSaved").click(function(){
				$("select[name=dateType]").val("or_sending_deadline");
        		$("#weeksAgo").prop("checked","checked");
        		$("select[name=outputPosiv]").val("1");
        		$("select[name=deliveryInvoiceFlag]").val("1");
        		$("select[name=reservationType]").val("2");
        		$("select[name=recordCountPerPage]").val("1000");
        		
        	});
        	
        	
		});
		
		function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#searchCustomerInfo").submit();
		}
		
		function searchDeliveryState(urls, deliveryInvoiceNumber){
			window.open(urls+deliveryInvoiceNumber, "송장 조회" , "width=700, height=800, top=100, left=300, scrollbars=no");
		}
    </script>
    <style type="text/css">
    	.table-3bgogi-hover > tr :hover{
    		background: #FFC6C6;
    	}
    	
    </style>
    
        <!-- page content -->
        <!-- ============================================================== -->
        <!-- wrapper  -->
        <!-- ============================================================== -->
        <div class="dashboard-wrapper">
            <div class="container-fluid dashboard-content">
                    <!-- ============================================================== -->
                    <!-- pageheader -->
                    <!-- ============================================================== -->
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="page-header">
                                <h3 class="mb-2"> 주문 고객 상세 검색 </h3>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> C / S</a></li>
	                                        <li class="breadcrumb-item  active" aria-current="page"><a href="javascript:void(0);" class="breadcrumb-link"> 고객 검색 </a></li>
	                                        
                                        </ol>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ============================================================== -->
                    <!-- end pageheader -->
                    <!-- ============================================================== -->
                    <!-- ============================================================== -->
                    <!-- content -->
                    <!-- ============================================================== -->
                    	<form class="row" id="searchCustomerInfo" action="<c:url value='/orders/search/customer_orders.do'/>" method="get">
                    		<input type="hidden" name="searchCondition" >
                        	<input type="hidden" name="currentPage" value="${osVO.currentPage}">
                        	<input type="hidden" name="excelOrFk" value="0">
                        <!-- ============================================================== -->
                        <!-- search bar  -->
                        <!-- ============================================================== -->
		                        <div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
		                        	<div class="card">
		                        		<div class="card-body">
		                        			<label for="" style="font-size: 13px;"> 날짜 관련 </label>
		                        			<div class="form-row">
			                        			<div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
			                        				<select class="form-control form-control-sm  mb-2" name="dateType">
									                    <option value="or_regdate"
									                    	<c:if test="${osVO.dateType == 'or_regdate' }">
									                    		selected="selected"
									                    	</c:if>
									                    >주문 등록일 기준</option>
									                    <option value="or_settlement_day"
									                    	<c:if test="${osVO.dateType == 'or_settlement_day' }">
									                    		selected="selected"
									                    	</c:if>
									                    >주문 결제일 기준</option>
									                    <option value="or_sending_day"
									                    	<c:if test="${osVO.dateType == 'or_sending_day' }">
									                    		selected="selected"
									                    	</c:if>
									                    >주문 발송일 기준</option>
									                    <option value="or_sending_deadline"
									                    	<c:if test="${osVO.dateType == 'or_sending_deadline' }">
									                    		selected="selected"
									                    	</c:if>
									                    >주문 발송기한 기준</option>
								                    </select>
			                        			</div>
			                        			<div class="col-xl-4 col-lg-4 col-md-12 col-sm-12 col-12">
			                        				<label class="custom-control custom-radio custom-control-inline  mb-2">
			                                        	<input type="radio" id="todays" name="datePeriod" value="0"
			                                        		<c:if test="${osVO.datePeriod == 0 }">
																 checked="checked"
															</c:if>
			                                        	class="custom-control-input"><span class="custom-control-label"> 오늘 </span>
			                                        </label>
			                                        <label class="custom-control custom-radio custom-control-inline  mb-2">
			                                        	<input type="radio" id="weeksAgo" name="datePeriod" value="1" 
			                                        		<c:if test="${osVO.datePeriod == 1 }">
																 checked="checked"
															</c:if>
			                                        	class="custom-control-input"><span class="custom-control-label"> 일주일 </span>
			                                        </label>
			                                        <label class="custom-control custom-radio custom-control-inline  mb-2">
			                                        	<input type="radio" id="userSelect" name="datePeriod" value="2" 
			                                        		<c:if test="${osVO.datePeriod == 2 }">
																 checked="checked"
															</c:if>
			                                        	class="custom-control-input"><span class="custom-control-label"> 임의 </span>
			                                        </label>
			                        			</div>
			                        			
			                        			<div class="col-xl-5 col-lg-5 col-md-12 col-sm-12 col-12">
			                        				<div class="form-row">			                        				
														<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
															<input type="text" class="form-control form-control-sm mb-2" id="dateStart" name="dateStart" style="text-align: center;"
																<c:if test="${osVO.datePeriod != 2 }">
																	 readonly="readonly"
																</c:if>  
															value="${osVO.dateStart }"/>
														</div>
														
														<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
															<input type="text" class="form-control form-control-sm mb-2" id="dateEnd" name="dateEnd" style="text-align: center;"
																<c:if test="${osVO.datePeriod != 2 }">
																	 readonly="readonly"
																</c:if>  
															value="${osVO.dateEnd }"/>
														</div>
			                        				</div>
			                        			</div>
		                        			</div>
		                        		</div>
		                        	</div>
		                        </div>
		                        
		                        <div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
		                        	<div class="card">
		                        		<div class="card-body">
		                        			<label for="" style="font-size: 13px;"> 
		                        				검색 관련
		                        				<button type="button" class="fas fa-search" data-toggle="tooltip" data-placement="top" title="구매자/수령자통합과 주문번호는 ,를 구분자로 다중검색이 가능하나 주문번호는 정확하게 일치하는 값만 검색이 됩니다"></button> 
		                        			</label>
		                        			<div class="form-row">
			                        			<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
			                        				<div class="form-row">
			                        					<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
			                        						<select class="form-control form-control-sm mb-2" name="ssPk">
											                    <option value="0">전체 판매처</option>
											                    <c:forEach var="sslist" items="${ssList }">						                    	
												                    <option value="${sslist.ssPk }"
												                    	<c:if test="${osVO.ssPk == sslist.ssPk }">
												                    		selected="selected"
												                    	</c:if>
												                    > ${sslist.ssName }</option>
											                    </c:forEach>
											                    <option value="-1">개인주문</option>
										                    </select>
			                        					</div>
			                        					<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
			                        						<select class="form-control form-control-sm mb-2" name="searchType">
							                            		<option value="orderNames" 
											                    	<c:if test="${osVO.searchType == 'orderNames' }">
																		 selected="selected"
																	</c:if>
											                    >구매자 / 수령자 통합</option>
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
											                    <option value="anotherName"
											                    	<c:if test="${osVO.searchType == 'anotherName' }">
																		 selected="selected"
																	</c:if>
											                    >송장표기명</option>
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
											                    <option value="receiverAddress"
											                    	<c:if test="${osVO.searchType == 'receiverAddress' }">
																		 selected="selected"
																	</c:if>
											                    >주소</option>
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
											                    <option value="matchingOption"
											                    	<c:if test="${osVO.searchType == 'matchingOption' }">
																		 selected="selected"
																	</c:if>
											                    >매칭 옵션명</option>
											                    <option value="storeProduct"
											                    	<c:if test="${osVO.searchType == 'storeProduct' }">
																		 selected="selected"
																	</c:if>
											                    >판매처 상품명(옵션명)</option>
											                    <option value="invoiceNum"
											                    	<c:if test="${osVO.searchType == 'invoiceNum' }">
																		 selected="selected"
																	</c:if>
											                    >송장번호</option>
											                    <option value="deliveryMessage"
											                    	<c:if test="${osVO.searchType == 'deliveryMessage' }">
																		 selected="selected"
																	</c:if>
											                    >요청사항</option>
										                    </select>
			                        					</div>
			                        				</div>
			                        			</div>
			                        			<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
			                        				<div class="form-row">
			                        					<div class="col-xl-8 col-lg-8 col-md-12 col-sm-12 col-12">
			                        						<div class="input-group">			                        						
				                        						<input class="form-control form-control-sm mb-2" id="searchKeyword" name="searchKeyword" type="text"  placeholder="검색 내용을 입력해주세요" value="${osVO.searchKeyword }">
				                        						<div class="input-group-append">				                        						
							                                		<button class="btn btn-primary btn-xs mb-2" type="submit"> 검색 </button>
							                                		
				                        						</div>
			                        						</div>
			                        					</div>
			                        					<div class="col-xl-4 col-lg-4 col-md-12 col-sm-12 col-12">
			                        						<select class="form-control form-control-sm mb-2" name="recordCountPerPage">
																<option value="10"
																	<c:if test="${osVO.recordCountPerPage == 10 }">
																		selected="selected"
																	</c:if>
																>10 개씩 보기</option>
																<option value="20"
																	<c:if test="${osVO.recordCountPerPage == 20 }">
																		selected="selected"
																	</c:if>
																>20 개씩 보기</option>
																<option value="100"
																	<c:if test="${osVO.recordCountPerPage == 100 }">
																		selected="selected"
																	</c:if>
																>100 개씩 보기</option>
																<option value="200"
																	<c:if test="${osVO.recordCountPerPage == 200 }">
																		selected="selected"
																	</c:if>
																>200 개씩 보기</option>
																<option value="500"
																	<c:if test="${osVO.recordCountPerPage == 500 }">
																		selected="selected"
																	</c:if>
																>500 개씩 보기</option>
																<option value="1000"
																	<c:if test="${osVO.recordCountPerPage == 1000 }">
																		selected="selected"
																	</c:if>
																>1000 개씩 보기</option>
																<option value="2000"
																	<c:if test="${osVO.recordCountPerPage == 2000 }">
																		selected="selected"
																	</c:if>
																>2000 개씩 보기</option>
																<option value="3000"
																	<c:if test="${osVO.recordCountPerPage == 3000 }">
																		selected="selected"
																	</c:if>
																>3000 개씩 보기</option>
																		<option value="4000"
																	<c:if test="${osVO.recordCountPerPage == 4000 }">
																		selected="selected"
																	</c:if>
																>4000 개씩 보기</option>
															</select>
			                        					</div>
			                        				</div>
			                        			</div>
		                        			</div>
		                        		</div>
		                        	</div>
		                        </div>
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                            	<div class="card-body" style="padding-bottom: 5px;">
                            		<div class="row">
                                    	<div class="col-xl-5 col-lg-5 col-md-12 col-sm-12 col-12">
                                        	<label for="" style="font-size: 13px;"> 발송 관련 </label>
                                        	<div class="form-inline">
	                                            <select class="form-control form-control-sm mb-2 
				                            		<c:if test="${osVO.outputPosiv != 0 }">
				                            			selected-values
				                            		</c:if>
				                            	" name="outputPosiv">
				                            		<option value="0"
				                            			<c:if test="${osVO.outputPosiv == 0 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>출고 여부</option>
				                            		<option value="1"
				                            			<c:if test="${osVO.outputPosiv == 1 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>출고 가능</option>
				                            		<option value="2"
				                            			<c:if test="${osVO.outputPosiv == 2 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>출고 불가</option>
				                            	</select>&nbsp;
				                            	<select class="form-control form-control-sm mb-2 
				                            		<c:if test="${osVO.deliveryInvoiceFlag != 0 }">
				                            			selected-values
				                            				
				                            		</c:if>
				                            	" name="deliveryInvoiceFlag">
				                            		<option value="0"
				                            			<c:if test="${osVO.deliveryInvoiceFlag == 0 }">
															selected="selected"
														</c:if>
				                            		>송장 출력 여부</option>
				                            		<option value="1"
				                            			<c:if test="${osVO.deliveryInvoiceFlag == 1 }">
															selected="selected"
														</c:if>
				                            		>송장 있는 건</option>
				                            		<option value="2"
				                            			<c:if test="${osVO.deliveryInvoiceFlag == 2 }">
															selected="selected"
														</c:if>
				                            		>송장 없는 건</option>
				                            	</select>&nbsp;
				                            	<select class="form-control form-control-sm mb-2 
				                            		<c:if test="${osVO.reservationType != 0 }">
				                            			selected-values
				                            				
				                            		</c:if>
				                            	" name="reservationType" >
				                            		<option value="0"
				                            			<c:if test="${osVO.reservationType == 0 }">
															selected="selected"
														</c:if>
				                            		> 예약 여부 </option>
				                            		<option value="1"
				                            			<c:if test="${osVO.reservationType == 1 }">
															selected="selected"
														</c:if>
				                            		> 예약 건</option>
				                            		<option value="2"
				                            			<c:if test="${osVO.reservationType == 2 }">
															selected="selected"
														</c:if>
				                            		> 예약아닌 건</option>
				                            	</select>&nbsp;
				                            	<select class="form-control form-control-sm mb-2 
						                        	<c:if test="${osVO.receiveType != 0 }">
				                            			selected-values
				                            				
				                            		</c:if>
						                        " name="receiveType"  >
							                        <option value="0"
							                        	<c:if test="${osVO.receiveType == 0}">
															selected="selected"
														</c:if>
							                        > 상품수령타입 </option>
													<option value="1"
														<c:if test="${osVO.receiveType == 1}">
															selected="selected"
														</c:if>
													>택배</option>
													<option value="2"
														<c:if test="${osVO.receiveType == 2}">
															selected="selected"
														</c:if>
													>퀵서비스</option>
													<option value="3"
														<c:if test="${osVO.receiveType == 3}">
															selected="selected"
														</c:if>
													>방문수령</option>
													<option value="4"
														<c:if test="${osVO.receiveType == 3}">
															selected="selected"
														</c:if>
													>제주익일특급</option>
												</select>&nbsp;
												<select class="form-control form-control-sm mb-2 
						                        	<c:if test="${osVO.edtFk != 0 }">
				                            			selected-values
				                            				
				                            		</c:if>
						                        " name="edtFk"  >
							                        <option value="0"> 배송타입 </option>
													<option value="1">우체국택배</option>
													<option value="3">새벽배송(프레시솔루션)</option>
												</select>
                                        	</div>
                                        </div>
                                        
                                    	<div class="col-xl-7 col-lg-7 col-md-12 col-sm-12 col-12">
                                        	<label for="" style="font-size: 13px;"> 주문서 상태 </label>
                                        	<div class="form-inline">
                                        		<select class="form-control form-control-sm mb-2 
				                            		<c:if test="${osVO.depositFlag != 0 }">
				                            			selected-values
				                            		</c:if>
				                            	" name="depositFlag" >
				                            		<option value="0"
				                            			<c:if test="${osVO.depositFlag == 0 }">
															selected="selected"
														</c:if>
				                            		> 입금 여부 </option>
				                            		<option value="1"
				                            			<c:if test="${osVO.depositFlag == 1 }">
															selected="selected"
														</c:if>
				                            		> 입금 완료</option>
				                            		<option value="2"
				                            			<c:if test="${osVO.depositFlag == 2 }">
															selected="selected"
														</c:if>
				                            		> 입금 대기</option>
				                            	</select>&nbsp;
				                            	<select class="form-control form-control-sm mb-2
				                            		<c:if test="${osVO.refundFlag != 0 }">
				                            			selected-values
				                            				
				                            		</c:if>
				                            	" name="refundFlag" >
				                            		<option value="0"
				                            			<c:if test="${osVO.refundFlag == 0 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>환불 여부</option>
				                            		<option value="1"
				                            			<c:if test="${osVO.refundFlag == 1 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>환불만</option>
				                            		<option value="2"
				                            			<c:if test="${osVO.refundFlag == 2 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>환불 아닌</option>
				                            	</select>&nbsp;
				                            	<select class="form-control form-control-sm mb-2 
				                            		<c:if test="${osVO.cancledFlag != 0 }">
				                            			selected-values
				                            				
				                            		</c:if>
				                            	" name="cancledFlag" >
				                            		<option value="0"
				                            			<c:if test="${osVO.cancledFlag == 0 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>주문취소 여부</option>
				                            		<option value="1"
				                            			<c:if test="${osVO.cancledFlag == 1 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>미취소 주문</option>
				                            		<option value="2"
				                            			<c:if test="${osVO.cancledFlag == 2 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>취소 포함</option>
				                            		<option value="3"
				                            			<c:if test="${osVO.cancledFlag == 3 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>취소만</option>
				                            	</select>&nbsp;
				                            	<select class="form-control form-control-sm mb-2 
				                            		<c:if test="${osVO.excelFlag != 0 }">
				                            			selected-values
				                            				
				                            		</c:if>
				                            	" name="excelFlag" >
				                            		<option value="0"
				                            			<c:if test="${osVO.excelFlag == 0 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>대량주소 여부</option>
				                            		<option value="1"
				                            			<c:if test="${osVO.excelFlag == 1 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>대량주소 원본파일</option>
				                            		<option value="2"
				                            			<c:if test="${osVO.excelFlag == 2 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>대량주소 대상파일</option>
				                            		<option value="3"
				                            			<c:if test="${osVO.excelFlag == 3 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>대량주소 원본,대상파일</option>
				                            		<option value="4"
				                            			<c:if test="${osVO.excelFlag == 4 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>대량주소 원본파일 제외</option>
				                            	</select>&nbsp;
				                            	<select class="form-control form-control-sm mb-2 
				                            		<c:if test="${osVO.specialRegionFlag != 0 }">
				                            			selected-values
				                            				
				                            		</c:if>
				                            	" name="specialRegionFlag"
				                            		
				                            	>
				                            		<option value="0"
				                            			<c:if test="${osVO.specialRegionFlag == 0 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>특수지역 여부</option>
				                            		<option value="1"
				                            			<c:if test="${osVO.specialRegionFlag == 1 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>특수지역</option>
				                            		<option value="2"
				                            			<c:if test="${osVO.specialRegionFlag == 2 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>특수지역 체크 안된</option>
				                            		<option value="3"
				                            			<c:if test="${osVO.specialRegionFlag == 3 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>특수지역 체크 된</option>
				                            		<option value="4"
				                            			<c:if test="${osVO.specialRegionFlag == 4 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>특수지역이 아닌</option>
				                            	</select>
				                            	
                                        	</div>
                                        </div>
                                    </div>
                                    <div class="row">
                                    	<div class="col-xl-5 col-lg-5 col-md-12 col-sm-12 col-12">
                                        	<label for="" style="font-size: 13px;"> 주문입력차수, 송장생성차수 </label>
                                        	<div class="form-inline">
                                        		<c:set var="insertingCountNum" value="1"/>
                                        		<select class="form-control form-control-sm mb-2" name="insertingCount">
							                        <option value=""> 주문입력차수 </option>
													<c:forEach var="isoList" items="${insertStoreOrderList }">
														<fmt:formatDate var='isoRegdate' value='${isoList.orRegdate }' pattern='yyyy-MM-dd HH:mm:ss'/>
														<option value="<fmt:formatDate value='${isoList.orRegdate }' pattern='yyyy-MM-dd HH:mm:ss'/>"
															<c:if test="${osVO.insertingCount == isoRegdate}">
																selected="selected"
															</c:if>
														>${insertingCountNum } 차 <fmt:formatDate value='${isoList.orRegdate }' pattern='yyyy-MM-dd HH:mm:ss'/></option>
														<c:set var="insertingCountNum" value="${insertingCountNum + 1 }"/>
													</c:forEach>
												</select>&nbsp;
												<c:set  var="invoiceCountNum" value="1"/>
												<select class="selectpicker form-control-sm mb-2" multiple data-actions-box="true" 
													data-width="300px" id="createInvoiceNumList" name="createInvoiceNumList"  title="송장 입력 전체 차수">
									            	<c:if test="${empty invoiceNum }">
									                	<option disabled> 금일 생성된 송장이 없습니다 </option>
									                </c:if>
									                <c:if test="${!empty invoiceNum }">
									                	<option disabled>송장생성차수 </option>
									                	<c:forEach var="invoiceNumList" items="${invoiceNum }">
															<option value="${invoiceNumList.orInvoiceNumDate }"
															
																<c:if test="${!empty osVO.createInvoiceNumList }">
																
																	<c:forEach var="invoiceNums" items="${osVO.createInvoiceNumList }">				
																													
																		<c:if test="${invoiceNumList.orInvoiceNumDate  == invoiceNums }">
																			selected="selected"
																		</c:if>
																		
																	</c:forEach>
																</c:if>
															>송장 ${invoiceCountNum} 차 ${invoiceNumList.orDeliveryCompany} ${invoiceNumList.orInvoiceNumDate }</option>
															
															<c:set  var="invoiceCountNum" value="${invoiceCountNum + 1 }"/>
														</c:forEach>
									                </c:if>
								                </select>
                                        	</div>
                                        </div>
                                        
                                        <div class="col-xl-7 col-lg-7 col-md-12 col-sm-12 col-12">
                                        	<label for="" style="font-size: 13px;"> 추가 검색 </label>
                                        	<div class="form-inline">
                                        		<select class="form-control form-control-sm" name="searchAddType">
				                            		<option value="0"
				                            			<c:if test="${osVO.searchAddType == 0 }">
				                            				selected="selected"
				                            			</c:if>
				                            		> 합계 검색 </option>
				                            		<option value="1"
				                            			<c:if test="${osVO.searchAddType == 1 }">
				                            				selected="selected"
				                            			</c:if>
				                            		> 제외 검색 </option>
				                            	</select>&nbsp;
				                            	<select class="form-control form-control-sm" name="searchAddKind">
				                            		<option value="orderNames" 
								                    	<c:if test="${osVO.searchAddKind == 'orderNames' }">
															 selected="selected"
														</c:if>
								                    >구매자 / 수령자 통합</option>
								                    <option value="buyerName" 
								                    	<c:if test="${osVO.searchAddKind == 'buyerName' }">
															 selected="selected"
														</c:if>
								                    >구매자</option>
								                    <option value="receiverName"
								                    	<c:if test="${osVO.searchAddKind == 'receiverName' }">
															 selected="selected"
														</c:if>
								                    >수령인</option>
								                    <option value="buyerNum"
								                    	<c:if test="${osVO.searchAddKind == 'buyerNum' }">
															 selected="selected"
														</c:if>
								                    >구매자 번호</option>
								                    <option value="receiverNum"
								                    	<c:if test="${osVO.searchAddKind == 'receiverNum' }">
															 selected="selected"
														</c:if>
								                    >수령인 번호</option>
								                    <option value="orderNum"
								                    	<c:if test="${osVO.searchAddKind == 'orderNum' }">
															 selected="selected"
														</c:if>
								                    >주문번호</option>
								                    <option value="receiverAddress"
								                    	<c:if test="${osVO.searchAddKind == 'receiverAddress' }">
															 selected="selected"
														</c:if>
								                    >주소</option>
								                    <option value="orderProductNum"
								                    	<c:if test="${osVO.searchAddKind == 'orderProductNum' }">
															 selected="selected"
														</c:if>
								                    >상품주문번호</option>
								                    <option value="matchingProduct"
								                    	<c:if test="${osVO.searchAddKind == 'matchingProduct' }">
															 selected="selected"
														</c:if>
								                    >매칭 상품명</option>
								                    <option value="matchingOption"
								                    	<c:if test="${osVO.searchAddKind == 'matchingOption' }">
															 selected="selected"
														</c:if>
								                    >옵션 옵션명</option>
								                    <option value="storeProduct"
								                    	<c:if test="${osVO.searchAddKind == 'storeProduct' }">
															 selected="selected"
														</c:if>
								                    >판매처 상품명(옵션명)</option>
								                    <option value="invoiceNum"
								                    	<c:if test="${osVO.searchAddKind == 'invoiceNum' }">
															 selected="selected"
														</c:if>
								                    >송장번호</option>
								                    <option value="deliveryMessage"
								                    	<c:if test="${osVO.searchAddKind == 'deliveryMessage' }">
															 selected="selected"
														</c:if>
								                    >요청사항</option>
							                    </select>&nbsp;
							                    <input class="form-control form-control-sm" id="searchAddKeyword" name="searchAddKeyword" type="text"  placeholder="추가 검색어 입력" value="${osVO.searchAddKeyword }">
                                        	</div>
                                        </div>
                                    </div>
                            	</div>
                            	<div class="card-body" style="padding-top: 0px;">
                            		<div class="btn-group">
				                    	<button class="btn btn-success btn-xs mb-2" id="delivOutputSaved" type="button" style="margin-right:5px;"> 발송처리 양식 </button>
				                    	<button class="btn btn-success btn-xs mb-2" id="productOutputSaved" type="button"> 주문서, 라벨지 양식 </button>
				                    </div> 
                            	</div>
                            </div>
                        </div>
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            	<div class="card">
                            		<div class="form-inline" style="display: table;">
                            			<div class="form-group" style="display: table-cell; text-align: left">  
	                                		<h5 class="card-header"> 주문서 검색 목록 ( 총 : ${osVO.totalRecord } 개 )</h5>
	                                	</div>
                            		</div>
	                                <div class="card-body">
	                                	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mb-4">
	                                		<div class="btn btn-warning btn-xs mb-2">
				                                <button class="" data-toggle="dropdown" type="button"> 선택 재출력 사항 <span class="caret"></span></button>
				                                <div class="dropdown-menu dropdown-menu-right" role="menu">
				                                
				                                	<a id="reprintingDelivInvoiceBtn" class="dropdown-item" href="#"> 송장 재출력 </a>
				                                	<a id="orderDelivInvoiceBtn" class="dropdown-item" href="#"> 주문서 재출력 </a>
				                                	<a id="labelDelivInvoiceBtn" class="dropdown-item" href="#"> 라벨지 재출력 </a>
				                                	
				                                    <!-- <div class="dropdown-divider"></div>
				                                    <a class="dropdown-item searchType" href="#">입고 무게 내림</a>
				                                    <a class="dropdown-item searchType" href="#">입고 무게 올림</a> -->
				                                </div>
				                            </div>
				                            
	                                		<button class="btn btn-success btn-xs mb-2" id="excelResultDown" type="button"> 검색 결과 엑셀 다운로드 </button>
	                                		<button class="btn btn-brand btn-xs mb-2" id="delivResultBtn" type="button"> 발송 결과 보기 </button>
	                                		<button class="btn btn-success btn-xs mb-2 createNewOrder" type="button" id="createNewOrder"> 새주문생성 </button>
	                                		<button class="btn btn-success btn-xs mb-2 createOrderByOrderInfo" type="button" id="createOrderByOrderInfo"> 기존정보로 새주문생성</button>
	                                		<button class="btn btn-primary btn-xs mb-2" type="button" id="addProductButton"> 상품추가 </button>
	                                		<button class="btn btn-primary btn-xs mb-2" type="button" id="aligoSmsBtn"> 문자 보내기 </button>
	                                		<button class="btn btn-primary btn-xs mb-2" type="button" id="changeSendingDeadlineBtn"> 발송일 변경 </button>
	                                		<button class="btn btn-primary btn-xs mb-2" type="button" id="combineOrderButton"> 주문서 합치기 및 정보변경</button>
	                                		<button class="btn btn-primary btn-xs mb-2" type="button" id="pickUpServiceBtn"> 수령방식변경 </button>
	                                		<button class="btn btn-primary btn-xs mb-2" type="button" id="devideOrderButton"> 주문서 나누기 </button>
	                                		<button class="btn btn-danger btn-xs mb-2" type="button" id="deleteDelivButton"> 송장 삭제 </button>
	                                		<button class="btn btn-primary btn-xs mb-2" type="button" id="outputBtn"> 발송 하기 </button>
	                                		<button class="btn btn-danger btn-xs mb-2" type="button" id="outputCancledBtn"> 발송 취소 처리 </button>
	                                		&nbsp;
	                                		&nbsp;
	                                		<button class="btn btn-danger btn-xs mb-2" type="button" id="deleteOrderButton" style="text-align: right;"> 삭제하기 </button>
	                                		
	                                	</div>
	                                	<div class="table-responsive">
		                                    <table class="table table-hover table-3bgogi-hover " style="font-size: 12px; word-break: keep-all; white-space: nowrap;">
		                                        <thead class="bg-light">
		                                            <tr>
		                                                <th scope="col">
		                                                	<label class="custom-control custom-checkbox be-select-all">
								                                <input class="custom-control-input chk_all" type="checkbox" name="chk_all" id="orSeiralSpecialNumberAllSelect"><span class="custom-control-label"></span>
								                            </label>
		                                                </th>
		                                                <th scope="col">ID</th>
		                                                <th scope="col">발송기한</th>
		                                                <th scope="col">판매처</th>
		                                                <th scope="col">구매자(표기명)/수령인</th>
		                                                <th scope="col"><i class="fas fa-phone mr-2  text-danger"></i>연락처</th>
		                                                <th scope="col"><i class="fa fa-map-marker-alt mr-2  text-primary"></i>주소</th>
		                                                <th scope="col">주문건</th>
		                                                <th scope="col">상품개수</th>
		                                                <th scope="col">금액</th>
		                                                <th scope="col" style="text-align: center;">송장번호</th>
		                                                <th scope="col">발송상태</th>
		                                            </tr>
		                                        </thead>
		                                        <tbody>
		                                        	<c:if test="${empty orderList }">
			                                        	<tr style="text-align: center;">
			                                        		<td colspan="11"> 검색된 결과가 없습니다 </td>
			                                        	</tr>
		                                        	</c:if>
		                                        	<c:if test="${!empty orderList }">
		                                        		<c:set var="now" value="<%=new java.util.Date()%>" />
		                                        		<c:forEach var="orderlist" items="${orderList }">
		                                        		
				                                            <tr
				                                            	<c:if test="${orderlist.orExcelDivCount > 0 }">
				                                            		style='background-color:#FFC6C6;'
				                                            	</c:if>
				                                            >
				                                                <td scope="row" class="checkTd">
				                                                	<label class="custom-control custom-checkbox be-select-all">
										                                <input class="custom-control-input chk_all" type="checkbox" name="orSerialSpecialNumberList" 
										                                	
										                                	<c:if test="${empty orderlist.orDeliveryInvoiceNumber and empty orderlist.orSendingDay }">
										                                		data-deliv-weiting="1" 
										                                	</c:if>
										                                	
										                                	<c:if test="${!empty orderlist.orDeliveryInvoiceNumber and empty orderlist.orSendingDay }">
										                                		data-deliv="1" data-deliv-num="${orderlist.orDeliveryInvoiceNumber }"
										                                	</c:if>
										                                	<c:if test="${!empty orderlist.orDeliveryInvoiceNumber and !empty orderlist.orSendingDay }">
										                                		data-output="1"
										                                	</c:if>
										                                	
										                                	data-buyer-name="${orderlist.orBuyerName }"
										                                	data-buyer-contract-number="${orderlist.orBuyerContractNumber1 }"
										                                value="${orderlist.orSerialSpecialNumber }"><span class="custom-control-label"></span>
										                            </label>
				                                                </td>
				                                               
				                                                <td>${orderlist.orBuyerId == null ? ' - ' :  orderlist.orBuyerId}</td>
				                                                <td>
				                                                	<c:if test="${empty orderlist.orSendingDeadline }">
				                                                		-
				                                                	</c:if>
				                                                	<c:if test="${!empty orderlist.orSendingDeadline }">
				                                                		<fmt:formatDate value="${orderlist.orSendingDeadline }" pattern="yyyy-MM-dd"/>
				                                                	</c:if>
				                                                </td>
				                                                <td>
				                                                	${orderlist.ssName }
				                                                </td>
				                                                <td  class="orderDetails" data-serial-num="${orderlist.orSerialSpecialNumber }" data-toggle="modal" data-target="#customerOrderModal">${orderlist.orBuyerName }(${orderlist.orBuyerAnotherName ==  null ? ' - ' :  orderlist.orBuyerAnotherName })/<br>
				                                                	${orderlist.orReceiverName }
				                                                </td>
				                                                <td  class="orderDetails" data-serial-num="${orderlist.orSerialSpecialNumber }" data-toggle="modal" data-target="#customerOrderModal">${orderlist.orBuyerContractNumber1 }/<br>
				                                                	${orderlist.orReceiverContractNumber1 }
				                                                </td>
				                                                <td  class="orderDetails" data-serial-num="${orderlist.orSerialSpecialNumber }" data-toggle="modal" data-target="#customerOrderModal">
			                                                       	${orderlist.orShippingAddress } ${orderlist.orShippingAddressDetail }  
			                                                       	<c:if test="${orderlist.orDepositFlag == true }">
			                                                       		<br>
			                                                       		 <span class="text-danger">- 해당 주문건은 입금이 확인되지 않았습니다 -</span>
			                                                       	</c:if>
			                                                    </td>
			                                                    <td>
			                                                    	${orderlist.totalOrderCount } 건
			                                                    </td>
			                                                    <td>총 ${orderlist.orAmount + orderlist.orCancledQty }개
			                                                    	<c:if test="${orderlist.orCancledQty > 0 }">
			                                                    		<br> <span class="text-danger">취소  ${orderlist.orCancledQty } 개 </span>
			                                                    	</c:if>
			                                                    	<c:if test="${orderlist.orRefunds > 0 }">
			                                                    		<br> <span class="text-danger">환불  ${orderlist.orRefunds } 개 </span>
			                                                    	</c:if>
			                                                    </td>
			                                                    <td>
			                                                    	<fmt:formatNumber value="${orderlist.orTotalPrice }" pattern="#,###"/> 원 
			                                                    	
			                                                    </td>
			                                                    <c:if test="${orderlist.orRecType > 0 }">
			                                                    	<c:if test="${orderlist.orDepositFlag == true }">
				                                                    	<td colspan="2" style="text-align: center;">
				                                                    		<button  type="button" class="btn btn-rounded btn-success btn-xs" value="${orderlist.orSerialSpecialNumber }" name="depositBtn">입금완료처리</button>
				                                                    	</td>
				                                                    </c:if>
				                                                    <c:if test="${orderlist.orDepositFlag == false }">
					                                                    <td style="text-align: center;">
					                                                    	<c:if test="${empty orderlist.orDeliveryInvoiceNumber }">
					                                                    		<c:if test="${orderlist.orRecType == 1 }">
					                                                    			- 퀵서비스 예정 -
					                                                    		</c:if>
					                                                    		<c:if test="${orderlist.orRecType == 2 }">
					                                                    			- 방문수령 예정 -
					                                                    		</c:if>
					                                                    	</c:if>
					                                                    	<c:if test="${!empty orderlist.orDeliveryInvoiceNumber }">
					                                                    		${orderlist.orDeliveryInvoiceNumber}<br>
					                                                    		<c:if test="${orderlist.orRecType == 1 }">
					                                                    			- 퀵서비스 -
					                                                    		</c:if>
					                                                    		<c:if test="${orderlist.orRecType == 2 }">
					                                                    			- 방문수령 -
					                                                    		</c:if>
					                                                    	</c:if>
					                                                    </td>
					                                                    <td>
					                                                    	<c:if test="${empty orderlist.orSendingDay  }">
					                                                    		-
					                                                    	</c:if>
					                                                    	<c:if test="${!empty orderlist.orSendingDay  }">
					                                                    		<fmt:formatDate value="${orderlist.orSendingDay }" pattern="yyyy-MM-dd"/><br>
					                                                    		<fmt:formatDate value="${orderlist.orSendingDay }" pattern="HH:mm:ss"/>
					                                                    	</c:if>
					                                                    </td>
				                                                    </c:if>
			                                                    </c:if>
			                                                    
			                                                    <c:if test="${orderlist.orRecType == 0 }">
			                                                    	<c:if test="${orderlist.orDepositFlag == true }">
				                                                    	<td colspan="2" style="text-align: center;">
				                                                    		<button  type="button" class="btn btn-rounded btn-success btn-xs" value="${orderlist.orSerialSpecialNumber }" name="depositBtn">입금완료처리</button>
				                                                    	</td>
				                                                    </c:if>
				                                                    <c:if test="${orderlist.orDepositFlag == false }">
					                                                    <td style="text-align: center;">
					                                                    	<c:if test="${empty orderlist.orDeliveryInvoiceNumber }">
					                                                    		-
					                                                    	</c:if>
					                                                    	<c:if test="${!empty orderlist.orDeliveryInvoiceNumber }">
					                                                    		<c:forEach var="edtlist" items="${edtList }">
					                                                    			<c:if test="${orderlist.edtFk == edtlist.edtPk }">
							                                                    		<a onclick="searchDeliveryState('${edtlist.edtUrl }','${orderlist.orDeliveryInvoiceNumber}')" href="javascript:void(0)">
							                                                    			${orderlist.orDeliveryInvoiceNumber }<br>
							                                                    			${edtlist.edtType }
							                                                    		</a>
					                                                    			</c:if>
					                                                    		</c:forEach>
					                                                    	</c:if>
					                                                    </td>
					                                                    <td>
					                                                    	<c:if test="${empty orderlist.orSendingDay  }">
					                                                    		-
					                                                    	</c:if>
					                                                    	<c:if test="${!empty orderlist.orSendingDay  }">
					                                                    		<fmt:formatDate value="${orderlist.orSendingDay }" pattern="yyyy-MM-dd"/><br>
					                                                    		<fmt:formatDate value="${orderlist.orSendingDay }" pattern="HH:mm:ss"/>
					                                                    	</c:if>
					                                                    </td>
				                                                    </c:if>
			                                                    </c:if>
			                                                    
			                                                    
				                                            </tr>
		                                        		</c:forEach>
		                                        	</c:if>
		                                        </tbody>
		                                    </table>
	                                	</div>
	                                </div>
	                            </div>
                            </div>
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
								<nav aria-label="Page navigation" style="text-align: center;" >
									<ul class="pagination" style="display: table; margin-left: auto; margin-right: auto;">
									
										<c:if test="${osVO.firstPage>1 }">
											<li class="page-item" style="float:left; padding-top:5px;"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${osVO.firstPage-1})">«</a></li>
										</c:if>
										<c:forEach var="i" step="1" end="${osVO.lastPage}" begin="${osVO.firstPage }">
											<li class="page-item
												<c:if test="${osVO.currentPage == i }">
													active
												</c:if>
											"  style="float:left; padding-top:5px;"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${i})">${i }</a></li>
										</c:forEach>
										<c:if test="${osVO.lastPage < osVO.totalPage}">
											<li class="page-item" style="float:left; padding-top:5px;"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${osVO.lastPage+1})">»</a></li>
										</c:if>
									</ul>
								</nav>
							</div>
                    	</form>
                    </div>
                    <iframe id="csSearchIframe" width="0" height="0" style="display: none;">
                    	
                    	
					</iframe> 
        <!-- /page content -->
        <!-- <script type="text/javascript">
        	$(function(){
        		
        		
        		/* $(".dashboard-content *").attr("style", "font-size: 8px !important"); */
        	});
        </script> -->
        
        <!-- 고객 상세 사항 -->
	<div class="modal fade" id="customerOrderModal" tabindex="-1"
		role="dialog" aria-labelledby="customerOrderModalLabel"
		aria-hidden="true">
		<div class="modal-dialog" role="document"
			style=" max-width: 90%; width: 90%; font-size: 10px;">
			<div class="modal-content">
				<div class="modal-header">
					<a href="#" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</a>
				</div>
				<div class="modal-body">
					<div class="row">
						<!-- ============================================================== -->
						<!-- profile -->
						<!-- ============================================================== -->
						<div class="col-xl-3 col-lg-3 col-md-5 col-sm-12 col-12">
							<!-- ============================================================== -->
							<!-- card profile -->
							<!-- ============================================================== -->
							<div class="card">
								<div class="card-body">
									<div class="text-center">
										<h4 class="font-24 mb-0" id="orderDetailCustomerName" style="font-size: 18px;">전기찬</h4>
										<h4 class="font-24 mb-0" id="orderDetailReceiverName" style="font-size: 18px;">전기찬</h4>
									</div>
								</div>
								<div class="card-body border-top">
									<h3 class="font-16">배송지 정보</h3>
									<div class="">
										<ul class="list-unstyled mb-0" style="font-size: 12px;">
											<li class="mb-2" id="orderDetailCustomerPhone"><i class="fas fa-fw fa-phone mr-2"></i>010-9350-3632</li>
											<li class="mb-0" id="orderDetailShippingAddress"><i class="fas fa-home mr-2"></i>인천시 효서로 388 3층 하나로축산</li>
										</ul>
									</div>
								</div>
								<div class="card-body border-top">
									<h3 class="font-16">주문 정보</h3>
									<div class="">
										<ul class="list-unstyled mb-0" style="font-size: 12px;">
											<li class="mb-2" id="orderDetailSettlementDay"></li>
											<li class="mb-2" id="orderDetailInflowRoute"></li>
											<li class="mb-2" id="orderDetailDeliveryPrice"></li>
											<br>
											
											<li class="mb-2" id="orderDetailRecType" style="display: none; font-size: 16px;"></li>
											
											<li class="mb-2" id="orderDetailRecMemo" style="display: none;"></li>
											<li class="mb-2" id="orderDetailRecStoragePlace" style="display: none;"></li>
											
										</ul>
									</div>
								</div>

							</div>
							<!-- ============================================================== -->
							<!-- end card profile -->
							<!-- ============================================================== -->
						</div>
						<!-- ============================================================== -->
						<!-- end profile -->
						<!-- ============================================================== -->
						<!-- ============================================================== -->
						<!-- campaign data -->
						<!-- ============================================================== -->
						<div class="col-xl-9 col-lg-9 col-md-7 col-sm-12 col-12">
							<!-- ============================================================== -->
							<!-- campaign tab one -->
							<!-- ============================================================== -->
							<div class="influence-profile-content pills-regular">
								<ul class="nav nav-pills mb-1 nav-justified" id="pills-tab"
									role="tablist">
									<li class="nav-item"><a class="nav-link active" id="pills-campaign-tab" data-toggle="pill" href="#pills-campaign" role="tab" aria-controls="pills-campaign" aria-selected="true"> 주문 정보 </a></li>
									<li class="nav-item"><a class="nav-link" id="order_cs-tab" data-toggle="pill" href="#order_cs" role="tab" aria-controls="order_cs" aria-selected="false"> cs 내역 </a></li>
								</ul>
								<div class="tab-content" id="pills-tabContent">
									<div class="tab-pane fade show active" id="pills-campaign"
										role="tabpanel" aria-labelledby="pills-campaign-tab" style="">
										<div class="row">
											<div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="card">
													<div class="card-body">
														<p>총 주문 건 수</p>
														<h3 class="mb-1" id="orderDetailOrderQuantity"></h3>
													</div>
												</div>
											</div>
											<div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="card">
													<div class="card-body">
														<p>총 상품 건 수</p>
														<h3 class="mb-1" id="orderDetailProductQuantity"></h3>
													</div>
												</div>
											</div>
											<div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12">
												<div class="card">
													<div class="card-body">
														<p>총 주문 금액</p>
														<h3 class="mb-1" id="orderDetailTotalPrice"></h3>
													</div>
												</div>
											</div>
										</div>
										<div class="section-block">
											<button class="btn btn-primary btn-xs" id="orderHistoryBtn">작업 기록 확인</button>
											<button class="btn btn-primary btn-xs" id="SelfdevisionOrderBtn">상품 나누기</button>
											<button class="btn btn-danger btn-xs" id="deleteOrderTargetingDeleteBtn"> 주문 삭제 </button>
											<button class="btn btn-primary btn-xs" id="changeProductBtn"> 상품 변경 </button>
											<button class="btn btn-primary btn-xs" id="cancleOrderPart"> 주문 취소 </button>
											<button class="btn btn-primary btn-xs" id="refundOrderBtn"> 환불 처리 </button>
											<button class="btn btn-brand btn-xs" id="specialReqBtn"> 특별 요청 사항 </button>
											<button class="btn btn-success btn-xs" id="multiMatchingDivBtn"> 복수 매칭 나누기 </button>
											<button class="btn btn-primary btn-xs" id="excelGiftSetBtn"> 엑셀 주소지 입력 </button>
										</div>
										<!-- 상품 정보란 -->
										<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="orderProductDetailHTML" style="height: 550px; overflow-y: auto;border: 2px solid #e6e6f2; padding-top: 20px;">
										</div>
									</div>
									
									<!-- cs 현황쪽 -->
									<div class="tab-pane fade" id="order_cs" role="tabpanel" aria-labelledby="order_cs-tab">
										<div class="card">
											<div class="card-body">
												<form class="form-group" id="orderRecordForm">
													<div class="input-group mb-3">
														<input type="text" class="form-control" id="aorReason" placeholder="c/s 내역을 입력해주세요">
														<div class="input-group-append">
															<button type="submit" class="btn btn-primary"> 입력 </button>
														</div>
													</div>
												</form>
												<div class="card" id="adminOrderRecordList">
									
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<iframe id="excelDownloadIframe" width="0" height="0">
	</iframe>  
	<script src="${pageContext.request.contextPath}/resources/libs/js/renewal_cs_manage.js"></script>
	<%@ include file="../../inc/bottom.jsp" %>
        