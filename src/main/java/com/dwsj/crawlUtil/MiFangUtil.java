package com.dwsj.crawlUtil;

import com.alibaba.fastjson.JSONObject;
import com.dwsj.util.GsonUtils;
import com.dwsj.util.HttpClientUtil;
import com.dwsj.util.JsonResult;
import com.dwsj.util.StringUtil;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 米房服务官网数据获取
 * @author YuDong
 *
 */
public class MiFangUtil {

	public static void main(String[] args) throws IOException {
		
		String str = "aliyungf_tc=AQAAAKPBjG0xXQsAgYYOt7nUOM0vG/2U; Path=/; HttpOnly, SESSION=b0bfa312-ee5f-4ae3-8f48-b84e687fc9c1";
		String[] cooTemp = str.split(";");
		String aliyungfTc = cooTemp[0];
		String[] session = cooTemp[2].split(",");
		System.out.println(session[1]+"; "+aliyungfTc);
	}
	
	/**
	 * 获取个人基础数据
	 * {"id":335222,"username":"d1194ab2-92dd-4b47-ad76-e71950b7d7e9","nickname":"刘少怪","avatar":"http://wx.qlogo.cn/mmopen/vi_32/KNmVd2yl1J8jTEGPJR3J6Y3d7JCBDxiaRt04de58kccg22DF31mwKAfD0gczCWZy9Ax8AibyLzCdt7m1b7v8Gddw/0","state":"enable","create_time":"2017-11-11T10:19:28Z","modify_time":"2017-11-11T10:19:28Z","depts":[{"id":2,"type":2,"name":"个人","description":"系统预设个人群组","create_time":"2017-04-24T18:58:13Z","modify_time":"2017-04-24T18:58:13Z"}],"roles":[{"id":100,"type":2,"name":"个人","description":"预设个人角色","dept_id":2,"permissions":[]}],"wechat_binding":true,"uaa_binding":false,"phone":"17702718868","id_no":"421087199408258213","real_name":"刘少怪","bank_card_binded":true}
	 * @param cookie
	 * @return
	 */
	public static String getBaseInfoData(String cookie) {
		String url = "https://hhd-api.tmindtech.com/v1/own";
		String result = null;
		try {
			result = sendGet(url, cookie, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取首页的数据
	 * {"borrow":{"total":{"money":273400,"interest":0,"overtime_interest":394,"count":2,"confirm_money":0},"in7days":{"money":273400,"interest":0,"overtime_interest":394,"count":2,"confirm_money":0},"in30days":{"money":273400,"interest":0,"overtime_interest":394,"count":2,"confirm_money":0}},"lend":{"total":{"money":312500,"interest":0,"overtime_interest":0,"count":1,"confirm_money":0},"in7days":{"money":312500,"interest":0,"overtime_interest":0,"count":1,"confirm_money":0},"in30days":{"money":312500,"interest":0,"overtime_interest":0,"count":1,"confirm_money":0}}}
	 * @param cookie
	 * @throws IOException
	 */
	public static String getIndexData(String cookie){
		String url = "https://hhd-api.tmindtech.com/v1/iou/stat";
		String result = null;
		try {
			result = sendGet(url, cookie, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * 获取借入的数据
	 * @param cookie
	 * @throws IOException
	 * {"offset":0,"count":2,"total_count":2,"data_list":[{"id":6129467,"iou_no":"201804280610056129467","type":0,"creator":{"id":167680,"name":"L","avatar":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLoaDUhelx5SbR4msGIWDABQUEKIV5RworKSK0SDX0S6RDQhyQ8B6TJn30ibG77WEXMKJWh0Ic8nMg/0"},"borrower_id":335222,"borrower_name":"刘少怪","lender_id":167680,"lender_name":"周亮","money":100000,"create_time":"2018-04-28T06:10:05Z","trade_time":"2018-04-28T06:17:34Z","start_time":"2018-04-28T00:00:00Z","end_time":"2018-05-04T00:00:00Z","interest_rate":200,"use_target_type":"10001","use_target_desc":"","state":2,"is_report":0,"days":6,"interest":0,"overtime_interest":0,"paid_money":100000,"confirm_money":100000,"borrow_is_overdue":true,"uuid":"8495b5736473401b99f52be7019aee5f"},{"id":6129874,"iou_no":"201804280637416129874","type":0,"creator":{"id":335222,"name":"刘少怪","avatar":"http://wx.qlogo.cn/mmopen/vi_32/KNmVd2yl1J8jTEGPJR3J6Y3d7JCBDxiaRt04de58kccg22DF31mwKAfD0gczCWZy9Ax8AibyLzCdt7m1b7v8Gddw/0"},"borrower_id":335222,"borrower_name":"刘少怪","lender_id":1833617,"lender_name":"余凡","money":150000,"create_time":"2018-04-28T06:37:41Z","trade_time":"2018-04-28T06:57:51Z","start_time":"2018-04-28T00:00:00Z","end_time":"2018-04-29T00:00:00Z","interest_rate":1000,"use_target_type":"10007","use_target_desc":"家里装修","state":1,"is_report":0,"days":1,"interest":0,"overtime_interest":296,"paid_money":0,"confirm_money":0,"borrow_is_overdue":true,"uuid":"e97442ca6c1e4b1b8341f2f927bc9ded"}]}
	 */
	public static String getBorrowData(String cookie){
		String url = "https://hhd-api.tmindtech.com/v1/ious?state_list=1,2&is_report=0,1,2,9&is_borrow=true&search=&offset=0&limit=100";
		String result = null;
		try {
			result = sendGet(url, cookie, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取借出的数据
	 * {"offset":0,"count":1,"total_count":1,"data_list":[{"id":1481593,"iou_no":"201711111308241481593","type":0,"creator":{"id":188066,"name":"潮起潮落","avatar":"http://wx.qlogo.cn/mmopen/vi_32/nrMb0MeXDlxtUriaoF08uGa8JCvmibJKzIibyLAo1Qnn0Ldvna0QRxCvGjYTEucpia57Y9EzEicmibamLrHNNZgzqMOw/0"},"borrower_id":188066,"borrower_name":"吴鹏","lender_id":335222,"lender_name":"刘少怪","money":140000,"create_time":"2017-11-11T13:08:24Z","trade_time":"2017-11-11T13:13:20Z","start_time":"2017-11-11T00:00:00Z","end_time":"2017-11-23T00:00:00Z","interest_rate":0,"use_target_type":"10009","use_target_desc":"","state":2,"is_report":0,"days":6,"interest":0,"overtime_interest":0,"paid_money":140000,"confirm_money":140000,"borrow_is_overdue":true}]}
	 * @param cookie
	 * @throws IOException
	 */
	public static String getLendData(String cookie){
		String url = "https://hhd-api.tmindtech.com/v1/ious?state_list=1,2&is_report=0,1,2,9&is_borrow=false&search=&offset=0&limit=100";
		String result = null;
		try {
			result = sendGet(url, cookie, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取逾期的数据
	 * {"borrow_total":{"money":373400,"count":3},"lender_count":2,"borrow_return_same_day_count":0,"borrow_current":{"money":273400,"count":2},"lend_total":{"money":452500,"count":2},"borrower_count":2,"lend_return_same_day_count":0,"lend_current":{"money":312500,"count":1},"overtime_total":{"money":150000,"count":1},"overtime7days":{"money":0,"count":0},"overtime_current":{"money":150000,"count":1}}
	 * @param cookie
	 * @throws IOException
	 */
	public static String getOverdueData(String cookie,int userId){
		String url = "https://hhd-api.tmindtech.com/v1/iou/public_stat?owner_id="+userId;
		String result = null;
		try {
			result = sendGet(url, cookie, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 登录并返回登录信息
	 * Cookie:SESSION=e1aeb4fa-e469-4371-8125-737814c8e29d
	 * @param phone
	 * @param code
	 * @return
	 */
	public static String loginAndReturnCookie(String phone,String code) {
		String url = "https://hhd-api.tmindtech.com/v1/login/phone";
		Map<String,String> params = new HashMap<String,String>();
		params.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/56.0.2924.75 Mobile/14E5239e Safari/602.1");
		params.put("Access-Control-Request-Headers", "content-type");
		params.put("Referer", "https://hhd.tmindtech.com");
		params.put("Origin:", "https://hhd.tmindtech.com");
		params.put("Accept:", "application/json");
		params.put("Accept-Encoding", "gzip, deflate, br");
		params.put("Accept-Language", "zh-CN,zh;q=0.9");

		params.put("phone", phone);
		params.put("is_bind_login", "false");
		params.put("code", code);
		
		Map<String,String> map = HttpClientUtil.httpPostAndRuturnHeaders(url, params);
		//System.out.println(GsonUtils.translateToJson(map.get("data")));
		//System.out.println(GsonUtils.translateToJson(map.get("headers")));
		//System.out.println(GsonUtils.translateToJson(map.get("cookie")));
		if (StringUtils.isEmpty(map.get("cookie")) || map.get("cookie").equals("null")) {
			//不考虑验证码过期或者错误的情况，直接认为登录失败
			JSONObject data = JSONObject.parseObject(map.get("data"));
			if(data.getBoolean("code") && (data.getInteger("code") == 400007006  )) {
				return GsonUtils.translateToJson(new JsonResult(400, data.getString("message"),phone));
			}else if(data.getBoolean("code") && (data.getInteger("code") == 400007004  )) {
				sendMiFangSMS(phone);
				return GsonUtils.translateToJson(new JsonResult(400,"短信验证码已过期，已经重新下发短信",phone));
			}else {
				return GsonUtils.translateToJson(new JsonResult(400,"登录失败"));
			}
		}else {
			JSONObject obj = JSONObject.parseObject(StringUtil.ascii2native(map.get("cookie")));
			String[] arr = obj.get("value").toString().split(";path");
			//aliyungf_tc=AQAAAKPBjG0xXQsAgYYOt7nUOM0vG/2U; Path=/; HttpOnly, SESSION=b0bfa312-ee5f-4ae3-8f48-b84e687fc9c1
			String[] cooTemp = arr[0].split(";");
			String aliyungfTc = cooTemp[0];
			String[] session = cooTemp[2].split(",");
			System.out.println(session[1]+"; "+aliyungfTc);
			
			return GsonUtils.translateToJson(new JsonResult(200,"登录成功",session[1]+"; "+aliyungfTc));
		}
		
	}
	
	/**
	 * 发送短信验证码
	 * @param phone
	 * @return
	 */
	public static String sendMiFangSMS(String phone) {
		String url = "https://hhd-api.tmindtech.com/v1/login/phone/verify_code";
		Map<String,String> params = new HashMap<String,String>();
		params.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/56.0.2924.75 Mobile/14E5239e Safari/602.1");
		params.put("Access-Control-Request-Headers", "content-type");
		params.put("Referer", "https://hhd.tmindtech.com");
		params.put("phone", phone);
		params.put("response", "BZV3NVgALZRiJezaRmit4l6u96eJX3G7C25Qf1bODtlX7LUthtCpb0K_S7m9mBHavxwQtmGJLVQ27yhu5JLMUKyXMoOCdMiv7H7LyApJT8XJAlAb-HZ-3snn0Y80Prm8VGmLRGcS9cXJkoywOccaFLBlP7fXsXZScSQERXPLCGL85zzCQIMH9Qh5laOOHgLBrW7Hvu-7r9nAwyKlqiP15hudmB9kXgLLkXp24_mHvCCU7I_1mplGIzdYeZaMkVoDGot-9IZ8LCgBF9rxYFYWwQ5_xL5YO7TUgM1r0SPsDWA");
		
		String content = HttpClientUtil.httpPost(url, params);
		System.out.println(content);
		return content;
		
	}
	
	
	/**
	 * 发送带cookie的get请求
	 * @param str_param_url
	 * @param cookie
	 * @param charset
	 * @throws IOException
	 */
	 private static String sendGet(String str_param_url,String cookie,String charset) throws IOException  {  
        // 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码  
        //    String getURL = GET_URL + "?username="  + URLEncoder.encode("fat man", "utf-8");  
        String getURL = str_param_url;
        URL getUrl = new URL(getURL);
        // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，  
        // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection  
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();  
  
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/56.0.2924.75 Mobile/14E5239e Safari/602.1"); 
		connection.setRequestProperty("Access-Control-Request-Headers", "content-type");  
		connection.setRequestProperty("Referer", "https://hhd.tmindtech.com");
		connection.setRequestProperty("Origin", "https://hhd.tmindtech.com");
		connection.setRequestProperty("Accept", "application/json");  
		connection.setRequestProperty("Connection", "keep-alive");  
        if (cookie != null) {  
            //发送cookie信息上去，以表明自己的身份，否则会被认为没有权限  
            //System.out.println("set cookieVal = [" + cookie + "]");
            connection.setRequestProperty("Cookie", cookie);  
        }
        // 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到  
        // 服务器  
        connection.connect();  
        // 取得输入流，并使用Reader读取  
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),charset));  
        StringBuffer sb = new StringBuffer();
        String lines;  
        while ((lines = reader.readLine()) != null)  {  
             //System.out.println(lines);  
             sb.append(lines);
        }  
        reader.close();  
        // 断开连接  
        connection.disconnect();  
        return sb.toString();
    }  
	  
	
	 /**
	  * 只获取逾期数据，传入cookie
	  * {"offset":0,"count":2,"total_count":2,"data_list":[{"overtime":"2018-04-29T00:00:00Z","money":150000,"days":21,"state":1},{"overtime":"2018-05-10T00:00:00Z","money":123400,"days":10,"state":1}]}
	  */
	 public static String getOverDueData(String name,String phone,String cookie) throws IOException {
		 try {
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder()
					.url("https://hhd-api.tmindtech.com/v1/ious/public_overtime/new?name=" + name + "&phone=" + phone
							+ "&offset=0&limit=40")
					.get().addHeader("Host", "hhd-api.tmindtech.com").addHeader("Connection", "keep-alive")
					.addHeader("Upgrade-Insecure-Requests", "1")
					.addHeader("User-Agent",
							"Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
					.addHeader("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
					.addHeader("Accept-Language", "zh-CN,zh;q=0.9").addHeader("Cookie", "SESSION=" + cookie)
					.addHeader("Cache-Control", "no-cache")
					.addHeader("Postman-Token", "019363ae-1cef-441f-9e9b-a6aab7efb1b5").build();
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "{\"count\":0,\"data_list\":[],\"offset\":0,\"total_count\":0}";
	 }
	 
	 
	 
}
