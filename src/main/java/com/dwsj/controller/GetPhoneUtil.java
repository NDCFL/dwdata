package com.dwsj.controller;

import com.alibaba.fastjson.JSONObject;
import com.dwsj.common.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.apache.http.util.TextUtils.isEmpty;
@Controller
@RequestMapping("phone")
public class GetPhoneUtil {
    //appid
    public static final String APPID = "f4225c386db2cf1b5bfd8f2e065db5fb";
    //appkey值
    public static final String APPKEY = "f861118ea8126f1e9ce5df50e07157d6f402739d3eeac5864559d59ecde0e9ba";
    public static void main1(String arg[]) {
        GetPhoneUtil getPhoneUtil = new GetPhoneUtil();
        try {
            System.out.println(getPhoneUtil.get("https://crs-api.dianhua.cn/calls/login?token=d05bb2780d8240efa795e662315774d1&sid=SID11af8c6ab6ea42fa82731b9b442b9c7d&tel=17786506171&pin_pwd=825628&sms_code=594320"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int getSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }
    public String md5(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] output = messageDigest.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        String temp;
        for (byte aB : output) {
            temp = (Integer.toHexString(aB & 0XFF));
            if (temp.length() == 1) {
                sb.append("0").append(temp);
            } else {
                sb.append(temp);
            }
        }
        return sb.toString();
    }

    @ResponseBody
    @RequestMapping("phoneCheck")
    public Message getRult(String tel, HttpSession session) throws Exception {
        Integer time = getSecondTimestamp(new Date());
        String sign = md5(APPID+APPKEY+time);
        String tocken = getToken(APPID,sign,time+"");
        session.setAttribute("user1",tocken);
        String url = "https://crs-api.dianhua.cn/calls/flow?token=" + tocken + "&tel=" + tel;
        System.out.println(tel+"===================");
        String result = get(url);
        System.out.println(result);
        return Message.success(result);
    }
    @ResponseBody
    @RequestMapping("getLogin")
    public Message getLogin(String tel,String password,String sid,String sms_code,HttpSession session) throws Exception {
        System.out.println(tel+"===="+password+"======"+sid+"===="+sms_code);
        HashMap<String,String> map  = new HashMap<>();
        map.put("tel",tel);
        map.put("pin_pwd",password);
        map.put("sid",sid);
        map.put("sms_code",sms_code);
        String url = "https://crs-api.dianhua.cn/calls/login?token=" + session.getAttribute("user1").toString();
        System.out.println(url+"===================");
        //http://bmp.dianhua.cn/report/detail?cid=218&no=SIDff368066fe194b5f8e8d8d31022595ca
        String result = post(url,map);
        System.out.println(result+"=========getlogin=");
        return Message.success(result);
    }
    public String getToken(String appid, String sign, String time) throws IOException {
        if (isEmpty(appid) || isEmpty(sign) || isEmpty(time)) {
            throw new IllegalArgumentException();
        }
        String url = "https://crs-api.dianhua.cn/token?appid=" + appid + "&sign=" + sign + "&time=" + time;
        System.out.println(url);
        String result = get(url);
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        System.out.println("getToken: " + result);
        return jsonObject1.get("token").toString();
    }
    /**
     * 工具函数，post 请求
     *
     * @param link 请求链接
     * @param postDataParams 请求参数
     * @return 请求结果
     * @throws IOException
     */
    public static String post(String link, HashMap<String, String> postDataParams) throws IOException {
        URL url = new URL(link);
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            if (!(postDataParams == null || postDataParams.isEmpty())) {
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes(getParamsString(postDataParams));
            }
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
    /**
     * 工具函数，map 转换 String
     *
     * @param params 要转换的 map
     * @return 转换结果
     * @throws IOException
     */
    public static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
    public String get(String link) throws IOException {
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
    @RequestMapping("test")
    public void test(){
        try {
            HashMap<String,String> map  = new HashMap<>();
            map.put("tel","17786506171");
            map.put("sid","SID0c4f95f2f2984c4fb6847e935743ce93");
            map.put("sms_code","393718");
            String url = "https://crs-api.dianhua.cn/calls/verify?token=f54f6e3ec5d656772ab811ec4183f712";
            String result = null;
            result = post(url,map);
            System.out.println(result+"=========getlogin=");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
