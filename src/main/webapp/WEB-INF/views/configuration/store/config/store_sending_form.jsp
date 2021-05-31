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
                                                <a class="nav-link active" href="<c:url value='/config/store/sending_form.do?ssPk=${ssVO.ssPk }'/>"> 판매처 발송 엑셀 설정</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" href="<c:url value='/config/store/info.do?ssPk=${ssVO.ssPk  }'/>"> 판매처 정보 변경</a>
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
						                                <div class="p-3 mb-2 bg-danger text-white text-center">
						                                	해당 설정값은 개발자 혹은 관련 지식이 있는 사람만이 변경가능합니다
						                                </div>
						                                <div class="card-body">
						                                    <form id="validationform" data-parsley-validate="" novalidate="" action="<c:url value='/config/store/sending_form.do'/>" method="POST">
						                                    	<input type="hidden" name="ssPk" value="${ssVO.ssPk }">
						                                        <div class="form-group row">
						                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 엑셀 제목 설정</label>
						                                            <div class="col-12 col-sm-8 col-lg-6">
						                                                <textarea required="" name="ssSendingHeadForm" class="form-control">${ssVO.ssSendingHeadForm }</textarea>
						                                            </div>
						                                        </div>
						                                        <div class="form-group row">
						                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 엑셀 데이터값 설정 </label>
						                                            <div class="col-12 col-sm-8 col-lg-6">
						                                                <textarea required="" name="ssSendingBodyForm" class="form-control">${ssVO.ssSendingBodyForm }</textarea>
						                                            </div>
						                                        </div>
						                                        <div class="form-group row">
						                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 엑셀 데이터값 그룹화 설정 </label>
						                                            <div class="col-12 col-sm-8 col-lg-6">
						                                                <textarea required="" name="ssSendingGroupForm" class="form-control">${ssVO.ssSendingGroupForm }</textarea>
						                                            </div>
						                                        </div>
						                                        <div class="form-group row text-right">
						                                            <div class="col col-sm-10 col-lg-9 offset-sm-1 offset-lg-0">
						                                                <button type="submit" class="btn btn-space btn-primary">변경하기</button>
						                                            </div>
						                                        </div>
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