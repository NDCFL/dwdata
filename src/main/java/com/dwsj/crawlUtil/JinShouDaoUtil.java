package com.dwsj.crawlUtil;

import com.dwsj.util.GsonUtils;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 今收到
 * @author YuDong
 *
 */
public class JinShouDaoUtil {

	public static void main(String[] args) throws IOException {
		String phone = "18522179401";//18522179401
		String realName = "冉华松";//冉华松
		System.out.println(GsonUtils.translateToJson(getData(phone, realName,"0qce48e3nrrv5vdimin89re0i7")));
	}
	
	public static Map<String,Object> getData(String phone,String realName,String cookie) {
		try {
			String html = stepOne(phone, URLEncoder.encode(realName), cookie);
			Map<String, Object> map = parseHtmlAndGetUid(html);
			if ((boolean) map.get("status")) {
				int uid = Integer.parseInt((String) map.get("uid"));
				String result = stepTwo(uid, cookie);
				//{"man":{"avatar":"http:\/\/thirdwx.qlogo.cn\/mmopen\/jWRoRZ0xMshEfxS8oYCJrOk8JvXSTRy4upXYApCD47wX5RQ0QoaBicd6pM34Ofn3JEeVlIubVw9MAF5RemRyLn2w41mtBUFoR\/132132","realname":"\u5189\u534e\u677e"},"item":{"count_money":4000,"count_repaying":2000,"count_person":1,"count_times":2},"black":{"max_money":0,"count_money":0,"count_times":0,"count_now_money":0,"more_than_seven_times":0,"more_than_seven_money":0,"report_times":0},"list":[{"realname":"\u5218\u5c11\u602a","avatar":"","id":"YXpxbGZhaGhqZ2c3","money":"2000","begintime":"1525536000","endtime":"1525968000","status":"\u5f85\u8fd8\u6b3e","b_time":"2018-05-06","time":"2018-05-11"},{"realname":"\u5218\u5c11\u602a","avatar":"","id":"YXpxbGZhaGdobHE3","money":"2000","begintime":"1525536000","endtime":"1525881600","status":"\u5df2\u5b8c\u6210","b_time":"2018-05-06","time":"2018-05-10"}]}
				//带部分注释：{"black":{"count_money_逾期总额":0,"count_now_money":0,"count_times_逾期次数":0,"max_money":0,"more_than_seven_money_大于七天逾期金额":0,"more_than_seven_times_大于七天逾期次数":0,"report_times":0},"item":{"count_money_累计借入金额":4000,"count_person_累计借入人数":1,"count_repaying_待还总额":2000,"count_times_累计借入笔数":2},"list":[{"avatar":"","b_time":"2018-05-06","begintime":"1525536000","endtime":"1525968000","id":"YXpxbGZhaGhqZ2c3","money":"2000","realname":"刘少怪","status":"待还款","time":"2018-05-11"},{"avatar":"","b_time":"2018-05-06","begintime":"1525536000","endtime":"1525881600","id":"YXpxbGZhaGdobHE3","money":"2000","realname":"刘少怪","status":"已完成","time":"2018-05-10"}],"man":{"avatar":"http://thirdwx.qlogo.cn/mmopen/jWRoRZ0xMshEfxS8oYCJrOk8JvXSTRy4upXYApCD47wX5RQ0QoaBicd6pM34Ofn3JEeVlIubVw9MAF5RemRyLn2w41mtBUFoR/132132","realname":"冉华松"}}
				map = new HashMap<String, Object>();
				map.put("status", true);
				map.put("msg", result);
			} else {
				//System.out.println("error");
				map = new HashMap<String, Object>();
				map.put("status", false);
				map.put("msg", "未注册用户或当前未逾期");
			}
			return map;
		} catch (Exception e) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("status", false);
			map.put("msg", "系统异常，本次查询失败");
			return map;
		}
	
	}
	
	
	/**
	 * @param realName 需url编码
	 * 1.首先调用查询接口；
	 * 2.在返回结果里找到<div class="container container-fill"></div>里<script>标签中util.message的内容；
	 * 3.会有两种结果：
	 * 		(a)--> util.message('未注册用户或当前未逾期，可以通过好友列表查看信用报告与负债明细', '', 'error');
	 * 		(b)--> util.message('success', 'http://web.moneyll.cn/app/index.php?i=2&c=entry&do=index&m=xuan_jjd&url=http%3A%2F%2Fweb.moneyll.cn%2Faddons%2Fxuan_jjd%2Ftemplate%2Fmobile%2Ffind_credit_2.html%3Fuid%3D3069', 'success');
	 * 4.判断util.message，如果有路径，则取出其中的uid；
	 * 5.调用http://web.moneyll.cn/app/index.php?i=2&c=entry&do=member&op=find_credit&m=xuan_jjd&uid=[uid]
	 * 6.解析返回的json
	 * @return
	 * @throws IOException
	 */
	public static String stepOne(String phone,String realName,String cookie) throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("http://web.moneyll.cn/app/index.php?i=2&c=entry&do=member&op=check_credit&m=xuan_jjd&phone="+phone+"&realname="+realName)
		  .get()
		  .addHeader("Referer", "http://web.moneyll.cn/addons/xuan_jjd/template/mobile/check_credit.html")
		  .addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Mobile Safari/537.36")
		  .addHeader("Cookie", "USER_UID=3070; PHPSESSID="+cookie)
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "34896f6d-347c-4d8b-a0bf-990518286171")
		  .build();

		Response response = client.newCall(request).execute();
		String result = response.body().string();
		return result;
	}
	
	/**
	 * 2.解析返回的html并拿到uid，若无uid，则直接返回结果
	 */
	public static Map<String,Object> parseHtmlAndGetUid(String html) {
		Map<String,Object> map = new HashMap<String,Object>();
		Document doc = Jsoup.parse(html);
        String nodeText = doc.select("div.container-fill").select("script").first().toString();  
        if (nodeText.contains("http://web.moneyll.cn/app/index.php")) {//有账户
 			String[] temp = nodeText.split("3Fuid%3D");
 			int index = temp[1].indexOf("'");
 			map.put("status", true);
 			map.put("uid", temp[1].substring(0, index));
 		}else {//无账户
 			map.put("status", false);
 			//System.out.println("未注册用户或当前未逾期"); 
 		}
		return map;
	}
	
	public static String stepTwo(int uid,String cookie) throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("http://web.moneyll.cn/app/index.php?i=2&c=entry&do=member&op=find_credit&m=xuan_jjd&uid="+uid)
		  .get()
		  .addHeader("Referer", "http://web.moneyll.cn/addons/xuan_jjd/template/mobile/check_credit.html")
		  .addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Mobile Safari/537.36")
		  .addHeader("Cookie", "USER_UID=3070; PHPSESSID="+cookie)
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "34896f6d-347c-4d8b-a0bf-990518286171")
		  .build();

		Response response = client.newCall(request).execute();
		String result = response.body().string();
		return result;
	}
	
	
}
