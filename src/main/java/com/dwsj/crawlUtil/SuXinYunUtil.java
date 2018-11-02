package com.dwsj.crawlUtil;

import com.dwsj.util.MD5Utils;
import com.dwsj.util.system.SysConf;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class SuXinYunUtil {
    /**
     * 接口调用  POST
     */
        public static String httpURLConnectionPOST(String path,String param) {
        try {
            //传递参数

            URL url = new URL(path);
            // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
            // 此时cnnection只是为一个连接对象,待连接中
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
            connection.setDoOutput(true);
            // 设置连接输入流为true
            connection.setDoInput(true);
            // 设置请求方式为post
            connection.setRequestMethod("POST");
            // post请求缓存设为false
            connection.setUseCaches(false);
            // 设置该HttpURLConnection实例是否自动执行重定向
            connection.setInstanceFollowRedirects(true);
            InetAddress address = InetAddress.getLocalHost();
            String ip = address.getHostAddress();//获得本机IP
            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            connection.connect();
            // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
            // 格式 parm = aaa=111&bbb=222&ccc=333&ddd=444
            String parm = param;
            System.out.println("传递参数：" + parm);
            // 将参数输出到连接
            dataout.writeBytes(parm);
            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)
            //System.out.println(connection.getResponseCode());
            // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder(); // 用来存储响应数据

            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
                //sb.append(bf.readLine());
                sb.append(line).append(System.getProperty("line.separator"));
            }
            bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
            connection.disconnect(); // 销毁连接
            return  sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static String getRandomStr() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        return str.substring(str.length()-8, str.length());
    }
    public static String getData(String idcard){
        String timeStamp = System.currentTimeMillis()+"";
        String randomStr =  new SuXinYunUtil().getRandomStr();
        String sign = timeStamp + randomStr + idcard + "c09c9bdf42e3a5714f7de0cac9fd5306";
        sign = DigestUtils.shaHex(sign);
        sign = MD5Utils.getMD5Str(sign);
        sign = sign.toUpperCase();
        return  httpURLConnectionPOST("https://newapi.sujiexing.com/api/respond","uid=1001&timeStamp="+timeStamp+"&randomStr="+randomStr+"&idCard="+idcard+"&sign="+sign);

    }
    public static void main(String arg[]){
        String timeStamp = System.currentTimeMillis()+"";
        String randomStr =  new SuXinYunUtil().getRandomStr();
        String sign = timeStamp + randomStr + "510922199707290290" + "c09c9bdf42e3a5714f7de0cac9fd5306";
        sign = DigestUtils.shaHex(sign);
        sign = MD5Utils.getMD5Str(sign);
        sign = sign.toUpperCase();
        System.out.println(httpURLConnectionPOST("https://newapi.sujiexing.com/api/respond","uid=1001&timeStamp="+timeStamp+"&randomStr="+randomStr+"&idCard=510922199707290290&sign="+sign));
    }
}
