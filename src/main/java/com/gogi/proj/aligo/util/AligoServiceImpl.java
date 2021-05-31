package com.gogi.proj.aligo.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AligoServiceImpl implements AligoService{

	private static final Logger logger = LoggerFactory.getLogger(AligoServiceImpl.class);
	
	@Autowired
	private AligoDAO aligoDao;

	@Override
	public int insertAligoSendingForm(AligoSendingFormVO asfVO) {
		// TODO Auto-generated method stub
		return aligoDao.insertAligoSendingForm(asfVO);
	}

	@Override
	public List<AligoSendingFormVO> selectAligoSendingFormList() {
		// TODO Auto-generated method stub
		return aligoDao.selectAligoSendingFormList();
	}

	@Override
	public AligoSendingFormVO selectAligoSendingFormByAsfPk(AligoSendingFormVO asfVO) {
		// TODO Auto-generated method stub
		return aligoDao.selectAligoSendingFormByAsfPk(asfVO);
	}

	@Override
	public int deleteAligoSendingFormByAsfPk(AligoSendingFormVO asfVO) {
		// TODO Auto-generated method stub
		return aligoDao.deleteAligoSendingFormByAsfPk(asfVO);
	}
	
}
