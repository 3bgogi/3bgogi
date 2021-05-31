<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		$("#projectForm").submit(function(){
    			
    			if($("#proTitle").val() == null || $("#proTitle").val() == ""){
    				
    				alert("업무명을 입력해주세요.");
    				$("#proTitle").focus();
    				
    				return false;
    			}
    			
    			var projectArray = $("#proTitle").val().split("#");
    			
    			var proTitle = projectArray[0];
    			
    			var proDetail = projectArray[1];
    			
    			$("#proTitle").val(proTitle);
    			
    			$("#proDetail").val(proDetail);    			
    			
    		});
    		
    	});
    </script>
    <style type="text/css">
		.table-grid-test{
			display: grid;
		}
    </style>
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
                            <h2 class="pageheader-title"> 업무 확인 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 개인 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 업무일지 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 업무 확인 </li>
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
                        	<div class="col-xl-2 col-lg-2 col-md-6 col-sm-12 col-12">
		                        <div class="sidebar-nav-fixed">
		                        	<div id="custom-search" class="top-search-bar">
		                        		<hr>
                                        <h6>업무 조건 관련</h6>
		                        		<label class="custom-control custom-checkbox">
                                        	<input type="checkbox" class="custom-control-input projectSearchCondition" name="todayProject"><span class="custom-control-label">오늘 할 일만 보기</span>
                                        </label>
		                                <label class="custom-control custom-checkbox">
                                        	<input type="checkbox" class="custom-control-input projectSearchCondition" name="alarmOnlyProject"><span class="custom-control-label">알람만 보기</span>
                                        </label>
                                        <label class="custom-control custom-checkbox">
                                        	<input type="checkbox" class="custom-control-input projectSearchCondition" name="showAnotherTeamProject"><span class="custom-control-label">다른 팀원 업무 보기</span>
                                        </label>
                                        <label class="custom-control custom-checkbox">
                                        	<input type="checkbox" class="custom-control-input projectSearchCondition" name="finishedProject"><span class="custom-control-label">완료된 업무만 보기</span>
                                        </label>
                                        
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
	                                        <label class="custom-control custom-checkbox">
	                                        	<input type="checkbox" class="custom-control-input projectSearchCondition" name="showHide"><span class="custom-control-label">삭제된 업무만 보기</span>
	                                        </label>
                                        </sec:authorize>
                                        
                                        <hr>
                                        <h6>태그 관련</h6>
                                        <label class="custom-control custom-checkbox">
                                        	<input type="checkbox" class="custom-control-input projectSearchCondition" id="showAnotherTeamProjectTag" name="showAnotherTeamProjectTag"><span class="custom-control-label">다른 팀원 태그 보기</span>
                                        </label>
		                            </div>
		                            <hr>
		                            <ul class="list-unstyled" id="searchProjectTagList">
		                            	<c:forEach var="projectsTaglist" items="${projectsTagList }">
		                            		<li><a href="#" class="projectTags projectSearchCondition">${projectsTaglist.ptagTitle }</a></li>	
		                            	</c:forEach>
		                            </ul>
		                        </div>
		                    </div>
	                        <div class="col-xl-10">
		                        <div class="row">
									<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12"style="margin-bottom: 25px;">
										<form id="projectForm" action="<c:url value='/project/projects.do'/>" method="POST" enctype="multipart/form-data">
											<div class="input-group">
												<input id="proTitle" name="proTitle" type="text" class="form-control" placeholder="업무명을 적은 뒤 #을 적어 상세사항 입력">
												<input id="proDetail" name="proDetail" type="hidden" value="">
												<div class="input-group-append be-addon">
													<button type="button" data-toggle="dropdown"
														class="btn btn-secondary dropdown-toggle"> 추가사항 </button>
													<div class="dropdown-menu">
														<a href="#" class="dropdown-item">알람 켜기</a>
															<div class="dropdown-divider"></div>
														<input name="thumbnailImage" type="file" class="dropdown-item">
													</div>
												</div>
												<button id="projectFormButton" type="button" class="btn btn-primary"> 입력 </button>
											</div>										
										</form>
									</div>
									<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="projectListDivs" style="display: contents;">									
			                            <c:if test="${!empty projectsList }">
			                            	<c:forEach var="projectslist" items="${projectsList }">		                            	
					                            <div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-12 table-grid-test">
					                                <div class="card">
					                                	<input type="hidden" value="${projectslist.proPk }" name="proPk">
					                                	<c:if test="${!empty projectslist.proThumbnailImage}">				                                	
						                                    <img class="card-img-top renewal-img-padding" src="${pageContext.request.contextPath}/resources/images/project_image/${projectslist.proThumbnailImage}" alt="${projectslist.proThumbnailImageRealName }" align="middle">
					                                	</c:if>
					                                    <div class="card-header d-flex">
					                                        <h4 data-toggle="modal" data-target="#projectModal" class="mb-0 projectTitle" style="color:${projectslist.proTitleColor}; cursor: pointer;">${projectslist.proTitle }</h4>
					                                        <input type="hidden" name="proPk" value="${projectslist.proPk }">
					                                        <div class="dropdown ml-auto">
					                                            <a class="toolbar" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="mdi mdi-dots-vertical"></i>  </a>
					                                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink">
					                                                <a class="dropdown-item deleteAllProjectData" data-pro-pk="${projectslist.proPk }" href="#"> 업무 삭제 </a>
					                                                <a class="dropdown-item changeFixedProjectStatus 
					                                                <c:if test='${projectslist.projectTargetList[0].ptTopAlarmFlag eq true}'>
					                                                	active
					                                                </c:if>
					                                                " data-pro-pk="${projectslist.proPk }" href="#"> 상단 알람 </a>
					                                            </div>
					                                        </div>
					                                    </div>
					                                    <div class="card-body">
					                                        <p class="card-text">${projectslist.proDetail }</p>
					                                    </div>
					                                    <div class="card-footer">
					                                    	<div class="btn-group tag-list">
					                                    		<c:forEach var="projectTaglist" items="${projectslist.projectTagList }">
					                                    			<span class="badge badge-light">#${projectTaglist.ptagTitle }</span>&nbsp;
					                                    		</c:forEach>
															</div>
					                                    </div>
					                                </div>
					                            </div>
			                            	</c:forEach>		                            
			                            </c:if>
									</div>
		                            
		                       </div>
	                    </div>
                        <!-- ============================================================== -->
                        <!-- end valifation types -->
                        <!-- ============================================================== -->
                    </div>
           
            </div>
        <!-- /page content -->
    </script>
        <%@ include file="../../inc/bottom.jsp" %>