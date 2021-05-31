package com.gogi.proj.product.products.model;

import java.util.List;

import com.gogi.proj.product.products.vo.ProductOptionVO;
import com.gogi.proj.product.products.vo.ProductsVO;

public interface ProductsDAO {

	public List<ProductsVO> selectProductList();
	
	public List<ProductsVO> selectProductListByCfFk(int cfFk);
	
	public int insertProducts(ProductsVO productVo);
	
	public int selectProductCountByCfFk(int cfFk);
	
	public ProductsVO selectDetailProductWithOptionByProductPk(int productPk);
	
	public List<ProductsVO> selectProductOptionsByCodeAndName(ProductsVO productsVO);
	
	public List<ProductOptionVO> selectProductOptionList();
	
	/**
	 * 
	 * @MethodName : updateProducts
	 * @date : 2020. 11. 4.
	 * @author : Jeon KiChan
	 * @param proVO
	 * @return
	 * @메소드설명 : 상품 수정
	 */
	public int updateProducts(ProductsVO proVO);
}
