<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/jquery-ui-1.12.1/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<link href='${pageContext.request.contextPath}/resources/vendor/full-calendar/css/fullcalendar.css' rel='stylesheet' />
<link href='${pageContext.request.contextPath}/resources/vendor/full-calendar/css/fullcalendar.min.css' rel='stylesheet' />
<link href='${pageContext.request.contextPath}/resources/vendor/full-calendar/css/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src="${pageContext.request.contextPath}/resources/libs/js/renewal_anlay.js"></script>
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


    	});
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
                                <h3 class="mb-2"> 예약된 상품 확인 </h3>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 상품 예약 </a></li>
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
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <!-- ============================================================== -->
                            <!-- campaign tab one -->
                            <!-- ============================================================== -->
                            <div class="influence-profile-content pills-regular">
                                <div class="tab-content" id="pills-tabContent">
                                    <div class="tab-pane fade show active" id="pills-campaign" role="tabpanel" aria-labelledby="pills-campaign-tab">
                                        <div class="row">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <div class="section-block">
                                                    <h3 class="section-title"> 예약 상품 확인 </h3>
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
												<div id="cal_tab" class="cal table-responsive">
												    
												</div>
												<br>
												<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" style="text-align: right;">
													<div class="form-group">
														<button class="btn btn-primary" id="checkAll"> 전체 체크</button>
														<button class="btn btn-danger" id="checkDel"> 전체 체크 해제</button>
														
													</div>
												</div>
												<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
													<button class="btn btn-success btn-block" id="checkProdBtn"> 체크된 목록 검색</button>
												</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                    	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="card">
                                <h5 class="card-header"> 조회 목록 </h5>
                                <div class="card-body">
                                    <table class="table table-hover">
                                    	<thead>
                                    		<tr>
												<th>상품명</th>
												<th>개수</th>
                                    		</tr>
                                    	</thead>
                                        <tbody id="checkProd">
                                        	
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    <script src='${pageContext.request.contextPath}/resources/vendor/full-calendar/js/moment.min.js'></script>
    <script src='${pageContext.request.contextPath}/resources/vendor/full-calendar/js/fullcalendar.js'></script>
    <script src='${pageContext.request.contextPath}/resources/vendor/full-calendar/js/jquery-ui.min.js'></script>
    
     <%@ include file="../../inc/bottom.jsp" %>