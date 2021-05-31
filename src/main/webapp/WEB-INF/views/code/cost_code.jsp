<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %> 
    <script type="text/javascript">
    	$(function(){
    		
    		$("#updateCcCode").submit(function(){
    			let a = confirm("해당 정보로 정보를 변경하시겠습니까?");
    			if(a == true){
    				
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
                            <h2 class="pageheader-title"> 원재료 분류 코드 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 설정 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 분류 코드 </a></li>
                                        <li class="breadcrumb-item  active" aria-current="page"><a href="javascript:void(0);" class="breadcrumb-link"> 원재료 분류 코드 </a></li>
                                        
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
                            <form class="card" id="updateCcCode"  action="<c:url value=''/>" method="POST">
                                <h5 class="card-header"> 등록된 원재료 분류 코드 </h5>
                                <div class="card-body">
                                    <table class="table table-hover">
                                    	<thead>
											<tr>
												<th width="20%">원재료 분류 코드</th>
												<th width="35%">원재료 분류 값</th>
												<th width="15%"> 책임관리자 </th>
												<th width="30%">부분육 입고 여부</th>
											</tr>
                                    	</thead>
                                        <tbody>
                                        	<c:if test="${!empty ccList }">
                                        		<c:set var="counting" value="0"/>
                                        		<c:forEach var="cclist" items="${ccList }">                                        		
		                                            <tr>
		                                            	<td>
		                                            		<input type="hidden" name="ccList[${counting }].ccPk" value="${cclist.ccPk }">
		                                            		<input class="form-control" name="ccList[${counting }].ccCode"  type="text" value="${cclist.ccCode }" style="width: 100%;">
		                                            	</td>
		                                            	
		                                            	<td>
		                                            		<input class="form-control" name="ccList[${counting }].ccCodeType"  type="text" value="${cclist.ccCodeType }">
		                                            	</td>
		                                            	<td>
		                                            		<input class="form-control" name="ccList[${counting }].ccManager"  type="text" value="${cclist.ccManager }">
		                                            	</td>
		                                            	<td>
		                                            	
		                                            		<label class="custom-control custom-radio custom-control-inline">
					                                        	<input type="radio" name="ccList[${counting }].ccCarcassFlag" value="0"
					                                        		<c:if test="${cclist.ccCarcassFlag == 'false' }">
																		 checked
																	</c:if>
					                                        	class="custom-control-input"><span class="custom-control-label"> N </span>
					                                        </label>
					                                        
					                                        <label class="custom-control custom-radio custom-control-inline">
					                                        	<input type="radio" name="ccList[${counting }].ccCarcassFlag" value="1" 
					                                        		<c:if test="${cclist.ccCarcassFlag == 'true' }">
																		 checked
																	</c:if>
					                                        	class="custom-control-input"><span class="custom-control-label"> Y </span>
					                                        </label>
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
                            	<h5 class="card-header"> 원재료 분류 코드 추가하기 </h5>
					            <div class="card-body" id="">
					            	<form class="form-group" action="<c:url value='/code/insert_cost_code.do'/>" method="POST">
				                    	<div class="input-group">
				                    		<input type="text" class="form-control" name="ccCode" placeholder="원재료분류코드">
					                        <input type="text" class="form-control" name="ccCodeType" placeholder="원재료분류값">
					                        <input type="text" class="form-control" name="ccManager" placeholder="책임관리자">
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