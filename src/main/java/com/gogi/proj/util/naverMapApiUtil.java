package com.gogi.proj.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class naverMapApiUtil {

	private String clientId = "5Es5VerCO4Y0QHrF_gSl";
	private String clientSecret = "wJdpXRyEJU";
	
	private String kakaoAppKey = "bcbba5428da89430f8eb29996ec26f2a";
	
	public String getCoordiante(String localPlaceName) throws IOException {
		
        String text = localPlaceName;
        
        try {
            text = URLEncoder.encode(text, "UTF-8");
            
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        String apiURL = "https://dapi.kakao.com/v2/local/search/address.json?query=" + text;    // json 결과

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", "KakaoAK "+kakaoAppKey);
        String responseBody = get(apiURL,requestHeaders, "GET");
		
		return responseBody;
	}
	
	public static String get(String apiUrl, Map<String, String> requestHeaders, String methodType){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod(methodType);
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }
	
	public static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    public static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
	
    public static Map<String, Object> returnJson(String responseBody) throws ParseException {
    	
    	JSONParser parser = new JSONParser();
    	Object obj = parser.parse( responseBody );
    	JSONObject jsonObj = (JSONObject) obj;
    	
    	
    	 Map<String, Object> map = null;
         
         try {
             
             map = new ObjectMapper().readValue(jsonObj.toJSONString(), Map.class) ;
             
         } catch (JsonParseException e) {
        	 throw new RuntimeException("json 변환 실패", e);
         } catch (JsonMappingException e) {
			// TODO Auto-generated catch block
        	 throw new RuntimeException("map 변환 실패", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("에러 발생", e);
		} 
  
         return map;

    }
}
