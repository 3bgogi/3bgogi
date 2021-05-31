<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../../inc/top.jsp" %>
    <%@ include file="../../../inc/top_nav.jsp" %>
    <script type="text/javascript">

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
                                <h2 class="pageheader-title"> 판매처 별 엑셀 파일 열값 설정하기 </h2>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 설정 </a></li>
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 판매처 설정 </a></li>
                                            <li class="breadcrumb-item active" aria-current="page"> 엑셀 설정 </li>
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
                    		<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="card">
                                    <div class="card-header tab-regular">
                                        <ul class="nav nav-tabs card-header-tabs" id="myTab" role="tablist">
                                            <li class="nav-item">
                                                <a class="nav-link active" href="<c:url value='/config/store/excel_config.do?ssPk=${ssVO.ssPk }'/>"> 판매처 주문서 엑셀 설정</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" href="<c:url value='/config/store/sending_form.do?ssPk=${ssVO.ssPk }'/>"> 판매처 발송 엑셀 설정</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" href="<c:url value='/config/store/info.do?ssPk=${ssVO.ssPk }'/>"> 판매처 정보 변경</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" href="<c:url value='/config/store/merge.do?ssPk=${ssVO.ssPk }'/>"> 판매처 묶음 정리 변경</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="card-body">
                                        <div class="tab-content" id="myTabContent">
                                            <div class="tab-pane fade show active">
                                            	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						                            <div class="row">
						                                <div class="offset-md-3 col-md-6">
						                                    <div class="card">
						                                        <div class="card-header">
						                                        </div>
						                                        <div class="card-body" >
						                                            <form class="needs-validation" action="<c:url value='/config/store/update/excel_config.do'/>" method="POST">
						                                            	<input type="hidden" name="sedsPk" value="${sedsVO.sedsPk }">
						                                            	<input type="hidden" name="ssFk" value="${sedsVO.ssFk }">
						                                            	<div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 엑셀 주문 데이터 시작 행 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsStartRow" name="sedsStartRow">
								                                                	<option value="-1"> 행을 선택해주세요 </option>
								                                                	<c:forEach var="i" begin="0" end="10" step="1">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsStartRow == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${i + 1} 행</option>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 구매자명 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsBuyerName" name="sedsBuyerName">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsBuyerName == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 구매자 연락처1 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsBuyerContractNumber1" name="sedsBuyerContractNumber1">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsBuyerContractNumber1 == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 수취인명 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsReceiverName" name="sedsReceiverName">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsReceiverName == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 수령인연락처1 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsReceiverContractNumber1" name="sedsReceiverContractNumber1">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsReceiverContractNumber1 == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품명 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsProduct" name="sedsProduct">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList  }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsProduct == i}">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션명 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsProductOption" name="sedsProductOption">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsProductOption == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 수량 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsQuantity" name="sedsQuantity">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsQuantity == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 총 주문가격 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsTotalPrice" name="sedsTotalPrice">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsTotalPrice == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 주문번호 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsOrderNumber" name="sedsOrderNumber">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsOrderNumber == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품주문번호 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsProductOrderNumber" name="sedsProductOrderNumber">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsProductOrderNumber == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 우편번호 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsShippingAddressNumber" name="sedsShippingAddressNumber">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsShippingAddressNumber == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 배송지 주소 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsShippingAddress" name="sedsShippingAddress">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsShippingAddress == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 배송지 주소 상세사항 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsShippingAddressDetail" name="sedsShippingAddressDetail">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsShippingAddressDetail == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <hr>
								                                        <span class="badge badge-danger" style="text-align: center; display: block;"> 윗 사항은 필수로 입력해야 하는 사항입니다</span>
								                                        <hr>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 구매자 아이디 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsBuyerId" name="sedsBuyerId">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsBuyerId == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 구매자 연락처2 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsBuyerContractNumber2" name="sedsBuyerContractNumber2">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsBuyerContractNumber2 == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 수령인연락처2 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsReceiverContractNumber2" name="sedsReceiverContractNumber2">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsReceiverContractNumber2 == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품종류 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsProductType" name="sedsProductType">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsProductType == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 배송메세지 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsDeliveryMessage" name="sedsDeliveryMessage">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsDeliveryMessage == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 택배사 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsDeliveryCompany" name="sedsDeliveryCompany">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsDeliveryCompany == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 배송방법 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsDeliveryType" name="sedsDeliveryType">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsDeliveryType == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품고유번호 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsProductNumber" name="sedsProductNumber">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsProductNumber == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 결제위치 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsPaymentPositionType" name="sedsPaymentPositionType">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsPaymentPositionType == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 결제일 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsSettlementDay" name="sedsSettlementDay">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsSettlementDay == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품가격 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsProductPrice" name="sedsProductPrice">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsProductPrice == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션가격 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsProductOptionPrice" name="sedsProductOptionPrice">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsProductOptionPrice == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품할인가 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsDiscountPrice" name="sedsDiscountPrice">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsDiscountPrice == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 총 원가 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsTotalCost" name="sedsTotalCost">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsTotalCost == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 발주확인일 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsOrderCheckDay" name="sedsOrderCheckDay">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsOrderCheckDay == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 발송기한 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsSendingDeadline" name="sedsSendingDeadline">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsSendingDeadline == i  }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 발송일 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsSendingDay" name="sedsSendingDay">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsSendingDay == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 배송비형태 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsDeliveryChargeType" name="sedsDeliveryChargeType">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsDeliveryChargeType == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 배송묶음번호 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsDeliveryNumber" name="sedsDeliveryNumber">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsDeliveryNumber == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 배송비 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsDeliveryPrice" name="sedsDeliveryPrice">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsDeliveryPrice == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 배송할인액 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsDeliveryDiscountPrice" name="sedsDeliveryDiscountPrice">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsDeliveryDiscountPrice == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 결제수단 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsPaymentType" name="sedsPaymentType">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsPaymentType == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 결제수수료 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsPaymentCommision" name="sedsPaymentCommision">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsPaymentCommision == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 유입경로 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsInflowRoute" name="sedsInflowRoute">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsInflowRoute == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 특별 요청 사항 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsRequest" name="sedsRequest">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsRequest == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 성별 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsGender" name="sedsGender">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsGender == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 생년월일 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsBirthDate" name="sedsBirthDate">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsBirthDate == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 사용자정의1 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsUserColumn1" name="sedsUserColumn1">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsUserColumn1 == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 사용자정의2 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsUserColumn2" name="sedsUserColumn2">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsUserColumn2 == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 사용자정의3 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsUserColumn3" name="sedsUserColumn3">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsUserColumn3 == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
								                                        
								                                        <div class="form-group row">
								                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 사용자정의4 </label>
								                                            <div class="col-12 col-sm-8 col-lg-6">
								                                                <select class="form-control form-control-sm" id="sedsUserColumn4" name="sedsUserColumn4">
								                                                	<option value="-1"> 열을 선택해주세요 </option>
								                                                	<c:set var="i" value="0"/>	
								                                                	<c:forEach var="excellist" items="${excelColumnList }">                             	
									                                                    <option value="${i }"
									                                                    	<c:if test="${sedsVO.sedsUserColumn4 == i }">
									                                                    		selected="selected"
									                                                    	</c:if>
									                                                    >${excellist } 열</option>
									                                                    <c:set var="i" value="${i+1 }"/>
								                                                	</c:forEach>
								                                                </select>
								                                            </div>
								                                        </div>
						                                                <hr class="mb-4">
						                                                <button class="btn btn-primary btn-lg btn-block" type="submit"> 엑셀 데이터 변경하기 </button>
						                                            </form>
						                                        </div>
						                                    </div>
						                                </div>
						                            </div>
						                        </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </div>
                </div>
        <!-- /page content -->
        <%@ include file="../../../inc/bottom.jsp" %>