<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="kr">
<head>

<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>${osVO.searchKeyword}상세통계</title>
<!-- Bootstrap CSS -->
<link
	href="${pageContext.request.contextPath}/resources/libs/css/theme.css"
	rel="stylesheet" media="all">
<link
	href="${pageContext.request.contextPath}/resources/libs/mdi-font/css/material-design-iconic-font.min.css"
	rel="stylesheet" media="all">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<link
	href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/libs/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/charts/chartist-bundle/chartist.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/charts/morris-bundle/morris.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/fonts/material-design-iconic-font/css/materialdesignicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/charts/c3charts/c3.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/fonts/flag-icon-css/flag-icon.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/multi-select/css/multi-select.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/bootstrap-colorpicker/%40claviska/jquery-minicolors/jquery.minicolors.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/bootstrap-select/css/bootstrap-select.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/datepicker/jquery.datetimepicker.css" />
<script
	src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
<script

	src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/charts/c3charts/c3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/charts/c3charts/d3-5.4.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/charts/c3charts/C3chartjs.js"></script>



<script type="text/javascript">
	$(function() {

	});
</script>
<style>
html, body {
	
}

body {
	display: -ms-flexbox;
	display: flex;
	-ms-flex-align: center;
	align-items: center;
	padding-top: 40px;
	padding-bottom: 40px;
}
</style>
</head>
<body>

	<div class="container-fluid  dashboard-content">
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="section-block">
					<h3 class="section-title">${osVO.searchKeyword}상세통계, 기간 [
						${osVO.dateStart } ~ ${osVO.dateEnd } ]</h3>
				</div>
			</div>
			<div>
				<div class="influence-profile-content pills-regular">
					<ul class="nav nav-pills mb-3 nav-justified" id="pills-tab" role="tablist">
					
						<li class="nav-item">
							<a class="nav-link active" id="pills-campaign-tab" data-toggle="pill" 
							href="#pills-campaign" role="tab" aria-controls="pills-campaign" aria-selected="true"> 기초 통계 </a>
						</li>
						<li class="nav-item">
							<a class="nav-link" id="pills-msg-tab" data-toggle="pill"
						 	href="#pills-msg" role="tab" aria-controls="pills-msg" aria-selected="false"> 카테고리 별 판매 순위</a>
						</li>
					</ul>
					
					<div class="tab-content" id="pills-tabContent">
						<div class="tab-pane fade show active" id="pills-campaign" role="tabpanel" aria-labelledby="pills-campaign-tab">
							<div class="row">
								<div class="col-xl-8 col-lg-8 col-md-8 col-sm-12 col-12">
									<div class="row">									
										<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
											<div class="card">
												<div class="card-body">
													<h5 class="mb-1">총 매출</h5>
													<p>
														<fmt:formatNumber value="${laVO.totalPrice }"
															pattern="#,###" />
														원
													</p>
												</div>
											</div>
										</div>
										<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
											<div class="card">
												<div class="card-body">
													<h5 class="mb-1">총 송장</h5>
													<p>
														<fmt:formatNumber value="${laVO.totalOrder }"
															pattern="#,###" />
														장
													</p>
												</div>
											</div>
										</div>
										<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
											<div class="card">
												<div class="card-body">
													<h5 class="mb-1">1인 평균 구매액</h5>
													<p>
														<fmt:formatNumber
															value="${laVO.totalPrice / laVO.totalOrder }"
															pattern="#,###" />
														원
													</p>
												</div>
											</div>
										</div>
										<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
											<div class="card">
												<div class="card-body">
													<h5 class="mb-1">1인 최대 구매액</h5>
													<p>
														<fmt:formatNumber value="${laVO.maxPrice }" pattern="#,###" />
														원
													</p>
												</div>
											</div>
										</div>
										<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
											<div class="card">
												<div class="card-body">
													<h5 class="mb-1">1인 최저 구매액 (0원은 쿠폰)</h5>
													<p>
														<fmt:formatNumber value="${laVO.minPrice }" pattern="#,###" />
														원
													</p>
												</div>
											</div>
										</div>
										<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
											<div class="card">
												<div class="card-body">
													<h5 class="mb-1">1인 최대 구매 수량</h5>
													<p>
														<fmt:formatNumber value="${laVO.maxQty }" pattern="#,###" />
														개
													</p>
												</div>
											</div>
										</div>
										<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
											<div class="card">
												<div class="card-body">
													<h5 class="mb-1">1인 최저 구매 수량</h5>
													<p>
														<fmt:formatNumber value="${laVO.minQty }" pattern="#,###" />
														개
													</p>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-xl-4 col-lg-4 col-md-4 col-sm-12 col-12">
									<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
										<div class="card">
											<h5 class="card-header">유입량 순위</h5>
											<div class="card-body">
												<div class="table-responsive">
													<table class="table table-hover"
														style="table-layout: fixed; word-break: keep-all;">
														<thead class="bg-light">
															<tr class="border-0">
																<th class="border-0" width="80%">유입경로</th>
																<th class="border-0" width="20%">주문수</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="inflowlist" items="${inflowList }">
																<tr>
																	<td>${inflowlist.orInflowRoute }</td>
																	<td><fmt:formatNumber
																			value="${inflowlist.totalOrder }" pattern="#,###" />
																		건</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tab-pane fade" id="pills-msg" role="tabpanel" aria-labelledby="pills-msg-tab">
							<div class="row">
								<c:forEach var="tpList" items="${topProductList }">
									<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
										<c:set var="levels" value="1" />
										<div class="card">
											<div class="card-body" style="padding-bottom: 1px; padding-top: 10px;">
												<div class="row">
													<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
														<div class="media influencer-profile-data d-flex align-items-center p-2">
															<div class="media-body ">
																<div class="influencer-profile-data">
																	<h5 class="m-b-10">${tpList.cfCodeType }</h5>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											
											<div class="border-top card-footer p-0">
												<c:set var="level" value="1" />
												<c:forEach var="palist" begin="0" step="1" end="5" items="${tpList.paList }">
													<div class="campaign-metrics d-xl-inline-block" style="padding: 8px 20px;">
														<h5 class="mb-0">${levels }위 ${palist.products }</h5>
														<p class="mb-0">
															총 매출 <fmt:formatNumber value="${palist.totalPrice }" pattern="#,###" /> 원
														</p>
														<p class="mb-0"> 
															총 구매량 <fmt:formatNumber value="${palist.totalAmount }" pattern="#,###" /> 개
														</p>
													</div>
													<c:set var="i" value="${i + 1 }" />
													<c:set var="levels" value="${levels + 1 }" />
												</c:forEach>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		
	});
</script>
</html>
