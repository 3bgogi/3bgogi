<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %> 
    <script type="text/javascript">
    	$(function(){
    		
    		$("#updateCfCode").submit(function(){
    			if(confirm("해당 정보로 정보를 변경하시겠습니까?")){
    				
    			}else{
    				
    				event.preventDefault();
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
                            <h2 class="pageheader-title"> 상품 분류 코드 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 설정 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 분류 코드 </a></li>
                                        <li class="breadcrumb-item  active" aria-current="page"><a href="javascript:void(0);" class="breadcrumb-link"> 상품 분류 코드 </a></li>
                                        
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
						<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
                            <form class="card" id="updateCfCode"  action="<c:url value='/code/cf_code.do'/>" method="POST">
                                <h5 class="card-header"> 등록된 상품 분류 코드 </h5>
                                <div class="card-body">
                                    <table class="table table-hover">
                                    	<thead>
											<tr>
												<th width="30%">상품 분류 코드</th>
												<th width="70%">상품 분류 값</th>
											</tr>
                                    	</thead>
                                    	
                                        <tbody>
                                        	<c:if test="${!empty cfList }">
                                        		<c:set var="counting" value="0"/>
                                        		<c:forEach var="cflist" items="${cfList }">                                        		
		                                            <tr>
		                                            	<td>
		                                            		<input type="hidden" name="cfList[${counting }].cfPk" value="${cflist.cfPk }">
		                                            		<input class="form-control" name="cfList[${counting }].cfCode"  type="text" value="${cflist.cfCode }" style="width: 50px;">
		                                            	</td>
		                                            	<td>
		                                            		<input class="form-control" name="cfList[${counting }].cfCodeType"  type="text" value="${cflist.cfCodeType }">
		                                            	</td>
		                                            </tr>
		                                            <c:set var="counting" value="${counting + 1 }"/>
                                        		</c:forEach>            	
                                        	</c:if>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="card-body">
                                	<button id="seqBtn" class="btn btn-primary btn-block" type="submit"> 정보 일괄 변경 </button>
                                </div>
                            </form>
                        </div>
                        
                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
                            <div class="card">        	
                            	<h5 class="card-header"> 상품 분류 코드 추가하기 </h5>
					            <div class="card-body" id="">
					            	<form class="form-group" action="<c:url value='/code/insert_cf_code.do'/>" method="POST">
				                    	<div class="input-group">
				                    		<input type="text" class="form-control" name="cfCode" placeholder="상품분류코드">
					                        <input type="text" class="form-control" name="cfCodeType" placeholder="상품분류값">
					                        <div class="input-group-append">
					                        	<button type="submit" class="btn btn-primary"> 추가 </button>
					                        </div>
				                        </div>
					            	</form>
					            </div>
				        	</div>
                        </div>
					</div>
	            </div>
        <!-- /page content -->
        
        <script type="text/javascript">
        	$(function(){
        		
        	});
        </script>
        <%@ include file="../inc/bottom.jsp" %>