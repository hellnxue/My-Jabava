package com.jabava.utils;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.jexl2.UnifiedJEXL;
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
	
	public static boolean isBigDecimal(String str){
		if(org.apache.commons.lang.StringUtils.isEmpty(str)){
			return false;
		}
		try {
			new BigDecimal(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static String getRandomNum(int count){
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for(int i=0;i<count;i++){
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num)+""), "");
        }
        return sb.toString();
    }

	/**
	 * 使用Jexl格式化字符串
	 * @param str
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String formatString(String str, Map<String, Object> params) throws Exception {
		JexlContext context = new MapContext(params);
		JexlEngine jexl = new JexlEngine();
		UnifiedJEXL ujexl = new UnifiedJEXL(jexl);
		UnifiedJEXL.Expression expr = ujexl.parse(str);
		return expr.evaluate(context).toString();
	}
	
	public static String fillWithChar(String original, int length){
		return fillWithChar(original, length, '0', true);
	}
	
	public static String fillWithChar(String original, int length, char c){
		return fillWithChar(original, length, c, true);
	}
	
	public static String fillWithChar(String original, int length, char c, boolean pre){
		while(original.length() < length){
			if(pre){
				original = String.valueOf(c) + original;
			}else{
				original = original + String.valueOf(c);
			}
		}
		
		return original;
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
