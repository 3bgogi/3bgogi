<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		//어드민 사용 여부 체크
    		/* $("input[name=enabled]").change(function(){
    				var adminPk = $(this).next().val();
    				var abled = $(this).val();
    				
    				location.href="<c:url value='/admin/ableChange.do?adminPk="+adminPk+"&abled="+abled+"'/>";

    		});
    		 */
    		 
    		 $("input[name=adminId]").click(function(){
    			 
    			 $('.roleInfo').find('input[name=adminRoles]').each(function(){
    				 
    				 	$(this).next().css("color", "#3d405c");
    				 	$(this).next().next().val("0");
    				 	
						
					});	
    			 
    			 var adminId = $(this).val();
    			 
    				$.ajax({
    					url:'<c:url value="/admin/admin_auth.do"/>',
    					data:{
    						"adminId":adminId
    					},
    					success:function(data){
							$.each(data, function(idx, items){
								
								var role = this.role;
								var adminRolePk = this.adminRolePk;
								
								$('.roleInfo').find('input[name=adminRoles]').each(function(){
									
									if($(this).val() == role){
																				
										$(this).next().css("color", "red");
										$(this).next().next().val(adminRolePk);
										
									} 
									
								});								
								
							});
							
    					},
    					error:function(xhr, status, error){
    						alert("세선이 종료되었거나 권한이 없습니다. ");
    						location.href="<c:url value='/login.do'/>";
    					}
    					
    				});
    				
    		 });
    		 
    		 $(".roleInfo").click(function(){
    			var roleName = $(this).find("input[name=adminRoles]").val();
    			var adminRolePk = $(this).find("input[name=adminRolePk]").val();
    			var adminId = "";
    			
    			$('input:radio[name=adminId]').each(function(){
    				
	    			if($(this).is(":checked") ){	    				
	    				adminId = $(this).val();
	    			}
    			});
    			
    			location.href = "<c:url value='/admin/add_or_delete_Auth.do?roleName="+roleName+"&adminRolePk="+adminRolePk+"&adminId="+adminId+"'/>";
    			
    		 });
    	});
    </script>
    <style type="text/css">
    	.roleInfo > *{
    		cursor: pointer;
    	}
    </style>
        <!-- page content -->
        <!-- ============================================================== -->
        <!-- wrapper  -->
        <!-- ============================================================== -->
        <div class="dashboard-wrapper">
            <div class="dashboard-ecommerce">
                <div class="container-fluid dashboard-content ">
                    <!-- ============================================================== -->
                    <!-- pageheader  -->
                    <!-- ============================================================== -->
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="page-header">
                                <h2 class="pageheader-title"> 팀원 정보 및 권한 부여 </h2>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 보안 </a></li>
                                            <li class="breadcrumb-item active" aria-current="page"> 팀원 </li>
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
                        <div class="offset-xl-1 col-xl-10 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4 class="mb-0"> 팀원 목록 </h4>
                                        </div>
                                        
                                        <div class="card-body">
                                        	<input type="hidden" id="adminTargetName">
                                        	<c:forEach var="adminlist" items="${adminList }">                                        	
		                                        <div class="row">
			                                        <div class="col-md-2 mb-1">
			                                       		<label for="cc-expiration">이름</label>
			                                        	<input type="text" class="form-control" id="adminName" readonly="readonly" value="${adminlist.adminName }">
			                                        </div>
			                                        <div class="col-md-3 mb-1">
			                                        	<label for="cc-cvv">연락처</label>
			                                        	<input type="text" class="form-control" id="adminPhone" readonly="readonly" value="${adminlist.adminPhone }">
			                                        </div>
			                                        <div class="col-md-5 mb-1">
			                                        	<label for="cc-cvv">주소</label>
			                                        	<input type="text" class="form-control" id="adminAddress" readonly="readonly" value="${adminlist.adminAddress }">
			                                        </div>
			                                        <div class="col-md-2 mb-1">
			                                        	<div class="card-body">
				                                        	<label class="custom-control custom-radio custom-control-inline">
				                                                <input type="radio" name="adminId"  class="custom-control-input" value="${adminlist.adminId }"><span class="custom-control-label">권한</span>
				                                            </label>
			                                            </div>
			                                        </div>
		                                        </div>
                                        	</c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4 mb-4">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4 class="d-flex justify-content-between align-items-center mb-0">
                                            	<span class="text-muted"> 활성화된 권한 조회 </span>
                                            </h4>
                                        </div>
                                        <div class="card-body">
                                            <ul class="list-group mb-3">
                                                <li class="list-group-item d-flex justify-content-between">
                                                    <div class="roleInfo">
                                                        <input type="hidden" name="adminRoles" value="ROLE_USER">
                                                        <h6 class="my-0"> 일반 </h6>
                                                        <input type="hidden" name="adminRolePk" value="0">
                                                        <small class="text-muted"> 정보 제한 </small>
                                                    </div>
                                                    <span class="text-muted role_user">ROLE_USER</span>
                                                </li>
                                                <li class="list-group-item d-flex justify-content-between">
                                                    <div class="roleInfo">
                                                        <input type="hidden" name="adminRoles" value="ROLE_ADMIN">
                                                        <h6 class="my-0"> 관리자급 </h6>
                                                        <input type="hidden" name="adminRolePk" value="0">
                                                        <small class="text-muted"> 정보 관리 </small>
                                                    </div>
                                                    <span class="text-muted role_admin">ROLE_ADMIN</span>
                                                </li>
                                                <li class="list-group-item d-flex justify-content-between">
                                                    <div class="roleInfo">
                                                        <input type="hidden" name="adminRoles" value="ROLE_DEVELOPER">
                                                        <h6 class="my-0"> 개발자급 </h6>
                                                        <input type="hidden" name="adminRolePk" value="0">
                                                        <small class="text-muted"> 슈퍼 계정 </small>
                                                    </div>
                                                    <span class="text-muted role_developer">ROLE_DEVELOPER</span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>