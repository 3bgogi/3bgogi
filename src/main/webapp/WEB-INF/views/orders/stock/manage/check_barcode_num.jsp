<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../../inc/top.jsp" %>
    <%@ include file="../../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){

    		$("#searchKeyword").focus();

			$("#selectOptionStockForm").submit(function(){
				var searchKeyword = $("#searchKeyword").val();
				
				if(searchKeyword = ''){
					alert("바코드번호를 입력해주세요 ");
					
					event.preventDefault();
					return false;
				}
				
				var selectOptionStockForm = jQuery("#selectOptionStockForm").serialize();
				
				$.ajax({
					type       : 'POST',
					data       : selectOptionStockForm ,
					url        : '<c:url value="/stock/check_barcode_dupli.do"/>',
					success    : function(data){		
						
						var optionsListHTML = "";
						$.each(data, function(){
							
							optionsListHTML+='<tr>'
	                            +'<th scope="col" style="text-align:center;" >'+this.productName+'<br>['+this.optionName+']</th>'
	                            +'<th scope="col" style="text-align:center;">'+this.optionBarcodeNumber1+'</th>';
	                            
	                            if(this.optionBarcodeNumber2 != ''){
	                            	optionsListHTML+='<th scope="col" style="text-align:center;">'+this.optionBarcodeNumber2+'</th>';    	
	                            }else{
	                            	optionsListHTML+='<th scope="col" style="text-align:center;"> - </th>';
	                            }
	                            
	                       optionsListHTML+='</tr>';
	                       
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
                            <h2 class="pageheader-title"> 바코드 확인  </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 상품 및 원재료 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 상품 및 원재료 목록 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 바코드 확인 </li>
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
                                <h5 class="card-header"> 바코드 확인 </h5>
                                <div class="card-body">
                                    <form id="selectOptionStockForm"  method="get">
                                        <div class="form-group">
                                            <div class="input-group">
						                        <input type="text" class="form-control" id="searchKeyword" name="searchKeyword" placeholder="바코드를 입력해주세요">
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
                                                <th scope="col" width="15%" style="text-align: center;">바코드 번호 1</th>
                                                <th scope="col" width="35%" style="text-align: center;">바코드 번호 2</th>
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
        <%@ include file="../../../inc/bottom.jsp" %>