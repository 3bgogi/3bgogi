<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	
    </script>
    
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
                            <h2 class="pageheader-title"> 주문서 등록 결과 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문서 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문 입력 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문 등록 </a></li>
                                        
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
                        		<div class="card-header">
                        			<h4> 결과값 </h4>
                        		</div>
								<div class="card-body">
									<c:if test="${!empty insertResult }">
										<div class="alert alert-primary" role="alert">새롭게 입력된 주문서 :
											${insertResult} 개</div>
									</c:if>
									<c:if test="${!empty duplResult }">
										<div class="alert alert-danger" role="alert">이미 입력된 주문서 :
											${duplResult} 개</div>
									</c:if>
									<c:if test="${!empty mergedSuccessedResult }">
										<div class="alert alert-success" role="alert">묶음 정리 주문서 :
											${mergedSuccessedResult} 개</div>
									</c:if>
									<c:if test="${!empty updateResult }">
										<div class="alert alert-success" role="alert"> 프레시솔루션 업로드 : 
											${updateResult} 개</div>
											
									</c:if>
								</div>
							</div>
                        </div>
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<c:if test="${!empty checkingIroList }">
								<div class="card">
									<div class="card-header">
										<h4 class="mb-0">필터링 된 고객</h4>
									</div>
									<div class="card-body">
										<table class="table table-hover">
											<thead>
												<tr>
													<th scope="col">구매자명</th>
													<th scope="col">구매자번호</th>
													<th scope="col">확인사항</th>
													<th scope="col">확인 여부</th>
													<th scope="col">등록일</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="checkingIrolist" items="${checkingIroList }">
													<tr>
														<th scope="row">${checkingIrolist.iroName }</th>
														<td>${checkingIrolist.iroPhoneNumber}</td>
														<td>${checkingIrolist.iroDetail }</td>
														<td style="color: red;">N</td>
														<td><fmt:formatDate
																value="${checkingIrolist.iroRegdate }" pattern="yyyy-MM-dd" />
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</c:if>
						</div>
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<a class="btn btn-primary btn-xs" href="<c:url value='/orders/order_page.do'/>"> 확인 </a>
						</div>
					</div>
	            </div>
        <!-- /page content -->
        <%@ include file="../inc/bottom.jsp" %>