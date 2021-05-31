package com.gogi.proj.orders.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.configurations.vo.StoreMergeVO;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.orders.vo.AdminOrderRecordVO;
import com.gogi.proj.orders.vo.IrregularOrderVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;

@Repository
public class OrdersDAOMybatis extends SqlSessionDaoSupport implements OrdersDAO{

	private String orderExcelNameSpace = "order.excel";
	private String irregularNameSpace = "order.irregular";
	private String orderCsNameSpace = "order.cs";
	private String searchNameSpace = "order.search_customer_order_info";
	private String adminOrderRecordNameSpace = "order.order_record";

	@Override
	public int insertOrderData(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(orderExcelNameSpace+".insertOrderData",ordersVO);
	}

	@Override
	public List<OrdersVO> selectTotalOrderInToday() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderExcelNameSpace+".selectTotalOrderInToday");
	}

	@Override
	public int deleteOrders(int orPk) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(orderExcelNameSpace+".deleteOrders", orPk);
	}

	@Override
	public List<OrdersVO> selectOrderByOrOrderNumber(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderExcelNameSpace+".selectOrderByOrOrderNumber", ordersVO);
	}

	@Override
	public List<OrdersVO> selectNotMatchingedOrders(OrderSearchVO orderSearchVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderExcelNameSpace+".selectNotMatchingedOrders", orderSearchVO);
	}

	@Override
	public int countingNotMatchingedOrders(OrderSearchVO orderSearchVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderExcelNameSpace+".countingNotMatchingedOrders", orderSearchVO);
	}

	
	/*고객 필터링 부분*/
	@Override
	public int addIrregularOrders(IrregularOrderVO iroVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(irregularNameSpace+".addIrregularOrders", iroVO);
	}

	@Override
	public List<IrregularOrderVO> selectIrregularOrdersNotFiltered() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(irregularNameSpace+".selectIrregularOrdersNotFiltered");
	}

	@Override
	public List<IrregularOrderVO> selectIrregularOrders() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(irregularNameSpace+".selectIrregularOrders");
	}

	@Override
	public int successedFiltering(IrregularOrderVO iroVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(irregularNameSpace+".successedFiltering", iroVO);
	}

	@Override
	public int updateFilteringData(IrregularOrderVO iroVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(irregularNameSpace+".updateFilteringData", iroVO);
	}

	@Override
	public int deleteFilteringData(IrregularOrderVO iroVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(irregularNameSpace+".deleteFilteringData", iroVO);
	}
	/*고객 필터링 끝*/

	/*묶음정리 기능 관련 시작*/
	@Override
	public List<OrdersVO> selectNotMergedOrders(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderExcelNameSpace+".selectNotMergedOrders", ssVO);
	}

	@Override
	public int grantOrSerialSpecialNumber(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderExcelNameSpace+".grantOrSerialSpecialNumber", ordersVO);
	}

	@Override
	public List<OrdersVO> selectOrdersByOrderNumber(String orderNumber) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderExcelNameSpace+".selectOrdersByOrderNumber", orderNumber);
	}

	@Override
	public List<OrdersVO> searchCustomerOrderInfo(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderCsNameSpace+".searchCustomerOrderInfo", osVO);
	}

	@Override
	public int searchCustomerOrderInfoCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderCsNameSpace+".searchCustomerOrderInfoCounting", osVO);
	}

	@Override
	public List<OrdersVO> selectCustomerOrderProductInfoDetail(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderCsNameSpace+".selectCustomerOrderProductInfoDetail", orVO);
	}

	@Override
	public List<OrdersVO> selectOrdersPkByOrSerialSpecialNumber(String orSerialSpecialNumber) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderCsNameSpace+".selectOrdersPkByOrSerialSpecialNumber", orSerialSpecialNumber);
	}

	@Override
	public int deleteOrdersByOrPk(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(orderCsNameSpace+".deleteOrdersByOrPk", orVO);
	}
	
	/*묶음정리 기능 관련 끝*/
	
	@Override
	public int updateOrderDeliveryInvoiceNumber(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		
		return getSqlSession().update(orderExcelNameSpace+".updateOrderDeliveryInvoiceNumber", ordersVO);
	}

	@Override
	public OrdersVO selectOrdersByPk(int orPk) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderCsNameSpace+".selectOrdersByPk", orPk);
	}

	@Override
	public int grantOrSerialSpecialNumberByOrPk(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderExcelNameSpace+".grantOrSerialSpecialNumberByOrPk", ordersVO);
	}

	@Override
	public List<OrdersVO> selectNotMatchingedCostData() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderExcelNameSpace+".selectNotMatchingedCostData");
	}

	@Override
	public int updateOrderCostsData(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderExcelNameSpace+".updateOrderCostsData", ordersVO);
	}

	@Override
	public int insertDevideOrderData(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(orderExcelNameSpace+".insertDevideOrderData", ordersVO);
	}

	@Override
	public int updateDevideOrderData(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderExcelNameSpace+".updateDevideOrderData", ordersVO);
	}

	@Override
	public List<OrdersVO> selectCombineInfoBySerialSpecialNumber(List<String> orSerialSpecialNumber) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderCsNameSpace+".selectCombineInfoBySerialSpecialNumber", orSerialSpecialNumber);
	}

	@Override
	public int updateCombineOrders(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".updateCombineOrders", orVO);
	}

	@Override
	public int changeProductAndOptionByOrPk(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".changeProductAndOptionByOrPk", orVO);
	}

	@Override
	public OrdersVO selectOnlyOneOrdersAllInfoBySerialNumber(String orSerialSpecialNumber) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderCsNameSpace+".selectOnlyOneOrdersAllInfoBySerialNumber", orSerialSpecialNumber);
	}

	@Override
	public int insertAddOrderData(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(orderExcelNameSpace+".insertAddOrderData", orVO);
	}

	@Override
	public List<OrdersVO> selectedOrderExcelByOrderSerachVO(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderExcelNameSpace+".selectedOrderExcelByOrderSerachVO", osVO);
	}

	@Override
	public List<OrdersVO> selectOrdersCountingByInputDate() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderExcelNameSpace+".selectOrdersCountingByInputDate");
	}

	@Override
	public int deleteOrdersByDate(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(orderExcelNameSpace+".deleteOrdersByDate", ordersVO);
	}

	@Override
	public int outputCancledBySerialNumber(OrdersVOList orVOList) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".outputCancledBySerialNumber", orVOList);
	}

	@Override
	public int changeSendingDeadline(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".changeSendingDeadline", osVO);
	}

	@Override
	public int writeDevideOrderFlag(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderExcelNameSpace+".writeDevideOrderFlag", orVO);
	}

	@Override
	public int updateOutputDateBySerialNumber(OrdersVOList orVOList) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".updateOutputDateBySerialNumber", orVOList);
	}

	@Override
	public List<OrdersVOList> selectedOrderExcelByOrderSerachVOForVegit(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderExcelNameSpace+".selectedOrderExcelByOrderSerachVOForVegit", osVO);
	}

	@Override
	public List<ProductOptionVO> selectOrderInHowManyProducts(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderCsNameSpace+".selectOrderInHowManyProducts", orVO);
	}

	@Override
	public OrdersVO searchRefundOrder(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderCsNameSpace+".searchRefundOrder", orVO);
	}

	@Override
	public int orderRefundsEdit(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".orderRefundsEdit", orVO);
	}

	@Override
	public int addCustomerSpecialRequest(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".addCustomerSpecialRequest", orVO);
	}

	@Override
	public OrdersVO selectCustomerSpecialRequest(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderCsNameSpace+".selectCustomerSpecialRequest", orVO);
	}

	@Override
	public List<OrdersVO> selectDeliveryMsg(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderCsNameSpace+".selectDeliveryMsg", osVO);
	}

	@Override
	public int editDelivNum(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".editDelivNum", orVO);
	}

	@Override
	public OrdersVO selectOrderQtyByPk(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderCsNameSpace+".selectOrderQtyByPk", orVO);
	}

	@Override
	public int updateMultiMatchingProductOriginalOrder(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".updateMultiMatchingProductOriginalOrder", orVO);
	}

	@Override
	public List<ProductOptionVO> selectOrdersMatchingProductByOrPk(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderCsNameSpace+".selectOrdersMatchingProductByOrPk", orVO);
	}

	@Override
	public int updateExcelDivOrders(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".updateExcelDivOrders", orVO);
	}

	@Override
	public List<OrdersVO> newSearchCustomerOrderInfo(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(searchNameSpace+".newSearchCustomerOrderInfo", osVO);
	}

	@Override
	public int newSearchCustomerOrderInfoCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(searchNameSpace+".newSearchCustomerOrderInfoCounting", osVO);
	}

	@Override
	public List<OrdersVO> newSearchCustomerOrderInfoToExcelFile(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(searchNameSpace+".newSearchCustomerOrderInfoToExcelFile", osVO);
	}

	@Override
	public List<OrdersVO> selectCreateInvoiceNum() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderCsNameSpace+".selectCreateInvoiceNum");
	}

	@Override
	public OrdersVO selectBuyerAddrInfo(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderCsNameSpace+".selectBuyerAddrInfo", orVO);
	}

	@Override
	public int checkDepositOrder(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".checkDepositOrder", orVO);
	}

	@Override
	public int receiverPickUp(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".receiverPickUp", orVO);
	}

	@Override
	public int deleteExcelGiftOrderByOrFk(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(orderExcelNameSpace+".deleteExcelGiftOrderByOrFk", orVO);
	}

	@Override
	public int insertAdminOrderRecord(AdminOrderRecordVO aorVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(adminOrderRecordNameSpace+".insertAdminOrderRecord", aorVO);
	}

	@Override
	public List<AdminOrderRecordVO> searchAdminOrderRecordBySerialSpecialNumber(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(adminOrderRecordNameSpace+".searchAdminOrderRecordBySerialSpecialNumber", orVO);
	}

	@Override
	public int[] insertOrderDataBatch(List<OrdersVO> orList) {
		// TODO Auto-generated method stub
		int [] result = null;
		
		int [] suc = new int[3];
		
		Connection con = null;
        PreparedStatement pstmt = null ;
        
        String batchSql = ""
        		+"INSERT INTO orders(or_buyer_id, or_buyer_name, or_buyer_another_name, or_buyer_contract_number1, or_buyer_contract_number2, or_receiver_name, or_product, or_product_type, or_product_option, "
						+"or_amount, or_delivery_message, or_delivery_type, or_delivery_company, or_order_number, or_product_order_number, or_product_number, or_payment_position_type, " 
						+"or_product_price, or_product_option_price, or_discount_price, or_total_price,  or_check_day, or_sending_deadline, or_delivery_charge_type, or_delivery_number, "
						+"or_delivery_price, or_delivery_discount_price, or_receiver_contract_number1, or_receiver_contract_number2, or_shipping_address_number, or_shipping_province, or_shipping_address, "
						+"or_shipping_address_detail, or_sending_address, or_payment_type, or_payment_commision, or_another_payment_commision, or_inflow_route, or_tax_flag, or_regdate, or_settlement_day, ss_fk, "
						+"or_delivery_invoice_number, or_delivery_charge_payment_type, or_delivery_special_price, or_user_column1, or_user_column4, or_fk, or_deposit_flag, edt_fk) "
	+"SELECT ?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+"?, ?, ?, ?, ?, ?, ?, ?, " 
			+"?, ?, ?, ?, ?, ?, ?, ?, "
			+"?, ?, ?, ?, ?, ?, ?, "
			+"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
			+"?, ?, ?, ?, ?, ?, ?, ? "
	+"FROM dual "
	+"WHERE NOT EXISTS( "
		+"SELECT " 
			+"or_pk " 
		+"FROM " 
			+"orders " 
		+"WHERE " 
			+"or_product_order_number = ? "
			+"AND ss_fk = ? "
			+"AND or_order_number = ? "
			+"AND or_sending_deadline >= DATE_ADD(NOW(), INTERVAL -2 MONTH) "
        +") ";
		
        try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://192.168.0.66:3306/3bgogi_test_schema?serverTimezone=UTC&autoReconnection=true", "3bgogis", "1234");
			
			pstmt = con.prepareStatement(batchSql);
			con.setAutoCommit(false);
			
			int listSize = orList.size();
			
			for( int i = 0; i < listSize; i++) {
				
				pstmt.setString(1, orList.get(i).getOrBuyerId());
				pstmt.setString(2, orList.get(i).getOrBuyerName());
				pstmt.setString(3, orList.get(i).getOrBuyerAnotherName());
				pstmt.setString(4, orList.get(i).getOrBuyerContractNumber1());
				pstmt.setString(5, orList.get(i).getOrBuyerContractNumber2());
				pstmt.setString(6, orList.get(i).getOrReceiverName());
				pstmt.setString(7, orList.get(i).getOrProduct());
				pstmt.setString(8, orList.get(i).getOrProductType());
				pstmt.setString(9, orList.get(i).getOrProductOption());
				pstmt.setInt(10, orList.get(i).getOrAmount());
				pstmt.setString(11, orList.get(i).getOrDeliveryMessage());
				pstmt.setString(12, orList.get(i).getOrDeliveryType());
				pstmt.setString(13, orList.get(i).getOrDeliveryCompany());
				pstmt.setString(14, orList.get(i).getOrOrderNumber());
				pstmt.setString(15, orList.get(i).getOrProductOrderNumber());
				pstmt.setString(16, orList.get(i).getOrProductNumber());
				pstmt.setString(17, orList.get(i).getOrPaymentPositionType());
				pstmt.setInt(18, orList.get(i).getOrProductPrice());
				pstmt.setInt(19, orList.get(i).getOrProductOptionPrice());
				pstmt.setInt(20, orList.get(i).getOrDiscountPrice());
				pstmt.setInt(21, orList.get(i).getOrTotalPrice());
				pstmt.setDate(22, orList.get(i).getOrCheckDay());
				pstmt.setDate(23, orList.get(i).getOrSendingDeadline());
				pstmt.setString(24, orList.get(i).getOrDeliveryChargeType());
				pstmt.setString(25, orList.get(i).getOrDeliveryNumber());
				pstmt.setInt(26, orList.get(i).getOrDeliveryPrice());
				pstmt.setInt(27, orList.get(i).getOrDeliveryDiscountPrice());
				pstmt.setString(28, orList.get(i).getOrReceiverContractNumber1());
				pstmt.setString(29, orList.get(i).getOrReceiverContractNumber2());
				pstmt.setString(30, orList.get(i).getOrShippingAddressNumber());
				pstmt.setString(31, orList.get(i).getOrShippingProvince());
				pstmt.setString(32, orList.get(i).getOrShippingAddress());
				pstmt.setString(33, orList.get(i).getOrShippingAddressDetail());
				pstmt.setString(34, orList.get(i).getOrSendingAddress());
				pstmt.setString(35, orList.get(i).getOrPaymentType());
				pstmt.setInt(36, orList.get(i).getOrPaymentCommision());
				pstmt.setInt(37, orList.get(i).getOrAnotherPaymentCommision());
				pstmt.setString(38, orList.get(i).getOrInflowRoute());
				pstmt.setBoolean(39, orList.get(i).isOrTaxFlag());
				pstmt.setTimestamp(40, orList.get(i).getOrRegdate());
				pstmt.setTimestamp(41, orList.get(i).getOrSettlementDay());
				pstmt.setInt(42, orList.get(i).getSsFk());
				pstmt.setString(43, orList.get(i).getOrDeliveryInvoiceNumber());
				pstmt.setString(44, orList.get(i).getOrDeliveryChargePaymentType());
				pstmt.setInt(45, orList.get(i).getOrDeliverySpecialPrice());
				pstmt.setString(46, orList.get(i).getOrUserColumn1());
				pstmt.setString(47, orList.get(i).getOrUserColumn4());
				pstmt.setInt(48, orList.get(i).getOrFk());
				pstmt.setBoolean(49, orList.get(i).isOrDepositFlag());
				pstmt.setInt(50, orList.get(i).getEdtFk());
				
				pstmt.setString(51, orList.get(i).getOrProductOrderNumber());
				pstmt.setInt(52, orList.get(i).getSsFk());
				pstmt.setString(53, orList.get(i).getOrOrderNumber());
				
				
				pstmt.addBatch();
				pstmt.clearParameters();
				
				if(i % 3000 == 0) {
					
					result = pstmt.executeBatch();
					
					
					pstmt.clearBatch();
					
					con.commit();
					
					for(int j = 0; j < result.length; j++) {
						if(result[j] == 0) {
							suc[1]++;
						}else {
							
							suc[0]++;
						}
					}
				}
			}
			
			result = pstmt.executeBatch() ;
            con.commit() ;
            con.setAutoCommit(true);
            
            for(int j = 0; j < result.length; j++) {
				if(result[j] == 0) {
					suc[1]++;
				}else {
					
					suc[0]++;
				}
			}
            
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            
            try {
                con.rollback() ;
                con.setAutoCommit(true);
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
 