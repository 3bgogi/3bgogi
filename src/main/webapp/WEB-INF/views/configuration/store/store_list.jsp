<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <style type="text/css">
    	.renewal-store-list{
    		cursor: pointer;
    	}
    </style>
    <script type="text/javascript">
    	$(function(){
    		
    		$('form[name=addStores]').submit(function(){
    			var v_ssName = $("#v_ssName").val();
    			var v_ssStoreNickname = $("#v_ssStoreNickname").val();
    			var v_ssStoreId = $("#v_ssStoreId").val();
    			var v_ssStorePassword = $("#v_ssStorePassword").val();
    			var v_ssStorePasswordDupl = $("#v_ssStorePasswordDupl").val();
    			var v_ssAuthKey = $("#v_ssAuthKey").val();
    			var v_ssCommission = $("#v_ssCommission").val();
    			var v_ssStoreUrl = $("#v_ssStoreUrl").val();
    			
    			if(ssName == ''){
    				alert("판매처명이 입력되지 않았습니다");
    				$("#v_ssStoreId").focus();
    				
    				return false;
    				
    			}else if(v_ssStorePassword != ''){
    				
    				if(v_ssStorePassword != v_ssStorePasswordDupl){
    					alert("두 비밀번호가 맞지 않습니다.");
    					$("#v_ssStorePassword").focus();
    					
    					return false;
    				}
    				
    			}else if(v_ssStoreNickname == ''){
    				
    				alert("판매처 별칭이 입력되지 않았습니다");
    				$("#v_ssStoreNickname").focus();
    				
    				return false;
    				
    			}
    			
    			$("#ssName").val(v_ssName);
    			$("#ssStoreNickname").val(v_ssStoreNickname);
    			$("#ssStoreId").val(v_ssStoreId);
    			$("#ssStorePassword").val(v_ssStorePassword);
    			$("#ssAuthKey").val(v_ssAuthKey);
    			$("#ssCommission").val(v_ssCommission);
    			$("#ssStoreUrl").val(encodeURI(v_ssStoreUrl));

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
                                <h2 class="pageheader-title"> 판매처 목록 및 추가 </h2>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 설정 </a></li>
                                            <li class="breadcrumb-item active" aria-current="page"> 판매처 설정 </li>
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
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="row">
                            	<div class="offset-md-2 col-md-4 mb-4">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4 class="d-flex justify-content-between align-items-center mb-0">
                                                        <span class="text-muted">등록된 판매처</span>
                                          <span class="badge badge-secondary badge-pill">2</span>
                                                 </h4>
                                        </div>
                                        <div class="card-body">
                                            <ul class="list-group mb-3">
                                            	<c:if test="${!empty ssList }">
                                            		<c:forEach var="sslist" items="${ssList }">
                                            			<li class="list-group-item d-flex justify-content-between">
		                                                    <div>
		                                                        <a class="my-0 renewal-store-list" href="<c:url value='/config/store/excel_config.do?ssPk=${sslist.ssPk }'/>">${sslist.ssName }</a>
		                                                    </div>
		                                                    <span class="text-muted"> <fmt:formatDate value="${sslist.ssRegdate }" pattern="yyyy-MM-dd"/> </span>
		                                                </li>
                                            		</c:forEach>
                                            	</c:if>
                                            	<c:if test="${empty ssList }">
                                            		<li class="list-group-item d-flex justify-content-between">
		                                                    <div>
		                                                        <h6 class="my-0"> 등록된 판매처가 없습니다. </h6>
		                                                    </div>
		                                                </li>
                                            	</c:if>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4 class="mb-0"> 판매처 추가하기  </h4>
                                        </div>
                                        <div class="card-body">
                                            <form class="needs-validation" id="addStores" name="addStores" action="<c:url value='/config/store/add_store.do'/>" method="post">
                                            	<input type="hidden" name="ssName" id="ssName">
                                            	<input type="hidden" name="ssStoreNickname" id="ssStoreNickname">
                                            	<input type="hidden" name="ssStoreId" id="ssStoreId">
                                            	<input type="hidden" name="ssStorePassword" id="ssStorePassword">
                                            	<input type="hidden" name="ssAuthKey" id="ssAuthKey">
                                            	<input type="hidden" name="ssCommission" id="ssCommission">
                                            	<input type="hidden" name="ssStoreUrl" id="ssStoreUrl">
                                            	<div class="row">
                                                    <div class="col-md-12 mb-3">
                                                        <label for="firstName"> 판매처명 </label>
                                                        <input type="text" class="form-control" name="v_ssName" id="v_ssName" placeholder="" value="" required>
                                                        <div class="invalid-feedback">

                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12 mb-3">
                                                        <label for="firstName"> 판매처별칭 </label>
                                                        <input type="text" class="form-control" name="v_ssStoreNickname" id="v_ssStoreNickname" placeholder="" value="" required>
                                                        <div class="invalid-feedback">

                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12 mb-3">
                                                        <label for="firstName"> 판매처 아이디</label>
                                                        <input type="text" class="form-control" name="v_ssStoreId" id="v_ssStoreId" placeholder="" value="">
                                                        <div class="invalid-feedback">

                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12 mb-3">
                                                        <label for="firstName"> 판매처 비밀번호 </label>
                                                        <input type="password" class="form-control" name="v_ssStorePassword" id="v_ssStorePassword" placeholder="">
                                                        <div class="invalid-feedback">

                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12 mb-3">
                                                        <label for="firstName"> 판매처 비밀번호 확인 </label>
                                                        <input type="password" class="form-control" name="v_ssStorePasswordDupl" id="v_ssStorePasswordDupl" placeholder="">
                                                        <div class="invalid-feedback">

                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12 mb-3">
                                                        <label for="firstName"> 인증키 ( Auth-key )</label>
                                                        <input type="text" class="form-control" name="v_ssAuthKey" id="v_ssAuthKey" placeholder="">
                                                        <div class="invalid-feedback">

                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12 mb-3">
                                                        <label for="firstName"> 판매처 기본 수수료 </label>
                                                        <input type="text" class="form-control" name="v_ssCommission" id="v_ssCommission" placeholder="" value="0">
                                                        <div class="invalid-feedback">

                                                        </div>
                                                    </div>
                                                </div>
                                                <h4 class="mb-3"> 데이터 자동화 여부 </h4>
                                                <div class="d-block my-3">
                                                    <div class="custom-control custom-radio">
                                                        <input id="credit" name="ssAutoInsert" type="radio" class="custom-control-input" value="1" checked="checked">
                                                        <label class="custom-control-label" for="credit"> 자동 </label>
                                                    </div>
                                                    <div class="custom-control custom-radio">
                                                        <input id="debit" name="ssAutoInsert" type="radio" class="custom-control-input" value="0" required="">
                                                        <label class="custom-control-label" for="debit"> 수동 </label>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12 mb-3">
                                                        <label for="firstName"> 판매처 주소 ( http:// https:// 까지 다 적어야함)</label>
                                                        <input type="text" class="form-control" name="v_ssStoreUrl" id="v_ssStoreUrl" placeholder="">
                                                        <div class="invalid-feedback">

                                                        </div>
                                                    </div>
                                                </div>
                                                <hr class="mb-4">
                                                <button class="btn btn-primary btn-lg btn-block" type="submit"> 판매처 추가하기 </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>