<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../../inc/top.jsp" %>
    <%@ include file="../../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		var costIoCounting = 0;
    		
    		$('#cilInputDate').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d',
    			"setDate" : new Date()
    			
    		});
    		
    		$("#ccList").change(function(){
    			var ccPk = $(this).val();
    			
    			if(ccPk == 0){
    				return false;
    			}
    			
    			$.ajax({
				    type       : 'GET',
				    data       : {
				    	"ccPk":ccPk
				    },
				    datatype:'json',
				    url        : '/stock/carcass/select_cost_detail.do',
				    success    : function(data){
				    	
				    	var cdListHTML = "";
				    	
				    	$.each(data, function(idx, items){
				    		cdListHTML+="<option value='"+this.cdPk+"'>"+this.cdName+"</option>";
				    	});
				    	
				    	$("#cdList").html(cdListHTML);
				    }
				    
				});
    			
    		});
    		
    		$("#addCostIoBtn").click(function(){
    			
    			var ccCode = $("#ccList").val();
    			var ccCodeType = $("#ccList option:selected").text();
    			
    			var costDetail = $("#cdList").val();
    			var costDetailName = $("#cdList option:selected").text();
    			
    			var ciLevel = $("#carcassLevel").val();
    			var ciAnimalProdTraceNum = $("#cilAnimalProdTraceNum").val();
    			
    			
    			if(ciLevel == ''){
    				alert("등급을 입력해주세요");
    				return false;
    			}
    			
    			var innerHTML = "";
    			
    			innerHTML+='<div class="form-group row">'
	            	+'<label class="col-12 col-sm-3 col-form-label text-sm-right"> 추가된 부분육  </label>'
	            	+'<div class="col-12 col-sm-8 col-lg-6">'  	
	                	+'<div class="row">'
	                       	 +'<div class="col-12 col-sm-6 col-lg-6">' 
		                       	+'<div class="input-group">'
									+'<input class="form-control" type="text"  name="ccValues" readonly="readonly" value="'+ccCodeType+' [ '+costDetailName+' ] '+ciLevel+' 등급">'
									+'<input class="form-control" type="hidden"  name="costIoList['+costIoCounting+'].cdFk" value="'+costDetail+'">'
									+'<input class="form-control" type="hidden"  name="costIoList['+costIoCounting+'].ciAnimalProdTraceNum" value="'+ciAnimalProdTraceNum+'">'
									+'<input class="form-control" type="hidden"  name="costIoList['+costIoCounting+'].ciCountryOfOrigin" value="국내산">'
									+'<input class="form-control" type="hidden"  name="costIoList['+costIoCounting+'].ciLevel" value="'+ciLevel+'">'
								+'</div>'	
	                        +'</div>'
	                        
	                        +'<div class="col-12 col-sm-6 col-lg-6">'
	                        	+'<div class="input-group">'
		                        +'<input class="form-control" type="number"  name="costIoList['+costIoCounting+'].ciWeight" placeHolder="무게를 입력해주세요">'
		                        +'<input class="form-control" type="text"  name="costIoList['+costIoCounting+'].ciMarblingLevel" placeHolder="마블링 등급">'
		                        +'<div class="input-group-append">'
		                            +'<button type="button" class="btn btn-danger deleteCarcassBtn"> 삭제 </button>'
		                        +'</div>'
		                        +'</div>'
	                        +'</div>'
	            	+'</div>'
	            +'</div>';
	            
	            $("#divLine").after(innerHTML);
	            
	            costIoCounting++;
    		});
    		
    		$("#insertCarcassForm").submit(function(){
    			var cilProduct = $("#cilProduct").val();
    			var cilAnimalProdTraceNum = $("#cilAnimalProdTraceNum").val();
    			var cilFile = $("#cilFile").val();
    			var cilTransFile = $("#cilTransFile").val();
    			var cilWeight = $("#cilWeight").val();
    			var carcassLevel = $("#carcassLevel").val();
    			
    			if(cilProduct == ''){
    				alert("품목명을 입력해주세요");
    				$("#cilProduct").focus();
    				return false;
    				
    			}else if(cilAnimalProdTraceNum == ''){
    				alert("이력번호를 입력해주세요");
    				$("#cilAnimalProdTraceNum").focus();
    				return false;
    				
    			}else if(cilFile == ''){
    				alert("물품사진을 입력해주세요");
    				$("#cilFile").focus();
    				return false;
    				
    			}else if(cilTransFile == ''){
    				alert("명세서를 입력해주세요");
    				$("#cilTransFile").focus();
    				return false;
    				
    			}else if(cilWeight == ''){
    				alert("도체 무게를 입력해주세요");
    				$("#cilWeight").focus();
    				return false;
    				
    			}else if(carcassLevel == ''){
    				alert("등급을 입력해주세요");
    				$("#carcassLevel").focus();
    				return false;
    				
    			}
    			
    			if($("input[name=ccValues]").length == 0){
    				alert("입력된 부분육이 존재하지 않습니다");
    				return false;
    				
    			}
    			
    		});
    		
    		$(document).on("click", ".deleteCarcassBtn", function(){
    			$(this).parent().parent().parent().parent().parent().parent().remove();
    			
    			
    		});
	
    	});

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
                            <h2 class="pageheader-title"> 도체 입력 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 재고 관리 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 도체 관리 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 도체 목록 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 도체 입력 </li>
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
                                <h5 class="card-header"> 도체 상세 입력</h5>
                                <div class="card-body">
                                    <form name="insertCarcassForm" id="insertCarcassForm" action="<c:url value='/stock/carcass/insert.do'/>" method="post" enctype="multipart/form-data">
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 품목명 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="도체명을 입력해주세요" class="form-control" id="cilProduct" name="cilProduct">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 이력번호 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="이력번호를 입력해주세요" id="cilAnimalProdTraceNum" class="form-control" name="cilAnimalProdTraceNum" value="">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 구매처 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="구매처를 입력해주세요" class="form-control" id="cilPurchaseStore" name="cilPurchaseStore" value="">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 구매가 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="number" placeholder="구매가를 입력해주세요" class="form-control" id="cilPurchasePrice" name="cilPurchasePrice" value="">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 도체 무게 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="number" placeholder="g 단위로 입력해주세요" class="form-control" id="cilWeight" name="cilWeight" value="">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 도체 번호 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="도체 번호를 입력해주세요" class="form-control" id="cilNum" name="cilNum" value="">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 등급 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="등급을 입력해주세요" class="form-control" id="carcassLevel" value="">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 두수 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="number" placeholder="두수를 입력해주세요" class="form-control" id="cilQty" name="cilQty" value="">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 입고일</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" class="form-control" id="cilInputDate" name="cilInputDate" value="">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 물품 사진 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="file" placeholder="사진 입력" class="form-control" id="cilFile" name="cilFile">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 명세서 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="file" placeholder="명세서 입력" class="form-control" id="cilTransFile" name="cilTransFile">
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="form-group row">
                                        	<label class="col-12 col-sm-12 col-form-label text-sm-center"> 부분 무게 입력 </label>
                                        </div>
                                        <div class="form-group row">
                                        	<label class="col-12 col-sm-3 col-form-label text-sm-right"> 부분육 추가  </label>
                                        	<div class="col-12 col-sm-8 col-lg-6">  	
	                                        	<div class="input-group">
			                                        <div class="input-group-prepend">
			                                        
				                                        <select class="form-control" id="ccList" name="ccList" style="height: calc(2.25rem + 2px);">
				                                        	<option value="0">원가 목록</option>
											                <c:if test="${!empty ccList }">
											                	<c:forEach var="cclist" items="${ccList }">
											                		<c:if test="${cclist.ccPk == 1 or cclist.ccPk == 2 }">											                		
												                		<option value="${cclist.ccPk }">${cclist.ccCodeType }</option>
											                		</c:if>
											                	</c:forEach>
											                </c:if>
											                <c:if test="${empty ccList }">
											                	<option value="0">입력된 원가 코드가 없습니다</option>
											                </c:if>
										                </select>
			                                        </div>
			                                        <select class="form-control" id="cdList" name="cdList">
			                                        	<option> 원가 코드를 선택해주세요 </option>
			                                        </select>
			                                        <div class="input-group-append">
	                                                    <button type="button" id="addCostIoBtn" class="btn btn-primary"> 추가하기 </button>
	                                                </div>
		                                        </div>
                                        	</div>
                                        </div>
                                        
                                        <hr id="divLine">
                                        <div class="form-group row text-right">
                                            <div class="col col-sm-10 col-lg-9 offset-sm-1 offset-lg-0">
                                                <button type="submit" class="btn btn-space btn-primary">저장하기</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

					</div>

			<!-- ============================================================== -->
                        <!-- end valifation types -->
                        <!-- ============================================================== -->
                    </div>
            </div>
        <!-- /page content -->
        <%@ include file="../../../inc/bottom.jsp" %>