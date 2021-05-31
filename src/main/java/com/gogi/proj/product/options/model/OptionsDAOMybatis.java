package com.gogi.proj.product.options.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.product.options.vo.OptionsCostsMatchingVO;
import com.gogi.proj.product.options.vo.OptionsVO;

@Repository
public class OptionsDAOMybatis extends SqlSessionDaoSupport implements OptionsDAO{

	private String namespace = "products.option";
	
	private String optionsCostsMatchingNameSpace = "products.options_costs_matching";
	
	@Override
	public int insertOptions(OptionsVO optionVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertOptions",optionVO);
	}

	@Override
	public int insertOptionsCostsMatching(OptionsCostsMatchingVO optionsCostsMatchingVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(optionsCostsMatchingNameSpace+".insertOptionsCostsMatching",optionsCostsMatchingVO);
	}

	@Override
	public OptionsVO selectOptionsByOptionPk(int optionPk) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectOptionsByOptionPk", optionPk);
	}

	@Override
	public int deleteOptions(OptionsVO optionVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(namespace+".deleteOptions", optionVO);
	}

	@Override
	public List<OptionsCostsMatchingVO> selectDeleteOCMData(OptionsCostsMatchingVO ocmVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(optionsCostsMatchingNameSpace+".selectDeleteOCMData", ocmVO);
	}

	@Override
	public int deleteOCMData(OptionsCostsMatchingVO ocmVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(optionsCostsMatchingNameSpace+".deleteOCMData", ocmVO);
	}

	@Override
	public int updateOptions(OptionsVO optionVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateOptions", optionVO);
	}
	
}
