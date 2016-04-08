package com.jabava.utils;

import java.lang.reflect.Method;
import java.util.Map;

public class ObjectUtils {
	public static void setValue(Object obj, String fieldName, Object value){
		if(obj instanceof Map){
			((Map)obj).put(fieldName, value);
		}else{
			fieldName = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
			try {
				Method m = obj.getClass().getDeclaredMethod("set" + fieldName, value.getClass());
				m.invoke(obj, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
