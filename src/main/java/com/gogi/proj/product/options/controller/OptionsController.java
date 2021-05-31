package com.gogi.proj.product.options.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gogi.proj.classification.code.model.AllClassificationCodeService;
import com.gogi.proj.classification.code.vo.ExcelOrderSeqVO;
import com.gogi.proj.classification.code.vo.PackingCombineCodeVO;
import com.gogi.proj.product.cost.model.CostDetailService;
import com.gogi.proj.product.cost.vo.CostsVO;
import com.gogi.proj.product.costs.model.CostsService;
import com.gogi.proj.product.options.model.OptionsService;
import com.gogi.proj.product.options.vo.OptionsCostsMatchingListVO;
import com.gogi.proj.product.options.vo.OptionsCostsMatchingVO;
import com.gogi.proj.product.options.vo.OptionsVO;
import com.gogi.proj.product.products.model.ProductsService;
import com.gogi.proj.product.products.vo.ProductOptionVO;
import com.gogi.proj.product.products.vo.ProductsVO;

@Controller
@RequestMapping("/options")
public class OptionsController {

	private static final Logger logger = LoggerFactory.getLogger(OptionsController.class);
	
	@Autowired
	private OptionsService optionService;
	
	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private CostsService costsService; // 원가 등
	
	@Autowired
	private AllClassificationCodeService accService; //분류 코드 등
	
	@Autowired
	private CostDetailService costDetailService;
	
	/**
	 * @MethodName : insertOptionsGet
	 * @date : 2019. 2. 25.
	 * @author : Jeon KiChan
	 * @메소드설명 : 데이터 관리/데이터입력/상품/옵션 등록 입력 :: 옵션 등록 페이지
	 * @param : 상품과 연결 할 PK 가져옴
	 */
	@RequestMapping(value="/insert/option.do",method=RequestMethod.GET)
	public String insertOptionsGet(@RequestParam int productPk, Model model ) {
		logger.info("insert option page");
		
		List<ExcelOrderSeqVO> exceloderseqVoList = accService.selectExcelOrderSeqCodeList();
		List<PackingCombineCodeVO> packingcombinecodeVoList = accService.selectPackingCombineCodeList();
		List<CostsVO> costsVoList = costsService.selectCostsGroupBYTotalPriceResult();
		ProductsVO productVO = productsService.selectDetailProductWithOptionByProductPk(productPk);
		
		List<ProductOptionVO> productList = productsService.selectProductOptionList();
		
		model.addAttribute("productVO", productVO);
		model.addAttribute("exceloderseqVoList", exceloderseqVoList);
		model.addAttribute("packingcombinecodeVoList", packingcombinecodeVoList);
		model.addAttribute("costsVoList", costsVoList);
		model.addAttribute("productList", productList);
		
		return "product/insert/options";
	}
	
	@RequestMapping(value="/read/option.do", method=RequestMethod.GET)
	public String readOptionGet(@RequestParam int optionPk, Model model) {
		
		
		
		OptionsVO optionVO = optionService.selectOptionsByOptionPk(optionPk);
		logger.info("optionVO = {}", optionVO.toString());
		List<ExcelOrderSeqVO> exceloderseqVoList = accService.selectExcelOrderSeqCodeList();
		List<PackingCombineCodeVO> packingcombinecodeVoList = accService.selectPackingCombineCodeList();
		List<CostsVO> costsVoList = costsService.selectCostsGroupBYTotalPriceResult();
		List<ProductOptionVO> productList = productsService.selectProductOptionList();
		
		ProductsVO productVO = productsService.selectDetailProductWithOptionByProductPk(optionVO.getProductFk());
		
		model.addAttribute("productVO", productVO);
		model.addAttribute("exceloderseqVoList", exceloderseqVoList);
		model.addAttribute("packingcombinecodeVoList", packingcombinecodeVoList);
		model.addAttribute("costsVoList", costsVoList);
		model.addAttribute("optionVO", optionVO);
		model.addAttribute("productList", productList);
		
		return "product/read/options";
	}
	
	/**
	 * @MethodName : costsVoListAjax
	 * @date : 2019. 2. 25.
	 * @author : Jeon KiChan
	 * @메소드설명 : 옵션 입력창에서 원가에 대한 추가 버튼을 누를 경우 ajax형태로 원가리스트 값을 가져옴
	 */
	@RequestMapping(value="/insert/costsVoListAjax.do", method=RequestMethod.GET)
	public @ResponseBody List<CostsVO> costsVoListAjax(){
		
		return costsService.selectCostsGroupBYTotalPriceResult();
	}
	
	/**
	 * @MethodName : insertOptionsPost
	 * @date : 2019. 5. 29.
	 * @author : Jeon KiChan
	 * @메소드설명 : 데이터 관리/데이터입력/상품/옵션 등록 입력 :: 등록하기 페이지, 트랜잭션 처리로 오류 시 롤백할 수 있도록 설정
	 * @param : 옵션과 원가를 매칭시킬 값을 가져와서 insert
	 */
	@RequestMapping(value="/insert/option.do", method=RequestMethod.POST)
	@Transactional
	public String insertOptionsPost(@ModelAttribute OptionsVO optionVO, Model model){
		
		String msg = "";
		String url = "/products/read/product.do?productPk="+optionVO.getProductFk();
		
		logger.info("option_result = {}", optionVO.toString());
		
		int optionPk = optionService.insertOptions(optionVO);
		
		System.out.println("optionPk = "+optionPk);
		System.out.println("optionVO = Pk =>"+optionVO.getOptionPk());
		
		int result = costDetailService.selectCostsPkByCostNameAndInsertMatchingData(optionVO);
		
		
		if(result == 0) {
			msg = "옵션 등록 실패";
			
		}else{
			msg = "옵션 등록 성공";
		}
		
		model.addAttribute("url", url);
		model.addAttribute("msg", msg);
		
		return "common/message";
	}
	
	
	@RequestMapping(value="/delete/option.do", method=RequestMethod.GET)
	public String deleteOptionGet(@ModelAttribute OptionsVO optionVO, Model model) {
		
		String msg = "옵션 삭제 완료.";
		String url = "/products/read/product.do?productPk="+optionVO.getProductFk();
		
		int result = optionService.deleteOptions(optionVO);
		
		if(result > 0) {
			
		}else {
			msg = "옵션 삭제 실패";
		}
		
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return "common/message";
	}
	
	@RequestMapping(value="/delete/option_cost_matching.do", method=RequestMethod.GET)
	public String deleteOptionCostMatchingGet(@ModelAttribute OptionsCostsMatchingVO ocmVO, Model model) {

		String msg = optionService.selectDeleteOCMData(ocmVO);
		String url = "/options/read/option.do?optionPk="+ocmVO.getOptionFk();
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping(value="/update/option.do", method=RequestMethod.POST)
	public String updateOptions(@ModelAttribute OptionsVO optionVO, Model model) {
		
		String msg = "";
		String url = "/options/read/option.do?optionPk="+optionVO.getOptionPk();
		
		int result = optionService.updateOptions(optionVO);
		
		if(result > 0) {
			msg = "옵션 수정 완료";
		}else {
			msg = "옵션 수정 실패";
		}
		
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return "common/message";
	}
}
