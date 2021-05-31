<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %> 
    <script type="text/javascript">
    	$(function(){
    		
    		$("#updateExcelOrderSeq").submit(function(){
    			if(confirm("해당 정보로 정보를 변경하시겠습니까?")){
    				
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
            <div class="container-fluid  dashboard-content">
                <!-- ============================================================== -->
                <!-- pageheader -->
                <!-- ============================================================== -->
                <div class="row">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <div class="page-header">
                            <h2 class="pageheader-title"> 주문서 분류 코드 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 설정 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 분류 코드 </a></li>
                                        <li class="breadcrumb-item  active" aria-current="page"><a href="javascript:void(0);" class="breadcrumb-link"> 주문서 분류 코드 </a></li>
                                        
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
						<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
                            <form class="card" id="updateExcelOrderSeq"  action="<c:url value='/code/excel_order_seq.do'/>" method="POST">
                                <h5 class="card-header"> 등록된 주문서 순서 값 </h5>
                                <div class="card-body">
                                    <table class="table table-hover">
                                    	<thead>
											<tr>
												<th width="30%">주문서 순서</th>
												<th width="50%">주문서 분류명</th>
												<th width="20%">주문서 합계 표기</th>
											</tr>
                                    	</thead>
                                    	
                                        <tbody>
                                        	<c:if test="${!empty eosList }">
                                        		<c:set var="counting" value="0"/>
                                        		<c:forEach var="eoslist" items="${eosList }">                                        		
		                                            <tr>
		                                            	<td>
		                                            		<input type="hidden" name="eosList[${counting }].eosPk" value="${eoslist.eosPk }">
		                                            		<input class="form-control" name="eosList[${counting }].eosSeq"  type="text" value="${eoslist.eosSeq }" style="width: 50px;">
		                                            	</td>
		                                            	<td>
		                                            		<input class="form-control" name="eosList[${counting }].eosLocation"  type="text" value="${eoslist.eosLocation }">
		                                            	</td>
		                                            	<td>
		                                            		<label class="custom-control custom-radio custom-control-inline">
					                                        	<input type="radio" name="eosList[${counting}].eosExcelTotalQtyFlag" value="0"
					                                        		<c:if test="${eoslist.eosExcelTotalQtyFlag == 'false' }">
																		 checked
																	</c:if>
					                                        	class="custom-control-input"><span class="custom-control-label"> N </span>
					                                        </label>
					                                        <label class="custom-control custom-radio custom-control-inline">
					                                        	<input type="radio" name="eosList[${counting}].eosExcelTotalQtyFlag" value="1"
					                                        		<c:if test="${eoslist.eosExcelTotalQtyFlag == 'true' }">
																		 checked
																	</c:if>
					                                        	class="custom-control-input"><span class="custom-control-label"> Y </span>
					                                        </label>
		                                            	</td>
		                                            </tr>
		                                            <c:set var="counting" value="${counting + 1 }"/>
		                                            
                                        		</c:forEach>            	
                                        	</c:if>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="card-body">
                                	<button id="seqBtn" class="btn btn-primary btn-block" type="submit"> 정보 일괄 변경 </button>
                                </div>
                            </form>
                        </div>
                        
                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
                            <div class="card">        	
                            	<h5 class="card-header"> 주문서 분류 추가하기 </h5>
					            <div class="card-body" id="">
					            	<form id="insertExcelOrderSeqForm" class="form-group" action="<c:url value='/code/insert_excel_order_seq.do'/>" method="POST">
				                    	<div class="input-group">
				                    		<input type="text" class="form-control" id="eosSeq" name="eosSeq" placeholder="주문서 순서">
					                        <input type="text" class="form-control" id="eosLocation" name="eosLocation" placeholder="주문서 분류명">
					                        <div class="input-group-append">
					                        	<button type="submit" class="btn btn-primary"> 추가 </button>
					                        </div>
				                        </div>
					            	</form>
					            </div>
				        	</div>
                        </div>
					</div>
	            </div>
        <!-- /page content -->
        
        <script type="text/javascript">
        	$(function(){
        		$("#insertExcelOrderSeqForm").submit(function(){
        			
        			var eosSeq = $("#eosSeq").val();
        			var eosLocation = $("#eosLocation").val();
        			
        			if(eosSeq == ''){
        				alert("주문서 순서를 적어주세요");
        				$("#eosSeq").focus();
        				
        				return false;
        			}if(eosLocation == ''){
        				alert("주문서 분류명을 입력해주세요");
        				$("#eosLocation").focus();
        				
        				return false;
        			}
        		});
        	});
        </script>
        <%@ include file="../inc/bottom.jsp" %>