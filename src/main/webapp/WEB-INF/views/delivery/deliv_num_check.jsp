<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="kr">
<head>
<!-- Required meta tags -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title> 송장 체크 </title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/multi-select/css/multi-select.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap-select/css/bootstrap-select.css">

<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>

<script type="text/javascript">
	$(function(){
		
		$("#orDeliveryInvoiceNumber").focus();
		
		var submitFlag = 0;
		
		$("#sendingReqBtn").click(function(){
			
			$("#sendingForm").attr("action", "/delivery/config/deliv_num_check.do");
			submitFlag = 1;
			
			$("#sendingForm").submit();
			
		});
		
		$("#sendingForm").submit(function(){
			
			orDeliveryInvoiceNumber = $("#orDeliveryInvoiceNumber").val();
			
			if(submitFlag == 1){
				
			}else{
				if(orDeliveryInvoiceNumber == ''){
					$("#orDeliveryInvoiceNumber").focus();
					
				}else{
					$("#"+orDeliveryInvoiceNumber).remove();
					$("#orDeliveryInvoiceNumber").val("");
					
					$("#orDeliveryInvoiceNumber").focus();
					
				}
				
				event.preventDefault();
				return false;
				
			}
		});
	});
</script>
</head>
<body>
<!-- 체크 -->
	<div class="container-fluid  dashboard-content" style="padding: 0;">
		<div class="row">
			<div class="col-xl-4 col-lg-4 col-md-4 col-sm-12 col-12" style="padding: 0;">
			</div>
			<div class="col-xl-4 col-lg-4 col-md-4 col-sm-12 col-12" style="padding: 0;">
				<div class="card" style="margin-bottom: 10px;">
					<form class="card-body" id="sendingForm">
						<table class="table table-bordered">
							<tbody>
								<tr>
									<td width="50%">
										<div class="input-group">
											<input type="text" class="form-control" id="orDeliveryInvoiceNumber" autocomplete="off">
											<div class="input-group-append">
												<button class="btn btn-primary btn-xs" type="submit">체크</button>
											</div>
										</div>
									</td>
									<td width="50%">
										<div class="input-group">
											<c:set var="invoiceCountNum" value="1"/>
											<select class="selectpicker" multiple data-actions-box="true" id="createInvoiceNumList" name="createInvoiceNumList">
												
									            	<c:if test="${empty invoiceNum }">
									                	<option disabled>송장 차수 </option>
									                </c:if>
									                <c:if test="${!empty invoiceNum }">
									                	<option disabled>송장 차수 </option>
									                	<c:forEach var="invoiceNumList" items="${invoiceNum }">
															<option 
																value="${invoiceNumList.orInvoiceNumDate }"
																<c:if test="${!empty osVO.createInvoiceNumList }">
																	<c:forEach var="invoiceNums" items="${osVO.createInvoiceNumList }">				
																		<c:if test="${invoiceNumList.orInvoiceNumDate  == invoiceNums }">
																			selected='selected'
																		</c:if>
																	</c:forEach>
																</c:if>
															>송장 ${invoiceCountNum} 차 ${invoiceNumList.orInvoiceNumDate }</option>
															<c:set  var="invoiceCountNum" value="${invoiceCountNum + 1 }"/>
														</c:forEach>
									                </c:if>
								                </select>
											
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<button class="btn btn-secondary btn-xs btn-block" type="button" id="sendingReqBtn"> 검색하기 </button>
									</td>
								</tr>
							</tbody>
						</table>
					
					</form>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xl-4 col-lg-4 col-md-4 col-sm-12 col-12" style="padding: 0;">
			
			</div>
			<div class="col-xl-4 col-lg-4 col-md-4 col-sm-12 col-12" style="padding: 0;">
				<div class="card">
					<div class="card-body">
						<table class="table table-hover">
							<thead>
								<tr style="font-size: 10px;">
									<th width="100%">송장번호</th>
								</tr>
							</thead>
							<tbody id="sendingProductList">
								<c:if test="${empty orList }">								
									<tr>
										<td>
											등록된 송장이 없습니다
										</td>
									</tr>
								</c:if>
								<c:if test="${!empty orList }">
									<c:forEach var="orlist" items="${orList }">									
										<tr id="${orlist.orDeliveryInvoiceNumber }">
											<td>${orlist.orDeliveryInvoiceNumber }</td>
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
</body> 
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-select/js/bootstrap-select.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/multi-select/js/jquery.multi-select.js"></script>
</html>
