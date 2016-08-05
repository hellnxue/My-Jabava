package com.jabava.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 日期工具类
 *
 * @version $Id: DateUtils.java, 
 * v 0.1 2016年3月2日 下午4:59:18 
 * <pre>
 * @author steven.chen
 * @date 2016年3月2日 下午4:59:18 
 * </pre>
 */
public class JabavaDateUtils {

	public static String formatDate(String pattern) throws Exception{
		return formatDate(new Date(), pattern);
	}
	
	public static String formatDate(Date date, String pattern) throws Exception{
		if(date == null) {
			throw new Exception("日期为空");
		}
		
		if(StringUtils.isEmpty(pattern)){
			throw new Exception("模式字符串为空");
		}
		
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	/**
	 * 本月第一天 00:00:00
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月2日 下午5:01:51 
	 * </pre>
	 *
	 * @param date
	 * @return
	 */
	public static Date firstDayOfMonth(){
		
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		GregorianCalendar gcFirst = (GregorianCalendar) Calendar.getInstance();
		gcFirst.setTime(now);
		gcFirst.set(Calendar.DAY_OF_MONTH, 1);
		gcFirst.set(Calendar.HOUR_OF_DAY, 0);
		gcFirst.set(Calendar.SECOND,0);
		gcFirst.set(Calendar.MINUTE,0);
	    return gcFirst.getTime();
	}
	/**
	 * 本月最后一天 23:59:59
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月2日 下午5:11:28 
	 * </pre>
	 *
	 * @return
	 */
	public static Date lastDayOfMonth(){
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(now);
		gcLast.add(Calendar.MONTH, 1);    //加一个月
		gcLast.set(Calendar.DAY_OF_MONTH, 0);//本月最后一天
		gcLast.set(Calendar.HOUR_OF_DAY, 23);
		gcLast.set(Calendar.SECOND,59);
		gcLast.set(Calendar.MINUTE,59);
	    return gcLast.getTime();
	}
	/**
	 * 指定年月的第一天  00:00:00
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月5日 下午6:17:52 
	 * </pre>
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date firstDayOfMonth(int year,int month){
		
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		GregorianCalendar gcFirst = (GregorianCalendar) Calendar.getInstance();
		gcFirst.setTime(now);
		gcFirst.set(Calendar.YEAR, year);
		gcFirst.set(Calendar.MONTH, month-1);
		gcFirst.set(Calendar.DAY_OF_MONTH, 1);
		gcFirst.set(Calendar.HOUR_OF_DAY, 0);
		gcFirst.set(Calendar.SECOND,0);
		gcFirst.set(Calendar.MINUTE,0);
	    return gcFirst.getTime();
	}
	/**
	 * 指定年月的最后一天 23:59:59
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月5日 下午6:25:22 
	 * </pre>
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date lastDayOfMonth(int year,int month){
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(now);
		gcLast.set(Calendar.YEAR, year);
		gcLast.set(Calendar.MONTH, month);
		gcLast.set(Calendar.DAY_OF_MONTH, 0);//本月最后一天
		gcLast.set(Calendar.HOUR_OF_DAY, 23);
		gcLast.set(Calendar.SECOND,59);
		gcLast.set(Calendar.MINUTE,59);
	    return gcLast.getTime();
	}
	/**
	 * 上个月第一天 00：00：00
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月5日 下午6:55:45 
	 * </pre>
	 *
	 * @return
	 */
	public static Date previousMonth(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		Date now = calendar.getTime();
		GregorianCalendar gcFirst = (GregorianCalendar) Calendar.getInstance();
		gcFirst.setTime(now);
		gcFirst.set(Calendar.DAY_OF_MONTH, 1);
		gcFirst.set(Calendar.HOUR_OF_DAY, 0);
		gcFirst.set(Calendar.SECOND,0);
		gcFirst.set(Calendar.MINUTE,0);
		
	    return gcFirst.getTime();
	}
	
	/**
	 * 根据给定的两个年月字符串获(yyyyMM)取月份数
	 * @param startMonth
	 * @param endMonth
	 */
	public static int getMonths(String startYm, String endYm){
		int startYear = Integer.valueOf(startYm.substring(0,4));
		int startMonth = Integer.valueOf(startYm.substring(4));
		int endYear = Integer.valueOf(endYm.substring(0,4));
		int endMonth = Integer.valueOf(endYm.substring(4));
		return (endYear - startYear) * 12 + endMonth - startMonth + 1;
	}
	
	/**
	 * 根据当前年月字符串(yyMM)获取下月字符串
	 * @param currentMonth
	 * @return
	 */
	public static String getNextMonth(String currentMonth){
		int year = Integer.valueOf(currentMonth.substring(0,4));
		int month = Integer.valueOf(currentMonth.substring(4));
		if(month == 12){
			year ++;
			month = 1;
		}else{
			month ++;
		}
		return String.valueOf(year) + (month > 9 ? "" : "0") + month;
	}
	
	/**
	 * 根据当前年月字符串(yyMM)获取上月字符串
	 * @param currentMonth
	 * @return
	 */
	public static String getLastMonth(String currentMonth){
		int year = Integer.valueOf(currentMonth.substring(0,4));
		int month = Integer.valueOf(currentMonth.substring(4));
		if(month == 1){
			year --;
			month = 12;
		}else{
			month --;
		}
		return String.valueOf(year) + (month > 9 ? "" : "0") + month;
	}
	
	public static Date parseDate(String source, String pattern) throws Exception{
		if(StringUtils.isEmpty(source)){
			throw new Exception("原始字符串为空");
		}
		
		if(StringUtils.isEmpty(pattern)){
			throw new Exception("模式字符串为空");
		}
		
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.parse(source);
	}
	
	public static Date parseStrictDate(String source, String pattern) throws Exception{
		if(StringUtils.isEmpty(source)){
			throw new Exception("原始字符串为空");
		}
		
		if(StringUtils.isEmpty(pattern)){
			throw new Exception("模式字符串为空");
		}
		
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
		format.setLenient(false);
		return format.parse(source);
	}
	/**
	 * 获得指定日期的前一天
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayBefore(String specifiedDay){
		Calendar c = Calendar.getInstance();
		Date date=null;
		try {
		date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (Exception e) {
		e.printStackTrace();
		}
		c.setTime(date);
		int day=c.get(Calendar.DATE);
		c.set(Calendar.DATE,day-1);

		String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayBefore;
	}
	
	public static long getDiffDays(Date date){
		return getDiffDays(new Date(), date);
	}
	
	public static long getDiffDays(Date date1, Date date2){
		return (date2.getTime() - date1.getTime()) / (1000 * 3600 * 24);
	}
	
	/**
	* 日期转换成字符串
	* @param date
	* @return str
	*/
	public static String DateToStr(Date date) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   String str = format.format(date);
	   return str;
	}

	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception{
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
	//	System.out.println( df.format(firstDayOfMonth()));
	//	System.out.println( df.format(lastDayOfMonth()));
	//	System.out.println( df.format(firstDayOfMonth(2016,11)));
	//	System.out.println( df.format(lastDayOfMonth(2016,11)));
		
		System.out.println( df.format(previousMonth()));
		
		System.out.println(getMonths("201510","201603"));
		
		System.out.println(getLastMonth("201601"));
		System.out.println(getLastMonth("201612"));
		System.out.println(getNextMonth("201601"));
		System.out.println(getNextMonth("201612"));
		
		//System.out.println(parseStrictDate("2006-99-99", "yyyy-MM-dd"));
		
		System.out.println(getDiffDays(parseDate("2016-07-14", "yyyy-MM-dd")));
	}

}
