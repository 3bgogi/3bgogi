<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		$("#deleteFreebie").click(function(){
    			
    			fbPk = $("input[name=fbPk]:checked").val();
    			fbSize = $("input[name=fbPk]:checked").length;

    			if(fbSize > 1){
    				alert("한 번에 한 정책만 삭제할 수 있습니다");
    				return false;
    				
    			}if(fbSize == 0){
    				alert("사은품 정책을 선택해주세요");
    				return false;
    				
    			}
    			
    			if(confirm("해당 사은품 정책을 삭제하시겠습니까?")){
    				location.href="<c:url value='/config/freebie/delete.do?fbPk="+fbPk+"'/>";

    			}
    		});
    	});
    	
    	function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#freebieForm").submit();
			
		}
    	
    	function updateFreebie(fbPk){
    		location.href="<c:url value='/config/freebie/update.do?fbPk="+fbPk+"'/>";
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
                            <h2 class="pageheader-title"> 사은품 정책 목록 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 설정 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 사은품 정책 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 사은품 정책 목록</li>
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
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                        <div class="card">
                                <div class="card-body">
                                	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mb-2">
                                		<a class="btn btn-success btn-xs mb-2" href="<c:url value='/config/freebie/insert.do'/>"> 사은품 정책 추가 </a>
	                                	<button class="btn btn-danger btn-xs mb-2" type="button" id="deleteFreebie"> 선택 사은품 정책 삭제 </button>
	                                	
	                                </div>
                                	<div class="table-responsive">
	                                    <table id="example2" class="table table-bordered" style="text-align: center;">
	                                        <thead class="bg-light">
	                                            <tr>
	                                                <th width="4%">
	                                                	<label class="custom-control custom-checkbox be-select-all">
								                        	<input class="custom-control-input chk_all" type="checkbox" name="chk_all" id="fbAllSelect"><span class="custom-control-label"></span>
								                        </label>
	                                                </th>
	                                                
	                                                <th width="20%">사은품 정책명</th>
	                                                <th width="10%">판매처</th>
	                                                <th width="20%">기간</th>
	                                                <th width="20%">상품명</th>
	                                                <th width="15%">합계 금액</th>
	                                                <th width="20%">등록일</th>
	                                                <th width="8%">수정</th>
	                                                
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:if test="${empty fbList }">
	                                        		<tr>
	                                        			<td colspan="8" style="text-align: center;"> 등록된 사은품 정책이 없습니다 </td>
	                                        		</tr>
	                                        	</c:if>
	                                        	<c:if test="${!empty fbList }">
		                                        	<c:forEach var="fblist" items="${fbList }">                                        	
			                                            <tr class="table-3bgogi-hover">
			                                                <td scope="row" class="checkTd">
				                                            	<label class="custom-control custom-checkbox be-select-all">
										                        	<input class="custom-control-input chk_all" type="checkbox" name="fbPk"
										                        	value="${fblist.fbPk }"><span class="custom-control-label"></span>
										                        </label>
				                                            </td>
			                                                <td>${fblist.fbName }</td>
			                                                
			                                                <c:set var="stores" value="${fn:split(fblist.ssList,',') }"/>
			                                                <td>
			                                                	<c:forEach var="sslist" items="${ssList }">

									                        			<c:forEach var="storeslist" items="${stores }">								                        			
										                        			<c:if test="${sslist.ssPk == storeslist }">
										                        				${sslist.ssName }<br>
										                        			</c:if>
									                        			</c:forEach>
									                        	</c:forEach>
			                                                
			                                                </td>
			                                                <td>
			                                                	<c:if test="${fblist.fbRegType == 'od.or_regdate' }">
			                                                		업무페이지 등록 기준
			                                                	</c:if>
			                                                	<c:if test="${fblist.fbRegType == 'od.or_settlement_day' }">
			                                                		상품 결제일 기준
			                                                	</c:if>
			                                                	
			                                                	<br>
			                                                	<fmt:parseDate var="minDate" value="${fblist.fbMinDate }" pattern="yyyy-MM-dd HH:mm"/>
			                                                	시작 : <fmt:formatDate value="${minDate }" pattern="yyyy-MM-dd HH:mm"/>
			                                                	<br>
			                                                	<fmt:parseDate var="maxDate" value="${fblist.fbMaxDate }" pattern="yyyy-MM-dd HH:mm"/>
			                                                	종료 : <fmt:formatDate value="${maxDate }" pattern="yyyy-MM-dd HH:mm"/>
			                                                
			                                                	
			                                                </td>
			                                                <td>${fblist.productName } [ ${fblist.optionName } ]</td>
			                                                <td><fmt:formatNumber value="${fblist.fbMinPrice }" pattern="#,###" />원  ~ <fmt:formatNumber value="${fblist.fbMaxPrice }" pattern="#,###" />원</td>
			                                                <td><fmt:formatDate value="${fblist.fbRegdate }" pattern="yyyy-MM-dd"/> </td>
			                                                <td><button type="button" class="btn btn-primary btn-xs" onclick="updateFreebie('${fblist.fbPk}')">수정</button></td>
			                                            </tr>
		                                        	</c:forEach>
	                                        	</c:if>
	                                        </tbody>
	                                    </table>
                                	</div>
                                </div>
                            </div>
                        </div>
                        
						<form class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="freebieForm">
							<input type="hidden" name="searchCondition" >
                        	<input type="hidden" name="searchKeyword">
                        	<input type="hidden" name="currentPage" value="${osVO.currentPage}">
							<nav aria-label="Page navigation" style="text-align: center;">
								<ul class="pagination" style="display: -webkit-inline-box;">
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
						</form>

						<!-- ============================================================== -->
                        <!-- end valifation types -->
                        <!-- ============================================================== -->
                    </div>
                 </div>
            </div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>