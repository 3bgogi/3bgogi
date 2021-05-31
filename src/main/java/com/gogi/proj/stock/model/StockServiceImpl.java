package com.gogi.proj.stock.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.log.model.LogService;
import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.matching.vo.OptionMatchingVO;
import com.gogi.proj.matching.vo.ProductMatchingVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.paging.PaginationInfo;
import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.product.cost.vo.CostIoOrderVO;
import com.gogi.proj.product.cost.vo.CostIoVO;
import com.gogi.proj.product.cost.vo.CostsVO;
import com.gogi.proj.product.options.vo.OptionsCostsMatchingVO;
import com.gogi.proj.product.options.vo.OptionsVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;
import com.gogi.proj.security.AdminServiceImpl;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.stock.vo.PrintDataSetVO;
import com.gogi.proj.stock.vo.ProductInputListVO;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockDAO stockDao;
	
	@Autowired
	private AdminServiceImpl adminService;
	
	@Autowired
	private LogService logService;
	/**
	 * 재고 할당 기능 !!!!!!
	 * 
	 * 당분간은 재고 처리를 마이너스 형태로 처리 및 원육쪽도 마이너스로 차감될 수 있게 설정
	 * 추후 재고 파악 및 이력번호 처리가 완료되면 제대로 차감할 예정 2020-05-12
	 */
	@Override
	@Transactional
	public void stockChecking(OrderSearchVO osVO, String ip, String adminId) {
		
		List<OrdersVO> testOr = stockDao.selectUpdateCostIoTarget(osVO);
		
		Timestamp today = new Timestamp(new java.util.Date().getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String thisTime = sdf.format(today);
		
		ProductMatchingVO pmVO = null;
		OptionMatchingVO omVO = null;
		OptionsVO opVO = null;
		OptionsCostsMatchingVO ocmVO = null;
		CostsVO costVO = null;
		CostDetailVO cdVO = null;
		CostIoVO ciVO = null;
		OrderHistoryVO ohVO = null;
		
		//옵션 개수 확인용
		int optionCounting = 0;
		int optionCountingSuc = 0;
		
		int costDetailCounting = 0;
		int costDetailCountingSuc = 0;
		
		int checkFail = 0;
		
		
		List<OptionsVO> successOptionStock = null;
		List<CostIoVO> successCostDetailList = null;
		List<CostIoVO> costDetailInitList = null;
		List<CostIoOrderVO> costIoOrderList = null;
		
		//주문서
		for(int i = 0; i < testOr.size(); i++) {
			successOptionStock = new ArrayList<OptionsVO>();
			successCostDetailList = new ArrayList<CostIoVO>();
			costDetailInitList = new ArrayList<CostIoVO>();
			costIoOrderList = new ArrayList<CostIoOrderVO>();
			
			//상품명 매칭
			for(int j = 0; j < testOr.get(i).getProductMatchingVOList().size(); j++) {
				pmVO = testOr.get(i).getProductMatchingVOList().get(j);
				
				//옵션명 매칭
				for(int q = 0; q < pmVO.getOptionMatchingVOList().size(); q++) {
					omVO = pmVO.getOptionMatchingVOList().get(q);

					if(omVO.getOptionsVOList().size() != 0) {
						
						//옵션
						for(int w = 0; w < omVO.getOptionsVOList().size(); w++) {
							opVO = omVO.getOptionsVOList().get(w);
							
							//옵션 내에서 재고 처리를 할 경우
							if(opVO.getOptionCostFlag() == true) {
								
								optionCounting++; //옵션 개수
								
								int optionStocks = 0;
								int subtStocks = 0;
								
								//다른 재고 상품이랑 이어져 있을 경우
								if(opVO.getAnotherOptionPk() != 0) {
									opVO.setOptionPk(opVO.getAnotherOptionPk());
									optionStocks = stockDao.selectOptionStockCheck(opVO).getOptionStock();
									subtStocks = omVO.getOmOptionAmount() * testOr.get(i).getOrAmount() * opVO.getAnotherOptionQty();
								}else {
									optionStocks = stockDao.selectOptionStockCheck(opVO).getOptionStock();
									subtStocks = omVO.getOmOptionAmount() * testOr.get(i).getOrAmount();
								}
								
								/*옵션 재고 처리 시작하기*/
								 
								
								if(optionStocks >= subtStocks) {
									opVO.setOptionStock(subtStocks);
									successOptionStock.add(opVO);
									optionCountingSuc++;
									
								}else {
									checkFail++;
									continue;
									
								}
							
								//옵션 내가 아닌 원재료에 접근하여 재고 처리를 할 경우
							}else {
								
								//옵션, 원가 매칭
								for(int ii = 0; ii < opVO.getOptionCostsMatchingVOList().size(); ii++) {
									ocmVO = opVO.getOptionCostsMatchingVOList().get(ii);
									
									//원가 사항
									for(int jj = 0; jj < ocmVO.getCostsVOList().size(); jj++) {
										costVO = ocmVO.getCostsVOList().get(jj);
										
										//원가 상세사항
										for(int qq = 0; qq < costVO.getCostDetailList().size(); qq++) {
											cdVO = costVO.getCostDetailList().get(qq);
											
											List<CostIoVO> stockList = stockDao.selectCostIoStockChecking(cdVO);

											if(stockList.size() != 0) {
												ciVO = stockList.get(0);
												costDetailCounting++;
												
												CostIoOrderVO cioVO = new CostIoOrderVO();

												
												ciVO.setCiOutputWeight(cdVO.getCdCost());
												ciVO.setCiOutputQty(omVO.getOmOptionAmount() * testOr.get(i).getOrAmount());
												successCostDetailList.add(ciVO);
												
												cioVO.setCiFk(ciVO.getCiPk());
												cioVO.setOrFk(testOr.get(i).getOrPk());
												cioVO.setCioQty(omVO.getOmOptionAmount() * testOr.get(i).getOrAmount());
												costIoOrderList.add(cioVO);
												costDetailCountingSuc++;
												
												/*int costIoTotalWeight = 0;
												
												//보유 중인 원육 총 무게 합산하기
												for(CostIoVO civ :stockList) {
													costIoTotalWeight+= (civ.getCiWeight() - civ.getCiOutputWeight());
												}
												
												//보유 중인 원육의 무게가 적다면 멈춤
												if( costIoTotalWeight < cdVO.getCdCost()) {
													checkFail++;
													break;
												}
												
												
												// 원육 전부 차감되었을 경우 로그만 감기고 사용 불가로 만들기
												//costDetailInitList.add(stockList.get(stockCounting));
												int overWeight = 0;
												int rowWeight = 0;
												int divInt = 0;
												int divWeight = 0;
												
												boolean outputFlag = false;

												CostIoOrderVO cioVO = null;
												
												//원육 무게 합산하기
												int addCostTotalWeight = 0;
												
												costBreak : for(int stockCounting = 0; stockCounting < stockList.size(); stockCounting++) {
													ciVO = stockList.get(stockCounting);
													costDetailCounting++; //원가 개수 최대치	
													
													addCostTotalWeight+=(ciVO.getCiWeight() - ciVO.getCiOutputWeight());
													
													//출고할 수 있는 원재료가 있을 경우  ** cdVO.getCdCost()는 임의로 무게를 가져왔음  database - dao형식과 맞지 않음 cdcost는 원칙적으로 원가임 **
													if(addCostTotalWeight > cdVO.getCdCost()) {
														cioVO = new CostIoOrderVO();

														
														ciVO.setCiOutputWeight(cdVO.getCdCost());
														ciVO.setCiOutputQty(omVO.getOmOptionAmount() * testOr.get(i).getOrAmount());
														outputFlag = true;
														successCostDetailList.add(ciVO);
														
														cioVO.setCiFk(ciVO.getCiPk());
														cioVO.setOrFk(testOr.get(i).getOrPk());
														cioVO.setCioQty(omVO.getOmOptionAmount() * testOr.get(i).getOrAmount());
														costIoOrderList.add(cioVO);
														costDetailCountingSuc++;
														
														break;
													//원재료가 출고 불가할 정도로 적을 경우
													}else{

														//출고 원육이 적을 경우 전부 차감으로 돌림
														ciVO.setCiOutputWeight(cdVO.getCdCost());
														ciVO.setCiOutputQty(0);
														successCostDetailList.add(ciVO);
														costDetailInitList.add(stockList.get(stockCounting));
														costDetailCountingSuc++;

													}
													
												}
												overWeight=0;
												
												if(outputFlag == true) {
													
												}*/
												
											}else {
												checkFail++;
												continue;
												
											}
											
										}//원가 상세사항
										
									}//원가 사항
									
								}//옵션, 원가 매칭
								
							}//else 원재료 접근 이 부분 살려줘야함					
							
							// 재고 할당 이 부분*********************************************************************************
							
							
						}// 옵션
					}else {
						
					}//else
					
				}//옵션명 매칭
				
			}// 상품명 매칭
			
			// 밑에  costDetailCounting == costDetailCountingSuc && 추가하기 ****** 재고 할당 이 부분*********************************************************************************
			if(costDetailCounting == costDetailCountingSuc && optionCounting == optionCountingSuc &&  checkFail == 0) {
				
				
				
				if(successOptionStock.size() > 0 ) {
					for(int optionInt = 0; optionInt < successOptionStock.size(); optionInt++) {
						stockDao.updateOptionStockSubtract(successOptionStock.get(optionInt));
					}
					
				}
				
				if(successCostDetailList.size() > 0 ) {
					for(int costDetailInt = 0; costDetailInt < successCostDetailList.size(); costDetailInt++) {
						successCostDetailList.get(costDetailInt).setCiOutputLastTime(today);
						stockDao.updateCostIoStockSubtract(successCostDetailList.get(costDetailInt));
					}
					
				}
				
				if(successOptionStock.size() > 0 || successCostDetailList.size() > 0) {
					int result = stockDao.updateOrderStockComplete(testOr.get(i));
					
					ohVO = new OrderHistoryVO();
					ohVO.setOhAdmin(adminId);
					ohVO.setOhIp(ip);
					ohVO.setOrFk(testOr.get(i).getOrPk());
					ohVO.setOhEndPoint("재고");
					ohVO.setOhRegdate(thisTime);
					ohVO.setOhDetail("상품 재고 할당 완료");
					
					logService.insertOrderHistory(ohVO);
					
					if(result > 0) {
					}
				}
				
				if(costDetailInitList.size() > 0) {
					
					for(int costDetailSoldOutInt = 0; costDetailSoldOutInt < costDetailInitList.size(); costDetailSoldOutInt++) {						
						int result = stockDao.updateCostIoStockSoldout(costDetailInitList.get(costDetailSoldOutInt));
						
						if(result > 0) {
						}
					}
					
				}
				
				if(costIoOrderList.size() > 0 ) {
					for(int costIoOrderInt = 0; costIoOrderInt < costIoOrderList.size(); costIoOrderInt++) {
						int result = stockDao.insertCio(costIoOrderList.get(costIoOrderInt));

					}
				}
				
				successOptionStock = null;
				successCostDetailList = null;
				costDetailInitList =  null;
				costIoOrderList = null;
				
				
				
			}else {

			}
			
			optionCounting = 0;
			optionCountingSuc = 0;
			costDetailCounting = 0;
			costDetailCountingSuc = 0;
			checkFail = 0;

		}// 주문서
		
	}


	@Override
	public int[] stockSearchList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		int [] totalResult = new int[3]; 
		
		//출고 가능 주문서 개수
		int outputPosOrderCouning = stockDao.outputPosOrderCouning(osVO);
				
		//예약 건 개수
		int outputReservOrderCounting = stockDao.outputReservOrderCounting(osVO);
		
		// 재고 미할당 개수
		int notOutputOrderCounting = stockDao.notOutputOrderCounting(osVO);
		
		totalResult[0] = outputPosOrderCouning;
		totalResult[1] = outputReservOrderCounting;
		totalResult[2] = notOutputOrderCounting;
		
		return totalResult;
	}


	@Override
	public List<OrdersVO> searchOutputListByOutputType(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return stockDao.searchOutputListByOutputType(osVO);
	}


	@Override
	public List<Map<String, String>> selectStockResult(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return stockDao.selectStockResult(osVO);
	}


	@Override
	public List<PrintDataSetVO> selectProductLabel(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return stockDao.selectProductLabel(osVO);
	}


	@Override
	@Transactional
	public boolean updateProductChangeValues(OrdersVO pramOrVO) {
		// TODO Auto-generated method stub
		//주문서 결과값 가져오기
		OrdersVO orVO = stockDao.selectStockChangeOrderByOrPk(pramOrVO);
		int orPk = orVO.getOrPk();
		boolean orInvFlag = orVO.getOrInvFlag();
		
		boolean result = false;
		//재고할당이 되지 않은 상태라면

		if(orInvFlag == false)  return true;
		
		//아무 매칭이 되지 않은 상태라면
		if(orVO.getProductMatchingVOList().size() == 0) return true;
		
		for(int i = 0; i < orVO.getProductMatchingVOList().size(); i++) {
			
			// 옵션매칭이 이뤄지지 않은 상태라면
			if(orVO.getProductMatchingVOList().get(i).getOptionMatchingVOList().size() == 0) return true;
			
			for(int j = 0; j < orVO.getProductMatchingVOList().get(i).getOptionMatchingVOList().size(); j++) {
				OptionMatchingVO omVO = orVO.getProductMatchingVOList().get(i).getOptionMatchingVOList().get(j);
				int ocmEach = omVO.getOmOptionAmount();
				
				for(int ii = 0; ii < omVO.getOptionsVOList().size(); ii++) {
					OptionsVO opVO = omVO.getOptionsVOList().get(ii);
					
					// 원가 매칭을 사용하지 않은 옵션을 경우
					if(opVO.getOptionStockFlag() == true) {
						
						//다른 상품의 재고를 사용한다면
						if(opVO.getAnotherOptionPk() != 0) {
							opVO.setOptionPk(opVO.getAnotherOptionPk());
							opVO.setOptionStock(opVO.getAnotherOptionQty() * orVO.getOrAmount() * ocmEach);
							stockDao.addOptionStock(opVO);
							stockDao.changeOrderInvFlag(orVO);
							result = true;
						}else {
							opVO.setOptionStock(orVO.getOrAmount() * ocmEach);
							stockDao.addOptionStock(opVO);
							stockDao.changeOrderInvFlag(orVO);
							result = true;
						}

					// 원육 입출고 데이터 삭제하기
					} else if(opVO.getOptionCostFlag() == false && opVO.getOptionStockFlag() == false){
						stockDao.deleteCostIoOrderByOrpk(orVO);
						stockDao.changeOrderInvFlag(orVO);
						result = true;
						
					}else {
						result = false;
					}
					
				}
			}
		
		}
		
		return result;
	}


	@Override
	public List<ProductOptionVO> productOptionStockAlarm() {
		// TODO Auto-generated method stub
		return stockDao.productOptionStockAlarm();
	}


	@Override
	public List<ProductOptionVO> selectOptionStockByNameOrBarcodeNum(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return stockDao.selectOptionStockByNameOrBarcodeNum(osVO);
	}


	@Override
	@Transactional
	public int insertProductInputList(AdminVO adminsVO, ProductInputListVO pilVO) {
		// TODO Auto-generated method stub
		
		int authCount = 0;
		
		int result = 0;
		
		AdminVO adminVO = adminService.selectAdminInfoByAdminPk(adminsVO.getAdminPk());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date today = new java.util.Date();
		
		String parseToday = sdf.format(today);
		
		pilVO.setPilReqDate(parseToday);
		pilVO.setPilAdmin(adminVO.getAdminId()+"("+adminVO.getAdminName()+")");
		pilVO.setPilAdminPk(adminVO.getAdminPk());
		pilVO.setPilPermFlag(false);
		
		//관리자 권한이 있는지 체크
		for(String role :adminsVO.getRoles()) {
			  if(role.equals("ROLE_ADMIN")) authCount++;
			  
		  }
		
		//관리자 권한이 있을 경우
		if(authCount > 0) {
			pilVO.setPilPermAdmin(adminVO.getAdminId()+"("+adminVO.getAdminName()+")");
			pilVO.setPilPermAdminPk(adminVO.getAdminPk());
			pilVO.setPilPermFlag(true);
			pilVO.setPilResDate(parseToday);
			
			result+= stockDao.insertProductInputList(pilVO);
			OptionsVO opVO = new OptionsVO();
			opVO.setOptionPk(pilVO.getOptionFk());
			opVO.setOptionStock(pilVO.getPilQty());
			result+=stockDao.addOptionStock(opVO);
			
		//관리자 권한이 없을 경우
		}else {
			result+= stockDao.insertProductInputList(pilVO);
		
		}
		
		return result;
	}

	@Override
	public List<ProductInputListVO> selectProductInputLists(PaginationInfo paging) {
		// TODO Auto-generated method stub
		return stockDao.selectProductInputLists(paging);
	}


	@Override
	public int selectProductInputListsCount(PaginationInfo paging) {
		// TODO Auto-generated method stub
		return stockDao.selectProductInputListsCount(paging);
	}


	@Override
	@Transactional
	public int updateProductInputList(AdminVO adminsVO, ProductInputListVO pilVO) {
		// TODO Auto-generated method stub
		
		int result = 0;
		
		boolean dupli = stockDao.selectProductInputListFlag(pilVO);
		
		if(dupli == true) {
			return 1;
		}
		
		AdminVO adminVO = adminService.selectAdminInfoByAdminPk(adminsVO.getAdminPk());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date today = new java.util.Date();
		
		String parseToday = sdf.format(today);
		
		pilVO.setPilPermAdmin(adminVO.getAdminId()+"("+adminVO.getAdminName()+")");
		pilVO.setPilPermAdminPk(adminVO.getAdminPk());
		pilVO.setPilPermFlag(true);
		pilVO.setPilResDate(parseToday);

		OptionsVO opVO = new OptionsVO();
		opVO.setOptionPk(pilVO.getOptionFk());
		opVO.setOptionStock(pilVO.getPilQty());
		
		result+=stockDao.addOptionStock(opVO);
		result+=stockDao.updateProductInputList(pilVO);
		
		return result;
	}


	@Override
	public List<ProductInputListVO> selectProductInputListLimitTen() {
		// TODO Auto-generated method stub
		return stockDao.selectProductInputListLimitTen();
	}


	@Override
	public int productInputDontPerm() {
		// TODO Auto-generated method stub
		return stockDao.productInputDontPerm();
	}


	@Override
	public int changeOrderInvFlag(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return stockDao.changeOrderInvFlag(orVO);
	}


	@Override
	public List<ProductOptionVO> checkOptionBarcodeDupli(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return stockDao.checkOptionBarcodeDupli(osVO);
	}
	
	
}
