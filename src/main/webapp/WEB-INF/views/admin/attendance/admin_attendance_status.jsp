<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
			$("button[name=teamInfoButton]").click(function(){
				
				var adminPk = $(this).val();
				
				location.href = "<c:url value='/admin/team/team_info_detail.do?adminPk="+adminPk+"'/>";
				
			});
			
			$("button[name=startWork]").on('click', function(){
				var checkWork = 1;
				var aaPk = 0;
				var adminPk = $("input[name=adminPk]").val();
				location.href="<c:url value='/admin/attendance_check.do?checkWork="+checkWork+"&aaPk="+aaPk+"&adminPk="+adminPk+"'/>";
			});

			$("button[name=endWork]").on('click', function(){
				if(confirm('퇴근 처리를 하시겠습니까?')){
					var checkWork = 2;
					var aaPk = $("input[name=aaPk]").val();
					var adminPk = $("input[name=adminPk]").val();
					location.href="<c:url value='/admin/attendance_check.do?checkWork="+checkWork+"&aaPk="+aaPk+"&adminPk="+adminPk+"'/>";
					
				}else{
					
				}
				
			});
    		
    		
    		
    	});
    </script>
    <style type="text/css">
    	.tab-content{
    		padding:10px !important;
    		
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
                                <h3 class="mb-2"> 팀별 출퇴근 기록  </h3>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 팀원 </a></li>
                                            <li class="breadcrumb-item active" aria-current="page"> 출퇴근 현황 </li>
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
                    	 <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mb-5">
                            <div class="card col-xl-4 col-lg-4 col-md-12 col-sm-12 col-12 mb-5 offset-xl-4 offset-lg-4">
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
                                <div class="card-body border-top" style="text-align: center;">
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
                                <div class="card-body border-top" style="text-align: center;">
                                    <h3 class="font-16"> 출결 관리 </h3>
                                    <div class="form-control">
                                    	<c:if test="${empty aaVO.aaWorkStart }">
                                    		<button type="button" class="btn btn-primary btn-block" name="startWork"> 출근 </button>
                                    	</c:if>
                                    	<c:if test="${!empty aaVO.aaWorkStart and empty aaVO.aaWorkEnd }">
                                    		<button type="button" class="btn btn-danger btn-block" name="endWork"> 퇴근 </button>
                                    	</c:if>
                                    	<c:if test="${!empty aaVO.aaWorkStart and !empty aaVO.aaWorkEnd }">
                                    		<button type="button" class="btn btn-primary btn-block"> 수고하셨습니다^^ </button>
                                    	</c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="section-block">
                                <h5 class="section-title"> 오늘 출퇴근 기록 확인</h5>
                                <p> 오늘의 출퇴 기록 및 휴가 현황을 알 수 있습니다. &nbsp;&nbsp;</p>
                                <p>
                                	<button type="button" class="btn btn-dark btn-sm" style="border-radius: 40px; background-color:white;" ></button> 출근 전&nbsp;
                                	<button type="button" class="btn btn-primary btn-sm" style="border-radius: 40px; background-color:#FFA2A2; border-color: #FFA2A2;" ></button> 출근  &nbsp;
	                                <button type="button" class="btn btn-success btn-sm" style="border-radius: 40px; background-color:#e9ba41; border-color: #e9ba41;" ></button> 퇴근 &nbsp;
	                                <button type="button" class="btn btn-info btn-sm" style="border-radius: 40px;" ></button> 휴가 &nbsp;
	                                <button type="button" class="btn btn-danger btn-sm" style="border-radius: 40px;" ></button> 휴무
                                </p>
                            </div>
                            <div class="pills-vertical">
                                <div class="row">
                                    <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12 col-12">
                                        <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                                            <a class="nav-link active" id="whole-team-tab" data-toggle="pill" href="#whole-team" role="tab" aria-controls="whole-team" aria-selected="true"> 팀 종합 기록</a>
                                            <a class="nav-link" id="manage-team-tab" data-toggle="pill" href="#manage-team" role="tab" aria-controls="manage-team" aria-selected="false"> 시스템 운영 개발 </a>
                                            <a class="nav-link" id="distribution-team-tab" data-toggle="pill" href="#distribution-team" role="tab" aria-controls="distribution-team" aria-selected="false"> 물류 운영 관리 </a>
                                            <a class="nav-link" id="marketing-team-tab" data-toggle="pill" href="#marketing-team" role="tab" aria-controls="marketing-team" aria-selected="false"> 디지털 마케팅 </a>
                                        </div>
                                    </div>
                                    <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 ">
                                        <div class="tab-content" id="v-pills-tabContent">
											<div class="tab-pane fade show active" id="whole-team" role="tabpanel" aria-labelledby="whole-team-tab">
												<div class="table-responsive">
													<table class="table table-striped table-bordered first">
														<thead style="text-align: center;">
															<tr>
																<th>현황</th>
																<th>이름</th>
																<th>일자</th>
																<th>출근</th>
																<th>퇴근</th>
																<th>상세</th>
															</tr>
														</thead>
														<tbody style="text-align: center;">
															<jsp:useBean id="toDay" class="java.util.Date"/>
															<fmt:formatDate var="toDayFormat" value="${toDay }" pattern="MM-dd"/>
															<c:set var="attendanceRank" value="1"></c:set>
															<c:forEach var="aalist" items="${aaList }">
																<tr>
																	<td>
																			<c:if test="${empty aalist.aaWorkStart and empty aalist.workBreaks}">
																				<button type="button" class="btn btn-dark btn-sm" style="border-radius: 40px; background-color:white;" ></button>
																			</c:if>
																			<c:if test="${empty aalist.aaWorkStart and !empty aalist.workBreaks}">
																				<button type="button" class="btn btn-danger btn-sm" style="border-radius: 40px;" ></button>
																			</c:if>
																			<c:if test="${!empty aalist.aaWorkStart and !empty aalist.workBreaks}">
																				<button type="button" class="btn btn-primary btn-sm" style="border-radius: 40px; background-color:#FFA2A2; border-color: #FFA2A2;" ></button>
																			</c:if>
																			
																			<c:if test="${!empty aalist.aaWorkStart and empty aalist.aaWorkEnd and empty aalist.workBreaks}">
																				<button type="button" class="btn btn-primary btn-sm" style="border-radius: 40px; background-color:#FFA2A2; border-color: #FFA2A2;" ></button> 
																			</c:if>
																			<c:if test="${!empty aalist.aaWorkStart and !empty aalist.aaWorkEnd and empty aalist.workBreaks}">
																				<button type="button" class="btn btn-success btn-sm" style="border-radius: 40px; background-color:#e9ba41; border-color: #e9ba41;" ></button>
																			</c:if>
																		</td>
																		
																	<td> ${aalist.adminName }</td>
																	<td>${toDayFormat }</td>
																	<td>
																		<c:if test="${!empty aalist.aaWorkStart }">
																			<fmt:formatDate value="${aalist.aaWorkStart }" pattern="HH:mm"/>
																			<c:if test="${attendanceRank < 4 }">																			
																				<br><img style="width:30px;" alt="출근순위" src="${pageContext.request.contextPath}/resources/images/attendance_image/number${attendanceRank}.png">
																			</c:if>
																			<c:set var="attendanceRank" value="${attendanceRank+1 }"></c:set>
																		</c:if>
																		<c:if test="${empty aalist.aaWorkStart and !empty aalist.workBreaks}">
																			휴무일 [${aalist.workBreaks }]
																		</c:if>
																		<c:if test="${empty aalist.aaWorkStart and empty aalist.workBreaks}">
																			-
																		</c:if>
																	</td>
																	<td>
																		<c:if test="${!empty aalist.aaWorkEnd }">
																			<fmt:formatDate value="${aalist.aaWorkEnd }" pattern="HH:mm"/>
																		</c:if>
																		<c:if test="${empty aalist.aaWorkEnd }">
																			-
																		</c:if>
																	</td>
																	<td><button type="button" class="btn btn-primary btn-block" value="${aalist.adminPk }" name="teamInfoButton"> 보기 </button></td>
																</tr>
															</c:forEach>
		
														</tbody>
													</table>
												</div>
											</div>
											<div class="tab-pane fade" id="manage-team" role="tabpanel" aria-labelledby="manage-team-tab">
                                            	<div class="table-responsive">
													<table class="table table-striped table-bordered first">
														<thead style="text-align: center;">
															<tr>
																<th>현황</th>
																<th>이름</th>
																<th>일자</th>
																<th>출근</th>
																<th>퇴근</th>
																<th>상세</th>
															</tr>
														</thead>
														<tbody style="text-align: center;">
															<c:forEach var="aalist" items="${aaList }">
																<c:if test="${aalist.jcFk == '3' }">																
																	<tr>
																		<td>
																			<c:if test="${empty aalist.aaWorkStart and empty aalist.workBreaks}">
																				<button type="button" class="btn btn-dark btn-sm" style="border-radius: 40px; background-color:white;" ></button>
																			</c:if>
																			<c:if test="${empty aalist.aaWorkStart and !empty aalist.workBreaks}">
																				<button type="button" class="btn btn-danger btn-sm" style="border-radius: 40px;" ></button>
																			</c:if>
																			<c:if test="${!empty aalist.aaWorkStart and !empty aalist.workBreaks}">
																				<button type="button" class="btn btn-primary btn-sm" style="border-radius: 40px; background-color:#FFA2A2; border-color: #FFA2A2;" ></button>
																			</c:if>
																			<c:if test="${!empty aalist.aaWorkStart and empty aalist.aaWorkEnd and empty aalist.workBreaks}">
																				<button type="button" class="btn btn-primary btn-sm" style="border-radius: 40px; background-color:#FFA2A2; border-color: #FFA2A2;" ></button> 
																			</c:if>
																			<c:if test="${!empty aalist.aaWorkStart and !empty aalist.aaWorkEnd and empty aalist.workBreaks}">
																				<button type="button" class="btn btn-success btn-sm" style="border-radius: 40px; background-color:#e9ba41; border-color: #e9ba41;" ></button>
																			</c:if>
																		</td>
																		<td> ${aalist.adminName }</td>
																		<td>${toDayFormat }</td>
																		<td>
																			<c:if test="${!empty aalist.aaWorkStart }">
																				<fmt:formatDate value="${aalist.aaWorkStart }" pattern="HH:mm"/>
																			</c:if>
																			<c:if test="${empty aalist.aaWorkStart and !empty aalist.workBreaks}">
																				휴무일 [${aalist.workBreaks }]
																			</c:if>
																			<c:if test="${empty aalist.aaWorkStart and empty aalist.workBreaks}">
																				-
																			</c:if>
																		</td>
																		<td>
																			<c:if test="${!empty aalist.aaWorkEnd }">
																				<fmt:formatDate value="${aalist.aaWorkEnd }" pattern="HH:mm"/>
																			</c:if>
																			<c:if test="${empty aalist.aaWorkEnd }">
																				-
																			</c:if>
																		</td>
																		<td><button type="button" class="btn btn-primary btn-block" value="${aalist.adminPk }" name="teamInfoButton"> 보기 </button></td>
																	</tr>
																</c:if>
															</c:forEach>
		
														</tbody>
													</table>
												</div>
                                            </div>
                                            <div class="tab-pane fade" id="distribution-team" role="tabpanel" aria-labelledby="distribution-team-tab">
                                                <div class="table-responsive">
													<table class="table table-striped table-bordered first">
														<thead style="text-align: center;">
															<tr>
																<th>현황</th>
																<th>이름</th>
																<th>일자</th>
																<th>출근</th>
																<th>퇴근</th>
																<th>상세</th>
															</tr>
														</thead>
														<tbody style="text-align: center;">
															<c:forEach var="aalist" items="${aaList }">
																<c:if test="${aalist.jcFk == '2' }">																
																	<tr>
																		<td>
																			<c:if test="${empty aalist.aaWorkStart and empty aalist.workBreaks}">
																				<button type="button" class="btn btn-dark btn-sm" style="border-radius: 40px; background-color:white;" ></button>
																			</c:if>
																			<c:if test="${empty aalist.aaWorkStart and !empty aalist.workBreaks}">
																				<button type="button" class="btn btn-danger btn-sm" style="border-radius: 40px;" ></button>
																			</c:if>
																			<c:if test="${!empty aalist.aaWorkStart and !empty aalist.workBreaks}">
																				<button type="button" class="btn btn-primary btn-sm" style="border-radius: 40px; background-color:#FFA2A2; border-color: #FFA2A2;" ></button>
																			</c:if>
																			<c:if test="${!empty aalist.aaWorkStart and empty aalist.aaWorkEnd and empty aalist.workBreaks}">
																				<button type="button" class="btn btn-primary btn-sm" style="border-radius: 40px; background-color:#FFA2A2; border-color: #FFA2A2;" ></button> 
																			</c:if>
																			<c:if test="${!empty aalist.aaWorkStart and !empty aalist.aaWorkEnd and empty aalist.workBreaks}">
																				<button type="button" class="btn btn-success btn-sm" style="border-radius: 40px; background-color:#e9ba41; border-color: #e9ba41;" ></button>
																			</c:if>
																		</td>
																		<td> ${aalist.adminName }</td>
																		<td>${toDayFormat }</td>
																		<td>
																			<c:if test="${!empty aalist.aaWorkStart }">
																				<fmt:formatDate value="${aalist.aaWorkStart }" pattern="HH:mm"/>
																			</c:if>
																			<c:if test="${empty aalist.aaWorkStart and !empty aalist.workBreaks}">
																				휴무일 [${aalist.workBreaks }]
																			</c:if>
																			<c:if test="${empty aalist.aaWorkStart and empty aalist.workBreaks}">
																				-
																			</c:if>
																		</td>
																		<td>
																			<c:if test="${!empty aalist.aaWorkEnd }">
																				<fmt:formatDate value="${aalist.aaWorkEnd }" pattern="HH:mm"/>
																			</c:if>
																			<c:if test="${empty aalist.aaWorkEnd }">
																				-
																			</c:if>
																		</td>
																		<td><button type="button" class="btn btn-primary btn-block" value="${aalist.adminPk }" name="teamInfoButton"> 보기 </button></td>
																	</tr>
																</c:if>
															</c:forEach>
		
														</tbody>
													</table>
												</div>
                                            </div>
                                            <div class="tab-pane fade" id="marketing-team" role="tabpanel" aria-labelledby="marketing-team-tab">
                                                <div class="table-responsive">
													<table class="table table-striped table-bordered first">
														<thead style="text-align: center;">
															<tr>
																<th>현황</th>
																<th>이름</th>
																<th>일자</th>
																<th>출근</th>
																<th>퇴근</th>
																<th>상세</th>
															</tr>
														</thead>
														<tbody style="text-align: center;">
															<c:forEach var="aalist" items="${aaList }">
																<c:if test="${aalist.jcFk == '1' }">																
																	<tr>
																		<td>
																			<c:if test="${empty aalist.aaWorkStart and empty aalist.workBreaks}">
																				<button type="button" class="btn btn-dark btn-sm" style="border-radius: 40px; background-color:white;" ></button>
																			</c:if>
																			<c:if test="${empty aalist.aaWorkStart and !empty aalist.workBreaks}">
																				<button type="button" class="btn btn-danger btn-sm" style="border-radius: 40px;" ></button>
																			</c:if>
																			<c:if test="${!empty aalist.aaWorkStart and !empty aalist.workBreaks}">
																				<button type="button" class="btn btn-primary btn-sm" style="border-radius: 40px; background-color:#FFA2A2; border-color: #FFA2A2;" ></button>
																			</c:if>
																			<c:if test="${!empty aalist.aaWorkStart and empty aalist.aaWorkEnd and empty aalist.workBreaks}">
																				<button type="button" class="btn btn-primary btn-sm" style="border-radius: 40px; background-color:#FFA2A2; border-color: #FFA2A2;" ></button> 
																			</c:if>
																			<c:if test="${!empty aalist.aaWorkStart and !empty aalist.aaWorkEnd and empty aalist.workBreaks}">
																				<button type="button" class="btn btn-success btn-sm" style="border-radius: 40px; background-color:#e9ba41; border-color: #e9ba41;" ></button>
																			</c:if>
																		</td>
																		<td> ${aalist.adminName }</td>
																		<td>${toDayFormat }</td>
																		<td>
																			<c:if test="${!empty aalist.aaWorkStart }">
																				<fmt:formatDate value="${aalist.aaWorkStart }" pattern="HH:mm"/>
																			</c:if>
																			<c:if test="${empty aalist.aaWorkStart and !empty aalist.workBreaks}">
																				휴무일 [${aalist.workBreaks }]
																			</c:if>
																			<c:if test="${empty aalist.aaWorkStart and empty aalist.workBreaks}">
																				-
																			</c:if>
																		</td>
																		<td>
																			<c:if test="${!empty aalist.aaWorkEnd }">
																				<fmt:formatDate value="${aalist.aaWorkEnd }" pattern="HH:mm"/>
																			</c:if>
																			<c:if test="${empty aalist.aaWorkEnd }">
																				-
																			</c:if>
																		</td>
																		<td><button type="button" class="btn btn-primary btn-block" value="${aalist.adminPk }" name="teamInfoButton"> 보기 </button></td>
																	</tr>
																</c:if>
															</c:forEach>
														</tbody>
													</table>
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
        <%@ include file="../../inc/bottom.jsp" %>