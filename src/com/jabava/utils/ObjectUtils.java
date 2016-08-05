package com.jabava.utils;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

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
	
	public static String formatBeanField(String dbColumn){
		if(StringUtils.isEmpty(dbColumn)){
			return null;
		}
		
		while(dbColumn.indexOf("_") != -1){
			int idx = dbColumn.indexOf("_");
			if(dbColumn.length() > idx + 1){
				dbColumn = dbColumn.substring(0, idx) + firstCharToUpper(dbColumn.substring(idx + 1));
			}else{
				dbColumn = dbColumn.substring(0, idx);
			}
		}
		
		return dbColumn;
	}
	
	private static String firstCharToUpper(String str){
		if(StringUtils.isEmpty(str)){
			return null;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public static void main(String[] args){
		String str = "common___info109";
		str = ObjectUtils.formatBeanField(str);
		System.out.println(str);
	}
}
