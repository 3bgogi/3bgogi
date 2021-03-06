<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script src="${pageContext.request.contextPath}/resources/vendor/inputmask/js/jquery.inputmask.bundle.js"></script>
    <style type="text/css">
    	.renewal-store-list{
    		cursor: pointer;
    	}
    </style>
    <script type="text/javascript">
    	$(function(){
    		
    		$('#fbMinDate').datetimepicker({
    			timepicker: true,
    			lang:'kr',
    			format:'Y-m-d H:i'
    			
    		});
    		
    		$('#fbMaxDate').datetimepicker({
    			timepicker: true,
    			lang:'kr',
    			format:'Y-m-d H:i'
    		});
    		
    		
    		$("input[name=fbTotalQtyFlag]").change(function(){
    			var fbTotalQtyFlag = $(this).val();
    			var fbAddType = $("input[name=fbAddType]:checked").val();
    			if(fbTotalQtyFlag == 0){
    				$("#fbMinTotalQty").prop("readonly","readonly");
    				$("#fbMaxTotalQty").prop("readonly","readonly");
    			}else{
    				$("#fbMinTotalQty").prop("readonly","");
    				$("#fbMaxTotalQty").prop("readonly","");
    			}
    			
    			if(fbAddType == 3 && fbTotalQtyFlag == 1){
    				$("#fbMinPrice").prop("readonly","readonly");
    				
    			}else{
    				$("#fbMinPrice").prop("readonly","");
    				
    			}
    			
    		});
    		
    		$("input[name=fbAnotherCheckFlag]").change(function(){
    			var fbAnotherCheckFlag = $(this).val();
    			
    			if(fbAnotherCheckFlag == 0){
    				$("#fbAnotherCheckList").prop("disabled","disabled");
    				$("#fbAnotherCheckWord").prop("readonly","readonly");
    				$("#fbAnotherCheckType").prop("disabled","disabled");
    				
    			}else{
    				$("#fbAnotherCheckList").prop("disabled","");
    				$("#fbAnotherCheckWord").prop("readonly","");
    				$("#fbAnotherCheckType").prop("disabled","");
    			}
    		});
    		
    		$("input[name=fbAnotherCheckFlag2]").change(function(){
    			var fbAnotherCheckFlag2 = $(this).val();
    			
    			if(fbAnotherCheckFlag2 == 0){
    				$("#fbAnotherCheckList2").prop("disabled","disabled");
    				$("#fbAnotherCheckWord2").prop("readonly","readonly");
    				$("#fbAnotherCheckType2").prop("disabled","disabled");
    				
    			}else{
    				$("#fbAnotherCheckList2").prop("disabled","");
    				$("#fbAnotherCheckWord2").prop("readonly","");
    				$("#fbAnotherCheckType2").prop("disabled","");
    			}
    		});
    		
    		$("#freebieInsertForm").submit(function(){
    			var fbMinPrice = number($("#fbMinPrice").val());
    			var fbMaxPrice = number($("#fbMaxPrice").val());
    			
    			var fbMinTotalQty = $("#fbMinTotalQty").val();
    			var fbMaxTotalQty = $("#fbMaxTotalQty").val();
    			
    			
    			if(fbMinPrice > fbMaxPrice){
    				alert("?????? ????????? ?????????????????? ?????? ??? ????????????");
    				$("#fbMaxPrice").focus();
    				event.preventDefault();
    				return false;
    				
    			}else if(fbMinTotalQty == '' && fbMinTotalQty == ''){
    				$("#fbMinTotalQty").val("0");
    				$("#fbMaxTotalQty").val("0");
    				event.preventDefault();
    				return false;
    			}
    			
    		});
    		
    		$("input[name=fbAddType]").change(function(){
    			var fbTotalQtyFlag = $("input[name=fbTotalQtyFlag]:checked").val();
    			var fbAddType = $(this).val();
    			
    			if($(this).val() == 3){
    				$("#totalPrice").text("?????? ??????");
    				$("#totalQty").text("?????? ??????");
    				$("#maxTotalPrice").hide();
    				$("#maxTotalQty").hide();
    				$("#fbMinPrice").prop("placeholder","?????? ??????");
    				$("#fbMinTotalQty").prop("placeholder","?????? ??????");
    				
    			}else{
    				$("#totalPrice").text("?????? ??????");
    				$("#totalQty").text("?????? ??????");
    				$("#maxTotalPrice").show();
    				$("#maxTotalQty").show();
    				$("#fbMinPrice").prop("placeholder","?????? ??????");
    				$("#fbMinTotalQty").prop("placeholder","?????? ??????");
    			}
    			
    			if(fbAddType == 3 && fbTotalQtyFlag == 1){
    				$("#fbMinPrice").prop("readonly","readonly");
    				$("#fbMinTotalQty").focus();
    				
    			}else{
    				$("#fbMinPrice").prop("readonly","");
    				$("#fbMinPrice").focus();
    				
    			}
    			
    		});
    		
    		
    		
    	});
    	
    	function openProductOption(){
    		
    		window.open("<c:url value='/common/products.do'/>", "?????? ??????" , "width=800, height=800, top=50, left=400, scrollbars=no");
    		
    	}
    </script>
    
        <!-- page content -->
        <!-- ============================================================== -->
        <!-- wrapper  -->
        <!-- ============================================================== -->
        <div class="dashboard-wrapper">
            <div class="container-fluid dashboard-content ">
                  <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="page-header">
                                <h2 class="pageheader-title"> ????????? ?????? ?????? </h2>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> ?????? </a></li>
                                            <li class="breadcrumb-item active" aria-current="page"> ????????? ?????? </li>
                                        </ol>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ============================================================== -->
                    <!-- end pageheader  -->
                    <!-- ============================================================== -->
                    <form id="freebieInsertForm" action="<c:url value='/config/freebie/insert.do'/>" method="POST">
	                    <div class="row">
	                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                            <div class="card">
	                                <h5 class="card-header"> ????????? ?????? ?????? </h5>
	                                <div class="card-body">
	                                    <div id="validationform">
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> ????????? ????????? </label>
	                                            <div class="col-12 col-sm-8 col-lg-6">
	                                                <input type="text" required="" id="fbName" name="fbName" class="form-control">
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> ?????? ?????? ????????? </label>
	                                            <div class="col-12 col-sm-8 col-lg-6">
							                        <select class="selectpicker" multiple data-actions-box="true" data-width="100%" id="ssList" name="ssList">
								                        <c:if test="${empty ssList }">
								                        	<option value="0">????????? ???????????? ????????????</option>
								                        </c:if>
								                        <c:if test="${!empty ssList }">
								                        	<c:forEach var="sslist" items="${ssList }">
								                        		<option value="${sslist.ssPk }">${sslist.ssName }</option>
								                        	</c:forEach>
								                        </c:if>
							                        </select>
							                        
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right">????????? ?????? ??????</label>
	                                            <div class="col-6 col-sm-4 col-lg-3">
	                                                <input type="text" required id="fbMinDate" class="form-control" name="fbMinDate" value="${fbVO.fbMinDate }">
	                                            </div>
	                                            <div class="col-6 col-sm-4 col-lg-3">
	                                                <input type="text" required id="fbMaxDate" class="form-control" name="fbMaxDate" value="${fbVO.fbMaxDate }">
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> ????????? ?????? ?????? </label>
	                                            <div class="col-9 col-sm-7 col-lg-9">
	                                            	<label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" value="0" name="fbType" checked="checked" class="custom-control-input"><span class="custom-control-label"> ?????????????????? </span>
		                                            </label>
	                                                <label class="custom-control custom-radio custom-control-inline">
	                                                	<input type="radio" value="1" name="fbType" class="custom-control-input"><span class="custom-control-label"> ???????????? ?????? </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
	                                                	<input type="radio" value="2" name="fbType" class="custom-control-input"><span class="custom-control-label"> ????????? ?????? ?????? </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
	                                                	<input type="radio" value="3" name="fbType" class="custom-control-input"><span class="custom-control-label"> ????????? ?????? ?????? </span>
		                                            </label>
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> ?????? ?????????</label>
	                                            <div class="col-9 col-sm-7 col-lg-4">
	                                            	<label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" value="od.or_regdate" name="fbRegType" checked="checked" class="custom-control-input"><span class="custom-control-label"> ??????????????? ????????? </span>
		                                            </label>
	                                                <label class="custom-control custom-radio custom-control-inline">
	                                                	<input type="radio" value="od.or_settlement_day" name="fbRegType" class="custom-control-input"><span class="custom-control-label"> ????????? </span>
		                                            </label>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                            <div class="card">
	                                <h5 class="card-header"> ????????? ?????? </h5>
	                                <div class="card-body">
	                                    <div id="validationform">
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> ????????? </label>
	                                            <div class="col-12 col-sm-8 col-lg-6">
	                                                <div class="input-group mb-3">
		                                                <input type="hidden" class="form-control" id="productPk">
		                                                <input type="text" class="form-control" id="productName">
		                                                <div class="input-group-append">
		                                                    <button type="button" class="btn btn-primary" id="searchProduct" onclick="openProductOption()"> ?????? </button>
		                                                </div>
		                                            </div>
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> ????????? </label>
	                                            <div class="col-12 col-sm-8 col-lg-6">
	                                                <input type="text" required="" class="form-control" id="optionName">
	                                                <input type="hidden" required="" class="form-control" id="optionPk" name="optionFk">
	                                                
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> ????????? ?????? </label>
	                                            <div class="col-9 col-sm-7 col-lg-4">
	                                                <label class="custom-control custom-radio custom-control-inline">
	                                                	<input type="radio" value="0" name="fbAddType" checked class="custom-control-input"><span class="custom-control-label"> ???????????? </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" value="1" name="fbAddType" class="custom-control-input"><span class="custom-control-label"> ???????????? </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" value="2" name="fbAddType" class="custom-control-input"><span class="custom-control-label"> ???????????? x ?????? </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" value="3" name="fbAddType" class="custom-control-input" id="multiType"><span class="custom-control-label"> ?????? </span>
		                                            </label>
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2">
	                                                <input type="number" required="" name="fbAddQty" placeholder="??????" class="form-control" value="0">
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                            <div class="card">
	                                <h5 class="card-header"> ????????? ?????? ?????? </h5>
	                                <div class="card-body">
	                                    <div id="validationform">
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right" id="totalPrice"> ?????? ?????? </label>
	                                            <div class="col-6 col-sm-4 col-lg-3">
	                                                <input type="number" placeholder="?????? ??????" id="fbMinPrice" name="fbMinPrice" class="form-control" value="0">
	                                            </div>
	                                            <div class="col-6 col-sm-4 col-lg-3" id="maxTotalPrice">
	                                                <input type="number" placeholder="?????? ??????" id="fbMaxPrice" name="fbMaxPrice" class="form-control" value="0">
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right" id="totalQty"> ?????? ?????? </label>
	                                            <div class="col-6 col-sm-4 col-lg-2">
	                                                <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="fbTotalQtyFlag" value="0" checked class="custom-control-input"><span class="custom-control-label"> ????????? </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" name="fbTotalQtyFlag" value="1" class="custom-control-input"><span class="custom-control-label"> ?????? </span>
		                                            </label>
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2">
	                                                <input type="number" placeholder="?????? ??????" id="fbMinTotalQty" name="fbMinTotalQty" class="form-control" readonly="readonly" value="0">
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2" id="maxTotalQty">
	                                                <input type="number" placeholder="?????? ??????" id="fbMaxTotalQty" name="fbMaxTotalQty" class="form-control" readonly="readonly" value="0">
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                            <div class="card">
	                                <h5 class="card-header"> 
	                                	?????? ?????? 
	                                	<button type="button" class="fas fa-search" data-toggle="tooltip" data-placement="top" title="????????? ?????? ?????? 1??? ????????? ,??? ???????????? ???????????? ????????? ?????? ??? ????????????"></button>
	                                </h5>
	                                <div class="card-body">
	                                    <div id="validationform">
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> ????????? ?????? ?????? 1 </label>
	                                            <div class="col-6 col-sm-4 col-lg-2">
	                                                <label class="custom-control custom-radio custom-control-inline">
	                                                	<input type="radio" name="fbAnotherCheckFlag" checked class="custom-control-input" value="0"><span class="custom-control-label"> ????????? </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" name="fbAnotherCheckFlag" class="custom-control-input" value="1"><span class="custom-control-label"> ?????? </span>
		                                            </label>
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2">
	                                                <select class="form-control" id="fbAnotherCheckList" name="fbAnotherCheckList" disabled="disabled">
	                                                	<option value="0">????????? ?????????</option>
	                                                	<option value="1">????????? ?????????</option>
	                                                	<option value="2">????????? ????????????</option>
	                                                	<option value="3">?????? ?????????</option>
	                                                	<option value="4">?????? ?????????</option>
	                                                	<option value="5">??????</option>
	                                                	<option value="6">???????????????1</option>
	                                                	<option value="7">???????????????2</option>
	                                                	<option value="8">???????????????3</option>
	                                                </select>
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2">
	                                                <input type="text" placeholder="?????????" id="fbAnotherCheckWord" name="fbAnotherCheckWord" class="form-control" readonly="readonly">
	                                            </div>
	                                             <div class="col-3 col-sm-2 col-lg-2">
	                                                <select class="form-control" id="fbAnotherCheckType" name="fbAnotherCheckType" disabled="disabled">
	                                                	<option value="0">?????? ?????? ??????</option>
	                                                	<option value="1">?????? ???</option>
	                                                	<option value="2">??????</option>
	                                                	<option value="3">?????? ??????</option>
	                                                </select>
	                                            </div>
	                                        </div>
	                                        
	                                        
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> ????????? ?????? ?????? 2 </label>
	                                            <div class="col-6 col-sm-4 col-lg-2">
	                                                <label class="custom-control custom-radio custom-control-inline">
	                                                	<input type="radio" name="fbAnotherCheckFlag2" checked class="custom-control-input" value="0"><span class="custom-control-label"> ????????? </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" name="fbAnotherCheckFlag2" class="custom-control-input" value="1"><span class="custom-control-label"> ?????? </span>
		                                            </label>
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2">
	                                                <select class="form-control" id="fbAnotherCheckList2" name="fbAnotherCheckList2" disabled="disabled">
	                                                	<option value="0">????????? ?????????</option>
	                                                	<option value="1">????????? ?????????</option>
	                                                	<option value="2">????????? ????????????</option>
	                                                	<option value="3">?????? ?????????</option>
	                                                	<option value="4">?????? ?????????</option>
	                                                	<option value="5">??????</option>
	                                                	<option value="6">???????????????1</option>
	                                                	<option value="7">???????????????2</option>
	                                                	<option value="8">???????????????3</option>
	                                                </select>
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2">
	                                                <input type="text" placeholder="?????????" id="fbAnotherCheckWord2" name="fbAnotherCheckWord2" class="form-control" readonly="readonly">
	                                            </div>
	                                             <div class="col-3 col-sm-2 col-lg-2">
	                                                <select class="form-control" id="fbAnotherCheckType2" name="fbAnotherCheckType2" disabled="disabled">
	                                                	<option value="0">?????? ?????? ??????</option>
	                                                	<option value="1">?????? ???</option>
	                                                	<option value="2">??????</option>
	                                                	<option value="3">?????? ??????</option>
	                                                </select>
	                                            </div>
	                                        </div>
	                                        
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                    		<div class="offset-4 col-2 col-sm-3">	                    		
			                    	<button class="btn btn-primary btn-block"> ????????? ???????????? </button>
	                    		</div>
	                    </div>
                   </form>
           		</div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>