package com.dwsj.yongxun;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ZhengXin98Util {
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
    public static void main(String arg[]) throws IOException {
//        System.out.println(get("http://fly.98zhengxin.com/API/Alipay3?account=17702718868&password=asd123",IDCARD_KEY,IDCARD_CODE));
//        System.out.println(get("http://fly.98zhengxin.com/API/Alipay4?tid=alipay0705172122713be2a62c675d0400da&sms=572282",IDCARD_KEY,IDCARD_CODE));
//        System.out.println(get("http://fly.98zhengxin.com/API/Alipay1?orderId=alipay0705172122713be2a62c675d0400da",IDCARD_KEY,IDCARD_CODE));
        System.out.println(get("https://vip.98zhengxin.com/pre/prevention?name="+ URLEncoder.encode("陈飞龙","utf-8")+"&idcard=360730199712200617",PHONE_KEY,PHONE_CODE));
    }
}
