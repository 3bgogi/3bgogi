<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../../../inc/top.jsp" %>
    <%@ include file="../../../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		$("#backList").click(function(){
    			location.href="<c:url value='/stock/carcass/carcass_cut/list.do'/>";
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
                            <h2 class="pageheader-title"> 부분육 상세확인 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 재고 관리 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 원육 관리 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 부분육 목록 </li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ecommerce-widget">
                    <div class="row">
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="card">
								<h5 class="card-header"> ${ciVO.costDetailName } ( 등록일 - <fmt:formatDate value="${ciVO.ciRegdate }" pattern="yyyy-MM-dd"/>)</h5>
								<div class="card-body">
									<form class="needs-validation" id="updateCostInputDataForm" method="POST" action="<c:url value='/products/update/cost_io.do'/>">
										<input type="hidden" name="ciPk" value="${ciVO.ciPk }">
										<div class="mb-3">
											<label for="ciPrice">원가</label> 
											<input type="number" class="form-control numberCheck" id="ciPrice" name="ciPrice" placeholder="원가를 입력해주세요" value="${ciVO.ciPrice }">
										</div>
										<div class="row">
											<div class="col-md-5 mb-3">
												<label for="ciWeight"> 입고 무게( g단위로 입력 ) </label> 
												<input type="text" class="form-control numberCheck" id="ciWeight" name="ciWeight" placeholder="" value="${ciVO.ciWeight }" required="">
											</div>
											<div class="col-md-4 mb-3">
												<label for="ciOutputWeight"> 출고 무게 </label> 
												<input type="text" class="form-control numberCheck" id="ciOutputWeight" name="ciOutputWeight" placeholder="" value="${ciVO.ciOutputWeight }" required="" disabled="disabled">
											</div>
											<div class="col-md-3 mb-3">
												<label for="ciLevel">등급</label> 
												<input type="text" class="form-control" id="ciLevel" name="ciLevel" placeholder="" required="" value="${ciVO.ciLevel }">
											</div>
											<div class="col-md-3 mb-3">
												<label for="ciMarblingLevel">근내지방등급</label> 
												<input type="text" class="form-control" id="ciMarblingLevel" name="ciMarblingLevel" placeholder="" value="${ciVO.ciMarblingLevel }">
											</div>
										</div>
										<div class="mb-3">
											<label for="ciAnimalProdTraceNum">이력번호</label> 
											<input type="text" class="form-control" id="ciAnimalProdTraceNum" name="ciAnimalProdTraceNum" placeholder="이력번호를 입력해주세요" required="" value="${ciVO.ciAnimalProdTraceNum }">
										</div>
										<div class="mb-3">
											<label for="ciItemsManufNum">품목제조번호</label> 
											<input type="text" class="form-control" id="ciItemsManufNum" name="ciItemsManufNum" placeholder="품목제조번호를 입력해주세요" required="" value="${ciVO.ciItemsManufNum }">
										</div>
										<div class="row">
											<div class="col-md-6 mb-3">
												<label for="ciAbattoir"> 도축장 </label> 
												<input type="text" class="form-control" id="ciAbattoir" name="ciAbattoir" placeholder="" value="${ciVO.ciAbattoir }">
											</div>
											<div class="col-md-6 mb-3">
												<label for="ciCountryOfOrigin"> 원산지 </label> 
												<input type="text" class="form-control" id="ciCountryOfOrigin" name="ciCountryOfOrigin" placeholder="" value="${ciVO.ciCountryOfOrigin }" required="">
											</div>
										</div>
										<hr class="mb-4">
										<div class="form-group row text-right">
				                        	<div class="col col-sm-10 col-lg-9 offset-sm-1 offset-lg-0">
				                            	<button type="submit" class="btn btn-space btn-primary"> 수정하기 </button>
				                                <button  type="button" id="backList" class="btn btn-space btn-danger"> 목록 </button>
				                                
				                            </div>
				                        </div>
									</form>
								</div>
							</div>
						</div>
					</div>
                 </div>
            </div>
        <%@ include file="../../../../inc/bottom.jsp" %>