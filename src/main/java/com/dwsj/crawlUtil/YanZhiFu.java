package com.dwsj.crawlUtil;

import com.dwsj.util.StringUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/**
 * 颜值付
 * 
 * @author YuDong
 *
 */
public class YanZhiFu {
	
	public static void main(String[] args) throws Exception {
		System.out.println(getJSONData("陈莉", "15885991281"));
	}	
	
	
	
	public static String getJSONData(String name,String phone) throws Exception{
		String urlPath = new String("https://miaojiedao.cn/app/index.php?i=2&c=entry&do=member&op=check_credit&m=xuan_jjd"); 
		//&phone=15885991281&realname=陈莉
	    String param="&realname="+URLEncoder.encode(name,"UTF-8")+"&phone="+URLEncoder.encode(phone,"UTF-8");
		//建立连接
	    URL url=new URL(urlPath);
	    HttpURLConnection httpConn=(HttpURLConnection)url.openConnection();
	    //设置参数
	    httpConn.setDoOutput(true);   //需要输出
	    httpConn.setDoInput(true);   //需要输入
	    httpConn.setUseCaches(false);  //不允许缓存
	    httpConn.setRequestMethod("GET");   //设置POST方式连接
	    //设置请求属性
	    httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");
	    httpConn.setRequestProperty("Host", "miaojiedao.cn");
	    httpConn.setRequestProperty("Referer", "https://miaojiedao.cn/addons/xuan_jjd/template/mobile/check_credit.html");
	    httpConn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
	    httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
	    httpConn.setRequestProperty("Charset", "UTF-8");
	    //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
	    httpConn.connect();
	    //建立输入流，向指向的URL传入参数
	    DataOutputStream dos=new DataOutputStream(httpConn.getOutputStream());
	    dos.writeBytes(param);
	    dos.flush();
	    dos.close();
	    //获得响应状态
	    int resultCode=httpConn.getResponseCode();
	   // System.out.println("HttpURLConnection 连接返回状态--> "+ httpConn.getResponseCode());
	    if(HttpURLConnection.HTTP_OK==resultCode){
	      StringBuffer sb=new StringBuffer();
	      String readLine=new String();
	      BufferedReader responseReader=new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
	      while((readLine=responseReader.readLine())!=null){
	        sb.append(readLine).append("\n");
	      }
	      responseReader.close();
	      return StringUtil.ascii2native(sb.toString());
		}
		return null;
	}
	
}
