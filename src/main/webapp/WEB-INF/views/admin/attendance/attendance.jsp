<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script src="${pageContext.request.contextPath}/resources/libs/js/renewal_attendance_manage.js"></script>
   
    <style type="text/css">
    	.cal_top{
		    text-align: center;
		    font-size: 30px;
		}
		.cal{
		    text-align: center; 
		}
		table.calendar{
		    border: 1px solid black;
		    display: inline-table;
		    text-align: left;
		}
		table.calendar td{
		    vertical-align: top;
		    border: 1px solid skyblue;
		    width: 100px;
		}
    </style>
        <!-- page content -->
        <!-- ============================================================== -->
        <!-- wrapper  -->
        <!-- ============================================================== -->
        <div class="dashboard-wrapper">
        <div class="influence-profile">
                <div class="container-fluid dashboard-content ">
                    <!-- ============================================================== -->
                    <!-- pageheader -->
                    <!-- ============================================================== -->
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="page-header">
                                <h3 class="mb-2"> 개인 출결 관리  </h3>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">개인</a></li>
                                            <li class="breadcrumb-item active" aria-current="page"> 출결 관리 </li>
                                        </ol>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ============================================================== -->
                    <!-- end pageheader -->
                    <!-- ============================================================== -->
                    <!-- ============================================================== -->
                    <!-- content -->
                    <!-- ============================================================== -->
                    <div class="row">
                        <!-- ============================================================== -->
                        <!-- profile -->
                        <!-- ============================================================== -->
                        <div class="col-xl-3 col-lg-3 col-md-5 col-sm-12 col-12">
                            <!-- ============================================================== -->
                            <!-- card profile -->
                            <!-- ============================================================== -->
                            <div class="card">
                                <div class="card-body">
                                    <div class="user-avatar text-center d-block">
                                        <img src="${pageContext.request.contextPath}/resources/images/3bgogi_icon.png" alt="User Avatar" class="rounded-circle user-avatar-xxl">
                                    </div>
                                    <div class="text-center">
                                        <h2 class="font-24 mb-0"> ${adminVo.adminname }</h2>
                                        <input type="hidden" value="${adminVo.adminPk }" name="adminPk">
                                        <input type="hidden" value="${aaVO.aaPk }" name="aaPk" >
                                    </div>
                                </div>
                                <div class="card-body border-top">
                                    <h3 class="font-16"> 연락처 </h3>
                                    <div class="">
	                                    <ul class="list-unstyled mb-0">
	                                        <li class="mb-0"><i class="fas fa-fw fa-phone mr-2"></i> ${adminVo.adminPhone }</li>
	                                    </ul>
	                                    <ul class="list-unstyled mb-0">
	                                        <li class="mb-0"><i class="fas fa-fw fa-home mr-2"></i> ${adminVo.adminAddress }</li>
	                                    </ul>
	                                    <ul class="list-unstyled mb-0">
	                                        <li class="mb-0"><i class="fas fa-fw fa-bell mr-2"></i> 아침 출근 시각 : ${adminVo.adminWorktime }</li>
	                                    </ul>
                                    </div>
                                </div>
                                <div class="card-body border-top">
                                    <h3 class="font-16"> 출결 관리 </h3>
                                    <div class="form-control">
                                    	<c:if test="${empty aaVO.aaWorkStart }">
                                    		<button type="button" class="btn btn-primary btn-block" name="startWork"> 출근 </button>
                                    	</c:if>
                                    	<c:if test="${!empty aaVO.aaWorkStart and empty aaVO.aaWorkEnd }">
                                    		<button type="button" class="btn btn-primary btn-block" name="endWork"> 퇴근 </button>
                                    	</c:if>
                                    	<c:if test="${!empty aaVO.aaWorkStart and !empty aaVO.aaWorkEnd }">
                                    		<button type="button" class="btn btn-primary btn-block"> 수고하셨습니다^^ </button>
                                    	</c:if>
                                    </div>
                                </div>
                            </div>
                            <!-- ============================================================== -->
                            <!-- end card profile -->
                            <!-- ============================================================== -->
                        </div>
                        <!-- ============================================================== -->
                        <!-- end profile -->
                        <!-- ============================================================== -->
                        <!-- ============================================================== -->
                        <!-- campaign data -->
                        <!-- ============================================================== -->
                        <div class="col-xl-9 col-lg-9 col-md-7 col-sm-12 col-12">
                            <!-- ============================================================== -->
                            <!-- campaign tab one -->
                            <!-- ============================================================== -->
                            <div class="influence-profile-content pills-regular">
                                <ul class="nav nav-pills mb-3 nav-justified" id="pills-tab" role="tablist">
                                	<li class="nav-item">
                                        <a class="nav-link active" id="pills-review-tab" data-toggle="pill" href="#pills-review" role="tab" aria-controls="pills-review" aria-selected="true"> 종합 </a>
                                    </li>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
	                                    <li class="nav-item">
	                                        <a class="nav-link" id="breaks-tab" data-toggle="pill" href="#breaks" role="tab" aria-controls="breaks" aria-selected="false"> 휴가신청 </a>
	                                    </li>
                                    </sec:authorize>
                                </ul>
                                <div class="tab-content" id="pills-tabContent">
                                    <div class="tab-pane fade show active" id="pills-review" role="tabpanel" aria-labelledby="pills-review-tab">
                                        <!-- 종합 기록 -->
                                        <div class="card">
				                            <h5 class="card-header"> 종합 출결 결과 </h5>
				                            <div class="card-body">
				                                <div class="cal_top">
											        <a href="#" id="movePrevMonth"><span id="prevMonth" class="cal_tit">&lt;</span></a>
											        <span id="cal_top_year"></span>
											        <span id="cal_top_month"></span>
											        <a href="#" id="moveNextMonth"><span id="nextMonth" class="cal_tit">&gt;</span></a>
											    </div>
											    <div id="cal_tab" class="cal">
											    </div>
				                            </div>
				                        </div>
                                    </div>
                                    <!-- 휴가 신청 제작 -->
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
	                                    <div class="tab-pane fade" id="breaks" role="tabpanel" aria-labelledby="breaks-tab">
	                                        <p>제작중</p>
	                                    </div>
                                    
                                    </sec:authorize>
                                    <!-- 휴가 신청 제작 -->
                                </div>
                            </div>
                            <!-- ============================================================== -->
                            <!-- end campaign tab one -->
                            <!-- ============================================================== -->
                        </div>
                        <!-- ============================================================== -->
                        <!-- end campaign data -->
                        <!-- ============================================================== -->
                    </div>
                </div>
            </div>
        <!-- /page content -->
        <!--모달   -->
				<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel"> 개별 출퇴근 기록 </h5>
								<a href="#" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</a>
							</div>
							<div class="modal-body">
								<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
									<div class="card">
										<div class="card-body">
											<table class="table table-hover">
												<thead>
													<tr>
														<th scope="col">출근일</th>
														<th scope="col">출근</th>
														<th scope="col">퇴근</th>
														<th scope="col">근무시간</th>
													</tr>
												</thead>
												<tbody class="aaContent">

												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<a href="#" class="btn btn-secondary" data-dismiss="modal"> 닫기 </a>
							</div>
						</div>
					</div>
				</div>
			 <script type="text/javascript">

    	$(function(){
    		
			$("button[name=startWork]").click(function(){
				var checkWork = 1;
				var aaPk = 0;
				var adminPk = $("input[name=adminPk]").val();
				location.href="<c:url value='/admin/attendance_check.do?checkWork="+checkWork+"&aaPk="+aaPk+"&adminPk="+adminPk+"'/>";
			});

			$("button[name=endWork]").click(function(){
				var checkWork = 2;
				var aaPk = $("input[name=aaPk]").val();
				var adminPk = $("input[name=adminPk]").val();
				location.href="<c:url value='/admin/attendance_check.do?checkWork="+checkWork+"&aaPk="+aaPk+"&adminPk="+adminPk+"'/>";
			});
    		
    	});
    </script>
        <%@ include file="../../inc/bottom.jsp" %>