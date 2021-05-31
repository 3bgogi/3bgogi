<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		$(".detailCosts").click(function(){
    			var costName = $(this).data("cost-name");
    			var costUniqueNum = $(this).data("unique-num");
    			var costDataLocation = $(this).parent().parent().next().find(".card-body");
    			var costDetailDataHTML = "";
    			var costDetailTotalCost = 0;
    			
    			$.ajax({
    			    type       : 'GET',
    			    url        : '/products/list/cost_detail_by_costs.do',
    			    data       : {"costName":costName,
    			    			  "costUniqueNum":costUniqueNum},
    			    success    : function(data){
						console.log(data);
						
						costDetailDataHTML+=
								'<table class="table table-striped">'
			                        +'<thead>'
			                        +'<tr>'
			                            +'<th scope="col">원재료명</th>'
			                            +'<th scope="col">상품 생산 원가</th>'
			                            +'<th scope="col">단위</th>'
			                            +'<th scope="col">생산 단위(Gram, Ml, Ea)</th>'
			                            +'<th scope="col">원재료 등록일</th>'
			                        +'</tr>'
			                    +'</thead>'
			                    +'<tbody>';
			              $.each(data, function(){
			            	  costDetailDataHTML+='<tr>'
		                            +'<th scope="row">'+this.costDetailList[0].cdName+'</th>';
		                            
		                            if(this.costDetailList[0].cdMeasure == 'kg'){
		                            	if(this.costDetailList[0].cdLossFlag == true){
		                            		costDetailDataHTML+='<td>'+comma(((this.costDetailList[0].cdCost + this.costDetailList[0].cdCost / this.costDetailList[0].cdLossRate) / 1000 * this.costMeasureCal).toFixed(0))+' 원</td>';
		                            		costDetailTotalCost+=((this.costDetailList[0].cdCost + this.costDetailList[0].cdCost / this.costDetailList[0].cdLossRate) / 1000 * this.costMeasureCal);
		                            		
		                            	}else{
		                            		costDetailDataHTML+='<td>'+comma(((this.costDetailList[0].cdCost/1000) * this.costMeasureCal).toFixed(0))+' 원</td>';
		                            		costDetailTotalCost+=((this.costDetailList[0].cdCost/1000) * this.costMeasureCal);
		                            	}
		                            }else if(this.costDetailList[0].cdMeasure == 'liter'){
		                            	costDetailDataHTML+='<td>'+comma(((this.costDetailList[0].cdCost/1000) * this.costMeasureCal))+' 원</td>';
		                            	costDetailTotalCost+=((this.costDetailList[0].cdCost/1000) * this.costMeasureCal);
		                            	
		                            }else if(this.costDetailList[0].cdMeasure == 'ea'){
		                            	costDetailDataHTML+='<td>'+comma((this.costDetailList[0].cdCost * this.costMeasureCal))+' 원</td>';
		                            	costDetailTotalCost+=(this.costDetailList[0].cdCost * this.costMeasureCal);
		                            }
		                            	
		                            costDetailDataHTML+='<td>'+this.costDetailList[0].cdMeasure +'</td>'
		                            +'<td>'+comma(this.costMeasureCal);
		                            
		                            if(this.costDetailList[0].cdMeasure == 'kg'){
		                            	costDetailDataHTML+=' g';
		                            }else if(this.costDetailList[0].cdMeasure == 'liter'){
		                            	costDetailDataHTML+=' ml';
		                            }else if(this.costDetailList[0].cdMeasure == 'ea'){
		                            	costDetailDataHTML+=' 개';
		                            }
		                            
		                            costDetailDataHTML+='</td>'
		                            +'<td>'+this.costDetailList[0].cdRegdate +'</td>'
		                        +'</tr>';
			              });
			              
			              costDetailDataHTML+='<td colspan="5" style="text-align:center;"> 총 원가 : '+comma(costDetailTotalCost.toFixed(0))+' 원</td>';
			              
			              costDetailDataHTML+='</tbody>'
			                +'</table>';
			              
			              costDataLocation.html(costDetailDataHTML);
    			    }
    			});
    			
    		});
    	});
    	
    </script>
        <!-- page content -->
        <!-- ============================================================== -->
        <!-- wrapper  -->
        <!-- ============================================================== -->
        <div class="dashboard-wrapper">
            <div class="container-fluid dashboard-content">
                <!-- ============================================================== -->
                <!-- pageheader -->
                <!-- ============================================================== -->
                <div class="row">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <div class="page-header">
                            <h2 class="pageheader-title"> 다중 원재료 목록 </h2>
                            <p class="pageheader-text">Proin placerat ante duiullam scelerisque a velit ac porta, fusce sit amet vestibulum mi. Morbi lobortis pulvinar quam.</p>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 상품 및 원재료 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 상품 및 원재료 목록 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 다중 원재료 목록 </li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- end pageheader -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- accrodions -->
                <!-- ============================================================== -->
					<div class="row">
						<div class="col-lg-12">
							<div class="email-search">
								<div class="input-group input-search">
									<input class="form-control" type="text" placeholder="다중 원재료 검색"><span class="input-group-btn">
										<button class="btn btn-secondary" type="button">
											<i class="fas fa-search"></i>
										</button>
									</span>
								</div>
							</div>
						</div>
					</div>
					<hr>
					<div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="accrodion-regular">
                                <div id="accordion3">
                                	<c:set var="idCounting" value="0"/>
                                	<c:forEach var="costslist" items="${costsList }">
	                                	<div class="card mb-2">
	                                        <div class="card-header" id="headerCostList${idCounting}${costslist.costUniqueNum }">
	                                            <h5 class="mb-0">
	                                               <button class="btn btn-link collapsed detailCosts" data-cost-name="${costslist.costName }" data-unique-num="${costslist.costUniqueNum }" data-toggle="collapse" data-target="#${idCounting}${costslist.costUniqueNum }" aria-expanded="false" aria-controls="${idCounting}${costslist.costUniqueNum }">
	                                                 <span class="fas fa-angle-down mr-3"></span>${costslist.costName } 등록일 [<fmt:formatDate value="${costslist.costRegdate }" pattern="yyyy-MM-dd"/>]
	                                             </button>
	                                            </h5>
	                                        </div>
	                                        <div id="${idCounting}${costslist.costUniqueNum }" class="collapse" aria-labelledby="headerCostList${idCounting}${costslist.costUniqueNum }" data-parent="#accordion3">
	                                            <div class="card-body">
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <c:set var="idCounting" value="${idCounting+1 }"/>
                                	</c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
              
            </div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>