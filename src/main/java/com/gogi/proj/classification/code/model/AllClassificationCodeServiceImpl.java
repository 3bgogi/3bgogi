package com.gogi.proj.classification.code.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.classification.code.vo.ClassificationVO;
import com.gogi.proj.classification.code.vo.CostCodeVO;
import com.gogi.proj.classification.code.vo.ExcelOrderSeqVO;
import com.gogi.proj.classification.code.vo.PackingCombineCodeVO;

@Service
public class AllClassificationCodeServiceImpl implements AllClassificationCodeService{

	@Autowired
	private AllClassificationCodeDAO accDao;
	
	//원가 카테고리 리스트 가져오기
	@Override
	public List<CostCodeVO> selectCostCodeList() {
		// TODO Auto-generated method stub
		return accDao.selectCostCodeList();
	}
	
	//상품 카테고리 리스트 가져오기
	@Override
	public List<ClassificationVO> selectClassificationList() {
		// TODO Auto-generated method stub
		return accDao.selectClassificationList();
	}

	@Override
	public List<ExcelOrderSeqVO> selectExcelOrderSeqCodeList() {
		// TODO Auto-generated method stub
		return accDao.selectExcelOrderSeqCodeList();
	}

	@Override
	public List<PackingCombineCodeVO> selectPackingCombineCodeList() {
		// TODO Auto-generated method stub
		return accDao.selectPackingCombineCodeList();
	}

	@Override
	public List<ClassificationVO> selectCfListOrderbyCfPk() {
		// TODO Auto-generated method stub
		return accDao.selectCfListOrderbyCfPk();
	}

	@Override
	@Transactional
	public int updateExcelOrderSeq(ExcelOrderSeqVO eosVO) {
		// TODO Auto-generated method stub
		int result = 0;
		
		for(int i = 0; i < eosVO.getEosList().size(); i++) {
			result += accDao.updateExcelOrderSeq(eosVO.getEosList().get(i));
			
		}
		
		return result;
	}

	@Override
	public int insertExcelOrderSeq(ExcelOrderSeqVO eosVO) {
		// TODO Auto-generated method stub
		return accDao.insertExcelOrderSeq(eosVO);
	}

	@Override
	@Transactional
	public int updateClassificationCode(ClassificationVO cfVO) {
		// TODO Auto-generated method stub
		int result = 0;
		
		for(ClassificationVO cf : cfVO.getCfList()) {
			result += accDao.updateClassificationCode(cf);
		}
		
		return result;
	}

	@Override
	public int insertClassificationCode(ClassificationVO cfVO) {
		// TODO Auto-generated method stub
		return accDao.insertClassificationCode(cfVO);
	}

	@Override
	public int updateCostCode(CostCodeVO ccVO) {
		// TODO Auto-generated method stub
		int result = 0;
		
		for(CostCodeVO cc : ccVO.getCcList()) {
			result += accDao.updateCostCode(ccVO);
		}
		
		return result;
	}

	@Override
	public int insertCostCode(CostCodeVO ccVO) {
		// TODO Auto-generated method stub
		return accDao.insertCostCode(ccVO);
	}
	
	
}
