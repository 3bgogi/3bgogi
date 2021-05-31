<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../../inc/top.jsp" %>
    <%@ include file="../../../inc/top_nav.jsp" %>
    <style type="text/css">
    	.products_table_hover{
    		background-color: #c3e6cb;
    	}
    	.SelfdevisionOrderBtn{
    		cursor: pointer;
    	}
    </style>
    <script type="text/javascript">
		   
    
    	$(function(){
    		
    		window.onbeforeunload = function(e){
    	    	opener.location.reload();
        	}
    		
    		$(".orSerialNum").mouseover(function(){
    			var table_id = $(this).next().data("table-product-info");
    			
    			var table_order = $(this).data("serial-num");
    			$(this).addClass("products_table_hover");
    			$("td[data-table-info='"+table_id+"']").addClass("products_table_hover");
    			$("td[data-serial-num-info='"+table_order+"']").addClass("products_table_hover");
    			
    		});
    		
    		$(".orSerialNum").mouseleave(function(){
    			
    			
    			var table_id = $(this).next().data("table-product-info");
    			var table_order = $(this).data("serial-num");
    			$(this).removeClass("products_table_hover");
    			$("td[data-table-info='"+table_id+"']").removeClass("products_table_hover");
    			$("td[data-serial-num-info='"+table_order+"']").removeClass("products_table_hover");
    			
    		});
    		
    		$(".productInfo").mouseover(function(){
    			var table_id = $(this).data("table-product-info");
    			var table_order = $(this).data("serial-num-info");
    			$(this).next().addClass("products_table_hover");
    			$(this).addClass("products_table_hover");
    			$("td[data-table-info='"+table_id+"']").addClass("products_table_hover");
    			$("td[data-serial-num='"+table_order+"']").addClass("products_table_hover");
    			
    		});
    		
    		$(".productInfo").mouseleave(function(){
    			
    			var table_id = $(this).data("table-product-info");
    			var table_order = $(this).data("serial-num-info");
    			$(this).next().removeClass("products_table_hover");
    			$(this).removeClass("products_table_hover");
    			$("td[data-table-info='"+table_id+"']").removeClass("products_table_hover");
    			$("td[data-serial-num='"+table_order+"']").removeClass("products_table_hover");
    			
    		});
    		
    		$(".productDetail").mouseover(function(){
    			var table_id = $(this).data("table-product-detail");
    			var table_order = $(this).data("serial-num-info");
    			$(this).prev().addClass("products_table_hover");
    			$(this).addClass("products_table_hover");
    			$("td[data-table-info='"+table_id+"']").addClass("products_table_hover");
    			$("td[data-serial-num='"+table_order+"']").addClass("products_table_hover");
    			
    		});
    		
    		$(".productDetail").mouseleave(function(){
    			
    			var table_id = $(this).data("table-product-detail");
    			var table_order = $(this).data("serial-num-info");
    			$(this).prev().removeClass("products_table_hover");
    			$(this).removeClass("products_table_hover");
    			$("td[data-table-info='"+table_id+"']").removeClass("products_table_hover");
    			$("td[data-serial-num='"+table_order+"']").removeClass("products_table_hover");
    			
    		});
    		
    		$(".combineBtn").click(function(){
    			var orderNum = $(this).val();
    			var orSize = $("td[data-combine-target='"+orderNum+"']").length;
    			
    			if(orSize == 0){
    				alert("합포할 수 있는 주문서가 없습니다");
    			}else{
    				
    				if(confirm(orSize+"개의 주문서를 합포 하시겠습니까?")){				
    						var orSerialSpecialNumberList = new Array(orSize);
    						
    						$("td[data-combine-target='"+orderNum+"']").each(function(i, selected) {
    							
    							orSerialSpecialNumberList[i]=$(selected).data("serial-num");

    					     });
    						
    						window.open("/orders/config/combine_order.do?orSerialSpecialNumberList="+orSerialSpecialNumberList, "주문서 합치기" , "width=1500px, height=620px, top=50px, left=50px, scrollbars=no");
    				}
    			}
    			
    		});

    	});
    	

    </script>
    <style type="text/css">
    	.selectClass{
    		background-color: #FFA2A2;
    		color:white;
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
                            <h2 class="pageheader-title"> 합포장 확인 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문서 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문 입력 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문 매칭 </a></li>
                                        
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
                    	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 합포장이 가능 주문서 예상 목록 </h5>
                                <div class="card-body">
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr style="text-align: center;">
                                            	<th scope="col"> 판매처 </th>
                                                <th scope="col"> 구매자 / 수령자</th>
                                                <th scope="col"> 주소 </th>
                                                <th scope="col"> 주문서 구분 </th>
                                                <th scope="col"> 구매 상품 </th>
                                                <th scope="col"> 매칭 상품 </th>
                                            </tr>
                                        </thead>
                                        <tbody class="dataTable">
                                        	<c:set var="tableCountings" value="1"/>
                                        	<c:set var="rowCounting" value="1"/>
	                                        	<c:set var="backgroundBoolean" value="1"/>
	                                        	<c:forEach var="orlist" items="${orList }">
	                                        		<c:set var="packingDupli" value=""/>
	                                        		<c:set var="rowSpans" value="${fn:length(orlist.orVoList)}"/>
	                                        		<c:forEach var="stocklist" items="${orlist.orVoList }">
	                                        			<tr
	                                        			<c:if test="${backgroundBoolean % 2 == 0}">
			                                        		style="background-color:#edeef4;"
			                                        	</c:if>
	                                        			>
	                                        			<c:if test="${rowCounting == 1 }">
	                                        				<td rowspan="${rowSpans }" style="width:260px; text-align: center;" data-table-info="${tableCountings }">
								                            	<p style="margin-bottom: 5px;">${orlist.ssName } </p><button class="btn btn-primary btn-xs combineBtn" value="${orlist.orOrderNumber }"> 합치기</button>
								                            </td>
			                                        		<td rowspan="${rowSpans }" style="width:260px; text-align: center;" data-table-info="${tableCountings }">
								                            	<p style="margin-bottom: 5px;">${orlist.orBuyerName } / ${orlist.orBuyerContractNumber1 }<br>
								                                ${orlist.orReceiverName } / ${orlist.orReceiverContractNumber1 }</p>
								                            </td>
								                            <td rowspan="${rowSpans }" style="width:300px; text-align: center;" data-table-info="${tableCountings }">
								                            	<p style="margin-bottom: 5px;">${orlist.orShippingAddress } ${orlist.orShippingAddressDetail }</p>
								                            </td>
								                            <c:set var="rowCounting" value="${rowCounting+1 }"/>
			                                        	</c:if>
			                                        	
			                                        	
			                                        	
			                                        	<c:if test="${!empty stocklist.productMatchingVOList }">	                                        			
			                                        		<c:forEach var="pmlist" items="${stocklist.productMatchingVOList }"> 
					                                        		<td style="border: 1px solid lightgray; width:430px;" class="orSerialNum" data-combine-target="${orlist.orOrderNumber }"  data-serial-num="${stocklist.orSerialSpecialNumber }">
																		<span style="font-size: 12px;">${stocklist.orSerialSpecialNumber }</span>
					                                        		</td>
			                                        		</c:forEach>
	                                        			</c:if>
	                                        			
			                                        	<c:if test="${!empty stocklist.productMatchingVOList }">	                                        			
			                                        		<c:forEach var="pmlist" items="${stocklist.productMatchingVOList }"> 
			                                        		
					                                        		<td style="border: 1px solid lightgray; width:430px;" class="productInfo" data-table-product-info="${tableCountings }" data-serial-num-info="${stocklist.orSerialSpecialNumber }">
																		<span style="font-size: 12px;"> ${stocklist.orProduct }</span><br>
																		<span style="font-size: 12px;"> ${stocklist.orProductOption }</span>
					                                        		</td>
			                                        		</c:forEach>
	                                        			</c:if>
	                                        			
	                                        			<c:if test="${!empty stocklist.productMatchingVOList }">	                                        			
			                                        		<c:forEach var="pmlist" items="${stocklist.productMatchingVOList }"> 
					                                        		<td style="border: 1px solid lightgray; width:430px;" class="productDetail" data-table-product-detail="${tableCountings }" data-serial-num-info="${stocklist.orSerialSpecialNumber }">
																		<c:if test="${!empty pmlist.optionMatchingVOList }">																		
							                                        		<c:forEach var="opmlist" items="${pmlist.optionMatchingVOList }">
								                                        		<c:forEach var="proOplist" items="${opmlist.productOptionList }">
								                                        			<c:if test="${!empty proOplist.optionName }">								                                        			
									                                        			<span style="font-size: 12px;">${proOplist.productName } [ ${proOplist.optionName } ] ${proOplist.cfFk } 개 </span><br>
								                                        			</c:if>
								                                        		</c:forEach>

							                                        		</c:forEach>
																		</c:if>
					                                        		</td>
			                                        		</c:forEach>
	                                        			</c:if>
	                                        		</tr>
	                                        		</c:forEach>
		                                        	<c:set var="backgroundBoolean" value="${backgroundBoolean + 1 }"/>
		                                        	<c:set var="rowCounting" value="1"/>
		                                        	<c:set var="tableCountings" value="${tableCountings + 1 }"/>
	                                        	</c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
        <!-- /page content -->
        <iframe id="excelDownloadIframe" width="0" height="0">
		</iframe>       
		
		<script type="text/javascript">
		 $(document).ready(function(e){
		        genRowspan("orSerialNum");
		    });
		     
		    function genRowspan(className){
		        $("." + className).each(function() {
		            var rows = $("." + className + ":contains('" + $(this).text() + "')");
		            if (rows.length > 1) {
		                rows.eq(0).attr("rowspan", rows.length);
		                rows.not(":eq(0)").remove();
		            }
		        });
		    }
		</script>
        <%@ include file="../../../inc/bottom.jsp" %>