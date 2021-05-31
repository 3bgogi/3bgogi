<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../../inc/top.jsp" %>
    <%@ include file="../../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    
	    $(function(){
			$("#orPkAllSelect").click(function(){
    			
    			if($(this).is(":checked")){
    				$("input[name=orPk]").prop("checked","checked");
    				
    			}else{
    				$("input[name=orPk]").prop("checked","");
    				
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
    		
    		$("#checkExceptAddrdOrder").click(function(){
    			
    			var orSize = $("input[name=orPkList]").length;
    			
    			if(orSize == 0){
    				alert("확인 처리할 수 있는 주문서가 없습니다");	
    			}
    			
    			if(confirm("확인 처리를 하시겠습니까?")){
    				var orPkList = new Array(orSize);
        			
        			$("input[name=orPkList]").each(function(i, selecter){
        				orPkList.push($(selecter).val());
        			})
        			
        			$.ajax({
        				type       : 'POST',
        			    url        : '<c:url value="/order/config/check_except_addr.do"/>',
        			    data		:{
        			    	"orPkList":orPkList
        			    },
        			    success    : function(data){
        			    	alert(data);
        			    	location.reload();

        			    }
        			});
    			}
    			
    			 
    		});
    		
    		$("#addExceptAddr").click(function(){
    			
    			window.open('<c:url value="/order/config/except_address_keyword.do"/>', "제외 키워드 목록 및 추가" , "width=550, height=800, top=200, left=1200, scrollbars=no");
    			
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
                            <h2 class="pageheader-title"> 특수 지역 체크  </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문서 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 특수 지역 </a></li>
                                        
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                	<div class="row">
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="card" style="background-color: #efeff6;">
								<div class="card-body">
									<h4> 주문서 진행 단계 </h4>
									<a href="<c:url value='/orders/order_page.do'/>" class="btn btn-success"> 주문서 입력</a>
									<a href="<c:url value='/order/config/search_except_addr_order.do'/>" class="btn btn-success blinking"> 특수 지역 체크  </a>
									<a href="<c:url value="/order/matching/products_matching.do"/>" class="btn"> 상품 매칭 </a> 
									<a href="<c:url value="/order/matching/option_matching.do"/>" class="btn"> 옵션 매칭 </a>
									<a href="<c:url value='/config/freebie/apply.do'/>" class="btn"> 사은품 부여  </a>    
									<a href="<c:url value='/orders/delivery_msg_check.do'/>" class="btn"> 요구사항 체크 </a>
									<a href="<c:url value='/stock/stk_check.do'/>" class="btn"> 재고 할당 </a> 
									<a href="<c:url value='/orders/cancled_order_check.do'/>" class="btn"> 취소 주문  </a>
									<a href="<c:url value='/security/epost.do'/>" class="btn"> 송장 부여  </a> 
								</div>
							</div>
						</div>
					</div>
                		<div class="row">
							<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                            <form class="card">
	                                <h5 class="card-header"> 주소 특정 키워드 제외 주문서 </h5>
	                                <div class="card-body">
	                                	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mb-2">
	                                		<button class="btn btn-success btn-xs  mb-2" type="button" id="checkExceptAddrdOrder"> 주문 확인 처리  </button>
	                                		&nbsp;
	                                		<button class="btn btn-success btn-xs  mb-2" type="button" id="addExceptAddr"> 특수 지역 추가하기  </button>
	                                	</div>
	                                    <table class="table table-hover">
	                                        <thead>
	                                            <tr class="border-0" style="text-align: center;">
	                                                <th class="border-0">구매자명</th>
	                                                <th class="border-0">수령인명</th>
	                                                <th class="border-0">주소</th>
	                                                <th class="border-0">상품</th>
	                                                <th class="border-0">개수</th>
	                                                <th class="border-0">발송기한</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:if test="${empty orList }">
	                                        		<tr>
	                                        			<td style="text-align: center;" colspan="7"> 특수 지역 주문서가 존재하지 않습니다 </td>
	                                        		</tr>
	                                        	</c:if>
	                                        	<c:if test="${!empty orList }">
	                                        		<c:set var="deadlineCheckCounting" value="0"/>
	                                        		<c:forEach var="orlist" items="${orList }">
		                                        		<tr>
			                                                <td>
			                                                	<input name="orPkList" type="hidden" value="${orlist.orPk }">
			                                                	${orlist.orBuyerName } <br>
			                                                	${orlist.orBuyerContractNumber1 }
			                                                 </td>
			                                                <td>
			                                                    ${orlist.orReceiverName } <br>
			                                                	${orlist.orReceiverContractNumber1 }
			                                                </td>
			                                                <td>
			                                                	${orlist.orShippingAddress }
			                                                	<c:if test="${!empty orlist.orShippingAddressDetail }">
			                                                		<br> ${orlist.orShippingAddressDetail }
			                                                	</c:if>
			                                                	<c:forEach var="list" items="${eakList }">
			                                                		<c:if test="${fn:contains(orlist.orShippingAddress,list.eakWord) }">
			                                                			<c:if test="${fn:contains(list.eakReason,'날짜확인') }">
			                                                				<c:set var="deadlineCheckCounting" value="${deadlineCheckCounting + 1 }"/>
			                                                				
			                                                			</c:if>
			                                                			<br> <span class="text-danger">사유 : ${list.eakReason }</span>
			                                                		</c:if>
			                                                	</c:forEach>
			                                                </td>
			                                                <td>
			                                                	${orlist.orProduct } <br>
			                                                	${orlist.orProductOption }
			                                                	
			                                                </td>
			                                                
			                                                <td style="text-align: center;">
			                                                	${orlist.orAmount }
			                                                </td>
			                                                <td
			                                                	<c:if test="${deadlineCheckCounting > 0 }">
			                                                		class="blinking"
			                                                		style="color: red; font-weight: bold; text-align: center;"
			                                                		
			                                                	</c:if>
			                                                >
			                                                	${orlist.orSendingDeadline } (<fmt:formatDate value="${orlist.orSendingDeadline }" pattern="E"/>)
			                                                	
			                                                </td>
			                                            </tr>
			                                            <c:set var="deadlineCheckCounting" value="0"/>
	                                        		</c:forEach>
	                                        	</c:if>
	                                        </tbody>
	                                    </table>
	                                </div>
	                            </form>
	                        </div>
						</div>
            </div>
        <!-- /page content -->
        <%@ include file="../../../inc/bottom.jsp" %>