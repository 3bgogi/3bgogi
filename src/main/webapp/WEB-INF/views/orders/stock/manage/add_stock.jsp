<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../../inc/top.jsp" %>
    <%@ include file="../../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		var inputPosiv = false;
    		
    		$('#pilExpDate').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d',
    			"setDate" : new Date()
    			
    		});
    		
    		$("#searchKeyword").focus();
    		
    		$("#pilExpDate").change(function(){
    			if($(this).val() != '' && $("#pilFile").val() != ''){
    				$("#addStockBtn").show();
    				
    				
    			}else{
    				$("#addStockBtn").hide();
    			}
    		});
    		
    		$("#pilFile").change(function(){
    			if($(this).val() != '' && $("#pilExpDate").val() != ''){
    				$("#addStockBtn").show();
    			}else{
    				$("#addStockBtn").hide();
    			}
    		});
    		
    		$("form[name=stockAddForms]").submit(function(){

    			var pilQty = $(this).find("#pilQty").val();
    			var optionFk = $(this).find("#optionFk").val();
    			var pilFile = $(this).find("#pilFile").val();
    			var pilExpDate = $(this).find("#pilExpDate").val();

    			if(pilQty == ''){
    				alert("재고를 제대로 입력해주세요");
    				return false;
    				
    			}else if(optionFk == ''){
    				alert("잘못된 상품입니다");
    				return false;
    				
    			}else if(pilExpDate == ''){
    				alert("유통기한이 입력되지 않았습니다");
    				return false;
    				
    			}else if(pilFile == ''){
    				alert("이미지 파일이 입력되지 않았습니다");
    				return false;
    				
    			}
    			
    			//$(this).append("files", $("#pilFile")[0].files[0]);

    			var form = new FormData(document.getElementById('stockAddForms'));
    			form.append("files", $("#pilFile")[0].files[0]);
    			
    			$.ajax({
					type       : 'POST',
					data       : form,
					processData: false, 
					contentType: false,
					url        : '<c:url value="/stock/add_option_stocks.do"/>',
					success    : function(data){		
						
						alert(data);
						location.reload();
						
					}
					
				});
    			
    			
    			event.preventDefault();
    			return false;
    		});
    		
    		$(document).on("submit", ".addOptionStocks", function(){
    			var pilQty = $(this).find("input[name=pilQty]").val();
    			var optionFk = $(this).find(".addStockBtn").data("option-fk");
    			
    			$("#optionFk").val(optionFk);
				$("#pilQty").val(pilQty);
				
    			$(this).find(".addStockBtn").click();
    			
    			event.preventDefault();
				return false;
				
    		});
    		
    		$(document).on("click", ".addStockBtn", function(){
    			var pilQty = $(this).parent().parent().find("input[name=pilQty]").val();
    			var optionFk = $(this).parent().parent().find(".addStockBtn").data("option-fk");
    			
    			$("#optionFk").val(optionFk);
				$("#pilQty").val(pilQty);
				
    			$(this).find(".addStockBtn").click();
    			
    			event.preventDefault();
				return false;
				
    		});
						
			$("#selectOptionStockForm").submit(function(){
				var searchKeyword = $("#searchKeyword").val();
				
				if(searchKeyword = ''){
					alert("상품명 혹은 바코드번호를 입력해주세요 ");
					
					event.preventDefault();
					return false;
				}
				
				var selectOptionStockForm = jQuery("#selectOptionStockForm").serialize();
				
				$.ajax({
					type       : 'POST',
					data       : selectOptionStockForm ,
					url        : '<c:url value="/stock/search_option_stocks.do"/>',
					success    : function(data){		
						
						var optionsListHTML = "";
						$.each(data, function(){
							
							optionsListHTML+='<tr>'
	                            +'<th scope="col">'+this.productName+'<br>['+this.optionName+']</th>'
	                            +'<th scope="col">'+comma(this.optionStock)+' 개</th>'
	                            +'<th scope="col" colspan="2">'
		                            +'<form class="form-group addOptionStocks">'
		                                +'<div class="input-group">'
					                     +'<input type="number" class="form-control" name="pilQty">'
					                     +'<input type="hidden" class="form-control" name="optionFk" value="'+this.optionPk+'">'
					                        +'<div class="input-group-append">'
					                        	+'<button type="button" class="btn btn-primary btn-xs addStockBtn" data-toggle="modal" data-target="#stockModal" data-option-fk="'+this.optionPk+'"> 추가 </button>'
					                        +'</div>'
				                        +'</div>'
		                            +'</form>'
	                            +'</th>'
	                        +'</tr>';
						});
						
						
						$("#productLists").html(optionsListHTML);
						event.preventDefault();
						return false;
					}
					
				});
				
				event.preventDefault();
				return false;

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
                                <h5 class="card-header"> 상품 재고 확인</h5>
                                <div class="card-body">
                                    <form id="selectOptionStockForm"  method="get">
                                        <div class="form-group">
                                            <div class="input-group">
						                        <input type="text" class="form-control" id="searchKeyword" name="searchKeyword" placeholder="상품명 혹은 바코드를 입력해주세요">
						                        <div class="input-group-append">
						                        	<button type="submit" class="btn btn-primary"> 검색 </button>
						                        </div>
					                        </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="card-body">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th scope="col" width="50%" style="text-align: center;">상품명 </th>
                                                <th scope="col" width="15%" style="text-align: center;">재고</th>
                                                <th scope="col" width="35%" style="text-align: center;">개수</th>
                                            </tr>
                                        </thead>
                                        <tbody id="productLists">
                                            <tr>
                                                <th scope="row" colspan="4" style="text-align: center;"> 상품을 검색해주세요 </th>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <!-- ============================================================== -->
                        <!-- end valifation types -->
                        <!-- ============================================================== -->
                    </div>
            </div>
        <!-- /page content -->
		<div class="modal fade" id="stockModal" tabindex="-1" role="dialog" aria-labelledby="#stockModal" aria-hidden="true">
			<div class="modal-dialog" role="document" style="max-width: 700px;">
				<form class="modal-content" id="stockAddForms" name="stockAddForms" enctype="multipart/form-data">
					<div class="modal-header">
						<h5 class="modal-title" id="stockModal"> 재고 추가 상세사항 </h5>
						<a href="#" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</a>
					</div>
					<div class="modal-body" >
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<input type="hidden" name="pilQty" id="pilQty">
							<input type="hidden" name="optionFk" id="optionFk">
                            <div class="tab-regular">
                                <ul class="nav nav-tabs " id="myTab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link active" id="addExpDate-tab" data-toggle="tab" href="#addExpDate" role="tab" aria-controls="addExpDate" aria-selected="true"> 유통기한 </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="addStockFile-tab" data-toggle="tab" href="#addStockFile" role="tab" aria-controls="addStockFile" aria-selected="false"> 명세서 </a>
                                    </li>
                                    <!-- <li class="nav-item">
                                        <a class="nav-link" id="productStateFile-tab" data-toggle="tab" href="#productStateFile" role="tab" aria-controls="productStateFile" aria-selected="false"> 입고사진 </a>
                                    </li> -->
                                </ul>
                                <div class="tab-content" id="stockDetailContent">
                                    <div class="tab-pane fade show active" id="addExpDate" role="tabpanel" aria-labelledby="addExpDate-tab">
                                        <div class="form-group">
							            	<label class="col-form-label"> 유통기한 입력 </label>
								            <div class="input-group mb-3">
									            <input type="text" class="form-control" name="pilExpDate" id="pilExpDate" placeholder="클릭하여 날짜를 선택해주세요">
								            </div>
							            </div>
                                    </div>
                                    <div class="tab-pane fade" id="addStockFile" role="tabpanel" aria-labelledby="addStockFile-tab">
                                        <div class="form-group">
							            	<label class="col-form-label"> 명세서 입력</label>
								            <div class="input-group mb-3">
									            <input type="file" class="form-control" name="pilFile" id="pilFile">
								            </div>
							            </div>
                                    </div>
                                    <div class="tab-pane fade" id="productStateFile" role="tabpanel" aria-labelledby="productStateFile-tab">
                                        <div class="form-group">
							            	<label class="col-form-label"> 입고사진 입력 </label>
								            <div class="input-group mb-3">
									            <input type="file" class="form-control" name="pilProductFile" id="pilProductFile">
								            </div>
							            </div>
                                    </div>
                                </div>
                            </div>
                        </div>
					</div>
					 <div class="modal-footer" style="display: none;" id="addStockBtn">
						<button type="submit" class="btn btn-primary"> 재고 추가 </button>
					</div>
				</form>
			</div>
		</div>
        <%@ include file="../../../inc/bottom.jsp" %>