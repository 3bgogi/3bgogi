<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		$("#costName").focus();
    		
    		$("#costProductionTime").keyup(function(){
    			var regex=  /^\d+$/;
    			
    			var costProductionTime = $(this).val();
    			
    			if(!regex.test(costProductionTime)){
    				alert("숫자만 입력해야 합니다.");
    				$(this).val("0");
    			}
    		});
    		
    		$("#insertCostDetailForm").submit(function(){
    			var costName = $("#costName").val();
    			
    			//다중 원가명을 입력하지 않았을 경우
    			if(costName == null || costName == ''){
    				alert("다중 원가명을 입력해주세요");
    				$("#costName").focus();
    				event.preventDefault();
    				return false;
    			}
    			
    			$("select[name$=cdFk]").each(function(){
    				
    				if($(this).val() == 0){
    					alert("상품을 선택해야 합니다");
    					$(this).focus();
    					event.preventDefault();
    					return false;
    					
    				}
    				
    			});
    			
    			$("input[name$=costMeasureCal]").each(function(){
    				
    				if($(this).val() == null || $(this).val() == ''){
    					alert("값을 입력해야 합니다");
    					$(this).focus();
    					event.preventDefault();
    					return false;
    					
    				}
    				
    			});
    			
    			
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
                            <h2 class="pageheader-title"> 상품 및 원재료 </h2>
                            <p class="pageheader-text">Proin placerat ante duiullam scelerisque a velit ac porta, fusce sit amet vestibulum mi. Morbi lobortis pulvinar quam.</p>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 상품 및 원재료 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 상품 및 원재료 입력 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 다중 원재료 입력 </li>
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
                                <h5 class="card-header"> 다중 다중 원재료 입력</h5>
                                <div class="card-body">
                                    <form name="insertCostDetailForm" id="insertCostDetailForm" method="post" action="<c:url value='/products/insert/costs.do'/>">
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 다중 원재료 원가명 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="다중 원가명을 입력해주세요." class="form-control" id="costName" name="costName">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 생산 시간 (분단위)</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="생산하는데 드는 시간을 입력해주세요. (쓰지 않아도 무관)" value="0" class="form-control" id="costProductionTime" name="costProductionTime">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 다중 원재료 설명란 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <textarea class="form-control" id="cdRemark" name="cdRemark" rows="3" style="resize: none;" placeholder="적지 않아도 무관."></textarea>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="form-group row">
                                        	<label class="col-12 col-sm-12 col-form-label text-sm-center"> 다중 원재료 </label>
                                        </div>
                                        <hr>
                                        <div class="form-group row">
                                        	<div class="col-6 col-sm-12 col-lg-6 offset-3 addCostDetailDiv">
                                        		<button type="button" class="btn btn-primary btn-block" id="addCostDetail" name="addCostDetail"> <i class="fas fa-plus"></i> 원재료 추가하기 </button>
                                        	</div>
                                        </div>
                                        <div class="col-8 col-sm-10 col-lg-8 offset-2 addCostDetailDiv" id="addCostDetailDiv">
                                        	<div class="form-group row">                                        	
	                                            <div class="col-8 col-sm-6 col-lg-8">
	                                            	<select class="form-control" data-live-search="true" name="costsVOList[0].cdFk" data-size="8">
	                                                	<option value="0"> 분류를 선택해주세요. </option>
	                                                </select>
	                                            </div>
	                                            <div class="col-2 col-sm-3 col-lg-2">
	                                                <input type="text" disabled="disabled" class="form-control" id="test3" name="costsVOList[0].costMeasureCal">
	                                            </div>
	                                            <div class="col-2 col-sm-3 col-lg-2">
	                                                <button type="button" class="btn btn-warning btn-block" name="deleteCostDetail"> <i class="fas fa-trash"></i> 삭제 </button>
	                                            </div>
                                        	</div>
                                        </div>
                                        <div class="form-group row text-right">
                                            <div class="col col-sm-12 col-lg-12">
                                                <button type="submit" class="btn btn-space btn-primary">저장하기</button>
                                                <button class="btn btn-space btn-secondary">취소하기</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        
                        <!-- ============================================================== -->
                        <!-- end valifation types -->
                        <!-- ============================================================== -->
                    </div>
            </div>
		    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
		    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
            <script src="${pageContext.request.contextPath}/resources/vendor/slimscroll/jquery.slimscroll.js"></script>
		    <script src="${pageContext.request.contextPath}/resources/vendor/multi-select/js/jquery.multi-select.js"></script>
		    <script src="${pageContext.request.contextPath}/resources/libs/js/main-js.js"></script>
		    <script src="${pageContext.request.contextPath}/resources/libs/js/renewal_cost_manage.js"></script>
		    <script type="text/javascript">
		    	$(function(){
		    		
		    	});
		    </script>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>