<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../../inc/top.jsp" %>
    <%@ include file="../../../inc/top_nav.jsp" %>
    <script type="text/javascript">
		$(function(){
			$("#storeMergeUpdateForm").submit(function(){
				var orMerge = $("#orMergeSeleted").val();
				
				
				if(orMerge == ''){
					alert("하나의 값이 꼭 있어야합니다");
					event.preventDefault();
					return false;	
				}
				
				if(confirm("해당 설정값으로 묶음정리를 하시겠습니까?")){

					$("#ssMerge").val(orMerge.toString());
				}else{
					event.preventDefault();
					return false;					
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
                                <h2 class="pageheader-title"> 판매처 별 엑셀 파일 열값 설정하기 </h2>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 설정 </a></li>
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 판매처 설정 </a></li>
                                            <li class="breadcrumb-item active" aria-current="page"> 엑셀 설정 </li>
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
                    		<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="card">
                                    <div class="card-header tab-regular">
                                        <ul class="nav nav-tabs card-header-tabs" id="myTab" role="tablist">
                                            <li class="nav-item">
                                                <a class="nav-link" href="<c:url value='/config/store/excel_config.do?ssPk=${ssVO.ssPk }'/>"> 판매처 주문서 엑셀 설정</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" href="<c:url value='/config/store/sending_form.do?ssPk=${ssVO.ssPk }'/>"> 판매처 발송 엑셀 설정</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" href="<c:url value='/config/store/info.do?ssPk=${ssVO.ssPk  }'/>"> 판매처 정보 변경</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link active" href="<c:url value='/config/store/merge.do?ssPk=${ssVO.ssPk  }'/>"> 판매처 묶음 정리 변경</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="card-body">
                                        <div class="tab-content" id="myTabContent">
                                            <div class="tab-pane fade show active">
                                            	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						                            <div class="card">
						                                <div class="p-3 mb-2 bg-danger text-white text-center">
						                                	해당 설정값은 잘못될 경우 송장이 여러 개로 나눠질 수 있습니다
						                                </div>
						                                <div class="p-3 mb-2 bg-danger text-white text-center">
						                                	아래 선택사항으로 그룹화하여 하나의 송장으로 만들어집니다
						                                </div>
						                                <div class="card-body">
						                                    <div class="offset-xl-3 col-xl-6 col-lg-12 col-md-12 col-sm-12 col-12">
									                            <div class="card">
									                                <h5 class="card-header"> 묶음 값 선택 </h5>
									                                <div class="card-body">
									                                	<c:set var="loopBreakFlag" value="false" />
									                                	<c:set var="selectedMerge" value="${fn:split(ssVO.ssMerge,',') }" />
									                                    <select multiple="multiple" id="orMergeSeleted" name="orMergeSeleted[]">
										                                    	<option value='or_buyer_id'
										                                    		<c:forEach var="mergeList" items="${selectedMerge }">
										                                    			<c:if test="${loopBreakFlag == false }">										                                    			
												                                    		<c:if test="${mergeList == 'or_buyer_id' }">
												                                    			selected
												                                    			<c:set var="loopBreakFlag" value="true" />
												                                    		</c:if>
										                                    			</c:if>	
										                                    		</c:forEach>
										                                    		<c:set var="loopBreakFlag" value="false" />
										                                    	>구매자 아이디</option>
										                                        <option value='or_buyer_name'
										                                        	<c:forEach var="mergeList" items="${selectedMerge }">
										                                    			<c:if test="${loopBreakFlag == false }">										                                    			
												                                    		<c:if test="${mergeList == 'or_buyer_name' }">
												                                    			selected
												                                    			<c:set var="loopBreakFlag" value="true" />
												                                    		</c:if>
										                                    			</c:if>	
										                                    		</c:forEach>
										                                    		<c:set var="loopBreakFlag" value="false" />
										                                        >구매자명</option>
										                                        <option value='or_receiver_name'
										                                        	<c:forEach var="mergeList" items="${selectedMerge }">
										                                    			<c:if test="${loopBreakFlag == false }">										                                    			
												                                    		<c:if test="${mergeList == 'or_receiver_name' }">
												                                    			selected
												                                    			<c:set var="loopBreakFlag" value="true" />
												                                    		</c:if>
										                                    			</c:if>	
										                                    		</c:forEach>
										                                    		<c:set var="loopBreakFlag" value="false" />
										                                        >수취인명</option>
										                                        <option value='or_order_number'
										                                        	<c:forEach var="mergeList" items="${selectedMerge }">
										                                    			<c:if test="${loopBreakFlag == false }">										                                    			
												                                    		<c:if test="${mergeList == 'or_order_number' }">
												                                    			selected
												                                    			<c:set var="loopBreakFlag" value="true" />
												                                    		</c:if>
										                                    			</c:if>	
										                                    		</c:forEach>
										                                    		<c:set var="loopBreakFlag" value="false" />
										                                        >주문번호</option>
										                                        <option value='or_product_order_number'
										                                        	<c:forEach var="mergeList" items="${selectedMerge }">
										                                    			<c:if test="${loopBreakFlag == false }">										                                    			
												                                    		<c:if test="${mergeList == 'or_product_order_number' }">
												                                    			selected
												                                    			<c:set var="loopBreakFlag" value="true" />
												                                    		</c:if>
										                                    			</c:if>	
										                                    		</c:forEach>
										                                    		<c:set var="loopBreakFlag" value="false" />
										                                        >상품주문번호</option>
										                                        <option value='or_delivery_number'
										                                        	<c:forEach var="mergeList" items="${selectedMerge }">
										                                    			<c:if test="${loopBreakFlag == false }">										                                    			
												                                    		<c:if test="${mergeList == 'or_delivery_number' }">
												                                    			selected
												                                    			<c:set var="loopBreakFlag" value="true" />
												                                    		</c:if>
										                                    			</c:if>	
										                                    		</c:forEach>
										                                    		<c:set var="loopBreakFlag" value="false" />
										                                        >배송묶음번호</option>
										                                        <option value='or_user_column1'
										                                        	<c:forEach var="mergeList" items="${selectedMerge }">
										                                    			<c:if test="${loopBreakFlag == false }">										                                    			
												                                    		<c:if test="${mergeList == 'or_user_column1' }">
												                                    			selected
												                                    			<c:set var="loopBreakFlag" value="true" />
												                                    		</c:if>
										                                    			</c:if>	
										                                    		</c:forEach>
										                                    		<c:set var="loopBreakFlag" value="false" />
										                                        >사용자정의1</option>
										                                        <option value='or_user_column2'
										                                        	<c:forEach var="mergeList" items="${selectedMerge }">
										                                    			<c:if test="${loopBreakFlag == false }">										                                    			
												                                    		<c:if test="${mergeList == 'or_user_column2' }">
												                                    			selected
												                                    			<c:set var="loopBreakFlag" value="true" />
												                                    		</c:if>
										                                    			</c:if>	
										                                    		</c:forEach>
										                                    		<c:set var="loopBreakFlag" value="false" />
										                                        >사용자정의2</option>
										                                        <option value='or_user_column3'
										                                        	<c:forEach var="mergeList" items="${selectedMerge }">
										                                    			<c:if test="${loopBreakFlag == false }">										                                    			
												                                    		<c:if test="${mergeList == 'or_user_column3' }">
												                                    			selected
												                                    			<c:set var="loopBreakFlag" value="true" />
												                                    		</c:if>
										                                    			</c:if>	
										                                    		</c:forEach>
										                                    		<c:set var="loopBreakFlag" value="false" />
										                                        >사용자정의3</option>
									                                    	
									                                    </select>
									                                </div>
									                            </div>
									                        </div>
									                        <div class="form-group row text-right">
							                                    <div class="col col-sm-10 col-lg-9 offset-sm-1 offset-lg-0">
							                                    	<form id="storeMergeUpdateForm" action="<c:url value='/config/store/merge.do'/>" method="POST">
							                                    		<input type="hidden" name="ssPk" value="${ssVO.ssPk }">
							                                    		<input type="hidden" name="ssMerge" id="ssMerge" value="">
								                                     	<button type="submit" id="mergeBtn" class="btn btn-space btn-primary">변경하기</button>
							                                    	</form>
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
        <!-- /page content -->
        <%@ include file="../../../inc/bottom.jsp" %>