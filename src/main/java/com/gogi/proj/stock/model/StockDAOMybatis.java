package com.gogi.proj.stock.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.paging.PaginationInfo;
import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.product.cost.vo.CostIoOrderVO;
import com.gogi.proj.product.cost.vo.CostIoVO;
import com.gogi.proj.product.options.vo.OptionsVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;
import com.gogi.proj.stock.vo.PrintDataSetVO;
import com.gogi.proj.stock.vo.ProductInputListVO;

@Repository
public class StockDAOMybatis extends SqlSessionDaoSupport implements StockDAO {

	private String namespace = "order.stock";
	private String productInputNameSpace = "order.product_input_list";

	@Override
	public List<OrdersVO> selectUpdateCostIoTarget(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectUpdateCostIoTarget", osVO);
	}

	@Override
	public int updateOrderStockComplete(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateOrderStockComplete", orVO);
	}

	@Override
	public OptionsVO selectOptionStockCheck(OptionsVO opVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectOptionStockCheck", opVO);
	}

	@Override
	public int updateOptionStockSubtract(OptionsVO opVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateOptionStockSubtract", opVO);
	}

	@Override
	public List<CostIoVO> selectCostIoStockChecking(CostDetailVO cdVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectCostIoStockChecking", cdVO);
	}

	@Override
	public int updateCostIoStockSubtract(CostIoVO ciVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateCostIoStockSubtract", ciVO);
	}

	@Override
	public int updateCostIoStockSoldout(CostIoVO ciVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateCostIoStockSoldout", ciVO);
	}

	@Override
	public int updateCostIoQtyInit() {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateCostIoQtyInit");
	}

	@Override
	public int insertCio(CostIoOrderVO cioVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertCio", cioVO);
	}

	@Override
	public int outputPosOrderCouning(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".outputPosOrderCouning", osVO);
	}

	@Override
	public int outputReservOrderCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".outputReservOrderCounting", osVO);
	}

	@Override
	public int notOutputOrderCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".notOutputOrderCounting", osVO);
	}

	@Override
	public List<OrdersVO> searchOutputListByOutputType(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".searchOutputListByOutputType", osVO);
	}

	@Override
	public List<Map<String, String>> selectStockResult(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectStockResult", osVO);
	}

	@Override
	public List<PrintDataSetVO> selectProductLabel(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectProductLabel", osVO);
	}

	@Override
	public int addOptionStock(OptionsVO opVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".addOptionStock", opVO);
	}

	@Override
	public int deleteCostIoOrderByOrpk(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(namespace+".deleteCostIoOrderByOrpk", orVO);
	}

	@Override
	public int changeOrderInvFlag(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".changeOrderInvFlag", orVO);
	}

	@Override
	public OrdersVO selectStockChangeOrderByOrPk(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectStockChangeOrderByOrPk", orVO);
	}

	@Override
	public List<ProductOptionVO> productOptionStockAlarm() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".productOptionStockAlarm");
	}

	@Override
	public List<ProductOptionVO> selectOptionStockByNameOrBarcodeNum(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectOptionStockByNameOrBarcodeNum", osVO);
	}

	@Override
	public int insertProductInputList(ProductInputListVO pilVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(productInputNameSpace+".insertProductInputList", pilVO);
	}

	@Override
	public int updateProductInputList(ProductInputListVO pilVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(productInputNameSpace+".updateProductInputList", pilVO);
	}

	@Override
	public List<ProductInputListVO> selectProductInputLists(PaginationInfo paging) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(productInputNameSpace+".selectProductInputLists", paging);
	}

	@Override
	public int selectProductInputListsCount(PaginationInfo paging) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(productInputNameSpace+".selectProductInputListsCount", paging);
	}

	@Override
	public boolean selectProductInputListFlag(ProductInputListVO pilVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(productInputNameSpace+".selectProductInputListFlag", pilVO);
	}

	@Override
	public List<ProductInputListVO> selectProductInputListLimitTen() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(productInputNameSpace+".selectProductInputListLimitTen");
	}

	@Override
	public int productInputDontPerm() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(productInputNameSpace+".productInputDontPerm");
	}

	@Override
	public List<ProductOptionVO> checkOptionBarcodeDupli(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".checkOptionBarcodeDupli", osVO);
	}
}
