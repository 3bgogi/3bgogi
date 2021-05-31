<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/jquery-ui-1.12.1/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<link href='${pageContext.request.contextPath}/resources/vendor/full-calendar/css/fullcalendar.css' rel='stylesheet' />
<link href='${pageContext.request.contextPath}/resources/vendor/full-calendar/css/fullcalendar.min.css' rel='stylesheet' />
<link href='${pageContext.request.contextPath}/resources/vendor/full-calendar/css/fullcalendar.print.css' rel='stylesheet' media='print' />
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

    <script type="text/javascript">
   
    
    	$(function(){
    		
    		$(document).on("click", "button[name=insertTest]", function(){
    			
    			window.open("<c:url value='/admin/team/attendance_change.do?aaPk="+$(this).val()+"'/>", "출퇴근 변경" , "width=430, height=500, top=200, left=1200, scrollbars=no");
    		});
    	
    		$.applyDatePicker=function(id){
    			$.setToday(id);
    			
    			$(id).datepicker({
    				
    				dateFormat:"yy-mm-dd",
    			    changeMonth: true,
    				dayNamesMin:['일','월','화', '수', '목', '금','토'],
    				monthNames:['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    				monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
    			}).focus(function(){
    				/* $(".ui-datepicker-prev, .ui-datepicker-next").remove(); */
    				
    			});
    			
    		}
    		
    		$.convertDate=function(date){
    			return date.getFullYear()+"-"+$.formatDate(date.getMonth()+1)+"-"+$.formatDate(date.getDate());
    		}
    		
    		$.formatDate=function(data){
    			if(data<10){
    				data="0"+data;
    			}
    			return data
    		}
    		
    		$.setToday=function(id){
    			var endtDay=$(id).val();
    			
    			//처음 페이지 로드시 현재 날짜 셋팅하기
    			if(endtDay==null || endtDay==''){
    				var today=new Date();
    				var str=$.convertDate(today);
    				
    				$(id).val(str);
    			}		
    		}
    		
    		
    		$.applyDatePicker("#adStart");
    		$.applyDatePicker("#adEnd");
    		
    		$("#adType").change(function(){
    			if($(this).val() == 1){
    				
    				$(".calendarDiv").show();
    				$(".adWeeksDiv").hide();
    				
    			}else if($(this).val()==2){
    				$(".calendarDiv").hide();
    				$(".adWeeksDiv").show();
    			}
    			
    		});
    		
    		$("#adStart").change(function(){
    			var adEnd = new Date($("#adEnd").val());
    			var refDate = new Date();
    			refDate.setHours(0);
    			refDate.setMinutes(0);
    			refDate.setSeconds(0);
    			
    			var toDay = new Date($(this).val());
    			
    			if(toDay < refDate){
    				alert("휴가를 오늘보다 전 일에 신청할 수 없습니다. 오늘로 설정합니다.");
	    			$(this).val(formatDate(refDate));
	    			$(this).focus();
	    			return false;
    			}else if(toDay > adEnd){
    				alert("휴가 종료일이 시작일보다 값이 작아 재 설정합니다.");
    				$("#adEnd").val(formatDate(toDay));
    				$("#adEnd").focus();
    				return false;
    			}
    			
    			
    		});
    		
    		$("#adEnd").change(function(){
    			
    			var adStart = new Date($("#adStart").val());
    			
    			var toDay = new Date($(this).val());
    			
    			if(toDay < adStart){
    				alert("휴가 시작일보다 전 일에 신청할 수 없습니다.");
	    			$(this).val(formatDate(adStart));
	    			$(this).focus();
	    			return false;
    			}
    			
    		});
			
			$("#formatYear").change(function(){
				var formatYear = $(this).val();
				
				if(formatYear == null){
					
					alert('년도를 선택해주세요.');
					
					event.preventDefault();
					
					return false;
				}
				
				var adminPk = $("input[name=adminFk]").val();
				var formatYear = $("#formatYear").val();
				
				$.ajax({
					url:'<c:url value="/admin/team/ajax_aamonth.do"/>',
					data:{
						"adminPk":adminPk,
						"formatYear":formatYear
					},
					success:function(data){
						Pace.start();
						var formatMonthList = "<option>월을 선택해주세요.</option>";
						
						$.each(data, function(idx, items){
							
							formatMonthList+="<option value='"+this.formatMonth+"'>"+this.formatMonth+"</option>";
							
						});
						
						$("#formatMonth").html(formatMonthList);
						Pace.stop();
						
					},
					error:function(xhr, status, error){
						alert("세선이 종료되었거나 권한이 없습니다. ");
						location.href="<c:url value='/login.do'/>";
						Pace.stop();
					}
					
				});
				
			});
			
			$("#formatMonth").change(function(){
				var formatMonth = $(this).val();
				
				if(formatMonth == null){
					
					alert('월을 선택해주세요.');
					
					event.preventDefault();
					
					return false;
				}
				
				var adminPk = $("input[name=adminFk]").val();
				var formatYear = $("#formatYear").val();
				var formatMonth = $("#formatMonth").val();

				
			});
			
			$('#dateStart').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d'
    		});
    		$('#dateEnd').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d'
    		});
    		

    		$("#weeklyPriceDataForm").submit(function(){
    			var dateStart = $("#dateStart").val();
    			var dateEnd = $("#dateEnd").val();
    			var datePeriod = $("#datePeriod").val();
    			
    			$.ajax({
	    				type       : 'GET',
	    				data       : {
	    					"dateStart":dateStart,
	    					"dateEnd":dateEnd,
	    					"datePeriod":datePeriod
	    				},
	    				url        : '/admin/team/ajax_weekly_price.do',
	    				success    : function(data){
	    					
	    					var weeklyPriceDataTableHTML = "";
	    					var totalWorkTime = 0;
	    					var totalJobCost = 0;
	    					
	    					if(data.length > 0){
	    						
		    					$.each(data, function( idx){
		    						weeklyPriceDataTableHTML+="<tr style='text-align:center;'>";
		    						
		    						weeklyPriceDataTableHTML+="<td>"+this.starts+"</td>";
		    						weeklyPriceDataTableHTML+="<td>"+this.ends+"</td>";
		    						weeklyPriceDataTableHTML+="<td>"+this.dates+"</td>";
		    						weeklyPriceDataTableHTML+="<td>"+this.work_time+" 시간</td>";
		    						totalWorkTime+=this.work_time;
		    						
		    						weeklyPriceDataTableHTML+="<td>"+this.job_cost+" 원</td>";
		    						totalJobCost+=this.job_cost;
		    						
		    						weeklyPriceDataTableHTML += "</tr>";
		    					});
		    					
		    					var TIME_Start2 = String(totalWorkTime) ; // 문자형으로 바꾼다음에
		    					var ary = TIME_Start2.split('.'); // 점으로 구분배열
		    					var sT_min  = ary[1].substring(0,2) ; // 두자리만 뽑고 (위치만 하나 뒤로)
		    					
		    					console.log(totalWorkTime);
		    					console.log(ary);
		    					console.log(sT_min);
		    					
		    					var calCost = Number(sT_min);
		    					
		    					weeklyPriceDataTableHTML+= '<tr style="text-align:center;">';
		    					weeklyPriceDataTableHTML+="<td colspan='3'> 총 합 </td>";
		    					weeklyPriceDataTableHTML+="<td>"+ary[0]+" 시간 "+(calCost * 6 / 10)+" 분</td>";
		    					TIME_Start2 = String(totalJobCost) 
		    					ary = TIME_Start2.split('.');
		    					
		    					weeklyPriceDataTableHTML+="<td>"+comma(Number(ary[0]))+" 원</td>";
		    					weeklyPriceDataTableHTML+='</tr>';
		    					
	    					}else{
	    						weeklyPriceDataTableHTML+="<tr style='text-align:center;'><td colspan='5'> 해당 기간내에 출퇴근 기록이 존재하지 않습니다 </td></tr>";
	    						
	    					}
	    					
	    					
	    					
	    					
	    					$("#weeklyPriceDataTable").html(weeklyPriceDataTableHTML);
	    				}
	    				
	    				
	    			});
    			event.preventDefault();
    			return false;
    		});
    		
    	});
    	
    	function daysOffChange(updateType,adPk, adminPk){
    		location.href="<c:url value='/admin/team/admin_daysoff_change.do?updateType="+updateType+"&adPk="+adPk+"&adminPk="+adminPk+"'/>";
    		
    	}
    </script>
    <style type="text/css">
    	
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
                                <h3 class="mb-2"> 팀원 기록 상세 확인 </h3>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 팀원 </a></li>
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 근태관리 </a></li>
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 팀원상세확인 </a></li>
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
                                        <h2 class="font-24 mb-0"> ${adminVO.adminName }</h2>
                                    </div>
                                </div>
                                <div class="card-body border-top" style="text-align: center;">
                                    <h3 class="font-16"> 연락처 </h3>
                                    <div class="">
	                                    <ul class="list-unstyled mb-0">
	                                        <li class="mb-0"><i class="fas fa-fw fa-phone mr-2"></i> ${adminVO.adminPhone }</li>
	                                    </ul>
	                                    <ul class="list-unstyled mb-0">
	                                        <li class="mb-0"><i class="fas fa-fw fa-home mr-2"></i> ${adminVO.adminAddress }</li>
	                                    </ul>
	                                    <ul class="list-unstyled mb-0">
	                                        <li class="mb-0"><i class="fas fa-fw fa-bell mr-2"></i> 아침 출근 시각 : ${adminVO.adminWorktime }</li>
	                                    </ul>
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
                                        <a class="nav-link active" id="pills-campaign-tab" data-toggle="pill" href="#pills-campaign" role="tab" aria-controls="pills-campaign" aria-selected="true">출결기록</a>
                                    </li>
                                    
                                    <li class="nav-item">
                                        <a class="nav-link" id="pills-packages-tab" data-toggle="pill" href="#pills-packages" role="tab" aria-controls="pills-packages" aria-selected="false">휴무</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="pills-msg-tab" data-toggle="pill" href="#pills-msg" role="tab" aria-controls="pills-msg" aria-selected="false">내 정보 확인</a>
                                    </li>
                                    <sec:authorize access="hasRole('ROLE_DEVELOPER')">
	                                    <li class="nav-item">
	                                        <a class="nav-link" id="admin-auth-tab" data-toggle="pill" href="#admin-auth" role="tab" aria-controls="admin-auth" aria-selected="false"> 중요정보변경 </a>
	                                    </li>
                                    </sec:authorize>
                                </ul>
                                <div class="tab-content" id="pills-tabContent">
                                    <div class="tab-pane fade show active" id="pills-campaign" role="tabpanel" aria-labelledby="pills-campaign-tab">
                                        <div class="row">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <div class="section-block">
                                                    <h3 class="section-title"> 출결 기록 확인 </h3>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card">
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
                                        <sec:authorize access="hasRole('ROLE_DEVELOPER')">
	                                        <form class="card" id="weeklyPriceDataForm">
	                                        	<div class="card-header">
	                                        		<h4> 임의 주급 계산기 </h4>
	                                        	</div>
	                                            <div class="card-body" style=" text-align: right;">
	                                            	<input type="hidden" name="datePeriod" value="${adminVO.adminPk }" id="datePeriod">
	                                            	<div class="input-group mb-3">                                            	
		                                                <input type="text" class="form-control" id="dateStart" name="dateStart" style="width: 8em; text-align: center;" value="${osVO.dateStart }"/>
		                                                <input type="text" class="form-control" id="dateEnd" name="dateEnd" style="width: 8em; text-align: center;" value="${osVO.dateEnd }"/>
		                                                <div class="input-group-append be-addon">
		                                                	<button type="submit" class="btn btn-primary" > 해당 기간으로 조회하기 </button>
		                                                </div>
	                                            	</div>
	                                            </div>
	                                            <div class="card-body">
	                                            	<table class="table table-hover">
				                                        <thead>
				                                            <tr style="text-align: center;">
				                                            
				                                                <th scope="col">첫날</th>
				                                                <th scope="col">마지막날</th>
				                                                <th scope="col">올해 주차</th>
				                                                <th scope="col">근무시간</th>
				                                                <th scope="col">임의로 계산된 임금</th>
				                                            </tr>
				                                        </thead>
				                                        <tbody id="weeklyPriceDataTable">
				                                            <tr style="text-align: center;">
				                                                <th colspan="5"> 날짜를 검색해주세요 </th>
				                                            </tr>
				                                        </tbody>
				                                    </table>
	                                            </div>
	                                        </form>
                                        </sec:authorize>
                                    </div>
                                    <div class="tab-pane fade" id="pills-packages" role="tabpanel" aria-labelledby="pills-packages-tab">
                                        <div class="card">
                                            <h5 class="card-header"> 휴무 추가하기 </h5>
                                            <div class="card-body">
                                                <form name="daysoffForm" method="post" action="<c:url value='/admin/team/team_info_detail.do'/>">
                                                    <div class="row">
                                                        <div class="offset-xl-3 col-xl-6 offset-lg-3 col-lg-3 col-md-12 col-sm-12 col-12 p-4">
                                                        	<div class="form-group">
                                                                <label for="email">휴무명</label>
                                                                <input type="text" class="form-control form-control-lg" id="adTitle" name="adTitle" placeholder="휴가 혹은 휴무 명을 입력해주세요">
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="adType"> 종류 </label>
                                                                <select class="form-control" id="adType" name="adType">                                                                	
                                                                	<option value="1">휴가</option>
                                                                	<option value="2">고정적휴무</option>
                                                                </select>
                                                            </div>
                                                            <div class="form-group adWeeksDiv" style="display: none;">
                                                                <label for="adWeeks"> 요일 선택 </label>
                                                                <select class="form-control" id="adWeeks" name="adWeeks">
                                                                	<c:set var="weeks" value="${fn:split('월요일,화요일,수요일,목요일,금요일,토요일,일요일',',') }"/>
                                                                	<c:set var="weekCount" value="0"/>
                                                                	<c:set var="dupliCount" value="0"/>
                                                                	<c:forEach var="weekArray" items="${weeks }" varStatus="idx">
                                                                		<c:set var="dupliCount" value="0"/>
                                                                		<c:forEach var="weekAdList" items="${adList }">
                                                                			<c:if test="${weekAdList.adWeeks == idx.index and weekAdList.adType == 2 }">
                                                                				<c:set var="dupliCount" value="${dupliCount + 1 }"/>
                                                                			</c:if>
                                                                		</c:forEach>
                                                                		<c:if test="${dupliCount == 0 }">
                                                                			<option value="${idx.index }">${weekArray }</option>
                                                                		</c:if>
                                                                	</c:forEach>     	
                                                                </select>
                                                            </div>
                                                            <div class="form-row calendarDiv">
                                                            	<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12 mb-2">                                                            	
	                                                                <label for="adStart">휴가 시작일</label>
	                                                                <input type="text" class="form-control form-control-lg" id="adStart" name="adStart" placeholder="">
                                                            	</div>
                                                            	<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12 mb-2">      
                                                            		<label for="adEnd">휴가 종료일</label>                                                      	
	                                                                <input type="text" class="form-control form-control-lg" id="adEnd" name="adEnd" placeholder="">
                                                            	</div>
                                                            </div>
                                                            <input type="hidden" value="${adminVO.adminPk }" name="adminFk">
                                                            <button type="submit" class="btn btn-primary float-right"> 휴무 신청하기 </button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="card">
                                        	<h5 class="card-header"> 고정휴무일 </h5>
                                            <div class="card-body">
                                                <table class="table table-striped table-bordered first">
														<thead style="text-align: center;">
															<tr>
																<th> 요일 </th>
																<th> 승인 여부 </th>
																<sec:authorize access="hasRole('ROLE_DEVELOPER')">
																<th>승인 및 삭제</th>
																</sec:authorize>
															</tr>
														</thead>
														<tbody style="text-align: center;">
															<c:if test="${!empty adList }">															
																<c:forEach var="adlist" items="${adList }">
																	<c:if test="${adlist.adType == 2 }">																	
																		<tr>
																			<td> ${adlist.adTitle } </td>
																			<td>
																				<c:if test="${adlist.adFlag eq true}">
																					승인
																				</c:if>
																				<c:if test="${adlist.adFlag ne true}">
																					승인 대기중
																				</c:if>
																			</td>
																			<sec:authorize access="hasRole('ROLE_DEVELOPER')">
																				<td>
																					<c:if test="${adlist.adFlag eq true}">
																						<button type="button" class="btn btn-warning" onclick="daysOffChange(0,'${adlist.adPk}', '${adminVO.adminPk }')" value="${adlist.adPk }" name="deleteDaysoff">삭제</button>
																					</c:if>
																					
																					<c:if test="${adlist.adFlag ne true}">
																						<button type="button" class="btn btn-primary" onclick="daysOffChange(1,'${adlist.adPk}', '${adminVO.adminPk }')" value="${adlist.adPk }" name="permisionDaysoff">허가</button>
																					</c:if>
																				</td>
																			</sec:authorize>
																		</tr>
																	</c:if>
																</c:forEach>
															</c:if>
															<c:if test="${empty adList }">															
																<tr>
																	<td colspan="2"> 휴무 데이터가 존재하지 않습니다. </td>
																</tr>
															</c:if>
														</tbody>
													</table>
                                            </div>
                                        </div>
                                        <div class="card">
                                        	<h5 class="card-header"> 휴가 및 유동적 휴일 </h5>
                                            <div class="card-body">
                                                <table class="table table-striped table-bordered first">
														<thead style="text-align: center;">
															<tr>
																<th> 날 짜 </th>
																<th> 휴가명 </th>
																<th> 승인 여부 </th>
																<sec:authorize access="hasRole('ROLE_DEVELOPER')">
																	<th>승인 및 삭제</th>
																</sec:authorize>
															</tr>
														</thead>
														<tbody style="text-align: center;">
															<c:if test="${!empty adList }">															
																<c:forEach var="adlist" items="${adList }">
																	<c:if test="${adlist.adType == 1 }">																	
																		<tr>
																			<td>
																				<fmt:formatDate value="${adlist.adStart }" pattern="yyyy-MM-dd"/> ~ 
																				<fmt:formatDate value="${adlist.adEnd }" pattern="yyyy-MM-dd"/>
																			</td>
																			<td> ${adlist.adTitle } </td>
																			<td>
																				<c:if test="${adlist.adFlag eq true}">
																					승인
																				</c:if>
																				<c:if test="${adlist.adFlag ne true}">
																					승인 대기중
																				</c:if>
																			</td>
																			
																			<sec:authorize access="hasRole('ROLE_DEVELOPER')">
																				<td>
																					<c:if test="${adlist.adFlag eq true}">
																						<button type="button" class="btn btn-warning" onclick="daysOffChange(0,'${adlist.adPk}', '${adminVO.adminPk }')" value="${adlist.adPk }" value="${adlist.adPk }" name="deleteDaysoff">삭제</button>
																					</c:if>
																					<c:if test="${adlist.adFlag ne true}">
																						<button type="button" class="btn btn-primary" onclick="daysOffChange(1,'${adlist.adPk}', '${adminVO.adminPk }')" value="${adlist.adPk }" value="${adlist.adPk }" name="permisionDaysoff">허가</button>
																					</c:if>
																				</td>
																			</sec:authorize>
																		</tr>
																	</c:if>
																</c:forEach>
															</c:if>
															<c:if test="${empty adList }">															
																<tr>
																	<td colspan="2"> 휴무 데이터가 존재하지 않습니다. </td>
																</tr>
															</c:if>
														</tbody>
													</table>
                                            </div>
                                        </div>
                                    </div>
									<div class="tab-pane fade" id="pills-msg" role="tabpanel" aria-labelledby="pills-msg-tab">
                                        <div class="card">
                                            <h5 class="card-header"> 내 정보 </h5>
                                            <div class="card-body">
                                            
                                            	<form id="adminInfoChangeForm" action="<c:url value='/update_admin_info.do'/>" method="POST">
                                            		<input type="hidden" id="adminPk" name="adminPk" value="${adminVO.adminPk }">
                                            		<div class="form-group row">
			                                            <label class="col-12 col-sm-3 col-form-label text-sm-right">아이디</label>
			                                            <div class="col-12 col-sm-8 col-lg-6">
			                                                <input class="form-control form-control-lg" type="text" id="adminId" name="adminId" readonly="readonly" value="${adminVO.adminId }" placeholder="아이디" autocomplete="off">
			                                            </div>
			                                        </div>
			                                        
			                                        <div class="form-group row">
			                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 부서 </label>
			                                            <div class="col-12 col-sm-8 col-lg-6">
			                                                <select class="form-control form-control-lg" name="jc_fk">
			                                                	<option value="0">선택되지 않음</option>
			                                                	<c:forEach var="jclist" items="${jcList }">
			                                                		<option value="${jclist.jcPk }">${jclist.jcType }</option>
			                                                	</c:forEach>
			                                                </select>
			                                            </div>
			                                        </div>
			                                        
			                                        <div class="form-group row">
			                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 출근시각</label>
			                                            <div class="col-12 col-sm-8 col-lg-6">
			                                                <input class="form-control form-control-lg" type="text" id="adminWorktime" value="${adminVO.adminWorktime }" name="adminWorktime" placeholder="이름">
			                                            </div>
			                                        </div>
			                                        <div class="form-group row">
			                                            <label class="col-12 col-sm-3 col-form-label text-sm-right">이름</label>
			                                            <div class="col-12 col-sm-8 col-lg-6">
			                                                <input class="form-control form-control-lg" type="text" id="adminName" value="${adminVO.adminName }" name="adminName" placeholder="이름">
			                                            </div>
			                                        </div>
			                                        <div class="form-group row">
			                                            <label class="col-12 col-sm-3 col-form-label text-sm-right">연락처</label>
			                                            <div class="col-12 col-sm-8 col-lg-6">
			                                                <input class="form-control form-control-lg" id="adminPhone" id="adminPhone" type="text" value="${adminVO.adminPhone }" name="adminPhone" placeholder="연락처" onkeyup="checkphone()">
			                                            </div>
			                                        </div>
			                                        <div class="form-group row">
			                                            <label class="col-12 col-sm-3 col-form-label text-sm-right">주소</label>
			                                            <div class="col-12 col-sm-8 col-lg-6">
			                                                <input class="form-control form-control-lg" type="text" id="adminAddress" value="${adminVO.adminAddress }" name="adminAddress" placeholder="주소">
			                                            </div>
			                                        </div>
			                                        <div class="form-group row">
			                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 비밀번호 확인 </label>
			                                            <div class="col-12 col-sm-8 col-lg-6">
			                                                <input class="form-control form-control-lg" type="password" id="adminPass" name="adminPass" placeholder="비밀번호" autocomplete="off">
			                                            </div>
			                                        </div>
			                                        <div class="form-group pt-2">
									                    <button class="btn btn-block btn-primary" id="updateAdminInfoBtn" type="submit"> 수정하기 </button>
									                </div>
                                            	</form>
								            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="admin-auth" role="tabpanel" aria-labelledby="admin-auth-tab">
                                        <div class="card">
                                            <h5 class="card-header"> 접속권한 </h5>
                                            <div class="card-body">
                                            
                                            	<sec:authorize access="hasRole('ROLE_MASTER')">
                                            	<div class="form-group row">
		                                            <label class="col-sm-3 col-form-label text-sm-right"> 권한 변경 및 삭제</label>
		                                            <div class="col-sm-6">
		                                                <div class="custom-controls-stacked">
		                                                    <label class="custom-control custom-checkbox">
		                                                        <input type="checkbox" value="ROLE_USER"
		                                                        	<c:forEach var="adRolelist" items="${adRoleList }">
		                                                        		<c:if test="${adRolelist.role ==  'ROLE_USER'}">
		                                                        			checked="checked" data-role-pk="${adRolelist.adminRolePk }"
		                                                        		</c:if>
		                                                        	</c:forEach>
		                                                        class="custom-control-input admin_auth"><span class="custom-control-label">일반</span>
		                                                    </label>
		                                                    <label class="custom-control custom-checkbox">
		                                                        <input type="checkbox" value="ROLE_ADMIN"  
		                                                        	<c:forEach var="adRolelist" items="${adRoleList }">
		                                                        		<c:if test="${adRolelist.role ==  'ROLE_ADMIN'}">
		                                                        			checked="checked" data-role-pk="${adRolelist.adminRolePk }"
		                                                        		</c:if>
		                                                        	</c:forEach>
		                                                        class="custom-control-input admin_auth"><span class="custom-control-label">관리자</span>
		                                                    </label>
		                                                    <label class="custom-control custom-checkbox">
		                                                        <input type="checkbox" value="ROLE_DEVELOPER" 
		                                                        	<c:forEach var="adRolelist" items="${adRoleList }">
		                                                        		<c:if test="${adRolelist.role ==  'ROLE_DEVELOPER'}">
		                                                        			checked="checked" data-role-pk="${adRolelist.adminRolePk }"
		                                                        		</c:if>
		                                                        	</c:forEach>
		                                                        class="custom-control-input admin_auth"><span class="custom-control-label">개발</span>
		                                                    </label>
		                                                    <label class="custom-control custom-checkbox">
		                                                        <input type="checkbox" value="ROLE_MASTER" 
		                                                        	<c:forEach var="adRolelist" items="${adRoleList }">
		                                                        		<c:if test="${adRolelist.role ==  'ROLE_MASTER'}">
		                                                        			checked="checked" data-role-pk="${adRolelist.adminRolePk }"
		                                                        		</c:if>
		                                                        	</c:forEach>
		                                                        class="custom-control-input admin_auth"><span class="custom-control-label">최고관리자</span>
		                                                    </label>
															
		                                                    <div id="error-container2"></div>
		                                                </div>
		                                            </div>
		                                        </div>
		                                        </sec:authorize>
								            </div>
                                        </div>
                                        <div class="card">
                                            <h5 class="card-header"> 비밀번호 변경 </h5>
                                            <div class="card-body">
                                            	<form id="changeAdminPassForm" action="<c:url value='/change_admin_pass.do'/>" method="POST">
                                            		<input type="hidden" id="adminPk" name="adminPk" value="${adminVO.adminPk }">
			                                        <div class="form-group row">
			                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 새 비밀번호</label>
			                                            <div class="col-12 col-sm-8 col-lg-6">
			                                                <input class="form-control form-control-lg" type="password" id="updateAdminPass" name="adminPass"placeholder="비밀번호" autocomplete="off">
			                                            </div>
			                                        </div>
			                                        <div class="form-group row">
			                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 새 비밀번호확인</label>
			                                            <div class="col-12 col-sm-8 col-lg-6">
			                                                <input class="form-control form-control-lg" type="password" id="updateAdminPassCheck" name="adminPassCheck"placeholder="비밀번호 확인" autocomplete="off">
			                                            </div>
			                                        </div>

			                                        <div class="form-group pt-2">
									                    <button class="btn btn-block btn-primary" id="updateAdminInfoBtn" type="submit"> 변경하기 </button>
									                </div>
                                            	</form>
								            </div>
                                        </div>
                                    </div>
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
        
        
    <script src='${pageContext.request.contextPath}/resources/vendor/full-calendar/js/moment.min.js'></script>
    <script src='${pageContext.request.contextPath}/resources/vendor/full-calendar/js/fullcalendar.js'></script>
    <script src='${pageContext.request.contextPath}/resources/vendor/full-calendar/js/jquery-ui.min.js'></script>
    <script type="text/javascript">
    	$(function(){
    		
    		$("#changeAdminPassForm").submit(function(){
    			var updateAdminPass= $("#updateAdminPass").val();
    			var updateAdminPassCheck = $("#updateAdminPassCheck").val();
    			
    			if(updateAdminPass.length < 6){
	    			alert("비밀번호는 6자리 이상이어야 합니다.");
	    			$("#updateAdminPass").focus();
	    			return false;
	    			
	    		}else if(updateAdminPass != updateAdminPassCheck){
	    			alert("비밀번호가 서로 다릅니다");
	    			$("#updateAdminPass").val("");
	    			$("#updateAdminPassCheck").val("");
	    			$("#updateAdminPass").focus();
	    			return false;

	    		}
    			
    		});
    		
			$("#adminInfoChangeForm").submit(function(event){
    			//정규화 공식
    			var regNumber = /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/;
    			
	    		var adminPass = $("#adminPass").val();
	    		var adminName = $("#adminName").val();
	    		var adminPhone = $("#adminPhone").val();
	    		var adminAddress = $("#adminAddress").val();
	    		
	    		if(adminPass.length < 6){
	    			alert("비밀번호는 6자리 이상이어야 합니다.");
	    			$("#adminPass").focus();
	    			return false;
	    			
	    		}else if(adminName.trim() == ""){
	    			alert("이름을 입력해주세요.");
	    			$("#adminName").focus();
	    			return false;
	    			
	    		}else if(regNumber.test(adminPhone) == false){
	    			alert("연락처는 숫자만 입력해야 합니다.");
	    			$("#adminPhone").focus();
	    			return false;
	    			
	    		}
	    		
    		});
			
			$(".admin_auth").change(function(){
    			var roleName = $(this).val();
    			var adminRolePk = $(this).data("role-pk");
    			var adminId = $("#adminId").val();
    			var adminPk = $("#adminPk").val();
    			
    			if(adminRolePk == null){
    				adminRolePk = 0;
    			}
    			
    			location.href = "<c:url value='/admin/add_or_delete_Auth.do?roleName="+roleName+"&adminRolePk="+adminRolePk+"&adminId="+adminId+"&adminPk="+adminPk+"'/>";
    			
    			
    		 });
			
			
    	});
    </script>
        <%@ include file="../../inc/bottom.jsp" %>