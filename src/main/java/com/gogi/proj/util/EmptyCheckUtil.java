package com.gogi.proj.util;

import java.util.List;
import java.util.Map;

public class EmptyCheckUtil {

	public static boolean isEmpty(Object obj) {
        if (obj == null) { return true; }
        if ((obj instanceof String) && (((String) obj).trim().length() == 0)) { return true; } 
        if (obj instanceof Map) { return ((Map<?, ?>) obj).isEmpty(); }
        if (obj instanceof List) { return ((List<?>) obj).isEmpty(); }
        if (obj instanceof Object[]) { return (((Object[]) obj).length == 0); } 

        return false;
    }
	
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
    }
}
