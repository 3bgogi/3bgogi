<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
	    $(function(){
			$(".irodataDelete").click(function(){
				var iroPk = $(this).val();
				
				if(confirm(" 해당 사항을 삭제처리 하시겠습니까? ")){
					
					location.href="<c:url value='/orders/irregular/delete.do?iroPk="+iroPk+"'/>";
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
                            <h2 class="pageheader-title"> 구매자 필터링 전체 목록 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 주문서 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 고객 필터링 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 전체 목록 </li>
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
                        <!-- justified tabs  -->
                        <!-- ============================================================== -->
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mb-5">
                            <div class="section-block">
                                <h5 class="section-title"> 전체 목록 </h5>
                                <p> 고객 필터링 데이터에서 확인, 미확인을 구분하지 않고 전부 가져옴 </p>
                            </div>
                            <div class="tab-regular">
                                <ul class="nav nav-tabs nav-fill" id="myTab7" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link" id="home-tab-justify" style="cursor: pointer;" href="<c:url value='/orders/irregular/list.do'/>"> 목록 </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link active" id="profile-tab-justify" style="cursor: pointer;" href="<c:url value='/orders/irregular/all_list.do'/>"> 완료 목록</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="contact-tab-justify" style="cursor: pointer;" href="<c:url value='/orders/irregular/add.do'/>"> 추가하기 </a>
                                    </li>
                                </ul>
                                <div class="tab-content" id="myTabContent7">
                                    <div class="tab-pane fade show active" id="home-justify" role="tabpanel" aria-labelledby="home-tab-justify">
                                        <div class="card">
			                                <div class="card-body">
			                                    <table class="table table-hover">
			                                        <thead>
			                                            <tr>
			                                                <th scope="col"> 구매자명 </th>
			                                                <th scope="col"> 구매자번호 </th>
			                                                <th scope="col"> 확인사항 </th>
			                                                <th scope="col"> 확인 여부 </th>
			                                                <th scope="col" colspan="2"> 등록일 </th>
			                                            </tr>
			                                        </thead>
			                                        <tbody>
			                                        	<c:if test="${!empty iroAllList }">
				                                        	<c:forEach var="irolist" items="${iroAllList }">
				                                        		<tr>
					                                                <th scope="row"> ${irolist.iroName } </th>
					                                                <td> ${irolist.iroPhoneNumber } </td>
					                                                <td> ${irolist.iroDetail } </td>
					                                                
					                                                	<c:if test="${irolist.iroFlag == true }">
					                                                		<td style="color:skyblue;"> Y </td>
					                                                	</c:if>
					                                                	<c:if test="${irolist.iroFlag == false }">
					                                                		<td style="color:red;"> N </td>
					                                                	</c:if>
					                                                <td> <fmt:formatDate value="${irolist.iroRegdate }" pattern="yyyy-MM-dd"/> </td>
					                                                <td><button class="btn btn-primary btn-block irodataDelete" value="${irolist.iroPk }"> 삭제하기 </button></td>
					                                            </tr>
				                                        	</c:forEach>			                                        	
			                                        	</c:if>
			                                        	<c:if test="${empty iroAllList }">
			                                        			<tr style="text-align: center;">
					                                               	<th colspan="6"> 필터할 고객데이터가 존재하지 않습니다</th>
					                                            </tr>
			                                        	</c:if>
			                                        </tbody>
			                                    </table>
			                                </div>
			                            </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- ============================================================== -->
                        <!-- end justified tabs  -->
                        <!-- ============================================================== -->
                    </div>
              
            </div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>