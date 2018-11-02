package com.dwsj.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Tools {
	/**
	 * �?��字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/**
	 * �?��字符串是否为�?null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	/**
	 * 字符串转换为字符串数�?
	 * @param str 字符�?
	 * @param splitRegex 分隔�?
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}
	
	/**
	 * 用默认的分隔�?,)将字符串转换为字符串数组
	 * @param str	字符�?
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2DateWithoutCheck(String date){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
	}
	
	/**
	 * 生成流水号(30位)
	 * @return
	 */
	public static String snGenerate(){
		String str = UUID.randomUUID().toString();
		String temp = str.substring(str.length()-13, str.length());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date())+temp;
	}
	
	public static void main(String[] args) {
		
		System.out.println(getRandomStr());
	}
	/**
	 * 生成随机数，8位
	 */
	public static String getRandomStr() {
		String str = UUID.randomUUID().toString();
		return str.substring(str.length()-8, str.length());
	}
	
	
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * JSP页面获取uuid
	 * @return
	 */
	public static String getUUidInJsp(){
		String str = UUID.randomUUID().toString();
		String uuid = str.substring(str.length()-10, str.length());
		return uuid;
	}
	/**
	 * 字符串JSONArray转Object[]
	 * @param jsonArrStr
	 * @return
	 */
	public static Object[] getArrFromJsonArrStr(String jsonArrStr) {   
	    return JSONArray.parseArray(jsonArrStr).toArray();
	}
	
	
	/**
	 * 从request中提取JSON参数
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static JSONObject extractData(HttpServletRequest request) {
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer("");
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			String acceptjson = sb.toString();
			JSONObject obj  = JSONObject.parseObject(acceptjson);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return new JSONObject();
		}
	}
	

	/**
	 * 根据List<Map<String, Object>> list中的map的某个重复值移除整个map
	 * @param key 根据该key去重
	 */
	public static List<Map<String, Object>> removeRepeatMap(List<Map<String, Object>> list,String key) {
        Map<String, Map> msp = new HashMap<String, Map>();
        List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
        //System.out.println("初始数据：" + list.toString());
        //把list中的数据转换成msp,去掉同一id值多余数据，保留查找到第一个id值对应的数据
        for(int i = list.size()-1 ; i>=0; i--){
            Map map = list.get(i);
            String id = map.get(key).toString();
            map.remove(key);
            msp.put(id, map);
        }
        //把msp再转换成list,就会得到根据某一字段去掉重复的数据的List<Map>
        Set<String> mspKey = msp.keySet();
        for(String k: mspKey){
            Map newMap = msp.get(k);
            newMap.put(key, k);
            listMap.add(newMap);
        }
        System.out.println("去掉重复数据后的数据：" + listMap.toString());
        return listMap;
    }
	
	
	public static ArrayList dealUserRemark(String jsonStr) {
		ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		JSONArray arr = JSONArray.parseArray(jsonStr);
		Map<String,Object> map = null;
		JSONObject obj = null;
		for(int i=0;i<arr.size();i++) {
			obj = arr.getJSONObject(i);
			map = new HashMap<String, Object>();
			map.put("content", obj.getString("content"));
			map.put("time", obj.getString("time"));
			list.add(map);
		}
		return list;
	}
	
	
	/**
	 * 隐藏手机号码中间4位
	 * @param phone
	 * @return
	 */
	public static String hidePhone(String phone) {
		if (StringUtils.isEmpty(phone)) {
			return "";
		}
		return phone.substring(0, 3)+"****"+phone.substring(7, 11);
	}

	
	/**
	 * 去除掉小数点后多余的零
	 */
	public static String stripTrailingZeros(BigDecimal price) {
		if (price== null) {
			return "0";
		}
		return price.stripTrailingZeros().toPlainString();
	}
	
}
