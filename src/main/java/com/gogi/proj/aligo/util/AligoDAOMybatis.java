package com.gogi.proj.aligo.util;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class AligoDAOMybatis extends SqlSessionDaoSupport implements AligoDAO{

	private String namespace = "sms.aligo_sms";

	@Override
	public int insertAligoSendingForm(AligoSendingFormVO asfVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertAligoSendingForm", asfVO);
	}

	@Override
	public List<AligoSendingFormVO> selectAligoSendingFormList() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectAligoSendingFormList");
	}

	@Override
	public AligoSendingFormVO selectAligoSendingFormByAsfPk(AligoSendingFormVO asfVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectAligoSendingFormByAsfPk", asfVO);
	}

	@Override
	public int deleteAligoSendingFormByAsfPk(AligoSendingFormVO asfVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(namespace+".deleteAligoSendingFormByAsfPk", asfVO);
	}
}
