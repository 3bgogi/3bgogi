<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
			$(document).on("click",".addOptions",function(){
				
				var productPk = $("#productPk").val();
				
				location.href="<c:url value='/options/insert/option.do?productPk="+productPk+"'/>";
				
			});
			
			$("button[name=readOptionBtn]").click(function(){
				location.href="<c:url value='/options/read/option.do?optionPk="+$(this).val()+"'/>";
			});
			
			$("button[name=deleteOptionBtn]").click(function(){
				var productPk = $("#productPk").val();
				if(confirm("해당 옵션을 정말 삭제하시겠습니까?")){
					location.href="<c:url value='/options/delete/option.do?optionPk="+$(this).val()+"&productFk="+productPk+"'/>";
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
                            <h2 class="pageheader-title"> 상품 확인 </h2>
                            <p class="pageheader-text">Proin placerat ante duiullam scelerisque a velit ac porta, fusce sit amet vestibulum mi. Morbi lobortis pulvinar quam.</p>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">데이터 관리</a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">데이터 목록</a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">상품 목록</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">상품</li>
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
                                <h5 class="card-header"> 상품 상세 확인</h5>
                                <div class="card-body">
                                    <form id="insertProductForm" method="POST">
                                    	<input type="hidden" value="${productsVo.productPk }" id="productPk"/>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 명</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="productName" placeholder="상품 명을 입력해주세요" value="${productsVo.productName }"class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 분류 코드 선택</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <select class="form-control" id="cfFk" name="cfFk">
                                                	<c:forEach var="cflist" items="${cfList }">
		                                                <option
		                                                <c:if test='${cflist.cfPk eq productsVo.cfFk }'>selected="selected"</c:if>
		                                                value="${cflist.cfPk }"> ${cflist.cfCode } - ${cflist.cfCodeType } </option>                                                	                                                		
                                                	</c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 사용 여부 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="productFlag" 
	                                                <c:if test="${productsVo.productFlag eq true }">
	                                                	checked="checked"
	                                                </c:if>
	                                                value="1" class="custom-control-input"><span class="custom-control-label">사용</span>
	                                            </label>
	                                            
	                                            <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="productFlag"
	                                                <c:if test="${productsVo.productFlag eq false }">
	                                                	checked="checked"
	                                                </c:if>
	                                                 value="0" class="custom-control-input"><span class="custom-control-label">사용하지 않음</span>
	                                            </label>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 메모 1</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="productMemo1" value="${productsVo.productMemo1 }" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 메모 2</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="productMemo2" value="${productsVo.productMemo2 }" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 메모 3</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="productMemo3" value="${productsVo.productMemo3 }" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 메모 4</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="productMemo4" value="${productsVo.productMemo4 }" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 메모 5</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="productMemo5" value="${productsVo.productMemo5 }" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품 메모 6</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="productMemo6" value="${productsVo.productMemo6 }" class="form-control">
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
                                                <textarea name="productRemark" style="resize: none;" rows="8" class="form-control">${productsVo.productRemark }</textarea>
                                            </div>
                                        </div>
                                        <c:if test="${!empty productsVo.optionVOList }">
                                        	<c:forEach var="optionlist" items="${productsVo.optionVOList }">
	                                        	<div class="form-group row">
		                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 옵션 </label>
		                                            <div class="col-12 col-sm-8 col-lg-6 row">
			                                            <div class="col-md-8 mb-3">
			                                            	<input type="text" value="${optionlist.optionName } ( 원가 : <fmt:formatNumber value="${optionlist.optionCost }" pattern="#,###"/> 원)" readonly="readonly" class="form-control">
			                                            </div>
			                                             <div class="col-md-4 mb-3">
			                                             	<input type="hidden" value="${optionlist.optionPk }" name="optionPk">
				                                    		<button type="button" name="readOptionBtn" class="btn btn-space btn-success" value="${optionlist.optionPk }"> 옵션확인</button>
				                                    		<button type="button" name="deleteOptionBtn" class="btn btn-space btn-danger" value="${optionlist.optionPk }"> 옵션삭제</button>        
			                                             </div>
			                                        </div>
			                                        
		                                        </div>
	                                        </c:forEach>
                                        </c:if>
                                        <div class="form-group row text-right">
                                            <div class="col col-sm-11 col-lg-9 offset-sm-1 offset-lg-0">
                                                <button type="button" class="btn btn-space btn-primary"> 수정 하기 </button>
                                                <button type="button" class="btn btn-space btn-secondary btn-warning addOptions"> 옵션 추가 하기 </button>
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