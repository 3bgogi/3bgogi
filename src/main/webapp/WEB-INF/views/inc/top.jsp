<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="kr">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link href="${resourcePath}/resources/libs/css/theme.css" rel="stylesheet" media="all">
    <link href="${resourcePath}/resources/libs/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet"  media="all">
    <link rel="stylesheet" href="${resourcePath}/resources/vendor/bootstrap/css/bootstrap.min.css">
    <link href="${resourcePath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${resourcePath}/resources/libs/css/style.css">
    <link rel="stylesheet" href="${resourcePath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <link rel="stylesheet" href="${resourcePath}/resources/vendor/charts/chartist-bundle/chartist.css">
    <link rel="stylesheet" href="${resourcePath}/resources/vendor/charts/morris-bundle/morris.css">
    <link rel="stylesheet" href="${resourcePath}/resources/vendor/fonts/material-design-iconic-font/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="${resourcePath}/resources/vendor/charts/c3charts/c3.css">
    <link rel="stylesheet" href="${resourcePath}/resources/vendor/fonts/flag-icon-css/flag-icon.min.css">
    <link rel="stylesheet" href="${resourcePath}/resources/vendor/multi-select/css/multi-select.css">
    <link rel="stylesheet" href="${resourcePath}/resources/vendor/bootstrap-colorpicker/%40claviska/jquery-minicolors/jquery.minicolors.css">
    <link rel="stylesheet" href="${resourcePath}/resources/vendor/bootstrap-select/css/bootstrap-select.css">
    <link rel="stylesheet" href="${resourcePath}/resources/vendor/datepicker/jquery.datetimepicker.css" />
    
    <!-- Optional JavaScript -->
    <script src="${resourcePath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Stylish" rel="stylesheet">
    <link rel="stylesheet" href="${resourcePath}/resources/css/renewal_page.css">
    
    <script src="${resourcePath}/resources/vendor/pace-master/pace.min.js"></script>
    <script src="${resourcePath}/resources/libs/js/common_util.js"></script>
    <link rel=”stylesheet” type=”text/css” href=”//cdn.rawgit.com/innks/NanumSquareRound/master/nanumsquareround.min.css”>
    <title> 3Bgogi Renewal Home Page</title>
    <style type="text/css">
    	*{
    		font-family: ‘NanumSquareRound’,sans-serif;
    	}
    	.blinking{
			-webkit-animation:blink 1s ease-in-out infinite alternate;
			-moz-animation:blink 1s ease-in-out infinite alternate;
			animation:blink 1s ease-in-out infinite alternate;
		}
		@-webkit-keyframes blink{
			 0% {opacity:0.4;}
			100% {opacity:1;}
		}
		@-moz-keyframes blink{
			 0% {opacity:0.4;}
			100% {opacity:1;}
		}
		@keyframes blink{
			 0% {opacity:0.4;}
			100% {opacity:1;}
		}
			
		.selected-values{
			background-color: #FFA2A2;
		}
		
		.selected-values option{
			background-color: #fff;
		}
    </style>
</head>
<script type="text/javascript">
	$(function(){
		$(".createNewOrder").click(function(){
			
			window.open('/orders/create/order.do', "새주문생성" , "width=1500, height=900, top=100, left=100, scrollbars=no");
			
		});
	});
$.ajaxSetup({
	
    beforeSend: function(xhr) {
        xhr.setRequestHeader("AJAX", true);
     },
     error: function(xhr, status, err) {
        if (xhr.status == 401) {
            alert("인증에 실패 했습니다. 로그인 페이지로 이동합니다.");
            location.href = "/loginFail.do";
         } else if (xhr.status == 403) {
            alert("세션이 만료가 되었습니다. 로그인 페이지로 이동합니다.");
              location.href = "/login.do";
         } else if (xhr.status == 500) {
             alert(" 500에러 ");
        }
     }
});
	/* function openProjectWhenSetupPage(){
		$.ajax({
			url:"<c:url value='/project/project_alarm.do'/>",
			method:"POST",
			async:false,
			success:function(data){
				projectTopRewrite(data);
				
				setTimeout(function(){
					openProjectWhenSetupPage();
					clearTimeout(this);
				}, 60000);
				
			}
		});
	} */
	
	$.ajax({
		url:"<c:url value='/project/project_alarm.do'/>",
		method:"POST",
		success:function(data){
			projectTopRewrite(data);
			/* openProjectWhenSetupPage(); */
		}
	});
	
	
function projectTopRewrite(data){
		var alarmCount = 0;
		var projectList = "";
		var projects = new Array();
		
		$.each(data, function(idx){
			
			projectList+='<a href="#" class="list-group-item list-group-item-action showDetailProject" ';
			
			 if(this.proTopAlarmCheck === true){
	            projectList+=' style="background: beige;"';
	         }
			 
			projectList+=' id="setInterval'+this.proPk+'"   data-toggle="modal" data-target="#projectModal">'
				+'<input type="hidden" value="'+this.proPk+'" name="proPk">'
                +'<div class="notification-info">'
                    +'<div class="notification-list-user-img">';
                    
                    if(this.proThumbnailImage != null){
                    	
                    	projectList+='<img src="${resourcePath}/resources/images/project_image/'+this.proThumbnailImage+'" class="user-avatar-md rounded-circle">';
                    }
                    
                    projectList+='</div>'
                    +'<div class="notification-list-user-block"><span class="notification-list-user-name" style="color:'+this.proTitleColor+'"> '+this.proTitle+'</span>';
                    
                    if(this.proDetail != null){
                    	projectList+=this.proDetail;
                    }
                    
             projectList+='<div class="notification-date">'+formatDate(this.proRegdate)+'</div>'
                    +'</div>'
                +'</div>'
            +'</a>';
            
            if(this.proTopAlarmCheck === true){
            	alarmCount++;
            	var ProjectsVO = new Object();
            	ProjectsVO.proPk = this.proPk;
            	ProjectsVO.proTitle = this.proTitle;
            	
            	projects.push(JSON.stringify(ProjectsVO));
            }
            
		});
		
		if(alarmCount == 0){
			$("#alarmDiv").hide();
			$("#projectAlarm").hide();
			
			
		}else{
			alarmDivDisplay(projects);
        	$("#projectAlarm").show();
        	console.log(projects);
		}
		
		$(".topFixedProjectListDiv").html(projectList);
		
	}
	
	function alarmDivDisplay(data){
		/* alarmDivHTML =data;
		$("#alarmId").text(alarmDivHTML);
		$("#alarmDiv").show(); */
		
		
		window.open("<c:url value='/alarm/popup.do?projects="+encodeURI(data)+"'/>", "업무 알람" , "width=430, height=220, top=200, left=1200, scrollbars=no");
		
	}

</script>
<body>
    <div class="dashboard-main-wrapper">
        <div class="dashboard-header">
            <nav class="navbar navbar-expand-lg bg-white fixed-top">
                <a class="navbar-brand" href="<c:url value='/main/home.do'/>" style="color:#FFA2A2;">삼형제고기</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon fas fa-angle-down"></span>
                </button>
                <div class="collapse navbar-collapse " id="navbarSupportedContent">
                    <ul class="navbar-nav ml-auto navbar-right-top" style="display: -webkit-box; float: right;">
                        <li class="nav-item">
                        </li>
                        <sec:authorize access="hasRole('ROLE_MASTER')">
                        <li class="nav-item dropdown notification">
                            <a class="nav-link nav-icons" href="#" id="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-cart-plus"></i></a>
                            <ul class="dropdown-menu dropdown-menu-right notification-dropdown">
                                <li>
                                    <div class="card" style="margin-bottom:0;">
                                    	<div class="card-header">
                                    		<h5 style="margin: 0;text-align: center;">분류실 메세지 보내기</h5>
                                    		
                                    	</div>
										<div class="card-body">
											<textarea class="form-control" id="ccMessageWindow" rows="7" cols="50" readonly="readonly"></textarea>
									        <div class="input-group">
						                    	<input id="ccInputMessage" type="text" class="form-control" placeholder="">
						                    	
						                        <div class="input-group-append">
						                        	<button type="button" id="ccOpenSocket" onclick="ccOpenSocket();" style="display: none;">Open</button>
						        					<!-- <button type="button" onclick="send();">Send</button> -->
						                        	<button type="button" id="ccSendBtn" class="btn btn-primary btn-xs"> 전송 </button>
						                        </div>
						                    </div>
										</div>
									</div>
                                </li>
                            </ul>
                        </li>
                        
                        <li class="nav-item dropdown notification">
                            <a class="nav-link nav-icons" href="#" id="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-piggy-bank"></i></a>
                            <ul class="dropdown-menu dropdown-menu-right notification-dropdown">
                                <li>
                                    <div class="card" style="margin-bottom:0;">
                                    	<div class="card-header">
                                    		<h5 style="margin: 0;text-align: center;">2층 메세지 보내기</h5>
                                    		
                                    		
                                    			<p style="margin: 0;text-align: center;"> 라벨지 프로그램을 관리자 모드로 변경할 경우 관리자모드, 일반적인 경우로 돌리려면 일반모드 라고 작성하면 됨 </p>
                                    			
                                    		
                                    	</div>
										<div class="card-body">
											<textarea class="form-control" id="messageWindow" rows="7" cols="50" readonly="readonly"></textarea>
									        <div class="input-group">
									        	<sec:authentication var="principal" property="principal" />
									        	
									        	<input type="hidden" id="sender" value="${principal.username }[${principal.adminname }]" >
						                    	<input id="inputMessage" type="text" class="form-control" placeholder="">
						                    	
						                        <div class="input-group-append">
						                        	<button type="button" id="openSocket" onclick="openSocket();" style="display: none;">Open</button>
						        					<!-- <button type="button" onclick="send();">Send</button> -->
						        					
						        					
						                        	<button type="button" id="chatSendBtn" class="btn btn-primary btn-xs"> 전송 </button>
						                        </div>
						                    </div>
										</div>
									</div>
                                </li>
                            </ul>
                        </li>
                        </sec:authorize>
                        <li class="nav-item dropdown notification">
                            <a class="nav-link nav-icons" href="#" id="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="far fa-comments"></i> <span class="indicator" id="sendSeqAlarm"></span></a>
                            <ul class="dropdown-menu dropdown-menu-right notification-dropdown">
                                <li>
                                    <div class="notification-title"> 강제 출고 요청  </div>
                                    <div class="notification-list">
                                        <div id="sendingReqHTML" class="list-group">
                                            
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown notification">
                            <a class="nav-link nav-icons" href="#" id="navbarDropdownMenuLink1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-fw fa-bell" id="alarmBellIcon"></i> <span class="indicator" id="projectAlarm"></span></a>
                            <ul class="dropdown-menu dropdown-menu-right notification-dropdown">
                                <li>
                                    <div class="notification-title"> 업무 알람 </div>
                                    <div class="notification-list">
                                        <div class="list-group topFixedProjectListDiv">
                                            <a href="#" class="list-group-item list-group-item-action active"   data-toggle="modal" data-target="#projectModal">
                                                
                                            </a>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="list-footer"><a href="<c:url value='/project/projects.do'/>"> 모든 업무 보기 </a></div>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown nav-user">
                            <a class="nav-link nav-user-img" href="#" id="navbarDropdownMenuLink2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="${resourcePath}/resources/images/3bgogi_icon.png" alt="" class="user-avatar-md rounded-circle"></a>
                            <div class="dropdown-menu dropdown-menu-right nav-user-dropdown" aria-labelledby="navbarDropdownMenuLink2">
                                <div class="nav-user-info">
                                    <h5 class="mb-0 text-white nav-user-name">
									<sec:authentication var="principal" property="principal" />
										접속자 ID [ ${principal.username } ] 

									 </h5>
                                    <span class="status"></span><span class="ml-2">- ${principal.adminname } </span>
                                </div>
                                <a class="dropdown-item" href="#"><i class="fas fa-user mr-2"></i> 계정 </a>
                                <a class="dropdown-item" href="#"><i class="fas fa-cog mr-2"></i> 환경설정 </a>
                                <a class="dropdown-item" href="<c:url value='/logout.do'/>"><i class="fas fa-power-off mr-2"></i> 로그아웃 </a>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>