package com.gogi.proj.aligo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.util.JsonToMapUtil;

public class AligoSendingForm {

	private String key = "zfg29xmik8qpil747n40b5iktnynl87b";
	private String user_id = "wwaudgns";

	final static String encodingType = "utf-8";
	final static String boundary = "____boundary____";
	
	public String smsMsg(AligoVO aligo) {
		
        String apiURL = "https://apis.aligo.in/send/";    // json 결과

        Map<String, String> requestHeaders = new HashMap<>();
        
        requestHeaders.put("key",key);
        requestHeaders.put("user_id",user_id);
        requestHeaders.put("sender","032-715-6690");
        requestHeaders.put("receiver",aligo.getReceiver());
        requestHeaders.put("destination", aligo.getDestination() == null ? "":aligo.getDestination());
        requestHeaders.put("msg",aligo.getMsg());
        //requestHeaders.put("data-urlencode", "title="+URLEncoder.encode(aligo.getTitle(),"UTF-8"));
        requestHeaders.put("rdate", aligo.getrDate() == null ? "":aligo.getrDate());
        requestHeaders.put("rtime", aligo.getrTime() == null ? "":aligo.getrTime());
        requestHeaders.put("testmode_yn", "N");
        
        String responseBody = get(apiURL,requestHeaders,"POST");
		
		return responseBody;
		
	}

	public AligoVO aligoSendingForm(List<OrdersVO> orList) {
		AligoVO aligoVO = new AligoVO();
		int counting = 1;
		int totalPrice = 0;
		int deliveryPrice = 0;
		
		DecimalFormat df = new DecimalFormat("###,###");
		String address = "";
		StringBuilder productList = new StringBuilder("[삼형제고기주문]\n\n");
		
		for(OrdersVO orVO : orList) {
			if(counting == 1) {
				productList.append(orVO.getOrBuyerName()+" 님 주문내역입니다\n\n●주문내역\n");
				aligoVO.setReceiver(orVO.getOrBuyerContractNumber1());
				address+=(orVO.getOrShippingAddress()+" "+orVO.getOrShippingAddressDetail());
				productList.append(counting+". "+orVO.getOrProduct()+" "+orVO.getOrProductOption()+" "+orVO.getOrAmount()+" 개\n"+df.format(orVO.getOrTotalPrice())+" 원\n");
				deliveryPrice+=orVO.getOrDeliveryPrice();
				
			}else {
				productList.append(counting+". "+orVO.getOrProduct()+" "+orVO.getOrProductOption()+" "+orVO.getOrAmount()+" 개\n"+df.format(orVO.getOrTotalPrice())+" 원\n");
			}
			totalPrice += orVO.getOrTotalPrice();
			counting++;
		}
		
		totalPrice+=deliveryPrice;
		
		if(deliveryPrice > 0) {
			productList.append("\n총 합 : "+df.format(totalPrice)+"원(배송비 "+df.format(deliveryPrice)+"원 포함)\n");
			
		}else {
			productList.append("\n총 합 : "+df.format(totalPrice)+"원\n");
			
		}
		
		productList.append("\n");
		productList.append("기업은행(삼형제고기)\n");
		productList.append("231-160549-04-019\n\n");
		productList.append("주문자명으로 입금 부탁드립니다^^\n");
		productList.append("주문자 정보 -(틀릴 경우 바로 연락주세요)\n");
		productList.append(address);
		
		aligoVO.setMsg(productList.toString());
		return aligoVO;
		
	}
	
	public AligoVO sendingAligoSMSLargeFormData(List<OrdersVO> orList) {
		AligoVO aligoVo = new AligoVO();
		
		StringBuffer aligoDes = new StringBuffer("");
		StringBuffer aligorec = new StringBuffer("");
		
		int counting = 0;
		
		for(OrdersVO orVO : orList) {
			if(counting == 0) {
				aligoDes.append(orVO.getOrBuyerContractNumber1()+"|"+orVO.getOrBuyerName());
				aligoDes.append(orVO.getOrBuyerContractNumber1()+"|"+orVO.getOrBuyerName());
				
			}else {
				aligoDes.append(","+orVO.getOrBuyerContractNumber1()+"|"+orVO.getOrBuyerName());
				aligoDes.append(","+orVO.getOrBuyerContractNumber1()+"|"+orVO.getOrBuyerName());
			}
			
			counting++;
		}
		
		aligoVo.setDestination(aligoDes.toString());
		aligoVo.setReceiver(aligorec.toString());
		
		return aligoVo;
	}
	
	public String get(String apiUrl, Map<String, String> requestHeaders, String methodType){
        MultipartEntityBuilder  builder = MultipartEntityBuilder.create();
		
		builder.setBoundary(boundary);
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.setCharset(Charset.forName(encodingType));
		
		String result = "";
        try {
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                builder.addTextBody(header.getKey(),  header.getValue()
    					, ContentType.create("Multipart/related", encodingType));
                
            }

            HttpEntity entity = builder.build();
            HttpClient client = HttpClients.createDefault();
    		HttpPost post = new HttpPost(apiUrl);
    		post.setEntity(entity);
    		
    		HttpResponse res = client.execute(post);
    		
    		result = "";
    		if(res != null){
    			BufferedReader in = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), encodingType));
    			String buffer = null;
    			while((buffer = in.readLine())!=null){
    				result += buffer;
    			}
    			
    			in.close();
    		}
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {

        }
        
        return result;
    }
	
	
	public Map<String, Object> aligoRemain() {
		
		Map<String, Object> remainResult = null;
		try{

			/**************** 최근 전송 목록 ******************/
			/* "result_code":결과코드,"message":결과문구, */
			/** list : 전송된 목록 배열 ***/
			/******************** 인증정보 ********************/
			String sms_url = "https://apis.aligo.in/remain/"; // 전송요청 URL
			
			String sms = "";
			sms += "user_id=" + user_id; // SMS 아이디 
			sms += "&key=" + key; //인증키
			/******************** 인증정보 ********************/
			
			URL url = new URL(sms_url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			OutputStream os = conn.getOutputStream();
			os.write(sms.getBytes());
			os.flush();
			os.close();
			
			String result = "";
			String buffer = null;
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			while((buffer = in.readLine())!=null){
				result += buffer;
			}
			
			in.close();

			JsonToMapUtil jt = new JsonToMapUtil();
			
			JSONParser parser = new JSONParser();
			Object Object = null;
			
			try {
				Object = parser.parse( result );
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			remainResult = jt.getMapFromJsonObject((JSONObject)Object);
			
		}catch(MalformedURLException e1){
			
		}catch(IOException e2){
			
		}
	
		return remainResult;
	}
}
