<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
			
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
                            <h2 class="pageheader-title"> 거래처 상세사항 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 세금계산서</a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 거래처 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 상세사항 </li>
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
                                <h5 class="card-header"> ${rcVO.rcName } 상세사항 </h5>
                                <div class="card-body">
                                    <form id="insertResCompany" method="POST" action="<c:url value='/tax/res_company/read.do'/>">
                                    	<input type="hidden" name="rcPk" value="${rcVO.rcPk }">
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 거래처 명 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="rcName" placeholder="거래처 명을 입력해주세요" class="form-control" value="${rcVO.rcName }">
                                            </div>
                                        </div>
                                        
										<div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 사업자등록번호 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="rcNum" class="form-control" placeholder="사업자등록번호를 입력해주세요" value="${rcVO.rcNum }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 거래처 연락처 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="rcContractNum" class="form-control" placeholder="거래처 연락처를 입력해주세요" value="${rcVO.rcContractNum }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 비고 1</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="rcRemark1" class="form-control" value="${rcVO.rcRemark1 }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 비고 2</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="rcRemark2" class="form-control" value="${rcVO.rcRemark2 }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 비고 3</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="rcRemark3" class="form-control" value="${rcVO.rcRemark3 }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 비고 4</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="rcRemark4" class="form-control" value="${rcVO.rcRemark4 }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 비고 5</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="rcRemark5" class="form-control" value="${rcVO.rcRemark5 }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 비고6</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="rcRemark6" class="form-control" value="${rcVO.rcRemark6 }">
                                            </div>
                                        </div>
                                        <div class="form-group row text-right">
                                            <div class="col col-sm-10 col-lg-9 offset-sm-1 offset-lg-0">
                                                <button id="resCompanyUpdateBtn" type="submit" class="btn btn-space btn-success"> 수정 하기 </button>
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
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>