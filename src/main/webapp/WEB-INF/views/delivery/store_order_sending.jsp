<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		$('#dateStart').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d'
    			
    		});
    		
    	});
    	
    	function storeSending(ssPk){
    		if(confirm("발송 처리를 하시겠습니까?")){
    			location.href="<c:url value='/delivery/store_sending.do?ssPk="+ssPk+"'/>";
    			
    		}else{
    			
    		}
    		
    	}
    	
    	function storeSendingCancled(sendingTime){
    		var dateStart = $("#dateStart").val();
    		
    		if(confirm("발송 처리를 해제하시겠습니까?")){
    			location.href="<c:url value='/delivery/store_sending_cancled.do?dateEnd="+sendingTime+"&dateStart="+dateStart+"'/>";
    			
    		}else{
    			
    		}
    		
    	}
    	
    	function storeSendingResults(sendingTime, store_num){
    		ssSendingDay = $("#dateStart").val();
    		
    		if(confirm("발송 목록을 보시겠습니까?")){
    			location.href="<c:url value='/delivery/sending/results.do?ssRegdate="+sendingTime+"&ssPk="+store_num+"&ssSendingDay="+ssSendingDay+"'/>";
    			
    		}else{
    			
    		}
    	}
    	
    	function storeAutoSending(sendingTime, store_num){
    		ssSendingDay = $("#dateStart").val();
    		
    		if(confirm("자동 발송처리를 하시겠습니까?")){
    			location.href="<c:url value='/delivery/godomall_sending.do?ssRegdate="+sendingTime+"&ssPk="+store_num+"&ssSendingDay="+ssSendingDay+"'/>";
    			
    		}else{
    			
    		}
    	}
    	
    	function storeExcelDownload(sendingTime, store_num){
    		
    		if(confirm("발송 엑셀파일을 다운로드 하시겠습니까?")){
    			
    			var ssRegdateInput = document.createElement("input");
    			ssRegdateInput.name="ssRegdate";
    			ssRegdateInput.type="hidden";
    			ssRegdateInput.value=sendingTime;
    			
    			var ssPkInput = document.createElement("input");
    			ssPkInput.name="ssPk";
    			ssPkInput.type="hidden";
    			ssPkInput.value=store_num;
    			
    			var ssSendingDayInput = document.createElement("input");
    			ssSendingDayInput.name="ssSendingDay";
    			ssSendingDayInput.type="hidden";
    			ssSendingDayInput.value=$("#dateStart").val();
    			
    			var excelDownloadForm =  document.createElement("form")
    			excelDownloadForm.action="/delivery/sending/excel_download.do";
    			excelDownloadForm.method="GET";
    			
    			
    			
    			excelDownloadForm.append(ssRegdateInput);
    			excelDownloadForm.append(ssPkInput);
    			excelDownloadForm.append(ssSendingDayInput);
    			
    			$("#excelDownloadIframe").append(excelDownloadForm);
    			
    			excelDownloadForm.submit();
    			
    			$("#excelDownloadIframe").html("");
    			
    			
    			
    			/* location.href="<c:url value='/delivery/sending/excel_download.do?ssRegdate="+sendingTime+"&ssPk="+store_num+"'/>"; */
    			/* $("#excelDownload").prop("href","/delivery/sending/excel_download.do?ssRegdate="+sendingTime+"&ssPk="+store_num);
    			$("#excelDownload").click(); */
		    	
    			/* $.ajax({
				    type       : 'GET',
				    async      : false,
				    data       : {
				    	"ssRegdate":sendingTime,
				    	"ssPk":store_num
				    },
				    url        : "<c:url value='/delivery/sending/excel_download.do'/>",
				    success    : function(data){
				    	
				    	alert(data);
				    	
				    	
				    	
				    },error : function(){
				    	alert("실패");
				    }
				    
				}); */
    			
    		}else{
    			
    		}
    		
    	}
    	
    </script>
    <style type="text/css">
    	.sending-header{
    	background-color: #71748d; 
    	font-color: #fff;
    	}
    	
    	.sending-header > th{
    	color: #fff !important;
    	
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
                            <h2 class="pageheader-title"> 판매처 송장부여 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 발송 및 출고</a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 판매처 송장부여 </a></li>
                                        
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
                		<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        	<div class="card">
	                        	<div class="card-body">
	                        		<form id="epostPageForm" action="<c:url value='/delivery/store_order_sending.do'/>" method="get">
	                        			<div class="email-filters-right">
				                            <div class="btn-group">
				                            	<input type="text" class="btn btn-light" id="dateStart" name="dateStart" style="width: 8em;" value="${osVO.dateStart }"/>
				                            </div>
				                            <div class="btn-group" style="text-align: right;">
				                                <button class="btn btn-primary" type="submit"> 발송일 검색 </button>
				                            </div>
				                        </div>
	                        		</form>
		                        </div>
	                        </div>
                        </div>
                        
                        <iframe id="fileDown" style='visibility:hidden' src="" width="1" height="1"></iframe>
                    	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <div class="card-body">
                                	<div class="table-responsive">
	                                    <table class="table table-bordered" style="word-break: keep-all;">
	                                        <thead>
	                                            <tr class="sending-header text-center">
	                                                <th scope="col">판매처</th>
	                                                <th scope="col">상품 개수</th>
	                                                <th scope="col">송장 개수</th>
	                                                <th scope="col">발송 시간</th>
	                                                <th scope="col">판매처 발송파일</th>
	                                                <th scope="col"> 목록 </th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:if test="${empty storeSendingResult }">
	                                        		<tr>
		                                                <th scope="row" colspan="7" style="text-align: center;">발송값이 없습니다</th>
		                                            </tr>
	                                        	</c:if>
	                                        	<c:if test="${!empty storeSendingResult }">
	                                        		<c:forEach var="sendinglist" items="${storeSendingResult }">
	                                        			<tr>
			                                                <th scope="row">${sendinglist.ssName }</th>
			                                                <td>${sendinglist.orAmount }</td>
			                                                <td>${sendinglist.orSerialSpecialNumber }</td>
			                                                <td>
			                                                	<c:if test="${empty sendinglist.orOutputDate }">
			                                                		<button class="btn btn-outline-success" onclick="storeSending('${sendinglist.ssFk }')">발송하기</button>
			                                                	</c:if>
			                                                	<c:if test="${!empty sendinglist.orOutputDate }">
			                                                		<button class="btn btn-outline-danger" onclick="storeSendingCancled('<fmt:formatDate value='${sendinglist.orOutputDate }' pattern='yyyy-MM-dd HH:mm:ss'/>')">
			                                                		<fmt:formatDate value='${sendinglist.orOutputDate }' pattern='yyyy-MM-dd HH:mm:ss'/>
			                                                		발송취소
			                                                		</button>
			                                                	</c:if>
			                                                </td>
			                                                <td>
			                                                	<c:if test="${!empty sendinglist.orOutputDate}">
			                                                		<c:if test="${sendinglist.orUserColumn3 == 0 }"> 
				                                                		<button class="btn btn-outline-success" onclick="storeExcelDownload('<fmt:formatDate value='${sendinglist.orOutputDate }' pattern='yyyy-MM-dd HH:mm:ss'/>', '${sendinglist.ssFk }')">
				                                                		다운로드
				                                                		</button>
			                                                		</c:if>
			                                                		
			                                                		<c:if test="${sendinglist.orUserColumn3 == 1}">
			                                                			<button class="btn btn-outline-danger" onclick="storeAutoSending('<fmt:formatDate value='${sendinglist.orOutputDate }' pattern='yyyy-MM-dd HH:mm:ss'/>', '${sendinglist.ssFk }')">
				                                                			자동발송
				                                                		</button>
			                                                		</c:if>
			                                                	</c:if>
			                                                </td>
			                                                <td>
			                                                	<c:if test="${!empty sendinglist.orOutputDate }">			                                                	
				                                                	<button class="btn btn-primary btn-xs" type="button"  onclick="storeSendingResults('<fmt:formatDate value='${sendinglist.orOutputDate }' pattern='yyyy-MM-dd HH:mm:ss'/>', '${sendinglist.ssFk }')">발송 목록</button>
			                                                	</c:if>
			                                                	
			                                                	<c:if test="${empty sendinglist.orOutputDate }">
			                                                		-
			                                                	</c:if>
			                                                </td>
			                                            </tr>
	                                        		</c:forEach>
	                                        	</c:if>
	                                        </tbody>
	                                    </table>
                                	</div>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
        <!-- /page content -->
		<iframe id="excelDownloadIframe" width="0" height="0">
		</iframe>
			         
        <%@ include file="../inc/bottom.jsp" %>