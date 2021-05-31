package com.gogi.proj.product.cost.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.classification.code.vo.CostCodeVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.paging.PaginationInfo;
import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.product.cost.vo.CostIoVO;
import com.gogi.proj.product.cost.vo.CostsVO;
import com.gogi.proj.product.cost_io.model.CostIoDAO;
import com.gogi.proj.product.costs.model.CostsDAO;
import com.gogi.proj.product.options.model.OptionsDAO;
import com.gogi.proj.product.options.vo.OptionsCostsMatchingListVO;
import com.gogi.proj.product.options.vo.OptionsCostsMatchingVO;
import com.gogi.proj.product.options.vo.OptionsVO;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.stock.vo.CarcassInputListVO;

@Service
public class CostDetailServiceImpl implements CostDetailService{

	@Autowired
	private CostDetailDAO costDetailDAO;
	
	@Autowired
	private OptionsDAO optionsDAO;
	
	@Autowired
	private CostsDAO costsDao;
	
	@Autowired
	private CostIoDAO costIoDao;
	
	@Transactional
	@Override
	public int selectCostsPkByCostNameAndInsertMatchingData(OptionsVO optionVO) {
		// TODO Auto-generated method stub
		int count = 0;
		List<CostsVO> costsVoList;
		OptionsCostsMatchingVO optionsCostsMatchingVO;
		
		if(optionVO.getOptionCostsMatchingVOList() == null) return count;
		
		for(int i = 0; i < optionVO.getOptionCostsMatchingVOList().size(); i++) {
			if(optionVO.getOptionCostsMatchingVOList().get(i).getOcmEach() != 0) {
				costsVoList = costDetailDAO.selectCostsPkByCostName(optionVO.getOptionCostsMatchingVOList().get(i));
				
				System.out.println("costVoList size = "+costsVoList.size());
				
				for( int j = 0; j < costsVoList.size(); j ++) {
					optionsCostsMatchingVO = new OptionsCostsMatchingVO();
					optionsCostsMatchingVO.setOptionFk(optionVO.getOptionPk());
					optionsCostsMatchingVO.setCostFk(costsVoList.get(j).getCostPk());
					optionsCostsMatchingVO.setOcmGramRecalFlag(optionVO.getOptionCostsMatchingVOList().get(i).getOcmGramRecalFlag());
					optionsCostsMatchingVO.setOcmProductionDivide(optionVO.getOptionCostsMatchingVOList().get(i).getOcmProductionDivide());
					optionsCostsMatchingVO.setOcmEach(optionVO.getOptionCostsMatchingVOList().get(i).getOcmEach());
					
					optionsDAO.insertOptionsCostsMatching(optionsCostsMatchingVO);
					
					count++;
				}
			}
		}
		
		return count;//costDetailDAO.selectCostsPkByCostName(optionsCostsMatchingListVO);
	}

	@Override
	public List<CostDetailVO> selectAllCostDetail(PaginationInfo info) {
		// TODO Auto-generated method stub
		return costDetailDAO.selectAllCostDetail(info);
	}

	@Override
	public int insertCostDetail(CostDetailVO costDetailVO) {
		// TODO Auto-generated method stub
		return costDetailDAO.insertCostDetail(costDetailVO);
	}

	@Override
	public int selectCostDetailCount(PaginationInfo info) {
		// TODO Auto-generated method stub
		return costDetailDAO.selectCostDetailCount(info);
	}

	@Override
	public List<CostDetailVO> selectCostDetailByCcpk(int ccPk) {
		// TODO Auto-generated method stub
		return costDetailDAO.selectCostDetailByCcpk(ccPk);
	}

	@Override
	public List<CostDetailVO> selectAllCostDetailJoinCostCodeList() {
		// TODO Auto-generated method stub
		return costDetailDAO.selectAllCostDetailJoinCostCodeList();
	}

	/*controller에서 원가 데이터를 modelattribute로 가져온 뒤
	costsvo안에 있는 costsvolist를 분해 후 넣기*/
	@Transactional
	@Override
	public int insertCostsData(CostsVO costsVO) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatTime = sdf.format(new Date());
		
		int result = 0;
		
		for(int i = 0; i< costsVO.getCostsVOList().size(); i++) {
			
			if(costsVO.getCostsVOList().get(i).getCdFk() != 0) {
				costsVO.getCostsVOList().get(i).setCostUniqueNum(formatTime);
				costsVO.getCostsVOList().get(i).setCostName(costsVO.getCostName());
				
				//결과값 처리
				result += costDetailDAO.insertCostsData(costsVO.getCostsVOList().get(i));

			}
		}
		
		return result;
	}

	@Override
	public int countingCostsGroupByCostName() {
		// TODO Auto-generated method stub
		return costDetailDAO.countingCostsGroupByCostName();
	}

	@Override
	public CostDetailVO selectCostDetailByCcfk(CostDetailVO cdVO) {
		// TODO Auto-generated method stub
		return costDetailDAO.selectCostDetailByCcfk(cdVO);
	}

	@Override
	public List<CostDetailVO> selectCostdetailWightCostcodeByCcPk(CostCodeVO ccVO) {
		// TODO Auto-generated method stub
		return costDetailDAO.selectCostdetailWightCostcodeByCcPk(ccVO);
	}

	@Transactional
	@Override
	public int insertCarcassAndCostIo(AdminVO adminVO, CarcassInputListVO cilVO) {
		// TODO Auto-generated method stub
		if(cilVO.getCostIoList().size() == 0) return 0;
		cilVO.setCilAdmin(adminVO.getUsername());
		cilVO.setCilAdminPk(adminVO.getAdminPk());
		
		int result = costDetailDAO.insertCarcass(cilVO);
		
		for(int i = 0; i < cilVO.getCostIoList().size(); i++) {
			if(cilVO.getCostIoList().get(i).getCdFk() != 0) {		
				cilVO.getCostIoList().get(i).setCilFk(cilVO.getCilPk());
				result+=costIoDao.insertCostInputData(cilVO.getCostIoList().get(i));
			}
			
		}
		
		return result;
	}

	@Override
	public List<CarcassInputListVO> selectCarcassInputList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return costDetailDAO.selectCarcassInputList(osVO);
	}

	@Override
	public CarcassInputListVO selectCarcassInputListDetail(CarcassInputListVO cilVO) {
		// TODO Auto-generated method stub
		return costDetailDAO.selectCarcassInputListDetail(cilVO);
	}

	@Override
	public int updateCarcassInputList(CarcassInputListVO cilVO) {
		// TODO Auto-generated method stub
		return costDetailDAO.updateCarcassInputList(cilVO);
	}

	@Override
	public int deleteCarcassInputList(CarcassInputListVO cilVO) {
		// TODO Auto-generated method stub
		return costDetailDAO.deleteCarcassInputList(cilVO);
	}

	@Override
	public List<CostIoVO> selectCostIoHistory(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return costDetailDAO.selectCostIoHistory(osVO);
	}

	@Override
	public int selectCostIoHistoryCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return costDetailDAO.selectCostIoHistoryCounting(osVO);
	}

	@Override
	public int selectCarcassInputListCount(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return costDetailDAO.selectCarcassInputListCount(osVO);
	}

	@Override
	public List<CostDetailVO> selectCarcassCostManage(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		int totalRecord = costDetailDAO.selectCarcassCostManageCounting(osVO);
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		
		osVO.setTotalRecord(totalRecord);
		
		return costDetailDAO.selectCarcassCostManage(osVO);
	}

	@Override
	public List<CostDetailVO> selsectCarcassCostCategoryCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return costDetailDAO.selsectCarcassCostCategoryCounting(osVO);
	}

}
