<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		$("#addIroOrders").submit(function(){
    			
    			var iroName = $("#iroName").val();
    			var iroPhoneNumber = $("#iroPhoneNumber").val();
    			var iroDetail = $("#iroDetail").val();
    			
    			if(iroName == ''){
    				alert("구매자명이 입력되지 않았습니다 ");
    				$("#iroName").focus();
    				event.preventDefault();
    				return false;
    				
    			}else if(iroPhoneNumber == ''){
    				alert("구매자 전화번호가 입력되지 않았습니다");
    				$("#iroPhoneNumber").focus();
    				event.preventDefault();
    				return false;
    				
    			}else if(iroDetail == ''){
    				alert("확인 사항이 입력되지 않았습니다 ");
    				$("#iroDetail").focus();
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
                            <h2 class="pageheader-title"> 구매자 필터링 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 주문서 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 고객 필터링 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 추가 </li>
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
                        <!-- justified tabs  -->
                        <!-- ============================================================== -->
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mb-5">
                            <div class="section-block">
                                <h5 class="section-title"> 추가하기 </h5>
                                <p> 주문서 입력이 들어올 때에 구매자명, 핸드폰 번호로 필터링 하여 체크하는 기능 - 구매자 추가하기 </p>
                            </div>
                            <div class="tab-regular">
                                <ul class="nav nav-tabs nav-fill" id="myTab7" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link" id="home-tab-justify" style="cursor: pointer;" href="<c:url value='/orders/irregular/list.do'/>"> 목록 </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="profile-tab-justify" style="cursor: pointer;" href="<c:url value='/orders/irregular/all_list.do'/>"> 전체 목록</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link active" id="contact-tab-justify" style="cursor: pointer;"> 추가하기 </a>
                                    </li>
                                </ul>
                                <div class="tab-content" id="myTabContent7">
                                    <div class="tab-pane fade show active" id="home-justify" role="tabpanel" aria-labelledby="home-tab-justify">
                                    	<div class="offset-xl-2 col-xl-8 col-lg-12 col-md-12 col-sm-12 col-12">
	                                        <div class="card">
		                                        <div class="card-body">
		                                            <form class="needs-validation" id="addIroOrders" name="addIroOrders" action="" method="post">
		                                                <div class="row">
		                                                    <div class="col-md-6 mb-3">
		                                                        <label for="firstName"> 구매자명 </label>
		                                                        <input type="text" class="form-control" id="iroName" name="iroName" placeholder="" value="" required="">
		                                                    </div>
		                                                    <div class="col-md-6 mb-3">
		                                                        <label for="lastName"> 구매자 번호 </label>
		                                                        <input type="text" class="form-control" id="iroPhoneNumber" name="iroPhoneNumber" placeholder="" value="" required="">
		                                                    </div>
		                                                </div>
		                                                <div class="mb-3">
		                                                    <label for="email"> 확인사항 </label>
		                                                    <input type="text" class="form-control" id="iroDetail" name="iroDetail" placeholder="확인해야 될 사항을 입력해주세요. ">
		                                                </div>
		                                                <div class="row">
		                                                    <div class="col-md-5 mb-3">
		                                                        <label for="country"> 판매처 선택 </label>
		                                                        <select class="custom-select d-block w-100" id="ssFk" name="ssFk">
		                                                            <c:if test="${!empty storeList }">
		                                                            	<c:forEach var="storelist" items="${storeList }">
		                                                            		<option value="${storelist.ssPk }">${storelist.ssName }</option>
		                                                            	</c:forEach>
		                                                            </c:if>
		                                                            <c:if test="${empty storeList }">
		                                                            	<option value="0"> 현재 등록된 판매처가 존재 하지 않습니다. </option>
		                                                            </c:if>
		                                                        </select>
		                                                    </div>
		                                                </div>
		                                                <hr class="mb-4">
		                                                <button class="btn btn-primary btn-lg btn-block" type="submit"> 필터링 추가하기 </button>
		                                            </form>
		                                        </div>
		                                    </div>
	                                    </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- ============================================================== -->
                        <!-- end justified tabs  -->
                        <!-- ============================================================== -->
                    </div>
              
            </div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>