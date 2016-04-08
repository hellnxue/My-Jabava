package com.jabava.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class JabavaStringUtils {
	
	/**
	 * 是否是0以上的整数 true 是 false  不是
	 * 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月8日 下午4:30:24 
	 * </pre>
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str){
		if(StringUtils.isBlank(str)){
			return false;
		}
		return str.matches("[0-9]+");
	}

	public static void main(String[] args) {
		
		System.out.println(isNum("0"));
		System.out.println(isNum("-123"));
		System.out.println(isNum( "12.4"));
		
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Matcher matcher = pattern.matcher((CharSequence)"124");
		boolean result=matcher.matches();
		System.out.println(result);
		
	}

}
