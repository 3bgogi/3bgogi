<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		$("#orPkAllSelect").click(function(){
    			
    			if($(this).is(":checked")){
    				$("input[name=orPk]").prop("checked","checked");
    				
    			}else{
    				$("input[name=orPk]").prop("checked","");
    				
    			}
    			
    		});
    		
    		$("#ssPk").change(function(){
    			if($(this).val() == 14){
    				$("#smartstore").hide();
    				$("#autoStore").show();
    				
    				
    			}else{
    				$("#smartstore").show();
    				$("#autoStore").hide();

    			}
    			
    		});
    		
    		$("input[name=orPk]").click(function(){
    			
    			if($(this).is(":checked")){
    				
    				
    			}else{
    				
    				if($("#orPkAllSelect").is(":checked")){
    					$("#orPkAllSelect").prop("checked","");
    					
    				}
    			}
    			
    			/*		if($("#orSeiralSpecialNumberAllSelect").is(":checked")){
    				$("input[name=orSerialSpecialNumberList]").prop("checked","checked");
    			}else{
    				$("input[name=orSerialSpecialNumberList]").prop("checked","");
    			}
    			*/
    			
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
                            <h2 class="pageheader-title"> 주문 등록 페이지 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문 입력 단계 </a></li>
                                        <li class="breadcrumb-item  active" aria-current="page"><a href="javascript:void(0);" class="breadcrumb-link"> 취소 주문 체크 </a></li>
                                        
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
									<a href="<c:url value='/stock/stk_check.do'/>" class="btn btn-success"> 재고 할당 </a> 
									<a href="<c:url value='/orders/cancled_order_check.do'/>" class="btn btn-success blinking"> 취소 주문  </a>
									<a href="<c:url value='/security/epost.do'/>" class="btn"> 송장 부여  </a> 
								</div>
							</div>
						</div>
					</div>
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 취소 주문서 체크 ( 변형되지 않은 원본을 넣어야함 ) </h5>
                                <div class="card-body">
                                    <form id="form" method="post" action="<c:url value='/orders/cancled_order_check.do'/>" enctype="multipart/form-data">
                                        <div class="form-group row">
                                            <div class="col-3 col-lg-2 col-form-label text-right">
                                            	<select class="custom-select d-block w-100" id="ssPk" name="ssPk">
													<c:if test="${!empty storeList }">
														<c:forEach var="storelist" items="${storeList }">
															<option value="${storelist.ssPk }">${storelist.ssName }</option>
														</c:forEach>
													</c:if>
													<c:if test="${empty storeList }">
														<option value="0">현재 등록된 판매처가 존재 하지 않습니다.</option>
													</c:if>
												</select> 
                                            </div>
                                            
                                            <div class="col-9 col-lg-10 col-form-label">
                                                <input id="smartstore" type="file" name="excelfile" class="form-control">
                                                <input id="autoStore" type="text" name="" class="form-control" style="display: none;" value="자동화 작업이 진행됩니다" readonly="readonly">
                                            </div>
                                        </div>
                                        <div class="row pt-2 pt-sm-5 mt-1">
                                            <div class="col-sm-6 offset-6">
                                                <p class="text-right">
                                                    <button type="submit" class="btn btn-space btn-danger"> 취소 체크 </button>
                                                </p>
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
	                                <h5 class="card-header">  취소 주문서 목록 </h5>
	                                <div class="card-body">
	                                	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mb-2">
	                                		<button class="btn btn-success btn-xs  mb-2" type="button" id="deleteCancledOrder"> 선택 주문 취소하기 </button>
	                                	</div>
	                                    <table class="table table-hover">
	                                        <thead>
	                                            <tr>
	                                                <th scope="col">
	                                                	<label class="custom-control custom-checkbox be-select-all">
								                        	<input class="custom-control-input chk_all" type="checkbox" name="chk_all" id="orPkAllSelect"><span class="custom-control-label"></span>
								                        </label>
	                                                </th>
	                                                <th scope="col">주문자</th>
	                                                <th scope="col">수령자</th>
	                                                <th scope="col">주소</th>
	                                                <th scope="col">상품</th>
	                                                <th scope="col">개수</th>
	                                                <th scope="col">전체보기</th>
	                                            </tr>
	                                        </thead>
	                                        
	                                        <tbody>
	                                        	<c:if test="${empty cancleCheckFlag and empty cancledOrderList }">
	                                        		 <tr>
	                                        		 	<td colspan="7" style="text-align: center;"> 취소주문을 체크해주세요</td>
	                                        		 </tr>
	                                        		 
	                                        	</c:if>
	                                        	
	                                        	
	                                        	
	                                        	<c:if test="${cancleCheckFlag == true and empty cancledOrderList }">
	                                        		 <tr>
	                                        		 	<td colspan="7" style="text-align: center;"> 취소주문건이 존재하지 않습니다</td>
	                                        		 </tr>
	                                        	</c:if>
	                                        	
	                                        	<c:if test="${cancleCheckFlag == true and !empty cancledOrderList }">	     
	                                        		<tr>
			                                        	<td colspan="7" style="text-align: center; font-size: 18px; color: red; font-weight: bold;"> 취소건에 대한 사은품 지급여부를 cs에서 꼭 확인해주세요 사은품은 취소되지 않습니다 </td>
			                                        </tr>                                   	
		                                        	<c:forEach var="cancleList" items="${cancledOrderList }">
		                                        		<tr>
			                                                <th scope="row">
			                                                	<label class="custom-control custom-checkbox be-select-all">
			                                                		<input class="custom-control-input chk_all" type="checkbox" name="orPk" value="${cancleList.orPk }">
			                                                		<span class="custom-control-label"></span>
			                                                	</label>
			                                                </th>
			                                                <td>
			                                                	${cancleList.orBuyerName } <br>
			                                                	${cancleList.orBuyerContractNumber1 }
			                                                </td>
			                                                <td>
			                                                	${cancleList.orReceiverName } <br>
			                                                	${cancleList.orReceiverContractNumber1 }
			                                                </td>
			                                                <td>
			                                                	${cancleList.orShippingAddress } ${cancleList.orShippingAddressDetail }
			                                                </td>
			                                                <td>
			                                                	${cancleList.orProduct } <br>
			                                                	${cancleList.orProductOption }
			                                                </td>
			                                                <td>
			                                                	${cancleList.orAmount }
			                                                </td>
			                                                <td>
			                                                	<button class="btn btn-outline btn-success" value="${cancleList.orSerialSpecialNumber }"> 보기 </button>
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
	             <iframe id="cancledOrderIframe" width="0" height="0" style="display: none;">
                    	
                    	
				</iframe>
        <!-- /page content -->
        <%@ include file="../inc/bottom.jsp" %>
<script type="text/javascript">
	$(function(){
			
		$(document).on('click', '#deleteCancledOrder', function(){

			
			var orPkSize = $("input[name=orPk]:checked").length;
			
			if(orPkSize > 0){
				
				if(confirm("선택 주문서를 취소처리하시겠습니까?")){
					var counting = 0;
					
					var divForm = document.createElement("form");
					divForm.id="deleteCancledOrderForm";
					
					$("#cancledOrderIframe").html(divForm);
					
					for(var i=0; i<orPkSize; i++){
						
						var orPk = document.createElement("input");
						orPk.name="orVoList["+counting+"].orPk";
						orPk.type="hidden";
						orPk.value=$("input[name=orPk]:checked")[i].value;
						$("#deleteCancledOrderForm").append(orPk);	
						counting++;
					}
					
					var deleteCancledOrderFormParams = jQuery("#deleteCancledOrderForm").serialize();
					
					console.log(deleteCancledOrderFormParams);
					$.ajax({
					    type       : 'POST',
					    url        : '/orders/update_cancled_order.do',
					    data       : deleteCancledOrderFormParams,
					    success    : function(data){
					    	alert(data+" 건 취소 처리 완료");
					    	location.reload();
					    }
					});
				}
			}
			
		});
		
	});

</script>