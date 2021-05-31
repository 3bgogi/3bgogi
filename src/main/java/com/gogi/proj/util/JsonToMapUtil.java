package com.gogi.proj.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToMapUtil {

	public static Map<String, Object> getMapFromJsonObject( JSONObject jsonObj ){
		
        Map<String, Object> map = null;
        
        try {
            
            map = new ObjectMapper().readValue(jsonObj.toJSONString(), Map.class) ;
            
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return map;
    }
	
	 /**
     * JsonArray를 List<Map<String, String>>으로 변환한다.
     *
     * @param jsonArray JSONArray.
     * @return List<Map<String, Object>>.
     */
    public static List<Map<String, Object>> getListMapFromJsonArray( JSONArray jsonArray )
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
        if( jsonArray != null )
        {
            int jsonSize = jsonArray.size();
            for( int i = 0; i < jsonSize; i++ )
            {
                Map<String, Object> map = JsonToMapUtil.getMapFromJsonObject( ( JSONObject ) jsonArray.get(i) );
                list.add( map );
            }
        }
        
        return list;
    }
    
    /**
     * 
     * @MethodName : getMapToDAO
     * @date : 2020. 8. 11.
     * @author : Jeon KiChan
     * @param entryMap
     * @param object
     * @return
     * @메소드설명 : map을 vo, dao 형태로 바꿔서 반환해주기
     */
    public static Object getMapToDAO(Map<String, Object> entryMap, Object object) {
    	
		for(Map.Entry<String, Object> resultMap : entryMap.entrySet()) {
			if(resultMap.getValue() instanceof Map<?, ?>) {
				Map<String, Object> nextMap = (Map<String, Object> )resultMap.getValue();
				return getMapToDAO(nextMap, object);
    			
    		}else {
    			
    			try {
					Field field = object.getClass().getDeclaredField(resultMap.getKey());
					field.setAccessible(true);
					
					if(isNumber((String)resultMap.getValue())) {
						field.set(object, Integer.parseInt((String)resultMap.getValue()));
					}else {
						
						field.set(object, resultMap.getValue());
					}
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException("필드값을 찾을 수 없습니다", e);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException("보안 에러", e);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(" 필드값의 값이 적절하지 않습니다", e);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(" 필드값을 참조할 수 없습니다", e);
				}
    		}
		}
		
		return object;
    }
    
    
    /**
     * 
     * @MethodName : isNumber
     * @date : 2020. 8. 12.
     * @author : Jeon KiChan
     * @param value
     * @return
     * @메소드설명 : 숫자 여부 체크
     */
    public static boolean isNumber(String value) {
    	
    	try {
    		
    		Integer.parseInt(value);
    		return true;
    		
    	}catch(NumberFormatException e) {
    		return false;
    	}
    }
	
}
