<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ============================================================== -->
        <!-- left sidebar -->
        <!-- ============================================================== -->
        <div class="nav-left-sidebar sidebar-dark">
            <div class="menu-list">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <a class="d-xl-none d-lg-none" href="#"> 메뉴 열람 </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav flex-column">
                            <li class="nav-item ">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#submenu-1" aria-controls="submenu-1"><i class="fa fa-fw fa-user-circle"></i>개인 <span class="badge badge-success">6</span></a>
                                <div id="submenu-1" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                    	<li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/admin/attendance/admin_attendance_status.do'/>"> 출결 관리 </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/project/projects.do'/>">업무</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <!-- <li class="nav-item">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#team" aria-controls="team"><i class="fas fa-fw fa-sitemap"></i> 팀  </a>
                                <div id="team" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                        <li class="nav-item">
                                            <a class="nav-link" href="javascript:void(0);"> 팀 프로젝트 </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="javascript:void(0);"> 팀원 정보  </a>
                                        </li>
                                    </ul>
                                </div>
                            </li> -->
                            
                            <li class="nav-item">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#stock_manage" aria-controls="stock_manage"><i class="fas fa-archive"></i> 재고 관리  </a>
                                <div id="stock_manage" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                    	<li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#product_manage" aria-controls="product_manage"> 상품 관리 </a>
                                            <div id="product_manage" class="collapse submenu" style="">
                                                <ul class="nav flex-column">
                                                	<li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/stock/manage.do'/>"> 상품 입고 </a>
						                            </li>
                                                   <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/stock/stock_req_list.do'/>"> 상품 입고 내역 </a>
						                            </li>
                                                </ul>
                                            </div>
                                        </li>
                                    </ul>
                                    <ul class="nav flex-column">
                                    	<li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#meat_manage" aria-controls="meat_manage"> 원육 관리 </a>
                                            <div id="meat_manage" class="collapse submenu" style="">
                                                <ul class="nav flex-column">
                                                   <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/stock/carcass/carcass_cut/insert.do'/>"> 부분 원육 입고  </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/stock/carcass/carcass_cut/list.do'/>"> 등록된 부분 원육 목록  </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/stock/carcass/insert.do'/>"> 도체 등록   </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/stock/carcass/list.do'/>"> 등록된 도체 목록  </a>
						                            </li>
                                                </ul>
                                            </div>
                                        </li>
                                    </ul>
                                    <ul class="nav flex-column">
                                    	<li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/stock/carcass_manage.do'/>"> 원육 출고 관리 </a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li class="nav-divider">
                               	 관리 메뉴
                            </li>
                            
                            <%-- <li class="nav-item">
                            	<a class="nav-link" href="<c:url value='/analytics/reserv_product_qty.do'/>"><i class="fas fa-calendar-alt"></i> 예약 상품 확인 </a>
                            </li> --%>
                            
                            <li class="nav-item">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#sending" aria-controls="sending"><i class="fas fa-fw fa-shipping-fast"></i> 발송 및 출고  </a>
                                <div id="sending" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                        <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/delivery/sending.do'/>"> 상품 출고 </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/delivery/store_order_sending.do'/>"> 판매처 송장부여 </a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#customer_service" aria-controls="sending"><i class="fas fa-address-book"></i> C / S  </a>
                                <div id="customer_service" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                        <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/orders/search/customer_orders.do'/>"> 고객 검색  </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/delivery/config/deliv_num_check.do'/>"> 송장 체크 </a>
                                        </li>
                                        
                                        <li class="nav-item">
                                            <a class="nav-link createNewOrder" href="#"> 새로운 주문 생성 </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" target="_blank" href="<c:url value='/aligo_msg/view.do'/>"> Aligo 문자  </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/config/block_sending_list/insert.do'/>"> 문자발송금지명단  </a>
                                        </li>
                                        
                                    </ul>
                                </div>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#submenu-6" aria-controls="submenu-6"><i class="fas fa-fw fa-file"></i> 주문 </a>
                                <div id="submenu-6" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                    	<li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#store_order" aria-controls="store_order"> 주문 입력 </a>
                                            <div id="store_order" class="collapse submenu" style="">
                                                <ul class="nav flex-column">
                                                   <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/orders/order_page.do'/>"> * 주문 등록 </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/order/config/search_except_addr_order.do'/>"> * 특수 지역 체크  </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value="/order/matching/products_matching.do"/>"> * 상품명 매칭 </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value="/order/matching/option_matching.do"/>"> * 옵션명 매칭 및 원가체크 </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/config/freebie/apply.do'/>"> * 사은품 부여 </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/orders/delivery_msg_check.do'/>"> * 요청 사항 체크 </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/stock/stk_check.do'/>"> * 재고 할당 </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/orders/cancled_order_check.do'/>"> * 취소 주문 체크 </a>
						                            </li>
						                            <li class="nav-item">
						                             	<a class="nav-link" href="<c:url value='/security/epost.do'/>"> * 송장 부여 </a>
						                            </li>
                                                </ul>
                                                
                                            </div>
                                        </li>
                                    	<li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#excel_order" aria-controls="excel_order"> 송장입력 </a>
                                            <div id="excel_order" class="collapse submenu" style="">
                                                <ul class="nav flex-column">
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/orders/smart_store_sending_order_insert.do'/>" style="color:#ffa3a3;"> 프레쉬솔루션 </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#irregular_order" aria-controls="irregular_order"> 고객 필터링 </a>
                                            <div id="irregular_order" class="collapse submenu" style="">
                                                <ul class="nav flex-column">
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/orders/irregular/list.do'/>"> 목록 </a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/orders/irregular/add.do'/>"> 추가하기 </a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/orders/irregular/all_list.do'/>"> 전체 목록 </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#submenu-7" aria-controls="submenu-7"><i class="fas fa-fw fa-inbox"></i> 상품 및 원재료 <span class="badge badge-secondary">New</span></a>
                                <div id="submenu-7" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                    	<li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#dataList" aria-controls="dataList"> 상품 및 원재료 목록 </a>
                                            <div id="dataList" class="collapse submenu" style="">
                                                <ul class="nav flex-column">
                                                	<li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/products/list/product_list.do'/>"> 상품 목록 </a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/products/list/costs.do'/>"> 다중 원재료 목록 </a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/products/list/cost_detail.do'/>"> 원재료 목록 </a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/stock/check_barcode_dupli.do'/>"> 바코드 확인 </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#submenu-11" aria-controls="submenu-11"> 상품 및 원재료 입력 </a>
                                            <div id="submenu-11" class="collapse submenu" style="">
                                                <ul class="nav flex-column">
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/products/insert/cost_detail.do'/>"> 원재료 상세사항 입력 </a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/products/insert/costs.do'/>"> 다중 원재료 입력 </a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="<c:url value='/products/insert/product.do'/>"> 상품 등록 </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#analy" aria-controls="analy"><i class="fas fa-chart-pie"></i> 조회 </a>
                                <div id="analy" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                        <li class="nav-item">
                                        	<a class="nav-link" href="<c:url value='/analytics/analy_search_list.do'/>"> 선택 조회 </a>
                                        </li>
                                        <li class="nav-item">
                                        	<a class="nav-link" href="<c:url value='/analytics/total_sales.do'/>"> 매출 </a>
                                        </li>
                                        <li class="nav-item">
                                        	<a class="nav-link" href="<c:url value='/config/event_msg.do'/>"> 문자 대상 추출 </a>
                                        </li>
                                         <li class="nav-item">
                                        	<a class="nav-link" href="<c:url value='/analytics/local.do'/>"> 지역 통계 </a>
                                        </li>
                                        
                                    </ul>
                                </div>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#configuration_setting" aria-controls="configuration_setting"><i class="fas fa-cogs"></i> 설정 </a>
                                <div id="configuration_setting" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                    	
                                        <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/config/store/list.do'/>"> 판매처 설정 </a>
                                        </li>
                                        <li class="nav-item">
				                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#freebie" aria-controls="freebie"> 사은품 정책 </a>
				                            <div id="freebie" class="collapse submenu" style="">
					                            <ul class="nav flex-column">
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/config/freebie/insert.do'/>"> 사은품 정책 추가  </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/config/freebie/list.do'/>"> 사은품 정책 목록  </a>
						                            </li>
					                            </ul>
				                            </div>
			                            </li>
			                            <li class="nav-item">
			                            	<a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#classification_code" aria-controls="classification_code"> 분류 코드 </a>
			                                <div id="classification_code" class="collapse submenu" style="">
			                                	<ul class="nav flex-column">
			                                       <li class="nav-item">
			                                           <a class="nav-link" href="<c:url value='/code/cost_code.do'/>"> 원재료 분류 코드  </a>
			                                       </li>
			                                       <li class="nav-item">
			                                           <a class="nav-link" href="<c:url value='/code/cf_code.do'/>"> 상품 분류 코드 </a>
			                                       </li>
			                                       <li class="nav-item">
			                                           <a class="nav-link" href="<c:url value='/code/excel_order_seq.do'/>"> 주문서 분류 코드 </a>
			                                       </li>
			                                    </ul>
			                                </div>
			                           </li>
			                           <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/delivery/config/early_deliv.do'/>"> 배송 방법 설정 </a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            
                            <li class="nav-item">
                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#tax" aria-controls="tax"><i class="fas fa-chart-pie"></i> 세금계산서 </a>
                                <div id="tax" class="collapse submenu" style="">
                                    <ul class="nav flex-column">
                                        <li class="nav-item">
				                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#res_company" aria-controls="res_company"> 거래처</a>
				                            <div id="res_company" class="collapse submenu" style="">
					                            <ul class="nav flex-column">
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/tax/res_company/insert.do'/>"> 거래처 추가  </a>
						                            </li>
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/tax/res_company/list.do'/>"> 거래처 목록  </a>
						                            </li>
					                            </ul>
				                            </div>
			                            </li>
			                            <li class="nav-item">
				                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#product_info" aria-controls="product_info"> 거래내역서 </a>
				                            <div id="product_info" class="collapse submenu" style="">
					                            <ul class="nav flex-column">
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/tax/product_info/insert.do'/>"> 거래내역서 추가  </a>
						                            </li>
						                            
						                            <li class="nav-item">
						                            	<a class="nav-link" href="<c:url value='/tax/product_info/list.do'/>"> 거래내역서 목록  </a>
						                            </li>
					                            </ul>
				                            </div>
			                            </li>
                                    </ul>
                                </div>
                            </li>
                            <sec:authorize access="hasRole('ROLE_MASTER')">
	                             <li class="nav-divider">
	                               	 관리자 전용
	                            </li>
	                            <li class="nav-item">
	                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#logs" aria-controls="logs"> 기록 확인  </a>
	                                <div id="logs" class="collapse submenu" style="">
	                                    <ul class="nav flex-column">
	                                        <li class="nav-item">
		                                        <a class="nav-link" href="<c:url value='/order/config/delete_order_list.do'/>"> 주문서 삭제 내역 </a>
		                                    </li>
		                                    <li class="nav-item">
		                                    	<a class="nav-link" href="<c:url value='/log/prod_qty_log.do'/>"> 상품 생산 기록 </a>
		                                    </li>
	                                        
	                                    </ul>
	                                </div>
	                            </li>
	                            
	                            <li class="nav-item">
	                                <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#calc_tax" aria-controls="calc_tax"> 면세과세  </a>
	                                <div id="calc_tax" class="collapse submenu" style="">
	                                    <ul class="nav flex-column">
	                                        <li class="nav-item">
		                                        <a class="nav-link" href="<c:url value='/tax/tax_table.do' />"> 계산하기 </a>
		                                    </li>
	                                    </ul>
	                                </div>
	                            </li>
                            </sec:authorize>
                            
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- end left sidebar -->
        <!-- ============================================================== -->