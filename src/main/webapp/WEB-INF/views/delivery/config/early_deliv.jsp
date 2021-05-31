<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	
    	$(function(){
    		$("button[name=delivImposBtn]").click(function(){
    			edaPk = $(this).val();
    			
    			window.open("<c:url value='/delivery/config/early_deliv/impos.do?edaPk="+edaPk+"'/>", "배송불가지역" , "width=900, height=800, top=100, left=300, scrollbars=no");
    			
    		});
    		
    		$("#insertEarlyAreaZipCodeForm").submit(function(){
    			const edaZipCode = $("#edaZipCode").val();
    			
    			if(edaZipCode == ''){
    				alert("우편번호를 입력해주세요");
    				$("#edaZipCode").focus();
    				
    				event.preventDefault();
    				return false;
    			}
    			
    		});
    		
    		regexFunction('keyup','#edaZipCode');
    		
    		$("#edaZipCode").keyup(function(){
    			if($(this).val().length > 5){
    				
    				alert("우편번호는 5자리까지만 입력할 수 있습니다");
    				const edaZipCode = $(this).val();
    				
    				$(this).val(edaZipCode.substring(0,5));
    				
    				
    			}
    		});
    		
    		$("#zipcodeDelBtn").click(function(){
    			const edaPk = $("input[name=edaPk]:checked").val();
    			
    			if(confirm("해당 우편번호를 정말 삭제하시겠습니까?")){
    				location.href='/delivery/config/early_deliv_del.do?edaPk='+edaPk;
	    			
    			}
    			
    			
    		});
    		
    		/* $("#zipcodeAddBtn").click(function(){
    			
    			if($(this).hasClass("open")){
    				$("#zipCodeAdd").hide();
    				$(this).removeClass("open");
    				$(this).text("일괄 입력");
    			}else{
    				$("#zipCodeAdd").show();
    				$(this).addClass("open");
    				$(this).text("일괄 입력 닫기");
    				
    			}
    			
    		}); */
    		
    	});
    	
    	function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#delivSearchForm").submit();
		}
		
    </script>
    <style type="text/css">
    	.selectClass{
    		background-color: #FFA2A2;
    		color:white;
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
                            <h2 class="pageheader-title"> 배송 방법 설정 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 설정 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 배송방법설정 </a></li>
                                        
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 배송 구분 검색 </h5>
                                <div class="card-body">
                                    <form class="needs-validation" id="delivSearchForm" action="<c:url value='/delivery/config/early_deliv.do'/>" method="GET">
                                    	<input type="hidden" name="currentPage" value="${osVO.currentPage}">
                                        <div class="form-row">
                                            <div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12 mb-2">
                                                <label for="validationCustom03">배송구분</label>
                                                <select class="form-control" name="edtFk">
                                                
                                                	<c:if test="${empty edtList }">
														<option> 등록된 배송 방법이 없습니다</option>
													</c:if>
													
													<c:if test="${!empty edtList }">		
														<option value="0">전체</option>									
														<c:forEach var="edtlist" items="${edtList }">
															<option value="${edtlist.edtPk }"
																<c:if test="${osVO.edtFk == edtlist.edtPk}">
																	selected="selected"
																</c:if>
															>${edtlist.edtType }</option>
														</c:forEach>
													</c:if>
													
                                                </select>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12 mb-2">
                                                <label for="validationCustom04">배송지역</label>
                                                <input type="text" class="form-control" id="searchKeyword" name="searchKeyword" placeholder="우편번호만 검색, 미입력시 전체 검색"
                                                	<c:if test="${!empty osVO.searchKeyword and !empty osVO.searchAddKeyword}">
                                                		value="${osVO.searchKeyword }~${osVO.searchAddKeyword }"
                                                	</c:if>
                                                	<c:if test="${!empty osVO.searchKeyword and empty osVO.searchAddKeyword}">
                                                		value="${osVO.searchKeyword }"
                                                	</c:if>
                                                >
                                            </div>
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 ">
                                                <p class="text-right">
                                                    <button type="submit" class="btn btn-space btn-primary"> 검색 </button>
                                                </p>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="col-xl-8 col-lg-8 col-md-8 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 배송 구분 설정 </h5>
                                <div class="card-body">
                                	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mb-2">
										<button class="btn btn-danger btn-xs mb-2" type="button" id="zipcodeDelBtn"> 우편 번호 삭제 </button>
									</div>
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th scope="col"></th>
                                                <th scope="col">배송가능여부</th>
                                                <th scope="col">배송구분</th>
                                                <th scope="col">우편번호</th>
                                                <th scope="col">불가지역 등록 개수</th>
                                                <th scope="col"> 관리 </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:if test="${!empty searchList }">
                                            	<c:forEach var="delivTypeList" items="${searchList }">
                                            		<c:forEach var="searchlist" items="${delivTypeList.edtList }">
			                                            <tr>
			                                                <th scope="row">
											                    <label class="custom-control custom-radio">
					                                                <input type="radio" name="edaPk" value="${searchlist.edaPk }" class="custom-control-input"><span class="custom-control-label"></span>
					                                            </label>
			                                                </th>
			                                                
			                                                <td>
			                                                	<label class="custom-control custom-checkbox be-select-all">
											                    	<input class="custom-control-input chk_all" type="checkbox" name="edaPosFlag" 
											                    		<c:if test="${searchlist.edaPosFlag == true}">
											                    			checked="checked"
											                    		</c:if>
											                    		<c:if test="${searchlist.edaPosFlag == false}">
											                    			
											                    		</c:if>
											                    	value="${searchlist.edaPosFlag }"><span class="custom-control-label"></span>
											                    </label>
											                </td>
											                <td>
			                                                	${delivTypeList.edtType }
			                                                </td>
			                                                <td>
			                                                	${searchlist.edaZipCode }
			                                                </td>
			                                                <td>
			                                                	${searchlist.delivCount }건
			                                                </td>
			                                                <td>
			                                                	<button class="fas fa-cogs" name="delivImposBtn" value="${searchlist.edaPk }"></button>
			                                                </td>
			                                            </tr>
                                            		</c:forEach>
                                            	</c:forEach>
                                        	
                                        	</c:if>

                                        </tbody>
                                    </table>
                                </div>
                                <nav aria-label="Page navigation example" style="text-align: center;">
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
                            </div>
                        </div>
                        <form id="insertEarlyAreaZipCodeForm" class="col-xl-4 col-lg-4 col-md-4 col-sm-12 col-12" action="<c:url value='/delivery/config/early_deliv.do'/>" method="POST">
                            <div class="card">
                            	<h4 class="card-header"> 배송 구분 추가 </h4>
                            		<div class="card-body border-top">
										<h3 class="font-16"> 배송 방법 선택  </h3>
										<select class="form-control" name="edtFk">
											<c:if test="${empty edtList }">
												<option> 등록된 배송 방법이 없습니다</option>
											</c:if>
											<c:if test="${!empty edtList }">											
												<c:forEach var="edtlist" items="${edtList }">
													<option value="${edtlist.edtPk }">${edtlist.edtType }</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
									
									<div class="card-body">
										<h3 class="font-16"> 우편번호 </h3>
										<input type="text" class="form-control mb-2" id="edaZipCode" name="edaZipCode" placeholder="우편번호 입력" value="">
										<input type="text" class="form-control mb-2" id="edaZipCodeAdd" name="edaZipCodeAdd" placeholder="~ 우편번호" value="" style="display: none;">
										<button class="btn btn-primary btn-block" id="zipcodeAddBtn"> 추가하기 </button>										
									</div>
									<div class="card-body border-top">
										<h3 class="font-16"> 배송 여부 </h3>
										<div class="custom-control custom-checkbox">
											<input type="radio" value="1" class="custom-control-input" name="edaPosFlag" id="delivTrue" checked="checked">
											<label class="custom-control-label" for="delivTrue"> 배송가능 </label>
										</div>
										<div class="custom-control custom-checkbox">
											<input type="radio" value="0" class="custom-control-input" name="edaPosFlag" id="delivFalse">
											<label class="custom-control-label" for="delivFalse"> 배송불가 </label>
										</div>
									</div>
									<div class="card-body border-top">
										<button class="btn btn-secondary btn-lg btn-block" type="submit"> 추가 </button>
									</div>
								</div>
                        </form>
                </div>
            </div>
        <!-- /page content -->
        <iframe id="excelDownloadIframe" width="0" height="0">
		</iframe>       
		
		<script type="text/javascript">
		 
		</script>
        <%@ include file="../../inc/bottom.jsp" %>