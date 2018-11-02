package com.dwsj.crawlUtil;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dwsj.util.system.SysConf;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 黑爬虫提供的接口数据，返回json
 * 文档可参考项目内doc文档
 * @author YuDong
 *
 */
public class BlackSpiderUtil {
	/*
	 * {"Info":{"Name":"朱金梅","Mobile":"13769828011","IDCard":"520202199503064089","Token":"2018031419455094613952","Time":1521027950},"Summary":{"CreditTotal":160500,"UnRepaymentTotal":41000,"OverdueTotal":"0","Overdue":"0","OverdueCount":1,"CreditCount":12,"CreditScore":"0"},"FutureUnrepayment":{"Today":0,"Day1":0,"Day2":0,"Day3":0,"Day4":0,"Day5":0,"Day6":0,"DayMore":41000},"Reports":[{"HostName":"同信圆","Summary":[{"key":"累计借款金额","Value":160500},{"key":"借款次数：","Value":12},{"key":"累计出借金额：","Value":0},{"key":"出借次数：","Value":0},{"key":"当前借款金额：","Value":41000},{"key":"当前出借金额：","Value":0},{"key":"当前应还：","Value":41000},{"key":"逾期次数：","Value":"1"}],"Records":{"0":["司福波","13000","2018-01-21","2018-02-22","还款成功"],"1":["司福波","21500","2018-02-26","2018-04-02","还款成功"],"3":["司福波","10000","2018-03-02","2018-03-11","还款成功"],"4":["司福波","8800","2018-02-17","2018-02-18","还款成功"],"5":["司福波","12500","2018-02-10","2018-02-10","还款成功"],"6":["司福波","5000","2018-02-01","2018-02-07","还款成功"],"7":["司福波","10000","2018-02-28","2018-03-08","还款成功"],"9":["司福波","21500","2018-02-23","2018-02-24","还款成功"],"10":["司福波","10000","2018-02-18","2018-02-28","还款成功"],"11":["司福波","7200","2018-02-20","2018-02-27","还款成功"],"2":["司福波","21500","2018-02-26","2018-03-29","借款成功，待还款"],"8":["司福波","19500","2018-02-22","2018-03-22","借款成功，待还款"]},"Header":["出借人","金额","借款时间","还款时间","状态"],"Status":1},{"HostName":"好记信","Summary":[],"Records":[],"Header":["出借人","金额","借款时间","还款时间","状态"],"Status":2},{"HostName":"蚂蚁支付","Summary":[],"Records":[],"Header":["出借人","金额","借款时间","还款时间","状态"],"Status":0},{"HostName":"叉虫钱包","Summary":[],"Records":[],"Header":["出借人","金额","借款时间","还款时间","状态"],"Status":0},{"HostName":"米缸钱包","Summary":[],"Records":[],"Header":["出借人","金额","借款时间","还款时间","状态"],"Status":0},{"HostName":"大学时代","Summary":[],"Records":[],"Header":["出借人","金额","借款时间","还款时间","状态"],"Status":2},{"HostName":"惠众时代","Summary":[],"Records":[],"Header":["出借人","金额","借款时间","还款时间","状态"],"Status":0},{"HostName":"果*金","Summary":[],"Records":[],"Header":["出借人","金额","借款时间","还款时间","状态"],"Status":0},{"HostName":"我*借","Summary":[],"Records":[],"Header":["出借人","金额","借款时间","还款时间","状态"],"Status":2},{"HostName":"博*信","Summary":[],"Records":[],"Header":["出借人","金额","借款时间","还款时间","状态"],"Status":2},{"HostName":"来*星","Summary":[],"Records":[],"Header":["出借人","金额","借款时间","还款时间","状态"],"Status":2},{"HostName":"易借到","Summary":[],"Records":[],"Header":["出借人","金额","借款时间","还款时间","状态"],"Status":0},{"HostName":"无忧交易宝","Summary":[],"Records":[],"Header":["买家姓名","借款金额","创建日期","到期日期","状态"],"Status":0},{"HostName":"米*服务","Summary":[],"Records":[],"Header":["逾期日期","逾期金额","逾期天数","状态"],"Status":0}],"StatusCode":0}
	 */

	public static JSONObject getTestData() {
		String result = "{\"Info\":{\"Name\":\"朱金梅\",\"Mobile\":\"13769828011\",\"IDCard\":\"520202199503064089\",\"Token\":\"2018031419455094613952\",\"Time\":1521027950},\"Summary\":{\"CreditTotal\":160500,\"UnRepaymentTotal\":41000,\"OverdueTotal\":\"0\",\"Overdue\":\"0\",\"OverdueCount\":1,\"CreditCount\":12,\"CreditScore\":\"0\"},\"FutureUnrepayment\":{\"Today\":0,\"Day1\":0,\"Day2\":0,\"Day3\":0,\"Day4\":0,\"Day5\":0,\"Day6\":0,\"DayMore\":41000},\"Reports\":[{\"HostName\":\"同信圆\",\"Summary\":[{\"key\":\"累计借款金额\",\"Value\":160500},{\"key\":\"借款次数：\",\"Value\":12},{\"key\":\"累计出借金额：\",\"Value\":0},{\"key\":\"出借次数：\",\"Value\":0},{\"key\":\"当前借款金额：\",\"Value\":41000},{\"key\":\"当前出借金额：\",\"Value\":0},{\"key\":\"当前应还：\",\"Value\":41000},{\"key\":\"逾期次数：\",\"Value\":\"1\"}],\"Records\":{\"0\":[\"司福波\",\"13000\",\"2018-01-21\",\"2018-02-22\",\"还款成功\"],\"1\":[\"司福波\",\"21500\",\"2018-02-26\",\"2018-04-02\",\"还款成功\"],\"3\":[\"司福波\",\"10000\",\"2018-03-02\",\"2018-03-11\",\"还款成功\"],\"4\":[\"司福波\",\"8800\",\"2018-02-17\",\"2018-02-18\",\"还款成功\"],\"5\":[\"司福波\",\"12500\",\"2018-02-10\",\"2018-02-10\",\"还款成功\"],\"6\":[\"司福波\",\"5000\",\"2018-02-01\",\"2018-02-07\",\"还款成功\"],\"7\":[\"司福波\",\"10000\",\"2018-02-28\",\"2018-03-08\",\"还款成功\"],\"9\":[\"司福波\",\"21500\",\"2018-02-23\",\"2018-02-24\",\"还款成功\"],\"10\":[\"司福波\",\"10000\",\"2018-02-18\",\"2018-02-28\",\"还款成功\"],\"11\":[\"司福波\",\"7200\",\"2018-02-20\",\"2018-02-27\",\"还款成功\"],\"2\":[\"司福波\",\"21500\",\"2018-02-26\",\"2018-03-29\",\"借款成功，待还款\"],\"8\":[\"司福波\",\"19500\",\"2018-02-22\",\"2018-03-22\",\"借款成功，待还款\"]},\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":1},{\"HostName\":\"好记信\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":2},{\"HostName\":\"蚂蚁支付\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":0},{\"HostName\":\"叉虫钱包\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":0},{\"HostName\":\"米缸钱包\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":0},{\"HostName\":\"大学时代\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":2},{\"HostName\":\"惠众时代\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":0},{\"HostName\":\"果*金\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":0},{\"HostName\":\"我*借\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":2},{\"HostName\":\"博*信\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":2},{\"HostName\":\"来*星\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":2},{\"HostName\":\"易借到\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":0},{\"HostName\":\"无忧交易宝\",\"Summary\":[],\"Records\":[],\"Header\":[\"买家姓名\",\"借款金额\",\"创建日期\",\"到期日期\",\"状态\"],\"Status\":0},{\"HostName\":\"米*服务\",\"Summary\":[],\"Records\":[],\"Header\":[\"逾期日期\",\"逾期金额\",\"逾期天数\",\"状态\"],\"Status\":0}],\"StatusCode\":0}";
		JSONObject object  = JSONObject.parseObject(result);
		return object;
	}
	
	public static void main(String[] args) {
//		BlackSpiderUtil bl = new BlackSpiderUtil();
//		System.out.println(bl.getBlackSpiderData("朱金梅", "13769828011", "520202199503064089"));
		String result = "{\"Info\":{\"Name\":\"朱金梅\",\"Mobile\":\"13769828011\",\"IDCard\":\"520202199503064089\",\"Token\":\"2018031419455094613952\",\"Time\":1521027950},\"Summary\":{\"CreditTotal\":160500,\"UnRepaymentTotal\":41000,\"OverdueTotal\":\"0\",\"Overdue\":\"0\",\"OverdueCount\":1,\"CreditCount\":12,\"CreditScore\":\"0\"},\"FutureUnrepayment\":{\"Today\":0,\"Day1\":0,\"Day2\":0,\"Day3\":0,\"Day4\":0,\"Day5\":0,\"Day6\":0,\"DayMore\":41000},\"Reports\":[{\"HostName\":\"同信圆\",\"Summary\":[{\"key\":\"累计借款金额\",\"Value\":160500},{\"key\":\"借款次数：\",\"Value\":12},{\"key\":\"累计出借金额：\",\"Value\":0},{\"key\":\"出借次数：\",\"Value\":0},{\"key\":\"当前借款金额：\",\"Value\":41000},{\"key\":\"当前出借金额：\",\"Value\":0},{\"key\":\"当前应还：\",\"Value\":41000},{\"key\":\"逾期次数：\",\"Value\":\"1\"}],\"Records\":{\"0\":[\"司福波\",\"13000\",\"2018-01-21\",\"2018-02-22\",\"还款成功\"],\"1\":[\"司福波\",\"21500\",\"2018-02-26\",\"2018-04-02\",\"还款成功\"],\"3\":[\"司福波\",\"10000\",\"2018-03-02\",\"2018-03-11\",\"还款成功\"],\"4\":[\"司福波\",\"8800\",\"2018-02-17\",\"2018-02-18\",\"还款成功\"],\"5\":[\"司福波\",\"12500\",\"2018-02-10\",\"2018-02-10\",\"还款成功\"],\"6\":[\"司福波\",\"5000\",\"2018-02-01\",\"2018-02-07\",\"还款成功\"],\"7\":[\"司福波\",\"10000\",\"2018-02-28\",\"2018-03-08\",\"还款成功\"],\"9\":[\"司福波\",\"21500\",\"2018-02-23\",\"2018-02-24\",\"还款成功\"],\"10\":[\"司福波\",\"10000\",\"2018-02-18\",\"2018-02-28\",\"还款成功\"],\"11\":[\"司福波\",\"7200\",\"2018-02-20\",\"2018-02-27\",\"还款成功\"],\"2\":[\"司福波\",\"21500\",\"2018-02-26\",\"2018-03-29\",\"借款成功，待还款\"],\"8\":[\"司福波\",\"19500\",\"2018-02-22\",\"2018-03-22\",\"借款成功，待还款\"]},\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":1},{\"HostName\":\"好记信\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":2},{\"HostName\":\"蚂蚁支付\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":0},{\"HostName\":\"叉虫钱包\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":0},{\"HostName\":\"米缸钱包\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":0},{\"HostName\":\"大学时代\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":2},{\"HostName\":\"惠众时代\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":0},{\"HostName\":\"果*金\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":0},{\"HostName\":\"我*借\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":2},{\"HostName\":\"博*信\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":2},{\"HostName\":\"来*星\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":2},{\"HostName\":\"易借到\",\"Summary\":[],\"Records\":[],\"Header\":[\"出借人\",\"金额\",\"借款时间\",\"还款时间\",\"状态\"],\"Status\":0},{\"HostName\":\"无忧交易宝\",\"Summary\":[],\"Records\":[],\"Header\":[\"买家姓名\",\"借款金额\",\"创建日期\",\"到期日期\",\"状态\"],\"Status\":0},{\"HostName\":\"米*服务\",\"Summary\":[],\"Records\":[],\"Header\":[\"逾期日期\",\"逾期金额\",\"逾期天数\",\"状态\"],\"Status\":0}],\"StatusCode\":0}";
		JSONObject object  = JSONObject.parseObject(result);
		//System.out.println(object);
		
		//获取用户基础数据
		JSONObject baseUser = object.getJSONObject("Info");
//		Info info = new Info();
//		info.setName(baseUser.getString("Name"));
//		info.setMobile(baseUser.getString("Mobile"));
//		info.setIdcard(baseUser.getString("IDCard"));
		//System.out.println("返回的用户基本信息："+GsonUtils.translateToJson(info));
		
		//获取每日待还数据
		/*JSONObject futureObject = object.getJSONObject("FutureUnrepayment");
		FutureUnrepayment future = new FutureUnrepayment();
		future.setDay1(futureObject.getInt("Day1"));
		future.setDay2(futureObject.getInt("Day2"));
		future.setDay3(futureObject.getInt("Day3"));
		future.setDay4(futureObject.getInt("Day4"));
		future.setDay5(futureObject.getInt("Day5"));
		future.setDay6(futureObject.getInt("Day6"));
		future.setDayMore(futureObject.getInt("DayMore"));
		future.setToday(futureObject.getInt("Today"));
		System.out.println("返回的所有每日待还数据："+GsonUtils.translateToJson(future));*/
		
		//获取各个平台的具体数据
		JSONArray reports = object.getJSONArray("Reports");//所有平台数据
		//System.out.println(reports);
		JSONObject jsonObject = null;
//		for(int i = 0;i<reports.size();i++) {
//			jsonObject = reports.getJSONObject(i);//单个平台数据
//			//System.out.println(jsonObject);
//			if(JSONArray.parseObject(jsonObject.get("Records")).size() > 0) {
//				JSONArray record = JSONArray.parseObject(jsonObject.get("Records"));//单个平台数据具体结款还款信息
//				JSONObject data = null;
//				for(int j=0;j<record.size();j++) {
//					//System.out.println(record.get(j));
//					  Map<String, Object> map = (Map<String, Object>) record.get(j);
//					  for (Entry<String, Object> entry : map.entrySet()) {
//				           // System.out.println(entry.getKey()+"="+entry.getValue());  //单个平台单条还款结款信息
//						  //System.out.println(entry.getValue());
//						  List<String> list = (List<String>) entry.getValue();
//						  for(String str : list) {
//							  System.out.print(str+" ");
//						  }
//						  System.out.println();
//					  }
//
//				}
//			}
			
			
			
//		}
		
		
	}

	
	
	/**
	 * 获取黑爬虫提供的接口数据，原始数据
	 * @param name 姓名
	 * @param phone 手机号
	 * @param idcard 身份证号
	 * @return
	 */
	public static JSONObject getBlackSpiderData(String name,String phone,String idcard){
		try {
			String param="?name="+URLEncoder.encode(name,"UTF-8")+"&mobile="+URLEncoder.encode(phone,"UTF-8")+"&idcard="+URLEncoder.encode(idcard,"UTF-8");
			URL url=new URL(SysConf.HPC_API_URL + param);
		    HttpURLConnection httpConn=(HttpURLConnection)url.openConnection();
		    //设置参数
		    httpConn.setDoOutput(true);   //需要输出
		    httpConn.setDoInput(true);   //需要输入
		    httpConn.setUseCaches(false);  //不允许缓存
		    httpConn.setRequestMethod("GET");   //设置POST方式连接
		    httpConn.setConnectTimeout(90000);//一分半超时
		    //设置请求属性
		    httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		    httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
		    httpConn.setRequestProperty("Charset", "UTF-8");
		    httpConn.setRequestProperty("api-key", SysConf.HPC_API_KEY);
		    httpConn.connect();
		    //建立输入流，向指向的URL传入参数
		    DataOutputStream dos=new DataOutputStream(httpConn.getOutputStream());
		    dos.writeBytes(param);
		    dos.flush();
		    dos.close();
		    //获得响应状态
		    int resultCode=httpConn.getResponseCode();
		    //System.out.println("HttpURLConnection 连接返回状态--> "+ httpConn.getResponseCode());
		    if(HttpURLConnection.HTTP_OK==resultCode){
		      StringBuffer sb=new StringBuffer();
		      String readLine=new String();
		      BufferedReader responseReader=new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
		      while((readLine=responseReader.readLine())!=null){
		        sb.append(readLine).append("\n");
		      }
		      responseReader.close();
		      //System.out.println(sb.toString());
		      return JSONObject.parseObject(sb.toString());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
		return new JSONObject();
	}
	
	
}
