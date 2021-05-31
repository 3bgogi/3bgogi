<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
			$("#productSubmit").submit(function(){
				var productName = $("input[name=productName]").val();
				
				if(productName=="" || productName.length < 3){
					alert("상품 명이 너무 짧거나 입력되지 않았습니다.");
					
					$("#productName").focus();
					
					return false;
					
				}
				
			});
    	});
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
                            <h2 class="pageheader-title"> 상품 등록 </h2>
                            <p class="pageheader-text">Proin placerat ante duiullam scelerisque a velit ac porta, fusce sit amet vestibulum mi. Morbi lobortis pulvinar quam.</p>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">데이터 관리</a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">데이터 입력</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">상품 등록</li>
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
                        <!-- ============================================================== -->
                        <!-- valifation types -->
                        <!-- ============================================================== -->
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 상품 상세입력</h5>
                                <div class="card-body">
                                    <form id="insertProductForm" method="POST" action="<c:url value='/products/insert/product.do'/>">
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 명</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="productName" placeholder="상품 명을 입력해주세요" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 분류 코드 선택</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <select class="form-control" id="cfFk" name="cfFk">
                                                	<c:forEach var="cflist" items="${cfList }">
	                                                    <option value="${cflist.cfPk }"> ${cflist.cfCode } - ${cflist.cfCodeType } </option>                                                	
                                                	</c:forEach>
                                                	
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 사용 여부 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="productFlag" checked="checked" value="1" class="custom-control-input"><span class="custom-control-label">사용</span>
	                                            </label>
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="productFlag" value="0" class="custom-control-input"><span class="custom-control-label">사용하지 않음</span>
	                                            </label>
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 메모 1</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="productMemo1" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 메모 2</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="productMemo2" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 메모 3</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="productMemo3" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 메모 4</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="productMemo4" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 메모 5</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="productMemo5" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 메모 6</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="productMemo6" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                        	<div class="col-xl-3 col-lg-6 col-md-6 col-sm-12 col-12"></div>
                                        	<div class="col-xl-3 col-lg-6 col-md-6 col-sm-12 col-12">
				                                <!-- .card -->
				                                <div class="card card-figure">
				                                    <!-- .card-figure -->
				                                    <figure class="figure">
				                                        <!-- .figure-img -->
				                                        <div class="figure-img">
				                                            <img class="img-fluid" src="${pageContext.request.contextPath}/resources/images/card-img.jpg" alt="Card image cap">
				                                            <div class="figure-action">
				                                                <a href="#" class="btn btn-block btn-sm btn-primary"> 등록되지 않음</a>
				                                            </div>
				                                        </div>
				                                        <!-- /.figure-img -->
				                                        <!-- .figure-caption -->
				                                        <figcaption class="figure-caption">
				                                            <p class="text-muted mb-0"> 상품 이미지 1 </p>
				                                        </figcaption>
				                                        <!-- /.figure-caption -->
				                                    </figure>
				                                    <!-- /.card-figure -->
				                                </div>
				                                <!-- /.card -->
				                            </div>
				                            <div class="col-xl-3 col-lg-6 col-md-6 col-sm-12 col-12">
				                                <!-- .card -->
				                                <div class="card card-figure">
				                                    <!-- .card-figure -->
				                                    <figure class="figure">
				                                        <!-- .figure-img -->
				                                        <div class="figure-img">
				                                            <img class="img-fluid" src="${pageContext.request.contextPath}/resources/images/card-img.jpg" alt="Card image cap">
				                                            <div class="figure-action">
				                                                <a href="#" class="btn btn-block btn-sm btn-primary"> 등록되지 않음</a>
				                                            </div>
				                                        </div>
				                                        <!-- /.figure-img -->
				                                        <!-- .figure-caption -->
				                                        <figcaption class="figure-caption">
				                                            <p class="text-muted mb-0"> 상품 이미지 2</p>
				                                        </figcaption>
				                                        <!-- /.figure-caption -->
				                                    </figure>
				                                    <!-- /.card-figure -->
				                                </div>
				                                <!-- /.card -->
				                            </div>
				                        </div>   
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 비고 사항 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <textarea name="productRemark" style="resize: none;" rows="8" class="form-control"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group row text-right">
                                            <div class="col col-sm-10 col-lg-9 offset-sm-1 offset-lg-0">
                                                <button id="productSubmit" type="submit" class="btn btn-space btn-primary"> 입력 하기 </button>
                                                <a class="btn btn-space btn-secondary" href="<c:url value='/products/list/product_list.do'/>"> 상품 목록 </a>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- ============================================================== -->
                        <!-- end valifation types -->
                        <!-- ============================================================== -->
                    </div>
            </div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>