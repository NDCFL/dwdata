package com.dwsj.crawlUtil;

import com.alibaba.fastjson.JSONObject;
import com.dwsj.util.ExecPythonUtils;
import com.dwsj.util.HttpClientUtil;
import com.dwsj.util.system.SysConf;
import com.squareup.okhttp.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 签了么
 * @author YuDong
 *
 */
public class QianLeMeUtil {
	
	
	public static void main(String[] args) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		String[] params = new String[]{"17786506171","asd123"};
		paramMap.put("pyPath","C:\\python\\dwsjshuju\\WuYoujt");
//		String[] params = new String[]{"陈莉","15885991281"};
//		paramMap.put("pyPath", SysConf.PYTHON_LOCAL_PATH+"/qlm/");
		paramMap.put("pyName", "run.py");
		paramMap.put("params", params);
		String jsonStr = HttpClientUtil.httpPost("http://www.dwsjshuju.com/callpy/call", paramMap,"UTF-8");
		jsonStr = jsonStr.replace("\r\n","");
		System.out.println(jsonStr);
	}
	
	
	
	

	/**
	 * 调用脚本
	 * @param name
	 * @param phone
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws Exception
	 */
	public static String getQLMData(String name,String phone){
		try {
			String[] param = new String[]{name,phone};
			System.out.println(SysConf.PYTHON_LOCAL_PATH+"qlm/"+"===============");
			String jsonStr = ExecPythonUtils.execPythonShell(SysConf.PYTHON_LOCAL_PATH+"qlm/", "run.py",param);
			//String jsonStr = ExecPythonUtils.execPythonShell("F:/python/dwsjshuju/qlm", "run.py",param);
			if (!StringUtils.isEmpty(jsonStr)) {
	  			jsonStr = jsonStr.replace("\\\"", "'").replace("\\n", "").replace("\\r", "").replace("\\n", "").replace("\\t", "")
						.replace("/themes/default/Public/assets/", "http://qlm.tuoxinjz.cn/themes/default/Public/assets/");
				//return StringUtil.ascii2native(jsonStr);
				Document doc = Jsoup.parse(JSONObject.parseObject(jsonStr).getString("data"));
				doc.select("script").remove();
				doc.select("a").remove();
				//iconfont
				doc.select("i.iconfont").remove();
				String html = doc.html().replaceAll("(\r\n|\r|\n|\n\r)", "").replace("\"", "'").replace("  ", "");  
				//System.out.println(html);
				
					return new String(html.getBytes(),"UTF-8");
			}else{
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} 
	}
	
	
	
	/**
	 * 1.签了么登录
	 * @throws IOException
	 */
	public static int login () throws IOException {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
		RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"nameid\"\r\n\r\n421087199408258213\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"pass\"\r\n\r\n825825\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
		Request request = new Request.Builder()
		  .url("http://qlm.hzjyxs.com/index.php?g=user&m=login&a=zh_account")
		  .post(body)
		  .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
		  .addHeader("Host", "qlm.hzjyxs.com")
		  .addHeader("Connection", "keep-alive")
		  .addHeader("Accept", "*/*")
		  .addHeader("X-Requested-With", "XMLHttpRequest")
		  .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")
		  .addHeader("Referer", "http://qlm.hzjyxs.com/index.php?g=&&g=user&m=login&a=index")
		  .addHeader("Accept-Encoding", "gzip, deflate")
		  .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "2aca3d63-4c38-4bac-b391-e329f764c32b")
		  .build();

		Response response = client.newCall(request).execute();
		try {
			JSONObject resp = JSONObject.parseObject(response.body().string());
			if (resp.getInteger("code") == 200) {
				return 200;
			}
		} catch (Exception e) {
			return 201;
		}
		return 201;
	}
	
	
	
	/**
	 * 2.请求查询页面，获取到其ck的值后进行查询
	 * @throws IOException
	 */
	public static String query(String name,String phone) throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("http://qlm.hzjyxs.com/index.php?g=user&m=center&a=index")
		  .get()
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "48dd9a84-8cf0-45a3-979f-f5f617dfdf88")
		  .build();
		Response response = client.newCall(request).execute();
		//拿到cookie
		String cookieStr = response.header("Set-Cookie");
		String[] temp = cookieStr.split(";");
		String cookie = temp[0];
		
		String html = response.body().string();
		Document doc = Jsoup.parse(html);
		String ck = doc.getElementsByClass("ck").first().val();
		System.out.println("ck-->"+ck);
		
		
		
		return null;
	}
	
	/**
	 * 获取代理ip
	 * @param size 数量
	 * @return
	 * @throws IOException 
	 */
	public static List<String> proxyIPs(int size) throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("http://dev.kuaidaili.com/api/getproxy/?orderid=962718882440819&num="+size+"&b_pcchrome=1&b_pcie=1&b_pcff=1&protocol=2&method=2&an_an=1&an_ha=1&sp1=1&sp2=1&quality=1&sort=1&sep=3")
		  .get()
		  .addHeader("Cache-Control", "no-cache")
		  .build();

		Response response = client.newCall(request).execute();
		String ret = response.body().string();
		if (ret.contains("ERROR")) {
			return new ArrayList<String>();
		}
		String[] temp = null;
		List<String> ips = new ArrayList<String>();
		temp = ret.split(" ");
		for(int i=0;i<temp.length;i++) {
			ips.add(temp[i]);
		}
		return ips;
	}

	
}