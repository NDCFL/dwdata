package com.dwsj.util;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

public final class NumberUtil {

	public static boolean isNum(String numStr){
		boolean isnum = numStr != null && !numStr.equalsIgnoreCase("null") && !numStr.trim().equals("");
		isnum = isnum ? Pattern.matches("-{0,1}([0-9]*\\.{0,1}\\d{1,})|([0-9]*\\.[0-9]*E-[0-9]{1,})$", numStr) : isnum;
		return isnum;
	}
	
	/**
  	 * num 要转换的数 from源数的进制 to要转换成的进制
  	 * @param num
  	 * @param from
  	 * @param to
  	 * @return
  	 */
	public static String change(String num,int from, int to){
		return new java.math.BigInteger(num, from).toString(to);
	}
	
	/**
	 * 判断是否是0或者null
	 * @param number
	 * @return
	 */
	public static boolean isEmpty(Number number){
		if (number == null) return true;
		return number.equals(0) || ( Double.valueOf(String.valueOf(number)) > -0.0000000000001 && Double.valueOf(String.valueOf(number)) < 0.000000001 );
	}
	
	/**
	 * 如果为null,则返回0
	 * @param number
	 * @return
	 */
	public static Number return0IfNull(Number number){
		return isEmpty(number) ? 0 : number;
	}
	
	/**
	 * 分 转 元
	 * @param money
	 * @return
	 */
	public static Double fenToYuan(String money){
		
		if (isNum(money )){
			return fenToYuan(Double.valueOf(money));
		}
		return null;
	}
	
	/**
	 * 分转元
	 * @param money
	 * @return
	 */
	public static Double fenToYuan(double money){
		BigDecimal bd = new BigDecimal(Double.toString(money));
		bd.setScale(2,BigDecimal.ROUND_HALF_UP);
		return bd.divide(new BigDecimal(100.0)).doubleValue();
	}
	
	/**
	 * 带精度的加
	 * @param a	如果为null,则当成0
	 * @param b	如果为null,则当成0
	 * @param scale	精度,向上取整
	 * @return
	 */
	public static Double add(Double a, Double b, int scale){
		a = a == null ? 0.0 : a;
		b = b == null ? 0.0 : b;
		BigDecimal aa = new BigDecimal(Double.toString(a)).setScale(scale,BigDecimal.ROUND_DOWN);
		BigDecimal bb = new BigDecimal(Double.toString(b)).setScale(scale,BigDecimal.ROUND_DOWN);
		return aa.add(bb).doubleValue();
	}
	
	/**
	 * 带精度的减
	 * @param a	如果为null,则当成0
	 * @param b	如果为null,则当成0
	 * @param scale	精度,向上取整
	 * @return
	 */
	public static Double sub(Double a, Double b, int scale){
		a = a == null ? 0.0 : a;
		b = b == null ? 0.0 : b;
		BigDecimal aa = new BigDecimal(Double.toString(a)).setScale(scale,BigDecimal.ROUND_HALF_UP);
		BigDecimal bb = new BigDecimal(Double.toString(b)).setScale(scale,BigDecimal.ROUND_HALF_UP);
		return aa.subtract(bb).doubleValue();
	}
	
	/**
	 * 带精度的除
	 * @param a	如果为null,则当成0
	 * @param b	如果为null或为0,则抛异常
	 * @param scale	精度,向上取整
	 * @return
	 */
	public static Double div(Double a, Double b, int scale){
		if (isEmpty(b)) throw new ArithmeticException();
		
		a = a == null ? 0.0 : a;
		BigDecimal aa = new BigDecimal(Double.toString(a)).setScale(scale,BigDecimal.ROUND_HALF_UP);
		BigDecimal bb = new BigDecimal(Double.toString(b)).setScale(scale,BigDecimal.ROUND_HALF_UP);
		return aa.divide(bb,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 截断
	 * @param a
	 * @param b
	 * @param scale
	 * @return
	 */
	public static Double divCut(Double a, Double b, int scale){
		if (isEmpty(b)) throw new ArithmeticException();
		
		a = a == null ? 0.0 : a;
		BigDecimal aa = new BigDecimal(Double.toString(a));
		BigDecimal bb = new BigDecimal(Double.toString(b));
		return aa.divide(bb,scale,BigDecimal.ROUND_DOWN).doubleValue();
	}
	
	/**
	 * 带精度的乘
	 * @param a	如果为null,则当成0
	 * @param b	如果为null,则当成0
	 * @param scale	精度,向上取整
	 * @return
	 */
	public static Double mul(Double a, Double b, int scale){
		
		a = a == null ? 0.0 : a;
		b = b == null ? 0.0 : b;
		BigDecimal aa = new BigDecimal(Double.toString(a)).setScale(scale,BigDecimal.ROUND_HALF_UP);
		BigDecimal bb = new BigDecimal(Double.toString(b)).setScale(scale,BigDecimal.ROUND_HALF_UP);
		return aa.multiply(bb).doubleValue();
	}


	/**
	 * 带精度的比较(如果参数a或b为null,则视为0)
	 * @param a
	 * @param b
	 * @param scale
	 * @return
	 */
	public static int compareTo(Double a, Double b, int scale){
		if (a == null){
			a = 0.0;
		}
		if (b == null){
			b = 0.0;
		}
		
		BigDecimal aa = new BigDecimal(Double.toString(a)).setScale(scale,BigDecimal.ROUND_HALF_UP);
		BigDecimal bb = new BigDecimal(Double.toString(b)).setScale(scale,BigDecimal.ROUND_HALF_UP);
		return aa.compareTo(bb);
	}
	
	/**
	 * a > b
	 * @param a
	 * @param b
	 * @param scale	精度
	 * @return
	 */
	public static boolean gt(Double a, Double b, int scale){
		return compareTo(a,b,scale) > 0;
	}
	
	/**
	 * a >= b
	 * @param a
	 * @param b
	 * @param scale
	 * @return
	 */
	public static boolean ge(Double a, Double b, int scale){
		return compareTo(a,b,scale) >= 0;
	}
	
	/**
	 * a == b
	 * @param a
	 * @param b
	 * @param scale
	 * @return
	 */
	public static boolean eq(Double a, Double b, int scale){
		return compareTo(a,b,scale) == 0;
	}
	
	/**
	 * a <= b
	 * @param a
	 * @param b
	 * @param scale
	 * @return
	 */
	public static boolean le(Double a, Double b, int scale){
		return compareTo(a,b,scale) <= 0;
	}
	
	/**
	 * a < b
	 * @param a
	 * @param b
	 * @param scale
	 * @return
	 */
	public static boolean lt(Double a, Double b, int scale){
		return compareTo(a,b,scale) < 0;
	}
	
	/**
	 * a != b
	 * @param a
	 * @param b
	 * @param scale
	 * @return
	 */
	public static boolean ne(Double a, Double b, int scale){
		return compareTo(a,b,scale) != 0;
	}
	
	/**
	 * 加法
	 * @param scale
	 * @param nums
	 * @return
	 */
	public static double addAllCut( int scale, Double... nums){
		if (nums == null || nums.length == 0) return 0.00;
		
		BigDecimal aa = new BigDecimal("0.00");
		for (int i = 0; i < nums.length; i++){
			aa = aa.add(new BigDecimal(String.valueOf(nums[i])));
		}
		return aa.setScale(scale,BigDecimal.ROUND_DOWN).doubleValue();
	}
	
	/**
	 * 获取needCount个随机下标<br>
	 * FIXME cgw:待优化:
	 * 1 正	500量大:分治分N组x人
	 * 2 反	1000000量大:分治分N组x人
	 * z*G 	z:分组大小; G:CPU性能
	 * @param totalCount	目标集合size
	 * @param needCount		所需要的下标个数
	 * @return	如果needCount > totalCount , 则返回一个size为0的set集合
	 */
	public static Set<Integer> selectRandomPartOf(int totalCount, int needCount){
		
		if (totalCount < needCount){
			return Collections.emptySet();
		}
		
		Random rd = new Random();
		Set<Integer> idxs = new HashSet<Integer>();
		
		while (idxs.size() < needCount){
			int index = rd.nextInt(totalCount);
			idxs.add(index);
		}
		return idxs;
	}
	
	/**
	 * 截断
	 * @param a
	 * @param scale
	 * @return
	 */
	public static Double transfer(double a, int scale){
		return new BigDecimal(Double.toString(a)).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 生成纯数字的随机数字符串(最少是13位)
	 * @param len	长度
	 * @return
	 */
	public static String generatePureNumericOrderId(int len){
		
		String orderId = "";
		orderId = String.valueOf(System.currentTimeMillis());
		
		if (len <= 13){
			return orderId;
		} 

		len -= 13;
		StringBuilder sb = new StringBuilder();
		Random rd = new Random();
		for (int i=0; i<len; i++){
			sb.append(rd.nextInt(10));
		}
		
		return orderId+sb.toString();
	}
	
	/**
	 * 生成随机数
	 * @param len	长度，如<=0，则返回1位随机数
	 * @return
	 */
	public static String genRandNumber(int len){
		if (len <= 0) len = 1;
		Random rd = new Random();  
        String[] radmon = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < len; i++) {  
            String s = radmon[rd.nextInt(10)];  
            sb.append(s);  
        }  
        return sb.toString();
	}
	
	/**
	 * 计算一个随机值,该值介于[min,max)之间<br>
	 * @param min
	 * @param max
	 * @return 
	 * @exception	当min > max时,抛出运行时非法参数异常
	 */
	public static int genRandBetween(int min, int max){
		if (min > max) throw new IllegalArgumentException("min > max");
		if (min == max) return min;
		Random rd = new Random();
		int nextInt = rd.nextInt(max-min);
		return nextInt + min;
	}
}
