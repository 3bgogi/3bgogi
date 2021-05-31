<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		$('#dateStart').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d',
    			"setDate" : new Date(1985,10,01)
    			
    		});
    		$('#dateEnd').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d'
    		});
    		
    		$("#dateStart").click(function(){
    			$("#userSelect").prop("checked", true);
    		});
    		
    		$("#dateEnd").click(function(){
    			$("#userSelect").prop("checked", true);
    		});
    		
    		$("#excelResultDown").click(function(){

				if(confirm("검색 결과를 엑셀파일로 다운로드하시겠습니까?")){
	    			var divs = document.createElement("div");
	    			
	    			var excelDownloadForm =  document.createElement("form");
	    			excelDownloadForm.action="/config/event_msg_excel.do";
	    			excelDownloadForm.method="POST";

	    			var analyForms = $("#eventMsgForm").children().clone(true).appendTo(excelDownloadForm);
	    			
	    			/* excelDownloadForm.append(analyForms); */
	    			divs.append(excelDownloadForm);
	    			
	    			
	    			$("#excelDownloadIframe").append(divs);
	    		
	    			excelDownloadForm.submit();
	    			$("#excelDownloadIframe").html("");
	    		}
			});
    		
    		
    		$("#eventMsgForm").submit(function(){    			
	    		var ssList = $("#ssPkList").val();
				
				if(ssList == ''){
					alert("조회할 판매처를 선택해주세요");
					return false;
					
				}
				
    		});

    	});
    	
    	function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#eventMsgForm").submit();
			
		}
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
                                <h2 class="pageheader-title"> 문자 대상 추출 </h2>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                        	<li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 분석 </a></li>
                                            <li class="breadcrumb-item active" aria-current="page"> 문자 대상 추출 </li>
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
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="checkboxradio">
                                <form id="eventMsgForm" class="card" action="<c:url value='/config/event_msg.do'/>" method="GET">
                                	<input type="hidden" name="searchCondition" >
		                        	<input type="hidden" name="currentPage" value="${osVO.currentPage}">
                                	<div class="card-body">
                                		<h4> 검색 설정 </h4>
                                		<div class="btn-group">
				                        	<select class="selectpicker" multiple data-actions-box="true" data-width="200px" id="ssPkList" name="ssPkList" title="판매처를 선택해주세요">
								                <c:if test="${!empty ssList }">
								                	<c:forEach var="sslist" items="${ssList }">
								                    	<option value="${sslist.ssPk }"
								                        	<c:forEach var="ssPklist" items="${osVO.ssPkList }">
								                    			<c:if test="${ssPklist == sslist.ssPk }">
									                        		selected="selected"
									                        	</c:if>
								                    		</c:forEach>
								                        >${sslist.ssName }</option>
								                    </c:forEach>
								                </c:if>
							                </select>                                
				                        </div>
				                        
                                		<div class="btn-group">
				                                <label class="custom-control custom-radio custom-control-inline">
		                                        	<input type="radio" id="todays" name="datePeriod" value="0"
		                                        		<c:if test="${osVO.datePeriod == 0 }">
															 checked="checked"
														</c:if>
		                                        	class="custom-control-input"><span class="custom-control-label"> 1개월 </span>
		                                        </label>
		                                        <label class="custom-control custom-radio custom-control-inline">
		                                        	<input type="radio" id="weeksAgo" name="datePeriod" value="1" 
		                                        		<c:if test="${osVO.datePeriod == 1 }">
															 checked="checked"
														</c:if>
		                                        	class="custom-control-input"><span class="custom-control-label"> 6개월 </span>
		                                        </label>
		                                        <label class="custom-control custom-radio custom-control-inline">
		                                        	<input type="radio" id="userSelect" name="datePeriod" value="2" 
		                                        		<c:if test="${osVO.datePeriod == 2 }">
															 checked="checked"
														</c:if>
		                                        	class="custom-control-input"><span class="custom-control-label"> 임의 </span>
		                                        </label>
				                            </div>
                                		<div class="btn-group">
				                        	<input type="text" class="btn btn-light btn-xs" id="dateStart" name="dateStart" style="width: 8em;" value="${osVO.dateStart }"/> &nbsp;
				                            <input type="text" class="btn btn-light btn-xs" id="dateEnd" name="dateEnd" style="width: 8em;" value="${osVO.dateEnd }"/>                               
				                        </div>
				                        
				                        <div class="btn-group">
				                        	<select class="selectpicker" multiple data-actions-box="true" data-width="200px" id="cfPkList" name="cfPkList">
								            	<c:if test="${empty cfList }">
								                	<option value="0">등록된카테고리가 없습니다</option>
								                </c:if>
								                <c:if test="${!empty cfList }">
								                	<option value=""> 카테고리 선택 </option>
								                	<c:forEach var="cflist" items="${cfList }">
								                    	<option value="${cflist.cfPk }"
								                    		<c:forEach var="cfPklist" items="${osVO.cfPkList }">
								                    			<c:if test="${cflist.cfPk == cfPklist }">
									                        		selected="selected"
									                        	</c:if>
								                    		</c:forEach>
								                        >${cflist.cfCodeType }</option>
								                    </c:forEach>
								                </c:if>
							                </select>                                
				                        </div>
				                        <div class="btn-group">
				                            <select class="form-control" name="searchType">
								                <option value="matchingProduct"
								                   <c:if test="${osVO.searchType == 'matchingProduct' }">
														selected="selected"
													</c:if>
								                >매칭 상품명</option>
								                <option value="matchingOption"
								                    <c:if test="${osVO.searchType == 'matchingOption' }">
														selected="selected"
													</c:if>
								                >매칭 옵션명</option>
								                <option value="storeProduct"
								                    <c:if test="${osVO.searchType == 'storeProduct' }">
														selected="selected"
													</c:if>
								                >판매처 상품명</option>
								                <option value="storeOption"
								                    <c:if test="${osVO.searchType == 'storeOption' }">
														selected="selected"
													</c:if>
								                >판매처 옵션명</option>
							                </select>
				                        </div>
				                        <div class="btn-group">
				                            <input class="form-control" id="searchKeyword" name="searchKeyword" type="text"  placeholder="검색 내용을 입력해주세요" value="${osVO.searchKeyword }">
				                        </div>
				                        <div class="btn-group">
				                            <select class="form-control" name="exSearchType">
								                <option value="matchingProduct"
								                   <c:if test="${osVO.exSearchType == 'matchingProduct' }">
														selected="selected"
													</c:if>
								                >(추가 검색) 매칭 상품명</option>
								                <option value="matchingOption"
								                    <c:if test="${osVO.exSearchType == 'matchingOption' }">
														selected="selected"
													</c:if>
								                >(추가 검색) 매칭 옵션명</option>
								                <option value="storeProduct"
								                    <c:if test="${osVO.exSearchType == 'storeProduct' }">
														selected="selected"
													</c:if>
								                >(추가 검색) 판매처 상품명</option>
								                <option value="storeOption"
								                    <c:if test="${osVO.exSearchType == 'storeOption' }">
														selected="selected"
													</c:if>
								                >(추가 검색) 판매처 옵션명</option>
							                </select>
				                        </div>
				                        <div class="btn-group">
				                            <input class="form-control" id="exSerachKeyword" name="exSerachKeyword" type="text"  placeholder="추가 검색 내용을 입력해주세요" value="${osVO.exSerachKeyword }">
				                            <button class="btn btn-primary" type="submit"> 검색 </button>
				                        </div>
                                	</div>
                                </form>
                            </div>
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="checkboxradio">
                                <div class="card">
                                <h5 class="card-header"> 문자 대상자( 총 합 : <fmt:formatNumber value="${osVO.totalRecord }" pattern="#,###"/> 명) </h5>
                                <div class="card-body">
                                	<c:if test="${!empty odList }">                                	
	                                	<button class="btn btn-success btn-xs mb-3" id="excelResultDown">결과 엑셀 다운로드</button>
                                	</c:if>
                                    <table class="table table-hover">
                                    	
                                    	<thead>
											<tr>
												<th>연락처</th>
												<th>고객명</th>
											</tr>
                                    	</thead>
                                        <tbody>
                                        	<c:if test="${!empty odList }">
                                        		<c:forEach var="odlist" items="${odList }">                                        		
		                                            <tr>
		                                            	<td>${odlist.orBuyerContractNumber1 }</td>
		                                            	<td>${odlist.orBuyerName }</td>
		                                            </tr>
                                        		</c:forEach>           	
                                        	</c:if>
                                        	<c:if test="${empty odList }">
                                        		<tr>
                                        			<td colspan="2"> 문자 대상자가 존재하지 않습니다</td>
                                        		</tr>
                                        	</c:if>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            </div>
                            <form class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="eventMsgFormPaging" method="get" action="<c:url value='/config/event_msg.do'/>">
                            
							<c:if test="${osVO.totalRecord != 0 }">							
								<nav aria-label="Page navigation" style="text-align: center;">
									<ul class="pagination" style="display: inline-flex;  flex-wrap: wrap;">
										<c:if test='${osVO.firstPage > 1}'>
											<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${osVO.firstPage-1})">«</a></li>
										</c:if>
										
										
										<c:forEach var="i" step="1" end="${osVO.lastPage}" begin="${osVO.firstPage }">
											<li class="page-item 
												<c:if test='${osVO.currentPage == i }'>
													active
												</c:if>
											"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${i})">${i }</a></li>
										</c:forEach>
										<c:if test='${osVO.lastPage < osVO.totalPage}'>
											<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${osVO.lastPage+1})">»</a></li>
										</c:if>
									</ul>
								</nav>
							</c:if>
						</form>
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="checkboxradio">
                                <div class="card">
                                <h5 class="card-header"> 키워드 값 </h5>
                                <div class="card-body">
                                    <table class="table table-hover">
                                    	<thead>
											<tr>
												<th>판매처 상품명</th>
												<th>판매처 옵션명</th>
												<th>매칭 상품명</th>
												<th>매칭 옵션명</th>
											</tr>
                                    	</thead>
                                        <tbody>
                                        	<c:if test="${!empty keywords }">
                                        		<c:forEach var="keyword" items="${keywords }">                                        		
		                                            <tr>
		                                            	<td>${keyword.orProduct}</td>
		                                            	<td>${keyword.orProductOption}</td>
		                                            	<td>${keyword.orUserColumn1}</td>
		                                            	<td>${keyword.orUserColumn2}</td>
		                                            </tr>
                                        		</c:forEach>           	
                                        	</c:if>
                                        	
                                        	<c:if test="${empty keywords }">
                                        		<tr>
                                        			<td colspan="4"> 검색 키워드가 없습니다 </td>
                                        		</tr>
                                        	</c:if>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            </div>
                    </div>
                </div>
        <!-- /page content -->
        <iframe id="excelDownloadIframe" width="0" height="0">
		</iframe>  
        <%@ include file="../inc/bottom.jsp" %>