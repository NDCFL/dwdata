package com.dwsj.crawlUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dwsj.util.HttpClientUtil;
import com.dwsj.util.StringUtil;
import com.dwsj.vo.Result;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * @author cgw
 * @date 2018年6月22日
 */
public class XinYuSouUtil {
	
	public static String getXysDataList(String name) {
		
		try {
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("condition", name);
			String jsonStr = HttpClientUtil.httpPost("http://xinyusou.com/order/orderlist", paramMap);
			jsonStr = jsonStr.replace("\\r\\n","");
			
			Document doc = Jsoup.parse(jsonStr);
			Elements elements = doc.select("body > section > div > a");
			// 可能有多个同名的人
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			for (int i = 0; i < elements.size(); i++) {
				Element e = elements.get(i);
				String href = e.select("a").attr("href");
				String img = e.select("img").attr("src");
				String title = e.select("p.tit").html().trim();
				String money = e.select("p > span:nth-child(2) > em").text().trim();
				Map<String,String> item = new HashMap<String,String>();
				item.put("orderId", StringUtil.subString(href,"orderId=",null).replace("orderId=",""));		//
				item.put("img", img);		// 图片
				item.put("title", title);	// 标题
				item.put("money", money);	// 欠款金额
				list.add(item);
			}
			return list.toString().replaceAll("=",":");
		} catch (Exception e) {
			return "";
		}
		
	}
	
	public static String getXysDataByOrderId(String orderId) throws Exception{
		// http://www.xinyusou.com/order/detail?orderId=764560d8006149fd96dbd0c103a12829
		String html = get("http://www.xinyusou.com/order/detail?orderId="+orderId);
		html = html.replace("\\r\\n","");
		html = html.replace("<head>", "<head><base href='http://www.xinyusou.com/'>");
		html = html.replace("信誉搜","地网数据");
		
		Document doc = Jsoup.parse(html);
		doc.select("body > div.site-search").remove();
		doc.select("#search_form").remove();
		doc.select("body > header").remove();
		doc.select("body > section > div > div.col-md-3.user").remove();
		doc.select("#layer-photos-demo > div:nth-child(2)").remove();
		doc.select("body > section > div > div.col-md-9 > div.col-md-12.fx").remove();
		doc.select("#commentForm").remove();
		doc.select("body > section > div > div.col-md-9 > div.col-md-12.margin-top-20").remove();
		doc.select("body > div.footer").remove();
		
		return doc.toString().trim();
	}
	public static String get(String link) throws IOException {
		URL url = new URL(link);
		InputStream stream = null;
		HttpURLConnection connection = null;
		String result = null;
		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(10000);
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.connect();
			int responseCode = connection.getResponseCode();
			if (responseCode != HttpsURLConnection.HTTP_OK) {
				throw new IOException("HTTP error code: " + responseCode);
			}
			stream = connection.getInputStream();
			if (stream != null) {
				byte[] bytes = toBytes(stream);
				result = new String(bytes, StandardCharsets.UTF_8);
			}
		} finally {
			if (stream != null) {
				stream.close();
			}
			if (connection != null) {
				connection.disconnect();
			}
		}
		return result;
	}
	private static byte[] toBytes(InputStream from) throws IOException {
		ByteArrayOutputStream out;
		out = new ByteArrayOutputStream();
		byte[] buf = new byte[8096];
		while (true) {
			int r = from.read(buf);
			if (r == -1) {
				break;
			}
			out.write(buf, 0, r);
		}
		return out.toByteArray();
	}
	public static void main(String[] args) {
		System.out.println(getXysDataList("刘志军").replaceAll("=",":"));
		// 可能有多个同名人
//		Result result = getXysDataList("刘志军");
//		System.out.println(result+"========------================");
//		if (result.isNotOk()) {
//			System.out.println(result+"====================");
//			return;
//		}
//		System.out.println(result.data.toString());
//		JSONArray jsonArr = JSONArray.parseArray(result.data.toString());
//		System.out.println(jsonArr.size());
//		for (Iterator iterator = jsonArr.iterator(); iterator.hasNext();) {
//			Object object = (Object) iterator.next();
//			JSONObject json = JSONObject.parseObject(object.toString());
//			String html = null;
//			try {
//				html = getXysDataByOrderId(json.getString("orderId"));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			System.out.println(html);
//
//		}
	}
}


