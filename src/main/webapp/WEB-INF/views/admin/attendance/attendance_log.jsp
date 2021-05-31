<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		

    		$(".showAADetail").click(function(){
    			var adminPk = $(this).val();
    			var adminWorktime = $(this).next().val();
    			var workStart = $("#workYear").val();
    			var workEnd = $("#workMonth").val();
    			
    			location.href = "<c:url value='/admin/team/team_info_detail.do?adminPk="+adminPk+"'/>";
    			
    		});
    		
    		
    		
    	});
    </script>
        <!-- page content -->
        <!-- ============================================================== -->
        <!-- wrapper  -->
        <!-- ============================================================== -->
        <div class="dashboard-wrapper">
        <div class="influence-finder">
                <div class="container-fluid dashboard-content">
                    <!-- ============================================================== -->
                    <!-- pageheader -->
                    <!-- ============================================================== -->
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="page-header">
                                <h3 class="mb-2"> 출퇴근 기록 확인  </h3>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 보안 </a></li>
                                            <li class="breadcrumb-item active" aria-current="page"> 출퇴근기록 </li>
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
                    	<div class="col-xl-3 col-lg-4 col-md-4 col-sm-12 col-12">
                                <div class="card">
                                    <div class="card-body">
                                        <h3 class="font-16"> 년도 선택 </h3>
                                        <select name="workYear" id="workYear" class="form-control">
                                            <c:forEach var="aaYearAndMonthlist" items="${aaYearAndMonthList }">
                                            	<option value="${aaYearAndMonthlist.workYear }">${aaYearAndMonthlist.workYear }</option>
                                            </c:forEach>
                                        </select>
                                        
                                        <hr>
                                        
                                        <h3 class="font-16"> 월 선택 </h3>
                                        <select name="workMonth" id="workMonth" class="form-control">
                                            <c:forEach var="aaYearAndMonthlist" items="${aaYearAndMonthList }">
                                            	<option value="${aaYearAndMonthlist.workMonth }">${aaYearAndMonthlist.workMonth }</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        <div class="col-xl-9 col-lg-8 col-md-8 col-sm-12 col-12">
							<c:forEach var="adminvolist" items="${adminvoList }">
								<div class="card">
                                <div class="card-body">
                                    <div class="row align-items-center">
                                        <div class="col-xl-9 col-lg-8 col-md-8 col-sm-12 col-12">
                                            <div class="user-avatar float-xl-left pr-4 float-none">
                                                <img src="${pageContext.request.contextPath}/resources/images/3bgogi_icon.png" alt="User Avatar" class="rounded-circle user-avatar-xl">
                                                    </div>
                                                <div class="pl-xl-3">
                                                    <div class="m-b-0">
                                                        <div class="user-avatar-name d-inline-block">
                                                            <h2 class="font-24 m-b-10">${adminvolist.adminName } </h2>
                                                        </div>
                                                    </div>
                                                    <div class="user-avatar-address">
                                                        <p class="mb-2"><i class="fa fa-map-marker-alt mr-2  text-primary"></i>${adminvolist.adminAddress } <span class="m-l-10"><span class="m-l-20">${adminvolist.adminPhone }</span></span>
                                                        </p>
                                                        <p class="mb-1">출근 시각 : ${adminvolist.adminWorktime } , 부서 : ${adminvolist.jcType } </p>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xl-3 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <div class="float-xl-right float-none mt-xl-0 mt-4">
                                                    <button type="button" class="btn btn-primary showAADetail" value="${adminvolist.adminPk }" >출퇴근 기록 보기</button>
                                                    <input type="hidden" value="${adminvolist.adminWorktime }" name="workTimes">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
							</c:forEach>
                            
                                <!-- ============================================================== -->
                                <!-- end content -->
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
		<%@ include file="../../inc/bottom.jsp" %>