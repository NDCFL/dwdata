package com.dwsj.common;

import com.alibaba.fastjson.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class IdCardUtil  {
    private static final String IDCARD_KEY = "011121803291300413N7T1G6A0L7D";
    private static final String IDCARD_CODE = "e2454aa17ae66728840587394e9f9057";
    private static final String PHONE_KEY = "00351805291807267K6E5J6R4T0P";
    private static final String PHONE_CODE = "68489950b04fcd3fbb2be06a9ecee6b1";

    public static String get(String link,String key,String code) throws IOException {
        URL url = new URL(link);
        InputStream stream = null;
        HttpURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("COMPANY_KEY", key);
            connection.setRequestProperty("COMPANY_CODE", code);
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
    public static  void main(String arg[]) throws IOException {
        //调用身份证信息查询
//        System.out.println(get("http://vip.98zhengxin.com/idcard/card1?name="+URLEncoder.encode("陈飞龙")+"&idcard=360730199712200617",PHONE_KEY,PHONE_CODE));
//        System.out.println(get("http://vip.98zhengxin.com/idcard/idcardanalysis?idcard=360730199712200617",PHONE_KEY,PHONE_CODE));
        //调用手机二元素查询
//        String result = get("http://vip.98zhengxin.com/mobile/mobilesimple1?name="+URLEncoder.encode("陈飞龙")+"&mobile=16619772839",PHONE_KEY,PHONE_CODE);
//        JSONObject jsonObject = JSONObject.parseObject(result);
//        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
//        System.out.println(jsonObject);
        //支付宝
//        System.out.println(get("http://fly.98zhengxin.com/API/Alipay3?account=17702718868&password=asd123",IDCARD_KEY,IDCARD_CODE));
        //输入短信验证码
//        System.out.println(get("http://fly.98zhengxin.com/API/Alipay4?tid=alipay0702101912322ca51247de6354526b&sms=156047",IDCARD_KEY,IDCARD_CODE));
//        System.out.println(get("http://fly.98zhengxin.com/API/Alipay1?orderId=alipay0702101912322ca51247de6354526b",IDCARD_KEY,IDCARD_CODE));
        //京东
//        System.out.println(get("http://vip.98zhengxin.com/bd/jd?username=17702718868&password=asdf1234",PHONE_KEY,PHONE_CODE));
//        System.out.println(get("http://vip.98zhengxin.com/bd/jd1?tid=JD08397657ca8b4e4e8b66d12612ba4310",PHONE_KEY,PHONE_CODE));
//        淘宝
//        System.out.println(get("http://vip.98zhengxin.com/bd/tb1?username="+ URLEncoder.encode("17786506171")+"&password=asd123&months=12",PHONE_KEY,PHONE_CODE));
//        System.out.println(get("http://vip.98zhengxin.com/bd/tb3?tid=TB0727181147803deb3438c30034e91ba154&check=507931",PHONE_KEY,PHONE_CODE));
//        System.out.println(get("http://vip.98zhengxin.com/bd/tb2?tid=TB072718151641930c3fb26c7724d99861a9",PHONE_KEY,PHONE_CODE));
    }
}
