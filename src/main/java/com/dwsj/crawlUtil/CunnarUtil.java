package com.dwsj.crawlUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dwsj.util.FileUtil;
import com.dwsj.util.ImageUtil;
import com.dwsj.util.NumberUtil;
import com.dwsj.util.system.SysConf;
import com.squareup.okhttp.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * 存证云
 * @author win7 虚拟机
 *
 */
public class CunnarUtil {
	
//	public static void main(String[] args) throws IOException {
		
		//System.out.println(GsonUtils.translateToJson(proxyIPs(20)));
		//System.out.println(sendGet("?phone=18627985213&tpl=LOGIN", "https://www.cunnar.com/sms.html", 30));
		
	/*	List<String> ips = proxyIPs(100);
		System.out.println(GsonUtils.translateToJson(ips));
		String returnCode = sendSmsAndReturnCode("18627980213",ips);
		System.out.println(returnCode);*/
		/*if ("9999".equals(returnCode) || "8000".equals(returnCode) || "9998".equals(returnCode)) {
			//return GsonUtils.translateToJson(new JsonResult(201, "系统繁忙，请稍后再试。"));
		}else {
			 if ("1022".equals(returnCode)) {
				 //return GsonUtils.translateToJson(new JsonResult(201, "手机号码已存在。"));
		 	} else {
                 if ("1017".equals(returnCode)) {
                	 //return GsonUtils.translateToJson(new JsonResult(201, "随机码错误。")); 
                 } 
            }
		}*/
		//需要输入3个参数：手机号、短信验证码、序列号（短信内）
//		String[] param = new String[] {"18627985213","851658","569"};
//		ExecPythonUtils.execPythonShell("C:\\Users\\win7 虚拟机\\Desktop\\cunnar", "run.py", param);
		//String[] param = new String[] {"18627985213","851658","569"};
		//ExecPythonUtils.execPythonShell("F:/python/dwsjshuju/cunnar/", "run.py", param);
		
		
//	}
	
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
	
	
	/**
	 * 1.发送短信并返回状态码
	 * @param proxyIPs [代理ip:端口]列表
	 * */
	public static String sendSmsAndReturnCode(String mobile,List<String> proxyIPs) throws IOException {
		String ip = null;
		String port = null;
		String[] temp = null;
		for(int i =0;i<proxyIPs.size();i++) {
			temp = proxyIPs.get(i).split(":");
			ip = temp[0];
			port = temp[1];
			String result = null;
			try {
				result = sendSmsAndReturnCode(mobile, ip, Integer.parseInt(port)).replace("\"", "");
			} catch (Exception e) {
				System.out.println(ip+":"+port+"-->超时");
				continue;
			}
			System.out.println(ip+":"+port+"  序列号-->"+result);
			if (!"1022".equals(result)) {
				return result;
			}
		}
		return "1022";
	}
	
	
	private static String sendSmsAndReturnCode(String mobile,String ip,int port) throws IOException {
		OkHttpClient client = new OkHttpClient().setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port)));
		client.setReadTimeout(20, TimeUnit.SECONDS);
		//OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("multipart/form-data");
		RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"phone\"\r\n\r\n18627985213\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"tpl\"\r\n\r\nLOGIN\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
		Request request = new Request.Builder()
		  .url("https://www.cunnar.com/sms.html?phone="+mobile+"&tpl=LOGIN")
		  .get()
		  .addHeader("content-type", "multipart/form-data")
		  .addHeader("Host", "www.cunnar.com")
		  .addHeader("Connection", "keep-alive")
		  .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
		  .addHeader("X-Requested-With", "XMLHttpRequest")
		  .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36")
		  .addHeader("Referer", "https://www.cunnar.com/login.html")
		  .addHeader("Accept-Encoding", "gzip, deflate, br")
		  .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
		  .addHeader("Cache-Control", "no-cache")
		  .build();

		Response response = client.newCall(request).execute();
		String ret = response.body().string();
		if (ret.startsWith("\"")) {
			ret = ret.substring(1, ret.length() - 1);
		}
		return ret;
	}
	
	
	
	  private static String sendGet(String param,String url,Integer timeoutSecond) {
	        String result = "";
	        BufferedReader in = null;
	        try {
	            String urlNameString = url + param;
	        	
	            URL realUrl = new URL(urlNameString);
	            @SuppressWarnings("static-access")
	            Proxy proxy = new Proxy(Proxy.Type.DIRECT.HTTP, new InetSocketAddress("39.105.107.145", 16818));
	            URLConnection connection = (HttpURLConnection) realUrl.openConnection(proxy);
	            connection.setRequestProperty("accept", "*/*");
	            connection.setRequestProperty("content-type", "multipart/form-data");
	            connection.setRequestProperty("Host", "www.cunnar.com");
	            connection.setRequestProperty("Connection", "keep-alive");
	            connection.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
		  		connection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
		  		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36");
		  		connection.setRequestProperty("Referer", "https://www.cunnar.com/login.html");
		  		connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
		  		connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
		  		connection.setRequestProperty("Cache-Control", "no-cache");
	            if (timeoutSecond != null && timeoutSecond > 0) {
	            	 connection.setConnectTimeout(timeoutSecond*1000);
	                 connection.setReadTimeout(timeoutSecond*1000);
				}
	            connection.connect();
	            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	            //System.out.println(result);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        // 使用finally块来关闭输入流
	        finally {
	            try {
	                if (in != null) {
	                    in.close();
	                }
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        }
	        return result;
	    }
	
	
	
	public static StringBuilder log = new StringBuilder();
	
	/**
	 * 解析python爬出来的json数据:<br>
	 * 调用脚本，获取到脚本返回的数据后，将pdf转移到文件服务器路径下，再转成图片，<br>
	 * 拿到图片路径后拼入json，存入数据库，处理完后，修改存证云认证状态
	 * @param jsonStr <br>
	 * 		格式：{"code": 100,"result": [{"name": "xyz.pdf","path": "E:\xxx\cunnar\17702718868\xyz.pdf"},{"name": "abc.pdf","path": "E:\xxx\cunnar\17702718868\abc.pdf"}]}
	 * @return
	 */
	public static JSONObject parseJsonAndSaveFileFromPy(final String jsonStr){
		String newjsonStr = jsonStr.replace("\r\n", "");
		try {
			JSONObject json = JSONObject.parseObject(newjsonStr);
			Integer code = (Integer) json.get("code");
			if (code != 100){ return null; }
			
			JSONArray arr = json.getJSONArray("result");
			JSONObject retRoot = new JSONObject();
			retRoot.put("code", code);
			JSONArray result = new JSONArray();
			for (int i=0; i<arr.size(); i++){
				JSONObject contract = arr.getJSONObject(i);
				String name = (String) contract.get("name");
				String path = contract.get("path").toString();
				String pureName = name.substring(0, name.length()-4);
				String phone = FileUtil.getLevelNameFromRight(path, 1);
				String newName = pureName+NumberUtil.genRandNumber(7)+".pdf";
				
				String decPath = SysConf.upload_path+"/"+FileUtil.getLevelNameFromRight(path, 2)+"/"+phone+"/"+newName;
				boolean copyTo = FileUtil.copyTo(path, decPath);
				if (!copyTo) {
					log.append("失败: copyTo----------------------------------------------------S\n");
					log.append("失败: path = "+path+"\n");
					log.append("失败: decPath = "+decPath+"\n");
					log.append("失败: copyTo----------------------------------------------------E\n");
				} else {
					log.append("成功: copyTo----------------------------------------------------S\n");
					log.append("成功: path = "+path+"\n");
					log.append("成功: decPath = "+decPath+"\n");
					log.append("成功: copyTo----------------------------------------------------E+\n");
				}
				String imgPath = ImageUtil.pdfToPngAndClippingReturnImgPath(decPath,327,1000,2220,2170);
				String imgUrl = SysConf.upload_url+"/"+FileUtil.getLevelNameFromRight(path, 2)+"/"+phone+"/"+FileUtil.getLevelNameFromRight(imgPath, 0);
				//转URL路径
				JSONObject retJson = new JSONObject();
				retJson.put("path", imgUrl);
				retJson.put("name", name);
				result.add(retJson);
			}
			retRoot.put("result", result);
			return retRoot;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解析存证云py返回的json："+e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		String name = "(1)刘少怪与韩腾腾的个人借款合同.pdf";
		String path = "E:\\aa\\cunnar\\17702718868\\"+name;
		String pureName = name.substring(0, name.length()-4);
		String phone = FileUtil.getLevelNameFromRight(path, 1);
		String newName = pureName+NumberUtil.genRandNumber(7)+".pdf";
		
		String decPath = "C:/aa/"+FileUtil.getLevelNameFromRight(path, 2)+"/"+phone+"/"+newName;
		boolean copyTo = FileUtil.copyTo(path, decPath);
		if (!copyTo) {
			System.out.println("asdkjasdkhakjsjhdkahskd");
		}
		String imgPath = ImageUtil.pdfToPngAndClippingReturnImgPath(decPath,327,1000,2220,2170);
		String imgUrl = SysConf.upload_url+"/"+FileUtil.getLevelNameFromRight(path, 2)+"/"+phone+"/"+FileUtil.getLevelNameFromRight(imgPath, 0);
		//转URL路径
		JSONObject retJson = new JSONObject();
		retJson.put("path", imgUrl);
		retJson.put("name", name);
		System.out.println(retJson);
	}
}
