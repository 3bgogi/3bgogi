package com.gogi.proj.product.products.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.proj.product.products.vo.ProductOptionVO;
import com.gogi.proj.product.products.vo.ProductsVO;

@Service
public class ProductsServiceImpl implements ProductsService{

	@Autowired
	private ProductsDAO productsDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductsServiceImpl.class);

	@Override
	public List<ProductsVO> selectProductList() {
		// TODO Auto-generated method stub
		return productsDao.selectProductList();
	}

	@Override
	public List<ProductsVO> selectProductListByCfFk(int cfFk) {
		// TODO Auto-generated method stub
		return productsDao.selectProductListByCfFk(cfFk);
	}

	@Override
	public int insertProducts(ProductsVO productVo) {
		// TODO Auto-generated method stub
		return productsDao.insertProducts(productVo);
	}

	@Override
	public int selectProductCountByCfFk(int cfFk) {
		// TODO Auto-generated method stub
		return productsDao.selectProductCountByCfFk(cfFk);
	}

	@Override
	public ProductsVO selectDetailProductWithOptionByProductPk(int productPk) {
		// TODO Auto-generated method stub
		return productsDao.selectDetailProductWithOptionByProductPk(productPk);
	}

	@Override
	public List<ProductsVO> selectProductOptionsByCodeAndName(ProductsVO productsVO) {
		// TODO Auto-generated method stub
		return productsDao.selectProductOptionsByCodeAndName(productsVO);
	}

	@Override
	public List<ProductOptionVO> selectProductOptionList() {
		// TODO Auto-generated method stub
		return productsDao.selectProductOptionList();
	}

	@Override
	public int updateProducts(ProductsVO proVO) {
		// TODO Auto-generated method stub
		return productsDao.updateProducts(proVO);
	}
}
