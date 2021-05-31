package com.gogi.proj.product.products.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/products")
public class ProductsController {

	private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);
	
	@Autowired
	private AllClassificationCodeService accService;
	
	@Autowired
	private ProductsService productsService;
	
	/**
	 * @MethodName : insertProductsGet
	 * @date : 2019. 2. 19.
	 * @author : Jeon KiChan
	 * @메소드설명 : 데이터 관리/데이터입력/상품등록 입력 페이지 들어가기 :: 상품 등록 페이지
	 */
	@RequestMapping(value="/insert/product.do",method=RequestMethod.GET)
	public String insertProductsGet(Model model) {
		logger.info("insert products page");
		
		List<ClassificationVO> cfList = accService.selectClassificationList();
		
		model.addAttribute("cfList", cfList);
		
		return "product/insert/products";
	}
	
	/**
	 * @MethodName : readProductsGet
	 * @date : 2019. 2. 21.
	 * @author : Jeon KiChan
	 * @메소드설명 : 데이터 관리/데이터목록/상품목록/상품 보기  페이지 들어가기 :: 상품 보기 페이지
	 */
	@RequestMapping(value="/read/product.do",method=RequestMethod.GET)
	public String readProductsGet(@RequestParam int productPk, Model model) {
		logger.info("read Product, parameter productPk = {}",productPk);
		
		List<ClassificationVO> cfList = accService.selectClassificationList();
		
		ProductsVO productsVo = productsService.selectDetailProductWithOptionByProductPk(productPk);
		
		model.addAttribute("cfList", cfList);
		model.addAttribute("productsVo", productsVo);
		
		return "product/read/products";
	}
	
	
	/**
	 * 
	 * @MethodName : readProductsPost
	 * @date : 2020. 11. 4.
	 * @author : Jeon KiChan
	 * @param proVO
	 * @param model
	 * @return
	 * @메소드설명 : 상품 수정하기
	 */
	@RequestMapping(value="/read/product.do",method=RequestMethod.POST)
	public String readProductsPost(@ModelAttribute ProductsVO proVO, Model model) {
		
		String msg = "";
		String url = "/products/read/product.do?productPk="+proVO.getProductPk();
		
		int result = productsService.updateProducts(proVO);
		
		if( result > 0) {
			msg = "상품 수정 완료";
		}else {
			msg = "상품 수정 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	/**
	 * @MethodName : insertProductsPost
	 * @date : 2019. 2. 20.
	 * @author : Jeon KiChan
	 * @메소드설명 : 데이터 관리/데이터입력/상품등록 입력 :: 상품 등록하기
	 */
	@RequestMapping(value="/insert/product.do",method=RequestMethod.POST)
	public String insertProductsPost(@ModelAttribute ProductsVO productVo, Model model) {
		logger.info("insert products page");
		logger.info("parameter type productVo = {}", productVo);
		
		String msg = "";
		String url = "/products/list/product_list.do";
		
		int productPk = productsService.insertProducts(productVo);
		
		if(productPk > 0) {
			
			msg = "상품 등록 완료";
			
		}else {
			
			msg = "상품 등록 실패. 관리자 문의 혹은 시스템 문제";
			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url",url);
		
		return "common/message";
	}
	
	/**
	 * @MethodName : productsListGet
	 * @date : 2019. 2. 20.
	 * @author : Jeon KiChan
	 * @메소드설명 : 데이터 관리/데이터 목록/상품 목록 페이지 들어가기 :: 상품 전체 목록 보기
	 */
	@RequestMapping(value="/list/product_list.do",method=RequestMethod.GET)
	public String productsListGet(Model model) {
		logger.info("products list page");
		
		List<ClassificationVO> cfList = accService.selectClassificationList();
		List<ProductsVO> productList = productsService.selectProductList();
		
		model.addAttribute("cfList", cfList);
		model.addAttribute("productList", productList);
		
		return "product/list/product_list";
	}
	
	/**
	 * @MethodName : productsListByCfFkAjax
	 * @date : 2019. 2. 20.
	 * @author : Jeon KiChan
	 * @메소드설명 : 데이터 관리/데이터 목록/상품 목록 :: 상품 목록 페이지에서 카테고리를 누를 시 해당 상품 리스트 가져오기
	 */
	@RequestMapping(value="/list/product_list_by_cffk.do", method=RequestMethod.POST)
	public @ResponseBody List<ProductsVO>  productsListByCfFkAjax(@RequestParam int cfFk){
		
		return productsService.selectProductListByCfFk(cfFk);
	}
	
	/**
	 * @MethodName : productsListByCfFkAjax
	 * @date : 2019. 2. 20.
	 * @author : Jeon KiChan
	 * @메소드설명 : 데이터 관리/데이터 목록/상품 목록 :: 상품 목록 페이지에서 카테고리를 누를 시 해당 상품의 개수 가져오기
	 */
	@RequestMapping(value="/list/product_list_count_by_cffk.do", method=RequestMethod.GET)
	public @ResponseBody int  productsListCountByCfFkAjax(@RequestParam int cfFk){
		
		return productsService.selectProductCountByCfFk(cfFk);
	}
}
