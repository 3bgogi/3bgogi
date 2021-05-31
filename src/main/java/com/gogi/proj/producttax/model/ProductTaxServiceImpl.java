package com.gogi.proj.producttax.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.producttax.vo.ProductInfoVO;
import com.gogi.proj.producttax.vo.ResCompanyVO;
import com.gogi.proj.producttax.vo.TaxTableVO;

@Service
public class ProductTaxServiceImpl implements ProductTaxService{

	@Autowired
	private ProductTaxDAO ptDao;

	@Override
	public int insertResCompany(ResCompanyVO rcVO) {
		// TODO Auto-generated method stub
		return ptDao.insertResCompany(rcVO);
	}

	@Override
	public List<ResCompanyVO> selectRecCompany(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return ptDao.selectRecCompany(osVO);
	}

	@Override
	public ResCompanyVO selectRecCompanyByRcPk(ResCompanyVO rcVO) {
		// TODO Auto-generated method stub
		return ptDao.selectRecCompanyByRcPk(rcVO);
	}

	@Override
	public int selectRecCompanyCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return ptDao.selectRecCompanyCounting(osVO);
	}

	@Override
	public int insertProductInfo(ProductInfoVO piVO) {
		// TODO Auto-generated method stub
		return ptDao.insertProductInfo(piVO);
	}

	@Override
	public List<ProductInfoVO> selectProductInfoList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return ptDao.selectProductInfoList(osVO);
	}

	@Override
	public int selectProductInfoListCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return ptDao.selectProductInfoListCounting(osVO);
	}

	@Override
	public ProductInfoVO selectProductInfoByPiPk(ProductInfoVO piVO) {
		// TODO Auto-generated method stub
		return ptDao.selectProductInfoByPiPk(piVO);
	}

	@Override
	public int updateTaxbilFlag(ProductInfoVO piVO) {
		// TODO Auto-generated method stub
		ProductInfoVO stat = ptDao.selectProductInfoByPiPk(piVO);
		
		boolean taxFlag = stat.isPiTaxbilCheckFlag();

		if(taxFlag == true) {
			stat.setPiTaxbilCheckFlag(false);
			
		}else {
			stat.setPiTaxbilCheckFlag(true);
			
		}
		
		return ptDao.updateTaxbilFlag(stat);
	}

	@Override
	public int updateAccFlag(ProductInfoVO piVO) {
		// TODO Auto-generated method stub
		ProductInfoVO stat = ptDao.selectProductInfoByPiPk(piVO);
		
		boolean accFlag = stat.isPiAccFlag();
		
		if(accFlag) {
			stat.setPiAccFlag(false);	
		}else {
			stat.setPiAccFlag(true);
		}
		
		return ptDao.updateAccFlag(stat);
	}

	@Override
	public int updateProductInfo(ProductInfoVO piVO) {
		// TODO Auto-generated method stub
		return ptDao.updateProductInfo(piVO);
	}

	@Override
	public int deleteProductInfo(ProductInfoVO piVO) {
		// TODO Auto-generated method stub
		return ptDao.deleteProductInfo(piVO);
	}

	@Override
	public int updateResCompany(ResCompanyVO rcVO) {
		// TODO Auto-generated method stub
		return ptDao.updateResCompany(rcVO);
	}

	@Override
	public int deleteResCompany(ResCompanyVO rcVO) {
		// TODO Auto-generated method stub
		return ptDao.deleteResCompany(rcVO);
	}

	@Override
	public int insertTaxTableData(TaxTableVO ttVO) {
		// TODO Auto-generated method stub
		return ptDao.insertTaxTableData(ttVO);
	}

	@Override
	public List<TaxTableVO> taxZero() {
		// TODO Auto-generated method stub
		return ptDao.taxZero();
	}

	@Override
	public List<TaxTableVO> dutyZero() {
		// TODO Auto-generated method stub
		return ptDao.dutyZero();
	}

	@Override
	public List<TaxTableVO> taxTableCount() {
		// TODO Auto-generated method stub
		return ptDao.taxTableCount();
	}

	@Override
	public int deleteTaxTable() {
		// TODO Auto-generated method stub
		return ptDao.deleteTaxTable();
	}

	@Override
	public int[] insertTaxTableDataBatch(List<TaxTableVO> ttList) {
		// TODO Auto-generated method stub
		return ptDao.insertTaxTableDataBatch(ttList);
	}
}
