<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
			
    		$('#inputDate').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d'
    		
    			
    		});
    		
    		$("button[name=fileDownload]").click(function(){
				var piFilePath = $(this).data("file-path");
				var piFileUniqName = $(this).data("file-uniq-name");
				
				var totalPath = piFilePath+piFileUniqName;
				var source = totalPath.split("resources");
				
				
				$("#piImg").attr("src","/resources/"+source[1] ).load();
				
    		});
    		
    		$("#piCost, #piTax").focusout(function(){
    			piCost = $("#piCost").val();
    			piTax = $("#piTax").val();
    			
    			$("#piTotalCost").val( (Number(piCost) + Number(piTax)) );
    		});
    		
    		/* $("button[name=fileDownload]").click(function(){
				var piFilePath = $(this).data("file-path");
				var piFileUniqName = $(this).data("file-uniq-name");
				
    				if(confirm("파일을 다운로드 하시겠습니까?")){
    	    			
    	    		var piFilePathInput = document.createElement("input");
    	    		piFilePathInput.name="piFilePath";
    	    		piFilePathInput.type="hidden";
    	    		piFilePathInput.value=piFilePath;
    	    		
    	    		var piFileUniqNameInput = document.createElement("input");
    	    		piFileUniqNameInput.name="piFileUniqName";
    	    		piFileUniqNameInput.type="hidden";
    	    		piFileUniqNameInput.value=piFileUniqName;
    	    		
    	    			
    	    		var downloadForm =  document.createElement("form")
    	    		downloadForm.action="/tax/product_info/file_down.do";
    	    		downloadForm.method="GET";
    	    			
    	    		downloadForm.append(piFilePathInput);
    	    		downloadForm.append(piFileUniqNameInput);
    	    			
    	    		$("#fileDownloadIframe").append(downloadForm);
    	    		
    	    		downloadForm.submit();
    	    		
    	    		$("#fileDownloadIframe").html("");
    	    		

    			}
    		}); */
    		
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
                            <h2 class="pageheader-title"> 거래내역서 상세보기 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 세금계산서</a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 거래내역서 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 상세보기 </li>
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
                                <h5 class="card-header"> 거래내역서 상세사항 </h5>
                                <div class="card-body">
                                    <form id="updateProductInfoForm" method="POST" action="<c:url value='/tax/product_info/update.do'/>" enctype="multipart/form-data">
                                    	<input type="hidden" name="piPk" value="${piVO.piPk }">
                                    	
                                    	<div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 발급일자 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" id="piInputDate" name="piInputDate" class="form-control" value="${piVO.piInputDate }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 상품명 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="piName" class="form-control" value="${piVO.piName }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 수량 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="piQty" class="form-control" value="${piVO.piQty }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 단위 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="piMeasure" class="form-control" value="${piVO.piMeasure }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 공급가 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" id="piCost" name="piCost" class="form-control" value="${piVO.piCost }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 세액 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" id="piTax" name="piTax" class="form-control" value="${piVO.piTax }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 합계 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" id="piTotalCost" name="piTotalCost" class="form-control" value="${piVO.piTotalCost }">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 미수금 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="piAccountReceivable" class="form-control" value="${piVO.piAccountReceivable }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 비고1</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="piRemark1" class="form-control" value="${piVO.piRemark1 }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 비고2</label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="piRemark2" class="form-control" value="${piVO.piRemark2 }">
                                            </div>
                                        </div>
                                        <c:if test="${!empty piVO.piFilePath }">
	                                        <div class="form-group row">
	                                        	<label class="col-12 col-sm-3 col-form-label text-sm-right"> 파일 </label>
	                                            <div class="col-12 col-sm-8 col-lg-6" style="text-align: center;">       
	                                            	<input type="hidden" name="piFilePath" value="${piVO.piFilePath }">         
	                                            	<input type="hidden" name="piFileExe" value="${piVO.piFileExe }">     
	                                            	<input type="hidden" name="piFileOriName" value="${piVO.piFileOriName }">     
	                                            	<input type="hidden" name="piFileUniqName" value="${piVO.piFileUniqName }">                         	
			                                        <button name="fileDownload" class="btn btn-primary btn-xs"
			                                        	data-toggle="modal" data-target="#piImgFile"  
			                                        	data-file-path="${piVO.piFilePath }"  type="button"
			                                        	data-file-uniq-name="${piVO.piFileUniqName }">
					                                       	명세서 확인
					                                </button>
	                                            </div>
	                                        </div>
	                                        
	                                        <div class="form-group row">
	                                        	<label class="col-12 col-sm-3 col-form-label text-sm-right"> 명세서 수정 </label>
	                                            <div class="col-12 col-sm-8 col-lg-6">       
					                                <input type="file" id="piFile" name="piFile" class="form-control">
	                                            </div>
	                                        </div>
                                        </c:if>
                                        <c:if test="${empty piVO.piFilePath }">
	                                        <div class="form-group row">
	                                        	<label class="col-12 col-sm-3 col-form-label text-sm-right"> 명세서 입력 </label>
	                                            <div class="col-12 col-sm-8 col-lg-6">       
					                                <input type="file" id="piFile" name="piFile" class="form-control">
	                                            </div>
	                                        </div>
                                        </c:if>
                                        <div class="form-group row text-right">
                                            <div class="col col-sm-10 col-lg-9 offset-sm-1 offset-lg-0">
                                                <button id="resCompanyInsertBtn" type="submit" class="btn btn-space btn-primary"> 수정하기 </button>
                                                <a class="btn btn-space btn-danger" href="<c:url value='/tax/product_info/list.do'/>">목록으로</a>
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
            <iframe id="fileDownloadIframe" width="0" height="0">
			</iframe> 
			<div class="modal fade" id="piImgFile" tabindex="-1" role="dialog" aria-labelledby="#piImgFile" aria-hidden="true">
			<div class="modal-dialog" role="document" style="max-width: 900px;">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="stockModal"> 이미지 파일 </h5>
						<a href="#" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</a>
					</div>
					<div class="modal-body" >
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="piImgDiv">
							<img id="piImg">
                        </div>
					</div>
				</div>
			</div>
		</div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>