package com.jabava.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
	public static void main(String args[]){
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
	//	System.out.println( df.format(firstDayOfMonth()));
	//	System.out.println( df.format(lastDayOfMonth()));
	//	System.out.println( df.format(firstDayOfMonth(2016,11)));
	//	System.out.println( df.format(lastDayOfMonth(2016,11)));
		
		System.out.println( df.format(previousMonth()));
	}

}
