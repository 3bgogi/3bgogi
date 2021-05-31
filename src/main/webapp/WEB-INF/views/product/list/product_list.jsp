<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		$(document).on("click",".enterProduct",function(){

    			var productPk = $(this).find(".productNum").val();
    			
    			/* location.href = "<c:url value='/products/read/product.do?productPk="+productPk+"'/>"; */
    			
    			window.open("<c:url value='/products/read/product.do?productPk="+productPk+"'/>", "상품 목록" , "width=800, height=800, top=50, left=300, scrollbars=no");
    			
    		});
    		
    		$(document).on("keyup","#searchProducts",function(){
				
				var searchProduct =$("#searchProducts").val();
				
    			if(searchProduct != null || searchProduct  != ''){
    				
    				$(".productName").each(function(){    		
    					
	    				if($(this).text().includes(searchProduct)){
	    					$(this).parent().parent().show();
	    					
	    				}else{
	    					$(this).parent().parent().hide();
	    				}
    				});

    			}else{
    				
    				$(".productName").each(function(){
    					$(this).parent().parent().show();
    				});
    				
    			}
    			
    		});

			$(".cfCodeLi").click(function(){
				
				$.each( $(".cfCodeLi"),function(index, items){
					$(this).removeClass("active");
					
				});
				
				$(this).addClass("active");
				
				var cfFk = $(this).find(".cfPk").val();
				
	    		$.ajax({
	    			url:"<c:url value='/products/list/product_list_count_by_cffk.do'/>",
	    			data:{"cfFk":cfFk},
	    			type:"get",
	    			success:function(data){
	    				$(".productCount").text("( 총 "+data+"개 )");
	    				
	    			}
	    			
	    		});
	    		
	    		$.ajax({
	    			url:"<c:url value='/products/list/product_list_by_cffk.do'/>",
	    			data:{"cfFk":cfFk},
	    			type:"post",
	    			success:function(data){
		    				var productListByFk = "";
	    				
	    				if(data.length >= 1){
	    							    				
		    				$.each(data, function(index, items){
		    					
		    					productListByFk+='<div class="email-list-item email-list-item--unread enterProduct">'
		                            +'<div class="email-list-actions">'
		                                +'<label class="custom-control custom-checkbox">'
		                                    +'<input class="custom-control-input checkboxes productNum" type="checkbox" value="'+items.productPk+'" name="productPk"><span class="custom-control-label"></span>'
		                                +'</label><a class="favorite" href="#"><span><i class="fas fa-star"></i></span></a>'
		                            +'</div>'
		                            +'<div class="email-list-detail"><span class="date float-right"><span class="icon"> </span>'+formatDate(items.productRegdate)+'</span><span class="from productName"> '+items.productName+' </span>'
		                                +'<p class="msg"> '+items.productMemo1+' ( 등록된 옵션 개수 : '+items.productOptionCount+' 개 )</p>'
		                            +'</div>'
		                        +'</div>';
		    				});
		    				
	    				}else{
	    					
	    					productListByFk+='<div class="email-list-item email-list-item--unread">'
	    						+'<div class="card-body">'
	                				+'<h3 style="text-align: center;"> 검색 결과가 존재하지 않습니다.</h3>'
	                			+'</div>'
	                		+'</div>';
	                		
	    				}
	    				
	    				$("#productListResults").html(productListByFk);
	    				$("#searchProducts").val("");
	    			},
	    		});
			});
			
    	});
    </script>
    
        <!-- page content -->
        <!-- ============================================================== -->
        <!-- wrapper  -->
        <!-- ============================================================== -->
        <div class="dashboard-wrapper">
            <div class="container-fluid">
                <aside class="page-aside" style=" margin-top:67px; overflow-y: scroll;">
                    <div class="aside-content">
                        <div class="aside-header">
                            <button class="navbar-toggle" data-target=".aside-nav" data-toggle="collapse" type="button"><span class="icon"><i class="fas fa-caret-down"></i></span></button><span class="title"> 상품 총 목록</span>
                        </div>
                        <div class="aside-compose"><a class="btn btn-lg btn-secondary btn-block" href="<c:url value='/products/insert/product.do'/>"> 상품 추가 하기 </a></div>
                        <div class="aside-nav collapse">
                            <ul class="nav">
                            	<li class="active cfCodeLi"><input type="hidden" value="0" class="cfPk" ><a href="javascript:void(0)" style="font-weight: bold;"> 상품 전체  <span class="badge badge-primary float-right">188</span></a></li>
                            	<c:forEach var="cflist" items="${cfList }">
	                                <li class="cfCodeLi"><input type="hidden" value="${cflist.cfPk }" class="cfPk" ><a href="#"><span class="icon"><i class="m-r-10 mdi mdi-label text-secondary"></i></span> ${cflist.cfCodeType }<span class="badge badge-primary float-right"> ${cflist.cfProductCount }</span></a></li>                            	
                            	</c:forEach>
                            </ul>
                        </div>
                    </div>
                </aside>
                <div class="main-content container-fluid p-0">
                    <div class="email-inbox-header">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="email-title"><span class="icon"><i class="fas fa-inbox"></i></span> 상품 개수 <span class="new-messages productCount">( 총 188개 )</span> </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="email-search">
                                    <div class="input-group input-search">
                                        <input id="searchProducts" class="form-control" type="text" placeholder="상품 검색하기"><span class="input-group-btn">
                                       <button class="btn btn-secondary" type="button"><i class="fas fa-search"></i></button></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="email-filters">
                        <div class="email-filters-left">
                            <label class="custom-control custom-checkbox be-select-all">
                                <input class="custom-control-input chk_all" type="checkbox" name="chk_all"><span class="custom-control-label"></span>
                            </label>
                            <div class="btn-group">
                                <button class="btn btn-light dropdown-toggle" data-toggle="dropdown" type="button"> 정렬하기 <span class="caret"></span></button>
                                <div class="dropdown-menu dropdown-menu-right" role="menu">
                                <a class="dropdown-item" href="#">등록일순</a>
                                <a class="dropdown-item" href="#">상품명순</a>
                                    <div class="dropdown-divider">
                                    </div>
                                    <a class="dropdown-item active" href="#"> 오름차순</a>
                                    <a class="dropdown-item" href="#"> 내림차순</a>
                                </div>
                            </div>
                            <div class="btn-group">
                                <button class="btn btn-light dropdown-toggle" data-toggle="dropdown" type="button"> 상품 보기 개수  <span class="caret"></span></button>
                                <div class="dropdown-menu dropdown-menu-right" role="menu">
	                                <a class="dropdown-item" href="#"> 10개씩 </a>
	                                <a class="dropdown-item" href="#"> 20개씩</a>
	                                <a class="dropdown-item" href="#"> 30개씩</a>
	                                <a class="dropdown-item" href="#"> 50개씩</a>
	                                <a class="dropdown-item" href="#"> 100개씩</a>
                                </div>
                            </div>
                            
                            <div class="btn-group">
                                <button class="btn btn-light" type="button"> 사용하기 </button>
                                <button class="btn btn-light" type="button"> 사용치 않기 </button>
                                <button class="btn btn-light" type="button"> 삭제 </button>
                            </div>
                        </div>
                        <div class="email-filters-right"><span class="email-pagination-indicator">1-50 of 188</span>
                            <div class="btn-group email-pagination-nav">
                                <button class="btn btn-light" type="button"><i class="fas fa-angle-left"></i></button>
                                <button class="btn btn-light" type="button"><i class="fas fa-angle-right"></i></button>
                            </div>
                        </div>
                    </div>
                    <div class="email-list" id="productListResults">
                    	<c:if test="${empty productList }">
                    		<div class="email-list-item email-list-item--unread">
                    			<div class="card-body">
                    			<h3 style="text-align: center;"> 검색 결과가 존재하지 않습니다.</h3>
                    			</div>
                    		</div>
                    	</c:if>
                    	<c:if test="${!empty productList }">                    	
	                        <c:forEach var="productlist" items="${productList }">
	                        	<div class="email-list-item email-list-item--unread enterProduct">
		                            <div class="email-list-actions">
		                                <label class="custom-control custom-checkbox">
		                                    <input class="custom-control-input checkboxes productNum" type="checkbox" value="${productlist.productPk }" name="productPk"><span class="custom-control-label"></span>
		                                </label><a class="favorite" href="#"><span><i class="fas fa-star"></i></span></a>
		                            </div>
		                            <div class="email-list-detail">
			                            <span class="date float-right">
			                            	<span class="icon"></span> <fmt:formatDate value="${productlist.productRegdate }" pattern="yyyy-MM-dd"/>
			                            </span>
		                            	<span class="from productName"> ${productlist.productName } </span>
		                                <p class="msg"> ${productlist.productMemo1 } ( 등록된 옵션 개수 : ${productlist.productOptionCount } 개 )</p>
		                            </div>
		                        </div>
	                        </c:forEach>
                    	</c:if>
                    </div>
                </div>
            </div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>