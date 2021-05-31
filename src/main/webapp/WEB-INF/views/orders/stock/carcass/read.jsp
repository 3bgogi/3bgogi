<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../../inc/top.jsp" %>
    <%@ include file="../../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		var costIoCounting = 0;
    		
    		$('#cilInputDate').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d',
    			"setDate" : new Date()
    			
    		});
    		

    		
    		$(document).on("click", "#deleteCarcass", function(){
    			
    			if(confirm("해당 도체값을 정말로 삭제하시겠습니까? ( 복구 불가 )")){
    				alert("삭제하기");
    			}
    			
    		});
    		
    		$("#backList").click(function(){
    			location.href="<c:url value='/stock/carcass/list.do'/>";
    			
    		});
	
    	});

    </script>
    <style type="text/css">
    	.table-3bgogi-hover:hover{
    		background: #FFC6C6;
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
                            <h2 class="pageheader-title"> 도체 입력 상세사항 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 재고 관리 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 도체 관리 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 도체 목록 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 도체 입력 상세사항 </li>
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
                        <!-- ============================================================== -->
                        <!-- valifation types -->
                        <!-- ============================================================== -->
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 도체 입력 상세사항 </h5>
                                <div class="card-body">
                                    <form name="updateCarcassForm" id="updateCarcassForm" action="<c:url value='/stock/carcass/update.do'/>" method="post">
                                    	<input type="hidden" name="cilPk" value="${cilList.cilPk }">
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 품목명 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" class="form-control" id="cilProduct" name="cilProduct" value="${cilList.cilProduct }">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 이력번호 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="이력번호를 입력해주세요" readonly="readonly" id="cilAnimalProdTraceNum" class="form-control" value="${cilList.cilAnimalProdTraceNum }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 구매처 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="구매처를 입력해주세요" class="form-control" id="cilPurchaseStore" name="cilPurchaseStore" value="${cilList.cilPurchaseStore }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 구매가 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="구매가를 입력해주세요" readonly="readonly" class="form-control" id="cilPurchasePrice" value="<fmt:formatNumber value='${cilList.cilPurchasePrice }' pattern='#,###' /> 원">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 도체 무게 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="g 단위로 입력해주세요" readonly="readonly" class="form-control" id="cilWeight" value="<fmt:formatNumber value='${cilList.cilWeight }' pattern='#,###'/> g">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 도체 번호 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="도체 번호를 입력해주세요" readonly="readonly" class="form-control" id="cilNum"  value="${cilList.cilNum }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 두수 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="number" placeholder="두수를 입력해주세요" readonly="readonly" class="form-control" id="cilQty" value="${cilList.cilQty }">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 입고일</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" class="form-control" id="cilInputDate" name="cilInputDate" value="${cilList.cilInputDate }">
                                            </div>
                                        </div>
                                        <hr>                              
	                                    <div class="form-group row">
	                                    	<label class="col-12 col-sm-3 col-form-label text-sm-right"></label>
	                                        <div class="col-12 col-sm-8 col-lg-6">
		                                        	<div class="card">
										            <div class="card-body">
										        		<table class="table table-hover">
										                	<thead>
										                		
																<tr>
																	<th>부위</th>
																	<th>무게 ( 단위 g )</th>
																	<th>등급</th>
																	<th>근내지방등급</th>
																</tr>
										                    </thead>
										                    <tbody>
										                    	<c:if test="${!empty cilList.costIoList}">
										                    		<c:forEach var="ciList" items="${cilList.costIoList }">                    		
											                    		<tr>
											                    			<td>${ciList.ciAnimalProdTraceNum }</td>
											                    			<td>
											                    				<fmt:formatNumber value="${ciList.ciWeight }" pattern="#,###"/> g
											                    			</td>
											                    			
											                    			<td>${ciList.ciLevel } 등급</td>
											                    			<td>${ciList.ciMarblingLevel }</td>
											                    		</tr>
										                    		</c:forEach>
										                        </c:if>
										                        
										                        <c:if test="${empty cilList.costIoList }">
										                        	<tr>
										                        		<td colspan="4">작업기록이 없습니다</td>
										                        	</tr>
										                        </c:if>
										                   	</tbody>
										                </table>
									             	</div>
		                                    	</div>
									        </div>
	                                    </div>
                                        <hr id="divLine">
                                        <div class="form-group row text-right">
                                            <div class="col col-sm-10 col-lg-9 offset-sm-1 offset-lg-0">
                                                <button type="submit" id="updateCarcass" class="btn btn-space btn-success">수정하기</button>
                                                <button type="button" id="backList" class="btn btn-space btn-primary">목록으로</button>
                                                
                                                <button type="button" id="deleteCarcass" value="${cilList.cilPk }" class="btn btn-space btn-danger">삭제하기</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

					</div>

			<!-- ============================================================== -->
                        <!-- end valifation types -->
                        <!-- ============================================================== -->
                    </div>
            </div>
        <!-- /page content -->
        <%@ include file="../../../inc/bottom.jsp" %>