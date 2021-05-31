<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){

    		$("#piPkAllSelect").click(function(){
    			
    			if($(this).is(":checked")){
    				$("input[name=piPk]").prop("checked","checked");
    				
    			}else{
    				$("input[name=piPk]").prop("checked","");
    				
    			}
    			
    		});
    		
    		$("input[name=piPk]").click(function(){
    			
    			if($(this).is(":checked")){
    				
    			}else{
    				
    				if($("#piPkAllSelect").is(":checked")){
    					$("#piPkAllSelect").prop("checked","");
    					
    				}
    			}
    			
    			/*		if($("#orSeiralSpecialNumberAllSelect").is(":checked")){
    				$("input[name=orSerialSpecialNumberList]").prop("checked","checked");
    			}else{
    				$("input[name=orSerialSpecialNumberList]").prop("checked","");
    			}
    			*/
    			
    		});
    		
    		$('#dateStart, #dateEnd').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d'
    			
    		});
    		
    		$(".fileDownload").click(function(){
				var piFilePath = $(this).data("file-path");
				var piFileUniqName = $(this).data("file-uniq-name");
				
				var totalPath = piFilePath+piFileUniqName;
				var source = totalPath.split("resources");
				
				$("#piImg").attr("src","/resources/"+source[1] ).load();
				
    		});
    		
    		$("#deleteProductInfo").click(function(){
    			piPkSize = $("input[name=piPk]:checked").length;
    			
    			if(piPkSize > 1 ){
    				alert("한 건의 거래내역서만 선택해주세요");
    				
    				return false;
    				
    			}else if(piPkSize == 0){
    				alert("선택된 거래내역서가 없습니다");
    				
    				return false;
    			}
    			
    			if(confirm(piPkSize+" 개의 거래내역서를 삭제하시겠습니까?")){
    				
    				piPk = $("input[name=piPk]:checked").val();
    				
    				$.ajax({
        				type       : 'GET',
        				async		: false,
        				data       : {
        					"piPk":piPk
        				},
        				url        : '/tax/product_info/delete.do',
        				success    : function(data){
        					alert(data);
        					
        					location.reload();
        					
        				}
        				
        			});
    				
    			}
    			
    		});
    		
    		$("input[name=taxFlag]").change(function(){
    				
    			piPk = $(this).data("pi-pk");
    			
    			$.ajax({
    				type       : 'GET',
    				async		: false,
    				data       : {
    					"piPk":piPk
    				},
    				url        : '/tax/product_info/tax_update.do',
    				success    : function(data){
    					if(data == 1){		
    						alert("변경 완료");
    						
    					}else{
    						alert("변경 실패");
    						
    					}
    				}
    				
    			});
    			
    			$("#productInfoSearchForm").submit();
    			
    		});
    		
    		$("input[name=accFlag]").change(function(){
				
    			piPk = $(this).data("pi-pk");
    			
    			$.ajax({
    				type       : 'GET',
    				async		: false,
    				data       : {
    					"piPk":piPk
    				},
    				url        : '/tax/product_info/acc_update.do',
    				success    : function(data){
    					if(data == 1){		
    						alert("변경 완료");
    						
    					}else{
    						alert("변경 실패");
    						
    					}
    				}
    				
    			});
    			
    			
    			$("#productInfoSearchForm").submit();
    			
    		});
    		
    		
    	});
    	
    	function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#productInfoSearchForm").submit();
		
		}

    </script>
    <style type="text/css">
    	.table-3bgogi-hover:hover{
    		background: #FFC6C6;
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
                            <h2 class="pageheader-title"> 거래내역서 목록 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 세금계산서</a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 거래내역서 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 목록 </li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- end pageheader -->
                <!-- ============================================================== -->
                <div class="ecommerce-widget">
                    <div class="row">
                        <!-- ============================================================== -->
                        <!-- valifation types -->
                        <!-- ============================================================== -->
                        <div class="col-xl-9 col-lg-8 col-md-8 col-sm-12 col-12">
	                        <div class="card">
                                <h5 class="card-header">거래내역서 목록</h5>
                                <div class="card-body">
                                	<div class="table-responsive">
                                		<button class="btn btn-danger btn-xs mb-2" id="deleteProductInfo" type="button"> 선택 삭제</button>
                                		<a class="btn btn-success btn-xs mb-2" href="/tax/product_info/insert.do"> 거래내역서 추가 </a>
	                                    <table id="example2" class="table table-bordered" style="text-align:center; font-size: 12px; word-break: keep-all; white-space: nowrap;">
	                                        <colgroup>
	                                        	<col width="40px" />
	                                        	<col width="180px" />
												<col width="180px" />
												<col width="80px" />
												<col width="100px" />
												<col width="100px" />
												<col width="100px" />
												<col width="90px" />
												<col width="90px" />
												<col width="80px" />
												<col width="80px" />
											</colgroup>
	                                        <thead class="bg-light">
	                                            <tr>
	                                            	<th scope="col">
		                                               	<label class="custom-control custom-checkbox be-select-all">
								                             <input class="custom-control-input chk_all" type="checkbox" name="chk_all" id="piPkAllSelect"><span class="custom-control-label"></span>
								                        </label>
		                                            </th>
	                                            	<th >거래처</th>
	                                                <th >상품 (명세서)</th>
	                                                <th >수량</th>
	                                                <th >공급가</th>
	                                                <th >세액</th>
	                                                <th >합계</th>
	                                                <th >세금계산서</th>
	                                                <th >이체</th>
	                                                <th >발급일자</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:if test="${!empty piList}">
	                                        		<c:set var="totalAmount" value="0"></c:set>
		                                        	<c:set var="totalCost" value="0"></c:set>
		                                        	<c:set var="totalTax" value="0"></c:set>
		                                        	<c:set var="totalPrice" value="0"></c:set>
		                                        	<c:forEach var="pilist" items="${piList }">
		                                        		<tr>
		                                        			<td scope="row" class="checkTd">
				                                                <label class="custom-control custom-checkbox be-select-all">
										                        	<input class="custom-control-input chk_all" type="checkbox" name="piPk" value="${pilist.piPk }">
										                            <span class="custom-control-label"></span>
										                    	</label>
				                                            </td>
		                                        			<td>${pilist.rcNames }</td>
		                                        			<td>
			                                        			<a href="<c:url value='/tax/product_info/detail.do?piPk=${pilist.piPk }'/>">${pilist.piName }</a>
			                                        			<c:if test="${!empty pilist.piFilePath }">
			                                        			
			                                        				<a>
			                                        					<i class="fas fa-archive fileDownload"
			                                        						data-toggle="modal" data-target="#piImgFile"
			                                        						data-file-path="${pilist.piFilePath }" 
			                                        						data-file-uniq-name="${pilist.piFileUniqName }"
			                                        						
			                                        					 ></i>
			                                        				</a>
			                                        				
			                                        			</c:if>
		                                        			</td>
		                                        			<td>
		                                        				<fmt:formatNumber value="${pilist.piQty }" pattern="#,###"/>
		                                        				<c:set var="totalAmount" value="${totalAmount + pilist.piQty}"></c:set>
		                                        			</td>
		                                        			<td>
		                                        				<fmt:formatNumber value="${pilist.piCost }" pattern="#,###"/>
		                                        				<c:set var="totalCost" value="${totalCost + pilist.piCost}"></c:set>
		                                        			</td>
		                                        			<td>
		                                        				<fmt:formatNumber value="${pilist.piTax }" pattern="#,###"/>
		                                        				<c:set var="totalTax" value="${totalTax + pilist.piTax}"></c:set>
		                                        			</td>
		                                        			
		                                        			<td>
		                                        				<fmt:formatNumber value="${pilist.piTotalCost }" pattern="#,###"/>
		                                        				<c:set var="totalPrice" value="${ totalPrice + pilist.piTotalCost }"></c:set>
		                                        			</td>
		                                        			
		                                        			<td>
		                                        				<label class="custom-control custom-checkbox custom-control-inline">
					                                                <input type="checkbox" class="custom-control-input" name="taxFlag" data-pi-pk="${pilist.piPk }"
					                                                	<c:if test="${pilist.piTaxbilCheckFlag == true}">
				                                        					checked="checked"
				                                        				</c:if>
				                                        				<c:if test="${pilist.piTaxbilCheckFlag == false}">
				                                        				</c:if>
					                                                ><span class="custom-control-label"></span>
					                                            </label>
		                                        			</td>
		                                        			
		                                        			<td>
		                                        				<label class="custom-control custom-checkbox custom-control-inline">
					                                                <input type="checkbox" class="custom-control-input" name="accFlag" data-pi-pk="${pilist.piPk }"
					                                                	<c:if test="${pilist.piAccFlag == true}">
				                                        					checked="checked"
				                                        				</c:if>
				                                        				<c:if test="${pilist.piAccFlag == false}">
				                                        				</c:if>
					                                                ><span class="custom-control-label"></span>
					                                            </label>
		                                        			</td>
		                                        			<td>${pilist.piInputDate }</td>
		                                        		</tr>
		                                        	</c:forEach>
		                                        	
	                                        		<tr>
	                                        			<td colspan="3"> 총 합</td>
		                                                <td >
		                                                	<fmt:formatNumber value="${totalAmount }" pattern="#,###" />
		                                                </td>
		                                                <td >
		                                                	<fmt:formatNumber value="${totalCost }" pattern="#,###" />
		                                                </td>
		                                                <td >
		                                                	<fmt:formatNumber value="${totalTax }" pattern="#,###" />
		                                                </td>
		                                                <td >
		                                                	<fmt:formatNumber value="${totalPrice }" pattern="#,###" />
		                                                </td>
		                                                <td colspan="3" style="text-align: center;"> - </td>
	                                        		</tr>
	                                        	</c:if>
	                                        </tbody>
	                                    </table>
                                	</div>
                                </div>
                            </div>
                        </div>
                        
                        	<!-- 페이징 처리에 필요한 값 -->
						<div class="col-xl-3 col-lg-4 col-md-4 col-sm-12 col-12">
                        	<form id="productInfoSearchForm" action="" method="get">
                        	<input type="hidden" name="currentPage" value="${osVO.currentPage}">
								<div class="card">
									<div class="card-body border-top">
										<h3 class="font-16"> 상품 명</h3>
										<input type="text" class="form-control" id="searchKeyword" name="searchKeyword" placeholder="상품 명을 입력해주세요" value="${osVO.searchKeyword }">
									</div>
									
									<div class="card-body">
										<h3 class="font-16"> 거래처 </h3>
										<select class="form-control" id="ccPk" name="ccPk" data-live-search="true" data-size="8">
											<option value="0">전체</option>
											<c:forEach var="rclist" items="${rcList }">
												<option value="${rclist.rcPk }"  
													<c:if test="${osVO.ccPk == rclist.rcPk}">
														selected="selected"
													</c:if>
												>${rclist.rcName }</option>
											</c:forEach>
											
										</select>
									</div>
									<div class="card-body">
										<h3 class="font-16"> 페이지 당 목록 개수 </h3>
										<select class="form-control mb-3" name=recordCountPerPage>
											<option value="10"
												<c:if test="${osVO.recordCountPerPage == 10 }">
													selected="selected"
												</c:if>
											>10 개씩</option>
											<option value="20"
												<c:if test="${osVO.recordCountPerPage == 20 }">
													selected="selected"
												</c:if>
											>20 개씩</option>
											<option value="30"
												<c:if test="${osVO.recordCountPerPage == 30 }">
													selected="selected"
												</c:if>
											>30 개씩</option>
											<option value="40"
												<c:if test="${osVO.recordCountPerPage == 40 }">
													selected="selected"
												</c:if>
											>40 개씩</option>
										</select>
									</div>
									<div class="card-body">
										<h3 class="font-16"> 체크사항 </h3>
										<select class="form-control mb-3" name="taxFlag">
											<option value="0"
												<c:if test="${osVO.taxFlag == 0 }">
													selected="selected"
												</c:if>
											>세금계산서</option>
											<option value="1"
												<c:if test="${osVO.taxFlag == 1 }">
													selected="selected"
												</c:if>
											>세금계산서 있는 건</option>
											<option value="2"
												<c:if test="${osVO.taxFlag == 2 }">
													selected="selected"
												</c:if>
											>세금계산서 없는 건</option>
										</select>
										
										<select class="form-control mb-3" name="labelBtn">
											<option value="0"
												<c:if test="${osVO.labelBtn == 0 }">
													selected="selected"
												</c:if>
											>이체내역</option>
											<option value="1"
												<c:if test="${osVO.labelBtn == 1 }">
													selected="selected"
												</c:if>
											>이체내역 있는 건</option>
											<option value="2"
												<c:if test="${osVO.labelBtn == 2 }">
													selected="selected"
												</c:if>
											>이체내역 없는 건</option>
										</select>
										
										<select class="form-control mb-3" name="matchingFlag">
											<option value="0"
												<c:if test="${osVO.matchingFlag == 0 }">
													selected="selected"
												</c:if>
											>명세서</option>
											<option value="1"
												<c:if test="${osVO.matchingFlag == 1 }">
													selected="selected"
												</c:if>
											>명세서 있는 건</option>
											<option value="2"
												<c:if test="${osVO.matchingFlag == 2 }">
													selected="selected"
												</c:if>
											>명세서 없는 건</option>
											
										</select>
									</div>
									<div class="card-body">
										<h3 class="font-16"> 발급일자 </h3>
										<div class="form-row">							
											<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12 mb-2">
												<input type="text" class="form-control" id="dateStart" name="dateStart" value="${osVO.dateStart }"/>
											</div>		
											<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12 mb-2">
												<input type="text" class="form-control" id="dateEnd" name="dateEnd" value="${osVO.dateEnd }"/>
											</div>		
										</div>
									</div>    
									<div class="card-body border-top">
										<button class="btn btn-secondary btn-lg btn-block"> 검색하기 </button>
									</div>
								</div>
							</form>
						</div>
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<nav aria-label="Page navigation" style="text-align: center;">
								<ul class="pagination" style="display: inline-flex;  flex-wrap: wrap;">
									<c:if test='${osVO.firstPage>1 }'>
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
						</div>

						<!-- ============================================================== -->
                        <!-- end valifation types -->
                        <!-- ============================================================== -->
                    </div>
                 </div>
            </div>
           <iframe id="fileDownloadIframe" width="0" height="0">
           
			</iframe> 
        <!-- /page content -->
        <div class="modal fade" id="piImgFile" tabindex="-1" role="dialog" aria-labelledby="#piImgFile" aria-hidden="true">
			<div class="modal-dialog" role="document" style="max-width: 900px;">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="stockModal"> 이미지 파일 </h5>
						<a href="#" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</a>
					</div>
					<div class="modal-body" >
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="piImgDiv">
							<img id="piImg">
                        </div>
					</div>
				</div>
			</div>
		</div>
		<script src="${pageContext.request.contextPath}/resources/vendor/multi-select/js/jquery.multi-select.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#ccPk").selectpicker();
				
			});
		</script>
        <%@ include file="../../inc/bottom.jsp" %>