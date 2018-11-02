package com.dwsj.crawlUtil;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 博汶信
 * @author YuDong
 *
 */
public class BoWenXin {

	public static void main(String[] args) throws Exception {
		String urlPath = new String("http://www.weixin2933.top/app/index.php?i=238&c=entry&op=loan_history&do=loan_history_index&m=fanchun_money"); 
	    String param="id_card="+URLEncoder.encode("530302199710171212","UTF-8");
		//建立连接
	    URL url=new URL(urlPath);
	    HttpURLConnection httpConn=(HttpURLConnection)url.openConnection();
	    //设置参数
	    httpConn.setDoOutput(true);   //需要输出
	    httpConn.setDoInput(true);   //需要输入
	    httpConn.setUseCaches(false);  //不允许缓存
	    httpConn.setRequestMethod("POST");   //设置POST方式连接
	    //设置请求属性
	    httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    // httpConn.setRequestProperty("Content-Type", "application/json");
	    httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
	    httpConn.setRequestProperty("Charset", "UTF-8");
	    httpConn.setRequestProperty("User-Agent", " Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 MicroMessenger/6.5.2.501 NetType/WIFI WindowsWechat QBCore/3.43.691.400 QQBrowser/9.0.2524.400 ");
	    httpConn.setRequestProperty("Accept-Encoding", "gzip,deflate ");
	    
	    
	    //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
	    httpConn.connect();
	    //建立输入流，向指向的URL传入参数
	    DataOutputStream dos=new DataOutputStream(httpConn.getOutputStream());
	    dos.writeBytes(param);
	    dos.flush();
	    dos.close();
	    //获得响应状态
	    int resultCode=httpConn.getResponseCode();
	    System.out.println("HttpURLConnection 连接返回状态--> "+ httpConn.getResponseCode());
	    //if(HttpURLConnection.HTTP_OK==resultCode){
	      StringBuffer sb=new StringBuffer();
	      String readLine=new String();
	      BufferedReader responseReader=new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
	      while((readLine=responseReader.readLine())!=null){
	        sb.append(readLine).append("\n");
	      }
	      responseReader.close();
	      System.out.println(sb.toString());
	   // } 
	}
	
}
