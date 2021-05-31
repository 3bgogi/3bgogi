<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../../../inc/top.jsp" %>
    <%@ include file="../../../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    
    var regNumber = /^[0-9]*$/
    
    	$(function(){
    		
			$(document).on("keyup","#searchCostDetails",function(){
				
				var searchProduct =$("#searchCostDetails").val();

    			if(searchProduct != null || searchProduct  != ''){
    				
    				$(".costDetailName").each(function(){    		
    					
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
    		
    		$(".labelBtn").click(function(){
    			if($(this).hasClass("active")){
    				$(this).removeClass("active");
    				$("input[name=labelBtn]").val("0").trigger('change');
    				
    			}else{
    				$(this).addClass("active");
    				$("input[name=labelBtn]").val("1").trigger('change');
    				
    			}
    		});
    		
			$(".pageSet").click(function(){

    			$(".pageSet").each(function(){
    				$(this).removeClass("active");
    				
    			});
    			
    			$(this).addClass("active");
    			recordCountPerPage = $(this).data("page-set");
    			$("input[name=recordCountPerPage]").val(recordCountPerPage).trigger('change');
    			
    			
    		});
    		
			$("input[name=costCodeSet]").change(function(){
				$("input[name=ccPk]").val($(this).val()).trigger('change');
			});
			
    		$(".costDetail").click(function(){
    			
    			classEx = $(this).hasClass("active");
    			
    			$(".costDetail").each(function(){
    				$(this).removeClass("active");
    				
    			});
    			
    			if( classEx ){
    				$(this).removeClass("active");
    				$("input[name=cdPk]").val("0").trigger('change');
    				
    			}else{    				
	    			$(this).addClass("active");
	    			cdPk = $(this).data("cd-pk");
	    			$("input[name=cdPk]").val(cdPk).trigger('change');
	    			
    			}
    			
    			
    		});
    		
    		$(document).on('click', '.outputFlag', function(){
    			cdFk = $(this).data("cd-fk");
    			ciPk = $(this).data("ci-pk");
    			
    			
    			$.ajax({
    				type       : 'GET',
    				data       : {
    					"cdFk":cdFk,
    					"ciPk":ciPk
    				},
    				url        : '/stock/choose_cost_io_ajax.do',
    				success    : function(data){	
    					if(data == 1){
    						alert("출고 원육 지정 완료");
    					}else{
    						alert("출고 원육 지정 실패");
    					}
    					
    				},error		:function(){

    				}
    				
    			});
    			
    		});
    		
    		
    		$("input[name=labelBtn], input[name=cdPk], input[name=sortingBtn], input[name=recordCountPerPage], input[name=costCodeSet]").change(function(){
    			$("#carcassManageForm").submit();
    			
    		});
        	
    	});
    	
    	function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#carcassManageForm").submit();
			
		}

		
    	/* function selectCarcassManage(){
    		totalRecord = 0;
    		
    		currentPage = $("input[name=currentPage]").val();
    		recordCountPerPage = $("input[name=recordCountPerPage]").val();
    		
    		
			var carcassManageFormData = jQuery("#carcassManageForm").serialize();
			
			$.ajax({
				type       : 'POST',
				data       : carcassManageFormData,
				url        : '/stock/carcass_manage_ajax.do',
				success    : function(data){		
					
					if(data.cdList.length > 0){
						caracssHTML = "";
						
						totalRecord = data.totalRecord;
						
						$.each(data.cdList, function(){
							cdName = this.cdName;
							cdPk = this.cdPk;
							
							$.each(this.costIoVOList, function(){
    							caracssHTML+='<div class="email-list-item email-list-item--unread">'
		                            +'<div class="email-list-actions">'
		                                +'<label class="custom-control custom-checkbox">'
		                                    +'<input class="custom-control-input checkboxes" type="checkbox" value="'+this.ciPk+'"><span class="custom-control-label"></span>'
		                                +'</label><a class="outputFlag favorite';
		                                
		                                if(this.ciOutputFlag == true){
		                                	caracssHTML+= ' active';
		                                }

		                                caracssHTML+= ' " data-cd-fk="'+cdPk+'" data-ci-pk="'+this.ciPk+'" href="#"><span><i class="fas fa-star"></i></span></a>'
		                                
		                            +'</div>'
		                            +'<div class="email-list-detail">'
		                            	+'<span class="date float-right">'
		                            			+formatDate(this.ciRegdate)
		                            			
		                            		+'</span>'
		                            	+'<span class="from">'+cdName+', 이력번호 : '+this.ciAnimalProdTraceNum+', 등급 '+this.ciLevel;
		                            	
		                            	if( regNumber.test(this.ciMarblingLevel) == true  && this.ciMarblingLevel != ''){
		                            		caracssHTML+=' , 근내지방등급 '+this.ciMarblingLevel;
		                            		
		                            		
		                            	}

		                            	caracssHTML+='</span>'
		                               + '<p class="msg">입고무게 : '+comma(this.ciWeight)+'g</p>'
		                            +'</div>'
		                        +'</div>';
							});
							
						});
						
						 paginationRebuild(totalRecord, currentPage, recordCountPerPage );
						 
						$("#carcassLists").html( caracssHTML );
						
						
					}
					
				}
				
			});
		} */
    	
    	
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
            <div class="container-fluid">
                <aside class="page-aside" style=" margin-top:67px; overflow-y: scroll;">
                    <div class="aside-content">
                        <div class="aside-header">
                            <button class="navbar-toggle" data-target=".aside-nav" data-toggle="collapse" type="button"><span class="icon"><i class="fas fa-caret-down"></i></span></button><span class="title"> 원육 출고 관리 </span>
                            <p class="description"> - 부분육 종합 관리 </p>
                            <p class="description" style="color: red;"> - 별 표시는 라벨지에 기록되는 값 </p>
                        </div>
                        <div class="aside-compose"><a class="btn btn-lg btn-secondary btn-block" href="#"> 현재 등록된 관리 목록 </a></div>
                        <div class="aside-nav collapse">
                            <ul class="nav">
                            	<li class="chooseCategory" style="margin: auto; display: table;">
                            		<label class="custom-control custom-radio custom-control-inline">
						                 <input type="radio"  name="costCodeSet"class="custom-control-input"
						                 	<c:if test="${osVO.ccPk == 0}">
		                            			checked="checked"
		                            		</c:if>
						                  value="0"><span class="custom-control-label">전체</span>
						            </label>
						            <label class="custom-control custom-radio custom-control-inline">
						                 <input type="radio"  name="costCodeSet"class="custom-control-input"
						                 	<c:if test="${osVO.ccPk == 1}">
		                            			checked="checked"
		                            		</c:if>
						                  value="1"><span class="custom-control-label">한우</span>
						            </label>
						            <label class="custom-control custom-radio custom-control-inline">
						                 <input type="radio"  name="costCodeSet"class="custom-control-input"
						                 	<c:if test="${osVO.ccPk ==  2}">
		                            			checked="checked"
		                            		</c:if>
						                  value="2"><span class="custom-control-label">한돈</span>
						            </label>
                            	</li>
                            	<c:forEach var="categorylist" items="${categoryList }">                            		
	                            	<li class="costDetail
	                            		<c:if test="${osVO.cdPk ==  categorylist.cdPk}">
	                            			active scrollMove
	                            		</c:if>
	                            	" data-cd-pk="${categorylist.cdPk }"><a href="#">${categorylist.cdName } <span class="badge badge-primary float-right"> ${categorylist.cdCost } 건</span> </a></li>
	                            	
                            	</c:forEach>
                            </ul>
                        </div>
                    </div>
                </aside>
                <div class="main-content container-fluid p-0">
                    <div class="email-inbox-header">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="email-search">
                                    <div class="input-group input-search">
                                        <input class="form-control" id="searchCostDetails" type="text" placeholder="상품 검색"><span class="input-group-btn">
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
                                <button class="btn btn-light dropdown-toggle" data-toggle="dropdown" type="button">설정 <span class="caret"></span></button>
                                <div class="dropdown-menu dropdown-menu-right" role="menu">
                                	<a class="dropdown-item deleteBtn" href="#">선택값 삭제하기</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item labelBtn
                                    	<c:if test="${osVO.labelBtn != 0}">
                                    		active
                                    	</c:if>
                                    " href="#">라벨지 표기만 보기</a>
                                </div>
                            </div>
                            
                            <div class="btn-group">
                                <button class="btn btn-light dropdown-toggle" data-toggle="dropdown" type="button">정렬 <span class="caret"></span></button>
                                <div class="dropdown-menu dropdown-menu-right" role="menu">
                                	<a class="dropdown-item upperSortingBtn active" href="#">등록일 내림</a>
                                	<a class="dropdown-item lowerSortingBtn" href="#">등록일 올림</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item searchType" href="#">입고 무게 내림</a>
                                    <a class="dropdown-item searchType" href="#">입고 무게 올림</a>
                                </div>
                            </div>
                            <div class="btn-group">
                                <button class="btn btn-light dropdown-toggle" data-toggle="dropdown" type="button">페이지 개수 <span class="caret"></span></button>
                                <div class="dropdown-menu dropdown-menu-right" role="menu">
                                	<a class="dropdown-item pageSet
                                		<c:if test="${osVO.recordCountPerPage == 10}">
                                			active
                                		</c:if>
                                	" data-page-set="10" href="#">10개씩 보기</a>
                                	<a class="dropdown-item pageSet
                                		<c:if test="${osVO.recordCountPerPage == 20}">
                                			active
                                		</c:if>
                                	" data-page-set="20" href="#">20개씩 보기</a>
                                	<a class="dropdown-item pageSet
                                		<c:if test="${osVO.recordCountPerPage == 30}">
                                			active
                                		</c:if>
                                	" data-page-set="30" href="#">30개씩 보기</a>
                                	<a class="dropdown-item pageSet
                                		<c:if test="${osVO.recordCountPerPage == 50}">
                                			active
                                		</c:if>
                                	" data-page-set="50" href="#">50개씩 보기</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="email-list">
                    	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="carcassLists" style="padding: 0;">                    	
	                    	<c:forEach var="cdlist" items="${cdList }">
	                    		<c:forEach var="ciList" items="${cdlist.costIoVOList }">                    		
			                    	<div class="email-list-item email-list-item--unread">
			                            <div class="email-list-actions">
			                                <label class="custom-control custom-checkbox">
			                                    <input class="custom-control-input checkboxes" type="checkbox" value="${ciList.ciPk }"><span class="custom-control-label"></span>
			                                </label><a class="outputFlag favorite
			                                	<c:if test="${ciList.ciOutputFlag == true}">
			                                		active
			                                	</c:if>
			                                " data-cd-fk="${cdlist.cdPk }" data-ci-pk="${ciList.ciPk }" href="#"><span><i class="fas fa-star"></i></span></a>
			                            </div>
			                            <div class="email-list-detail">
			                            	<span class="date float-right">
			                            		<fmt:formatDate value="${ciList.ciRegdate }" pattern="yyyy-MM-dd"/>
			                            	</span>
			                            	<span class="from costDetailName">${cdlist.cdName }, 이력번호 : ${ciList.ciAnimalProdTraceNum }, 등급 ${ciList.ciLevel }
			                            		<c:if test="${!empty ciList.ciMarblingLevel and ciList.ciMarblingLevel != ' '}">
			                            			, 근내지방등급 ${ciList.ciMarblingLevel }
			                            			
			                            		</c:if>
			                            	</span>
			                                <p class="msg">입고무게 : <fmt:formatNumber value="${ciList.ciWeight }" pattern="#,###"/>g</p>
			                            </div>
			                        </div>
	                    		</c:forEach>                            		
	                        </c:forEach>
                    	</div>
                    	
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" style="margin-top: 10px; text-align: center;">
                        	<form id="carcassManageForm" action="<c:url value='/stock/carcass_manage.do'/>" method="GET">                        	
	                        	<input type="hidden" name="labelBtn" value="${osVO.labelBtn }">
	                        	<input type="hidden" name="sortingBtn" value="${osVO.sortingBtn }">
	                        	<input type="hidden" name="cdPk" value="${osVO.cdPk }">
	                        	<input type="hidden" name="ccPk" value="${osVO.ccPk }">
	                        	<input type="hidden" name="currentPage" value="${osVO.currentPage}">
	                        	<input type="hidden" name="recordCountPerPage" value="${osVO.recordCountPerPage}">
	                        	
                        	</form>
                        		<input type="hidden" name="totalRecord" value="${osVO.totalRecord }">
                        	
                           	<nav aria-label="Page navigation" style="text-align: center;" id="pageNavigation">
								<ul class="pagination" style="display: table; margin-left: auto; margin-right: auto;">
									<c:if test="${osVO.firstPage>1 }">
										<li class="page-item" style="float:left; padding-top:5px;"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${osVO.firstPage-1})">«</a></li>
									</c:if>
									<c:forEach var="i" step="1" end="${osVO.lastPage}" begin="${osVO.firstPage }">
										<li class="page-item
											<c:if test="${osVO.currentPage == i }">
												active
											</c:if>
										"  style="float:left; padding-top:5px;"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${i})">${i }</a></li>
									</c:forEach>
									<c:if test="${osVO.lastPage < osVO.totalPage}">
										<li class="page-item" style="float:left; padding-top:5px;"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${osVO.lastPage+1})">»</a></li>
									</c:if>
								</ul>
							</nav>
                       </div>
                    </div>
                </div>
            </div>
        <!-- /page content -->
        <iframe id="fileDownloadIframe" width="0" height="0">
		</iframe> 
		<script type="text/javascript">
			
			/* var scrollMoveLocation = document.querySelector(".scrollMove").offsetTop;
		    
		    window.scrollTo({top:scrollMoveLocation, behavior:'smooth'}); */
		    
		    
		</script>
        <%@ include file="../../../../inc/bottom.jsp" %>