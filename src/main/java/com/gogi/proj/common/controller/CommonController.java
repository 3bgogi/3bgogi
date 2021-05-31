package com.gogi.proj.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gogi.proj.classification.code.model.AllClassificationCodeService;
import com.gogi.proj.classification.code.vo.ClassificationVO;
import com.gogi.proj.product.products.model.ProductsService;
import com.gogi.proj.product.products.vo.ProductsVO;

@Controller
@RequestMapping(value="/common")
public class CommonController {

	
	@Autowired
	private AllClassificationCodeService accService;
	
	@Autowired
	private ProductsService productService;
	
	/**
	 * 
	 * @MethodName : findProductAndOption
	 * @date : 2020. 6. 22.
	 * @author : Jeon KiChan
	 * @param searchType
	 * @param model
	 * @return
	 * @메소드설명 : 상품을 찾아 값을 넘겨줄 수 있는 페이지로 이동
	 * 				기존의 상품의 경우 js가 너무 많아져서
	 * 				통합으로 사용할 수 있는 관련 페이지를 제작
	 * 				타입에 따라서 함수를 유동적으로 사용할 수 있도록 함
	 */
	@RequestMapping(value="/products.do", method=RequestMethod.GET)
	public String findProductAndOption(@RequestParam(defaultValue="0") int searchType, Model model) {
		
		List<ClassificationVO> classList = accService.selectClassificationList();
		
		model.addAttribute("classList", classList);
		model.addAttribute("searchType", searchType);
		
		return "common/product_option";
	}
	
	
	/**
	 * 
	 * @MethodName : findProductAndOptionBySearch
	 * @date : 2020. 6. 23.
	 * @author : Jeon KiChan
	 * @param proVO
	 * @return
	 * @메소드설명 : 분류코드와 상품명을 Ajax로 받아 리턴 
	 */
	@RequestMapping(value="/product_search.do", method=RequestMethod.GET)
	@ResponseBody
	public List<ProductsVO> findProductAndOptionBySearchAJAX(@ModelAttribute ProductsVO proVO){
		
		
		return productService.selectProductOptionsByCodeAndName(proVO);
	}
}
