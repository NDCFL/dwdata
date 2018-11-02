package com.dwsj.crawlUtil;

import com.squareup.okhttp.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * 果然信
 * @author win7 虚拟机
 *
 */
public class GrxUtil {
	public static void main(String[] args) throws IOException {
		getGrxData("421087199308108218","0hoo5dboc7o6hogu47jrofn0p0");
	}
	
	
	
	public static String getGrxData(String idCard,String cookie) throws IOException {
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "identity_num="+idCard);
		Request request = new Request.Builder()
		  .url("http://zzpaper.acx1.cn/user/paper/search.html")
		  .post(body)
		  .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
		  .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Connection", "keep-alive")
		  .addHeader("Content-Length", "31")
		  .addHeader("Content-Type", "application/x-www-form-urlencoded")
		  .addHeader("Cookie", "PHPSESSID="+cookie)
		  .addHeader("Host", "zzpaper.acx1.cn")
		  .addHeader("Origin", "http://zzpaper.acx1.cn")
		  .addHeader("Referer", "http://zzpaper.acx1.cn/user/paper/search.html")
		  .addHeader("Upgrade-Insecure-Requests", "1")
		  .addHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
		  .addHeader("Postman-Token", "cdccb8d4-9cc7-43ab-8f22-e8a9596bc544")
		  .build();

		Response response = client.newCall(request).execute();
		if (StringUtils.isEmpty(response.body())) {
			return "";
		}
		Document doc = Jsoup.parse(response.body().string());
		doc.select("script").remove();
		String html = doc.html().replaceAll("(\r\n|\r|\n|\n\r)", "").replace("\"", "'").replace("  ", "").replace("/themes/simpleboot3/public/assets/", "http://zzpaper.acx1.cn/themes/simpleboot3/public/assets/");  
		System.out.println(html);
		return html; 
		
	}
	
}
