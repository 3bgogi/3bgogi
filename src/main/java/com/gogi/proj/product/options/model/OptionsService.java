package com.gogi.proj.product.options.model;

import java.util.List;

import com.gogi.proj.product.cost.vo.CostsVO;
import com.gogi.proj.product.options.vo.OptionsCostsMatchingVO;
import com.gogi.proj.product.options.vo.OptionsVO;

public interface OptionsService {
	
	public int insertOptions(OptionsVO optionVO);

	public OptionsVO selectOptionsByOptionPk(int optionPk);
	
	public int deleteOptions(OptionsVO optionVO);
	
	public String selectDeleteOCMData(OptionsCostsMatchingVO ocmVO);
	
	public int updateOptions(OptionsVO optionVO);
}
