package com.jabava.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

public class JabavaUtil {

	public static String BLANK = "";

	public static String formatLikeWildcard(String str) {
		if (str == null || BLANK.equals(str)) {
			return null;
		}
		return str = "%" + str + "%";
	}

	public static String formatDate(String sourceDate) throws Exception {
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		Date d = sdf1.parse(sourceDate);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		return sdf2.format(d);
	}
	
	public static Date formatDate(String source, String pattern) throws Exception{
		if(StringUtils.isEmpty(source)){
			throw new Exception("原始字符串为空");
		}
		
		if(StringUtils.isEmpty(pattern)){
			throw new Exception("模式字符串为空");
		}
		
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.parse(source);
	}
	
	public static String parseDate(Date date, String pattern) throws Exception{
		if(StringUtils.isEmpty(pattern)){
			throw new Exception("模式字符串为空");
		}
		
		if(date == null) {
			date = new Date();
		}
		
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	/**
	 * 
	 * @param source
	 * 		原始日期
	 * @param field
	 * 		标识，如Calendar.MONTH
	 * @param amount
	 * 		数量，可以为负数
	 * @return
	 */
	public static Date addDate(Date source,int field,int amount){
		if(source == null) {
			source = new Date();
		}
		
		Calendar c = new GregorianCalendar();
		c.setTime(source);
		c.add(field, amount);
		return c.getTime();
	}
	
	/**
	 * 获取某个日期的某个值，如年、月、日等
	 * @param date
	 * 	如果为空，则默认当前日期
	 * @param type
	 * 	与Calendar的常量一致
	 * @return
	 */
	public static int getDateElement(Date date, int type){
		if(date == null){
			date = new Date();
		}
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(type);
	}

	public static int isNumeric(String str) {
		if (str == null || BLANK.equals(str)) {
			return 0;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return 1;
		}
		return 2;
	}
	
	public static String getCellStr(Cell cell){
		if(cell == null){
			return BLANK;
		}
		switch(cell.getCellType()){
			case Cell.CELL_TYPE_NUMERIC: //数值型 0
				return BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
			case Cell.CELL_TYPE_STRING: //字符串型 1
				return cell.getStringCellValue().trim();
			case Cell.CELL_TYPE_FORMULA: //公式型 2
				cell.toString().trim();
			case Cell.CELL_TYPE_BLANK: //空值 3
				return BLANK;
			case Cell.CELL_TYPE_BOOLEAN: //布尔型 4
				return String.valueOf(cell.getBooleanCellValue());
			case Cell.CELL_TYPE_ERROR: //错误 5
				return BLANK;
			default:
				return BLANK;
		}
		//return cell.toString().trim();
	}
	
	/**
	 * String 类型转 BigDecimal
	 * @param value
	 * @return
	 */
	public static BigDecimal string2BigDecinal(String value){
		if(StringUtils.isEmpty(value)){
			return null;
		}
		try {
			return new BigDecimal(DecimalFormat.getInstance().parse(value).toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
