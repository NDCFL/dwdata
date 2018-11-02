package com.dwsj.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpProtocolParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HttpClientUtil {


	public static String httpReader(String url, String code){
		System.out.println("GetPage:"+url);
		
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);	
		
		String result = null;
		try {
			client.executeMethod(method);
			int status = method.getStatusCode();
			if (status == HttpStatus.SC_OK) {
				result = method.getResponseBodyAsString();
			} else {
				System.out.println("Method failed: " + method.getStatusLine());
			}
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			System.out.println("发生网络异常");
			e.printStackTrace();
		} finally{
			// 释放连接
			if(method!=null)method.releaseConnection();
			method = null;
			client = null;
		}
		return result;
	}
	
	public static String httpGet(String url) {
		return httpGet(url,"UTF-8");
	}
	public static String httpGet(String url,String code) {
		System.out.println("GetPage:"+url);
		String content = null;
		HttpClient httpClient = new HttpClient();
		// 设置header
		httpClient.getParams().setParameter(HttpMethodParams.USER_AGENT,"Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2");
		GetMethod method = new GetMethod(url);
		try {
			int statusCode = httpClient.executeMethod(method);
			System.out.println("httpClientUtils::statusCode="+statusCode);
			System.out.println(method.getStatusLine());
			content = new String(method.getResponseBody(), code);
			
		} catch (Exception e) {
			System.out.println("time out");
			e.printStackTrace();
		} finally {
			if(method!=null)method.releaseConnection();
			method = null;
			httpClient = null;
		}
		return content;
	}
	
	public static String httpPost(String url, Map<?,?> paramMap, String code) {
		System.out.println("GetPage:"+url);
		String content = null;
		if (url == null || url.trim().length() == 0 
				/*|| paramMap == null
				|| paramMap.isEmpty()*/
				)
			return null;
		HttpClient httpClient = new HttpClient();
		// 设置header
		httpClient.getParams().setParameter(HttpMethodParams.USER_AGENT,"Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2");//
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, code);
		// 代理设置
		//httpClient.getHostConfiguration().setProxy("128.128.176.74", 808);
		
		PostMethod method = new PostMethod(url);
		if (paramMap != null && !paramMap.isEmpty()) {
			Iterator<String> it = (Iterator<String>) paramMap.keySet().iterator();

			while (it.hasNext()) {
				String key = it.next() + "";
				Object o = paramMap.get(key);
				if (o != null && o instanceof String) {
					method.addParameter(new NameValuePair(key, o.toString()));
				}
				if (o != null && o instanceof String[]) {
					String[] s = (String[]) o;
					if (s != null)
						for (int i = 0; i < s.length; i++) {
							method.addParameter(new NameValuePair(key, s[i]));
						}
				}
			}
		}
		
		try {
			int statusCode = httpClient.executeMethod(method);
			
			System.out.println("httpClientUtils::statusCode="+statusCode);
			System.out.println("======= "+ GsonUtils.translateToJson(method.getResponseHeaders()));
			System.out.println(method.getStatusLine());
			content = new String(method.getResponseBody(), code);
		} catch (Exception e) {
			System.out.println("time out");
			e.printStackTrace();
		} finally {
			if(method!=null)method.releaseConnection();
			method = null;
			httpClient = null;
		}
		
		return content;

	}

	public static String httpPost(String url, Map paramMap) {
		//编码：UTF-8
		return HttpClientUtil.httpPost(url, paramMap, "UTF-8");
	}
	
	public static Map<String,String> httpPostAndRuturnHeaders(String url, Map paramMap) {
		//编码：UTF-8
		return HttpClientUtil.httpPostAndRuturnHeaders(url, paramMap, "UTF-8");
	}
	
	private static Map<String,String> httpPostAndRuturnHeaders(String url, Map paramMap, String code) {
		System.out.println("GetPage:"+url);
		String content = null;
		Map<String,String> map = null;
		if (url == null || url.trim().length() == 0 
				/*|| paramMap == null
				|| paramMap.isEmpty()*/
				)
			return null;
		HttpClient httpClient = new HttpClient();
		// 设置header
		httpClient.getParams().setParameter(HttpMethodParams.USER_AGENT,"Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2");//
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, code);
		// 代理设置
		//httpClient.getHostConfiguration().setProxy("128.128.176.74", 808);
		
		PostMethod method = new PostMethod(url);
		if (paramMap != null && !paramMap.isEmpty()) {
			Iterator<String> it = paramMap.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next() + "";
				Object o = paramMap.get(key);
				if (o != null && o instanceof String) {
					method.addParameter(new NameValuePair(key, o.toString()));
				}
				if (o != null && o instanceof String[]) {
					String[] s = (String[]) o;
					if (s != null)
						for (int i = 0; i < s.length; i++) {
							method.addParameter(new NameValuePair(key, s[i]));
						}
				}
			}
		}
		try {
			int statusCode = httpClient.executeMethod(method);
			System.out.println("httpClientUtils::statusCode="+statusCode);
			//System.out.println("======= "+GsonUtils.translateToJson(method.getResponseHeader("Set-Cookie")));
			System.out.println(method.getStatusLine());
			content = new String(method.getResponseBody(), code);
			map = new HashMap<String, String>();
			map.put("data", content);
			map.put("headers", GsonUtils.translateToJson(method.getResponseHeaders()));
			map.put("cookie", GsonUtils.translateToJson(method.getResponseHeader("Set-Cookie")));
		} catch (Exception e) {
			System.out.println("time out");
			e.printStackTrace();
		} finally {
			if(method!=null)method.releaseConnection();
			method = null;
			httpClient = null;
		}
		
		return map;
	}

	@SuppressWarnings("deprecation")
	public static String sendCCB(String url,String xml) throws Exception { 
		DefaultHttpClient httpclient = new DefaultHttpClient();
		StringBuilder sb = new StringBuilder();
		HttpPost httppost = null;
		BufferedReader reader = null;
		try {
			// 代理的设置
			// HttpHost proxy = new HttpHost("10.60.8.20", 8080);
			// httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
			// proxy);
			httppost = new HttpPost(url);
			System.out.println("请求: " + httppost.getRequestLine());
			
			
			// 构造最简单的字符串数据
			String xmlaa = null;
			xmlaa = new String(xml.getBytes("GBK"),"GB2312");
			String reqbody = "requestXml=" + xmlaa;
			StringEntity reqEntity = new StringEntity("GB2312");
			// 设置类型
			reqEntity.setContentType("application/x-www-form-urlencoded");

			// 设置请求的数据
			httppost.setEntity(reqEntity);
			httppost.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
			httppost.setHeader("Accept-Encoding", "gzip, deflate");
			HttpProtocolParams.setUserAgent(httppost.getParams(),
							"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; LEN2; InfoPath.2; CIBA)");
			httppost.setHeader(
					"Accept",
					"image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
			httppost.setHeader("Accept-Language", "zh-cn");
			httppost.setHeader("connection", "close");

			//HttpEntity en = httppost.getEntity();
			//System.out.println(inputStream2String(en.getContent()));
			// 执行
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			//System.out.println(response.getStatusLine());
//			if (entity != null) {
//				System.out.println("Response content length: "+ entity.getContentLength());
//			}
			// 显示结果
			reader = new BufferedReader(new InputStreamReader(entity.getContent(), "GB18030"));
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			if (entity != null) {
				entity.consumeContent();
			}
			
		} finally {
			httppost = null;
			httpclient = null;
			reader.close();
		}
		
		return sb.toString();
		 
	 }
	 public static String inputStream2String(InputStream is){
			try
			{
			   BufferedReader in = new BufferedReader(new InputStreamReader(is));
			   StringBuffer buffer = new StringBuffer();
			   String line = "";
			   while ((line = in.readLine()) != null){
			     buffer.append(line);
			   }
			   return buffer.toString();
			}catch(Exception e)
			{
				e.printStackTrace();
				return "Error";
			}
		}
}
