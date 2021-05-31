<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %> 
    <script type="text/javascript">
    $(function(){
		$("#deleteTaxTableBtn").click(function(){
			if(confirm("현재까지 입력된 값을 삭제하시겠습니까?")){
				location.href="<c:url value='/tax/delete_tax_table.do'/>";
				
			}
			
		});
		
		$("#insertTaxTableForm").submit(function(){
			
			doubleSubmitCheck();
			
			if(doubleSubmitFlag == false){
				
				return false;
			}
			
			$("#insertTaxTableBtn").removeClass("btn btn-space btn-secondary btn-block");
			
    		$("#insertTaxTableBtn").text("");
    		
			$("#insertTaxTableBtn").addClass("dashboard-spinner spinner-xs");
			
		});
		
	});
    	
   		 var doubleSubmitFlag = false;

    	function doubleSubmitCheck(){
    	    if(doubleSubmitFlag){
    	        return doubleSubmitFlag;
    	        
    	    }else{
    	        doubleSubmitFlag = true;
    	        return false;
    	    }
    	}
    	
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
                            <h2 class="pageheader-title"> 면세과세 계산 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 면세과세 </a></li>
                                        <li class="breadcrumb-item  active" aria-current="page"><a href="javascript:void(0);" class="breadcrumb-link"> 계산하기 </a></li>
                                        
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 면세과세 엑셀 데이터 입력 </h5>
                                <div class="card-body">
                                    <form id="insertTaxTableForm" method="post" action="<c:url value='/tax/tax_table.do'/>" enctype="multipart/form-data">
                                        <div class="form-group row">
                                            <div class="col-xl-10 col-lg-10 col-md-10 col-sm-12 col-12 mb-3">
                                                <input id="smartstore" type="file" name="excelfile" class="form-control">
                                            </div>
                                             <div class="col-xl-2 col-lg-2 col-md-2 col-sm-12 col-12 mb-3">
                                                <button id="insertTaxTableBtn" type="submit" class="btn btn-space btn-secondary btn-block"> 입력하기 </button>
                                                
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 결과값 </h5>
                                <div class="card-body">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th scope="col">기준일</th>
                                                <th scope="col">데이터 개수</th>
                                                <th scope="col">면세</th>
                                                <th scope="col">신용카드매출전표</th>
                                                <th scope="col">현금 등</th>
                                                
                                                <th scope="col">과세</th>
                                                <th scope="col">신용카드매출전표</th>
                                                <th scope="col">현금 등</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:if test="${empty totalCount}">
                                        		<tr>
	                                                <th scope="row" colspan="7" style="text-align: center;"> 입력된 값이 없습니다 </th>
	                                            </tr>
                                        	</c:if>
                                        	<c:if test="${!empty totalCount}">
                                        		<c:set var="counting" value="${fn:length(taxList) }"/>
                                        		<c:forEach var="i" begin="0" end="${counting - 1 }" step="1">
                                        			<tr>
		                                                <th scope="row">${taxList[i].ttDate }</th>
		                                                <td>
		                                                	<fmt:formatNumber value="${totalCount[i].totalCount }" pattern="#,###"/> 건
		                                                </td>
		                                                <td>면세</td>
		                                                <td>
		                                                	<fmt:formatNumber value="${taxList[i].ttCreditPrice }" pattern="#,###"/> 원
		                                                </td>
		                                                <c:set var="totalPrice" value="${taxList[i].ttCashDeductionPrice + taxList[i].ttCashReceiptPrice + taxList[i].ttAnotherPrice }"/>
		                                                <td>
		                                                	<fmt:formatNumber value="${totalPrice }" pattern="#,###"/> 원
		                                                </td>
		                                                 <td>과세</td>
		                                                <td>
		                                                	<fmt:formatNumber value="${dutyList[i].ttCreditPrice }" pattern="#,###"/> 원
		                                                </td>
		                                                <c:set var="totalPrice" value="${dutyList[i].ttCashDeductionPrice + dutyList[i].ttCashReceiptPrice + dutyList[i].ttAnotherPrice }"/>
		                                                <td>
		                                                	<fmt:formatNumber value="${totalPrice }" pattern="#,###"/> 원
		                                                </td>
		                                            </tr>
                                        		</c:forEach>  
                                        		<tr>
	                                                <th scope="row" colspan="8" style="text-align: center;">
	                                                	<button class="btn btn-primary btn-block" id="deleteTaxTableBtn"> 입력건 일괄 삭제하기 </button>
	                                                </th>
	                                            </tr>                                      	
                                        	</c:if>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
					</div>
	            </div>
	            
        <!-- /page content -->
        <%@ include file="../inc/bottom.jsp" %>