package com.gogi.proj.orders.autocomplete;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.gogi.proj.orders.controller.OrdersController;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;

public class Godomall {

	private static final Logger logger = LoggerFactory.getLogger(Godomall.class);
	
	private String partnerKey = "JUEwJUVBSSVFNyUyQXElODElQUI=";
	private String key = "JTI1JTFGJTdGJUMxJUQ0JTFDJThFUyUwMlIlN0ZyJUJDJUM0JUYzJUY1JTVCJUYzJUVDVCU1QkElREElOER6JTI2JUYwJTFCOSUxRCU3QyU3RSVBNyUxQiU5NCVGRiVGM0clMDFI";
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private Document doc;
	
	public Godomall() {
		// TODO Auto-generated constructor stub
		dbFactory = DocumentBuilderFactory.newInstance();
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("객체 생성 오류");
		}
	}
	
	/**
	 * 
	 * @param tag
	 * @param eElement
	 * @return 
	 * @메소드설명 xml 태그 값 가져오기
	 */
	private String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		
		if (nValue == null)
			return null;
		
		return nValue.getNodeValue();
	}
	
	public String godomallAutoSend(List<OrdersVO> orderList) {
		String result = "";
		String orderStatus = "d1";

		/*List<OrdersVO> list = new ArrayList<>();
		
		OrdersVO orVO = new OrdersVO();
		orVO.setOrOrderNumber("2101081009000001");
		orVO.setOrProductOrderNumber("107|108|109");
		orVO.setOrDeliveryCompany("프레시솔루션");
		list.add(orVO);*/

		int orderSize = orderList.size();
		
		for(int p = 0; p < orderSize; p++) {
			int delivCompanyNum = GodomallInvoiceCompNum.getCompanyNumber(orderList.get(p).getOrDeliveryCompany());
			
			String urlParameters = "partner_key=" + partnerKey + "&key=" + key + "&orderNo="+orderList.get(p).getOrOrderNumber()
									+"&sno="+orderList.get(p).getOrProductOrderNumber()+"&orderStatus="+orderStatus
									+"&invoiceCompanySno="+delivCompanyNum+"&invoiceNo="+orderList.get(p).getOrDeliveryInvoiceNumber();

			String url = "https://openhub.godo.co.kr/godomall5/order/Order_Status.php?";
			
			try {
				doc = dBuilder.parse(url + urlParameters);
				
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("XML 변환 오류");
				
			}
			
			NodeList successItems = doc.getElementsByTagName("statusData");
			
			
			//return
			for (int i = 0; i < successItems.getLength(); i++) {
				Node node = successItems.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {				
					Element element = (Element) node;

					result +="주문 번호 = "+getTagValue("orderNo", element)+", 상품주문번호 = "+getTagValue("sno", element)+", 결과 [ "+getTagValue("msg", element)+" ] , 택배사고유값 = "+delivCompanyNum+"\n";
					
					
				}
				
			}
			
		}
		
		dBuilder = null;
		doc = null;
		
		return result;
		
	}
	
	
	/**
	 * 
	 * @MethodName : getGodomallOrders
	 * @date : 2021. 1. 6.
	 * @author : Jeon KiChan
	 * @param ssFk
	 * @return
	 * @메소드설명 : 고도몰 자동화
	 */
	public List<OrdersVO> getGodomallOrders(int ssFk){
		boolean failCheck = false;
		List<OrdersVO> orList = new ArrayList<>();
		OrdersVO orVO = null;
		
		Calendar calendar = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		Date months = calendar.getTime();
		Date todays = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp today = new Timestamp(new Date().getTime());

		String dateType = "order";
		String startDate = sdf1.format(months);
		String endDate = sdf1.format(todays);
		String orderStatus = "p1";

		String urlParameters = "partner_key=" + partnerKey + "&key=" + key + "&dateType=" + dateType + "&startDate="
				+ startDate + "&endDate=" + endDate + "&orderStatus=" + orderStatus;
		String url = "https://openhub.godo.co.kr/godomall5/order/Order_Search.php?";


		Document doc = null;
		try {
			doc = dBuilder.parse(url + urlParameters);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Element root = doc.getDocumentElement();

		NodeList nList = doc.getElementsByTagName("order_data");

		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) node;
				String orderNumber = getTagValue("orderNo", element);
				String orderDate = getTagValue("orderDate", element);
				String orBuyerId = getTagValue("memId", element);
				String orderName = "";
				String orderCellPhone = "";
				String receiverName = "";
				String receiverCellPhone = "";
				String receiverZonecode = "";
				String receiverAddress = "";
				String receiverAddressSub = "";
				String mc_deliveryHopeDate = "";
				String mc_orderMemo1 = "";
				String mc_orderMemo2 = "";
				int deliveryCharge = 0;
				int deliveryAreaCharge = 0;
				
				NodeList delivList = element.getElementsByTagName("orderInfoData");

				for(int j = 0; j < delivList.getLength(); j++) {
					Node delivNode = delivList.item(j);
					
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element delivEle = (Element)delivNode;
						orderName = getTagValue("orderName", delivEle);
						orderCellPhone = getTagValue("orderCellPhone", delivEle);
						receiverName = getTagValue("receiverName", delivEle);
						receiverCellPhone = getTagValue("receiverCellPhone", delivEle);
						receiverZonecode = getTagValue("receiverZonecode", delivEle);
						receiverAddress = getTagValue("receiverAddress", delivEle);
						receiverAddressSub = getTagValue("receiverAddressSub", delivEle);
						mc_deliveryHopeDate = getTagValue("mc_deliveryHopeDate", delivEle);
						mc_orderMemo1 = "( "+getTagValue("mc_orderMemo1", delivEle)+" ) "+getTagValue("orderMemo", delivEle) == null ? "" : getTagValue("orderMemo", delivEle);
						mc_orderMemo2 = getTagValue("mc_orderMemo2", delivEle);
						//mc_orderMemo3 = getTagValue("mc_orderMemo3", delivEle);
						
					}
				}
				
				NodeList delivInfoList = element.getElementsByTagName("orderDeliveryData");

				for(int j = 0; j < delivInfoList.getLength(); j++) {
					Node delivNode = delivInfoList.item(j);
					
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element delivEle = (Element)delivNode;
						
						deliveryCharge = (int)Double.parseDouble(getTagValue("deliveryCharge", delivEle));
						deliveryAreaCharge = (int)Double.parseDouble(getTagValue("deliveryAreaCharge", delivEle));
						
					}
				}

				NodeList prodList = element.getElementsByTagName("orderGoodsData");

				for(int j = 0; j < prodList.getLength(); j++) {
					Node prodNode = prodList.item(j);
					
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element prodEle = (Element)prodNode;
						
						orVO = new OrdersVO();
						
						orVO.setOrOrderNumber(orderNumber);
						try {
							orVO.setOrSettlementDay(new Timestamp(sdf.parse(orderDate).getTime()));
							Calendar cals = Calendar.getInstance();
	        				cals.setTime(new Date(sdf1.parse(mc_deliveryHopeDate).getTime()));
	        				cals.add(Calendar.DATE, -1);
	        				java.sql.Date d = new java.sql.Date(cals.getTimeInMillis());
	        				orVO.setOrSendingDeadline(d);
	        				
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							failCheck = true;
							break;
						}
						
						orVO.setOrBuyerId(orBuyerId);
						orVO.setOrBuyerName(orderName);
						orVO.setOrBuyerContractNumber1(orderCellPhone);
						orVO.setOrReceiverName(receiverName);
						orVO.setOrReceiverContractNumber1(receiverCellPhone);
						orVO.setOrShippingAddressNumber(receiverZonecode);
						orVO.setOrShippingAddress(receiverAddress);
						orVO.setOrShippingAddressDetail(receiverAddressSub);
						orVO.setOrDeliveryPrice(deliveryCharge + deliveryAreaCharge);
						orVO.setSsFk(ssFk);
        				orVO.setOrDeliveryMessage(mc_orderMemo1);
						orVO.setOrUserColumn4(mc_orderMemo2);
						String optionInfo = getTagValue("optionInfo", prodEle).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\"", "");
						List<String> options = Arrays.asList(optionInfo.split(","));
						orVO.setOrProductOrderNumber(getTagValue("sno", prodEle));
						orVO.setOrAmount(Integer.parseInt(getTagValue("goodsCnt", prodEle)));
						orVO.setOrProduct(getTagValue("goodsNm", prodEle));
						orVO.setOrProductNumber(getTagValue("goodsNo", prodEle));
						
						int dcPrice = (int)Double.parseDouble(getTagValue("goodsDcPrice", prodEle));
						int memberDcPrice = (int)Double.parseDouble(getTagValue("memberDcPrice", prodEle));
						int overlapDcPrice = (int)Double.parseDouble(getTagValue("memberOverlapDcPrice", prodEle));
						int couponDcPice = (int)Double.parseDouble(getTagValue("couponGoodsDcPrice", prodEle));
						int goodsPrice = (int)Double.parseDouble(getTagValue("goodsPrice", prodEle));
						int optionPrice = (int)(Double.parseDouble(getTagValue("optionPrice", prodEle)));
						orVO.setOrTotalPrice(goodsPrice+optionPrice);
						orVO.setOrTotalPrice( ( orVO.getOrTotalPrice() * orVO.getOrAmount() ) - dcPrice - memberDcPrice - overlapDcPrice - couponDcPice);
						orVO.setOrProductOption(options.get(0)+" "+options.get(1));
						orVO.setOrUserColumn1(options.get(2));
						orVO.setOrRegdate(today);
						
						orList.add(orVO);
					}
				}
			}
		}
		
		if( failCheck == false) {
			return orList;
		}else {
			return null;
		}
		
	}
	
	
	public OrdersVOList getGodomallCancledOrders(int ssFk){
		boolean failCheck = false;
		OrdersVOList orderVO = new OrdersVOList();
		
		List<OrdersVO> orList = new ArrayList<>();
		OrdersVO orVO = null;
		
		Calendar calendar = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		Date months = calendar.getTime();
		Date todays = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp today = new Timestamp(new Date().getTime());

		String dateType = "order";
		String startDate = sdf1.format(months);
		String endDate = sdf1.format(todays);
		String orderStatus = "r1";

		String urlParameters = "partner_key=" + partnerKey + "&key=" + key + "&dateType=" + dateType + "&startDate="
				+ startDate + "&endDate=" + endDate;
		String url = "https://openhub.godo.co.kr/godomall5/order/Order_Search.php?";


		Document doc = null;
		
		//환불 접수
		try {
			doc = dBuilder.parse(url + urlParameters + "&orderStatus=" + orderStatus);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Element root = doc.getDocumentElement();

		NodeList nList = doc.getElementsByTagName("order_data");

		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) node;
				String orderNumber = getTagValue("orderNo", element);

				NodeList prodList = element.getElementsByTagName("orderGoodsData");

				for(int j = 0; j < prodList.getLength(); j++) {
					Node prodNode = prodList.item(j);
					
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element prodEle = (Element)prodNode;
						
						orVO = new OrdersVO();
						orVO.setOrOrderNumber(orderNumber);
						orVO.setSsFk(ssFk);
						orVO.setOrProductOrderNumber(getTagValue("sno", prodEle));
						orList.add(orVO);
					}
				}
			}
		}
		
		orderStatus = "c3";
		try {
			doc = dBuilder.parse(url + urlParameters + "&orderStatus=" + orderStatus);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		root = doc.getDocumentElement();

		nList = doc.getElementsByTagName("order_data");

		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) node;
				String orderNumber = getTagValue("orderNo", element);

				NodeList prodList = element.getElementsByTagName("orderGoodsData");

				for(int j = 0; j < prodList.getLength(); j++) {
					Node prodNode = prodList.item(j);
					
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element prodEle = (Element)prodNode;
						
						orVO = new OrdersVO();
						orVO.setOrOrderNumber(orderNumber);
						orVO.setSsFk(ssFk);
						orVO.setOrProductOrderNumber(getTagValue("sno", prodEle));
						orList.add(orVO);
					}
				}
			}
		}
		
		orderStatus = "c4";
		try {
			doc = dBuilder.parse(url + urlParameters + "&orderStatus=" + orderStatus);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		root = doc.getDocumentElement();

		nList = doc.getElementsByTagName("order_data");

		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) node;
				String orderNumber = getTagValue("orderNo", element);

				NodeList prodList = element.getElementsByTagName("orderGoodsData");

				for(int j = 0; j < prodList.getLength(); j++) {
					Node prodNode = prodList.item(j);
					
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element prodEle = (Element)prodNode;
						
						orVO = new OrdersVO();
						orVO.setOrOrderNumber(orderNumber);
						orVO.setSsFk(ssFk);
						orVO.setOrProductOrderNumber(getTagValue("sno", prodEle));
						orList.add(orVO);
					}
				}
			}
		}
		
		orderStatus = "b1";
		try {
			doc = dBuilder.parse(url + urlParameters + "&orderStatus=" + orderStatus);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		root = doc.getDocumentElement();

		nList = doc.getElementsByTagName("order_data");

		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) node;
				String orderNumber = getTagValue("orderNo", element);

				NodeList prodList = element.getElementsByTagName("orderGoodsData");

				for(int j = 0; j < prodList.getLength(); j++) {
					Node prodNode = prodList.item(j);
					
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element prodEle = (Element)prodNode;
						
						orVO = new OrdersVO();
						orVO.setOrOrderNumber(orderNumber);
						orVO.setSsFk(ssFk);
						orVO.setOrProductOrderNumber(getTagValue("sno", prodEle));
						orList.add(orVO);
					}
				}
			}
		}
		
		orderStatus = "r3";
		try {
			doc = dBuilder.parse(url + urlParameters + "&orderStatus=" + orderStatus);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		root = doc.getDocumentElement();

		nList = doc.getElementsByTagName("order_data");

		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) node;
				String orderNumber = getTagValue("orderNo", element);

				NodeList prodList = element.getElementsByTagName("orderGoodsData");

				for(int j = 0; j < prodList.getLength(); j++) {
					Node prodNode = prodList.item(j);
					
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element prodEle = (Element)prodNode;
						
						orVO = new OrdersVO();
						orVO.setOrOrderNumber(orderNumber);
						orVO.setSsFk(ssFk);
						orVO.setOrProductOrderNumber(getTagValue("sno", prodEle));
						orList.add(orVO);
					}
				}
			}
		}
		
		if( orList != null && orList.size() != 0) {
			orderVO.setOrVoList(orList);
			
			return orderVO;
			
		}else {
			return null;
		}
		
	}
	
	
}
