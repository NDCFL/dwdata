package com.dwsj.crawlUtil;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.StringUtils;

import java.io.IOException;

import static com.sun.xml.internal.ws.api.message.Packet.Status.Request;


public class PingZhengYunUtil {

	public static void main(String[] args) throws IOException {
		//System.out.println(getData("522323199802220546",""));
		getPzyData("421087199308108218","CCFF8CF2867304D0EAA321D9CBF0A80D");
	}
	
	
	/**
	 * 1.登录并获取cookie
	 * @return
	 * @throws IOException
	 */
	public static String getPzyData(String idCard,String cookie) throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("http://pingzhengyun.veyd.cn/ysd/creditController/creditMgr?cardNo="+idCard)
		  .get()
		  .addHeader("Host", "pingzhengyun.veyd.cn")
		  .addHeader("Connection", "keep-alive")
		  .addHeader("Upgrade-Insecure-Requests", "1")
		  .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36")
		  .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
		  .addHeader("Referer", "http://pingzhengyun.veyd.cn/ysd/creditController/queryCredit")
		  .addHeader("Accept-Encoding", "gzip, deflate")
		  .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
		  .addHeader("Cookie", "JSESSIONID="+cookie)
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "f47ed769-dc82-49b5-aec6-1ea41a51dae5")
		  .build();

		Response response = client.newCall(request).execute();
		if (StringUtils.isEmpty(response.body())) {
			return "";
		}
		String html = response.body().string().replace("\r\n", "").replace("\"", "'");
		if (html.contains("该身份证号不在本平台")) {
			html = "<html><body style=\"text-align:center;margin-top:20px\">该身份证号不在本平台,无数据展示</body></html>";
		}
		Document doc = Jsoup.parse(html);
 		doc.select("script").remove();
		
		
		html = doc.html().replaceAll("(\r\n|\r|\n|\n\r)", "").replace("\"", "'").replace("  ", "");  
		
		if (html.length() <= 45) {
			html = "<html><body style=\"text-align:center;margin-top:20px\">该用户未注册凭Z云</body></html>";
		}
		System.out.println(html);
		return html;
	
	}

	
	/*public static String getData(String idCard,String cookie) throws IOException {
		try {
			OkHttpClient client = new OkHttpClient();
			MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
			RequestBody body = RequestBody.create(mediaType, "id_card=" + idCard);
			Request request = new Request.Builder().url("http://pzy.veyd.cn/personal/personal/credit.html").post(body)
					.addHeader("Referer", "http://pzy.veyd.cn/personal/personal/search.htm")
					.addHeader("Content-Type", "application/x-www-form-urlencoded")
					.addHeader("Cookie", "PHPSESSID=" + cookie).addHeader("Cache-Control", "no-cache")
					.addHeader("Postman-Token", "a71de318-ca87-413f-a16f-32676c47b2f5").build();
			Response response = client.newCall(request).execute();
			String result = response.body().string();
			return result.replace("../../../", "http://pzy.veyd.cn/")
					.replace("/static/js/mui.min.js", "http://pzy.veyd.cn/static/js/mui.min.js")
					.replace("/static/js/jquery.min.js", "http://pzy.veyd.cn/static/js/jquery.min.js");
		} catch (Exception e) {
			return "";
		}
	}*/
	
}
