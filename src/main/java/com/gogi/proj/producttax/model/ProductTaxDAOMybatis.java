package com.gogi.proj.producttax.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.producttax.vo.ProductInfoVO;
import com.gogi.proj.producttax.vo.ResCompanyVO;
import com.gogi.proj.producttax.vo.TaxTableVO;

@Repository
public class ProductTaxDAOMybatis extends SqlSessionDaoSupport implements ProductTaxDAO{

	private String resCompanyNameSpace = "tax.res_company";
	private String productInfoNameSpace = "tax.product_info";
	private String taxTable = "tax.tax_table";
	
	@Override
	public int insertResCompany(ResCompanyVO rcVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(resCompanyNameSpace+".insertResCompany", rcVO);
	}
	
	@Override
	public List<ResCompanyVO> selectRecCompany(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(resCompanyNameSpace+".selectRecCompany", osVO);
	}
	
	@Override
	public ResCompanyVO selectRecCompanyByRcPk(ResCompanyVO rcVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(resCompanyNameSpace+".selectRecCompanyByRcPk", rcVO);
	}
	
	@Override
	public int selectRecCompanyCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(resCompanyNameSpace+".selectRecCompanyCounting", osVO);
	}

	@Override
	public int insertProductInfo(ProductInfoVO piVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(productInfoNameSpace+".insertProductInfo", piVO);
	}

	@Override
	public List<ProductInfoVO> selectProductInfoList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(productInfoNameSpace+".selectProductInfoList", osVO);
	}

	@Override
	public int selectProductInfoListCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(productInfoNameSpace+".selectProductInfoListCounting", osVO);
	}

	@Override
	public ProductInfoVO selectProductInfoByPiPk(ProductInfoVO piVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(productInfoNameSpace+".selectProductInfoByPiPk", piVO);
	}

	@Override
	public int updateTaxbilFlag(ProductInfoVO piVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(productInfoNameSpace+".updateTaxbilFlag", piVO);
	}

	@Override
	public int updateAccFlag(ProductInfoVO piVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(productInfoNameSpace+".updateAccFlag", piVO);
	}

	@Override
	public int updateProductInfo(ProductInfoVO piVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(productInfoNameSpace+".updateProductInfo", piVO);
	}

	@Override
	public int deleteProductInfo(ProductInfoVO piVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(productInfoNameSpace+".deleteProductInfo", piVO);
	}

	@Override
	public int updateResCompany(ResCompanyVO rcVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(resCompanyNameSpace+".updateResCompany", rcVO);
	}

	@Override
	public int deleteResCompany(ResCompanyVO rcVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(resCompanyNameSpace+".deleteResCompany", rcVO);
	}

	@Override
	public int insertTaxTableData(TaxTableVO ttVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(taxTable+".insertTaxTableData", ttVO);
	}

	@Override
	public List<TaxTableVO> taxZero() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(taxTable+".taxZero");
	}

	@Override
	public List<TaxTableVO> dutyZero() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(taxTable+".dutyZero");
	}

	@Override
	public List<TaxTableVO> taxTableCount() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(taxTable+".taxTableCount");
	}

	@Override
	public int deleteTaxTable() {
		// TODO Auto-generated method stub
		return getSqlSession().delete(taxTable+".deleteTaxTable");
	}

	@Override
	public int[] insertTaxTableDataBatch(List<TaxTableVO> ttList) {
		// TODO Auto-generated method stub
		int [] result = null;
		
		int [] suc = new int[3];
		
		Connection con = null;
        PreparedStatement pstmt = null ;
        
        String batchSql = ""
        	+"INSERT INTO tax_table( "
    			+"tt_date, "
    			+"tt_order_number, "
    			+"tt_product_order_number, "
    			+"tt_product, "
    			+"tt_total_price, "
    			+"tt_tax_price, "
    			+"tt_duty_free_price, "
    			+"tt_credit_price, "
    			+"tt_cash_deduction_price, "
    			+"tt_cash_receipt_price, "
    			+"tt_another_price, "
    			+"tt_tax_type, "
    			+"tt_tax_stat "
    			+") "
    		+"SELECT " 
    			+"?, "
    			+"?, "
    			+"?, "
    			+"?, "
    			+"?, "
    			+"?, "
    			+"?, "
    			+"?, "
    			+"?, "
    			+"?, "
    			+"?, "
    			+"?, "
    			+"? "
    		+"FROM dual " 
    		+"WHERE NOT EXISTS( "
    			+"SELECT " 
    				+"tt_pk "
    			+"FROM " 
    				+"tax_table " 
    			+"WHERE " 
    				+"tt_order_number = ? "
    				+"AND tt_product_order_number = ? " 
    				+"AND tt_tax_type = ? "
    				+"AND tt_tax_stat = ? "
    			+") ";
		
        try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://192.168.0.66:3306/3bgogi_test_schema?serverTimezone=UTC&autoReconnection=true", "3bgogis", "1234");
			
			pstmt = con.prepareStatement(batchSql);
			con.setAutoCommit(false);
			
			int listSize = ttList.size();
			
			for( int i = 0; i < listSize; i++) {
				
				pstmt.setString(1, ttList.get(i).getTtDate());
				pstmt.setString(2, ttList.get(i).getTtOrderNumber());
				pstmt.setString(3, ttList.get(i).getTtProductOrderNumber());
				pstmt.setString(4, ttList.get(i).getTtProduct());
				pstmt.setInt(5, ttList.get(i).getTtTotalPrice());
				pstmt.setInt(6, ttList.get(i).getTtTaxPrice());
				pstmt.setInt(7, ttList.get(i).getTtDutyFreePrice());
				pstmt.setInt(8, ttList.get(i).getTtCreditPrice());
				pstmt.setInt(9, ttList.get(i).getTtCashDeductionPrice());
				pstmt.setInt(10, ttList.get(i).getTtCashReceiptPrice());
				pstmt.setInt(11, ttList.get(i).getTtAnotherPrice());
				pstmt.setString(12, ttList.get(i).getTtTaxType());
				pstmt.setString(13, ttList.get(i).getTtTaxStat());
				
				pstmt.setString(14, ttList.get(i).getTtOrderNumber());
				pstmt.setString(15, ttList.get(i).getTtProductOrderNumber());
				pstmt.setString(16, ttList.get(i).getTtTaxType());
				pstmt.setString(17, ttList.get(i).getTtTaxStat());
				
				pstmt.addBatch();
				pstmt.clearParameters();
				
				if(i % 3000 == 0) {
					
					result = pstmt.executeBatch();
					
					
					pstmt.clearBatch();
					
					con.commit();
					
					for(int j = 0; j < result.length; j++) {
						if(result[j] != 0) {
							suc[0]++;
						}else {
							suc[1]++;
						}
						suc[2]++;
					}
				}
			}
			
			result = pstmt.executeBatch() ;
            con.commit() ;
            con.setAutoCommit(true);
            
            for(int j = 0; j < result.length; j++) {
				if(result[j] != 0) {
					suc[0]++;
				}else {
					suc[1]++;
				}
				suc[2]++;
			}
            
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            
            try {
                con.rollback() ;
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
		}finally{
            if (pstmt != null) try {pstmt.close();pstmt = null;} catch(SQLException ex){}
            
            if (con != null) try {con.close();con = null;} catch(SQLException ex){}
            
        }
        
		return suc;
	}
	
	
}
