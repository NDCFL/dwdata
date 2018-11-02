package com.dwsj.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 * 日期工具类
 * @author cgw
 * @date 2017年6月8日
 */
public final class DateUtil {
	
	private static final char[] zeroArray =
            "0000000000000000000000000000000000000000000000000000000000000000".toCharArray();
	
	private static int DATE_MILLIS = 86400000;	// 24*60*60*1000
	private static int HOUR_MILLIS = 3600000;	// 	  60*60*1000
	
	private static String getFormat(int level){
		if (level == Calendar.YEAR){
			return "yyyy";
		}else if (level == Calendar.MONTH){
			return "yyyy-MM";
		}else if (level == Calendar.DATE){
			return "yyyy-MM-dd";
		}else if (level == Calendar.HOUR){
			return "yyyy-MM-dd hh";
		}else if (level == Calendar.MINUTE){
			return "yyyy-MM-dd hh:mm";
		}else if (level == Calendar.SECOND){
			return "yyyy-MM-dd hh:mm:ss";
		}
		return null;
	}
	
	private static Calendar getCalendar(){
		return new GregorianCalendar();
	}
	

	/**
	 * 获取当前时间前/后若干天的时间
	 * @param date
	 * @param increment
	 * @return
	 */
	public static Date incrementAndGet(Date date, int increment){
	    return incrementAndGet(date, increment, Calendar.DATE);
	}
	
	public static Date decreseAndGet(Date date, int increment){
		return incrementAndGet(date,-increment,Calendar.DATE);
	}
	public static Date decreseAndGet(Date date, int increment, int field){
		return incrementAndGet(date,-increment,field);
	}
	
	
	/**
	 * 获取当前时间前/后若干时长的时间（calender.xxx）
	 * @param date
	 * @param increment
	 * @return
	 */
	public static Date incrementAndGet(Date date, int increment, int field){
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		calendar.add(field, increment);
		Date newDate = calendar.getTime();
		return newDate;
	}
	
	/**
	 * 获取date时间after之前或者之后的时间
	 * @param date 时间节点
	 * @param after 之前（负数）,之后（正数）
	 */
	public static Date afterOrBeforeDayFormByNow(Date date, int after) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH,after);
		return date = calendar.getTime();
	}
	
	/**
	 * leftDate < rightDate
	 * 
	 * @param leftDate
	 * @param rightDate
	 * @param level (eg:Calendar.DATE)
	 * @return
	 */
	public static boolean lt(Date leftDate, Date rightDate, int level){
		int cmp = compareTo(leftDate, rightDate,level);
		return cmp < 0;
	}
	
	/**
	 * leftDate <= rightDate
	 * 
	 * @param leftDate
	 * @param rightDate
	 * @param level (eg:Calendar.DATE)
	 * @return
	 */
	public static boolean le(Date leftDate, Date rightDate, int level){
		int cmp = compareTo(leftDate, rightDate,level);
		return (cmp <= 0 );
	}
	
	/**
	 * leftDate > rightDate
	 * 
	 * @param leftDate
	 * @param rightDate
	 * @param level (eg:Calendar.DATE)
	 * @return
	 */
	public static boolean gt(Date leftDate, Date rightDate, int level){
		int cmp = compareTo(leftDate, rightDate,level);
		return (cmp > 0 );
	}
	
	/**
	 * leftDate >= rightDate <br>
	 * 
	 * @param leftDate
	 * @param rightDate
	 * @param level (eg:Calendar.DATE)
	 * @return
	 */
	public static boolean ge(Date leftDate, Date rightDate, int level){
		int cmp = compareTo(leftDate, rightDate,level);
		return ( cmp >= 0 );
	}
	
	/**
	 * leftDate == rightDate <br>
	 * 
	 * @param leftDate
	 * @param rightDate
	 * @param level (eg:Calendar.DATE)
	 * @return
	 */
	public static boolean equals(Date leftDate, Date rightDate, int level){
		int cmp = compareTo(leftDate, rightDate,level);
		return ( cmp == 0 );
	}
	
	/**
	 * startDate <= targetDate <= endDate <br>
	 * 
	 * @param targetDate
	 * @param startDate
	 * @param endDate
	 * @param level (eg:Calendar.DATE)
	 * @return
	 */
	public static boolean isBetween(Date targetDate, Date startDate, Date endDate, int level){
		boolean startLe = le(startDate,targetDate,level);
		boolean endGe = ge(endDate,targetDate,level);
		return startLe && endGe;
	}
	
	/**
	 * parse String --> Date
	 * @param dateStr
	 * @param format
	 * @return date
	 */
	public static Date parse(String dateStr, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date targetDate = null;
		try {
			targetDate = sdf.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException("Can`t parse the ["+dateStr+"] become Date Object");
		}
		
		return targetDate;
	}
	
	/**
	 * parse Date ---> String
	 * @param date
	 * @param format
	 * @return string
	 */
	public static String parse(Date date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * parse String---> String
	 * @param date
	 * @param format
	 * @return string
	 */
	public static String strParseStr(String dateStr, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = DateUtil.parse(dateStr, "yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	
	/**
	 * Compare leftDate and rightDate 
	 * @param leftDate
	 * @param rightDate
	 * @param level (eg:Calendar.DATE)
	 * @return returns<br>
	 * 1: leftDate > rightDate <br>
	 * 0: leftDate = rightDate <br>
	 * -1:leftDate < rightDate
	 */
	public static int compareTo(Date leftDate, Date rightDate, int level){
		String format = getFormat(level);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String ldStr = sdf.format(leftDate);
		String rdStr = sdf.format(rightDate);
		return ldStr.compareToIgnoreCase(rdStr);
	}
	
	/**
	 * sub = leftDate - rightDate<br>
	 * 不考虑时间轴
	 * @param leftDate
	 * @param rightDate
	 * @param field
	 * @return sub
	 */
	public static int sub(Date leftDate, Date rightDate, int field){
		Calendar calendar = getCalendar();
		calendar.setTime(leftDate);
		int left = calendar.get(field);
		calendar.setTime(rightDate);
		int right = calendar.get(field);
		
		return left - right;
	}
	
	/**
	 * sub = leftDate - rightDate<br>
	 * 考虑时间轴
	 * @param leftDate
	 * @param rightDate
	 * @param field
	 * @return
	 */
	public static int subTimeAxised(Date leftDate, Date rightDate, int field){
		
		Calendar leftCal = Calendar.getInstance();
		leftCal.setTime(leftDate);
		Calendar rightCal = Calendar.getInstance();
		rightCal.setTime(rightDate);
		
		Integer difference = null;
		Long left = leftDate.getTime();
		Long right = rightDate.getTime();
		Long delta = left -right;
		
		int deltaYears = leftCal.get(Calendar.YEAR) - rightCal.get(Calendar.YEAR);
		int deltaMonthes = leftCal.get(Calendar.MONTH) - rightCal.get(Calendar.MONTH);
		
		switch(field){
			case Calendar.YEAR:
				difference = deltaYears;
				break;
			case Calendar.MONTH:
				difference = (int) (deltaYears * 12 + deltaMonthes);
				break;
			case Calendar.DATE:
				difference = calcDelta(delta,DATE_MILLIS);
				break;
			case Calendar.HOUR:
				difference = calcDelta(delta,HOUR_MILLIS);
				break;
			case Calendar.MINUTE:
				difference = calcDelta(delta,60000);
				break;
			case Calendar.SECOND:
				difference = calcDelta(delta,1000);
				break;
			case Calendar.MILLISECOND:
				difference = (int) (left -right);
				break;
			default:
				difference = calcDelta(delta,DATE_MILLIS);
				break;

		}

		return difference;
	}
	
	private static int calcDelta(long delta, long base){
		double delta5 = delta / base;	// 去整
		if (delta%base > 0){	// 取余, 如果有余, 则算作1
			delta5++;
		}
		return (int) Math.floor(delta5);
	}
	
	public static String zeroPadString(String string, int length) {
        if (string == null || string.length() > length) {
            return string;
        }
        StringBuilder buf = new StringBuilder(length);
        buf.append(zeroArray, 0, length - string.length()).append(string);
        return buf.toString();
    }
	
	public static String dateToMillis(Date date) {
        return zeroPadString(Long.toString(date.getTime()), 15);
    }
	
	
	public static void main(String[] args) {
		String dateStr = "2018-05-01 12:42:53";
		Date date = DateUtil.parse(dateStr, "yyyy-MM-dd HH:mm:ss");
		System.out.println(date);
		
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss 格式的字符串转Date
	 * @param dateStr
	 * @return
	 */
	public static Date StringToDate(String dateStr){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	
	/**
	 * date类型转换为long类型
	 * @param date
	 * @return
	 */
 	public static long dateToLong(Date date) {
 		return date.getTime();
 	}
	
	/**
	 * @Title: yearPoor  
	 * @Description: 计算两个date时间段的年份之差
	 * @param @param date date时间
	 * @return Integer 年份=之差
	 * @author 彭河川
	 * @date 2017年8月8日    下午8:08:23
	 */
	public static Integer yearPoor(Date date) {
		if (date == null) {
			return 0;
		} else {
			Calendar bef = Calendar.getInstance();
			Calendar aft = Calendar.getInstance();
			bef.setTime(date);
			aft.setTime(new Date());
			return (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR));
		}
	}
	
	/**
	 * CST时间字符串转换成date
	 * @param cst
	 * @return
	 */
	public static Date CST2Date(String cst) {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		Date date = null;
		try {
			date = sdf.parse(cst);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 计算两个时间的毫秒数
	 * @param date1 被减数（晚一点的时间）
	 * @param date2 减数（早一点的时间）
	 */
	public static long subDateForSecond(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return 0L;
		}
		BigDecimal sub = new BigDecimal(date1.getTime()).subtract(new BigDecimal(date2.getTime()));
		return sub.longValue();
	}
	
	/**
	 * 获取年月日时分秒毫秒
	 * @param date
	 * @param field
	 * @return
	 */
	public static int getField(Date date, int field){
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		return calendar.get(field);
	}
	
	/**
	 * 判断day是否是指定date当月的最后一天
	 * @param date
	 * @param day
	 * @return
	 */
	public static boolean isLastDayOfMonth(Date date, int day){
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		int maxDay = calendar.getActualMaximum(Calendar.DATE);
		
		return day == maxDay;
	}
	
	/**
	 * 获取该年是365天还是366天
	 * @param date
	 * @return
	 */
	public static int getDaysOfYear(Date date){
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
	}
	
	public static Date setHMS(Date date,int hours,int minutes,int seconds) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.set(Calendar.HOUR_OF_DAY, hours <=0 ? 0 : hours);
		instance.set(Calendar.MINUTE, minutes <=0 ? 0 : minutes);
		instance.set(Calendar.SECOND, seconds <=0 ? 0 : seconds);
		return instance.getTime();
	}
	
	/**
	 * 比较两个date的时间先后顺序，前者在后者之前返回true
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static boolean compare(Date date1,Date date2) throws ParseException  {
		if(date1.getTime()-date2.getTime()<0) {
			 return true; 
		}else {
            return false; 
		}
	}
	
	/**
	 * 获取现在的时间
	 * @param format
	 * @return
	 */
	public static String now(String format) {
		return parse(new Date(),format);
	}
	
	/**
	 * 判断两个时间相减是否大于24小时
	 * 判断date1-date2相减是否大于24小时
	 */  
	public static boolean gtOneDay(String date1, String date2) { 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        Date start; 
        Date end;
		try {
			start = sdf.parse(date1);
			end = sdf.parse(date2);
			long cha = start.getTime() - end.getTime(); 
	        double result = cha * 1.0 / (1000 * 60 * 60); 
	        if(result<=24){ 
	             return false; 
	        }else{ 
	             return true; 
	        } 
		} catch (Exception e) {
			return false;
		} 
       
	} 
	
	
	/**
	 * 毫秒转化时分秒毫秒 
	 */  
	public static String formatTime(Long ms) {  
	    Integer ss = 1000;  
	    Integer mi = ss * 60;  
	    Integer hh = mi * 60;  
	    Integer dd = hh * 24;  
	  
	    Long day = ms / dd;  
	    Long hour = (ms - day * dd) / hh;  
	    Long minute = (ms - day * dd - hour * hh) / mi;  
	    Long second = (ms - day * dd - hour * hh - minute * mi) / ss;  
	   // Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;  
	      
	    StringBuffer sb = new StringBuffer();  
	    if(day > 0) {  
	        sb.append(day+"天");  
	    }  
	    if(hour > 0) {  
	        sb.append(hour+"小时");  
	    }  
	    if(minute > 0) {  
	        sb.append(minute+"分");  
	    }  
	    if(second > 0) {  
	        sb.append(second+"秒");  
	    }  
	   /* if(milliSecond > 0) {  
	        sb.append(milliSecond+"毫秒");  
	    }*/  
	    return sb.toString();  
	}  
	
	/**
	 * 计算年龄
	 */
	public static int calculateAge(Date birthday) {
		int birthYear = birthday.getYear();
		int currentYear = new Date().getYear();
		return currentYear-birthYear;
	}
	/**
	 * 计算时间差(天)
	 * @return
	 */
	public static int subTime(Date rightDate){
		Date now = new Date();
		Calendar leftCal = Calendar.getInstance();
		leftCal.setTime(new Date());
		Calendar rightCal = Calendar.getInstance();
		rightCal.setTime(rightDate);
		
		Integer difference = null;
		Long left = now.getTime();
		Long right = rightDate.getTime();
		Long delta = left -right;
		
		difference = calcDelta(delta,DATE_MILLIS);

		return difference-1;
	}

	
}
