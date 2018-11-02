package com.dwsj.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetAddressUtil {
//    https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=26.58.68.45&co=&resource_id=6006&t=1530062258212&ie=utf8&oe=gbk&format=json&tn=baidu
    public static String get(String link) throws IOException {
        URL url = new URL(link);
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
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
    private static   byte[] toBytes(InputStream from) throws IOException {
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
    //获取IP地址对应的区域
    public static  String  getAdress(HttpServletRequest request){
        try {
            String result = get("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query="+GetClientIpUtil.getIp(request)+"&co=&resource_id=6006&t=1530062258212&ie=utf8&oe=utf-8&format=json&tn=baidu");
            JSONObject accessorJSON = JSON.parseObject(result);
            JSONObject accessorJSON1 = JSON.parseObject(accessorJSON.getJSONArray("data").get(0).toString());
             return  accessorJSON1.get("location").toString();
        } catch (IOException e) {
            e.printStackTrace();
            return  "地址有误";
        }
    }
    public static void main(String arg[]){
//        System.out.println(getAdress());
    }
}
