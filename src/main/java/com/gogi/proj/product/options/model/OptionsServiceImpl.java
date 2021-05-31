package com.gogi.proj.product.options.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.gogi.proj.product.cost.model.CostDetailDAO;
import com.gogi.proj.product.cost.vo.CostsVO;
import com.gogi.proj.product.options.vo.OptionsCostsMatchingVO;
import com.gogi.proj.product.options.vo.OptionsVO;

@Service
public class OptionsServiceImpl implements OptionsService{

	private static final Logger logger = LoggerFactory.getLogger(OptionsServiceImpl.class);
	
	@Autowired
	private OptionsDAO optionDao;
	
	@Autowired
	private CostDetailDAO costDetailDAO;
	
	@Autowired
	PlatformTransactionManager transaction;

	@Override
	public int insertOptions(OptionsVO optionVO) {
		// TODO Auto-generated method stub
		return optionDao.insertOptions(optionVO);
	}

	@Override
	public OptionsVO selectOptionsByOptionPk(int optionPk) {
		// TODO Auto-generated method stub
		return optionDao.selectOptionsByOptionPk(optionPk);
	}

	@Override
	public int deleteOptions(OptionsVO optionVO) {
		// TODO Auto-generated method stub
		return optionDao.deleteOptions(optionVO);
	}

	@Override
	public String selectDeleteOCMData(OptionsCostsMatchingVO ocmVO) {
		// TODO Auto-generated method stubsz 
		TransactionStatus status = this.transaction.getTransaction(new DefaultTransactionDefinition());
		
		List<OptionsCostsMatchingVO> ocmList = optionDao.selectDeleteOCMData(ocmVO);
		
		int totalData = ocmList.size();
		int result = 0;
		
		String resultMsg = "";
		String errorMsg = "";
		try {
			
			for(int i=0; i < ocmList.size(); i++) {
				result += optionDao.deleteOCMData(ocmList.get(i));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			errorMsg = e.getMessage();
		}
		
		
		if(totalData == result) {
			resultMsg = "삭제 완료";
			this.transaction.commit(status);
		}else {
			resultMsg = "삭제 실패 error = "+errorMsg;
			this.transaction.rollback(status);
		}
		
		return resultMsg;
	}

	@Override
	@Transactional
	public int updateOptions(OptionsVO optionVO) {
		// TODO Auto-generated method stub
		int result = optionDao.updateOptions(optionVO);
		
		List<CostsVO> costsVoList;
		OptionsCostsMatchingVO optionsCostsMatchingVO;
		
		if(optionVO.getOptionCostsMatchingVOList() == null) return result;
		
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
					
					optionDao.insertOptionsCostsMatching(optionsCostsMatchingVO);
					
					result++;
				}
			}
		}
		
		return result; 
	}

}
