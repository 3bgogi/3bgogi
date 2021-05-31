<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../../inc/top.jsp" %>
    <%@ include file="../../../inc/top_nav.jsp" %>
    <script type="text/javascript">

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
                                                <a class="nav-link active" href="<c:url value='/config/store/info.do?ssPk=${ssVO.ssPk  }'/>"> 판매처 정보 변경</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" href="<c:url value='/config/store/merge.do?ssPk=${ssVO.ssPk  }'/>"> 판매처 묶음 정리 변경</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="card-body">
                                        <div class="tab-content" id="myTabContent">
                                            <div class="tab-pane fade show active">
                                            	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						                            <div class="card">
				                                        <div class="card-header">
				                                            <h4 class="mb-0"> 판매처 정보 변경  </h4>
				                                        </div>
				                                        <div class="card-body">
				                                            <form class="needs-validation" id="storeInfoChange" name="storeInfoChange" action="<c:url value='/config/store/info_change.do'/>" method="post">
				                                            	<input type="hidden" name="ssPk" id="ssPk" value="${ssVO.ssPk }">
				                                            	
				                                            	<div class="row">
				                                                    <div class="col-md-12 mb-3">
				                                                        <label for="firstName"> 판매처명 </label>
				                                                        <input type="text" class="form-control" name="ssName" id="ssName" placeholder="" value="${ssVO.ssName }" required>
				                                                        <div class="invalid-feedback">
				
				                                                        </div>
				                                                    </div>
				                                                </div>
				                                                <div class="row">
				                                                    <div class="col-md-12 mb-3">
				                                                        <label for="firstName"> 판매처별칭 </label>
				                                                        <input type="text" class="form-control" name="ssStoreNickname" id="ssStoreNickname" placeholder="" value="${ssVO.ssStoreNickname }" required>
				                                                        <div class="invalid-feedback">
				
				                                                        </div>
				                                                    </div>
				                                                </div>
				                                                <div class="row">
				                                                    <div class="col-md-12 mb-3">
				                                                        <label for="firstName"> 판매처 아이디</label>
				                                                        <input type="text" class="form-control" name="ssStoreId" id="ssStoreId" placeholder="" value="${ssVO.ssStoreId }">
				                                                        <div class="invalid-feedback">
				
				                                                        </div>
				                                                    </div>
				                                                </div>
				                                                <div class="row">
				                                                    <div class="col-md-12 mb-3">
				                                                        <label for="firstName"> 판매처 비밀번호 </label>
				                                                        <input type="password" class="form-control" name="ssStorePassword" id="ssStorePassword" placeholder="" value="${ssVO.ssStorePassword }">
				                                                        <div class="invalid-feedback">
				
				                                                        </div>
				                                                    </div>
				                                                </div>
				                                                <div class="row">
				                                                    <div class="col-md-12 mb-3">
				                                                        <label for="firstName"> 인증키 ( Auth-key )</label>
				                                                        <input type="text" class="form-control" name="ssAuthKey" id="ssAuthKey" placeholder="" value="${ssVO.ssAuthKey }">
				                                                        <div class="invalid-feedback">
				
				                                                        </div>
				                                                    </div>
				                                                </div>
				                                                <div class="row">
				                                                    <div class="col-md-12 mb-3">
				                                                        <label for="firstName"> 판매처 기본 수수료 </label>
				                                                        <input type="text" class="form-control" name="ssCommission" id="ssCommission" placeholder="" value="${ssVO.ssCommission }">
				                                                        <div class="invalid-feedback">
				
				                                                        </div>
				                                                    </div>
				                                                </div>
				                                                <div class="row">
				                                                    <div class="col-md-12 mb-3">
				                                                        <label for="firstName"> 판매처 주소 ( http:// https:// 까지 다 적어야함)</label>
				                                                        <input type="text" class="form-control" name="ssStoreUrl" id="ssStoreUrl" placeholder="" value="${ssVO.ssStoreUrl }">
				                                                        <div class="invalid-feedback">
				
				                                                        </div>
				                                                    </div>
				                                                </div>
				                                                <hr class="mb-4">
				                                                <button class="btn btn-primary btn-lg btn-block" type="submit"> 정보 변경</button>
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
        <!-- /page content -->
        <%@ include file="../../../inc/bottom.jsp" %>