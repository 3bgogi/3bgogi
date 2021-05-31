package com.gogi.proj.product.options.model;

import java.util.List;

import com.gogi.proj.product.options.vo.OptionsCostsMatchingVO;
import com.gogi.proj.product.options.vo.OptionsVO;

public interface OptionsDAO {

	public int insertOptions(OptionsVO optionVO);
	
	public int insertOptionsCostsMatching(OptionsCostsMatchingVO optionsCostsMatchingVO);
	
	public OptionsVO selectOptionsByOptionPk(int optionPk);
	
	public int deleteOptions(OptionsVO optionVO);
	
	public List<OptionsCostsMatchingVO> selectDeleteOCMData(OptionsCostsMatchingVO ocmVO);
	
	public int deleteOCMData(OptionsCostsMatchingVO ocmVO);
	
	public int updateOptions(OptionsVO optionVO);
	
}
